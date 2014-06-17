/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/impl/DefaultHttpResponseFactory.java $
 * $Revision: 618367 $
 * $Date: 2008-02-04 10:26:06 -0800 (Mon, 04 Feb 2008) $
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

package org.apache.http.impl;

import java.util.Locale;

import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ReasonPhraseCatalog;
import org.apache.http.impl.EnglishReasonPhraseCatalog;

/**
 * Default implementation of a factory for creating response objects.
 *
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 618367 $
 * 
 * @since 4.0
 */
public class DefaultHttpResponseFactory implements HttpResponseFactory {

    /** The catalog for looking up reason phrases. */
    protected final ReasonPhraseCatalog reasonCatalog;


    /**
     * Creates a new response factory with the given catalog.
     *
     * @param catalog   the catalog of reason phrases
     */
    public DefaultHttpResponseFactory(ReasonPhraseCatalog catalog) {
        if (catalog == null) {
            throw new IllegalArgumentException
                ("Reason phrase catalog must not be null.");
        }
        this.reasonCatalog = catalog;
    }

    /**
     * Creates a new response factory with the default catalog.
     * The default catalog is
     * {@link EnglishReasonPhraseCatalog EnglishReasonPhraseCatalog}.
     */
    public DefaultHttpResponseFactory() {
        this(EnglishReasonPhraseCatalog.INSTANCE);
    }


    // non-javadoc, see interface HttpResponseFactory
    public HttpResponse newHttpResponse(final ProtocolVersion ver,
                                        final int status,
                                        HttpContext context) {
        if (ver == null) {
            throw new IllegalArgumentException("HTTP version may not be null");
        }
        final Locale loc      = determineLocale(context);
        final String reason   = reasonCatalog.getReason(status, loc);
        StatusLine statusline = new BasicStatusLine(ver, status, reason);
        return new BasicHttpResponse(statusline, reasonCatalog, loc); 
    }


    // non-javadoc, see interface HttpResponseFactory
    public HttpResponse newHttpResponse(final StatusLine statusline,
                                        HttpContext context) {
        if (statusline == null) {
            throw new IllegalArgumentException("Status line may not be null");
        }
        final Locale loc = determineLocale(context);
        return new BasicHttpResponse(statusline, reasonCatalog, loc);
    }


    /**
     * Determines the locale of the response.
     * The implementation in this class always returns the default locale.
     *
     * @param context   the context from which to determine the locale, or
     *                  <code>null</code> to use the default locale
     *
     * @return  the locale for the response, never <code>null</code>
     */
    protected Locale determineLocale(HttpContext context) {
        return Locale.getDefault();
    }
}
