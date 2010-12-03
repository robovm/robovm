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

package org.apache.harmony.luni.internal.net.www;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Properties;

/**
 * Instances of this class map file extensions to MIME content types based on a
 * default MIME table.
 * 
 * The default values can be overridden by modifying the contents of the file
 * "content-types.properties".
 */
public class MimeTable implements FileNameMap {

    public static final String UNKNOWN = "content/unknown"; //$NON-NLS-1$

    /**
     * A hash table containing the mapping between extensions and mime types.
     */
    public static final Properties types = new Properties();

    // Default mapping.
    static {
        types.setProperty("text", "text/plain"); //$NON-NLS-1$ //$NON-NLS-2$
        types.setProperty("txt", "text/plain"); //$NON-NLS-1$ //$NON-NLS-2$
        types.setProperty("htm", "text/html"); //$NON-NLS-1$ //$NON-NLS-2$
        types.setProperty("html", "text/html"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    /**
     * Constructs a MIME table using the default values defined in this class.
     * 
     * It then augments this table by reading pairs of extensions and
     * corresponding content types from the file "content-types.properties",
     * which is represented in standard java.util.Properties.load(...) format.
     */
    public MimeTable() {
        InputStream str = AccessController
                .doPrivileged(new PrivilegedAction<InputStream>() {
                    public InputStream run() {
                        return getContentTypes();
                    }
                });

        if (str != null) {
            try {
                try {
                    types.load(str);
                } finally {
                    str.close();
                }
            } catch (IOException ex) {
                // Ignore
            }
        }
    }

    /**
     * Answer an InputStream over an external properties file containing the
     * MIME types.
     * 
     * Looks in the location specified in the user property, and then in the
     * expected location.
     * 
     * @return the InputStream, or null if none.
     */
    private InputStream getContentTypes() {
        // User override?
        String userTable = System.getProperty("content.types.user.table"); //$NON-NLS-1$
        if (userTable != null) {
            try {
                return new FileInputStream(userTable);
            } catch (IOException e) {
                // Ignore invalid values
            }
        }

        // Standard location?
        String javahome = System.getProperty("java.home"); //$NON-NLS-1$
        File contentFile = new File(javahome, "lib" //$NON-NLS-1$
                + File.separator + "content-types.properties"); //$NON-NLS-1$
        try {
            return new FileInputStream(contentFile);
        } catch (IOException e) {
            // Not found or can't read
        }

        return null;
    }

    /**
     * Determines the MIME type for the given filename.
     * 
     * @param filename
     *            The file whose extension will be mapped.
     * 
     * @return The mime type associated with the file's extension or null if no
     *         mapping is known.
     */
    public String getContentTypeFor(String filename) {
        if (filename.endsWith("/")) { //$NON-NLS-1$
            // a directory, return html
            return (String) types.get("html"); //$NON-NLS-1$
        }
        int lastCharInExtension = filename.lastIndexOf('#');
        if (lastCharInExtension < 0) {
            lastCharInExtension = filename.length();
        }
        int firstCharInExtension = filename.lastIndexOf('.') + 1;
        String ext = ""; //$NON-NLS-1$
        if (firstCharInExtension > filename.lastIndexOf('/')) {
            ext = filename.substring(firstCharInExtension, lastCharInExtension);
        }
        return types.getProperty(ext.toLowerCase());
    }
}
