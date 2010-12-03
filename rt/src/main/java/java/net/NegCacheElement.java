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

/**
 * This class is used to hold information about failed host name lookups.
 *
 * @see NegativeCache
 */
class NegCacheElement {

    // we need the time to figure out when the entry is stale
    final long timeAdded = System.currentTimeMillis();

    // holds the name of the host for which the lookup failed
    final String hostName;

    /**
     * Constructor used to set the hostname for the entry for which the lookup
     * failed.
     * 
     * @param hostName
     *            name of the host for which the lookup failed.
     */
    NegCacheElement(String hostName) {
        this.hostName = hostName;
    }
}
