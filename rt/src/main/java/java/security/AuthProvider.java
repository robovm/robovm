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

package java.security;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

/**
 * {@code AuthProvider} is an abstract superclass for Java Security {@code
 * Provider} which provide login and logout.
 */
public abstract class AuthProvider extends Provider {

    /**
     * @serial
     */
    private static final long serialVersionUID = 4197859053084546461L;

    /**
     * Constructs a new instance of {@code AuthProvider} with its name, version
     * and description.
     * 
     * @param name
     *            the name of the provider.
     * @param version
     *            the version of the provider.
     * @param info
     *            a description of the provider.
     */
    protected AuthProvider(String name, double version, String info) {
        super(name, version, info); 
    }
    
    /**
     * Performs a login into this {@code AuthProvider}. The specified {@code
     * CallbackHandler} is used to obtain information from the caller.
     * <p>
     * If a {@code SecurityManager} is installed, code calling this method needs
     * the {@code SecurityPermission} {@code authProvider.NAME} (where NAME is
     * the provider name) to be granted, otherwise a {@code SecurityException}
     * will be thrown.
     * 
     * @param subject
     *            the subject that is used to login.
     * @param handler
     *            the handler to obtain authentication information from the
     *            caller.
     * @throws LoginException
     *             if the login fails.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and the caller does
     *             not have permission to invoke this method.
     */
    public abstract void login(Subject subject, CallbackHandler handler) throws LoginException;
    
    /**
     * Performs a logout from this {@code AuthProvider}.
     * <p>
     * If a {@code SecurityManager} is installed, code calling this method needs
     * the {@code SecurityPermission} {@code authProvider.NAME} (where NAME is
     * the provider name) to be granted, otherwise a {@code SecurityException}
     * will be thrown.
     *
     * @throws LoginException
     *             if the logout fails.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and the caller does
     *             not have permission to invoke this method.
     */
    public abstract void logout() throws LoginException;
    
    /**
     * Sets the {@code CallbackHandler} to this {@code AuthProvider}. If no
     * handler is passed to the {@link #login(Subject, CallbackHandler)} method,
     * this {@code AuthProvider} is using the specified {@code CallbackHandler}.
     * <p>
     * If no handler is set, this {@code AuthProvider} uses the {@code
     * CallbackHandler} specified by the {@code
     * auth.login.defaultCallbackHandler} security property.
     * <p>
     * If a {@code SecurityManager} is installed, code calling this method needs
     * the {@code SecurityPermission} {@code authProvider.NAME} (where NAME is
     * the provider name) to be granted, otherwise a {@code SecurityException}
     * will be thrown.
     *
     * @param handler
     *            the handler to obtain authentication information from the
     *            caller.
     * @throws SecurityException
     *             if a {@code SecurityManager} is installed and the caller does
     *             not have permission to invoke this method.
     */
    public abstract void setCallbackHandler(CallbackHandler handler);
}