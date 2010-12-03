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
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * {@code CacheResponse} is used for getting resource data from the installed
 * {@code ResponseCache}. A {@code CacheResponse} object provides an {@code
 * InputStream} to access the response body and also a method {@code
 * getHeaders()} to fetch the response headers.
 * 
 * @see ResponseCache
 */
public abstract class CacheResponse {
    /**
     * This implementation does nothing.
     */
    public CacheResponse() {
        super();
    }

    /**
     * Returns an {@code InputStream} to access the response body.
     * 
     * @return an {@code InputStream} which can be used to fetch the response
     *         body.
     * @throws IOException
     *             if an I/O error is encountered while retrieving the response
     *             body.
     */
    public abstract InputStream getBody() throws IOException;

    /**
     * Returns an immutable {@code Map} which contains the response headers
     * information.
     * 
     * @return an immutable {@code Map} which contains the response headers. The
     *         generic map contains response header fields as the key and a list
     *         of strings as values.
     * @throws IOException
     *             if an I/O error is encountered while retrieving the response
     *             headers.
     */
    public abstract Map<String, List<String>> getHeaders() throws IOException;
}
