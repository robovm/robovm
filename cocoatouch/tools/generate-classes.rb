#!/usr/bin/ruby

require 'rubygems'
require 'nokogiri'
require 'open-uri'
require 'yaml'
require 'fileutils'
IGNORED_PROTOCOLS = ['NSObject', 'NSCoding', 'NSCopying', 'NSMutableCopying', 'NSFastEnumeration']

$script_name = File.basename($0, File.extname($0))

class Array
  def odd_values
    self.values_at(* self.each_index.select {|i| i.odd?})
  end
  def even_values
    self.values_at(* self.each_index.select {|i| i.even?})
  end
end

def objc_to_java(type, instancetype, typedefs, overrides = nil)
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
  when 'unichar *' then 'CharPtr'
  when 'void *' then 'VoidPtr'
  when 'char' then 'byte'
  when 'unsigned char' then 'byte'
  when 'long long' then 'long'
  when 'unsigned long long' then 'long'
  when 'unsigned short' then 'char'
  when 'unsigned int' then 'int'
  when 'unsigned' then 'int'
  when 'SEL' then 'Selector'
  when /NSString\s*\*/ then 'String'
  when /^id\s*<\s*(.*?)\s*>$/ then $1
  when /^(.*?)\s*\*\*$/ then "Ptr<#{$1}>"
  when /^(.*?)\s*\*$/ then $1
  when 'instancetype' then instancetype
  else type
  end

  java_type
end

def wildcard_match(pattern, s)
  re = '^' + ("_#{pattern}_".split('*').map {|part| Regexp.quote(part)}.join('.*').slice(1..-2)) + '$'
  s.match(re)
end

def escape_html(s)
  s.gsub(/</, '&lt;').gsub(/>/, '&gt;').gsub(/&/, '&amp;')
end

class ObjCType
  attr_accessor :decl
  attr_accessor :attributes
  attr_accessor :type
  def initialize(decl, clazz, overrides = nil)
    @decl = decl
    @attributes = []
    if decl =~ /\boneway\b/ 
      decl = decl.sub(/oneway/, '')
      @attributes.push('oneway')
    end
    if decl =~ /\binout\b/ 
      decl = decl.sub(/inout/, '')
      @attributes.push('inout')
    end
    if decl =~ /\bconst\b/ 
      decl = decl.sub(/const/, '')
      @attributes.push('const')
    end
    @type = decl.strip
    @clazz = clazz
    @java_type = objc_to_java(@type, @clazz.name, @clazz.typedefs, overrides)
  end
  def primitive?
    @java_type =~ /void|byte|boolean|char|short|int|long|float|double/
  end
  def by_val?
    @clazz.structs.has_key?(@java_type) && @type == @java_type
  end
  def to_java
    @java_type
  end
  def to_bro
    (by_val? ? '@ByVal ' : '') + (@java_type.match(/Ptr<.*>/) ? @java_type : @java_type.sub(/<.*>/, ''))
  end
  def to_bro_return_type
    (by_val? ? '@StructRet ' : '') + (@java_type.match(/Ptr<.*>/) ? @java_type : @java_type.sub(/<.*>/, ''))
  end
end

class ObjCClass
  attr_accessor :name
  attr_accessor :superclass
  attr_accessor :url
  attr_accessor :availability
  attr_accessor :conf
  attr_accessor :typedefs
  attr_accessor :structs
  attr_accessor :protocols
  attr_accessor :methods
  attr_accessor :properties
  attr_accessor :is_protocol
  def initialize(name, conf, typedefs, structs, is_protocol = false)
    @name = name
    @conf = conf
    @typedefs = typedefs
    @structs = structs
    @protocols = []
    @methods = []
    @properties = []
    @is_protocol = is_protocol
    @visibility = @conf['visibility'] || 'public'
  end
  def to_java(template)
    @properties = @properties.sort_by {|p| p.name}
    @properties.uniq!
    @methods = @methods.sort_by {|m| m.static ? "0_#{m.name}" : "1_#{m.name}"}
    @methods.uniq!

    javadoc = ["@see <a href=\"#{@url}\">#{@name} #{@is_protocol ? 'Protocol' : 'Class'} Reference</a>"]
    if availability
      javadoc.push("@since #{escape_html(@availability)}")
    end
    javadoc_s = javadoc.join("\n *   ")

    protocols = @is_protocol ? [@protocols, 'NSObjectProtocol'].flatten : @protocols
    template = template.sub(/<div class="javadoc">.*<\/div>/m, "<div class=\"javadoc\">\n *   #{javadoc_s}\n * </div>")
    template = template.gsub(/\/\*<name>\*\/.*\/\*<\/name>\*\//, "/*<name>*/ #{@name} /*</name>*/")
    template = template.sub(/\/\*<extends>\*\/.*\/\*<\/extends>\*\//, "/*<extends>*/ #{@superclass} /*</extends>*/")
    if !protocols.empty?
      if @is_protocol
        template = template.sub(/\/\*<implements>\*\/.*\/\*<\/implements>\*\//, "/*<implements>*/ extends #{protocols.join(', ')} /*</implements>*/")
      else
        template = template.sub(/\/\*<implements>\*\/.*\/\*<\/implements>\*\//, "/*<implements>*/ implements #{protocols.join(', ')} /*</implements>*/")
      end
    else
      template = template.sub(/\/\*<implements>\*\/.*\/\*<\/implements>\*\//, "/*<implements>*/ /*</implements>*/")
    end
    constructors = @methods.select {|m| m.constructor?}
    constructors_s = constructors.map {|m| m.to_java}.join("\n").gsub(/\n/m, "\n    ") + "\n"
    if !@conf['skip_def_constructor']
      constructors_s = "public #{@name}() {}\n    " + constructors_s
    end
    if !@conf['skip_skip_init_constructor']
      constructors_s = "protected #{@name}(SkipInit skipInit) { super(skipInit); }\n    " + constructors_s
    end
    template = template.sub(/\/\*<constructors>\*\/.*\/\*<\/constructors>\*\//m, "/*<constructors>*/\n    #{constructors_s}    /*</constructors>*/")
    properties_s = @properties.map {|p| p.to_java}.flatten.join("\n").gsub(/\n/m, "\n    ")
    template = template.sub(/\/\*<properties>\*\/.*\/\*<\/properties>\*\//m, "/*<properties>*/\n    #{properties_s}\n    /*</properties>*/")
    methods_s = @methods.select {|m| !m.constructor?}.map {|m| m.to_java}.join("\n").gsub(/\n/m, "\n    ")
    template = template.sub(/\/\*<methods>\*\/.*\/\*<\/methods>\*\//m, "/*<methods>*/\n    #{methods_s}\n    /*</methods>*/")
    if !@visibility.include?('final')
      callbacks = [@properties.map {|p| p.callbacks}, @methods.map {|m| m.callbacks}].flatten
      if !callbacks.empty?
        callbacks_s = callbacks.join("\n").gsub(/\n/m, "\n        ")
        template = template.sub(/\/\*<callbacks>\*\/.*\/\*<\/callbacks>\*\//m, "/*<callbacks>*/\n    static class Callbacks {\n        #{callbacks_s}\n    }\n    /*</callbacks>*/")
      else
        template = template.sub(/\/\*<callbacks>\*\/.*\/\*<\/callbacks>\*\//m, "/*<callbacks>*/\n    /*</callbacks>*/")
      end
    end
    if @is_protocol
        adapters = [@properties.map {|p| p.adapters}, @methods.map {|m| m.adapters}].flatten
        if !adapters.empty?
          super_adapter = @protocols.empty? ? 'NSObject' : "#{@protocols.first}.Adapter"
          adapters_s = adapters.join("\n").gsub(/\n/m, "\n        ")
          template = template.sub(/\/\*<adapter>\*\/.*\/\*<\/adapter>\*\//m, "/*<adapter>*/\n    public static class Adapter extends #{super_adapter} implements #{@name} {\n        #{adapters_s}\n    }\n    /*</adapter>*/")
        end
    end
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
  attr_accessor :url
  attr_accessor :availability  
  def initialize(clazz, objcprot)
    @clazz = clazz
    objcprot = objcprot.sub(/;$/, '')
    @objcprot = objcprot
    @static = objcprot.start_with?('+')
    objcprot = objcprot.sub(/^[-+]\s*/, '')
    if objcprot.index(':') == nil
      parts = objcprot.match(/^(.*?)([^\)]+)$/).values_at(1, 2)
    else
      parts = objcprot.split(/(\w+:)/)
    end
    parts = parts.map {|p| p.strip}

    @selector = parts[1..-1].even_values.join('')
    methods_conf = @clazz.conf['methods'] || {}
    conf_key = (@static ? '+' : '-') + @selector
    @conf = methods_conf[conf_key]
    if !methods_conf.has_key?(conf_key)
      # Treat * in keys as wildcards and use the first match
      @conf = (methods_conf.to_a.select {|p| wildcard_match(p[0], conf_key)}.first || [nil, nil])[1]
    end
    @conf = @conf || {}

    @name = @conf['name'] || parts[1].chomp(':')
    @visibility = @conf['visibility'] || 'public'

    @return_type = ObjCType.new(parts.first.sub(/^\((.*)\)$/, '\\1'), @clazz, @conf['return_type'])
    @parameters = parts[1..-1].odd_values.map {|part| part.match(/^\((.*?)\)([^\)]+?)[.;]*$/).values_at(1, 2)}.map do |pair|
      overrides = @conf['parameters'] ? (@conf['parameters'][pair[1]] ? @conf['parameters'][pair[1]]['type'] : nil) : nil
      [ObjCType.new(pair[0], @clazz, overrides), pair[1]]
    end
  end
  def static?
    @static
  end
  def constructor?
    !@clazz.is_protocol && !@static && @name.start_with?('init') && (!@conf.has_key?('constructor') || @conf['constructor']) && 
        (@return_type.decl == 'id' || @return_type.decl == 'instancetype' || @return_type.decl == "#{@clazz.name} *")
  end
  def get_javadoc
    "/**\n" +
    (@url ? " * @see <a href=\"#{@url}\">#{escape_html(@objcprot)}</a>\n" : '') +
    (@availability ? " * @since #{escape_html(@availability)}\n" : '') +
    " */"
  end
  def java_signature
    parameters_s = @parameters.map {|p| "#{p[0].to_java} #{p[1]}"}.join(', ')
    if @clazz.is_protocol
      (@static && 'static ' || '') + @return_type.to_java + ' ' + @name + '(' + parameters_s + ')'
    else
      if constructor? then
        "#{@visibility} #{@clazz.name}" + '(' + parameters_s + ')'
      else
        "#{@visibility} " + (@static && 'static ' || '') + @return_type.to_java + ' ' + @name + '(' + parameters_s + ')'
      end
    end
  end
  def to_java
    selector_var = @selector.gsub(/:/, '$')
    sig = java_signature
    java = ''
    if @clazz.is_protocol
      java = "#{get_javadoc}\n#{sig};"
    else
      msg_send_parameters = [@static ? 'ObjCClass __self__' : "#{@clazz.name} __self__", 'Selector __cmd__', @parameters.map {|p| "#{p[0].to_bro} #{p[1]}"}].flatten
      msg_send_super_parameters = ['ObjCSuper __super__', 'Selector __cmd__', @parameters.map {|p| "#{p[0].to_bro} #{p[1]}"}].flatten
      msg_send_parameters_s = msg_send_parameters.join(', ')
      msg_send_super_parameters_s = msg_send_super_parameters.join(', ')

      msg_send_ret_s = constructor? ? '@Pointer long' : @return_type.to_bro
      msg_send_super_ret_s = @return_type.to_bro

      msg_send = "objc_#{@name}"
      msg_send_proto = "@Bridge private native static #{msg_send_ret_s} #{msg_send}(#{msg_send_parameters_s});"
      msg_send_super = "objc_#{@name}Super"
      msg_send_super_proto = "@Bridge private native static #{@return_type.to_bro} #{msg_send_super}(#{msg_send_super_parameters_s});"

      args = [@static ? 'objCClass' : 'this', selector_var, @parameters.map {|p| p[1]}].flatten
      args_super = ['getSuper()', selector_var, @parameters.map {|p| p[1]}].flatten
      args_s = args.join(', ')
      args_super_s = args_super.join(', ')

      java = "#{java}\nprivate static final Selector #{selector_var} = Selector.register(\"#{@selector}\");"
      body = ""

      if constructor?
        java = "#{java}\n#{msg_send_proto}"
        body = "super((SkipInit) null);\ninitObject(#{msg_send}(#{args_s}));"
      else

        ret = @return_type.to_java != 'void' ? 'return ' : ''
        can_override = !@static && !(@visibility.include?('private') || @visibility.include?('final'))
        if can_override
          body_msg_send = "if (customClass) { #{ret}#{msg_send_super}(#{args_super_s}); } else { #{ret}#{msg_send}(#{args_s}); }"
        else
          body_msg_send = "#{ret}#{msg_send}(#{args_s});"
        end

        java = "#{java}\n#{msg_send_proto}"
        if can_override
          java = "#{java}\n#{msg_send_super_proto}"
        end
        body = body_msg_send

      end

      body = body.gsub(/\n/m, "\n    ")

      java = "#{java}\n#{get_javadoc}\n#{sig} {\n    #{body}\n}"
    end
    java
  end
  def callbacks
    if !@static && !constructor? && !(@visibility.include?('private') || @visibility.include?('final'))
      # This method can be overridden
      parameters_s = ["#{@clazz.name} __self__", "Selector __cmd__", @parameters.map {|p| "#{p[0].to_bro} #{p[1]}"}].flatten.join(', ')
      args = @parameters.map {|p| p[1]}.join(', ')
      ret = @return_type.to_java != 'void' ? 'return ' : ''
      ["@Callback @BindSelector(\"#{@selector}\") public static #{@return_type.to_bro} #{@name}(#{parameters_s}) { #{ret}__self__.#{name}(#{args}); }"]
    else
      []
    end
  end
  def adapters
    if !@static && !constructor? && !(@visibility.include?('private') || @visibility.include?('final'))
      parameters_s = @parameters.map {|p| "#{p[0].to_java} #{p[1]}"}.join(', ')
      ["@NotImplemented(\"#{@selector}\") public #{java_signature} { throw new UnsupportedOperationException(); }"]
    else
      []
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
  attr_accessor :url
  attr_accessor :availability  
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
    @type = ObjCType.new(@type, @clazz, @conf['type'])
  end
  def read_only?
    @attrs['readonly']
  end
  def getter_to_java
    getter = @type.decl == 'BOOL' ? "is#{@base_java_name}" : "get#{@base_java_name}"
    selector_var = @getter_selector.gsub(/:/, '$')
    java = ''
    if @clazz.is_protocol
      java = "#{get_javadoc}\n#{@type.to_java} #{getter}();"
    else
      msg_send_parameters_s = "#{@clazz.name} __self__, Selector __cmd__"
      msg_send_super_parameters_s = "ObjCSuper __super__, Selector __cmd__"

      msg_send = "objc_#{getter}"
      msg_send_proto = "@Bridge private native static #{@type.to_bro} #{msg_send}(#{msg_send_parameters_s});"
      msg_send_super = "objc_#{getter}Super"
      msg_send_super_proto = "@Bridge private native static #{@type.to_bro} #{msg_send_super}(#{msg_send_super_parameters_s});"

      args_s = "this, #{selector_var}"
      args_super_s = "getSuper(), #{selector_var}"

      can_override = !(@visibility.include?('private') || @visibility.include?('final'))
      if can_override
        body_msg_send = "if (customClass) { return #{msg_send_super}(#{args_super_s}); } else { return #{msg_send}(#{args_s}); }"
      else
        body_msg_send = "return #{msg_send}(#{args_s});"
      end

      java = "#{java}\nprivate static final Selector #{selector_var} = Selector.register(\"#{@getter_selector}\");"
      body = ""
      java = "#{java}\n#{msg_send_proto}"
      if can_override
        java = "#{java}\n#{msg_send_super_proto}"
      end
      body = body_msg_send

#      if !(@visibility.include?('private') || @visibility.include?('final'))
#        # Call objc_msgSendSuper if this is a custom class
#        java = "#{java}\n@Bridge(symbol = \"objc_msgSendSuper\") private native static #{@type.to_bro} #{msg_send_super}(#{msg_send_super_parameters_s});"
#        body = "if (customClass) { return #{msg_send}Super(#{args_super}); } else { return #{msg_send}(#{args}); }"
#      else
#        body = "return #{msg_send}(#{args});"
#      end
      body = body.gsub(/\n/m, "\n    ")
      sig = "#{@visibility} #{@type.to_java} #{getter}()"
      java = "#{java}\n#{get_javadoc}\n#{sig} {\n    #{body}\n}"
    end
    java
  end
  def setter_to_java
    setter = @attrs.has_key?('setter') ? @attrs['setter'] : "set#{@base_java_name}"
    selector_var = @setter_selector.gsub(/:/, '$')
    java = ''
    if @clazz.is_protocol
      java = "#{get_javadoc}\nvoid #{setter}(#{@type.to_java} v);"
    else
      java = "#{java}\nprivate static final Selector #{selector_var} = Selector.register(\"#{@setter_selector}\");"
      sig = "#{@visibility} void #{setter}(#{@type.to_java} #{@name})"
      msg_send = "objc_#{setter}"
      msg_send_parameters_s = "#{@clazz.name} __self__, Selector __cmd__, #{@type.to_bro} #{@name}"
      msg_send_super_parameters_s = "ObjCSuper __super__, Selector __cmd__, #{@type.to_bro} #{@name}"
      java = "#{java}\n@Bridge private native static void #{msg_send}(#{msg_send_parameters_s});"
      args = "this, #{selector_var}, #{@name}"
      args_super = "getSuper(), #{selector_var}, #{@name}"
      body = ""
      if !(@visibility.include?('private') || @visibility.include?('final'))
        # Call objc_msgSendSuper if this is a custom class
        java = "#{java}\n@Bridge private native static void #{msg_send}Super(#{msg_send_super_parameters_s});"
        body = "if (customClass) { #{msg_send}Super(#{args_super}); } else { #{msg_send}(#{args}); }"
      else
        body = "#{msg_send}(#{args});"
      end
      body = body.gsub(/\n/m, "\n    ")
      java = "#{java}\n#{get_javadoc}\n#{sig} {\n    #{body}\n}"
    end
    java
  end
  def get_javadoc
    "/**\n" +
    (@url ? " * @see <a href=\"#{@url}\">#{escape_html(@decl)}</a>\n" : '') +
    (availability ? " * @since #{escape_html(availability)}\n" : '') +
    " */"
  end
  def to_java
    read_only? ? [getter_to_java] : [getter_to_java, setter_to_java]
  end
  def callbacks
    if !(@visibility.include?('private') || @visibility.include?('final'))
      # This property can be overridden
      getter = @type.decl == 'BOOL' ? "is#{@base_java_name}" : "get#{@base_java_name}"
      get = "@Callback @BindSelector(\"#{@getter_selector}\") public static #{@type.to_bro} #{getter}(#{@clazz.name} __self__, Selector __cmd__) { return __self__.#{getter}(); }"
      if read_only?
        [get]
      else
        setter = @attrs.has_key?('setter') ? @attrs['setter'] : "set#{@base_java_name}"
        set = "@Callback @BindSelector(\"#{@setter_selector}\") public static void #{setter}(#{@clazz.name} __self__, Selector __cmd__, #{@type.to_bro} #{@name}) { __self__.#{setter}(#{@name}); }"
        [get, set]
      end
    else
      []
    end
  end
  def adapters
    if !(@visibility.include?('private') || @visibility.include?('final'))
      getter = @type.decl == 'BOOL' ? "is#{@base_java_name}" : "get#{@base_java_name}"
      get = "@NotImplemented(\"#{@getter_selector}\") public #{@type.to_java} #{getter}() { throw new UnsupportedOperationException(); }"
      if read_only?
        [get]
      else
        setter = @attrs.has_key?('setter') ? @attrs['setter'] : "set#{@base_java_name}"
        set = "@NotImplemented(\"#{@setter_selector}\") public void #{setter}(#{@type.to_java} #{@name}) { throw new UnsupportedOperationException(); }"
        [get, set]
      end
    else
      []
    end
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

def get_availability(doc)
  availability = nil
  doc.css('div.spec_sheet_info_box tr').each do |n|
    td = n.css('td').first
    if td != nil && td.content == 'Availability'
      div = td.next.css('div').first
      availability = div != nil ? div.content : nil
    end
  end
  availability
end

def get_methods(doc, clazz, url)
  methods = []
  elems = [doc.css('div.api.classMethod'), doc.css('div.api.instanceMethod')].flatten
  elems.each do |n|
    prot = n.css('div.declaration').first.content.gsub(/\302\240/, ' ').strip
    availability_div = n.css('div.availability').css('li').first
    availability = availability_div ? availability_div.content.strip : nil
    anchor_a = n.xpath('./a').find {|a| a['name'] && a['name'].start_with?('//apple_ref/occ/')}
    anchor = anchor_a ? anchor_a['name'] : nil
    method = ObjCMethod.new(clazz, prot)
    method.availability = availability
    method.url = anchor ? "#{url}##{anchor}" : nil
    if !method.conf['exclude']
      methods.push(method)
    end
  end
  methods
end

def get_properties(doc, clazz, url)
  properties = []
  doc.css('div.api.propertyObjC').each do |n|
    decl = n.css('div.declaration').first.content.gsub(/\302\240/, ' ').strip
    availability_div = n.css('div.availability').css('li').first
    availability = availability_div ? availability_div.content.strip : nil
    anchor_a = n.xpath('./a').find {|a| a['name'] && a['name'].start_with?('//apple_ref/occ/')}
    anchor = anchor_a ? anchor_a['name'] : nil
    property = ObjCProperty.new(clazz, decl)
    property.availability = availability
    property.url = anchor ? "#{url}##{anchor}" : nil
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
global = YAML.load_file("#{script_dir}/global.yaml")

ARGV[1..-1].each do |yaml_file|
  conf = YAML.load_file(yaml_file)
  base_url = conf['base_url']
  package = conf['package'] || ''
  library = conf['library'] || ''

  imports = conf['imports'] || []
  imports << "java.util.*"
  imports << "org.robovm.objc.*"
  imports << "org.robovm.objc.annotation.*"
  imports << "org.robovm.objc.block.*"
  imports << "org.robovm.rt.bro.*"
  imports << "org.robovm.rt.bro.annotation.*"
  imports << "org.robovm.rt.bro.ptr.*"
  imports.uniq!

  enums = []
  enums_conf = conf['enums'] || {}

  typedefs = (global['typedefs'] || {}).merge(conf['typedefs'] || {})
  structs = (global['structs'] || {}).merge(conf['structs'] || {})

  conf['classes'].keys.sort.each do |class_name|
    puts "#{class_name}..."
    class_conf = conf['classes'][class_name]
    clazz = ObjCClass.new(class_name, class_conf, typedefs, structs)
    urls = class_conf['urls'] || [class_conf['url']]
    clazz.url = base_url + urls.first
    urls.each_with_index do |url, i|
      doc = parse_url(base_url + url, "#{class_name}_#{i}")
      clazz.superclass = get_superclass(doc) || clazz.superclass
      clazz.availability = clazz.availability || get_availability(doc)
      (class_conf['protocols'] || get_protocols(doc)).each {|p| clazz.protocols.push(p)}
      get_methods(doc, clazz, base_url + url).each {|m| clazz.methods.push(m)}
      get_properties(doc, clazz, base_url + url).each {|p| clazz.properties.push(p)}
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
    clazz = ObjCClass.new(class_name, class_conf, typedefs, structs, true)
    urls = class_conf['urls'] || [class_conf['url']]
    clazz.url = base_url + urls.first
    urls.each_with_index do |url, i|
      doc = parse_url(base_url + url, "#{class_name}_#{i}")
      clazz.availability = clazz.availability || get_availability(doc)
      (class_conf['protocols'] || get_protocols(doc)).each {|p| clazz.protocols.push(p)}
      get_methods(doc, clazz, base_url + url).each {|m| clazz.methods.push(m)}
      get_properties(doc, clazz, base_url + url).each {|p| clazz.properties.push(p)}
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
        license = conf['license'] || global['license']
        if license
          f << license
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
