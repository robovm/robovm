/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/auth/AuthSchemeBase.java $
 * $Revision: 653867 $
 * $Date: 2008-05-06 11:17:29 -0700 (Tue, 06 May 2008) $
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

package org.apache.http.impl.auth;

import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.auth.AUTH;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.CharArrayBuffer;

/**
 * Abstract authentication scheme class that serves as a basis
 * for all authentication schemes supported by HttpClient. This class
 * defines the generic way of parsing an authentication challenge. It 
 * does not make any assumptions regarding the format of the challenge 
 * nor does it impose any specific way of responding to that challenge.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
*/
public abstract class AuthSchemeBase implements AuthScheme {

    /**
     * Flag whether authenticating against a proxy.
     */
    private boolean proxy;
    
    public AuthSchemeBase() {
        super();
    }

    /**
     * Processes the given challenge token. Some authentication schemes
     * may involve multiple challenge-response exchanges. Such schemes must be able 
     * to maintain the state information when dealing with sequential challenges 
     * 
     * @param header the challenge header
     * 
     * @throws MalformedChallengeException is thrown if the authentication challenge
     * is malformed
     */
    public void processChallenge(final Header header) throws MalformedChallengeException {
        if (header == null) {
            throw new IllegalArgumentException("Header may not be null");
        }
        String authheader = header.getName();
        if (authheader.equalsIgnoreCase(AUTH.WWW_AUTH)) {
            this.proxy = false;
        } else if (authheader.equalsIgnoreCase(AUTH.PROXY_AUTH)) {
            this.proxy = true;
        } else {
            throw new MalformedChallengeException("Unexpected header name: " + authheader);
        }

        CharArrayBuffer buffer;
        int pos;
        if (header instanceof FormattedHeader) {
            buffer = ((FormattedHeader) header).getBuffer();
            pos = ((FormattedHeader) header).getValuePos();
        } else {
            String s = header.getValue();
            if (s == null) {
                throw new MalformedChallengeException("Header value is null");
            }
            buffer = new CharArrayBuffer(s.length());
            buffer.append(s);
            pos = 0;
        }
        while (pos < buffer.length() && HTTP.isWhitespace(buffer.charAt(pos))) {
            pos++;
        }
        int beginIndex = pos;
        while (pos < buffer.length() && !HTTP.isWhitespace(buffer.charAt(pos))) {
            pos++;
        }
        int endIndex = pos;
        String s = buffer.substring(beginIndex, endIndex);
        if (!s.equalsIgnoreCase(getSchemeName())) {
            throw new MalformedChallengeException("Invalid scheme identifier: " + s);
        }
        
        parseChallenge(buffer, pos, buffer.length());
    }

    protected abstract void parseChallenge(
            CharArrayBuffer buffer, int pos, int len) throws MalformedChallengeException;

    /**
     * Returns <code>true</code> if authenticating against a proxy, <code>false</code>
     * otherwise.
     *  
     * @return <code>true</code> if authenticating against a proxy, <code>false</code>
     * otherwise
     */
    public boolean isProxy() {
        return this.proxy;
    }
    
}
