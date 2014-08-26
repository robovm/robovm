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
package org.robovm.rt.bro;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.robovm.rt.bro.annotation.GlobalValue;

/**
 * Reads a {@link GlobalValue} lazily and optionally caches the result.
 */
public class LazyGlobalValue<T> {
    private final static Object NO_VALUE = new Object();

    private final Method getter;
    private final boolean constant;
    @SuppressWarnings("unchecked")
    private T cachedValue = (T) NO_VALUE;

    /**
     * Creates a new constant {@link LazyGlobalValue}. The value will only be
     * read once and that value will then always be returned by 
     * {@link #value()}.
     * 
     * @param owner the class where the {@link GlobalValue} method is to be 
     *        found.
     * @param getterName the name of the {@link GlobalValue} getter method.
     */
    public LazyGlobalValue(Class<?> owner, String getterName) {
        this(owner, getterName, true);
    }

    /**
     * Creates a new {@link LazyGlobalValue}.
     * 
     * @param owner the class where the {@link GlobalValue} method is to be 
     *        found.
     * @param getterName the name of the {@link GlobalValue} getter method.
     * @param constant if {@code true} the value will only read once and then 
     *        cached.
     */
    public LazyGlobalValue(Class<?> owner, String getterName, boolean constant) {
        if (owner == null) {
            throw new NullPointerException("owner");
        }
        if (getterName == null) {
            throw new NullPointerException("getterName");
        }
        this.constant = constant;
        try {
            this.getter = owner.getDeclaredMethod(getterName);
            this.getter.setAccessible(true);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("@GlobalValue getter method named "
                    + getterName + " not found in class " + owner.getName(), e);
        }
        if (this.getter.getAnnotation(GlobalValue.class) == null) {
            throw new IllegalArgumentException("Method " + this.getter 
                    + " is not a @GlobalValue method");
        }
    }

    /**
     * Returns the value of the {@link GlobalValue}.
     * 
     * @return the value.
     */
    @SuppressWarnings("unchecked")
    public T value() {
        try {
            if (!constant) {
                return (T) getter.invoke(null);
            }
            if (cachedValue == NO_VALUE) {
                cachedValue = (T) getter.invoke(null);
            }
            return cachedValue;
        } catch (Error | RuntimeException e) {
            throw e;
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new Error(e);
        }
    }
}
