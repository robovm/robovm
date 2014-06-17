/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/impl/entity/LaxContentLengthStrategy.java $
 * $Revision: 576073 $
 * $Date: 2007-09-16 03:53:13 -0700 (Sun, 16 Sep 2007) $
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

package org.apache.http.impl.entity;

import org.apache.http.Header;
import org.apache.http.HeaderElement;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.ParseException;
import org.apache.http.ProtocolException;
import org.apache.http.entity.ContentLengthStrategy;
import org.apache.http.params.HttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;

/**
 * The lax implementation of the content length strategy.
 * <p>
 * This strategy conforms to the entity transfer rules outlined in  
 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec4.4">Section 4.4</a>, 
 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec3.6">Section 3.6</a>, 
 * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec14.html#sec14.41">Section 14.41</a>
 * and <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec14.13">Section 14.13</a>
 * of <a href="http://www.w3.org/Protocols/rfc2616/rfc2616.txt">RFC 2616</a>, but is lenient 
 * about unsupported transfer codecs and malformed content-length headers.
 * </p>
 * <h>4.4 Message Length</h>
 * <p>
 * The transfer-length of a message is the length of the message-body as it appears in the 
 * message; that is, after any transfer-codings have been applied. When a message-body is 
 * included with a message, the transfer-length of that body is determined by one of the 
 * following (in order of precedence):
 * </p>
 * <p>
 * 1.Any response message which "MUST NOT" include a message-body (such as the 1xx, 204, 
 * and 304 responses and any response to a HEAD request) is always terminated by the first 
 * empty line after the header fields, regardless of the entity-header fields present in the 
 * message.
 * </p>
 * <p>
 * 2.If a Transfer-Encoding header field (section 14.41) is present and has any value other 
 * than "identity", then the transfer-length is defined by use of the "chunked" transfer-
 * coding (section 3.6), unless the message is terminated by closing the connection.
 * </p>
 * <p>
 * 3.If a Content-Length header field (section 14.13) is present, its decimal value in 
 * OCTETs represents both the entity-length and the transfer-length. The Content-Length 
 * header field MUST NOT be sent if these two lengths are different (i.e., if a 
 * Transfer-Encoding
 * </p>
 * <pre>
 *    header field is present). If a message is received with both a
 *    Transfer-Encoding header field and a Content-Length header field,
 *    the latter MUST be ignored.
 * </pre>
 * <p>
 * 4.If the message uses the media type "multipart/byteranges", and the ransfer-length is not 
 * otherwise specified, then this self- elimiting media type defines the transfer-length. 
 * This media type UST NOT be used unless the sender knows that the recipient can arse it; the 
 * presence in a request of a Range header with ultiple byte- range specifiers from a 1.1 
 * client implies that the lient can parse multipart/byteranges responses.
 * </p>
 * <pre>
 *     A range header might be forwarded by a 1.0 proxy that does not
 *     understand multipart/byteranges; in this case the server MUST
 *     delimit the message using methods defined in items 1,3 or 5 of
 *     this section.
 * </pre>
 * <p>
 * 5.By the server closing the connection. (Closing the connection cannot be used to indicate 
 * the end of a request body, since that would leave no possibility for the server to send back 
 * a response.)
 * </p>
 * <p>
 * For compatibility with HTTP/1.0 applications, HTTP/1.1 requests containing a message-body 
 * MUST include a valid Content-Length header field unless the server is known to be HTTP/1.1 
 * compliant. If a request contains a message-body and a Content-Length is not given, the 
 * server SHOULD respond with 400 (bad request) if it cannot determine the length of the 
 * message, or with 411 (length required) if it wishes to insist on receiving a valid 
 * Content-Length.
 * </p>
 * <p>All HTTP/1.1 applications that receive entities MUST accept the "chunked" transfer-coding 
 * (section 3.6), thus allowing this mechanism to be used for messages when the message 
 * length cannot be determined in advance. 
 * </p>
 * <h>3.6 Transfer Codings</h>
 * <p>
 * Transfer-coding values are used to indicate an encoding transformation that 
 * has been, can be, or may need to be applied to an entity-body in order to ensure 
 * "safe transport" through the network. This differs from a content coding in that 
 * the transfer-coding is a property of the message, not of the original entity.
 * </p>
 * <pre>
 * transfer-coding         = "chunked" | transfer-extension
 * transfer-extension      = token *( ";" parameter )
 * </pre>
 * <p>
 * Parameters are in the form of attribute/value pairs.
 * </p>
 * <pre>
 * parameter               = attribute "=" value
 * attribute               = token
 * value                   = token | quoted-string
 * </pre>
 * <p>
 * All transfer-coding values are case-insensitive. HTTP/1.1 uses transfer-coding values in 
 * the TE header field (section 14.39) and in the Transfer-Encoding header field (section 14.41).
 * </p>
 * <p>
 * Whenever a transfer-coding is applied to a message-body, the set of transfer-codings MUST 
 * include "chunked", unless the message is terminated by closing the connection. When the 
 * "chunked" transfer-coding is used, it MUST be the last transfer-coding applied to the 
 * message-body. The "chunked" transfer-coding MUST NOT be applied more than once to a 
 * message-body. These rules allow the recipient to determine the transfer-length of the 
 * message (section 4.4).
 * </p>
 * <h>14.41 Transfer-Encoding</h>
 * <p>
 * The Transfer-Encoding general-header field indicates what (if any) type of transformation has 
 * been applied to the message body in order to safely transfer it between the sender and the 
 * recipient. This differs from the content-coding in that the transfer-coding is a property of 
 * the message, not of the entity.
 * </p>
 * <pre>
 *   Transfer-Encoding       = "Transfer-Encoding" ":" 1#transfer-coding
 * </pre>
 * <p>
 * If multiple encodings have been applied to an entity, the transfer- codings MUST be listed in 
 * the order in which they were applied. Additional information about the encoding parameters 
 * MAY be provided by other entity-header fields not defined by this specification.
 * </p> 
 * <h>14.13 Content-Length</h>
 * <p>
 * The Content-Length entity-header field indicates the size of the entity-body, in decimal 
 * number of OCTETs, sent to the recipient or, in the case of the HEAD method, the size of 
 * the entity-body that would have been sent had the request been a GET.
 * </p>
 * <pre>
 *   Content-Length    = "Content-Length" ":" 1*DIGIT
 * </pre>
 * <p>
 * Applications SHOULD use this field to indicate the transfer-length of the message-body, 
 * unless this is prohibited by the rules in section 4.4. 
 * </p>
 * 
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 *
 * @version $Revision: 576073 $
 * 
 * @since 4.0
 */
public class LaxContentLengthStrategy implements ContentLengthStrategy {

    public LaxContentLengthStrategy() {
        super();
    }

    public long determineLength(final HttpMessage message) throws HttpException {
        if (message == null) {
            throw new IllegalArgumentException("HTTP message may not be null");
        }

        HttpParams params = message.getParams(); 
        boolean strict = params.isParameterTrue(CoreProtocolPNames.STRICT_TRANSFER_ENCODING);
        
        Header transferEncodingHeader = message.getFirstHeader(HTTP.TRANSFER_ENCODING);
        Header contentLengthHeader = message.getFirstHeader(HTTP.CONTENT_LEN);
        // We use Transfer-Encoding if present and ignore Content-Length.
        // RFC2616, 4.4 item number 3
        if (transferEncodingHeader != null) {
            HeaderElement[] encodings = null;
            try {
                encodings = transferEncodingHeader.getElements();
            } catch (ParseException px) {
                throw new ProtocolException
                    ("Invalid Transfer-Encoding header value: " +
                     transferEncodingHeader, px);
            }
            if (strict) {
                // Currently only chunk and identity are supported
                for (int i = 0; i < encodings.length; i++) {
                    String encoding = encodings[i].getName();
                    if (encoding != null && encoding.length() > 0 
                        && !encoding.equalsIgnoreCase(HTTP.CHUNK_CODING)
                        && !encoding.equalsIgnoreCase(HTTP.IDENTITY_CODING)) {
                        throw new ProtocolException("Unsupported transfer encoding: " + encoding);
                    }
                }
            }
            // The chunked encoding must be the last one applied RFC2616, 14.41
            int len = encodings.length;
            if (HTTP.IDENTITY_CODING.equalsIgnoreCase(transferEncodingHeader.getValue())) {
                return IDENTITY;                            
            } else if ((len > 0) && (HTTP.CHUNK_CODING.equalsIgnoreCase(
                    encodings[len - 1].getName()))) { 
                return CHUNKED;
            } else {
                if (strict) {
                    throw new ProtocolException("Chunk-encoding must be the last one applied");
                }
                return IDENTITY;                            
            }
        } else if (contentLengthHeader != null) {
            long contentlen = -1;
            Header[] headers = message.getHeaders(HTTP.CONTENT_LEN);
            if (strict && headers.length > 1) {
                throw new ProtocolException("Multiple content length headers");
            }
            for (int i = headers.length - 1; i >= 0; i--) {
                Header header = headers[i];
                try {
                    contentlen = Long.parseLong(header.getValue());
                    break;
                } catch (NumberFormatException e) {
                    if (strict) {
                        throw new ProtocolException("Invalid content length: " + header.getValue());
                    }
                }
                // See if we can have better luck with another header, if present
            }
            if (contentlen >= 0) {
                return contentlen;
            } else {
                return IDENTITY;
            }
        } else {
            return IDENTITY;
        }
    }
        
}
