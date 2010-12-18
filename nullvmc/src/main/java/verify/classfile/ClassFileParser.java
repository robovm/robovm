/*
 *  ClassFileParser.java
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

/**
 *   A <code>ClassFileParser</code> is used for parsing 
 *   class files (for whatever reason).  It has a single
 *   entrypoint, the static method <code>parse</code>.
 *   This method is given the class file information, as
 *   an array of bytes, and an instance of a subclass of
 *   <code>ClassFileHandler</code>.  As the parser progresses
 *   through the file, it will call 
 *
 *
 */

public class ClassFileParser 
  implements ClassFileConstants
{


  /**** handleXXX methods ****/

  protected void handlePreample (int magic, int minor, int major) {}

  protected void handleConstantPool (byte[] data,
				     byte[] tags,
				     int[] offsets,
				     int count) {} 

  /* 2 */
  protected void handleClassBegin (int acc, int th, int sup) {}

  protected void handleInterfacesBegin (int count) {}
  protected void handleInterface (int if_index, int offset) {}
  protected void handleInterfacesEnd () {}

    
  protected void handleFieldsBegin (int count) {}
  protected void handleField (int findex,
			      int access_flags, 
			      int name_index, 
			      int descriptor_index) {}
  protected void handleConstantValueAttribute (int field_index,
					       int valindex) {}
  protected void handleFieldsEnd () {}

  protected void handleMethodsBegin (int count) {}
  protected void handleMethod (int mth_index,
			       int acc,
			       int name,
			       int desc) {}
  protected void handleCodeAttributeBegin (int method_index,
					   int max_stack,
					   int max_locals,
					   byte[] code,
					   int code_off,
					   int code_len,
					   int exc_table_length)  
  {}
  protected void handleExceptionTableEntry (int method_index,
					    int exc_index,
					    int s, int e, int h, int c)
  {
  }



  protected void handleExceptionsAttribute (int method_id, 
					    int[] exceptions) {}
  protected void handleMethodsEnd () {}

  protected void handleSourceFileAttribute (int idx) {}

  protected void handleUnknownAttribute (int  name, 
					 byte[] data,
					 int att_off,
					 int att_len) { }

  protected void handleClassEnd () {}


  
  /* This is used for cheking names of attributes, 
   * and since these are all constructed from charcters
   * represented by 7 bits in the modified UTF8 format,
   * we don't need to actually decode the UTF data.
   */
  private final boolean equals_utf8 (int name, String s)
  {
    int where = offsets[name];
    int len = n2h.get2u (bytes, where);

    if (len != s.length ()) return false;
    for (int i = 0; i < len; i++)
      {
	if (bytes[i+where+2] != s.charAt (i))
	  return false;
      }
    return true;
  }


  /***** private members ********/

  private byte[] bytes;
  private int offset;
  private final int length;
  private final int start;

  private int    constant_pool_count;
  private byte   tags[];
  private int    offsets[];
    
  protected ClassFileParser (byte [] i,
			     int off,
			     int len)
  {
    // First, some very rudimentary checks, so that
    // we know that we can read the entire byte array.
    if (i == null) throw new NullPointerException ();
    if (off > i.length) throw new IllegalArgumentException ();
    if (off+len > i.length) throw new IllegalArgumentException ();
    if (len < 0) throw new IllegalArgumentException ();

    bytes = i;
    start = offset = off;
    length = len;
  }

  // this is called upon invocation of parse()
  private void rewind()
  {
    offset = start;
  }

  // check that we can access the next NUM bytes
  final private void check (int num)
  {
    if (offset+num > start+length)
      throw new ClassFormatError ("Premature end of data");
  }

  // Read 1 byte unsigned.  Since byte is signed, we need to
  // mask off the possible sign extension.  Hopefully the 
  // compiler is smart about it...
  private int read1 () {
    check (1);
    return n2h.get1u(bytes, offset++);
  }

  // read 2 bytes unsigned
  private int read2u () {
    check (2);
    int res= n2h.get2u (bytes, offset);
    offset += 2;
    return res;
  }

  // read 4 bytes, signed or unsigned
  private int read4 () {
    check (4);
    int res= n2h.get4 (bytes, offset);
    offset += 4;
    return res;
  }

  // read 8 bytes signed or unsigned data
  private long read8 () {
    long res = n2h.get8 (bytes, offset);
    return res;
  }

  // skip over some data (we do this quite a lot)
  private final void skip (int i) {
    check (i);
    offset += i;
  }

  // the current position
  private int pos () { return offset; }

  protected final void check_tag (int index, int tag)
  {
    if (index > constant_pool_count || index < 0)
      throw new ClassFormatError ("constant pool index out of range");
    
    if (tags[index] != tag)
      throw new ClassFormatError ("did not find "
				  + CONSTANT_Name[tag]
				  + " value at pool index " + index
				  + ", found: "
				  + CONSTANT_Name[tags[index]]);
  }

  public void parse ()
  {
    rewind ();

    int magic = read4 ();
    int minor_version = read2u ();
    int major_verson = read2u ();

    if (magic != 0xCAFEBABE)
      throw new ClassFormatError ("bad magic number");
	
    handlePreample (magic, minor_version, major_verson);
	
    constant_pool_count = read2u ();
	
    read_constpool (constant_pool_count);

    int access_flags = read2u ();
    int this_class = read2u ();
    int super_class = read2u ();

    check_tag (this_class, CONSTANT_Class);
    if (super_class != 0) 
      check_tag (super_class, CONSTANT_Class);

    handleClassBegin (access_flags, this_class, super_class);

    int interfaces_count = read2u (); 
	
    handleInterfacesBegin (interfaces_count);

    for (int i = 0; i < interfaces_count; i++)
      {
	int iface = read2u ();
	check_tag (iface, CONSTANT_Class);
	handleInterface (i, iface);
      }

    handleInterfacesEnd ();

    read_fields ();
    read_methods ();

    int attributes_count = read2u ();

    for (int i = 0; i < attributes_count; i++)
      {
	read_one_class_attribute ();
      }

    if (offset-start != length)
      throw new ClassFormatError ("unused data before end of file");
  }

  private void read_constpool (int count)
  {
    tags = new byte[count];
    offsets = new int[count];

    offsets[0] = pos ();
    for (int c = 1; c < count; c++)
      {
	int tag = read1 ();
	tags[c] = (byte) tag;
	offsets[c] = pos ();

	switch (tag) {
	case CONSTANT_String:
	case CONSTANT_Class:
	  skip (2);
	  break;
	    
	case CONSTANT_Fieldref:
	case CONSTANT_Methodref:
	case CONSTANT_InterfaceMethodref:
	case CONSTANT_NameAndType:
	case CONSTANT_Integer:
	case CONSTANT_Float:
	  skip (4);
	  break;

	case CONSTANT_Double:
	case CONSTANT_Long:
	  skip (8);
	  c++;
	  break;
	    
	case CONSTANT_Utf8:		    
	  int len = read2u ();
	  skip (len);
	  break;

	case CONSTANT_Unicode:
	  throw new ClassFormatError ("unicode not supported");

	default:
	  throw new ClassFormatError ("illegal tag " + tag);
	}
      }

    handleConstantPool (bytes, tags, offsets, count);
  }

  private void read_fields ()
  {
    
    int fields_count = read2u ();
    handleFieldsBegin (fields_count);

    for (int i = 0; i < fields_count; i++)
      {
	int access_flags     = read2u ();
	int name_index       = read2u ();
	int descriptor_index = read2u ();
	int attributes_count = read2u ();

	check_tag (name_index, CONSTANT_Utf8);
	check_tag (descriptor_index, CONSTANT_Utf8);

	handleField (i,
		     access_flags,
		     name_index,
		     descriptor_index);
	
	for (int j = 0; j < attributes_count; j++)
	  {
	    read_one_field_attribute (i);
	  }
      }

    handleFieldsEnd ();

  }

  private int read_one_class_attribute ()
  {
    int name = read2u ();
    int length = read4 ();

    check_tag (name, CONSTANT_Utf8);

    if (equals_utf8 (name, "SourceFile"))
      {
	int sf = read2u ();
	check_tag (sf, CONSTANT_Utf8);
	handleSourceFileAttribute (sf);
	if (length != 2) throw new ClassFormatError ();
      }

    else
      {
	int here = pos ();
	check (length);
	handleUnknownAttribute (name, 
				bytes,
				pos (),
				length);
	offset +=  length;
      }

    return length+6;
  }

  private int read_one_method_attribute (int method_index)
  {
    int name = read2u ();
    int length = read4 ();

    check_tag (name, CONSTANT_Utf8);

    if (equals_utf8 (name, "Exceptions"))
      {
	int len = read2u ();
	int[] indexes = new int[len];
	for (int i = 0; i < len; i++)
	  indexes[i] = read2u ();
	handleExceptionsAttribute (method_index, indexes);
      }
    else if (equals_utf8 (name, "Code"))
      {
	int start_off = pos ();
	int max_stack = read2u ();
	int max_locals = read2u ();
	int code_length = read4 ();

	int code = pos ();
	skip (code_length);
	int exception_table_length = read2u ();

	handleCodeAttributeBegin (method_index, 
				  max_stack, max_locals,
				  bytes, code, code_length,
				  exception_table_length);


	for (int i = 0; i < exception_table_length; i++)
	  {
	    int start_pc   = read2u ();
	    int end_pc     = read2u ();
	    int handler_pc = read2u ();
	    int catch_type = read2u ();

	    if (start_pc > end_pc
		|| start_pc > code_length
		|| end_pc > code_length
		|| handler_pc > code_length)
	      throw new ClassFormatError ();

	    if (! (tags[catch_type] == CONSTANT_Class
		   || tags[catch_type] == 0))
	      {
		throw new ClassFormatError ("missing exception handler info");
	      }

	    handleExceptionTableEntry (method_index,
				       i,
				       start_pc,
				       end_pc,
				       handler_pc, 
				       catch_type);

	  }
	
	int attributes_count = read2u ();

	for (int i = 0; i < attributes_count; i++)
	  {
	    read_one_code_attribute (method_index);
	  }

	if ((pos() - start_off) != length)
	  throw new ClassFormatError ();
      }

    else
      {
	int here = pos ();
	check (length);
	handleUnknownAttribute (name, 
				bytes,
				pos (),
				length);
	offset +=  length;
      }

    return length+6;
  }

  private int read_one_field_attribute (int field_index)
  {
    int name = read2u ();
    int length = read4 ();

    check_tag (name, CONSTANT_Utf8);

    if (equals_utf8 (name, "ConstantValue"))
      {
	int cv = read2u ();

	if (cv < constant_pool_count 
	    && cv > 0
	    && (   tags[cv] == CONSTANT_Integer
		|| tags[cv] == CONSTANT_Float
		|| tags[cv] == CONSTANT_Long
		|| tags[cv] == CONSTANT_Double
		|| tags[cv] == CONSTANT_String))
	  {
	    handleConstantValueAttribute (field_index, cv);
	  }
	else
	  {
	    throw new ClassFormatError ("erroneous ConstantValue");
	  }

	if (length != 2) throw new ClassFormatError ();
      }

    else
      {
	int here = pos ();
	check (length);
	handleUnknownAttribute (name, 
				bytes,
				pos (),
				length);
	offset +=  length;
      }

    return length+6;
  }

  private int read_one_code_attribute (int method_index)
  {
    int name = read2u ();
    int length = read4 ();

    check_tag (name, CONSTANT_Utf8);

      {
	int here = pos ();
	check (length);
	handleUnknownAttribute (name, 
				bytes,
				pos (),
				length);
	offset +=  length;
      }

    return length+6;
  }

  private void read_methods ()
  {
    int methods_count = read2u ();

    handleMethodsBegin (methods_count);

    for (int i = 0; i < methods_count; i++)
      {
	int access_flags     = read2u ();
	int name_index       = read2u ();
	int descriptor_index = read2u ();
	int attributes_count = read2u ();

	check_tag (name_index, CONSTANT_Utf8);
	check_tag (descriptor_index, CONSTANT_Utf8);

	handleMethod (i, access_flags, name_index,
		      descriptor_index);
	
	for (int j = 0; j < attributes_count; j++)
	  {
	    read_one_method_attribute (i);
	  }
      }

    handleMethodsEnd ();
  }

}

  
