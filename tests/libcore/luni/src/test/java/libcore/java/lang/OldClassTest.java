/*
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

package libcore.java.lang;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.AbstractList;
import java.util.Collection;
import java.util.List;
import java.util.Vector;
//import tests.support.Support_ClassLoader;
import tests.support.resource.Support_Resources;

@SuppressWarnings("deprecation")
public class OldClassTest extends junit.framework.TestCase {

    public static final String FILENAME =
        OldClassTest.class.getPackage().getName().replace('.', '/') +
        "/test#.properties";

    final String packageName = getClass().getPackage().getName();
    final String classNameInitError1 = packageName + ".TestClass1";
    final String classNameInitError2 = packageName + ".TestClass1B";
    final String classNameLinkageError = packageName + ".TestClass";
    final String sourceJARfile = "illegalClasses.jar";
    final String illegalClassName = "illegalClass";

    @Retention(RetentionPolicy.RUNTIME)
    public @interface TestAnnotation {
        String value();
    }

    public static class TestClass1C {
        static TestClass2 tc = new TestClass2(0);

        TestClass1C() {
        }

    }

    public static class TestClass2 {

        public TestClass2(int i) throws IllegalArgumentException {
            throw new IllegalArgumentException();
        }
    }

    public static class TestClass3 {
        private TestClass3() {}
    }

    interface TestInterface {
        public static int TEST_INTERFACE_FIELD = 0;

        int getCount();
        void setCount(int value);
    }

    static class StaticMember$Class {
        class Member2$A {
        }
    }

    class Member$Class {
        class Member3$B {
        }
    }

    @Deprecated
    @TestAnnotation("libcore.java.lang.OldClassTest$ExtendTestClass")
    public static class ExtendTestClass extends PublicTestClass {

        private static final long serialVersionUID = 1L;

        public enum enumExm {ONE, TWO, THREE};
        @Override
        public void setCount(int value) {

        }
    }

    public class ExtendTestClass1 extends ExtendTestClass {

    }

    @TestAnnotation("libcore.java.lang.OldClassTest$PublicTestClass")
    public static class PublicTestClass implements TestInterface, Serializable, Cloneable {

        private static final long serialVersionUID = 1L;

        public static String TEST_FIELD = "test field";

        Object clazz;

        public PublicTestClass() {
            class LocalClass { }

            clazz = new LocalClass();
        }

        public Object getLocalClass() {
            class LocalClass {}
            Object returnedObject = new LocalClass();
            return returnedObject;
        }

        int count = 0;

        public int getCount() {
            return count;
        }

        public void setCount(int value) {
            count = value;
        }

        private class PrivateClass1 {

            public String toString() {
                return "PrivateClass0";
            }
        }

        public class PrivateClass2 {

            public String toString() {
                return "PrivateClass1";
            }
        }
    }

    public static class TestClass {
        @SuppressWarnings("unused")
        private int privField = 1;

        public int pubField = 2;

        private Object cValue = null;

        public Object ack = new Object();

        @SuppressWarnings("unused")
        private int privMethod() {
            return 1;
        }

        public int pubMethod() {
            return 2;
        }

        public Object cValue() {
            return cValue;
        }

        public TestClass() {
        }

        @SuppressWarnings("unused")
        private TestClass(Object o) {
        }
    }

    public static class SubTestClass extends TestClass {
    }

    interface Intf1 {
        public int field1 = 1;
        public int field2 = 1;
        void test();
    }

    interface Intf2 {
        public int field1 = 1;
        void test();
    }

    interface Intf3 extends Intf1 {
        public int field1 = 1;
    }

    interface Intf4 extends Intf1, Intf2 {
        public int field1 = 1;
        void test2(int a, Object b);
    }

    interface Intf5 extends Intf1 {
    }

    class Cls1 implements Intf2 {
        public int field1 = 2;
        public int field2 = 2;
        public void test() {
        }
    }

    class Cls2 extends Cls1 implements Intf1 {
        public int field1 = 2;
        @Override
        public void test() {
        }
    }

    class Cls3 implements Intf3, Intf4 {
        public void test() {
        }
        public void test2(int a, Object b) {
        }
    }

    static class Cls4 {

    }

    public void test_getAnnotations() {
      Annotation [] annotations = PublicTestClass.class.getAnnotations();
      assertEquals(1, annotations.length);
      assertEquals(TestAnnotation.class, annotations[0].annotationType());

      annotations = ExtendTestClass.class.getAnnotations();
      assertEquals(2, annotations.length);

      for(int i = 0; i < annotations.length; i++) {
          Class<? extends Annotation> type = annotations[i].annotationType();
          assertTrue("Annotation's type " + i + ": " + type,
              type.equals(Deprecated.class) ||
              type.equals(TestAnnotation.class));
      }
    }

    public void test_forNameLjava_lang_StringLbooleanLClassLoader() throws Exception {

        ClassLoader pcl = getClass().getClassLoader();

        Class<?> [] classes = {PublicTestClass.class, ExtendTestClass.class,
                ExtendTestClass1.class, TestInterface.class, String.class};

        for(int i = 0; i < classes.length; i++) {
            Class<?> clazz = Class.forName(classes[i].getName(), true, pcl);
            assertEquals(classes[i], clazz);

            clazz = Class.forName(classes[i].getName(), false, pcl);
            assertEquals(classes[i], clazz);
        }

        Class<?> [] systemClasses = {String.class, Integer.class, Object.class,
                Object[].class};

        for(int i = 0; i < systemClasses.length; i++) {
            Class<?> clazz = Class.forName(systemClasses[i].getName(), true,
                                            ClassLoader.getSystemClassLoader());
            assertEquals(systemClasses[i], clazz);

            clazz = Class.forName(systemClasses[i].getName(), false,
                                            ClassLoader.getSystemClassLoader());
            assertEquals(systemClasses[i], clazz);
        }

        try  {
            Class.forName(null, true, pcl);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException  npe) {
            //expected
        }

        try {
            Class.forName("NotExistClass", true, pcl);
            fail("ClassNotFoundException is not thrown for non existent class.");
        } catch(ClassNotFoundException cnfe) {
            //expected
        }

        try {
            Class.forName("String", false, pcl);
            fail("ClassNotFoundException is not thrown for non existent class.");
        } catch(ClassNotFoundException cnfe) {
            //expected
        }

        try {
            Class.forName("libcore.java.lang.NonexistentClass", false, pcl);
            fail("ClassNotFoundException is not thrown for non existent class.");
        } catch(ClassNotFoundException cnfe) {
            //expected
        }
    }

    // AndroidOnly: Class.forName method throws ClassNotFoundException on Android.
    public void test_forNameLjava_lang_StringLbooleanLClassLoader_AndroidOnly() throws Exception {

        // Android doesn't support loading class files from a jar.
        try {

            URL url = getClass().getClassLoader().getResource(
                    packageName.replace(".", "/") + "/" + sourceJARfile);

            ClassLoader loader = new URLClassLoader(new URL[] { url },
                    getClass().getClassLoader());
            try {
                Class.forName(classNameLinkageError, true, loader);
                fail("LinkageError or ClassNotFoundException expected.");
            } catch (java.lang.LinkageError le) {
                // Expected for the RI.
            } catch (java.lang.ClassNotFoundException ce) {
                // Expected for Android.
            }
        } catch(Exception e) {
            fail("Unexpected exception was thrown: " + e.toString());
        }

        try {
            Class.forName(classNameInitError2,
                    true, getClass().getClassLoader());
            fail("ExceptionInInitializerError or ClassNotFoundException " +
            "should be thrown.");
        } catch (java.lang.ExceptionInInitializerError ie) {
            // Expected for the RI.
        // Remove this comment to let the test pass on Android.
        } catch (java.lang.ClassNotFoundException ce) {
            // Expected for Android.
        }
    }

    public void test_getAnnotation() {
      TestAnnotation target = PublicTestClass.class.getAnnotation(TestAnnotation.class);
      assertEquals(target.value(), PublicTestClass.class.getName());

      assertNull(PublicTestClass.class.getAnnotation(Deprecated.class));

      Deprecated target2 = ExtendTestClass.class.getAnnotation(Deprecated.class);
      assertNotNull(target2);
    }

    public void test_getDeclaredAnnotations() {
        Annotation [] annotations = PublicTestClass.class.getDeclaredAnnotations();
        assertEquals(1, annotations.length);

        annotations = ExtendTestClass.class.getDeclaredAnnotations();
        assertEquals(2, annotations.length);

        annotations = TestInterface.class.getDeclaredAnnotations();
        assertEquals(0, annotations.length);

        annotations = String.class.getDeclaredAnnotations();
        assertEquals(0, annotations.length);
    }

    public void test_getEnclosingClass() {
        Class clazz = OldClassTest.class.getEnclosingClass();
        assertNull(clazz);

        assertEquals(getClass(), Cls1.class.getEnclosingClass());
        assertEquals(getClass(), Intf1.class.getEnclosingClass());
        assertEquals(getClass(), Cls4.class.getEnclosingClass());
    }

    public void test_getEnclosingMethod() {
        Method clazz = ExtendTestClass.class.getEnclosingMethod();
        assertNull(clazz);

        PublicTestClass ptc = new PublicTestClass();
        try {
            assertEquals("getEnclosingMethod returns incorrect method.",
                    PublicTestClass.class.getMethod("getLocalClass",
                            (Class []) null),
                    ptc.getLocalClass().getClass().getEnclosingMethod());
        } catch(NoSuchMethodException nsme) {
            fail("NoSuchMethodException was thrown.");
        }
    }

    public void test_getEnclosingConstructor() {

        PublicTestClass ptc = new PublicTestClass();

        assertEquals("getEnclosingConstructor method returns incorrect class.",
                PublicTestClass.class.getConstructors()[0],
                ptc.clazz.getClass().getEnclosingConstructor());

        assertNull("getEnclosingConstructor should return null for local " +
                "class declared in method.",
                ptc.getLocalClass().getClass().getEnclosingConstructor());

        assertNull("getEnclosingConstructor should return null for local " +
                "class declared in method.",
                ExtendTestClass.class.getEnclosingConstructor());
    }


    public void test_getEnumConstants() {
        Object [] clazz = ExtendTestClass.class.getEnumConstants();
        assertNull(clazz);
        Object [] constants = TestEnum.class.getEnumConstants();
        assertEquals(TestEnum.values().length, constants.length);
        for(int i = 0; i < constants.length; i++) {
            assertEquals(TestEnum.values()[i], constants[i]);
        }
        assertEquals(0, TestEmptyEnum.class.getEnumConstants().length);
    }
    public enum TestEnum {
        ONE, TWO, THREE
    }
    public enum TestEmptyEnum {
    }

    public void test_getGenericInterfaces() {
        Type [] types = ExtendTestClass1.class.getGenericInterfaces();
        assertEquals(0, types.length);

        Class [] interfaces = {TestInterface.class, Serializable.class,
                               Cloneable.class};
        types = PublicTestClass.class.getGenericInterfaces();
        assertEquals(interfaces.length, types.length);
        for(int i = 0; i < types.length; i++) {
            assertEquals(interfaces[i], types[i]);
        }

        types = TestInterface.class.getGenericInterfaces();
        assertEquals(0, types.length);

        types = List.class.getGenericInterfaces();
        assertEquals(1, types.length);
        assertEquals(Collection.class, ((ParameterizedType)types[0]).getRawType());

        assertEquals(0, int.class.getGenericInterfaces().length);
        assertEquals(0, void.class.getGenericInterfaces().length);
    }

    public void test_getGenericSuperclass () {
        assertEquals(PublicTestClass.class,
                                  ExtendTestClass.class.getGenericSuperclass());
        assertEquals(ExtendTestClass.class,
                ExtendTestClass1.class.getGenericSuperclass());
        assertEquals(Object.class, PublicTestClass.class.getGenericSuperclass());
        assertEquals(Object.class, String.class.getGenericSuperclass());
        assertEquals(null, TestInterface.class.getGenericSuperclass());

        ParameterizedType type = (ParameterizedType) Vector.class.getGenericSuperclass();
        assertEquals(AbstractList.class, type.getRawType());
    }

    // RoboVM note: Uses custom classloader which isn't supported on RoboVM
    // // AndroidOnly: Uses dalvik.system.PathClassLoader.
    // // Different behavior between cts host and run-core-test")
    // public void test_getPackage() {

    //   Package thisPackage = getClass().getPackage();
    //   assertEquals("libcore.java.lang",
    //                   thisPackage.getName());

    //   Package stringPackage = String.class.getPackage();
    //   assertNotNull("java.lang", stringPackage.getName());

    //   String hyts_package_name = "hyts_package_dex.jar";
    //   File resources = Support_Resources.createTempFolder();
    //   Support_Resources.copyFile(resources, "Package", hyts_package_name);

    //   String resPath = resources.toString();
    //   if (resPath.charAt(0) == '/' || resPath.charAt(0) == '\\')
    //       resPath = resPath.substring(1);

    //   try {

    //       URL resourceURL = new URL("file:/" + resPath + "/Package/"
    //               + hyts_package_name);

    //       ClassLoader cl = Support_ClassLoader.getInstance(resourceURL,
    //               getClass().getClassLoader());

    //       Class clazz = cl.loadClass("C");
    //       assertNull("getPackage for C.class should return null",
    //               clazz.getPackage());

    //       clazz = cl.loadClass("a.b.C");
    //       Package cPackage = clazz.getPackage();
    //       assertNotNull("getPackage for a.b.C.class should not return null",
    //               cPackage);

    //     /*
    //      * URLClassLoader doesn't work on Android for jar files
    //      *
    //      * URL url = getClass().getClassLoader().getResource(
    //      *         packageName.replace(".", "/") + "/" + sourceJARfile);
    //      *
    //      * ClassLoader loader = new URLClassLoader(new URL[] { url }, null);
    //      *
    //      * try {
    //      *     Class<?> clazz = loader.loadClass(illegalClassName);
    //      *     Package pack = clazz.getPackage();
    //      *     assertNull(pack);
    //      * } catch(ClassNotFoundException cne) {
    //      *     fail("ClassNotFoundException was thrown for " + illegalClassName);
    //      * }
    //     */
    //   } catch(Exception e) {
    //       fail("Unexpected exception was thrown: " + e.toString());
    //   }
    // }

    public void test_getSigners() {
        assertNull(void.class.getSigners());
        assertNull(PublicTestClass.class.getSigners());
    }

    public void test_getSimpleName() {
        assertEquals("PublicTestClass", PublicTestClass.class.getSimpleName());
        assertEquals("void", void.class.getSimpleName());
        assertEquals("int[]", int[].class.getSimpleName());
    }

    public void test_getTypeParameters() {
        assertEquals(0, PublicTestClass.class.getTypeParameters().length);
        TypeVariable [] tv = TempTestClass1.class.getTypeParameters();
        assertEquals(1, tv.length);
        assertEquals(Object.class, tv[0].getBounds()[0]);

        TempTestClass2<String> tc = new TempTestClass2<String>();
        tv = tc.getClass().getTypeParameters();
        assertEquals(1, tv.length);
        assertEquals(String.class, tv[0].getBounds()[0]);
    }

    class TempTestClass1<T> {
    }

    class TempTestClass2<S extends String> extends TempTestClass1<S> {
    }

    public void test_isAnnotation() {
        assertTrue(Deprecated.class.isAnnotation());
        assertTrue(TestAnnotation.class.isAnnotation());
        assertFalse(PublicTestClass.class.isAnnotation());
        assertFalse(String.class.isAnnotation());
    }

     public void test_isAnnotationPresent() {
        assertTrue(PublicTestClass.class.isAnnotationPresent(TestAnnotation.class));
        assertFalse(ExtendTestClass1.class.isAnnotationPresent(TestAnnotation.class));
        assertFalse(String.class.isAnnotationPresent(Deprecated.class));
        assertTrue(ExtendTestClass.class.isAnnotationPresent(TestAnnotation.class));
        assertTrue(ExtendTestClass.class.isAnnotationPresent(Deprecated.class));
     }

    public void test_isAnonymousClass() {
        assertFalse(PublicTestClass.class.isAnonymousClass());
        assertTrue((new Thread() {}).getClass().isAnonymousClass());
    }

    public void test_isEnum() {
      assertFalse(PublicTestClass.class.isEnum());
      assertFalse(ExtendTestClass.class.isEnum());
      assertTrue(TestEnum.ONE.getClass().isEnum());
      assertTrue(TestEnum.class.isEnum());
    }

    public void test_isLocalClass() {
        assertFalse(ExtendTestClass.class.isLocalClass());
        assertFalse(TestInterface.class.isLocalClass());
        assertFalse(TestEnum.class.isLocalClass());
        class InternalClass {}
        assertTrue(InternalClass.class.isLocalClass());
    }

    public void test_isMemberClass() {
        assertFalse(OldClassTest.class.isMemberClass());
        assertFalse(String.class.isMemberClass());
        assertTrue(TestEnum.class.isMemberClass());
        assertTrue(StaticMember$Class.class.isMemberClass());
    }

    public void test_isSynthetic() {
      assertFalse("Returned true for non synthetic class.",
              ExtendTestClass.class.isSynthetic());
      assertFalse("Returned true for non synthetic class.",
              TestInterface.class.isSynthetic());
      assertFalse("Returned true for non synthetic class.",
              String.class.isSynthetic());
    }

    public void test_getCanonicalName() {
        Class [] classArray = { int.class, int[].class, String.class,
                                PublicTestClass.class, TestInterface.class,
                                ExtendTestClass.class };
        String [] classNames = {"int", "int[]", "java.lang.String",
                      "libcore.java.lang.OldClassTest.PublicTestClass",
                        "libcore.java.lang.OldClassTest.TestInterface",
                     "libcore.java.lang.OldClassTest.ExtendTestClass"};

        for(int i = 0; i < classArray.length; i++) {
            assertEquals(classNames[i], classArray[i].getCanonicalName());
        }
    }

    public void test_getClassLoader() {
        assertEquals(ExtendTestClass.class.getClassLoader(),
                         PublicTestClass.class.getClassLoader());

        assertNull(int.class.getClassLoader());
        assertNull(void.class.getClassLoader());
    }

    public void test_getClasses() {
        assertEquals("Incorrect class array returned",
                     11, OldClassTest.class.getClasses().length);
    }

    public void test_getDeclaredClasses() {
        Class [] declClasses = Object.class.getDeclaredClasses();
        assertEquals("Incorrect length of declared classes array is returned " +
                "for Object.", 0, declClasses.length);

        declClasses = PublicTestClass.class.getDeclaredClasses();
        assertEquals(2, declClasses.length);

        assertEquals(0, int.class.getDeclaredClasses().length);
        assertEquals(0, void.class.getDeclaredClasses().length);

        for(int i = 0; i < declClasses.length; i++) {
            Constructor<?> constr = declClasses[i].getDeclaredConstructors()[0];
            constr.setAccessible(true);
            PublicTestClass publicClazz = new PublicTestClass();
            try {
                Object o = constr.newInstance(publicClazz);
                assertTrue("Returned incorrect class: " + o.toString(),
                        o.toString().startsWith("PrivateClass"));
            } catch(Exception e) {
                fail("Unexpected exception was thrown: " + e.toString());
            }
        }

        declClasses = TestInterface.class.getDeclaredClasses();
        assertEquals(0, declClasses.length);
    }

    public void test_getDeclaredConstructor$Ljava_lang_Class() throws Exception {
        try {
            TestClass.class.getDeclaredConstructor(String.class);
            fail("NoSuchMethodException should be thrown.");
        } catch(NoSuchMethodException nsme) {
            //expected
        }
    }

    public void test_getDeclaredFieldLjava_lang_String() throws Exception {
        try {
            TestClass.class.getDeclaredField(null);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        try {
            TestClass.class.getDeclaredField("NonExistentField");
            fail("NoSuchFieldException is not thrown.");
        } catch(NoSuchFieldException nsfe) {
            //expected
        }
    }

    public void test_getDeclaredMethodLjava_lang_String$Ljava_lang_Class() throws Exception {
        try {
            TestClass.class.getDeclaredMethod(null, new Class[0]);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }

        try {
            TestClass.class.getDeclaredMethod("NonExistentMethod", new Class[0]);
            fail("NoSuchMethodException is not thrown.");
        } catch(NoSuchMethodException nsme) {
            //expected
        }
    }

    public void test_getMethodLjava_lang_String$Ljava_lang_Class() throws Exception {
        Method m = ExtendTestClass1.class.getMethod("getCount", new Class[0]);
        assertEquals("Returned incorrect method", 0, ((Integer) (m.invoke(new ExtendTestClass1())))
                .intValue());

        try {
            m = TestClass.class.getMethod("init", new Class[0]);
            fail("Failed to throw exception accessing to init method");
        } catch (NoSuchMethodException e) {
            // Correct
            return;
        }

        try {
            TestClass.class.getMethod("pubMethod", new Class[0]);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_getDeclaringClass() {
        assertNull(OldClassTest.class.getDeclaringClass());
        assertNotNull(PublicTestClass.class.getDeclaringClass());
    }

    public void test_getFieldLjava_lang_String() throws Exception {
        Field f = TestClass.class.getField("pubField");
        assertEquals("Returned incorrect field", 2, f.getInt(new TestClass()));

        f = PublicTestClass.class.getField("TEST_FIELD");
        assertEquals("Returned incorrect field", "test field",
                f.get(new PublicTestClass()));

        f = PublicTestClass.class.getField("TEST_INTERFACE_FIELD");
        assertEquals("Returned incorrect field", 0,
                f.getInt(new PublicTestClass()));

        try {
            TestClass.class.getField(null);
            fail("NullPointerException is thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_getFields2() throws Exception {
        Field[] f;
        Field expected = null;

        f = PublicTestClass.class.getFields();
        assertEquals("Test 1: Incorrect number of fields;", 2, f.length);

        f = Cls2.class.getFields();
        assertEquals("Test 2: Incorrect number of fields;", 6, f.length);

        f = Cls3.class.getFields();
        assertEquals("Test 2: Incorrect number of fields;", 5, f.length);

        for (Field field : f) {
            if (field.toString().equals("public static final int "
                    + "libcore.java.lang.OldClassTest$Intf3.field1")) {
                expected = field;
                break;
            }
        }
        if (expected == null) {
            fail("Test 3: getFields() did not return all fields.");
        }
        assertEquals("Test 4: Incorrect field;", expected,
                Cls3.class.getField("field1"));

        expected = null;
        for (Field field : f) {
            if(field.toString().equals("public static final int " +
                    "libcore.java.lang.OldClassTest$Intf1.field2")) {
                expected = field;
                break;
            }
        }
        if (expected == null) {
            fail("Test 5: getFields() did not return all fields.");
        }
        assertEquals("Test 6: Incorrect field;", expected,
                Cls3.class.getField("field2"));
    }

    public void test_getFields() throws Exception {
        Field expected = null;
        Field[] fields = Cls2.class.getFields();
        for (Field field : fields) {
            if(field.toString().equals("public int libcore.java.lang.OldClassTest$Cls2.field1")) {
                expected = field;
                break;
            }
        }
        if (expected == null) {
            fail("getFields() did not return all fields");
        }
        assertEquals(expected, Cls2.class.getField("field1"));
    }

    public void test_getInterfaces() {
        Class [] interfaces1 = Cls1.class.getInterfaces();
        assertEquals(1, interfaces1.length);
        assertEquals(Intf2.class, interfaces1[0]);

        Class [] interfaces2 = Cls2.class.getInterfaces();
        assertEquals(1, interfaces2.length);
        assertEquals(Intf1.class, interfaces2[0]);

        Class [] interfaces3 = Cls3.class.getInterfaces();
        assertEquals(2, interfaces3.length);
        assertEquals(Intf3.class, interfaces3[0]);
        assertEquals(Intf4.class, interfaces3[1]);

        Class [] interfaces4 = Cls4.class.getInterfaces();
        assertEquals(0, interfaces4.length);
    }

    public void test_getMethods() throws Exception {
        assertEquals("Incorrect number of methods", 10,
                Cls2.class.getMethods().length);
        assertEquals("Incorrect number of methods", 11,
                Cls3.class.getMethods().length);

        Method expected = null;
        Method[] methods = Cls2.class.getMethods();
        for (Method method : methods) {
            if(method.toString().equals("public void libcore.java.lang.OldClassTest$Cls2.test()")) {
                expected = method;
                break;
            }
        }
        if (expected == null) {
            fail("getMethods() did not return all methods");
        }
        assertEquals(expected, Cls2.class.getMethod("test"));

        expected = null;
        methods = Cls3.class.getMethods();
        for (Method method : methods) {
            if(method.toString().equals("public void libcore.java.lang.OldClassTest$Cls3.test()")) {
                expected = method;
                break;
            }
        }
        if (expected == null) {
            fail("getMethods() did not return all methods");
        }
        assertEquals(expected, Cls3.class.getMethod("test"));

        expected = null;
        methods = Cls3.class.getMethods();
        for (Method method : methods) {
            if(method.toString().equals("public void libcore.java.lang.OldClassTest$Cls3.test2(int,"
                    + "java.lang.Object)")) {
                expected = method;
                break;
            }
        }
        if (expected == null) {
            fail("getMethods() did not return all methods");
        }

        assertEquals(expected, Cls3.class.getMethod("test2", int.class,
                Object.class));

        assertEquals("Incorrect number of methods", 1,
                Intf5.class.getMethods().length);
    }

    public void test_getResourceLjava_lang_String() {
        assertNull(getClass().getResource(
                "libcore/java/lang/NonExistentResource"));
        assertNull(getClass().getResource(getClass().getName() + "NonExistentResource"));
    }

    public void test_getResourceAsStreamLjava_lang_String() throws Exception {
        String name = "/HelloWorld.txt";
        assertNotNull("the file " + name + " can not be found in this " +
                "directory", getClass().getResourceAsStream(name));

        final String nameBadURI = "org/apache/harmony/luni/tests/test_resource.txt";
        assertNull("the file " + nameBadURI + " should not be found in this directory",
                getClass().getResourceAsStream(nameBadURI));

        ClassLoader pcl = getClass().getClassLoader();
        Class<?> clazz = pcl.loadClass("libcore.java.lang.OldClassTest");
        assertNotNull(clazz.getResourceAsStream("HelloWorld1.txt"));

        try {
            getClass().getResourceAsStream(null);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_isAssignableFromLjava_lang_Class() {
        assertFalse("returned true not assignable classes",
                Integer.class.isAssignableFrom(String.class));

        try {
            Runnable.class.isAssignableFrom(null);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
    }

    public void test_newInstance() throws Exception {
        try {
            TestClass3.class.newInstance();
            fail("IllegalAccessException is not thrown.");
        } catch(IllegalAccessException  iae) {
            //expected
        }

        try {
            TestClass1C.class.newInstance();
            fail("ExceptionInInitializerError should be thrown.");
        } catch (java.lang.ExceptionInInitializerError ie) {
            //expected
        }
    }

    public void test_asSubclass1() {
        assertEquals(ExtendTestClass.class,
                ExtendTestClass.class.asSubclass(PublicTestClass.class));

        assertEquals(PublicTestClass.class,
                PublicTestClass.class.asSubclass(TestInterface.class));

        assertEquals(ExtendTestClass1.class,
                ExtendTestClass1.class.asSubclass(PublicTestClass.class));

        assertEquals(PublicTestClass.class,
                PublicTestClass.class.asSubclass(PublicTestClass.class));
    }

    public void test_asSubclass2() {
        try {
            PublicTestClass.class.asSubclass(ExtendTestClass.class);
            fail("Test 1: ClassCastException expected.");
        } catch(ClassCastException cce) {
            // Expected.
        }

        try {
            PublicTestClass.class.asSubclass(String.class);
            fail("Test 2: ClassCastException expected.");
        } catch(ClassCastException cce) {
            // Expected.
        }
    }

    public void test_cast() {
        Object o = PublicTestClass.class.cast(new ExtendTestClass());
        assertTrue(o instanceof ExtendTestClass);

        try {
            ExtendTestClass.class.cast(new PublicTestClass());
            fail("Test 1: ClassCastException expected.");
        } catch(ClassCastException cce) {
            //expected
        }

        try {
            ExtendTestClass.class.cast(new String());
            fail("ClassCastException is not thrown.");
        } catch(ClassCastException cce) {
            //expected
        }
    }

    public void test_desiredAssertionStatus() {
      Class [] classArray = { Object.class, Integer.class,
                              String.class, PublicTestClass.class,
                              ExtendTestClass.class, ExtendTestClass1.class};

      for(int i = 0; i < classArray.length; i++) {
          assertFalse("assertion status for " + classArray[i],
                       classArray[i].desiredAssertionStatus());
      }
   }

    public void testGetResourceAsStream1() throws IOException {
        Class clazz = getClass();

        InputStream stream = clazz.getResourceAsStream("HelloWorld.txt");
        assertNotNull(stream);

        byte[] buffer = new byte[20];
        int length = stream.read(buffer);
        String s = new String(buffer, 0, length);
        assertEquals("Hello, World.\n",  s);

        stream.close();
    }

    public void testGetResourceAsStream2() throws IOException {
        Class clazz = getClass();

        InputStream stream = clazz.getResourceAsStream("/libcore/java/lang/HelloWorld.txt");
        assertNotNull(stream);

        byte[] buffer = new byte[20];
        int length = stream.read(buffer);
        String s = new String(buffer, 0, length);
        assertEquals("Hello, World.\n", s);

        stream.close();

        try {
            clazz.getResourceAsStream(null);
            fail("NullPointerException is not thrown.");
        } catch(NullPointerException npe) {
            //expected
        }
        assertNull(clazz.getResourceAsStream("/NonExistentResource"));
        assertNull(clazz.getResourceAsStream("libcore/java/lang/HelloWorld.txt"));
    }
}
