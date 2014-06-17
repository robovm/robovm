/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/protocol/RequestDate.java $
 * $Revision: 555989 $
 * $Date: 2007-07-13 06:33:39 -0700 (Fri, 13 Jul 2007) $
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

package org.apache.http.protocol;

import java.io.IOException;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequestInterceptor;

/**
 * A request interceptor that adds a Date header.
 * For use on the client side.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 555989 $
 * 
 * @since 4.0
 */
public class RequestDate implements HttpRequestInterceptor {

    private static final HttpDateGenerator DATE_GENERATOR = new HttpDateGenerator(); 
    
    public RequestDate() {
        super();
    }

    public void process(final HttpRequest request, final HttpContext context) 
            throws HttpException, IOException {
        if (request == null) {
            throw new IllegalArgumentException
                ("HTTP request may not be null.");
        }
        if ((request instanceof HttpEntityEnclosingRequest) &&
            !request.containsHeader(HTTP.DATE_HEADER)) {
            String httpdate = DATE_GENERATOR.getCurrentDate();
            request.setHeader(HTTP.DATE_HEADER, httpdate); 
        }
    }
    
}
