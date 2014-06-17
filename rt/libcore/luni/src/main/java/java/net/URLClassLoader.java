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

package java.net;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FilePermission;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.SecureClassLoader;
import java.security.cert.Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import libcore.io.IoUtils;
import libcore.io.Streams;

/**
 * This class loader is responsible for loading classes and resources from a
 * list of URLs which can refer to either directories or JAR files. Classes
 * loaded by this {@code URLClassLoader} are granted permission to access the
 * URLs contained in the URL search list.
 */
@FindBugsSuppressWarnings({ "DMI_COLLECTION_OF_URLS", "DP_CREATE_CLASSLOADER_INSIDE_DO_PRIVILEGED" })
public class URLClassLoader extends SecureClassLoader {

    ArrayList<URL> originalUrls;

    List<URL> searchList;
    ArrayList<URLHandler> handlerList;
    Map<URL, URLHandler> handlerMap = new HashMap<URL, URLHandler>();

    private URLStreamHandlerFactory factory;

    static class IndexFile {

        private HashMap<String, ArrayList<URL>> map;
        //private URLClassLoader host;


        static IndexFile readIndexFile(JarFile jf, JarEntry indexEntry, URL url) {
            BufferedReader in = null;
            InputStream is = null;
            try {
                // Add mappings from resource to jar file
                String parentURLString = getParentURL(url).toExternalForm();
                String prefix = "jar:" + parentURLString + "/";
                is = jf.getInputStream(indexEntry);
                in = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                HashMap<String, ArrayList<URL>> pre_map = new HashMap<String, ArrayList<URL>>();
                // Ignore the 2 first lines (index version)
                if (in.readLine() == null) return null;
                if (in.readLine() == null) return null;
                TOP_CYCLE:
                while (true) {
                    String line = in.readLine();
                    if (line == null) {
                        break;
                    }
                    URL jar = new URL(prefix + line + "!/");
                    while (true) {
                        line = in.readLine();
                        if (line == null) {
                            break TOP_CYCLE;
                        }
                        if (line.isEmpty()) {
                            break;
                        }
                        ArrayList<URL> list;
                        if (pre_map.containsKey(line)) {
                            list = pre_map.get(line);
                        } else {
                            list = new ArrayList<URL>();
                            pre_map.put(line, list);
                        }
                        list.add(jar);
                    }
                }
                if (!pre_map.isEmpty()) {
                    return new IndexFile(pre_map);
                }
            } catch (MalformedURLException e) {
                // Ignore this jar's index
            } catch (IOException e) {
                // Ignore this jar's index
            } finally {
                IoUtils.closeQuietly(in);
                IoUtils.closeQuietly(is);
            }
            return null;
        }

        private static URL getParentURL(URL url) throws IOException {
            URL fileURL = ((JarURLConnection) url.openConnection()).getJarFileURL();
            String file = fileURL.getFile();
            String parentFile = new File(file).getParent();
            parentFile = parentFile.replace(File.separatorChar, '/');
            if (parentFile.charAt(0) != '/') {
                parentFile = "/" + parentFile;
            }
            URL parentURL = new URL(fileURL.getProtocol(), fileURL
                    .getHost(), fileURL.getPort(), parentFile);
            return parentURL;
        }

        public IndexFile(HashMap<String, ArrayList<URL>> map) {
            this.map = map;
        }

        ArrayList<URL> get(String name) {
            return map.get(name);
        }
    }

    class URLHandler {
        URL url;
        URL codeSourceUrl;

        public URLHandler(URL url) {
            this.url = url;
            this.codeSourceUrl = url;
        }

        void findResources(String name, ArrayList<URL> resources) {
            URL res = findResource(name);
            if (res != null && !resources.contains(res)) {
                resources.add(res);
            }
        }

        Class<?> findClass(String packageName, String name, String origName) {
            URL resURL = targetURL(url, name);
            if (resURL != null) {
                try {
                    InputStream is = resURL.openStream();
                    return createClass(is, packageName, origName);
                } catch (IOException e) {
                }
            }
            return null;
        }


        Class<?> createClass(InputStream is, String packageName, String origName) {
            if (is == null) {
                return null;
            }
            byte[] clBuf;
            try {
                clBuf = Streams.readFully(is);
            } catch (IOException e) {
                return null;
            }
            if (packageName != null) {
                String packageDotName = packageName.replace('/', '.');
                Package packageObj = getPackage(packageDotName);
                if (packageObj == null) {
                    definePackage(packageDotName, null, null,
                            null, null, null, null, null);
                } else {
                    if (packageObj.isSealed()) {
                        throw new SecurityException("Package is sealed");
                    }
                }
            }
            return defineClass(origName, clBuf, 0, clBuf.length, new CodeSource(codeSourceUrl, (Certificate[]) null));
        }

        URL findResource(String name) {
            URL resURL = targetURL(url, name);
            if (resURL != null) {
                try {
                    URLConnection uc = resURL.openConnection();
                    uc.getInputStream().close();
                    // HTTP can return a stream on a non-existent file
                    // So check for the return code;
                    if (!resURL.getProtocol().equals("http")) {
                        return resURL;
                    }
                    int code;
                    if ((code = ((HttpURLConnection) uc).getResponseCode()) >= 200
                            && code < 300) {
                        return resURL;
                    }
                } catch (SecurityException e) {
                    return null;
                } catch (IOException e) {
                    return null;
                }
            }
            return null;
        }

        URL targetURL(URL base, String name) {
            try {
                StringBuilder fileBuilder = new StringBuilder();
                fileBuilder.append(base.getFile());
                URI.PATH_ENCODER.appendEncoded(fileBuilder, name);
                String file = fileBuilder.toString();

                return new URL(base.getProtocol(), base.getHost(), base.getPort(), file, null);
            } catch (MalformedURLException e) {
                return null;
            }
        }

    }

    class URLJarHandler extends URLHandler {
        final JarFile jf;
        final String prefixName;
        final IndexFile index;
        final Map<URL, URLHandler> subHandlers = new HashMap<URL, URLHandler>();

        public URLJarHandler(URL url, URL jarURL, JarFile jf, String prefixName) {
            super(url);
            this.jf = jf;
            this.prefixName = prefixName;
            this.codeSourceUrl = jarURL;
            final JarEntry je = jf.getJarEntry("META-INF/INDEX.LIST");
            this.index = (je == null ? null : IndexFile.readIndexFile(jf, je, url));
        }

        public URLJarHandler(URL url, URL jarURL, JarFile jf, String prefixName, IndexFile index) {
            super(url);
            this.jf = jf;
            this.prefixName = prefixName;
            this.index = index;
            this.codeSourceUrl = jarURL;
        }

        IndexFile getIndex() {
            return index;
        }

        @Override
        void findResources(String name, ArrayList<URL> resources) {
            URL res = findResourceInOwn(name);
            if (res != null && !resources.contains(res)) {
                resources.add(res);
            }
            if (index != null) {
                int pos = name.lastIndexOf("/");
                // only keep the directory part of the resource
                // as index.list only keeps track of directories and root files
                String indexedName = (pos > 0) ? name.substring(0, pos) : name;
                ArrayList<URL> urls = index.get(indexedName);
                if (urls != null) {
                    urls.remove(url);
                    for (URL url : urls) {
                        URLHandler h = getSubHandler(url);
                        if (h != null) {
                            h.findResources(name, resources);
                        }
                    }
                }
            }

        }

        @Override
        Class<?> findClass(String packageName, String name, String origName) {
            String entryName = prefixName + name;
            JarEntry entry = jf.getJarEntry(entryName);
            if (entry != null) {
                /**
                 * Avoid recursive load class, especially the class
                 * is an implementation class of security provider
                 * and the jar is signed.
                 */
                try {
                    Manifest manifest = jf.getManifest();
                    return createClass(entry, manifest, packageName, origName);
                } catch (IOException e) {
                }
            }
            if (index != null) {
                ArrayList<URL> urls;
                if (packageName == null) {
                    urls = index.get(name);
                } else {
                    urls = index.get(packageName);
                }
                if (urls != null) {
                    urls.remove(url);
                    for (URL url : urls) {
                        URLHandler h = getSubHandler(url);
                        if (h != null) {
                            Class<?> res = h.findClass(packageName, name, origName);
                            if (res != null) {
                                return res;
                            }
                        }
                    }
                }
            }
            return null;
        }

        private Class<?> createClass(JarEntry entry, Manifest manifest, String packageName, String origName) {
            byte[] clBuf;
            try {
                InputStream is = jf.getInputStream(entry);
                clBuf = Streams.readFully(is);
            } catch (IOException e) {
                return null;
            }
            if (packageName != null) {
                String packageDotName = packageName.replace('/', '.');
                Package packageObj = getPackage(packageDotName);
                if (packageObj == null) {
                    if (manifest != null) {
                        definePackage(packageDotName, manifest,
                                codeSourceUrl);
                    } else {
                        definePackage(packageDotName, null, null,
                                null, null, null, null, null);
                    }
                } else {
                    boolean exception = packageObj.isSealed();
                    if (manifest != null) {
                        if (isSealed(manifest, packageName + "/")) {
                            exception = !packageObj
                                    .isSealed(codeSourceUrl);
                        }
                    }
                    if (exception) {
                        throw new SecurityException(String.format("Package %s is sealed",
                                packageName));
                    }
                }
            }
            CodeSource codeS = new CodeSource(codeSourceUrl, entry.getCertificates());
            return defineClass(origName, clBuf, 0, clBuf.length, codeS);
        }

        URL findResourceInOwn(String name) {
            String entryName = prefixName + name;
            if (jf.getEntry(entryName) != null) {
                return targetURL(url, name);
            }
            return null;
        }

        @Override
        URL findResource(String name) {
            URL res = findResourceInOwn(name);
            if (res != null) {
                return res;
            }
            if (index != null) {
                int pos = name.lastIndexOf("/");
                // only keep the directory part of the resource
                // as index.list only keeps track of directories and root files
                String indexedName = (pos > 0) ? name.substring(0, pos) : name;
                ArrayList<URL> urls = index.get(indexedName);
                if (urls != null) {
                    urls.remove(url);
                    for (URL url : urls) {
                        URLHandler h = getSubHandler(url);
                        if (h != null) {
                            res = h.findResource(name);
                            if (res != null) {
                                return res;
                            }
                        }
                    }
                }
            }
            return null;
        }

        private synchronized URLHandler getSubHandler(URL url) {
            URLHandler sub = subHandlers.get(url);
            if (sub != null) {
                return sub;
            }
            String protocol = url.getProtocol();
            if (protocol.equals("jar")) {
                sub = createURLJarHandler(url);
            } else if (protocol.equals("file")) {
                sub = createURLSubJarHandler(url);
            } else {
                sub = createURLHandler(url);
            }
            if (sub != null) {
                subHandlers.put(url, sub);
            }
            return sub;
        }

        private URLHandler createURLSubJarHandler(URL url) {
            String prefixName;
            String file = url.getFile();
            if (url.getFile().endsWith("!/")) {
                prefixName = "";
            } else {
                int sepIdx = file.lastIndexOf("!/");
                if (sepIdx == -1) {
                    // Invalid URL, don't look here again
                    return null;
                }
                sepIdx += 2;
                prefixName = file.substring(sepIdx);
            }
            try {
                URL jarURL = ((JarURLConnection) url
                        .openConnection()).getJarFileURL();
                JarURLConnection juc = (JarURLConnection) new URL(
                        "jar", "",
                        jarURL.toExternalForm() + "!/").openConnection();
                JarFile jf = juc.getJarFile();
                URLJarHandler jarH = new URLJarHandler(url, jarURL, jf, prefixName, null);
                // TODO : to think what we should do with indexes & manifest.class file here
                return jarH;
            } catch (IOException e) {
            }
            return null;
        }

    }

    class URLFileHandler extends URLHandler {
        private String prefix;

        public URLFileHandler(URL url) {
            super(url);
            String baseFile = url.getFile();
            String host = url.getHost();
            int hostLength = 0;
            if (host != null) {
                hostLength = host.length();
            }
            StringBuilder buf = new StringBuilder(2 + hostLength
                    + baseFile.length());
            if (hostLength > 0) {
                buf.append("//").append(host);
            }
            // baseFile always ends with '/'
            buf.append(baseFile);
            prefix = buf.toString();
        }

        @Override
        Class<?> findClass(String packageName, String name, String origName) {
            String filename = prefix + name;
            try {
                filename = URLDecoder.decode(filename, "UTF-8");
            } catch (IllegalArgumentException e) {
                return null;
            } catch (UnsupportedEncodingException e) {
                return null;
            }

            File file = new File(filename);
            if (file.exists()) {
                try {
                    InputStream is = new FileInputStream(file);
                    return createClass(is, packageName, origName);
                } catch (FileNotFoundException e) {
                }
            }
            return null;
        }

        @Override
        URL findResource(String name) {
            int idx = 0;
            String filename;

            // Do not create a UNC path, i.e. \\host
            while (idx < name.length() &&
                   ((name.charAt(idx) == '/') || (name.charAt(idx) == '\\'))) {
                idx++;
            }

            if (idx > 0) {
                name = name.substring(idx);
            }

            try {
                filename = URLDecoder.decode(prefix, "UTF-8") + name;

                if (new File(filename).exists()) {
                    return targetURL(url, name);
                }
                return null;
            } catch (IllegalArgumentException e) {
                return null;
            } catch (UnsupportedEncodingException e) {
                // must not happen
                throw new AssertionError(e);
            }
        }

    }


    /**
     * Constructs a new {@code URLClassLoader} instance. The newly created
     * instance will have the system ClassLoader as its parent. URLs that end
     * with "/" are assumed to be directories, otherwise they are assumed to be
     * JAR files.
     *
     * @param urls
     *            the list of URLs where a specific class or file could be
     *            found.
     */
    public URLClassLoader(URL[] urls) {
        this(urls, ClassLoader.getSystemClassLoader(), null);
    }

    /**
     * Constructs a new URLClassLoader instance. The newly created instance will
     * have the system ClassLoader as its parent. URLs that end with "/" are
     * assumed to be directories, otherwise they are assumed to be JAR files.
     *
     * @param urls
     *            the list of URLs where a specific class or file could be
     *            found.
     * @param parent
     *            the class loader to assign as this loader's parent.
     */
    public URLClassLoader(URL[] urls, ClassLoader parent) {
        this(urls, parent, null);
    }

    /**
     * Adds the specified URL to the search list.
     *
     * @param url
     *            the URL which is to add.
     */
    protected void addURL(URL url) {
        try {
            originalUrls.add(url);
            searchList.add(createSearchURL(url));
        } catch (MalformedURLException e) {
        }
    }

    /**
     * Returns all known URLs which point to the specified resource.
     *
     * @param name
     *            the name of the requested resource.
     * @return the enumeration of URLs which point to the specified resource.
     * @throws IOException
     *             if an I/O error occurs while attempting to connect.
     */
    @Override
    public Enumeration<URL> findResources(final String name) throws IOException {
        if (name == null) {
            return null;
        }
        ArrayList<URL> result = new ArrayList<URL>();
        int n = 0;
        while (true) {
            URLHandler handler = getHandler(n++);
            if (handler == null) {
                break;
            }
            handler.findResources(name, result);
        }
        return Collections.enumeration(result);
    }

    /**
     * Gets all permissions for the specified {@code codesource}. First, this
     * method retrieves the permissions from the system policy. If the protocol
     * is "file:/" then a new permission, {@code FilePermission}, granting the
     * read permission to the file is added to the permission collection.
     * Otherwise, connecting to and accepting connections from the URL is
     * granted.
     *
     * @param codesource
     *            the code source object whose permissions have to be known.
     * @return the list of permissions according to the code source object.
     */
    @Override
    protected PermissionCollection getPermissions(final CodeSource codesource) {
        PermissionCollection pc = super.getPermissions(codesource);
        URL u = codesource.getLocation();
        if (u.getProtocol().equals("jar")) {
            try {
                // Create a URL for the resource the jar refers to
                u = ((JarURLConnection) u.openConnection()).getJarFileURL();
            } catch (IOException e) {
                // This should never occur. If it does continue using the jar
                // URL
            }
        }
        if (u.getProtocol().equals("file")) {
            String path = u.getFile();
            String host = u.getHost();
            if (host != null && host.length() > 0) {
                path = "//" + host + path;
            }

            if (File.separatorChar != '/') {
                path = path.replace('/', File.separatorChar);
            }
            if (isDirectory(u)) {
                pc.add(new FilePermission(path + "-", "read"));
            } else {
                pc.add(new FilePermission(path, "read"));
            }
        } else {
            String host = u.getHost();
            if (host.length() == 0) {
                host = "localhost";
            }
            pc.add(new SocketPermission(host, "connect, accept"));
        }
        return pc;
    }

    /**
     * Returns the search list of this {@code URLClassLoader}.
     *
     * @return the list of all known URLs of this instance.
     */
    public URL[] getURLs() {
        return originalUrls.toArray(new URL[originalUrls.size()]);
    }

    /**
     * Determines if the URL is pointing to a directory.
     */
    private static boolean isDirectory(URL url) {
        String file = url.getFile();
        return (file.length() > 0 && file.charAt(file.length() - 1) == '/');
    }

    /**
     * Returns a new {@code URLClassLoader} instance for the given URLs and the
     * system {@code ClassLoader} as its parent.
     *
     * @param urls
     *            the list of URLs that is passed to the new {@code
     *            URLClassLoader}.
     * @return the created {@code URLClassLoader} instance.
     */
    public static URLClassLoader newInstance(final URL[] urls) {
        return new URLClassLoader(urls, ClassLoader.getSystemClassLoader());
    }

    /**
     * Returns a new {@code URLClassLoader} instance for the given URLs and the
     * specified {@code ClassLoader} as its parent.
     *
     * @param urls
     *            the list of URLs that is passed to the new URLClassLoader.
     * @param parentCl
     *            the parent class loader that is passed to the new
     *            URLClassLoader.
     * @return the created {@code URLClassLoader} instance.
     */
    public static URLClassLoader newInstance(final URL[] urls, final ClassLoader parentCl) {
        return new URLClassLoader(urls, parentCl);
    }

    /**
     * Constructs a new {@code URLClassLoader} instance. The newly created
     * instance will have the specified {@code ClassLoader} as its parent and
     * use the specified factory to create stream handlers. URLs that end with
     * "/" are assumed to be directories, otherwise they are assumed to be JAR
     * files.
     *
     * @param searchUrls
     *            the list of URLs where a specific class or file could be
     *            found.
     * @param parent
     *            the {@code ClassLoader} to assign as this loader's parent.
     * @param factory
     *            the factory that will be used to create protocol-specific
     *            stream handlers.
     */
    public URLClassLoader(URL[] searchUrls, ClassLoader parent, URLStreamHandlerFactory factory) {
        super(parent);
        this.factory = factory;
        int nbUrls = searchUrls.length;
        originalUrls = new ArrayList<URL>(nbUrls);
        handlerList = new ArrayList<URLHandler>(nbUrls);
        searchList = Collections.synchronizedList(new ArrayList<URL>(nbUrls));
        for (int i = 0; i < nbUrls; i++) {
            originalUrls.add(searchUrls[i]);
            try {
                searchList.add(createSearchURL(searchUrls[i]));
            } catch (MalformedURLException e) {
            }
        }
    }

    /**
     * Tries to locate and load the specified class using the known URLs. If the
     * class could be found, a class object representing the loaded class will
     * be returned.
     *
     * @throws ClassNotFoundException
     *             if the specified class cannot be loaded.
     */
    @Override
    protected Class<?> findClass(final String className) throws ClassNotFoundException {
        String partialName = className.replace('.', '/');
        final String classFileName = new StringBuilder(partialName).append(".class").toString();
        String packageName = null;
        int position = partialName.lastIndexOf('/');
        if ((position = partialName.lastIndexOf('/')) != -1) {
            packageName = partialName.substring(0, position);
        }
        int n = 0;
        while (true) {
            URLHandler handler = getHandler(n++);
            if (handler == null) {
                break;
            }
            Class<?> res = handler.findClass(packageName, classFileName, className);
            if (res != null) {
                return res;
            }
        }
        throw new ClassNotFoundException(className);
    }

    /**
     * Returns an URL that will be checked if it contains the class or resource.
     * If the file component of the URL is not a directory, a Jar URL will be
     * created.
     *
     * @return java.net.URL a test URL
     */
    private URL createSearchURL(URL url) throws MalformedURLException {
        if (url == null) {
            return url;
        }

        String protocol = url.getProtocol();

        if (isDirectory(url) || protocol.equals("jar")) {
            return url;
        }
        if (factory == null) {
            return new URL("jar", "",
                    -1, url.toString() + "!/");
        }
        // use jar protocol as the stream handler protocol
        return new URL("jar", "",
                -1, url.toString() + "!/",
                factory.createURLStreamHandler("jar"));
    }

    /**
     * Returns an URL referencing the specified resource or {@code null} if the
     * resource could not be found.
     *
     * @param name
     *            the name of the requested resource.
     * @return the URL which points to the given resource.
     */
    @Override
    public URL findResource(final String name) {
        if (name == null) {
            return null;
        }
        int n = 0;
        while (true) {
            URLHandler handler = getHandler(n++);
            if (handler == null) {
                break;
            }
            URL res = handler.findResource(name);
            if (res != null) {
                return res;
            }
        }
        return null;
    }

    private URLHandler getHandler(int num) {
        if (num < handlerList.size()) {
            return handlerList.get(num);
        }
        makeNewHandler();
        if (num < handlerList.size()) {
            return handlerList.get(num);
        }
        return null;
    }

    private synchronized void makeNewHandler() {
        while (!searchList.isEmpty()) {
            URL nextCandidate = searchList.remove(0);
            if (nextCandidate == null) {
                throw new NullPointerException("nextCandidate == null");
            }
            if (!handlerMap.containsKey(nextCandidate)) {
                URLHandler result;
                String protocol = nextCandidate.getProtocol();
                if (protocol.equals("jar")) {
                    result = createURLJarHandler(nextCandidate);
                } else if (protocol.equals("file")) {
                    result = createURLFileHandler(nextCandidate);
                } else {
                    result = createURLHandler(nextCandidate);
                }
                if (result != null) {
                    handlerMap.put(nextCandidate, result);
                    handlerList.add(result);
                    return;
                }
            }
        }
    }

    private URLHandler createURLHandler(URL url) {
        return new URLHandler(url);
    }

    private URLHandler createURLFileHandler(URL url) {
        return new URLFileHandler(url);
    }

    private URLHandler createURLJarHandler(URL url) {
        String prefixName;
        String file = url.getFile();
        if (url.getFile().endsWith("!/")) {
            prefixName = "";
        } else {
            int sepIdx = file.lastIndexOf("!/");
            if (sepIdx == -1) {
                // Invalid URL, don't look here again
                return null;
            }
            sepIdx += 2;
            prefixName = file.substring(sepIdx);
        }
        try {
            URL jarURL = ((JarURLConnection) url
                    .openConnection()).getJarFileURL();
            JarURLConnection juc = (JarURLConnection) new URL(
                    "jar", "",
                    jarURL.toExternalForm() + "!/").openConnection();
            JarFile jf = juc.getJarFile();
            URLJarHandler jarH = new URLJarHandler(url, jarURL, jf, prefixName);

            if (jarH.getIndex() == null) {
                try {
                    Manifest manifest = jf.getManifest();
                    if (manifest != null) {
                        String classpath = manifest.getMainAttributes().getValue(
                                Attributes.Name.CLASS_PATH);
                        if (classpath != null) {
                            searchList.addAll(0, getInternalURLs(url, classpath));
                        }
                    }
                } catch (IOException e) {
                }
            }
            return jarH;
        } catch (IOException e) {
        }
        return null;
    }

    /**
     * Defines a new package using the information extracted from the specified
     * manifest.
     *
     * @param packageName
     *            the name of the new package.
     * @param manifest
     *            the manifest containing additional information for the new
     *            package.
     * @param url
     *            the URL to the code source for the new package.
     * @return the created package.
     * @throws IllegalArgumentException
     *             if a package with the given name already exists.
     */
    protected Package definePackage(String packageName, Manifest manifest,
                                    URL url) throws IllegalArgumentException {
        Attributes mainAttributes = manifest.getMainAttributes();
        String dirName = packageName.replace('.', '/') + "/";
        Attributes packageAttributes = manifest.getAttributes(dirName);
        boolean noEntry = false;
        if (packageAttributes == null) {
            noEntry = true;
            packageAttributes = mainAttributes;
        }
        String specificationTitle = packageAttributes
                .getValue(Attributes.Name.SPECIFICATION_TITLE);
        if (specificationTitle == null && !noEntry) {
            specificationTitle = mainAttributes
                    .getValue(Attributes.Name.SPECIFICATION_TITLE);
        }
        String specificationVersion = packageAttributes
                .getValue(Attributes.Name.SPECIFICATION_VERSION);
        if (specificationVersion == null && !noEntry) {
            specificationVersion = mainAttributes
                    .getValue(Attributes.Name.SPECIFICATION_VERSION);
        }
        String specificationVendor = packageAttributes
                .getValue(Attributes.Name.SPECIFICATION_VENDOR);
        if (specificationVendor == null && !noEntry) {
            specificationVendor = mainAttributes
                    .getValue(Attributes.Name.SPECIFICATION_VENDOR);
        }
        String implementationTitle = packageAttributes
                .getValue(Attributes.Name.IMPLEMENTATION_TITLE);
        if (implementationTitle == null && !noEntry) {
            implementationTitle = mainAttributes
                    .getValue(Attributes.Name.IMPLEMENTATION_TITLE);
        }
        String implementationVersion = packageAttributes
                .getValue(Attributes.Name.IMPLEMENTATION_VERSION);
        if (implementationVersion == null && !noEntry) {
            implementationVersion = mainAttributes
                    .getValue(Attributes.Name.IMPLEMENTATION_VERSION);
        }
        String implementationVendor = packageAttributes
                .getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
        if (implementationVendor == null && !noEntry) {
            implementationVendor = mainAttributes
                    .getValue(Attributes.Name.IMPLEMENTATION_VENDOR);
        }

        return definePackage(packageName, specificationTitle,
                specificationVersion, specificationVendor, implementationTitle,
                implementationVersion, implementationVendor, isSealed(manifest,
                dirName) ? url : null);
    }

    private boolean isSealed(Manifest manifest, String dirName) {
        Attributes attributes = manifest.getAttributes(dirName);
        if (attributes != null) {
            String value = attributes.getValue(Attributes.Name.SEALED);
            if (value != null) {
                return value.equalsIgnoreCase("true");
            }
        }
        Attributes mainAttributes = manifest.getMainAttributes();
        String value = mainAttributes.getValue(Attributes.Name.SEALED);
        return (value != null && value.equalsIgnoreCase("true"));
    }

    /**
     * returns URLs referenced in the string classpath.
     *
     * @param root
     *            the jar URL that classpath is related to
     * @param classpath
     *            the relative URLs separated by spaces
     * @return URL[] the URLs contained in the string classpath.
     */
    private ArrayList<URL> getInternalURLs(URL root, String classpath) {
        // Class-path attribute is composed of space-separated values.
        StringTokenizer tokenizer = new StringTokenizer(classpath);
        ArrayList<URL> addedURLs = new ArrayList<URL>();
        String file = root.getFile();
        int jarIndex = file.lastIndexOf("!/") - 1;
        int index = file.lastIndexOf("/", jarIndex) + 1;
        if (index == 0) {
            index = file.lastIndexOf(
                    System.getProperty("file.separator"), jarIndex) + 1;
        }
        file = file.substring(0, index);
        while (tokenizer.hasMoreElements()) {
            String element = tokenizer.nextToken();
            if (!element.isEmpty()) {
                try {
                    // Take absolute path case into consideration
                    URL url = new URL(new URL(file), element);
                    addedURLs.add(createSearchURL(url));
                } catch (MalformedURLException e) {
                    // Nothing is added
                }
            }
        }
        return addedURLs;
    }
}
