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

package org.apache.harmony.luni.internal.net.www.protocol.ftp;

import java.io.BufferedInputStream;
import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketPermission;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.security.Permission;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.harmony.luni.internal.net.www.MimeTable;
import org.apache.harmony.luni.net.NetUtil;
import org.apache.harmony.luni.internal.nls.Messages;

public class FtpURLConnection extends URLConnection {

    private static final int FTP_PORT = 21;

    // FTP Reply Constants
    private static final int FTP_DATAOPEN = 125;

    private static final int FTP_OPENDATA = 150;

    private static final int FTP_OK = 200;

    private static final int FTP_USERREADY = 220;

    private static final int FTP_TRANSFEROK = 226;

    // private static final int FTP_PASV = 227;

    private static final int FTP_LOGGEDIN = 230;

    private static final int FTP_FILEOK = 250;

    private static final int FTP_PASWD = 331;

    // private static final int FTP_DATAERROR = 451;

    // private static final int FTP_ERROR = 500;

    private static final int FTP_NOTFOUND = 550;

    private Socket controlSocket;

    private Socket dataSocket;

    private ServerSocket acceptSocket;

    private InputStream ctrlInput;

    private InputStream inputStream;

    private OutputStream ctrlOutput;

    private int dataPort;

    private String username = "anonymous"; //$NON-NLS-1$

    private String password = ""; //$NON-NLS-1$

    private String replyCode;

    private String hostName;

    private Proxy proxy;

    private Proxy currentProxy;

    private URI uri;

    /**
     * FtpURLConnection constructor comment.
     * 
     * @param url
     */
    protected FtpURLConnection(URL url) {
        super(url);
        hostName = url.getHost();
        String parse = url.getUserInfo();
        if (parse != null) {
            int split = parse.indexOf(':');
            if (split >= 0) {
                username = parse.substring(0, split);
                password = parse.substring(split + 1);
            } else {
                username = parse;
            }
        }
        uri = null;
        try {
            uri = url.toURI();
        } catch (URISyntaxException e) {
            // do nothing.
        }
    }

    /**
     * FtpURLConnection constructor.
     * 
     * @param url
     * @param proxy
     */
    protected FtpURLConnection(URL url, Proxy proxy) {
        this(url);
        this.proxy = proxy;
    }

    /**
     * Change the server directory to that specified in the URL
     */
    private void cd() throws IOException {
        int idx = url.getFile().lastIndexOf('/');

        if (idx > 0) {
            String dir = url.getFile().substring(0, idx);
            write("CWD " + dir + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
            int reply = getReply();
            if (reply != FTP_FILEOK && dir.length() > 0 && dir.charAt(0) == '/') {
                write("CWD " + dir.substring(1) + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
                reply = getReply();
            }
            if (reply != FTP_FILEOK) {
                throw new IOException(Messages.getString("luni.1C")); //$NON-NLS-1$
            }
        }
    }

    /**
     * Establishes the connection to the resource specified by this
     * <code>URL</code>
     * 
     * @see #connected
     * @see java.io.IOException
     * @see URLStreamHandler
     */
    @Override
    public void connect() throws IOException {
        // Use system-wide ProxySelect to select proxy list,
        // then try to connect via elements in the proxy list.
        List<Proxy> proxyList = null;
        if (null != proxy) {
            proxyList = new ArrayList<Proxy>(1);
            proxyList.add(proxy);
        } else {
            proxyList = NetUtil.getProxyList(uri);
        }
        if (null == proxyList) {
            currentProxy = null;
            connectInternal();
        } else {
            ProxySelector selector = ProxySelector.getDefault();
            Iterator<Proxy> iter = proxyList.iterator();
            boolean connectOK = false;
            String failureReason = ""; //$NON-NLS-1$
            while (iter.hasNext() && !connectOK) {
                currentProxy = iter.next();
                try {
                    connectInternal();
                    connectOK = true;
                } catch (IOException ioe) {
                    failureReason = ioe.getLocalizedMessage();
                    // If connect failed, callback "connectFailed"
                    // should be invoked.
                    if (null != selector && Proxy.NO_PROXY != currentProxy) {
                        selector.connectFailed(uri, currentProxy.address(), ioe);
                    }
                }
            }
            if (!connectOK) {
                // luni.1D=Unable to connect to server\: {0}
                throw new IOException(Messages.getString("luni.1D", failureReason)); //$NON-NLS-1$
            }
        }
    }

    private void connectInternal() throws IOException {
        int port = url.getPort();
        int connectTimeout = getConnectTimeout();
        if (port <= 0) {
            port = FTP_PORT;
        }
        if (null == currentProxy || Proxy.Type.HTTP == currentProxy.type()) {
            controlSocket = new Socket();
        } else {
            controlSocket = new Socket(currentProxy);
        }
        InetSocketAddress addr = new InetSocketAddress(hostName, port);
        controlSocket.connect(addr, connectTimeout);
        connected = true;
        ctrlOutput = controlSocket.getOutputStream();
        ctrlInput = controlSocket.getInputStream();
        login();
        setType();
        if (!getDoInput()) {
            cd();
        }

        try {
            acceptSocket = new ServerSocket(0);
            dataPort = acceptSocket.getLocalPort();
            /* Cannot set REUSEADDR so we need to send a PORT command */
            port();
            if (connectTimeout == 0) {
                // set timeout rather than zero as before
                connectTimeout = 3000;
            }
            acceptSocket.setSoTimeout(getConnectTimeout());
            if (getDoInput()) {
                getFile();
            } else {
                sendFile();
            }
            dataSocket = acceptSocket.accept();
            dataSocket.setSoTimeout(getReadTimeout());
            acceptSocket.close();
        } catch (InterruptedIOException e) {
            throw new IOException(Messages.getString("luni.1E")); //$NON-NLS-1$
        }
        if (getDoInput()) {
            inputStream = new FtpURLInputStream(new BufferedInputStream(
                    dataSocket.getInputStream()), controlSocket);
        }

    }

    /**
     * Answers the content type of the resource. Just takes a guess based on the
     * name.
     */
    @Override
    public String getContentType() {
        String result = guessContentTypeFromName(url.getFile());
        if (result == null) {
            return MimeTable.UNKNOWN;
        }
        return result;
    }

    private void getFile() throws IOException {
        int reply;
        String file = url.getFile();
        write("RETR " + file + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
        reply = getReply();
        if (reply == FTP_NOTFOUND && file.length() > 0 && file.charAt(0) == '/') {
            write("RETR " + file.substring(1) + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
            reply = getReply();
        }
        if (!(reply == FTP_OPENDATA || reply == FTP_TRANSFEROK)) {
            throw new FileNotFoundException(Messages.getString("luni.1F", reply)); //$NON-NLS-1$
        }
    }

    /**
     * Creates a input stream for writing to this URL Connection.
     * 
     * @return The input stream to write to
     * @throws IOException
     *             Cannot read from URL or error creating InputStream
     * 
     * @see #getContent()
     * @see #getOutputStream()
     * @see java.io.InputStream
     * @see java.io.IOException
     * 
     */
    @Override
    public InputStream getInputStream() throws IOException {
        if (!connected) {
            connect();
        }
        return inputStream;
    }

    /**
     * Answers the permission object (in this case, SocketPermission) with the
     * host and the port number as the target name and "resolve, connect" as the
     * action list.
     * 
     * @return the permission object required for this connection
     * @throws IOException
     *             thrown when an IO exception occurs during the creation of the
     *             permission object.
     */
    @Override
    public Permission getPermission() throws IOException {
        int port = url.getPort();
        if (port <= 0) {
            port = FTP_PORT;
        }
        return new SocketPermission(hostName + ":" + port, "connect, resolve"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Creates a output stream for writing to this URL Connection.
     * 
     * @return The output stream to write to
     * @throws IOException
     *             when the OutputStream could not be created
     * 
     * @see #getContent()
     * @see #getInputStream()
     * @see java.io.IOException
     * 
     */
    @Override
    public OutputStream getOutputStream() throws IOException {
        if (!connected) {
            connect();
        }
        return dataSocket.getOutputStream();
    }

    private int getReply() throws IOException {
        byte[] code = new byte[3];
        for (int i = 0; i < code.length; i++) {
            final int tmp = ctrlInput.read();
            if (tmp == -1) {
                throw new EOFException();
            }
            code[i] = (byte) tmp;
        }
        replyCode = new String(code, "ISO8859_1"); //$NON-NLS-1$
        
        boolean multiline = false;
        if (ctrlInput.read() == '-') {
            multiline = true;
        }
        readLine(); /* Skip the rest of the first line */
        if (multiline) {
            while (readMultiLine()) {/* Read all of a multiline reply */
            }
        }
        
        try {
            return Integer.parseInt(replyCode);
        } catch (NumberFormatException e) {
            throw (IOException)(new IOException("reply code is invalid").initCause(e));
        }
    }

    private void login() throws IOException {
        int reply;
        reply = getReply();
        if (reply == FTP_USERREADY) {
        } else {
            throw new IOException(Messages.getString("luni.1D", url.getHost())); //$NON-NLS-1$
        }
        write("USER " + username + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
        reply = getReply();
        if (reply == FTP_PASWD || reply == FTP_LOGGEDIN) {
        } else {
            throw new IOException(Messages.getString("luni.20", url.getHost())); //$NON-NLS-1$
        }
        if (reply == FTP_PASWD) {
            write("PASS " + password + "\r\n"); //$NON-NLS-1$ //$NON-NLS-2$
            reply = getReply();
            if (!(reply == FTP_OK || reply == FTP_USERREADY || reply == FTP_LOGGEDIN)) {
                throw new IOException(Messages.getString("luni.20", url.getHost())); //$NON-NLS-1$
            }
        }
    }

    private void port() throws IOException {
        write("PORT " //$NON-NLS-1$
                + controlSocket.getLocalAddress().getHostAddress().replace('.',
                        ',') + ',' + (dataPort >> 8) + ','
                + (dataPort & 255)
                + "\r\n"); //$NON-NLS-1$
        if (getReply() != FTP_OK) {
            throw new IOException(Messages.getString("luni.21")); //$NON-NLS-1$
        }
    }

    /**
     * Read a line of text and return it for possible parsing
     */
    private String readLine() throws IOException {
        StringBuilder sb = new StringBuilder();
        int c;
        while ((c = ctrlInput.read()) != '\n') {
            sb.append((char) c);
        }
        return sb.toString();
    }

    private boolean readMultiLine() throws IOException {
        String line = readLine();
        if (line.length() < 4) {
            return true;
        }
        if (line.substring(0, 3).equals(replyCode)
                && (line.charAt(3) == (char) 32)) {
            return false;
        }
        return true;
    }

    /**
     * Issue the STOR command to the server with the file as the parameter
     */
    private void sendFile() throws IOException {
        int reply;
        write("STOR " //$NON-NLS-1$
                + url.getFile().substring(url.getFile().lastIndexOf('/') + 1,
                        url.getFile().length()) + "\r\n"); //$NON-NLS-1$
        reply = getReply();
        if (!(reply == FTP_OPENDATA || reply == FTP_OK || reply == FTP_DATAOPEN)) {
            throw new IOException(Messages.getString("luni.22")); //$NON-NLS-1$
        }
    }

    /**
     * Set the flag if this <code>URLConnection</code> supports input (read).
     * It cannot be set after the connection is made. FtpURLConnections cannot
     * support both input and output
     * 
     * @param newValue *
     * @throws IllegalAccessError
     *             when this method attempts to change the flag after connected
     * 
     * @see #doInput
     * @see #getDoInput()
     * @see java.lang.IllegalAccessError
     * @see #setDoInput(boolean)
     */
    @Override
    public void setDoInput(boolean newValue) {
        if (connected) {
            throw new IllegalAccessError();
        }
        this.doInput = newValue;
        this.doOutput = !newValue;
    }

    /**
     * Set the flag if this <code>URLConnection</code> supports output(read).
     * It cannot be set after the connection is made.\ FtpURLConnections cannot
     * support both input and output.
     * 
     * @param newValue
     * 
     * @throws IllegalAccessError
     *             when this method attempts to change the flag after connected
     * 
     * @see #doOutput
     * @see java.lang.IllegalAccessError
     * @see #setDoOutput(boolean)
     */
    @Override
    public void setDoOutput(boolean newValue) {
        if (connected) {
            throw new IllegalAccessError();
        }
        this.doOutput = newValue;
        this.doInput = !newValue;
    }

    /**
     * Set the type of the file transfer. Only Image is supported
     */
    private void setType() throws IOException {
        write("TYPE I\r\n"); //$NON-NLS-1$
        if (getReply() != FTP_OK) {
            throw new IOException(Messages.getString("luni.23")); //$NON-NLS-1$
        }
    }

    private void write(String command) throws IOException {
        ctrlOutput.write(command.getBytes("ISO8859_1")); //$NON-NLS-1$
    }
}
