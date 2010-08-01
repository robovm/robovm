/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler.tests.opcode.a;

import org.nullvm.compiler.tests.opcode.a.b.R;

/**
 *
 * @version $Id$
 */
public class E extends W {
    //public int x;
    
    public static void main(String[] args) {
        Q q = new E();
        q.x = 10;
        ((W) q).x = 20;
        ((E) q).x = 30;
        System.out.println(q.x);
        System.out.println(((W) q).x);
        System.out.println(((E) q).x);
    }
}
