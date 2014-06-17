/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/client/EntityEnclosingRequestWrapper.java $
 * $Revision: 674186 $
 * $Date: 2008-07-05 05:18:54 -0700 (Sat, 05 Jul 2008) $
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

package org.apache.http.impl.client;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.ProtocolException;
import org.apache.http.protocol.HTTP;

/**
 * A wrapper class for {@link HttpEntityEnclosingRequest}s that can 
 * be used to change properties of the current request without 
 * modifying the original object.
 * </p>
 * This class is also capable of resetting the request headers to
 * the state of the original request.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 674186 $
 * 
 * @since 4.0
 */
public class EntityEnclosingRequestWrapper extends RequestWrapper 
    implements HttpEntityEnclosingRequest {
    
    private HttpEntity entity;
    
    public EntityEnclosingRequestWrapper(final HttpEntityEnclosingRequest request) 
        throws ProtocolException {
        super(request);
        this.entity = request.getEntity();
    }

    public HttpEntity getEntity() {
        return this.entity;
    }

    public void setEntity(final HttpEntity entity) {
        this.entity = entity;
    }
    
    public boolean expectContinue() {
        Header expect = getFirstHeader(HTTP.EXPECT_DIRECTIVE);
        return expect != null && HTTP.EXPECT_CONTINUE.equalsIgnoreCase(expect.getValue());
    }

    @Override
    public boolean isRepeatable() {
        return this.entity == null || this.entity.isRepeatable();
    }
    
}
