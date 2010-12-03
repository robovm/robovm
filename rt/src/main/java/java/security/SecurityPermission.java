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
 * {@code SecurityPermission} objects guard access to the mechanisms which
 * implement security. Security permissions have names, but not actions.
 */
public final class SecurityPermission extends BasicPermission {

    private static final long serialVersionUID = 5236109936224050470L;

    /**
     * Constructs a new instance of {@code SecurityPermission} with the given
     * name.
     *
     * @param name
     *            the name of the permission.
     */
    public SecurityPermission(String name) {
        super(name);
    }

    /**
     * Constructs a new instance of {@code SecurityPermission} with the given
     * {@code name} and {@code action} list. The action list is ignored - it is
     * existing for compatibility reasons only.
     *
     * @param name
     *            the name of the permission.
     * @param action
     *            ignored.
     */
    public SecurityPermission(String name, String action) {
        super(name, action);
    }
}
