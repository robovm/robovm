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

public class MappedPlatformAddress extends PlatformAddress {
    
    MappedPlatformAddress(long address, long size) {
        super(address, size);
    }

    public final void mmapLoad() {
        memorySpy.rangeCheck(this, 0, (int) size * SIZEOF_JBYTE);
        osMemory.load(osaddr, size);
    }

    public final boolean mmapIsLoaded() {
        memorySpy.rangeCheck(this, 0, (int) size * SIZEOF_JBYTE);
        return osMemory.isLoaded(osaddr, size);
    }

    public final void mmapFlush() {
        memorySpy.rangeCheck(this, 0, (int) size * SIZEOF_JBYTE);
        osMemory.flush(osaddr, size);
    }
    
    public final void free(){
        if(memorySpy.free(this)){
            osMemory.unmap(osaddr, size);
        }
    }
    
    public PlatformAddress duplicate(){
        return PlatformAddressFactory.mapOn(osaddr, size);
    }
    
    public final PlatformAddress offsetBytes(int offset) {
        return PlatformAddressFactory.mapOn(osaddr + offset, size - offset);
    }

    public final PlatformAddress offsetBytes(long offset) {
        return PlatformAddressFactory.mapOn(osaddr + offset, size - offset);
    }
}
