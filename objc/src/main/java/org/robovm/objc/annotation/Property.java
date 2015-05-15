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

import org.robovm.objc.ObjCExtensions;
import org.robovm.objc.ObjCObject;

/**
 * Used to mark a class or interface method as an Objective-C property getter
 * or setter method. If the method is a member of a class it must have the 
 * <code>native</code> modifier. Property methods must not be 
 * <code>static</code>.
 * <p>
 * Property methods in ordinary classes can have any name if a 
 * {@link #selector()} has been specified. The property getter must return 
 * something and take 0 parameters and the setter must return nothing and take
 * exactly 1 parameter.
 * <p>
 * Property methods in extension classes (extending {@code NSExtensions} or
 * {@link ObjCExtensions} must be {@code static} and take an instance of the
 * target class as first parameter.
 * <p>
 * If no {@link #selector()} has been specified the name of
 * the method will be used to determine the selector for the property. In that
 * case the property methods must follow the Java beans property methods
 * naming convention: the getter name starts with "is" or "get" while the 
 * setter name starts with "set".
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Property {
    /**
     * The name of the Objective-C selector this property getter or setter binds
     * to. If not specified the selector will be derived from the name of the 
     * method.
     */
    String selector() default "";
    
    /**
     * The name of the property. If not specified the name will be derived from
     * the name of the method. "is", "get" or "set" will be dropped from the
     * start of the method name and the rest will be used as property name with
     * the first character downcased.
     */
    String name() default "";
    
    /**
     * Specifies that when a property is set a strong reference from the 
     * Objective-C object the property belongs to to the Java instance being set
     * should be created. This prevents the Java instance from being GCed until 
     * the Objective-C object instance is deallocated. This is typically used 
     * for Objective-C properties which don't retain their values (either 
     * specified as {@code assign} or {@code weak} in Objective-C).
     * <p>
     * This attribute is only meaningful when set on a setter method. If 
     * specified on a getter method it will be ignored.
     * <p>
     * NOTE: There must be a corresponding getter method in the class. This will
     * be used to determine the old value of the property.
     * {@link ObjCObject#removeStrongRef(Object)} will be called passing the old
     * value if not {@code null}. If the property methods follow the Java beans
     * naming convention the getter method can be automatically determined.
     * Otherwise the {@link #name()} has to be set to the same value on both
     * the getter and setter methods.
     * 
     * @see ObjCObject#addStrongRef(Object)
     * @see ObjCObject#removeStrongRef(Object)
     */
    boolean strongRef() default false;
}
