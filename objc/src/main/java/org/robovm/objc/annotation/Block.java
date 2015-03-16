/*
 * Copyright (C) 2014 RoboVM AB
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

import org.robovm.rt.bro.annotation.ByRef;
import org.robovm.rt.bro.annotation.ByVal;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.MachineSizedFloat;
import org.robovm.rt.bro.annotation.MachineSizedSInt;
import org.robovm.rt.bro.annotation.MachineSizedUInt;
import org.robovm.rt.bro.annotation.Marshaler;
import org.robovm.rt.bro.annotation.Pointer;

/**
 * Used to mark a method parameter or method return type as a block type. The
 * type of the parameter must be a functional interface with a single abstract
 * method, e.g. {@link Runnable}, {@link Callable} or any of the block
 * interfaces in the {@code org.robovm.objc.block} package. The RoboVM compiler
 * will generate a {@link Marshaler} class which will be used to marshal the
 * block type.
 * <p>
 * The interface type can be a generic type such as {@link Callable}. The RoboVM
 * compiler will resolve the actual type arguments from the method parameter or
 * return type and use these in the {@link Callback} method signature. Wrapper
 * classes for primitive types like {@link Integer} will be boxed/unboxed to
 * their primitive counterpart.
 * <p>
 * Type modifier annotations such as {@link ByVal} and {@link Pointer} can be
 * specified as the {@link #value()} of the {@link Block} annotation. The syntax 
 * is:<br>
 * <pre>
 *   RetAnno1 RetAnno2 (ParamAnno1_1 ParamAnno1_2 ..., ParamAnno2_1, ...)</pre>
 * Recognized annotations are {@link ByVal}, {@link ByRef}, {@link Pointer},
 * {@link MachineSizedFloat}, {@link MachineSizedSInt}, {@link MachineSizedUInt}
 * and {@link Block}.
 * <p>
 * Examples:<br>
 * {@code void (^)(void)} => {@code @Block Runnable}<br>
 * {@code void (^)(BOOL)} => {@code @Block VoidBooleanBlock}<br>
 * {@code CGRect (^)(long, char)} => 
 *       {@code @Block("@ByVal (@MachineSizedSInt,)") Block1<Long, Byte, CGRect>}<br>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface Block {
    /**
     * Specifies return and parameter annotations for the method in the target
     * interface. See {@link Block} for the syntax of this value. The default
     * is an empty string which means that no annotations will be applied.
     */
    String value() default "";
}
