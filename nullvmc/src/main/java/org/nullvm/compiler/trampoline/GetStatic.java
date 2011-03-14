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
public class GetStatic extends FieldAccessor {

    public GetStatic(String targetClass, String fieldName, String fieldDesc) {
        super(targetClass, fieldName, fieldDesc);
    }

}
