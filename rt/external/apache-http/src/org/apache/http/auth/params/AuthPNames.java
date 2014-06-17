/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/auth/params/AuthPNames.java $
 * $Revision: 578403 $
 * $Date: 2007-09-22 03:56:04 -0700 (Sat, 22 Sep 2007) $
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

package org.apache.http.auth.params;

/**
 * Parameter names for HttpAuth.
 * 
 * @version $Revision: 578403 $
 * 
 * @since 4.0
 */
public interface AuthPNames {

    /**
     * Defines the charset to be used when encoding 
     * {@link org.apache.http.auth.Credentials}.
     * <p>
     * This parameter expects a value of type {@link String}.
     * If not defined, then
     * {@link org.apache.http.params.CoreProtocolPNames#HTTP_ELEMENT_CHARSET
     *        HttpProtocolParams.HTTP_ELEMENT_CHARSET}
     * should be used.
     * </p>
     */
    public static final String CREDENTIAL_CHARSET = "http.auth.credential-charset"; 

}
