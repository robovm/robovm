/*
 *  Signature.java 
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


public class Signature {

  final Type   return_type;
  final Type[] declared_arguments;

  public Type returnType ()
  {
    return return_type;
  }

  public int numberArgs ()
  {
    return declared_arguments.length;
  }

  public Type declaredArgument (int index)
  {
    if (index < 0 || index >= declared_arguments.length)
      throw new IllegalArgumentException ();
	
    return declared_arguments[index];
  }

  private static java.util.Stack stack_of_types = new java.util.Stack();

  public static Signature forString (TypeContext ctx, String sig)
    throws IllegalTypeNameException
  {
    Type[] args; Type result;
    if (sig.charAt (0) == '(')
      {
	int[] end = new int[1];
	int index = 1;
	    
	synchronized (stack_of_types)
	  {
	    while (sig.charAt (index) != ')')
	      {
		stack_of_types.push (ctx.typeForName (sig, index, end));
		index = end[0];
	      }

	    int count = stack_of_types.size ();
	    args = new Type[count];
	    while (count-- > 0)
	      args[count] = (Type)stack_of_types.pop ();
	  }

	result = ctx.typeForName (sig, index+1, end);

	if (end[0] == sig.length ())
	  return new Signature (result, args);
      }

    throw new IllegalTypeNameException ("bad signature: "+ sig);
  }

  private Signature (Type ret, Type[] args)
  {
    return_type = ret;
    declared_arguments = args;
  }

  public String toString ()
  {
    StringBuffer b = new StringBuffer ("(");
    for (int i = 0; i < declared_arguments.length; i++)
      {
	b.append (declared_arguments[i]);
	if (i+1 != declared_arguments.length)
	  b.append (", ");
      }
    b.append (") ");
    b.append (return_type);
    return b.toString ();
  }
}
