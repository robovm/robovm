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

/**
 * {@code ProtectionDomain} represents all permissions that are granted to a
 * specific code source. The {@link ClassLoader} associates each class with the
 * corresponding {@code ProtectionDomain}, depending on the location and the
 * certificates (encapsulates in {@link CodeSource}) it loads the code from.
 * <p>
 * A class belongs to exactly one protection domain and the protection domain
 * can not be changed during the lifetime of the class.
 */
public class ProtectionDomain {

    // CodeSource for this ProtectionDomain
    private CodeSource codeSource;

    // Static permissions for this ProtectionDomain
    private PermissionCollection permissions;

    // ClassLoader
    private ClassLoader classLoader;

    // Set of principals associated with this ProtectionDomain
    private Principal[] principals;

    // false if this ProtectionDomain was constructed with static 
    // permissions, true otherwise. 
    private boolean dynamicPerms;

    /**
     * Constructs a new instance of {@code ProtectionDomain} with the specified
     * code source and the specified static permissions.
     * <p>
     * If {@code permissions} is not {@code null}, the {@code permissions}
     * collection is made immutable by calling
     * {@link PermissionCollection#setReadOnly()} and it is considered as
     * granted statically to this {@code ProtectionDomain}.
     * <p>
     * The policy will not be consulted by access checks against this {@code
     * ProtectionDomain}.
     * <p>
     * If {@code permissions} is {@code null}, the method {@link
     * ProtectionDomain#implies(Permission)} always returns {@code false}.
     *
     * @param cs
     *            the code source associated with this domain, maybe {@code
     *            null}.
     * @param permissions
     *            the {@code PermissionCollection} containing all permissions to
     *            be statically granted to this {@code ProtectionDomain}, maybe
     *            {@code null}.
     */
    public ProtectionDomain(CodeSource cs, PermissionCollection permissions) {
        this.codeSource = cs;
        if (permissions != null) {
            permissions.setReadOnly();
        }
        this.permissions = permissions;
        //this.classLoader = null;
        //this.principals = null;
        //dynamicPerms = false;
    }

    /**
     * Constructs a new instance of {@code ProtectionDomain} with the specified
     * code source, the permissions, the class loader and the principals.
     * <p>
     * If {@code permissions} is {@code null}, and access checks are performed
     * against this protection domain, the permissions defined by the policy are
     * consulted. If {@code permissions} is not {@code null}, the {@code
     * permissions} collection is made immutable by calling
     * {@link PermissionCollection#setReadOnly()}. If access checks are
     * performed, the policy and the provided permission collection are checked.
     * <p>
     * External modifications of the provided {@code principals} array has no
     * impact on this {@code ProtectionDomain}.
     *
     * @param cs
     *            the code source associated with this domain, maybe {@code
     *            null}.
     * @param permissions
     *            the permissions associated with this domain, maybe {@code
     *            null}.
     * @param cl
     *            the class loader associated with this domain, maybe {@code
     *            null}.
     * @param principals
     *            the principals associated with this domain, maybe {@code
     *            null}.
     */
    public ProtectionDomain(CodeSource cs, PermissionCollection permissions,
            ClassLoader cl, Principal[] principals) {
        this.codeSource = cs;
        if (permissions != null) {
            permissions.setReadOnly();
        }
        this.permissions = permissions;
        this.classLoader = cl;
        if (principals != null) {
            this.principals = new Principal[principals.length];
            System.arraycopy(principals, 0, this.principals, 0,
                    this.principals.length);
        }
        dynamicPerms = true;
    }

    /**
     * Returns the {@code ClassLoader} associated with this {@code
     * ProtectionDomain}.
     *
     * @return the {@code ClassLoader} associated with this {@code
     *         ProtectionDomain}, maybe {@code null}.
     */
    public final ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * Returns the {@code CodeSource} of this {@code ProtectionDomain}.
     *
     * @return the {@code CodeSource} of this {@code ProtectionDomain}, maybe
     *         {@code null}.
     */
    public final CodeSource getCodeSource() {
        return codeSource;
    }

    /**
     * Returns the static permissions that are granted to this {@code
     * ProtectionDomain}.
     *
     * @return the static permissions that are granted to this {@code
     *         ProtectionDomain}, maybe {@code null}.
     */
    public final PermissionCollection getPermissions() {
        return permissions;
    }

    /**
     * Returns the principals associated with this {@code ProtectionDomain}.
     * Modifications of the returned {@code Principal} array has no impact on
     * this {@code ProtectionDomain}.
     *
     * @return the principals associated with this {@code ProtectionDomain}.
     */
    public final Principal[] getPrincipals() {
        if( principals == null ) {
            return new Principal[0];
        }
        Principal[] tmp = new Principal[principals.length];
        System.arraycopy(principals, 0, tmp, 0, tmp.length);
        return tmp;
    }

    /**
     * Indicates whether the specified permission is implied by this {@code
     * ProtectionDomain}.
     * <p>
     * If this {@code ProtectionDomain} was constructed with
     * {@link #ProtectionDomain(CodeSource, PermissionCollection)}, the
     * specified permission is only checked against the permission collection
     * provided in the constructor. If {@code null} was provided, {@code false}
     * is returned.
     * <p>
     * If this {@code ProtectionDomain} was constructed with
     * {@link #ProtectionDomain(CodeSource, PermissionCollection, ClassLoader, Principal[])}
     * , the specified permission is checked against the policy and the
     * permission collection provided in the constructor.
     *
     * @param permission
     *            the permission to check against the domain.
     * @return {@code true} if the specified {@code permission} is implied by
     *         this {@code ProtectionDomain}, {@code false} otherwise.
     */
    public boolean implies(Permission permission) {
        // First, test with the Policy, as the default Policy.implies() 
        // checks for both dynamic and static collections of the 
        // ProtectionDomain passed...
        if (dynamicPerms
                && Policy.getAccessiblePolicy().implies(this, permission)) {
            return true;
        }

        // ... and we get here if 
        // either the permissions are static
        // or Policy.implies() did not check for static permissions
        // or the permission is not implied
        return permissions == null ? false : permissions.implies(permission);
    }

    /**
     * Returns a string containing a concise, human-readable description of the
     * this {@code ProtectionDomain}.
     *
     * @return a printable representation for this {@code ProtectionDomain}.
     */
    @Override
    public String toString() {
        StringBuilder buf = new StringBuilder(200);
        buf.append("ProtectionDomain\n"); //$NON-NLS-1$
        buf.append("CodeSource=").append( //$NON-NLS-1$
                codeSource == null ? "<null>" : codeSource.toString()).append( //$NON-NLS-1$
                "\n"); //$NON-NLS-1$
        buf.append("ClassLoader=").append( //$NON-NLS-1$
                classLoader == null ? "<null>" : classLoader.toString()) //$NON-NLS-1$
                .append("\n"); //$NON-NLS-1$
        if (principals == null || principals.length == 0) {
            buf.append("<no principals>\n"); //$NON-NLS-1$
        } else {
            buf.append("Principals: <\n"); //$NON-NLS-1$
            for (int i = 0; i < principals.length; i++) {
                buf.append("\t").append( //$NON-NLS-1$
                        principals[i] == null ? "<null>" : principals[i] //$NON-NLS-1$
                                .toString()).append("\n"); //$NON-NLS-1$
            }
            buf.append(">"); //$NON-NLS-1$
        }

        //permissions here
        buf.append("Permissions:\n"); //$NON-NLS-1$
        if (permissions == null) {
            buf.append("\t\t<no static permissions>\n"); //$NON-NLS-1$
        } else {
            buf.append("\t\tstatic: ").append(permissions.toString()).append( //$NON-NLS-1$
                    "\n"); //$NON-NLS-1$
        }

        if (dynamicPerms) {
            if (Policy.isSet()) {
                if (canGetPolicy()) {
                    PermissionCollection perms;
                    perms = Policy.getAccessiblePolicy().getPermissions(this);
                    if (perms == null) {
                        buf.append("\t\t<no dynamic permissions>\n"); //$NON-NLS-1$
                    } else {
                        buf.append("\t\tdynamic: ").append(perms.toString()) //$NON-NLS-1$
                                .append("\n"); //$NON-NLS-1$
                    }
                } else {
                    buf.append("\t\t<no rights to retrieve dynamic permissions>\n"); //$NON-NLS-1$
                }
            } else {
                buf.append("\t\t<no dynamic permissions>\n"); //$NON-NLS-1$
            }
        }
        return buf.toString();
    }
    
    /*
     * Returns true if the caller has permission to retrieve the current
     * security policy settings, or false if not.
     */
    private boolean canGetPolicy() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            try {
                sm.checkPermission(Policy.GET_POLICY);
            } catch (SecurityException e) {
                return false;
            }
        }
        return true;
    }
}
