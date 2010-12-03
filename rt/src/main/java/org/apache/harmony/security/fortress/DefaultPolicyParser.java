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

package org.apache.harmony.security.fortress;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.security.AccessController;
import java.security.CodeSource;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.Permission;
import java.security.Principal;
import java.security.UnresolvedPermission;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.harmony.security.DefaultPolicyScanner;
import org.apache.harmony.security.PolicyEntry;
import org.apache.harmony.security.UnresolvedPrincipal;
import org.apache.harmony.security.DefaultPolicyScanner.GrantEntry;
import org.apache.harmony.security.DefaultPolicyScanner.KeystoreEntry;
import org.apache.harmony.security.DefaultPolicyScanner.PermissionEntry;
import org.apache.harmony.security.DefaultPolicyScanner.PrincipalEntry;
import org.apache.harmony.security.internal.nls.Messages;


/**
 * This is a basic loader of policy files. It delegates lexical analysis to 
 * a pluggable scanner and converts received tokens to a set of 
 * {@link org.apache.harmony.security.PolicyEntry PolicyEntries}. 
 * For details of policy format, see the 
 * {@link org.apache.harmony.security.DefaultPolicy default policy description}.
 * <br>
 * For ordinary uses, this class has just one public method <code>parse()</code>, 
 * which performs the main task.
 * Extensions of this parser may redefine specific operations separately, 
 * by overriding corresponding protected methods. 
 * <br>
 * This implementation is effectively thread-safe, as it has no field references 
 * to data being processed (that is, passes all the data as method parameters).
 * 
 * @see org.apache.harmony.security.DefaultPolicy
 * @see org.apache.harmony.security.DefaultPolicyScanner
 * @see org.apache.harmony.security.PolicyEntry
 */
public class DefaultPolicyParser {

    // Pluggable scanner for a specific file format
    private final DefaultPolicyScanner scanner;

    /** 
     * Default constructor, 
     * {@link org.apache.harmony.security.DefaultPolicyScanner DefaultPolicyScanner} 
     * is used. 
     */
    public DefaultPolicyParser() {
        scanner = new DefaultPolicyScanner();
    }

    /** 
     * Extension constructor for plugging-in custom scanner.
     */
    public DefaultPolicyParser(DefaultPolicyScanner s) {
        this.scanner = s;
    }

    /**
     * This is the main business method. It manages loading process as follows:
     * the associated scanner is used to parse the stream to a set of 
     * {@link org.apache.harmony.security.DefaultPolicyScanner.GrantEntry composite tokens},
     * then this set is iterated and each token is translated to a PolicyEntry.
     * Semantically invalid tokens are ignored, the same as void PolicyEntries.
     * <br>
     * A policy file may refer to some KeyStore(s), and in this case the first
     * valid reference is initialized and used in processing tokens.   
     * 
     * @param location an URL of a policy file to be loaded
     * @param system system properties, used for property expansion
     * @return a collection of PolicyEntry objects, may be empty
     * @throws Exception IO error while reading location or file syntax error 
     */
    public Collection<PolicyEntry>parse(URL location, Properties system)
            throws Exception {

        boolean resolve = PolicyUtils.canExpandProperties();
        Reader r = new BufferedReader(new InputStreamReader(
                AccessController
                        .doPrivileged(new PolicyUtils.URLLoader(location))));

        Collection<GrantEntry> grantEntries = new HashSet<GrantEntry>();
        List<KeystoreEntry> keystores = new ArrayList<KeystoreEntry>();

        try {
            scanner.scanStream(r, grantEntries, keystores);
        }
        finally {
            r.close();
        }

        //XXX KeyStore could be loaded lazily...
        KeyStore ks = initKeyStore(keystores, location, system, resolve);

        Collection<PolicyEntry> result = new HashSet<PolicyEntry>();
        for (Iterator<GrantEntry> iter = grantEntries.iterator(); iter.hasNext();) {
            DefaultPolicyScanner.GrantEntry ge = iter
                    .next();
            try {
                PolicyEntry pe = resolveGrant(ge, ks, system, resolve);
                if (!pe.isVoid()) {
                    result.add(pe);
                }
            }
            catch (Exception e) {
                // TODO: log warning
            }
        }

        return result;
    }

    /**
     * Translates GrantEntry token to PolicyEntry object. It goes step by step, 
     * trying to resolve each component of the GrantEntry:
     * <ul>
     * <li> If <code>codebase</code> is specified, expand it and construct an URL.
     * <li> If <code>signers</code> is specified, expand it and obtain 
     * corresponding Certificates.
     * <li> If <code>principals</code> collection is specified, iterate over it. 
     * For each PrincipalEntry, expand name and if no class specified, 
     * resolve actual X500Principal from a KeyStore certificate; otherwise keep it 
     * as UnresolvedPrincipal. 
     * <li> Iterate over <code>permissions</code> collection. For each PermissionEntry,
     * try to resolve (see method 
     * {@link #resolvePermission(DefaultPolicyScanner.PermissionEntry, DefaultPolicyScanner.GrantEntry, KeyStore, Properties, boolean) resolvePermission()}) 
     * a corresponding permission. If resolution failed, ignore the PermissionEntry. 
     * </ul>
     * In fact, property expansion in the steps above is conditional and is ruled by
     * the parameter <i>resolve</i>.  
     * <br>
     * Finally a new PolicyEntry is created, which associates the trinity 
     * of resolved URL, Certificates and Principals to a set of granted Permissions.
     * 
     * @param ge GrantEntry token to be resolved
     * @param ks KeyStore for resolving Certificates, may be <code>null</code> 
     * @param system system properties, used for property expansion 
     * @param resolve flag enabling/disabling property expansion
     * @return resolved PolicyEntry
     * @throws Exception if unable to resolve codebase, signers or principals 
     * of the GrantEntry
     * @see DefaultPolicyScanner.PrincipalEntry
     * @see DefaultPolicyScanner.PermissionEntry
     * @see org.apache.harmony.security.PolicyUtils
     */
    protected PolicyEntry resolveGrant(DefaultPolicyScanner.GrantEntry ge,
            KeyStore ks, Properties system, boolean resolve) throws Exception {

        URL codebase = null;
        Certificate[] signers = null;
        Set<Principal>principals = new HashSet<Principal>();
        Set<Permission>permissions = new HashSet<Permission>();
        if (ge.codebase != null) {
            codebase = new URL(resolve ? PolicyUtils.expandURL(ge.codebase,
                    system) : ge.codebase);
        }
        if (ge.signers != null) {
            if (resolve) {
                ge.signers = PolicyUtils.expand(ge.signers, system);
            }
            signers = resolveSigners(ks, ge.signers);
        }
        if (ge.principals != null) {
            for (Iterator<PrincipalEntry> iter = ge.principals.iterator(); iter.hasNext();) {
                DefaultPolicyScanner.PrincipalEntry pe = iter
                        .next();
                if (resolve) {
                    pe.name = PolicyUtils.expand(pe.name, system);
                }
                if (pe.klass == null) {
                    principals.add(getPrincipalByAlias(ks, pe.name));
                } else {
                    principals.add(new UnresolvedPrincipal(pe.klass, pe.name));
                }
            }
        }
        if (ge.permissions != null) {
            for (Iterator<PermissionEntry> iter = ge.permissions.iterator(); iter.hasNext();) {
                DefaultPolicyScanner.PermissionEntry pe = iter
                        .next();
                try {
                    permissions.add(resolvePermission(pe, ge, ks, system,
                            resolve));
                }
                catch (Exception e) {
                    // TODO: log warning
                }
            }
        }
        return new PolicyEntry(new CodeSource(codebase, signers), principals,
                permissions);
    }

    /**
     * Translates PermissionEntry token to Permission object.
     * First, it performs general expansion for non-null <code>name</code> and
     * properties expansion for non-null <code>name</code>, <code>action</code> 
     * and <code>signers</code>.
     * Then, it obtains signing Certificates(if any), tries to find a class specified by 
     * <code>klass</code> name and instantiate a corresponding permission object.
     * If class is not found or it is signed improperly, returns UnresolvedPermission.
     *
     * @param pe PermissionEntry token to be resolved
     * @param ge parental GrantEntry of the PermissionEntry 
     * @param ks KeyStore for resolving Certificates, may be <code>null</code>
     * @param system system properties, used for property expansion
     * @param resolve flag enabling/disabling property expansion
     * @return resolved Permission object, either of concrete class or UnresolvedPermission
     * @throws Exception if failed to expand properties, 
     * or to get a Certificate, 
     * or to create an instance of a successfully found class 
     */
    protected Permission resolvePermission(
            DefaultPolicyScanner.PermissionEntry pe,
            DefaultPolicyScanner.GrantEntry ge, KeyStore ks, Properties system,
            boolean resolve) throws Exception {
        if (pe.name != null) {
            pe.name = PolicyUtils.expandGeneral(pe.name,
                    new PermissionExpander().configure(ge, ks));
        }
        if (resolve) {
            if (pe.name != null) {
                pe.name = PolicyUtils.expand(pe.name, system);
            }
            if (pe.actions != null) {
                pe.actions = PolicyUtils.expand(pe.actions, system);
            }
            if (pe.signers != null) {
                pe.signers = PolicyUtils.expand(pe.signers, system);
            }
        }
        Certificate[] signers = (pe.signers == null) ? null : resolveSigners(
                ks, pe.signers);
        try {
            Class<?> klass = Class.forName(pe.klass);
            if (PolicyUtils.matchSubset(signers, klass.getSigners())) {
                return PolicyUtils.instantiatePermission(klass, pe.name,
                        pe.actions);
            }
        }
        catch (ClassNotFoundException cnfe) {}
        //maybe properly signed class will be loaded later
        return new UnresolvedPermission(pe.klass, pe.name, pe.actions, signers);
    }

    /** 
     * Specific handler for expanding <i>self</i> and <i>alias</i> protocols. 
     */
    class PermissionExpander implements PolicyUtils.GeneralExpansionHandler {

        // Store KeyStore
        private KeyStore ks;

        // Store GrantEntry
        private DefaultPolicyScanner.GrantEntry ge;

        /** 
         * Combined setter of all required fields. 
         */
        public PermissionExpander configure(DefaultPolicyScanner.GrantEntry ge,
                KeyStore ks) {
            this.ge = ge;
            this.ks = ks;
            return this;
        }

        /**
         * Resolves the following protocols:
         * <dl>
         * <dt>self
         * <dd>Denotes substitution to a principal information of the parental 
         * GrantEntry. Returns a space-separated list of resolved Principals 
         * (including wildcarded), formatting each as <b>class &quot;name&quot;</b>.
         * If parental GrantEntry has no Principals, throws ExpansionFailedException.
         * <dt>alias:<i>name</i>
         * <dd>Denotes substitution of a KeyStore alias. Namely, if a KeyStore has 
         * an X.509 certificate associated with the specified name, then returns 
         * <b>javax.security.auth.x500.X500Principal &quot;<i>DN</i>&quot;</b> string, 
         * where <i>DN</i> is a certificate's subject distinguished name.  
         * </dl>
         * @throws ExpansionFailedException - if protocol is other than 
         * <i>self</i> or <i>alias</i>, or if data resolution failed 
         */
        public String resolve(String protocol, String data)
                throws PolicyUtils.ExpansionFailedException {

            if ("self".equals(protocol)) { //$NON-NLS-1$
                //need expanding to list of principals in grant clause 
                if (ge.principals != null && ge.principals.size() != 0) {
                    StringBuilder sb = new StringBuilder();
                    for (Iterator<PrincipalEntry> iter = ge.principals.iterator(); iter
                            .hasNext();) {
                        DefaultPolicyScanner.PrincipalEntry pr = iter
                                .next();
                        if (pr.klass == null) {
                            // aliased X500Principal
                            try {
                                sb.append(pc2str(getPrincipalByAlias(ks,
                                        pr.name)));
                            }
                            catch (Exception e) {
                                throw new PolicyUtils.ExpansionFailedException(
                                        Messages.getString("security.143", pr.name), e); //$NON-NLS-1$
                            }
                        } else {
                            sb.append(pr.klass).append(" \"").append(pr.name) //$NON-NLS-1$
                                    .append("\" "); //$NON-NLS-1$
                        }
                    }
                    return sb.toString();
                } else {
                    throw new PolicyUtils.ExpansionFailedException(
                            Messages.getString("security.144")); //$NON-NLS-1$
                }
            }
            if ("alias".equals(protocol)) { //$NON-NLS-1$
                try {
                    return pc2str(getPrincipalByAlias(ks, data));
                }
                catch (Exception e) {
                    throw new PolicyUtils.ExpansionFailedException(
                            Messages.getString("security.143", data), e); //$NON-NLS-1$
                }
            }
            throw new PolicyUtils.ExpansionFailedException(
                    Messages.getString("security.145", protocol)); //$NON-NLS-1$
        }

        // Formats a string describing the passed Principal. 
        private String pc2str(Principal pc) {
            String klass = pc.getClass().getName();
            String name = pc.getName();
            StringBuilder sb = new StringBuilder(klass.length() + name.length()
                    + 5);
            return sb.append(klass).append(" \"").append(name).append("\"") //$NON-NLS-1$ //$NON-NLS-2$
                    .toString();
        }
    }

    /**
     * Takes a comma-separated list of aliases and obtains corresponding 
     * certificates.
     * @param ks KeyStore for resolving Certificates, may be <code>null</code> 
     * @param signers comma-separated list of certificate aliases, 
     * must be not <code>null</code>
     * @return an array of signing Certificates
     * @throws Exception if KeyStore is <code>null</code> 
     * or if it failed to provide a certificate  
     */
    protected Certificate[] resolveSigners(KeyStore ks, String signers)
            throws Exception {
        if (ks == null) {
            throw new KeyStoreException(Messages.getString("security.146", //$NON-NLS-1$
                    signers));
        }

        Collection<Certificate> certs = new HashSet<Certificate>();
        StringTokenizer snt = new StringTokenizer(signers, ","); //$NON-NLS-1$
        while (snt.hasMoreTokens()) {
            //XXX cache found certs ??
            certs.add(ks.getCertificate(snt.nextToken().trim()));
        }
        return certs.toArray(new Certificate[certs.size()]);
    }

    /**
     * Returns a subject's X500Principal of an X509Certificate, 
     * which is associated with the specified keystore alias. 
     * @param ks KeyStore for resolving Certificate, may be <code>null</code>
     * @param alias alias to a certificate
     * @return X500Principal with a subject distinguished name
     * @throws KeyStoreException if KeyStore is <code>null</code> 
     * or if it failed to provide a certificate
     * @throws CertificateException if found certificate is not 
     * an X509Certificate 
     */
    protected Principal getPrincipalByAlias(KeyStore ks, String alias)
            throws KeyStoreException, CertificateException {

        if (ks == null) {
            throw new KeyStoreException(
                    Messages.getString("security.147", alias)); //$NON-NLS-1$
        }
        //XXX cache found certs ??
        Certificate x509 = ks.getCertificate(alias);
        if (x509 instanceof X509Certificate) {
            return ((X509Certificate) x509).getSubjectX500Principal();
        } else {
            throw new CertificateException(Messages.getString("security.148", //$NON-NLS-1$
                    alias, x509));
        }
    }

    /**
     * Returns the first successfully loaded KeyStore, from the specified list of
     * possible locations. This method iterates over the list of KeystoreEntries;
     * for each entry expands <code>url</code> and <code>type</code>,
     * tries to construct instances of specified URL and KeyStore and to load 
     * the keystore. If it is loaded, returns the keystore, otherwise proceeds to 
     * the next KeystoreEntry. 
     * <br>
     * <b>Note:</b> an url may be relative to the policy file location or absolute.
     * @param keystores list of available KeystoreEntries
     * @param base the policy file location
     * @param system system properties, used for property expansion
     * @param resolve flag enabling/disabling property expansion
     * @return the first successfully loaded KeyStore or <code>null</code>
     */
    protected KeyStore initKeyStore(List<KeystoreEntry>keystores,
            URL base, Properties system, boolean resolve) {

        for (int i = 0; i < keystores.size(); i++) {
            try {
                DefaultPolicyScanner.KeystoreEntry ke = keystores
                        .get(i);
                if (resolve) {
                    ke.url = PolicyUtils.expandURL(ke.url, system);
                    if (ke.type != null) {
                        ke.type = PolicyUtils.expand(ke.type, system);
                    }
                }
                if (ke.type == null || ke.type.length() == 0) {
                    ke.type = KeyStore.getDefaultType();
                }
                KeyStore ks = KeyStore.getInstance(ke.type);
                URL location = new URL(base, ke.url);
                InputStream is = AccessController
                        .doPrivileged(new PolicyUtils.URLLoader(location));
                try {
                    ks.load(is, null);
                }
                finally {
                    is.close();
                }
                return ks;
            }
            catch (Exception e) {
                // TODO: log warning
            }
        }
        return null;
    }
}
