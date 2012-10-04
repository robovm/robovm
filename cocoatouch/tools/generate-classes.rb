#!/usr/bin/ruby

require 'rubygems'
require 'nokogiri'
require 'open-uri'
require 'yaml'
require 'fileutils'

IGNORED_PROTOCOLS = ['NSObject', 'NSCoding', 'NSCopying', 'NSMutableCopying', 'NSFastEnumeration']

$script_name = File.basename($0, File.extname($0))

def assert(msg = nil)
  raise "Assertion failed: #{msg}" unless yield
end

class Array
  def odd_values
    self.values_at(* self.each_index.select {|i| i.odd?})
  end
  def even_values
    self.values_at(* self.each_index.select {|i| i.even?})
  end
end

def objc_to_java(type, typedefs, overrides = nil)
  if type =~ /^oneway\s+(.*?)$/ then return objc_to_java($1, typedefs) end
  if type =~ /^inout\s+(.*?)$/ then return objc_to_java($1, typedefs) end
  if type =~ /^const\s+(.*?)$/ then return objc_to_java($1, typedefs) end

  java_type = overrides || typedefs[type] || case type
  when 'BOOL' then 'boolean'
  when /^void \(\^\)\(void\)$/ then 'VoidBlock'
  when /^void \(\^\)\(BOOL.*\)$/ then 'VoidBooleanBlock'
  when 'Class' then 'ObjCClass'
  when 'id' then 'NSObject'
  when 'NSInteger' then 'int'
  when 'NSUInteger' then 'int'
  when 'NSInteger *' then 'IntPtr'
  when 'NSUInteger *' then 'IntPtr'
  when 'NSIndex' then 'int'
  when 'NSTimeInterval' then 'double'
  when 'CFTimeInterval' then 'double'
  when 'CGFloat' then 'float'
  when 'CGFloat *' then 'FloatPtr'
  when 'unichar' then 'char'
  when 'void *' then 'VoidPtr'
  when 'SEL' then 'Selector'
  when /NSString\s*\*/ then 'String'
  when /^id\s*<\s*(.*?)\s*>$/ then $1
  when /^(.*?)\s*\*\*$/ then "Ptr<#{$1}>"
  when /^(.*?)\s*\*$/ then $1
  else type
  end

  "@Type(\"#{type}\") #{java_type}"
end

def arg_to_java(arg, typedefs, overrides)
  type = arg[0]
  name = arg[1]
  if overrides && overrides[name] && overrides[name]['type']
    type = objc_to_java(type, typedefs, overrides[name]['type'])
  else
    type = objc_to_java(type, typedefs)
  end
  if overrides && overrides[name] && overrides[name]['name']
    name = overrides[name]['name']
  end
  "#{type} #{name}"
end

def wildcard_match(pattern, s)
  re = '^' + ("_#{pattern}_".split('*').map {|part| Regexp.quote(part)}.join('.*').slice(1..-2)) + '$'
  s.match(re)
end

class ObjCClass
  attr_accessor :name
  attr_accessor :superclass
  attr_accessor :conf
  attr_accessor :typedefs
  attr_accessor :protocols
  attr_accessor :methods
  attr_accessor :properties
  attr_accessor :is_protocol
  def initialize(name, conf, typedefs, is_protocol = false)
    @name = name
    @conf = conf
    @typedefs = typedefs
    @protocols = []
    @methods = []
    @properties = []
    @is_protocol = is_protocol
  end
  def to_java(template)
    @properties = @properties.sort_by {|p| p.name}
    @properties.uniq!
    @methods = @methods.sort_by {|m| m.static ? "0_#{m.name}" : "1_#{m.name}"}
    @methods.uniq!

    template = template.gsub(/\/\*<name>\*\/.*\/\*<\/name>\*\//, "/*<name>*/ #{@name} /*</name>*/")
    template = template.sub(/\/\*<extends>\*\/.*\/\*<\/extends>\*\//, "/*<extends>*/ #{@superclass} /*</extends>*/")
    if !@protocols.empty?
      if @is_protocol
        template = template.sub(/\/\*<implements>\*\/.*\/\*<\/implements>\*\//, "/*<implements>*/ extends #{@protocols.join(', ')} /*</implements>*/")
      else
        template = template.sub(/\/\*<implements>\*\/.*\/\*<\/implements>\*\//, "/*<implements>*/ implements #{@protocols.join(', ')} /*</implements>*/")
      end
    else
      template = template.sub(/\/\*<implements>\*\/.*\/\*<\/implements>\*\//, "/*<implements>*/ /*</implements>*/")
    end
    constructors = @methods.select {|m| m.constructor?}
    constructors_s = constructors.map {|m| m.to_java}.join("\n    ") + "\n"
    if !@conf['skip_def_constructor']
      constructors_s = "public #{@name}() {}\n    " + constructors_s
    end
    template = template.sub(/\/\*<constructors>\*\/.*\/\*<\/constructors>\*\//m, "/*<constructors>*/\n    #{constructors_s}    /*</constructors>*/")
    properties_s = @properties.map {|p| p.to_java}.flatten.join("\n    ")
    template = template.sub(/\/\*<properties>\*\/.*\/\*<\/properties>\*\//m, "/*<properties>*/\n    #{properties_s}\n    /*</properties>*/")
    methods_s = @methods.select {|m| !m.constructor?}.map {|m| m.to_java}.join("\n    ")
    template = template.sub(/\/\*<methods>\*\/.*\/\*<\/methods>\*\//m, "/*<methods>*/\n    #{methods_s}\n    /*</methods>*/")
    template
  end
end

class ObjCMethod
  attr_accessor :clazz
  attr_accessor :selector
  attr_accessor :conf
  attr_accessor :name
  attr_accessor :selector
  attr_accessor :static
  def initialize(clazz, objcprot)
    @clazz = clazz
    @objcprot = objcprot
    @static = objcprot.start_with?('+')
    objcprot = objcprot.sub(/^[-+]\s*/, '')
    if objcprot.index(':') == nil
      parts = objcprot.match(/^(.*?)([^\)]+)$/).values_at(1, 2)
    else
      parts = objcprot.split(/(\w+:)/)
    end
    parts = parts.map {|p| p.strip}
    @returnType = parts.shift.sub(/^\((.*)\)$/, '\\1')
    @name = parts.first.chomp(':')
    @args = parts.odd_values.map {|a| a.match(/^\((.*?)\)([^\)]+?)[.;]*$/).values_at(1, 2)}
    @selector = parts.even_values.join('')
    methods_conf = @clazz.conf['methods'] || {}
    conf_key = (@static ? '+' : '-') + @selector
    @conf = methods_conf[conf_key]
    if !methods_conf.has_key?(conf_key)
      # Treat * in keys as wildcards and use the first match
      @conf = (methods_conf.to_a.select {|p| wildcard_match(p[0], conf_key)}.first || [nil, nil])[1]
    end
    @conf = @conf || {}
    @name = @conf['name'] || @name
    @visibility = @conf['visibility'] || 'public'
  end
  def static?
    @static
  end
  def constructor?
    !@clazz.is_protocol && !@static && @name.start_with?('init') && (!@conf.has_key?('constructor') || @conf['constructor']) && 
        (@returnType == 'id' || @returnType == 'instancetype' || @returnType == "#{@clazz.name} *")
  end
  def to_java
    jtype = objc_to_java(@returnType, @clazz.typedefs, @conf['return_type'])
    args = @args.map {|a| arg_to_java(a, @clazz.typedefs, @conf['args'])}
    if constructor? then
      "@Bind(\"#{@selector}\") #{@visibility} #{@clazz.name}" + '(' + args.join(', ') + ') {}'
    elsif @clazz.is_protocol
      "@Bind(\"#{@selector}\") " + (@static && 'static ' || '') + jtype + ' ' + @name + '(' + args.join(', ') + ');'
    else
      "@Bind(\"#{@selector}\") #{@visibility} native " + (@static && 'static ' || '') + jtype + ' ' + @name + '(' + args.join(', ') + ');'
    end
  end
  def hash
    @selector.hash
  end
  def eql?(o)
    @selector.eql?(o.selector)
  end  
end

class ObjCProperty
  attr_accessor :clazz
  attr_accessor :conf
  attr_accessor :name
  attr_accessor :getter_selector
  attr_accessor :setter_selector
  def initialize(clazz, decl)
    @clazz = clazz
    @decl = decl
    decl = decl.sub(/^@property\s*/, '')
    /(\((?:[^)]+)\))?\s*(.*)/ =~ decl
    type_name = $2
    @attrs = $1 != nil ? $1.strip.slice(1..-2).split(/,\s*/) : []
    @attrs = @attrs.inject(Hash.new) do |h, o|
      pair = o.split(/\s*=\s*/)
      h[pair[0]] = pair.size > 1 ? pair[1] : true
      h
    end
    /^(.*?)(\w+);?$/ =~ type_name
    @type = $1.strip
    @name = $2.strip
    @objc_name = @name
    @getter_selector = @attrs.has_key?('getter') ? @attrs['getter'] : @name
    @setter_selector = @attrs.has_key?('setter') ? @attrs['setter'] : "set" + @name.slice(0,1).capitalize + @name.slice(1..-1) + ":"

    props_conf = @clazz.conf['properties'] || {}
    @conf = props_conf[@name]
    if !@conf
      # Treat * in keys as wildcards and use the first match
      @conf = (props_conf.to_a.select {|p| wildcard_match(p[0], @name)}.first || [nil, nil])[1]
    end
    @conf = @conf || {}
    @name = @conf['name'] || @name
    @base_java_name = @name.slice(0,1).capitalize + @name.slice(1..-1)
    @visibility = @conf['visibility'] || 'public'
  end
  def read_only?
    @attrs['readonly']
  end
  def getter_to_java
    jtype = objc_to_java(@type, @clazz.typedefs, @conf['type'])
    getter = @type == 'BOOL' ? "is#{@base_java_name}" : "get#{@base_java_name}"
    if @clazz.is_protocol
      "@Bind(\"#{@getter_selector}\") #{jtype} #{getter}();"
    else
      "@Bind(\"#{@getter_selector}\") #{@visibility} native #{jtype} #{getter}();"
    end
  end
  def setter_to_java
    jtype = objc_to_java(@type, @clazz.typedefs, @conf['type'])
    setter = @attrs.has_key?('setter') ? @attrs['setter'] : "set#{@base_java_name}"
    if @clazz.is_protocol
      "@Bind(\"#{@setter_selector}\") void #{setter}(#{jtype} v);"
    else
      "@Bind(\"#{@setter_selector}\") #{@visibility} native void #{setter}(#{jtype} v);"
    end
  end
  def to_java
    read_only? ? [getter_to_java] : [getter_to_java, setter_to_java]
  end
  def hash
    @objc_name.hash
  end
  def eql?(o)
    @objc_name = o.objc_name
  end
end

class ObjCEnum
  attr_accessor :name
  def initialize(decl, conf)
    @decl = decl
    conf = conf || {}
    @conf = conf
    @values = decl.sub(/.*enum\s*\{(.*?)\}.*/m, '\\1').gsub(/\/\/.*\n/, "\n").strip.chomp(',').split(/,/).map {|s| s.strip}
    @values = @values.map do |s|
      pair = s.split(/\s*=\s*/)
      [pair[0], pair.size > 1 ? pair[1].gsub(/(\d+)UL/, '\\1').gsub(/NSUIntegerMax/, '0xFFFFFFFF') : nil]
    end
    @values[0][1] = @values[0][1] || '0'
    @values.each_with_index do |p, i|
      if p[1] != nil
        p[1] = p[1] =~ /^-?\d+$/ ? p[1].to_i : p[1]
        if !p[1].is_a?(Fixnum)
          enum = conf.keys.select {|k| p[1].start_with?(k)}.first
          if enum && enum
            p[1] = "#{enum}.#{p[1].slice(enum.size .. -1)}.value()"
          end
        end
      elsif @values[i - 1][1].is_a?(Fixnum)
        p[1] = @values[i - 1][1] + 1
      else 
        p[1] = @values[i - 1][1] + ' + 1'
      end
    end

    # Determine common prefix
    prefix = @values[0][0].dup
    @values[1..-1].each do |p|
      e = p[0]
      prefix.slice!(e.size..-1) if e.size < prefix.size   # optimisation
      prefix.chop! while e.index(prefix) != 0
    end

    first = @values[0][0]
    @name = (conf.select {|key, value| (value || {})['first'] == first}.first || [nil])[0] || prefix
    @values = @values.map {|p| [p[0][prefix.size .. -1], p[1]]}
  end
  def to_java
    extra_values = (@conf[@name] || {})['extra_values'] || []
    s = "public enum #{@name} {\n"
    s += @values.map {|v| "#{v[0]}(#{v[1]})"}.concat(extra_values).map {|v| "    #{v}"}.join(",\n") + ";\n\n"
    s += "    private final int n;\n\n"
    s += "    private #{@name}(int n) { this.n = n; }\n"
    s += "    public int value() { return n; }\n"
    s += "    public static #{@name} fromValue(int n) {\n"
    s += "        for (#{@name} v : values()) {\n"
    s += "            if (n == v.value()) {\n"
    s += "                return v;\n"
    s += "            }\n"
    s += "        }\n"
    s += "        throw new IllegalArgumentException(\"Unknown #{@name} value: \" + n);\n"
    s += "    }\n"
    if (@conf[@name] || {})['bitmask']
      s += "    public static EnumSet<#{@name}> fromBits(int bits) {\n"
      s += "        EnumSet<#{@name}> set = EnumSet.noneOf(#{@name}.class);\n"
      s += "        for (#{@name} v : values()) {\n"
      s += "            int value = v.value();\n"
      s += "            if ((bits & value) == value) {\n"
      s += "                set.add(v);\n"
      s += "            }\n"
      s += "        }\n"
      s += "        return set;\n"
      s += "    }\n"
      s += "    public static int toBits(EnumSet<#{@name}> set) {\n"
      s += "        int bits = 0;\n"
      s += "        for (#{@name} v : set) {\n"
      s += "            bits |= v.value();\n"
      s += "        }\n"
      s += "        return bits;\n"
      s += "    }\n"
    end
    s += "}"
  end
end

def parse_url(url, name)
  cache_file = "/tmp/#{$script_name}.tmp/#{name}.html"
  FileUtils.mkdir_p(File.dirname(cache_file))
  if !File.size?(cache_file) then
    open(cache_file, 'wb') do |f|
      f << open(url).read
    end
  end
  Nokogiri::HTML(open(cache_file))
end

def get_superclass(doc)
  superclass = nil
  doc.css('div.spec_sheet_info_box tr').each do |n|
    td = n.css('td').first
    if td != nil && td.content == 'Inherits from'
      a = td.next.css('a').first
      superclass =  a != nil ? a.content : 'ObjCObject'
    end
  end
  superclass
end

def get_protocols(doc)
  protocols = []
  doc.css('div.spec_sheet_info_box tr').each do |n|
    td = n.css('td').first
    if td != nil && td.content == 'Conforms to'
      protocols = td.next.css('a').to_a.map {|x| x.content}.select {|p| !IGNORED_PROTOCOLS.include?(p) && !p.include?('(')}
    end
  end
  protocols
end

def get_methods(doc, clazz)
  methods = []
  elems = [doc.css('div.api.classMethod'), doc.css('div.api.instanceMethod')].flatten
  elems.each do |n|
    prot = n.css('div.declaration').first.content.gsub(/\302\240/, ' ').strip
    method = ObjCMethod.new(clazz, prot)
    if !method.conf['exclude']
      methods.push(method)
    end
  end
  methods
end

def get_properties(doc, clazz)
  properties = []
  doc.css('div.api.propertyObjC').each do |n|
    decl = n.css('div.declaration').first.content.gsub(/\302\240/, ' ').strip
    property = ObjCProperty.new(clazz, decl)
    if !property.conf['exclude']
      properties.push(property)
    end
  end
  properties
end

def get_enums(doc, conf)
  enums = []
  doc.css('.declaration').each do |n|
    decl = n.content.gsub(/\302\240/, ' ').strip
    if decl =~ /.*enum\s*\{/
      enum = ObjCEnum.new(decl, conf)
#      puts "  #{enum.name}"
      if conf.has_key?(enum.name)
        enums.push(enum)
      end
    end
  end
  enums
end

script_dir = File.expand_path(File.dirname(__FILE__))
target_dir = ARGV[0]
def_class_template = IO.read("#{script_dir}/class_template.java")
def_protocol_template = IO.read("#{script_dir}/protocol_template.java")

ARGV[1..-1].each do |yaml_file|
  conf = YAML.load_file(yaml_file)
  base_url = conf['base_url']
  package = conf['package'] || ''
  library = conf['library'] || ''

  imports = conf['imports'] || []
  imports << "java.util.*"
  imports << "org.robovm.objc.*"
  imports << "org.robovm.objc.bind.*"
  imports << "org.robovm.objc.block.*"
  imports << "org.robovm.rt.bro.annotation.*"
  imports << "org.robovm.rt.bro.ptr.*"
  imports.uniq!

  enums = []
  enums_conf = conf['enums'] || {}

  typedefs = conf['typedefs'] || {}

  conf['classes'].keys.sort.each do |class_name|
    puts "#{class_name}..."
    class_conf = conf['classes'][class_name]
    clazz = ObjCClass.new(class_name, class_conf, typedefs)
    urls = class_conf['urls'] || [class_conf['url']]
    urls.each_with_index do |url, i|
      doc = parse_url(base_url + url, "#{class_name}_#{i}")
      clazz.superclass = get_superclass(doc) || clazz.superclass
      (class_conf['protocols'] || get_protocols(doc)).each {|p| clazz.protocols.push(p)}
      get_methods(doc, clazz).each {|m| clazz.methods.push(m)}
      get_properties(doc, clazz).each {|p| clazz.properties.push(p)}
      get_enums(doc, enums_conf).each {|e| enums.push(e)}
    end

    target_file = File.join(target_dir, package.gsub('.', File::SEPARATOR), "#{clazz.name}.java")
    FileUtils.mkdir_p(File.dirname(target_file))

    template = File.size?(target_file) ? IO.read(target_file) : def_class_template;

    open(target_file, 'wb') do |f|
      if package.size > 0
        template = template.sub(/^package .*;/, "package #{package};")
      end
      if library.size > 0
        template = template.sub(/\/\*<library>\*\/.*\/\*<\/library>\*\//m, "/*<library>*/@Library(\"#{library}\")/*</library>*/")
      end
      imports_s = imports.map {|im| "import #{im};"}.join("\n")
      template = template.sub(/\/\*<imports>\*\/.*\/\*<\/imports>\*\//m, "/*<imports>*/\n#{imports_s}\n/*</imports>*/")
      f << clazz.to_java(template)
    end
  end

  (conf['protocols'] || {}).keys.sort.each do |class_name|
    puts "#{class_name}..."
    class_conf = conf['protocols'][class_name]
    clazz = ObjCClass.new(class_name, class_conf, typedefs, true)
    urls = class_conf['urls'] || [class_conf['url']]
    urls.each_with_index do |url, i|
      doc = parse_url(base_url + url, "#{class_name}_#{i}")
      (class_conf['protocols'] || get_protocols(doc)).each {|p| clazz.protocols.push(p)}
      get_methods(doc, clazz).each {|m| clazz.methods.push(m)}
      get_properties(doc, clazz).each {|p| clazz.properties.push(p)}
      get_enums(doc, enums_conf).each {|e| enums.push(e)}
    end

    target_file = File.join(target_dir, package.gsub('.', File::SEPARATOR), "#{clazz.name}.java")
    FileUtils.mkdir_p(File.dirname(target_file))

    template = File.size?(target_file) ? IO.read(target_file) : def_protocol_template

    open(target_file, 'wb') do |f|
      if package.size > 0
        template = template.sub(/^package .*;/, "package #{package};")
      end
      imports_s = imports.map {|im| "import #{im};"}.join("\n")
      template = template.sub(/\/\*<imports>\*\/.*\/\*<\/imports>\*\//m, "/*<imports>*/\n#{imports_s}\n/*</imports>*/")
      f << clazz.to_java(template)
    end
  end

  (conf['data_type_urls'] || []).each_with_index do |url, i|
      doc = parse_url(base_url + url, "data_types_#{yaml_file}_#{i}")
      get_enums(doc, enums_conf).each {|e| enums.push(e)}
  end

  enums.each do |enum|
    target_file = File.join(target_dir, package.gsub('.', File::SEPARATOR), "#{enum.name}.java")
    if !File.size?(target_file)
      FileUtils.mkdir_p(File.dirname(target_file))
      open(target_file, 'wb') do |f|
        if conf['license']
          f << conf['license']
        end
        if package.size > 0
          f << "package #{package};\n\n"
        end
        f << "import java.util.*;\n\n"
        f << enum.to_java
        f << "\n"
      end
    end
  end

end
