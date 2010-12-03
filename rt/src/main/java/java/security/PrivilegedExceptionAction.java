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
 * {@code PrivilegedAction} represents an action, that can be executed
 * privileged regarding access control. Instances of {@code PrivilegedAction}
 * can be executed invoking {@code AccessController.doPrivileged()}.
 *
 * @see AccessController
 * @see AccessController#doPrivileged(PrivilegedExceptionAction)
 * @see AccessController#doPrivileged(PrivilegedExceptionAction,
 *      AccessControlContext)
 * @see PrivilegedAction
 */
public interface PrivilegedExceptionAction<T> {
    /**
     * Returns the result of running the action.
     *
     * @return the result of running the action
     * @throws Exception
     *             if an exception occurred.
     */
    T run() throws Exception;
}
