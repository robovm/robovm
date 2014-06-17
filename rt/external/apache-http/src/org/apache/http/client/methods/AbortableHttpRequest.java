/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/client/methods/AbortableHttpRequest.java $
 * $Revision: 639600 $
 * $Date: 2008-03-21 04:28:15 -0700 (Fri, 21 Mar 2008) $
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

package org.apache.http.client.methods;

import java.io.IOException;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ClientConnectionRequest;
import org.apache.http.conn.ConnectionReleaseTrigger;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;

/**
 * Interface representing an HTTP request that can be aborted by shutting 
 * down the underlying HTTP connection.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * <!-- empty lines to avoid svn diff problems -->
 * @version   $Revision: 639600 $
 *
 * @since 4.0
 */
public interface AbortableHttpRequest {

    /**
     * Sets the {@link ClientConnectionRequest} callback that can be
     * used to abort a long-lived request for a connection.
     * If the request is already aborted, throws an {@link IOException}.
     * 
     * @see ClientConnectionManager
     * @see ThreadSafeClientConnManager
     */
    void setConnectionRequest(ClientConnectionRequest connRequest) throws IOException;
    
    /**
     * Sets the {@link ConnectionReleaseTrigger} callback that can
     * be used to abort an active connection.
     * Typically, this will be the {@link ManagedClientConnection} itself.
     * If the request is already aborted, throws an {@link IOException}.
     */
    void setReleaseTrigger(ConnectionReleaseTrigger releaseTrigger) throws IOException;

    /**
     * Aborts this http request. Any active execution of this method should
     * return immediately. If the request has not started, it will abort after
     * the next execution. Aborting this request will cause all subsequent
     * executions with this request to fail.
     * 
     * @see HttpClient#execute(HttpUriRequest)
     * @see HttpClient#execute(org.apache.http.HttpHost,
     *      org.apache.http.HttpRequest)
     * @see HttpClient#execute(HttpUriRequest,
     *      org.apache.http.protocol.HttpContext)
     * @see HttpClient#execute(org.apache.http.HttpHost,
     *      org.apache.http.HttpRequest, org.apache.http.protocol.HttpContext)
     */
    void abort();

}

