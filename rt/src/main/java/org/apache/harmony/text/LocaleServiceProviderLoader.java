/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.harmony.text;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.ServiceConfigurationError;
import java.util.Set;
import java.util.spi.LocaleServiceProvider;

/**
 * This class is the helper to service provider for
 * <code>DateFormatSymbolsProvider</code> and
 * <code>DecimalFormatSymbolsProvider</code>.
 * 
 */
public class LocaleServiceProviderLoader {
    /**
     * The comment string used in configuration files
     */
    public static final String PROVIDER_CONFIGURATION_FILE_COMMENT = "#"; //$NON-NLS-1$

    private static ClassLoader systemClassLoader;

    /**
     * Get provider that support specified locale. If there is no provider
     * found, return <code>null</code>.
     * 
     * @param cache
     *            cached providers
     * @param locale
     *            the specified locale
     * @param providerFileName
     *            provider configuration file name
     * @return <code>LocaleServiceProvider</code> that support the locale
     */
    public static LocaleServiceProvider getProviderByLocale(
            HashMap<ClassLoader, Object> cache, Locale locale,
            String providerFileName) {
        HashMap<LocaleServiceProvider, Set<Locale>> providers = getProviders(
                cache, providerFileName);
        // no provider installed
        if (providers == null || providers.size() == 0) {
            return null;
        }

        // find provider
        for (LocaleServiceProvider provider : providers.keySet()) {
            if (providers.get(provider).contains(locale)) {
                return provider;
            }
        }

        // no provider found
        return null;
    }

    /**
     * Return all locales supported by the installed providers. If no provider
     * installed return <code>null</code>.
     * 
     * @param cache
     *            cached providers
     * @param providerFileName
     *            provider configuration file name
     * @return all locales supported by the installed providers
     */
    public static Locale[] getProviderSupportLocales(
            HashMap<ClassLoader, Object> cache, String providerFileName) {

        HashMap<LocaleServiceProvider, Set<Locale>> providers = getProviders(
                cache, providerFileName);

        if (providers == null || providers.size() == 0) {
            return null;
        }

        List<Locale> locales = new ArrayList<Locale>();
        for (Set<Locale> element : providers.values()) {
            locales.addAll(element);
        }

        return locales.toArray(new Locale[0]);
    }

    /**
     * Load all installed providers.
     * 
     * @param cache
     *            cached providers
     * @param providerFileName
     *            provider configuration file name
     * @return all installed providers and locales supported by them
     */
    private static synchronized HashMap<LocaleServiceProvider, Set<Locale>> getProviders(
            HashMap<ClassLoader, Object> cache, String providerFileName) {
        ClassLoader classLoader = getClassLoader();

        HashMap<LocaleServiceProvider, Set<Locale>> providers = null;
        if (cache.containsKey(classLoader)) {
            providers = (HashMap<LocaleServiceProvider, Set<Locale>>) cache
                    .get(classLoader);
        } else {
            Enumeration<URL> e = null;
            try {
                e = classLoader.getResources(providerFileName);
            } catch (IOException ex) {
                // Unexpected ClassLoader exception, ignore
                return null;
            }

            // Examine each configuration file
            providers = new HashMap<LocaleServiceProvider, Set<Locale>>();
            while (e.hasMoreElements()) {
                loadProviderLocales(e.nextElement(), classLoader, providers);
            }
            cache.put(classLoader, providers);
        }
        return providers;
    }

    private static ClassLoader getClassLoader() {
        ClassLoader classLoader = getContextClassLoader();
        if (null == classLoader) {
            classLoader = getSystemClassLoader();
        }
        return classLoader;
    }

    /*
     * Use privileged code to get the context class loader.
     */
    private static ClassLoader getContextClassLoader() {
        final Thread t = Thread.currentThread();
        return AccessController
                .doPrivileged(new PrivilegedAction<ClassLoader>() {
                    public ClassLoader run() {
                        return t.getContextClassLoader();
                    }
                });
    }

    /*
     * Use privileged code to get the system class loader.
     */
    private static ClassLoader getSystemClassLoader() {
        if (null == systemClassLoader) {
            systemClassLoader = AccessController
                    .doPrivileged(new PrivilegedAction<ClassLoader>() {
                        public ClassLoader run() {
                            return ClassLoader.getSystemClassLoader();
                        }
                    });
        }
        return systemClassLoader;
    }

    /**
     * Load providers from configuration file.
     * 
     * @param configFile
     *            <code>URL</code> of provider configuration file
     * @param classLoader
     *            <code>ClassLoader</code> used for load configuration file
     * @param providers
     *            has loaded providers by the same class loader
     */
    private static void loadProviderLocales(URL configFile,
            ClassLoader classLoader,
            HashMap<LocaleServiceProvider, Set<Locale>> providers) {
        BufferedReader reader = null;
        try {
            InputStream is = configFile.openStream();
            // Read each line for DateFormatSymbols provider class names
            reader = new BufferedReader(new InputStreamReader(is));
            String providerClassName = reader.readLine();
            while (null != providerClassName) {
                providerClassName = trimClassName(providerClassName);
                // Skip comments and blank lines
                if (providerClassName.length() > 0) {
                    // Load the provider
                    Object cp = null;
                    try {
                        Class<?> c = Class.forName(providerClassName, true,
                                classLoader);
                        cp = c.newInstance();
                    } catch (Exception ex) {
                        throw new ServiceConfigurationError(ex.getMessage(), ex);
                    }

                    // Put the locales supported by this provider into the map
                    LocaleServiceProvider provider = (LocaleServiceProvider) cp;
                    Locale[] locales = provider.getAvailableLocales();
                    if (locales != null && locales.length > 0) {
                        HashSet<Locale> localesSet = new HashSet<Locale>();
                        for (Locale locale : locales) {
                            if (!localesSet.contains(locale)) {
                                localesSet.add(locale);
                            }
                        }
                        providers.put(provider, localesSet);
                    }
                }
                // Read the next line of the config file
                providerClassName = reader.readLine();
            }
        } catch (IOException ex) {
            // Can't read this configuration file, ignore
        } finally {
            try {
                if (null != reader) {
                    reader.close();
                }
            } catch (IOException ex) {
                // Ignore closing exception
            }
        }
    }

    /*
     * Trim comment string, and then trim white spaces.
     */
    private static String trimClassName(String name) {
        String trimedName = name;
        int index = name.indexOf(PROVIDER_CONFIGURATION_FILE_COMMENT);
        // Trim comments
        if (index != -1) {
            trimedName = name.substring(0, index);
        }
        return trimedName.trim();
    }
}
