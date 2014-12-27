#!/usr/bin/env ruby

require 'rubygems'
require 'nokogiri'
require 'open-uri'
require 'fileutils'
require 'tmpdir'

@dev_root = File.realpath("#{File.dirname(__FILE__)}/../..")
@dir = Dir.mktmpdir
puts "Using tmp dir #{@dir}"

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
  cmd = "#{@dev_root}/bin/robovm -cp #{classpath} -cache #{@dir}/cache -d #{@dir}/#{group}-#{artifact} -o out -verbose -forcelinkclasses '**.*'"
  puts "Running command: #{cmd}"
  system({"ROBOVM_DEV_ROOT" => @dev_root}, cmd)
end

n = 1
(1..10).each do |page|
  url = "http://mvnrepository.com/popular?p=#{page}"
  doc = Nokogiri::HTML(open(url))
  doc.css('#maincontent div.im a.im-link').map {|a| a['href']}.select {|e| e.scan(/\//).count == 3}.each do |e|
    e =~ /\/artifact\/(.*)\/(.*)/
    group = $1
    artifact = $2
    url = "http://mvnrepository.com#{e}"
    doc = Nokogiri::HTML(open(url))
    versions = doc.css('#maincontent table.versions tr').map do |tr|
      tds = tr.css('td').map {|e| e.content.strip}.to_a
      {:group => group, :artifact => artifact, :version => tds[-4], :usages => (tds[-3] || '0').gsub(/ |,/, '').to_i, :type => tds[-2]}
    end
    versions = versions.sort_by {|e| e[:usages]}.reverse
    compile_artifact(versions.first[:group], versions.first[:artifact], versions.first[:version], n)
    n = n + 1
  end
end
