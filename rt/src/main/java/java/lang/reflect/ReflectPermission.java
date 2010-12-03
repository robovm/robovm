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

package java.lang.reflect;

import java.security.BasicPermission;

/**
 * A {@code ReflectPermission} object represents a permission to access
 * operations in the reflection layer.
 */
public final class ReflectPermission extends BasicPermission {

    private static final long serialVersionUID = 7412737110241507485L;

    /**
     * Constructs a new {@code ReflectPermission} instance with the specified
     * name.
     * 
     * @param permissionName
     *            the name of the new permission
     * @throws IllegalArgumentException
     *             if {@code name} is empty
     * @throws NullPointerException
     *             if {@code name} is {@code null}
     */
    public ReflectPermission(String permissionName) {
        super(permissionName);
    }

    /**
     * Constructs a new {@code ReflectPermission} instance with the specified
     * name and action list. The action list will be ignored.
     * 
     * @param name
     *            the name of the new permission
     * @param actions
     *            this parameter will be ignored
     * @throws IllegalArgumentException
     *             if {@code name} is empty
     * @throws NullPointerException
     *             if {@code name} is {@code null}
     */
    public ReflectPermission(String name, String actions) {
        super(name, actions);
    }
}
