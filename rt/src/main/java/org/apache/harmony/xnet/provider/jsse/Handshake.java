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

/**
 *
 * This class incapsulates the constants determining the types of handshake
 * messages as defined in TLS 1.0 spec., 7.4. Handshake protocol.
 * (http://www.ietf.org/rfc/rfc2246.txt)
 *
 */
public class Handshake {

    /**
     *
     * hello_request handshake type
     */
    public static final byte HELLO_REQUEST = 0;

    /**
     *
     * client_hello handshake type
     */
    public static final byte CLIENT_HELLO = 1;

    /**
     *
     * server_hello handshake type
     */
    public static final byte SERVER_HELLO = 2;

    /**
     *
     * certificate handshake type
     */
    public static final byte CERTIFICATE = 11;

    /**
     *
     * server_key_exchange  handshake type
     */
    public static final byte SERVER_KEY_EXCHANGE = 12;

    /**
     *
     * certificate_request handshake type
     */
    public static final byte CERTIFICATE_REQUEST = 13;

    /**
     *
     * server_hello_done handshake type
     */
    public static final byte SERVER_HELLO_DONE = 14;

    /**
     *
     * certificate_verify handshake type
     */
    public static final byte CERTIFICATE_VERIFY = 15;

    /**
     *
     * client_key_exchange handshake type
     */
    public static final byte CLIENT_KEY_EXCHANGE = 16;

    /**
     *
     * finished handshake type
     */
    public static final byte FINISHED = 20;

}