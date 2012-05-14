/*
 * Copyright (C) 2007 The Android Open Source Project
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

package java.lang;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class VMClassLoader {

//    /**
//     * Get a resource from a file in the bootstrap class path.
//     *
//     * It would be simpler to just walk through the class path elements
//     * ourselves, but that would require reopening Jar files.
//     *
//     * We assume that the bootclasspath can't change once the VM has
//     * started.  This assumption seems to be supported by the spec.
//     */
//    static URL getResource(String name) {
//        int numEntries = getBootClassPathSize();
//        for (int i = 0; i < numEntries; i++) {
//            String urlStr = getBootClassPathResource(name, i);
//            if (urlStr != null) {
//                try {
//                    return new URL(urlStr);
//                } catch (MalformedURLException mue) {
//                    mue.printStackTrace();
//                    // unexpected; keep going
//                }
//            }
//        }
//        return null;
//    }
//
//    /*
//     * Get an enumeration with all matching resources.
//     */
//    static List<URL> getResources(String name) {
//        ArrayList<URL> list = new ArrayList<URL>();
//        int numEntries = getBootClassPathSize();
//        for (int i = 0; i < numEntries; i++) {
//            String urlStr = getBootClassPathResource(name, i);
//            if (urlStr != null) {
//                try {
//                    list.add(new URL(urlStr));
//                } catch (MalformedURLException mue) {
//                    mue.printStackTrace();
//                    // unexpected; keep going
//                }
//            }
//        }
//        return list;
//    }

//    /**
//     * Load class with bootstrap class loader.
//     */
//    native static Class loadClass(String name, boolean resolve) throws ClassNotFoundException;
//
//    native static Class getPrimitiveClass(char type);

    static Class defineClass(ClassLoader cl, String name, byte[] data, int offset, int len)
            throws ClassFormatError {
    	throw new UnsupportedOperationException();
    }

    static Class defineClass(ClassLoader cl, byte[] data, int offset, int len)
            throws ClassFormatError {
            	throw new UnsupportedOperationException();
            }

    native static Class findLoadedClass(ClassLoader cl, String name);

    native static final Class findClassInClasspathForLoader(ClassLoader cl, String name) 
            throws ClassNotFoundException;
    
//    /**
//     * Boot class path manipulation, for getResources().
//     */
//    native private static int getBootClassPathSize();
//    native private static String getBootClassPathResource(String name, int index);
}
