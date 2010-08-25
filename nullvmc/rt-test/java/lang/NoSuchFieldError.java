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
public class NoSuchFieldError extends IncompatibleClassChangeError {

    public NoSuchFieldError() {
    }
    
    public NoSuchFieldError(String message) {
        super(message);
    }
    
}
