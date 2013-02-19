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


/**
 * The abstract superclass of the classes which represent numeric base types
 * (that is {@link Byte}, {@link Short}, {@link Integer}, {@link Long},
 * {@link Float}, and {@link Double}.
 */
public abstract class Number implements java.io.Serializable {

    private static final long serialVersionUID = -8742448824652078965L;

    /**
     * Empty default constructor.
     */
    public Number() {
    }

    /**
     * Returns this object's value as a byte. Might involve rounding and/or
     * truncating the value, so it fits into a byte.
     *
     * @return the primitive byte value of this object.
     */
    public byte byteValue() {
        return (byte) intValue();
    }

    /**
     * Returns this object's value as a double. Might involve rounding.
     *
     * @return the primitive double value of this object.
     */
    public abstract double doubleValue();

    /**
     * Returns this object's value as a float. Might involve rounding.
     *
     * @return the primitive float value of this object.
     */
    public abstract float floatValue();

    /**
     * Returns this object's value as an int. Might involve rounding and/or
     * truncating the value, so it fits into an int.
     *
     * @return the primitive int value of this object.
     */
    public abstract int intValue();

    /**
     * Returns this object's value as a long. Might involve rounding and/or
     * truncating the value, so it fits into a long.
     *
     * @return the primitive long value of this object.
     */
    public abstract long longValue();

    /**
     * Returns this object's value as a short. Might involve rounding and/or
     * truncating the value, so it fits into a short.
     *
     * @return the primitive short value of this object.
     */
    public short shortValue() {
        return (short) intValue();
    }
}
