/*
 *  CacheEntry.java
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


package verify.path;

import java.util.*;
import java.util.zip.*;
import java.io.*;
import java.net.*;


final class CacheEntry {
    String   dir;
    String[] files;
    long     time;
    
    CacheEntry (String d)
    {
	dir = d;
	files = new File(dir).list();
	time = System.currentTimeMillis ();
    }
    
    void touch ()
    {
	time = System.currentTimeMillis ();
    }
    
    final long EXPIRATION_TIME_MS = 10000;
    
    boolean old () {
	return (System.currentTimeMillis () - time) > EXPIRATION_TIME_MS;
    }
    
    public int hashCode () { return dir.hashCode(); }
    boolean contains (String file) { 
	if (files == null)
	    return false;
	
	int index = file.lastIndexOf(SearchPath.file_seperator_char);
	String f;
	
	if (index == -1)
	    f = file;
	else
	    f = file.substring (index+1);
	
	for (int i = 0; i < files.length; i++)
	    {
		if (f.equals (files[i])) return true;
	    }
	
	return false;
    }
}

