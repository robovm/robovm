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

package libcore.java.lang;

import junit.framework.TestCase;

public class OldRuntimeExceptionTest extends TestCase {

    public void test_ConstructorLStringLThrowable() {
        String message = "Test message";
        NullPointerException npe = new NullPointerException();
        RuntimeException re = new RuntimeException(message, npe);
        assertEquals(message, re.getMessage());
        assertEquals(npe, re.getCause());

        re = new RuntimeException(null, npe);
        assertNull(re.getMessage());

        re = new RuntimeException(message, null);
        assertNull(re.getCause());
    }

    public void test_ConstructorLThrowable() {
        NullPointerException npe = new NullPointerException();
        RuntimeException re = new RuntimeException(npe);
        assertEquals(npe, re.getCause());

        re = new RuntimeException((Throwable) null);
        assertNull(re.getCause());
    }
}
