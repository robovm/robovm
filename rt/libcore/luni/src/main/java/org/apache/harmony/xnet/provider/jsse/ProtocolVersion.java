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
package org.apache.harmony.xnet.provider.jsse;

import java.util.Hashtable;

/**
 *
 * Represents Protocol Version
 */
public class ProtocolVersion {
    /**
     * Protocols supported by this provider implementation
     */
    public static final String[] supportedProtocols = new String[] { "TLSv1",
            "SSLv3" };

    private static Hashtable<String, ProtocolVersion> protocolsByName = new Hashtable<String, ProtocolVersion>(4);

    /**
     *
     * Returns true if protocol version is supported
     *
     * @param version
     */
    public static boolean isSupported(byte[] version) {
        if (version[0] != 3 || (version[1] != 0 && version[1] != 1)) {
            return false;
        }
        return true;
    }

    /**
     * Returns ProtocolVersion
     *
     * @param version
     * @return
     */
    public static ProtocolVersion getByVersion(byte[] version) {
        if (version[0] == 3) {
            if (version[1] == 1) {
                return TLSv1;
            }
            if (version[1] == 0) {
                return SSLv3;
            }
        }
        return null;
    }

    /**
     * Returns true if provider supports protocol version
     *
     * @param name
     * @return
     */
    public static boolean isSupported(String name) {
        return protocolsByName.containsKey(name);
    }

    /**
     * Returns ProtocolVersion
     *
     * @param name
     * @return
     */
    public static ProtocolVersion getByName(String name) {
        return protocolsByName.get(name);
    }

    /**
     * Highest protocol version supported by provider implementation
     *
     * @param protocols
     * @return
     */
    public static ProtocolVersion getLatestVersion(String[] protocols) {
        if (protocols == null || protocols.length == 0) {
            return null;
        }
        ProtocolVersion latest = getByName(protocols[0]);
        ProtocolVersion current;
        for (int i = 1; i < protocols.length; i++) {
            current = getByName(protocols[i]);
            if (current == null) {
                continue;
            }
            if ((latest == null)
                    || (latest.version[0] < current.version[0])
                    || (latest.version[0] == current.version[0] && latest.version[1] < current.version[1])) {
                latest = current;
            }
        }
        return latest;

    }

    /**
     * SSL 3.0 protocol version
     */
    public static final ProtocolVersion SSLv3 = new ProtocolVersion("SSLv3",
            new byte[] { 3, 0 });

    /**
     * TLS 1.0 protocol version
     */
    public static final ProtocolVersion TLSv1 = new ProtocolVersion("TLSv1",
            new byte[] { 3, 1 });

    static {
        protocolsByName.put(SSLv3.name, SSLv3);
        protocolsByName.put(TLSv1.name, TLSv1);
        protocolsByName.put("SSL", SSLv3);
        protocolsByName.put("TLS", TLSv1);
    }

    /**
     * Protocol name
     */
    public final String name;

    /**
     * Protocol version as byte array
     */
    public final byte[] version;

    private ProtocolVersion(String name, byte[] version) {
        this.name = name;
        this.version = version;
    }
}