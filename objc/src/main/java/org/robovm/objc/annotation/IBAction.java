/*
 * Copyright (C) 2012 - 2015 RoboVM AB
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
 * Used to mark a class method as an Objective-C method compliant with a Xcode
 * interface builder action. The method must have exactly zero, one or two
 * arguments, with the first argument (if any) being of type {@code UIResponder}
 * or any subtype, and the second argument (if any) being of type
 * {@code UIEvent}.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IBAction {
    /**
     * The name of the Objective-C selector this action binds to. If not
     * specified the selector will be derived from the name of the method. If
     * the method takes a {@code UIEvent} as second parameter the selector will
     * default to {@code <methodName>:withEvent:}
     */
    String selector() default "";
}
