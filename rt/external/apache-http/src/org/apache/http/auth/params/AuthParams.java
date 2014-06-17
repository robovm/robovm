/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/auth/params/AuthParams.java $
 * $Revision: 618365 $
 * $Date: 2008-02-04 10:20:08 -0800 (Mon, 04 Feb 2008) $
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

package org.apache.http.auth.params;

import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;

/**
 * This class implements an adaptor around the {@link HttpParams} interface
 * to simplify manipulation of the HTTP authentication specific parameters.
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 * 
 * @version $Revision: 618365 $
 * 
 * @since 4.0
 *
 * @see AuthPNames
 */
public final class AuthParams {

    private AuthParams() {
        super();
    }

    /**
     * Obtains the charset for encoding
     * {@link org.apache.http.auth.Credentials}.
     * If not configured,
     * {@link HTTP#DEFAULT_PROTOCOL_CHARSET HTTP.DEFAULT_PROTOCOL_CHARSET}
     * is used instead.
     * 
     * @return The charset
     *
     * @see AuthPNames#CREDENTIAL_CHARSET
     */
    public static String getCredentialCharset(final HttpParams params) {
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        String charset = (String) params.getParameter
            (AuthPNames.CREDENTIAL_CHARSET);
        //@@@ TODO: inconsistent with JavaDoc in AuthPNames,
        //@@@ TODO: check HTTP_ELEMENT_CHARSET first, or fix JavaDocs
        if (charset == null) {
            charset = HTTP.DEFAULT_PROTOCOL_CHARSET;
        }
        return charset;
    }


    /**
     * Sets the charset to be used when encoding
     * {@link org.apache.http.auth.Credentials}.
     * 
     * @param charset The charset
     */
    public static void setCredentialCharset(final HttpParams params, final String charset) {
        if (params == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        params.setParameter(AuthPNames.CREDENTIAL_CHARSET, charset);
    }

}
