/*
 * Copyright (C) 2007 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import junit.framework.Assert;

/**
 * more string tests
 */
public class Main {
    public static void main(String args[]) {
        String test = "0123456789";
        String test1 = new String("0123456789");    // different object
        String test2 = new String("0123456780");    // different value
        String offset = new String("xxx0123456789yyy");
        String sub = offset.substring(3, 13);
        Object blah = new Object();

        Assert.assertTrue(test.equals(test));
        Assert.assertTrue(test.equals(test1));
        Assert.assertFalse(test.equals(test2));

        Assert.assertEquals(test.compareTo(test1), 0);
        Assert.assertTrue(test1.compareTo(test2) > 0);
        Assert.assertTrue(test2.compareTo(test1) < 0);

        /* compare string with a nonzero offset, in left/right side */
        Assert.assertEquals(test.compareTo(sub), 0);
        Assert.assertEquals(sub.compareTo(test), 0);
        Assert.assertTrue(test.equals(sub));
        Assert.assertTrue(sub.equals(test));
        /* same base, one is a substring */
        Assert.assertFalse(offset.equals(sub));
        Assert.assertFalse(sub.equals(offset));
        /* wrong class */
        Assert.assertFalse(test.equals(blah));

        /* null ptr - throw */
        try {
            test.compareTo(null);
            Assert.fail("didn't get expected npe");
        } catch (NullPointerException npe) {
            System.out.println("Got expected npe");
        }
        /* null ptr - ok */
        Assert.assertFalse(test.equals(null));

        test = test.substring(1);
        Assert.assertTrue(test.equals("123456789"));
        Assert.assertFalse(test.equals(test1));

        test = test.substring(1);
        Assert.assertTrue(test.equals("23456789"));

        test = test.substring(1);
        Assert.assertTrue(test.equals("3456789"));

        test = test.substring(1);
        Assert.assertTrue(test.equals("456789"));

        test = test.substring(3,5);
        Assert.assertTrue(test.equals("78"));

        test = "this/is/a/path";
        String[] strings = test.split("/");
        Assert.assertEquals(4, strings.length);

        Assert.assertEquals("this is a path", test.replaceAll("/", " "));
        Assert.assertEquals("this is a path", test.replace("/", " "));
    }
}
