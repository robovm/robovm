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

package org.apache.harmony.niochar.charset;

public class UTF_16LE extends UTF_16 {

    public UTF_16LE(String canonicalName, String[] aliases) {
        super(canonicalName, aliases);
    }

    protected int getDefaultEndian() {
        return LITTLE;
    }

    protected int getDetectedEndian(int b1, int b2) {
        return (b1 == 0xFF && b2 == 0xFE) ? getDefaultEndian() : NOT_DETECTED;
    }

}
