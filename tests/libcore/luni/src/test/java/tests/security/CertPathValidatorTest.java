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

import java.security.cert.CertPath;
import java.security.cert.CertPathParameters;
import java.security.cert.CertPathValidator;
import java.security.cert.CertPathValidatorResult;
import junit.framework.TestCase;

public abstract class CertPathValidatorTest extends TestCase {

    private final String algorithmName;


    public CertPathValidatorTest(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public abstract CertPathParameters getParams();
    public abstract CertPath getCertPath();
    public abstract void validateResult(CertPathValidatorResult validatorResult);

    public void testCertPathValidator() throws Exception {
        CertPathValidator certPathValidator = CertPathValidator.getInstance(
                algorithmName);

        CertPathValidatorResult validatorResult = certPathValidator.validate(
                getCertPath(), getParams());

        validateResult(validatorResult);
    }


}
