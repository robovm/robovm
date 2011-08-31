/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;



/**
 *
 * @version $Id$
 */
public class Load extends UnaryOpInstruction {

    public Load(Variable result, Value op) {
        super(result, op);
    }
    
    @Override
    public String toString() {
        return result + " = load " + op.getType() + " " + op;
    }
}
