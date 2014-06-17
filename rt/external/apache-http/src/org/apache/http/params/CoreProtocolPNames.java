/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/params/CoreProtocolPNames.java $
 * $Revision: 576077 $
 * $Date: 2007-09-16 04:50:22 -0700 (Sun, 16 Sep 2007) $
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

package org.apache.http.params;


/**
 * Defines parameter names for protocol execution in HttpCore.
 * 
 * @version $Revision: 576077 $
 * 
 * @since 4.0
 */
public interface CoreProtocolPNames {

    /**
     * Defines the {@link org.apache.http.ProtocolVersion protocol version}
     * used per default.
     * <p>
     * This parameter expects a value of type
     * {@link org.apache.http.ProtocolVersion}.
     * </p>
     */
    public static final String PROTOCOL_VERSION = "http.protocol.version"; 

    /**
     * Defines the charset to be used for encoding HTTP protocol elements.
     * <p>
     * This parameter expects a value of type {@link String}.
     * </p>
     */
    public static final String HTTP_ELEMENT_CHARSET = "http.protocol.element-charset"; 
    
    /**
     * Defines the charset to be used per default for encoding content body.
     * <p>
     * This parameter expects a value of type {@link String}.
     * </p>
     */
    public static final String HTTP_CONTENT_CHARSET = "http.protocol.content-charset"; 
    
    /**
     * Defines the content of the <tt>User-Agent</tt> header.
     * <p>
     * This parameter expects a value of type {@link String}.
     * </p>
     */
    public static final String USER_AGENT = "http.useragent"; 

    /**
     * Defines the content of the <tt>Server</tt> header.
     * <p>
     * This parameter expects a value of type {@link String}.
     * </p>
     */
    public static final String ORIGIN_SERVER = "http.origin-server"; 

    /**
     * Defines whether responses with an invalid <tt>Transfer-Encoding</tt> header should be 
     * rejected.
     * <p>
     * This parameter expects a value of type {@link Boolean}.
     * </p>
     */
    public static final String STRICT_TRANSFER_ENCODING = "http.protocol.strict-transfer-encoding"; 

    /**
     * <p>
     * Activates 'Expect: 100-continue' handshake for the
     * entity enclosing methods. The purpose of the 'Expect: 100-continue'
     * handshake to allow a client that is sending a request message with 
     * a request body to determine if the origin server is willing to 
     * accept the request (based on the request headers) before the client
     * sends the request body.
     * </p>
     * 
     * <p>
     * The use of the 'Expect: 100-continue' handshake can result in 
     * noticable peformance improvement for entity enclosing requests
     * (such as POST and PUT) that require the target server's 
     * authentication.
     * </p>
     * 
     * <p>
     * 'Expect: 100-continue' handshake should be used with 
     * caution, as it may cause problems with HTTP servers and 
     * proxies that do not support HTTP/1.1 protocol.
     * </p>
     * 
     * This parameter expects a value of type {@link Boolean}.
     */
    public static final String USE_EXPECT_CONTINUE = "http.protocol.expect-continue"; 

    /**
     * <p>
     * Defines the maximum period of time in milliseconds the client should spend
     * waiting for a 100-continue response.
     * </p>
     * 
     * This parameter expects a value of type {@link Integer}.
     */
    public static final String WAIT_FOR_CONTINUE = "http.protocol.wait-for-continue";
    
}
