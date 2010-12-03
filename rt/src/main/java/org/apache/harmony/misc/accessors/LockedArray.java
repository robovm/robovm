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

/**
 * Wraps the locked primitive type array. Holds a reference to Array object
 * and its memory location. Objects of this type require a manual disposal
 * through the {@link #release()}method. Please note that the array address
 * is no longer valid after the {@link #release()}call is made.
 * <p>
 * Locked arrays can only be obtained with help of {@link ArrayAccessor} class.
 * @see ArrayAccessor
 */
public class LockedArray {

    private static final int NO_LOCK = 0;

    private static final int SHORT_LOCK = 1;

    private static final int LONG_LOCK = 2;

    LockedArray(Object array, long addr, boolean isLong) {
        this.array = array;
        this.addr = addr;
        this.lockType = isLong ? LONG_LOCK : SHORT_LOCK;
    }

    private Object array;

    private long addr;

    private int lockType;

    /**
     * Returns the memory address for this locked array.
     */
    public long getAddress() {
        return addr;
    }

    /**
     * Returns the primitive type array object associated with this locked
     * array.
     * 
     * @return primitive type array object
     */
    public Object getArray() {
        return array;
    }

    /**
     * Returns true if the array was locked for a long period of time.
     */
    public boolean isLongLock() {
        return lockType > SHORT_LOCK;
    }

    /**
     * Returns true if the array is currently locked, false otherwise.
     */
    public boolean isLocked() {
        return lockType > NO_LOCK;
    }

    /**
     * Unlocks the array. This method gives a hint to virtual
     * machine that this array no longer needs to be locked in memory. The
     * array address becomes invalid after this call and should no longer be
     * used. This method does nothing if the array was already released
     * once.
     * <p>
     * Default implementation of this method delegates to Release
     * <>ArrayElements or ReleasePrimitiveArrayCritical JNI calls in case of
     * long and short locks respectively.
     */
    public void release() {
        if (lockType > NO_LOCK) {
            ArrayAccessor.releaseArray(array, addr, lockType == LONG_LOCK);
        }
        lockType = NO_LOCK;
    }

    /**
     * Unlocks the array. This method gives a hint to virtual
     * machine that this array no longer needs to be locked in memory and virtual
     * machine should not copy values back to Java array.
     * The array address becomes invalid after this call and should no longer be
     * used. This method does nothing if the array was already released
     * once.
     * <p>
     * Default implementation of this method delegates to Release
     * <>ArrayElements or ReleasePrimitiveArrayCritical JNI calls in case of
     * long and short locks respectively with JNI_ABORT mode.
     */
    public void releaseNoCopy() {
        if (lockType > NO_LOCK) {
            ArrayAccessor.releaseArrayNoCopy(array, addr, lockType == LONG_LOCK);
        }
        lockType = NO_LOCK;
    }

}
