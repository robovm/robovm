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

import java.lang.reflect.Method;

/**
 * Test reflection on old-style inner classes.
 */
public class Main {
    private static Runnable theRunnable = new Runnable() {
            public void run() { }
        };

    private static Runnable create() {
        return new Runnable() {
                public void run() { }
            };
    }

    private static String nameOf(Class clazz) {
        return (clazz == null) ? "(null)" : clazz.getName();
    }

    private static String nameOf(Method meth) {
        return (meth == null) ? "(null)" : meth.toString();
    }

    private static void infoFor(Class clazz) {
        System.out.println("Class: " + nameOf(clazz) + "\n" +
                "  getDeclaringClass(): " +
                nameOf(clazz.getDeclaringClass()) + "\n" +
                "  getEnclosingClass(): " +
                nameOf(clazz.getEnclosingClass()) + "\n" +
                "  getEnclosingMethod(): " +
                nameOf(clazz.getEnclosingMethod()));
    }

    public static void main(String args[]) {
        infoFor(theRunnable.getClass());
        infoFor(create().getClass());
    }
}
