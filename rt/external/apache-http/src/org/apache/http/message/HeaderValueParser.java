/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpcore/trunk/module-main/src/main/java/org/apache/http/message/HeaderValueParser.java $
 * $Revision: 589325 $
 * $Date: 2007-10-28 03:37:56 -0700 (Sun, 28 Oct 2007) $
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


import org.apache.http.HeaderElement;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.util.CharArrayBuffer;



/**
 * Interface for parsing header values into elements.
 * Instances of this interface are expected to be stateless and thread-safe.
 *
 *
 * <!-- empty lines above to avoid 'svn diff' context problems -->
 * @version $Revision: 589325 $ $Date: 2007-10-28 03:37:56 -0700 (Sun, 28 Oct 2007) $
 *
 * @since 4.0
 */
public interface HeaderValueParser {

    /**
     * Parses a header value into elements.
     * Parse errors are indicated as <code>RuntimeException</code>.
     * <p>
     * Some HTTP headers (such as the set-cookie header) have values that
     * can be decomposed into multiple elements. In order to be processed
     * by this parser, such headers must be in the following form:
     * </p>
     * <pre>
     * header  = [ element ] *( "," [ element ] )
     * element = name [ "=" [ value ] ] *( ";" [ param ] )
     * param   = name [ "=" [ value ] ]
     *
     * name    = token
     * value   = ( token | quoted-string )
     *
     * token         = 1*&lt;any char except "=", ",", ";", &lt;"&gt; and
     *                       white space&gt;
     * quoted-string = &lt;"&gt; *( text | quoted-char ) &lt;"&gt;
     * text          = any char except &lt;"&gt;
     * quoted-char   = "\" char
     * </pre>
     * <p>
     * Any amount of white space is allowed between any part of the
     * header, element or param and is ignored. A missing value in any
     * element or param will be stored as the empty {@link String};
     * if the "=" is also missing <var>null</var> will be stored instead.
     * </p>
     *
     * @param buffer    buffer holding the header value to parse
     * @param cursor    the parser cursor containing the current position and 
     *                  the bounds within the buffer for the parsing operation
     *
     * @return  an array holding all elements of the header value
     *
     * @throws ParseException        in case of a parse error
     */
    HeaderElement[] parseElements(
            CharArrayBuffer buffer,
            ParserCursor cursor) throws ParseException;

    /**
     * Parses a single header element.
     * A header element consist of a semicolon-separate list
     * of name=value definitions.
     *
     * @param buffer    buffer holding the element to parse
     * @param cursor    the parser cursor containing the current position and 
     *                  the bounds within the buffer for the parsing operation
     *
     * @return  the parsed element
     *
     * @throws ParseException        in case of a parse error
     */
    HeaderElement parseHeaderElement(
            CharArrayBuffer buffer,
            ParserCursor cursor) throws ParseException;

    /**
     * Parses a list of name-value pairs.
     * These lists are used to specify parameters to a header element.
     * Parse errors are indicated as <code>RuntimeException</code>.
     * <p>
     * This method comforms to the generic grammar and formatting rules
     * outlined in the 
     * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec2.html#sec2.2"
     *   >Section 2.2</a>
     * and
     * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec3.html#sec3.6"
     *   >Section 3.6</a>
     * of
     * <a href="http://www.w3.org/Protocols/rfc2616/rfc2616.txt">RFC 2616</a>.
     * </p>
     * <h>2.2 Basic Rules</h>
     * <p>
     * The following rules are used throughout this specification to
     * describe basic parsing constructs. 
     * The US-ASCII coded character set is defined by ANSI X3.4-1986.
     * </p>
     * <pre>
     *     OCTET          = <any 8-bit sequence of data>
     *     CHAR           = <any US-ASCII character (octets 0 - 127)>
     *     UPALPHA        = <any US-ASCII uppercase letter "A".."Z">
     *     LOALPHA        = <any US-ASCII lowercase letter "a".."z">
     *     ALPHA          = UPALPHA | LOALPHA
     *     DIGIT          = <any US-ASCII digit "0".."9">
     *     CTL            = <any US-ASCII control character
     *                      (octets 0 - 31) and DEL (127)>
     *     CR             = <US-ASCII CR, carriage return (13)>
     *     LF             = <US-ASCII LF, linefeed (10)>
     *     SP             = <US-ASCII SP, space (32)>
     *     HT             = <US-ASCII HT, horizontal-tab (9)>
     *     <">            = <US-ASCII double-quote mark (34)>
     * </pre>
     * <p>
     * Many HTTP/1.1 header field values consist of words separated
     * by LWS or special characters. These special characters MUST be
     * in a quoted string to be used within 
     * a parameter value (as defined in section 3.6).
     * <p>
     * <pre>
     * token          = 1*<any CHAR except CTLs or separators>
     * separators     = "(" | ")" | "<" | ">" | "@"
     *                | "," | ";" | ":" | "\" | <">
     *                | "/" | "[" | "]" | "?" | "="
     *                | "{" | "}" | SP | HT
     * </pre>
     * <p>
     * A string of text is parsed as a single word if it is quoted using
     * double-quote marks.
     * </p>
     * <pre>
     * quoted-string  = ( <"> *(qdtext | quoted-pair ) <"> )
     * qdtext         = <any TEXT except <">>
     * </pre>
     * <p>
     * The backslash character ("\") MAY be used as a single-character
     * quoting mechanism only within quoted-string and comment constructs.
     * </p>
     * <pre>
     * quoted-pair    = "\" CHAR
     * </pre>
     * <h>3.6 Transfer Codings</h>
     * <p>
     * Parameters are in the form of attribute/value pairs.
     * </p>
     * <pre>
     * parameter               = attribute "=" value
     * attribute               = token
     * value                   = token | quoted-string
     * </pre> 
     *
     * @param buffer    buffer holding the name-value list to parse
     * @param cursor    the parser cursor containing the current position and 
     *                  the bounds within the buffer for the parsing operation
     *
     * @return  an array holding all items of the name-value list
     *
     * @throws ParseException        in case of a parse error
     */
    NameValuePair[] parseParameters(
            CharArrayBuffer buffer,
            ParserCursor cursor) throws ParseException;


    /**
     * Parses a name=value specification, where the = and value are optional.
     *
     * @param buffer    the buffer holding the name-value pair to parse
     * @param cursor    the parser cursor containing the current position and 
     *                  the bounds within the buffer for the parsing operation
     *
     * @return  the name-value pair, where the value is <code>null</code>
     *          if no value is specified
     */
    NameValuePair parseNameValuePair(
            CharArrayBuffer buffer,
            ParserCursor cursor) throws ParseException;
    
}

