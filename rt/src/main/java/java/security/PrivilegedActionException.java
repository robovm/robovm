/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package java.security;

/**
 * {@code PrivilegedActionException} wraps exceptions which are thrown from
 * within privileged operations.
 * <p>
 * Privileged actions which can throw exceptions are of type {@code
 * PrivilegedExceptionAction} and are thrown by
 * <ul>
 * {@code AccessController#doPrivileged(PrivilegedExceptionAction)}<br>
 * {@code AccessController#doPrivileged(PrivilegedExceptionAction,
 * AccessControlContext)} </br>
 * </ul>
 *
 * @see PrivilegedExceptionAction
 * @see AccessController#doPrivileged(PrivilegedExceptionAction)
 * @see AccessController#doPrivileged(PrivilegedExceptionAction,
 *      AccessControlContext)
 */
public class PrivilegedActionException extends Exception {

    private static final long serialVersionUID = 4724086851538908602l;

    private Exception exception;

    /**
     * Constructs a new instance of {@code PrivilegedActionException} with the
     * cause.
     *
     * @param ex
     *            the exception which is the cause for this exception.
     */
    public PrivilegedActionException(Exception ex) {
        super(ex);
        this.exception = ex;
    }

    /**
     * Returns the exception that was thrown by a
     * {@code PrivilegedExceptionAction}.
     *
     * @return the exception that was thrown by a
     *         {@code PrivilegedExceptionAction}.
     */
    public Exception getException() {
        return exception; // return ( getCause() instanceof Exception ) ?
        // getCause() : null;
    }

    /**
     * Returns the exception that was thrown by a
     * {@code PrivilegedExceptionAction}.
     *
     * @return the exception that was thrown by a
     *         {@code PrivilegedExceptionAction}.
     */
    @Override
    public Throwable getCause() {
        return exception;
    }

    /**
     * Returns a string containing a concise, human-readable description of this
     * {@code PrivilegedActionException}.
     *
     * @return a printable representation for this {@code
     *         PrivilegedActionException}.
     */
    @Override
    public String toString() {
        String s = getClass().getName();
        return exception == null ? s : s + ": " + exception; //$NON-NLS-1$
    }

}
