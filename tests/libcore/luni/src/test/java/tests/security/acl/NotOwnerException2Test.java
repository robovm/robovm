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

package tests.security.acl;

import junit.framework.TestCase;

import java.security.acl.NotOwnerException;

public class NotOwnerException2Test extends TestCase {

    /**
     * java.security.acl.NotOwnerException#NotOwnerException()
     */
    public void test_Constructor() {
        // Test for method java.security.acl.NotOwnerException()
        try {
            throw new NotOwnerException();
        } catch (NotOwnerException e) {
            assertEquals("NotOwnerException.toString() should have been "
                    + "'java.security.acl.NotOwnerException' but was "
                    + e.toString(), "java.security.acl.NotOwnerException", e
                    .toString());
        }
    }
}
