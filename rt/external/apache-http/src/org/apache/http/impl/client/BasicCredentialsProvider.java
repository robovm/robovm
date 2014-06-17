/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/client/BasicCredentialsProvider.java $
 * $Revision: 653041 $
 * $Date: 2008-05-03 03:39:28 -0700 (Sat, 03 May 2008) $
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

package org.apache.http.impl.client;

import java.util.HashMap;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;

/**
 * Default implementation of {@link CredentialsProvider}
 * 
 * @author <a href="mailto:remm@apache.org">Remy Maucherat</a>
 * @author Rodney Waldhoff
 * @author <a href="mailto:jsdever@apache.org">Jeff Dever</a>
 * @author Sean C. Sullivan
 * @author <a href="mailto:becke@u.washington.edu">Michael Becke</a>
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author <a href="mailto:adrian@intencha.com">Adrian Sutton</a>
 * 
 * @since 4.0
 */
public class BasicCredentialsProvider implements CredentialsProvider {

    private final HashMap<AuthScope, Credentials> credMap;

    /**
     * Default constructor.
     */
    public BasicCredentialsProvider() {
        super();
        this.credMap = new HashMap<AuthScope, Credentials>();
    }

    /** 
     * Sets the {@link Credentials credentials} for the given authentication 
     * scope. Any previous credentials for the given scope will be overwritten.
     * 
     * @param authscope the {@link AuthScope authentication scope}
     * @param credentials the authentication {@link Credentials credentials} 
     * for the given scope.
     * 
     * @see #getCredentials(AuthScope)
     */
    public synchronized void setCredentials(
            final AuthScope authscope, 
            final Credentials credentials) {
        if (authscope == null) {
            throw new IllegalArgumentException("Authentication scope may not be null");
        }
        credMap.put(authscope, credentials);
    }

    /**
     * Find matching {@link Credentials credentials} for the given authentication scope.
     *
     * @param map the credentials hash map
     * @param authscope the {@link AuthScope authentication scope}
     * @return the credentials 
     * 
     */
    private static Credentials matchCredentials(
            final HashMap<AuthScope, Credentials> map, 
            final AuthScope authscope) {
        // see if we get a direct hit
        Credentials creds = map.get(authscope);
        if (creds == null) {
            // Nope.
            // Do a full scan
            int bestMatchFactor  = -1;
            AuthScope bestMatch  = null;
            for (AuthScope current: map.keySet()) {
                int factor = authscope.match(current);
                if (factor > bestMatchFactor) {
                    bestMatchFactor = factor;
                    bestMatch = current;
                }
            }
            if (bestMatch != null) {
                creds = map.get(bestMatch);
            }
        }
        return creds;
    }
    
    /**
     * Get the {@link Credentials credentials} for the given authentication scope.
     *
     * @param authscope the {@link AuthScope authentication scope}
     * @return the credentials 
     * 
     * @see #setCredentials(AuthScope, Credentials)
     */
    public synchronized Credentials getCredentials(final AuthScope authscope) {
        if (authscope == null) {
            throw new IllegalArgumentException("Authentication scope may not be null");
        }
        return matchCredentials(this.credMap, authscope);
    }

    @Override
    public String toString() {
        return credMap.toString();
    }
    
    /**
     * Clears all credentials.
     */
    public synchronized void clear() {
        this.credMap.clear();
    }
    
}
