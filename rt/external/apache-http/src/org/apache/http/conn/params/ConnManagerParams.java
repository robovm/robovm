/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/conn/params/ConnManagerParams.java $
 * $Revision: 658785 $
 * $Date: 2008-05-21 10:47:40 -0700 (Wed, 21 May 2008) $
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

package org.apache.http.conn.params;

import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.params.HttpParams;

/**
 * This class represents a collection of HTTP protocol parameters applicable
 * to client-side
 * {@link org.apache.http.conn.ClientConnectionManager connection managers}. 
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 * @author Michael Becke
 * 
 * @version $Revision: 658785 $
 * 
 * @since 4.0
 *
 * @see ConnManagerPNames
 */
public final class ConnManagerParams implements ConnManagerPNames {

    /** The default maximum number of connections allowed overall */
    public static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 20;

    /**
     * Returns the timeout in milliseconds used when retrieving a
     * {@link org.apache.http.conn.ManagedClientConnection} from the
     * {@link org.apache.http.conn.ClientConnectionManager}.
     * 
     * @return timeout in milliseconds.
     */ 
    public static long getTimeout(final HttpParams params) {
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        return params.getLongParameter(TIMEOUT, 0);
    }

    /**
     * Sets the timeout in milliseconds used when retrieving a
     * {@link org.apache.http.conn.ManagedClientConnection} from the
     * {@link org.apache.http.conn.ClientConnectionManager}.
     * 
     * @param timeout the timeout in milliseconds
     */ 
    public static void setTimeout(final HttpParams params, long timeout) {
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        params.setLongParameter(TIMEOUT, timeout);
    }

    /** The default maximum number of connections allowed per host */
    private static final ConnPerRoute DEFAULT_CONN_PER_ROUTE = new ConnPerRoute() {
        
        public int getMaxForRoute(HttpRoute route) {
            return ConnPerRouteBean.DEFAULT_MAX_CONNECTIONS_PER_ROUTE;
        }
        
    };
    
    /**
     * Sets lookup interface for maximum number of connections allowed per route.
     *
     * @param params HTTP parameters
     * @param connPerRoute lookup interface for maximum number of connections allowed 
     *        per route
     * 
     * @see ConnManagerPNames#MAX_CONNECTIONS_PER_ROUTE
     */
    public static void setMaxConnectionsPerRoute(final HttpParams params,
                                                final ConnPerRoute connPerRoute) {
        if (params == null) {
            throw new IllegalArgumentException
                ("HTTP parameters must not be null.");
        }
        params.setParameter(MAX_CONNECTIONS_PER_ROUTE, connPerRoute);
    }

    /**
     * Returns lookup interface for maximum number of connections allowed per route.
     *
     * @param params HTTP parameters
     * 
     * @return lookup interface for maximum number of connections allowed per route.
     * 
     * @see ConnManagerPNames#MAX_CONNECTIONS_PER_ROUTE
     */
    public static ConnPerRoute getMaxConnectionsPerRoute(final HttpParams params) {
        if (params == null) {
            throw new IllegalArgumentException
                ("HTTP parameters must not be null.");
        }
        ConnPerRoute connPerRoute = (ConnPerRoute) params.getParameter(MAX_CONNECTIONS_PER_ROUTE);
        if (connPerRoute == null) {
            connPerRoute = DEFAULT_CONN_PER_ROUTE;
        }
        return connPerRoute;
    }


    /**
     * Sets the maximum number of connections allowed.
     *
     * @param params HTTP parameters
     * @param maxTotalConnections The maximum number of connections allowed.
     * 
     * @see ConnManagerPNames#MAX_TOTAL_CONNECTIONS
     */
    public static void setMaxTotalConnections(
            final HttpParams params,
            int maxTotalConnections) {
        if (params == null) {
            throw new IllegalArgumentException
                ("HTTP parameters must not be null.");
        }
        params.setIntParameter(MAX_TOTAL_CONNECTIONS, maxTotalConnections);
    }

    /**
     * Gets the maximum number of connections allowed.
     *
     * @param params HTTP parameters
     *
     * @return The maximum number of connections allowed.
     * 
     * @see ConnManagerPNames#MAX_TOTAL_CONNECTIONS
     */
    public static int getMaxTotalConnections(
            final HttpParams params) {
        if (params == null) {
            throw new IllegalArgumentException
                ("HTTP parameters must not be null.");
        }
        return params.getIntParameter(MAX_TOTAL_CONNECTIONS, DEFAULT_MAX_TOTAL_CONNECTIONS);
    }

    
}
