/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.trampoline;

import static org.nullvm.compiler.Types.*;

import org.nullvm.compiler.llvm.FunctionType;
import org.nullvm.compiler.llvm.Type;


/**
 *
 * @version $Id$
 */
public class ExceptionMatch extends Trampoline {
    private static final long serialVersionUID = 1L;
    
    public ExceptionMatch(String callingClass, String targetClass) {
        super(callingClass, targetClass);
    }

    @Override
    public FunctionType getFunctionType() {
        return new FunctionType(Type.I32, ENV_PTR);
    }
}
