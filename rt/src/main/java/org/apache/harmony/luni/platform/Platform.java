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

import org.apache.harmony.kernel.vm.VM;

/**
 * The Platform class gives access to the low-level underlying capabilities of
 * the operating system.
 * 
 * The platform is structured into operations on the process heap memory,
 * network subsystem, and file system through different OS components.
 * 
 * OS components are 'dangerous' in that they pass through the calls and
 * arguments to the OS with very little checking, and as such may cause fatal
 * exceptions in the runtime. Access to the OS components is restricted to
 * trusted code running on the system classpath.
 * 
 * @see IFileSystem
 * @see INetworkSystem
 * @see IMemorySystem
 */
public class Platform {

    static final IFileSystem FILE_SYSTEM = OSFileSystem.getOSFileSystem();

    static final IMemorySystem MEMORY_SYSTEM = OSMemory.getOSMemory();

    static final INetworkSystem NETWORK_SYSTEM = OSNetworkSystem.getOSNetworkSystem();

    /**
     * Checks to ensure that whoever is asking for the OS component is running
     * on the system classpath.
     */
    private static final void accessCheck() {
        if (VM.callerClassLoader() != null) {
            throw new SecurityException();
        }
    }

    /**
     * Answers the instance that interacts directly with the OS file system.
     * 
     * @return a low-level file system interface.
     */
    public static IFileSystem getFileSystem() {
        accessCheck();
        return FILE_SYSTEM;
    }

    /**
     * Answers the instance that interacts directly with the OS memory system.
     * 
     * @return a low-level memory system interface.
     */
    public static IMemorySystem getMemorySystem() {
        accessCheck();
        return MEMORY_SYSTEM;
    }

    /**
     * Answers the instance that interacts directly with the OS network system.
     * 
     * @return a low-level network system interface.
     */
    public static INetworkSystem getNetworkSystem() {
        accessCheck();
        return NETWORK_SYSTEM;
    }
}
