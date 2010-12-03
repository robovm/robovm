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

package java.nio.channels.spi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.channels.Channel;
import java.nio.channels.DatagramChannel;
import java.nio.channels.Pipe;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Enumeration;

import org.apache.harmony.luni.platform.Platform;
import org.apache.harmony.nio.internal.SelectorProviderImpl;

/**
 * {@code SelectorProvider} is an abstract base class that declares methods for
 * providing instances of {@link DatagramChannel}, {@link Pipe},
 * {@link java.nio.channels.Selector} , {@link ServerSocketChannel}, and
 * {@link SocketChannel}. All the methods of this class are thread-safe.
 * <p>
 * A provider instance can be retrieved through a system property or the
 * configuration file in a jar file; if no provide is available that way then
 * the system default provider is returned.
 */
public abstract class SelectorProvider extends Object {

    private static final String SYMBOL_COMMENT = "#"; //$NON-NLS-1$

    private static final String PROVIDER_IN_SYSTEM_PROPERTY = "java.nio.channels.spi.SelectorProvider"; //$NON-NLS-1$

    private static final String PROVIDER_IN_JAR_RESOURCE = "META-INF/services/java.nio.channels.spi.SelectorProvider"; //$NON-NLS-1$

    private static SelectorProvider provider = null;

    private static Channel inheritedChannel;

    /**
     * Constructs a new {@code SelectorProvider}.
     * 
     * @throws SecurityException
     *             if there is a security manager installed that does not permit
     *             the runtime permission labeled "selectorProvider".
     */
    protected SelectorProvider() {
        super();
        if (null != System.getSecurityManager()) {
            System.getSecurityManager().checkPermission(
                    new RuntimePermission("selectorProvider")); //$NON-NLS-1$
        }
    }

    /**
     * Gets a provider instance by executing the following steps when called for
     * the first time:
     * <ul>
     * <li> if the system property "java.nio.channels.spi.SelectorProvider" is
     * set, the value of this property is the class name of the provider
     * returned; </li>
     * <li>if there is a provider-configuration file named
     * "java.nio.channels.spi.SelectorProvider" in META-INF/services of a jar
     * file valid in the system class loader, the first class name is the
     * provider's class name; </li>
     * <li> otherwise, a system default provider will be returned.</li>
     * </ul>
     *
     * @return the provider.
     */
    synchronized public static SelectorProvider provider() {
        if (null == provider) {
            provider = loadProviderByProperty();
            if (null == provider) {
                provider = loadProviderByJar();
            }
            if (null == provider) {
                provider = AccessController
                        .doPrivileged(new PrivilegedAction<SelectorProvider>() {
                            public SelectorProvider run() {
                                return new SelectorProviderImpl();
                            }
                        });
            }
        }
        return provider;
    }

    /*
     * load the provider in the jar file of class path.
     */
    static SelectorProvider loadProviderByJar() {
        Enumeration<URL> enumeration = null;

        ClassLoader classLoader = AccessController
                .doPrivileged(new PrivilegedAction<ClassLoader>() {
                    public ClassLoader run() {
                        return ClassLoader.getSystemClassLoader();
                    }
                });
        try {
            enumeration = classLoader.getResources(PROVIDER_IN_JAR_RESOURCE);
        } catch (IOException e) {
            throw new Error(e);
        }
        if (null == enumeration) {
            return null;
        }
        // for every jar, read until we find the provider name.
        while (enumeration.hasMoreElements()) {
            BufferedReader br = null;
            String className = null;
            try {
                br = new BufferedReader(new InputStreamReader((enumeration
                        .nextElement()).openStream()));
            } catch (Exception e) {
                continue;
            }
            try {
                // only the first class is loaded ,as spec says, not the same as
                // we do before.
                while ((className = br.readLine()) != null) {
                    className = className.trim();
                    int siteComment = className.indexOf(SYMBOL_COMMENT);
                    className = (-1 == siteComment) ? className : className
                            .substring(0, siteComment);
                    if (0 < className.length()) {
                        return (SelectorProvider) classLoader.loadClass(
                                className).newInstance();
                    }
                }
            } catch (Exception e) {
                throw new Error(e);
            } finally {
                try {
                    br.close();
                } catch (IOException ioe) {
                    // Ignore
                }
            }
        }
        return null;
    }

    /*
     * Load by system property.
     */
    static SelectorProvider loadProviderByProperty() {
        return AccessController
                .doPrivileged(new PrivilegedAction<SelectorProvider>() {
                    public SelectorProvider run() {
                        try {
                            final String className = System
                                    .getProperty(PROVIDER_IN_SYSTEM_PROPERTY);
                            if (null != className) {
                                Class<?> spClass = ClassLoader
                                        .getSystemClassLoader().loadClass(
                                                className);
                                return (SelectorProvider) spClass.newInstance();
                            }
                            return null;
                        } catch (Exception e) {
                            throw new Error(e);
                        }
                    }
                });
    }

    /**
     * Creates a new open {@code DatagramChannel}.
     * 
     * @return the new channel.
     * @throws IOException
     *             if an I/O error occurs.
     */
    public abstract DatagramChannel openDatagramChannel() throws IOException;

    /**
     * Creates a new {@code Pipe}.
     * 
     * @return the new pipe.
     * @throws IOException
     *             if an I/O error occurs.
     */
    public abstract Pipe openPipe() throws IOException;

    /**
     * Creates a new selector.
     * 
     * @return the new selector.
     * @throws IOException
     *             if an I/O error occurs.
     */
    public abstract AbstractSelector openSelector() throws IOException;

    /**
     * Creates a new open {@code ServerSocketChannel}.
     * 
     * @return the new channel.
     * @throws IOException
     *             if an I/O error occurs.
     */
    public abstract ServerSocketChannel openServerSocketChannel()
            throws IOException;

    /**
     * Create a new open {@code SocketChannel}.
     * 
     * @return the new channel.
     * @throws IOException
     *             if an I/O error occurs.
     */
    public abstract SocketChannel openSocketChannel() throws IOException;

    /**
     * Returns the channel inherited from the instance that created this
     * virtual machine.
     * 
     * @return the channel.
     * @throws IOException
     *             if an I/O error occurs.
     * @throws SecurityException
     *             if there is a security manager installed that does not permit
     *             the runtime permission labeled "selectorProvider".
     */
    public Channel inheritedChannel() throws IOException {
        if (null == inheritedChannel) {
            inheritedChannel = Platform.getNetworkSystem().inheritedChannel();
        }
        return inheritedChannel;
    }
}
