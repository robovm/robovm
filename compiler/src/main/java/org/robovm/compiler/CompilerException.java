package org.robovm.compiler;

@SuppressWarnings("serial")
public class CompilerException extends RuntimeException {

    public CompilerException() {
        super();
    }

    public CompilerException(String message, Throwable cause) {
        super(message, cause);
    }

    public CompilerException(String message) {
        super(message);
    }

    public CompilerException(Throwable cause) {
        super(cause);
    }

}
