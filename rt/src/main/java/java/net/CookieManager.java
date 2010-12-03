/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.net;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Collections;

import org.apache.harmony.luni.util.Msg;

/**
 * This class provides a concrete implementation of CookieHandler. It separates
 * the storage of cookies from the policy which decides to accept or deny
 * cookies. The constructor can have two arguments: a CookieStore and a
 * CookiePolicy. The former is in charge of cookie storage and the latter makes
 * decision on acceptance/rejection.
 * 
 * CookieHandler is in the center of cookie management. User can make use of
 * CookieHandler.setDefault to set a CookieManager as the default one used.
 * 
 * CookieManager.put uses CookiePolicy.shouldAccept to decide whether to put
 * some cookies into a cookie store. Three built-in CookiePolicy is defined:
 * ACCEPT_ALL, ACCEPT_NONE and ACCEPT_ORIGINAL_SERVER. Users can also customize
 * the policy by implementing CookiePolicy. Any accepted HTTP cookie is stored
 * in CookieStore and users can also have their own implementation. Up to now,
 * Only add(URI, HttpCookie) and get(URI) are used by CookieManager. Other
 * methods in this class may probably be used in a more complicated
 * implementation.
 * 
 * There are many ways to customize user's own HTTP cookie management:
 * 
 * First, call CookieHandler.setDefault to set a new CookieHandler
 * implementation. Second, call CookieHandler.getDefault to use CookieManager.
 * The CookiePolicy and CookieStore used are customized. Third, use the
 * customized CookiePolicy and the CookieStore.
 * 
 * This implementation conforms to RFC 2965, section 3.3.
 * 
 * @since 1.6
 */
public class CookieManager extends CookieHandler {
    private CookieStore store;

    private CookiePolicy policy;

    private static final String VERSION_ZERO_HEADER = "Set-cookie"; //$NON-NLS-1$

    private static final String VERSION_ONE_HEADER = "Set-cookie2"; //$NON-NLS-1$

    /**
     * Constructs a new cookie manager.
     * 
     * The invocation of this constructor is the same as the invocation of
     * CookieManager(null, null).
     * 
     */
    public CookieManager() {
        this(null, null);
    }

    /**
     * Constructs a new cookie manager using a specified cookie store and a
     * cookie policy.
     * 
     * @param store
     *            a CookieStore to be used by cookie manager. The manager will
     *            use a default one if the arg is null.
     * @param cookiePolicy
     *            a CookiePolicy to be used by cookie manager
     *            ACCEPT_ORIGINAL_SERVER will be used if the arg is null.
     */
    public CookieManager(CookieStore store, CookiePolicy cookiePolicy) {
        this.store = store == null ? new CookieStoreImpl() : store;
        policy = cookiePolicy == null ? CookiePolicy.ACCEPT_ORIGINAL_SERVER
                : cookiePolicy;
    }

    /**
     * Searchs and gets all cookies in the cache by the specified uri in the
     * request header.
     * 
     * @param uri
     *            the specified uri to search for
     * @param requestHeaders
     *            a list of request headers
     * @return a map that record all such cookies, the map is unchangeable
     * @throws IOException
     *             if some error of I/O operation occurs
     */
    @Override
    public Map<String, List<String>> get(URI uri,
            Map<String, List<String>> requestHeaders) throws IOException {
        if (uri == null || requestHeaders == null) {
            throw new IllegalArgumentException(Msg.getString("KB004")); //$NON-NLS-1$
        }
        List<HttpCookie> cookies = store.get(uri);
        for (int i = 0; i < cookies.size(); i++) {
            HttpCookie cookie = cookies.get(i);
            String uriPath = uri.getPath();
            String cookiePath = cookie.getPath();
            // if the uri's path does not path-match cookie's path, remove
            // cookies from the list
            if (cookiePath == null || uriPath.length() == 0
                    || !uriPath.startsWith(cookiePath)) {
                cookies.remove(i);
            }
        }
        // TODO parse cookies into Map and so far requesterHeaders are not used
        return getCookieMap(cookies, requestHeaders);
    }

    private static Map<String, List<String>> getCookieMap(
            List<HttpCookie> cookies, Map<String, List<String>> requestHeaders) {
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        ArrayList<String> cookieStr = new ArrayList<String>();
        // If all cookies are version 1, add a "$Version="1"" header
        boolean versionOne = true;
        for (HttpCookie cookie : cookies) {
            if (cookie.getVersion() == 0) {
                versionOne = false;
                break;
            }
        }
        if (versionOne && !cookies.isEmpty()) {
            cookieStr.add("$Version=\"1\""); //$NON-NLS-1$
        }
        // add every cookie's string representation into map
        for (HttpCookie cookie : cookies) {
            cookieStr.add(cookie.toString());
        }
        // TODO So far only "Cookie" head detected
        map.put("Cookie", cookieStr); //$NON-NLS-1$
        return Collections.unmodifiableMap(map);
    }

    /**
     * Sets cookies according to uri and responseHeaders
     * 
     * @param uri
     *            the specified uri
     * @param responseHeaders
     *            a list of request headers
     * @throws IOException
     *             if some error of I/O operation occurs
     */
    @Override
    public void put(URI uri, Map<String, List<String>> responseHeaders)
            throws IOException {
        if (uri == null || responseHeaders == null) {
            throw new IllegalArgumentException(Msg.getString("KA019")); //$NON-NLS-1$
        }
        // parse and construct cookies according to the map
        List<HttpCookie> cookies = parseCookie(responseHeaders);
        for (HttpCookie cookie : cookies) {
            // if the cookie conforms to the policy and matches the uri's path,
            // add it into the store
            if (policy.shouldAccept(uri, cookie)) {
                store.add(uri, cookie);
            }
        }
    }

    private static List<HttpCookie> parseCookie(
            Map<String, List<String>> responseHeaders) {
        List<HttpCookie> cookies = new ArrayList<HttpCookie>();
        for (Map.Entry<String, List<String>> entry : responseHeaders.entrySet()) {
            String key = entry.getKey();
            // Only "Set-cookie" and "Set-cookie2" pair will be parsed
            if (key != null
                    && (key.equalsIgnoreCase(VERSION_ZERO_HEADER) || key
                            .equalsIgnoreCase(VERSION_ONE_HEADER))) {
                // parse list elements one by one
                for (String cookieStr : entry.getValue()) {
                    try {
                        for (HttpCookie cookie : HttpCookie.parse(cookieStr)) {
                            cookies.add(cookie);
                        }
                    } catch (IllegalArgumentException e) {
                        // this string is invalid, jump to the next one.
                    }
                }
            }
        }
        return cookies;
    }

    /**
     * Sets the cookie policy of this cookie manager.
     * 
     * ACCEPT_ORIGINAL_SERVER is the default policy for CookieManager.
     * 
     * @param cookiePolicy
     *            the cookie policy. if null, the original policy will not be
     *            changed.
     */
    public void setCookiePolicy(CookiePolicy cookiePolicy) {
        if (cookiePolicy != null) {
            policy = cookiePolicy;
        }
    }

    /**
     * Gets current cookie store.
     * 
     * @return the cookie store currently used by cookie manager.
     */
    public CookieStore getCookieStore() {
        return store;
    }
}
