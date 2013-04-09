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

import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import junit.framework.TestCase;

public class OldAttributedCharacterIteratorTest extends TestCase {

    AttributedCharacterIterator it;
    String string = "test test";

    public void test_getRunLimitLSet() {
        AttributedString as = new AttributedString("test");
        as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE, "a", 2,
                3);
        AttributedCharacterIterator it = as.getIterator();
        HashSet<AttributedCharacterIterator.Attribute>  attr =
            new HashSet<AttributedCharacterIterator.Attribute>();
        attr.add(AttributedCharacterIterator.Attribute.LANGUAGE);
        assertEquals("non-null value limit",
                2, it.getRunLimit(attr));

        as = new AttributedString("test");
        as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE, null,
                2, 3);
        it = as.getIterator();
        assertEquals("null value limit",
                4, it.getRunLimit(attr));

        attr.add(AttributedCharacterIterator.Attribute.READING);
        assertEquals("null value limit",
                4, it.getRunLimit(attr));
    }

    public void test_getAllAttributeKeys() {
        AttributedString as = new AttributedString("test");
        AttributedCharacterIterator it = as.getIterator();
        Set<AttributedCharacterIterator.Attribute> emptyAttributes =
            it.getAllAttributeKeys();
        assertTrue(emptyAttributes.isEmpty());

        int attrCount = 10;
        for(int i = 0 ; i < attrCount; i++) {
            as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE,
                    "a");
        }
        it = as.getIterator();
        Set<AttributedCharacterIterator.Attribute> attributes =
            it.getAllAttributeKeys();
        for(AttributedCharacterIterator.Attribute attr:attributes) {
            assertEquals(AttributedCharacterIterator.Attribute.LANGUAGE, attr);
        }
    }

    public void test_getAttributeLAttributedCharacterIterator_Attribute() {

        Object attribute =
            it.getAttribute(AttributedCharacterIterator.Attribute.LANGUAGE);
        assertEquals("ENGLISH", attribute);

        attribute =
            it.getAttribute(AttributedCharacterIterator.Attribute.READING);
        assertEquals("READ", attribute);

        assertNull(it.getAttribute(AttributedCharacterIterator.
                Attribute.INPUT_METHOD_SEGMENT));
    }

    public void test_getAttributes() {
        Map<AttributedCharacterIterator.Attribute, Object> attributes =
            it.getAttributes();
        assertEquals(2, attributes.size());
        assertEquals("ENGLISH",
                attributes.get(AttributedCharacterIterator.Attribute.LANGUAGE));
        assertEquals("READ",
                attributes.get(AttributedCharacterIterator.Attribute.READING));

        AttributedString as = new AttributedString("test");
        assertTrue(as.getIterator().getAttributes().isEmpty());
    }

    public void test_getRunLimit() {
        int limit = it.getRunLimit();
        assertEquals(string.length(), limit);

        AttributedString as = new AttributedString("");
        assertEquals(0, as.getIterator().getRunLimit());

        as = new AttributedString(new AttributedString("test text").
                getIterator(), 2, 7);

        AttributedCharacterIterator it = as.getIterator();
        assertEquals(5, it.getRunLimit());
    }

    public void test_getRunLimitLAttribute() {
        AttributedString as = new AttributedString("");
        assertEquals(0, as.getIterator().getRunLimit(
                AttributedCharacterIterator.Attribute.LANGUAGE));

        as = new AttributedString("text");
        as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE,
                "ENGLISH");

        as.addAttribute(AttributedCharacterIterator.Attribute.READING,
        "READ", 1, 3);

        assertEquals(4, as.getIterator().getRunLimit(
                AttributedCharacterIterator.Attribute.LANGUAGE));

        assertEquals(1, as.getIterator().getRunLimit(
                AttributedCharacterIterator.Attribute.READING));
    }

    public void test_getRunStart() {
        assertEquals(0, it.getRunStart());

        AttributedString as = new AttributedString("");
        assertEquals(0, as.getIterator().getRunStart());

        as = new AttributedString(new AttributedString("test text").
                getIterator(), 2, 7);

        AttributedCharacterIterator it = as.getIterator();

        assertEquals(0, it.getRunStart());

        as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE,
                "GERMAN", 1, 2);
        as.addAttribute(AttributedCharacterIterator.Attribute.READING,
                "READ", 1, 3);
        assertEquals(0, as.getIterator().getRunStart());
    }

    public void test_getRunStartLAttribute() {
        assertEquals(0, it.getRunStart(
                AttributedCharacterIterator.Attribute.LANGUAGE));

        AttributedString as = new AttributedString("test text");
        as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE,
                            "GERMAN", 2, 5);
        as.addAttribute(AttributedCharacterIterator.Attribute.READING,
                            "READ", 2, 7);

        assertEquals(0, as.getIterator().getRunStart(
                AttributedCharacterIterator.Attribute.LANGUAGE));
        assertEquals(0, as.getIterator().getRunStart(
                AttributedCharacterIterator.Attribute.READING));
    }

    public void test_getRunStartLjava_util_Set() {
        AttributedString as = new AttributedString("test");
        as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE, "a", 2,
                3);
        AttributedCharacterIterator it = as.getIterator();
        HashSet<AttributedCharacterIterator.Attribute>  attr =
            new HashSet<AttributedCharacterIterator.Attribute>();
        attr.add(AttributedCharacterIterator.Attribute.LANGUAGE);
        assertEquals(0, it.getRunStart(attr));

        as = new AttributedString("test");
        as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE,
                "ENGLISH",1, 3);
        it = as.getIterator();
        assertEquals(0, it.getRunStart(attr));

        attr.add(AttributedCharacterIterator.Attribute.READING);
        assertEquals(0, it.getRunStart(attr));


    }

    protected void setUp() {

        AttributedString as = new AttributedString(string);

        as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE,
                "GERMAN");
        as.addAttribute(AttributedCharacterIterator.Attribute.READING,
                "READ");
        as.addAttribute(AttributedCharacterIterator.Attribute.LANGUAGE,
                "ENGLISH");

        it = as.getIterator();
    }

    protected void tearDown() {
    }
}
