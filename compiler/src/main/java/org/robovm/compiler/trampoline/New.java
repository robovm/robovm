/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.trampoline;

import static org.robovm.compiler.Types.*;

import org.robovm.compiler.llvm.FunctionType;


/**
 *
 * @version $Id$
 */
public class New extends Trampoline {
    private static final long serialVersionUID = 1L;
    
    public New(String callingClass, String targetClass) {
        super(callingClass, targetClass);
    }

    @Override
    public FunctionType getFunctionType() {
        return new FunctionType(OBJECT_PTR, ENV_PTR);
    }
}
