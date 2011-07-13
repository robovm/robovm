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
public enum Linkage {

    _private,
    linker_private,
    linker_private_weak,
    linkonce,
    linkonce_odr,
    external;
    
    public String toString() {
        if (this == _private) {
            return "private";
        }
        return super.toString();
    }
}
