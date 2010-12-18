/*
 *  ClassFileConstants.java
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

import java.lang.reflect.Modifier;

public interface ClassFileConstants 
{
  public static final int CONSTANT_Utf8 = 1;
  public static final int CONSTANT_Unicode = 2;
  
  public static final int CONSTANT_Integer = 3;
  public static final int CONSTANT_Float = 4;
  public static final int CONSTANT_Long = 5;
  public static final int CONSTANT_Double = 6;
  
  public static final int CONSTANT_Class = 7;
  public static final int CONSTANT_String = 8;
  
  public static final int CONSTANT_Fieldref = 9;
  public static final int CONSTANT_Methodref = 10;
  public static final int CONSTANT_InterfaceMethodref = 11;
  public static final int CONSTANT_NameAndType = 12;

  public static final String[] CONSTANT_Name = 
  {
    "<ILLEGAL>",                        /* 0 */
    "CONSTANT_Utf8", 			/* 1 */
    "CONSTANT_Unicode", 		/* 2 */
  
    "CONSTANT_Integer", 		/* 3 */
    "CONSTANT_Float", 			/* 4 */
    "CONSTANT_Long", 			/* 5 */
    "CONSTANT_Double", 			/* 6 */
  
    "CONSTANT_Class", 			/* 7 */
    "CONSTANT_String", 			/* 8 */
  
    "CONSTANT_Fieldref", 		/* 9 */
    "CONSTANT_Methodref", 		/* 10 */
    "CONSTANT_InterfaceMethodref", 	/* 11 */
    "CONSTANT_NameAndType" 		/* 12 */
  };

  public static final int PUBLIC = Modifier.PUBLIC;
  public static final int PRIVATE = Modifier.PRIVATE;
  public static final int PROTECTED = Modifier.PROTECTED;
  public static final int STATIC = Modifier.STATIC;
  public static final int FINAL = Modifier.FINAL;
  public static final int SYNCHRONIZED = Modifier.SYNCHRONIZED;
  public static final int VOLATILE = Modifier.VOLATILE;
  public static final int TRANSIENT = Modifier.TRANSIENT;
  public static final int NATIVE = Modifier.NATIVE;
  public static final int INTERFACE = Modifier.INTERFACE;
  public static final int ABSTRACT = Modifier.ABSTRACT;
  
}
