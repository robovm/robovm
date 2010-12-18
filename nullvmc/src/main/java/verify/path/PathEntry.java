/*
 *  PathEntry.java
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




abstract class PathEntry {
    abstract URL getURL (String file);
    abstract InputStream getStream (String file);
    abstract byte[] getBytes (String file);

    protected static byte[] readbytes (InputStream is, int length)
    {
	try {

	    byte[] data = new byte[length];
	    int read; 
	    int off = 0;
	    
	    while (off != length)
		{
		    read = is.read (data, off, (int) (length-off));

		    if (read == -1) 
			return null;

		    off += read;
		}
	    
	    return data;
	} catch (IOException x) {
	    return null;
	}
    }

}


