/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.trampoline;

import static org.robovm.compiler.Types.*;

import org.robovm.compiler.llvm.FunctionType;
import org.robovm.compiler.llvm.Type;


/**
 *
 * @version $Id$
 */
public class Instanceof extends Trampoline {
    private static final long serialVersionUID = 1L;
    
    public Instanceof(String callingClass, String targetClass) {
        super(callingClass, targetClass);
    }

    @Override
    public FunctionType getFunctionType() {
        return new FunctionType(Type.I32, ENV_PTR, OBJECT_PTR);
    }
}
