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

package org.apache.harmony.lang.annotation;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.annotation.AnnotationTypeMismatchException;
import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * This class represents member element of an annotation.
 * It consists of name and value, supplemented with element
 * definition information (such as declared type of element).
 * <br>The value may be one of the following types:
 * <ul>
 * <li> boxed primitive
 * <li> Class
 * <li> enum constant
 * <li> annotation (nested)
 * <li> one-dimensional array of the above
 * <li> Throwable
 * </ul>
 * The last type is specific for this implementation; a Throwable value
 * means that the error occured during parsing or resolution of corresponding
 * class-data structures and throwing is delayed until the element
 * is requested for value.
 *
 * @see android.lang.annotation.AnnotationFactory
 *
 * @author Alexey V. Varlamov, Serguei S. Zapreyev
 * @version $Revision$
 */
@SuppressWarnings({"serial"})
public class AnnotationMember implements Serializable {

    /**
     * Tag description of a Throwable value type.
     */
    protected static final char ERROR = '!';

    /**
     * Tag description of an array value type.
     */
    protected static final char ARRAY = '[';

    /**
     * Tag description of all value types except arrays and Throwables.
     */
    protected static final char OTHER = '*';

//    public static final char INT = 'I';
//    public static final char CHAR = 'C';
//    public static final char DOUBLE = 'D';
//    public static final char FLOAT = 'F';
//    public static final char BYTE = 'B';
//    public static final char LONG = 'J';
//    public static final char SHORT = 'S';
//    public static final char BOOL = 'Z';
//    public static final char CLASS = 'c';
//    public static final char ENUM = 'e';
//    public static final char ANTN = '@';

    private enum DefaultValues {NO_VALUE}

    /**
     * Singleton representing missing element value.
     */
    protected static final Object NO_VALUE = DefaultValues.NO_VALUE;

    protected final String name;
    protected final Object value; // a primitive value is wrapped to the corresponding wrapper class
    protected final char tag;
    // no sense to serialize definition info as it can be changed arbitrarily
    protected transient Class<?> elementType;
    protected transient Method definingMethod;


    /**
     * Creates a new element with specified name and value.
     * Definition info will be provided later when this
     * element becomes actual annotation member.
     * @param name element name, must not be null
     * @param val element value, should be of addmissible type,
     * as specified in the description of this class
     *
     * @see #setDefinition(AnnotationMember)
     */
    public AnnotationMember(String name, Object val) {
        this.name = name;
        value = val == null ? NO_VALUE : val;
        if (value instanceof Throwable) {
            tag = ERROR;
        } else if (value.getClass().isArray()) {
            tag = ARRAY;
        } else {
            tag = OTHER;
        }
    }

    /**
     * Creates the completely defined element.
     * @param name element name, must not be null
     * @param value element value, should be of addmissible type,
     * as specified in the description of this class
     * @param m element-defining method, reflected on the annotation type
     * @param type declared type of this element
     * (return type of the defining method)
     */
    public AnnotationMember(String name, Object val, Class type, Method m) {
        this(name, val);

        definingMethod = m;

        if (type == int.class) {
            elementType = Integer.class;
        } else if (type == boolean.class) {
            elementType = Boolean.class;
        } else if (type == char.class) {
            elementType = Character.class;
        } else if (type == float.class) {
            elementType = Float.class;
        } else if (type == double.class) {
            elementType = Double.class;
        } else if (type == long.class) {
            elementType = Long.class;
        } else if (type == short.class) {
            elementType = Short.class;
        } else if (type == byte.class) {
            elementType = Byte.class;
        } else {
            elementType = type;
        }
    }

    /**
     * Fills in element's definition info and returns this.
     */
    protected AnnotationMember setDefinition(AnnotationMember copy) {
        definingMethod = copy.definingMethod;
        elementType = copy.elementType;
        return this;
    }

    /**
     * Returns readable description of this annotation value.
     */
    public String toString() {
        if (tag == ARRAY) {
            StringBuilder sb = new StringBuilder(80);
            sb.append(name).append("=[");
            int len = Array.getLength(value);
            for (int i = 0; i < len; i++) {
                if (i != 0) sb.append(", ");
                sb.append(Array.get(value, i));
            }
            return sb.append("]").toString();
        } else {
            return name+ "=" +value;
        }
    }

    /**
     * Returns true if the specified object represents equal element
     * (equivalent name-value pair).
     * <br> A special case is the contained Throwable value; it is considered
     * transcendent so no other element would be equal.
     * @return true if passed object is equivalent element representation,
     * false otherwise
     * @see #equalArrayValue(Object)
     * @see java.lang.annotation.Annotation#equals(Object)
     */
    public boolean equals(Object obj) {
        if (obj == this) {
            // not a mere optimization,
            // this is needed for consistency with hashCode()
            return true;
        }
        if (obj instanceof AnnotationMember) {
            AnnotationMember that = (AnnotationMember)obj;
            if (name.equals(that.name) && tag == that.tag) {
                if (tag == ARRAY) {
                    return equalArrayValue(that.value);
                } else if (tag == ERROR) {
                    // undefined value is incomparable (transcendent)
                    return false;
                } else {
                    return value.equals(that.value);
                }
            }
        }
        return false;
    }

    /**
     * Returns true if the contained value and a passed object are equal arrays,
     * false otherwise. Appropriate overloaded method of Arrays.equals()
     * is used for equality testing.
     * @see java.util.Arrays#equals(java.lang.Object[], java.lang.Object[])
     * @return true if the value is array and is equal to specified object,
     * false otherwise
     */
    public boolean equalArrayValue(Object otherValue) {
        if (value instanceof Object[] && otherValue instanceof Object[]) {
            return Arrays.equals((Object[])value, (Object[])otherValue);
        }
        Class type = value.getClass();
        if (type != otherValue.getClass()) {
            return false;
        }
        if (type == int[].class) {
            return Arrays.equals((int[])value, (int[])otherValue);
        } else if (type == byte[].class) {
            return Arrays.equals((byte[])value, (byte[])otherValue);
        } else if (type == short[].class) {
            return Arrays.equals((short[])value, (short[])otherValue);
        } else if (type == long[].class) {
            return Arrays.equals((long[])value, (long[])otherValue);
        } else if (type == char[].class) {
            return Arrays.equals((char[])value, (char[])otherValue);
        } else if (type == boolean[].class) {
            return Arrays.equals((boolean[])value, (boolean[])otherValue);
        } else if (type == float[].class) {
            return Arrays.equals((float[])value, (float[])otherValue);
        } else if (type == double[].class) {
            return Arrays.equals((double[])value, (double[])otherValue);
        }
        return false;
    }

    /**
     * Computes hash code of this element. The formula is as follows:
     * <code> (name.hashCode() * 127) ^ value.hashCode() </code>
     * <br>If value is an array, one of overloaded Arrays.hashCode()
     * methods is used.
     * @return the hash code
     * @see java.util.Arrays#hashCode(java.lang.Object[])
     * @see java.lang.annotation.Annotation#hashCode()
     */
    public int hashCode() {
        int hash = name.hashCode() * 127;
        if (tag == ARRAY) {
            Class type = value.getClass();
            if (type == int[].class) {
                return hash ^ Arrays.hashCode((int[])value);
            } else if (type == byte[].class) {
                return hash ^ Arrays.hashCode((byte[])value);
            } else if (type == short[].class) {
                return hash ^ Arrays.hashCode((short[])value);
            } else if (type == long[].class) {
                return hash ^ Arrays.hashCode((long[])value);
            } else if (type == char[].class) {
                return hash ^ Arrays.hashCode((char[])value);
            } else if (type == boolean[].class) {
                return hash ^ Arrays.hashCode((boolean[])value);
            } else if (type == float[].class) {
                return hash ^ Arrays.hashCode((float[])value);
            } else if (type == double[].class) {
                return hash ^ Arrays.hashCode((double[])value);
            }
            return hash ^ Arrays.hashCode((Object[])value);
        } else {
            return hash ^ value.hashCode();
        }
    }

    /**
     * Throws contained error (if any) with a renewed stack trace.
     */
    public void rethrowError() throws Throwable {
        if (tag == ERROR) {
            // need to throw cloned exception for thread safety
            // besides it is better to provide actual stack trace
            // rather than recorded during parsing

            // first check for expected types
            if (value instanceof TypeNotPresentException) {
                TypeNotPresentException tnpe = (TypeNotPresentException)value;
                throw new TypeNotPresentException(tnpe.typeName(), tnpe.getCause());
            } else if (value instanceof EnumConstantNotPresentException) {
                EnumConstantNotPresentException ecnpe = (EnumConstantNotPresentException)value;
                throw new EnumConstantNotPresentException(ecnpe.enumType(), ecnpe.constantName());
            } else if (value instanceof ArrayStoreException) {
                ArrayStoreException ase = (ArrayStoreException)value;
                throw new ArrayStoreException(ase.getMessage());
            }
            // got some other error, have to go with deep cloning
            // via serialization mechanism
            Throwable error = (Throwable)value;
            StackTraceElement[] ste = error.getStackTrace();
            ByteArrayOutputStream bos = new ByteArrayOutputStream(
                    ste == null ? 512 : (ste.length + 1) * 80);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(error);
            oos.flush();
            oos.close();
            ByteArrayInputStream bis = new ByteArrayInputStream(bos
                    .toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            error = (Throwable)ois.readObject();
            ois.close();

            throw error;
        }
    }

    /**
     * Validates contained value against its member definition
     * and if ok returns the value.
     * Otherwise, if the value type mismatches definition
     * or the value itself describes an error,
     * throws appropriate exception.
     * <br> Note, this method may return null if this element was constructed
     * with such value.
     *
     * @see #rethrowError()
     * @see #copyValue()
     * @return actual valid value or null if no value
     */
    public Object validateValue() throws Throwable {
        if (tag == ERROR) {
            rethrowError();
        }
        if (value == NO_VALUE) {
            return null;
        }
        if (elementType == value.getClass()
                || elementType.isInstance(value)) { // nested annotation value
            return copyValue();
        } else {
            throw new AnnotationTypeMismatchException(definingMethod,
                    value.getClass().getName());
        }

    }


    /**
     * Provides mutation-safe access to contained value. That is, caller is free
     * to modify the returned value, it will not affect the contained data value.
     * @return cloned value if it is mutable or the original immutable value
     */
    public Object copyValue() throws Throwable
    {
        if (tag != ARRAY || Array.getLength(value) == 0) {
            return value;
        }
        Class type = value.getClass();
        if (type == int[].class) {
            return ((int[])value).clone();
        } else if (type == byte[].class) {
            return ((byte[])value).clone();
        } else if (type == short[].class) {
            return ((short[])value).clone();
        } else if (type == long[].class) {
            return ((long[])value).clone();
        } else if (type == char[].class) {
            return ((char[])value).clone();
        } else if (type == boolean[].class) {
            return ((boolean[])value).clone();
        } else if (type == float[].class) {
            return ((float[])value).clone();
        } else if (type == double[].class) {
            return ((double[])value).clone();
        }
        return ((Object[])value).clone();
    }
}
