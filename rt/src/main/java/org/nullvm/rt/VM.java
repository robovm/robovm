/*
 * Copyright (C) 2011 The NullVM Open Source Project
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
package org.nullvm.rt;


/**
 *
 * @version $Id$
 */
public final class VM {

    /**
     * Returns the defining classes of the methods in the call stack. If 
     * <code>skipNum</code> is 0 the first entry in the returned array is the 
     * class of the method calling the caller of this method.
     * 
     * @param skipNum the number of classes to skip.
     * @param maxDepth the max number of classes to return. -1 for the entire
     *        stack.
     * @return the classes.
     */
    public static native final Class<?>[] getStackClasses(int skipNum, int maxDepth);

    /**
     * Searches an internal table of strings for a string equal to the specified
     * String. If the string is not in the table, it is added. Answers the
     * string contained in the table which is equal to the specified String. The
     * same string object is always answered for strings which are equal.
     * 
     * @param string the String to intern
     * @return the interned string equal to the specified String
     */
    public native static final String intern(String string);
    
    /**
     * Returns the bootstrap {@link ClassLoader}.
     * 
     * @return the {@link ClassLoader}.
     */
    public native static final ClassLoader getBootClassLoader();
    
    public native static final long getObjectAddress(Object object);
    
    public native static final int getInstanceFieldOffset(long field);
    
    public native static final long getClassFieldAddress(long field);
    
    public native static final Object getObject(long address);
    public native static final double getDouble(long address);
    public native static final float getFloat(long address);
    public native static final long getLong(long address);
    public native static final int getInt(long address);
    public native static final char getChar(long address);
    public native static final short getShort(long address);
    public native static final byte getByte(long address);
    public native static final boolean getBoolean(long address);
    public native static final void setObject(long address, Object value);
    public native static final void setDouble(long address, double value);
    public native static final void setFloat(long address, float value);
    public native static final void setLong(long address, long value);
    public native static final void setInt(long address, int value);
    public native static final void setChar(long address, char value);
    public native static final void setShort(long address, short value);
    public native static final void setByte(long address, byte value);
    public native static final void setBoolean(long address, boolean value);

    public native static final long getPointer(long address);
    public native static final void setPointer(long address, long value);
    
    public native static final long getStringUTFChars(String s);
    public native static final String newStringUTF(long address);
    public native static final long getBooleanArrayElements(boolean[] array);
    public native static final long getByteArrayElements(byte[] array);
    public native static final long getCharArrayElements(char[] array);
    public native static final long getShortArrayElements(short[] array);
    public native static final long getIntArrayElements(int[] array);
    public native static final long getLongArrayElements(long[] array);
    public native static final long getFloatArrayElements(float[] array);
    public native static final long getDoubleArrayElements(double[] array);
    public native static final boolean[] newBooleanArray(long address, int size);
    public native static final byte[] newByteArray(long address, int size);
    public native static final char[] newCharArray(long address, int size);
    public native static final short[] newShortArray(long address, int size);
    public native static final int[] newIntArray(long address, int size);
    public native static final long[] newLongArray(long address, int size);
    public native static final float[] newFloatArray(long address, int size);
    public native static final double[] newDoubleArray(long address, int size);
    
    public native static final long getPointerArrayElements(long[] array);
    public native static final long[] newPointerArray(long address, int size);
}
