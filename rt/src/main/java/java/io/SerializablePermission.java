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

package java.io;

import java.security.BasicPermission;

/**
 * Is used to enable access to potentially unsafe serialization operations. It
 * does have a name but no action list. The following table lists valid
 * permission names:
 * <table>
 * <tr>
 * <td>enableSubclassImplementation</td>
 * <td>Subclasses can override serialization behavior.</td>
 * </tr>
 * <tr>
 * <td>enableSubstitution</td>
 * <td>Object substitution is allowed.</td>
 * </tr>
 * </table>
 * 
 * @see ObjectStreamConstants
 */
public final class SerializablePermission extends BasicPermission {
    private static final long serialVersionUID = 8537212141160296410L;

    // Serializable field
    @SuppressWarnings("unused")
    private String actions;

    /**
     * Constructs a new {@code SerializablePermission} with the specified name.
     * 
     * @param permissionName
     *            the name of the new permission.
     */
    public SerializablePermission(String permissionName) {
        super(permissionName);
    }

    /**
     * Constructs a new {@code SerializablePermission} with the specified name.
     * The action list is ignored.
     * 
     * @param name
     *            the name of the new permission.
     * @param actions
     *            ignored.
     */
    public SerializablePermission(String name, String actions) {
        super(name, actions);
    }
}
