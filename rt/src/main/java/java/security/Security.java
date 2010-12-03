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

import java.io.InputStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.Map.Entry;

import org.apache.harmony.security.Util;
import org.apache.harmony.security.fortress.Engine;
import org.apache.harmony.security.fortress.PolicyUtils;
import org.apache.harmony.security.fortress.SecurityAccess;
import org.apache.harmony.security.fortress.Services;
import org.apache.harmony.security.internal.nls.Messages;

/**
 * {@code Security} is the central class in the Java Security API. It manages
 * the list of security {@code Provider} that have been installed into this
 * runtime environment.
 */
public final class Security {

    // Security properties
    private static Properties secprops = new Properties();

    // static initialization
    // - load security properties files
    // - load statically registered providers
    // - if no provider description file found then load default providers
    static {
        AccessController.doPrivileged(new java.security.PrivilegedAction<Void>() {
            public Void run() {
                boolean loaded = false;
                File f = new File(System.getProperty("java.home") //$NON-NLS-1$
                        + File.separator + "lib" + File.separator //$NON-NLS-1$
                        + "security" + File.separator + "java.security"); //$NON-NLS-1$ //$NON-NLS-2$
                if (f.exists()) {
                    try {
                        FileInputStream fis = new FileInputStream(f);
                        InputStreamReader is = new InputStreamReader(fis);
                        secprops.load(is);
                        loaded = true;
                        is.close();
                    } catch (IOException e) {
//                        System.err.println("Could not load Security properties file: "
//                                        + e);
                    }
                }

                if (Util.equalsIgnoreCase("true", secprops.getProperty("security.allowCustomPropertiesFile", "true"))) { //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                    String securityFile = System.getProperty("java.security.properties"); //$NON-NLS-1$
                    if (securityFile != null) {
                        if (securityFile.startsWith("=")) { // overwrite //$NON-NLS-1$
                            secprops = new Properties();
                            loaded = false;
                            securityFile = securityFile.substring(1);
                        }
                        try {
                            securityFile = PolicyUtils.expand(securityFile, System.getProperties());
                        } catch (PolicyUtils.ExpansionFailedException e) {
//                            System.err.println("Could not load custom Security properties file "
//                                    + securityFile +": " + e);
                        }
                        f = new File(securityFile);
                        InputStreamReader is;
                        try {
                            if (f.exists()) {
                                FileInputStream fis = new FileInputStream(f);
                                is = new InputStreamReader(fis);
                            } else {
                                URL url = new URL(securityFile);
                                is = new InputStreamReader(url.openStream());
                            }
                            secprops.load(is);
                            loaded = true;
                            is.close();
                        } catch (IOException e) {
 //                           System.err.println("Could not load custom Security properties file "
 //                                   + securityFile +": " + e);
                        }
                    }
                }
                if (!loaded) {
                    registerDefaultProviders();
                }
                Engine.door = new SecurityDoor();
                return null;
            }
        });
    }

    /**
     * This class can't be instantiated.
     */
    private Security() {
    }

    // Register default providers
    private static void registerDefaultProviders() {
        secprops.put("security.provider.1", "org.apache.harmony.security.provider.cert.DRLCertFactory");  //$NON-NLS-1$ //$NON-NLS-2$
        secprops.put("security.provider.2", "org.apache.harmony.security.provider.crypto.CryptoProvider");  //$NON-NLS-1$ //$NON-NLS-2$
        secprops.put("security.provider.3", "org.apache.harmony.xnet.provider.jsse.JSSEProvider");  //$NON-NLS-1$ //$NON-NLS-2$
        secprops.put("security.provider.4", "org.bouncycastle.jce.provider.BouncyCastleProvider");  //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Returns value for the specified algorithm with the specified name.
     *
     * @param algName
     *            the name of the algorithm.
     * @param propName
     *            the name of the property.
     * @return value of the property.
     * @deprecated Use {@link AlgorithmParameters} and {@link KeyFactory}
     *             instead.
     */
    @Deprecated
    public static String getAlgorithmProperty(String algName, String propName) {
        if (algName == null || propName == null) {
            return null;
        }
        String prop = propName + "." + algName; //$NON-NLS-1$
        Provider[] providers = getProviders();
        for (int i = 0; i < providers.length; i++) {
            for (Enumeration e = providers[i].propertyNames(); e
                    .hasMoreElements();) {
                String pname = (String) e.nextElement();
                if (Util.equalsIgnoreCase(prop, pname)) {
                    return providers[i].getProperty(pname);
                }
            }
        }
        return null;
    }

    /**
     * Insert the given {@code Provider} at the specified {@code position}. The
     * positions define the preference order in which providers are searched for
     * requested algorithms.
     * <p>
     * If a {@code SecurityManager} is installed, code calling this method needs
     * the {@code SecurityPermission} {@code insertProvider.NAME} (where NAME is
     * the provider name) to be granted, otherwise a {@code SecurityException}
     * will be thrown.
     *
     * @param provider
     *            the provider to insert.
     * @param position
     *            the position (starting from 1).
     * @return the actual position or {@code -1} if the given {@code provider}
     *         was already in the list. The actual position may be different
     *         from the desired position.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and the caller does
     *             not have permission to invoke this method.
     */
    public static synchronized int insertProviderAt(Provider provider,
            int position) {
        // check security access; check that provider is not already
        // installed, else return -1; if (position <1) or (position > max
        // position) position = max position + 1; insert provider, shift up
        // one position for next providers; Note: The position is 1-based
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("insertProvider." + provider.getName()); //$NON-NLS-1$
        }
        if (getProvider(provider.getName()) != null) {
            return -1;
        }
        int result = Services.insertProviderAt(provider, position);
        renumProviders();
        return result;
    }

    /**
     * Adds the given {@code provider} to the collection of providers at the
     * next available position.
     * <p>
     * If a {@code SecurityManager} is installed, code calling this method needs
     * the {@code SecurityPermission} {@code insertProvider.NAME} (where NAME is
     * the provider name) to be granted, otherwise a {@code SecurityException}
     * will be thrown.
     *
     * @param provider
     *            the provider to be added.
     * @return the actual position or {@code -1} if the given {@code provider}
     *         was already in the list.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and the caller does
     *             not have permission to invoke this method.
     */
    public static int addProvider(Provider provider) {
        return insertProviderAt(provider, 0);
    }

    /**
     * Removes the {@code Provider} with the specified name form the collection
     * of providers. If the the {@code Provider} with the specified name is
     * removed, all provider at a greater position are shifted down one
     * position.
     * <p>
     * Returns silently if {@code name} is {@code null} or no provider with the
     * specified name is installed.
     * <p>
     * If a {@code SecurityManager} is installed, code calling this method needs
     * the {@code SecurityPermission} {@code removeProvider.NAME} (where NAME is
     * the provider name) to be granted, otherwise a {@code SecurityException}
     * will be thrown.
     *
     * @param name
     *            the name of the provider to remove.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and the caller does
     *             not have permission to invoke this method.
     */
    public static synchronized void removeProvider(String name) {
        // It is not clear from spec.:
    	// 1. if name is null, should we checkSecurityAccess or not? 
    	//    throw SecurityException or not?
        // 2. as 1 but provider is not installed
        // 3. behavior if name is empty string?

        Provider p;
        if ((name == null) || (name.length() == 0)) {
            return;
        }
        p = getProvider(name);
        if (p == null) {
            return;
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("removeProvider." + name); //$NON-NLS-1$
        }
        Services.removeProvider(p.getProviderNumber());
        renumProviders();
        p.setProviderNumber(-1);
    }

    /**
     * Returns an array containing all installed providers. The providers are
     * ordered according their preference order.
     *
     * @return an array containing all installed providers.
     */
    public static synchronized Provider[] getProviders() {
        return Services.getProviders();
    }

    /**
     * Returns the {@code Provider} with the specified name. Returns {@code
     * null} if name is {@code null} or no provider with the specified name is
     * installed.
     *
     * @param name
     *            the name of the requested provider.
     * @return the provider with the specified name, maybe {@code null}.
     */
    public static synchronized Provider getProvider(String name) {
        return Services.getProvider(name);
    }

    /**
     * Returns the array of providers which meet the user supplied string
     * filter. The specified filter must be supplied in one of two formats:
     * <nl>
     * <li> CRYPTO_SERVICE_NAME.ALGORITHM_OR_TYPE
     * <p>
     * (for example: "MessageDigest.SHA")
     * <li> CRYPTO_SERVICE_NAME.ALGORITHM_OR_TYPE
     * ATTR_NAME:ATTR_VALUE
     * <p>
     * (for example: "Signature.MD2withRSA KeySize:512")
     * </nl>
     *
     * @param filter
     *            case-insensitive filter.
     * @return the providers which meet the user supplied string filter {@code
     *         filter}. A {@code null} value signifies that none of the
     *         installed providers meets the filter specification.
     * @throws InvalidParameterException
     *             if an unusable filter is supplied.
     * @throws NullPointerException
     *             if {@code filter} is {@code null}.
     */
    public static Provider[] getProviders(String filter) {
        if (filter == null) {
            throw new NullPointerException(Messages.getString("security.2A")); //$NON-NLS-1$
        }
        if (filter.length() == 0) {
            throw new InvalidParameterException(
                    Messages.getString("security.2B")); //$NON-NLS-1$
        }
        HashMap<String, String> hm = new HashMap<String, String>();
        int i = filter.indexOf(':');
        if ((i == filter.length() - 1) || (i == 0)) {
            throw new InvalidParameterException(
                    Messages.getString("security.2B")); //$NON-NLS-1$
        }
        if (i < 1) {
            hm.put(filter, ""); //$NON-NLS-1$
        } else {
            hm.put(filter.substring(0, i), filter.substring(i + 1));
        }
        return getProviders(hm);
    }

    /**
     * Returns the array of providers which meet the user supplied set of
     * filters. The filter must be supplied in one of two formats:
     * <nl>
     * <li> CRYPTO_SERVICE_NAME.ALGORITHM_OR_TYPE
     * <p>
     * for example: "MessageDigest.SHA" The value associated with the key must
     * be an empty string. <li> CRYPTO_SERVICE_NAME.ALGORITHM_OR_TYPE
     * ATTR_NAME:ATTR_VALUE
     * <p>
     * for example: "Signature.MD2withRSA KeySize:512" where "KeySize:512" is
     * the value of the filter map entry.
     * </nl>
     *
     * @param filter
     *            case-insensitive filter.
     * @return the providers which meet the user supplied string filter {@code
     *         filter}. A {@code null} value signifies that none of the
     *         installed providers meets the filter specification.
     * @throws InvalidParameterException
     *             if an unusable filter is supplied.
     * @throws NullPointerException
     *             if {@code filter} is {@code null}.
     */
    public static synchronized Provider[] getProviders(Map<String,String> filter) {
        if (filter == null) {
            throw new NullPointerException(Messages.getString("security.2A")); //$NON-NLS-1$
        }
        if (filter.isEmpty()) {
            return null;
        }
        java.util.List<Provider> result = Services.getProvidersList();
        Set<Entry<String, String>> keys = filter.entrySet();
        Map.Entry<String, String> entry;
        for (Iterator<Entry<String, String>> it = keys.iterator(); it.hasNext();) {
            entry = it.next();
            String key = entry.getKey();
            String val = entry.getValue();
            String attribute = null;
            int i = key.indexOf(' ');
            int j = key.indexOf('.');
            if (j == -1) {
                throw new InvalidParameterException(
                        Messages.getString("security.2B")); //$NON-NLS-1$
            }
            if (i == -1) { // <crypto_service>.<algorithm_or_type>
                if (val.length() != 0) {
                    throw new InvalidParameterException(
                            Messages.getString("security.2B")); //$NON-NLS-1$
                }
            } else { // <crypto_service>.<algorithm_or_type> <attribute_name>
                if (val.length() == 0) {
                    throw new InvalidParameterException(
                            Messages.getString("security.2B")); //$NON-NLS-1$
                }
                attribute = key.substring(i + 1);
                if (attribute.trim().length() == 0) {
                    throw new InvalidParameterException(
                            Messages.getString("security.2B")); //$NON-NLS-1$
                }
                key = key.substring(0, i);
            }
            String serv = key.substring(0, j);
            String alg = key.substring(j + 1);
            if (serv.length() == 0 || alg.length() == 0) {
                throw new InvalidParameterException(
                        Messages.getString("security.2B")); //$NON-NLS-1$
            }
            Provider p;
            for (int k = 0; k < result.size(); k++) {
                try {
                    p = result.get(k);
                } catch (IndexOutOfBoundsException e) {
                    break;
                }
                if (!p.implementsAlg(serv, alg, attribute, val)) {
                    result.remove(p);
                    k--;
                }
            }
        }
        if (result.size() > 0) {
            return result.toArray(new Provider[result.size()]);
        }
        return null;
    }

    /**
     * Returns the value of the security property named by the argument.
     * <p>
     * If a {@code SecurityManager} is installed, code calling this method needs
     * the {@code SecurityPermission} {@code getProperty.KEY} (where KEY is the
     * specified {@code key}) to be granted, otherwise a {@code
     * SecurityException} will be thrown.
     *
     * @param key
     *            the name of the requested security property.
     * @return the value of the security property.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and the caller does
     *             not have permission to invoke this method.
     */
    public static String getProperty(String key) {
        if (key == null) {
            throw new NullPointerException(Messages.getString("security.2C")); //$NON-NLS-1$
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("getProperty." + key); //$NON-NLS-1$
        }
        String property = secprops.getProperty(key);
        if (property != null) {
            property = property.trim();
        }
        return property;
    }

    /**
     * Sets the value of the specified security property.
     * <p>
     * If a {@code SecurityManager} is installed, code calling this method needs
     * the {@code SecurityPermission} {@code setProperty.KEY} (where KEY is the
     * specified {@code key}) to be granted, otherwise a {@code
     * SecurityException} will be thrown.
     *
     * @param key
     *            the name of the security property.
     * @param datnum
     *            the value of the security property.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and the caller does
     *             not have permission to invoke this method.
     */
    public static void setProperty(String key, String datnum) {
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSecurityAccess("setProperty." + key); //$NON-NLS-1$
        }
        secprops.put(key, datnum);
    }

    /**
     * Returns a {@code Set} of all registered algorithms for the specified
     * cryptographic service. {@code "Signature"}, {@code "Cipher"} and {@code
     * "KeyStore"} are examples for such kind of services.
     *
     * @param serviceName
     *            the case-insensitive name of the service.
     * @return a {@code Set} of all registered algorithms for the specified
     *         cryptographic service, or an empty {@code Set} if {@code
     *         serviceName} is {@code null} or if no registered provider
     *         provides the requested service.
     */
    public static Set<String> getAlgorithms(String serviceName) {
        Set<String> result = new HashSet<String>();
        Provider[] p = getProviders();
        for (int i = 0; i < p.length; i++) {
            for (Iterator it = p[i].getServices().iterator(); it.hasNext();) {
                Provider.Service s = (Provider.Service) it.next();
                if (Util.equalsIgnoreCase(s.getType(),serviceName)) {
                    result.add(s.getAlgorithm());
                }
            }
        }
        return result;
    }

    /**
     * 
     * Update sequence numbers of all providers.
     *  
     */
    private static void renumProviders() {
        Provider[] p = Services.getProviders();
        for (int i = 0; i < p.length; i++) {
            p[i].setProviderNumber(i + 1);
        }
    }

    private static class SecurityDoor implements SecurityAccess {
        // Access to Security.renumProviders()
        public void renumProviders() {
            Security.renumProviders();
        }

        //  Access to Security.getAliases()
        public Iterator<String> getAliases(Provider.Service s) {
            return s.getAliases();
        }
        
        // Access to Provider.getService()
        public Provider.Service getService(Provider p, String type) {
            return p.getService(type);
        }
    }
}
