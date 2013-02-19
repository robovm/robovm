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

/**
 * This exception is used to signal that a fatal alert has occurred while working through the
 * protocol.
 */
public class AlertException extends RuntimeException {

    private static final long serialVersionUID = -4448327177165687581L;
    // SSLException to be thrown to application side
    private final SSLException reason;
    // alert description code
    private final byte description;

    /**
     * Constructs the instance.
     *
     * @param description The alert description code from {@link AlertProtocol}
     * @param reason The SSLException to be thrown to application side after alert processing
     *            (sending the record with alert, shutdown work, etc).
     * @see AlertProtocol
     */
    protected AlertException(byte description, SSLException reason) {
        super(reason);
        this.reason = reason;
        this.description = description;
    }

    /**
     * Returns the reason of alert. This reason should be rethrown after alert processing.
     *
     * @return the reason of alert.
     */
    protected SSLException getReason() {
        return reason;
    }

    /**
     * Returns alert's description code.
     *
     * @return alert description code from {@link AlertProtocol}
     * @see AlertProtocol for more information about possible reason codes.
     */
    protected byte getDescriptionCode() {
        return description;
    }
}
