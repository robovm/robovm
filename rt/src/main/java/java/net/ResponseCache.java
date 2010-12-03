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
import java.util.List;
import java.util.Map;

/**
 * This class is an implementation of {@code URLConnection} caches intended
 * primarily for the according stream handler implementations.
 * <p>
 * The system's default cache can be registered by invoking the method {@code
 * setDefault(ResponseCache)} and be retrieved by invoking the method {@code
 * getDefault()}. If {@code URLConnection#useCaches} is set, {@code
 * URLConnection} class will use {@code ResponseCache} to store and get
 * resources.
 * <p>
 * Whether the resource is cached depends on the implementation of {@code
 * ResponseCache}. If so, a {@code CacheResponse} is returned from which the
 * stream handler reads. If the stream handler fails to get a resource from the
 * cache, it must get the resource from its original location.
 * <p>
 * To write to the cache, the protocol handlers call {@code put()}, upon which a
 * {@code CacheRequest} is supplied to which the resources are written.
 *
 * @see #put(URI, URLConnection)
 * @see CacheRequest
 * @see CacheResponse
 * @see URLConnection
 * @see URLStreamHandler
 */
public abstract class ResponseCache {

    /*
     * _defaultResponseCache is used to store default response cache.
     */
    private static ResponseCache _defaultResponseCache = null;

    /*
     * "getResponseCache" permission. getDefault method requires this
     * permission.
     */
    private static NetPermission getResponseCachepermission = new NetPermission(
            "getResponseCache"); //$NON-NLS-1$

    /*
     * "setResponseCache" permission. setDefault method requires this
     * permission.
     */
    private static NetPermission setResponseCachepermission = new NetPermission(
            "setResponseCache"); //$NON-NLS-1$

    /*
     * check getResponseCache permission. getDefault method requires
     * "getResponseCache" permission if a security manager is installed.
     */
    private static void checkGetResponseCachePermission() {
        SecurityManager sm = System.getSecurityManager();
        if (null != sm) {
            sm.checkPermission(getResponseCachepermission);
        }
    }

    /*
     * check setResponseCache permission. setDefault method requires
     * "setResponseCache" permission if a security manager is installed.
     */
    private static void checkSetResponseCachePermission() {
        SecurityManager sm = System.getSecurityManager();
        if (null != sm) {
            sm.checkPermission(setResponseCachepermission);
        }
    }

    /**
     * Creates a new instance of this class.
     */
    public ResponseCache() {
        super();
    }

    /**
     * Gets the default response cache of the system.
     * 
     * @return the default {@code ResponseCache}.
     * @throws SecurityException
     *             if a security manager is installed but it doesn't have the
     *             {@code NetPermission("getResponseCache")}.
     */
    public static ResponseCache getDefault() {
        checkGetResponseCachePermission();
        return _defaultResponseCache;
    }

    /**
     * Sets the default response cache of the system. Removes the system's
     * default {@code ResponseCache} if the parameter {@code responseCache} is
     * set to {@code null}. This setting may be ignored by some non-standard
     * protocols.
     * 
     * @param responseCache
     *            the {@code ResponseCache} instance to set as default or
     *            {@code null} to remove the current default {@code
     *            ResponseCache}.
     * @throws SecurityException
     *             if a security manager is installed but it doesn't have the
     *             {@code NetPermission("setResponseCache")}.
     */
    public static void setDefault(ResponseCache responseCache) {
        checkSetResponseCachePermission();
        _defaultResponseCache = responseCache;
    }

    /**
     * Gets the cached response according to the requesting URI, method and
     * headers.
     * 
     * @param uri
     *            the requesting URI.
     * @param rqstMethod
     *            the requesting method.
     * @param rqstHeaders
     *            a map of requesting headers.
     * @return the {@code CacheResponse} object if the request is available in the cache
     *         or {@code null} otherwise.
     * @throws IOException
     *             if an I/O error occurs while getting the cached data.
     * @throws IllegalArgumentException
     *             if any one of the parameters is set to {@code null}.
     */
    public abstract CacheResponse get(URI uri, String rqstMethod,
            Map<String, List<String>> rqstHeaders) throws IOException;

    /**
     * Allows the protocol handler to cache data after retrieving resources. The
     * {@code ResponseCache} decides whether the resource data should be cached
     * or not. If so, this method returns a {@code CacheRequest} with a {@code
     * WriteableByteChannel} to put the resource data down. Otherwise, this
     * method returns {@code null}.
     * 
     * @param uri
     *            the reference to the requested resource.
     * @param conn
     *            the connection to fetch the response.
     * @return a CacheRequest object with a WriteableByteChannel if the resource
     *         has to be cached, {@code null} otherwise.
     * @throws IOException
     *             if an I/O error occurs while adding the resource.
     * @throws IllegalArgumentException
     *             if any one of the parameters is set to {@code null}.
     */
    public abstract CacheRequest put(URI uri, URLConnection conn)
            throws IOException;
}
