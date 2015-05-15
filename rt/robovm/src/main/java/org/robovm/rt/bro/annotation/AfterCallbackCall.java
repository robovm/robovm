/*
 * Copyright (C) 2013 RoboVM AB
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
 * Methods annotated with {@link AfterCallbackCall} will be called for the
 * parameters of a {@link Callback} method that were marshaled from pointers 
 * after the method has completed. This can be used to deallocate memory or 
 * update the native object or Java object that was the result of the 
 * marshaling. {@link AfterCallbackCall} methods are optional.
 * <p>
 * For {@link Callback} parameters that are marshaled from native pointers to 
 * Java objects the method must have a signature matching:
 * <pre>public static void afterNativeToJava(long before, T after, long flags)</pre>
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AfterCallbackCall {
}
