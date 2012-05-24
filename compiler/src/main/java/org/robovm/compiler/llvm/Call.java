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
public class Call extends FunctionCallInstruction {
    
    public Call(Value function, Value ... args) {
        super("call", function, args);
    }
    
    public Call(Variable result, Value function, Value ... args) {
        super("call", result, function, args);
    }

    public Call(Value function, Argument... args) {
        super("call", function, args);
    }

    public Call(Variable result, Value function, Argument... args) {
        super("call", result, function, args);
    }
}
