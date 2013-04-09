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

import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.spec.SecretKeySpec;
import javax.crypto.MacSpi;

import org.apache.harmony.crypto.tests.support.MyMacSpi;

import junit.framework.TestCase;


/**
 * Tests for <code>MacSpi</code> class constructors and methods.
 *
 */
public class MacSpiTest extends TestCase {
class Mock_MacSpi extends MyMacSpi {

    @Override
    protected byte[] engineDoFinal() {
        return super.engineDoFinal();
    }

    @Override
    protected int engineGetMacLength() {
        return super.engineGetMacLength();
    }

    @Override
    protected void engineInit(Key key, AlgorithmParameterSpec params) throws InvalidKeyException, InvalidAlgorithmParameterException {
        super.engineInit(key, params);
    }

    @Override
    protected void engineReset() {
        super.engineReset();
    }

    @Override
    protected void engineUpdate(byte input) {
        super.engineUpdate(input);
    }

    @Override
    protected void engineUpdate(byte[] input, int offset, int len) {
        super.engineUpdate(input, offset, len);
    }

}

class Mock_MacSpi1 extends MyMacSpi1 {

    @Override
    protected byte[] engineDoFinal() {
        return super.engineDoFinal();
    }

    @Override
    protected int engineGetMacLength() {
        return super.engineGetMacLength();
    }

    @Override
    protected void engineInit(Key key, AlgorithmParameterSpec params) throws InvalidKeyException, InvalidAlgorithmParameterException {
        super.engineInit(key, params);
    }

    @Override
    protected void engineReset() {
        super.engineReset();
    }

    @Override
    protected void engineUpdate(byte input) {
        super.engineUpdate(input);
    }

    @Override
    protected void engineUpdate(byte[] input, int offset, int len) {
        super.engineUpdate(input, offset, len);
    }

    protected void engineUpdate(ByteBuffer input) {
        super.engineUpdate(input);
    }

}


class Mock_MacSpi2 extends MyMacSpi2 {

    @Override
    protected byte[] engineDoFinal() {
        return super.engineDoFinal();
    }

    @Override
    protected int engineGetMacLength() {
        return super.engineGetMacLength();
    }

    @Override
    protected void engineInit(Key key, AlgorithmParameterSpec params) throws InvalidKeyException, InvalidAlgorithmParameterException {
        super.engineInit(key, params);
    }

    @Override
    protected void engineReset() {
        super.engineReset();
    }

    @Override
    protected void engineUpdate(byte input) {
        super.engineUpdate(input);
    }

    @Override
    protected void engineUpdate(byte[] input, int offset, int len) {
        super.engineUpdate(input, offset, len);
    }

    protected void engineUpdate(ByteBuffer input) {
        super.engineUpdate(input);
    }

}


    /**
     * Test for <code>MacSpi</code> constructor
     * Assertion: constructs MacSpi
     */
    public void testMacSpiTests01() throws Exception {
        Mock_MacSpi mSpi = new Mock_MacSpi();

        byte [] bb1 = {(byte)1, (byte)2, (byte)3, (byte)4, (byte)5};
        SecretKeySpec sks = new SecretKeySpec(bb1, "SHA1");

        assertEquals("Incorrect MacLength", mSpi.engineGetMacLength(), 0);

        try {
            mSpi.engineInit(null, null);
            fail("IllegalArgumentException must be thrown");
        } catch (IllegalArgumentException e) {
        }

        mSpi.engineInit(sks, null);

        byte[] bb = mSpi.engineDoFinal();
        assertEquals(bb.length, 0);
        try {
            mSpi.clone();
            fail("CloneNotSupportedException was not thrown as expected");
        } catch (CloneNotSupportedException e) {
        }

        Mock_MacSpi1 mSpi1 = new Mock_MacSpi1();
        mSpi1.clone();

        byte [] bbb = new byte[10];
        for (int i = 0; i < bbb.length; i++) {
            bbb[i] = (byte)i;
        }
        try {
            mSpi1.engineInit(null, null);
            fail("IllegalArgumentException must be thrown");
        } catch (IllegalArgumentException e) {
        }
        mSpi1.engineInit(sks, null);

        ByteBuffer byteBuf = ByteBuffer.allocate(10);
        byteBuf.put(bbb);
        byteBuf.position(5);
        int beforeUp = byteBuf.remaining();
        mSpi1.engineUpdate(byteBuf);
        bb = mSpi1.engineDoFinal();
        assertEquals("Incorrect result of engineDoFinal", bb.length, beforeUp);

        Mock_MacSpi2 mSpi2 = new Mock_MacSpi2();

        mSpi2.engineInit(null, null);
        mSpi2.engineInit(sks, null);

        try {
            mSpi2.clone();
        } catch (CloneNotSupportedException e) {
        }

        byte [] bbuf = {(byte)5, (byte)4, (byte)3, (byte)2, (byte)1};
        byteBuf = ByteBuffer.allocate(5);
        byteBuf.put(bbuf);
        byteBuf.position(5);
        if (!byteBuf.hasRemaining()) {
            mSpi2.engineUpdate(byteBuf);
        }
    }
}


class MyMacSpi1 extends MyMacSpi {
    public Object clone() throws CloneNotSupportedException {
        return new MyMacSpi1();
    }
}

class MyMacSpi2 extends MacSpi {
    protected int engineGetMacLength() {
        return 0;
    }

    protected void engineInit(Key key, AlgorithmParameterSpec params)
            throws InvalidKeyException, InvalidAlgorithmParameterException {
    }

    protected void engineUpdate(byte input) {
    }

    protected void engineUpdate(byte[] input, int offset, int len) {
    }

    protected byte[] engineDoFinal() {
        return new byte[0];
    }

    protected void engineReset() {
    }
}
