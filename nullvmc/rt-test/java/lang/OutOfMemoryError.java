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
public class OutOfMemoryError extends VirtualMachineError {

    public OutOfMemoryError() {
    }
    
    public OutOfMemoryError(String message) {
        super(message);
    }
    
}
