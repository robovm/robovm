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

package org.apache.harmony.luni.util;

public final class URLUtil {

    /**
     * Canonicalize the path, i.e. remove ".." and "." occurences.
     *
     * @param path the path to be canonicalized
     * @return the canonicalized path
     */
    public static String canonicalizePath(String path) {
        int dirIndex;

        while ((dirIndex = path.indexOf("/./")) >= 0) { //$NON-NLS-1$
            path = path.substring(0, dirIndex + 1)
                    + path.substring(dirIndex + 3);
        }

        if (path.endsWith("/.")) { //$NON-NLS-1$
            path = path.substring(0, path.length() - 1);
        }

        while ((dirIndex = path.indexOf("/../")) >= 0) { //$NON-NLS-1$
            if (dirIndex != 0) {
                path = path.substring(0, path
                        .lastIndexOf('/', dirIndex - 1))
                        + path.substring(dirIndex + 3);
            } else {
                path = path.substring(dirIndex + 3);
            }
        }

        if (path.endsWith("/..") && path.length() > 3) { //$NON-NLS-1$
            path = path.substring(0, path.lastIndexOf('/',
                    path.length() - 4) + 1);
        }
        return path;
    }
}
