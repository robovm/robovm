/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/protocol/HttpContext.java $
 * $Revision: 558111 $
 * $Date: 2007-07-20 13:01:50 -0700 (Fri, 20 Jul 2007) $
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

package org.apache.http.protocol;

/**
 * A context for executing a request.
 * The context is used to tie together the request, the response,
 * and optional application data. It is also used for internal data.
 * Attribute names starting with the prefix "http." are
 * {@link #RESERVED_PREFIX reserved} for internal data.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 558111 $
 * 
 * @since 4.0
 */
public interface HttpContext {

    /** The prefix reserved for use by HTTP components. "http." */
    public static final String RESERVED_PREFIX  = "http.";
    
    Object getAttribute(String id);

    void setAttribute(String id, Object obj);

    Object removeAttribute(String id);
    
}
