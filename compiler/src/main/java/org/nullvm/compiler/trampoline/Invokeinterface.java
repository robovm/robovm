/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.trampoline;


/**
 *
 * @version $Id$
 */
public class Invokeinterface extends Invoke {
    private static final long serialVersionUID = 1L;
    
    public Invokeinterface(String callingClass, String targetClass, String methodName, String methodDesc) {
        super(callingClass, targetClass, methodName, methodDesc);
    }

    @Override
    public boolean isStatic() {
        return false;
    }
}
