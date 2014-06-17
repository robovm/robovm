/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/cookie/params/CookieSpecParamBean.java $
 * $Revision: 632313 $
 * $Date: 2008-02-29 05:19:50 -0800 (Fri, 29 Feb 2008) $
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

package org.apache.http.cookie.params;

import java.util.Collection;

import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

public class CookieSpecParamBean extends HttpAbstractParamBean {

    public CookieSpecParamBean (final HttpParams params) {
        super(params);
    }

    public void setDatePatterns (final Collection <String> patterns) {
        params.setParameter(CookieSpecPNames.DATE_PATTERNS, patterns);
    }

    public void setSingleHeader (final boolean singleHeader) {
        params.setBooleanParameter(CookieSpecPNames.SINGLE_COOKIE_HEADER, singleHeader);
    }
    
}
