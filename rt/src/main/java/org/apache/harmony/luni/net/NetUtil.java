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

package org.apache.harmony.luni.net;

import java.net.Proxy;
import java.net.ProxySelector;
import java.net.URI;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.List;

public class NetUtil {

    /**
     * Answers whether to use a SOCKS proxy.
     * 
     * @param proxy java.net.Proxy <code>proxy</code> is used to determine
     *        whether using SOCKS proxy.
     * @return true if only the type of <code>proxy</code> is
     *         Proxy.Type.SOCKS.
     */
    public static boolean usingSocks(Proxy proxy) {
        if (null != proxy && Proxy.Type.SOCKS == proxy.type()) {
            return true;
        }
        return false;
    }

    /**
     * Answer whether to prefer IPV6 address
     * 
     * @return boolean
     */
    public static boolean preferIPv6Addresses() {
        final Action a = new Action("java.net.preferIPv6Addresses");//$NON-NLS-1$
        return AccessController.doPrivileged(a).booleanValue();
    }

    /**
     * Answer whether to prefer IPV4 stack
     * 
     * @return boolean
     */
    public static boolean preferIPv4Stack() {
        final Action a = new Action("java.net.preferIPv4Stack");//$NON-NLS-1$
        return AccessController.doPrivileged(a).booleanValue();
    }

    /**
     * Gets proxy list according to the URI by system ProxySelector.
     * 
     * @param uri
     * @return a list of proxy for the URI. Returns null if no proxy is
     *         available.
     */
    public static List<Proxy> getProxyList(URI uri) {
        // use system default selector to get proxy list
        ProxySelector selector = ProxySelector.getDefault();
        if (null == selector) {
            return null;
        }
        return selector.select(uri);
    }

    private static final class Action implements PrivilegedAction<Boolean> {
        private final String propertyName;

        Action(String propertyName) {
            super();
            this.propertyName = propertyName;
        }

        public Boolean run() {
            if (Boolean.getBoolean(propertyName)) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
    }

    static void intToBytes(int value, byte bytes[], int start) {
        /*
         * Shift the int so the current byte is right-most Use a byte mask of
         * 255 to single out the last byte.
         */
        bytes[start] = (byte) ((value >> 24) & 255);
        bytes[start + 1] = (byte) ((value >> 16) & 255);
        bytes[start + 2] = (byte) ((value >> 8) & 255);
        bytes[start + 3] = (byte) (value & 255);
    }

    static int bytesToInt(byte bytes[], int start) {
        /*
         * First mask the byte with 255, as when a negative signed byte converts
         * to an integer, it has bits on in the first 3 bytes, we are only
         * concerned about the right-most 8 bits. Then shift the rightmost byte
         * to align with its position in the integer.
         */
        int value = ((bytes[start + 3] & 255)) | ((bytes[start + 2] & 255) << 8)
                | ((bytes[start + 1] & 255) << 16) | ((bytes[start] & 255) << 24);
        return value;
    }
}
