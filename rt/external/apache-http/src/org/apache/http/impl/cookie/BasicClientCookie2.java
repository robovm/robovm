/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/cookie/BasicClientCookie2.java $
 * $Revision: 659191 $
 * $Date: 2008-05-22 11:26:53 -0700 (Thu, 22 May 2008) $
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

package org.apache.http.impl.cookie;

import java.util.Date;

import org.apache.http.cookie.SetCookie2;

/**
 * HTTP "magic-cookie" represents a piece of state information
 * that the HTTP agent and the target server can exchange to maintain 
 * a session as specified by RFC2965.
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 */
public class BasicClientCookie2 extends BasicClientCookie implements SetCookie2 {

    private String commentURL;
    private int[] ports;
    private boolean discard;
    
    /**
     * Default Constructor taking a name and a value. The value may be null.
     * 
     * @param name The name.
     * @param value The value.
     */
    public BasicClientCookie2(final String name, final String value) {
        super(name, value);
    }

    @Override
    public int[] getPorts() {
        return this.ports;
    }

    public void setPorts(final int[] ports) {
        this.ports = ports;
    }
    
    @Override
    public String getCommentURL() {
        return this.commentURL;
    }

    public void setCommentURL(final String commentURL) {
        this.commentURL = commentURL;
    }

    public void setDiscard(boolean discard) {
        this.discard = discard;
    }

    @Override
    public boolean isPersistent() {
        return !this.discard && super.isPersistent();
    }

    @Override
    public boolean isExpired(final Date date) {
        return this.discard || super.isExpired(date);
    }
 
    @Override
    public Object clone() throws CloneNotSupportedException {
        BasicClientCookie2 clone = (BasicClientCookie2) super.clone();
        clone.ports = this.ports.clone();
        return clone;
    }
    
}

