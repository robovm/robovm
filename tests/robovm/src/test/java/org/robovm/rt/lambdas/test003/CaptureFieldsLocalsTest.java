/*
 * Copyright (C) 2015 The Android Open Source Project
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
package org.robovm.rt.lambdas.test003;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

interface I {
    int add();
}

class Locals { 
    public int add(int val) {
        int i = val;
        I l = () -> i;
        return l.add();
    }
}

class Fields {
    int field;
    
    public Fields(int value) {
        this.field = value;
    }
    
    public int add() {
        I i = () -> field;
        return i.add();
    }
}

class LocalsAndFields {
    int field;

    public LocalsAndFields(int value) {
        this.field = value;       
    }

    public int add(int a) {
        I i = () -> a + field;
        return i.add();
    }    
}

public class CaptureFieldsLocalsTest {

    @Test
    public void test003() {
        for(int i = 0; i < 10; i++) {
            Locals l = new Locals();
            assertEquals(i, l.add(i));
        }
        
        for(int i = 0; i < 10; i++) {
            Fields f = new Fields(i);
            assertEquals(i, f.add());
        }
    
        for(int i = 0; i < 10; i++) {
            LocalsAndFields laf = new LocalsAndFields(i);
            assertEquals(i+i, laf.add(i));
        }
    }
}
