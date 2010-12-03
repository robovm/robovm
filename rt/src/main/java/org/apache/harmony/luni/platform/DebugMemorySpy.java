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
 * This memory spy tries to be as protective as possible, and fail as early as
 * posible for misbehaving allocators.
 * 
 */
final class DebugMemorySpy extends AbstractMemorySpy {

    private final boolean stackDump = true;

    public DebugMemorySpy() {
        super();
        startSpy();
    }

    public boolean free(PlatformAddress address) {
        boolean vetoed = super.free(address);
        if (!vetoed && stackDump) {
            Thread.dumpStack();
        }
        return vetoed;
    }

    /*
     * If this is memory that we allocated then we can check the access is
     * within range. However, we cannot check the range for memory that has been
     * allocated by the OS, or for addresses that we have computed. i.e. it is
     * quite possible that the range checker does <em>not</em> catch some
     * buffer overruns.
     */
    public void rangeCheck(PlatformAddress address, int offset, int length)
            throws IndexOutOfBoundsException {
        AddressWrapper wrapper = (AddressWrapper) memoryInUse.get(address);
        if (wrapper != null) {
            PlatformAddress accessStart = address.offsetBytes(offset);
            PlatformAddress accessEnd = accessStart.offsetBytes(length);
            PlatformAddress allocStart = wrapper.shadow;
            PlatformAddress allocEnd = allocStart.offsetBytes(address.size);
            boolean under = (accessStart.compareTo(allocStart)) == -1;
            boolean over = (accessEnd.compareTo(allocEnd)) == 1;
            if (under || over) {
                System.err.println("Memory Spy! Access out of allocated range"); //$NON-NLS-1$
                System.err
                        .println("\tAlloc range  : [" + allocStart + " ... " + allocEnd + "]"); //$NON-NLS-1$//$NON-NLS-2$//$NON-NLS-3$
                System.err
                        .println("\tAccess range : [" + accessStart + " ... " + accessEnd + "]"); //$NON-NLS-1$//$NON-NLS-2$ //$NON-NLS-3$
                if (stackDump) {
                    Thread.dumpStack();
                }
                // throw new IndexOutOfBoundsException();
            }
        }
    }

    private void startSpy() {
        Thread spy = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        orphanedMemory(notifyQueue.remove()); // Blocks until
                                                                // notified of
                                                                // collected
                                                                // reference
                    } catch (InterruptedException e) {
                        // Ignore interruptions
                    }
                }
            }
        });
        spy.setDaemon(true);
        spy.setName("Platform Interface Memory Spy"); //$NON-NLS-1$
        spy.setPriority(Thread.MAX_PRIORITY);
        spy.start();
    }
}
