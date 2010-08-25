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
public class IncompatibleClassChangeError extends LinkageError {

    public IncompatibleClassChangeError() {
    }
    
    public IncompatibleClassChangeError(String message) {
        super(message);
    }
    
}
