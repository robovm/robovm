/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.lang.reflect;

import java.lang.annotation.Annotation;

/**
 * {@code AccessibleObject} is the superclass of all member reflection classes
 * (Field, Constructor, Method). AccessibleObject provides the ability to toggle
 * a flag controlling access checks for these objects. By default, accessing a
 * member (for example, setting a field or invoking a method) checks the
 * validity of the access (for example, invoking a private method from outside
 * the defining class is prohibited) and throws IllegalAccessException if the
 * operation is not permitted. If the accessible flag is set to true, these
 * checks are omitted. This allows privileged code, such as Java object
 * serialization, object inspectors, and debuggers to have complete access to
 * objects.
 *
 * @see Field
 * @see Constructor
 * @see Method
 * @see ReflectPermission
 */
public class AccessibleObject implements AnnotatedElement {

    /*
     * This class must be implemented by the VM vendor.
     */

    /**
     * Attempts to set the value of the accessible flag for all the objects in
     * the array provided. Only one security check is performed. Setting this
     * flag to {@code false} will enable access checks, setting to {@code true}
     * will disable them. If there is a security manager, checkPermission is
     * called with a {@code ReflectPermission("suppressAccessChecks")}.
     *
     * @param objects
     *            the accessible objects
     * @param flag
     *            the new value for the accessible flag
     * @throws SecurityException
     *             if the request is denied
     * @see #setAccessible(boolean)
     * @see ReflectPermission
     */
    public static void setAccessible(AccessibleObject[] objects, boolean flag)
            throws SecurityException {
        // TODO: Implement AccessibleObject.setAccessible(AccessibleObject[], boolean)
    }

    /**
     * Constructs a new {@code AccessibleObject} instance. {@code
     * AccessibleObject} instances can only be constructed by the virtual
     * machine.
     */
    protected AccessibleObject() {
        super();
    }

    /**
     * Indicates whether this object is accessible without security checks being
     * performed. Returns the accessible flag.
     *
     * @return {@code true} if this object is accessible without security
     *         checks, {@code false} otherwise
     */
    public boolean isAccessible() {
        // TODO: Implement AccessibleObject.isAccessible()
        return true;
    }

    /**
     * Attempts to set the value of the accessible flag. Setting this flag to
     * false will enable access checks, setting to true will disable them. If
     * there is a security manager, checkPermission is called with a
     * ReflectPermission("suppressAccessChecks").
     * 
     * @param flag the new value for the accessible flag
     * @see ReflectPermission
     * @throws SecurityException if the request is denied
     */
    public void setAccessible(boolean flag) throws SecurityException {
        // TODO: Implement AccessibleObject.setAccessible(boolean)
    }

    public native boolean isAnnotationPresent(Class<? extends Annotation> annotationType);

    public native Annotation[] getDeclaredAnnotations();

    public native Annotation[] getAnnotations();

    public native <T extends Annotation> T getAnnotation(Class<T> annotationType);
}
