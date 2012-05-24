/*
 * Copyright (C) 2012 RoboVM
 *
 * TODO: Insert proper license header.
 */
package org.robovm.compiler.llvm;

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
