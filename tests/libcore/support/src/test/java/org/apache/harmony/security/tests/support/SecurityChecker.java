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
* @author Alexey V. Varlamov
* @version $Revision$
*/

package org.apache.harmony.security.tests.support;

import java.security.Permission;

/**
 * Custom security manager
 */

public class SecurityChecker extends SecurityManager {

    public boolean enableAccess;

    public Permission checkTarget;

    public boolean checkAsserted;

    public SecurityChecker(Permission target, boolean enable) {
        checkAsserted = false;
        checkTarget = target;
        enableAccess = enable;
    }

    public void checkPermission(Permission p) {
        if (checkTarget.equals(p)) {
            checkAsserted = true;
            if (!enableAccess) {
                throw new SecurityException();
            }
        }
    }

    public SecurityChecker reset() {
        checkAsserted = false;
        return this;
    }
}