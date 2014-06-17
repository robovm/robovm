/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/ConnectionReuseStrategy.java $
 * $Revision: 496070 $
 * $Date: 2007-01-14 04:18:34 -0800 (Sun, 14 Jan 2007) $
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

import org.apache.http.protocol.HttpContext;

/**
 * Interface for deciding whether a connection should be kept alive.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 *
 * <!-- empty lines above to avoid 'svn diff' context problems -->
 * @version $Revision: 496070 $
 * 
 * @since 4.0
 */
public interface ConnectionReuseStrategy {

    /**
     * Decides whether a connection can be kept open after a request.
     * If this method returns <code>false</code>, the caller MUST
     * close the connection to correctly implement the HTTP protocol.
     * If it returns <code>true</code>, the caller SHOULD attempt to
     * keep the connection open for reuse with another request.
     * <br/>
     * One can use the HTTP context to retrieve additional objects that 
     * may be relevant for the keep-alive strategy: the actual HTTP 
     * connection, the original HTTP request, target host if known, 
     * number of times the connection has been reused already and so on.
     * <br/>
     * If the connection is already closed, <code>false</code> is returned.
     * The stale connection check MUST NOT be triggered by a
     * connection reuse strategy.
     *
     * @param response
     *          The last response received over that connection.
     * @param context   the context in which the connection is being 
     *          used.
     *
     * @return <code>true</code> if the connection is allowed to be reused, or
     *         <code>false</code> if it MUST NOT be reused
     */
    boolean keepAlive(HttpResponse response, HttpContext context);
            
}
