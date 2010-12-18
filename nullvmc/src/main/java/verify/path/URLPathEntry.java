/*
 *  URLPathEntry.java
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

final class URLPathEntry extends PathEntry {
    final URL base;

    URLPathEntry (URL f) {
	base = f;
    }

    public String toString () { return base.toString (); }

    URL getURL (String file) {

	try {
	    URL res = new URL (base, file);
	    InputStream is = res.openStream (); // exc if not found
	    is.close ();
	    return res;
	} catch (java.io.IOException x) {
	    return null;
	}
    }

    InputStream getStream (String file) {

	try {
	    URL res = new URL (base, file);
	    return res.openStream ();
	} catch (java.io.IOException x) {
	    return null;
	}

    }

    byte[] getBytes (String file) {

	try {
	    URL res = new URL (base, file);
	    URLConnection conn = res.openConnection ();
	    int len = conn.getContentLength ();
	    if (len == -1) return null;
	    return readbytes (conn.getInputStream (), len);
	} catch (java.io.IOException x) {
	    return null;
	}

    }

}

