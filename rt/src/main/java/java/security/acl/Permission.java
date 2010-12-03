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

package java.security.acl;

/**
 * The interface that represents a permission.
 * <p>
 * It can be granted or denied to a {@link java.security.Principal Principal} 
 * using an {@link Acl}. 
 */
public interface Permission {

    
    /**
     * Checks whether the specified object equals this permission.
     * 
     * @param another
     *            the permission object to compare to this permission.
     * @return true if the specified permission object is equal to this, false
     *         if not.
     */
    boolean equals(Object another);
    
    /**
     * Returns the string representation of this permission.
     * 
     * @return the string representation of this permission.
     */
    String toString();
}
