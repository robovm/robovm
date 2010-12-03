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

import java.security.AccessController;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.harmony.luni.util.PriviAction;

/**
 * This class is used to maintain the negative name lookup cache, which caches
 * host names which could not be resolved, as a security feature.
 *
 * @see NegCacheElement
 */
class NegativeCache<K, V> extends LinkedHashMap<K, V> {

    private static final long serialVersionUID = 1L;

    private static NegativeCache<String, NegCacheElement> negCache;

    // maximum number of entries in the cache
    private static final int MAX_NEGATIVE_ENTRIES = 5;

    // the loading for the cache
    private static final float LOADING = 0.75F;

    /**
     * Constructs a negative cache for name lookups.
     * 
     * @param initialCapacity
     *            the initial size of the cache.
     * @param loadFactor
     *            the load factor of the backing map.
     * @param accessOrder
     *            if {@code true} indicates that traversal order should begin
     *            with most recently accessed element.
     */
    NegativeCache(int initialCapacity, float loadFactor, boolean accessOrder) {
        super(initialCapacity, loadFactor, accessOrder);
    }

    /**
     * Returns whether the eldest entry should be removed. It is removed if the
     * size has grown beyond the maximum size allowed for the cache. A {@code
     * LinkedHashMap} is created such that the least recently used entry is
     * deleted.
     * 
     * @param eldest
     *            the map entry which will be deleted if we return {@code true}.
     */
    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > MAX_NEGATIVE_ENTRIES;
    }

    /**
     * Adds the host name and the corresponding name lookup fail message to the
     * cache.
     * 
     * @param hostName
     *            the name of the host for which the lookup failed.
     * @param failedMessage
     *            the message returned when the lookup fails.
     */
    static synchronized void put(String hostName, String failedMessage) {
        checkCacheExists();
        negCache.put(hostName, new NegCacheElement(failedMessage));
    }

    /**
     * Returns the message of the negative cache if the entry has not yet
     * expired.
     * 
     * @param hostName
     *            the name of the host for which we look up the entry.
     * @return the message which was returned when the host lookup failed if the
     *         entry has not yet expired.
     */
    static synchronized String getFailedMessage(String hostName) {
        checkCacheExists();
        NegCacheElement element = negCache.get(hostName);
        if (element != null) {
            // check if element is still valid
            String ttlValue = AccessController
                    .doPrivileged(new PriviAction<String>(
                            "networkaddress.cache.negative.ttl")); //$NON-NLS-1$
            int ttl = 10;
            try {
                if (ttlValue != null) {
                    ttl = Integer.decode(ttlValue).intValue();
                }
            } catch (NumberFormatException e) {
            }
            if (ttl == 0) {
                negCache.clear();
                element = null;
            } else if (ttl != -1) {
                if (element.timeAdded + (ttl * 1000) < System
                        .currentTimeMillis()) {
                    // remove the element from the cache and return null
                    negCache.remove(hostName);
                    element = null;
                }
            }
        }
        if (element != null) {
            return element.hostName;
        }
        return null;
    }

    /**
     * This method checks if we have created the cache and if not creates it
     */
    static synchronized void checkCacheExists() {
        if (negCache == null) {
            /*
             * Create with the access order set so ordering is based on when the
             * entries were last accessed. We make the default cache size one
             * greater than the maximum number of entries as we will grow to one
             * larger and then delete the LRU entry
             */
            negCache = new NegativeCache<String, NegCacheElement>(
                    MAX_NEGATIVE_ENTRIES + 1, LOADING, true);
        }
    }
}
