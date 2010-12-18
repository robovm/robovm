/*
 *  n2h.java -- integers and strings from "network format"...
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


public class n2h {

  /* this stupid routine accounts for 7% of the running time
     of the entire application. What can we do? */

  public static int get1u (byte[] data, int off) {
    return ((int)data[off]) & 0xff;
  }
    
  public static int get1s (byte[] data, int off) {
    return data[off];
  }
    
  public static char get2 (byte[] data, int off) {
    return (char)get2u (data, off);
  }
    
  public static int get2u (byte[] data, int off) {
    return
      ((((int)data[off]) << 8) & 0xff00)
      | (((int)data[off+1])      & 0x00ff);
  }
    
  public static int get2s (byte[] data, int off) {
    return
       (((int)data[off]) << 8) 
      | (((int)data[off+1]) & 0x00ff);
  }
    
  public static int get4 (byte[] data, int off) {
    return
      ((((int)data[off])   << 24  ) & 0xff000000)
      | ((((int)data[off+1]) << 16  ) & 0x00ff0000)
      | ((((int)data[off+2]) << 8   ) & 0x0000ff00)
      | (((int)data[off+3]          ) & 0x000000ff);
  }

  public static long get4u (byte[] data, int off) {
    return
      ((((long)data[off])   << 24  ) & 0xff000000)
      | ((((long)data[off+1]) << 16  ) & 0x00ff0000)
      | ((((long)data[off+2]) << 8   ) & 0x0000ff00)
      | (((long)data[off+3]          ) & 0x000000ff);
  }

  public static long get4s (byte[] data, int off) {
    return
       (((long)data[off])   << 24  ) 
      | ((((long)data[off+1]) << 16  ) & 0x00ff0000)
      | ((((long)data[off+2]) << 8   ) & 0x0000ff00)
      | (((long)data[off+3]          ) & 0x000000ff);
  }

  public static long get8 (byte[] data, int off) {
    return
      ((long)get4 (data,off)) << 32
      | (((long)get4 (data,off+4)) & 0xffffffffL); 
  }

  public static byte[] get_bytes(byte[] data, int off, int len) {
    if (off+len > data.length) throw new IllegalArgumentException ();
    byte[] dest = new byte[len];
    System.arraycopy (data, off, dest, 0, len);
    return dest;
  }

  public static byte[] get_utf8_bytes(byte[] data, int off) {
    int len = get2 (data,off);
    byte[] dest = new byte[len];
    System.arraycopy (data, off+2, dest, 0, len);
    return dest;
  }

  public static String get_utf8_string(byte[] data, int off) {
    int len = get2 (data,off);
    return decode_utf8 (data, off+2, len);
  }

  /**
   *  Decode a Java-style UTF byte sequence.  Doing it ourselves seems
   *  to be *much* faster than calling
   *
   *     new String (data, off, len, "UTF8");
   *
   *  Which does a whole lot of meta-decoding stuff, and it might very
   *  well be implemented in Java anyway.
   */

  private static String decode_utf8 (byte[] data, int off, int len)
  {
    if (off+len > data.length)
      throw new IllegalArgumentException ();

    StringBuffer result = new StringBuffer (len);

    int index = off; 
    int end   = off+len;
    while (index < end)
      {
	int ch0 = get1u (data, index++);
	if (ch0 < 128 && ch0 > 0) 
	  {
	    result.append ((char)ch0);
	    continue;
	  }

	if (index == end)
	  throw new ClassFormatError ("erroneous UTF8 sequence");

	int ch1 = get1u (data, index++);

	if (   (ch0 & 0xE0) == 0xC0 
            && (ch1 & 0xC0) == 0x80)
	  {
	    result.append ((char) (  ((ch0 & 0x1F) << 6) 
				   + ((ch1 & 0x3F))));
	    continue;
	  }

	if (index == end)
	  throw new ClassFormatError ("erroneous UTF8 sequence");

	int ch2 = get1u (data, index++);

	if (   (ch0 & 0xF0) == 0xE0
	    && (ch1 & 0xC0) == 0x80
	    && (ch2 & 0xF0) == 0xE0)
	  {
	    result.append ((char) (  ((ch0 & 0x0F) << 12)
				   + ((ch1 & 0x3F) << 6)
				   + ((ch2 & 0x3F))));
	    continue;
	  }
	
	throw new ClassFormatError ("erroneous UTF8 sequence");
      }

    return result.toString ();
  }






}
