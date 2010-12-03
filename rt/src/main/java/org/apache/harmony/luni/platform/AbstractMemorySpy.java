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


import java.lang.ref.ReferenceQueue;
import java.lang.ref.Reference;
import java.lang.ref.PhantomReference;
import java.util.HashMap;
import java.util.Map;

/**
 * Abstract implementation for the OS memory spies.
 * 
 */
abstract class AbstractMemorySpy implements IMemorySpy {

    // TODO: figure out how to prevent this being a synchronization bottleneck
    protected Map<PlatformAddress,AddressWrapper> memoryInUse = new HashMap<PlatformAddress, AddressWrapper>(); // Shadow to Wrapper

    protected Map<Reference,PlatformAddress> refToShadow = new HashMap<Reference, PlatformAddress>(); // Reference to Shadow

    protected ReferenceQueue<Object> notifyQueue = new ReferenceQueue<Object>();

       final class AddressWrapper {
        final PlatformAddress shadow;

        final PhantomReference<PlatformAddress> wrAddress;

        volatile boolean autoFree = false;

        AddressWrapper(PlatformAddress address) {
            super();
            this.shadow = address.duplicate();
            this.wrAddress = new PhantomReference<PlatformAddress>(address, notifyQueue);
        }
    }

    public AbstractMemorySpy() {
        super();
    }

    public void alloc(PlatformAddress address) {
        AddressWrapper wrapper = new AddressWrapper(address);
        synchronized (this) {
            memoryInUse.put(wrapper.shadow, wrapper);
            refToShadow.put(wrapper.wrAddress, wrapper.shadow);
        }
    }

    public boolean free(PlatformAddress address) {
        AddressWrapper wrapper;
        synchronized (this) {
            wrapper = memoryInUse.remove(address);
        }
        if (wrapper == null) {
            // Attempt to free memory we didn't alloc
            System.err
                    .println("Memory Spy! Fixed attempt to free memory that was not allocated " + address); //$NON-NLS-1$
        }
        return wrapper != null;
    }

    public void rangeCheck(PlatformAddress address, int offset, int length)
            throws IndexOutOfBoundsException {
        // Do nothing
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.apache.harmony.luni.platform.struct.IMemorySpy#autoFree(org.apache.harmony.luni.platform.struct.PlatformAddress)
     */
    public void autoFree(PlatformAddress address) {
        AddressWrapper wrapper;
        synchronized (this) {
            wrapper = memoryInUse.get(address);
        }
        if (wrapper != null) {
            wrapper.autoFree = true;
        }
    }

    protected void orphanedMemory(Reference ref) {
        AddressWrapper wrapper;
        synchronized (this) {
            PlatformAddress shadow = refToShadow.remove(ref);
            wrapper = memoryInUse.get(shadow);
            if (wrapper != null) {
                // There is a leak if we were not auto-freeing this memory.
                if (!wrapper.autoFree) {
                    System.err
                            .println("Memory Spy! Fixed memory leak by freeing " + wrapper.shadow); //$NON-NLS-1$
                }
                wrapper.shadow.free();
            }
        }
        ref.clear();
    }
}
