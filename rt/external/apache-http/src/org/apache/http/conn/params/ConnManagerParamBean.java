/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/conn/params/ConnManagerParamBean.java $
 * $Revision: 658781 $
 * $Date: 2008-05-21 10:42:13 -0700 (Wed, 21 May 2008) $
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

package org.apache.http.conn.params;

import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

/**
 * Allows for setting parameters relating to connection managers on
 * {@link HttpParams}.  This class ensures that the values set on the params
 * are type-safe. 
 */
public class ConnManagerParamBean extends HttpAbstractParamBean {

    public ConnManagerParamBean (final HttpParams params) {
        super(params);
    }
    
    public void setTimeout (final long timeout) {
        params.setLongParameter(ConnManagerPNames.TIMEOUT, timeout);
    }

    /** @see ConnManagerPNames#MAX_TOTAL_CONNECTIONS */
    public void setMaxTotalConnections (final int maxConnections) {
        params.setIntParameter(ConnManagerPNames.MAX_TOTAL_CONNECTIONS, maxConnections);
    }
    
    /** @see ConnManagerPNames#MAX_CONNECTIONS_PER_ROUTE */
    public void setConnectionsPerRoute(final ConnPerRouteBean connPerRoute) {
        params.setParameter(ConnManagerPNames.MAX_CONNECTIONS_PER_ROUTE, connPerRoute);
    }

}
