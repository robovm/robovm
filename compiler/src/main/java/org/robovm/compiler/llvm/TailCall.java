/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.llvm;


/**
 *
 * @version $Id$
 */
public class TailCall extends FunctionCallInstruction {
    
    public TailCall(Value function, Value ... args) {
        super("tail call", function, args);
    }
    
    public TailCall(Variable result, Value function, Value ... args) {
        super("tail call", result, function, args);
    }
}
