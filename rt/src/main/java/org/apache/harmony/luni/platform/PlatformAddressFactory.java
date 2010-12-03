/* Licensed to the Apache Software Foundation (ASF) under one or more
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
package org.apache.harmony.luni.platform;

import java.io.IOException;

public class PlatformAddressFactory {

    public static PlatformAddress on(long value) {
    	return PlatformAddressFactory.on(value, PlatformAddress.UNKNOWN);
    }

    public static PlatformAddress on(long value, long size) {
        PlatformAddress addr = (value == 0) ? PlatformAddress.NULL : new PlatformAddress(value, size);
        return addr;
    }

    public static MappedPlatformAddress mapOn(long value, long size) {
        MappedPlatformAddress addr = new MappedPlatformAddress(value, size);
        return addr;
    }
    
    public static PlatformAddress allocMap(long fd, long start, long size, int mode) throws IOException{
        if (size == 0) {
            // if size is 0, call to mmap has incorrect behaviour on 
            // unix and windows, so return empty address
            return mapOn(0, 0);
        }
        long osAddress = PlatformAddress.osMemory.mmap(fd, start, size, mode);
        PlatformAddress newMemory = mapOn(osAddress, size);
        PlatformAddress.memorySpy.alloc(newMemory);
        return newMemory;
    }

    /**
     * Allocates a contiguous block of OS heap memory.
     * 
     * @param size The number of bytes to allocate from the system heap.
     * @return PlatformAddress representing the memory block.
     */
    public static PlatformAddress alloc(long size) {
    	long osAddress = PlatformAddress.osMemory.malloc(size);
    	PlatformAddress newMemory = on(osAddress, size);
    	PlatformAddress.memorySpy.alloc(newMemory);
    	return newMemory;
    }

    /**
     * Allocates a contiguous block of OS heap memory and initializes it to
     * a given value.
     * 
     * @param size The number of bytes to allocate from the system heap.
     * @param init The value to initialize the memory.
     * @return PlatformAddress representing the memory block.
     */
    public static PlatformAddress alloc(long size, byte init) {
        long osAddress = PlatformAddress.osMemory.malloc(size);
        PlatformAddress.osMemory.memset(osAddress, init, size);
        PlatformAddress newMemory = on(osAddress, size);
        PlatformAddress.memorySpy.alloc(newMemory);
        return newMemory;
    }
}
