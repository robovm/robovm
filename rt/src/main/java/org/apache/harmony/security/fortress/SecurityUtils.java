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

/**
* @author Alexander V. Astapchuk
*/

package org.apache.harmony.security.fortress;

import java.security.AccessControlContext;
import java.util.WeakHashMap;

import org.apache.harmony.security.internal.nls.Messages;

//FIXME: move this class under umbrella of protected packages -
// see lib/java.security: property 'package.access',
// so only trusted classes like Thread and AccessController will
// have an access to this class. 
// This is to remove dependency on VMStack, to reduce number 
// of VM2API-dependent classes.

/**
 * The class is used to perform an exchange of information between 
 * java.lang.Thread and java.security.AccessController.<br>
 * The data to exchange is inherited contexts for the Thread-s.  
 * 
 */
public final class SecurityUtils {

    // A map used to store inherited contexts.<br>
    // A thread is used as a key for the map and AccessControlContext 
    // passed to the putContext is used as a value.
    private static final WeakHashMap<Thread, AccessControlContext> ACC_CACHE = new WeakHashMap<Thread, AccessControlContext>();

    /**
     * This method to be invoked in the Thread's constructor. The first argument
     * (thread) must be Thread's this and the second must be a snapshot of the
     * current AccessControlContext:
     * <p>
     * <code>
     * Thread() {<br>
     * SecurityUtils.putContext(this,AccessController.getContext());<br>
     *  ...do the stuff you need...<br>
     * }<br>
     * </code>
     *
     * The method throws SecurityException if the method is called more than 
     * once for a given thread. The first call to <code>putContext</code> is
     * always performed in the Thread's constructor so this effectively means
     * that no one can replace the snapshot taken.
     * 
     * @throws SecurityException if a context for the passed 
     *     <code>thread</code> already exists in the map.
     * @throws NullPointerException if thread is null
     * @throws Error if context is null AND if null context is already stored 
     *     in the map 
     */
    public static void putContext(Thread thread, AccessControlContext context)
            throws SecurityException {
        if (thread == null) {
            throw new NullPointerException(Messages.getString("security.140")); //$NON-NLS-1$
        }
        synchronized (ACC_CACHE) {
            if (ACC_CACHE.containsKey(thread)) {
                throw new SecurityException(Messages.getString("security.141")); //$NON-NLS-1$
            }
            if (context == null) {
                // this only allowed once - for the very first thread.
                if (ACC_CACHE.containsValue(null)) {
                    throw new Error(Messages.getString("security.142")); //$NON-NLS-1$
                }
            }
            ACC_CACHE.put(thread, context);
        }
    }

    /**
     * Returns the AccessControlContext stored for a given thread.<br>
     * The method may return null - for the very first thread created 
     * by the VM which does not have inherited context.<br>
     * It may also return null if no Thread found in the map - that seems 
     * possible during VM startup process.
     */
    public static AccessControlContext getContext(Thread thread)
            throws SecurityException {

        // ~fixme: see 'fixme' at the top of the file
        /*
         Class cl = VMStack.getCallerClass(0);
         if (cl != AccessController.class) {
         throw new SecurityException("You ["+cl+"] do not have access to this resource.");
         }
         */

        synchronized (ACC_CACHE) {
            return ACC_CACHE.get(thread);
        }
    }
}