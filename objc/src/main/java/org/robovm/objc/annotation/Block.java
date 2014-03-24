/*
 * Copyright (C) 2014 Trillian Mobile AB
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
import java.util.concurrent.Callable;

import org.robovm.objc.ObjCBlock;
import org.robovm.rt.bro.annotation.Callback;

/**
 * Used to mark a method parameter as a block type. The type of the parameter
 * must be a functional interface with a single abstract method (e.g.
 * {@link Runnable} or {@link Callable}). The RoboVM compiler will generate
 * the appropriate {@link Callback} method and wrap the object in an
 * {@link ObjCBlock}. The interface type can be a generic type such as
 * {@link Callable}. The RoboVM compiler will resolve the actual type arguments
 * from the method parameter type and use these in the {@link Callback} method
 * signature. Primitive types will be boxed/unboxed.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface Block {
}
