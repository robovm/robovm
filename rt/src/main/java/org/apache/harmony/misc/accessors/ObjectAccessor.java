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

package org.apache.harmony.misc.accessors;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Hashtable;

/**
 * Provides the direct access to classes and objects. This class allows to overcome
 * the certain limitations of the reflection API, such as setting the constant fields or
 * allocating objects without calling its constructor. The following groups of operations
 * are supported:
 * <ul>
 *      <li><b>getField/MethodID,getStaticField/MethodID</b> - used to get ID for methods and fields.
 *      <li><b>getXXX/setXXX</b> - used to read and write non-static fields in objects
 *          (XXX stands for a field type);
 *      <li><b>getStaticXXX/setStaticXXX</b> - used to read and write static fields in classes
 *          (XXX stands for a field type);
 *      <li><b>invokeStaticXXX</b> - used to call static methods in a class (XXX means return type);
 *      <li><b>invokeVirtualXXX</b> - used to call virtual methods for object (XXX means return type);
 *      <li><b>invokeNonVirtualXXX</b> - used to call non-virtual methods for
 *          the given class and object (XXX means return type);
 *      <li><b>allocateObject, newInstance</b> - provides a fine control over object
 *      construction;
 *      <li><b>hasStaticInitializer</b> - informational methods about class;
 *      <li><b>monitorEnter/Exit</b> - enter/exit monitor associated with the given object
 * </ul>
 * Fields and methods are identified in the class with help of ID's those actual meaning
 * is implementation dependent.
 * Depending on a platform, ID's may represent the real offets in the physical memory,
 * though it is not always guaranteed. Unlike the {@link ArrayAccessor} class, users should not rely on
 * any correspondence between ID's and memory address space. However, it is guaranteed that ID's, once
 * obtained, are valid during the whole lifetime of the given class and can equally be
 * applied for all its instances.
 * <p>
 * No security checks are made while reading and writing object's fields, as well as while calling object's methods. In addition to
 * variables, this class also allows to set the values for constant fields within an object.
 * <p>
 * For accessing Array objects, please use the {@link ArrayAccessor} class.
 *
 * @see ArrayAccessor
 */
public class ObjectAccessor {

    /**
     * This class complies to singleton pattern.
     */
    private static ObjectAccessor instance;
    static ObjectAccessor getInstance() {
        if (instance == null) {
            System.loadLibrary("accessors"); //$NON-NLS-1$
            instance = new ObjectAccessor();
        }
        return instance;
    }
    private ObjectAccessor(){}

    private Hashtable methods = new Hashtable();


    //Boolean field access
    /**
     * Reads a boolean field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be read
     * @param fieldID field ID.
     * @return boolean field value
     * @see #setBoolean(Object, long, boolean)
     */
    public final native boolean getBoolean(Object o, long fieldID);

    /**
     * Reads a boolean field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID.
     * @return boolean field value
     * @see #setStaticBoolean(Class, long, boolean)
     */
    public final native boolean getStaticBoolean(Class c, long fieldID);

    /**
     * Writes a boolean field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be set.
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getBoolean(Object, long)
     */
    public final native void setBoolean(Object o, long fieldID, boolean value);

    /**
     * Writes a boolean field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getStaticBoolean(Class, long)
     */
    public final native void setStaticBoolean(Class c, long fieldID, boolean value);


    //Byte field access
    /**
     * Reads a byte field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be read
     * @param fieldID field ID.
     * @return byte field value
     * @see #setByte(Object, long, byte)
     */
    public final native byte getByte(Object o, long fieldID);

    /**
     * Reads a byte field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID.
     * @return byte field value
     * @see #setStaticByte(Class, long, byte)
     */
    public final native byte getStaticByte(Class c, long fieldID);

    /**
     * Writes a byte field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be set.
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getByte(Object, long)
     */
    public final native void setByte(Object o, long fieldID, byte value);

    /**
     * Writes a byte field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getStaticByte(Class, long)
     */
    public final native void setStaticByte(Class c, long fieldID, byte value);

    //Char field access
    /**
     * Reads a char field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be read
     * @param fieldID field ID.
     * @return char field value
     * @see #setChar(Object, long, char)
     */
    public final native char getChar(Object o, long fieldID);

    /**
     * Reads a char field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID.
     * @return char field value
     * @see #setStaticChar(Class, long, char)
     */
    public final native char getStaticChar(Class c, long fieldID);

    /**
     * Writes a char field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be set.
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getChar(Object, long)
     */
    public final native void setChar(Object o, long fieldID, char value);

    /**
     * Writes a char field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getStaticChar(Class, long)
     */
    public final native void setStaticChar(Class c, long fieldID, char value);

    //Short field access
    /**
     * Reads a short field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be read
     * @param fieldID field ID.
     * @return short field value
     * @see #setShort(Object, long, short)
     */
    public final native short getShort(Object o, long fieldID);

    /**
     * Reads a short field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID.
     * @return short field value
     * @see #setStaticShort(Class, long, short)
     */
    public final native short getStaticShort(Class c, long fieldID);

    /**
     * Writes a short field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be set.
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getShort(Object, long)
     */
    public final native void setShort(Object o, long fieldID, short value);

    /**
     * Writes a short field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getStaticShort(Class, long)
     */
    public final native void setStaticShort(Class c, long fieldID, short value);

    //Int field access
    /**
     * Reads a int field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be read
     * @param fieldID field ID.
     * @return int field value
     * @see #setInt(Object, long, int)
     */
    public final native int getInt(Object o, long fieldID);

    /**
     * Reads a int field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID.
     * @return int field value
     * @see #setStaticInt(Class, long, int)
     */
    public final native int getStaticInt(Class c, long fieldID);

    /**
     * Writes a int field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be set.
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getInt(Object, long)
     */
    public final native void setInt(Object o, long fieldID, int value);

    /**
     * Writes a int field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getStaticInt(Class, long)
     */
    public final native void setStaticInt(Class c, long fieldID, int value);

    //Long field access
    /**
     * Reads a long field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be read
     * @param fieldID field ID.
     * @return long field value
     * @see #setLong(Object, long, long)
     */
    public final native long getLong(Object o, long fieldID);

    /**
     * Reads a long field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID.
     * @return long field value
     * @see #setStaticLong(Class, long, long)
     */
    public final native long getStaticLong(Class c, long fieldID);

    /**
     * Writes a long field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be set.
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getLong(Object, long)
     */
    public final native void setLong(Object o, long fieldID, long value);

    /**
     * Writes a long field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getStaticLong(Class, long)
     */
    public final native void setStaticLong(Class c, long fieldID, long value);

    //Float field access
    /**
     * Reads a float field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be read
     * @param fieldID field ID.
     * @return float field value
     * @see #setFloat(Object, long, float)
     */
    public final native float getFloat(Object o, long fieldID);

    /**
     * Reads a float field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID.
     * @return float field value
     * @see #setStaticFloat(Class, long, float)
     */
    public final native float getStaticFloat(Class c, long fieldID);

    /**
     * Writes a float field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be set.
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getFloat(Object, long)
     */
    public final native void setFloat(Object o, long fieldID, float value);

    /**
     * Writes a float field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getStaticFloat(Class, long)
     */
    public final native void setStaticFloat(Class c, long fieldID, float value);

    //Double field access
    /**
     * Reads a double field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be read
     * @param fieldID field ID.
     * @return double field value
     * @see #setDouble(Object, long, double)
     */
    public final native double getDouble(Object o, long fieldID);

    /**
     * Reads a double field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID.
     * @return double field value
     * @see #setStaticDouble(Class, long, double)
     */
    public final native double getStaticDouble(Class c, long fieldID);

    /**
     * Writes a double field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be set.
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getDouble(Object, long)
     */
    public final native void setDouble(Object o, long fieldID, double value);

    /**
     * Writes a double field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getStaticDouble(Class, long)
     */
    public final native void setStaticDouble(Class c, long fieldID, double value);

    //Object field access
    /**
     * Reads an Object field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be read
     * @param fieldID field ID.
     * @return Object field value
     * @see #setObject(Object, long, Object)
     */
    public final native Object getObject(Object o, long fieldID);

    /**
     * Reads an Object field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID.
     * @return Object field value
     * @see #setStaticObject(Class, long, Object)
     */
    public final native Object getStaticObject(Class c, long fieldID);

    /**
     * Writes an Object field for the given object.
     * Use the {@link #getFieldID(Field)} method to obtain the ID for the specific field
     * within a class. For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     * @param o object those field needs to be set.
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getObject(Object, long)
     */
    public final native void setObject(Object o, long fieldID, Object value);

    /**
     * Writes an Object field for the given class.
     * Use the {@link #getStaticFieldID(Class, String)} method to obtain the ID for the specific field
     * within a class.
     * @param c class those field needs to be read
     * @param fieldID field ID
     * @param value field value to be set
     * @see #getStaticObject(Class, long)
     */
    public final native void setStaticObject(Class c, long fieldID, Object value);


    /**
     * Returns the ID for a field with the given name.
     * For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     *
     * @param c class containing field
     * @param name field name
     * @return field ID
     * @throws NoSuchFieldError if the field could not be found
     */
    public final long getFieldID(Class c, String name) {
        try {
            return getFieldID(c.getDeclaredField(name));
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (NoSuchFieldException e) {
            // Try to find the field in the superclass
            Class sc = c.getSuperclass();
            if (sc == null) {
                throw new NoSuchFieldError(e.getMessage());
            } else {
                return getFieldID(sc, name);
            }
        }
    }

    /**
     * Returns the ID for a field with the given name and type.
     * This may be faster than getting the field using it's name only.
     * For static fields, use the {@link #getStaticFieldID(Class, String)} method.
     *
     * @param c class containing field
     * @param name field name
     * @param type field type
     * @return field ID
     * @throws NoSuchFieldError if the field could not be found
     */
    public final long getFieldID(Class c, String name, Class type) {
        return getFieldID(c, name, getSystemName(type));
    }

    /**
     * Returns the ID for the static field with the given name.
     *
     * @param c class containing static field
     * @param name field name
     * @return field ID
     * @throws NoSuchFieldError if the field could not be found
     */
    public final long getStaticFieldID(Class c, String name) {
       return getFieldID(c, name);
    }

    /**
     * Returns the ID for the static field with the given name and type.
     * This may be faster than getting the field using it's name only.
     * @param c class containing static field
     * @param name field name
     * @param type field type
     * @return field ID
     * @throws NoSuchFieldError if the field could not be found
     */
    public final long getStaticFieldID(Class c, String name, Class type) {
        return getStaticFieldID(c, name, getSystemName(type));
    }

    /**
     * Returns the ID for the reflected field.
     *
     * @param f reflected field
     * @return field ID
     */
    public final native long getFieldID(Field f);

    /**
     * Returns the ID for the reflected static field.
     * Default implementation delegates to the
     * {@link #getFieldID(Field)} call.
     *
     * @param f reflected field
     * @return field ID
     */
    public final long getStaticFieldID(Field f) {
        return getFieldID(f);
    }


    /**
     * Returns the ID for the specified method or constructor.
     * Use class constants for primitive parameter types. For example,
     * for <code>byte</code> type use the {@link java.lang.Byte#TYPE} class.
     * @param c a class the method belongs to
     * @param name method name or <code>null</code> in case of constructor
     * @param parameterTypes array of parameter types.
     * @return method ID
     * @throws NoSuchMethodError if the method could not be found
     * @see #invokeVirtualVoid(Object, long, Object[])
     * @see #invokeNonVirtualVoid(Class, Object, long, Object[])
     * @see #newInstance(Class, long, Object[])
     */
    public final long getMethodID(Class c, String name, Class[] parameterTypes) {
        return getMethodID(c, name, parameterTypes, false);
    }

    private static long getMethodID(Class c, String name, Class[] parameterTypes, boolean isStatic) {
        try {
            String sig = "("; //$NON-NLS-1$
            for (int i=0; i < parameterTypes.length; i++) {
                sig += getSystemName(parameterTypes[i]);
            }
            sig += ")"; //$NON-NLS-1$
            if (name == null) {
                name = "<init>"; //$NON-NLS-1$
                sig += "V"; //$NON-NLS-1$
            } else {
                Method m = c.getDeclaredMethod(name, parameterTypes);
                sig += getSystemName(m.getReturnType());
            }
            return isStatic ? getStaticMethodID0(c, name, sig) : getMethodID0(c, name, sig);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new NoSuchMethodError(e.getMessage());
        }
    }


    /**
     * Returns the ID for the specified static method.
     * Use class constants for primitive parameter types. For example,
     * for <code>byte</code> type use the {@link java.lang.Byte#TYPE} class.
     * @param c a class the method belongs to
     * @param name method name or <code>null</code> in case of constructor
     * @param parameterTypes array of parameter types.
     * @return static method ID
     * @throws NoSuchMethodError if the method could not be found
     * @see #invokeVirtualVoid(Object, long, Object[])
     */
    public final long getStaticMethodID(Class c, String name, Class[] parameterTypes) {
        return getMethodID(c, name, parameterTypes, true);
    }

    /**
     * Returns the ID for the reflected constructor.
     * @param c reflected constructor
     * @return method ID
     * @see #invokeVirtualVoid(Object, long, Object[])
     * @see #newInstance(Class, long, Object[])
     */
    public final long getMethodID(Constructor c) {
        return getMethodID0(c);
    }

     /**
     * Returns the ID for the reflected method.
     * @param m reflected method
     * @return method ID
     * @see #invokeVirtualVoid(Object, long, Object[])
     * @see #invokeNonVirtualVoid(Class, Object, long, Object[])
     * @see #invokeStaticVoid(Class, long, Object[])
     * @see #newInstance(Class, long, Object[])
     */
    public final long getMethodID(Method m) {
        return getMethodID0(m);
    }

    private static native long getMethodID0(Class c, String name, String sig);

    private static native long getStaticMethodID0(Class c, String name, String sig);

    private static native long getMethodID0(Member m);


    private Class[] objectArrayTypes(Object[] args) {
        Class[] res = new Class[args.length];
        for (int i=0; i<args.length; i++) {
            res[i] = args[i] == null ? null : args[i].getClass();
        }
        return res;
    }

    /**
     * Invokes static void method with the given ID without security check.
     * Primitive type arguments should be wrapped with appropriate objects.
     * For example, byte value should be wrapped with {@link java.lang.Byte#TYPE}.
     * Use the {@link #getMethodID(Class, String, Class[])} call to obtain the method ID.
     * @param c a class where method is defined
     * @param methodID method ID
     * @param args array of arguments
     * @see #getMethodID(Class, String, Class[])
     */
    public final native void invokeStaticVoid(Class c, long methodID, Object[] args);


    /**
     * Invokes void method or constructor with the given ID without security check.
     * Primitive type arguments should be wrapped with appropriate objects.
     * For example, byte value should be wrapped with {@link java.lang.Byte#TYPE}.
     * Use the {@link #getMethodID(Class, String, Class[])} call to obtain the method ID.
     * @param methodID method ID
     * @param obj an object those method or constructor needs to be called
     * @param args array of arguments
     * @see #getMethodID(Class, String, Class[])
     */
    public final native void invokeVirtualVoid(Object obj, long methodID, Object[] args);


    /**
     * Invokes a non-virtual void method or constructor with the given ID without security check.
     * Primitive type arguments should be wrapped with appropriate objects.
     * For example, byte value should be wrapped with {@link java.lang.Byte#TYPE}.
     * Use the {@link #getMethodID(Class, String, Class[])} call to obtain the method ID.
     * @param c a class where method or constructor is defined
     * @param obj an object those method or constructor needs to be called
     * @param methodID method ID
     * @param args array of arguments
     * @see #getMethodID(Class, String, Class[])
     */
    public final native void invokeNonVirtualVoid(Class c, Object obj, long methodID, Object[] args);


    /**
     * Invokes a static reference-type method with the given ID without security check.
     * Primitive type arguments should be wrapped with appropriate objects.
     * For example, byte value should be wrapped with {@link java.lang.Byte#TYPE}.
     * Use the {@link #getMethodID(Class, String, Class[])} call to obtain the method ID.
     * @param c a class where method is defined
     * @param methodID method ID
     * @param args array of arguments
     * @see #getMethodID(Class, String, Class[])
     */
    public final native Object invokeStaticObject(Class c, long methodID, Object[] args);

    /**
     * Invokes reference-type method with the given ID without security check.
     * Primitive type arguments should be wrapped with appropriate objects.
     * For example, byte value should be wrapped with {@link java.lang.Byte#TYPE}.
     * Use the {@link #getMethodID(Class, String, Class[])} call to obtain the method ID.
     * @param methodID method ID
     * @param obj an object those method or constructor needs to be called
     * @param args array of arguments
     * @see #getMethodID(Class, String, Class[])
     */
    public final native Object invokeVirtualObject(Object obj, long methodID, Object[] args);


    /**
     * Invokes a non-virtual reference-type method with the given ID without security check.
     * Primitive type arguments should be wrapped with appropriate objects.
     * For example, byte value should be wrapped with {@link java.lang.Byte#TYPE}.
     * Use the {@link #getMethodID(Class, String, Class[])} call to obtain the method ID.
     * @param c a class where method or constructor is defined
     * @param obj an object those method or constructor needs to be called
     * @param methodID method ID
     * @param args array of arguments
     * @see #getMethodID(Class, String, Class[])
     */
    public final native Object invokeNonVirtualObject(Class c, Object obj, long methodID, Object[] args);


    /**
     * Invokes a static long method with the given ID without security check.
     * Primitive type arguments should be wrapped with appropriate objects.
     * For example, byte value should be wrapped with {@link java.lang.Byte#TYPE}.
     * Use the {@link #getMethodID(Class, String, Class[])} call to obtain the method ID.
     * @param c a class where method is defined
     * @param methodID method ID
     * @param args array of arguments
     * @see #getMethodID(Class, String, Class[])
     */
    public final native long invokeStaticLong(Class c, long methodID, Object[] args);

    /**
     * Invokes long method with the given ID without security check.
     * Primitive type arguments should be wrapped with appropriate objects.
     * For example, byte value should be wrapped with {@link java.lang.Byte#TYPE}.
     * Use the {@link #getMethodID(Class, String, Class[])} call to obtain the method ID.
     * @param methodID method ID
     * @param obj an object those method or constructor needs to be called
     * @param args array of arguments
     * @see #getMethodID(Class, String, Class[])
     */
    public final native long invokeVirtualLong(Object obj, long methodID, Object[] args);


    /**
     * Invokes a non-virtual long method with the given ID without security check.
     * Primitive type arguments should be wrapped with appropriate objects.
     * For example, byte value should be wrapped with {@link java.lang.Byte#TYPE}.
     * Use the {@link #getMethodID(Class, String, Class[])} call to obtain the method ID.
     * @param c a class where method or constructor is defined
     * @param obj an object those method or constructor needs to be called
     * @param methodID method ID
     * @param args array of arguments
     * @see #getMethodID(Class, String, Class[])
     */
    public final native long invokeNonVirtualLong(Class c, Object obj, long methodID, Object[] args);


    /**
     * Allocates new object of the given class without calling its constructor.
     * Constructor can be called independently with help of {@link #invokeNonVirtualVoid(Class, Object, long,  Object[])} method.
     * @param c A class those object needs to be allocated.
     * @return allocated object
     */
    public final native Object allocateObject(Class c);

    /**
     * Allocates new object of class c and invokes its constructor with the given ID
     * and args without security checks.
     * Primitive type arguments should be wrapped with appropriate objects.
     * For example, byte value should be wrapped with {@link java.lang.Byte#TYPE}.
     * Use the {@link #getMethodID(Class, String, Class[])} call to obtain the constructor ID.
     * @param methodID method ID
     * @param c class those instance needs to be created
     * @param args array of arguments
     * @return allocated object
     */
    public final native Object newInstance(Class c, long methodID, Object[] args);

    /**
     * Allocates new object of a class c and invokes noarg constructor without security check.
     *
     * @param c class those object needs to be created
     * @return allocated object
     */
    public final native Object newInstance(Class c);

    /**
     * Determines whether the class c has static initializer.
     * @return true if class c has static initializer, false otherwise
     */
    public final native boolean hasStaticInitializer(Class c);

    /**
     * calls monitorEnter java bytecode command. Acquire object <code>o</code> monitor
     * @param o object to lock
     */
    public native void monitorEnter(Object o);

      /**
       * calls monitorExit java bytecode command. To free object <code>o</code> monitor
       * @param o object to unlock
       */
    public native void monitorExit(Object o);

    private static final String getSystemName(Class cls) {
        if (cls == boolean.class) {
            return "Z"; //$NON-NLS-1$
        } else if (cls == char.class) {
            return "C"; //$NON-NLS-1$
        } else if (cls == byte.class) {
            return "B"; //$NON-NLS-1$
        } else if (cls == short.class) {
            return "S"; //$NON-NLS-1$
        } else if (cls == int.class) {
            return "I"; //$NON-NLS-1$
        } else if (cls == long.class) {
            return "J"; //$NON-NLS-1$
        } else if (cls == float.class) {
            return "F"; //$NON-NLS-1$
        } else if (cls == double.class) {
            return "D"; //$NON-NLS-1$
        } else if (cls == void.class) {
            return "V"; //$NON-NLS-1$
        } else { // Object type.
            String className = cls.getName().replace('.', '/');
            // Add reference to non-array reference types.
            return (cls.isArray() ? className : ('L' + className + ';'));
        }
    }

    private final native long getFieldID(Class c, String name, String sig);

    private final native long getStaticFieldID(Class c, String name, String sig);

    public final native long getGlobalReference(Object o);
    
    public final native void releaseGlobalReference(long ref);
    
    public final native Object getObjectFromReference(long ref);
}

