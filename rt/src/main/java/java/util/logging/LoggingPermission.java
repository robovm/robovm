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

package java.util.logging;

import java.io.Serializable;
import java.security.BasicPermission;
import java.security.Guard;

import org.apache.harmony.logging.internal.nls.Messages;

/**
 * The permission required to control the logging when run with a
 * {@code SecurityManager}.
 */
public final class LoggingPermission extends BasicPermission implements Guard,
        Serializable {

    // for serialization compatibility with J2SE 1.4.2
    private static final long serialVersionUID = 63564341580231582L;

    /**
     * Constructs a {@code LoggingPermission} object required to control the
     * logging. The {@code SecurityManager} checks the permissions.
     * <p>
     * {@code LoggingPermission} objects are created by the security policy code
     * and depends on the security policy file, therefore programmers shouldn't
     * normally use them directly.
     * </p>
     * 
     * @param name
     *            currently must be "control".
     * @param actions
     *            currently must be either {@code null} or the empty string.
     * @throws IllegalArgumentException
     *             if name null or different from {@code string} control.
     */
    public LoggingPermission(String name, String actions) {
        super(name, actions);
        if (!"control".equals(name)) { //$NON-NLS-1$
            // logging.6=Name must be "control".
            throw new IllegalArgumentException(Messages.getString("logging.6")); //$NON-NLS-1$
        }
        if (null != actions && !"".equals(actions)) { //$NON-NLS-1$
            // logging.7=Actions must be either null or the empty string.
            throw new IllegalArgumentException(Messages.getString("logging.7")); //$NON-NLS-1$
        }
    }

}
