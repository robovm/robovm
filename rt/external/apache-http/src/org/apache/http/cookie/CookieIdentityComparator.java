/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/cookie/CookieIdentityComparator.java $
 * $Revision: 618308 $
 * $Date: 2008-02-04 07:51:19 -0800 (Mon, 04 Feb 2008) $
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

import java.io.Serializable;
import java.util.Comparator;

/**
 * This cookie comparator can be used to compare identity of cookies.
 *  
 * <p>
 *  Cookies are considered identical if their names are equal and 
 *  their domain attributes match ignoring case.
 * </p>
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 */
public class CookieIdentityComparator implements Serializable, Comparator<Cookie> {

    private static final long serialVersionUID = 4466565437490631532L;

    public int compare(final Cookie c1, final Cookie c2) {
        int res = c1.getName().compareTo(c2.getName());
        if (res == 0) {
            // do not differentiate empty and null domains 
            String d1 = c1.getDomain();
            if (d1 == null) {
                d1 = "";
            }
            String d2 = c2.getDomain();
            if (d2 == null) {
                d2 = "";
            }
            res = d1.compareToIgnoreCase(d2);
        }
        return res;
    }

}
