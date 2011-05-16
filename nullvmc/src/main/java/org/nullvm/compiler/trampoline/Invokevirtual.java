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
public class Invokevirtual extends Invoke {
    private final String runtimeClass;

    public Invokevirtual(String runtimeClass, String targetClass, String methodName, String methodDesc) {
        super(targetClass, methodName, methodDesc);
        this.runtimeClass = runtimeClass;
    }

    public String getRuntimeClass() {
        return runtimeClass;
    }
}
