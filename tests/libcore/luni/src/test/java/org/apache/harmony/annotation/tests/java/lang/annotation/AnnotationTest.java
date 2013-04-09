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

package org.apache.harmony.annotation.tests.java.lang.annotation;

import junit.framework.TestCase;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Test case of java.lang.annotation.Annotation
 */
public class AnnotationTest extends TestCase {

    public void test_annotationType() {
        Annotation [] annotations = AnnotatedClass.class.getDeclaredAnnotations();
        assertEquals(1, annotations.length);
        Annotation anno = annotations[0];
        assertEquals(TestAnnotation1.class, anno.annotationType());
    }

    public void test_equals() throws Exception {
        // test type
        Method m1 = AnnotatedClass2.class
                .getDeclaredMethod("a", new Class[] {});
        Method m2 = AnnotatedClass2.class
                .getDeclaredMethod("b", new Class[] {});
        assertFalse("other annotation class type",
                m1.getDeclaredAnnotations()[0].equals(m2
                        .getDeclaredAnnotations()[0]));

        // test equality / non equality for base types and compound types
        List<Method> methods = Arrays.asList(AnnotatedClass.class.getDeclaredMethods());
        Map<String, List<Method>> eqs = new HashMap<String, List<Method>>();
        Map<String, List<Method>> neqs = new HashMap<String, List<Method>>();
        for (Method m : methods) {
            String name = m.getName();
            //System.out.println("name "+name);
            Map<String, List<Method>> curT = name.charAt(0) == 'e'? eqs : neqs;
            String testNum = name.substring(1,3); // 01
            List<Method> mlist = curT.get(testNum);
            if (mlist == null) {
                mlist = new ArrayList<Method>();
                curT.put(testNum, mlist);
            }
            mlist.add(AnnotatedClass.class.getDeclaredMethod(name, new Class[] {}));
        }

        for (List<Method> eqList : eqs.values()) {
            for (int i = 0; i < eqList.size() -1; i++) {
                for (int j = i+1; j < eqList.size(); j++) {
                    Method me1 = eqList.get(i);
                    Method me2 = eqList.get(j);
                    //System.out.println("eq test for "+me1.getName()+", "+me2.getName());
                    Annotation a1 = me1.getDeclaredAnnotations()[0];
                    Annotation a2 = me2.getDeclaredAnnotations()[0];
                    assertEquals("must be equal : method1:"+me1.getName()+", method2: "+me2.getName(), a1, a2);
                    assertEquals("same hashcode", a1.hashCode(), a2.hashCode());
                }
            }
        }

        for (List<Method> eqList : neqs.values()) {
            for (int i = 0; i < eqList.size() -1; i++) {
                for (int j = i+1; j < eqList.size(); j++) {
                    Method me1 = eqList.get(i);
                    Method me2 = eqList.get(j);
                    Annotation a1 = me1.getDeclaredAnnotations()[0];
                    Annotation a2 = me2.getDeclaredAnnotations()[0];
                    //System.out.println("ne test for "+me1.getName()+", "+me2.getName());
                    assertFalse("must not be equal : method1:"+me1.getName()+", method2: "+me2.getName(),
                            a1.equals(a2));
                    if (a1.hashCode() != a2.hashCode()) {
                        assertFalse("not same hashcode -> not equals", a1.equals(a2));
                    }
                }

            }
        }
    }

    public void test_hashCode() throws SecurityException, NoSuchMethodException {
        Annotation a1 = AnnotatedClass.class.getDeclaredAnnotations()[0];
        assertEquals(a1.hashCode(), (127 * "value".hashCode() ^ "foobar".hashCode()));
        // i+= 127 *(key.hashCode() ^ memberValHashCode(value);

        Method m1 = AnnotatedClass.class.getDeclaredMethod("e34c", new Class[] {});
        int arrHc = Arrays.hashCode(new Object[]{});
        /*
        TestAnnotation3[] arrAnno() default {};
        String[] arrString() default {};
        Class[] arrClass() default {};
        TestEnum1[] arrEnum() default {};
         */
        assertEquals(
                (127 * "arrAnno".hashCode() ^ arrHc) +
                (127 * "arrString".hashCode() ^ arrHc)+
                (127 * "arrClass".hashCode() ^ arrHc) +
                (127 * "arrEnum".hashCode() ^ arrHc)
                ,
                m1.getDeclaredAnnotations()[0].hashCode());

        Method m2 = AnnotatedClass3.class.getDeclaredMethod("a", new Class[] {});
        assertEquals(
                (127 * "i".hashCode() ^ 12345),
                m2.getDeclaredAnnotations()[0].hashCode());

    }
}

class AnnotatedClass2 {
    @TestAnnotation3()
    void a() {}
    @TestAnnotation3b()
    void b() {}
}

class AnnotatedClass3 {
    @TestAnnotation4(i = 12345)
    void a() {}
}

@TestAnnotation1("foobar")
class AnnotatedClass {

    // ----- boolean -----
    @TestAnnotation3(z = false)
    void e01a() {}
    @TestAnnotation3(z = false)
    void e01b() {}
    @TestAnnotation3()
    void e01c() {}

    @TestAnnotation3(z = true)
    void e02a() {}
    @TestAnnotation3(z = true)
    void e02b() {}

    @TestAnnotation3(z = false)
    void n03a() {}
    @TestAnnotation3(z = true)
    void n03b() {}


    // ----- byte -----
    @TestAnnotation3(b = 0)
    void e04a() {}
    @TestAnnotation3(b = 0)
    void e04b() {}
    @TestAnnotation3()
    void e04c() {}

    @TestAnnotation3(b= 127)
    void e05a() {}
    @TestAnnotation3(b = 127)
    void e05b() {}

    @TestAnnotation3(b = -128)
    void n06a() {}
    @TestAnnotation3(b = 127)
    void n06b() {}


    // ----- short -----
    @TestAnnotation3(s = 0)
    void e07a() {}
    @TestAnnotation3(s = 0)
    void e07b() {}
    @TestAnnotation3()
    void e07c() {}

    @TestAnnotation3(s= 32767)
    void e08a() {}
    @TestAnnotation3(s = 32767)
    void e08b() {}

    @TestAnnotation3(s = -32768)
    void n09a() {}
    @TestAnnotation3(s = 32767)
    void n09b() {}


    // ----- int -----
    @TestAnnotation3(i = 100)
    void e10a() {}
    @TestAnnotation3(i = 100)
    void e10b() {}
    @TestAnnotation3()
    void e10c() {}

    @TestAnnotation3(i = Integer.MAX_VALUE)
    void e11a() {}
    @TestAnnotation3(i = Integer.MAX_VALUE)
    void e11b() {}

    @TestAnnotation3(i = Integer.MAX_VALUE)
    void n12a() {}
    @TestAnnotation3(i = Integer.MIN_VALUE)
    void n12b() {}


    // ----- long -----
    @TestAnnotation3(j = 0)
    void e13a() {}
    @TestAnnotation3(j = 0)
    void e13b() {}
    @TestAnnotation3()
    void e13c() {}

    @TestAnnotation3(j = Long.MAX_VALUE)
    void e14a() {}
    @TestAnnotation3(j = Long.MAX_VALUE)
    void e14b() {}

    @TestAnnotation3(j = Long.MAX_VALUE)
    void n15a() {}
    @TestAnnotation3(j = Long.MIN_VALUE)
    void n15b() {}


    // ----- float -----
    @TestAnnotation3(f = 0.0f)
    void e16a() {}
    @TestAnnotation3(f = 0.0f)
    void e16b() {}
    @TestAnnotation3()
    void e16c() {}

    @TestAnnotation3(f = Float.MAX_VALUE)
    void e17a() {}
    @TestAnnotation3(f = Float.MAX_VALUE)
    void e17b() {}

    @TestAnnotation3(f = Float.NaN)
    void e18a() {}
    @TestAnnotation3(f = Float.NaN)
    void e18b() {}

    @TestAnnotation3(f = Long.MAX_VALUE)
    void n19a() {}
    @TestAnnotation3(f = Long.MIN_VALUE)
    void n19b() {}

    @TestAnnotation3(f = 0.0f)
    void n20a() {}
    @TestAnnotation3(f = -0.0f)
    void n20b() {}


    // ----- double -----
    @TestAnnotation3(d = 0.0d)
    void e21a() {}
    @TestAnnotation3(d = 0.0d)
    void e21b() {}
    @TestAnnotation3()
    void e21c() {}

    @TestAnnotation3(d = Double.MAX_VALUE)
    void e22a() {}
    @TestAnnotation3(d = Double.MAX_VALUE)
    void e22b() {}

    @TestAnnotation3(d = Double.NaN)
    void e23a() {}
    @TestAnnotation3(d = Double.NaN)
    void e23b() {}


    @TestAnnotation3(d = Double.MAX_VALUE)
    void n24a() {}
    @TestAnnotation3(d = Double.MIN_VALUE)
    void n24b() {}

    @TestAnnotation3(d = 0.0d)
    void n25a() {}
    @TestAnnotation3(d = -0.0d)
    void n25b() {}


 // ----- String -----
    @TestAnnotation3(aString = "")
    void e26a() {}
    @TestAnnotation3(aString = "")
    void e26b() {}
    @TestAnnotation3()
    void e26c() {}

    @TestAnnotation3(aString = "asjdfk jkls dfjklsd fklsd jklds kflds jfkldsfjd"+"d")
    void e27a() {}
    @TestAnnotation3(aString = "asjdfk jkls dfjklsd fklsd jklds kflds jfkldsfj"+"dd")
    void e27b() {}

    @TestAnnotation3(aString = "a")
    void n28a() {}
    @TestAnnotation3(aString = "b")
    void n28b() {}


    // ----- Class-----
    @TestAnnotation3(aClazz = Void.class)
    void e29a() {}
    @TestAnnotation3(aClazz = Void.class)
    void e29b() {}
    @TestAnnotation3()
    void e29c() {}

    @TestAnnotation3(aClazz = Integer.class)
    void n30a() {}
    @TestAnnotation3(aClazz = int.class)
    void n30b() {}


    // ----- Enum-----
    @TestAnnotation3(aEnum = TestEnum1.F)
    void e31a() {}
    @TestAnnotation3(aEnum = TestEnum1.F)
    void e31b() {}
    @TestAnnotation3()
    void e31c() {}

    @TestAnnotation3(aEnum = TestEnum1.F)
    void n32a() {}
    @TestAnnotation3(aEnum = TestEnum1.A)
    void n32b() {}

    @TestAnnotation3(aEnum = TestEnum1.F)
    void n33a() {}
    @TestAnnotation3(aEnum = TestEnum1.L)
    void n33b() {}


    // ----- String arr-----
    @TestAnnotation2(arrString = {})
    void e34a() {}
    @TestAnnotation2(arrString = {})
    void e34b() {}
    @TestAnnotation2(arrString = {})
    void e34c() {}

    @TestAnnotation2(arrString = { "a", "b"})
    void e35a() {}
    @TestAnnotation2(arrString = { "a", "b" })
    void e35b() {}

    @TestAnnotation2(arrString = { "a", "b"})
    void n36a() {}
    @TestAnnotation2(arrString = { "a", "c" })
    void n36b() {}


    // ----- Class arr-----
    @TestAnnotation2(arrClass= {})
    void e37a() {}
    @TestAnnotation2(arrClass = {})
    void e37b() {}
    @TestAnnotation2(arrClass = {})
    void e37c() {}

    @TestAnnotation2(arrClass = { Void.class, Integer.class})
    void e38a() {}
    @TestAnnotation2(arrClass = { Void.class, Integer.class})
    void e38b() {}

    @TestAnnotation2(arrClass = { Void.class, Integer.class})
    void n39a() {}
    @TestAnnotation2(arrClass = { Void.class, int.class})
    void n39b() {}

    // ----- Enum arr-----
    @TestAnnotation2(arrEnum= {})
    void e40a() {}
    @TestAnnotation2(arrEnum = {})
    void e40b() {}
    @TestAnnotation2(arrEnum = {})
    void e40c() {}

    @TestAnnotation2(arrEnum = { TestEnum1.A, TestEnum1.F })
    void e41a() {}
    @TestAnnotation2(arrEnum = { TestEnum1.A, TestEnum1.F })
    void e41b() {}

    @TestAnnotation2(arrEnum = { TestEnum1.A, TestEnum1.F })
    void n42a() {}
    @TestAnnotation2(arrEnum = { TestEnum1.A, TestEnum1.L })
    void n42b() {}


    // ----- Annotation arr-----
    @TestAnnotation2(arrAnno= {})
    void e43a() {}
    @TestAnnotation2(arrAnno = {})
    void e43b() {}
    @TestAnnotation2()
    void e43c() {}

    @TestAnnotation2(arrAnno = { @TestAnnotation3(i = 20), @TestAnnotation3(j = 123)})
    void e44a() {}
    @TestAnnotation2(arrAnno = { @TestAnnotation3(i = 20), @TestAnnotation3(j = 123)})
    void e44b() {}

    @TestAnnotation2(arrAnno = { @TestAnnotation3(i = 20), @TestAnnotation3(j = 123)})
    void n45a() {}
    @TestAnnotation2(arrAnno = { @TestAnnotation3(i = 20), @TestAnnotation3(j = 124)})
    void n45b() {}

    @TestAnnotation2(arrAnno = { @TestAnnotation3(i = 20), @TestAnnotation3(j = 123)})
    void n46a() {}
    @TestAnnotation2(arrAnno = { @TestAnnotation3(i = -20), @TestAnnotation3(j = 123)})
    void n46b() {}

}
