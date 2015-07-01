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
package org.robovm.objc;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.robovm.apple.foundation.NSObject;
import org.robovm.apple.foundation.NSObjectProtocol;
import org.robovm.apple.foundation.NSString;
import org.robovm.objc.annotation.BindSelector;
import org.robovm.objc.annotation.CustomClass;
import org.robovm.objc.annotation.NotImplemented;
import org.robovm.rt.bro.Bro;
import org.robovm.rt.bro.NativeObject;
import org.robovm.rt.bro.annotation.Callback;

/**
 * Tests that the {@code ObjCMemberPlugin} generates the expected
 * {@link Callback} methods for classes subclassing native ObjC classes.
 */
public class CustomClassTest {

    public static class SubClass1 extends NSObject {
        @Override
        public String description() {
            return "(overridden) " + super.description();
        }
    }

    @CustomClass("SubClass2")
    public static class SubClass2 extends NSObject {}

    public static class SubClass3 extends SubClass1 {
        @Override
        public String description() {
            return "(overridden again) " + super.description();
        }
    }

    public interface Protocol extends NSObjectProtocol {
        @org.robovm.objc.annotation.Method(selector = "foo:")
        NSObject foo(NSObject obj);

        @org.robovm.objc.annotation.Method(selector = "bar:")
        NSObject bar(NSObject obj);
    }

    public static class ProtocolAdapter extends NSObject implements Protocol {
        @NotImplemented("foo:")
        public NSObject foo(NSObject obj) {
            return null;
        }

        @NotImplemented("bar:")
        public NSObject bar(NSObject obj) {
            return null;
        }
    }

    public static class ProtocolImpl extends ProtocolAdapter {
        @Override
        public NSObject foo(NSObject obj) {
            return new NSString("foo" + obj);
        }
    }

    public static abstract class AbstractBaseClass extends NSObject {
        @Override
        public String description() {
            return "(abstract base) " + super.description();
        }
    }

    public static class ConcreteSubClass extends AbstractBaseClass {
        @Override
        public String description() {
            return "(concrete) " + super.description();
        }
    }

    public interface NSObjectProxyProtocol extends NSObjectProtocol {
        @org.robovm.objc.annotation.Method(selector = "description")
        public String description();
    }

    public static class NSObjectProxyProtocolReturner extends NSObject {
        @org.robovm.objc.annotation.Method(selector = "performSelector:")
        public native final NSObjectProxyProtocol performSelector2(Selector aSelector);
    }

    @Before
    public void setUp() {
        Assume.assumeTrue(Bro.IS_DARWIN);
    }

    @Test
    public void testCustomClassName() {
        SubClass1 o1 = new SubClass1();
        assertEquals("j_" + SubClass1.class.getName().replace('.', '_'),
                o1.getObjCClass().getName());
        SubClass2 o2 = new SubClass2();
        assertEquals("SubClass2", o2.getObjCClass().getName());
    }

    @Test
    public void testCallOverridenMethodFromJava() {
        SubClass1 o = new SubClass1();
        assertTrue(o.description().startsWith("(overridden) "
                + "<j_org_robovm_objc_CustomClassTest$SubClass1: 0x"));
    }

    @Test
    public void testCallOverridenMethodFromObjC() {
        SubClass1 o = new SubClass1();
        NSString description = (NSString) o.performSelector(Selector.register("description"));
        assertEquals(o.description(), description.toString());
    }

    @Test
    public void testCallOverridenOverriddenMethodFromObjC() {
        SubClass3 o = new SubClass3();
        NSString description = (NSString) o.performSelector(Selector.register("description"));
        assertEquals(o.description(), description.toString());
    }

    @Test
    public void testOnlySingleCallbackInHierarchy() throws Exception {
        Method method1 = SubClass1.class.getDeclaredMethod("$cb$description", SubClass1.class, Selector.class);
        assertNotNull(method1.getAnnotation(Callback.class));
        assertNotNull(method1.getAnnotation(BindSelector.class));
        assertEquals("description", method1.getAnnotation(BindSelector.class).value());
        try {
            SubClass3.class.getDeclaredMethod("$cb$description", SubClass2.class, Selector.class);
            fail("NoSuchMethodException expected");
        } catch (NoSuchMethodException e) {
        }
    }

    @Test
    public void testNotImplemented() throws Exception {
        assertFalse(getMethodNames(ProtocolAdapter.class).contains("$cb$foo$"));
    }

    @Test
    public void testCallProtocolMethodFromObjC() throws Exception {
        assertTrue(getMethodNames(ProtocolImpl.class).contains("$cb$foo$"));
        assertFalse(getMethodNames(ProtocolImpl.class).contains("$cb$bar$"));
        ProtocolImpl p = new ProtocolImpl();
        NSObject o = p.performSelector(Selector.register("foo:"), new NSString("bar"));
        assertEquals("foobar", o.toString());
    }

    @Test
    public void testAbstractBaseClass() throws Exception {
        assertTrue(getMethodNames(AbstractBaseClass.class).contains("$cb$description"));
        assertFalse(getMethodNames(ConcreteSubClass.class).contains("$cb$description"));
        AbstractBaseClass o = new ConcreteSubClass();
        NSString description = (NSString) o.performSelector(Selector.register("description"));
        assertEquals(o.description(), description.toString());
    }

    @Test
    public void testObjCProxy() throws Exception {
        Class<?> objcProxyCls = Class.forName(NSObjectProxyProtocol.class.getName() + "$ObjCProxy");
        assertTrue(getMethodNames(objcProxyCls).contains("$cb$description"));
        NSObjectProxyProtocolReturner pr = new NSObjectProxyProtocolReturner();
        NSObjectProxyProtocol description = pr.performSelector2(Selector.register("description"));
        assertSame(objcProxyCls, description.getClass());
        assertEquals(((NativeObject) description).as(NSObject.class).description(), description.toString());

        // Make sure the proxy class isn't treated as a custom class
        @SuppressWarnings("unchecked")
        ObjCClass objcClass = ObjCClass.getByType((Class<? extends ObjCObject>) objcProxyCls);
        assertFalse(objcClass.isCustom());
    }

    private Set<String> getMethodNames(Class<?> c) {
        TreeSet<String> result = new TreeSet<String>();
        for (Method m : c.getDeclaredMethods()) {
            result.add(m.getName());
        }
        return result;
    }
}
