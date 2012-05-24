/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.trampoline;

import static org.robovm.compiler.Types.*;
import static org.robovm.compiler.llvm.Type.*;

import org.robovm.compiler.llvm.FunctionType;


/**
 *
 * @version $Id$
 */
public class PutStatic extends FieldAccessor {
    private static final long serialVersionUID = 1L;
    
    public PutStatic(String callingClass, String targetClass, String fieldName, String fieldDesc) {
        super(callingClass, targetClass, fieldName, fieldDesc);
    }

    @Override
    public boolean isGetter() {
        return false;
    }
    
    @Override
    public boolean isStatic() {
        return true;
    }

    @Override
    public FunctionType getFunctionType() {
        return new FunctionType(VOID, ENV_PTR, getType(fieldDesc));
    }
}
