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
public class GetStatic extends FieldAccessor {
    private static final long serialVersionUID = 1L;
    
    public GetStatic(String callingClass, String targetClass, String fieldName, String fieldDesc) {
        super(callingClass, targetClass, fieldName, fieldDesc);
    }

    @Override
    public boolean isGetter() {
        return true;
    }
    
    @Override
    public boolean isStatic() {
        return true;
    }

    @Override
    public FunctionType getFunctionType() {
        return new FunctionType(getType(fieldDesc), ENV_PTR);
    }
}
