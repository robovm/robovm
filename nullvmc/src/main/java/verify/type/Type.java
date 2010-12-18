/*
 *  Type.java 
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

package verify.type;

public abstract class Type implements TypeTags
{

  // this holds the specific tag for this type
  public final int tag;

  // The context defining this type.  
  public final TypeContext context;

  // the id of this type in the given type context.
  public final int storageClass; 

  // if non-null, points to array type for this kind of element.
  ArrayType arrayType;

  protected Type (TypeContext ctx, int tag) 
  {
    this.tag           = tag;
    this.context       = ctx;
    this.storageClass  = computeStorageClass (tag);
  }

  // this is designed, so it can easily be inlined.
  public final void checkAssignmentFrom (Type other)
    throws verify.VerificationException
  {
    if (other == this)
      return;

    // any reference type can have null assigned to it.
    if (storageClass == T_ADDR && other.tag == T_NULL)
      return;

    subclassCheckAssignmentFrom (other);
  }

  protected abstract void subclassCheckAssignmentFrom (Type other)
    throws verify.VerificationException;

  public String storageClassName ()
  {
    return nameForStorageClass (storageClass);
  }

  public static int computeStorageClass (int tag)
  {
    switch (tag)
      {
      case T_BOOLEAN: case T_CHAR: case T_BYTE:
      case T_SHORT: case T_INT:
	return SC_INT;

      case T_ARRAY: case T_CLASS:
      case T_ADDR: case T_NULL:
	return SC_ADDR;

      default:
	return tag;
      }
  }

  public static String nameForStorageClass (int class0)
  {
    switch (class0)
      {
      case SC_INT:
	return "integer";

      case SC_FLOAT:
	return "float";

      case SC_DOUBLE:
	return "double/1";

      case SC_DOUBLE2:
	return "double/2";
	
      case SC_LONG:
	return "long/1";

      case SC_LONG2:
	return "long/2";

      case SC_NEW:
	return "new object";

      case SC_ADDR:
	return "reference";

      default:
	throw new InternalError ("no such storage class " + class0);
      }
  }
  
  /**
   *  Certain other values, are OK in place of arrays, ...
   *  If an array operation (store/load) is attempted on an Type
   *  for which this method answers true, then we would have to insert
   *  some kind of runtime check...
   */
  public boolean isAnonymousArray ()
  {
    return tag == T_NULL;
  }

  abstract public Type mergeWith (Type other)
    throws verify.VerificationException;
}



