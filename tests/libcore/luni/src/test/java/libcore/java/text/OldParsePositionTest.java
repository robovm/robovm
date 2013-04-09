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
package libcore.java.text;

import java.text.ParsePosition;

public class OldParsePositionTest extends junit.framework.TestCase {

    ParsePosition pp = new ParsePosition(Integer.MAX_VALUE);

    public void test_hashCode() {
        // Test for method int java.text.ParsePosition.hashCode()
        ParsePosition pp1 = new ParsePosition(0);
        ParsePosition pp2 = new ParsePosition(0);
        assertTrue("hashCode returns non equal hash codes for equal objects.",
                pp1.hashCode() == pp2.hashCode());
        pp1.setIndex(Integer.MAX_VALUE);
        assertTrue("hashCode returns equal hash codes for non equal objects.",
                pp1.hashCode() != pp2.hashCode());
    }

    public void test_getErrorIndex() {
        // Test for method int java.text.ParsePosition.getErrorIndex()
        pp.setErrorIndex(56);
        assertEquals("getErrorIndex failed.", 56, pp.getErrorIndex());
        pp.setErrorIndex(Integer.MAX_VALUE);
        assertEquals("getErrorIndex failed.", Integer.MAX_VALUE,
                pp.getErrorIndex());
        assertEquals("getErrorIndex failed.", Integer.MAX_VALUE,
                pp.getErrorIndex());
    }
}
