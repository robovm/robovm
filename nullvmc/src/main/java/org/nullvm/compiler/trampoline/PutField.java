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
public class PutField extends FieldAccessor {

    public PutField(String targetClass, String fieldName, String fieldDesc) {
        super(targetClass, fieldName, fieldDesc);
    }

}
