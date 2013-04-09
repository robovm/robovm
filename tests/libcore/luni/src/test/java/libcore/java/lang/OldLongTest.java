/* Licensed to the Apache Software Foundation (ASF) under one or more
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

package libcore.java.lang;

import java.util.Properties;
import junit.framework.TestCase;

public class OldLongTest extends TestCase {
    private Properties orgProps;

    @Override
    protected void setUp() {
        orgProps = System.getProperties();
    }

    @Override
    protected void tearDown() {
        System.setProperties(orgProps);
    }

    public void test_getLongLjava_lang_String() {
        Properties tProps = new Properties();
        tProps.put("testLong", "99");
        tProps.put("testIncLong", "string");
        System.setProperties(tProps);
        assertNull("returned incorrect default Long",
                Long.getLong("testIncLong"));
    }

    public void test_getLongLjava_lang_StringJ() {
        // Test for method java.lang.Long
        // java.lang.Long.getLong(java.lang.String, long)
        Properties tProps = new Properties();
        tProps.put("testIncLong", "string");
        System.setProperties(tProps);
        assertTrue("returned incorrect default Long", Long.getLong("testIncLong", 4L)
                .equals(new Long(4)));
    }

    public void test_getLongLjava_lang_StringLjava_lang_Long() {
        // Test for method java.lang.Long
        // java.lang.Long.getLong(java.lang.String, java.lang.Long)
        Properties tProps = new Properties();
        tProps.put("testIncLong", "string");
        System.setProperties(tProps);
        assertTrue("returned incorrect default Long", Long.getLong("testIncLong",
                new Long(4)).equals(new Long(4)));
    }

    public void test_floatValue() {
        assertEquals(Long.MAX_VALUE, new Long(Long.MAX_VALUE).floatValue(), 0F);
        assertEquals(Long.MIN_VALUE, new Long(Long.MIN_VALUE).floatValue(), 0F);
    }

    public void test_intValue() {
        assertEquals(-1, new Long(Long.MAX_VALUE).intValue());
        assertEquals(0, new Long(Long.MIN_VALUE).intValue());
    }

    public void test_longValue() {
        assertEquals(Long.MAX_VALUE, new Long(Long.MAX_VALUE).longValue());
        assertEquals(Long.MIN_VALUE, new Long(Long.MIN_VALUE).longValue());
    }

    public void test_shortValue() {
        assertEquals(-1, new Long(Long.MAX_VALUE).shortValue());
        assertEquals(0, new Long(Long.MIN_VALUE).shortValue());
    }
}
