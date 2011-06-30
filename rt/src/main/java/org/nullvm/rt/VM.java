/*
 * Copyright (C) 2011 The NullVM Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.nullvm.rt;


/**
 *
 * @version $Id$
 */
public final class VM {

    /**
     * Returns the defining classes of the methods in the call stack. If 
     * <code>skipNum</code> is 0 the first entry in the returned array is the 
     * class of the method calling the caller of this method.
     * 
     * @param skipNum the number of classes to skip.
     * @param maxDepth the max number of classes to return. -1 for the entire
     *        stack.
     * @return the classes.
     */
    public static native final Class<?>[] getStackClasses(int skipNum, int maxDepth);

    /**
     * Searches an internal table of strings for a string equal to the specified
     * String. If the string is not in the table, it is added. Answers the
     * string contained in the table which is equal to the specified String. The
     * same string object is always answered for strings which are equal.
     * 
     * @param string the String to intern
     * @return the interned string equal to the specified String
     */
    public native static final String intern(String string);
    
    /**
     * Returns the bootstrap {@link ClassLoader}.
     * 
     * @return the {@link ClassLoader}.
     */
    public native static final ClassLoader getBootClassLoader();    
}
