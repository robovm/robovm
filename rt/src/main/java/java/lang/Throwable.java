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
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import libcore.util.EmptyArray;

/**
 * The superclass of all classes which can be thrown by the VM. The
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
     * Throwables suppressed by this throwable. Null when suppressed exceptions
     * are disabled.
     */
    private List<Throwable> suppressedExceptions = new ArrayList<Throwable>();

    /**
     * An intermediate representation of the stack trace.  This field may
     * be accessed by the VM; do not rename.
     */
    private volatile Object stackState;

    /**
     * A fully-expanded representation of the stack trace.
     */
    private StackTraceElement[] stackTrace;

    /**
     * Constructs a new {@code Throwable} that includes the current stack trace.
     */
    public Throwable() {
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

    /**
     * Constructs a new {@code Throwable} with the current stack trace, the
     * specified detail message and the specified cause.
     *
     * @param enableSuppression if false, throwables passed to {@link
     *     #addSuppressed(Throwable)} will be silently discarded.
     * @since 1.7
     * @hide 1.7
     */
    protected Throwable(String detailMessage, Throwable throwable, boolean enableSuppression) {
        this(detailMessage, throwable);
        if (!enableSuppression) {
            this.suppressedExceptions = null;
        }
    }

    /**
     * Records the stack trace from the point where this method has been called
     * to this {@code Throwable}. This method is invoked by the {@code Throwable} constructors.
     *
     * <p>This method is public so that code (such as an RPC system) which catches
     * a {@code Throwable} and then re-throws it can replace the construction-time stack trace
     * with a stack trace from the location where the exception was re-thrown, by <i>calling</i>
     * {@code fillInStackTrace}.
     *
     * <p>This method is non-final so that non-Java language implementations can disable VM stack
     * traces for their language. Filling in the stack trace is relatively expensive.
     * <i>Overriding</i> this method in the root of a language's exception hierarchy allows the
     * language to avoid paying for something it doesn't need.
     *
     * @return this {@code Throwable} instance.
     */
    public Throwable fillInStackTrace() {
        // Fill in the intermediate representation
        stackState = nativeFillInStackTrace();
        // Mark the full representation as empty
        stackTrace = null;
        return this;
    }

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
     * localized text for the message. Android returns the regular detail message.
     *
     * @return this {@code Throwable}'s localized detail message.
     */
    public String getLocalizedMessage() {
        return getMessage();
    }

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
        for (StackTraceElement element : newTrace) {
            if (element == null) {
                throw new NullPointerException();
            }
        }
        stackTrace = newTrace;
    }

    /**
     * Writes a printable representation of this {@code Throwable}'s stack trace
     * to the {@code System.err} stream.
     *
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
     * Returns an array of StackTraceElement. Each StackTraceElement
     * represents a entry on the stack.
     *
     * @return an array of StackTraceElement representing the stack
     */
    private StackTraceElement[] getInternalStackTrace() {
        if (stackTrace == null) {
            stackTrace = nativeGetStackTrace(stackState);
            stackState = null; // Clean up intermediate representation
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
        try {
            printStackTrace(err, "", null);
        } catch (IOException e) {
            // Appendable.append throws IOException but PrintStream.append doesn't.
            throw new AssertionError();
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
        try {
            printStackTrace(err, "", null);
        } catch (IOException e) {
            // Appendable.append throws IOException, but PrintWriter.append doesn't.
            throw new AssertionError();
        }
    }

    /**
     * @param indent additional indentation on each line of the stack trace.
     *     This is the empty string for all but suppressed throwables.
     * @param parentStack the parent stack trace to suppress duplicates from, or
     *     null if this stack trace has no parent.
     */
    private void printStackTrace(Appendable err, String indent, StackTraceElement[] parentStack)
            throws IOException {
        err.append(toString());
        err.append("\n");

        StackTraceElement[] stack = getInternalStackTrace();
        if (stack != null) {
            int duplicates = parentStack != null ? countDuplicates(stack, parentStack) : 0;
            for (int i = 0; i < stack.length - duplicates; i++) {
                err.append(indent);
                err.append("\tat ");
                err.append(stack[i].toString());
                err.append("\n");
            }

            if (duplicates > 0) {
                err.append(indent);
                err.append("\t... ");
                err.append(Integer.toString(duplicates));
                err.append(" more\n");
            }
        }

        // Print suppressed exceptions indented one level deeper.
        if (suppressedExceptions != null) {
            for (Throwable throwable : suppressedExceptions) {
                err.append(indent);
                err.append("\tSuppressed: ");
                throwable.printStackTrace(err, indent + "\t", stack);
            }
        }

        Throwable cause = getCause();
        if (cause != null) {
            err.append(indent);
            err.append("Caused by: ");
            cause.printStackTrace(err, indent, stack);
        }
    }

    @Override
    public String toString() {
        String msg = getLocalizedMessage();
        String name = getClass().getName();
        if (msg == null) {
            return name;
        }
        return name + ": " + msg;
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
    public Throwable initCause(Throwable throwable) {
        if (cause != this) {
            throw new IllegalStateException("Cause already initialized");
        }
        if (throwable == this) {
            throw new IllegalArgumentException("throwable == this");
        }
        cause = throwable;
        return this;
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

    /**
     * Adds {@code throwable} to the list of throwables suppressed by this. The
     * throwable will included when this exception's stack trace is printed.
     *
     * @throws IllegalArgumentException if {@code throwable == this}.
     * @throws NullPointerException if {@code throwable == null}.
     * @since 1.7
     * @hide 1.7
     */
    public final void addSuppressed(Throwable throwable) {
        if (throwable == this) {
            throw new IllegalArgumentException("suppressed == this");
        }
        if (throwable == null) {
            throw new NullPointerException("suppressed == null");
        }
        if (suppressedExceptions != null) {
            suppressedExceptions.add(throwable);
        }
    }

    /**
     * Returns the throwables suppressed by this.
     *
     * @since 1.7
     * @hide 1.7
     */
    public final Throwable[] getSuppressed() {
        return (suppressedExceptions != null)
                ? suppressedExceptions.toArray(new Throwable[suppressedExceptions.size()])
                : EmptyArray.THROWABLE;
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        // ensure the stackTrace field is initialized
        getInternalStackTrace();
        out.defaultWriteObject();
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        if (suppressedExceptions != null) {
            // the deserialized list may be unmodifiable, so just create a mutable copy
            suppressedExceptions = new ArrayList<Throwable>(suppressedExceptions);
        }
    }

    /*
     * Creates a compact, VM-specific collection of goodies, suitable for
     * storing in the "stackState" field, based on the current thread's
     * call stack.
     */
    private static native Object nativeFillInStackTrace();

    /*
     * Creates an array of StackTraceElement objects from the data held
     * in "stackState".
     */
    private static native StackTraceElement[] nativeGetStackTrace(Object stackState);
}
