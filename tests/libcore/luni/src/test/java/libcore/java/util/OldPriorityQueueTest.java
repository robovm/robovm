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

package libcore.java.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import junit.framework.TestCase;
import libcore.util.SerializationTester;

public class OldPriorityQueueTest extends TestCase {
    public void test_ConstructorI() {
        PriorityQueue<Object> queue = new PriorityQueue<Object>(100);
        assertNotNull(queue);
        assertEquals(0, queue.size());
        assertNull(queue.comparator());

        try {
            new PriorityQueue(0);
            fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException e) {
            //expected
        }
    }

    public void test_remove_Ljava_lang_Object_using_comparator() {
        PriorityQueue<String> queue = new PriorityQueue<String>(10,
                new MockComparatorStringByLength());
        String[] array = { "AAAAA", "AA", "AAAA", "AAAAAAAA" };
        for (int i = 0; i < array.length; i++) {
            queue.offer(array[i]);
        }
        assertFalse(queue.contains("BB"));
        // Even though "BB" is equivalent to "AA" using the string length comparator, remove()
        // uses equals(), so only "AA" succeeds in removing element "AA".
        assertFalse(queue.remove("BB"));
        assertTrue(queue.remove("AA"));
    }

    public void test_remove_Ljava_lang_Object_not_exists() {
        Integer[] array = { 2, 45, 7, -12, 9, 23, 17, 1118, 10, 16, 39 };
        List<Integer> list = Arrays.asList(array);
        PriorityQueue<Integer> integerQueue = new PriorityQueue<Integer>(list);
        assertFalse(integerQueue.remove(111));
        assertFalse(integerQueue.remove(null));
        assertFalse(integerQueue.remove(""));
    }

    public void test_Serialization() throws Exception {
        String s = "aced0005737200176a6176612e7574696c2e5072696f72697479517565756594"
                + "da30b4fb3f82b103000249000473697a654c000a636f6d70617261746f7274001"
                + "64c6a6176612f7574696c2f436f6d70617261746f723b78700000000b70770400"
                + "00000c737200116a6176612e6c616e672e496e746567657212e2a0a4f78187380"
                + "2000149000576616c7565787200106a6176612e6c616e672e4e756d62657286ac"
                + "951d0b94e08b0200007870fffffff47371007e0003000000027371007e0003000"
                + "000077371007e00030000000a7371007e0003000000097371007e000300000017"
                + "7371007e0003000000117371007e00030000045e7371007e00030000002d73710"
                + "07e0003000000107371007e00030000002778";
        PriorityQueue<Integer> srcIntegerQueue = new PriorityQueue<Integer>(
                Arrays.asList(2, 45, 7, -12, 9, 23, 17, 1118, 10, 16, 39));
        new SerializationTester<PriorityQueue<Integer>>(srcIntegerQueue, s) {
            @Override protected boolean equals(PriorityQueue<Integer> a, PriorityQueue<Integer> b) {
                return Arrays.equals(a.toArray(), b.toArray());
            }
        }.test();
    }

    private static class MockComparatorStringByLength implements
            Comparator<String> {

        public int compare(String object1, String object2) {
            int length1 = object1.length();
            int length2 = object2.length();
            if (length1 > length2) {
                return 1;
            } else if (length1 == length2) {
                return 0;
            } else {
                return -1;
            }
        }

    }

    private static class MockComparatorCast<E> implements Comparator<E> {

        public int compare(E object1, E object2) {
            return 0;
        }
    }

    /**
     * removeAt(.) must sometimes perform siftUp(.).
     */
    public void test_removeAt_siftUp() {
        PriorityQueue<Integer> q = new PriorityQueue<Integer>();
        // Adding a valid heap will keep elements in the same order
        for (int i : new int[] { 0, 3, 1, 4, 5, 6, 2 }) {
            q.add(i);
        }
        q.remove(4);  // 2 replaces 4 but parent is 3, siftUp(.) is needed
        for (int i : new int[] { 0, 1, 2, 3, 5, 6 }) {
            assertEquals(i, (int) q.poll());
        }
        assertNull(q.poll());
    }

}
