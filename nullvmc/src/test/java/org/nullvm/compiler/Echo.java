/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package org.nullvm.compiler;

/**
 *
 * @version $Id$
 */
public class Echo {

    public static void println() {
        println("");
    }
    
    public static void println(String s) {
        print(s);
        print("\n");
    }
    
    @Native
    public static void print(String s) {
        System.out.print(s);
    }
    
    @Native
    public static void print(int n) {
        System.out.print(n);
    }
}
