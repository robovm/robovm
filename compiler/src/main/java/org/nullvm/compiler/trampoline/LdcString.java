/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.trampoline;

import static org.nullvm.compiler.Mangler.*;
import static org.nullvm.compiler.Types.*;

import org.nullvm.compiler.llvm.FunctionType;


/**
 *
 * @version $Id$
 */
public class LdcString extends Trampoline {

    public LdcString(String callingClass, String string) {
        super(callingClass, string);
    }

    @Override
    public FunctionType getFunctionType() {
        return new FunctionType(OBJECT_PTR, ENV_PTR);
    }
    
    @Override
    public String toString() {
        return getClass().getSimpleName() + "_" + mangleString(target);
    }
}
