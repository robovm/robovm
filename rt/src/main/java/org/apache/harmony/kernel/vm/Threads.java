/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
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

package org.apache.harmony.kernel.vm;

import java.security.AccessController;
import java.security.PrivilegedAction;

/**
 * <p>
 * This class must be implemented by the VM to support the Threading subsystem.
 * </p>
 */
public class Threads {
    
    private static final Threads INSTANCE = new Threads();

    /**
     * <p>
     * Retrieves an instance of the Threads service.
     * </p>
     * 
     * @return An instance of Threads.
     */
    public static Threads getInstance() {
        // TODO add class loader check
        return AccessController.doPrivileged(new PrivilegedAction<Threads>() {
            public Threads run() {
                return INSTANCE;
            }
        });
    }
    
    private Threads() {
        super();
    }
    
    /**
     * <p>
     * Unparks the {@link Thread} that's passed.
     * </p>
     * 
     * @param thread The {@link Thread} to unpark.
     */
    public native void unpark(Thread thread);
    
    /**
     * <p>
     * Park the {@link Thread#currentThread() current thread} for the specified
     * number of nanoseconds.
     * </p>
     * 
     * @param nanoseconds The number of nanoseconds to park the current thread.
     */
    public native void parkFor(long nanoseconds);
    
    /**
     * <p>
     * Park the {@link Thread#currentThread() current thread} until the
     * specified time, as defined by {@link System#currentTimeMillis()}.
     * </p>
     * 
     * @param time The absolute time to park the current thread until.
     */
    public native void parkUntil(long time);
}
