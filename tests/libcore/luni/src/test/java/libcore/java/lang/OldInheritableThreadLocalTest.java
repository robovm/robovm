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

public class OldInheritableThreadLocalTest extends TestCase {

    public void test_Ljava_lang_InheritableThreadLocal()
            throws InterruptedException {
        final Object value = new Object();
        final Object inheritedValue = new Object();
        final ThreadLocal<Object> threadLocal
                = new InheritableThreadLocal<Object>() {
            @Override
            protected Object childValue(Object parentValue) {
                assertSame(value, parentValue);
                return inheritedValue;
            }
        };
        threadLocal.set(value);
        final Object[] holder = new Object[1];
        Thread thread = new Thread() {
            public void run() {
                holder[0] = threadLocal.get();
            }
        };
        thread.start();
        thread.join();
        assertSame(value, threadLocal.get());
        assertSame(inheritedValue, holder[0]);

        // Cleanup properly, so other tests are not affected.
        threadLocal.remove();
    }

    public void test_childValue() {
        InheritableThreadLocal<String> itl = new InheritableThreadLocal<String>() {
            @Override
            protected String initialValue() {
                return "initial";
            }
            @Override
            protected String childValue(String parentValue) {
                return "childValue";
            }
        };
        assertEquals("initial", itl.get());
    }

}
