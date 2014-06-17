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

import java.util.Enumeration;


/**
 * {@code IdentityScope} represents a scope for {@link Identity} objects.
 *
 * @deprecated Use {@link Principal}, {@link KeyStore} and the {@code java.security.cert} package
 * instead.
 */
@Deprecated
public abstract class IdentityScope extends Identity {

    private static final long serialVersionUID = -2337346281189773310L;

    // systemScope holds reference to the current system scope
    private static IdentityScope systemScope;

    /**
     * Constructs a new instance of {@code IdentityScope}.
     */
    protected IdentityScope() {
    }

    /**
     * Constructs a new instance of {@code IdentityScope} with the specified
     * name.
     *
     * @param name
     *            the name of this {@code IdentityScope}.
     */
    public IdentityScope(String name) {
        super(name);
    }

    /**
     * Constructs a new instance of {@code IdentityScope} with the specified
     * name and the specified scope.
     *
     * @param name
     *            the name of this {@code IdentityScope}.
     * @param scope
     *            the scope of this {@code IdentityScope}.
     * @throws KeyManagementException
     *             if an identity with the same key already exists.
     */
    public IdentityScope(String name, IdentityScope scope)
            throws KeyManagementException {
        super(name, scope);
    }

    /**
     * Returns the system's scope.
     *
     * @return the system's scope.
     */
    public static IdentityScope getSystemScope() {
        /*
         * Test shows that the implementation class name is read from security property
         * "system.scope", and the class is only loaded from boot classpath. No default
         * implementation as fallback, i.e., return null if fails to init an instance.
         */
        if (systemScope == null) {
            String className = Security.getProperty("system.scope");
            if(className != null){
                try {
                    systemScope = (IdentityScope) Class.forName(className).newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return systemScope;
    }

    /**
     * Sets the system's scope.
     *
     * @param scope
     *            the scope to set.
     */
    protected static void setSystemScope(IdentityScope scope) {
        systemScope = scope;
    }

    /**
     * Returns the number of {@code Identity} objects in this scope.
     *
     * @return the number of {@code Identity} objects in this scope.
     */
    public abstract int size();

    /**
     * Returns the {@code Identity} with the specified name or {@code null} if
     * no {@code Identity} with the specified name is present in this scope.
     *
     * @param name
     *            the name of the {@code Identity} to be returned.
     * @return the {@code Identity} with the specified name or {@code null} if
     *         not present.
     */
    public abstract Identity getIdentity(String name);

    /**
     * Returns the {@code Identity} with the name of the specified principal or
     * {@code null} if no {@code Identity} with the name of the specified
     * principal is present in this scope.
     *
     * @param principal
     *            the {@code Principal} whose name is used to lookup the {@code
     *            Identity} to be returned.
     * @return the {@code Identity} with the specified name or {@code null} if
     *         not present.
     */
    public Identity getIdentity(Principal principal) {
        return getIdentity(principal.getName());
    }

    /**
     * Returns the {@code Identity} which is associated with the specified key
     * or {@code null} if no {@code Identity} associated with the specified key
     * is present in this scope.
     *
     * @param key
     *            the {@code PublicKey} of the {@code Identity} to be returned.
     * @return the {@code Identity} associated with the specified key or {@code
     *         null} if not present.
     */
    public abstract Identity getIdentity(PublicKey key);

    /**
     * Adds an {@code Identity} to this {@code IdentityScope}.
     *
     * @param identity
     *            the {@code Identity} to be added.
     * @throws KeyManagementException
     *             if the specified {@code Identity} is invalid or an identity
     *             with the same key already exists.
     */
    public abstract void addIdentity(Identity identity)
            throws KeyManagementException;

    /**
     * Removes an {@code Identity} from this {@code IdentityScope}.
     *
     * @param identity
     *            the {@code Identity} to be removed.
     * @throws KeyManagementException
     *             if the {@code Identity} is not present in this scope.
     */
    public abstract void removeIdentity(Identity identity)
            throws KeyManagementException;

    /**
     * Returns an {@code Enumeration} over the {@code Identity} objects in this
     * {@code IdentityScope}.
     *
     * @return an {@code Enumeration} over the {@code Identity} objects in this
     *         {@code IdentityScope}.
     */
    public abstract Enumeration<Identity> identities();

    /**
     * Returns a string containing a concise, human-readable description of this
     * {@code IdentityScope}.
     *
     * @return a printable representation for this {@code IdentityScope}.
     */
    @Override
    public String toString() {
        return new StringBuilder(super.toString())
                .append("[").append(size()).append("]").toString();
    }
}
