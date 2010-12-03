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
* @author Alexey V. Varlamov
*/

package org.apache.harmony.security;

import java.io.IOException;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import org.apache.harmony.security.internal.nls.Messages;

/**
 * This is a basic high-level tokenizer of policy files. It takes in a stream,
 * analyzes data read from it and returns a set of structured tokens. <br>
 * This implementation recognizes text files, consisting of clauses with the
 * following syntax:
 * 
 * <pre>
 * 
 *     keystore &quot;some_keystore_url&quot;, &quot;keystore_type&quot;;
 *  
 * </pre>
 * <pre>
 * 
 *     grant [SignedBy &quot;signer_names&quot;] [, CodeBase &quot;URL&quot;]
 *      [, Principal [principal_class_name] &quot;principal_name&quot;]
 *      [, Principal [principal_class_name] &quot;principal_name&quot;] ... {
 *      permission permission_class_name [ &quot;target_name&quot; ] [, &quot;action&quot;] 
 *      [, SignedBy &quot;signer_names&quot;];
 *      permission ...
 *      };
 *  
 * </pre>
 * 
 * For semantical details of this format, see the
 * {@link org.apache.harmony.security.DefaultPolicy default policy description}.
 * <br>
 * Keywords are case-insensitive in contrast to quoted string literals.
 * Comma-separation rule is quite forgiving, most commas may be just omitted.
 * Whitespaces, line- and block comments are ignored. Symbol-level tokenization
 * is delegated to java.io.StreamTokenizer. <br>
 * <br>
 * This implementation is effectively thread-safe, as it has no field references
 * to data being processed (that is, passes all the data as method parameters).
 * 
 * @see org.apache.harmony.security.fortress.DefaultPolicyParser
 */
public class DefaultPolicyScanner {

    /**
     * Specific exception class to signal policy file syntax error.
     * 
     */
    public static class InvalidFormatException extends Exception {

        /**
         * @serial
         */
        private static final long serialVersionUID = 5789786270390222184L;

        /** 
         * Constructor with detailed message parameter. 
         */
        public InvalidFormatException(String arg0) {
            super(arg0);
        }
    }

    /**
     * Configures passed tokenizer accordingly to supported syntax.
     */
    protected StreamTokenizer configure(StreamTokenizer st) {
        st.slashSlashComments(true);
        st.slashStarComments(true);
        st.wordChars('_', '_');
        st.wordChars('$', '$');
        return st;
    }

    /**
     * Performs the main parsing loop. Starts with creating and configuring a
     * StreamTokenizer instance; then tries to recognize <i>keystore </i> or
     * <i>grant </i> keyword. When found, invokes read method corresponding to
     * the clause and collects result to the passed collection.
     * 
     * @param r
     *            policy stream reader
     * @param grantEntries
     *            a collection to accumulate parsed GrantEntries
     * @param keystoreEntries
     *            a collection to accumulate parsed KeystoreEntries
     * @throws IOException
     *             if stream reading failed
     * @throws InvalidFormatException
     *             if unexpected or unknown token encountered
     */
    public void scanStream(Reader r, Collection<GrantEntry> grantEntries,
            List<KeystoreEntry> keystoreEntries) throws IOException,
            InvalidFormatException {
        StreamTokenizer st = configure(new StreamTokenizer(r));
        //main parsing loop
        parsing: while (true) {
            switch (st.nextToken()) {
            case StreamTokenizer.TT_EOF: //we've done the job
                break parsing;

            case StreamTokenizer.TT_WORD:
                if (Util.equalsIgnoreCase("keystore", st.sval)) { //$NON-NLS-1$
                    keystoreEntries.add(readKeystoreEntry(st));
                } else if (Util.equalsIgnoreCase("grant", st.sval)) { //$NON-NLS-1$
                    grantEntries.add(readGrantEntry(st));
                } else {
                    handleUnexpectedToken(st, Messages.getString("security.89")); //$NON-NLS-1$
                }
                break;

            case ';': //just delimiter of entries
                break;

            default:
                handleUnexpectedToken(st);
                break;
            }
        }
    }

    /**
     * Tries to read <i>keystore </i> clause fields. The expected syntax is
     * 
     * <pre>
     * 
     *     &quot;some_keystore_url&quot;[, &quot;keystore_type&quot;];
     *  
     * </pre>
     * 
     * @return successfully parsed KeystoreEntry
     * @throws IOException
     *             if stream reading failed
     * @throws InvalidFormatException
     *             if unexpected or unknown token encountered
     */
    protected KeystoreEntry readKeystoreEntry(StreamTokenizer st)
            throws IOException, InvalidFormatException {
        KeystoreEntry ke = new KeystoreEntry();
        if (st.nextToken() == '"') {
            ke.url = st.sval;
            if ((st.nextToken() == '"')
                    || ((st.ttype == ',') && (st.nextToken() == '"'))) {
                ke.type = st.sval;
            } else { // handle token in the main loop
                st.pushBack();
            }
        } else {
            handleUnexpectedToken(st, Messages.getString("security.8A")); //$NON-NLS-1$
        }
        return ke;
    }

    /**
     * Tries to read <i>grant </i> clause. <br>
     * First, it reads <i>codebase </i>, <i>signedby </i>, <i>principal </i>
     * entries till the '{' (opening curly brace) symbol. Then it calls
     * readPermissionEntries() method to read the permissions of this clause.
     * <br>
     * Principal entries (if any) are read by invoking readPrincipalEntry()
     * method, obtained PrincipalEntries are accumulated. <br>
     * The expected syntax is
     * 
     * <pre>
     * 
     *     [ [codebase &quot;url&quot;] | [signedby &quot;name1,...,nameN&quot;] | 
     *          principal ...] ]* { ... }
     *  
     * </pre>
     * 
     * @return successfully parsed GrantEntry
     * @throws IOException
     *             if stream reading failed
     * @throws InvalidFormatException
     *             if unexpected or unknown token encountered
     */
    protected GrantEntry readGrantEntry(StreamTokenizer st) throws IOException,
            InvalidFormatException {
        GrantEntry ge = new GrantEntry();
        parsing: while (true) {
            switch (st.nextToken()) {

            case StreamTokenizer.TT_WORD:
                if (Util.equalsIgnoreCase("signedby", st.sval)) { //$NON-NLS-1$
                    if (st.nextToken() == '"') {
                        ge.signers = st.sval;
                    } else {
                        handleUnexpectedToken(st, Messages.getString("security.8B")); //$NON-NLS-1$
                    }
                } else if (Util.equalsIgnoreCase("codebase", st.sval)) { //$NON-NLS-1$
                    if (st.nextToken() == '"') {
                        ge.codebase = st.sval;
                    } else {
                        handleUnexpectedToken(st, Messages.getString("security.8C")); //$NON-NLS-1$
                    }
                } else if (Util.equalsIgnoreCase("principal", st.sval)) { //$NON-NLS-1$
                    ge.addPrincipal(readPrincipalEntry(st));
                } else {
                    handleUnexpectedToken(st);
                }
                break;

            case ',': //just delimiter of entries
                break;

            case '{':
                ge.permissions = readPermissionEntries(st);
                break parsing;

            default: // handle token in the main loop
                st.pushBack();
                break parsing;
            }
        }

        return ge;
    }

    /**
     * Tries to read <i>Principal </i> entry fields. The expected syntax is
     * 
     * <pre>
     * 
     *     [ principal_class_name ] &quot;principal_name&quot;
     *  
     * </pre>
     * 
     * Both class and name may be wildcards, wildcard names should not
     * surrounded by quotes.
     * 
     * @return successfully parsed PrincipalEntry
     * @throws IOException
     *             if stream reading failed
     * @throws InvalidFormatException
     *             if unexpected or unknown token encountered
     */
    protected PrincipalEntry readPrincipalEntry(StreamTokenizer st)
            throws IOException, InvalidFormatException {
        PrincipalEntry pe = new PrincipalEntry();
        if (st.nextToken() == StreamTokenizer.TT_WORD) {
            pe.klass = st.sval;
            st.nextToken();
        } else if (st.ttype == '*') {
            pe.klass = PrincipalEntry.WILDCARD;
            st.nextToken();
        }
        if (st.ttype == '"') {
            StringBuilder sb = new StringBuilder();
            String[] elements = st.sval.split("[,]"); //$NON-NLS-1$
            int endIndex = elements.length - 1;
            for (int index = 0; index < endIndex; index++) {
                sb.append(elements[index].trim() + ',');
            }
            if (endIndex > -1) {
                sb.append(elements[endIndex].trim());
            }
            pe.name = sb.toString();
        } else if (st.ttype == '*') {
            pe.name = PrincipalEntry.WILDCARD;
        } else {
            handleUnexpectedToken(st, Messages.getString("security.8D")); //$NON-NLS-1$
        }
        return pe;
    }

    /**
     * Tries to read a list of <i>permission </i> entries. The expected syntax
     * is
     * 
     * <pre>
     * 
     *     permission permission_class_name
     *          [ &quot;target_name&quot; ] [, &quot;action_list&quot;]
     *          [, signedby &quot;name1,name2,...&quot;];
     *  
     * </pre>
     * 
     * List is terminated by '}' (closing curly brace) symbol.
     * 
     * @return collection of successfully parsed PermissionEntries
     * @throws IOException
     *             if stream reading failed
     * @throws InvalidFormatException
     *             if unexpected or unknown token encountered
     */
    protected Collection<PermissionEntry> readPermissionEntries(
            StreamTokenizer st) throws IOException, InvalidFormatException {
        Collection<PermissionEntry> permissions = new HashSet<PermissionEntry>();
        parsing: while (true) {
            switch (st.nextToken()) {

            case StreamTokenizer.TT_WORD:
                if (Util.equalsIgnoreCase("permission", st.sval)) { //$NON-NLS-1$
                    PermissionEntry pe = new PermissionEntry();
                    int tok = st.nextToken();
                    if (tok == StreamTokenizer.TT_WORD || tok == '"') {
                        pe.klass = st.sval;
                        if (st.nextToken() == '"') {
                            pe.name = st.sval;
                            st.nextToken();
                        }
                        if (st.ttype == ',') {
                            st.nextToken();
                        }
                        if (st.ttype == '"') {
                            pe.actions = st.sval;
                            if (st.nextToken() == ',') {
                                st.nextToken();
                            }
                        }
                        if (st.ttype == StreamTokenizer.TT_WORD
                                && Util.equalsIgnoreCase("signedby", st.sval)) { //$NON-NLS-1$
                            if (st.nextToken() == '"') {
                                pe.signers = st.sval;
                            } else {
                                handleUnexpectedToken(st);
                            }
                        } else { // handle token in the next iteration
                            st.pushBack();
                        }
                        permissions.add(pe);
                        continue parsing;
                    }
                }
                handleUnexpectedToken(st, Messages.getString("security.8E")); //$NON-NLS-1$
                break;

            case ';': //just delimiter of entries
                break;

            case '}': //end of list
                break parsing;

            default: // invalid token
                handleUnexpectedToken(st);
                break;
            }
        }

        return permissions;
    }

    /**
     * Formats a detailed description of tokenizer status: current token,
     * current line number, etc.
     */
    protected String composeStatus(StreamTokenizer st) {
        return st.toString();
    }

    /**
     * Throws InvalidFormatException with detailed diagnostics.
     * 
     * @param st
     *            a tokenizer holding the erroneous token
     * @param message
     *            a user-friendly comment, probably explaining expected syntax.
     *            Should not be <code>null</code>- use the overloaded
     *            single-parameter method instead.
     */
    protected final void handleUnexpectedToken(StreamTokenizer st,
            String message) throws InvalidFormatException {
        throw new InvalidFormatException(Messages.getString("security.8F", //$NON-NLS-1$
                composeStatus(st), message));
    }

    /**
     * Throws InvalidFormatException with error status: which token is
     * unexpected on which line.
     * 
     * @param st
     *            a tokenizer holding the erroneous token
     */
    protected final void handleUnexpectedToken(StreamTokenizer st)
            throws InvalidFormatException {
        throw new InvalidFormatException(Messages.getString("security.90", //$NON-NLS-1$
                composeStatus(st)));
    }

    /**
     * Compound token representing <i>keystore </i> clause. See policy format
     * {@link org.apache.harmony.security.DefaultPolicy description}for details.
     * 
     * @see org.apache.harmony.security.fortress.DefaultPolicyParser
     * @see org.apache.harmony.security.DefaultPolicyScanner
     */
    public static class KeystoreEntry {

        /**
         * The URL part of keystore clause.
         */
        public String url;

        /**
         * The typename part of keystore clause.
         */
        public String type;
    }

    /**
     * Compound token representing <i>grant </i> clause. See policy format
     * {@link org.apache.harmony.security.DefaultPolicy description}for details.
     * 
     * @see org.apache.harmony.security.fortress.DefaultPolicyParser
     * @see org.apache.harmony.security.DefaultPolicyScanner
     */
    public static class GrantEntry {

        /**
         * The signers part of grant clause. This is a comma-separated list of
         * certificate aliases.
         */
        public String signers;

        /**
         * The codebase part of grant clause. This is an URL from which code
         * originates.
         */
        public String codebase;

        /**
         * Collection of PrincipalEntries of grant clause.
         */
        public Collection<PrincipalEntry> principals;

        /**
         * Collection of PermissionEntries of grant clause.
         */
        public Collection<PermissionEntry> permissions;

        /**
         * Adds specified element to the <code>principals</code> collection.
         * If collection does not exist yet, creates a new one.
         */
        public void addPrincipal(PrincipalEntry pe) {
            if (principals == null) {
                principals = new HashSet<PrincipalEntry>();
            }
            principals.add(pe);
        }

    }

    /**
     * Compound token representing <i>principal </i> entry of a <i>grant </i>
     * clause. See policy format
     * {@link org.apache.harmony.security.DefaultPolicy description}for details.
     * 
     * @see org.apache.harmony.security.fortress.DefaultPolicyParser
     * @see org.apache.harmony.security.DefaultPolicyScanner
     */
    public static class PrincipalEntry {

        /**
         * Wildcard value denotes any class and/or any name.
         * Must be asterisk, for proper general expansion and 
         * PrivateCredentialsPermission wildcarding
         */
        public static final String WILDCARD = "*"; //$NON-NLS-1$
        
        /**
         * The classname part of principal clause.
         */
        public String klass;

        /**
         * The name part of principal clause.
         */
        public String name;
    }

    /**
     * Compound token representing <i>permission </i> entry of a <i>grant </i>
     * clause. See policy format
     * {@link org.apache.harmony.security.DefaultPolicy description}for details.
     * 
     * @see org.apache.harmony.security.fortress.DefaultPolicyParser
     * @see org.apache.harmony.security.DefaultPolicyScanner
     */
    public static class PermissionEntry {

        /**
         * The classname part of permission clause.
         */
        public String klass;

        /**
         * The name part of permission clause.
         */
        public String name;

        /**
         * The actions part of permission clause.
         */
        public String actions;

        /**
         * The signers part of permission clause. This is a comma-separated list
         * of certificate aliases.
         */
        public String signers;
    }
}
