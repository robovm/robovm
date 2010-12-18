/*
 *  AbstractStateQueue.java
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



package verify.bytecode;


public final class AbstractStateQueue {

  int count;

  AbstractState head;
  AbstractState tail;

  public AbstractStateQueue () {
    head = null; 
    tail = null; 
    count = 0;
  }  

  boolean contains (AbstractState s1)
  {
    return s1.next != null || head == s1 || tail == s1;
  }
  
  String toString (int pc_offset)
  {
    StringBuffer b = new StringBuffer ("{");
    for (AbstractState s = head;
	 s != null;
	 s = s.next)
      {
	b.append ("=>"+(s.pc()-pc_offset)+" ");
      }
    b.append ("}");
    return b.toString ();
  }

  boolean isEmpty () {
    return count == 0;
  }

  void clean ()
  {
    head = tail = null; count = 0;
  }

  int count () 
  {
    return count;
  }

  public void enqueue (AbstractState s, int pc_offset, boolean log)
    throws verify.VerificationException
  {
    /*if (log) { System.out.println (this.toString (pc_offset)); }*/

    if (contains (s))
      {
	if (log) System.out.println (" ==> "+ (s.pc ()-pc_offset)
				     +" was already there!");
	return ;
      }
    
    if (s == null)
      throw new IllegalArgumentException ("enqueue (null)");
    
    if (count == 0)
      {
	s.next = null;
	head = s;
	tail = s;
	count = 1;

	if (log)
	  {
	    System.out.println ("branch ==> " + (s.pc()-pc_offset)
				+ " (" + s.sp() + ")");
	  }

      }
    else
      {

	if (log)
	  {
	    System.out.println ("branch ==> " + (s.pc()-pc_offset)
				+ " (" + s.sp() + ")");
	  }

	tail.next = s;
	tail = s;
	count += 1;
      }
  }

  public AbstractState dequeue () {

    // simple, robust code...
    if (count == 0)
      {
	return null;
      }
    else if (count == 1)
      {
	AbstractState s = head;
	head = null;
	tail = null;
	count = 0;
	s.next = null;

	if (s == null)
	 throw new InternalError ("hm... 1");

	return s;
      }
    else 
      {
	AbstractState s = head;
	head = s.next;
	s.next = null; 
	count -= 1;

	if (s == null)
	 throw new InternalError ("hm... 2");

	return s;
      }

  }

}
