/*
 * Copyright (C) 2008 The Android Open Source Project
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

package libcore.java.lang.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;

public final class OldAndroidClassTest extends TestCase {
    private static final String packageName = "libcore.java.lang.reflect";

    public void testNewInstance() throws Exception {
        Class helloClass = Class.forName(OldAndroidClassTest.class.getName());
        Object instance = helloClass.newInstance();
        assertNotNull(instance);
    }

    public void testForName() throws Exception {
        try {
            Class.forName("this.class.DoesNotExist");
            fail();
        } catch (ClassNotFoundException expected) {
        }
    }

    public void testNewInstancePrivateConstructor() throws Exception {
        try {
            Class.forName(packageName + ".ClassWithPrivateConstructor").newInstance();
            fail();
        } catch (IllegalAccessException expected) {
        }
    }

    public void testGetDeclaredMethod() throws Exception {
        Class helloClass = Class.forName(OldAndroidClassTest.class.getName());
        Method method = helloClass.getDeclaredMethod("method", (Class[]) null);
        method.invoke(new OldAndroidClassTest(), (Object[]) null);
    }

    public void testGetDeclaredMethodWithArgs() throws Exception {
        Class helloClass = Class.forName(OldAndroidClassTest.class.getName());
        Method method = helloClass.getDeclaredMethod("methodWithArgs", Object.class);

        Object invokeArgs[] = new Object[1];
        invokeArgs[0] = "Hello";
        Object ret = method.invoke(new OldAndroidClassTest(), invokeArgs);
        assertEquals(ret, invokeArgs[0]);
    }

    public void testGetDeclaredMethodPrivate() throws Exception {
        Class helloClass = Class.forName(OldAndroidClassTest.class.getName());
        Method method = helloClass.getDeclaredMethod("privateMethod", (Class[]) null);
        method.invoke(new OldAndroidClassTest(), (Object[]) null);
    }

    public void testGetSuperclass() throws Exception {
        Class helloClass = Class.forName(OldAndroidClassTest.class.getName());
        Class objectClass = Class.forName("java.lang.Object");
        assertEquals(helloClass.getSuperclass().getSuperclass().getSuperclass(), objectClass);
    }

    public void testIsAssignableFrom() throws Exception {
        Class helloClass = Class.forName(OldAndroidClassTest.class.getName());
        Class objectClass = Class.forName("java.lang.Object");
        assertTrue(objectClass.isAssignableFrom(helloClass));
        assertFalse(helloClass.isAssignableFrom(objectClass));
    }

    public void testGetConstructor() throws Exception {
        Class helloClass = Class.forName(OldAndroidClassTest.class.getName());
        Constructor constructor = helloClass.getConstructor((Class[]) null);
        assertNotNull(constructor);
    }

    public void testGetModifiers() throws Exception {
        Class helloClass = Class.forName(OldAndroidClassTest.class.getName());
        assertTrue(Modifier.isPublic(helloClass.getModifiers()));
    }

    public void testGetMethod() throws Exception {
        Class helloClass = Class.forName(OldAndroidClassTest.class.getName());
        helloClass.getMethod("method", (Class[]) null);
        try {
            Class[] argTypes = new Class[1];
            argTypes[0] = helloClass;
            helloClass.getMethod("method", argTypes);
            fail();
        } catch (NoSuchMethodException expected) {
        }
    }

    // http://code.google.com/p/android/issues/detail?id=14
    public void testFieldSet() throws Exception {
        OldAndroidClassTest.SimpleClass obj = new OldAndroidClassTest.SimpleClass();
        Field field = obj.getClass().getDeclaredField("str");
        field.set(obj, null);
    }

    public class SimpleClass {
        public String str;
    }

    public Object methodWithArgs(Object o) {
        return o;
    }

    boolean methodInvoked;

    public void method() {
        methodInvoked = true;
    }

    boolean privateMethodInvoked;

    public void privateMethod() {
        privateMethodInvoked = true;
    }

    // Regression for 1018067: Class.getMethods() returns the same method over
    // and over again from all base classes
    public void testClassGetMethodsNoDupes() {
        Method[] methods = ArrayList.class.getMethods();
        Set<String> set = new HashSet<String>();

        for (Method method : methods) {
            String signature = method.toString();

            int par = signature.indexOf('(');
            int dot = signature.lastIndexOf('.', par);

            signature = signature.substring(dot + 1);

            assertFalse("Duplicate " + signature, set.contains(signature));
            set.add(signature);
        }
    }

    interface MyInterface {
        void foo();
    }

    interface MyOtherInterface extends MyInterface {
        void bar();
    }

    abstract class MyClass implements MyOtherInterface {
        public void gabba() {
        }

        public void hey() {
        }
    }

    // Check if we also reflect methods from interfaces
    public void testGetMethodsInterfaces() {
        Method[] methods = MyInterface.class.getMethods();
        assertTrue(hasMethod(methods, ".foo("));

        methods = MyOtherInterface.class.getMethods();
        assertTrue(hasMethod(methods, ".foo("));
        assertTrue(hasMethod(methods, ".bar("));

        methods = MyClass.class.getMethods();
        assertTrue(hasMethod(methods, ".foo("));
        assertTrue(hasMethod(methods, ".bar("));

        assertTrue(hasMethod(methods, ".gabba("));
        assertTrue(hasMethod(methods, ".hey("));

        assertTrue(hasMethod(methods, ".toString("));
    }

    private boolean hasMethod(Method[] methods, String signature) {
        for (Method method : methods) {
            if (method.toString().contains(signature)) {
                return true;
            }
        }
        return false;
    }

    // Test for Class.getPackage();
    public void testClassGetPackage() {
        assertNotNull(getClass().getPackage());
        assertEquals(packageName, getClass().getPackage().getName());
        assertEquals("Unknown", getClass().getPackage().getSpecificationTitle());

        Package p = Object.class.getPackage();
        assertNotNull(p);
        assertEquals("java.lang", p.getName());
        assertSame(p, Object.class.getPackage());
    }

    // Regression test for #1123708: Problem with getCanonicalName(),
    // getSimpleName(), and getPackage().
    //
    // A couple of interesting cases need to be checked: Top-level classes,
    // member classes, local classes, and anonymous classes. Also, boundary
    // cases with '$' in the class names are checked, since the '$' is used
    // as the separator between outer and inner class, so this might lead
    // to problems (it did in the previous implementation).
    //
    // Caution: Adding local or anonymous classes elsewhere in this
    // file might affect the test.
    private class MemberClass {
    }

    private class Mi$o$oup {
    }

    public void testVariousClassNames() {
        Class<?> clazz = this.getClass();
        String pkg = (clazz.getPackage() == null ? "" : clazz.getPackage().getName() + ".");

        // Simple, top-level class

        assertEquals(pkg + "OldAndroidClassTest", clazz.getName());
        assertEquals("OldAndroidClassTest", clazz.getSimpleName());
        assertEquals(pkg + "OldAndroidClassTest", clazz.getCanonicalName());

        clazz = MemberClass.class;

        assertEquals(pkg + "OldAndroidClassTest$MemberClass", clazz.getName());
        assertEquals("MemberClass", clazz.getSimpleName());
        assertEquals(pkg + "OldAndroidClassTest.MemberClass", clazz.getCanonicalName());

        class LocalClass {
            // This space intentionally left blank.
        }

        clazz = LocalClass.class;

        assertEquals(pkg + "OldAndroidClassTest$1LocalClass", clazz.getName());
        assertEquals("LocalClass", clazz.getSimpleName());
        assertNull(clazz.getCanonicalName());

        clazz = new Object() { }.getClass();

        assertEquals(pkg + "OldAndroidClassTest$1", clazz.getName());
        assertEquals("", clazz.getSimpleName());
        assertNull(clazz.getCanonicalName());

        // Weird special cases with dollar in name.

        clazz = Mou$$aka.class;

        assertEquals(pkg + "Mou$$aka", clazz.getName());
        assertEquals("Mou$$aka", clazz.getSimpleName());
        assertEquals(pkg + "Mou$$aka", clazz.getCanonicalName());

        clazz = Mi$o$oup.class;

        assertEquals(pkg + "OldAndroidClassTest$Mi$o$oup", clazz.getName());
        assertEquals("Mi$o$oup", clazz.getSimpleName());
        assertEquals(pkg + "OldAndroidClassTest.Mi$o$oup", clazz.getCanonicalName());

        class Ma$hedPotatoe$ {
        }

        clazz = Ma$hedPotatoe$.class;

        assertEquals(pkg + "OldAndroidClassTest$1Ma$hedPotatoe$", clazz.getName());
        assertEquals("Ma$hedPotatoe$", clazz.getSimpleName());
        assertNull(clazz.getCanonicalName());
    }

    public void testLocalMemberClass() {
        Class<?> clazz = this.getClass();

        assertFalse(clazz.isMemberClass());
        assertFalse(clazz.isLocalClass());

        clazz = MemberClass.class;

        assertTrue(clazz.isMemberClass());
        assertFalse(clazz.isLocalClass());

        class OtherLocalClass {
        }

        clazz = OtherLocalClass.class;

        assertFalse(clazz.isMemberClass());
        assertTrue(clazz.isLocalClass());

        clazz = new Object() { }.getClass();

        assertFalse(clazz.isMemberClass());
        assertFalse(clazz.isLocalClass());
    }
}

class ClassWithPrivateConstructor {
    private ClassWithPrivateConstructor() {
    }
}

class Mou$$aka {
}
