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

/**
* @author Alexey V. Varlamov
*/

package org.apache.harmony.security;

import java.security.Principal;

import org.apache.harmony.security.internal.nls.Messages;

/**
 * Descriptive implementation of Principal, which holds a name and a classname
 * of unresolved principal. It is used to define an arbitrary Principal which
 * may be not yet instantiated and authenticated. 
 * <br>
 * This concept is somewhat similar to UnresolvedPermission. A principal-based 
 * policy may grant permissions depending on what Principals own the current 
 * execution thread. So the policy refers to this model definition of 
 * acceptable principal and compares it with the actual principal. 
 * 
 * @see org.apache.harmony.security.PolicyEntry
 * @see org.apache.harmony.security.DefaultPolicy
 */
public final class UnresolvedPrincipal implements Principal {

    /** 
     * Wildcard value denotes any class and/or any name. 
     */
    public static final String WILDCARD = DefaultPolicyScanner.PrincipalEntry.WILDCARD;

    // Class name
    private final String klass;

    // Principal name
    private final String name;

    /**
     * Constructs a new definition of a Principal with specified
     * parameters. 
     * @param klass fully qualified class name, may be wildcard
     * @param name name of principal, may be wildcard
     * @throws IllegalArgumentException if <code>klass</code> value 
     * is <code>null </code> or is empty string 
     */
    public UnresolvedPrincipal(String klass, String name) {
        if (klass == null || klass.length() == 0) {
            throw new IllegalArgumentException(Messages.getString("security.91")); //$NON-NLS-1$
        }

        this.klass = klass;
        this.name = name;
    }

    /**
     * Returns name of a modeled Principal, or wildcard 
     * if any name is acceptable.
     */
    public String getName() {
        return name;
    }

    /** 
     * Returns fully qualified class name of a modeled Principal,
     * or wildcard if any class is acceptable. 
     */
    public String getClassName() {
        return klass;
    }

    /**
     * Returns <code>true</code> if compared object is a Principal
     * matching this definition, or if it is an UnresolvedPrincipal, 
     * which defines the same Principal; <code>false</code> otherwise.  
     */
    public boolean equals(Object that) {
        if (that instanceof UnresolvedPrincipal) {
            UnresolvedPrincipal up = (UnresolvedPrincipal) that;
            return klass.equals(up.klass)
                    && (name == null ? up.name == null : name.equals(up.name));
        }
        if (that instanceof Principal) {
            return implies((Principal) that);
        }
        return false;
    }

    /** 
     * Returns <code>true</code> if compared object is a Principal
     * exactly matching this definition. Namely, if the fully qualified name 
     * of class of passed Principal is equal to the class name value
     * of this definition and the name of passed Principal is equal to 
     * the name value of this definition, or if this definition allows
     * any class or name, respectively.  
     * Otherwise returns <code>false</code> .
     */
    public boolean implies(Principal another) {
        return (another != null)
                && (WILDCARD.equals(klass) 
                    || klass.equals(another.getClass().getName())
                && (WILDCARD.equals(name) 
                    || (name == null ? another.getName() == null 
                        : name.equals(another.getName()))));
    }

    /** 
     * Returns the hash code value for this object. 
     */
    public int hashCode() {
        int hash = 0;
        if (name != null) {
            hash ^= name.hashCode();
        }
        if (klass != null) {
            hash ^= klass.hashCode();
        }
        return hash;
    }

    /** 
     * Returns a string describing this model of Principal.
     * The format is 'Principal classname &quot;name&quot;'.
     */
    public String toString() {
        return "Principal " + klass + " \"" + name + "\""; //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
    }
}
