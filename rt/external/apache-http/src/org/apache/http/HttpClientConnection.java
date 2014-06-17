/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/HttpClientConnection.java $
 * $Revision: 542199 $
 * $Date: 2007-05-28 04:23:46 -0700 (Mon, 28 May 2007) $
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http;

import java.io.IOException;

/**
 * An HTTP connection for use on the client side.
 * It is used for sending requests and receiving responses.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 *
 * <!-- empty lines above to avoid 'svn diff' context problems -->
 * @version $Revision: 542199 $
 * 
 * @since 4.0
 */
public interface HttpClientConnection extends HttpConnection {

    /**
     * Checks if response data is available from the connection. May wait for
     * the specified time until some data becomes available. Note that some
     * implementations may completely ignore the timeout parameter.
     * 
     * @param timeout the maximum time in milliseconds to wait for data
     * @return true if data is available; false if there was no data available
     *         even after waiting for <code>timeout</code> milliseconds.
     * @throws IOException if an error happens on the connection
     */
    boolean isResponseAvailable(int timeout) 
        throws IOException; 
    
    /**
     * Sends the request line and all headers over the connection.
     * @param request the request whose headers to send.
     * @throws HttpException 
     * @throws IOException
     */
    void sendRequestHeader(HttpRequest request) 
        throws HttpException, IOException;

    /**
     * Sends the request entity over the connection.
     * @param request the request whose entity to send.
     * @throws HttpException
     * @throws IOException
     */
    void sendRequestEntity(HttpEntityEnclosingRequest request) 
        throws HttpException, IOException;

    /**
     * Receives the request line and headers of the next response available from
     * this connection. The caller should examine the HttpResponse object to
     * find out if it should try to receive a response entity as well.
     * 
     * @return a new HttpResponse object with status line and headers
     *         initialized.
     * @throws HttpException
     * @throws IOException
     */
    HttpResponse receiveResponseHeader() 
        throws HttpException, IOException;

    /**
     * Receives the next response entity available from this connection and
     * attaches it to an existing HttpResponse object.
     * 
     * @param response the response to attach the entity to
     * @throws HttpException
     * @throws IOException
     */
    void receiveResponseEntity(HttpResponse response) 
        throws HttpException, IOException;
    
    /**
     * Writes out all pending buffered data over the open connection.
     * 
     * @throws IOException
     */
    void flush() throws IOException;
    
}
