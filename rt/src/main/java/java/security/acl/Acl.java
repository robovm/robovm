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

package java.security.acl;

import java.security.Principal;
import java.util.Enumeration;

/**
 * The <i>Access Control List</i> (<b>ACL</b>) interface definition.
 * <p>
 * An ACL is a set of {@link AclEntry} objects.
 * <p>
 * An {@code AclEntry} is a list of {@link Permission}s that are granted
 * (<i>positive</i>) or denied
 * (<i>negative</i>) to a {@link Principal}.
 * <p>
 * An {@code Acl} has a list of owners ({@link Owner}) which are principals as
 * well {@code Principal}. Only those principals which are the {@code Acl}'s
 * owners are allowed to modify the {@code
 * Acl}.
 * <p>
 * The <i>ACL</i> has to conform to the following rules:
 * <ul>
 * <li>For each {@code Principal} there can be only one <i>positive</i> and one
 * <i>negative</i> {@code AclEntry}.</li>
 * <li>If the two {@code AclEntry}'s (<i>positive</i> and <i>negative</i>) for a
 * specific {@code Principal} grant and deny the same {@code Permission} to that
 * {@code Principal}, then that {@code Permission} is treated as
 * neither granted nor denied to that {@code Principal}.</li>
 * <li>Permissions associated with an individual {@code Principal} always
 * override permissions of the group(s) to which the individual belongs.</li>
 * <li>If there is no {@code AclEntry} associated with a specific {@code
 * Principal}, then it is interpreted as an empty list of permissions.</li>
 * </ul>
 */
public interface Acl extends Owner {

    /**
     * Sets the name of this <i>ACL</i> instance.
     *
     * @param caller
     *            the invoking {@code Principal}.
     * @param name
     *            the name to be set.
     * @throws NotOwnerException
     *             if the invoking {@code Principal} is not an owner of this
     *             <i>ACL</i>.
     */
    void setName(Principal caller, String name) throws NotOwnerException;

    /**
     * Returns the name of this <i>ACL</i> instance.
     *
     * @return the name of this <i>ACL</i> instance.
     */
    String getName();

    /**
     * Adds an {@code AclEntry} to this <i>ACL</i> instance.
     * <p>
     * If the <i>ACL</i> already has an {@code AclEntry} of the same type (<i>
     * positive</i> or <i>negative</i>) and principal, then the new entry is not added.
     *
     * @param caller
     *            the invoking {@code Principal}.
     * @param entry
     *            the ACL entry to add.
     * @return {@code true} if the entry is added, {@code false} if there is
     *             already an entry of the same type for the same principal
     * @throws NotOwnerException
     *             if the invoking {@code Principal} is not an owner of this
     *             <i>ACL</i>.
     */
    boolean addEntry(Principal caller, AclEntry entry) throws NotOwnerException;

    /**
     * Removes an {@code AclEntry} from this <i>ACL</i> instance.
     *
     * @param caller
     *            the invoking {@code Principal}.
     * @param entry
     *            the ACL entry to remove.
     * @return {@code true} if the entry is removed, {@code false} if the entry
     *            is not in this <i>ACL</i>.
     * @throws NotOwnerException
     *             if the invoking {@code Principal} is not an owner of this
     *             <i>ACL</i>.
     */
    boolean removeEntry(Principal caller, AclEntry entry)
                throws NotOwnerException;

    /**
     * Returns the set of allowed permissions for the specified {@code
     * Principal}.
     * <p>
     * If the specified principal has no entry in this ACL, an empty set is
     * returned.
     * <p>
     * The allowed permissions are collected according to the following rules:
     * <ul>
     * <li>The two permission lists (<i>positive</i> and <i>negative</i>) of the
     * principal's groups ({@link Group}) are collected. The positive (granted)
     * permissions are the union of all group's positive permissions that the
     * principal belongs to, the negative (denied) permissions are the union of
     * all group's negative permissions that the principal belongs to. If a
     * specific permission is in both the positive and the negative list, it is
     * removed from both lists.</li>
     * <li>The individual permissions (<i>positive</i> and <i>negative</i>) of
     * the principal override the group permissions. The positive individual
     * permissions override the group's negative permissions and the negative
     * individual permissions override the grpup's positive permissions.</li>
     * </ul>
     *
     * @param user
     *            the principal to get the allowed permissions for.
     * @return the set of allowed permissions for the specified principal.
     */
    Enumeration<Permission> getPermissions(Principal user);

    /**
     * Returns an {@code Enumeration} of the {@code AclEntry} of this
     * <i>ACL</i>.
     *
     * @return an {@code Enumeration} of the {@code AclEntry} of this
     *         <i>ACL</i>.
     */
    Enumeration<AclEntry> entries();

    /**
     * Checks whether the specified principal is granted the specified
     * permission.
     * <p>
     * The list of granted permissions is determined according to the rules
     * specified by {@code getPermissions}.
     *
     * @param principal
     *            the principal the check the permissions for.
     * @param permission
     *            the permission to check for the principal.
     * @return {@code true} if the principal is granted the permission,
     *            otherwise {@code false}.
     * @see #getPermissions(Principal)
     */
    boolean checkPermission(Principal principal, Permission permission);

    /**
     * Returns the string representation of this ACL.
     *
     * @return the string representation of this ACL.
     */
    String toString();
}
