/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang;

/**
 * Does nothing on Android.
 */
public final class Compiler {
    /**
     * Prevent this class from being instantiated.
     */
    private Compiler() {
        //do nothing
    }

    /**
     * Executes an operation according to the specified command object. This
     * method is the low-level interface to the JIT compiler. It may return any
     * object or {@code null} if no JIT compiler is available. Returns null
     * on Android, whether or not the system has a JIT.
     *
     * @param cmd
     *            the command object for the JIT compiler.
     * @return the result of executing command or {@code null}.
     */
    public static Object command(Object cmd) {
        return null;
    }

    /**
     * Compiles the specified class using the JIT compiler and indicates if
     * compilation has been successful. Does nothing and returns false on
     * Android.
     *
     * @param classToCompile
     *            java.lang.Class the class to JIT compile
     * @return {@code true} if the compilation has been successful;
     *         {@code false} if it has failed or if there is no JIT compiler
     *         available.
     */
    public static boolean compileClass(Class<?> classToCompile) {
        return false;
    }

    /**
     * Compiles all classes whose name matches the specified name using the JIT
     * compiler and indicates if compilation has been successful. Does nothing
     * and returns false on Android.
     *
     * @param nameRoot
     *            the string to match class names with.
     * @return {@code true} if the compilation has been successful;
     *         {@code false} if it has failed or if there is no JIT compiler
     *         available.
     */
    public static boolean compileClasses(String nameRoot) {
        return false;
    }

    /**
     * Disables the JIT compiler. Does nothing on Android.
     */
    public static void disable() {
    }

    /**
     * Enables the JIT compiler. Does nothing on Android.
     */
    public static void enable() {
    }
}
