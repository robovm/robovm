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

public class OldCharacterSubsetTest extends TestCase {

    public void test_equals() {
      Character.Subset subset1 = new Character.Subset("name") { };
      assertTrue(subset1.equals(subset1));
      assertFalse(subset1.equals(new Character.Subset("name") {}));
      assertFalse(subset1.equals(new Character.Subset("name1") {}));
      assertFalse(subset1.equals(new Integer(0)));
    }

    public void test_hashCode() {
      Character.Subset subset1 = new Character.Subset("name") {};
      Character.Subset subset2 = new Character.Subset("name") {};
      Character.Subset subset3 = new Character.Subset("name1") {};
      assertFalse(subset1.hashCode() == subset2.hashCode());
      assertFalse(subset1.hashCode() == subset3.hashCode());
    }
}
