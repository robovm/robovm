/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/protocol/BasicHttpContext.java $
 * $Revision: 654882 $
 * $Date: 2008-05-09 09:58:59 -0700 (Fri, 09 May 2008) $
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

import java.util.HashMap;
import java.util.Map;

/**
 * Default implementation of the {@link HttpContext HttpContext}.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 654882 $
 * 
 * @since 4.0
 */
public class BasicHttpContext implements HttpContext {
    
    private final HttpContext parentContext;
    private Map map = null;
    
    public BasicHttpContext() {
        this(null);
    }
    
    public BasicHttpContext(final HttpContext parentContext) {
        super();
        this.parentContext = parentContext;
    }
    
    public Object getAttribute(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id may not be null");
        }
        Object obj = null;
        if (this.map != null) {
            obj = this.map.get(id);
        }
        if (obj == null && this.parentContext != null) {
            obj = this.parentContext.getAttribute(id);
        }
        return obj;
    }

    public void setAttribute(final String id, final Object obj) {
        if (id == null) {
            throw new IllegalArgumentException("Id may not be null");
        }
        if (this.map == null) {
            this.map = new HashMap();
        }
        this.map.put(id, obj);
    }
    
    public Object removeAttribute(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Id may not be null");
        }
        if (this.map != null) {
            return this.map.remove(id);
        } else {
            return null;
        }
    }

}
