/*
 *  DirectoryPathEntry.java
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

final class DirectoryPathEntry extends PathEntry {
    final File dir;
    final String base_canon;

    public String toString () { return base_canon; }

    DirectoryPathEntry (File f)
	throws java.io.IOException
    {
	if (!f.isAbsolute ())
	    throw new IllegalArgumentException ();

	dir = f; 
	base_canon = dir.getCanonicalPath ();
    }

    /*
     *  We maintain a cache of files, so that we 
     *  can avoid many calls to stat, which are
     *  very expensive.
     *
     *  seen_cache contains (as keys) the directories 
     *  which we have visited so far.  The values are 
     *  instances of Long, designating the time of the 
     *  information.
     *
     *  file_cache contains path names for all
     *  the files in the visited directories.
     *
     *  Everytime a new directory is visited we
     *  use File.list() to read all the file names
     *  in that directory.  
     */

    private Hashtable seen_cache = new Hashtable ();

    private boolean in_cache (File f)
    {
	String dir = f.getParent ();
	CacheEntry ent;

	if (dir == null)
	    throw new IllegalArgumentException ();

	ent = (CacheEntry) seen_cache.get (dir);
	if (ent == null)
	    {
		ent = new CacheEntry (dir);
		seen_cache.put (dir, ent);
	    }

	if ( ent.contains (f.getPath ()) )
	    {
		return true;
	    }

	if ( ent.old () )
	    {
		if (f.exists ())
		    {
			seen_cache.remove (dir);
			return true;
		    }
		else
		    {
			ent.touch ();
		    }
	    }

	return false;
    }

    URL getURL (String file) {
	File f = new File (dir, file);
	    
	try {
	    if (! f.getCanonicalPath ().startsWith (base_canon))
		throw new IllegalArgumentException (file);

	    if (in_cache (f))
		return new URL ("file", "", f.getPath ());
	    else
		return null;

	} catch (IOException x) {
	    return null;
	}
    }

    InputStream getStream (String file) {
	File f = new File (dir, file);

	try {			
	    if (! f.getCanonicalPath ().startsWith (base_canon))
		throw new IllegalArgumentException (file);

	    if (in_cache (f))
		return new FileInputStream (f);
	    else
		return null;
	} catch (IOException x) {
	    return null;
	}
    }

    byte[] getBytes (String file) {
	File f = new File (dir, file);

	try {			
	    if (! f.getCanonicalPath ().startsWith (base_canon))
		throw new IllegalArgumentException (file);

	    if (in_cache (f))
		return readbytes (new FileInputStream (f),
				  (int) f.length ());
	    else
		return null;
	} catch (IOException x) {
	    return null;
	}
    }

}

