/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.cocoatouch.foundation;

import static org.junit.Assert.*;

import java.util.regex.Pattern;

import org.junit.Test;
import org.robovm.objc.Selector;
import org.robovm.rt.bro.Bro;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Library;

/**
 * Tests {@link NSObject}.
 */
@Library("Foundation")
public class NSObjectTest {

    static {
        Bro.bind();
    }
    
    @Test
    public void testDescription() {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        NSObject o = new NSObject();
        assertTrue(o.description().matches("<NSObject: 0x[0-9a-f]+>"));
//        assertEquals("", new NSString("Hello world!!!").toString());
        pool.drain();
    }

    private static final Selector description = Selector.register("description");
    @Bridge(symbol = "objc_msgSend") private native static NSString objc_description(NSObject __self__, Selector __cmd__);
    
    public static class MyObjCClass1 extends NSObject {
        @Override
        public String description() {
            return "FOOBAR";
        }
    }
    
    public static class MyObjCClass2 extends NSObject {
    }
    
    @Test
    public void testCustomClass1() throws Exception {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        MyObjCClass1 o = new MyObjCClass1();
        assertTrue(o.getHandle() != 0L);
        assertEquals("FOOBAR", objc_description(o, description).toString());
        pool.drain();
    }
    
    @Test
    public void testCustomClass2() throws Exception {
        NSAutoreleasePool pool = new NSAutoreleasePool();
        MyObjCClass2 o = new MyObjCClass2();
        assertTrue(o.getHandle() != 0L);
        String s = o.description();
        assertTrue(s.matches("<" + Pattern.quote(MyObjCClass2.class.getName()) + ": 0x[0-9a-f]+>"));
        assertEquals(s, objc_description(o, description).toString());
        pool.drain();
    }
}
