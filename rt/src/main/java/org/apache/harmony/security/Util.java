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

package org.apache.harmony.security;

public class Util {

    public static String toUpperCase(String s) {
        return org.apache.harmony.luni.util.Util.toASCIIUpperCase(s);
    }

    public static boolean equalsIgnoreCase(String s1, String s2) {
        s1 = org.apache.harmony.luni.util.Util.toASCIIUpperCase(s1);
        s2 = org.apache.harmony.luni.util.Util.toASCIIUpperCase(s2);
        return s1.equals(s2);
    }
}
