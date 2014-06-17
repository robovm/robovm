/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/conn/params/ConnPerRouteBean.java $
 * $Revision: 652947 $
 * $Date: 2008-05-02 16:15:40 -0700 (Fri, 02 May 2008) $
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

import java.util.HashMap;
import java.util.Map;

import org.apache.http.conn.routing.HttpRoute;

/**
 * This class maintains a map of HTTP routes to maximum number of connections allowed 
 * for those routes. This class can be used by pooling 
 * {@link org.apache.http.conn.ClientConnectionManager connection managers} for 
 * a fine-grained control of connections on a per route basis. 
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 * 
 * @version $Revision: 652947 $
 * 
 * @since 4.0
 */
public final class ConnPerRouteBean implements ConnPerRoute {

    /** The default maximum number of connections allowed per host */
    public static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 2;   // Per RFC 2616 sec 8.1.4
    
    private final Map<HttpRoute, Integer> maxPerHostMap;
    
    private int defaultMax;
    
    public ConnPerRouteBean(int defaultMax) {
        super();
        this.maxPerHostMap = new HashMap<HttpRoute, Integer>();
        setDefaultMaxPerRoute(defaultMax);
    }
    
    public ConnPerRouteBean() {
        this(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
    }
    
    public int getDefaultMax() {
        return this.defaultMax;
    }

    public void setDefaultMaxPerRoute(int max) {
        if (max < 1) {
            throw new IllegalArgumentException
                ("The maximum must be greater than 0.");
        }
        this.defaultMax = max;
    }

    public void setMaxForRoute(final HttpRoute route, int max) {
        if (route == null) {
            throw new IllegalArgumentException
                ("HTTP route may not be null.");
        }
        if (max < 1) {
            throw new IllegalArgumentException
                ("The maximum must be greater than 0.");
        }
        this.maxPerHostMap.put(route, Integer.valueOf(max));
    }

    public int getMaxForRoute(final HttpRoute route) {
        if (route == null) {
            throw new IllegalArgumentException
                ("HTTP route may not be null.");
        }
        Integer max = this.maxPerHostMap.get(route);
        if (max != null) {
            return max.intValue();
        } else {
            return this.defaultMax;
        }
    }
    
    public void setMaxForRoutes(final Map<HttpRoute, Integer> map) {
        if (map == null) {
            return;
        }
        this.maxPerHostMap.clear();
        this.maxPerHostMap.putAll(map);
    }
    
}
