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

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;

/**
 *
 * Base class for handshake messages
 */
public abstract class Message {

    /*
     * Message length
     */
    protected int length;

    /**
     * Returns message type
     * @return
     */
    abstract int getType();

    /**
     * Returns message length
     * @return
     */
    public int length() {
        return length;
    }

    /**
     * Sends message
     * @param out
     */
    abstract void send(HandshakeIODataStream out);

    /**
     * Sends fatal alert
     * @param description
     * @param reason
     */
    protected void fatalAlert(byte description, String reason) {
        throw new AlertException(description, new SSLHandshakeException(reason));
    }

    /**
     * Sends fatal alert
     * @param description
     * @param reason
     * @param cause
     */
    protected void fatalAlert(byte description, String reason, Throwable cause) {
        throw new AlertException(description, new SSLException(reason, cause));
    }
}
