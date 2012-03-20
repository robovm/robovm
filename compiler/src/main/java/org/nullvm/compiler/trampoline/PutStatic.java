/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.trampoline;

import static org.nullvm.compiler.Types.*;
import static org.nullvm.compiler.llvm.Type.*;

import org.nullvm.compiler.llvm.FunctionType;


/**
 *
 * @version $Id$
 */
public class PutStatic extends FieldAccessor {

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
