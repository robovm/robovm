/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/protocol/HttpRequestHandlerRegistry.java $
 * $Revision: 630662 $
 * $Date: 2008-02-24 11:40:51 -0800 (Sun, 24 Feb 2008) $
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

import java.util.Map;

/**
 * Maintains a map of HTTP request handlers keyed by a request URI pattern.
 * {@link HttpRequestHandler} instances can be looked up by request URI
 * using the {@link HttpRequestHandlerResolver} interface.<br/>
 * Patterns may have three formats:
 * <ul>
 *   <li><code>*</code></li>
 *   <li><code>*&lt;uri&gt;</code></li>
 *   <li><code>&lt;uri&gt;*</code></li>
 * </ul>
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 630662 $
 */
public class HttpRequestHandlerRegistry implements HttpRequestHandlerResolver {

    private final UriPatternMatcher matcher;

    public HttpRequestHandlerRegistry() {
        matcher = new UriPatternMatcher();
    }

    public void register(final String pattern, final HttpRequestHandler handler) {
        matcher.register(pattern, handler);
    }

    public void unregister(final String pattern) {
        matcher.unregister(pattern);
    }

    public void setHandlers(final Map map) {
        matcher.setHandlers(map);
    }

    public HttpRequestHandler lookup(final String requestURI) {
        return (HttpRequestHandler) matcher.lookup(requestURI);
    }

    /**
     * @deprecated use {@link UriPatternMatcher} directly
     */
    @Deprecated
    protected boolean matchUriRequestPattern(final String pattern, final String requestUri) {
        return matcher.matchUriRequestPattern(pattern, requestUri);
    }

}
