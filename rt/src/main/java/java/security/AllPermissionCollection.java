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

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.Enumeration;
import java.util.NoSuchElementException;

import org.apache.harmony.security.internal.nls.Messages;

/**
 * Specific {@code PermissionCollection} for storing {@code AllPermission}s. All
 * instances of {@code AllPermission} are equivalent, so it is enough to store a
 * single added instance.
 * 
 * @see AllPermission
 */
final class AllPermissionCollection extends PermissionCollection {

    private static final long serialVersionUID = -4023755556366636806L;

    private static final ObjectStreamField[] serialPersistentFields = { new ObjectStreamField(
        "all_allowed", Boolean.TYPE), }; //$NON-NLS-1$

    // Single element of collection.
    private transient Permission all;

    /**
     * Adds an {@code AllPermission} to the collection.
     */
    @Override
    public void add(Permission permission) {
        if (isReadOnly()) {
            throw new SecurityException(Messages.getString("security.15")); //$NON-NLS-1$
        }
        if (!(permission instanceof AllPermission)) {
            throw new IllegalArgumentException(Messages.getString("security.16", //$NON-NLS-1$
                permission));
        }
        all = permission;
    }

    /**
     * Returns the enumeration of the collection.
     */
    @Override
    public Enumeration<Permission> elements() {
        return new SingletonEnumeration<Permission>(all);
    }

    /**
     * An auxiliary implementation for enumerating a single object.
     * 
     */
    final static class SingletonEnumeration<E> implements Enumeration<E> {

        private E element;

        /**
         * Constructor taking the single element.
         * @param single the element
         */
        public SingletonEnumeration(E single) {
            element = single;
        }

        /**
         * Returns true if the element is not enumerated yet.
         */
        public boolean hasMoreElements() {
            return element != null;
        }

        /**
         * Returns the element and clears internal reference to it.
         */
        public E nextElement() {
            if (element == null) {
                throw new NoSuchElementException(Messages.getString("security.17")); //$NON-NLS-1$
            }
            E last = element;
            element = null;
            return last;
        }
    }

    /**
     * Indicates whether the argument permission is implied by the receiver.
     * {@code AllPermission} objects imply all other permissions.
     * 
     * @return boolean {@code true} if the argument permission is implied by the
     *         receiver, and {@code false} if it is not.
     * @param permission
     *            the permission to check.
     */
    @Override
    public boolean implies(Permission permission) {
        return all != null;
    }

    /**
     * Writes the fields according to expected format, adding the boolean field
     * {@code all_allowed} which is {@code true} if this collection is not
     * empty.
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("all_allowed", all != null); //$NON-NLS-1$
        out.writeFields();
    }

    /**
     * Restores internal state.
     */
    private void readObject(java.io.ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        if (fields.get("all_allowed", false)) { //$NON-NLS-1$
            all = new AllPermission();
        }
    }
}