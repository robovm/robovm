/*
 *  AbstractState.java
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

import verify.type.*;
import verify.classfile.*;

/**
 *  Class AbstractState is the verification-time version of the
 *  runtime state.  It is final mostly for performance reasons.
 *
 *  Let's face it, .. we have many many instances of AbstractState.
 *  However, I tried once with a very clever "copy-on-write" design,
 *  but it turned out that it was not worth the overhead.  So, why?
 *  Well, I guess it's because garbage is so cheap.  If you allocate a
 *  lot of memory, but don't use it, then it's almost free. 
 *
 */


public final class AbstractState
  implements verify.type.TypeTags 
{
  
  /*
    STACK:  state[0..max_stack-1], 
    LOCALS: state[max_stack...max_stack+max_locals]
  */
  private ClassFile.Method context;
  private Type[] storage;
  private AbstractCallStack jsrStack;

  void push_for_jsr (int return_pc)
  {
    for (AbstractCallStack next = jsrStack; next != null; next = next.next)
      {
	if (return_pc == next.return_pc)
	  return;
      }

    jsrStack = new AbstractCallStack (return_pc, jsrStack);
  }

  void pop_for_ret (int return_pc)
    throws verify.VerificationException
  {
    AbstractCallStack next;
    int i = 0;
    do
      {
	next = jsrStack;	
	if (next == null)
	  throw new verify.VerificationException
	    ("jsr/ret callstack mismatch.  A ret operation is "
	     +"performed, for a register which was initialized "
	     +"in a different jsr calling sequence. ");

	i += 1;
	jsrStack = jsrStack.next;
      }
    while (next.return_pc != return_pc);

    if (i > 1)
      System.out.println ("@@@@ interesting, multi level jsr @@@@");
  }

  // used when determining if another state,
  // with the same pc as this, should be merged.

  final boolean hasSameJsrStack (AbstractState other)
  {
    AbstractCallStack s1 = jsrStack;
    AbstractCallStack s2 = other.jsrStack;

    /* the common case */
    if (s1 == s2)
     return true; 

    while (s1 != null && s2 != null)
      {
	if (s1.return_pc == s2.return_pc)
	  {
	    s1 = s1.next;
	    s2 = s2.next;
	  }
	else
	  {
	    return false;
	  }
      }
    
    return (s1 == s2);
  }

  public int max_stack ()
  {
    return context.max_stack;
  }

  public int max_locals ()
  {
    return context.max_locals;
  }

  // we have to remember if we're in the beginning pf
  // a constructur, in which case special ruled apply!
  boolean constructor_start = false;

  /*
    the stack pointer
  */  
  private int sp;

  public int sp ()
  {
    return sp;
  }

  private int pc;

  public int pc ()
  {
    return pc;
  }

  public void set_pc (int pc)
  {
    this.pc = pc;
  }

  public AbstractState clearStack ()
  {
    sp = 0;
    return this;
  }

  public AbstractState (ClassFile.Method method)
  {
    pc       = -1;
    context  = method;
    storage  = new Type[max_stack ()+ max_locals ()];

    // implied:
    // sp                = 0;
    // jsrStack          = null;
    // constructor_start = false;
  }

  /**
   *  make a new abstract state, as a copy of <code>state</code>.
   *  We set the pc to -1, to notify that this state is "free".  When 
   *  the pc is set, that means that the state is "frozen", and must
   *  stay on the state list for that pc.  
   */
  public AbstractState (AbstractState state)
  {
    pc                = -1;
    sp                = state.sp;
    constructor_start = state.constructor_start;
    context           = state.context;
    jsrStack          = state.jsrStack;

    storage = new Type[state.storage.length];
    System.arraycopy (state.storage, 0,
		      storage, 0, state.storage.length);
  }

  /**
   *  Return the state, that an exception handler would see,
   *  if <code>exception</code> was thrown here.
   */
  public AbstractState (AbstractState state, Type exception)
    throws verify.VerificationException
  {
    pc                = -1;
    jsrStack          = state.jsrStack;
    constructor_start = state.constructor_start;
    context           = state.context;

    int max_stack = max_stack ();

    // create new storage, and copy the local variables
    storage = new Type[state.storage.length];
    System.arraycopy (state.storage, max_stack,
		      storage, max_stack, state.storage.length-max_stack);

    // empty the stack! (implied)
    // sp=0;

    // push the exception
    push (exception);
  }

  /**
   *  copy (rather than clone, because it has type Object)
   */
  public AbstractState copy ()
  {
    return new AbstractState (this);
  }

  /*
   *  This yields a hunch, as to weither an exception 
   *  happening in state <code>state</code>, would
   *  cause a merge with <code>this</code> to need
   *  rechecking.  This is called a bazillion times,
   *  with the hope that this can avoid some copying
   *  and merging of states...  It can be assumed, 
   *  that <code>this</code> and <code>state</code>
   *  have equal jsrStack's.
   */
  boolean exceptionMayMerge (AbstractState state)
  {
    Type[] storage       = this.storage;
    Type[] other_storage = state.storage;

    int max = storage.length;
    for (int i = max_stack (); i < max; i++)
      {
	Type this_type  = storage[i];
	Type other_type = other_storage[i];

	if (this_type == other_type)
	  continue;

	if (this_type == null)
	  continue;

	if (other_type == null)
	  return true;

	if (this_type.storageClass == SC_INT 
	    && other_type.storageClass == SC_INT)
	  continue;

	may_merge_true += 1;
	return true;

      }

    may_merge_false += 1;
    return false;
  }

  /* just some stats... to see if this is worthwhile */
  public static long may_merge_true = 0;
  public static long may_merge_false = 0;

  /*
   *  We'll try to merge.  If, however, either of us has an address
   *  somewhere, then we'll just "purify" both states and return
   *  false.  We need the type context for some things...
   */
  public boolean merge (TypeContext ctx, AbstractState other)
    throws verify.VerificationException
  {
    boolean need_recheck = false;

    // just help the compiler a little, it cannot analyze that
    // these do not change in this procedure.  We'll call this
    // quite often, so the performance is crucial.

    Type[] this_storage = this.storage;
    Type[] other_storage = other.storage;
    int max_stack = max_stack ();
    int sp = this.sp;

    if (other_storage.length != storage.length)
      throw new InternalError ("different sized states");

    for (int i = 0; i < storage.length; i++)
      {
	/* in the void */
	if (i >= sp && i < max_stack)
	  continue;

	Type this_type = this_storage[i];

	if (this_type == null)
	  continue;

	Type other_type = other_storage[i];

	if (other_type == this_type)
	  continue;

	if (other_type == null)
	  {
	    this_storage[i] = null;
	    need_recheck = true;
	    continue;
	  }

	Type new_type = this_type.mergeWith (other_type);

	if (new_type != this_type)
	  {
	    this_storage[i] = new_type;
	    need_recheck = true;
	  }
      }

    return need_recheck;
  }

  /**
   *  clone
   */
  public Object clone ()
  {
    return new AbstractState (this);
  }

  /**
   *  "equality"
   */
  public boolean equals (AbstractState other)
    throws verify.VerificationException
  {
    if (other.sp != sp)
      {
	throw new verify.VerificationException 
	  ("Different stack pointers");
      }

    for (int i = 0; i < sp; i++)
      {
	// stacks differ
	if (storage[i] != other.storage[i])
	  return false;
      }

    for (int i = max_stack (); i < storage.length; i++)
      {
	if (storage[i] != other.storage[i])
	  return false;
      }

    return true;
    
  }

  public void push (Type type) 
    throws verify.VerificationException
  {
    if (type == null) 
      throw new InternalError ("pushing null value!");

    if (sp >= max_stack ())
      {
	throw new verify.VerificationException ("stack overflow: push("+type+")");
      }
    (storage) [sp++] = type;
  }

  public void push (Type type1, Type type2) 
    throws verify.VerificationException
  {
    if (sp+1 >= max_stack ())
      {
	throw new verify.VerificationException
	  ("stack overflow: push(" + type1 + ", " + type2 + ")");
      }
    Type[] wstate = storage;

    wstate[sp++] = type1;
    wstate[sp++] = type2;
  }

  /* unchecked pop */
  public Type pop ()
    throws verify.VerificationException
  {
    if (sp < 1)
      {
	throw new verify.VerificationException ("stack underflow: pop()");
      }
    return (storage)[--sp];
  }

  /* pop with check of storage class */
  public Type pop (int storage_class)
    throws verify.VerificationException
  {
    if (sp < 1)
      {
	throw new verify.VerificationException ("stack underflow: pop()");
      }

    Type type = (storage)[--sp];

    if (type.storageClass != storage_class)
      {
	throw new IncompatibleTypesException 
	  ("unexpected value on stack: pop()", type, storage_class);
      }

    return type;
  }

  public void pop (int sc0, int sc1)
    throws verify.VerificationException
  {
    if (sp < 2)
      {
	throw new verify.VerificationException ("stack underflow: pop()");
      }

    Type rstate[] = storage;
    Type type1 = rstate[--sp];
    Type type0 = rstate[--sp];

    if (type1.storageClass != sc1
	|| type0.storageClass != sc0)
      {
	throw new IncompatibleTypesException 
	  ("unexpected value on stack", type0, sc0);
      }
  }

  /* unchecked pop of n stack elements */
  public void popn (int n)
    throws verify.VerificationException
  {
    if (sp<n || n<0)
      {
	throw new verify.VerificationException ("stack underflow: popn("+n+")");
      }
    sp -= n;
  }

  /* checked peek, peek(1) yields top-of-stack. */
  public Type peek (int n, int storage_class)
    throws verify.VerificationException
  {
    if (n < 1 || n > sp)
      {
	throw new verify.VerificationException ("stack underflow: peek("+n+")");
      }

    Type type = (storage)[sp-n];

    if (type.storageClass != storage_class)
      {
	throw new IncompatibleTypesException
	  ("unexpected value on stack: peek("+n+")", type, storage_class);
      }

    return type;
  }

  /* unchecked peek, peek(1) yields top-of-stack. */
  public Type peek (int n)
    throws verify.VerificationException
  {
    if (n < 1 || n > sp)
      {
        throw new verify.VerificationException ("stack underflow: peek("+n+")");
      }

    Type type = (storage)[sp-n];

    return type;
  }
  
  public Type read (int n, int storage_class)
    throws verify.VerificationException
  {
    if (n < 0 || n > max_locals ())
      {
	throw new verify.VerificationException ("out of bounds: read("+n+")");
      }

    Type type = (storage)[max_stack ()+n];

    if (type == null)
      throw new verify.VerificationException
	("reading uninitialized variable, local["+n+"]");

    if (type.storageClass != storage_class)
      {
	throw new IncompatibleTypesException
	  ("unexpected value in variable: read()", type, storage_class);
      }

    return type;
  }


  public void write (int n, Type type)
    throws verify.VerificationException
  {
    if (n < 0 || n > max_locals ())
      {
	throw new verify.VerificationException
	  ("out of bounds: write("+n+", "+type+")");
      }
    (storage)[max_stack ()+n] = type;
  }

  /**
   *   .., class1, class2 -> ..
   */
  public void store (int n, int class1, int class2)
    throws verify.VerificationException
  {
    int max_locals = max_locals ();
    int max_stack  = max_stack ();

    if (n < 0 || n+1 >= max_locals)
      {
	throw new verify.VerificationException ("out of bounds: store/2("+n+")");
      }
    
    if (sp < 2)
      {
	throw new verify.VerificationException ("stack underflow: store/2("+n+")");
      }

    Type[] wstate = storage;
    int index = max_stack+n;

    Type type2 = wstate[--sp]; 
    if (type2.storageClass != class2)
      {
	throw new IncompatibleTypesException 
	  ("bad value on stack: store/2("+n+"/2)", type2, class2);
      }    
    wstate[index+1] = type2; // push

    Type type1 = wstate[--sp]; 
    if (type1.storageClass != class1)
      {
	throw new IncompatibleTypesException 
	  ("bad value on stack: store/2("+n+")", type1, class1);
      }
    wstate[index] = type1;
  }

  /**
   *   .., class1 -> ..
   */
  public void store (int n, int class1)
    throws verify.VerificationException
  {
    int max_locals = max_locals ();
    int max_stack  = max_stack ();

    if (n < 0 || n >= max_locals)
      {
	throw new verify.VerificationException ("out of bounds: store("+n+")");
      }
    
    if (sp < 1)
      {
	throw new verify.VerificationException ("stack underflow: store("+n+")");
      }

    Type[] wstate = storage;
    int index    = max_stack+n; 

    Type type1 = wstate[--sp];	// pop value
    if (type1.storageClass != class1)
      {
	throw new IncompatibleTypesException
	  ("bad value on stack: store("+n+")", type1, class1);
      }

    wstate[index] = type1;	// write 
  }

  /**
   *    ..., -> ..., class1, class2
   */
  public void load (int n, int class1, int class2)
    throws verify.VerificationException
  {
    int max_locals = max_locals ();
    int max_stack  = max_stack ();

    if (n < 0 || n+1 >= max_locals)
      {
	throw new verify.VerificationException ("out of bounds: load/2("+n+")");
      }

    if (sp+2 > max_stack)
      {
	throw new verify.VerificationException ("stack overflow: load/2("+n+")");
      }

    Type[] wstate = storage;
    int index = max_stack+n;

    Type type1 = wstate[index];
    if (type1 == null)
      throw new verify.VerificationException
	("reading uninitialized variable, local["+n+"]");
    if (type1.storageClass != class1)
      {
	throw new IncompatibleTypesException 
	  ("bad value on stack: load/2("+n+")", type1, class1);
      }
    wstate[sp++] = type1;
    
    Type type2 = wstate[index+1];
    if (type1 == null)
      throw new verify.VerificationException
	("reading uninitialized variable, local["+n+"]");
    if (type2.storageClass != class2)
      {
	throw new IncompatibleTypesException 
	  ("bad value on stack: load/2("+n+"/2)", type2, class2);
      }
    wstate[sp++] = type2;    
  }

  
  /**
   *    ..., -> ..., class1
   */
  public void load (int n, int class1)
    throws verify.VerificationException
  {
    /*    System.out.println ("load ("+n+", "
	  +Type.nameForStorageClass (class1)+")");*/

    int max_locals = max_locals ();
    int max_stack  = max_stack ();

    if (n < 0 || n >= max_locals)
      {
	throw new verify.VerificationException ("out of bounds: load("+n+")");
      }

    if (sp+1 > max_stack)
      {
	throw new verify.VerificationException ("stack overflow: load("+n+")");
      }

    Type[] wstate = storage;
    int index = max_stack+n;

    Type type1 = wstate[index];
    if (type1 == null)
      throw new verify.VerificationException
	("reading uninitialized variable, local["+n+"]");

    if (type1.storageClass != class1)
      {
	throw new IncompatibleTypesException 
	  ("bad value on stack: load("+n+")", type1, class1);
      }
    wstate[sp++] = type1;
  }

  void aload (Type elemType, Type extra)
    throws verify.VerificationException
  {
    pop (SC_INT);
    Type array = pop (SC_ADDR);
    Type elem = elemType;

    // null is acceptable in place of an array, in which 
    // case we don't do the following check...
    if (! array.isAnonymousArray ())
      {
	if (array.tag != T_ARRAY)
	  throw new verify.VerificationException
	    ("non-array operand to aaload insn");
	
    
	elem = ((ArrayType)array).elementType;
	
	if (elemType.storageClass == elem.storageClass)
	  /* then we're ok */;
	
	else if (elem != elemType)
	  throw new verify.VerificationException
	    (""+elem+"[] found where "+elemType+"[] was expected");
      }

    if (extra != null)
      push (elem, extra);
    else
      push (elem);

  }

  void astore (Type elemType, int sc2)
    throws verify.VerificationException
  {
    if (sc2 != 0) pop (sc2);
    Type value = pop (elemType.storageClass);
    Type index = pop (SC_INT);
    Type array = pop (SC_ADDR);
    Type elem  = elemType;

    if (! array.isAnonymousArray ())
      {
	if (array.tag != T_ARRAY)
	  throw new verify.VerificationException
	    ("non-array operand to array store insn");
	
	elem = ((ArrayType)array).elementType;
      }
	
    if (elemType.storageClass == SC_ADDR)
      elem.checkAssignmentFrom (value);

    else if (elem != elemType)
      throw new verify.VerificationException
	(""+array+" operand to "+elemType+"[] store operation");
  }

  public void dupx (int n, int x) 
    throws verify.VerificationException
  {
    if (sp+n > max_stack ())
      {
	throw new verify.VerificationException
	  ("stack overflow: dupx("+n+","+x+")");
      }

    if (sp-(n+x) < 0)
      {
	throw new verify.VerificationException
	  ("stack underflow: dupx("+n+","+x+")"); 
      }

    Type[] wstate = storage;

    //    System.out.println ("BEFORE "+this.dump (true));

    // the net result is...
    sp += n;

    // first "slide" n+x elements n to the right
    int top = sp-1;
    for (int i = 0; i < n+x; i++)
      {
	wstate[(top-i)] = wstate[(top-i)-n];
      }

    //    System.out.println ("BETWEEN "+this.dump (true));

    // next, copy the n top elements, n+x down
    for (int i = 0; i < n; i++)
      {
	wstate[top-(n+x)-i] = wstate[top-i];
      }

    //    System.out.println ("AFTER "+this.dump (true));

    
  }

  void swap ()
    throws verify.VerificationException
  {
    Type[] wstate = storage;
    if (sp < 2)
      throw new verify.VerificationException
	("stack underflow in swap() operation");
    Type tmp = wstate[sp-1];
    wstate[sp-1] = wstate[sp-2];
    wstate[sp-2] = tmp;
  }

  void unop (Type type0)
    throws verify.VerificationException
  {
    if (sp < 1)
      throw new verify.VerificationException 
	("stack underflow, missing argument!");

    Type[] wstate = storage;
    
    if (wstate[sp-1].storageClass != type0.storageClass)
      throw new verify.VerificationException
	("erroneous argument "+(wstate[sp-1])+" to " +type0+" op");

    wstate[sp-1] = type0;
  }

  void unop (Type type0, Type type1)
    throws verify.VerificationException
  {
    if (sp < 2)
      throw new verify.VerificationException 
	("stack underflow, missing argument!");
    
    Type[] wstate = storage;
    
    if (   wstate[sp-2].storageClass != type0.storageClass
	   || wstate[sp-1].storageClass != type1.storageClass)
      throw new verify.VerificationException
	("erroneous argument "+(wstate[sp-2])+" to " +type0+" op");

    wstate[sp-2] = type0;
    wstate[sp-1] = type1;
  }

  void binop (Type type0)
    throws verify.VerificationException
  {
    Type[] wstate = storage;
	
    if (sp < 2)
      throw new verify.VerificationException
	("stack underflow in "+type0+" binop");

    if (wstate[sp-1].storageClass != type0.storageClass
	|| wstate[sp-2].storageClass != type0.storageClass)
      {
	throw new verify.VerificationException
	  ("erroneous arguments ("+wstate[sp-2]+", "+wstate[sp-1]
	   +") to "+type0+"-only binop");
      }
    
    sp -= 1;
    wstate[sp-1] = type0;
  }

  void binop (Type type0, Type type1)
    throws verify.VerificationException
  {
    Type[] wstate = storage;
    
    if (sp < 4)
      throw new verify.VerificationException
	("stack underflow in "+type0+" binop");
    
    if (   wstate[sp-1].storageClass != type1.storageClass
	   || wstate[sp-2].storageClass != type0.storageClass
	   || wstate[sp-3].storageClass != type1.storageClass
	   || wstate[sp-4].storageClass != type0.storageClass
	   )
      {
	throw new verify.VerificationException
	  ("erroneous arguments ("+wstate[sp-4]+", "+wstate[sp-3]
	   +") to "+type0+"-only binop");
      }
	
    sp -= 2;
    wstate[sp-2] = type0;
    wstate[sp-1] = type1;

  }


  /*
   *  print state, as "(II#)   [#??X??I#]", in which (...) is the stack,
   *  and [...] is the local variables.  '?' designates uninitialized
   *  local variables.  Other entries print their type tag character.
   */
  public String toString ()
  {
    StringBuffer b = new StringBuffer ("(");

    Type[] rstate = storage;

    if (rstate.length == 0)
      b.append (")[");

    for (int i= 0; i < rstate.length; i++)
      {
	if (i == sp) b.append (')');
	if (i == max_stack ()) b.append ('<');
	if (i >= sp && i < max_stack ())
	  {
	    b.append (' ');
	  }
	else if (rstate[i] == null)
	  {
	    b.append ('?');
	  }
	else 
	  {
	    b.append ((char)rstate[i].tag);
	  }
      }

    b.append ('>');

    // print {A B C} for call sequence 
    if (jsrStack != null)
      b.append ("{"+jsrStack+"}");

    return b.toString ();
  }
  
  public String dump (boolean stack_only)
  {
    StringBuffer b 
      = new StringBuffer ("*** state of "
			  + context.name ()
			  + " *** \n");

    Type[] rstate = storage;

    for (int i= 0; i < rstate.length; i++)
      {
	if (i >= sp && i < max_stack ())
	  {
	    /* ignore */
	  }
	else if (rstate[i] == null)
	  {
	    if (!stack_only)
	      b.append ("local["+(i-max_stack ())+"] = ?\n"); 
	  }
	else 
	  {
	    if (i < max_stack ())
	      b.append ("stack["+i+"] = "+rstate[i]+"\n"); 
	    else if (!stack_only)
	      b.append ("local["+(i-max_stack ())+"] = "+rstate[i]+"\n"); 
	  }
      }

    b.append ("******************************");
    return b.toString ();
  }
  
  ClassType replaceConstructedObject (Type object)
  {
    NewObjectType child = (NewObjectType)object;
    Type[] wstate = storage;
    for (int i = 0; i < wstate.length; i++)
      {
	if (wstate[i] == child)
	  wstate[i] = child.klass;
      }
    return child.klass;
  }

  /* in the verification process, there is a list of states
   * each basic block in the program.  This next-link facilitates 
   * tht list.  These fields are not copied when the object is.
   */

  // the list of unchecked states
  AbstractState next;

  // the list of states with the same pc
  AbstractState next_this_pc;

}

/**
 *  This class models the jsr/ret call stack
 */

class AbstractCallStack {
  final int return_pc;
  final AbstractCallStack next;
  AbstractCallStack (int pc, AbstractCallStack n) {
    return_pc = pc;
    next = n;
  }
  public String toString () {
    if (next == null)
      return Integer.toString (return_pc);
    else
      return next.toString ()+" "+return_pc;
  }
}
