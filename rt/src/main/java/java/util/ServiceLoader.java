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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import org.apache.harmony.luni.util.Msg;

/**
 * Service loader is a service-provider loading utility class.
 * @param <S> 
 * 
 * @since 1.6
 */
public final class ServiceLoader<S> implements Iterable<S> {

    private static final String META_INF_SERVICES = "META-INF/services/"; //$NON-NLS-1$

    private Set<URL> services;

    private Class<S> service;

    private ClassLoader loader;
    
    private ServiceLoader(){
        // do nothing
    }

    /**
     * reloads the services
     * 
     */
    public void reload() {
        internalLoad(this, service, loader);
    }

    /**
     * Answers the iterator of this ServiceLoader
     * 
     * @return the iterator of this ServiceLoader
     */
    public Iterator<S> iterator() {
        return new ServiceIterator(this);
    }

    /**
     * Constructs a serviceloader.
     * 
     * @param service
     *            the given service class or interface
     * @param loader
     *            the given class loader
     * @return a new ServiceLoader
     */
    public static <S> ServiceLoader<S> load(Class<S> service, ClassLoader loader) {
        ServiceLoader<S> sl = new ServiceLoader<S>();
        sl.service = service;
        sl.loader = loader;
        sl.services = new HashSet<URL>();
        internalLoad(sl, service, loader);
        return sl;
    }

    // try to find all jars that contains the service. Note according to the
    // lazy load, the service will not load this time.
    private static void internalLoad(ServiceLoader<?> sl, Class<?> service,
            ClassLoader loader) {
        Enumeration<URL> profiles = null;
        if (null == service) {
            sl.services.add(null);
            return;
        }
        try {
            if (null == loader) {
                profiles = ClassLoader.getSystemResources(META_INF_SERVICES
                        + service.getName());
            } else {
                profiles = loader.getResources(META_INF_SERVICES
                        + service.getName());
            }
        } catch (IOException e) {
            return;
        }
        if (null != profiles) {
            while (profiles.hasMoreElements()) {
                URL url = profiles.nextElement();
                sl.services.add(url);
            }
        }
    }

    /**
     * Constructs a serviceloader.
     * 
     * @param service
     *            the given service class or interface
     * @return a new ServiceLoader
     */
    public static <S> ServiceLoader<S> load(Class<S> service) {
        return ServiceLoader.load(service, Thread.currentThread()
                .getContextClassLoader());
    }

    /**
     * Constructs a serviceloader with extension class loader.
     * 
     * @param service
     *            the given service class or interface
     * @return a new ServiceLoader
     */
    public static <S> ServiceLoader<S> loadInstalled(Class<S> service) {
        // extClassLoader
        ClassLoader cl = ClassLoader.getSystemClassLoader();
        if (null != cl) {
            while (null != cl.getParent()) {
                cl = cl.getParent();
            }
        }
        return ServiceLoader.load(service, cl);
    }

    /**
     * Answers a string that indicate the information of this ServiceLoader
     * 
     * @return a string that indicate the information of this ServiceLoader
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ServiceLoader of "); //$NON-NLS-1$
        sb.append(service.getName());
        return sb.toString();
    }

    // inner class for returning
    private class ServiceIterator implements Iterator<S> {

        private static final String SINGAL_SHARP = "#"; //$NON-NLS-1$

        private ClassLoader cl;

        private Class<S> service;

        private Set<URL> services;

        private BufferedReader reader = null;

        private boolean isRead = false;

        private Queue<String> que;

        public ServiceIterator(ServiceLoader<S> sl) {
            cl = sl.loader;
            service = sl.service;
            services = sl.services;
        }

        public boolean hasNext() {
            if (!isRead) {
                readClass();
            }
            if (null != que && !que.isEmpty()) {
                return true;
            }
            return false;
        }

        @SuppressWarnings("unchecked")
        public S next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            String clsName = que.remove();
            try {
                S ret;
                if (null == cl) {
                    ret = service.cast(Class.forName(clsName).newInstance());
                } else {
                    ret = service.cast(cl.loadClass(clsName).newInstance());
                }
                return ret;
            } catch (Exception e) {
                // according to spec, this is a special design
                throw new ServiceConfigurationError(Msg.getString("KB005", //$NON-NLS-1$
                        clsName), e);
            }
        }

        private void readClass() {
            if (null == services) {
                isRead = true;
                return;
            }
            Iterator<URL> iter = services.iterator();
            que = new LinkedList<String>();
            while (iter.hasNext()) {                
                URL url = iter.next();                
                if (null == url) {
                    // follow RI
                    throw new NullPointerException();
                }
                try {
                    reader = new BufferedReader(new InputStreamReader(url
                            .openStream(), "UTF-8")); //$NON-NLS-1$
                    
                    String str;
                    // find class name (skip lines starts with "#")
                    while (true) {
                        str = reader.readLine();
                        if (null == str) {
                            break;
                        }
                        String[] strs = str.trim().split(SINGAL_SHARP);
                        if (0 != strs.length) {
                            str = strs[0].trim();
                            if (!(str.startsWith(SINGAL_SHARP) || 0 == str
                                    .length())) {
                                // a java class name, check if identifier
                                // correct
                                char[] namechars = str.toCharArray();
                                for (int i = 0; i < namechars.length; i++) {
                                    if (!(Character
                                            .isJavaIdentifierPart(namechars[i]) || namechars[i] == '.')) {
                                        throw new ServiceConfigurationError(Msg
                                                .getString("KB006", //$NON-NLS-1$
                                                        namechars[i]));
                                    }
                                }
                                // correct, if it does not exist in the que, add
                                // it to que
                                if (!que.contains(str)) {
                                    que.add(str);
                                }
                            }
                        }
                    }
                } catch (Exception e) {
                    // according to spec, this is a special design
                    throw new ServiceConfigurationError(Msg.getString("KB006", //$NON-NLS-1$
                            url), e);
                }
            }
            isRead = true;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }
}
