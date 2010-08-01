/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.tests.opcode.a;

public class A1 {
    
    public int f1(int x) {
        return 1 * x;
    }

    protected int f2(int x) {
        return 2 * x;
    }
    
    int f3(int x) {
        return 3 * x;
    }
    
    private int f4(int x) {
        return 4 * x;
    }
    
    public final int A1_f4(int x) {
        return f4(x);
    }
    
}
