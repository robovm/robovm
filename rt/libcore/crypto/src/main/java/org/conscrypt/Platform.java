/*
 * Copyright 2013 The Android Open Source Project
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

package org.conscrypt;

import org.apache.harmony.security.utils.AlgNameMapper;
import org.apache.harmony.security.utils.AlgNameMapperSource;

class Platform {
    private static class NoPreloadHolder {
        public static final Platform MAPPER = new Platform();
    }

    /**
     * Runs all the setup for the platform that only needs to run once.
     */
    public static void setup() {
        NoPreloadHolder.MAPPER.ping();
    }

    /**
     * Just a placeholder to make sure the class is initialized.
     */
    private void ping() {
    }

    private Platform() {
        AlgNameMapper.setSource(new OpenSSLMapper());
    }

    private static class OpenSSLMapper implements AlgNameMapperSource {
        @Override
        public String mapNameToOid(String algName) {
            return NativeCrypto.OBJ_txt2nid_oid(algName);
        }

        @Override
        public String mapOidToName(String oid) {
            return NativeCrypto.OBJ_txt2nid_longName(oid);
        }
    }
}