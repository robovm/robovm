#!/usr/bin/env ruby

# Copyright (C) 2014 Trillian Mobile AB
#
# This program is free software; you can redistribute it and/or
# modify it under the terms of the GNU General Public License
# as published by the Free Software Foundation; either version 2
# of the License, or (at your option) any later version.
#
# This program is distributed in the hope that it will be useful,
# but WITHOUT ANY WARRANTY; without even the implied warranty of
# MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
# GNU General Public License for more details.
#
# You should have received a copy of the GNU General Public License
# along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
#

require 'fileutils'

base_dir = File.dirname(__FILE__)
max_members = 10
c_members = [
  ['char'], ['short'], ['int'], ['uint64_t'], ['float'], ['double'], ['double', 'char']
]
java_members = [
  ['byte'], ['short'], ['int'], ['long'], ['float'], ['double'], ['double', 'byte']
]
member_values = [
  [['(byte) 34'], ['(byte) 59'], ['(byte) 101'], ['(byte) 158']],
  [['(short) 0x0009'], ['(short) 0x0042'], ['(short) 0x08ab'], ['(short) 0xa4f9']],
  [['0x0000002d'], ['0x0000ab34'], ['0x0019bec6'], ['0xe5ab2678']],
  [['0x000000000000322dL'], ['0x0000000028abef73L'], ['0x0000a128abf4c901L'], ['0x61b8afc3450a1b56L']],
  [['0.1234f'], ['123.4567f'], ['123456.789f'], ['12345678.90123f']],
  [['0.1234'], ['123.4567'], ['123456.789'], ['12345678.90123']],
  [['0.1234', '(byte) 83'], ['123.4567', '(byte) 103'], ['123456.789', '(byte) 189'], ['12345678.90123', '(byte) 247']]
]

FileUtils.mkdir_p("#{base_dir}/src/test/native")

File.open("#{base_dir}/src/test/native/pass_return_byval_test.c",'w') do |s|
  s.puts '#include <stdint.h>'
  s.puts
  (1..max_members).each do |size|
    c_members.each_with_index do |e, index|
      struct_name = "S#{size}_#{index}"
      s.puts "typedef struct {" + (0...size).map { |i| e.each_with_index.map { |type, j| "#{type} m#{i}_#{j}" }}.join('; ') + ";} #{struct_name};"
      s.puts "int native_sizeof_#{struct_name}(void) { return sizeof(#{struct_name}); }"
      s.puts "#{struct_name} native_new_#{struct_name}(" + 
        (0...size).map { |i| e.each_with_index.map { |type, j| "#{type} m#{i}_#{j}" }}.join(', ') + 
        ") { #{struct_name} s; " + (0...size).map { |i| e.each_with_index.map { |type, j| "s.m#{i}_#{j} = m#{i}_#{j};" }}.join(' ') + " return s; }"
      (0...size).map do |i| 
        e.each_with_index.map do |type, j|
          s.puts "#{type} native_get_#{struct_name}_m#{i}_#{j}(#{struct_name} s) { return s.m#{i}_#{j}; }"
        end
      end
    end
  end
end

uname = `uname`.strip
cc = ''
os_arch = {}
case uname
when 'Darwin' then
  xcode_dir = `xcode-select -p`.chomp
  device_sysroot = "#{xcode_dir}/Platforms/iPhoneOS.platform/Developer/SDKs/iPhoneOS.sdk"
  sim_sysroot = "#{xcode_dir}/Platforms/iPhoneSimulator.platform/Developer/SDKs/iPhoneSimulator.sdk"
  cc = 'clang'
  os_arch = {
    :ios => [
      ['x86', ['-target', 'i386-unknown-macosx10.7.0', '-isysroot', sim_sysroot]], 
      ['x86_64', ['-target', 'x86_64-unknown-macosx10.7.0', '-isysroot', sim_sysroot]], 
      ['thumbv7', ['-target', 'armv7-unknown-ios5.0.0', '-isysroot', device_sysroot, '-miphoneos-version-min=6.0']], 
      ['arm64', ['-target', 'arm64-unknown-ios5.0.0', '-isysroot', device_sysroot, '-miphoneos-version-min=6.0']]
    ], 
    :macosx => [
      ['x86', ['-arch', 'i386']],
      ['x86_64', ['-arch', 'x86_64']]
    ]
  }
else
  cc = 'gcc'
  os_arch = {
    :linux => [
      ['x86', ['-m32']], ['x86_64', ['-m64']]
    ]
  }
end

os_arch.each_key do |os|
  os_arch[os].each do |arch|
    robovm_arch = arch[0]
    c_flags = arch[1]
    dir = "#{base_dir}/src/test/resources/META-INF/robovm/#{os}/#{robovm_arch}"
    FileUtils.mkdir_p("#{dir}")

    system("#{cc} #{c_flags.join(' ')} -c #{base_dir}/src/test/native/pass_return_byval_test.c -o #{dir}/pass_return_byval_test.o")

    File.open("#{dir}/robovm.xml",'w') do |s|
      s.puts <<-eos
<config>
  <libs>
    <lib>pass_return_byval_test.o</lib>
  </libs>
  <exportedSymbols>
    <symbol>native_*</symbol>
  </exportedSymbols>
</config>
eos
    end
  end
end

File.open("#{base_dir}/src/test/java/org/robovm/rt/bro/PassReturnByValTest.java",'w') do |s|
  s.puts <<-eos
/*
 * Copyright (C) 2015 Trillian Mobile AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.robovm.rt.bro;

import static org.junit.Assert.*;

import org.junit.Test;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;

/**
 * Autogenerated test that tests passing and returning structs {@link ByVal}.
 */
@SuppressWarnings("unused")
@Library(Library.INTERNAL)
public class PassReturnByValTest {
    static { Bro.bind(PassReturnByValTest.class); }

    private static void assertEquals(float expected, float actual) {
        org.junit.Assert.assertEquals(expected, actual, 0.0f);
    }
    private static void assertEquals(double expected, double actual) {
        org.junit.Assert.assertEquals(expected, actual, 0.0);
    }
eos

  (1..max_members).each do |size|
    java_members.each_with_index do |e, index|
      struct_name = "S#{size}_#{index}"
      s.puts "    static class #{struct_name} extends Struct<#{struct_name}> {\n        " + ( (0...size).map do |i|
        e.each_with_index.map { |type, j| "@StructMember(#{i * e.size + j}) public native #{type} m#{i}_#{j}(); @StructMember(#{i * e.size + j}) public native #{struct_name} m#{i}_#{j}(#{type} v);" }
      end.join("\n        ") + "\n    }" )
    end
  end

  s.puts

  (1..max_members).each do |size|
    java_members.each_with_index do |e, index|
      struct_name = "S#{size}_#{index}"
      s.puts "    @Bridge public static native @MachineSizedUInt long native_sizeof_#{struct_name}();"
      s.puts "    @Bridge public static native @ByVal #{struct_name} native_new_#{struct_name}(" + (0...size).map { |i| e.each_with_index.map { |type, j| "#{type} m#{i}_#{j}" }}.join(', ') + ");"
      (0...size).map do |i|
        e.each_with_index.map do |type, j|
          s.puts "    @Bridge public static native #{type} native_get_#{struct_name}_m#{i}_#{j}(@ByVal #{struct_name} s);"
        end
      end
    end
  end

  s.puts

  (1..max_members).each do |size|
    java_members.each_with_index do |e, index|
      struct_name = "S#{size}_#{index}"
      s.puts "    @Test public void testSizeOf_#{struct_name}() {"
      s.puts "        assertEquals(native_sizeof_#{struct_name}(), #{struct_name}.sizeOf());"
      s.puts "    }"
    end
  end

  (1..max_members).each do |size|
    java_members.each_with_index do |e, index|
      struct_name = "S#{size}_#{index}"
      values = member_values[index]
      s.puts "    @Test public void testPass#{struct_name}ByVal() {"
      s.puts "        #{struct_name} s = new #{struct_name}();"
      (0...size).map do |i|
        e.each_with_index.map do |type, j|
          val = values[i % values.size][j]
          s.puts "        s.m#{i}_#{j}(#{val});"
        end
      end
      (0...size).map do |i|
        e.each_with_index.map do |type, j|
          val = values[i % values.size][j]
          s.puts "        assertEquals(#{val}, native_get_#{struct_name}_m#{i}_#{j}(s));"
        end
      end
      s.puts "    }"
    end
  end

  (1..max_members).each do |size|
    java_members.each_with_index do |e, index|
      struct_name = "S#{size}_#{index}"
      values = member_values[index]
      s.puts "    @Test public void testReturn#{struct_name}ByVal() {"
      s.puts "        #{struct_name} s = native_new_#{struct_name}(" + (0...size).map {|i| e.each_with_index.map {|type, j| values[i % values.size][j]}}.join(', ') + ");"
      (0...size).map do |i|
        e.each_with_index.map do |type, j|
          val = values[i % values.size][j]
          s.puts "        assertEquals(#{val}, s.m#{i}_#{j}());"
        end
      end
      s.puts "    }"
    end
  end

  s.puts "}"
end
