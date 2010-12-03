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
 * This is the interface that the memory watchers implement -- what they do with
 * this information is largely undefined.
 * 
 */
public interface IMemorySpy {

    public void alloc(PlatformAddress address);

    // Has a veto: true == do free,false = don't
    public boolean free(PlatformAddress address);

    public void rangeCheck(PlatformAddress address, int offset, int length)
            throws IndexOutOfBoundsException;

    /**
     * Requests that the given address is freed automatically when it becomes
     * garbage. If the address is alredy freed, or has not been notified as
     * allocated via this memory spy, then this call has no effect and completes
     * quietly.
     * 
     * @param address
     *            the address to be freed.
     */
    public void autoFree(PlatformAddress address);
}
