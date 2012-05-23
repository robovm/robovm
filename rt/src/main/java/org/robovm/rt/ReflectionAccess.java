/*
 * Copyright (C) 2012 RoboVM
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

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 *
 * @version $Id$
 */
public interface ReflectionAccess {

    Field clone(Field f);
    Constructor<?> clone(Constructor<?> c);
    Method clone(Method m);
    Field[] clone(Field[] f);
    Constructor<?>[] clone(Constructor<?>[] c);
    Method[] clone(Method[] m);
    boolean equals(Method m1, Method m2);
    boolean matchParameterTypes(Constructor<?> c, Class<?>[] parameterTypes);
    boolean matchParameterTypes(Method m, Class<?>[] parameterTypes);
}
