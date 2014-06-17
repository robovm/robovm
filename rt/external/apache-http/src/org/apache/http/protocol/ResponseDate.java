/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/protocol/ResponseDate.java $
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
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseInterceptor;
import org.apache.http.HttpStatus;

/**
 * A response interceptor that adds a Date header.
 * For use on the server side.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 555989 $
 * 
 * @since 4.0
 */
public class ResponseDate implements HttpResponseInterceptor {

    private static final HttpDateGenerator DATE_GENERATOR = new HttpDateGenerator(); 
    
    public ResponseDate() {
        super();
    }

    public void process(final HttpResponse response, final HttpContext context) 
            throws HttpException, IOException {
        if (response == null) {
            throw new IllegalArgumentException
                ("HTTP response may not be null.");
        }
        int status = response.getStatusLine().getStatusCode();
        if ((status >= HttpStatus.SC_OK) &&
            !response.containsHeader(HTTP.DATE_HEADER)) {
            String httpdate = DATE_GENERATOR.getCurrentDate();
            response.setHeader(HTTP.DATE_HEADER, httpdate); 
        }
    }
    
}
