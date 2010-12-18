/*
 *  NewObjectType.java 
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

/*
 *  This represents a new unitinialized object.
 *  Such ones, have their own storage type, which
 *  means that they cannot be stored or manipulated
 *  in other ways than using typeless on-stack operations
 *  dup, dup2, etc., or they can be popped by the code
 *  to invoke an initializer invokespecial #<init>.
 */

public class NewObjectType extends Type {

  public final ClassType klass;
  NewObjectType (TypeContext ctx, ClassType klass)
  {
    super (ctx, T_NEW);
    this.klass = klass;
  }

  protected void subclassCheckAssignmentFrom (Type other)
    throws IncompatibleTypesException
  {
    throw new InternalError
      ("something is declared of type"+ this);
  }

  public String toString ()
  {
    return "<uninitialized " + klass + ">"; 
  }

  public Type mergeWith (Type other)
  {
    if (other == null)
      return null;
    
    if (other.tag != T_NEW)
      return null;

    NewObjectType ao = (NewObjectType)other;

    if (ao.klass == klass)
      return this;

    else
      return null;
  }

  

}
