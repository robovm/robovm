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
public class PackedStructureType extends StructureType {
    
    public PackedStructureType(Type ... types) {
        super(types);
    }
    
    public PackedStructureType(String alias, Type ... types) {
        super(alias, types);
    }

    @Override
    public String getDefinition() {
        return "<" + super.getDefinition() + ">";
    }
}
