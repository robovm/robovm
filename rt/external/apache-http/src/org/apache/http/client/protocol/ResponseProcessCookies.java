/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/client/protocol/ResponseProcessCookies.java $
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

package org.apache.http.client.protocol;

import java.io.IOException;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.cookie.CookieOrigin;
import org.apache.http.cookie.CookieSpec;
import org.apache.http.cookie.MalformedCookieException;
import org.apache.http.cookie.SM;
import org.apache.http.protocol.HttpContext;

/**
 * Response interceptor that populates the current {@link CookieStore} with data 
 * contained in response cookies received in the given the HTTP response.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 673450 $
 * 
 * @since 4.0
 */
public class ResponseProcessCookies implements HttpResponseInterceptor {

    private final Log log = LogFactory.getLog(getClass());
    
    public ResponseProcessCookies() {
        super();
    }
    
    public void process(final HttpResponse response, final HttpContext context) 
            throws HttpException, IOException {
        if (response == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        if (context == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        
        // Obtain cookie store
        CookieStore cookieStore = (CookieStore) context.getAttribute(
                ClientContext.COOKIE_STORE);
        if (cookieStore == null) {
            this.log.info("Cookie store not available in HTTP context");
            return;
        }
        // Obtain actual CookieSpec instance
        CookieSpec cookieSpec = (CookieSpec) context.getAttribute(
                ClientContext.COOKIE_SPEC);
        if (cookieSpec == null) {
            this.log.info("CookieSpec not available in HTTP context");
            return;
        }
        // Obtain actual CookieOrigin instance
        CookieOrigin cookieOrigin = (CookieOrigin) context.getAttribute(
                ClientContext.COOKIE_ORIGIN);
        if (cookieOrigin == null) {
            this.log.info("CookieOrigin not available in HTTP context");
            return;
        }
        HeaderIterator it = response.headerIterator(SM.SET_COOKIE);
        processCookies(it, cookieSpec, cookieOrigin, cookieStore);
        
        // see if the cookie spec supports cookie versioning.
        if (cookieSpec.getVersion() > 0) {
            // process set-cookie2 headers.
            // Cookie2 will replace equivalent Cookie instances
            it = response.headerIterator(SM.SET_COOKIE2);
            processCookies(it, cookieSpec, cookieOrigin, cookieStore);
        }
    }
     
    private void processCookies(
            final HeaderIterator iterator, 
            final CookieSpec cookieSpec,
            final CookieOrigin cookieOrigin,
            final CookieStore cookieStore) {
        while (iterator.hasNext()) {
            Header header = iterator.nextHeader();
            try {
                List<Cookie> cookies = cookieSpec.parse(header, cookieOrigin);
                for (Cookie cookie : cookies) {
                    try {
                        cookieSpec.validate(cookie, cookieOrigin);
                        cookieStore.addCookie(cookie);

                        if (this.log.isDebugEnabled()) {
                            // BEGIN android-changed
                            this.log.debug("Cookie accepted: \""
                                    + cookieToString(cookie) + "\". ");
                            // END android-changed
                        }
                    } catch (MalformedCookieException ex) {
                        if (this.log.isWarnEnabled()) {
                            // BEGIN android-changed
                            this.log.warn("Cookie rejected: \""
                                    + cookieToString(cookie) + "\". " + ex.getMessage());
                            // END android-changed
                        }
                    }
                }
            } catch (MalformedCookieException ex) {
                if (this.log.isWarnEnabled()) {
                    this.log.warn("Invalid cookie header: \""
                            + header + "\". " + ex.getMessage());
                }
            }
        }
    }

    // BEGIN android-added
    /**
     * Don't log the cookie's value; that's potentially sensitive information.
     */
    private String cookieToString(Cookie cookie) {
        return cookie.getClass().getSimpleName()
                + "[version=" + cookie.getVersion()
                + ",name=" + cookie.getName()
                + ",domain=" + cookie.getDomain()
                + ",path=" + cookie.getPath()
                + ",expiry=" + cookie.getExpiryDate()
                + "]";
    }
    // END android-added
}
