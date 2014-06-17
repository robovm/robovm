/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/cookie/Cookie.java $
 * $Revision: 578403 $
 * $Date: 2007-09-22 03:56:04 -0700 (Sat, 22 Sep 2007) $
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

package org.apache.http.cookie;

import java.util.Date;

/**
 * HTTP "magic-cookie" represents a piece of state information
 * that the HTTP agent and the target server can exchange to maintain 
 * a session.
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 * 
 * @since 4.0
 */
public interface Cookie {

    /**
     * Returns the name.
     *
     * @return String name The name
     */
    String getName();

    /**
     * Returns the value.
     *
     * @return String value The current value.
     */
    String getValue();

    /**
     * Returns the comment describing the purpose of this cookie, or
     * <tt>null</tt> if no such comment has been defined.
     * 
     * @return comment 
     */
    String getComment();

    /**
     * If a user agent (web browser) presents this cookie to a user, the
     * cookie's purpose will be described by the information at this URL.
     */
    String getCommentURL();    
    
    /**
     * Returns the expiration {@link Date} of the cookie, or <tt>null</tt>
     * if none exists.
     * <p><strong>Note:</strong> the object returned by this method is 
     * considered immutable. Changing it (e.g. using setTime()) could result
     * in undefined behaviour. Do so at your peril. </p>
     * @return Expiration {@link Date}, or <tt>null</tt>.
     */
    Date getExpiryDate();

    /**
     * Returns <tt>false</tt> if the cookie should be discarded at the end
     * of the "session"; <tt>true</tt> otherwise.
     *
     * @return <tt>false</tt> if the cookie should be discarded at the end
     *         of the "session"; <tt>true</tt> otherwise
     */
    boolean isPersistent();

    /**
     * Returns domain attribute of the cookie.
     * 
     * @return the value of the domain attribute
     */
    String getDomain();

    /**
     * Returns the path attribute of the cookie
     * 
     * @return The value of the path attribute.
     */
    String getPath();

    /**
     * Get the Port attribute. It restricts the ports to which a cookie
     * may be returned in a Cookie request header.
     */
    int[] getPorts();

    /**
     * Indicates whether this cookie requires a secure connection.
     *
     * @return  <code>true</code> if this cookie should only be sent
     *          over secure connections, <code>false</code> otherwise.
     */
    boolean isSecure();

    /**
     * Returns the version of the cookie specification to which this
     * cookie conforms.
     *
     * @return the version of the cookie.
     */
    int getVersion();

    /**
     * Returns true if this cookie has expired.
     * @param date Current time
     * 
     * @return <tt>true</tt> if the cookie has expired.
     */
    boolean isExpired(final Date date);

}

