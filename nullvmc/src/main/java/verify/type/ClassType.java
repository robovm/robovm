/*
 *  ClassType.java 
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

import verify.classfile.ClassFile;

public class ClassType extends Type {

  final String class_name;

  ClassType (TypeContext ctx, String name)
  {
    super (ctx, T_CLASS);
    this.class_name = name;
  }

  public String toString ()
  {
    return class_name.replace ('/', '.').replace ('$','.');
  }

  private ClassType[] interfaces;
  private ClassType   super_class;

  /* called from BytecodeVerifier, and from this class... */
  public void loadFrom (ClassInfo info)
    throws IllegalTypeNameException
  {
    if (this == context.OBJECT)
      return;

    if (info == null)
      {
	/* do something meaningful, assume that we're a subclass
	   of object, if nothing else */

	if (super_class == null)
	  super_class = context.OBJECT;
	return;
      }

    //    System.err.print ("<loading "+class_name+" ");

    // collect as much information as possible...
    String sup = info.getSuperClassName ();
    if (sup != null)
      {
	super_class = (ClassType) context.classFor (sup);
      }
    else if (super_class == null)
      {
	super_class = context.OBJECT;
      }

    //    System.err.print ("sup="+super_class+" ");

    String[] ifaces = info.getSuperInterfaceNames ();
    if (ifaces != null)
      {
	interfaces = new ClassType[ifaces.length];
	for (int i = 0; i < ifaces.length; i++)
	  {
	    interfaces[i] = (ClassType) context.classFor (ifaces[i]);
	    //    System.err.print ("if["+i+"]="+interfaces[i]+" ");

	  }
      }

    //    System.err.print (">");

    i_am_loaded = true;
  }

  boolean i_am_loaded = false;
  boolean fully_loaded = false;

  private void load ()
    throws verify.VerificationException
  {
    if (fully_loaded) 
      return;

    if (! i_am_loaded)
      {
	//System.err.print ("["+(this)+"]");
	try {
	  ClassInfo info = context.loader.provide (class_name);
	  if (info == null)
	    throw new verify.VerificationException
	      ("cannot load class "+this);
	  loadFrom (info);
	} catch (IllegalTypeNameException x) {
	  /* ignore? */
	} 
      }

    fully_loaded = true;

    if (super_class != null)
      super_class.load ();

    if (interfaces != null)
      {
	for (int i = 0; i < interfaces.length; i++)
	  {
	    interfaces[i].load ();
	  }
      }

  }

  String shortName () { 
    int dot = class_name.lastIndexOf ('/');
    if (dot == -1)
      return class_name;
    else
      return "..."+class_name.substring (dot+1);
  }

  public boolean isSubtypeOf (ClassType otherClass)
    throws verify.VerificationException
  {
    if (this == otherClass)
      { 
	return true; 
      }

    if (super_class == null)
      load ();

    if (super_class != null)
      if (super_class.isSubtypeOf (otherClass))
	{
	  return true;
	}

    if (interfaces != null)
      {
	for (int i = 0; i < interfaces.length; i++)
	  {
	    if (interfaces[i].isSubtypeOf (otherClass))
	      { return true; }
	  }
      }

    return false;
  }

  public Type commonSuperWith (ClassType other)
  {
    ClassType best_guess = context.OBJECT;

    try {
      
      if (this.isSubtypeOf (other))
	return other;
      
      if (other.isSubtypeOf (this))
	return this;

      return context.NULL;

      /*      
      for (ClassType c = this; c != null; c = c.super_class)
	{
	  if (other.isSubtypeOf (c))
	    {
	      if (c != context.OBJECT)
		return c;
	      else
		break;
	    }
	}
      
      if (interfaces != null)
	for (int i = 0; i < interfaces.length; i++)
	  {
	    best_guess = interfaces[i].commonSuperWith (other);
	    
	    if (best_guess != context.OBJECT)
	      return best_guess;
	  }
      */
    } catch (verify.VerificationException x) {
      return context.NULL;
    }
    
  }

  public Type mergeWith (Type other)
    throws verify.VerificationException
  {
    if (other == null)
      return null;

    if (this == other)
      return this;
    
    if (other.tag == T_CLASS)
      return commonSuperWith ((ClassType)other);

    if (other.storageClass != SC_ADDR)
      return null;
    
    if (other.tag == T_ARRAY)
      return context.OBJECT;
    
    if (other.tag == T_NULL)
      return this;
    
    else
      return null;

  }

  public void subclassCheckAssignmentFrom (Type other)
    throws verify.VerificationException
  {
    if (other == this)
      return;

    if (other.storageClass != Type.T_ADDR)
      {
	throw new IncompatibleTypesException (this, other);
      }

    if (this == context.OBJECT) 
      return;

    if (other.tag == T_CLASS)
      {
	ClassType other_class = (ClassType)other;

	if (other_class.isSubtypeOf (this))
	  return;

	System.out.println ("this = class "+class_name);
	System.out.println ("other= class "+other_class.class_name);

      }

    if (other.tag == T_NULL)
      return;

    throw new IncompatibleTypesException (other, this);
  }

}
