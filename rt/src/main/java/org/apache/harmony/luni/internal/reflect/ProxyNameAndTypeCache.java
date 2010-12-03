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

package org.apache.harmony.luni.internal.reflect;

import java.util.Arrays;

class ProxyNameAndTypeCache {
    int[][] keyTable;

    int[] valueTable;

    int elementSize;

    int threshold;

    ProxyNameAndTypeCache(int initialCapacity) {
        if (initialCapacity < 13) {
            initialCapacity = 13;
        }
        this.elementSize = 0;
        this.threshold = (int) (initialCapacity * 0.66f);
        this.keyTable = new int[initialCapacity][];
        this.valueTable = new int[initialCapacity];
    }

    int get(int[] key) {
        int index = hashCode(key);
        while (keyTable[index] != null) {
            if (keyTable[index][0] == key[0] && keyTable[index][1] == key[1]) {
                return valueTable[index];
            }
            index = (index + 1) % keyTable.length;
        }
        return -1;
    }

    int hashCode(int[] key) {
        return (key[0] + key[1]) % keyTable.length;
    }

    int put(int[] key, int value) {
        int index = hashCode(key);
        while (keyTable[index] != null) {
            if (keyTable[index][0] == key[0] && keyTable[index][1] == key[1]) {
                return valueTable[index] = value;
            }
            index = (index + 1) % keyTable.length;
        }
        keyTable[index] = key;
        valueTable[index] = value;

        // assumes the threshold is never equal to the size of the table
        if (++elementSize > threshold) {
            rehash();
        }
        return value;
    }

    private void rehash() {
        ProxyNameAndTypeCache newHashtable = new ProxyNameAndTypeCache(keyTable.length * 2);
        for (int i = keyTable.length; --i >= 0;) {
            if (keyTable[i] != null) {
                newHashtable.put(keyTable[i], valueTable[i]);
            }
        }

        this.keyTable = newHashtable.keyTable;
        this.valueTable = newHashtable.valueTable;
        this.threshold = newHashtable.threshold;
    }

    int size() {
        return elementSize;
    }

    @Override
    @SuppressWarnings("nls")
    public String toString() {
        int max = size();
        StringBuilder buf = new StringBuilder();
        buf.append("{");
        for (int i = 0; i < max; ++i) {
            if (keyTable[i] != null) {
                buf.append(Arrays.toString(keyTable[i]));
                buf.append("->").append(valueTable[i]);
            }
            if (i < max) {
                buf.append(", ");
            }
        }
        buf.append("}");
        return buf.toString();
    }
}
