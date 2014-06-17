/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/protocol/ExecutionContext.java $
 * $Revision: 558154 $
 * $Date: 2007-07-20 14:29:02 -0700 (Fri, 20 Jul 2007) $
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
 * {@link HttpContext Context} attribute names for protocol execution.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 558154 $
 * 
 * @since 4.0
 */
public interface ExecutionContext {
    
    public static final String HTTP_CONNECTION  = "http.connection"; 
    public static final String HTTP_REQUEST     = "http.request"; 
    public static final String HTTP_RESPONSE    = "http.response"; 
    public static final String HTTP_TARGET_HOST = "http.target_host"; 
    public static final String HTTP_PROXY_HOST  = "http.proxy_host"; 
    public static final String HTTP_REQ_SENT    = "http.request_sent"; 

}
