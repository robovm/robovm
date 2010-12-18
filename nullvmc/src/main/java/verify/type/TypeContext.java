/*
 *  TypeContext.java 
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

/**
 *  Class TypeContext is the nursery for type objects.  "Global" variables
 *  are kept in this object, so that we are sure that we can garbage
 *  collect the entire thing away after we're done verifying.
 */
public class TypeContext implements TypeTags
{


  /**
   *  The basic types
   */
  public final BasicType BOOLEAN;
  public final BasicType BYTE;
  public final BasicType CHAR;
  public final BasicType SHORT;
  public final BasicType INT;
  public final BasicType LONG;
  public final BasicType FLOAT;
  public final BasicType DOUBLE;
  public final BasicType VOID;

  /**
   * This is also a little special...
   */
  public final ClassType OBJECT;
  public final ClassType THROWABLE;
  public final ClassType STRING;
  public final ClassType CLASS;

  /**
   *  The next two types, represent "the second half" of a 
   *  long or double in the abstract state 
   */
  public final BasicType LONG2;
  public final BasicType DOUBLE2;

  public final NullType  NULL;

  final ClassInfoProvider loader;

  public TypeContext (ClassInfoProvider the_loader)
  {
    loader = the_loader;

    BOOLEAN   = new BasicType (this, T_BOOLEAN, "boolean");
    BYTE      = new BasicType (this, T_BYTE,    "byte");
    CHAR      = new BasicType (this, T_CHAR,    "char");
    SHORT     = new BasicType (this, T_SHORT,   "short");
    INT       = new BasicType (this, T_INT,     "int");
    LONG      = new BasicType (this, T_LONG,    "long");
    FLOAT     = new BasicType (this, T_FLOAT,   "float");
    DOUBLE    = new BasicType (this, T_DOUBLE,  "double");
    VOID      = new BasicType (this, T_VOID,    "void");
    LONG2     = new BasicType (this, T_LONG2,   "long/2");
    DOUBLE2   = new BasicType (this, T_DOUBLE2, "double/2");

    OBJECT    = new ClassType (this, "java/lang/Object");
    classes.put ("java/lang/Object", OBJECT);

    THROWABLE = new ClassType (this, "java/lang/Throwable");
    classes.put ("java/lang/Throwable", THROWABLE);

    STRING    = new ClassType (this, "java/lang/String");
    classes.put ("java/lang/String", STRING);

    CLASS     = new ClassType (this, "java/lang/Class");
    classes.put ("java/lang/Class", CLASS);

    // the type of null (assignable to any reference type)
    NULL      = new NullType  (this);
  }

  public Type newForClass (ClassType classType)
  {
    return new NewObjectType (this, classType);
  }

  public Type forAddress (int pc)
  {
    String name = AddressType.nameFor (pc);
    Type type = (Type) classes.get (name);
    if (type == null)
      {
	type = new AddressType (this, pc);
	classes.put (name, type);
      }
    return type;
  }

  public Type classFor (String name)
    throws IllegalTypeNameException
  {
    if (name.charAt (0)=='[')
      return ArrayType.forElement (typeForName (name.substring (1)));
    else
      return classForName (name);
  }

  private final java.util.Hashtable classes
    = new java.util.Hashtable ();

  public ClassType classForName (String className)
    throws IllegalTypeNameException
  {
    ClassType result = (ClassType) classes.get (className);
    if (result == null)
      {
	result = new ClassType (this, className);
	classes.put (className, result);
      }
    return result;
  }

  public Type typeForName (String name)
    throws IllegalTypeNameException
  {
    int[] end = new int[1];
    Type result = typeForName (name, 0, end);

    if (end[0] != name.length ())
      throw new IllegalTypeNameException (name) ;
    return result;
  }

  public Type typeForName (String name, int start, int[] end)
    throws IllegalTypeNameException
  {
    switch (name.charAt (start)) {
    case 'Z': end[0] = start+1;  return BOOLEAN; 
    case 'B': end[0] = start+1;  return BYTE; 
    case 'C': end[0] = start+1;  return CHAR; 
    case 'S': end[0] = start+1;  return SHORT; 
    case 'I': end[0] = start+1;  return INT; 
    case 'J': end[0] = start+1;  return LONG; 
    case 'F': end[0] = start+1;  return FLOAT; 
    case 'D': end[0] = start+1;  return DOUBLE; 	
    case 'V': end[0] = start+1;  return VOID; 	
    case '[': return ArrayType.forName (this, name, start, end);
    case 'L': 
      for (int i = start+1; i < name.length (); i++)
	{
	  if (name.charAt (i) == ';')
	    {
	      end[0] = i+1;
	      return classForName (name.substring(start+1, i));
	    }
	}
      throw new IllegalTypeNameException
	("no end marker ';' in " + name.substring (start)) ;
      
      /* fall through and fail */
    }

    throw new IllegalTypeNameException (name.substring (start)) ;
  }

}

