/*
 * Copyright (C) 2010 The Android Open Source Project
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

import junit.framework.TestCase;
import tests.util.ClassLoaderBuilder;

public final class MissingClassesTest extends TestCase {

    private Class<?> loadableClass;

    @Override protected void setUp() throws Exception {
        String prefix = MissingClassesTest.class.getName();
        ClassLoader cl = new ClassLoaderBuilder()
                .withPrivateCopy(prefix + "$Loadable")
                .without(prefix + "$Unloadable")
                .build();
        loadableClass = cl.loadClass(prefix + "$Loadable");
    }

    /**
     * http://b/issue?id=2634005
     */
    public void testGetDeclaredFieldsFails() {
        try {
            loadableClass.getDeclaredFields();
            fail();
        } catch (NoClassDefFoundError expected) {
        }
    }

    public void testGetDeclaredMethodsFails() {
        try {
            loadableClass.getDeclaredMethods();
            fail();
        } catch (NoClassDefFoundError expected) {
        }
    }

    public void testGetMethodFails() throws NoSuchMethodException {
        try {
            loadableClass.getDeclaredMethod("method", Unloadable.class);
            fail();
        } catch (NoClassDefFoundError expected) {
        }
    }

    public void testGetFieldFails() throws NoSuchFieldException {
        try {
            loadableClass.getDeclaredField("field");
            fail();
        } catch (NoClassDefFoundError expected) {
        }
    }

    class Loadable {
        Unloadable field;
        void method(Unloadable unloadable) {}
    }

    class Unloadable {}
}
