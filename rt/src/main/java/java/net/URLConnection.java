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
import java.io.OutputStream;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.apache.harmony.luni.internal.net.www.MimeTable;
import org.apache.harmony.luni.internal.nls.Messages;
import org.apache.harmony.luni.util.PriviAction;

/**
 * Concrete implementations of the abstract {@code URLConnection} class provide
 * a communication link to a URL for exchanging data with a specific protocol
 * type. A {@code URLConnection} can only be set up after the instantiation but
 * before connecting to the remote resource.
 */
public abstract class URLConnection {

    /**
     * The URL which represents the remote target of this {@code URLConnection}.
     */
    protected URL url;

    private String contentType;

    private static boolean defaultAllowUserInteraction;

    private static boolean defaultUseCaches = true;

    ContentHandler defaultHandler = new DefaultContentHandler();

    private long lastModified = -1;

    /**
     * The data must be modified more recently than this time in milliseconds
     * since January 1, 1970, GMT to be transmitted.
     */
    protected long ifModifiedSince;

    /**
     * Specifies whether the using of caches is enabled or the data has to be
     * recent for every request.
     */
    protected boolean useCaches = defaultUseCaches;

    /**
     * Specifies whether this {@code URLConnection} is already connected to the
     * remote resource. If this field is set to {@code true} the flags for
     * setting up the connection are not changeable anymore.
     */
    protected boolean connected;

    /**
     * Specifies whether this {@code URLConnection} allows sending data.
     */
    protected boolean doOutput;

    /**
     * Specifies whether this {@code URLConnection} allows receiving data.
     */
    protected boolean doInput = true;

    /**
     * Specifies whether this {@code URLConnection} allows user interaction as
     * it is needed for authentication purposes.
     */
    protected boolean allowUserInteraction = defaultAllowUserInteraction;

    private static ContentHandlerFactory contentHandlerFactory;

    private int readTimeout = 0;

    private int connectTimeout = 0;

    /**
     * Cache for storing content handler
     */
    static Hashtable<String, Object> contentHandlers = new Hashtable<String, Object>();

    /**
     * HashMap for storing request property key-valuelist pairs
     */
    private HashMap<String, List<String>> requestProperties = new HashMap<String, List<String>>();

    /**
     * A hashtable that maps the filename extension (key) to a MIME-type
     * (element)
     */
    private static FileNameMap fileNameMap;

    /**
     * Creates a new {@code URLConnection} instance pointing to the resource
     * specified by the given URL.
     *
     * @param url
     *            the URL which represents the resource this {@code
     *            URLConnection} will point to.
     */
    protected URLConnection(URL url) {
        this.url = url;
    }

    /**
     * Establishes the connection to the earlier configured resource. The
     * connection can only be set up before this method has been called.
     * 
     * @throws IOException
     *             if an error occurs while connecting to the resource.
     */
    public abstract void connect() throws IOException;

    /**
     * Gets the option value which indicates whether user interaction is allowed
     * on this {@code URLConnection}.
     * 
     * @return the value of the option {@code allowUserInteraction}.
     * @see #allowUserInteraction
     */
    public boolean getAllowUserInteraction() {
        return allowUserInteraction;
    }

    /**
     * Gets an object representing the content of the resource this {@code
     * URLConnection} is connected to. First, it attempts to get the content
     * type from the method {@code getContentType()} which looks at the response
     * header field "Content-Type". If none is found it will guess the content
     * type from the filename extension. If that fails the stream itself will be
     * used to guess the content type.
     * 
     * @return the content representing object.
     * @throws IOException
     *             if an error occurs obtaining the content.
     */
    public Object getContent() throws java.io.IOException {
        if (!connected) {
            connect();
        }

        if ((contentType = getContentType()) == null) {
            if ((contentType = guessContentTypeFromName(url.getFile())) == null) {
                contentType = guessContentTypeFromStream(getInputStream());
            }
        }
        if (contentType != null) {
            return getContentHandler(contentType).getContent(this);
        }
        return null;
    }

    /**
     * Gets an object representing the content of the resource this {@code
     * URLConnection} is connected to. First, it attempts to get the content
     * type from the method {@code getContentType()} which looks at the response
     * header field "Content-Type". If none is found it will guess the content
     * type from the filename extension. If that fails the stream itself will be
     * used to guess the content type. The content type must match with one of
     * the list {@code types}.
     * 
     * @param types
     *            the list of acceptable content types.
     * @return the content representing object or {@code null} if the content
     *         type does not match with one of the specified types.
     * @throws IOException
     *             if an error occurs obtaining the content.
     */
    // Param is not generic in spec
    @SuppressWarnings("unchecked")
    public Object getContent(Class[] types) throws IOException {
        if (!connected) {
            connect();
        }

        if ((contentType = getContentType()) == null) {
            if ((contentType = guessContentTypeFromName(url.getFile())) == null) {
                contentType = guessContentTypeFromStream(getInputStream());
            }
        }
        if (contentType != null) {
            return getContentHandler(contentType).getContent(this, types);
        }
        return null;
    }

    /**
     * Gets the content encoding type specified by the response header field
     * {@code content-encoding} or {@code null} if this field is not set.
     * 
     * @return the value of the response header field {@code content-encoding}.
     */
    public String getContentEncoding() {
        return getHeaderField("Content-Encoding"); //$NON-NLS-1$
    }

    /**
     * Returns the specific ContentHandler that will handle the type {@code
     * contentType}.
     * 
     * @param type
     *            The type that needs to be handled
     * @return An instance of the Content Handler
     */
    private ContentHandler getContentHandler(String type) throws IOException {
        // Replace all non-alphanumeric character by '_'
        final String typeString = parseTypeString(type.replace('/', '.'));

        // if there's a cached content handler, use it
        Object cHandler = contentHandlers.get(type);
        if (cHandler != null) {
            return (ContentHandler) cHandler;
        }

        if (contentHandlerFactory != null) {
            cHandler = contentHandlerFactory.createContentHandler(type);
            contentHandlers.put(type, cHandler);
            return (ContentHandler) cHandler;
        }

        // search through the package list for the right class for the Content
        // Type
        String packageList = AccessController
                .doPrivileged(new PriviAction<String>(
                        "java.content.handler.pkgs")); //$NON-NLS-1$
        if (packageList != null) {
            final StringTokenizer st = new StringTokenizer(packageList, "|"); //$NON-NLS-1$
            while (st.countTokens() > 0) {
                try {
                    Class<?> cl = Class.forName(st.nextToken() + "." //$NON-NLS-1$
                            + typeString, true, ClassLoader
                            .getSystemClassLoader());
                    cHandler = cl.newInstance();
                } catch (ClassNotFoundException e) {
                } catch (IllegalAccessException e) {
                } catch (InstantiationException e) {
                }
            }
        }

        if (cHandler == null) {
            cHandler = AccessController
                    .doPrivileged(new PrivilegedAction<Object>() {
                        public Object run() {
                            try {
                                // Try looking up AWT image content handlers
                                String className = "org.apache.harmony.awt.www.content." //$NON-NLS-1$
                                        + typeString;
                                return Class.forName(className).newInstance();
                            } catch (ClassNotFoundException e) {
                            } catch (IllegalAccessException e) {
                            } catch (InstantiationException e) {
                            }
                            return null;
                        }
                    });
        }
        if (cHandler != null) {
            if (!(cHandler instanceof ContentHandler)) {
                throw new UnknownServiceException();
            }
            contentHandlers.put(type, cHandler); // if we got the handler,
            // cache it for next time
            return (ContentHandler) cHandler;
        }

        return defaultHandler;
    }

    /**
     * Gets the content length in bytes specified by the response header field
     * {@code content-length} or {@code -1} if this field is not set.
     * 
     * @return the value of the response header field {@code content-length}.
     */
    public int getContentLength() {
        return getHeaderFieldInt("Content-Length", -1); //$NON-NLS-1$
    }

    /**
     * Gets the MIME-type of the content specified by the response header field
     * {@code content-type} or {@code null} if type is unknown.
     * 
     * @return the value of the response header field {@code content-type}.
     */
    public String getContentType() {
        return getHeaderField("Content-Type"); //$NON-NLS-1$
    }

    /**
     * Gets the timestamp when this response has been sent as a date in
     * milliseconds since January 1, 1970 GMT or {@code 0} if this timestamp is
     * unknown.
     * 
     * @return the sending timestamp of the current response.
     */
    public long getDate() {
        return getHeaderFieldDate("Date", 0); //$NON-NLS-1$
    }

    /**
     * Gets the default setting whether this connection allows user interaction.
     * 
     * @return the value of the default setting {@code
     *         defaultAllowUserInteraction}.
     * @see #allowUserInteraction
     */
    public static boolean getDefaultAllowUserInteraction() {
        return defaultAllowUserInteraction;
    }

    /**
     * Gets the default value for the specified request {@code field} or {@code
     * null} if the field could not be found. The current implementation of this
     * method returns always {@code null}.
     * 
     * @param field
     *            the request field whose default value shall be returned.
     * @return the default value for the given field.
     * @deprecated Use {@link #getRequestProperty}
     */
    @Deprecated
    public static String getDefaultRequestProperty(String field) {
        return null;
    }

    /**
     * Gets the default setting whether this connection allows using caches.
     * 
     * @return the value of the default setting {@code defaultUseCaches}.
     * @see #useCaches
     */
    public boolean getDefaultUseCaches() {
        return defaultUseCaches;
    }

    /**
     * Gets the value of the option {@code doInput} which specifies whether this
     * connection allows to receive data.
     * 
     * @return {@code true} if this connection allows input, {@code false}
     *         otherwise.
     * @see #doInput
     */
    public boolean getDoInput() {
        return doInput;
    }

    /**
     * Gets the value of the option {@code doOutput} which specifies whether
     * this connection allows to send data.
     * 
     * @return {@code true} if this connection allows output, {@code false}
     *         otherwise.
     * @see #doOutput
     */
    public boolean getDoOutput() {
        return doOutput;
    }

    /**
     * Gets the timestamp when this response will be expired in milliseconds
     * since January 1, 1970 GMT or {@code 0} if this timestamp is unknown.
     * 
     * @return the value of the response header field {@code expires}.
     */
    public long getExpiration() {
        return getHeaderFieldDate("Expires", 0); //$NON-NLS-1$
    }

    /**
     * Gets the table which is used by all {@code URLConnection} instances to
     * determine the MIME-type according to a file extension.
     * 
     * @return the file name map to determine the MIME-type.
     */
    public static FileNameMap getFileNameMap() {
        // Must use lazy initialization or there is a bootstrap problem
        // trying to load the MimeTable resource from a .jar before
        // JarURLConnection has finished initialization.
        synchronized (URLConnection.class) {
            if (fileNameMap == null) {
                fileNameMap = new MimeTable();
            }
            return fileNameMap;
        }
    }

    /**
     * Gets the header value at the field position {@code pos} or {@code null}
     * if the header has fewer than {@code pos} fields. The current
     * implementation of this method returns always {@code null}.
     *
     * @param pos
     *            the field position of the response header.
     * @return the value of the field at position {@code pos}.
     */
    public String getHeaderField(int pos) {
        return null;
    }

    /**
     * Gets an unchangeable map of the response-header fields and values. The
     * response-header field names are the key values of the map. The map values
     * are lists of header field values associated with a particular key name.
     * 
     * @return the response-header representing generic map.
     * @since 1.4
     */
    public Map<String, List<String>> getHeaderFields() {
        return Collections.emptyMap();
    }

    /**
     * Gets an unchangeable map of general request properties used by this
     * connection. The request property names are the key values of the map. The
     * map values are lists of property values of the corresponding key name.
     * 
     * @return the request-property representing generic map.
     * @since 1.4
     */
    public Map<String, List<String>> getRequestProperties() {
        if (connected) {
            throw new IllegalStateException(Messages.getString("luni.5E")); //$NON-NLS-1$
        }

        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        for (String key : requestProperties.keySet()) {
            map.put(key, Collections.unmodifiableList(requestProperties.get(key)));
        }
        return Collections.unmodifiableMap(map);
    }

    /**
     * Adds the given property to the request header. Existing properties with
     * the same name will not be overwritten by this method.
     * 
     * @param field
     *            the request property field name to add.
     * @param newValue
     *            the value of the property which is to add.
     * @throws IllegalStateException
     *             if the connection has been already established.
     * @throws NullPointerException
     *             if the property name is {@code null}.
     * @since 1.4
     */
    public void addRequestProperty(String field, String newValue) {
        if (connected) {
            throw new IllegalStateException(Messages.getString("luni.5E")); //$NON-NLS-1$
        }
        if (field == null) {
            throw new NullPointerException(Messages.getString("luni.95")); //$NON-NLS-1$
        }

        List<String> valuesList = requestProperties.get(field);
        if (valuesList == null) {
            valuesList = new ArrayList<String>();
            valuesList.add(0, newValue);
            requestProperties.put(field, valuesList);
        } else {
            valuesList.add(0, newValue);
        }
    }

    /**
     * Gets the value of the header field specified by {@code key} or {@code
     * null} if there is no field with this name. The current implementation of
     * this method returns always {@code null}.
     * 
     * @param key
     *            the name of the header field.
     * @return the value of the header field.
     */
    public String getHeaderField(String key) {
        return null;
    }

    /**
     * Gets the specified header value as a date in milliseconds since January
     * 1, 1970 GMT. Returns the {@code defaultValue} if no such header field
     * could be found.
     * 
     * @param field
     *            the header field name whose value is needed.
     * @param defaultValue
     *            the default value if no field has been found.
     * @return the value of the specified header field as a date in
     *         milliseconds.
     */
    @SuppressWarnings("deprecation")
    public long getHeaderFieldDate(String field, long defaultValue) {
        String date = getHeaderField(field);
        if (date == null) {
            return defaultValue;
        }
        try {
            return Date.parse(date);
        } catch (Exception e) {
            return defaultValue;
        }
    }

    /**
     * Gets the specified header value as a number. Returns the {@code
     * defaultValue} if no such header field could be found or the value could
     * not be parsed as an {@code Integer}.
     * 
     * @param field
     *            the header field name whose value is needed.
     * @param defaultValue
     *            the default value if no field has been found.
     * @return the value of the specified header field as a number.
     */
    public int getHeaderFieldInt(String field, int defaultValue) {
        try {
            return Integer.parseInt(getHeaderField(field));
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Gets the name of the header field at the given position {@code posn} or
     * {@code null} if there are fewer than {@code posn} fields. The current
     * implementation of this method returns always {@code null}.
     * 
     * @param posn
     *            the position of the header field which has to be returned.
     * @return the header field name at the given position.
     */
    public String getHeaderFieldKey(int posn) {
        return null;
    }

    /**
     * Gets the point of time since when the data must be modified to be
     * transmitted. Some protocols transmit data only if it has been modified
     * more recently than a particular time.
     * 
     * @return the time in milliseconds since January 1, 1970 GMT.
     * @see #ifModifiedSince
     */
    public long getIfModifiedSince() {
        return ifModifiedSince;
    }

    /**
     * Gets an {@code InputStream} for reading data from the resource pointed by
     * this {@code URLConnection}. It throws an UnknownServiceException by
     * default. This method must be overridden by its subclasses.
     * 
     * @return the InputStream to read data from.
     * @throws IOException
     *             if no InputStream could be created.
     */
    public InputStream getInputStream() throws IOException {
        throw new UnknownServiceException(Messages.getString("luni.96")); //$NON-NLS-1$
    }

    /**
     * Gets the value of the response header field {@code last-modified} or
     * {@code 0} if this value is not set.
     * 
     * @return the value of the {@code last-modified} header field.
     */
    public long getLastModified() {
        if (lastModified != -1) {
            return lastModified;
        }
        return lastModified = getHeaderFieldDate("Last-Modified", 0); //$NON-NLS-1$
    }

    /**
     * Gets an {@code OutputStream} for writing data to this {@code
     * URLConnection}. It throws an {@code UnknownServiceException} by default.
     * This method must be overridden by its subclasses.
     * 
     * @return the OutputStream to write data.
     * @throws IOException
     *             if no OutputStream could be created.
     */
    public OutputStream getOutputStream() throws IOException {
        throw new UnknownServiceException(Messages.getString("luni.97")); //$NON-NLS-1$
    }

    /**
     * Gets a {@code Permission} object representing all needed permissions to
     * open this connection. The returned permission object depends on the state
     * of the connection and will be {@code null} if no permissions are
     * necessary. By default, this method returns {@code AllPermission}.
     * Subclasses should overwrite this method to return an appropriate
     * permission object.
     * 
     * @return the permission object representing the needed permissions to open
     *         this connection.
     * @throws IOException
     *             if an I/O error occurs while creating the permission object.
     */
    public java.security.Permission getPermission() throws IOException {
        return new java.security.AllPermission();
    }

    /**
     * Gets the value of the request header property specified by {code field}
     * or {@code null} if there is no field with this name.
     * 
     * @param field
     *            the name of the request header property.
     * @return the value of the property.
     * @throws IllegalStateException
     *             if the connection has been already established.
     */
    public String getRequestProperty(String field) {
        if (connected) {
            throw new IllegalStateException(Messages.getString("luni.5E")); //$NON-NLS-1$
        }
        List<String> valuesList = requestProperties.get(field);
        if (valuesList == null) {
            return null;
        }
        return valuesList.get(0);
    }

    /**
     * Gets the URL represented by this {@code URLConnection}.
     * 
     * @return the URL of this connection.
     */
    public URL getURL() {
        return url;
    }

    /**
     * Gets the value of the flag which specifies whether this {@code
     * URLConnection} allows to use caches.
     * 
     * @return {@code true} if using caches is allowed, {@code false} otherwise.
     */
    public boolean getUseCaches() {
        return useCaches;
    }

    /**
     * Determines the MIME-type of the given resource {@code url} by resolving
     * the filename extension with the internal FileNameMap. Any fragment
     * identifier is removed before processing.
     * 
     * @param url
     *            the URL with the filename to get the MIME type.
     * @return the guessed content type or {@code null} if the type could not be
     *         determined.
     */
    public static String guessContentTypeFromName(String url) {
        return getFileNameMap().getContentTypeFor(url);
    }

    /**
     * Determines the MIME-type of the resource represented by the input stream
     * {@code is} by reading its first few characters.
     * 
     * @param is
     *            the resource representing input stream to determine the
     *            content type.
     * @return the guessed content type or {@code null} if the type could not be
     *         determined.
     * @throws IOException
     *             if an I/O error occurs while reading from the input stream.
     */
    @SuppressWarnings("nls")
    public static String guessContentTypeFromStream(InputStream is)
            throws IOException {

        if (!is.markSupported()) {
            return null;
        }
        // Look ahead up to 64 bytes for the longest encoded header
        is.mark(64);
        byte[] bytes = new byte[64];
        int length = is.read(bytes);
        is.reset();

        // If there is no data from the input stream, can not determine
        // content-type
        if (length == -1) {
            return null;
        }

        // Check for Unicode BOM encoding indicators
        String encoding = "ASCII";
        int start = 0;
        if (length > 1) {
            if ((bytes[0] == (byte) 0xFF) && (bytes[1] == (byte) 0xFE)) {
                encoding = "UTF-16LE";
                start = 2;
                length -= length & 1;
            }
            if ((bytes[0] == (byte) 0xFE) && (bytes[1] == (byte) 0xFF)) {
                encoding = "UTF-16BE";
                start = 2;
                length -= length & 1;
            }
            if (length > 2) {
                if ((bytes[0] == (byte) 0xEF) && (bytes[1] == (byte) 0xBB)
                        && (bytes[2] == (byte) 0xBF)) {
                    encoding = "UTF-8";
                    start = 3;
                }
                if (length > 3) {
                    if ((bytes[0] == (byte) 0x00) && (bytes[1] == (byte) 0x00)
                            && (bytes[2] == (byte) 0xFE)
                            && (bytes[3] == (byte) 0xFF)) {
                        encoding = "UTF-32BE";
                        start = 4;
                        length -= length & 3;
                    }
                    if ((bytes[0] == (byte) 0xFF) && (bytes[1] == (byte) 0xFE)
                            && (bytes[2] == (byte) 0x00)
                            && (bytes[3] == (byte) 0x00)) {
                        encoding = "UTF-32LE";
                        start = 4;
                        length -= length & 3;
                    }
                }
            }
        }

        String header = new String(bytes, start, length - start, encoding);

        // Check binary types
        if (header.startsWith("PK")) {
            return "application/zip";
        }
        if (header.startsWith("GI")) {
            return "image/gif";
        }

        // Check text types
        String textHeader = header.trim().toUpperCase();
        if (textHeader.startsWith("<!DOCTYPE HTML") ||
                textHeader.startsWith("<HTML") ||
                textHeader.startsWith("<HEAD") ||
                textHeader.startsWith("<BODY") ||
                textHeader.startsWith("<HEAD")) {
            return "text/html";
        }

        if (textHeader.startsWith("<?XML")) {
            return "application/xml";
        }

        // Give up
        return null;
    }

    /**
     * Performs any necessary string parsing on the input string such as
     * converting non-alphanumeric character into underscore.
     * 
     * @param typeString
     *            the parsed string
     * @return the string to be parsed
     */
    private String parseTypeString(String typeString) {
        StringBuilder typeStringBuffer = new StringBuilder(typeString);
        for (int i = 0; i < typeStringBuffer.length(); i++) {
            // if non-alphanumeric, replace it with '_'
            char c = typeStringBuffer.charAt(i);
            if (!(Character.isLetter(c) || Character.isDigit(c) || c == '.')) {
                typeStringBuffer.setCharAt(i, '_');
            }
        }
        return typeStringBuffer.toString();
    }

    /**
     * Sets the flag indicating whether this connection allows user interaction
     * or not. This method can only be called prior to the connection
     * establishment.
     * 
     * @param newValue
     *            the value of the flag to be set.
     * @throws IllegalStateException
     *             if this method attempts to change the flag after the
     *             connection has been established.
     * @see #allowUserInteraction
     */
    public void setAllowUserInteraction(boolean newValue) {
        if (connected) {
            throw new IllegalStateException(Messages.getString("luni.5E")); //$NON-NLS-1$
        }
        this.allowUserInteraction = newValue;
    }

    /**
     * Sets the internally used content handler factory. The content factory can
     * only be set if it is allowed by the security manager and only once during
     * the lifetime of the application.
     * 
     * @param contentFactory
     *            the content factory to be set.
     * @throws Error
     *             if the security manager does not allow to set the content
     *             factory or it has been already set earlier ago.
     */
    public static synchronized void setContentHandlerFactory(
            ContentHandlerFactory contentFactory) {
        if (contentHandlerFactory != null) {
            throw new Error(Messages.getString("luni.98")); //$NON-NLS-1$
        }
        SecurityManager sManager = System.getSecurityManager();
        if (sManager != null) {
            sManager.checkSetFactory();
        }
        contentHandlerFactory = contentFactory;
    }

    /**
     * Sets the default value for the flag indicating whether this connection
     * allows user interaction or not. Existing {@code URLConnection}s are
     * unaffected.
     * 
     * @param allows
     *            the default value of the flag to be used for new connections.
     * @see #defaultAllowUserInteraction
     * @see #allowUserInteraction
     */
    public static void setDefaultAllowUserInteraction(boolean allows) {
        defaultAllowUserInteraction = allows;
    }

    /**
     * Sets the default value of the specified request header field. This value
     * will be used for the specific field of every newly created connection.
     * The current implementation of this method does nothing.
     * 
     * @param field
     *            the request header field to be set.
     * @param value
     *            the default value to be used.
     * @deprecated Use {@link #setRequestProperty} of an existing {@code
     *             URLConnection} instance.
     */
    @Deprecated
    public static void setDefaultRequestProperty(String field, String value) {
    }

    /**
     * Sets the default value for the flag indicating whether this connection
     * allows to use caches. Existing {@code URLConnection}s are unaffected.
     * 
     * @param newValue
     *            the default value of the flag to be used for new connections.
     * @see #defaultUseCaches
     * @see #useCaches
     */
    public void setDefaultUseCaches(boolean newValue) {
        if (connected) {
            throw new IllegalAccessError(Messages.getString("luni.5E")); //$NON-NLS-1$
        }
        defaultUseCaches = newValue;
    }

    /**
     * Sets the flag indicating whether this {@code URLConnection} allows input.
     * It cannot be set after the connection is established.
     * 
     * @param newValue
     *            the new value for the flag to be set.
     * @throws IllegalAccessError
     *             if this method attempts to change the value after the
     *             connection has been already established.
     * @see #doInput
     */
    public void setDoInput(boolean newValue) {
        if (connected) {
            throw new IllegalStateException(Messages.getString("luni.5E")); //$NON-NLS-1$
        }
        this.doInput = newValue;
    }

    /**
     * Sets the flag indicating whether this {@code URLConnection} allows
     * output. It cannot be set after the connection is established.
     * 
     * @param newValue
     *            the new value for the flag to be set.
     * @throws IllegalAccessError
     *             if this method attempts to change the value after the
     *             connection has been already established.
     * @see #doOutput
     */
    public void setDoOutput(boolean newValue) {
        if (connected) {
            throw new IllegalStateException(Messages.getString("luni.5E")); //$NON-NLS-1$
        }
        this.doOutput = newValue;
    }

    /**
     * Sets the internal map which is used by all {@code URLConnection}
     * instances to determine the MIME-type according to a filename extension.
     * 
     * @param map
     *            the MIME table to be set.
     */
    public static void setFileNameMap(FileNameMap map) {
        SecurityManager manager = System.getSecurityManager();
        if (manager != null) {
            manager.checkSetFactory();
        }
        synchronized (URLConnection.class) {
            fileNameMap = map;
        }
    }

    /**
     * Sets the point of time since when the data must be modified to be
     * transmitted. Some protocols transmit data only if it has been modified
     * more recently than a particular time. The data will be transmitted
     * regardless of its timestamp if this option is set to {@code 0}.
     * 
     * @param newValue
     *            the time in milliseconds since January 1, 1970 GMT.
     * @throws IllegalStateException
     *             if this {@code URLConnection} has already been connected.
     * @see #ifModifiedSince
     */
    public void setIfModifiedSince(long newValue) {
        if (connected) {
            throw new IllegalStateException(Messages.getString("luni.5E")); //$NON-NLS-1$
        }
        this.ifModifiedSince = newValue;
    }

    /**
     * Sets the value of the specified request header field. The value will only
     * be used by the current {@code URLConnection} instance. This method can
     * only be called before the connection is established.
     * 
     * @param field
     *            the request header field to be set.
     * @param newValue
     *            the new value of the specified property.
     * @throws IllegalStateException
     *             if the connection has been already established.
     * @throws NullPointerException
     *             if the parameter {@code field} is {@code null}.
     */
    public void setRequestProperty(String field, String newValue) {
        if (connected) {
            throw new IllegalStateException(Messages.getString("luni.5E")); //$NON-NLS-1$
        }
        if (field == null) {
            throw new NullPointerException(Messages.getString("luni.95")); //$NON-NLS-1$
        }

        List<String> valuesList = new ArrayList<String>();
        valuesList.add(newValue);
        requestProperties.put(field, valuesList);
    }

    /**
     * Sets the flag indicating whether this connection allows to use caches or
     * not. This method can only be called prior to the connection
     * establishment.
     * 
     * @param newValue
     *            the value of the flag to be set.
     * @throws IllegalStateException
     *             if this method attempts to change the flag after the
     *             connection has been established.
     * @see #useCaches
     */
    public void setUseCaches(boolean newValue) {
        if (connected) {
            throw new IllegalStateException(Messages.getString("luni.5E")); //$NON-NLS-1$
        }
        this.useCaches = newValue;
    }

    /**
     * Sets the timeout value in milliseconds for establishing the connection to
     * the resource pointed by this {@code URLConnection} instance. A {@code
     * SocketTimeoutException} is thrown if the connection could not be
     * established in this time. Default is {@code 0} which stands for an
     * infinite timeout.
     * 
     * @param timeout
     *            the connecting timeout in milliseconds.
     * @throws IllegalArgumentException
     *             if the parameter {@code timeout} is less than zero.
     */
    public void setConnectTimeout(int timeout) {
        if (0 > timeout) {
            throw new IllegalArgumentException(Messages.getString("luni.5B")); //$NON-NLS-1$
        }
        this.connectTimeout = timeout;
    }

    /**
     * Gets the configured connecting timeout.
     * 
     * @return the connecting timeout value in milliseconds.
     */
    public int getConnectTimeout() {
        return connectTimeout;
    }

    /**
     * Sets the timeout value in milliseconds for reading from the input stream
     * of an established connection to the resource. A {@code
     * SocketTimeoutException} is thrown if the connection could not be
     * established in this time. Default is {@code 0} which stands for an
     * infinite timeout.
     * 
     * @param timeout
     *            the reading timeout in milliseconds.
     * @throws IllegalArgumentException
     *             if the parameter {@code timeout} is less than zero.
     */
    public void setReadTimeout(int timeout) {
        if (0 > timeout) {
            throw new IllegalArgumentException(Messages.getString("luni.5B")); //$NON-NLS-1$
        }
        this.readTimeout = timeout;
    }

    /**
     * Gets the configured timeout for reading from the input stream of an
     * established connection to the resource.
     * 
     * @return the reading timeout value in milliseconds.
     */
    public int getReadTimeout() {
        return readTimeout;
    }

    /**
     * Returns the string representation containing the name of this class and
     * the URL.
     * 
     * @return the string representation of this {@code URLConnection} instance.
     */
    @Override
    public String toString() {
        return getClass().getName() + ":" + url.toString(); //$NON-NLS-1$
    }

    static class DefaultContentHandler extends java.net.ContentHandler {

        /**
         * @param u
         *            the URL connection
         * 
         * @see java.net.ContentHandler#getContent(java.net.URLConnection)
         */
        @Override
        public Object getContent(URLConnection u) throws IOException {
            return u.getInputStream();
        }
    }
}
