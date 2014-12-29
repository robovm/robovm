#!/usr/bin/env ruby

require 'rubygems'
require 'nokogiri'
require 'open-uri'
require 'fileutils'
require 'tmpdir'

@dev_root = File.realpath("#{File.dirname(__FILE__)}/../..")
@dir = Dir.mktmpdir(['robovm-compiler-stress', ''])
puts "Using tmp dir #{@dir}"

@includes = [
  'io.netty:netty-all'
]
@excludes = [
  'org.scala-lang:scala-compiler' # Makes gcc segfault on linking. 20069 classes. Too large?
]

def compile_artifact(group, artifact, version, n)
  puts "**************"
  puts "Compiling #{group}:#{artifact}:#{version} (\##{n})"
  pom = "#{@dir}/#{group}-#{artifact}.pom"
  File.write(pom,
<<eof
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
  xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>
  <groupId>org.robovm</groupId>
  <artifactId>foo</artifactId>
  <version>1.0</version>
  <packaging>pom</packaging>
  <dependencies>
    <dependency>
      <groupId>#{group}</groupId>
      <artifactId>#{artifact}</artifactId>
      <version>#{version}</version>
    </dependency>
  </dependencies>
</project>
eof
)

  system("mvn -q -f #{pom} -DoutputAbsoluteArtifactFilename=true -DoutputFile=#{pom}.deps -Dsilent=true dependency:list")
  deps = File.readlines("#{pom}.deps").find_all {|e| e =~ /\.m2\/repo/}.map {|e| e.gsub(/.*?:\//, '/').strip}
  classpath = deps.join(':')
  cmd = "#{@dev_root}/bin/robovm -cp #{classpath} -tmp #{@dir}/#{group}-#{artifact}.tmp -cache #{@dir}/cache -d #{@dir}/#{group}-#{artifact} -o out -verbose -forcelinkclasses '**.*' " + (ARGV.join(' '))
  puts "Running command: #{cmd}"
  system({"ROBOVM_DEV_ROOT" => @dev_root, "JVM_MX" => "4G"}, cmd) or raise "Compilation of #{group}:#{artifact} failed"
  system("rm -rf #{@dir}/#{group}-#{artifact}.tmp #{@dir}/#{group}-#{artifact}")
end

def determine_version(group, artifact)
  url = "http://mvnrepository.com/artifact/#{group}/#{artifact}"
  doc = Nokogiri::HTML(open(url))
  versions = doc.css('#maincontent table.versions tr').map do |tr|
    tds = tr.css('td').map {|e| e.content.strip}.to_a
    {:group => group, :artifact => artifact, :version => tds[-4], :usages => (tds[-3] || '0').gsub(/ |,/, '').to_i, :type => tds[-2]}
  end
  versions = versions.sort_by {|e| e[:usages]}.reverse
  versions.first[:version]
end

n = 1
@includes.each do |e|
  parts = e.split(/:/)
  group = parts[0]
  artifact = parts[1]
  version = parts.size > 2 ? parts[2] : determine_version(group, artifact)
  compile_artifact(group, artifact, version, n)
  n = n + 1
end
(1..10).each do |page|
  url = "http://mvnrepository.com/popular?p=#{page}"
  doc = Nokogiri::HTML(open(url))
  doc.css('#maincontent div.im a.im-link').map {|a| a['href']}.select {|e| e.scan(/\//).count == 3}.each do |e|
    e =~ /\/artifact\/(.*)\/(.*)/
    group = $1
    artifact = $2
    if !@excludes.include? "#{group}:#{artifact}"
      compile_artifact(group, artifact, determine_version(group, artifact), n)
    else
      puts "Skipping excluded artifact #{group}:#{artifact} (\##{n})"
    end
    n = n + 1
  end
end
