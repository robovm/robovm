/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
* @author Alexander V. Esin
* @version $Revision$
*/

package org.apache.harmony.security.x501;

import java.io.IOException;
import org.apache.harmony.security.asn1.ASN1StringType;
import org.apache.harmony.security.asn1.DerInputStream;

/**
 * X.501 Attribute Value
 */
public final class AttributeValue {

    public final boolean wasEncoded;

    public String escapedString;

    private String hexString;

    private int tag = -1;

    public byte[] encoded;

    public byte[] bytes; //FIXME remove??? bytes to be encoded

    public boolean hasQE; // raw string contains '"' or '\'

    public String rawString;

    public AttributeValue(String parsedString, boolean hasQorE) {
        wasEncoded = false;

        this.hasQE = hasQorE;

        this.rawString = parsedString;
        this.escapedString = makeEscaped(rawString);
    }

    public AttributeValue(String hexString, byte[] encoded) {
        wasEncoded = true;

        this.hexString = hexString;
        this.encoded = encoded;

        try {
            DerInputStream in = new DerInputStream(encoded);

            tag = in.tag;

            if (DirectoryString.ASN1.checkTag(tag)) {
                // has string representation
                this.rawString = (String) DirectoryString.ASN1.decode(in);
                this.escapedString = makeEscaped(rawString);
            } else {
                this.rawString = hexString;
                this.escapedString = hexString;
            }
        } catch (IOException e) {
            IllegalArgumentException iae = new IllegalArgumentException(); //FIXME message
            iae.initCause(e);
            throw iae;
        }
    }

    public AttributeValue(String rawString, byte[] encoded, int tag) {
        wasEncoded = true;

        this.encoded = encoded;
        this.tag = tag;

        if (rawString == null) {
            this.rawString = getHexString();
            this.escapedString = hexString;
        } else {
            this.rawString = rawString;
            this.escapedString = makeEscaped(rawString);
        }
    }

    /**
     * Checks if the string is PrintableString (see X.680)
     */
    private static boolean isPrintableString(String str) {
        for (int i = 0; i< str.length(); ++i) {
            char ch = str.charAt(i);
            if (!(ch == 0x20
            || ch >= 0x27 && ch<= 0x29 // '()
            || ch >= 0x2B && ch<= 0x3A // +,-./0-9:
            || ch == '='
            || ch == '?'
            || ch >= 'A' && ch<= 'Z'
            || ch >= 'a' && ch<= 'z')) {
                return false;
            }
        }
        return true;
    }

    public int getTag() {
        if (tag == -1) {
            tag = isPrintableString(rawString)
                    ? ASN1StringType.PRINTABLESTRING.id
                    : ASN1StringType.UTF8STRING.id;
        }
        return tag;
    }

    public String getHexString() {
        if (hexString == null) {
            if (!wasEncoded) {
                //FIXME optimize me: what about reusable OutputStream???
                encoded = isPrintableString(rawString)
                        ? ASN1StringType.PRINTABLESTRING.encode(rawString)
                        : ASN1StringType.UTF8STRING.encode(rawString);
            }

            StringBuilder buf = new StringBuilder(encoded.length * 2 + 1);
            buf.append('#');

            for (int i = 0, c; i < encoded.length; i++) {
                c = (encoded[i] >> 4) & 0x0F;
                if (c < 10) {
                    buf.append((char) (c + 48));
                } else {
                    buf.append((char) (c + 87));
                }

                c = encoded[i] & 0x0F;
                if (c < 10) {
                    buf.append((char) (c + 48));
                } else {
                    buf.append((char) (c + 87));
                }
            }
            hexString = buf.toString();
        }
        return hexString;
    }

    public void appendQEString(StringBuilder sb) {
        sb.append('"');
        if (hasQE) {
            char c;
            for (int i = 0; i < rawString.length(); i++) {
                c = rawString.charAt(i);
                if (c == '"' || c == '\\') {
                    sb.append('\\');
                }
                sb.append(c);
            }
        } else {
            sb.append(rawString);
        }
        sb.append('"');
    }

    /**
     * Escapes:
     * 1) chars ",", "+", """, "\", "<", ">", ";" (RFC 2253)
     * 2) chars "#", "=" (required by RFC 1779)
     * 3) a space char at the beginning or end
     * 4) according to the requirement to be RFC 1779 compatible:
     *    '#' char is escaped in any position
     */
    private String makeEscaped(String name) {
        int length = name.length();
        if (length == 0) {
            return name;
        }
        StringBuilder buf = new StringBuilder(length * 2);

        for (int index = 0; index < length; index++) {
            char ch = name.charAt(index);
            switch (ch) {
            case ' ':
                if (index == 0 || index == (length - 1)) {
                    // escape first or last space
                    buf.append('\\');
                }
                buf.append(' ');
                break;

            case '"':
            case '\\':
                hasQE = true;
                buf.append('\\');
                buf.append(ch);
                break;

            case ',':
            case '+':
            case '<':
            case '>':
            case ';':
            case '#': // required by RFC 1779
            case '=': // required by RFC 1779
                buf.append('\\');
                buf.append(ch);
                break;

            default:
                buf.append(ch);
                break;
            }
        }

        return buf.toString();
    }

    public String makeCanonical() {
        int length = rawString.length();
        if (length == 0) {
            return rawString;
        }
        StringBuilder buf = new StringBuilder(length * 2);

        int index = 0;
        if (rawString.charAt(0) == '#') {
            buf.append('\\');
            buf.append('#');
            index++;
        }

        int bufLength;
        for (; index < length; index++) {
            char ch = rawString.charAt(index);

            switch (ch) {
            case ' ':
                bufLength = buf.length();
                if (bufLength == 0 || buf.charAt(bufLength - 1) == ' ') {
                    break;
                }
                buf.append(' ');
                break;

            case '"':
            case '\\':
            case ',':
            case '+':
            case '<':
            case '>':
            case ';':
                buf.append('\\');

            default:
                buf.append(ch);
            }
        }

        //remove trailing spaces
        for (bufLength = buf.length() - 1; bufLength > -1
                && buf.charAt(bufLength) == ' '; bufLength--) {
        }
        buf.setLength(bufLength + 1);

        return buf.toString();
    }
}
