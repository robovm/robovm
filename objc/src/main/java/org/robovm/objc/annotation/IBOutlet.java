/*
 * Copyright (C) 2012 - 2015 Trillian Mobile AB
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
package org.robovm.objc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Used to mark a class method or field as an Objective-C property compliant
 * with a XCode interface builder outlet. Outlets must have the
 * <code>native</code> modifier and must not be <code>static</code>.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD })
public @interface IBOutlet {
    /**
     * The name of the Objective-C selector this outlet binds to. If not
     * specified the selector will be derived from the name of the method or
     * field.
     */
    String selector() default "";

    /**
     * The name of the outlet. If not specified the name will be derived from
     * the name of the method or field. In case of methods "is", "get" or "set"
     * will be dropped from the start of the method name and the rest will be
     * used as property name with the first character downcased.
     */
    String name() default "";
}
