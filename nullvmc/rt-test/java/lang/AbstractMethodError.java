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
public class AbstractMethodError extends IncompatibleClassChangeError {

    public AbstractMethodError() {
    }
    
    public AbstractMethodError(String message) {
        super(message);
    }
    
}
