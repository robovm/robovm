/*
 * Copyright (C) 2010 The NullVM Open Source Project
 *
 * TODO: Insert proper license header.
 */
package java.lang;

/**
 *
 * @version $Id$
 */
public final class String implements Comparable<String> {
    private char[] value;
    private int offset;
    private int count;

    String(int start, int length, char[] data) {
        value = data;
        offset = start;
        count = length;
    }
    
    public int compareTo(String o) {
        return 0;
    }
    
//    private static native String ldcAscii(Object cptr, int length);
}
