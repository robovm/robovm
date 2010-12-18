/*
 *  ConstantPool.java
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




final public class ConstantPool 
  implements ClassFileConstants
{

  private byte[] bytes;
  private byte[] tags;
  private int[]  offsets;
  private Object cache[];
  private int    size;

  final void check_tag (int index, int tag)
  {
    if (index > size || index < 0)
      throw new ClassFormatError ("constant pool index out of range");
    
    if (tags[index] != tag)
      throw new ClassFormatError ("did not find "
				  + CONSTANT_Name[tag]
				  + " value at pool index " + index
				  + ", found: "
				  + CONSTANT_Name[tags[index]]);
  }


  ConstantPool (byte[] bytes, byte[] tags, int[] offsets, int count)
  {
    this.bytes    = bytes;
    this.tags    = tags;
    this.offsets = offsets;
    this.cache   = new Object[count];
    this.size    = count;

    verify_constant_pool ();
  }

  private void verify_constant_pool () 
  {
    /* "typecheck" constant pool */
    for (int c = 1; c < size; c++)
      {
	switch (tags[c]) {
	case CONSTANT_String:
	case CONSTANT_Class:
	  {
	    int idx = n2h.get2u (bytes, offsets[c]);
	    check_tag (idx, CONSTANT_Utf8);
	  }
	  break;
	    
	case CONSTANT_Fieldref:
	case CONSTANT_Methodref:
	case CONSTANT_InterfaceMethodref:
	  {
	    int class_idx = n2h.get2u (bytes, offsets[c]);
	    int nat_idx   = n2h.get2u (bytes, offsets[c]+2);
	    check_tag (class_idx, CONSTANT_Class);
	    check_tag (nat_idx, CONSTANT_NameAndType);
	  }
	  break;

	case CONSTANT_NameAndType:
	  {
	    int name_idx = n2h.get2u (bytes, offsets[c]);
	    int type_idx   = n2h.get2u (bytes, offsets[c]+2);
	    check_tag (name_idx, CONSTANT_Utf8);
	    check_tag (type_idx, CONSTANT_Utf8);
	  }
	  break;

	case CONSTANT_Utf8:		    
	case CONSTANT_Integer:
	case CONSTANT_Float:
	case CONSTANT_Unicode:
	  break;

	case CONSTANT_Double:
	case CONSTANT_Long:
	  c++;
	  break;

	default:
	  throw new ClassFormatError ("illegal tag " + tags[c] + "@" + c);
	}
      }

  }

  public final int get_tag (int index) {
    if (index < 1 || index >= size)
      throw new IllegalArgumentException ();

    return tags[index];
  }

  public final String get_utf8 (int index) {
    String result = (String) cache[index];
    if (result == null)
      {
	result = n2h.get_utf8_string (bytes, offsets[index]);
	cache[index] = result;
      }
    return result;
  }
    
  public final String get_class (int index) {
    check_tag (index, CONSTANT_Class);
    String result = (String) cache[index];
    if (result == null)
      {
	result = get_utf8 (n2h.get2u (bytes, offsets[index]));
	cache[index] = result;
      }
    return result;
  }

  public final String get_string (int index) {
    check_tag (index, CONSTANT_String);
    String result = (String) cache[index];
    if (result == null)
      {
	result = get_utf8 (n2h.get2u (bytes, offsets[index]));
	cache[index] = result;
      }
    return result;
  }

  public final int get_integer (int index) {
    check_tag (index, CONSTANT_Integer);
    return n2h.get4 (bytes, offsets[index]);
  }

  public final long get_long (int index) {
    check_tag (index, CONSTANT_Long);
    return n2h.get8 (bytes, offsets[index]);
  }

  public final float get_float (int index) {
    check_tag (index, CONSTANT_Float);
    int data =  n2h.get4 (bytes, offsets[index]);
    return Float.intBitsToFloat (data);
  }

  public final double get_double (int index) {
    check_tag (index, CONSTANT_Double);
    long data =  n2h.get8 (bytes, offsets[index]);
    return Double.longBitsToDouble (data);
  }

  /** get_index(0|1) comes in two variations, a checked an a non-checked.
      The non-checked is supposed to be used when doing subsequent reads,
      in a context, where it is nown to be sound.  */

  private final IndexPair load_two_indexes (int index)
  {
    IndexPair pair = new IndexPair (n2h.get2u (bytes, offsets[index]),
				    n2h.get2u (bytes, offsets[index]+2));
    cache[index] = pair;
    return pair;
  }

  private final int get_index0 (int index) {
    IndexPair pair = (IndexPair) cache[index];
    if (pair == null)
      return load_two_indexes (index).index0;
    else
      return pair.index0;
  }

  private final int get_index0 (int index, int tag) {
    check_tag (index, tag);
    IndexPair pair = (IndexPair) cache[index];
    if (pair == null)
      return load_two_indexes (index).index0;
    else
      return pair.index0;
  }

  private final int get_index1 (int index) {
    IndexPair pair = (IndexPair) cache[index];
    if (pair == null)
      return load_two_indexes (index).index1;
    else
      return pair.index1;
  }

  private final int get_index1 (int index, int tag) {
    check_tag (index, tag);
    IndexPair pair = (IndexPair) cache[index];
    if (pair == null)
      return load_two_indexes (index).index1;
    else
      return pair.index1;
  }

  public final String get_fieldref_class (int index)
  {
    int class_index = get_index0 (index, CONSTANT_Fieldref);
    return get_class (class_index);
  }

  public final String get_fieldref_name (int index)
  {
    int nat_index = get_index1 (index, CONSTANT_Fieldref);
    int name_index = get_index0 (nat_index);
    return get_utf8 (name_index);
  }

  public final String get_fieldref_type (int index)
  {
    int nat_index = get_index1 (index, CONSTANT_Fieldref);
    int type_index = get_index1 (nat_index);
    return get_utf8 (type_index);
  }


  public final String get_interfacemethodref_class (int index)
  {
    int class_index = get_index0 (index, CONSTANT_InterfaceMethodref);
    return get_class (class_index);
  }

  public final String get_interfacemethodref_name (int index)
  {
    int nat_index = get_index1 (index, CONSTANT_InterfaceMethodref);
    int name_index = get_index0 (nat_index);
    return get_utf8 (name_index);
  }

  public final String get_interfacemethodref_type (int index)
  {
    int nat_index = get_index1 (index, CONSTANT_InterfaceMethodref);
    int type_index = get_index1 (nat_index);
    return get_utf8 (type_index);
  }


  public final String get_methodref_class (int index)
  {
    int class_index = get_index0 (index, CONSTANT_Methodref);
    return get_class (class_index);
  }

  public final String get_methodref_name (int index)
  {
    int nat_index = get_index1 (index, CONSTANT_Methodref);
    int name_index = get_index0 (nat_index);
    return get_utf8 (name_index);
  }

  public final String get_methodref_type (int index)
  {
    int nat_index = get_index1 (index, CONSTANT_Methodref);
    int type_index = get_index1 (nat_index);
    return get_utf8 (type_index);
  }

}

/* used in the constant pool cache */
class IndexPair {
  int index0, index1;
  IndexPair (int i0, int i1) { index0=i0; index1=i1; }
}


  
