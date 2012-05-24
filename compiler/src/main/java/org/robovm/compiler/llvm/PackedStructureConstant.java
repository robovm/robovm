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
public class PackedStructureConstant extends StructureConstant {

    public PackedStructureConstant(PackedStructureType type, Value ... values) {
        super(type, values);
    }
    
    @Override
    public String toString() {
        return "<" + super.toString() + ">";
    }
}
