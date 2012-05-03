/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.net;

import libcore.util.BasicLruCache;

/**
 * Implements caching for {@code InetAddress}. We use a unified cache for both positive and negative
 * cache entries.
 */
class AddressCache {
    /**
     * When the cache contains more entries than this, we start dropping the oldest ones.
     * This should be a power of two to avoid wasted space in our custom map.
     */
    private static final int MAX_ENTRIES = 512;

    // Default time-to-live for positive cache entries. 600 seconds (10 minutes).
    private static final long DEFAULT_POSITIVE_TTL_NANOS = 600 * 1000000000L;
    // Default time-to-live for negative cache entries. 10 seconds.
    private static final long DEFAULT_NEGATIVE_TTL_NANOS = 10 * 1000000000L;

    // The actual cache.
    private final BasicLruCache<String, AddressCacheEntry> cache
            = new BasicLruCache<String, AddressCacheEntry>(MAX_ENTRIES);

    static class AddressCacheEntry {
        // Either an InetAddress[] for a positive entry,
        // or a String detail message for a negative entry.
        final Object value;

        /**
         * The absolute expiry time in nanoseconds. Nanoseconds from System.nanoTime is ideal
         * because -- unlike System.currentTimeMillis -- it can never go backwards.
         *
         * Unless we need to cope with DNS TTLs of 292 years, we don't need to worry about overflow.
         */
        final long expiryNanos;

        AddressCacheEntry(Object value, long expiryNanos) {
            this.value = value;
            this.expiryNanos = expiryNanos;
        }
    }

    /**
     * Removes all entries from the cache.
     */
    public void clear() {
        cache.evictAll();
    }

    /**
     * Returns the cached InetAddress[] associated with 'hostname'. Returns null if nothing is known
     * about 'hostname'. Returns a String suitable for use as an UnknownHostException detail
     * message if 'hostname' is known not to exist.
     */
    public Object get(String hostname) {
        AddressCacheEntry entry = cache.get(hostname);
        // Do we have a valid cache entry?
        if (entry != null && entry.expiryNanos >= System.nanoTime()) {
            return entry.value;
        }
        // Either we didn't find anything, or it had expired.
        // No need to remove expired entries: the caller will provide a replacement shortly.
        return null;
    }

    /**
     * Associates the given 'addresses' with 'hostname'. The association will expire after a
     * certain length of time.
     */
    public void put(String hostname, InetAddress[] addresses) {
        put(hostname, addresses, true);
    }

    /**
     * Associates the given 'detailMessage' with 'hostname'. The association will expire after a
     * certain length of time.
     */
    public void put(String hostname, String detailMessage) {
        put(hostname, detailMessage, false);
    }

    /**
     * Associates the given 'addresses' with 'hostname'. The association will expire after a
     * certain length of time.
     */
    public void put(String hostname, Object value, boolean isPositive) {
        // Calculate the expiry time.
        String propertyName = isPositive ? "networkaddress.cache.ttl" : "networkaddress.cache.negative.ttl";
        long defaultTtlNanos = isPositive ? DEFAULT_POSITIVE_TTL_NANOS : DEFAULT_NEGATIVE_TTL_NANOS;
        long expiryNanos = System.nanoTime() + defaultTtlNanos;
        // Update the cache.
        cache.put(hostname, new AddressCacheEntry(value, expiryNanos));
    }

    /**
     * Records that 'hostname' is known not to have any associated addresses. (I.e. insert a
     * negative cache entry.)
     */
    public void putUnknownHost(String hostname, String detailMessage) {
        put(hostname, detailMessage);
    }

    private long customTtl(String propertyName, long defaultTtlNanos) {
        String ttlString = System.getProperty(propertyName, null);
        if (ttlString == null) {
            return System.nanoTime() + defaultTtlNanos;
        }
        try {
            long ttlS = Long.parseLong(ttlString);
            // For the system properties, -1 means "cache forever" and 0 means "don't cache".
            if (ttlS == -1) {
                return Long.MAX_VALUE;
            } else if (ttlS == 0) {
                return Long.MIN_VALUE;
            } else {
                return System.nanoTime() + ttlS * 1000000000L;
            }
        } catch (NumberFormatException ex) {
            return System.nanoTime() + defaultTtlNanos;
        }
    }
}
