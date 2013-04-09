/*
 * Copyright (C) 2011 The Android Open Source Project
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

package libcore.java.lang;

import java.util.Arrays;
import java.util.List;
import junit.framework.TestCase;

public final class PackageTest extends TestCase {
    /** assign packages immediately so that Class.getPackage() calls cannot side-effect it */
    private static final List<Package> packages = Arrays.asList(Package.getPackages());

    public void test_getAnnotations() throws Exception {
        // Package annotations aren't supported, but pre-ICS we crashed.
        assertEquals(0, getClass().getPackage().getAnnotations().length);
        assertEquals(0, getClass().getPackage().getDeclaredAnnotations().length);
    }

    public void testGetPackage() {
        Package libcoreJavaLang = Package.getPackage("libcore.java.lang");
        assertEquals("libcore.java.lang", libcoreJavaLang.getName());
        assertEquals(getClass().getPackage(), libcoreJavaLang);
    }

    // http://b/5171136
    public void testGetPackages() {
        assertTrue(packages.contains(getClass().getPackage()));
    }
}
