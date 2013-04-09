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

/**
* @author Vera Y. Petrashkova
* @version $Revision$
*/

package org.apache.harmony.crypto.tests.javax.crypto;

import java.math.BigInteger;
import java.security.AlgorithmParameters;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.RSAKeyGenParameterSpec;

import javax.crypto.ExemptionMechanismException;
import javax.crypto.ShortBufferException;
import javax.crypto.ExemptionMechanismSpi;
import org.apache.harmony.crypto.tests.support.MyExemptionMechanismSpi;

import junit.framework.TestCase;


/**
 * Tests for <code>ExemptionMechanismSpi</code> class constructors and
 * methods.
 *
 */
public class ExemptionMechanismSpiTest extends TestCase {
class Mock_ExemptionMechanismSpi extends MyExemptionMechanismSpi{

    @Override
    protected byte[] engineGenExemptionBlob() throws ExemptionMechanismException {
        return super.engineGenExemptionBlob();
    }

    @Override
    protected int engineGenExemptionBlob(byte[] output, int outputOffset) throws ShortBufferException, ExemptionMechanismException {
        return super.engineGenExemptionBlob(output, outputOffset);
    }

    @Override
    protected int engineGetOutputSize(int inputLen) {
        return super.engineGetOutputSize(inputLen);
    }

    @Override
    protected void engineInit(Key key) throws InvalidKeyException, ExemptionMechanismException {
        super.engineInit(key);

    }

    @Override
    protected void engineInit(Key key, AlgorithmParameterSpec params) throws InvalidKeyException, InvalidAlgorithmParameterException, ExemptionMechanismException {
        super.engineInit(key, params);

    }

    @Override
    protected void engineInit(Key key, AlgorithmParameters params) throws InvalidKeyException, InvalidAlgorithmParameterException, ExemptionMechanismException {
        super.engineInit(key, params);

    }

}

    /**
     * Test for <code>ExemptionMechanismSpi</code> constructor Assertion:
     * constructs ExemptionMechanismSpi
     * @throws Exception
     */
    public void testExemptionMechanismSpi01() throws Exception {
        Mock_ExemptionMechanismSpi emSpi = new Mock_ExemptionMechanismSpi(){};
        int len = MyExemptionMechanismSpi.getLength();
        byte [] bbRes = emSpi.engineGenExemptionBlob();
        assertEquals("Incorrect length", bbRes.length, len);
        assertEquals("Incorrect result",
                emSpi.engineGenExemptionBlob(new byte[1], len), len);
        assertEquals("Incorrect output size", 10, emSpi.engineGetOutputSize(100));
        Key key = null;
        AlgorithmParameters params = null;
        AlgorithmParameterSpec parSpec = null;
        try {
            emSpi.engineInit(key);
            fail("InvalidKeyException must be thrown");
        } catch (InvalidKeyException e) {
        }
        try {
            emSpi.engineInit(key, params);
            fail("InvalidKeyException must be thrown");
        } catch (InvalidKeyException e) {
        }
        try {
            emSpi.engineInit(key, parSpec);
            fail("InvalidKeyException must be thrown");
        } catch (InvalidKeyException e) {
        }
        key = ((MyExemptionMechanismSpi)emSpi).new tmp1Key("Proba", new byte[0]);
        try {
            emSpi.engineInit(key);
            fail("ExemptionMechanismException must be thrown");
        } catch (ExemptionMechanismException e) {
        }
        try {
            emSpi.engineInit(key, params);
            fail("ExemptionMechanismException must be thrown");
        } catch (ExemptionMechanismException e) {
        }
        try {
            emSpi.engineInit(key, parSpec);
            fail("ExemptionMechanismException must be thrown");
        } catch (ExemptionMechanismException e) {
        }
        key = ((MyExemptionMechanismSpi)emSpi).new tmpKey("Proba", new byte[0]);
        emSpi.engineInit(key);
        emSpi.engineInit(key, AlgorithmParameters.getInstance("DH"));
        emSpi.engineInit(key, new RSAKeyGenParameterSpec(10, new BigInteger ("10")));

        assertEquals("Incorrect result", 10, emSpi.engineGetOutputSize(100));
    }
}
