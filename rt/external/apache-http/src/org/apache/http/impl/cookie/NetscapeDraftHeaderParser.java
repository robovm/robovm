/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/cookie/NetscapeDraftHeaderParser.java $
 * $Revision: 603563 $
 * $Date: 2007-12-12 03:17:55 -0800 (Wed, 12 Dec 2007) $
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

package org.apache.http.impl.cookie;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.message.BasicHeaderElement;
import org.apache.http.message.BasicHeaderValueParser;
import org.apache.http.message.ParserCursor;
import org.apache.http.util.CharArrayBuffer;

public class NetscapeDraftHeaderParser {

    public final static NetscapeDraftHeaderParser DEFAULT = new NetscapeDraftHeaderParser();
    
    private final static char[] DELIMITERS = new char[] { ';' };
    
    private final BasicHeaderValueParser nvpParser;
    
    public NetscapeDraftHeaderParser() {
        super();
        this.nvpParser = BasicHeaderValueParser.DEFAULT;
    }
    
    public HeaderElement parseHeader(
            final CharArrayBuffer buffer,
            final ParserCursor cursor) throws ParseException {
        if (buffer == null) {
            throw new IllegalArgumentException("Char array buffer may not be null");
        }
        if (cursor == null) {
            throw new IllegalArgumentException("Parser cursor may not be null");
        }
        NameValuePair nvp = this.nvpParser.parseNameValuePair(buffer, cursor, DELIMITERS);
        List<NameValuePair> params = new ArrayList<NameValuePair>(); 
        while (!cursor.atEnd()) {
            NameValuePair param = this.nvpParser.parseNameValuePair(buffer, cursor, DELIMITERS);
            params.add(param);
        }
        return new BasicHeaderElement(
                nvp.getName(), 
                nvp.getValue(), params.toArray(new NameValuePair[params.size()]));
    }

}
