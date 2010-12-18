/*
 *  BasicType.java 
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

public class BasicType extends Type {
    
  protected String name;
  BasicType (TypeContext ctx, int tag, String name)
  {
    super (ctx, tag);
    this.name = name;
  }
    
  public String toString () { return name; }

  /** 
   *  Called to verify an assignment of a location of type "this"
   *  to a new value of type other.
   */
  protected void subclassCheckAssignmentFrom (Type other)
    throws IncompatibleTypesException
  {
    if (other == this)
      return;

    /* we allow all small integral types to be interchangable */
    if (storageClass == SC_INT && other.storageClass == SC_INT)
      return;

    throw new IncompatibleTypesException (this, other);
  }

  public Type mergeWith (Type other)
  {
    if (other == this)
      return this;

    if (other == null)
      return null;
    
    if (storageClass != other.storageClass)
      return null;

    if (storageClass == SC_INT)
      return context.INT;

    throw new InternalError ("BasicType merge");
  }

}

  
