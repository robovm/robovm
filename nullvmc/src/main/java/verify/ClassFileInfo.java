/*
 *  ClassFileInfo.java -- give ClassFile's ClassInfo interface
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


package verify;

import verify.classfile.ClassFile;
import verify.type.ClassInfo;

public class ClassFileInfo implements ClassInfo {

  private final ClassFile file;

  public ClassFileInfo (ClassFile c) { file = c; }

  public int getAccessFlags ()
    {
      return file.access_flags;
    }  
    
  public String   getClassName ()
    {
      return file.pool.get_class (file.this_class);
    }
    
  public String   getSuperClassName ()
    {
      if (file.super_class != 0)
	return file.pool.get_class (file.super_class);
      else
	return null;
    }
    
  public String[] getSuperInterfaceNames ()
    {
      if (file.interfaces_count == 0)
	return null;
	
      String[] result = new String[file.interfaces_count];
      for (int i = 0; i < file.interfaces_count; i++)
	{
	  result[i] = file.pool.get_class (file.interfaces[i]);
	}
	
      return result;
    }
    
    
}


