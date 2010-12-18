/*
 *  ClassFile.java
 *
 *  Copyright (C) 1999 by Kresten Krab Thorup <krab@daimi.au.dk>
 *
 *  This file is part of "Kresten's Verifier for Java Byte Codes"
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Library General Public
 *  License as published by the Free Software Foundation; either
 *  version 2 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Library General Public License for more details.
 *
 *  You should have received a copy of the GNU Library General Public
 *  License along with this library; if not, write to the Free
 *  Software Foundation, Inc., 59 Temple Place - Suite 330, Boston,
 *  MA 02111-1307, USA
 *
 */


package verify.classfile;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ClassFile
    extends verify.classfile.ClassFileParser
{
  public ClassFile (byte[] class_file_data) {
    super (class_file_data, 0, class_file_data.length);
    parse ();
  }

  public ClassFile (byte[] class_file_data, int off, int len) {
    super (class_file_data, off, len);
    parse ();
  }

  public int magic;
  public int minor_version;
  public int major_version;

  public int access_flags;
  public int this_class;
  public int super_class;
    
  public int interfaces_count;
  public int interfaces[];
    
  int fields_count;
  Field[] fields;

  public int methods_count;
  public Method[] methods;

  int source_file = -1;

  public String getName ()
  {
    return pool.get_class (this_class);
  }
    

  protected void handlePreample (int magic, int minor, int major) { 
    this.magic = magic;
    this.minor_version = minor;
    this.major_version  = major;
  }

  public /* read-only */ ConstantPool pool;
  protected void handleConstantPool (byte[] data,
				     byte[] tags,
				     int[] offsets,
				     int count) 
  {
    pool = new ConstantPool (data, tags, offsets, count);
  } 


  protected void handleClassBegin (int acc, int th, int sup) {
    access_flags = acc;
    this_class   = th;
    super_class  = sup;
  }
    
  protected void handleInterfacesBegin (int count) {
    interfaces_count = count;
    interfaces = new int[count];
  }
    
  protected void handleInterface (int if_index, int offset) {
    interfaces[if_index] = offset;
  }
    
  protected void handleFieldsBegin (int count) {
    fields_count = count;
    fields = new Field[count];
  }

  protected void handleField (int this_field,
			   int access_flags, 
			   int name_index, 
			   int descriptor_index) 
  {
    fields[this_field] = new Field (access_flags,
		    name_index,
		    descriptor_index);
    String name_str = fields[this_field].name();
    String desc_str = fields[this_field].signature();
    for (int i = 0; i < this_field; i++)
      {
	if (fields[i].name().equals(name_str) && fields[i].signature().equals(desc_str))
	  throw new ClassFormatError("duplicate field '" + name_str + "' with signature '" + desc_str + "'");
      }
  }
    
  protected void handleConstantValueAttribute (int this_field, int index)
  {
    fields[this_field].setConstantValue(index);
  }
    
  protected void handleMethodsBegin (int count) {
    methods_count = count;
    methods = new Method[count];
  }
    
  protected void handleMethod (int this_method,
			    int acc, int name, int desc) {
    methods[this_method] = new Method(acc,name,desc);
    String name_str = methods[this_method].name();
    String desc_str = methods[this_method].signature();
    for (int i = 0; i < this_method; i++)
      {
	if (methods[i].name().equals(name_str) && methods[i].signature().equals(desc_str))
	  throw new ClassFormatError("duplicate method '" + name_str + "' with signature '" + desc_str + "'");
      }
  }
    
  protected void handleCodeAttributeBegin (int this_method,
					int max_stack,
					int max_locals,
					byte[] data,
					int code_off,
					int code_len,
					int exc_table_entries)  
  {
    methods[this_method].handleCodeAttribute (max_stack,
					      max_locals,
					      data, code_off, code_len,
					      exc_table_entries);
  }


  protected void handleExceptionTableEntry (int this_method,
					 int this_exception,
					 int s, int e, int h, int c)
  {
    methods[this_method].exceptionEntry (this_exception,s,e,h,c);
  }

  protected void handleSourceFileAttribute (int idx) {
    source_file = idx;
  }

  protected void handleExceptionsAttribute(int this_method, 
					   int[] exceptions) 
  {
    methods[this_method].generated_exceptions = exceptions;
  }

  public class Method {
    
    public final int access_flags;
    public final int name_index;
    public final int descriptor_index;
    int[] generated_exceptions;

    public String signature () {
      return pool.get_utf8 (descriptor_index);
    }

    public String name () {
      return pool.get_utf8 (name_index);
    }
	
    Method (int acc, int nam, int desc) {
      access_flags = acc; name_index = nam; 
      descriptor_index = desc;
    }
	
    public int max_stack;
    public int max_locals;
    public byte[] code;
    public int code_off;
    public int code_len;

    public ExceptionTableEntry[] exceptions;

    void handleCodeAttribute (int max_stack,
			      int max_locals,
			      byte[] data, int code_off, int code_len,
			      int exc_table_entries)
    {
      this.max_stack = max_stack;
      this.max_locals = max_locals;
      code = data;
      this.code_off = code_off;
      this.code_len = code_len;

      exceptions = new ExceptionTableEntry[exc_table_entries];
    }

    void exceptionEntry (int this_exception,int s, int e, int h, int c)
    {
      exceptions[this_exception] = new ExceptionTableEntry (s, e, h, c);
    }

    public String toString() {
      return "<Method "
	+ pool.get_class (this_class)+"::"+ name ()
	+ " " + signature () + ">";
    }

  }

  public class ExceptionTableEntry {
    public int start_pc;
    public int end_pc;
    public int handler_pc;
    public int catch_type;

    ExceptionTableEntry (int s, int e, int h, int c) {
      start_pc = s; end_pc = e; handler_pc = h; catch_type = c;
    }

  }

  public class Field {

    final int access_flags;
    final int name_index;
    final int descriptor_index;

    Field (int acc, int nam, int desc) {
      access_flags = acc; name_index = nam; 
      descriptor_index = desc; 

    }

    int constant_value = -1;

    void setConstantValue (int index) {
      constant_value = index;
    }
    
    public String signature () {
      return pool.get_utf8 (descriptor_index);
    }

    public String name () {
      return pool.get_utf8 (name_index);
    }
  }
}
