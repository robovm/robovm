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
public class Throwable {
    private String message = null;

    public Throwable() {
    }
    
    public Throwable(String message) {
        this.message = message;
    }
    
    public String getMessage() {
        return message;
    }
}
