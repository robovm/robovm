/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.llvm;

/**
 *
 * @version $Id$
 */
public class OpaqueType extends UserType {
    public OpaqueType() {
        super();
    }
    
    public OpaqueType(String alias) {
        super(alias);
    }

    @Override
    public String getDefinition() {
        return "opaque";
    }
}
