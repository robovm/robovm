/* Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package java.net;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.harmony.luni.util.Msg;

/**
 * This class represents a http cookie, which indicates the status information
 * between the client agent side and the server side. According to RFC, there
 * are 3 http cookie specifications. This class is compatible with all the three
 * forms. HttpCookie class can accept all these 3 forms of syntax.
 * 
 * @since 1.6
 */
public final class HttpCookie implements Cloneable {

    private abstract static class Setter {
        boolean set;

        Setter() {
            set = false;
        }

        boolean isSet() {
            return set;
        }

        void set(boolean isSet) {
            set = isSet;
        }

        abstract void setValue(String value, HttpCookie cookie);

        void validate(String value, HttpCookie cookie) {
            if (cookie.getVersion() == 1 && value != null
                    && value.contains(COMMA_STR)) {
                throw new IllegalArgumentException();
            }
        }
    }

    private static final String DOT_STR = "."; //$NON-NLS-1$

    private static final String LOCAL_STR = ".local"; //$NON-NLS-1$

    private static final String QUOTE_STR = "\""; //$NON-NLS-1$

    private static final String COMMA_STR = ","; //$NON-NLS-1$

    private static Pattern HEAD_PATTERN = Pattern.compile("Set-Cookie2?:", //$NON-NLS-1$
            Pattern.CASE_INSENSITIVE);

    private static Pattern NAME_PATTERN = Pattern
            .compile(
                    "([^$=,\u0085\u2028\u2029][^,\n\t\r\r\n\u0085\u2028\u2029]*?)=([^;]*)(;)?", //$NON-NLS-1$
                    Pattern.DOTALL | Pattern.CASE_INSENSITIVE);

    private static Pattern ATTR_PATTERN0 = Pattern
            .compile("([^;=]*)(?:=([^;]*))?"); //$NON-NLS-1$

    private static Pattern ATTR_PATTERN1 = Pattern
            .compile("(,?[^;=]*)(?:=([^;,]*))?((?=.))?"); //$NON-NLS-1$

    private HashMap<String, Setter> attributeSet = new HashMap<String, Setter>();

    /**
     * A utility method used to check whether the host name is in a domain or
     * not.
     * 
     * @param domain
     *            the domain to be checked against
     * @param host
     *            the host to be checked
     * @return true if the host is in the domain, false otherwise
     */
    public static boolean domainMatches(String domain, String host) {
        if (domain == null || host == null) {
            return false;
        }
        String newDomain = domain.toLowerCase();
        String newHost = host.toLowerCase();
        return isValidDomain(newDomain) && effDomainMatches(newDomain, newHost)
                && isValidHost(newDomain, newHost);
    }

    private static boolean effDomainMatches(String domain, String host) {
        // calculate effective host name
        String effHost = host.indexOf(DOT_STR) != -1 ? host
                : (host + LOCAL_STR);

        // Rule 2: domain and host are string-compare equal, or A = NB, B = .B'
        // and N is a non-empty name string
        boolean inDomain = domain.equals(effHost);
        inDomain = inDomain
                || (effHost.endsWith(domain)
                        && effHost.length() > domain.length() && domain
                        .startsWith(DOT_STR));
        return inDomain;
    }

    private static boolean isCommaDelim(HttpCookie cookie) {
        String value = cookie.getValue();
        if (value.startsWith(QUOTE_STR) && value.endsWith(QUOTE_STR)) {
            cookie.setValue(value.substring(1, value.length() - 1));
            return false;
        }
        if (cookie.getVersion() == 1 && value.contains(COMMA_STR)) {
            cookie.setValue(value.substring(0, value.indexOf(COMMA_STR)));
            return true;
        }
        return false;
    }

    private static boolean isValidDomain(String domain) {
        // Rule 1: The value for Domain contains embedded dots, or is .local
        if (domain.length() <= 2) {
            return false;
        }
        return domain.substring(1, domain.length() - 1).indexOf(DOT_STR) != -1
                || domain.equals(LOCAL_STR);
    }

    private static boolean isValidHost(String domain, String host) {
        // Rule 3: host does not end with domain, or the remainder does not
        // contain "."
        boolean matches = !host.endsWith(domain);
        if (!matches) {
            String hostSub = host.substring(0, host.length() - domain.length());
            matches = hostSub.indexOf(DOT_STR) == -1;
        }
        return matches;
    }

    /**
     * Constructs a cookie from a string. The string should comply with
     * set-cookie or set-cookie2 header format as specified in RFC 2965. Since
     * set-cookies2 syntax allows more than one cookie definitions in one
     * header, the returned object is a list.
     * 
     * @param header
     *            a set-cookie or set-cookie2 header.
     * @return a list of constructed cookies
     * @throws IllegalArgumentException
     *             if the string does not comply with cookie specification, or
     *             the cookie name contains illegal characters, or reserved
     *             tokens of cookie specification appears
     * @throws NullPointerException
     *             if header is null
     */
    public static List<HttpCookie> parse(String header) {
        Matcher matcher = HEAD_PATTERN.matcher(header);
        // Parse cookie name & value
        List<HttpCookie> list = null;
        HttpCookie cookie = null;
        String headerString = header;
        int version = 0;
        // process set-cookie | set-cookie2 head
        if (matcher.find()) {
            String cookieHead = matcher.group();
            if ("set-cookie2:".equalsIgnoreCase(cookieHead)) { //$NON-NLS-1$
                version = 1;
            }
            headerString = header.substring(cookieHead.length());
        }

        // parse cookie name/value pair
        matcher = NAME_PATTERN.matcher(headerString);
        if (matcher.lookingAt()) {
            list = new ArrayList<HttpCookie>();
            cookie = new HttpCookie(matcher.group(1), matcher.group(2));
            cookie.setVersion(version);

            /*
             * Comma is a delimiter in cookie spec 1.1. If find comma in version
             * 1 cookie header, part of matched string need to be spitted out.
             */
            String nameGroup = matcher.group();
            if (isCommaDelim(cookie)) {
                headerString = headerString.substring(nameGroup
                        .indexOf(COMMA_STR));
            } else {
                headerString = headerString.substring(nameGroup.length());
            }
            list.add(cookie);
        } else {
            throw new IllegalArgumentException();
        }

        // parse cookie headerString
        while (!(headerString.length() == 0)) {
            matcher = cookie.getVersion() == 1 ? ATTR_PATTERN1
                    .matcher(headerString) : ATTR_PATTERN0
                    .matcher(headerString);

            if (matcher.lookingAt()) {
                String attrName = matcher.group(1).trim();

                // handle special situation like: <..>;;<..>
                if (attrName.length() == 0) {
                    headerString = headerString.substring(1);
                    continue;
                }

                // If port is the attribute, then comma will not be used as a
                // delimiter
                if (attrName.equalsIgnoreCase("port") //$NON-NLS-1$
                        || attrName.equalsIgnoreCase("expires")) { //$NON-NLS-1$
                    int start = matcher.regionStart();
                    matcher = ATTR_PATTERN0.matcher(headerString);
                    matcher.region(start, headerString.length());
                    matcher.lookingAt();
                } else if (cookie.getVersion() == 1
                        && attrName.startsWith(COMMA_STR)) {
                    // If the last encountered token is comma, and the parsed
                    // attribute is not port, then this attribute/value pair
                    // ends.
                    headerString = headerString.substring(1);
                    matcher = NAME_PATTERN.matcher(headerString);
                    if (matcher.lookingAt()) {
                        cookie = new HttpCookie(matcher.group(1), matcher
                                .group(2));
                        list.add(cookie);
                        headerString = headerString.substring(matcher.group()
                                .length());
                        continue;
                    }
                }

                Setter setter = cookie.attributeSet.get(attrName.toLowerCase());
                if (null == setter) {
                    throw new IllegalArgumentException();
                }
                if (!setter.isSet()) {
                    String attrValue = matcher.group(2);
                    setter.validate(attrValue, cookie);
                    setter.setValue(matcher.group(2), cookie);
                }
                headerString = headerString.substring(matcher.end());
            }
        }

        return list;
    }

    private String comment;

    private String commentURL;

    private boolean discard;

    private String domain;

    private long maxAge = -1l;

    private String name;

    private String path;

    private String portList;

    private boolean secure;

    private String value;

    private int version = 1;

    {
        attributeSet.put("comment", new Setter() { //$NON-NLS-1$
                    @Override
                    void setValue(String value, HttpCookie cookie) {
                        cookie.setComment(value);
                        if (cookie.getComment() != null) {
                            set(true);
                        }
                    }
                });
        attributeSet.put("commenturl", new Setter() { //$NON-NLS-1$
                    @Override
                    void setValue(String value, HttpCookie cookie) {
                        cookie.setCommentURL(value);
                        if (cookie.getCommentURL() != null) {
                            set(true);
                        }
                    }
                });
        attributeSet.put("discard", new Setter() { //$NON-NLS-1$
                    @Override
                    void setValue(String value, HttpCookie cookie) {
                        cookie.setDiscard(true);
                        set(true);
                    }
                });
        attributeSet.put("domain", new Setter() { //$NON-NLS-1$
                    @Override
                    void setValue(String value, HttpCookie cookie) {
                        cookie.setDomain(value);
                        if (cookie.getDomain() != null) {
                            set(true);
                        }
                    }
                });
        attributeSet.put("max-age", new Setter() { //$NON-NLS-1$
                    @Override
                    void setValue(String value, HttpCookie cookie) {
                        try {
                            cookie.setMaxAge(Long.parseLong(value));
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException(Msg.getString(
                                    "KB001", "max-age")); //$NON-NLS-1$//$NON-NLS-2$
                        }
                        set(true);

                        if (!attributeSet.get("version").isSet()) { //$NON-NLS-1$
                            cookie.setVersion(1);
                        }
                    }
                });

        attributeSet.put("path", new Setter() { //$NON-NLS-1$
                    @Override
                    void setValue(String value, HttpCookie cookie) {
                        cookie.setPath(value);
                        if (cookie.getPath() != null) {
                            set(true);
                        }
                    }
                });
        attributeSet.put("port", new Setter() { //$NON-NLS-1$
                    @Override
                    void setValue(String value, HttpCookie cookie) {
                        cookie.setPortlist(value);
                        if (cookie.getPortlist() != null) {
                            set(true);
                        }
                    }

                    @Override
                    void validate(String v, HttpCookie cookie) {
                        return;
                    }
                });
        attributeSet.put("secure", new Setter() { //$NON-NLS-1$
                    @Override
                    void setValue(String value, HttpCookie cookie) {
                        cookie.setSecure(true);
                        set(true);
                    }
                });
        attributeSet.put("version", new Setter() { //$NON-NLS-1$
                    @Override
                    void setValue(String value, HttpCookie cookie) {
                        try {
                            int v = Integer.parseInt(value);
                            if (v > cookie.getVersion()) {
                                cookie.setVersion(v);
                            }
                        } catch (NumberFormatException e) {
                            throw new IllegalArgumentException(Msg.getString(
                                    "KB001", "version"));//$NON-NLS-1$//$NON-NLS-2$
                        }
                        if (cookie.getVersion() != 0) {
                            set(true);
                        }
                    }
                });

        attributeSet.put("expires", new Setter() { //$NON-NLS-1$
                    @Override
                    void setValue(String value, HttpCookie cookie) {
                        cookie.setVersion(0);
                        attributeSet.get("version").set(true); //$NON-NLS-1$
                        if (!attributeSet.get("max-age").isSet()) { //$NON-NLS-1$
                            attributeSet.get("max-age").set(true); //$NON-NLS-1$
                            if (!"en".equalsIgnoreCase(Locale.getDefault() //$NON-NLS-1$
                                    .getLanguage())) {
                                cookie.setMaxAge(0);
                                return;
                            }
                            try {
                                cookie.setMaxAge((Date.parse(value) - System
                                        .currentTimeMillis()) / 1000);
                            } catch (IllegalArgumentException e) {
                                cookie.setMaxAge(0);
                            }
                        }
                    }

                    @Override
                    void validate(String v, HttpCookie cookie) {
                        return;
                    }
                });
    }

    /**
     * Initializes a cookie with the specified name and value.
     * 
     * The name attribute can just contain ASCII characters, which is immutable
     * after creation. Commas, white space and semicolons are not allowed. The $
     * character is also not allowed to be the beginning of the name.
     * 
     * The value attribute depends on what the server side is interested. The
     * setValue method can be used to change it.
     * 
     * RFC 2965 is the default cookie specification of this class. If one wants
     * to change the version of the cookie, the setVersion method is available.
     * 
     * @param name -
     *            the specific name of the cookie
     * @param value -
     *            the specific value of the cookie
     * 
     * @throws IllegalArgumentException -
     *             if the name contains not-allowed or reserved characters
     * 
     * @throws NullPointerException
     *             if the value of name is null
     */
    public HttpCookie(String name, String value) {
        String ntrim = name.trim(); // erase leading and trailing whitespaces
        if (!isValidName(ntrim)) {
            throw new IllegalArgumentException(Msg.getString("KB002")); //$NON-NLS-1$
        }

        this.name = ntrim;
        this.value = value;
    }

    private void attrToString(StringBuilder builder, String attrName,
            String attrValue) {
        if (attrValue != null && builder != null) {
            builder.append(";"); //$NON-NLS-1$ 
            builder.append("$");//$NON-NLS-1$ 
            builder.append(attrName);
            builder.append("=\""); //$NON-NLS-1$			
            builder.append(attrValue);
            builder.append(QUOTE_STR);
        }
    }

    /**
     * Answers a copy of this object.
     * 
     * @return a copy of this cookie
     */
    @Override
    public Object clone() {
        try {
            HttpCookie obj = (HttpCookie) super.clone();
            return obj;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    /**
     * Answers whether two cookies are equal. Two cookies are equal if they have
     * the same domain and name in a case-insensitive mode and path in a
     * case-sensitive mode.
     * 
     * @param obj
     *            the object to be compared.
     * @return true if two cookies equals, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof HttpCookie) {
            HttpCookie anotherCookie = (HttpCookie) obj;
            if (name.equalsIgnoreCase(anotherCookie.getName())) {
                String anotherDomain = anotherCookie.getDomain();
                boolean equals = domain == null ? anotherDomain == null
                        : domain.equalsIgnoreCase(anotherDomain);
                if (equals) {
                    String anotherPath = anotherCookie.getPath();
                    return path == null ? anotherPath == null : path
                            .equals(anotherPath);
                }
            }
        }
        return false;
    }

    /**
     * Answers the value of comment attribute(specified in RFC 2965) of this
     * cookie.
     * 
     * @return the value of comment attribute
     */
    public String getComment() {
        return comment;
    }

    /**
     * Answers the value of commentURL attribute(specified in RFC 2965) of this
     * cookie.
     * 
     * @return the value of commentURL attribute
     */
    public String getCommentURL() {
        return commentURL;
    }

    /**
     * Answers the value of discard attribute(specified in RFC 2965) of this
     * cookie.
     * 
     * @return discard value of this cookie
     */
    public boolean getDiscard() {
        return discard;
    }

    /**
     * Answers the domain name for this cookie in the format specified in RFC
     * 2965
     * 
     * @return the domain value of this cookie
     */
    public String getDomain() {
        return domain;
    }

    /**
     * Returns the Max-Age value as specified in RFC 2965 of this cookie.
     * 
     * @return the Max-Age value
     */
    public long getMaxAge() {
        return maxAge;
    }

    /**
     * Answers the name for this cookie.
     * 
     * @return the name for this cookie
     */
    public String getName() {
        return name;
    }

    /**
     * Answers the path part of a request URL to which this cookie is returned.
     * This cookie is visible to all subpaths.
     * 
     * @return the path used to return the cookie
     */
    public String getPath() {
        return path;
    }

    /**
     * Answers the value of port attribute(specified in RFC 2965) of this
     * cookie.
     * 
     * @return port list of this cookie
     */
    public String getPortlist() {
        return portList;
    }

    /**
     * Answers true if the browser only sends cookies over a secure protocol.
     * False if can send cookies through any protocols.
     * 
     * @return true if sends cookies only through secure protocol, false
     *         otherwise
     */
    public boolean getSecure() {
        return secure;
    }

    /**
     * Answers the value of this cookie.
     * 
     * @return the value of this cookie
     */
    public String getValue() {
        return value;
    }

    /**
     * Get the version of this cookie
     * 
     * @return 0 indicates the original Netscape cookie specification, while 1
     *         indicates RFC 2965/2109 specification.
     */
    public int getVersion() {
        return version;
    }

    /**
     * Answers whether the cookie has expired.
     * 
     * @return true is the cookie has expired, false otherwise
     */
    public boolean hasExpired() {
        // -1 indicates the cookie will persist until browser shutdown
        // so the cookie is not expired.
        if (maxAge == -1l) {
            return false;
        }

        boolean expired = false;
        if (maxAge <= 0l) {
            expired = true;
        }
        return expired;
    }

    /**
     * Answers hash code of this http cookie. The result is calculated as below:
     * 
     * getName().toLowerCase().hashCode() + getDomain().toLowerCase().hashCode() +
     * getPath().hashCode()
     * 
     * @return the hash code of this cookie
     */
    @Override
    public int hashCode() {
        int hashCode = name.toLowerCase().hashCode();
        hashCode += domain == null ? 0 : domain.toLowerCase().hashCode();
        hashCode += path == null ? 0 : path.hashCode();
        return hashCode;
    }

    private boolean isValidName(String n) {
        // name cannot be empty or begin with '$' or equals the reserved
        // attributes (case-insensitive)
        boolean isValid = !(n.length() == 0 || n.startsWith("$") || attributeSet.containsKey(n.toLowerCase())); //$NON-NLS-1$
        if (isValid) {
            for (int i = 0; i < n.length(); i++) {
                char nameChar = n.charAt(i);
                // name must be ASCII characters and cannot contain ';', ',' and
                // whitespace
                if (nameChar < 0
                        || nameChar >= 127
                        || nameChar == ';'
                        || nameChar == ','
                        || (Character.isWhitespace(nameChar) && nameChar != ' ')) {
                    isValid = false;
                    break;
                }
            }
        }
        return isValid;
    }

    /**
     * Set the value of comment attribute(specified in RFC 2965) of this cookie.
     * 
     * @param purpose
     *            the comment value to be set
     */
    public void setComment(String purpose) {
        comment = purpose;
    }

    /**
     * Set the value of commentURL attribute(specified in RFC 2965) of this
     * cookie.
     * 
     * @param purpose
     *            the value of commentURL attribute to be set
     */
    public void setCommentURL(String purpose) {
        commentURL = purpose;
    }

    /**
     * Set the value of discard attribute(specified in RFC 2965) of this cookie.
     * 
     * @param discard
     *            the value for discard attribute
     */
    public void setDiscard(boolean discard) {
        this.discard = discard;
    }

    /**
     * Set the domain value for this cookie. Browsers send the cookie to the
     * domain specified by this value. The form of the domain is specified in
     * RFC 2965.
     * 
     * @param pattern
     *            the domain pattern
     */
    public void setDomain(String pattern) {
        domain = pattern == null ? null : pattern.toLowerCase();
    }

    /**
     * Sets the Max-Age value as specified in RFC 2965 of this cookie to expire.
     * 
     * @param expiry
     *            the value used to set the Max-Age value of this cookie
     */
    public void setMaxAge(long expiry) {
        maxAge = expiry;
    }

    /**
     * Set the path to which this cookie is returned. This cookie is visible to
     * all the pages under the path and all subpaths.
     * 
     * @param path
     *            the path to which this cookie is returned
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * Set the value of port attribute(specified in RFC 2965) of this cookie.
     * 
     * @param ports
     *            the value for port attribute
     */
    public void setPortlist(String ports) {
        portList = ports;
    }

    /*
     * Handle 2 special cases: 1. value is wrapped by a quotation 2. value
     * contains comma
     */

    /**
     * Tells the browser whether the cookies should be sent to server through
     * secure protocols.
     * 
     * @param flag
     *            tells browser to send cookie to server only through secure
     *            protocol if flag is true
     */
    public void setSecure(boolean flag) {
        secure = flag;
    }

    /**
     * Sets the value for this cookie after it has been instantiated. String
     * newValue can be in BASE64 form. If the version of the cookie is 0,
     * special value as: white space, brackets, parentheses, equals signs,
     * commas, double quotes, slashes, question marks, at signs, colons, and
     * semicolons are not recommended. Empty values may lead to different
     * behavior on different browsers.
     * 
     * @param newValue
     *            the value for this cookie
     */
    public void setValue(String newValue) {
        // FIXME: According to spec, version 0 cookie value does not allow many
        // symbols. But RI does not implement it. Follow RI temporarily.
        value = newValue;
    }

    /**
     * Sets the version of the cookie. 0 indicates the original Netscape cookie
     * specification, while 1 indicates RFC 2965/2109 specification.
     * 
     * @param v
     *            0 or 1 as stated above
     * @throws IllegalArgumentException
     *             if v is neither 0 nor 1
     */
    public void setVersion(int v) {
        if (v != 0 && v != 1) {
            throw new IllegalArgumentException(Msg.getString("KB003")); //$NON-NLS-1$
        }
        version = v;
    }

    /**
     * Returns a string to represent the cookie. The format of string follows
     * the cookie specification. The leading token "Cookie" is not included
     * 
     * @return the string format of the cookie object
     */
    @Override
    public String toString() {
        StringBuilder cookieStr = new StringBuilder();
        cookieStr.append(name);
        cookieStr.append("="); //$NON-NLS-1$
        if (version == 0) {
            cookieStr.append(value);
        } else if (version == 1) {
            cookieStr.append(QUOTE_STR);
            cookieStr.append(value);
            cookieStr.append(QUOTE_STR);

            attrToString(cookieStr, "Path", path); //$NON-NLS-1$
            attrToString(cookieStr, "Domain", domain); //$NON-NLS-1$
            attrToString(cookieStr, "Port", portList);//$NON-NLS-1$		
        }
        return cookieStr.toString();
    }
}
