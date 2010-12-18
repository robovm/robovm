/*
 *  IncompatibleTypesException.java 
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

public class IncompatibleTypesException extends verify.VerificationException
{
  public IncompatibleTypesException (String txt)
  {
    super (txt);
  }

  public IncompatibleTypesException (Type from, Type to)
  {
    super (message1 (from, to));
  }
  
  private static String message1 (Type from, Type to)
  {
    return "incompatible assignment from " 
           + from.toString ()
           + " to "
           + to.toString();
  }

  public IncompatibleTypesException (String text, 
				     Type found, 
				     int expected_storage_class)
  {
    super (message2 (text, found, expected_storage_class));
  }

  private static String message2 (String text, 
				  Type found, 
				  int expected_storage_class)
  {
    return "byte code problem: " + text + "\n"
	   + "\tFound value of type " + found.toString ()
	   + ", expected value with storage class "
	   + Type.nameForStorageClass (expected_storage_class);
  }
}
