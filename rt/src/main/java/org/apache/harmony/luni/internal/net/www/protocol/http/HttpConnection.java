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
package org.apache.harmony.luni.internal.net.www.protocol.http;

import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.Socket;
import java.net.SocketException;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.apache.harmony.luni.internal.nls.Messages;

/**
 * An <code>HttpConnection</code> represents a persistent http or https connection and contains
 * various utility methods to access that connection.
 */
public class HttpConnection {

    private boolean usingSecureSocket = false;

    private Socket socket;
    private SSLSocket sslSocket;

    private InputStream inputStream;
    private OutputStream outputStream;
    private InputStream sslInputStream;
    private OutputStream sslOutputStream;

    private HttpConfiguration config;

    public HttpConnection(HttpConfiguration config, int connectTimeout) throws IOException {
        this.config = config;
        String hostName = config.getHostName();
        int hostPort = config.getHostPort();
        Proxy proxy = config.getProxy();
        if(proxy == null || proxy.type() == Proxy.Type.HTTP) {
            socket = new Socket();
        } else {
            socket = new Socket(proxy);
        }
        socket.connect(new InetSocketAddress(hostName, hostPort), connectTimeout);
    }

    public void closeSocketAndStreams() {
        if(usingSecureSocket) {
            if (null != sslOutputStream) {
                OutputStream temp = sslOutputStream;
                sslOutputStream = null;
                try {
                    temp.close();
                } catch (Exception ex) {
                    // ignored
                }
            }

            if (null != sslInputStream) {
                InputStream temp = sslInputStream;
                sslInputStream = null;
                try {
                    temp.close();
                } catch (Exception ex) {
                    // ignored
                }
            }

            if (null != sslSocket) {
                Socket temp = sslSocket;
                sslSocket = null;
                try {
                    temp.close();
                } catch (Exception ex) {
                    // ignored
                }
            }
        }
        if (null != outputStream) {
            OutputStream temp = outputStream;
            outputStream = null;
            try {
                temp.close();
            } catch (Exception ex) {
                // ignored
            }
        }

        if (null != inputStream) {
            InputStream temp = inputStream;
            inputStream = null;
            try {
                temp.close();
            } catch (Exception ex) {
                // ignored
            }
        }

        if (null != socket) {
            Socket temp = socket;
            socket = null;
            try {
                temp.close();
            } catch (Exception ex) {
                // ignored
            }
        }
    }

    public void setSoTimeout(int readTimeout) throws SocketException {
        socket.setSoTimeout(readTimeout);
    }

    public OutputStream getOutputStream() throws IOException {
        if(usingSecureSocket) {
            if (sslOutputStream == null) {
                sslOutputStream = sslSocket.getOutputStream();
            }
            return sslOutputStream;
        } else if(outputStream == null) {
            outputStream = socket.getOutputStream();
        }
        return outputStream;
    }

    public InputStream getInputStream() throws IOException {
        if(usingSecureSocket) {
            if (sslInputStream == null) {
                sslInputStream = sslSocket.getInputStream();
            }
            return sslInputStream;
        } else if(inputStream == null) {
            inputStream = socket.getInputStream();
        }
        return inputStream;
    }

    public HttpConfiguration getHttpConfiguration() {
        return config;
    }
    
    public SSLSocket getSecureSocket(SSLSocketFactory sslSocketFactory, HostnameVerifier hostnameVerifier) throws IOException {
        if(!usingSecureSocket) {
            String hostName = config.getHostName();
            int port = config.getHostPort();
            // create the wrapper over connected socket
            sslSocket = (SSLSocket) sslSocketFactory.createSocket(socket,
                    hostName, port, true);
            sslSocket.setUseClientMode(true);
            sslSocket.startHandshake();
            if (!hostnameVerifier.verify(hostName, sslSocket.getSession())) {
                throw new IOException(Messages.getString("luni.02", hostName)); //$NON-NLS-1$
            }
            usingSecureSocket = true;
        }
        return sslSocket;
    }

    Socket getSocket() {
        return socket;
    }
    
    /*
     * This method has been copied from the Apache Jakarta Commons HttpClient project
     * http://svn.apache.org/repos/asf/jakarta/commons/proper/httpclient/trunk/HttpClient/src/java/org/apache/commons/httpclient/HttpConnection.java r480424
     */
    protected boolean isStale() throws IOException {
        boolean isStale = true;
        if (!socket.isClosed()) {
            // the socket is open, but could still have been closed from the other end
            isStale = false;
            try {
                if (inputStream.available() <= 0) {
                    int soTimeout = socket.getSoTimeout();
                    try {
                        socket.setSoTimeout(1);
                        inputStream.mark(1);
                        int byteRead = inputStream.read();
                        if (byteRead == -1) {
                            // again - if the socket is reporting all data read,
                            // probably stale
                            isStale = true;
                        } else {
                            inputStream.reset();
                        }
                    } finally {
                        socket.setSoTimeout(soTimeout);
                    }
                }
            } catch (InterruptedIOException e) {
                if (!isSocketTimeoutException(e)) {
                    throw e;
                }
                // aha - the connection is NOT stale - continue on!
            } catch (IOException e) {
                // oops - the connection is stale, the read or soTimeout failed.
                isStale = true;
            }
        }

        return isStale;
    }
   
    /*
     * This field has been copied from the Apache Jakarta Commons HttpClient project
     * http://svn.apache.org/repos/asf/jakarta/commons/proper/httpclient/trunk/HttpClient/src/java/org/apache/commons/httpclient/HttpConnection.java r480424
     */
    static private final Class SOCKET_TIMEOUT_CLASS = SocketTimeoutExceptionClass();
    
    /*
     * This method has been copied from the Apache Jakarta Commons HttpClient project
     * http://svn.apache.org/repos/asf/jakarta/commons/proper/httpclient/trunk/HttpClient/src/java/org/apache/commons/httpclient/HttpConnection.java r480424
     */
    public static boolean isSocketTimeoutException(final InterruptedIOException e) {
        if (SOCKET_TIMEOUT_CLASS != null) {
            return SOCKET_TIMEOUT_CLASS.isInstance(e);
        } else {
            return true;
        }
    }

    /*
     * This method has been copied from the Apache Jakarta Commons HttpClient project
     * http://svn.apache.org/repos/asf/jakarta/commons/proper/httpclient/trunk/HttpClient/src/java/org/apache/commons/httpclient/HttpConnection.java r480424
     */
    static private Class SocketTimeoutExceptionClass() {
        try {
            return Class.forName("java.net.SocketTimeoutException");
        } catch (ClassNotFoundException e) {
            return null;
        }
    }

}