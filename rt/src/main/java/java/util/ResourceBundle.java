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

package java.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.security.AccessController;
import java.security.PrivilegedAction;

import org.apache.harmony.kernel.vm.VM;
import org.apache.harmony.luni.internal.nls.Messages;

/**
 * {@code ResourceBundle} is an abstract class which is the superclass of classes which
 * provide {@code Locale}-specific resources. A bundle contains a number of named
 * resources, where the names are {@code Strings}. A bundle may have a parent bundle,
 * and when a resource is not found in a bundle, the parent bundle is searched for
 * the resource. If the fallback mechanism reaches the base bundle and still
 * can't find the resource it throws a {@code MissingResourceException}.
 *
 * <ul>
 * <li>All bundles for the same group of resources share a common base bundle.
 * This base bundle acts as the root and is the last fallback in case none of
 * its children was able to respond to a request.</li>
 * <li>The first level contains changes between different languages. Only the
 * differences between a language and the language of the base bundle need to be
 * handled by a language-specific {@code ResourceBundle}.</li>
 * <li>The second level contains changes between different countries that use
 * the same language. Only the differences between a country and the country of
 * the language bundle need to be handled by a country-specific {@code ResourceBundle}.
 * </li>
 * <li>The third level contains changes that don't have a geographic reason
 * (e.g. changes that where made at some point in time like {@code PREEURO} where the
 * currency of come countries changed. The country bundle would return the
 * current currency (Euro) and the {@code PREEURO} variant bundle would return the old
 * currency (e.g. DM for Germany).</li>
 * </ul>
 *
 * <strong>Examples</strong>
 * <ul>
 * <li>BaseName (base bundle)
 * <li>BaseName_de (german language bundle)
 * <li>BaseName_fr (french language bundle)
 * <li>BaseName_de_DE (bundle with Germany specific resources in german)
 * <li>BaseName_de_CH (bundle with Switzerland specific resources in german)
 * <li>BaseName_fr_CH (bundle with Switzerland specific resources in french)
 * <li>BaseName_de_DE_PREEURO (bundle with Germany specific resources in german of
 * the time before the Euro)
 * <li>BaseName_fr_FR_PREEURO (bundle with France specific resources in french of
 * the time before the Euro)
 * </ul>
 *
 * It's also possible to create variants for languages or countries. This can be
 * done by just skipping the country or language abbreviation:
 * BaseName_us__POSIX or BaseName__DE_PREEURO. But it's not allowed to
 * circumvent both language and country: BaseName___VARIANT is illegal.
 *
 * @see Properties
 * @see PropertyResourceBundle
 * @see ListResourceBundle
 * @since 1.1
 */
public abstract class ResourceBundle {

    private static final String UNDER_SCORE = "_"; //$NON-NLS-1$

    private static final String EMPTY_STRING = ""; //$NON-NLS-1$

    /**
     * The parent of this {@code ResourceBundle} that is used if this bundle doesn't
     * include the requested resource.
     */
    protected ResourceBundle parent;

    private Locale locale;

    private long lastLoadTime = 0;

    static class MissingBundle extends ResourceBundle {
        @Override
        public Enumeration<String> getKeys() {
            return null;
        }

        @Override
        public Object handleGetObject(String name) {
            return null;
        }
    }

    private static final ResourceBundle MISSING = new MissingBundle();

    private static final ResourceBundle MISSINGBASE = new MissingBundle();

    private static final WeakHashMap<Object, Hashtable<String, ResourceBundle>> cache = new WeakHashMap<Object, Hashtable<String, ResourceBundle>>();

    /**
     * Constructs a new instance of this class.
     */
    public ResourceBundle() {
        /* empty */
    }

    /**
     * Finds the named resource bundle for the default {@code Locale} and the caller's
     * {@code ClassLoader}.
     * 
     * @param bundleName
     *            the name of the {@code ResourceBundle}.
     * @return the requested {@code ResourceBundle}.
     * @throws MissingResourceException
     *                if the {@code ResourceBundle} cannot be found.
     */
    public static final ResourceBundle getBundle(String bundleName)
            throws MissingResourceException {
        return getBundleImpl(bundleName, Locale.getDefault(), VM
                .callerClassLoader());
    }

    /**
     * Finds the named {@code ResourceBundle} for the specified {@code Locale} and the caller
     * {@code ClassLoader}.
     * 
     * @param bundleName
     *            the name of the {@code ResourceBundle}.
     * @param locale
     *            the {@code Locale}.
     * @return the requested resource bundle.
     * @throws MissingResourceException
     *                if the resource bundle cannot be found.
     */
    public static final ResourceBundle getBundle(String bundleName,
            Locale locale) {
        return getBundleImpl(bundleName, locale, VM.callerClassLoader());
    }

    /**
     * Finds the named resource bundle for the specified {@code Locale} and {@code ClassLoader}.
     * 
     * The passed base name and {@code Locale} are used to create resource bundle names.
     * The first name is created by concatenating the base name with the result
     * of {@link Locale#toString()}. From this name all parent bundle names are
     * derived. Then the same thing is done for the default {@code Locale}. This results
     * in a list of possible bundle names.
     *
     * <strong>Example</strong> For the basename "BaseName", the {@code Locale} of the
     * German part of Switzerland (de_CH) and the default {@code Locale} en_US the list
     * would look something like this:
     *
     * <ol>
     * <li>BaseName_de_CH</li>
     * <li>BaseName_de</li>
     * <li>Basename_en_US</li>
     * <li>Basename_en</li>
     * <li>BaseName</li>
     * </ol>
     *
     * This list also shows the order in which the bundles will be searched for a requested
     * resource in the German part of Switzerland (de_CH).
     *
     * As a first step, this method tries to instantiate
     * a {@code ResourceBundle} with the names provided.
     * If such a class can be instantiated and initialized, it is returned and
     * all the parent bundles are instantiated too. If no such class can be
     * found this method tries to load a {@code .properties} file with the names by
     * replacing dots in the base name with a slash and by appending
     * "{@code .properties}" at the end of the string. If such a resource can be found
     * by calling {@link ClassLoader#getResource(String)} it is used to
     * initialize a {@link PropertyResourceBundle}. If this succeeds, it will
     * also load the parents of this {@code ResourceBundle}.
     *
     * For compatibility with older code, the bundle name isn't required to be
     * a fully qualified class name. It's also possible to directly pass
     * the path to a properties file (without a file extension).
     *
     * @param bundleName
     *            the name of the {@code ResourceBundle}.
     * @param locale
     *            the {@code Locale}.
     * @param loader
     *            the {@code ClassLoader} to use.
     * @return the requested {@code ResourceBundle}.
     * @throws MissingResourceException
     *                if the {@code ResourceBundle} cannot be found.
     */
    public static ResourceBundle getBundle(String bundleName, Locale locale,
            ClassLoader loader) throws MissingResourceException {
        if (loader == null) {
            throw new NullPointerException();
        }
        if (bundleName != null) {
            ResourceBundle bundle;
            if (!locale.equals(Locale.getDefault())) {
                if ((bundle = handleGetBundle(bundleName, UNDER_SCORE + locale,
                        false, loader)) != null) {
                    return bundle;
                }
            }
            if ((bundle = handleGetBundle(bundleName, UNDER_SCORE
                    + Locale.getDefault(), true, loader)) != null) {
                return bundle;
            }
            throw new MissingResourceException(Messages.getString("luni.3A", bundleName, locale), bundleName + '_' + locale, //$NON-NLS-1$
                    EMPTY_STRING);
        }
        throw new NullPointerException();
    }

    /**
     * Finds the named resource bundle for the specified base name and control.
     * 
     * @param baseName
     *            the base name of a resource bundle
     * @param control
     *            the control that control the access sequence
     * @return the named resource bundle
     * 
     * @since 1.6
     */
    public static final ResourceBundle getBundle(String baseName,
            ResourceBundle.Control control) {
        return getBundle(baseName, Locale.getDefault(), getLoader(), control);
    }

    /**
     * Finds the named resource bundle for the specified base name and control.
     * 
     * @param baseName
     *            the base name of a resource bundle
     * @param targetLocale
     *            the target locale of the resource bundle
     * @param control
     *            the control that control the access sequence
     * @return the named resource bundle
     * 
     * @since 1.6
     */
    public static final ResourceBundle getBundle(String baseName,
            Locale targetLocale, ResourceBundle.Control control) {
        return getBundle(baseName, targetLocale, getLoader(), control);
    }

    private static ClassLoader getLoader() {
        return AccessController
                .doPrivileged(new PrivilegedAction<ClassLoader>() {
                    public ClassLoader run() {
                        ClassLoader cl = this.getClass().getClassLoader();
                        if (null == cl) {
                            cl = ClassLoader.getSystemClassLoader();
                        }
                        return cl;
                    }
                });
    }

    /**
     * Finds the named resource bundle for the specified base name and control.
     * 
     * @param baseName
     *            the base name of a resource bundle
     * @param targetLocale
     *            the target locale of the resource bundle
     * @param loader
     *            the class loader to load resource
     * @param control
     *            the control that control the access sequence
     * @return the named resource bundle
     * 
     * @since 1.6
     */
    public static ResourceBundle getBundle(String baseName,
            Locale targetLocale, ClassLoader loader,
            ResourceBundle.Control control) {
        boolean expired = false;
        String bundleName = control.toBundleName(baseName, targetLocale);
        Object cacheKey = loader != null ? (Object) loader : (Object) "null"; //$NON-NLS-1$
        Hashtable<String, ResourceBundle> loaderCache;
        // try to find in cache
        synchronized (cache) {
            loaderCache = cache.get(cacheKey);
            if (loaderCache == null) {
                loaderCache = new Hashtable<String, ResourceBundle>();
                cache.put(cacheKey, loaderCache);
            }
        }
        ResourceBundle result = loaderCache.get(bundleName);
        if (result != null) {
            long time = control.getTimeToLive(baseName, targetLocale);
            if (time == 0 || time == Control.TTL_NO_EXPIRATION_CONTROL
                    || time + result.lastLoadTime < System.currentTimeMillis()) {
                if (MISSING == result) {
                    throw new MissingResourceException(null, bundleName + '_'
                            + targetLocale, EMPTY_STRING);
                }
                return result;
            }
            expired = true;
        }
        // try to load
        ResourceBundle ret = processGetBundle(baseName, targetLocale, loader,
                control, expired, result);

        if (null != ret) {
            loaderCache.put(bundleName, ret);
            ret.lastLoadTime = System.currentTimeMillis();
            return ret;
        }
        loaderCache.put(bundleName, MISSING);
        throw new MissingResourceException(null, bundleName + '_'
                + targetLocale, EMPTY_STRING);
    }

    private static ResourceBundle processGetBundle(String baseName,
            Locale targetLocale, ClassLoader loader,
            ResourceBundle.Control control, boolean expired,
            ResourceBundle result) {
        List<Locale> locales = control.getCandidateLocales(baseName,
                targetLocale);
        if (null == locales) {
            throw new IllegalArgumentException();
        }
        List<String> formats = control.getFormats(baseName);
        if (Control.FORMAT_CLASS == formats
                || Control.FORMAT_PROPERTIES == formats
                || Control.FORMAT_DEFAULT == formats) {
            throw new IllegalArgumentException();
        }
        ResourceBundle ret = null;
        ResourceBundle currentBundle = null;
        ResourceBundle bundle = null;
        for (Locale locale : locales) {
            for (String format : formats) {
                try {
                    if (expired) {
                        bundle = control.newBundle(baseName, locale, format,
                                loader, control.needsReload(baseName, locale,
                                        format, loader, result, System
                                                .currentTimeMillis()));

                    } else {
                        try {
                            bundle = control.newBundle(baseName, locale,
                                    format, loader, false);
                        } catch (IllegalArgumentException e) {
                            // do nothing
                        }
                    }
                } catch (IllegalAccessException e) {
                    // do nothing
                } catch (InstantiationException e) {
                    // do nothing
                } catch (IOException e) {
                    // do nothing
                }
                if (null != bundle) {
                    if (null != currentBundle) {
                        currentBundle.setParent(bundle);
                        currentBundle = bundle;
                    } else {
                        if (null == ret) {
                            ret = bundle;
                            currentBundle = ret;
                        }
                    }
                }
                if (null != bundle) {
                    break;
                }
            }
        }

        if ((null == ret)
                || (Locale.ROOT.equals(ret.getLocale()) && (!(locales.size() == 1 && locales
                        .contains(Locale.ROOT))))) {
            Locale nextLocale = control.getFallbackLocale(baseName,
                    targetLocale);
            if (null != nextLocale) {
                ret = processGetBundle(baseName, nextLocale, loader, control,
                        expired, result);
            }
        }

        return ret;
    }

    private static ResourceBundle getBundleImpl(String bundleName,
            Locale locale, ClassLoader loader) throws MissingResourceException {
        if (bundleName != null) {
            ResourceBundle bundle;
            if (!locale.equals(Locale.getDefault())) {
                String localeName = locale.toString();
                if (localeName.length() > 0) {
                    localeName = UNDER_SCORE + localeName;
                }
                if ((bundle = handleGetBundle(bundleName, localeName, false,
                        loader)) != null) {
                    return bundle;
                }
            }
            String localeName = Locale.getDefault().toString();
            if (localeName.length() > 0) {
                localeName = UNDER_SCORE + localeName;
            }
            if ((bundle = handleGetBundle(bundleName, localeName, true, loader)) != null) {
                return bundle;
            }
            throw new MissingResourceException(Messages.getString("luni.3A", bundleName, locale), bundleName + '_' + locale, //$NON-NLS-1$
                    EMPTY_STRING);
        }
        throw new NullPointerException();
    }

    /**
     * Returns the names of the resources contained in this {@code ResourceBundle}.
     * 
     * @return an {@code Enumeration} of the resource names.
     */
    public abstract Enumeration<String> getKeys();

    /**
     * Gets the {@code Locale} of this {@code ResourceBundle}. In case a bundle was not
     * found for the requested {@code Locale}, this will return the actual {@code Locale} of
     * this resource bundle that was found after doing a fallback.
     * 
     * @return the {@code Locale} of this {@code ResourceBundle}.
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Returns the named resource from this {@code ResourceBundle}. If the resource
     * cannot be found in this bundle, it falls back to the parent bundle (if
     * it's not null) by calling the {@link #handleGetObject} method. If the resource still
     * can't be found it throws a {@code MissingResourceException}.
     * 
     * @param key
     *            the name of the resource.
     * @return the resource object.
     * @throws MissingResourceException
     *                if the resource is not found.
     */
    public final Object getObject(String key) {
        ResourceBundle last, theParent = this;
        do {
            Object result = theParent.handleGetObject(key);
            if (result != null) {
                return result;
            }
            last = theParent;
            theParent = theParent.parent;
        } while (theParent != null);
        throw new MissingResourceException(Messages.getString("luni.3A", last.getClass().getName(), key), last.getClass().getName(), key); //$NON-NLS-1$
    }

    /**
     * Returns the named string resource from this {@code ResourceBundle}.
     * 
     * @param key
     *            the name of the resource.
     * @return the resource string.
     * @throws MissingResourceException
     *                if the resource is not found.
     * @throws ClassCastException
     *                if the resource found is not a string.
     * @see #getObject(String)
     */
    public final String getString(String key) {
        return (String) getObject(key);
    }

    /**
     * Returns the named resource from this {@code ResourceBundle}.
     * 
     * @param key
     *            the name of the resource.
     * @return the resource string array.
     * @throws MissingResourceException
     *                if the resource is not found.
     * @throws ClassCastException
     *                if the resource found is not an array of strings.
     * @see #getObject(String)
     */
    public final String[] getStringArray(String key) {
        return (String[]) getObject(key);
    }

    private static ResourceBundle handleGetBundle(String base, String locale,
            boolean loadBase, final ClassLoader loader) {
        ResourceBundle bundle = null;
        String bundleName = base + locale;
        Object cacheKey = loader != null ? (Object) loader : (Object) "null"; //$NON-NLS-1$
        Hashtable<String, ResourceBundle> loaderCache;
        synchronized (cache) {
            loaderCache = cache.get(cacheKey);
            if (loaderCache == null) {
                loaderCache = new Hashtable<String, ResourceBundle>();
                cache.put(cacheKey, loaderCache);
            }
        }
        ResourceBundle result = loaderCache.get(bundleName);
        if (result != null) {
            if (result == MISSINGBASE) {
                return null;
            }
            if (result == MISSING) {
                if (!loadBase) {
                    return null;
                }
                String extension = strip(locale);
                if (extension == null) {
                    return null;
                }
                return handleGetBundle(base, extension, loadBase, loader);
            }
            return result;
        }

        try {
            Class<?> bundleClass = Class.forName(bundleName, true, loader);

            if (ResourceBundle.class.isAssignableFrom(bundleClass)) {
                bundle = (ResourceBundle) bundleClass.newInstance();
            }
        } catch (LinkageError e) {
        } catch (Exception e) {
        }

        if (bundle != null) {
            bundle.setLocale(locale);
        } else {
            final String fileName = bundleName.replace('.', '/');
            InputStream stream = AccessController
                    .doPrivileged(new PrivilegedAction<InputStream>() {
                        public InputStream run() {
                            return loader == null ? ClassLoader
                                    .getSystemResourceAsStream(fileName
                                            + ".properties") : loader //$NON-NLS-1$
                                    .getResourceAsStream(fileName
                                            + ".properties"); //$NON-NLS-1$
                        }
                    });
            if (stream != null) {
                try {
                    try {
                        bundle = new PropertyResourceBundle(new InputStreamReader(stream));
                    } finally {
                        stream.close();
                    }
                    bundle.setLocale(locale);
                } catch (IOException e) {
                    // do nothing
                }
            }
        }

        String extension = strip(locale);
        if (bundle != null) {
            if (extension != null) {
                ResourceBundle parent = handleGetBundle(base, extension, true,
                        loader);
                if (parent != null) {
                    bundle.setParent(parent);
                }
            }
            loaderCache.put(bundleName, bundle);
            return bundle;
        }

        if (extension != null && (loadBase || extension.length() > 0)) {
            bundle = handleGetBundle(base, extension, loadBase, loader);
            if (bundle != null) {
                loaderCache.put(bundleName, bundle);
                return bundle;
            }
        }
        loaderCache.put(bundleName, loadBase ? MISSINGBASE : MISSING);
        return null;
    }

    /**
     * Returns the named resource from this {@code ResourceBundle}, or null if the
     * resource is not found.
     * 
     * @param key
     *            the name of the resource.
     * @return the resource object.
     */
    protected abstract Object handleGetObject(String key);

    /**
     * Sets the parent resource bundle of this {@code ResourceBundle}. The parent is
     * searched for resources which are not found in this {@code ResourceBundle}.
     * 
     * @param bundle
     *            the parent {@code ResourceBundle}.
     */
    protected void setParent(ResourceBundle bundle) {
        parent = bundle;
    }

    private static String strip(String name) {
        int index = name.lastIndexOf('_');
        if (index != -1) {
            return name.substring(0, index);
        }
        return null;
    }

    private void setLocale(Locale locale) {
        this.locale = locale;
    }

    private void setLocale(String name) {
        String language = EMPTY_STRING, country = EMPTY_STRING, variant = EMPTY_STRING;
        if (name.length() > 1) {
            int nextIndex = name.indexOf('_', 1);
            if (nextIndex == -1) {
                nextIndex = name.length();
            }
            language = name.substring(1, nextIndex);
            if (nextIndex + 1 < name.length()) {
                int index = nextIndex;
                nextIndex = name.indexOf('_', nextIndex + 1);
                if (nextIndex == -1) {
                    nextIndex = name.length();
                }
                country = name.substring(index + 1, nextIndex);
                if (nextIndex + 1 < name.length()) {
                    variant = name.substring(nextIndex + 1, name.length());
                }
            }
        }
        locale = new Locale(language, country, variant);
    }

    public static final void clearCache() {
        cache.remove(ClassLoader.getSystemClassLoader());
    }

    public static final void clearCache(ClassLoader loader) {
        if (null == loader) {
            throw new NullPointerException();
        }
        cache.remove(loader);
    }

    public boolean containsKey(String key) {
        if (null == key) {
            throw new NullPointerException();
        }
        return keySet().contains(key);
    }

    public Set<String> keySet() {
        Set<String> ret = new HashSet<String>();
        Enumeration<String> keys = getKeys();
        while (keys.hasMoreElements()) {
            ret.add(keys.nextElement());
        }
        return ret;
    }

    protected Set<String> handleKeySet() {
        Set<String> set = keySet();
        Set<String> ret = new HashSet<String>();
        for (String key : set) {
            if (null != handleGetObject(key)) {
                ret.add(key);
            }
        }
        return ret;
    }

    private static class NoFallbackControl extends Control {

        static final Control NOFALLBACK_FORMAT_PROPERTIES_CONTROL = new NoFallbackControl(
                JAVAPROPERTIES);

        static final Control NOFALLBACK_FORMAT_CLASS_CONTROL = new NoFallbackControl(
                JAVACLASS);

        static final Control NOFALLBACK_FORMAT_DEFAULT_CONTROL = new NoFallbackControl(
                listDefault);

        public NoFallbackControl(String format) {
            super();
            listClass = new ArrayList<String>();
            listClass.add(format);
            super.format = Collections.unmodifiableList(listClass);
        }

        public NoFallbackControl(List<String> list) {
            super();
            super.format = list;
        }

        @Override
        public Locale getFallbackLocale(String baseName, Locale locale) {
            if (null == baseName || null == locale) {
                throw new NullPointerException();
            }
            return null;
        }
    }

    private static class SimpleControl extends Control {
        public SimpleControl(String format) {
            super();
            listClass = new ArrayList<String>();
            listClass.add(format);
            super.format = Collections.unmodifiableList(listClass);
        }
    }

    @SuppressWarnings("nls")
    /**
     * ResourceBundle.Control is a static utility class defines ResourceBundle
     * load access methods, its default access order is as the same as before.
     * However users can implement their own control.
     * 
     * @since 1.6
     */
    public static class Control {
        static List<String> listDefault = new ArrayList<String>();

        static List<String> listClass = new ArrayList<String>();

        static List<String> listProperties = new ArrayList<String>();

        static String JAVACLASS = "java.class";

        static String JAVAPROPERTIES = "java.properties";

        static {
            listDefault.add(JAVACLASS);
            listDefault.add(JAVAPROPERTIES);
            listClass.add(JAVACLASS);
            listProperties.add(JAVAPROPERTIES);
        }

        /**
         * a list defines default format
         */
        public static final List<String> FORMAT_DEFAULT = Collections
                .unmodifiableList(listDefault);

        /**
         * a list defines java class format
         */
        public static final List<String> FORMAT_CLASS = Collections
                .unmodifiableList(listClass);

        /**
         * a list defines property format
         */
        public static final List<String> FORMAT_PROPERTIES = Collections
                .unmodifiableList(listProperties);

        /**
         * a constant that indicates cache will not be used.
         */
        public static final long TTL_DONT_CACHE = -1L;

        /**
         * a constant that indicates cache will not be expired.
         */
        public static final long TTL_NO_EXPIRATION_CONTROL = -2L;

        private static final Control FORMAT_PROPERTIES_CONTROL = new SimpleControl(
                JAVAPROPERTIES);

        private static final Control FORMAT_CLASS_CONTROL = new SimpleControl(
                JAVACLASS);

        private static final Control FORMAT_DEFAULT_CONTROL = new Control();

        List<String> format;

        /**
         * default constructor
         * 
         */
        protected Control() {
            super();
            listClass = new ArrayList<String>();
            listClass.add(JAVACLASS);
            listClass.add(JAVAPROPERTIES);
            format = Collections.unmodifiableList(listClass);
        }

        /**
         * Answers a control according to the given format list
         * 
         * @param formats
         *            a format to use
         * @return a control according to the given format list
         */
        public static final Control getControl(List<String> formats) {
            switch (formats.size()) {
            case 1:
                if (formats.contains(JAVACLASS)) {
                    return FORMAT_CLASS_CONTROL;
                }
                if (formats.contains(JAVAPROPERTIES)) {
                    return FORMAT_PROPERTIES_CONTROL;
                }
                break;
            case 2:
                if (formats.equals(FORMAT_DEFAULT)) {
                    return FORMAT_DEFAULT_CONTROL;
                }
                break;
            }
            throw new IllegalArgumentException();
        }

        /**
         * Answers a control according to the given format list whose fallback
         * locale is null
         * 
         * @param formats
         *            a format to use
         * @return a control according to the given format list whose fallback
         *         locale is null
         */
        public static final Control getNoFallbackControl(List<String> formats) {
            switch (formats.size()) {
            case 1:
                if (formats.contains(JAVACLASS)) {
                    return NoFallbackControl.NOFALLBACK_FORMAT_CLASS_CONTROL;
                }
                if (formats.contains(JAVAPROPERTIES)) {
                    return NoFallbackControl.NOFALLBACK_FORMAT_PROPERTIES_CONTROL;
                }
                break;
            case 2:
                if (formats.equals(FORMAT_DEFAULT)) {
                    return NoFallbackControl.NOFALLBACK_FORMAT_DEFAULT_CONTROL;
                }
                break;
            }
            throw new IllegalArgumentException();
        }

        /**
         * Answers a list of candidate locales according to the base name and
         * locale
         * 
         * @param baseName
         *            the base name to use
         * @param locale
         *            the locale
         * @return the candidate locales according to the base name and locale
         */
        public List<Locale> getCandidateLocales(String baseName, Locale locale) {
            if (null == baseName || null == locale) {
                throw new NullPointerException();
            }
            List<Locale> retList = new ArrayList<Locale>();
            String language = locale.getLanguage();
            String country = locale.getCountry();
            String variant = locale.getVariant();
            if (!EMPTY_STRING.equals(variant)) {
                retList.add(new Locale(language, country, variant));
            }
            if (!EMPTY_STRING.equals(country)) {
                retList.add(new Locale(language, country));
            }
            if (!EMPTY_STRING.equals(language)) {
                retList.add(new Locale(language));
            }
            retList.add(Locale.ROOT);
            return retList;
        }

        /**
         * Answers a list of strings of formats according to the base name
         * 
         * @param baseName
         *            the base name to use
         * @return a list of strings of formats according to the base name
         */
        public List<String> getFormats(String baseName) {
            if (null == baseName) {
                throw new NullPointerException();
            }
            return format;
        }

        /**
         * Answers a list of strings of locales according to the base name
         * 
         * @param baseName
         *            the base name to use
         * @return a list of strings of locales according to the base name
         */
        public Locale getFallbackLocale(String baseName, Locale locale) {
            if (null == baseName || null == locale) {
                throw new NullPointerException();
            }
            if (Locale.getDefault() != locale) {
                return Locale.getDefault();
            }
            return null;
        }

        /**
         * Answers a new ResourceBundle according to the give parameters
         * 
         * @param baseName
         *            the base name to use
         * @param locale
         *            the given locale
         * @param format
         *            the format, default is "java.class" or "java.properities"
         * @param loader
         *            the classloader to use
         * @param reload
         *            if reload the resource
         * @return a new ResourceBundle according to the give parameters
         * @throws IllegalAccessException
         *             if can not access resources
         * @throws InstantiationException
         *             if can not instante a resource class
         * @throws IOException
         *             if other I/O exception happens
         */
        public ResourceBundle newBundle(String baseName, Locale locale,
                String format, ClassLoader loader, boolean reload)
                throws IllegalAccessException, InstantiationException,
                IOException {
            if (null == format || null == loader) {
                throw new NullPointerException();
            }
            InputStream streams = null;
            final String bundleName = toBundleName(baseName, locale);
            final ClassLoader clsloader = loader;
            ResourceBundle ret;
            Class<?> cls = null;
            if (JAVACLASS == format) {
                cls = AccessController
                        .doPrivileged(new PrivilegedAction<Class<?>>() {
                            public Class<?> run() {
                                try {
                                    return clsloader.loadClass(bundleName);
                                } catch (Exception e) {
                                    return null;
                                } catch (NoClassDefFoundError e) {
                                    return null;
                                }
                            }
                        });
                if (null == cls) {
                    return null;
                }
                try {
                    ResourceBundle bundle = (ResourceBundle) cls.newInstance();
                    bundle.setLocale(locale);
                    return bundle;
                } catch (NullPointerException e) {
                    return null;
                }
            }
            if (JAVAPROPERTIES == format) {
                final String resourceName = toResourceName(bundleName,
                        "properties");
                if (reload) {
                    URL url = null;
                    try {
                        url = loader.getResource(resourceName);
                    } catch (NullPointerException e) {
                        // do nothing
                    }
                    if (null != url) {
                        URLConnection con = url.openConnection();
                        con.setUseCaches(false);
                        streams = con.getInputStream();
                    }
                } else {
                    try {
                        streams = AccessController
                                .doPrivileged(new PrivilegedAction<InputStream>() {
                                    public InputStream run() {
                                        return clsloader
                                                .getResourceAsStream(resourceName);
                                    }
                                });
                    } catch (NullPointerException e) {
                        // do nothing
                    }
                }
                if (streams != null) {
                    try {
                        ret = new PropertyResourceBundle(new InputStreamReader(streams));
                        ret.setLocale(locale);
                        streams.close();
                    } catch (IOException e) {
                        return null;
                    }
                    return ret;
                }
                return null;
            }
            throw new IllegalArgumentException();
        }

        /**
         * Answers the time to live of the ResourceBundle, default is
         * TTL_NO_EXPIRATION_CONTROL
         * 
         * @param baseName
         *            the base name to use
         * @param locale
         *            the locale to use
         * @return TTL_NO_EXPIRATION_CONTROL
         */
        public long getTimeToLive(String baseName, Locale locale) {
            if (null == baseName || null == locale) {
                throw new NullPointerException();
            }
            return TTL_NO_EXPIRATION_CONTROL;
        }

        /**
         * Answers if the ResourceBundle needs to reload
         * 
         * @param baseName
         *            the base name of the ResourceBundle
         * @param locale
         *            the locale of the ResourceBundle
         * @param format
         *            the format to load
         * @param loader
         *            the ClassLoader to load resource
         * @param bundle
         *            the ResourceBundle
         * @param loadTime
         *            the expired time
         * @return if the ResourceBundle needs to reload
         */
        public boolean needsReload(String baseName, Locale locale,
                String format, ClassLoader loader, ResourceBundle bundle,
                long loadTime) {
            if (null == bundle) {
                // FIXME what's the use of bundle?
                throw new NullPointerException();
            }
            String bundleName = toBundleName(baseName, locale);
            String suffix = format;
            if (JAVACLASS == format) {
                suffix = "class";
            }
            if (JAVAPROPERTIES == format) {
                suffix = "properties";
            }
            String urlname = toResourceName(bundleName, suffix);
            URL url = loader.getResource(urlname);
            if (null != url) {
                String fileName = url.getFile();
                long lastModified = new File(fileName).lastModified();
                if (lastModified > loadTime) {
                    return true;
                }
            }
            return false;
        }

        /**
         * a utility method to answer the name of a resource bundle according to
         * the given base name and locale
         * 
         * @param baseName
         *            the given base name
         * @param locale
         *            the locale to use
         * @return the name of a resource bundle according to the given base
         *         name and locale
         */
        public String toBundleName(String baseName, Locale locale) {
            final String emptyString = EMPTY_STRING;
            final String preString = UNDER_SCORE;
            final String underline = UNDER_SCORE;
            if (null == baseName) {
                throw new NullPointerException();
            }
            StringBuilder ret = new StringBuilder();
            StringBuilder prefix = new StringBuilder();
            ret.append(baseName);
            if (!locale.getLanguage().equals(emptyString)) {
                ret.append(underline);
                ret.append(locale.getLanguage());
            } else {
                prefix.append(preString);
            }
            if (!locale.getCountry().equals(emptyString)) {
                ret.append((CharSequence) prefix);
                ret.append(underline);
                ret.append(locale.getCountry());
                prefix = new StringBuilder();
            } else {
                prefix.append(preString);
            }
            if (!locale.getVariant().equals(emptyString)) {
                ret.append((CharSequence) prefix);
                ret.append(underline);
                ret.append(locale.getVariant());
            }
            return ret.toString();
        }

        /**
         * a utility method to answer the name of a resource according to the
         * given bundleName and suffix
         * 
         * @param bundleName
         *            the given bundle name
         * @param suffix
         *            the suffix
         * @return the name of a resource according to the given bundleName and
         *         suffix
         */
        public final String toResourceName(String bundleName, String suffix) {
            if (null == suffix) {
                throw new NullPointerException();
            }
            StringBuilder ret = new StringBuilder(bundleName.replace('.', '/'));
            ret.append('.');
            ret.append(suffix);
            return ret.toString();
        }
    }
}
