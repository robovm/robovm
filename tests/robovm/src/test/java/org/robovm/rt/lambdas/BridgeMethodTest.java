/*
 * Copyright (C) 2015 RoboVM AB
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
package org.robovm.rt.lambdas;

import static org.junit.Assert.*;

import org.junit.Test;

interface A<T> { 
    T m(T t); 
}

interface B extends A<String> { 
    String m(String s); 
}

public class BridgeMethodTest {
    @Test
    public void testBridgeMethods() {
        B b = (s) -> { return "Hello " + s; };        
        assertEquals("Hello there", b.m("there"));
        
        A a = b;
        assertEquals("Hello there", a.m("there"));
    }
}
