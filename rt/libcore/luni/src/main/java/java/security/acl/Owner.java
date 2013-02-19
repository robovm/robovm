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

/**
 * The interface to manage owners of objects that require ownership.
 *
 * @see Acl
 * @see Principal
 */
public interface Owner {

    /**
     * Adds a principal to the list of owners.
     *
     * @param caller
     *            the invoking principal.
     * @param owner
     *            the owner to added.
     * @return {@code true} if the owner was added, {@code false} if it was already an owner.
     * @throws NotOwnerException
     *             if the invoking principal is not an owner.
     */
    boolean addOwner(Principal caller, Principal owner)
                 throws NotOwnerException;

    /**
     * Removes a principal from the list of owners.
     *
     * @param caller
     *            the invoking principal.
     * @param owner
     *            the owner to be removed.
     * @return {@code true} if the owner was removed, {@code false} if it was not an owner.
     * @throws NotOwnerException
     *             if the invoking principal is not an owner.
     * @throws LastOwnerException
     *             if the owner to be removed is the last owner and hence removing it
     *             would make this object owner-less.
     */
    boolean deleteOwner(Principal caller, Principal owner)
                throws NotOwnerException, LastOwnerException;

    /**
     * Checks whether the specified principal is an owner of this object.
     *
     * @param owner
     *            the principal to check.
     * @return {@code true} if the specified principal is an owner, otherwise {@code false}.
     */
    boolean isOwner(Principal owner);
}
