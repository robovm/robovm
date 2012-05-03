/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
* @author Alexander Y. Kleymenov
* @version $Revision$
*/

package org.apache.harmony.security.provider.cert;

import java.util.Arrays;

/**
 * The caching mechanism designed to speed up the process
 * of Certificates/CRLs generation in the case of their repeated
 * generation.
 *
 * It keeps correspondences between Objects (Certificates or CLRs)
 * and arrays of bytes on the base of which the Objects have been generated,
 * and provides the means to determine whether it contains the object built on
 * the base of particular encoded form or not. If there are such
 * objects they are returned from the cache, if not - newly generated
 * objects can be saved in the cache.<br>
 *
 * The process of Certificate/CRL generation
 * (implemented in <code>X509CertFactoryImpl</code>) is accompanied with
 * prereading of the beginning of encoded form. This prefix is used to determine
 * whether provided form is PEM encoding or not.<br>
 *
 * So the use of the prefix is the first point to (approximately)
 * determine whether object to be generated is in the cache or not.
 *
 * The failure of the predetermination process tells us that there were not
 * object generated from the encoded form with such prefix and we should
 * generate (decode) the object. If predetermination is successful,
 * we conduct the accurate search on the base of whole encoded form. <br>
 *
 * So to speed up the object generation process this caching mechanism provides
 * the following functionality:<br>
 *
 *      1. With having of the beginning of the encoded form (prefix)
 * it is possible to predetermine whether object has already been
 * generated on the base of the encoding with the SIMILAR prefix or not.
 * This process is not computationally expensive and takes a little time.
 * But it prevents us from use of expensive full encoding
 * search in the case of its failure.<br>
 *
 *      2. If predetermination ends with success, the whole encoding
 * form should be provided to make the final answer: whether object has
 * already been generated on the base of this PARTICULAR encoded form or not.
 * If it is so - the cached object is returned from the cache,
 * if not - new object should be generated and saved in the cache.<br>
 *
 * Note: The length of the prefixes of the encoded forms should not be
 * less than correspondence (default value is 28).
 */
public class Cache {

    // Hash code consist of 6 bytes: AABB00
    // where:
    // AA - 2 bytes for prefix hash
    //      value generated on the base of the prefix of encoding
    // BB - 2 bytes for tail hash
    //      value generated on the base of the tail of encoding
    // 00 - 2 reserved bytes equals to 0
    //
    // Note, that it is possible for 2 different arrays to have
    // the similar hash codes.

    // The masks to work with hash codes:
    // the hash code without the reserved bytes
    private static final long HASH_MASK = 0xFFFFFFFFFFFF0000L;
    // the hash code of the prefix
    private static final long PREFIX_HASH_MASK = 0xFFFFFFFF00000000L;
    // the index value contained in reserved bytes
    private static final int  INDEX_MASK = 0x00FFFF;

    // size of the cache
    private final int cache_size;
    // the number of bytes which will be used for array hash generation.
    private final int prefix_size;

    // The following 3 arrays contain the information about cached objects.
    // This information includes: hash of the array, encoded form of the object,
    // and the object itself.
    // The hash-encoding-object correspondence is made by means of index
    // in the particular array. I.e. for index N hash contained in hashes[N]
    // corresponds to the encoding contained in encodings[N] which corresponds
    // to the object cached at cache[N]

    // array containing the hash codes of encodings
    private final long[] hashes;
    // array containing the encodings of the cached objects
    private final byte[][] encodings;
    // array containing the cached objects
    private final Object[] cache;

    // This array is used to speed up the process of the search in the cache.
    // This is an ordered array of the hash codes from 'hashes' array (described
    // above) with last 2 (reserved) bytes equals to the index of
    // the hash in the 'hashes' array. I.e. hash code ABCD00 with index 10 in
    // the hashes array will be represented in this array as ABCD0A (10==0x0A)
    // So this array contains ordered <hash to index> correspondences.
    // Note, that every item in this array is unique.
    private final long[] hashes_idx;

    // the index of the last cached object
    private int last_cached = 0;
    // cache population indicator
    private boolean cache_is_full = false;

    /**
     * Creates the Cache object.
     * @param pref_size specifies how many leading/trailing bytes of object's
     * encoded form will be used for hash computation
     * @param size capacity of the cache to be created.
     */
    public Cache(int pref_size, int size) {
        cache_size = size;
        prefix_size = pref_size;
        hashes = new long[cache_size];
        hashes_idx = new long[cache_size];
        encodings = new byte[cache_size][];
        cache = new Object[cache_size];
    }

    /**
     * Creates the Cache object of size of 9.
     * @param pref_size specifies how many leading/trailing bytes of object's
     * encoded form will be used for hash computation
     */
    public Cache(int pref_size) {
        this(pref_size, 9);
    }

    /**
     * Creates the Cache object of size of 9.
     */
    public Cache() {
        this(28, 9);
    }

    /**
     * Returns the hash code for the array. This code is used to
     * predetermine whether the object was built on the base of the
     * similar encoding or not (by means of <code>contains(long)</code> method),
     * to exactly determine whether object is contained in the cache or not,
     * and to put the object in the cache.
     * Note: parameter array should be of length not less than
     * specified by <code>prefix_size</code> (default 28)
     * @param arr the byte array containing at least prefix_size leading bytes
     * of the encoding.
     * @return hash code for specified encoding prefix
     */
    public long getHash(byte[] arr) {
        long hash = 0;
        for (int i=1; i<prefix_size; i++) {
            hash += (arr[i] & 0xFF);
        } // it takes about 2 bytes for prefix_size == 28

        // shift to the correct place
        hash = hash << 32;
        return hash;
    }

    /**
     * Checks if there are any object in the cache generated
     * on the base of encoding with prefix corresponding
     * to the specified hash code.
     * @param prefix_hash the hash code for the prefix
     * of the encoding (retrieved by method <code>getHash(byte[]))</code>
     * @return false if there were not any object generated
     * on the base of encoding with specified hash code, true
     * otherwise.
     */
    public boolean contains(long prefix_hash) {
        if (prefix_hash == 0) {
            return false;
        }
        int idx = -1*Arrays.binarySearch(hashes_idx, prefix_hash)-1;
        if (idx == cache_size) {
            return false;
        } else {
            return (hashes_idx[idx] & PREFIX_HASH_MASK) == prefix_hash;
        }
    }

    /**
     * Returns the object built on the base on the specified encoded
     * form if it is contained in the cache and null otherwise.
     * This method is computationally expensive and should be called only if
     * the method <code>contains(long)</code> for the hash code returned true.
     * @param hash the hash code for the prefix of the encoding
     * (retrieved by method <code>getHash(byte[])</code>)
     * @param encoding encoded form of the required object.
     * @return the object corresponding to specified encoding or null if
     * there is no such correspondence.
     */
    public Object get(long hash, byte[] encoding) {
        hash |= getSuffHash(encoding);
        if (hash == 0) {
            return null;
        }
        int idx = -1*Arrays.binarySearch(hashes_idx, hash)-1;
        if (idx == cache_size) {
            return null;
        }
        while ((hashes_idx[idx] & HASH_MASK) == hash) {
            int i = (int) (hashes_idx[idx] & INDEX_MASK) - 1;
            if (Arrays.equals(encoding, encodings[i])) {
                return cache[i];
            }
            idx++;
            if (idx == cache_size) {
                return null;
            }
        }
        return null;
    }

    /**
     * Puts the object into the cache.
     * @param hash hash code for the prefix of the encoding
     * @param encoding the encoded form of the object
     * @param object the object to be saved in the cache
     */
    public void put(long hash, byte[] encoding, Object object) {
        // check for empty space in the cache
        if (last_cached == cache_size) {
            // so cache is full, will erase the first entry in the
            // cache (oldest entry). it could be better to throw out
            // rarely used value instead of oldest one..
            last_cached = 0;
            cache_is_full = true;
        }
        // index pointing to the item of the table to be overwritten
        int index = last_cached++;

        // improve the hash value with info from the tail of encoding
        hash |= getSuffHash(encoding);

        if (cache_is_full) {
            // indexing hash value to be overwritten:
            long idx_hash = (hashes[index] | (index+1));
            int idx = Arrays.binarySearch(hashes_idx, idx_hash);
            if (idx < 0) {
                // it will never happen because we use saved hash value
                // (hashes[index])
                System.out.println("WARNING! "+idx);
                idx = -(idx + 1);
            }
            long new_hash_idx = (hash | (index + 1));
            int new_idx = Arrays.binarySearch(hashes_idx, new_hash_idx);
            if (new_idx >= 0) {
                // it's possible when we write the same hash in the same cell
                if (idx != new_idx) {
                    // it will never happen because we use the same
                    // hash and the same index in hash table
                    System.out.println("WARNING: ");
                    System.out.println(">> idx: "+idx+" new_idx: "+new_idx);
                }
            } else {
                new_idx = -(new_idx + 1);
                // replace in sorted array
                if (new_idx > idx) {
                    System.arraycopy(hashes_idx, idx+1, hashes_idx, idx,
                            new_idx - idx - 1);
                    hashes_idx[new_idx-1] = new_hash_idx;
                } else if (idx > new_idx) {
                    System.arraycopy(hashes_idx, new_idx, hashes_idx, new_idx+1,
                            idx - new_idx);
                    hashes_idx[new_idx] = new_hash_idx;
                } else { // idx == new_idx
                    hashes_idx[new_idx] = new_hash_idx;
                }
            }
        } else {
            long idx_hash = (hash | (index + 1));
            int idx = Arrays.binarySearch(hashes_idx, idx_hash);
            if (idx < 0) {
                // it will always be true because idx_hash depends on index
                idx = -(idx + 1);
            }
            idx = idx - 1;
            if (idx != cache_size - index - 1) {
                // if not in the cell containing 0 (free cell), do copy:
                System.arraycopy(hashes_idx, cache_size - index,
                        hashes_idx, cache_size - index - 1,
                        idx - (cache_size - index) + 1);
            }
            hashes_idx[idx] = idx_hash;
        }
        // overwrite the values in the tables:
        hashes[index] = hash;
        encodings[index] = encoding;
        cache[index] = object;
    }

    // Returns the hash code built on the base of the tail of the encoded form
    // @param arr - the array containing at least prefix_size trailing bytes
    // of encoded form
    private long getSuffHash(byte[] arr) {
        long hash_addon = 0;
        for (int i=arr.length-1; i>arr.length - prefix_size; i--) {
            hash_addon += (arr[i] & 0xFF);
        }
        return hash_addon << 16;
    }

}
