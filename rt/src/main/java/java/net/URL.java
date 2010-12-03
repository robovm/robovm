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

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.security.AccessController;
import java.util.Hashtable;
import java.util.StringTokenizer;

import org.apache.harmony.luni.internal.nls.Messages;
import org.apache.harmony.luni.util.PriviAction;
import org.apache.harmony.luni.util.Util;

/**
 * A URL instance specifies the location of a resource on the internet as
 * specified by RFC 1738. Such a resource can be a simple file or a service
 * which generates the output dynamically. A URL is divided in its parts
 * protocol, host name, port, path, file, user-info, query, reference and
 * authority. However, not each of this parts has to be defined.
 */
public final class URL implements java.io.Serializable {
    private static final long serialVersionUID = -7627629688361524110L;

    private static final NetPermission specifyStreamHandlerPermission = new NetPermission(
            "specifyStreamHandler"); //$NON-NLS-1$

    private int hashCode;

    /**
     * The receiver's filename.
     * 
     * @serial the file of this URL
     * 
     */
    private String file;

    /**
     * The receiver's protocol identifier.
     * 
     * @serial the protocol of this URL (http, file)
     * 
     */
    private String protocol = null;

    /**
     * The receiver's host name.
     * 
     * @serial the host of this URL
     * 
     */
    private String host;

    /**
     * The receiver's port number.
     * 
     * @serial the port of this URL
     * 
     */
    private int port = -1;

    /**
     * The receiver's authority.
     * 
     * @serial the authority of this URL
     * 
     */
    private String authority = null;

    /**
     * The receiver's userInfo.
     */
    private transient String userInfo = null;

    /**
     * The receiver's path.
     */
    private transient String path = null;

    /**
     * The receiver's query.
     */
    private transient String query = null;

    /**
     * The receiver's reference.
     * 
     * @serial the reference of this URL
     * 
     */
    private String ref = null;

    /**
     * Cache for storing protocol handler
     */
    private static Hashtable<String, URLStreamHandler> streamHandlers = new Hashtable<String, URLStreamHandler>();

    /**
     * The URL Stream (protocol) Handler
     */
    transient URLStreamHandler strmHandler;

    /**
     * The factory responsible for producing URL Stream (protocol) Handler
     */
    private static URLStreamHandlerFactory streamHandlerFactory;

    /**
     * Sets the {@code URLStreamHandlerFactory} which creates protocol specific
     * stream handlers. This method can be invoked only once during an
     * application's lifetime. If the {@code URLStreamHandlerFactory} is already
     * set an {@link Error} will be thrown.
     * <p>
     * A security check is performed to verify whether the current policy allows
     * to set the stream handler factory.
     *
     * @param streamFactory
     *            the factory to be used for creating stream protocol handlers.
     */
    public static synchronized void setURLStreamHandlerFactory(
            URLStreamHandlerFactory streamFactory) {
        if (streamHandlerFactory != null) {
            throw new Error(Messages.getString("luni.9A")); //$NON-NLS-1$
        }
        SecurityManager sm = System.getSecurityManager();
        if (sm != null) {
            sm.checkSetFactory();
        }
        streamHandlers.clear();
        streamHandlerFactory = streamFactory;
    }

    /**
     * Creates a new URL instance by parsing the string {@code spec}.
     * 
     * @param spec
     *            the URL string representation which has to be parsed.
     * @throws MalformedURLException
     *             if the given string {@code spec} could not be parsed as a
     *             URL.
     */
    public URL(String spec) throws MalformedURLException {
        this((URL) null, spec, (URLStreamHandler) null);
    }

    /**
     * Creates a new URL to the specified resource {@code spec}. This URL is
     * relative to the given {@code context}. If the protocol of the parsed URL
     * does not match with the protocol of the context URL, then the newly
     * created URL is absolute and bases only on the given URL represented by
     * {@code spec}. Otherwise the protocol is defined by the context URL.
     * 
     * @param context
     *            the URL which is used as the context.
     * @param spec
     *            the URL string representation which has to be parsed.
     * @throws MalformedURLException
     *             if the given string {@code spec} could not be parsed as a URL
     *             or an invalid protocol has been found.
     */
    public URL(URL context, String spec) throws MalformedURLException {
        this(context, spec, (URLStreamHandler) null);
    }

    /**
     * Creates a new URL to the specified resource {@code spec}. This URL is
     * relative to the given {@code context}. The {@code handler} will be used
     * to parse the URL string representation. If this argument is {@code null}
     * the default {@code URLStreamHandler} will be used. If the protocol of the
     * parsed URL does not match with the protocol of the context URL, then the
     * newly created URL is absolute and bases only on the given URL represented
     * by {@code spec}. Otherwise the protocol is defined by the context URL.
     * 
     * @param context
     *            the URL which is used as the context.
     * @param spec
     *            the URL string representation which has to be parsed.
     * @param handler
     *            the specific stream handler to be used by this URL.
     * @throws MalformedURLException
     *             if the given string {@code spec} could not be parsed as a URL
     *             or an invalid protocol has been found.
     */
    public URL(URL context, String spec, URLStreamHandler handler)
            throws MalformedURLException {
        if (handler != null) {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                sm.checkPermission(specifyStreamHandlerPermission);
            }
            strmHandler = handler;
        }

        if (spec == null) {
            throw new MalformedURLException();
        }
        spec = spec.trim();

        // The spec includes a protocol if it includes a colon character
        // before the first occurrence of a slash character. Note that,
        // "protocol" is the field which holds this URLs protocol.
        int index;
        try {
            index = spec.indexOf(':');
        } catch (NullPointerException e) {
            throw new MalformedURLException(e.toString());
        }
        int startIPv6Addr = spec.indexOf('[');
        if (index >= 0) {
            if ((startIPv6Addr == -1) || (index < startIPv6Addr)) {
                protocol = spec.substring(0, index);
                // According to RFC 2396 scheme part should match
                // the following expression:
                // alpha *( alpha | digit | "+" | "-" | "." )
                char c = protocol.charAt(0);
                boolean valid = ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z');
                for (int i = 1; valid && (i < protocol.length()); i++) {
                    c = protocol.charAt(i);
                    valid = ('a' <= c && c <= 'z') ||
                            ('A' <= c && c <= 'Z') ||
                            ('0' <= c && c <= '9') ||
                            (c == '+') ||
                            (c == '-') ||
                            (c == '.');
                }
                if (!valid) {
                    protocol = null;
                    index = -1;
                } else {
                    // Ignore case in protocol names.
                	// Scheme is defined by ASCII characters.
                    protocol = Util.toASCIILowerCase(protocol);
                }
            }
        }

        if (protocol != null) {
            // If the context was specified, and it had the same protocol
            // as the spec, then fill in the receiver's slots from the values
            // in the context but still allow them to be over-ridden later
            // by the values in the spec.
            if (context != null && protocol.equals(context.getProtocol())) {
                String cPath = context.getPath();
                if (cPath != null && cPath.startsWith("/")) { //$NON-NLS-1$
                    set(protocol, context.getHost(), context.getPort(), context
                            .getAuthority(), context.getUserInfo(), cPath,
                            context.getQuery(), null);
                }
                if (strmHandler == null) {
                    strmHandler = context.strmHandler;
                }
            }
        } else {
            // If the spec did not include a protocol, then the context
            // *must* be specified. Fill in the receiver's slots from the
            // values in the context, but still allow them to be over-ridden
            // by the values in the ("relative") spec.
            if (context == null) {
                throw new MalformedURLException(
                        Messages.getString(
                                "luni.9B", spec)); //$NON-NLS-1$
            }
            set(context.getProtocol(), context.getHost(), context.getPort(),
                    context.getAuthority(), context.getUserInfo(), context
                            .getPath(), context.getQuery(), null);
            if (strmHandler == null) {
                strmHandler = context.strmHandler;
            }
        }

        // If the stream handler has not been determined, set it
        // to the default for the specified protocol.
        if (strmHandler == null) {
            setupStreamHandler();
            if (strmHandler == null) {
                throw new MalformedURLException(
                        Messages.getString(
                                "luni.9C", protocol)); //$NON-NLS-1$
            }
        }

        // Let the handler parse the URL. If the handler throws
        // any exception, throw MalformedURLException instead.
        //
        // Note: We want "index" to be the index of the start of the scheme
        // specific part of the URL. At this point, it will be either
        // -1 or the index of the colon after the protocol, so we
        // increment it to point at either character 0 or the character
        // after the colon.
        try {
            strmHandler.parseURL(this, spec, ++index, spec.length());
        } catch (Exception e) {
            throw new MalformedURLException(e.toString());
        }

        if (port < -1) {
            throw new MalformedURLException(Messages
                    .getString("luni.56", port)); //$NON-NLS-1$
        }
    }

    /**
     * Creates a new URL instance using the given arguments. The URL uses the
     * default port for the specified protocol.
     * 
     * @param protocol
     *            the protocol of the new URL.
     * @param host
     *            the host name or IP address of the new URL.
     * @param file
     *            the name of the resource.
     * @throws MalformedURLException
     *             if the combination of all arguments do not represent a valid
     *             URL or the protocol is invalid.
     */
    public URL(String protocol, String host, String file)
            throws MalformedURLException {
        this(protocol, host, -1, file, (URLStreamHandler) null);
    }

    /**
     * Creates a new URL instance using the given arguments. The URL uses the
     * specified port instead of the default port for the given protocol.
     * 
     * @param protocol
     *            the protocol of the new URL.
     * @param host
     *            the host name or IP address of the new URL.
     * @param port
     *            the specific port number of the URL. {@code -1} represents the
     *            default port of the protocol.
     * @param file
     *            the name of the resource.
     * @throws MalformedURLException
     *             if the combination of all arguments do not represent a valid
     *             URL or the protocol is invalid.
     */
    public URL(String protocol, String host, int port, String file)
            throws MalformedURLException {
        this(protocol, host, port, file, (URLStreamHandler) null);
    }

    /**
     * Creates a new URL instance using the given arguments. The URL uses the
     * specified port instead of the default port for the given protocol.
     *
     * @param protocol
     *            the protocol of the new URL.
     * @param host
     *            the host name or IP address of the new URL.
     * @param port
     *            the specific port number of the URL. {@code -1} represents the
     *            default port of the protocol.
     * @param file
     *            the name of the resource.
     * @param handler
     *            the stream handler to be used by this URL.
     * @throws MalformedURLException
     *             if the combination of all arguments do not represent a valid
     *             URL or the protocol is invalid.
     * @throws SecurityException
     *             if {@code handler} is non-{@code null}, and a security
     *             manager is installed that disallows user-defined protocol
     *             handlers.
     */
    public URL(String protocol, String host, int port, String file,
            URLStreamHandler handler) throws MalformedURLException {
        if (port < -1) {
            throw new MalformedURLException(Messages.getString("luni.56", port)); //$NON-NLS-1$
        }

        if (host != null && host.indexOf(":") != -1 && host.charAt(0) != '[') { //$NON-NLS-1$
            host = "[" + host + "]"; //$NON-NLS-1$ //$NON-NLS-2$
        }

        if (protocol == null) {
            throw new NullPointerException(Messages.getString("luni.9C", "null")); //$NON-NLS-1$ //$NON-NLS-2$
        }

        this.protocol = protocol;
        this.host = host;
        this.port = port;

        // Set the fields from the arguments. Handle the case where the
        // passed in "file" includes both a file and a reference part.
        int index = -1;
        index = file.indexOf("#", file.lastIndexOf("/")); //$NON-NLS-1$ //$NON-NLS-2$
        if (index >= 0) {
            this.file = file.substring(0, index);
            ref = file.substring(index + 1);
        } else {
            this.file = file;
        }
        fixURL(false);

        // Set the stream handler for the URL either to the handler
        // argument if it was specified, or to the default for the
        // receiver's protocol if the handler was null.
        if (handler == null) {
            setupStreamHandler();
            if (strmHandler == null) {
                throw new MalformedURLException(
                        Messages.getString("luni.9C", protocol)); //$NON-NLS-1$
            }
        } else {
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                sm.checkPermission(specifyStreamHandlerPermission);
            }
            strmHandler = handler;
        }
    }

    void fixURL(boolean fixHost) {
        int index;
        if (host != null && host.length() > 0) {
            authority = host;
            if (port != -1) {
                authority = authority + ":" + port; //$NON-NLS-1$
            }
        }
        if (fixHost) {
            if (host != null && (index = host.lastIndexOf('@')) > -1) {
                userInfo = host.substring(0, index);
                host = host.substring(index + 1);
            } else {
                userInfo = null;
            }
        }
        if (file != null && (index = file.indexOf('?')) > -1) {
            query = file.substring(index + 1);
            path = file.substring(0, index);
        } else {
            query = null;
            path = file;
        }
    }

    /**
     * Sets the properties of this URL using the provided arguments. Only a
     * {@code URLStreamHandler} can use this method to set fields of the
     * existing URL instance. A URL is generally constant.
     * 
     * @param protocol
     *            the protocol to be set.
     * @param host
     *            the host name to be set.
     * @param port
     *            the port number to be set.
     * @param file
     *            the file to be set.
     * @param ref
     *            the reference to be set.
     */
    protected void set(String protocol, String host, int port, String file,
            String ref) {
        if (this.protocol == null) {
            this.protocol = protocol;
        }
        this.host = host;
        this.file = file;
        this.port = port;
        this.ref = ref;
        hashCode = 0;
        fixURL(true);
    }

    /**
     * Compares this URL instance with the given argument {@code o} and
     * determines if both are equal. Two URL instances are equal if all single
     * parts are identical in their meaning. Compares the argument to the
     * receiver, and returns true if they represent the same URL. Two URLs are
     * equal if they have the same file, host, port, protocol, and reference
     * components.
     * 
     * @param o
     *            the URL this instance has to be compared with.
     * @return {@code true} if both instances represents the same URL, {@code
     *         false} otherwise.
     * @see #hashCode()
     */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (this.getClass() != o.getClass()) {
            return false;
        }
        return strmHandler.equals(this, (URL) o);
    }

    /**
     * Returns whether this URL refers to the same resource as the given
     * argument {@code otherURL}. All URL components except the reference field
     * are compared.
     * 
     * @param otherURL
     *            the URL to compare against.
     * @return {@code true} if both instances refer to the same resource,
     *         {@code false} otherwise.
     */
    public boolean sameFile(URL otherURL) {
        return strmHandler.sameFile(this, otherURL);
    }

    /**
     * Gets the hashcode value of this URL instance.
     * 
     * @return the appropriate hashcode value.
     */
    @Override
    public int hashCode() {
        if (hashCode == 0) {
            hashCode = strmHandler.hashCode(this);
        }
        return hashCode;
    }

    /**
     * Sets the receiver's stream handler to one which is appropriate for its
     * protocol. Throws a MalformedURLException if no reasonable handler is
     * available.
     * <p>
     * Note that this will overwrite any existing stream handler with the new
     * one. Senders must check if the strmHandler is null before calling the
     * method if they do not want this behavior (a speed optimization).
     */
    void setupStreamHandler() {
        // Check for a cached (previously looked up) handler for
        // the requested protocol.
        strmHandler = streamHandlers.get(protocol);
        if (strmHandler != null) {
            return;
        }

        // If there is a stream handler factory, then attempt to
        // use it to create the handler.
        if (streamHandlerFactory != null) {
            strmHandler = streamHandlerFactory.createURLStreamHandler(protocol);
            if (strmHandler != null) {
                streamHandlers.put(protocol, strmHandler);
                return;
            }
        }

        // Check if there is a list of packages which can provide handlers.
        // If so, then walk this list looking for an applicable one.
        String packageList = AccessController
                .doPrivileged(new PriviAction<String>(
                        "java.protocol.handler.pkgs")); //$NON-NLS-1$
        if (packageList != null) {
            StringTokenizer st = new StringTokenizer(packageList, "|"); //$NON-NLS-1$
            while (st.hasMoreTokens()) {
                String className = st.nextToken() + "." + protocol + ".Handler"; //$NON-NLS-1$ //$NON-NLS-2$

                try {
                    strmHandler = (URLStreamHandler) Class.forName(className,
                            true, ClassLoader.getSystemClassLoader())
                            .newInstance();
                    if (strmHandler != null) {
                        streamHandlers.put(protocol, strmHandler);
                    }
                    return;
                } catch (IllegalAccessException e) {
                } catch (InstantiationException e) {
                } catch (ClassNotFoundException e) {
                }
            }
        }

        // No one else has provided a handler, so try our internal one.

        String className = "org.apache.harmony.luni.internal.net.www.protocol." + protocol //$NON-NLS-1$
                + ".Handler"; //$NON-NLS-1$
        try {
            strmHandler = (URLStreamHandler) Class.forName(className)
                    .newInstance();
        } catch (IllegalAccessException e) {
        } catch (InstantiationException e) {
        } catch (ClassNotFoundException e) {
        }
        if (strmHandler != null) {
            streamHandlers.put(protocol, strmHandler);
        }

    }

    /**
     * Gets the content of the resource which is referred by this URL. By
     * default one of the following object types will be returned:
     * <p>
     * <li>Image for pictures</li>
     * <li>AudioClip for audio sequences</li>
     * <li>{@link InputStream} for all other data</li>
     * 
     * @return the content of the referred resource.
     * @throws IOException
     *             if an error occurs obtaining the content.
     */
    public final Object getContent() throws IOException {
        return openConnection().getContent();
    }

    /**
     * Gets the content of the resource which is referred by this URL. The
     * argument {@code types} is an array of allowed or expected object types.
     * {@code null} will be returned if the obtained object type does not match
     * with one from this list. Otherwise the first type that matches will be
     * used.
     * 
     * @param types
     *            the list of allowed or expected object types.
     * @return the object representing the resource referred by this URL,
     *         {@code null} if the content does not match to a specified content
     *         type.
     * @throws IOException
     *             if an error occurs obtaining the content.
     */
    // Param not generic in spec
    @SuppressWarnings("unchecked")
    public final Object getContent(Class[] types) throws IOException {
        return openConnection().getContent(types);
    }

    /**
     * Opens an InputStream to read the resource referred by this URL.
     * 
     * @return the stream which allows to read the resource.
     * @throws IOException
     *             if an error occurs while opening the InputStream.
     */
    public final InputStream openStream() throws java.io.IOException {
        return openConnection().getInputStream();
    }

    /**
     * Opens a connection to the remote resource specified by this URL. This
     * connection allows bidirectional data transfer.
     * 
     * @return the connection to this URL.
     * @throws IOException
     *             if an error occurs while opening the connection.
     */
    public URLConnection openConnection() throws IOException {
        return strmHandler.openConnection(this);
    }

    /**
     * Converts this URL instance into an equivalent URI object.
     * 
     * @return the URI instance that represents this URL.
     * @throws URISyntaxException
     *             if this URL cannot be converted into a URI.
     */
    public URI toURI() throws URISyntaxException {
        return new URI(toExternalForm());
    }

    /**
     * Opens a connection to the remote resource specified by this URL. The
     * connection will be established through the given proxy and allows
     * bidirectional data transfer.
     * 
     * @param proxy
     *            the proxy through which the connection will be established.
     * @return the appropriate URLconnection instance representing the
     *         connection to this URL.
     * @throws IOException
     *             if an I/O error occurs while opening the connection.
     * @throws SecurityException
     *             if a security manager is installed and it denies to connect
     *             to the proxy.
     * @throws IllegalArgumentException
     *             if the argument proxy is {@code null} or is an invalid type.
     * @throws UnsupportedOperationException
     *             if the protocol handler does not support opening connections
     *             through proxies.
     */
    public URLConnection openConnection(Proxy proxy) throws IOException {
        if (proxy == null) {
            // luni.9D=proxy should not be null
            throw new IllegalArgumentException(Messages.getString("luni.9D")); //$NON-NLS-1$
        }

        SecurityManager sm = System.getSecurityManager();
        if (sm != null && proxy.type() != Proxy.Type.DIRECT) {
            InetSocketAddress pAddress = (InetSocketAddress) proxy.address();
            String pHostName = pAddress.isUnresolved() ? pAddress.getHostName()
                    : pAddress.getAddress().getHostAddress();
            sm.checkConnect(pHostName, pAddress.getPort());
        }

        return strmHandler.openConnection(this, proxy);
    }

    /**
     * Returns a string containing a concise, human-readable representation of
     * this URL. The returned string is the same as the result of the method
     * {@code toExternalForm()}.
     * 
     * @return the string representation of this URL.
     */
    @Override
    public String toString() {
        return toExternalForm();
    }

    /**
     * Returns a string containing a concise, human-readable representation of
     * this URL.
     * 
     * @return the string representation of this URL.
     */
    public String toExternalForm() {
        if (strmHandler == null) {
            return "unknown protocol(" + protocol + ")://" + host + file; //$NON-NLS-1$ //$NON-NLS-2$
        }
        return strmHandler.toExternalForm(this);
    }

    /**
     * This method is called to restore the state of a URL object that has been
     * serialized. The stream handler is determined from the URL's protocol.
     * 
     * @param stream
     *            the stream to read from.
     * 
     * @throws IOException
     *             if an IO Exception occurs while reading the stream or the
     *             handler can not be found.
     */
    private void readObject(java.io.ObjectInputStream stream)
            throws java.io.IOException {
        try {
            stream.defaultReadObject();
            if (host != null && authority == null) {
                fixURL(true);
            } else if (authority != null) {
                int index;
                if ((index = authority.lastIndexOf('@')) > -1) {
                    userInfo = authority.substring(0, index);
                }
                if (file != null && (index = file.indexOf('?')) > -1) {
                    query = file.substring(index + 1);
                    path = file.substring(0, index);
                } else {
                    path = file;
                }
            }
            setupStreamHandler();
            if (strmHandler == null) {
                throw new IOException(Messages.getString("luni.9C", protocol)); //$NON-NLS-1$
            }
        } catch (ClassNotFoundException e) {
            throw new IOException(e.toString());
        }
    }

    /**
     * This method is called to write any non-transient, non-static variables
     * into the output stream.
     * <p>
     * Note that, we really only need the readObject method but the spec that
     * says readObject will be ignored if no writeObject is present.
     *
     * @param s
     *            the stream to write on.
     * @throws IOException
     *             if an IO Exception occurs during the write.
     */
    private void writeObject(ObjectOutputStream s) throws IOException {
        s.defaultWriteObject();
    }

    /**
     * Gets the value of the file part of this URL.
     * 
     * @return the file name this URL refers to or an empty string if the file
     *         part is not set.
     */
    public String getFile() {
        return file;
    }

    /**
     * Gets the value of the host part of this URL.
     * 
     * @return the host name or IP address of this URL.
     */
    public String getHost() {
        return host;
    }

    /**
     * Gets the port number of this URL or {@code -1} if the port is not set.
     * 
     * @return the port number of this URL.
     */
    public int getPort() {
        return port;
    }

    /**
     * Gets the protocol of this URL.
     * 
     * @return the protocol type of this URL.
     */
    public String getProtocol() {
        return protocol;
    }

    /**
     * Gets the value of the reference part of this URL.
     * 
     * @return the reference part of this URL.
     */
    public String getRef() {
        return ref;
    }

    /**
     * Gets the value of the query part of this URL.
     * 
     * @return the query part of this URL.
     */
    public String getQuery() {
        return query;
    }

    /**
     * Gets the value of the path part of this URL.
     * 
     * @return the path part of this URL.
     */
    public String getPath() {
        return path;
    }

    /**
     * Gets the value of the user-info part of this URL.
     * 
     * @return the user-info part of this URL.
     */
    public String getUserInfo() {
        return userInfo;
    }

    /**
     * Gets the value of the authority part of this URL.
     * 
     * @return the authority part of this URL.
     */
    public String getAuthority() {
        return authority;
    }

    /**
     * Sets the properties of this URL using the provided arguments. Only a
     * {@code URLStreamHandler} can use this method to set fields of the
     * existing URL instance. A URL is generally constant.
     * 
     * @param protocol
     *            the protocol to be set.
     * @param host
     *            the host name to be set.
     * @param port
     *            the port number to be set.
     * @param authority
     *            the authority to be set.
     * @param userInfo
     *            the user-info to be set.
     * @param path
     *            the path to be set.
     * @param query
     *            the query to be set.
     * @param ref
     *            the reference to be set.
     */
    protected void set(String protocol, String host, int port,
            String authority, String userInfo, String path, String query,
            String ref) {
        String filePart = path;
        if (query != null && !query.equals("")) { //$NON-NLS-1$
            if (filePart != null) {
                filePart = filePart + "?" + query; //$NON-NLS-1$
            } else {
                filePart = "?" + query; //$NON-NLS-1$
            }
        }
        set(protocol, host, port, filePart, ref);
        this.authority = authority;
        this.userInfo = userInfo;
        this.path = path;
        this.query = query;
    }

    /**
     * Gets the default port number of the protocol used by this URL. If no
     * default port is defined by the protocol or the {@code URLStreamHandler},
     * {@code -1} will be returned.
     *
     * @return the default port number according to the protocol of this URL.
     * @see URLStreamHandler#getDefaultPort
     */
    public int getDefaultPort() {
        return strmHandler.getDefaultPort();
    }
}
