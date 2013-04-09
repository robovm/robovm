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

import java.security.MessageDigestSpi;

/**
 * Tests implementation of MessageDigest
 *
 */
public class MyMessageDigest2 extends MessageDigestSpi {

    public static boolean runEngineReset = false;
    public static boolean runEngineDigest = false;
    public static boolean runEngineUpdate1 = false;
    public static boolean runEngineUpdate2 = false;

    /**
     *
     */
    public void engineReset() {
        runEngineReset = true;
    }

    /**
     *
     */
    public byte[] engineDigest() {
        runEngineDigest = true;
        return new byte[0];
    }

    /**
     *
     */
    public void engineUpdate(byte arg0) {
        runEngineUpdate1 = true;
    }

    /**
     *
     */
    public void engineUpdate(byte[] arg0, int arg1, int arg2) {
        runEngineUpdate2 = true;
    }

    /**
     * The implementation is not cloneable
     */
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException();
    }
}
