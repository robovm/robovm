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

package org.apache.harmony.luni.platform;

/**
 * The platform address class is an unsafe virtualization of an OS memory block.
 */
public class PlatformAddress implements ICommonDataTypes, Comparable {

    /**
     * This final field defines the sentinel for an unknown address value.
     */
    static final long UNKNOWN = -1;

    /**
     * This final field defines the size of an address on this platform.
     */
    static final int SIZEOF = Platform.getMemorySystem().getPointerSize();

    /**
     * NULL is the canonical address with address value zero.
     */
    public static final PlatformAddress NULL = new PlatformAddress(0, 0);

    /**
     * INVALID is the canonical address with an invalid value
     * (i.e. a non-address).
     */
    public static final PlatformAddress INVALID =
        new PlatformAddress(UNKNOWN, UNKNOWN);

    public static final IMemorySpy memorySpy = new RuntimeMemorySpy();

    static final IMemorySystem osMemory = Platform.getMemorySystem();

    final long osaddr;

    final long size;

    PlatformAddress(long address, long size) {
        super();
        osaddr = address;
        this.size = size;
    }

    /**
     * Sending auto free to an address means that, when this subsystem has
     * allocated the memory, it will automatically be freed when this object is
     * collected by the garbage collector if the memory has not already been
     * freed explicitly.
     * 
     */
    public final void autoFree() {
        memorySpy.autoFree(this);
    }

    public PlatformAddress duplicate() {
        return PlatformAddressFactory.on(osaddr, size);
    }

    public PlatformAddress offsetBytes(int offset) {
        return PlatformAddressFactory.on(osaddr + offset, size - offset);
    }

    public PlatformAddress offsetBytes(long offset) {
        return PlatformAddressFactory.on(osaddr + offset, size - offset);
    }

    public final void moveTo(PlatformAddress dest, long numBytes) {
        osMemory.memmove(dest.osaddr, osaddr, numBytes);
    }

    public final boolean equals(Object other) {
        return (other instanceof PlatformAddress)
                && (((PlatformAddress) other).osaddr == osaddr);
    }

    public final int hashCode() {
        return (int) osaddr;
    }

    public final boolean isNULL() {
        return this == NULL;
    }

    public void free() {
        // Memory spys can veto the basic free if they determine the memory was
        // not allocated.
        if (memorySpy.free(this)) {
            osMemory.free(osaddr);
        }
    }

    public final void setAddress(int offset, PlatformAddress address) {
        osMemory.setAddress(osaddr + offset, address.osaddr);
    }

    public final PlatformAddress getAddress(int offset) {
        int addr = getInt(offset);
        return PlatformAddressFactory.on(addr);
    }

    public final void setByte(int offset, byte value) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JBYTE);
        osMemory.setByte(osaddr + offset, value);
    }

    public final void setByteArray(int offset, byte[] bytes, int bytesOffset,
            int length) {
        memorySpy.rangeCheck(this, offset, length * SIZEOF_JBYTE);
        osMemory.setByteArray(osaddr + offset, bytes, bytesOffset, length);
    }

    public final byte getByte(int offset) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JBYTE);
        return osMemory.getByte(osaddr + offset);
    }

    public final void getByteArray(int offset, byte[] bytes, int bytesOffset,
            int length) {
        memorySpy.rangeCheck(this, offset, length * SIZEOF_JBYTE);
        osMemory.getByteArray(osaddr + offset, bytes, bytesOffset, length);
    }

    public final void setShort(int offset, short value, Endianness order) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JSHORT);
        osMemory.setShort(osaddr + offset, value, order);
    }

    public final void setShort(int offset, short value) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JSHORT);
        osMemory.setShort(osaddr + offset, value);
    }

    public final short getShort(int offset, Endianness order) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JSHORT);
        return osMemory.getShort(osaddr + offset, order);
    }

    public final short getShort(int offset) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JSHORT);
        return osMemory.getShort(osaddr + offset);
    }

    public final void setInt(int offset, int value, Endianness order) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JINT);
        osMemory.setInt(osaddr + offset, value, order);
    }

    public final void setInt(int offset, int value) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JINT);
        osMemory.setInt(osaddr + offset, value);
    }

    public final int getInt(int offset, Endianness order) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JINT);
        return osMemory.getInt(osaddr + offset, order);
    }

    public final int getInt(int offset) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JINT);
        return osMemory.getInt(osaddr + offset);
    }

    public final void setLong(int offset, long value, Endianness order) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JLONG);
        osMemory.setLong(osaddr + offset, value, order);
    }

    public final void setLong(int offset, long value) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JLONG);
        osMemory.setLong(osaddr + offset, value);
    }

    public final long getLong(int offset, Endianness order) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JLONG);
        return osMemory.getLong(osaddr + offset, order);
    }

    public final long getLong(int offset) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JLONG);
        return osMemory.getLong(osaddr + offset);
    }

    public final void setFloat(int offset, float value, Endianness order) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JFLOAT);
        osMemory.setFloat(osaddr + offset, value, order);
    }

    public final void setFloat(int offset, float value) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JFLOAT);
        osMemory.setFloat(osaddr + offset, value);
    }

    public final float getFloat(int offset, Endianness order) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JFLOAT);
        return osMemory.getFloat(osaddr + offset, order);
    }

    public final float getFloat(int offset) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JFLOAT);
        return osMemory.getFloat(osaddr + offset);
    }

    public final void setDouble(int offset, double value, Endianness order) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JDOUBLE);
        osMemory.setDouble(osaddr + offset, value, order);
    }

    public final void setDouble(int offset, double value) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JDOUBLE);
        osMemory.setDouble(osaddr + offset, value);
    }

    public final double getDouble(int offset, Endianness order) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JDOUBLE);
        return osMemory.getDouble(osaddr + offset, order);
    }

    public final double getDouble(int offset) {
        memorySpy.rangeCheck(this, offset, SIZEOF_JDOUBLE);
        return osMemory.getDouble(osaddr + offset);
    }

    public final long toLong() {
        return osaddr;
    }

    public final String toString() {
        return "PlatformAddress[" + osaddr + "]"; //$NON-NLS-1$ //$NON-NLS-2$
    }

    public final long getSize() {
        return size;
    }

    public final int compareTo(Object other) {
        if (other == null) {
            throw new NullPointerException(); // per spec.
        }
        if (other instanceof PlatformAddress) {
            long otherPA = ((PlatformAddress) other).osaddr;
            if (osaddr == otherPA) {
                return 0;
            }
            return osaddr < otherPA ? -1 : 1;
        }

        throw new ClassCastException(); // per spec.
    }
}
