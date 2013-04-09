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
* @author Boris V. Kuznetsov
* @version $Revision$
*/

package org.apache.harmony.security.tests.support;

import java.security.SecureRandomSpi;

/**
 * Test implementation of SecureRandom
 *
 */
public class RandomImpl extends SecureRandomSpi {

    public static boolean runEngineGenerateSeed = false;
    public static boolean runEngineNextBytes = false;
    public static boolean runEngineSetSeed = false;

    protected void engineSetSeed(byte[] seed) {
        runEngineSetSeed = true;
    }

    protected void engineNextBytes(byte[] bytes) {
        runEngineNextBytes = true;
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte)(i + 0xF1);
        }
    }

    protected byte[] engineGenerateSeed(int numBytes) {
        runEngineGenerateSeed = true;
        byte[] b = new byte[numBytes];
        for (int i = 0; i < b.length; i++) {
            b[i] = (byte)i;
        }
        return b;
    }

}

