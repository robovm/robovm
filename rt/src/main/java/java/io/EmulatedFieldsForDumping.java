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

package java.io;

import org.apache.harmony.luni.internal.nls.Messages;

/**
 * An EmulatedFieldsForDumping is an object that represents a set of emulated
 * fields for an object being dumped. It is a concrete implementation for
 * ObjectOutputStream.PutField
 * 
 * 
 * @see ObjectOutputStream.PutField
 * @see EmulatedFieldsForLoading
 */
class EmulatedFieldsForDumping extends ObjectOutputStream.PutField {

    // The actual representation, with a more powerful API (set&get)
    private EmulatedFields emulatedFields;

    // Record the ObjectOutputStream that created this PutField for checking in the write method
    private final ObjectOutputStream oos;

    /**
     * Constructs a new instance of EmulatedFieldsForDumping.
     * 
     * @param streamClass
     *            a ObjectStreamClass, which describe the fields to be emulated
     *            (names, types, etc).
     */
    EmulatedFieldsForDumping(ObjectOutputStream oos, ObjectStreamClass streamClass) {
        super();
        emulatedFields = new EmulatedFields(streamClass.fields(),
                (ObjectStreamField[]) null);
        this.oos = oos;
    }

    /**
     * Return the actual EmulatedFields instance used by the receiver. We have
     * the actual work in a separate class so that the code can be shared. The
     * receiver has to be of a subclass of PutField.
     * 
     * @return array of ObjectSlot the receiver represents.
     */
    EmulatedFields emulatedFields() {
        return emulatedFields;
    }

    /**
     * Find and set the byte value of a given field named <code>name</code> in
     * the receiver.
     * 
     * @param name
     *            A String, the name of the field to set
     * @param value
     *            New value for the field.
     */
    @Override
    public void put(String name, byte value) {
        emulatedFields.put(name, value);
    }

    /**
     * Find and set the char value of a given field named <code>name</code> in
     * the receiver.
     * 
     * @param name
     *            A String, the name of the field to set
     * @param value
     *            New value for the field.
     */
    @Override
    public void put(String name, char value) {
        emulatedFields.put(name, value);
    }

    /**
     * Find and set the double value of a given field named <code>name</code>
     * in the receiver.
     * 
     * @param name
     *            A String, the name of the field to set
     * @param value
     *            New value for the field.
     */
    @Override
    public void put(String name, double value) {
        emulatedFields.put(name, value);
    }

    /**
     * Find and set the float value of a given field named <code>name</code>
     * in the receiver.
     * 
     * @param name
     *            A String, the name of the field to set
     * @param value
     *            New value for the field.
     */
    @Override
    public void put(String name, float value) {
        emulatedFields.put(name, value);
    }

    /**
     * Find and set the int value of a given field named <code>name</code> in
     * the receiver.
     * 
     * @param name
     *            A String, the name of the field to set
     * @param value
     *            New value for the field.
     */
    @Override
    public void put(String name, int value) {
        emulatedFields.put(name, value);
    }

    /**
     * Find and set the long value of a given field named <code>name</code> in
     * the receiver.
     * 
     * @param name
     *            A String, the name of the field to set
     * @param value
     *            New value for the field.
     */
    @Override
    public void put(String name, long value) {
        emulatedFields.put(name, value);
    }

    /**
     * Find and set the Object value of a given field named <code>name</code>
     * in the receiver.
     * 
     * @param name
     *            A String, the name of the field to set
     * @param value
     *            New value for the field.
     */
    @Override
    public void put(String name, Object value) {
        emulatedFields.put(name, value);
    }

    /**
     * Find and set the short value of a given field named <code>name</code>
     * in the receiver.
     * 
     * @param name
     *            A String, the name of the field to set
     * @param value
     *            New value for the field.
     */
    @Override
    public void put(String name, short value) {
        emulatedFields.put(name, value);
    }

    /**
     * Find and set the boolean value of a given field named <code>name</code>
     * in the receiver.
     * 
     * @param name
     *            A String, the name of the field to set
     * @param value
     *            New value for the field.
     */
    @Override
    public void put(String name, boolean value) {
        emulatedFields.put(name, value);
    }

    /**
     * Write the field values to the specified ObjectOutput.
     * 
     * @param output
     *            the ObjectOutput
     * 
     * @throws IOException
     *             If an IO exception happened when writing the field values.
     */
    @Override
    @Deprecated
    public void write(ObjectOutput output) throws IOException {
        if (!output.equals(oos)) {
            // luni.E0=Attempting to write to a stream that did not create this PutField
            throw new IllegalArgumentException(Messages.getString("luni.E0")); //$NON-NLS-1$
        }

        EmulatedFields.ObjectSlot[] slots = emulatedFields.slots();
        for (int i = 0; i < slots.length; i++) {
            EmulatedFields.ObjectSlot slot = slots[i];
            Object fieldValue = slot.getFieldValue();
            Class<?> type = slot.getField().getType();
            // WARNING - default values exist for each primitive type
            if (type == Integer.TYPE) {
                output.writeInt(fieldValue != null ? ((Integer) fieldValue)
                        .intValue() : 0);
            } else if (type == Byte.TYPE) {
                output.writeByte(fieldValue != null ? ((Byte) fieldValue)
                        .byteValue() : (byte) 0);
            } else if (type == Character.TYPE) {
                output.writeChar(fieldValue != null ? ((Character) fieldValue)
                        .charValue() : (char) 0);
            } else if (type == Short.TYPE) {
                output.writeShort(fieldValue != null ? ((Short) fieldValue)
                        .shortValue() : (short) 0);
            } else if (type == Boolean.TYPE) {
                output.writeBoolean(fieldValue != null ? ((Boolean) fieldValue)
                        .booleanValue() : false);
            } else if (type == Long.TYPE) {
                output.writeLong(fieldValue != null ? ((Long) fieldValue)
                        .longValue() : (long) 0);
            } else if (type == Float.TYPE) {
                output.writeFloat(fieldValue != null ? ((Float) fieldValue)
                        .floatValue() : (float) 0);
            } else if (type == Double.TYPE) {
                output.writeDouble(fieldValue != null ? ((Double) fieldValue)
                        .doubleValue() : (double) 0);
            } else {
                // Either array or Object
                output.writeObject(fieldValue);
            }
        }
    }
}
