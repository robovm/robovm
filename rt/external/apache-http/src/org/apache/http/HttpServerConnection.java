/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/HttpServerConnection.java $
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
 * An HTTP connection for use on the server side.
 * Requests are received, responses are sent.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 542199 $
 * 
 * @since 4.0
 */
public interface HttpServerConnection extends HttpConnection {

    /**
     * Receives the request line and all headers available from this connection.
     * The caller should examine the returned request and decide if to receive a
     * request entity as well.
     * 
     * @return a new HttpRequest object whose request line and headers are
     *         initialized.
     * @throws HttpException
     * @throws IOException
     */
    HttpRequest receiveRequestHeader() 
        throws HttpException, IOException;

    /**
     * Receives the next request entity available from this connection and attaches it to 
     * an existing request. 
     * @param request the request to attach the entity to.
     * @throws HttpException
     * @throws IOException
     */
    void receiveRequestEntity(HttpEntityEnclosingRequest request) 
        throws HttpException, IOException;

    /**
     * Sends the response line and headers of a response over this connection.
     * @param response the response whose headers to send.
     * @throws HttpException
     * @throws IOException
     */
    void sendResponseHeader(HttpResponse response) 
        throws HttpException, IOException;
    
    /**
     * Sends the response entity of a response over this connection.
     * @param response the response whose entity to send.
     * @throws HttpException
     * @throws IOException
     */
    void sendResponseEntity(HttpResponse response) 
        throws HttpException, IOException;
    
    /**
     * Sends all pending buffered data over this connection.
     * @throws IOException
     */
    void flush()
        throws IOException;
    
}
