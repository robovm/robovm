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

package javax.net.ssl;

import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * The parameters for {@code KeyManager}s. The parameters are a list of
 * {@code KeyStore.Builder}s.
 *
 * @since 1.5
 * @see KeyStore.Builder
 */
public class KeyStoreBuilderParameters implements ManagerFactoryParameters {

    private final List<KeyStore.Builder> ksbuilders;

    /**
     * Creates a new {@code KeyStoreBuilderParameters} with the specified key
     * store builder.
     *
     * @param builder
     *            the key store builder.
     */
    public KeyStoreBuilderParameters(KeyStore.Builder builder) {
        if (builder == null) {
            throw new NullPointerException("builder == null");
        }
        ksbuilders = Collections.singletonList(builder);
    }

    /**
     * Creates a new {@code KeyStoreBuilderParameters} with the specified list
     * of {@code KeyStore.Builder}s.
     *
     * @param parameters
     *            the list of key store builders
     * @throws IllegalArgumentException
     *             if the specified list is empty.
     */
    public KeyStoreBuilderParameters(List<KeyStore.Builder> parameters) {
        if (parameters == null) {
            throw new NullPointerException("parameters == null");
        }
        if (parameters.isEmpty()) {
            throw new IllegalArgumentException("parameters.isEmpty()");
        }
        ksbuilders = Collections.unmodifiableList(new ArrayList<KeyStore.Builder>(parameters));
    }

    /**
     * Returns the unmodifiable list of {@code KeyStore.Builder}s associated
     * with this parameters instance.
     *
     * @return the unmodifiable list of {@code KeyStore.Builder}s.
     */
    public List<KeyStore.Builder> getParameters() {
        return ksbuilders;
    }
}
