/*
 * Copyright (C) 2009 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package libcore.javax.crypto.spec;

import java.security.KeyPair;
import javax.crypto.spec.DHPrivateKeySpec;
import javax.crypto.spec.DHPublicKeySpec;
import tests.security.KeyAgreementHelper;
import tests.security.KeyFactoryTest;

public class KeyFactoryTestDH extends KeyFactoryTest<DHPublicKeySpec, DHPrivateKeySpec> {

    public KeyFactoryTestDH() {
        super("DH", DHPublicKeySpec.class, DHPrivateKeySpec.class);
    }

    @Override protected void check(KeyPair keyPair) {
        new KeyAgreementHelper("DH").test(keyPair);
    }
}
