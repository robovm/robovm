/*
 * Copyright (C) 2015 Trillian Mobile AB
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
package org.robovm.rt;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import junit.framework.TestCase;

import org.junit.Test;
import org.junit.internal.TextListener;
import org.junit.runner.JUnitCore;

/**
 * Runs all test classes available in the classpath. A test class is a class
 * which extends from {@link TestCase} or has at least one public {@link Test} 
 * annotated method.
 */
public class RunAllTests {

    public static void main(String[] args) {
        List<Class<?>> classes = new ArrayList<>();
        for (Class<?> cls : VM.listClasses(Object.class, ClassLoader.getSystemClassLoader())) {
            if (!cls.getName().startsWith("junit.") && !cls.getName().startsWith("org.junit.")) {
                if (TestCase.class.isAssignableFrom(cls)) {
                    classes.add(cls);
                    continue;
                }
                for (Method m : cls.getMethods()) {
                    if (m.getAnnotation(Test.class) != null) {
                        classes.add(cls);
                        break;
                    }
                }
            }
        }

        JUnitCore jc = new JUnitCore();
        jc.addListener(new TextListener(System.out));
        jc.run(classes.toArray(new Class[classes.size()]));
        System.out.flush();
        System.exit(0);
    }
    
}
