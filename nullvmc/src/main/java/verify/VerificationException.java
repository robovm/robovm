/*
 *  VerificationException.java
 *
 *  Copyright (C) 1999 by Kresten Krab Thorup <krab@daimi.au.dk>
 *
 *  This file is part of "Kresten's Verifier for Java Byte Codes"
 *
 *  This is free software; you can redistribute it and/or modify it under the
 *  terms of the GNU General Public License as published by the Free Software
 *  Foundation; either version 2, or (at your option) any later version.
 * 
 *  It is distributed in the hope that it will be useful, but WITHOUT ANY
 *  WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 *  FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
 *  details.
 *
 */




package verify;

/**
 *  Generic exception to throw, when there is a problem 
 *  discovered in the class file or in the byte codes.
 */

public class VerificationException extends Exception {
  public VerificationException () { super (); }
  public VerificationException (String what) { super (what); }
}
