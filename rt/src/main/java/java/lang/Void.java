/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package java.lang;

import java.lang.reflect.Method;

/**
 * Placeholder class for the Java keyword {@code void}.
 *
 * @since 1.1
 */
public final class Void extends Object {
    
    /**
     * The {@link Class} object that represents the primitive type {@code void}.
     */
    public static final Class<Void> TYPE = lookupType();

    // Note: This can't be set to "void.class", since *that* is
    // defined to be "java.lang.Void.TYPE";

    @SuppressWarnings("unchecked")
    private static Class<Void> lookupType() {
        Class<?> voidType = null;
        try {
            Method method = Runnable.class.getMethod("run", new Class[0]); //$NON-NLS-1$
            voidType = method.getReturnType();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (Class<Void>) voidType;
    }

	private Void() {
	}
}
