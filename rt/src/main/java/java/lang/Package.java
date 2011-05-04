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

package java.lang;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.net.URL;

/**
 * Contains information about a Java package. This includes implementation and
 * specification versions. Typically this information is retrieved from the
 * manifest.
 * <p>
 * Packages are managed by class loaders. All classes loaded by the same loader
 * from the same package share a {@code Package} instance.
 *
 * @see ClassLoader
 * @since 1.0
 */
public class Package implements AnnotatedElement {

    /*
     * The code in this class has been copied from Android
     */

    // Start (C) Android

    private final String name, specTitle, specVersion, specVendor, implTitle,
            implVersion, implVendor;
    private final URL sealBase;

    Package(String name, String specTitle, String specVersion, String specVendor,
            String implTitle, String implVersion, String implVendor, URL sealBase) {
        this.name = name;
        this.specTitle = specTitle;
        this.specVersion = specVersion;
        this.specVendor = specVendor;
        this.implTitle = implTitle;
        this.implVersion = implVersion;
        this.implVendor = implVendor;
        this.sealBase = sealBase;
    }

    /**
     * Gets the annotation associated with the specified annotation type and
     * this package, if present.
     *
     * @param annotationType
     *            the annotation type to look for.
     * @return an instance of {@link Annotation} or {@code null}.
     * @see java.lang.reflect.AnnotatedElement#getAnnotation(java.lang.Class)
     */
    @SuppressWarnings("unchecked")
    public <A extends Annotation> A getAnnotation(Class<A> annotationType) {
        Annotation[] list = getAnnotations();
        for (int i = 0; i < list.length; i++) {
            if (annotationType.isInstance(list[i])) {
                return (A) list[i];
            }
        }

        return null;
    }

    /**
     * Gets all annotations associated with this package, if any.
     *
     * @return an array of {@link Annotation} instances, which may be empty.
     * @see java.lang.reflect.AnnotatedElement#getAnnotations()
     */
    public Annotation[] getAnnotations() {
        return getDeclaredAnnotations(this, true);
    }

    /**
     * Gets all annotations directly declared on this package, if any.
     *
     * @return an array of {@link Annotation} instances, which may be empty.
     * @see java.lang.reflect.AnnotatedElement#getDeclaredAnnotations()
     */
    public Annotation[] getDeclaredAnnotations() {
        return getDeclaredAnnotations(this, false);
    }

    /*
     * Returns the list of declared annotations of the given package.
     * If no annotations exist, an empty array is returned.
     *
     * @param pkg the package of interest
     * @param publicOnly reflects whether we want only public annotation or all
     * of them.
     * @return the list of annotations
     */
    // TODO(Google) Provide proper (native) implementation.
    private static native Annotation[] getDeclaredAnnotations(Package pkg,
            boolean publicOnly);

    /**
     * Indicates whether the specified annotation is present.
     *
     * @param annotationType
     *            the annotation type to look for.
     * @return {@code true} if the annotation is present; {@code false}
     *         otherwise.
     * @see java.lang.reflect.AnnotatedElement#isAnnotationPresent(java.lang.Class)
     */
    public boolean isAnnotationPresent(
            Class<? extends Annotation> annotationType) {
        return getAnnotation(annotationType) != null;
    }

    /**
     * Returns the title of the implementation of this package, or {@code null}
     * if this is unknown. The format of this string is unspecified.
     *
     * @return the implementation title, may be {@code null}.
     */
    public String getImplementationTitle() {
        return implTitle;
    }

    /**
     * Returns the name of the vendor or organization that provides this
     * implementation of the package, or {@code null} if this is unknown. The
     * format of this string is unspecified.
     *
     * @return the implementation vendor name, may be {@code null}.
     */
    public String getImplementationVendor() {
        return implVendor;
    }

    /**
     * Returns the version of the implementation of this package, or {@code
     * null} if this is unknown. The format of this string is unspecified.
     *
     * @return the implementation version, may be {@code null}.
     */
    public String getImplementationVersion() {
        return implVersion;
    }

    /**
     * Returns the name of this package in the standard dot notation; for
     * example: "java.lang".
     *
     * @return the name of this package.
     */
    public String getName() {
        return name;
    }

    /**
     * Attempts to locate the requested package in the caller's class loader. If
     * no package information can be located, {@code null} is returned.
     *
     * @param packageName
     *            the name of the package to find.
     * @return the requested package, or {@code null}.
     * @see ClassLoader#getPackage(java.lang.String)
     */
    public static Package getPackage(String packageName) {
        ClassLoader classloader = ClassLoader.callerClassLoader();
        return classloader.getPackage(packageName);
    }

    /**
     * Returns all the packages known to the caller's class loader.
     *
     * @return all the packages known to the caller's class loader.
     * @see ClassLoader#getPackages
     */
    public static Package[] getPackages() {
        ClassLoader classloader = ClassLoader.callerClassLoader();
        return classloader.getPackages();
    }

    /**
     * Returns the title of the specification this package implements, or
     * {@code null} if this is unknown.
     *
     * @return the specification title, may be {@code null}.
     */
    public String getSpecificationTitle() {
        return specTitle;
    }

    /**
     * Returns the name of the vendor or organization that owns and maintains
     * the specification this package implements, or {@code null} if this is
     * unknown.
     *
     * @return the specification vendor name, may be {@code null}.
     */
    public String getSpecificationVendor() {
        return specVendor;
    }

    /**
     * Returns the version of the specification this package implements, or
     * {@code null} if this is unknown. The version string is a sequence of
     * non-negative integers separated by dots; for example: "1.2.3".
     *
     * @return the specification version string, may be {@code null}.
     */
    public String getSpecificationVersion() {
        return specVersion;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    /**
     * Indicates whether this package's specification version is compatible with
     * the specified version string. Version strings are compared by comparing
     * each dot separated part of the version as an integer.
     *
     * @param version
     *            the version string to compare against.
     * @return {@code true} if the package versions are compatible; {@code
     *         false} otherwise.
     * @throws NumberFormatException
     *             if this package's version string or the one provided are not
     *             in the correct format.
     */
    public boolean isCompatibleWith(String version)
            throws NumberFormatException {
        String[] requested = version.split("\\.");
        String[] provided = specVersion.split("\\.");

        for (int i = 0; i < Math.min(requested.length, provided.length); i++) {
            int reqNum = Integer.parseInt(requested[i]);
            int provNum = Integer.parseInt(provided[i]);

            if (reqNum > provNum) {
                return false;
            } else if (reqNum < provNum) {
                return true;
            }
        }

        if (requested.length > provided.length) {
            return false;
        }

        return true;
    }

    /**
     * Indicates whether this package is sealed.
     *
     * @return {@code true} if this package is sealed; {@code false} otherwise.
     */
    public boolean isSealed() {
        return sealBase != null;
    }

    /**
     * Indicates whether this package is sealed with respect to the specified
     * URL.
     *
     * @param url
     *            the URL to check.
     * @return {@code true} if this package is sealed with {@code url}; {@code
     *         false} otherwise
     */
    public boolean isSealed(URL url) {
        return sealBase != null && sealBase.sameFile(url);
    }

    @Override
    public String toString() {
        return "package " + name;
    }
    
    // End (C) Android
}
