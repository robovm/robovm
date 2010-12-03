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
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamField;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.apache.harmony.security.internal.nls.Messages;

/**
 * {@code UnresolvedPermissionCollection} represents a specific {@code
 * PermissionCollection} for storing {@link UnresolvedPermission} instances.
 * Contained elements are grouped by their target type.
 */
final class UnresolvedPermissionCollection extends PermissionCollection {

    private static final long serialVersionUID = -7176153071733132400L;

    private static final ObjectStreamField[] serialPersistentFields = { 
        new ObjectStreamField("permissions", Hashtable.class), }; //$NON-NLS-1$

    // elements of the collection.
    private transient Map klasses = new HashMap();

    /**
     * Adds an unresolved permission to this {@code
     * UnresolvedPermissionCollection}.
     * 
     * @param permission
     *            the permission to be added.
     * @throws SecurityException
     *             if this collection is read only.
     * @throws IllegalArgumentException
     *             if {@code permission} is {@code null} or not an {@code
     *             UnresolvedPermission}.
     */
    public void add(Permission permission) {
        if (isReadOnly()) {
            throw new SecurityException(Messages.getString("security.15")); //$NON-NLS-1$
        }
        if (permission == null
            || permission.getClass() != UnresolvedPermission.class) {
            throw new IllegalArgumentException(Messages.getString("security.16", //$NON-NLS-1$
                permission));
        }
        synchronized (klasses) {
            String klass = permission.getName();
            Collection klassMates = (Collection)klasses.get(klass);
            if (klassMates == null) {
                klassMates = new HashSet();
                klasses.put(klass, klassMates);
            }
            klassMates.add(permission);
        }
    }

    public Enumeration elements() {
        Collection all = new ArrayList();
        for (Iterator iter = klasses.values().iterator(); iter.hasNext();) {
            all.addAll((Collection)iter.next());
        }
        return Collections.enumeration(all);
    }

    /**
     * Always returns {@code false}.
     * 
     * @return always {@code false}
     * @see UnresolvedPermission#implies(Permission).
     */
    public boolean implies(Permission permission) {
        return false;
    }
    
    /** 
     * Returns true if this collection contains unresolved permissions 
     * with the same classname as argument permission. 
     */
    boolean hasUnresolved(Permission permission) {
        return klasses.containsKey(permission.getClass().getName());
    }

    /**
     * Resolves all permissions of the same class as the specified target
     * permission and adds them to the specified collection. If passed
     * collection is {@code null} and some unresolved permissions were resolved,
     * an appropriate new collection is instantiated and used. All resolved
     * permissions are removed from this unresolved collection, and collection
     * with resolved ones is returned.
     * 
     * @param target
     *            a kind of permissions to be resolved.
     * @param holder
     *            an existing collection for storing resolved permissions.
     * @return a collection containing resolved permissions (if any found)
     */
    PermissionCollection resolveCollection(Permission target,
                                           PermissionCollection holder) {
        String klass = target.getClass().getName();
        if (klasses.containsKey(klass)) {
            synchronized (klasses) {
                Collection klassMates = (Collection)klasses.get(klass);
                for (Iterator iter = klassMates.iterator(); iter.hasNext();) {
                    UnresolvedPermission element = (UnresolvedPermission)iter
                        .next();
                    Permission resolved = element.resolve(target.getClass());
                    if (resolved != null) {
                        if (holder == null) {
                            holder = target.newPermissionCollection();
                            if (holder == null) {
                                holder = new PermissionsHash();
                            }
                        }
                        holder.add(resolved);
                        iter.remove();
                    }
                }
                if (klassMates.size() == 0) {
                    klasses.remove(klass);
                }
            }
        }
        return holder;
    }

    /** 
     * Output fields via default mechanism. 
     */
    private void writeObject(java.io.ObjectOutputStream out) throws IOException {
        Hashtable permissions = new Hashtable();
        for (Iterator iter = klasses.entrySet().iterator(); iter.hasNext();) {
        	Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            permissions.put(key, new Vector(((Collection) entry.getValue())));
        }
        ObjectOutputStream.PutField fields = out.putFields();
        fields.put("permissions", permissions); //$NON-NLS-1$
        out.writeFields();
    }

    /** 
     * Reads the object from stream and checks elements grouping for validity. 
     */
    private void readObject(java.io.ObjectInputStream in) throws IOException,
        ClassNotFoundException {
        ObjectInputStream.GetField fields = in.readFields();
        Map permissions = (Map)fields.get("permissions", null); //$NON-NLS-1$
        klasses = new HashMap();
        synchronized (klasses) {
            for (Iterator iter = permissions.entrySet().iterator(); iter
            	.hasNext();) {
            	Map.Entry entry = (Map.Entry) iter.next();
	            String key = (String) entry.getKey();
	            Collection values = (Collection) entry.getValue();

	            for (Iterator iterator = values.iterator(); iterator.hasNext();) {
	                UnresolvedPermission element =
	                        (UnresolvedPermission) iterator.next();

	                if (!element.getName().equals(key)) {
	                    throw new InvalidObjectException(
	                        Messages.getString("security.22")); //$NON-NLS-1$
	                }
	            }
	            klasses.put(key, new HashSet(values));
	        }
        }
    }
}