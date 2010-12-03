/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

/**
 * The superclass of all classes which can be thrown by the virtual machine. The
 * two direct subclasses are recoverable exceptions ({@code Exception}) and
 * unrecoverable errors ({@code Error}). This class provides common methods for
 * accessing a string message which provides extra information about the
 * circumstances in which the {@code Throwable} was created (basically an error
 * message in most cases), and for saving a stack trace (that is, a record of
 * the call stack at a particular point in time) which can be printed later.
 * <p>
 * A {@code Throwable} can also include a cause, which is a nested {@code
 * Throwable} that represents the original problem that led to this {@code
 * Throwable}. It is often used for wrapping various types of errors into a
 * common {@code Throwable} without losing the detailed original error
 * information. When printing the stack trace, the trace of the cause is
 * included.
 *
 * @see Error
 * @see Exception
 * @see RuntimeException
 */
public class Throwable implements java.io.Serializable {

    /*
     * This class must be implemented by the VM vendor, or the reference
     * implementation can be used if the documented natives are implemented.
     */

    private static final long serialVersionUID = -3042686055658047285L;

    /**
     * The message provided when the exception was created.
     */
    private String detailMessage;

    /**
     * The cause of this Throwable. Null when there is no cause.
     */
    private Throwable cause = this;

    /**
     * A fully-expanded representation of the stack trace.
     */
    private StackTraceElement[] stackTrace;

    /**
     * Constructs a new {@code Throwable} that includes the current stack trace.
     */
    public Throwable() {
        super();
        fillInStackTrace();
    }

    /**
     * Constructs a new {@code Throwable} with the current stack trace and the
     * specified detail message.
     *
     * @param detailMessage
     *            the detail message for this {@code Throwable}.
     */
    public Throwable(String detailMessage) {
        this();
        this.detailMessage = detailMessage;
    }

    /**
     * Constructs a new {@code Throwable} with the current stack trace, the
     * specified detail message and the specified cause.
     * 
     * @param detailMessage
     *            the detail message for this {@code Throwable}.
     * @param throwable
     *            the cause of this {@code Throwable}.
     */
    public Throwable(String detailMessage, Throwable throwable) {
        this();
        this.detailMessage = detailMessage;
        cause = throwable;
    }

    /**
     * Constructs a new {@code Throwable} with the current stack trace and the
     * specified cause.
     *
     * @param throwable
     *            the cause of this {@code Throwable}.
     */
    public Throwable(Throwable throwable) {
        this();
        this.detailMessage = throwable == null ? null : throwable.toString();
        cause = throwable;
    }

    /*
     * This native must be implemented to use the reference implementation of
     * this class.
     */
    /**
     * Records the stack trace from the point where this method has been called
     * to this {@code Throwable}. The method is public so that code which
     * catches a {@code Throwable} and then re-throws it can adjust the stack
     * trace to represent the location where the exception was re-thrown.
     *
     * @return this {@code Throwable} instance.
     */
    public native Throwable fillInStackTrace();

    /**
     * Returns the extra information message which was provided when this
     * {@code Throwable} was created. Returns {@code null} if no message was
     * provided at creation time.
     *
     * @return this {@code Throwable}'s detail message.
     */
    public String getMessage() {
        return detailMessage;
    }

    /**
     * Returns the extra information message which was provided when this
     * {@code Throwable} was created. Returns {@code null} if no message was
     * provided at creation time. Subclasses may override this method to return
     * localized text for the message. The Android reference implementation
     * returns the unlocalized detail message.
     * 
     * @return this {@code Throwable}'s localized detail message.
     */
    public String getLocalizedMessage() {
        return getMessage();
    }

    /**
     * This native must be implemented to use the reference implementation of
     * this class. The result of this native is cloned, and returned from the
     * public API getStackTrace().
     *
     * Answers an array of StackTraceElement. Each StackTraceElement represents
     * a entry on the stack.
     * 
     * @return an array of StackTraceElement representing the stack
     */
    private native StackTraceElement[] getStackTraceImpl();

    /**
     * Returns the array of stack trace elements of this {@code Throwable}. Each
     * {@code StackTraceElement} represents an entry in the call stack. The
     * element at position 0 is the top of the stack, that is, the stack frame
     * where this {@code Throwable} is thrown.
     *
     * @return a copy of the array of {@code StackTraceElement}s representing
     *         the call stack. Changes in the array obtained from this call will
     *         not change the call stack stored in this {@code Throwable}.
     * @see #printStackTrace()
     */
    public StackTraceElement[] getStackTrace() {
        return getInternalStackTrace().clone();
    }

    /**
     * Sets the array of stack trace elements. Each {@code StackTraceElement}
     * represents an entry in the call stack. A copy of the specified array is
     * stored in this {@code Throwable}. will be returned by {@code
     * getStackTrace()} and printed by {@code printStackTrace()}.
     *
     * @param trace
     *            the new array of {@code StackTraceElement}s. A copy of the
     *            array is stored in this {@code Throwable}, so subsequent
     *            changes to {@code trace} will not change the call stack stored
     *            in this {@code Throwable}.
     * @throws NullPointerException
     *             if any element in {@code trace} is {@code null}.
     * @see #printStackTrace()
     */
    public void setStackTrace(StackTraceElement[] trace) {
        StackTraceElement[] newTrace = trace.clone();
        for (java.lang.StackTraceElement element : newTrace) {
            if (element == null) {
                throw new NullPointerException();
            }
        }
        stackTrace = newTrace;
    }

    /**
     * Writes a printable representation of this {@code Throwable}'s stack trace
     * to the {@code System.err} stream.
     */
    public void printStackTrace() {
        printStackTrace(System.err);
    }

    /**
     * Counts the number of duplicate stack frames, starting from the
     * end of the stack.
     * 
     * @param currentStack a stack to compare
     * @param parentStack a stack to compare
     * 
     * @return the number of duplicate stack frames.
     */
    private static int countDuplicates(StackTraceElement[] currentStack,
            StackTraceElement[] parentStack) {
        int duplicates = 0;
        int parentIndex = parentStack.length;
        for (int i = currentStack.length; --i >= 0 && --parentIndex >= 0;) {
            StackTraceElement parentFrame = parentStack[parentIndex];
            if (parentFrame.equals(currentStack[i])) {
                duplicates++;
            } else {
                break;
            }
        }
        return duplicates;
    }

    /**
     * Returns an array of StackTraceElements. Each StackTraceElement represents
     * a entry on the stack. Cache the stack trace in the stackTrace field,
     * returning the cached field when it has already been initialized.
     * 
     * @return an array of StackTraceElement representing the stack
     */
    private StackTraceElement[] getInternalStackTrace() {
        if (stackTrace == null) {
            stackTrace = getStackTraceImpl();
        }
        return stackTrace;
    }

    /**
     * Writes a printable representation of this {@code Throwable}'s stack trace
     * to the specified print stream. If the {@code Throwable} contains a
     * {@link #getCause() cause}, the method will be invoked recursively for
     * the nested {@code Throwable}.
     * 
     * @param err
     *            the stream to write the stack trace on.
     */
    public void printStackTrace(PrintStream err) {
        err.println(toString());
        // Don't use getStackTrace() as it calls clone()
        // Get stackTrace, in case stackTrace is reassigned
        StackTraceElement[] stack = getInternalStackTrace();
        for (java.lang.StackTraceElement element : stack) {
            err.println("\tat " + element);
        }

        StackTraceElement[] parentStack = stack;
        Throwable throwable = getCause();
        while (throwable != null) {
            err.print("Caused by: ");
            err.println(throwable);
            StackTraceElement[] currentStack = throwable.getInternalStackTrace();
            int duplicates = countDuplicates(currentStack, parentStack);
            for (int i = 0; i < currentStack.length - duplicates; i++) {
                err.println("\tat " + currentStack[i]);
            }
            if (duplicates > 0) {
                err.println("\t... " + duplicates + " more");
            }
            parentStack = currentStack;
            throwable = throwable.getCause();
        }
    }

    /**
     * Writes a printable representation of this {@code Throwable}'s stack trace
     * to the specified print writer. If the {@code Throwable} contains a
     * {@link #getCause() cause}, the method will be invoked recursively for the
     * nested {@code Throwable}.
     * 
     * @param err
     *            the writer to write the stack trace on.
     */
    public void printStackTrace(PrintWriter err) {
        err.println(toString());
        // Don't use getStackTrace() as it calls clone()
        // Get stackTrace, in case stackTrace is reassigned
        StackTraceElement[] stack = getInternalStackTrace();
        for (java.lang.StackTraceElement element : stack) {
            err.println("\tat " + element);
        }

        StackTraceElement[] parentStack = stack;
        Throwable throwable = getCause();
        while (throwable != null) {
            err.print("Caused by: ");
            err.println(throwable);
            StackTraceElement[] currentStack = throwable.getInternalStackTrace();
            int duplicates = countDuplicates(currentStack, parentStack);
            for (int i = 0; i < currentStack.length - duplicates; i++) {
                err.println("\tat " + currentStack[i]);
            }
            if (duplicates > 0) {
                err.println("\t... " + duplicates + " more");
            }
            parentStack = currentStack;
            throwable = throwable.getCause();
        }
    }

    @Override
    public String toString() {
        String msg = getLocalizedMessage();
        String name = getClass().getName();
        if (msg == null) {
            return name;
        }
        return new StringBuilder(name.length() + 2 + msg.length()).append(name).append(": ")
                .append(msg).toString();
    }

    /**
     * Initializes the cause of this {@code Throwable}. The cause can only be
     * initialized once.
     *
     * @param throwable
     *            the cause of this {@code Throwable}.
     * @return this {@code Throwable} instance.
     * @throws IllegalArgumentException
     *             if {@code Throwable} is this object.
     * @throws IllegalStateException
     *             if the cause has already been initialized.
     */
    public synchronized Throwable initCause(Throwable throwable) {
        if (cause == this) {
            if (throwable != this) {
                cause = throwable;
                return this;
            }
            throw new IllegalArgumentException("Cause cannot be the receiver");
        }
        throw new IllegalStateException("Cause already initialized");
    }

    /**
     * Returns the cause of this {@code Throwable}, or {@code null} if there is
     * no cause.
     * 
     * @return Throwable this {@code Throwable}'s cause.
     */
    public Throwable getCause() {
        if (cause == this) {
            return null;
        }
        return cause;
    }

    private void writeObject(ObjectOutputStream s) throws IOException {
        // ensure the stackTrace field is initialized
        getInternalStackTrace();
        s.defaultWriteObject();
    }
}
