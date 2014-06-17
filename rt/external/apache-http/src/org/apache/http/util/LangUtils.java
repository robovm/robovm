/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/util/LangUtils.java $
 * $Revision: 503413 $
 * $Date: 2007-02-04 06:22:14 -0800 (Sun, 04 Feb 2007) $
 *
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.util;

/**
 * A set of utility methods to help produce consistent
 * {@link Object#equals equals} and {@link Object#hashCode hashCode} methods.
 *  
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 * 
 * @since 4.0
 */
public final class LangUtils {
    
    public static final int HASH_SEED = 17;
    public static final int HASH_OFFSET = 37;

    /** Disabled default constructor. */
    private LangUtils() {
    }

    public static int hashCode(final int seed, final int hashcode) {
        return seed * HASH_OFFSET + hashcode;
    }

    public static int hashCode(final int seed, final boolean b) {
        return hashCode(seed, b ? 1 : 0);
    }
    
    public static int hashCode(final int seed, final Object obj) {
        return hashCode(seed, obj != null ? obj.hashCode() : 0);
    }
    
    public static boolean equals(final Object obj1, final Object obj2) {
        return obj1 == null ? obj2 == null : obj1.equals(obj2);
    }

    public static boolean equals(final Object[] a1, final Object[] a2) {
        if (a1 == null) {
            if (a2 == null) {
                return true;
            } else {
                return false;
            }
        } else {
            if (a2 != null && a1.length == a2.length) {
                for (int i = 0; i < a1.length; i++) {
                    if (!equals(a1[i], a2[i])) {
                        return false;
                    }
                }
                return true;
            } else {
                return false;
            }
        }
    }
    
}
