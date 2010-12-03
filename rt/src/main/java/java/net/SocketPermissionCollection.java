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

package java.net;

import java.security.Permission;
import java.security.PermissionCollection;
import java.util.Enumeration;
import java.util.Vector;

/**
 * This class represents a list of {@code SocketPermission} objects and provides
 * a method to check whether or not a specific permission is implied by this
 * {@code SocketPermissionCollection}.
 */
final class SocketPermissionCollection extends PermissionCollection {

    private static final long serialVersionUID = 2787186408602843674L;

    private Vector<Permission> permissions = new Vector<Permission>();

    // Constructs a new instance of this class.
    public SocketPermissionCollection() {
        super();
    }

    // Adds the argument to the collection.
    @Override
    public void add(Permission permission) {
        if (isReadOnly()) {
            throw new IllegalStateException();
        }
        if (!(permission instanceof SocketPermission)) {
            throw new IllegalArgumentException(permission.toString());
        }
        permissions.addElement(permission);
    }

    // Returns an enumeration of the permissions
    @Override
    public Enumeration<Permission> elements() {
        return permissions.elements();
    }

    /**
     * Returns whether this permission collection implies {@code permission}.
     * Basically it tests whether {@code permission} is the subset of this
     * collection.
     */
    @Override
    public boolean implies(Permission permission) {
        if (!(permission instanceof SocketPermission)) {
            return false;
        }
        SocketPermission sp, argPerm = (SocketPermission) permission;
        int pmask = argPerm.actionsMask;
        int allMask = 0;
        int i = 0, count = permissions.size();
        while ((i < count) && ((allMask & pmask) != pmask)) {
            sp = (SocketPermission) permissions.elementAt(i);
            if (sp.checkHost(argPerm)) {
                if ((sp.actionsMask & SocketPermission.SP_RESOLVE) == SocketPermission.SP_RESOLVE) {
                    allMask |= SocketPermission.SP_RESOLVE;
                }
                // Only set flags if the port range and host can be implied
                if ((argPerm.portMin >= sp.portMin)
                        && (argPerm.portMax <= sp.portMax)) {
                    if ((sp.actionsMask & SocketPermission.SP_CONNECT) == SocketPermission.SP_CONNECT) {
                        allMask |= SocketPermission.SP_CONNECT;
                    }
                    if ((sp.actionsMask & SocketPermission.SP_ACCEPT) == SocketPermission.SP_ACCEPT) {
                        allMask |= SocketPermission.SP_ACCEPT;
                    }
                    if ((sp.actionsMask & SocketPermission.SP_LISTEN) == SocketPermission.SP_LISTEN) {
                        allMask |= SocketPermission.SP_LISTEN;
                    }
                }
            }
            ++i;
        }

        return (allMask & pmask) == pmask;
    }
}
