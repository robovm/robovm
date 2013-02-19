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

package java.lang;

/**
 * Thrown when the VM notices that a program tries to create a new
 * instance of a class which has no visible constructors from the location where
 * {@code new} is invoked.
 * <p>
 * Note that this can only occur when inconsistent class files are being loaded.
 */
public class InstantiationError extends IncompatibleClassChangeError {
    private static final long serialVersionUID = -4885810657349421204L;

    /**
     * Constructs a new {@code InstantiationError} that includes the current
     * stack trace.
     */
    public InstantiationError() {
    }

    /**
     * Constructs a new {@code InstantiationError} with the current stack trace
     * and the specified detail message.
     *
     * @param detailMessage
     *            the detail message for this error.
     */
    public InstantiationError(String detailMessage) {
        super(detailMessage);
    }

    /**
     * Constructs a new {@code InstantiationError} with the current stack trace
     * and the class that caused this error.
     *
     * @param clazz
     *            the class that can not be instantiated.
     */
    InstantiationError(Class<?> clazz) {
        super(clazz.getName());
    }

}
