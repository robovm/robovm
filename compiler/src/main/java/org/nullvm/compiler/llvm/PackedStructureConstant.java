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
public class PackedStructureConstant extends StructureConstant {

    public PackedStructureConstant(PackedStructureType type, Value ... values) {
        super(type, values);
    }
    
    @Override
    public String toString() {
        return "<" + super.toString() + ">";
    }
}
