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
public class IntegerType extends PrimitiveType {
    private final int bits;
    
    IntegerType(int bits) {
        super("i" + bits);
        this.bits = bits;
    }
    
    public int getBits() {
        return bits;
    }
}
