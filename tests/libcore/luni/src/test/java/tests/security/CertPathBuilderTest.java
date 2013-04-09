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
import java.security.cert.CertPathBuilder;
import java.security.cert.CertPathBuilderResult;
import java.security.cert.CertPathParameters;
import junit.framework.TestCase;
public abstract class CertPathBuilderTest extends TestCase {

    private final String algorithmName;
    private CertPathParameters params;

    public CertPathBuilderTest(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        params = getCertPathParameters();
    }

    public abstract CertPathParameters getCertPathParameters() throws Exception;
    public abstract void validateCertPath(CertPath path);

    public void testCertPathBuilder() throws Exception {
        CertPathBuilder pathBuilder = CertPathBuilder.getInstance(
                algorithmName);

        CertPathBuilderResult builderResult = pathBuilder.build(params);

        CertPath path = builderResult.getCertPath();

        assertNotNull("built path is null", path);

        validateCertPath(path);
    }
}
