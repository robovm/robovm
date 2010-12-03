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

package javax.security.auth;

import java.security.AccessController;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.PrivilegedAction;

import org.apache.harmony.security.fortress.PolicyUtils;
import org.apache.harmony.auth.DefaultSubjectPolicy;
import org.apache.harmony.auth.internal.nls.Messages;

/**
 * @deprecated Use
 *             {@link java.security.Policy#getPermissions(java.security.ProtectionDomain)}
 *             and
 *             {@link java.security.ProtectionDomain#ProtectionDomain(java.security.CodeSource, java.security.PermissionCollection, ClassLoader, java.security.Principal[])}
 *             to establish a policy's permissions for a principal.
 */
@Deprecated
public abstract class Policy {
    // Key to security properties, defining default policy provider.
    private static final String POLICY_PROVIDER = "auth.policy.provider"; //$NON-NLS-1$

    // The AuthPermission required to set custom Policy.
    private static final AuthPermission SET_POLICY = new AuthPermission("setPolicy"); //$NON-NLS-1$

    // The AuthPermission required to get current Policy.
    private static final AuthPermission GET_POLICY = new AuthPermission("getPolicy"); //$NON-NLS-1$

    // the current policy object
    private static Policy activePolicy;

    public abstract PermissionCollection getPermissions(Subject subject, CodeSource cs);

    public abstract void refresh();

    protected Policy() {
        super();
    }

    public static Policy getPolicy() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(GET_POLICY);
        }
        return getAccessiblePolicy();

    }

    /**
     * Shortcut accessor for friendly classes, to skip security checks. If
     * active policy was set to <code>null</code>, tries to load a default
     * provider, so this method never returns <code>null</code>. <br>
     * This method is synchronized with setPolicy()
     */
    static Policy getAccessiblePolicy() {
        Policy current = activePolicy;
        if (current == null) {
            synchronized (Policy.class) {
                // double check in case value has been reassigned
                // while we've been awaiting monitor
                if (activePolicy == null) {
                    activePolicy = getDefaultProvider();
                }
                return activePolicy;
            }
        }
        return current;
    }

    /**
     * Reads name of default policy provider from security.properties, loads the
     * class and instantiates the provider. In case of any exception, wraps it
     * with SecurityException and throws further.
     */
    private static final Policy getDefaultProvider() {
        final String defaultClass = AccessController
                .doPrivileged(new PolicyUtils.SecurityPropertyAccessor(POLICY_PROVIDER));

        if (defaultClass == null) {
            return new DefaultSubjectPolicy();
        }

        Object policy = AccessController.doPrivileged(new PrivilegedAction<Object>() {
            public Object run() {
                try {
                    return Class
                            .forName(defaultClass, true, ClassLoader.getSystemClassLoader())
                            .newInstance();
                } catch (Exception e) {
                    SecurityException se = new SecurityException(Messages.getString("auth.08")); //$NON-NLS-1$
                    se.initCause(e);
                    throw se;
                }
            }
        });

        if (!(policy instanceof Policy)) {
            throw new SecurityException(Messages.getString("auth.08")); //$NON-NLS-1$
        }
        return (Policy) policy;
    }

    public static void setPolicy(Policy policy) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(SET_POLICY);
        }
        synchronized (Policy.class) {
            activePolicy = policy;
        }
    }
}
