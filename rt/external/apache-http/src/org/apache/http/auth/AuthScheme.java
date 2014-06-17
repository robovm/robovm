/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/auth/AuthScheme.java $
 * $Revision: 537144 $
 * $Date: 2007-05-11 02:30:13 -0700 (Fri, 11 May 2007) $
 *
 * ====================================================================
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.auth;

import org.apache.http.Header;
import org.apache.http.HttpRequest;

/**
 * <p>
 * This interface represents an abstract challenge-response oriented 
 * authentication scheme.
 * </p>
 * <p>
 * An authentication scheme should be able to support the following
 * functions:
 * <ul>
 *   <li>Parse and process the challenge sent by the targer server
 *       in response to request for a protected resource
 *   <li>Provide its textual designation
 *   <li>Provide its parameters, if available
 *   <li>Provide the realm this authentication scheme is applicable to,
 *       if available
 *   <li>Generate authorization string for the given set of credentials,
 *       request method and URI as specificed in the HTTP request line
 *       in response to the actual authorization challenge
 * </ul>
 * </p>
 * <p>
 * Authentication schemes may ignore method name and URI parameters
 * if they are not relevant for the given authentication mechanism
 * </p>
 * <p>
 * Authentication schemes may be stateful involving a series of 
 * challenge-response exchanges
 * </p>
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 * @author <a href="mailto:adrian@ephox.com">Adrian Sutton</a>
 *
 * @since 4.0
 */

public interface AuthScheme {

    /**
     * Processes the given challenge token. Some authentication schemes
     * may involve multiple challenge-response exchanges. Such schemes must be able 
     * to maintain the state information when dealing with sequential challenges 
     * 
     * @param header the challenge header
     */
    void processChallenge(final Header header) throws MalformedChallengeException;
    
    /**
     * Returns textual designation of the given authentication scheme.
     * 
     * @return the name of the given authentication scheme
     */
    String getSchemeName();

    /**
     * Returns authentication parameter with the given name, if available.
     * 
     * @param name The name of the parameter to be returned
     * 
     * @return the parameter with the given name
     */
    String getParameter(final String name);

    /**
     * Returns authentication realm. If the concept of an authentication
     * realm is not applicable to the given authentication scheme, returns
     * <code>null</code>.
     * 
     * @return the authentication realm
     */
    String getRealm();
    
    /**
     * Tests if the authentication scheme is provides authorization on a per
     * connection basis instead of usual per request basis
     * 
     * @return <tt>true</tt> if the scheme is connection based, <tt>false</tt>
     * if the scheme is request based.
     */
    boolean isConnectionBased();    
    
    /**
     * Authentication process may involve a series of challenge-response exchanges.
     * This method tests if the authorization process has been completed, either
     * successfully or unsuccessfully, that is, all the required authorization 
     * challenges have been processed in their entirety.
     * 
     * @return <tt>true</tt> if the authentication process has been completed, 
     * <tt>false</tt> otherwise.
     */
    boolean isComplete();    

    /**
     * Produces an authorization string for the given set of {@link Credentials}.
     * 
     * @param credentials The set of credentials to be used for athentication
     * @param request The request being authenticated
     * @throws AuthenticationException if authorization string cannot 
     *   be generated due to an authentication failure
     * 
     * @return the authorization string
     */
    Header authenticate(Credentials credentials, HttpRequest request) 
            throws AuthenticationException;
    
}
