/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/auth/AuthSchemeRegistry.java $
 * $Revision: 652950 $
 * $Date: 2008-05-02 16:49:48 -0700 (Fri, 02 May 2008) $
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

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.http.params.HttpParams;

/**
 * Authentication scheme registry that can be used to obtain the corresponding
 * authentication scheme implementation for a given type of authorization challenge. 
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 *
 * @version $Revision: 652950 $
 * @since 4.0
 */
public final class AuthSchemeRegistry {

    private final Map<String,AuthSchemeFactory> registeredSchemes;
    
    public AuthSchemeRegistry() {
        super();
        this.registeredSchemes = new LinkedHashMap<String,AuthSchemeFactory>();
    }
    
    /**
     * Registers a {@link AuthSchemeFactory} with  the given identifier. If a factory with the 
     * given name already exists it will be overridden. This name is the same one used to 
     * retrieve the {@link AuthScheme authentication scheme} from {@link #getAuthScheme}.
     * 
     * <p>
     * Please note that custom authentication preferences, if used, need to be updated accordingly 
     * for the new {@link AuthScheme authentication scheme} to take effect.
     * </p>    
     * 
     * @param name the identifier for this scheme
     * @param factory the {@link AuthSchemeFactory} class to register
     * 
     * @see #getAuthScheme
     */
    public synchronized void register(
            final String name, 
            final AuthSchemeFactory factory) {
         if (name == null) {
             throw new IllegalArgumentException("Name may not be null");
         }
        if (factory == null) {
            throw new IllegalArgumentException("Authentication scheme factory may not be null");
        }
        registeredSchemes.put(name.toLowerCase(Locale.ENGLISH), factory);
    }

    /**
     * Unregisters the class implementing an {@link AuthScheme authentication scheme} with 
     * the given name.
     * 
     * @param name the identifier of the class to unregister
     */
    public synchronized void unregister(final String name) {
         if (name == null) {
             throw new IllegalArgumentException("Name may not be null");
         }
        registeredSchemes.remove(name.toLowerCase(Locale.ENGLISH));
    }

    /**
     * Gets the {@link AuthScheme authentication scheme} with the given name.
     * 
     * @param name the {@link AuthScheme authentication scheme} identifier
     * @param params the {@link HttpParams HTTP parameters} for the authentication
     *  scheme. 
     * 
     * @return {@link AuthScheme authentication scheme}
     * 
     * @throws IllegalStateException if a scheme with the given name cannot be found
     */
    public synchronized AuthScheme getAuthScheme(final String name, final HttpParams params) 
        throws IllegalStateException {

        if (name == null) {
            throw new IllegalArgumentException("Name may not be null");
        }
        AuthSchemeFactory factory = registeredSchemes.get(name.toLowerCase(Locale.ENGLISH));
        if (factory != null) {
            return factory.newInstance(params);
        } else {
            throw new IllegalStateException("Unsupported authentication scheme: " + name);
        }
    } 

    /**
     * Obtains a list containing names of all registered {@link AuthScheme authentication 
     * schemes} in their default order.
     * 
     * @return list of registered scheme names
     */
    public synchronized List<String> getSchemeNames() {
        return new ArrayList<String>(registeredSchemes.keySet()); 
    } 
 
    /**
     * Populates the internal collection of registered {@link AuthScheme authentication schemes} 
     * with the content of the map passed as a parameter.
     * 
     * @param map authentication schemes
     */
    public synchronized void setItems(final Map<String, AuthSchemeFactory> map) {
        if (map == null) {
            return;
        }
        registeredSchemes.clear();
        registeredSchemes.putAll(map);
    }
    
}
