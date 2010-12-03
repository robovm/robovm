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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * {@code PermissionCollection} is the common base class for all collections
 * that provide a convenient method for determining whether or not a given
 * permission is implied by any of the permissions present in this collection.
 * <p>
 * A {@code PermissionCollection} is typically created by using the
 * {@link Permission#newPermissionCollection()} factory method. If the mentioned
 * method returns {@code null}, then a {@code PermissionCollection} of any type
 * can be used. If a collection is returned, it must be used for holding several
 * permissions of the particular type.
 * <p>
 * Subclasses must be implemented thread save.
 */
public abstract class PermissionCollection implements Serializable {

    private static final long serialVersionUID = -6727011328946861783L;

    private boolean readOnly; // = false;

    /**
     * Adds the specified {@code Permission} to this collection.
     * 
     * @param permission
     *            the {@code Permission} to add.
     * @throws IllegalStateException
     *             if the collection is read only.
     */
    public abstract void add(Permission permission);

    /**
     * Returns an enumeration over all {@link Permission}s encapsulated by this
     * {@code PermissionCollection}.
     * 
     * @return an enumeration over all {@link Permission}s.
     */
    public abstract Enumeration<Permission> elements();

    /**
     * Indicates whether the specified permission is implied by this {@code
     * PermissionCollection}.
     * 
     * @param permission
     *            the permission to check.
     * @return {@code true} if the given permission is implied by the
     *         permissions in this collection, {@code false} otherwise.
     */
    public abstract boolean implies(Permission permission);

    /**
     * Indicates whether new permissions can be added to this {@code
     * PermissionCollection}.
     * 
     * @return {@code true} if the receiver is read only, {@code false} if new
     *         elements can still be added to this {@code PermissionCollection}.
     */
    public boolean isReadOnly() {
        return readOnly;
    }

    /**
     * Marks this {@code PermissionCollection} as read only, so that no new
     * permissions can be added to it.
     */
    public void setReadOnly() {
        readOnly = true;
    }

    /**
     * Returns a string containing a concise, human-readable description of this
     * {@code PermissionCollection}.
     * 
     * @return a printable representation for this {@code PermissionCollection}.
     */
    @Override
    public String toString() {
        List<String> elist = new ArrayList<String>(100);
        Enumeration<Permission> elenum = elements();
        String superStr = super.toString();
        int totalLength = superStr.length() + 5;
        if (elenum != null) {
            while (elenum.hasMoreElements()) {
                String el = elenum.nextElement().toString();
                totalLength += el.length();
                elist.add(el);
            }
        }
        int esize = elist.size();
        totalLength += esize * 4;
        StringBuilder result = new StringBuilder(totalLength).append(superStr)
            .append(" ("); //$NON-NLS-1$
        for (int i = 0; i < esize; i++) {
            result.append("\n ").append(elist.get(i).toString()); //$NON-NLS-1$
        }
        return result.append("\n)\n").toString(); //$NON-NLS-1$
    }
}
