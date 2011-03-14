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
public abstract class Type {
    public static final IntegerType I1 = new IntegerType(1);
    public static final IntegerType I8 = new IntegerType(8);
    public static final IntegerType I16 = new IntegerType(16);
    public static final IntegerType I32 = new IntegerType(32);
    public static final IntegerType I64 = new IntegerType(64);
    public static final FloatingPointType FLOAT = new FloatingPointType("float");
    public static final FloatingPointType DOUBLE = new FloatingPointType("double");
    public static final PrimitiveType VOID = new PrimitiveType("void");
    public static final PointerType I8_PTR = new PointerType(I8);
}
