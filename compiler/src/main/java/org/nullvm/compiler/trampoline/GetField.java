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
public class GetField extends FieldAccessor {
    private final String runtimeClass;

    public GetField(String runtimeClass, String targetClass, String fieldName, String fieldDesc) {
        super(targetClass, fieldName, fieldDesc);
        this.runtimeClass = runtimeClass;
    }

    public String getRuntimeClass() {
        return runtimeClass;
    }
}
