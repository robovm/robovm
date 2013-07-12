/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.rt;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;


/**
 *
 * @version $Id$
 */
public final class VM {

    /**
     * Returns the VM's boot class path.
     */
    public static native String bootClassPath();

    /**
     * Returns the VM's class path.
     */
    public static native String classPath();

    /**
     * Returns the path to the executable currently running.
     */
    public static native String executablePath();
    
    /**
     * Returns the parent directory of the path to the executable currently running.
     */
    public static native String basePath();
    
    /**
     * Returns the VM's version.
     */
    public static String vmVersion() {
        return VMVersion.VERSION;
    }

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
    
    public native static final long allocateMemory(int size);
    public native static final long allocateMemoryUncollectable(int size);
    public native static final long allocateMemoryAtomic(int size);
    public native static final long freeMemoryUncollectable(long address);
    
    public native static void registerDisappearingLink(long address, Object obj);
    public native static void unregisterDisappearingLink(long address);
    
    public native static final long malloc(int size);
    public native static final void free(long address);
    
    public native static final <T> T allocateObject(Class<T> cls);
    
    public native static final ByteBuffer newDirectByteBuffer(long address, long capacity);
    
    public native static final void memcpy(long s1, long s2, long n);
    
    public native static final void memmove8(long s1, long s2, long n);

    public native static final void memmove16(long s1, long s2, long n);

    public native static final void memmove32(long s1, long s2, long n);

    public native static final void memmove64(long s1, long s2, long n);
    
    public native static final void memset(long s, byte c, long n);
    
    public native static final long getCallbackMethodImpl(Method method);
    
    public native static final void bindBridgeMethod(Method method, long impl);

    public native static final boolean isBridgeMethodBound(Method method);

    public native static final long getObjectAddress(Object object);
    
    public native static final Object castAddressToObject(long address);

    public native static final long getFieldAddress(Field field);
    
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
    public native static final String newStringNoCopy(char[] chars, int offset, int length);
    
    public native static final long getArrayValuesAddress(Object array);
    
    public native static final boolean[] newBooleanArray(long address, int size);
    public native static final byte[] newByteArray(long address, int size);
    public native static final char[] newCharArray(long address, int size);
    public native static final short[] newShortArray(long address, int size);
    public native static final int[] newIntArray(long address, int size);
    public native static final long[] newLongArray(long address, int size);
    public native static final float[] newFloatArray(long address, int size);
    public native static final double[] newDoubleArray(long address, int size);
}
