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

    public int compareTo(String o) {
        return 0;
    }
    
    private static native String ldcAscii(Object cptr, int length);
}
