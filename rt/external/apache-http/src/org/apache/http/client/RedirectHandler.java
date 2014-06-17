/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/client/RedirectHandler.java $
 * $Revision: 538647 $
 * $Date: 2007-05-16 09:41:42 -0700 (Wed, 16 May 2007) $
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

package org.apache.http.client;

import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolException;
import org.apache.http.protocol.HttpContext;

/**
 * A handler for determining if an HTTP request should be redirected to 
 * a new location in response to an HTTP response received from the target
 * server.
 * 
 * <p>
 * Classes implementing this interface must synchronize access to shared
 * data as methods of this interfrace may be executed from multiple threads 
 * </p>
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 */
public interface RedirectHandler {

    /**
     * Determines if a request should be redirected to a new location
     * given the response from the target server.
     * 
     * @param response the response received from the target server
     * @param context the context for the request execution
     * 
     * @return <code>true</code> if the request should be redirected, <code>false</code>
     * otherwise
     */
    boolean isRedirectRequested(HttpResponse response, HttpContext context);
    
    /**
     * Determines the location request is expected to be redirected to 
     * given the response from the target server and the current request
     * execution context.
     * 
     * @param response the response received from the target server
     * @param context the context for the request execution
     * 
     * @return redirect URI 
     */
    URI getLocationURI(HttpResponse response, HttpContext context)
            throws ProtocolException;

}
