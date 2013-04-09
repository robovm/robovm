/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
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

package libcore.java.security;

import java.security.AlgorithmParameterGenerator;
import java.security.SecureRandom;

public class OldAlgorithmParameterGeneratorTest extends junit.framework.TestCase {

    public void test_initI() throws Exception {
        // Test for method void
        // java.security.AlgorithmParameterGenerator.init(int)
            // checks that no exception is thrown
        int[] valid = {512, 576, 640, 960, 1024};
        AlgorithmParameterGenerator gen = AlgorithmParameterGenerator
                .getInstance("DSA");

        for (int i = 0; i < valid.length; i++) {
            try {
                gen.init(valid[i]);
            } catch (Exception e) {
                fail("Exception should not be thrown for valid parameter" + valid[i]);
            }
        }
    }

    public void test_initILjava_security_SecureRandom() throws Exception {
        // Test for method void
        // java.security.AlgorithmParameterGenerator.init(int,
        // java.security.SecureRandom)
            // checks that no exception is thrown
        int[] valid = {512, 576, 640, 960, 1024};
        AlgorithmParameterGenerator gen = AlgorithmParameterGenerator
                .getInstance("DSA");

        for (int i = 0; i < valid.length; i++) {
            try {
                gen.init(valid[i], new SecureRandom());
                gen.init(valid[i], null);
            } catch (Exception e) {
                fail("Exception should not be thrown for valid parameter" + valid[i]);
            }
        }
    }
}
