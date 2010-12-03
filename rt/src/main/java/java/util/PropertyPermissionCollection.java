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

package java.util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.security.Permission;
import java.security.PermissionCollection;

/**
 * A {@code PermissionCollection} for holding {@code PropertyPermission}s.
 */
class PropertyPermissionCollection extends PermissionCollection {

    private static final long serialVersionUID = 7015263904581634791L;

    Hashtable<String, Permission> permissions = new Hashtable<String, Permission>(
            30);

    @Override
    public void add(Permission perm) {
        if (!isReadOnly()) {
            Permission prev = permissions.put(perm.getName(), perm);
            /*
             * If the permission already existed but with only "read" or "write"
             * set, then replace with both set.
             */
            if (prev != null && !prev.getActions().equals(perm.getActions())) {
                Permission np = new PropertyPermission(perm.getName(),
                        "read,write"); //$NON-NLS-1$
                permissions.put(perm.getName(), np);
            }
        } else {
            throw new IllegalStateException();
        }
    }

    @Override
    public Enumeration<Permission> elements() {
        return permissions.elements();
    }

    @Override
    public boolean implies(Permission perm) {
        Enumeration<Permission> elemEnum = elements();
        while (elemEnum.hasMoreElements()) {
            if ((elemEnum.nextElement()).implies(perm)) {
                return true;
            }
        }
        /*
         * At this point, the only way it can succeed is if both read and write
         * are set, and these are separately granted by two different
         * permissions with one representing a parent directory.
         */
        return perm.getActions().equals("read,write") //$NON-NLS-1$
                && implies(new PropertyPermission(perm.getName(), "read")) //$NON-NLS-1$
                && implies(new PropertyPermission(perm.getName(), "write")); //$NON-NLS-1$
    }

    private static final ObjectStreamField[] serialPersistentFields = {
            new ObjectStreamField("permissions", Hashtable.class), //$NON-NLS-1$
            new ObjectStreamField("all_allowed", Boolean.TYPE) }; //$NON-NLS-1$

    private void writeObject(ObjectOutputStream stream) throws IOException {
        ObjectOutputStream.PutField fields = stream.putFields();
        fields.put("permissions", permissions); //$NON-NLS-1$
        fields.put("all_allowed", false); //$NON-NLS-1$
        stream.writeFields();
    }

    @SuppressWarnings("unchecked")
    private void readObject(ObjectInputStream stream) throws IOException,
            ClassNotFoundException {
        ObjectInputStream.GetField fields = stream.readFields();
        permissions = (Hashtable<String, Permission>) fields.get(
                "permissions", null); //$NON-NLS-1$
    }
}
