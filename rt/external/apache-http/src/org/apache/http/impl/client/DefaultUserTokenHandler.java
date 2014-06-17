/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/client/DefaultUserTokenHandler.java $
 * $Revision: 659971 $
 * $Date: 2008-05-25 05:01:22 -0700 (Sun, 25 May 2008) $
 *
 * ====================================================================
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.impl.client;

import java.security.Principal;

import javax.net.ssl.SSLSession;

import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.client.UserTokenHandler;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ManagedClientConnection;
import org.apache.http.protocol.ExecutionContext;
import org.apache.http.protocol.HttpContext;

public class DefaultUserTokenHandler implements UserTokenHandler {

    public Object getUserToken(final HttpContext context) {
        
        Principal userPrincipal = null;
        
        AuthState targetAuthState = (AuthState) context.getAttribute(
                ClientContext.TARGET_AUTH_STATE);
        if (targetAuthState != null) {
            userPrincipal = getAuthPrincipal(targetAuthState);
            if (userPrincipal == null) {
                AuthState proxyAuthState = (AuthState) context.getAttribute(
                        ClientContext.PROXY_AUTH_STATE);
                userPrincipal = getAuthPrincipal(proxyAuthState);
            }
        }
        
        if (userPrincipal == null) {
            ManagedClientConnection conn = (ManagedClientConnection) context.getAttribute(
                    ExecutionContext.HTTP_CONNECTION);
            if (conn.isOpen()) {
                SSLSession sslsession = conn.getSSLSession();
                if (sslsession != null) {
                    userPrincipal = sslsession.getLocalPrincipal();
                }
            }
        }
        
        return userPrincipal;
    }

    private static Principal getAuthPrincipal(final AuthState authState) {
        AuthScheme scheme = authState.getAuthScheme();
        if (scheme != null && scheme.isComplete() && scheme.isConnectionBased()) {
            Credentials creds = authState.getCredentials();
            if (creds != null) {
                return creds.getUserPrincipal(); 
            }
        }
        return null;
    }
    
}
