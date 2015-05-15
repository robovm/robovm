/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/message/BasicLineFormatter.java $
 * $Revision: 574185 $
 * $Date: 2007-09-10 02:19:47 -0700 (Mon, 10 Sep 2007) $
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

package org.apache.http.message;


import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.StatusLine;
import org.apache.http.Header;
import org.apache.http.FormattedHeader;
import org.apache.http.util.CharArrayBuffer;


/**
 * Interface for formatting elements of the HEAD section of an HTTP message.
 * This is the complement to {@link LineParser}.
 * There are individual methods for formatting a request line, a
 * status line, or a header line. The formatting does <i>not</i> include the
 * trailing line break sequence CR-LF.
 * The formatted lines are returned in memory, the formatter does not depend
 * on any specific IO mechanism.
 * Instances of this interface are expected to be stateless and thread-safe.
 *
 * @author <a href="mailto:remm@apache.org">Remy Maucherat</a>
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author <a href="mailto:jsdever@apache.org">Jeff Dever</a>
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 * @author and others
 *
 *
 * <!-- empty lines above to avoid 'svn diff' context problems -->
 * @version $Revision: 574185 $
 *
 * @since 4.0
 */
public class BasicLineFormatter implements LineFormatter {

    /**
     * A default instance of this class, for use as default or fallback.
     * Note that {@link BasicLineFormatter} is not a singleton, there can
     * be many instances of the class itself and of derived classes.
     * The instance here provides non-customized, default behavior.
     */
    public final static BasicLineFormatter DEFAULT = new BasicLineFormatter();
    // RoboVM: added, see issue #834 
    public final static BasicLineFormatter INSTANCE = new BasicLineFormatter();


    // public default constructor


    /**
     * Obtains a buffer for formatting.
     *
     * @param buffer    a buffer already available, or <code>null</code>
     *
     * @return  the cleared argument buffer if there is one, or
     *          a new empty buffer that can be used for formatting
     */
    protected CharArrayBuffer initBuffer(CharArrayBuffer buffer) {
        if (buffer != null) {
            buffer.clear();
        } else {
            buffer = new CharArrayBuffer(64);
        }
        return buffer;
    }


    /**
     * Formats a protocol version.
     *
     * @param version           the protocol version to format
     * @param formatter         the formatter to use, or
     *                          <code>null</code> for the
     *                          {@link #DEFAULT default}
     *
     * @return  the formatted protocol version
     */
    public final static
        String formatProtocolVersion(final ProtocolVersion version,
                                     LineFormatter formatter) {
        if (formatter == null)
            formatter = BasicLineFormatter.DEFAULT;
        return formatter.appendProtocolVersion(null, version).toString();
    }


    // non-javadoc, see interface LineFormatter
    public CharArrayBuffer appendProtocolVersion(final CharArrayBuffer buffer,
                                                 final ProtocolVersion version) {
        if (version == null) {
            throw new IllegalArgumentException
                ("Protocol version may not be null");
        }

        // can't use initBuffer, that would clear the argument!
        CharArrayBuffer result = buffer;
        final int len = estimateProtocolVersionLen(version);
        if (result == null) {
            result = new CharArrayBuffer(len);
        } else {
            result.ensureCapacity(len);
        }

        result.append(version.getProtocol());
        result.append('/'); 
        result.append(Integer.toString(version.getMajor())); 
        result.append('.'); 
        result.append(Integer.toString(version.getMinor())); 

        return result;
    }


    /**
     * Guesses the length of a formatted protocol version.
     * Needed to guess the length of a formatted request or status line.
     *
     * @param version   the protocol version to format, or <code>null</code>
     *
     * @return  the estimated length of the formatted protocol version,
     *          in characters
     */
    protected int estimateProtocolVersionLen(final ProtocolVersion version) {
        return version.getProtocol().length() + 4; // room for "HTTP/1.1"
    }


    /**
     * Formats a request line.
     *
     * @param reqline           the request line to format
     * @param formatter         the formatter to use, or
     *                          <code>null</code> for the
     *                          {@link #DEFAULT default}
     *
     * @return  the formatted request line
     */
    public final static String formatRequestLine(final RequestLine reqline,
                                                 LineFormatter formatter) {
        if (formatter == null)
            formatter = BasicLineFormatter.DEFAULT;
        return formatter.formatRequestLine(null, reqline).toString();
    }


    // non-javadoc, see interface LineFormatter
    public CharArrayBuffer formatRequestLine(CharArrayBuffer buffer,
                                             RequestLine reqline) {
        if (reqline == null) {
            throw new IllegalArgumentException
                ("Request line may not be null");
        }

        CharArrayBuffer result = initBuffer(buffer);
        doFormatRequestLine(result, reqline);

        return result;
    }


    /**
     * Actually formats a request line.
     * Called from {@link #formatRequestLine}.
     *
     * @param buffer    the empty buffer into which to format,
     *                  never <code>null</code>
     * @param reqline   the request line to format, never <code>null</code>
     */
    protected void doFormatRequestLine(final CharArrayBuffer buffer,
                                       final RequestLine reqline) {
        final String method = reqline.getMethod();
        final String uri    = reqline.getUri();

        // room for "GET /index.html HTTP/1.1"
        int len = method.length() + 1 + uri.length() + 1 + 
            estimateProtocolVersionLen(reqline.getProtocolVersion());
        buffer.ensureCapacity(len);

        buffer.append(method);
        buffer.append(' ');
        buffer.append(uri);
        buffer.append(' ');
        appendProtocolVersion(buffer, reqline.getProtocolVersion());
    }



    /**
     * Formats a status line.
     *
     * @param statline          the status line to format
     * @param formatter         the formatter to use, or
     *                          <code>null</code> for the
     *                          {@link #DEFAULT default}
     *
     * @return  the formatted status line
     */
    public final static String formatStatusLine(final StatusLine statline,
                                                LineFormatter formatter) {
        if (formatter == null)
            formatter = BasicLineFormatter.DEFAULT;
        return formatter.formatStatusLine(null, statline).toString();
    }


    // non-javadoc, see interface LineFormatter
    public CharArrayBuffer formatStatusLine(final CharArrayBuffer buffer,
                                            final StatusLine statline) {
        if (statline == null) {
            throw new IllegalArgumentException
                ("Status line may not be null");
        }

        CharArrayBuffer result = initBuffer(buffer);
        doFormatStatusLine(result, statline);

        return result;
    }


    /**
     * Actually formats a status line.
     * Called from {@link #formatStatusLine}.
     *
     * @param buffer    the empty buffer into which to format,
     *                  never <code>null</code>
     * @param statline  the status line to format, never <code>null</code>
     */
    protected void doFormatStatusLine(final CharArrayBuffer buffer,
                                      final StatusLine statline) {

        int len = estimateProtocolVersionLen(statline.getProtocolVersion())
            + 1 + 3 + 1; // room for "HTTP/1.1 200 "
        final String reason = statline.getReasonPhrase();
        if (reason != null) {
            len += reason.length();
        }
        buffer.ensureCapacity(len);

        appendProtocolVersion(buffer, statline.getProtocolVersion());
        buffer.append(' ');
        buffer.append(Integer.toString(statline.getStatusCode()));
        buffer.append(' '); // keep whitespace even if reason phrase is empty
        if (reason != null) {
            buffer.append(reason);
        }
    }


    /**
     * Formats a header.
     *
     * @param header            the header to format
     * @param formatter         the formatter to use, or
     *                          <code>null</code> for the
     *                          {@link #DEFAULT default}
     *
     * @return  the formatted header
     */
    public final static String formatHeader(final Header header,
                                            LineFormatter formatter) {
        if (formatter == null)
            formatter = BasicLineFormatter.DEFAULT;
        return formatter.formatHeader(null, header).toString();
    }


    // non-javadoc, see interface LineFormatter
    public CharArrayBuffer formatHeader(CharArrayBuffer buffer,
                                        Header header) {
        if (header == null) {
            throw new IllegalArgumentException
                ("Header may not be null");
        }
        CharArrayBuffer result = null;

        if (header instanceof FormattedHeader) {
            // If the header is backed by a buffer, re-use the buffer
            result = ((FormattedHeader)header).getBuffer();
        } else {
            result = initBuffer(buffer);
            doFormatHeader(result, header);
        }
        return result;

    } // formatHeader


    /**
     * Actually formats a header.
     * Called from {@link #formatHeader}.
     *
     * @param buffer    the empty buffer into which to format,
     *                  never <code>null</code>
     * @param header    the header to format, never <code>null</code>
     */
    protected void doFormatHeader(final CharArrayBuffer buffer,
                                  final Header header) {
        final String name = header.getName();
        final String value = header.getValue();

        int len = name.length() + 2;
        if (value != null) {
            len += value.length();
        }
        buffer.ensureCapacity(len);

        buffer.append(name);
        buffer.append(": ");
        if (value != null) {
            buffer.append(value);
        }
    }


} // class BasicLineFormatter
