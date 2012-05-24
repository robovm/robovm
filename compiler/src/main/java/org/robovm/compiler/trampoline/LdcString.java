/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.trampoline;

import static org.robovm.compiler.Mangler.*;
import static org.robovm.compiler.Types.*;

import org.robovm.compiler.llvm.FunctionType;


/**
 *
 * @version $Id$
 */
public class LdcString extends Trampoline {
    private static final long serialVersionUID = 1L;
    
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
