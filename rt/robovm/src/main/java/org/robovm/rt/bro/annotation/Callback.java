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

import org.robovm.rt.VM;
import org.robovm.rt.bro.ptr.FunctionPtr;

/**
 * Marks a method as a callback which can be called from native code. Use
 * {@link VM#getCallbackMethodImpl(java.lang.reflect.Method)} or
 * {@link FunctionPtr#FunctionPtr(java.lang.reflect.Method)} to get a function
 * pointer which points to the {@code @Callback} annotated method.
 * <p>
 * See the <em>The Bro Java to Native Bridge</em> section in the <a
 * href="http://docs.robovm.com">user reference documentation</a> for more
 * information on how to call into native code and have native code call into
 * Java using the Bro framework.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Callback {
}
