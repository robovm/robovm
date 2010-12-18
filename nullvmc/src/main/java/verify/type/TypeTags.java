/*
 *  TypeTags.java 
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

public interface TypeTags {

  public static final int T_BOOLEAN = 'Z';
  public static final int T_CHAR    = 'C';
  public static final int T_BYTE    = 'B';
  public static final int T_SHORT   = 'S';
  public static final int T_INT     = 'I';

  public static final int T_LONG    = 'J';
  public static final int T_LONG2   = 'X';

  public static final int T_FLOAT   = 'F';

  public static final int T_DOUBLE  = 'D';
  public static final int T_DOUBLE2 = 'Q';

  public static final int T_VOID    = 'V';

  public static final int T_ARRAY   = '[';
  public static final int T_CLASS   = '#';
  public static final int T_ADDR    = '*';
  public static final int T_NEW     = '@';
  public static final int T_NULL    = '!';


	
  /** storage classes for the checker!  */

  public static final int SC_INT = T_INT;

  public static final int SC_FLOAT = T_FLOAT;

  public static final int SC_DOUBLE = T_DOUBLE;
  public static final int SC_DOUBLE2 = T_DOUBLE2;

  public static final int SC_LONG = T_LONG;
  public static final int SC_LONG2 = T_LONG2;

  public static final int SC_NEW = T_NEW;
  public static final int SC_ADDR = T_ADDR;

}
