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
public class NoClassDefFoundError extends LinkageError {

    public NoClassDefFoundError() {
    }
    
    public NoClassDefFoundError(String message) {
        super(message);
    }
    
}
