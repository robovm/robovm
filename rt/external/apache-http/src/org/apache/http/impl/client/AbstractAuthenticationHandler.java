/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/client/AbstractAuthenticationHandler.java $
 * $Revision: 673450 $
 * $Date: 2008-07-02 10:35:05 -0700 (Wed, 02 Jul 2008) $
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

package org.apache.http.impl.client;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.FormattedHeader;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.auth.AuthScheme;
import org.apache.http.auth.AuthSchemeRegistry;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.client.AuthenticationHandler;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.CharArrayBuffer;

/**
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 */
public abstract class AbstractAuthenticationHandler implements AuthenticationHandler {

    private final Log log = LogFactory.getLog(getClass());
    
    private static final List<String> DEFAULT_SCHEME_PRIORITY = Arrays.asList(new String[] {
            "ntlm",
            "digest",
            "basic"
    });
    
    public AbstractAuthenticationHandler() {
        super();
    }
    
    protected Map<String, Header> parseChallenges(
            final Header[] headers) throws MalformedChallengeException {
        
        Map<String, Header> map = new HashMap<String, Header>(headers.length);
        for (Header header : headers) {
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
            map.put(s.toLowerCase(Locale.ENGLISH), header);
        }
        return map;
    }
    
    protected List<String> getAuthPreferences() {
        return DEFAULT_SCHEME_PRIORITY;
    }
    
    public AuthScheme selectScheme(
            final Map<String, Header> challenges, 
            final HttpResponse response,
            final HttpContext context) throws AuthenticationException {
        
        AuthSchemeRegistry registry = (AuthSchemeRegistry) context.getAttribute(
                ClientContext.AUTHSCHEME_REGISTRY);
        if (registry == null) {
            throw new IllegalStateException("AuthScheme registry not set in HTTP context");
        }
        
        List<?> authPrefs = (List<?>) context.getAttribute(
                ClientContext.AUTH_SCHEME_PREF);
        if (authPrefs == null) {
            authPrefs = getAuthPreferences();
        }
        
        if (this.log.isDebugEnabled()) {
            this.log.debug("Authentication schemes in the order of preference: " 
                + authPrefs);
        }

        AuthScheme authScheme = null;
        for (int i = 0; i < authPrefs.size(); i++) {
            String id = (String) authPrefs.get(i);
            Header challenge = challenges.get(id.toLowerCase(Locale.ENGLISH)); 

            if (challenge != null) {
                if (this.log.isDebugEnabled()) {
                    this.log.debug(id + " authentication scheme selected");
                }
                try {
                    authScheme = registry.getAuthScheme(id, response.getParams());
                    break;
                } catch (IllegalStateException e) {
                    if (this.log.isWarnEnabled()) {
                        this.log.warn("Authentication scheme " + id + " not supported");
                        // Try again
                    }
                }
            } else {
                if (this.log.isDebugEnabled()) {
                    this.log.debug("Challenge for " + id + " authentication scheme not available");
                    // Try again
                }
            }
        }
        if (authScheme == null) {
            // If none selected, something is wrong
            throw new AuthenticationException(
                "Unable to respond to any of these challenges: "
                    + challenges);
        }
        return authScheme;
    }

}
