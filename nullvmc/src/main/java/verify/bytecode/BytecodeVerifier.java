/*
 *  BytecodeVerifier.java
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

public class BytecodeVerifier 
  implements InsnNames, TypeTags, ClassFileConstants
{
  public static long instruction_count = 0L;
  public static long instruction_check_count = 0L;

  public BytecodeVerifier (TypeContext context)
  {
    ctx = context;
  }

  TypeContext ctx;
  ClassFile classfile;

  boolean log0 = false;		// banner & stuff
  boolean log1 = false;		// log simple progress
  boolean log2 = false;		// log verbose progress
  boolean log3 = false;		// log excessive info

  boolean stats = true;

  void log (String message)
  {
    System.out.println (message);
  }

  void warn (String message)
  {
    // System.err.print ("\nwarning: "+message);
  }

//  public void verify (ClassFile c, java.util.Hashtable options)
//  {
//    if (options.get ("verbose").equals ("true"))
//      {
//	log0 = true; log1 = true; log2 = true; log3 = true;
//      }

  public void verify (ClassFile c) throws verify.VerificationException {
    classfile = c;
    
    ClassFile.Method[] all = c.methods;

    if (log1) log ("analysing class " + c.getName ());
    
    for (int i = 0; i < all.length; i++)
      {
	ClassFile.Method m = all[i];
	check (m);
      }
  }

  /**
   *  This array registers the "mode" of each byte in the byte code
   *  sequence.  All bytes start off as "unknown", and once assigned
   *  a value, it *must* not change.  At the end of verification,
   *  all bytes that do not have an assigned mode constitutes unreachable
   *  code.  The JVM Specification does not state that unreachable code
   *  is illegal, so we just emit a warning.
   */
  private char[] byte_mode;

  /** mode not yet determined */
  static final int BYTE_MODE_UNKNOWN         = 0;

  /** mode of the first byte in an instruction  */
  static final int BYTE_MODE_INSTRUCTION     = 1;

  /** mode of something in between, may not be executed. */
  static final int BYTE_MODE_ILLEGAL         = 2;

  /** mode of the first byte in a try-block  */
  static final int BYTE_MODE_TRY_INSTRUCTION = 3;

  private int pc_offset, end_pc;

  boolean this_method_has_exceptions;

  // we keep a queue of unchecked states.  
  // elements are extracted at "first_unchecked",
  // and stored in the .next_unchecked link from
  // last_unchecked.
  //
  // get_next_state() & branch() implement these primitives
  //

  private AbstractState[]    pc_states;
  private AbstractStateQueue unchecked ;

  AbstractState get_next_state (int[] pc)
    throws verify.VerificationException
  {
    AbstractState state = unchecked.dequeue ();
    if (state != null)
      {
	/* This is very important to do.  The state to be returned is a copy
	   of a state on the list for the current pc (state.pc), and as such,
	   it should stay intact on that list.  The return value,
	   state.copy(), is to be installed on some other pc's state list.  */

	pc[0] = state.pc();

	if (pc[0] == -1)
	  throw new InternalError ("next state has pc -1");

	state = state.copy ();

	if (state.pc() != -1)
	  throw new InternalError ("copy yields pc != -1");
      }
    return state;
  }

  private AbstractState findApplicableState (AbstractState state,
					     int target_pc)
  {
    int logic_pc = target_pc-pc_offset;
    for (AbstractState s = pc_states[logic_pc];
	 s != null;
	 s = s.next_this_pc)
      {
	if (s.pc() != target_pc)
	  throw new InternalError ("pc mismatch in list for pc="+logic_pc);
	
	if (state.hasSameJsrStack (s))
	  return s;
      }
    return null;
  }

  void branch (AbstractState state, int target_pc)
    throws verify.VerificationException
  {
    if (   target_pc >= end_pc
	|| target_pc < pc_offset)
      throw new verify.VerificationException 
	("control flow reaches outside of the code");

    int logic_pc = target_pc-pc_offset;

    if (state.pc() != -1)
      throw new InternalError ("pc mismatch in abstract state");

    // this is the only call to set_pc in the whole program!
    state.set_pc (target_pc);

    AbstractState s = findApplicableState (state, target_pc);

    if (s != null)
      {
	// merge new state into s
	boolean need_recheck = s.merge (ctx, state);	    
	
	if (!need_recheck)
	  return;
	
	if (! unchecked.contains (s))
	  unchecked.enqueue (s, pc_offset, log3);
	else
	  if (log3) log (" ==> " + logic_pc + "  (already there)");
	return;
      }

    // we did not find an existing state for this (pc,jsr-stack) combination
    // so we must install it.
    state.next_this_pc = pc_states[logic_pc];
    pc_states[logic_pc] = state;

    // and put it on the list of unchecked states as well.
    unchecked.enqueue (state, pc_offset, log3);
  }


  void conditional_branch (AbstractState state, 
			   int target_pc1,
			   int target_pc2)
    throws verify.VerificationException
  {
    if (target_pc1 < target_pc2)
      { 
	branch (state.copy (), target_pc1);
	branch (state, target_pc2);
      }
    else
      {
	branch (state.copy (), target_pc2);
	branch (state, target_pc1);
      }
  }


  /**
   *  This is the principal routine of the verifier.  
 * @throws IllegalTypeNameException 
   */
  void check (ClassFile.Method method) throws verify.VerificationException
  {
//    System.err.print (".");

    ClassType thisType;
    try
      {
	thisType = ctx.classForName (classfile.getName ());

	thisType.loadFrom (new verify.ClassFileInfo (classfile));

      }
    catch (verify.type.IllegalTypeNameException ex)
      {
        throw ex;
//	System.out.println (classfile.getName() +
//			    " is not an allowed class name");
//	return;
      }

    String mname = method.name ();

    if (log1) log ("checking " + thisType + " method " + mname);

    if ((method.access_flags & (ABSTRACT | NATIVE)) != 0)
      {
	return;
      }      

    /**
     *  Find the byte code.
     */

    byte[] code  = method.code;
    if (code == null)
      {
        throw new verify.VerificationException("non-native, non-abstract method "
                + mname 
                + " has no code attribute.");
//	System.err.println ("non-native, non-abstract method "
//			    + mname 
//			    + " has no code attribute.");
//	return;
      }

    // 0+pc_offset is the first instruction
    pc_offset = method.code_off;

    // end_pc is the first value past the end of the code
    end_pc    = pc_offset+method.code_len;
    
    // pc is the "current" pc, it is relative to the start of 
    // the byte array, so the "logical" pc is pc-pc_offset.
    int pc = pc_offset;

    // keeps track of which offset was the last beginning of
    // an instruction.  
    int pc_of_last_opcode = pc;
    
    if(log2) log ("code at ["+pc_offset+".."+end_pc+"]");

    AbstractState state = null;

    final int code_size = end_pc-pc_offset;

  try { // this is the entire thing...

    /**
     *  Next, setup state machinery, for (end_pc-pc_offset) instructions.
     */

    
    pc_states = new AbstractState[code_size]; 
    unchecked = new AbstractStateQueue ();

    byte_mode  = new char[code_size];

    if (pc_states == null
	|| pc_states.length != code_size
	|| byte_mode == null)
      throw new InternalError ("failed to initialize state");


    if (method.exceptions != null)
      {
	this_method_has_exceptions = true;
	for (int i = 0; i < method.exceptions.length; i++)
	  {
	    ClassFile.ExceptionTableEntry entry = method.exceptions[i];
	    if (   entry.start_pc < 0
		|| entry.end_pc < entry.start_pc
		|| entry.end_pc >= code_size
		|| entry.handler_pc < 0
		|| entry.handler_pc >= code_size)
	      {
		throw new verify.VerificationException
		  ("erroneous exception table");
	      }
	    
	    byte_mode[entry.start_pc] = BYTE_MODE_TRY_INSTRUCTION;
	  }
      }
    else
      {
	this_method_has_exceptions = false;
      }

    /**
     *  Establish initial state.  First, store the receiver, "this", if the
     *  method is non-static.  If so, this will offset the rest of
     *  the arguments by one.
     */

    int arg_offset = 0;
    state = new AbstractState (method);

    if ((method.access_flags & STATIC) == 0)
      {
	// non-static methods receive their arguments 
	// in positions 1..n+1
	arg_offset = 1;

	// a constructor has a NewObject on it's stack.  We say
	// except for the root class, so that it may use the
	// value of "this" if needed...  Currently, 
	if (mname.equals ("<init>") && classfile.super_class != 0)
	  {
	    // store unititialized object in local[0]
	    state.write (0, ctx.newForClass (thisType));
	    state.constructor_start = true;
	  }
	// 
	else
	  {
	    // store this in local[0]
	    state.write (0, thisType);
	  }
      }

    /**
     *   Next, store the types of arguments.
     */
    Signature sig = Signature.forString (ctx, method.signature ());
    int numberArgs = sig.numberArgs ();

    if (log3)
      {
	log ("signature is " + sig);
      }

    for (int i = 0; i < numberArgs; i++)
      {
	Type t = sig.declaredArgument (i);

	if (t.tag == T_LONG)
	  {
	    state.write (arg_offset+i, ctx.LONG); arg_offset += 1;
	    state.write (arg_offset+i, ctx.LONG2);
	  }
	else if (t.tag == T_DOUBLE)
	  {
	    state.write (arg_offset+i, ctx.DOUBLE); arg_offset += 1;
	    state.write (arg_offset+i, ctx.DOUBLE2);
	  }
	else
	  {
	    state.write (arg_offset + i, t);
	  }
      }
    /* we keep the signature around so that we can check the return type */

    // and say that we have a state ready for pc=0
    branch (state, pc_offset);

    int insn_count = 0;
    int[] pc_getter = new int[1];
    
  next_basic_block:
    while (true)
      {

	// get new state...
	if ((state = get_next_state (pc_getter)) == null)
	  {
	    break next_basic_block;
	  }

	// ready to go...
	pc                = pc_getter[0];
	pc_of_last_opcode = pc;

	if (log3) log ("--["+(pc-pc_offset)+"]----------");

	// if (log3) { log (state.dump (false)); }
	// inject_exceptions (method, state, pc_of_last_opcode);

      next_insn:
	while (true) 
	  {
	    /* first, some auxillary stuff.. */

	    instruction_check_count += 1;
	    ++insn_count;
//	    if ((++insn_count % 1000) == 0)
//	      System.err.print (":");

	    /* This checks that we are not executing code out-of-bounds */

	    if (pc >= end_pc || pc < pc_offset)
	      throw new verify.VerificationException
		("pc=" + (pc-pc_offset) + " is out of bounds");

	    /* This checks to see if we are executing an instruction, which
	       is at a previously discovered branch-target.  That is, this
	       instruction has previously been checked, but as the first
	       instruction in a basic block.  If so, we induce a branch to
	       this instruction, and exit the instruction loop.  This will
	       cause the states to be merged, but often this merge is
	       successfull, so we avoid checking the rest of the code
	       sequence.  Over all, this reduces the number of cycles in the
	       inner loop by 5%.  */

	    if (pc != pc_of_last_opcode)
	      {
		if (pc_states[pc-pc_offset] != null)
		  {
		    branch (state, pc);
		    break next_insn;
		  }
	      }
	    
	    /* First, we mark every byte since the last executed opcode as
	       "illegal", meaning that it is part of the body of the last
	       instruction.  If we find an instruction in this sequence, that
	       means that we must previously have execured code here, (in the
	       middle of the instruction), so someone is at fault!  We use a
	       cascading if-else, in decresing order of likelyhood.  On
	       average (checking the full JDK), only 1% of all instructions
	       are checked more than once.  */

	    for (int i = pc_of_last_opcode+1; i < pc; i++)
	      {
		int mode = byte_mode[i-pc_offset];
		if (mode == BYTE_MODE_UNKNOWN)
		  {
		    byte_mode[i-pc_offset] = BYTE_MODE_ILLEGAL;
		  }
		else if (mode == BYTE_MODE_ILLEGAL)
		  {
		    continue;
		  }
		else if (mode == BYTE_MODE_INSTRUCTION
			 || mode == BYTE_MODE_TRY_INSTRUCTION)
		  {
		    throw new verify.VerificationException
		      ("executed code inside an instruction");
		  }
		else
		  {
		    throw new InternalError ("illegal byte mode");
		  }
	      }

	    /* and now, we check the mode of the current instruction */
	    {
	      int mode = byte_mode[pc-pc_offset];

	      if (mode == BYTE_MODE_UNKNOWN)
		{
		  byte_mode[pc-pc_offset] = BYTE_MODE_INSTRUCTION;
		}
	      else if (mode == BYTE_MODE_INSTRUCTION)
		{
		  /* ignore */
		}
	      else if (mode == BYTE_MODE_TRY_INSTRUCTION)
		{
		  inject_exceptions (method, state, pc);
		}
	      else if (mode == BYTE_MODE_ILLEGAL)
		{
		  throw new verify.VerificationException
		    ("executing code in the middle of an instruction!");
		}
	      else
		{
		  throw new InternalError ("illegal byte mode");
		}
	    }

	    pc_of_last_opcode    = pc;

	    int opcode = n2h.get1u (code, pc++);

	    if (log3)
	      {
		log (state.toString()+" mode="
		     + ((int)byte_mode[pc-1-pc_offset])
		     + " pc="+(pc-1-pc_offset)+" "+insn_name[opcode]);
	      }
	    
	    switch (opcode)
	      {
	      case op_nop:				/* 0x00 */
		  continue next_insn;

	      case op_aconst_null:			/* 0x01 */
		  state.push (ctx.NULL); 
		  continue next_insn;
		  
	      case op_iconst_m1:			/* 0x02 */
	      case op_iconst_0:				/* 0x03 */
	      case op_iconst_1:				/* 0x04 */
	      case op_iconst_2:				/* 0x05 */
	      case op_iconst_3:				/* 0x06 */
	      case op_iconst_4:				/* 0x07 */
	      case op_iconst_5:				/* 0x08 */
		  state.push (ctx.INT);
		  continue next_insn;

	      case op_lconst_0:				/* 0x09 */
	      case op_lconst_1:				/* 0x0a */
		  state.push (ctx.LONG, ctx.LONG2);
		  continue next_insn;
		  
	      case op_fconst_0:				/* 0x0b */
	      case op_fconst_1:				/* 0x0c */
	      case op_fconst_2:				/* 0x0d */
		  state.push (ctx.FLOAT);
		  continue next_insn;

	      case op_dconst_0:				/* 0x0e */
	      case op_dconst_1:				/* 0x0f */
		  state.push (ctx.DOUBLE, ctx.DOUBLE2);
		  continue next_insn;

	      case op_sipush:				/* 0x11 */
		  pc++;

	      case op_bipush:				/* 0x10 */
		pc++;
		state.push (ctx.INT);
		continue next_insn;

	      case op_ldc:				/* 0x12 */
		{
		  int index = n2h.get1u (code, pc++);
		  Type t = getConstantType (index);

		  if (   t != ctx.FLOAT  && t != ctx.INT 
		      && t != ctx.STRING && t != ctx.CLASS)
		    {
		      throw new verify.type.IncompatibleTypesException
			("erroneous operand, "+t+", for ldc instruction");
		    }

		  state.push (t);
		}
		continue next_insn;

	      case op_ldc_w:				/* 0x13 */
		{
		  int index = n2h.get2u (code, pc); pc += 2;
		  Type t = getConstantType (index);

		  if (t != ctx.FLOAT && t != ctx.INT && t != ctx.STRING && t != ctx.CLASS)
		    {
		      throw new verify.type.IncompatibleTypesException
			("erroneous operand, "+t+", for ldc_w instruction");
		    }

		  state.push (t);
		  
		}
		continue next_insn;
		
	      case op_ldc2_w:				/* 0x14 */
		{
		  int index = n2h.get2u (code, pc); pc += 2;
		  Type t = getConstantType (index);
		  if (t == ctx.DOUBLE)
		    {
		      state.push (ctx.DOUBLE, ctx.DOUBLE2);
		    }
		  else if (t == ctx.LONG)
		    {
		      state.push (ctx.LONG, ctx.LONG2);
		    }
		  else
		    {
		      throw new verify.type.IncompatibleTypesException
			("erroneous operand, "+t+", for ldc2_w instruction");
		    }
		}
		continue next_insn;


	      case op_iload:				/* 0x15 */
		state.load (n2h.get1u (code, pc++), SC_INT);
		continue next_insn;

	      case op_lload:				/* 0x16 */
		state.load (n2h.get1u (code, pc++), SC_LONG, SC_LONG2);
		continue next_insn;

	      case op_fload:				/* 0x17 */
		state.load (n2h.get1u (code, pc++), SC_FLOAT);
		continue next_insn;

	      case op_dload:				/* 0x18 */
		state.load (n2h.get1u (code, pc++), SC_DOUBLE, SC_DOUBLE2);
		continue next_insn;

	      case op_aload:				/* 0x19 */
		{
		  int index = n2h.get1u (code, pc++);
		  if (index == 0 && state.constructor_start)
		    {
		      state.load (0, SC_NEW);
		    }
		  else
		    {
		      state.load (index, SC_ADDR);
		      if (log3) log("aload("+index+") = "+state.peek (1, SC_ADDR));
		    }
		}
		continue next_insn;
		
	      case op_iload_0:				/* 0x1a */
	      case op_iload_1:				/* 0x1b */
	      case op_iload_2:				/* 0x1c */
	      case op_iload_3:				/* 0x1d */
		state.load (opcode-op_iload_0, SC_INT);
		continue next_insn;

	      case op_lload_0:				/* 0x1e */
	      case op_lload_1:				/* 0x1f */
	      case op_lload_2:				/* 0x20 */
	      case op_lload_3:				/* 0x21 */
		state.load (opcode-op_lload_0, SC_LONG, SC_LONG2);
		continue next_insn;

	      case op_fload_0:				/* 0x22 */
	      case op_fload_1:				/* 0x23 */
	      case op_fload_2:				/* 0x24 */
	      case op_fload_3:				/* 0x25 */
		state.load (opcode-op_fload_0, SC_FLOAT);
		continue next_insn;

	      case op_dload_0:				/* 0x26 */
	      case op_dload_1:				/* 0x27 */
	      case op_dload_2:				/* 0x28 */
	      case op_dload_3:				/* 0x29 */
		state.load (opcode-op_dload_0, SC_DOUBLE, SC_DOUBLE2);
		continue next_insn;

	      case op_aload_0:				/* 0x2a */
		if (state.constructor_start)
		  {
		    state.load (0, SC_NEW);
		  }
		else
		  {
		    state.load (0, SC_ADDR);
		  }
		continue next_insn;

	      case op_aload_1:				/* 0x2b */
	      case op_aload_2:				/* 0x2c */
	      case op_aload_3:				/* 0x2d */
		state.load (opcode-op_aload_0, SC_ADDR);
		continue next_insn;

	      case op_iaload:				/* 0x2e */
		state.aload (ctx.INT, null);
		continue next_insn;

	      case op_laload:				/* 0x2f */
		state.aload (ctx.LONG, ctx.LONG2);
		continue next_insn;

	      case op_faload:				/* 0x30 */
		state.aload (ctx.FLOAT, null);
		continue next_insn;

	      case op_daload:				/* 0x31 */
		state.aload (ctx.DOUBLE, ctx.DOUBLE2);
		continue next_insn;

	      case op_aaload:				/* 0x32 */
		{
		  state.pop (SC_INT);
		  Type array = state.pop (SC_ADDR);
		  if (array.tag != T_ARRAY 
		      && array.tag != T_NULL)
		    throw new verify.VerificationException
		      ("non-array operand to aaload insn");
		  state.push (ctx.NULL);
		}

		continue next_insn;

	      case op_baload:				/* 0x33 */
		{
		  Type index = state.pop (SC_INT);
		  Type array = state.pop (SC_ADDR);
		  Type elem;
		  
		  if (! array.isAnonymousArray ())
		    {
		      if (array.tag != T_ARRAY)
			throw new verify.VerificationException
			  (""+array+" operand to baload operation");
		      
		      elem = ((ArrayType)array).elementType;
		  
		      if (elem.tag == T_BYTE || elem.tag == T_BOOLEAN)
			{
			  state.push (elem);
			}
		      else
			{
			  throw new verify.VerificationException
			    (""+array+" operand to baload operation");
			}
		    }
		  else
		    {
		      // we have to choose something, ..
		      // but we don't know what kind of array
		      // it is here...
		      state.push (ctx.INT);
		    }
		}
		continue next_insn;

	      case op_caload:				/* 0x34 */
		state.aload (ctx.CHAR, null);
		continue next_insn;

	      case op_saload:				/* 0x35 */
		state.aload (ctx.SHORT, null);
		continue next_insn;

	      case op_istore:				/* 0x36 */
		state.store (n2h.get1u (code, pc++), SC_INT);
		inject_exceptions (method, state, pc);
		continue next_insn;

	      case op_lstore:				/* 0x37 */
		state.store (n2h.get1u (code, pc++), SC_LONG, SC_LONG2);
		inject_exceptions (method, state, pc);
		continue next_insn;

	      case op_fstore:				/* 0x38 */
		state.store (n2h.get1u (code, pc++), SC_FLOAT);
		inject_exceptions (method, state, pc);
		continue next_insn;

	      case op_dstore:				/* 0x39 */
		state.store (n2h.get1u (code, pc++), SC_DOUBLE, SC_DOUBLE2);
		inject_exceptions (method, state, pc);
		continue next_insn;

	      case op_astore:				/* 0x3a */
		state.store (n2h.get1u (code, pc++), SC_ADDR);
		inject_exceptions (method, state, pc);
		continue next_insn;
		
	      case op_istore_0:				/* 0x3b */
	      case op_istore_1:				/* 0x3c */
	      case op_istore_2:				/* 0x3d */
	      case op_istore_3:				/* 0x3e */
		state.store (opcode-op_istore_0, SC_INT);
		inject_exceptions (method, state, pc);
		continue next_insn;

	      case op_lstore_0:				/* 0x3f */
	      case op_lstore_1:				/* 0x40 */
	      case op_lstore_2:				/* 0x41 */
	      case op_lstore_3:				/* 0x42 */
		state.store (opcode-op_lstore_0, SC_LONG, SC_LONG2);
		inject_exceptions (method, state, pc);
		continue next_insn;

	      case op_fstore_0:				/* 0x43 */
	      case op_fstore_1:				/* 0x44 */
	      case op_fstore_2:				/* 0x45 */
	      case op_fstore_3:				/* 0x46 */
		state.store (opcode-op_fstore_0, SC_FLOAT);
		inject_exceptions (method, state, pc);
		continue next_insn;

	      case op_dstore_0:				/* 0x47 */
	      case op_dstore_1:				/* 0x48 */
	      case op_dstore_2:				/* 0x49 */
	      case op_dstore_3:				/* 0x4a */
		state.store (opcode-op_dstore_0, SC_DOUBLE, SC_DOUBLE2);
		inject_exceptions (method, state, pc);
		continue next_insn;

	      case op_astore_0:				/* 0x4b */
	      case op_astore_1:				/* 0x4c */
	      case op_astore_2:				/* 0x4d */
	      case op_astore_3:				/* 0x4e */
		state.store (opcode-op_astore_0, SC_ADDR);
		inject_exceptions (method, state, pc);
		continue next_insn;

	      case op_iastore:				/* 0x4f */
		state.astore (ctx.INT, 0);
		continue next_insn;

	      case op_lastore:				/* 0x50 */
		state.astore (ctx.LONG, SC_LONG2);
		continue next_insn;
	      
	      case op_fastore:				/* 0x51 */
		state.astore (ctx.FLOAT, 0);
		continue next_insn;

	      case op_dastore:				/* 0x52 */
		state.astore (ctx.DOUBLE, SC_DOUBLE2);
		continue next_insn;

	      case op_aastore:				/* 0x53 */
		if (log3) { log (state.dump (false)); }
		state.astore (ctx.NULL, 0);
		continue next_insn;

	      case op_bastore:				/* 0x54 */
		{
		  Type value = state.pop (SC_INT);
		  Type index = state.pop (SC_INT);
		  Type array = state.pop (SC_ADDR);

		  if (! array.isAnonymousArray ())
		    {
		      if (array.tag != T_ARRAY)
			throw new verify.VerificationException
			  ("non-array operand to array store insn");

		      Type type = ((ArrayType)array).elementType;
		      if (type != ctx.BYTE && type != ctx.BOOLEAN)
			throw new verify.VerificationException
			  (""+array+" operand to "
			   +"(byte|boolean)"
			   +"[] store operation");
		    }
		}
		continue next_insn;

	      case op_castore:				/* 0x55 */
		state.astore (ctx.CHAR, 0);
		continue next_insn;

	      case op_sastore:				/* 0x56 */
		state.astore (ctx.SHORT, 0);
		continue next_insn;

	      case op_pop:				/* 0x57 */
                {
                  Type t = state.peek (1);
                  if (t == ctx.LONG2 || t == ctx.DOUBLE2)
                    {
                      throw new verify.type.IncompatibleTypesException
                        ("cannot split long or double on the stack");
                    }
                  state.popn (1);
                  continue next_insn;
                }

              case op_pop2:                             /* 0x58 */
              {
                Type t = state.peek (2);
                if (t == ctx.LONG2 || t == ctx.DOUBLE2)
                  {
                    throw new verify.type.IncompatibleTypesException
                      ("cannot split long or double on the stack");
                  }
                state.popn (2);
                continue next_insn;
              }
		
              case op_dup:                              /* 0x59 */
              {
                Type t = state.peek (1);
                if (t == ctx.LONG2 || t == ctx.DOUBLE2)
                  {
                    throw new verify.type.IncompatibleTypesException
                      ("cannot split long or double on the stack");
                  }
                state.dupx (1,0);
                continue next_insn;
              }

            case op_dup_x1:                           /* 0x5a */
              {
                Type t1 = state.peek (1);
                Type t2 = state.peek (2);
                if (t1 == ctx.LONG2 || t1 == ctx.DOUBLE2 || t2 == ctx.LONG2 || t2 == ctx.DOUBLE2)
                  {
                    throw new verify.type.IncompatibleTypesException
                      ("cannot split long or double on the stack");
                  }
                state.dupx (1,1);
                continue next_insn;
              }

            case op_dup_x2:                           /* 0x5b */
              {
                Type t1 = state.peek (1);
                Type t3 = state.peek (3);
                if (t1 == ctx.LONG2 || t1 == ctx.DOUBLE2 || t3 == ctx.LONG2 || t3 == ctx.DOUBLE2)
                  {
                    throw new verify.type.IncompatibleTypesException
                      ("cannot split long or double on the stack");
                  }
                state.dupx (1,2);
                continue next_insn;
              }

            case op_dup2:                             /* 0x5c */
              {
                Type t = state.peek (2);
                if (t == ctx.LONG2 || t == ctx.DOUBLE2)
                  {
                    throw new verify.type.IncompatibleTypesException
                      ("cannot split long or double on the stack");
                  }
                state.dupx (2,0);
                continue next_insn;
              }

            case op_dup2_x1:                          /* 0x5d */
              {
                Type t2 = state.peek (2);
                Type t3 = state.peek (3);
                if (t2 == ctx.LONG2 || t2 == ctx.DOUBLE2 || t3 == ctx.LONG2 || t3 == ctx.DOUBLE2)
                  {
                    throw new verify.type.IncompatibleTypesException
                      ("cannot split long or double on the stack");
                  }
                state.dupx (2,1);
                continue next_insn;
              }

            case op_dup2_x2:                          /* 0x5e */
              {
                Type t2 = state.peek (2);
                Type t4 = state.peek (4);
                if (t2 == ctx.LONG2 || t2 == ctx.DOUBLE2 || t4 == ctx.LONG2 || t4 == ctx.DOUBLE2)
                  {
                    throw new verify.type.IncompatibleTypesException
                      ("cannot split long or double on the stack");
                  }
                state.dupx (2,2);
                continue next_insn;
              }

            case op_swap:                             /* 0x5f */
            {
              Type t1 = state.peek (1);
              Type t2 = state.peek (2);
              if (t1 == ctx.LONG2 || t1 == ctx.DOUBLE2 || t2 == ctx.LONG2 || t2 == ctx.DOUBLE2)
                {
                  throw new verify.type.IncompatibleTypesException
                    ("cannot split long or double on the stack");
                }
              state.swap ();
              continue next_insn;
            }

	      case op_iadd:				/* 0x60 */
	      case op_isub:				/* 0x64 */
	      case op_imul:				/* 0x68 */
	      case op_idiv:				/* 0x6c */
	      case op_irem:				/* 0x70 */
	      case op_iand:				/* 0x7e */
	      case op_ior:				/* 0x80 */
	      case op_ixor:				/* 0x82 */
		state.binop (ctx.INT);
		continue next_insn;

	      case op_ladd:				/* 0x61 */
	      case op_lsub:				/* 0x65 */
	      case op_lmul:				/* 0x69 */
	      case op_ldiv:				/* 0x6d */
	      case op_lrem:				/* 0x71 */
	      case op_land:				/* 0x7f */
	      case op_lor:				/* 0x81 */
	      case op_lxor:				/* 0x83 */
		state.binop (ctx.LONG, ctx.LONG2);
		continue next_insn;

	      case op_fadd:				/* 0x62 */
	      case op_fsub:				/* 0x66 */
	      case op_fmul:				/* 0x6a */
	      case op_fdiv:				/* 0x6e */
	      case op_frem:				/* 0x72 */
		state.binop (ctx.FLOAT);
		continue next_insn;

	      case op_dadd:				/* 0x63 */
	      case op_dsub:				/* 0x67 */
	      case op_dmul:				/* 0x6b */
	      case op_ddiv:				/* 0x6f */
	      case op_drem:				/* 0x73 */
		state.binop (ctx.DOUBLE, ctx.DOUBLE2);
		continue next_insn;

	      case op_ineg:				/* 0x74 */
		state.unop (ctx.INT);
		continue next_insn;
		
	      case op_lneg:				/* 0x75 */
		state.unop (ctx.LONG, ctx.LONG2);
		continue next_insn;

	      case op_fneg:				/* 0x76 */
		state.unop (ctx.FLOAT);
		continue next_insn;

	      case op_dneg:				/* 0x77 */
		state.unop (ctx.DOUBLE, ctx.DOUBLE2);
		continue next_insn;

	      case op_ishl:				/* 0x78 */
	      case op_ishr:				/* 0x7a */
	      case op_iushr:				/* 0x7c */
		state.pop (SC_INT);
		state.unop (ctx.INT);
		continue next_insn;
		
	      case op_lshl: /* ..LI -> ..L */		/* 0x79 */
	      case op_lshr:				/* 0x7b */
	      case op_lushr:				/* 0x7d */
		state.pop (SC_INT);
		state.unop (ctx.LONG, ctx.LONG2);
		continue next_insn;

	      case op_iinc:				/* 0x84 */
		state.read (n2h.get1u (code, pc++), SC_INT);
		pc++; /* skip increment amount */
		continue next_insn;

	      case op_i2l:				/* 0x85 */
		state.pop (SC_INT);
		state.push (ctx.LONG, ctx.LONG2);
		continue next_insn;
		
	      case op_i2f:				/* 0x86 */
		state.pop (SC_INT);
		state.push (ctx.FLOAT);
		continue next_insn;

	      case op_i2d:				/* 0x87 */
		state.pop (SC_INT);
		state.push (ctx.DOUBLE, ctx.DOUBLE2);
		continue next_insn;

	      case op_l2i:				/* 0x88 */
		state.pop (SC_LONG, SC_LONG2);
		state.push (ctx.INT);
		continue next_insn;

	      case op_l2f:				/* 0x89 */
		state.pop (SC_LONG, SC_LONG2);
		state.push (ctx.FLOAT);
		continue next_insn;

	      case op_l2d:				/* 0x8a */
		state.pop (SC_LONG, SC_LONG2);
		state.push (ctx.DOUBLE, ctx.DOUBLE2);
		continue next_insn;

	      case op_f2i:				/* 0x8b */
		state.pop (SC_FLOAT);
		state.push (ctx.INT);
		continue next_insn;

	      case op_f2l:				/* 0x8c */
		state.pop (SC_FLOAT);
		state.push (ctx.LONG, ctx.LONG2);
		continue next_insn;

	      case op_f2d:				/* 0x8d */
		state.pop (SC_FLOAT);
		state.push (ctx.DOUBLE, ctx.DOUBLE2);
		continue next_insn;

	      case op_d2i:				/* 0x8e */
		state.pop (SC_DOUBLE, SC_DOUBLE2);
		state.push (ctx.INT);
		continue next_insn;

	      case op_d2l:				/* 0x8f */
		state.pop (SC_DOUBLE, SC_DOUBLE2);
		state.push (ctx.LONG, ctx.LONG2);
		continue next_insn;

	      case op_d2f:				/* 0x90 */
		state.pop (SC_DOUBLE, SC_DOUBLE2);
		state.push (ctx.FLOAT);
		continue next_insn;

	      case op_i2b:				/* 0x91 */
		state.pop (SC_INT);
		state.push (ctx.BYTE);
		continue next_insn;

	      case op_i2c:				/* 0x92 */
		state.pop (SC_INT);
		state.push (ctx.CHAR);
		continue next_insn;

	      case op_i2s:				/* 0x93 */
		state.pop (SC_INT);
		state.push (ctx.SHORT);
		continue next_insn;

	      case op_lcmp:				/* 0x94 */
		state.pop (SC_LONG, SC_LONG2);
		state.pop (SC_LONG, SC_LONG2);
		state.push (ctx.INT);
		continue next_insn;

	      case op_fcmpl:				/* 0x95 */
	      case op_fcmpg:				/* 0x96 */
		state.pop (SC_FLOAT, SC_FLOAT);
		state.push (ctx.INT);
		continue next_insn;

	      case op_dcmpl:				/* 0x97 */
	      case op_dcmpg:				/* 0x98 */
		state.pop (SC_DOUBLE, SC_DOUBLE2);
		state.pop (SC_DOUBLE, SC_DOUBLE2);
		state.push (ctx.INT);
		continue next_insn;

	      case op_ifeq:				/* 0x99 */
	      case op_ifne:				/* 0x9a */
	      case op_iflt:				/* 0x9b */
	      case op_ifge:				/* 0x9c */
	      case op_ifgt:				/* 0x9d */
	      case op_ifle:				/* 0x9e */
		{
		  int base_offset = pc-1;
		  state.pop (SC_INT);
		  int offset = n2h.get2s (code, pc); pc += 2;
		  conditional_branch (state, pc, base_offset+offset);
		}
		break next_insn;

	      case op_if_icmpeq:			/* 0x9f */
	      case op_if_icmpne:			/* 0xa0 */
	      case op_if_icmplt:			/* 0xa1 */
	      case op_if_icmpge:			/* 0xa2 */
	      case op_if_icmpgt:			/* 0xa3 */
	      case op_if_icmple:			/* 0xa4 */
		{
		  int base_offset = pc-1;
		  state.pop (SC_INT, SC_INT);
		  int offset = n2h.get2s (code, pc); pc += 2;
		  conditional_branch (state, pc, base_offset+offset);
		}
		break next_insn;

	      case op_if_acmpeq:			/* 0xa5 */
	      case op_if_acmpne:			/* 0xa6 */
		{
		  int base_offset = pc-1;
		  state.pop (SC_ADDR, SC_ADDR);
		  int offset = n2h.get2s (code, pc); pc += 2;
		  conditional_branch (state, pc, base_offset+offset);
		}
		break next_insn;

	      case op_goto:				/* 0xa7 */ 
		{
		  int offset = n2h.get2s (code, pc); pc += 2; 
		  int target_pc = pc - 3 + offset;
		  branch (state, target_pc);		
		}
		break next_insn;

	      case op_jsr:				/* 0xa8 */
		{
		  int offset = n2h.get2s (code, pc); pc += 2;
		  int target_pc = pc - 3 + offset;
		  state.push (ctx.forAddress (pc - pc_offset));
		  state.push_for_jsr (pc - pc_offset);
		  branch (state, target_pc);		
		}
		break next_insn;

	      case op_jsr_w:				/* 0xc9 */
		{
		  int offset = n2h.get4 (code, pc);  pc += 4;
		  int target_pc = pc - 5 + offset;
		  state.push (ctx.forAddress (pc - pc_offset));	
		  state.push_for_jsr (pc - pc_offset);
		  branch (state, target_pc);		
		}
		break next_insn;

	      case op_ret:				/* 0xa9 */
		{
		  int  index  = n2h.get1u (code, pc++);
		  Type addr   = state.read (index, SC_ADDR);
		  if (addr.tag != T_ADDR)
		    throw new verify.VerificationException
		      ("ret ["+index+"] does not specify a return address");
		  state.write (index, null); // un-initialize the return addr
		  AddressType atype = (AddressType)addr;
		  state.pop_for_ret (atype.pc());
		  branch (state, atype.pc() + pc_offset);
		}
		break next_insn;

	      case op_tableswitch:			/* 0xaa */
		{
		  int basepc = pc-1;
		  state.pop (SC_INT);
		  while (((pc-pc_offset) % 4) != 0) 
		    {
		      if (code[pc] != 0)
			warn ("padding byte '0x"
			      +Integer.toHexString(code[pc-1])
			      +"' is non-zero");
		      pc++;
		    }
		  int default_val = n2h.get4 (code, pc); pc += 4;
		  int low     = n2h.get4 (code, pc); pc += 4;
		  int high    = n2h.get4 (code, pc); pc += 4;
		  branch (state.copy (), default_val+basepc);
		  for (int i = 0; i < (high-low+1); i++)
		    {
		      int this_target = n2h.get4 (code, pc); pc += 4;
		      branch (state.copy (), this_target+basepc);
		    }
		}
		break next_insn;

	      case op_lookupswitch:			/* 0xab */
		{
		  int basepc = pc-1;
		  state.pop (SC_INT);
		  while (((pc-pc_offset) % 4) != 0) 
		    {
		      if (code[pc] != 0)
			  warn ("padding byte '0x"
				+Integer.toHexString(code[pc-1])
				+"' is non-zero");
		      pc++;
		    }

		  int default_val = n2h.get4 (code, pc); pc += 4;
		  int npairs  = n2h.get4 (code, pc); pc += 4;
		  branch (state.copy (), default_val+basepc);
		  while (npairs-- > 0)
		    {
		      int this_value  = n2h.get4 (code, pc); pc += 4;
		      int this_target = n2h.get4 (code, pc); pc += 4;
		      branch (state.copy (),
			      this_target+basepc);
		    }
		}
		break next_insn;

	      case op_ireturn:				/* 0xac */
		state.pop (SC_INT);
		if (sig.returnType ().storageClass != SC_INT)
		  throw new verify.VerificationException
		    ("erroneous return type");
		break next_insn;

	      case op_lreturn:				/* 0xad */
		state.pop (SC_LONG, SC_LONG2);
		if (sig.returnType () != ctx.LONG)
		  throw new verify.VerificationException
		    ("erroneous return type");
		break next_insn;

	      case op_freturn:				/* 0xae */
		state.pop (SC_FLOAT);
		if (sig.returnType () != ctx.FLOAT)
		  throw new verify.VerificationException
		    ("erroneous return type");
		break next_insn;

	      case op_dreturn:				/* 0xaf */
		state.pop (SC_DOUBLE, SC_DOUBLE2);
		if (sig.returnType () != ctx.DOUBLE)
		  throw new verify.VerificationException
		    ("erroneous return type");
		break next_insn;

	      case op_areturn:				/* 0xb0 */
		{
		  Type ret = state.pop (SC_ADDR);
		  sig.returnType ().checkAssignmentFrom (ret);
		}
		break next_insn;

	      case op_return:				/* 0xb1 */
		if (sig.returnType () != ctx.VOID)
		  throw new verify.VerificationException
		    ("erroneous return type");
		break next_insn;

	      case op_putstatic:			/* 0xb3 */
		{
		  int index = n2h.get2u (code, pc); pc += 2;
		  Type type = getFieldType (index);
		  if (type.tag == T_DOUBLE)
		    {
		      state.pop (SC_DOUBLE, SC_DOUBLE2);
		    }
		  else if (type.tag == T_LONG)
		    {
		      state.pop (SC_LONG, SC_LONG2);
		    }
		  else
		    {
		      Type value = state.pop (type.storageClass);
		      type.checkAssignmentFrom (value);
		    }
		}
		continue next_insn;

	      case op_getstatic:			/* 0xb2 */
		{
		  int index             = n2h.get2u (code, pc); pc += 2;
		  Type ftype            = getFieldType (index);

		  /*
		    FIXME: here we could check a lot more,
		    if we have the information available.
		  */

		  if (ftype.tag == T_DOUBLE)
		    {
		      state.push (ctx.DOUBLE, ctx.DOUBLE2);
		    }
		  else if (ftype.tag == T_LONG)
		    {
		      state.push (ctx.LONG, ctx.LONG2);
		    }
		  else
		    {
		      state.push (ftype);
		    }
		}
		continue next_insn;

	      case op_getfield:				/* 0xb4 */
		{
		  Type object           = state.pop (SC_ADDR); 
		  int index             = n2h.get2u (code, pc); pc += 2;
		  Type ftype            = getFieldType (index);
		  Type fclass           = getFieldClass (index);

		  if (log3)
		    {
		      log ("get field " + ftype);
		    }

		  fclass.checkAssignmentFrom (object);

		  /*
		    FIXME: here we could check a lot more,
		    if we have the information available.
		  */

		  if (ftype.tag == T_DOUBLE)
		    {
		      state.push (ctx.DOUBLE, ctx.DOUBLE2);
		    }
		  else if (ftype.tag == T_LONG)
		    {
		      state.push (ctx.LONG, ctx.LONG2);
		    }
		  else
		    {
		      state.push (ftype);
		    }
		}
		continue next_insn;

	      case op_putfield:				/* 0xb5 */
		{
		  int index   = n2h.get2u (code, pc); pc += 2;
		  Type ftype  = getFieldType (index);
		  Type fclass = getFieldClass (index);

		  if (ftype.tag == T_DOUBLE)
		    {
		      state.pop (SC_DOUBLE, SC_DOUBLE2);
		    }
		  else if (ftype.tag == T_LONG)
		    {
		      state.pop (SC_LONG, SC_LONG2);
		    }
		  else
		    {
		      Type value = state.pop (ftype.storageClass);
		      ftype.checkAssignmentFrom (value);
		    }

		  if (state.constructor_start)
		    {
		      // we're allowed to assign only to instance variables
		      // of the class itself.
		      Type self_new = state.pop (SC_NEW);
		      Type self = ((NewObjectType)self_new).klass;

		      if (self != thisType)
			{
			  throw new verify.VerificationException
			    ("illegal field assignment in "
			     +"constructor code");
			}
		    }
		  else
		    {
		      Type object = state.pop (SC_ADDR);
		      fclass.checkAssignmentFrom (object);
		    }
		}
		continue next_insn;

	      case op_invokevirtual:			/* 0xb6 */
	      case op_invokespecial:			/* 0xb7 */
	      case op_invokestatic:			/* 0xb8 */
	      case op_invokeinterface:			/* 0xb9 */
		{

		  int index = n2h.get2u (code, pc); pc += 2;

		  if (opcode == op_invokeinterface)
		    {
		      if (code[pc++] != 0)
			warn ("padding byte '0x"
			      +Integer.toHexString(code[pc-1])
			      +"' is non-zero");
		      if (code[pc++] != 0)
			warn ("padding byte '0x"
			      +Integer.toHexString(code[pc-1])
			      +"' is non-zero");
		    }

		  /*
		  if (log3) {
		    log (state.toString () + " before call \n" 
			 + state.dump (true));
		  }
		  */

		  Signature msig;
		  String msigname;
		  String cname;
		  if (opcode == op_invokeinterface)
		    {
		      msig = getInterfaceMethodSignature (index);
		      msigname = getInterfaceMethodName (index);
		      cname = getInterfaceMethodClass (index);
		    }
		  else
		    {
		      msig  = getMethodSignature (index);
		      msigname = getMethodName (index);
		      cname = getMethodClass (index);
		    }

		  if (log3)
		    {
		      log ("calling "+cname+"."+msigname+" "+msig);
		    }
		  
		  int nargs = msig.numberArgs ();
		  Type receiver = null;
		  Type expected_receiver = null;

		  boolean is_constructor = (msigname.equals ("<init>"));

		  if (opcode != op_invokestatic)
		    {
		      // first, find receiver...
		      receiver = find_receiver (msig, state, is_constructor);
		      if (cname.charAt (0) == '[')
		          expected_receiver = ctx.typeForName (cname);
		      else
		          expected_receiver = ctx.classForName (cname);


		      /*
		      if (log3) {
			log ("found receiver as "+receiver);
		      }
		      */
		      
		      if (receiver.tag == T_NEW)
			{
			  if (is_constructor && opcode == op_invokespecial)
			    {
			      /* Honky Dory, we're calling an constructor */
			      receiver =
				state.replaceConstructedObject (receiver);

			      if (state.constructor_start
				  && receiver == thisType)
				{
				  Type superClass =
				    getClassType (classfile.super_class);

				  if (expected_receiver != thisType
				      && expected_receiver != superClass)
				    throw new
				      verify.VerificationException
				      ("illegal constructor call");

				  state.constructor_start = false;
				}

			    }
			  else
			    {
			      throw new verify.VerificationException
				("Method "+msigname
				 + " called with an "
				 + "uninitialized object.");
			    }
			}

		      else if (is_constructor)
			{
			  throw new verify.VerificationException
			    ("Constructor"
			     + " called with an "
			     + "initialized object.");
			}

		      /*
		      if (log3) log ("expected: "+expected_receiver);
		      if (log3) log ("provided: "+receiver);
		      */
		      expected_receiver.checkAssignmentFrom (receiver);

		    }

		  // next, check argument passing
		  for (int arg = nargs-1; arg >= 0; arg--)
		    {
		      Type tt = pop_arg (msig.declaredArgument (arg), state);

		      /*
		      if (log3) {
			log (state.toString () + " expecting a "
			     + msig.declaredArgument (arg)
			     + " found a " + tt);
		      }
		      */

		    }

		  if (opcode != op_invokestatic)
		    {
		      // pop receiver, ... we only peek'd it before!
		      Type found = state.pop (SC_ADDR);
		      if (receiver != found)
			{
			  throw new InternalError
			    ("didn't find receiver after popping args!\n"
			     + " expecting '" + receiver 
			     + "' found '" + found + "'");
			}

		      expected_receiver.checkAssignmentFrom (receiver);
		    }

		  // finally, the return type
		  Type rtype = msig.returnType ();

		  /*
		  if (log3 && rtype.tag != T_VOID) {
		    log (state.toString () + " before pushing result ");
		  }
		  */
		  
		  if (rtype.tag == T_VOID)
		    {
		      /* ignore */;
		    }
		  else if (rtype.tag == T_DOUBLE)
		    {
		      state.push (ctx.DOUBLE, ctx.DOUBLE2);
		    }
		  else if (rtype.tag == T_LONG)
		    {
		      /*
		      if (log3) {
			log ("pushing result "+rtype);
		      }
		      */
		      
		      state.push (ctx.LONG, ctx.LONG2);
		    }
		  else
		    {
		      /*
		      if (log3) {
			log ("pushing result "+rtype);
		      }
		      */
		      
		      state.push (rtype);
		    }

		  /*
		  if (log3) {
		    log (state.toString () + " after call ");
		  }
		  */

		}
		continue next_insn;

	      case op_new:				/* 0xbb */
		{
		  int index = n2h.get2u (code, pc); pc += 2;
		  String name = classfile.pool.get_class (index);
		  ClassType type = ctx.classForName (name);
		  state.push (ctx.newForClass (type));
		}
	      continue next_insn;
	      
	      case op_newarray:				/* 0xbc */
		{
		  int atype_code = n2h.get1u (code, pc++);
		  Type size = state.pop (SC_INT);
		  Type element = null;

		  switch (atype_code) {
		  case 4: element = ctx.BOOLEAN; break;
		  case 5: element = ctx.CHAR; break;
		  case 6: element = ctx.FLOAT; break;
		  case 7: element = ctx.DOUBLE; break;
		  case 8: element = ctx.BYTE; break;
		  case 9: element = ctx.SHORT; break;
		  case 10: element = ctx.INT; break;
		  case 11: element = ctx.LONG; break;
		  default: throw new verify.VerificationException
			     ("erroneous array type in newarray instruction");
		  }
		  state.push (ArrayType.forElement (element));
		}
		continue next_insn;

	      case op_anewarray:			/* 0xbd */
		{
		  int idx = n2h.get2u (code, pc); pc += 2;
		  Type etype = getClassType (idx);
		  state.pop (SC_INT);
		  state.push (ArrayType.forElement (etype));
		}
		continue next_insn;

	      case op_arraylength:			/* 0xbe */
		{
		  Type t = state.pop (SC_ADDR);
		  if (!t.isAnonymousArray () && t.tag != T_ARRAY)
		    throw new verify.VerificationException
		      ("non-array operand to arraylength operation");
		  state.push (ctx.INT);
		}
		continue next_insn;

	      case op_athrow:				/* 0xbf */
		{
		  Type extype = state.pop (SC_ADDR);
		  inject_exceptions (method, state, pc-1);
		}		
		break next_insn;

	      case op_checkcast:			/* 0xc0 */
		{
		  Type t = state.pop (SC_ADDR);
		  int index = n2h.get2u (code, pc); pc += 2;
		  Type ctype = getClassType (index);
		  state.push (ctype);
		}
		continue next_insn;

	      case op_instanceof:			/* 0xc1 */
		{
		  Type t = state.pop (SC_ADDR);
		  int index = n2h.get2u (code, pc); pc += 2;
		  state.push (ctx.INT);
		}
		continue next_insn;

	      case op_monitorenter:			/* 0xc2 */
	      case op_monitorexit:			/* 0xc3 */
		{
		  Type obj = state.pop (SC_ADDR);
		}
		continue next_insn;

	      case op_wide:				/* 0xc4 */
		{
		  int the_mod_op = n2h.get1u (code, pc++);
		  int wide = n2h.get2u (code, pc); pc += 2;
		  switch (the_mod_op) {
		  case op_istore:
		    state.store (wide, SC_INT);
		    continue next_insn;

		  case op_fstore:
		    state.store (wide, SC_FLOAT);
		    continue next_insn;

		  case op_astore:
		    state.store (wide, SC_ADDR);
		    continue next_insn;

		  case op_lload:
		    state.load (wide, SC_LONG, SC_LONG2);
		    continue next_insn;

		  case op_dload:
		    state.load (wide, SC_DOUBLE, SC_DOUBLE2);
		    continue next_insn;

		  case op_iload:
		    state.load (wide, SC_INT);
		    continue next_insn;

		  case op_fload:
		    state.load (wide, SC_FLOAT);
		    continue next_insn;

		  case op_aload:
		    if (wide==0 && state.constructor_start)
		      {
			state.load (0, SC_NEW);
		      }
		    else
		      {
			state.load (wide, SC_ADDR);
		      }
		    continue next_insn;

		  case op_lstore:
		    state.store (wide, SC_LONG, SC_LONG2);
		    continue next_insn;

		  case op_dstore:
		    state.store (wide, SC_DOUBLE, SC_DOUBLE2);
		    continue next_insn;

		  case op_ret:
		    {
		      Type addr   = state.read (wide, SC_ADDR);
		      if (addr.tag != T_ADDR)
			throw new verify.VerificationException
			  ("ret ["+wide+"] does not specify a return address");
		      state.write (wide, null); // clear return addr
		      AddressType atype = (AddressType)addr;
		      branch (state, atype.pc() + pc_offset);
		    }
		    break next_insn;

		  case op_iinc:
		    pc += 2;	// we don't care how much!
		    state.read (wide, SC_INT); // make sure "wide" is OK
		    continue next_insn;
		    
		  default:
		    throw new verify.VerificationException
		      ("illegal opcode in wide operation");
		  }
		}
		/* unreachable 
		   continue next_insn; */

	      case op_multianewarray:			/* 0xc5 */
		{
		  int kind = n2h.get2u (code, pc); pc += 2;
		  int dim  = n2h.get1u (code, pc++);

		  Type array = getClassType (kind);

		  /* FIXME: check # dimensions */

		  if (log3) 
		    {
		      log ("dim="+dim+"; kind=\""+array+"\"");
		    }

		  for (int i = 0; i < dim; i++)
		    state.pop (SC_INT);
		  
		  state.push (array);

		}
		continue next_insn;

	      case op_ifnull:				/* 0xc6 */
	      case op_ifnonnull:			/* 0xc7 */
		{
		  int base = pc-1;
		  int target = n2h.get2s (code,pc); pc += 2;
		  Type obj = state.pop (SC_ADDR);

		  conditional_branch (state, base+target, pc);
		}
		break next_insn;

	      case op_goto_w:				/* 0xc8 */
	      {
		int basepc = pc-1;
		int offset = n2h.get4 (code, pc); pc += 4;
		branch (state, basepc+offset);
	      }
	      break next_insn;

	  default:                                      /* 0xba,0xc9-0xff */  

	    throw new verify.VerificationException 
	      ("illegal opcode 0x" + Integer.toHexString (opcode) 
	       + " at pc=" + (pc-1-pc_offset));

	      } // end of switch
	  } // end of next_insn

	for (int i = pc_of_last_opcode+1; i < pc; i++)
	  {
	    int mode = byte_mode[i-pc_offset];
	    if (mode == BYTE_MODE_UNKNOWN)
	      {
		byte_mode[i-pc_offset] = BYTE_MODE_ILLEGAL;
	      }
	    else if (mode == BYTE_MODE_ILLEGAL)
	      {
		continue;
	      }
	    else if (mode == BYTE_MODE_INSTRUCTION
		     || mode == BYTE_MODE_TRY_INSTRUCTION)
	      {
		throw new verify.VerificationException
		  ("executed code inside an instruction");
	      }
	    else
	      {
		throw new InternalError ("illegal byte mode");
	      }
	  }

      } // end of next_basic_block

//    } catch (verify.VerificationException ex) {
//      boolean keep1, keep2, keep3;
//
//      int real_pc = pc_of_last_opcode-pc_offset;
//      System.err.println ("\n*** VERIFICATION FAILURE ***\n"
//			  +"exception at pc="+(real_pc)
//			  +" ("
//			  +insn_name[n2h.get1u (code, pc_of_last_opcode)]
//			  +")");
//      System.err.println (ex.getMessage ());
//      log (state.dump (false));
//
//
//      /*
//      ex.printStackTrace ();
//      */
//
//      System.exit (1);
//
//    } catch (Throwable ex) {
//      System.err.println ("non-verificatrion exception, "
//			  + ex.getMessage ());
//      ex.printStackTrace ();
//      System.exit (1);

    } finally {
      
      boolean found_unreachable_code = false;

      // we're almost done...
      for (int i = 0; i < code_size; i++)
	{
	  switch (byte_mode[i])
	    {
	    case BYTE_MODE_UNKNOWN:
	      {
		int start = i++;
		while (i < (end_pc-pc_offset)
		       && byte_mode[i] == BYTE_MODE_UNKNOWN)
		  {
		    i += 1;
		  }
		
//		if (!found_unreachable_code)
//		  {
//		    System.err.print (" unreachable in "
//				      + method.name () + ": ");
//		    found_unreachable_code = true;
//		  }
//		else
//		  {
//		    System.err.print (", ");
//		  }
//		    
//		System.err.print 
//		  (""+start+"+"+(i-1-start)+"");
	      }
	    case BYTE_MODE_INSTRUCTION:
	    case BYTE_MODE_TRY_INSTRUCTION:
	      instruction_count += 1;

	    case BYTE_MODE_ILLEGAL:
	      break;

	    default:
	      throw new InternalError ("illegal byte mode");
	    }
	}


//      return;	
    }

    
  }


  /* This is called for every instruction to see if we need to "emulate" an
     exception happening here.  That means, many many states are created in
     this function, and then immediately discarded if they have no effect
     when merged into the exception handler's existing entry state.  So this
     might be a point to optimize.  Figure some way, to see if the current
     state, merged into the exception handler state, would change affairs.  */

  void inject_exceptions (ClassFile.Method m, AbstractState now, int pc)
    throws verify.VerificationException
  {
    //if (log3) { log ("inject ("+ this_method_has_exceptions +")"); };

    if (this_method_has_exceptions == false)
      return;

    for (int i = 0; i < m.exceptions.length; i++)
      {
	ClassFile.ExceptionTableEntry entry = m.exceptions[i];
        /*if (log3) { log ("=> ? "+ entry.start_pc 
			 +" -> " + entry.handler_pc); } */
	if (   (pc-pc_offset) >= entry.start_pc	// start pc is inclusive
	    && (pc-pc_offset)  < entry.end_pc)  // end pc is exclusive 
	  {
	    int exc_pc = pc_offset+entry.handler_pc;

	    /* The presentce of the next 6 lines of code account for a
	       radical 20-25% performance improvement of the checking.  
	       It helps us avoid many expensive copies, and resulting
	       merges as a concequence of inducing exceptions. */

	    AbstractState target = findApplicableState (now, exc_pc);
	    if (target != null)
	      {
		if (! target.exceptionMayMerge (now))
		  {
	          /*  if (log3) { log ("=> no merge"); } */
		    continue;
		  }
	      }

	    Type extype;

	    if (entry.catch_type == 0) // beyond spec!
	      extype = ctx.THROWABLE;
	    else
	      extype = getClassType (entry.catch_type);

	    AbstractState exstate = new AbstractState (now, extype);
	    /*if (log3) { log ("=> branch "); } */
	    branch (exstate, exc_pc);
	  }
      }

    /*if (log3) { log ("=> done."); }*/
  }

  ///////// interface to the constant pool goes here //////////

  Type getClassType (int index) 
    throws verify.type.IllegalTypeNameException
  {
    return ctx.classFor (classfile.pool.get_class (index));
  }

  Type getFieldType (int index) 
    throws verify.type.IllegalTypeNameException
  {
    return ctx.typeForName (classfile.pool.get_fieldref_type (index));
  }

  Type getFieldClass (int index)
    throws verify.type.IllegalTypeNameException
  {
    return ctx.classForName (classfile.pool.get_fieldref_class (index));
  }

  Signature getMethodSignature (int index)
    throws verify.type.IllegalTypeNameException
  {
    String sig = classfile.pool.get_methodref_type (index);
    return Signature.forString (ctx, sig);
  }

  Signature getInterfaceMethodSignature (int index) 
    throws verify.type.IllegalTypeNameException
  {
    String sig = classfile.pool.get_interfacemethodref_type (index);
    return Signature.forString (ctx, sig);
  }

  String getInterfaceMethodName (int index) 
    throws verify.type.IllegalTypeNameException
  {
    return classfile.pool.get_interfacemethodref_name (index);
  }

  String getMethodName (int index) 
    throws verify.type.IllegalTypeNameException
  {
    return classfile.pool.get_methodref_name (index);
  }

  String getInterfaceMethodClass (int index) 
    throws verify.type.IllegalTypeNameException
  {
    return classfile.pool.get_interfacemethodref_class (index);
  }

  String getMethodClass (int index) 
    throws verify.type.IllegalTypeNameException
  {
    return classfile.pool.get_methodref_class (index);
  }

  Type getConstantType (int index)
    throws verify.VerificationException
  {
    switch (classfile.pool.get_tag (index)) 
      {
      case CONSTANT_String:
	return ctx.STRING;
	
      case CONSTANT_Integer:
	return ctx.INT;
	
      case CONSTANT_Float:
	return ctx.FLOAT;
	
      case CONSTANT_Double:
	return ctx.DOUBLE;
	
      case CONSTANT_Long:
	return ctx.LONG;

	/* FIXME: why is this not allowed?  It appears straight-forward
	   that Java should allow

	      static final Class XCLASS = FOOBAR.class;

	   but it does not, so this is also not allowed as a constant value.
	*/

	case CONSTANT_Class:
	    return ctx.CLASS;

      default:
	throw new verify.VerificationException
	  ("did not find a constant value at index "
	   +index+" in constant pool");
      }
  }

  Type find_receiver (Signature sig,
		      AbstractState state,
		      boolean new_object)
    throws verify.VerificationException
  {
    int nargs = sig.numberArgs ();
    int offset = 0;
    for (int arg = nargs-1; arg >= 0; arg--)
      {
	Type targ = sig.declaredArgument (arg);
	if (targ.tag == T_LONG || targ.tag == T_DOUBLE) 
	  offset += 2;
	else
	  offset += 1;

	/* log ("peeking " + state.peek (offset, targ.storageClass)); */
      }
    
    if (new_object)
      return state.peek (offset+1, T_NEW);
    else
      return state.peek (offset+1, SC_ADDR);
  }

  Type pop_arg (Type argtype, AbstractState state)
    throws verify.VerificationException
  {
    if (argtype.tag == T_DOUBLE) 
      { 
	state.pop (SC_DOUBLE, SC_DOUBLE2);
	return ctx.DOUBLE;
      }
    else if (argtype.tag == T_LONG) 
      { 
	state.pop (SC_LONG, SC_LONG2);
	return ctx.LONG;
      }
    else
      {
	Type valueType = state.pop (argtype.storageClass);
	argtype.checkAssignmentFrom (valueType);
	return valueType;
      }
  }



}
