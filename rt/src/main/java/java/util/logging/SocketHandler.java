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

package java.util.logging;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.harmony.logging.internal.nls.Messages;

/**
 * A handler that writes log messages to a socket connection.
 * <p>
 * This handler reads the following properties from the log manager to
 * initialize itself:
 * <ul>
 * <li>java.util.logging.ConsoleHandler.level specifies the logging level,
 * defaults to {@code Level.ALL} if this property is not found or has an invalid
 * value.
 * <li>java.util.logging.SocketHandler.filter specifies the name of the filter
 * class to be associated with this handler, defaults to {@code null} if this
 * property is not found or has an invalid value.
 * <li>java.util.logging.SocketHandler.formatter specifies the name of the
 * formatter class to be associated with this handler, defaults to
 * {@code java.util.logging.XMLFormatter} if this property is not found or has
 * an invalid value.
 * <li>java.util.logging.SocketHandler.encoding specifies the encoding this
 * handler will use to encode log messages, defaults to {@code null} if this
 * property is not found or has an invalid value.
 * <li>java.util.logging.SocketHandler.host specifies the name of the host that
 * this handler should connect to. There's no default value for this property.
 * <li>java.util.logging.SocketHandler.encoding specifies the port number that
 * this handler should connect to. There's no default value for this property.
 * </ul>
 * <p>
 * This handler buffers the outgoing messages, but flushes each time a log
 * record has been published.
 * <p>
 * This class is not thread-safe.
 */
public class SocketHandler extends StreamHandler {

    // default level
    private static final String DEFAULT_LEVEL = "ALL"; //$NON-NLS-1$

    // default formatter
    private static final String DEFAULT_FORMATTER = "java.util.logging.XMLFormatter"; //$NON-NLS-1$

    // the socket connection
    private Socket socket;

    /**
     * Constructs a {@code SocketHandler} object using the properties read by
     * the log manager, including the host name and port number. Default
     * formatting uses the XMLFormatter class and level is set to ALL.
     * 
     * @throws IOException
     *             if failed to connect to the specified host and port.
     * @throws IllegalArgumentException
     *             if the host name or port number is illegal.
     * @throws SecurityException
     *             if a security manager determines that the caller does not
     *             have the required permission to control this handler.
     */
    public SocketHandler() throws IOException {
        super(DEFAULT_LEVEL, null, DEFAULT_FORMATTER, null);
        initSocket(LogManager.getLogManager().getProperty(
                "java.util.logging.SocketHandler.host"), LogManager //$NON-NLS-1$
                .getLogManager().getProperty(
                        "java.util.logging.SocketHandler.port")); //$NON-NLS-1$
    }

    /**
     * Constructs a {@code SocketHandler} object using the specified host name
     * and port number together with other properties read by the log manager.
     * Default formatting uses the XMLFormatter class and level is set to ALL.
     *
     * @param host
     *            the host name
     * @param port
     *            the port number
     * @throws IOException
     *             if failed to connect to the specified host and port.
     * @throws IllegalArgumentException
     *             if the host name or port number is illegal.
     * @throws SecurityException
     *             if a security manager determines that the caller does not
     *             have the required permission to control this handler.
     */
    public SocketHandler(String host, int port) throws IOException {
        super(DEFAULT_LEVEL, null, DEFAULT_FORMATTER, null);
        initSocket(host, String.valueOf(port));
    }

    // Initialize the socket connection and prepare the output stream
    private void initSocket(String host, String port) throws IOException {
        // check the validity of the host name
        if (null == host || "".equals(host)) { //$NON-NLS-1$
            // logging.C=Illegal host argument.
            throw new IllegalArgumentException(Messages.getString("logging.C")); //$NON-NLS-1$
        }
        // check the validity of the port number
        int p = 0;
        try {
            p = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            // logging.D=Illegal port argument.
            throw new IllegalArgumentException(Messages.getString("logging.D")); //$NON-NLS-1$
        }
        if (p <= 0) {
            // logging.D=Illegal port argument.
            throw new IllegalArgumentException(Messages.getString("logging.D")); //$NON-NLS-1$
        }
        // establish the network connection
        try {
            this.socket = new Socket(host, p);
        } catch (IOException e) {
            // logging.E=Failed to establish the network connection.
            getErrorManager().error(Messages.getString("logging.E"), e, //$NON-NLS-1$
                    ErrorManager.OPEN_FAILURE);
            throw e;
        }
        super.internalSetOutputStream(new BufferedOutputStream(this.socket
                .getOutputStream()));
    }

    /**
     * Closes this handler. The network connection to the host is also closed.
     * 
     * @throws SecurityException
     *             If a security manager determines that the caller does not
     *             have the required permission to control this handler.
     */
    @Override
    public void close() {
        try {
            super.close();
            if (null != this.socket) {
                this.socket.close();
                this.socket = null;
            }
        } catch (Exception e) {
            // logging.F=Exception occurred when closing the socket handler.
            getErrorManager().error(Messages.getString("logging.F"), e, //$NON-NLS-1$
                    ErrorManager.CLOSE_FAILURE);
        }
    }

    /**
     * Logs a record if necessary. A flush operation will be done afterwards.
     * 
     * @param record
     *            the log record to be logged.
     */
    @Override
    public void publish(LogRecord record) {
        super.publish(record);
        super.flush();
    }
}
