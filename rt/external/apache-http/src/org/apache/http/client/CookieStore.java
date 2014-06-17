/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/client/CookieStore.java $
 * $Revision: 604434 $
 * $Date: 2007-12-15 06:45:48 -0800 (Sat, 15 Dec 2007) $
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

package org.apache.http.client;

import java.util.Date;
import java.util.List;

import org.apache.http.cookie.Cookie;

/**
 * Abstract cookie store.
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 * 
 * @since 4.0
 */
public interface CookieStore {

    /**
     * Adds an {@link Cookie HTTP cookie}, replacing any existing equivalent cookies.
     * If the given cookie has already expired it will not be added, but existing 
     * values will still be removed.
     * 
     * @param cookie the {@link Cookie cookie} to be added
     */
    void addCookie(Cookie cookie);

    /**
     * Returns all cookies contained in this store.
     * 
     * @return all cookies
     */
    List<Cookie> getCookies();

    /**
     * Removes all of {@link Cookie cookies} in this store that have expired by 
     * the specified {@link java.util.Date date}. 
     * 
     * @return true if any cookies were purged.
     */
    boolean clearExpired(Date date);

    /**
     * Clears all cookies.
     */
    void clear();
    
}
