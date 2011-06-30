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

package sun.misc;

import java.lang.reflect.Field;

import org.apache.harmony.kernel.vm.Objects;
import org.apache.harmony.kernel.vm.Threads;
import org.apache.harmony.kernel.vm.VM;

/**
 * <p>The Unsafe service.</p>
 *
 */
public class Unsafe {

    private static final Unsafe INSTANCE = new Unsafe();
    
    /**
     * <p>
     * Retrieves an instance of this service.
     * </p>
     * 
     * @return An instance of Unsafe.
     */
    public static Unsafe getUnsafe() {
        /* Check that the caller of this method is in system code (i.e. on the
         * bootclasspath).  Unsafe methods are not designed to be called directly
         * by applications.  We assume that system code will not reveal the instance.
         */
        if (VM.callerClassLoader() != null) {
            throw new SecurityException("Unsafe");
        }

        // NullVM change. Not calling AccessController.doPrivileged() anymore.
        return INSTANCE;
//        return AccessController.doPrivileged(new PrivilegedAction<Unsafe>() {
//            public Unsafe run() {
//                return INSTANCE;
//            }
//        });
    }
    
    private Objects objects;
    private Threads threads;
    
    private Unsafe() {
        super();
        this.objects = Objects.getInstance();
        this.threads = Threads.getInstance();
    }

    /**
     * <p>
     * Retrieves the offset value of the {@link Field} for use by other methods
     * in this class.
     * </p>
     * 
     * @param field The {@link Field} to retrieve the offset for.
     * @return The offset value.
     */
    public native long objectFieldOffset(Field field);

    /**
     * <p>
     * Compares and swaps the value of an int-typed field on an Object instance.
     * </p>
     * 
     * @param object The instance containing the field.
     * @param fieldOffset The offset value of the field.
     * @param expected The expected value of the field.
     * @param update The new value to write to the field.
     * @return <code>true</code> if the field was updated, <code>false</code>
     *         otherwise.
     */
    public native boolean compareAndSwapInt(Object object, long fieldOffset, int expected, int update);

    /**
     * <p>
     * Compares and swaps the value of a long-typed field on an Object instance.
     * </p>
     * 
     * @param object The instance containing the field.
     * @param fieldOffset The offset value of the field.
     * @param expected The expected value of the field.
     * @param update The new value to write to the field.
     * @return <code>true</code> if the field was updated, <code>false</code>
     *         otherwise.
     */
    public native boolean compareAndSwapLong(Object object, long fieldOffset, long expected,
            long update);

    /**
     * <p>
     * Compares and swaps the value of an Object-typed field on an Object
     * instance.
     * </p>
     * 
     * @param object The instance containing the field.
     * @param fieldOffset The offset value of the field.
     * @param expected The expected value of the field.
     * @param update The new value to write to the field.
     * @return <code>true</code> if the field was updated, <code>false</code>
     *         otherwise.
     */
    public native boolean compareAndSwapObject(Object object, long fieldOffset, Object expected,
            Object update);

    /**
     * <p>
     * Retrieves the base offset for the array Class given. The Class passed
     * MUST me any array type, such that the method {@link Class#isArray()}
     * returns <code>true</code>. For example, <code>int[].class</code>.
     * </p>
     * 
     * @param clazz The array Class object.
     * @return The base offset value.
     */
    public int arrayBaseOffset(Class<?> clazz) {
        return objects.getArrayBaseOffset(clazz);
    }

    /**
     * <p>
     * Retrieves the array index scale for the array Class given. The index
     * scale is the value used to determine the offset of a particular element
     * in the array given the array's base offset and an index. The following
     * code snippet illustrates the usage.
     * </p>
     * 
     * <pre>
     * int base = unsafe.arrayBaseOffset(int[].class);
     * 
     * int scale = unsafe.arrayIndexScale(int[].class);
     * 
     * int elementIdx = 1;
     * 
     * int[] array = { 0, 1, 2 };
     * 
     * long offsetForIdx = base + (elementIdx * scale);
     * </pre>
     * 
     * <p>
     * The Class passed MUST me any array type, such that the method
     * {@link Class#isArray()} returns <code>true</code>. For example,
     * <code>int[].class</code>.
     * </p>
     * 
     * @param clazz The array Class object.
     * @return The index scale value.
     */
    public int arrayIndexScale(Class<?> clazz) {
        return objects.getArrayIndexScale(clazz);
    }

    /**
     * Writes an int value to an Object's field using ordered
     * setting. Ordered setting of a field is equivalent to the
     * volatile setting of that field except that it does not ensure
     * that the change is immediately available to other threads.
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the int field in the object.
     * @param newValue The value to write.
     */
    public void putOrderedInt(Object object, long fieldOffset,
                              int newValue) {
        // should probably implement less strict implementation
        putIntVolatile(object, fieldOffset, newValue);
    }

    /**
     * <p>
     * Writes an int value to an Object's field as though it were declared
     * <code>volatile</code>.
     * </p>
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public void putIntVolatile(Object object, long fieldOffset, int newValue) {
        objects.putIntVolatile(object, fieldOffset, newValue);
    }

    /**
     * <p>
     * Reads an int value from an Object's field as though it were declared
     * <code>volatile</code>.
     * </p>
     * 
     * @param object The instance containing the field to read from.
     * @param fieldOffset The offset of the field to read from.
     * @return The value that was read.
     */
    public int getIntVolatile(Object object, long fieldOffset) {
        return objects.getIntVolatile(object, fieldOffset);
    }

    /**
     * Writes a long value to an Object's field using ordered
     * setting. Ordered setting of a field is equivalent to the
     * volatile setting of that field except that it does not ensure
     * that the change is immediately available to other threads.
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public void putOrderedLong(Object object, long fieldOffset,
                               long newValue) {
        // should probably implement less strict implementation
        putLongVolatile(object, fieldOffset, newValue);
    }

    /**
     * <p>
     * Writes a long value to an Object's field as though it were declared
     * <code>volatile</code>.
     * </p>
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public void putLongVolatile(Object object, long fieldOffset, long newValue) {
        objects.putLongVolatile(object, fieldOffset, newValue);
    }

    /**
     * <p>
     * Reads a long value from an Object's field as though it were declared
     * <code>volatile</code>.
     * </p>
     * 
     * @param object The instance containing the field to read from.
     * @param fieldOffset The offset of the field to read from.
     * @return The value that was read.
     */
    public long getLongVolatile(Object object, long fieldOffset) {
        return objects.getLongVolatile(object, fieldOffset);
    }

    /**
     * <p>
     * Writes an Object reference value to an Object's field.
     * </p>
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public void putObject(Object object, long fieldOffset,
                          Object newValue) {
        // should probably implement less strict implementation
        putOrderedObject(object, fieldOffset, newValue);
    }

    /**
     * Writes an Object reference value to an Object's field using
     * ordered setting. Ordered setting of a field is equivalent to
     * the volatile setting of that field except that it does not
     * ensure that the change is immediately available to other
     * threads.
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public void putOrderedObject(Object object, long fieldOffset,
                                 Object newValue) {
        // should probably implement less strict implementation
        putObjectVolatile(object, fieldOffset, newValue);
    }

    /**
     * <p>
     * Writes an Object reference value to an Object's field as though it were
     * declared <code>volatile</code>.
     * </p>
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public void putObjectVolatile(Object object, long fieldOffset, Object newValue) {
        objects.putObjectVolatile(object, fieldOffset, newValue);
    }

    /**
     * <p>
     * Writes an int value to an Object's field as though it were declared
     * <code>volatile</code>.
     * </p>
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public Object getObjectVolatile(Object object, long fieldOffset) {
        return objects.getObjectVolatile(object, fieldOffset);
    }

    /**
     * <p>
     * Writes a long value to an Object's field.
     * </p>
     * 
     * @param object The instance containing the field to write to.
     * @param fieldOffset The offset of the field to write to.
     * @param newValue The value to write.
     */
    public void putLong(Object object, long fieldOffset, long newValue) {
        objects.putLong(object, fieldOffset, newValue);
    }

    /**
     * <p>
     * Reads a long value from an Object's field.
     * </p>
     * 
     * @param object The instance containing the field to read from.
     * @param fieldOffset The offset of the field to read from.
     * @return The value that was read.
     */
    public long getLong(Object object, long fieldOffset) {
        return objects.getLong(object, fieldOffset);
    }

    /**
     * <p>
     * Unparks the {@link Thread} that's passed.
     * </p>
     * 
     * @param thread The {@link Thread} to unpark.
     */
    public void unpark(Thread thread) {
        threads.unpark(thread);
    }

    /**
     * <p>
     * Parks the {@link Thread#currentThread() current thread} either for a set
     * number of nanoseconds or until a future point in time.
     * </p>
     * 
     * @param timestamp If <code>true</code> <code>nanosOrTimestamp</code>
     *        should be consider as a timestamp based on
     *        {@link System#currentTimeMillis()}. If <code>false</code>,
     *        then <code>nanosOrTimestamp</code> should be considered as a
     *        relative number of nanoseconds from when this method was called;
     *        the value <code>0L</code> can be used in conjunction with this
     *        to indicate that time is not a factor when parking the thread.
     * @param nanosOrTimestamp Either a relative number of nanoseconds or a
     *        timestamp in milliseconds as defined by
     *        {@link System#currentTimeMillis()}.
     */
    public void park(boolean timestamp, long nanosOrTimestamp) {
        if (timestamp) {
            threads.parkFor(nanosOrTimestamp);
        } else {
            threads.parkUntil(nanosOrTimestamp);
        }
    }
}
