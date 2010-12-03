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

/**
* @author Stepan M. Mishura
*/

package org.apache.harmony.security.asn1;

import java.util.Arrays;

import org.apache.harmony.security.x501.AttributeType;


/**
 * Represents Information Object Set. 
 * 
 * @see http://asn1.elibel.tm.fr/en/standards/index.htm
 */

public class InformationObjectSet {

    private final int capacity;

    private final Entry[][] pool;

    public InformationObjectSet() {
        this(64, 10);
    }

    public InformationObjectSet(int capacity, int size) {
        this.capacity = capacity;
        pool = new Entry[capacity][size];
    }

    public void put(AttributeType at) {
        put(at.oid.getOid(), at);
    }

    public void put(int[] oid, Object object) {

        int index = hashIntArray(oid) % capacity;
        // look for OID in the pool 
        Entry[] list = pool[index];
        int i = 0;
        for (; list[i] != null; i++) {

            // check wrong static initialization: no duplicate OIDs
            if (Arrays.equals(oid, list[i].oid)) {
                throw new Error(); //FIXME message
            }
        }

        // check : to avoid NPE
        if (i == (capacity - 1)) {
            throw new Error(); //FIXME message
        }
        list[i] = new Entry(oid, object);
    }

    public Object get(int[] oid) {
        int index = hashIntArray(oid) % capacity;

        // look for OID in the pool 
        Entry[] list = pool[index];
        for (int i = 0; list[i] != null; i++) {
            if (Arrays.equals(oid, list[i].oid)) {
                return list[i].object;
            }
        }
        return null;
    }

    // FIXME change me to Arrays.hashCode(int[])
    private int hashIntArray(int[] array) {
        int intHash = 0;
        for (int i = 0; i < array.length && i < 4; i++) {
            intHash += array[i] << (8 * i); //TODO what about to find better one?
        }
        return intHash & 0x7FFFFFFF; // only positive
    }

    private static class Entry {
        public int[] oid;

        public Object object;

        public Entry(int[] oid, Object object) {
            this.oid = oid;
            this.object = object;
        }
    }
}