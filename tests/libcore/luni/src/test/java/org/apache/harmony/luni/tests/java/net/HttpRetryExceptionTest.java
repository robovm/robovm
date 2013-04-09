/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package org.apache.harmony.luni.tests.java.net;

import java.io.Serializable;
import java.net.HttpRetryException;

import junit.framework.TestCase;

import org.apache.harmony.testframework.serialization.SerializationTest;
import org.apache.harmony.testframework.serialization.SerializationTest.SerializableAssert;

public class HttpRetryExceptionTest extends TestCase {

    private static final String LOCATION = "Http test";

    private static final String DETAIL = "detail";

    // comparator for HttpRetryException objects
    private static final SerializableAssert comparator = new SerializableAssert() {
        public void assertDeserialized(Serializable reference, Serializable test) {

            HttpRetryException ref = (HttpRetryException) reference;
            HttpRetryException tst = (HttpRetryException) test;

            assertEquals("getLocation", ref.getLocation(), tst.getLocation());
            assertEquals("responseCode", ref.responseCode(), tst.responseCode());
            assertEquals("getReason", ref.getReason(), tst.getReason());
            assertEquals("getMessage", ref.getMessage(), tst.getMessage());
        }
    };

    /**
     * serialization/deserialization.
     */
    public void testSerializationSelf() throws Exception {
        SerializationTest.verifySelf(new HttpRetryException(DETAIL, 100,
                LOCATION), comparator);
    }

    /**
     * serialization/deserialization compatibility with RI.
     */
    public void testSerializationCompatibility() throws Exception {
        SerializationTest.verifyGolden(this, new HttpRetryException(DETAIL,
                100, LOCATION), comparator);
    }
}
