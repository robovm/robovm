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
public class NoSuchMethodError extends IncompatibleClassChangeError {

    public NoSuchMethodError() {
    }
    
    public NoSuchMethodError(String message) {
        super(message);
    }
    
}
