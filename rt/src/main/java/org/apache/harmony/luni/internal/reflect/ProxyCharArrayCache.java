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

class ProxyCharArrayCache {
    static boolean equals(char[] first, char[] second) {
        if (first == second) {
            return true;
        }
        if (first == null || second == null) {
            return false;
        }
        if (first.length != second.length) {
            return false;
        }

        for (int i = first.length; --i >= 0;) {
            if (first[i] != second[i]) {
                return false;
            }
        }
        return true;
    }

    // to avoid using Enumerations, walk the individual tables skipping nulls
    private char[] keyTable[];

    private int valueTable[];

    // number of elements in the table
    private int elementSize;

    private int threshold;

    ProxyCharArrayCache(int initialCapacity) {
        if (initialCapacity < 13) {
            initialCapacity = 13;
        }
        this.elementSize = 0;
        this.threshold = (int) (initialCapacity * 0.66f);
        this.keyTable = new char[initialCapacity][];
        this.valueTable = new int[initialCapacity];
    }

    int get(char[] key) {
        int index = hashCodeChar(key);
        while (keyTable[index] != null) {
            if (equals(keyTable[index], key)) {
                return valueTable[index];
            }
            index = (index + 1) % keyTable.length;
        }
        return -1;
    }

    private int hashCodeChar(char[] val) {
        int length = val.length;
        int hash = 0;
        int n = 2; // number of characters skipped
        for (int i = 0; i < length; i += n) {
            hash += val[i];
        }
        return (hash & 0x7FFFFFFF) % keyTable.length;
    }

    int put(char[] key, int value) {
        int index = hashCodeChar(key);
        while (keyTable[index] != null) {
            if (equals(keyTable[index], key)) {
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
        ProxyCharArrayCache newHashtable = new ProxyCharArrayCache(
                keyTable.length * 2);
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
    public String toString() {
        int max = size();
        StringBuilder buf = new StringBuilder();
        buf.append("{"); //$NON-NLS-1$
        for (int i = 0; i < max; ++i) {
            if (keyTable[i] != null) {
                buf.append(keyTable[i]).append("->").append(valueTable[i]); //$NON-NLS-1$
            }
            if (i < max) {
                buf.append(", "); //$NON-NLS-1$
            }
        }
        buf.append("}"); //$NON-NLS-1$
        return buf.toString();
    }
}
