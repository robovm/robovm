/*
 * Copyright (C) 2012 RoboVM AB
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

import org.robovm.rt.bro.Bro;

/**
 *
 * @version $Id$
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Bridge {
    public String symbol() default "";

    /**
     * Specifies that this {@link Bridge} method is bound dynamically for each
     * invocation. The function pointer must be passed in as the first parameter
     * to the {@link Bridge} annotated method as a {@link Pointer} {@code long}.
     */
    boolean dynamic() default false;

    /**
     * Set to {@code true} to make this {@link Bridge} method optional. If
     * {@code true} the binding process ({@link Bro#bind()}) will not fail even
     * if the symbol of this {@link Bridge} method isn't available. Instead
     * a call to the method will throw {@link UnsatisfiedLinkError}. 
     */
    boolean optional() default false;
}
