/*
 * $HeadURL: http://svn.apache.org/repos/asf/httpcomponents/httpclient/trunk/module-client/src/main/java/org/apache/http/impl/auth/DigestScheme.java $
 * $Revision: 659595 $
 * $Date: 2008-05-23 09:47:14 -0700 (Fri, 23 May 2008) $
 *
 * ====================================================================
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.impl.auth;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.auth.AuthenticationException;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.AUTH;
import org.apache.http.auth.MalformedChallengeException;
import org.apache.http.auth.params.AuthParams;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.message.BasicHeaderValueFormatter;
import org.apache.http.message.BufferedHeader;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EncodingUtils;

/**
 * <p>
 * Digest authentication scheme as defined in RFC 2617.
 * Both MD5 (default) and MD5-sess are supported.
 * Currently only qop=auth or no qop is supported. qop=auth-int
 * is unsupported. If auth and auth-int are provided, auth is
 * used.
 * </p>
 * <p>
 * Credential charset is configured via the 
 * {@link org.apache.http.auth.params.AuthPNames#CREDENTIAL_CHARSET
 *        credential charset} parameter.
 * Since the digest username is included as clear text in the generated 
 * Authentication header, the charset of the username must be compatible
 * with the 
 * {@link org.apache.http.params.CoreProtocolPNames#HTTP_ELEMENT_CHARSET
 *        http element charset}.
 * </p>
 * 
 * @author <a href="mailto:remm@apache.org">Remy Maucherat</a>
 * @author Rodney Waldhoff
 * @author <a href="mailto:jsdever@apache.org">Jeff Dever</a>
 * @author Ortwin Glueck
 * @author Sean C. Sullivan
 * @author <a href="mailto:adrian@ephox.com">Adrian Sutton</a>
 * @author <a href="mailto:mbowler@GargoyleSoftware.com">Mike Bowler</a>
 * @author <a href="mailto:oleg at ural.ru">Oleg Kalnichevski</a>
 * 
 * @since 4.0
 */

public class DigestScheme extends RFC2617Scheme {
    
    /**
     * Hexa values used when creating 32 character long digest in HTTP DigestScheme
     * in case of authentication.
     * 
     * @see #encode(byte[])
     */
    private static final char[] HEXADECIMAL = {
        '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 
        'e', 'f'
    };
    
    /** Whether the digest authentication process is complete */
    private boolean complete;
    
    //TODO: supply a real nonce-count, currently a server will interprete a repeated request as a replay  
    private static final String NC = "00000001"; //nonce-count is always 1
    private static final int QOP_MISSING = 0;
    private static final int QOP_AUTH_INT = 1;
    private static final int QOP_AUTH = 2;

    private int qopVariant = QOP_MISSING;
    private String cnonce;

    /**
     * Default constructor for the digest authetication scheme.
     */
    public DigestScheme() {
        super();
        this.complete = false;
    }

    /**
     * Processes the Digest challenge.
     *  
     * @param header the challenge header
     * 
     * @throws MalformedChallengeException is thrown if the authentication challenge
     * is malformed
     */
    @Override
    public void processChallenge(
            final Header header) throws MalformedChallengeException {
        super.processChallenge(header);
        
        if (getParameter("realm") == null) {
            throw new MalformedChallengeException("missing realm in challange");
        }
        if (getParameter("nonce") == null) {
            throw new MalformedChallengeException("missing nonce in challange");   
        }
        
        boolean unsupportedQop = false;
        // qop parsing
        String qop = getParameter("qop");
        if (qop != null) {
            StringTokenizer tok = new StringTokenizer(qop,",");
            while (tok.hasMoreTokens()) {
                String variant = tok.nextToken().trim();
                if (variant.equals("auth")) {
                    qopVariant = QOP_AUTH;
                    break; //that's our favourite, because auth-int is unsupported
                } else if (variant.equals("auth-int")) {
                    qopVariant = QOP_AUTH_INT;               
                } else {
                    unsupportedQop = true;
                }     
            }
        }        
        
        if (unsupportedQop && (qopVariant == QOP_MISSING)) {
            throw new MalformedChallengeException("None of the qop methods is supported");   
        }
        // Reset cnonce
        this.cnonce = null;
        this.complete = true;
    }

    /**
     * Tests if the Digest authentication process has been completed.
     * 
     * @return <tt>true</tt> if Digest authorization has been processed,
     *   <tt>false</tt> otherwise.
     */
    public boolean isComplete() {
        String s = getParameter("stale");
        if ("true".equalsIgnoreCase(s)) {
            return false;
        } else {
            return this.complete;
        }
    }

    /**
     * Returns textual designation of the digest authentication scheme.
     * 
     * @return <code>digest</code>
     */
    public String getSchemeName() {
        return "digest";
    }

    /**
     * Returns <tt>false</tt>. Digest authentication scheme is request based.
     * 
     * @return <tt>false</tt>.
     */
    public boolean isConnectionBased() {
        return false;    
    }

    public void overrideParamter(final String name, final String value) {
        getParameters().put(name, value);
    }
    
    private String getCnonce() {
        if (this.cnonce == null) {
            this.cnonce = createCnonce();
        }
        return this.cnonce; 
    }
    
    /**
     * Produces a digest authorization string for the given set of 
     * {@link Credentials}, method name and URI.
     * 
     * @param credentials A set of credentials to be used for athentication
     * @param request    The request being authenticated
     * 
     * @throws org.apache.http.auth.InvalidCredentialsException if authentication credentials
     *         are not valid or not applicable for this authentication scheme
     * @throws AuthenticationException if authorization string cannot 
     *   be generated due to an authentication failure
     * 
     * @return a digest authorization string
     */
    public Header authenticate(
            final Credentials credentials, 
            final HttpRequest request) throws AuthenticationException {

        if (credentials == null) {
            throw new IllegalArgumentException("Credentials may not be null");
        }
        if (request == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        
        // Add method name and request-URI to the parameter map
        getParameters().put("methodname", request.getRequestLine().getMethod());
        getParameters().put("uri", request.getRequestLine().getUri());
        String charset = getParameter("charset");
        if (charset == null) {
            charset = AuthParams.getCredentialCharset(request.getParams());
            getParameters().put("charset", charset);
        }
        String digest = createDigest(credentials);
        return createDigestHeader(credentials, digest);
    }
    
    private static MessageDigest createMessageDigest(
            final String digAlg) throws UnsupportedDigestAlgorithmException {
        try {
            return MessageDigest.getInstance(digAlg);
        } catch (Exception e) {
            throw new UnsupportedDigestAlgorithmException(
              "Unsupported algorithm in HTTP Digest authentication: "
               + digAlg);
        }
    }
    
    /**
     * Creates an MD5 response digest.
     * 
     * @return The created digest as string. This will be the response tag's
     *         value in the Authentication HTTP header.
     * @throws AuthenticationException when MD5 is an unsupported algorithm
     */
    private String createDigest(final Credentials credentials) throws AuthenticationException {
        // Collecting required tokens
        String uri = getParameter("uri");
        String realm = getParameter("realm");
        String nonce = getParameter("nonce");
        String method = getParameter("methodname");
        String algorithm = getParameter("algorithm");
        if (uri == null) {
            throw new IllegalStateException("URI may not be null");
        }
        if (realm == null) {
            throw new IllegalStateException("Realm may not be null");
        }
        if (nonce == null) {
            throw new IllegalStateException("Nonce may not be null");
        }
        // If an algorithm is not specified, default to MD5.
        if (algorithm == null) {
            algorithm = "MD5";
        }
        // If an charset is not specified, default to ISO-8859-1.
        String charset = getParameter("charset");
        if (charset == null) {
            charset = "ISO-8859-1";
        }

        if (qopVariant == QOP_AUTH_INT) {
            throw new AuthenticationException(
                "Unsupported qop in HTTP Digest authentication");   
        }

        MessageDigest md5Helper = createMessageDigest("MD5");

        String uname = credentials.getUserPrincipal().getName();
        String pwd = credentials.getPassword();
        
        // 3.2.2.2: Calculating digest
        StringBuilder tmp = new StringBuilder(uname.length() + realm.length() + pwd.length() + 2);
        tmp.append(uname);
        tmp.append(':');
        tmp.append(realm);
        tmp.append(':');
        tmp.append(pwd);
        // unq(username-value) ":" unq(realm-value) ":" passwd
        String a1 = tmp.toString();
        
        //a1 is suitable for MD5 algorithm
        if(algorithm.equalsIgnoreCase("MD5-sess")) { // android-changed: ignore case
            // H( unq(username-value) ":" unq(realm-value) ":" passwd )
            //      ":" unq(nonce-value)
            //      ":" unq(cnonce-value)

            String cnonce = getCnonce();
            
            String tmp2=encode(md5Helper.digest(EncodingUtils.getBytes(a1, charset)));
            StringBuilder tmp3 = new StringBuilder(tmp2.length() + nonce.length() + cnonce.length() + 2);
            tmp3.append(tmp2);
            tmp3.append(':');
            tmp3.append(nonce);
            tmp3.append(':');
            tmp3.append(cnonce);
            a1 = tmp3.toString();
        } else if (!algorithm.equalsIgnoreCase("MD5")) { // android-changed: ignore case
            throw new AuthenticationException("Unhandled algorithm " + algorithm + " requested");
        }
        String md5a1 = encode(md5Helper.digest(EncodingUtils.getBytes(a1, charset)));

        String a2 = null;
        if (qopVariant == QOP_AUTH_INT) {
            // Unhandled qop auth-int
            //we do not have access to the entity-body or its hash
            //TODO: add Method ":" digest-uri-value ":" H(entity-body)      
        } else {
            a2 = method + ':' + uri;
        }
        String md5a2 = encode(md5Helper.digest(EncodingUtils.getAsciiBytes(a2)));

        // 3.2.2.1
        String serverDigestValue;
        if (qopVariant == QOP_MISSING) {
            StringBuilder tmp2 = new StringBuilder(md5a1.length() + nonce.length() + md5a2.length());
            tmp2.append(md5a1);
            tmp2.append(':');
            tmp2.append(nonce);
            tmp2.append(':');
            tmp2.append(md5a2);
            serverDigestValue = tmp2.toString();
        } else {
            String qopOption = getQopVariantString();
            String cnonce = getCnonce();
            
            StringBuilder tmp2 = new StringBuilder(md5a1.length() + nonce.length()
                + NC.length() + cnonce.length() + qopOption.length() + md5a2.length() + 5);
            tmp2.append(md5a1);
            tmp2.append(':');
            tmp2.append(nonce);
            tmp2.append(':');
            tmp2.append(NC);
            tmp2.append(':');
            tmp2.append(cnonce);
            tmp2.append(':');
            tmp2.append(qopOption);
            tmp2.append(':');
            tmp2.append(md5a2); 
            serverDigestValue = tmp2.toString();
        }

        String serverDigest =
            encode(md5Helper.digest(EncodingUtils.getAsciiBytes(serverDigestValue)));

        return serverDigest;
    }

    /**
     * Creates digest-response header as defined in RFC2617.
     * 
     * @param credentials User credentials
     * @param digest The response tag's value as String.
     * 
     * @return The digest-response as String.
     */
    private Header createDigestHeader(
            final Credentials credentials, 
            final String digest) throws AuthenticationException {
        
        CharArrayBuffer buffer = new CharArrayBuffer(128);
        if (isProxy()) {
            buffer.append(AUTH.PROXY_AUTH_RESP);
        } else {
            buffer.append(AUTH.WWW_AUTH_RESP);
        }
        buffer.append(": Digest ");
        
        String uri = getParameter("uri");
        String realm = getParameter("realm");
        String nonce = getParameter("nonce");
        String opaque = getParameter("opaque");
        String response = digest;
        String algorithm = getParameter("algorithm");

        String uname = credentials.getUserPrincipal().getName();
        
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>(20);
        params.add(new BasicNameValuePair("username", uname));
        params.add(new BasicNameValuePair("realm", realm));
        params.add(new BasicNameValuePair("nonce", nonce));
        params.add(new BasicNameValuePair("uri", uri));
        params.add(new BasicNameValuePair("response", response));
        
        if (qopVariant != QOP_MISSING) {
            params.add(new BasicNameValuePair("qop", getQopVariantString()));
            params.add(new BasicNameValuePair("nc", NC));
            params.add(new BasicNameValuePair("cnonce", getCnonce()));
        }
        if (algorithm != null) {
            params.add(new BasicNameValuePair("algorithm", algorithm));
        }    
        if (opaque != null) {
            params.add(new BasicNameValuePair("opaque", opaque));
        }

        for (int i = 0; i < params.size(); i++) {
            BasicNameValuePair param = params.get(i);
            if (i > 0) {
                buffer.append(", ");
            }
            boolean noQuotes = "nc".equals(param.getName()) ||
                               "qop".equals(param.getName());
            BasicHeaderValueFormatter.DEFAULT
                .formatNameValuePair(buffer, param, !noQuotes);
        }
        return new BufferedHeader(buffer);
    }

    private String getQopVariantString() {
        String qopOption;
        if (qopVariant == QOP_AUTH_INT) {
            qopOption = "auth-int";   
        } else {
            qopOption = "auth";
        }
        return qopOption;            
    }

    /**
     * Encodes the 128 bit (16 bytes) MD5 digest into a 32 characters long 
     * <CODE>String</CODE> according to RFC 2617.
     * 
     * @param binaryData array containing the digest
     * @return encoded MD5, or <CODE>null</CODE> if encoding failed
     */
    private static String encode(byte[] binaryData) {
        if (binaryData.length != 16) {
            return null;
        } 

        char[] buffer = new char[32];
        for (int i = 0; i < 16; i++) {
            int low = (binaryData[i] & 0x0f);
            int high = ((binaryData[i] & 0xf0) >> 4);
            buffer[i * 2] = HEXADECIMAL[high];
            buffer[(i * 2) + 1] = HEXADECIMAL[low];
        }

        return new String(buffer);
    }


    /**
     * Creates a random cnonce value based on the current time.
     * 
     * @return The cnonce value as String.
     * @throws UnsupportedDigestAlgorithmException if MD5 algorithm is not supported.
     */
    public static String createCnonce() {
        String cnonce;

        MessageDigest md5Helper = createMessageDigest("MD5");

        cnonce = Long.toString(System.currentTimeMillis());
        cnonce = encode(md5Helper.digest(EncodingUtils.getAsciiBytes(cnonce)));

        return cnonce;
    }
}
