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

package org.apache.harmony.auth;

import java.io.File;
import java.net.URL;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.Permission;
import java.security.PermissionCollection;
import java.security.Principal;
import java.security.cert.Certificate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.security.auth.AuthPermission;
import javax.security.auth.Policy;
import javax.security.auth.Subject;

import org.apache.harmony.security.PolicyEntry;
import org.apache.harmony.security.fortress.DefaultPolicyParser;
import org.apache.harmony.security.fortress.PolicyUtils;

/**
 * Default implementation for subject-based policy 
 */
@SuppressWarnings("deprecation")
public class DefaultSubjectPolicy extends Policy {

    private static final AuthPermission REFRESH_POLICY = new AuthPermission("refreshPolicy"); //$NON-NLS-1$

    // System property for dynamically added policy location.
    private static final String AUTH_SECURITY_POLICY = "java.security.auth.policy"; //$NON-NLS-1$

    // Prefix for numbered Policy locations specified in security.properties.
    private static final String POLICY_URL_PREFIX = "auth.policy.url."; //$NON-NLS-1$

    // A flag to denote whether this policy object was initialized or not. 
    private boolean isInitialized;

    // A set of PolicyEntries constituting this Policy.
    private Set<PolicyEntry> set;

    // A specific parser for a particular policy file format.
    // The implementation of parse thread-safe, so static instance is used 
    private static final DefaultPolicyParser parser = new DefaultPolicyParser();

    // empty source object for getPermissions method
    private static final CodeSource emptySource = new CodeSource(null, (Certificate[]) null);

    public DefaultSubjectPolicy() {
        super();
        isInitialized = false;
    }

    @Override
    public PermissionCollection getPermissions(Subject subject, CodeSource cs) {
        if (!isInitialized) {
            init();
        }

        Collection<Permission> pc = new HashSet<Permission>();
        Iterator<PolicyEntry> it = set.iterator();

        if (subject != null) {
            int size = subject.getPrincipals().size();
            Principal[] p = new Principal[size];
            subject.getPrincipals().toArray(p);

            if (cs == null) {
                cs = emptySource;
            }

            while (it.hasNext()) {
                PolicyEntry ge = it.next();
                if (ge.impliesCodeSource(cs) && ge.impliesPrincipals(p)) {
                    pc.addAll(ge.getPermissions());
                }
            }
        }
        return PolicyUtils.toPermissionCollection(pc);
    }

    @Override
    public void refresh() {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkPermission(REFRESH_POLICY);
        }
        init();
    }

    private synchronized void init() {

        set = new HashSet<PolicyEntry>();

        Properties system = new Properties(AccessController
                .doPrivileged(new PolicyUtils.SystemKit()));
        system.setProperty("/", File.separator); //$NON-NLS-1$
        URL[] policyLocations = PolicyUtils.getPolicyURLs(system, AUTH_SECURITY_POLICY,
                POLICY_URL_PREFIX);

        for (URL url : policyLocations) {
            try {
                set.addAll(parser.parse(url, system));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        isInitialized = true;
    }
}
