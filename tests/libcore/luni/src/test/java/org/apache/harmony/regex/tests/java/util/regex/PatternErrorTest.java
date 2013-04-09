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

package org.apache.harmony.regex.tests.java.util.regex;

import java.util.regex.Pattern;
import junit.framework.TestCase;

/**
 * Test boundary and error conditions in java.util.regex.Pattern
 *
 */
public class PatternErrorTest extends TestCase {
    public void testCompileErrors() throws Exception {
        // null regex string - should get NullPointerException
        try {
            Pattern.compile(null);
            fail("NullPointerException expected");
        } catch (NullPointerException e) {
        }


        // empty regex string - no exception should be thrown
        Pattern.compile("");

        // note: invalid regex syntax checked in PatternSyntaxExceptionTest

        // flags = 0 should raise no exception
        int flags = 0;
        Pattern.compile("foo", flags);

        // check that all valid flags accepted without exception
        flags |= Pattern.UNIX_LINES;
        flags |= Pattern.CASE_INSENSITIVE;
        flags |= Pattern.MULTILINE;
        // BEGIN android-changed
        // We don't support that flag.
        // flags |= Pattern.CANON_EQ;
        // END android-changed
        flags |= Pattern.COMMENTS;
        flags |= Pattern.DOTALL;
        flags |= Pattern.UNICODE_CASE;
        Pattern.compile("foo", flags);

        // add invalid flags - should get IllegalArgumentException
        /*
         * TODO: Inconsistency between the reference JDK behaviour and spec - exception is
         * not thrown
         */
        /*
         * Valid test is:
         * flags |= 0xFFFFFFFF;
         * try {
         *   Pattern.compile("foo",flags);
         * } catch (IllegalArgumentException e) {
         *   // This is the expected exception
         * } catch (Exception e) {
         *   fail();
         * }
         */

        /* Workaround test is: */
        // BEGIN android-changed
        // We don't support that flag.
        flags |= ~Pattern.CANON_EQ;
        // END android-changed
        // No exception expected to match incorrect the reference behaviour
        Pattern.compile("foo", flags);
    }
}
