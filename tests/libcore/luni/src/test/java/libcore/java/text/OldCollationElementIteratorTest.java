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

import java.text.CollationElementIterator;
import java.text.Collator;
import java.text.RuleBasedCollator;
import java.util.Locale;
import junit.framework.TestCase;

public class OldCollationElementIteratorTest extends TestCase {

    public void testPrevious() {
        RuleBasedCollator coll = (RuleBasedCollator) Collator.getInstance(Locale.US);
        String text = "abc";
        CollationElementIterator iterator = coll
                .getCollationElementIterator(text);
        int[] orders = new int[text.length()];
        int order = iterator.next();
        int i = 0;
        while (order != CollationElementIterator.NULLORDER) {
            orders[i++] = order;
            order = iterator.next();
        }

        int offset = iterator.getOffset();
        assertEquals(text.length(), offset);
        order = iterator.previous();

        while (order != CollationElementIterator.NULLORDER) {
            assertEquals(orders[--i], order);
            order = iterator.previous();
        }

        assertEquals(0, iterator.getOffset());
    }
}
