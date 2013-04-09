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

package libcore.java.sql;

import java.sql.Timestamp;
import java.util.TimeZone;
import junit.framework.TestCase;

public final class OldTimestampTest extends TestCase {

    public void test_toString() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

        Timestamp t1 = new Timestamp(Long.MIN_VALUE);
        assertEquals("292278994-08-17 07:12:55.192", t1.toString());

        Timestamp t2 = new Timestamp(Long.MIN_VALUE + 1);
        assertEquals("292278994-08-17 07:12:55.193", t2.toString());

        Timestamp t3 = new Timestamp(Long.MIN_VALUE + 807);
        assertEquals("292278994-08-17 07:12:55.999", t3.toString());

        Timestamp t4 = new Timestamp(Long.MIN_VALUE + 808);
        assertEquals("292269055-12-02 16:47:05.0", t4.toString());
    }
}
