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
