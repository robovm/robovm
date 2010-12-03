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

package javax.net.ssl;

import java.security.BasicPermission;

/**
 * The class representing a network permission.
 * <p>
 * The following permissions are defined, allowing the specified action:
 * <dl>
 * <dt> {@code "setHostnameVerifier"} </dt>
 * <dd> setting a callback object for additional verification of a hostname mismatch.</dd>
 * <dt> {@code "getSSLSessionContext"} </dt>
 * <dd> getting the {@code SSLSessionContext} of an {@code SSLSession}.</dd>
 * </dl>
 */
public final class SSLPermission extends BasicPermission {

    private static final long serialVersionUID = -3456898025505876775L;

    /**
     * Creates a new {@code SSLPermission} with the specified name.
     *
     * @param name
     *            the permission name.
     */
    public SSLPermission(String name) {
        super(name);
    }

    /**
     * Creates a new {@code SSLPermission} with the specified name.
     *
     * @param name
     *            the permission name.
     * @param actions
     *            is ignored and should be {@code null}.
     */
    public SSLPermission(String name, String actions) {
        super(name, actions);
    }
}
