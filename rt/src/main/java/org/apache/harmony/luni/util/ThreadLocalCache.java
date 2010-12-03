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

package org.apache.harmony.luni.util;

import java.lang.ref.SoftReference;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;

/**
 * The class extends the functionality of {@link java.lang.ThreadLocal} with
 * possibility of discarding the thread local storage content when a heap is
 * exhausted.
 */
public class ThreadLocalCache<T> {

    private SoftReference<ThreadLocal<T>> storage = new SoftReference<ThreadLocal<T>>(
            null);

    private ThreadLocal<T> getThreadLocal() {
        ThreadLocal<T> tls = storage.get();
        if (tls == null) {
            tls = new ThreadLocal<T>() {
                public T initialValue() {
                    return ThreadLocalCache.this.initialValue();
                }
            };
            storage = new SoftReference<ThreadLocal<T>>(tls);
        }
        return tls;
    }

    /**
     * Returns the initial value for the cache for the current thread.
     */
    protected T initialValue() {
        return null;
    }

    /**
     * Returns the thread local value of this object.
     */
    public T get() {
        return getThreadLocal().get();
    }

    /**
     * Sets the value of this variable for the current thread. Might be useful
     * for expanding the thread local cache.
     */
    public void set(T value) {
        getThreadLocal().set(value);
    }

    /**
     * Discards the cache for all threads.
     */
    public void remove() {
        storage.clear();
    }

    public static ThreadLocalCache<CharsetDecoder> utf8Decoder = new ThreadLocalCache<CharsetDecoder>() {
        protected CharsetDecoder initialValue() {
            return Charset.forName("UTF-8").newDecoder();
        }
    };

    public static ThreadLocalCache<CharsetEncoder> utf8Encoder = new ThreadLocalCache<CharsetEncoder>() {
        protected CharsetEncoder initialValue() {
            return Charset.forName("UTF-8").newEncoder();
        }
    };

    public static ThreadLocalCache<java.nio.ByteBuffer> byteBuffer = new ThreadLocalCache<java.nio.ByteBuffer>() {
        protected java.nio.ByteBuffer initialValue() {
            return java.nio.ByteBuffer.allocate(72); // >=
            // Manifest.LINE_LENGTH_LIMIT
        }
    };

    public static ThreadLocalCache<CharBuffer> charBuffer = new ThreadLocalCache<CharBuffer>() {
        protected CharBuffer initialValue() {
            return CharBuffer.allocate(72); // no specific requirement
        }
    };

}
