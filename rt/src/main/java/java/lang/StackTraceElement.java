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

import java.io.Serializable;

/**
 * A representation of a single stack frame. Arrays of {@code StackTraceElement}
 * are stored in {@link Throwable} objects to represent the whole state of the
 * call stack at the time a {@code Throwable} gets thrown.
 *
 * @see Throwable#getStackTrace()
 */
public final class StackTraceElement implements Serializable {

    private static final long serialVersionUID = 6992337162326171013L;

    private static final int NATIVE_LINE_NUMBER = -2;

    String declaringClass;

    String methodName;

    String fileName;

    int lineNumber;

    /**
     * Constructs a new {@code StackTraceElement} for a specified execution
     * point.
     *
     * @param cls
     *            the fully qualified name of the class where execution is at.
     * @param method
     *            the name of the method where execution is at.
     * @param file
     *            The name of the file where execution is at or {@code null}.
     * @param line
     *            the line of the file where execution is at, a negative number
     *            if unknown or {@code -2} if the execution is in a native
     *            method.
     * @throws NullPointerException
     *             if {@code cls} or {@code method} is {@code null}.
     */
    public StackTraceElement(String cls, String method, String file, int line) {
        if (cls == null || method == null) {
            throw new NullPointerException();
        }
        declaringClass = cls;
        methodName = method;
        fileName = file;
        lineNumber = line;
    }

    /**
     * <p>
     * Private, nullary constructor for VM use only.
     * </p>
     */
    private StackTraceElement() {
    }

    /**
     * Compares this instance with the specified object and indicates if they
     * are equal. In order to be equal, the following conditions must be
     * fulfilled:
     * <ul>
     * <li>{@code obj} must be a stack trace element,</li>
     * <li>the method names of this stack trace element and of {@code obj} must
     * not be {@code null},</li>
     * <li>the class, method and file names as well as the line number of this
     * stack trace element and of {@code obj} must be equal.</li>
     * </ul>
     *
     * @param obj
     *            the object to compare this instance with.
     * @return {@code true} if the specified object is equal to this
     *         {@code StackTraceElement}; {@code false} otherwise.
     * @see #hashCode
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof StackTraceElement)) {
            return false;
        }
        StackTraceElement castObj = (StackTraceElement) obj;

        /*
         * Unknown methods are never equal to anything (not strictly to spec,
         * but spec does not allow null method/class names)
         */
        if ((methodName == null) || (castObj.methodName == null)) {
            return false;
        }

        if (!getMethodName().equals(castObj.getMethodName())) {
            return false;
        }
        if (!getClassName().equals(castObj.getClassName())) {
            return false;
        }
        String localFileName = getFileName();
        if (localFileName == null) {
            if (castObj.getFileName() != null) {
                return false;
            }
        } else {
            if (!localFileName.equals(castObj.getFileName())) {
                return false;
            }
        }
        if (getLineNumber() != castObj.getLineNumber()) {
            return false;
        }

        return true;
    }

    /**
     * Returns the fully qualified name of the class belonging to this
     * {@code StackTraceElement}.
     *
     * @return the fully qualified type name of the class
     */
    public String getClassName() {
        return (declaringClass == null) ? "<unknown class>" : declaringClass;
    }

    /**
     * Returns the name of the Java source file containing class belonging to
     * this {@code StackTraceElement}.
     *
     * @return the name of the file, or {@code null} if this information is not
     *         available.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Returns the line number in the source for the class belonging to this
     * {@code StackTraceElement}.
     *
     * @return the line number, or a negative number if this information is not
     *         available.
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Returns the name of the method belonging to this {@code
     * StackTraceElement}.
     *
     * @return the name of the method, or "<unknown method>" if this information
     *         is not available.
     */
    public String getMethodName() {
        return (methodName == null) ? "<unknown method>" : methodName;
    }

    @Override
    public int hashCode() {
        /*
         * Either both methodName and declaringClass are null, or neither are
         * null.
         */
        if (methodName == null) {
            // all unknown methods hash the same
            return 0;
        }
        // declaringClass never null if methodName is non-null
        return methodName.hashCode() ^ declaringClass.hashCode();
    }

    /**
     * Indicates if the method name returned by {@link #getMethodName()} is
     * implemented as a native method.
     *
     * @return {@code true} if the method in which this stack trace element is
     *         executing is a native method; {@code false} otherwise.
     */
    public boolean isNativeMethod() {
        return lineNumber == NATIVE_LINE_NUMBER;
    }

    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(80);

        buf.append(getClassName());
        buf.append('.');
        buf.append(getMethodName());

        if (isNativeMethod()) {
            buf.append("(Native Method)");
        } else {
            String fName = getFileName();

            if (fName == null) {
                buf.append("(Unknown Source)");
            } else {
                int lineNum = getLineNumber();

                buf.append('(');
                buf.append(fName);
                if (lineNum >= 0) {
                    buf.append(':');
                    buf.append(lineNum);
                }
                buf.append(')');
            }
        }
        return buf.toString();
    }
}
