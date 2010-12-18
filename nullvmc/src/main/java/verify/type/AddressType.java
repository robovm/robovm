/*
 *  AddressType.java -- The type of jsr/ret addresses
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

public class AddressType extends BasicType {

  final int return_pc;

  public int hashCode () { return name.hashCode (); }
  
  public boolean equals (Object other)
  {
    if (other instanceof AddressType)
      {
	AddressType bo = (AddressType)other;
	return return_pc == bo.return_pc;
      }
    return false;
  }

  /* this should return something that cannot be confused with a class
     name, becuase TypeContext keeps them in the same hash table using
     this value as the key.  */

  static String nameFor (int pc)
  {
    return "0x" + Integer.toHexString (pc);
  }

  AddressType (TypeContext ctx, int pc) 
  {
    super (ctx, T_ADDR, nameFor (pc));
    return_pc = pc;
  }

  public int pc ()
  {
    return return_pc;
  }

  public Type mergeWith (Type other)
  {
    if (other == null)
      return null;
    
    if (other.tag != T_ADDR)
      return null;

    AddressType ao = (AddressType)other;

    if (ao.return_pc == return_pc)
      return this;

    else
      return null;
  }



}
