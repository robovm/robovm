/*
 *  ZipPathEntry.java
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



final class ZipPathEntry extends PathEntry {
    final ZipFile zip;
    final URL     file;

    public String toString () { return zip.getName (); }

    ZipPathEntry (File f) 
	throws MalformedURLException, ZipException, IOException
    {
	file = new URL ("file", "", f.getPath ());
	zip  = new ZipFile (f);
	//zip.readDirectory ();
    }

    /* 
       The url for a zip-file resource is,
	   
       <code>file:///path/file.zip#name</code>
	   
       Then, it is URLConnection's problem to handle that.
    */

    URL getURL (String f) {

	ZipEntry ent = zip.getEntry (f);

	try {
	    if (ent != null)
		return new URL (file, "#"+f);
	    else
		return null;
	} catch (IOException x) {
	    return null;
	}
    }

    InputStream getStream (String f) {

	ZipEntry ent = zip.getEntry (f);

	try {
	    if (ent != null)
		return zip.getInputStream (ent);
	    else
		return null;
	} catch (IOException x) {
	    return null;
	}
    }

    byte[] getBytes (String f) {
	ZipEntry ent = zip.getEntry (f);

	try {
	    if (ent != null)
	      {
		return readbytes (zip.getInputStream (ent),
				  (int) ent.getSize ());
	      }
	    else
	      {
		return null;
	      }
	} catch (IOException x) {
	  x.printStackTrace ();
	    return null;
	}
	    
    }
}

