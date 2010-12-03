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

import org.apache.harmony.luni.internal.nls.Messages;

/**
 * This abstract subclass of {@code URLConnection} defines methods for managing
 * HTTP connection according to the description given by RFC 2068.
 * 
 * @see ContentHandler
 * @see URL
 * @see URLConnection
 * @see URLStreamHandler
 */
public abstract class HttpURLConnection extends URLConnection {
    @SuppressWarnings("nls")
    private String methodTokens[] = { "GET", "DELETE", "HEAD", "OPTIONS",
            "POST", "PUT", "TRACE" };

   /**
     * The HTTP request method of this {@code HttpURLConnection}. The default
     * value is {@code "GET"}.
     */
    protected String method = "GET"; //$NON-NLS-1$

    /**
     * The status code of the response obtained from the HTTP request. The
     * default value is {@code -1}.
     * <p>
     * <li>1xx: Informational</li>
     * <li>2xx: Success</li>
     * <li>3xx: Relocation/Redirection</li>
     * <li>4xx: Client Error</li>
     * <li>5xx: Server Error</li>
     */
    protected int responseCode = -1;

    /**
     * The HTTP response message which corresponds to the response code.
     */
    protected String responseMessage;

    /**
     * Flag to define whether the protocol will automatically follow redirects
     * or not. The default value is {@code true}.
     */
    protected boolean instanceFollowRedirects = followRedirects;

    private static boolean followRedirects = true;

    /**
     * If the HTTP chunked encoding is enabled this parameter defines the
     * chunk-length. Default value is {@code -1} that means the chunked encoding
     * mode is disabled.
     */
    protected int chunkLength = -1;

    /**
     * If using HTTP fixed-length streaming mode this parameter defines the
     * fixed length of content. Default value is {@code -1} that means the
     * fixed-length streaming mode is disabled.
     */
    protected int fixedContentLength = -1;

    private final static int DEFAULT_CHUNK_LENGTH = 1024;

    // 2XX: generally "OK"
    // 3XX: relocation/redirect
    // 4XX: client error
    // 5XX: server error
    /**
     * Numeric status code, 202: Accepted
     */
    public final static int HTTP_ACCEPTED = 202;

    /**
     * Numeric status code, 502: Bad Gateway
     */
    public final static int HTTP_BAD_GATEWAY = 502;

    /**
     * Numeric status code, 405: Bad Method
     */
    public final static int HTTP_BAD_METHOD = 405;

    /**
     * Numeric status code, 400: Bad Request
     */
    public final static int HTTP_BAD_REQUEST = 400;

    /**
     * Numeric status code, 408: Client Timeout
     */
    public final static int HTTP_CLIENT_TIMEOUT = 408;

    /**
     * Numeric status code, 409: Conflict
     */
    public final static int HTTP_CONFLICT = 409;

    /**
     * Numeric status code, 201: Created
     */
    public final static int HTTP_CREATED = 201;

    /**
     * Numeric status code, 413: Entity too large
     */
    public final static int HTTP_ENTITY_TOO_LARGE = 413;

    /**
     * Numeric status code, 403: Forbidden
     */
    public final static int HTTP_FORBIDDEN = 403;

    /**
     * Numeric status code, 504: Gateway timeout
     */
    public final static int HTTP_GATEWAY_TIMEOUT = 504;

    /**
     * Numeric status code, 410: Gone
     */
    public final static int HTTP_GONE = 410;

    /**
     * Numeric status code, 500: Internal error
     */
    public final static int HTTP_INTERNAL_ERROR = 500;

    /**
     * Numeric status code, 411: Length required
     */
    public final static int HTTP_LENGTH_REQUIRED = 411;

    /**
     * Numeric status code, 301 Moved permanently
     */
    public final static int HTTP_MOVED_PERM = 301;

    /**
     * Numeric status code, 302: Moved temporarily
     */
    public final static int HTTP_MOVED_TEMP = 302;

    /**
     * Numeric status code, 300: Multiple choices
     */
    public final static int HTTP_MULT_CHOICE = 300;

    /**
     * Numeric status code, 204: No content
     */
    public final static int HTTP_NO_CONTENT = 204;

    /**
     * Numeric status code, 406: Not acceptable
     */
    public final static int HTTP_NOT_ACCEPTABLE = 406;

    /**
     * Numeric status code, 203: Not authoritative
     */
    public final static int HTTP_NOT_AUTHORITATIVE = 203;

    /**
     * Numeric status code, 404: Not found
     */
    public final static int HTTP_NOT_FOUND = 404;

    /**
     * Numeric status code, 501: Not implemented
     */
    public final static int HTTP_NOT_IMPLEMENTED = 501;

    /**
     * Numeric status code, 304: Not modified
     */
    public final static int HTTP_NOT_MODIFIED = 304;

    /**
     * Numeric status code, 200: OK
     */
    public final static int HTTP_OK = 200;

    /**
     * Numeric status code, 206: Partial
     */
    public final static int HTTP_PARTIAL = 206;

    /**
     * Numeric status code, 402: Payment required
     */
    public final static int HTTP_PAYMENT_REQUIRED = 402;

    /**
     * Numeric status code, 412: Precondition failed
     */
    public final static int HTTP_PRECON_FAILED = 412;

    /**
     * Numeric status code, 407: Proxy authentication required
     */
    public final static int HTTP_PROXY_AUTH = 407;

    /**
     * Numeric status code, 414: Request too long
     */
    public final static int HTTP_REQ_TOO_LONG = 414;

    /**
     * Numeric status code, 205: Reset
     */
    public final static int HTTP_RESET = 205;

    /**
     * Numeric status code, 303: See other
     */
    public final static int HTTP_SEE_OTHER = 303;

    /**
     * Numeric status code, 500: Internal error
     *
     * @deprecated Use {@link #HTTP_INTERNAL_ERROR}
     */
    @Deprecated
    public final static int HTTP_SERVER_ERROR = 500;

    /**
     * Numeric status code, 305: Use proxy
     */
    public final static int HTTP_USE_PROXY = 305;

    /**
     * Numeric status code, 401: Unauthorized
     */
    public final static int HTTP_UNAUTHORIZED = 401;

    /**
     * Numeric status code, 415: Unsupported type
     */
    public final static int HTTP_UNSUPPORTED_TYPE = 415;

    /**
     * Numeric status code, 503: Unavailable
     */
    public final static int HTTP_UNAVAILABLE = 503;

    /**
     * Numeric status code, 505: Version not supported
     */
    public final static int HTTP_VERSION = 505;

    /**
     * Constructs a new {@code HttpURLConnection} instance pointing to the
     * resource specified by the {@code url}.
     * 
     * @param url
     *            the URL of this connection.
     * @see URL
     * @see URLConnection
     */
    protected HttpURLConnection(URL url) {
        super(url);
    }

    /**
     * Closes the connection to the HTTP server.
     * 
     * @see URLConnection#connect()
     * @see URLConnection#connected
     */
    public abstract void disconnect();

    /**
     * Returns an input stream from the server in the case of an error such as
     * the requested file has not been found on the remote server. This stream
     * can be used to read the data the server will send back.
     * 
     * @return the error input stream returned by the server.
     */
    public java.io.InputStream getErrorStream() {
        return null;
    }

    /**
     * Returns the value of {@code followRedirects} which indicates if this
     * connection follows a different URL redirected by the server. It is
     * enabled by default.
     * 
     * @return the value of the flag.
     * @see #setFollowRedirects
     */
    public static boolean getFollowRedirects() {
        return followRedirects;
    }

    /**
     * Returns the permission object (in this case {@code SocketPermission})
     * with the host and the port number as the target name and {@code
     * "resolve, connect"} as the action list. If the port number of this URL
     * instance is lower than {@code 0} the port will be set to {@code 80}.
     * 
     * @return the permission object required for this connection.
     * @throws IOException
     *             if an IO exception occurs during the creation of the
     *             permission object.
     */
    @Override
    public java.security.Permission getPermission() throws IOException {
        int port = url.getPort();
        if (port < 0) {
            port = 80;
        }
        return new SocketPermission(url.getHost() + ":" + port, //$NON-NLS-1$
                "connect, resolve"); //$NON-NLS-1$
    }

    /**
     * Returns the request method which will be used to make the request to the
     * remote HTTP server. All possible methods of this HTTP implementation is
     * listed in the class definition.
     * 
     * @return the request method string.
     * @see #method
     * @see #setRequestMethod
     */
    public String getRequestMethod() {
        return method;
    }

    /**
     * Returns the response code returned by the remote HTTP server.
     * 
     * @return the response code, -1 if no valid response code.
     * @throws IOException
     *             if there is an IO error during the retrieval.
     * @see #getResponseMessage
     */
    public int getResponseCode() throws IOException {
        // Call getInputStream() first since getHeaderField() doesn't return
        // exceptions
        getInputStream();
        String response = getHeaderField(0);
        if (response == null) {
            return -1;
        }
        response = response.trim();
        int mark = response.indexOf(" ") + 1; //$NON-NLS-1$
        if (mark == 0) {
            return -1;
        }
        int last = mark + 3;
        if (last > response.length()) {
            last = response.length();
        }
        responseCode = Integer.parseInt(response.substring(mark, last));
        if (last + 1 <= response.length()) {
            responseMessage = response.substring(last + 1);
        }
        return responseCode;
    }

    /**
     * Returns the response message returned by the remote HTTP server.
     * 
     * @return the response message. {@code null} if no such response exists.
     * @throws IOException
     *             if there is an error during the retrieval.
     * @see #getResponseCode()
     */
    public String getResponseMessage() throws IOException {
        if (responseMessage != null) {
            return responseMessage;
        }
        getResponseCode();
        return responseMessage;
    }

    /**
     * Sets the flag of whether this connection will follow redirects returned
     * by the remote server. This method can only be called with the permission
     * from the security manager.
     * 
     * @param auto
     *            the value to enable or disable this option.
     * @see SecurityManager#checkSetFactory()
     */
    public static void setFollowRedirects(boolean auto) {
        SecurityManager security = System.getSecurityManager();
        if (security != null) {
            security.checkSetFactory();
        }
        followRedirects = auto;
    }

    /**
     * Sets the request command which will be sent to the remote HTTP server.
     * This method can only be called before the connection is made.
     * 
     * @param method
     *            the string representing the method to be used.
     * @throws ProtocolException
     *             if this is called after connected, or the method is not
     *             supported by this HTTP implementation.
     * @see #getRequestMethod()
     * @see #method
     */
    public void setRequestMethod(String method) throws ProtocolException {
        if (connected) {
            throw new ProtocolException(Messages.getString("luni.5E")); //$NON-NLS-1$
        }
        for (int i = 0; i < methodTokens.length; i++) {
            if (methodTokens[i].equals(method)) {
                // if there is a supported method that matches the desired
                // method, then set the current method and return
                this.method = methodTokens[i];
                return;
            }
        }
        // if none matches, then throw ProtocolException
        throw new ProtocolException();
    }

    /**
     * Returns whether this connection uses a proxy server or not.
     * 
     * @return {@code true} if this connection passes a proxy server, false
     *         otherwise.
     */
    public abstract boolean usingProxy();

    /**
     * Returns whether this connection follows redirects.
     * 
     * @return {@code true} if this connection follows redirects, false
     *         otherwise.
     */
    public boolean getInstanceFollowRedirects() {
        return instanceFollowRedirects;
    }

    /**
     * Sets whether this connection follows redirects.
     * 
     * @param followRedirects
     *            {@code true} if this connection will follows redirects, false
     *            otherwise.
     */
    public void setInstanceFollowRedirects(boolean followRedirects) {
        instanceFollowRedirects = followRedirects;
    }

    /**
     * Returns the date value in milliseconds since {@code 01.01.1970, 00:00h}
     * corresponding to the header field {@code field}. The {@code defaultValue}
     * will be returned if no such field can be found in the response header.
     * 
     * @param field
     *            the header field name.
     * @param defaultValue
     *            the default value to use if the specified header field wont be
     *            found.
     * @return the header field represented in milliseconds since January 1,
     *         1970 GMT.
     */
    @Override
    public long getHeaderFieldDate(String field, long defaultValue) {
        return super.getHeaderFieldDate(field, defaultValue);
    }

    /**
     * If the length of a HTTP request body is known ahead, sets fixed length to
     * enable streaming without buffering. Sets after connection will cause an
     * exception.
     * 
     * @see #setChunkedStreamingMode
     * @param contentLength
     *            the fixed length of the HTTP request body.
     * @throws IllegalStateException
     *             if already connected or an other mode already set.
     * @throws IllegalArgumentException
     *             if {@code contentLength} is less than zero.
     */
    public void setFixedLengthStreamingMode(int contentLength) {
        if (super.connected) {
            throw new IllegalStateException(Messages.getString("luni.5F")); //$NON-NLS-1$
        }
        if (0 < chunkLength) {
            throw new IllegalStateException(Messages.getString("luni.60")); //$NON-NLS-1$
        }
        if (0 > contentLength) {
            throw new IllegalArgumentException(Messages.getString("luni.61")); //$NON-NLS-1$
        }
        this.fixedContentLength = contentLength;
    }

    /**
     * If the length of a HTTP request body is NOT known ahead, enable chunked
     * transfer encoding to enable streaming with buffering. Notice that not all
     * http servers support this mode. Sets after connection will cause an
     * exception.
     * 
     * @see #setFixedLengthStreamingMode
     * @param chunklen
     *            the length of a chunk.
     * @throws IllegalStateException
     *             if already connected or an other mode already set.
     */
    public void setChunkedStreamingMode(int chunklen) {
        if (super.connected) {
            throw new IllegalStateException(Messages.getString("luni.5F")); //$NON-NLS-1$
        }
        if (0 <= fixedContentLength) {
            throw new IllegalStateException(Messages.getString("luni.60")); //$NON-NLS-1$
        }
        if (0 >= chunklen) {
            chunkLength = DEFAULT_CHUNK_LENGTH;
        } else {
            chunkLength = chunklen;
        }
    }
}
