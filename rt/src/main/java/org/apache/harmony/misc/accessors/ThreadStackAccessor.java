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
 * Provides access to call stack trace.
 */
public class ThreadStackAccessor {
     private static ThreadStackAccessor instance;

     static ThreadStackAccessor getInstance() {
        if (instance == null) {
            instance = new ThreadStackAccessor();
        }
        return instance;
    }

     /** VM independent implementation of getCallerClasses */
     private class ClassContext extends SecurityManager {
        public Class[] getCallerClass() {
            return getClassContext();
        }
     }

     private ClassContext classContext = new ClassContext();

     private ThreadStackAccessor() {}
    
    /**
     * Returns the class from the specified depth in the stack. If the
     * specified depth is equal to zero then the caller of the caller of this
     * method should be returned. Reflection stack frames should not be taken
     * into account. 
     * 
     * @param depth the stack depth to get a caller class from. It is not
     *        negative one.
     * @return class a class from the stack. If there is no class in specified
     *         depth, null is returned.
     */
    public Class getCallerClass(int depth) {
        return getClasses(depth+1, true)[depth];
    }

    /**
     * Collects and returns the stack of invoked methods as an array of the
     * {@link StackTraceElement} objects. This method may be used by security
     * checks implementation. It is not supposed to be used by Throwable class.
     * <p>
     * Note, that this method itself is excluded from the returned stack trace. 
     * The most top (recently invoked) method is stored as a first element of the array.
     * 
     * @return a stack of invoked methods as an array.
     */
    public StackTraceElement[] getCallsTrace() {
        return (new Throwable()).getStackTrace();
    }

    /**
     * Collects and returns the stack of the current thread as an array of
     * classes. Resulting array should contain maxSize elements at the maximum.
     * Note that reflection stack frames should not be taken into account. The
     * caller of the caller of this method is stored as a first element of the
     * array. If considerPrivileged is true then the last element of the array
     * should be the caller of the most recent privileged method.  
     * <p>
     * This method may be used by security checks implementation. It is not
     * supposed to be used by Throwable class.
     * 
     * @param maxSize maximum size of resulting array. If maxSize is less than
     * zero array may contain any number of elements.
     * @param considerPrivileged indicates that privileged methods should be
     *        taken into account. It means if considerPrivileged is true the
     *        last element of resulting array should be the caller of the most
     *        recent privileged method. If considerPrivileged is false then
     *        privileged methods don't affect resulting array.
     *        
     * @return a stack of invoked methods as an array of classes.
     */
    public Class[] getClasses(int maxSize, boolean considerPrivileged){
        return classContext.getCallerClass();
    }
}

