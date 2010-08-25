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
public class VirtualMachineError extends Error {

    public VirtualMachineError() {
    }
    
    public VirtualMachineError(String message) {
        super(message);
    }
    
}
