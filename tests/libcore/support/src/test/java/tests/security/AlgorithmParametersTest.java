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
package tests.security;

import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.InvalidParameterSpecException;
import junit.framework.TestCase;

public abstract class AlgorithmParametersTest extends TestCase {

    private final String algorithmName;
    private final TestHelper<AlgorithmParameters> helper;
    private final AlgorithmParameterSpec parameterData;

    public AlgorithmParametersTest(String algorithmName,
            TestHelper<AlgorithmParameters> helper, AlgorithmParameterSpec parameterData) {
        this.algorithmName = algorithmName;
        this.helper = helper;
        this.parameterData = parameterData;
    }

    public void testAlgorithmParameters() {
        AlgorithmParameters algorithmParameters = null;
        try {
            algorithmParameters = AlgorithmParameters
                    .getInstance(algorithmName);
        } catch (NoSuchAlgorithmException e) {
            fail(e.getMessage());
        }

        try {
            algorithmParameters.init(parameterData);
        } catch (InvalidParameterSpecException e) {
            fail(e.getMessage());
        }

        helper.test(algorithmParameters);
    }
}
