/*
 * Copyright (C) 2015 RoboVM AB
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
package org.robovm.rt.bro.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to specify the index of the first {@code va_arg} parameter in a
 * {@link Bridge} method. The parameter and all following parameters will be
 * replaced by {@code ...} when generating the native function signature. For
 * static methods the index specified in this annotation has to be 1 or greater.
 * Bro does not support passing variable number of arguments. The Java signature
 * still needs to have a fixed number of parameters but the target native
 * function can be a function which can take a variable number of arguments
 * (e.g. {@code printf(char *, ...)}).
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Variadic {
    /**
     * The index of the first {@code va_arg} parameter. Must be &gt;0 for static
     * methods.
     */
    int value();
}
