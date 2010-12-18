/*
 *  ClassInfo.java 
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
 *  This is an abstract interface, used by the TypeContext to 
 *  establish subclass relationships.  In the use, it masks for
 *  a ClassFile object, but it could also be a cover for a real
 *  Class object, if such a thing is already loaded into the VM.
 */

public interface ClassInfo {

  int      getAccessFlags ();
  String   getClassName ();
  String   getSuperClassName ();
  String[] getSuperInterfaceNames ();

}


