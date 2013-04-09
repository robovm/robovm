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
package libcore.java.security;

import dalvik.annotation.BrokenTest;
import java.security.AlgorithmParameterGenerator;
import java.security.AlgorithmParameters;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.spec.DHParameterSpec;
import junit.framework.TestCase;

public class OldDHTest extends TestCase {

    @BrokenTest("Suffers from DH slowness, disabling for now")
    public void testDHGen() throws Exception {
        KeyPairGenerator gen = null;
        try {
            gen = KeyPairGenerator.getInstance("DH");
        } catch (NoSuchAlgorithmException e) {
            fail(e.getMessage());
        }

        AlgorithmParameterGenerator algorithmparametergenerator = AlgorithmParameterGenerator.getInstance("DH");
        algorithmparametergenerator.init(1024, new SecureRandom());
        AlgorithmParameters algorithmparameters = algorithmparametergenerator.generateParameters();
        DHParameterSpec dhparameterspec = algorithmparameters.getParameterSpec(DHParameterSpec.class);


        //gen.initialize(1024);
        gen.initialize(dhparameterspec);
        KeyPair key = gen.generateKeyPair();
    }
}
