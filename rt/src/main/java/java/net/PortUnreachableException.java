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

package java.net;

/**
 * This {@code PortUnreachableException} will be thrown if an {@code
 * ICMP_Port_Unreachable} message has been received.
 */
public class PortUnreachableException extends SocketException {

    private static final long serialVersionUID = 8462541992376507323L;

    /**
     * Constructs a new instance of this class with its walkback filled in.
     */
    public PortUnreachableException() {
    }

    /**
     * Constructs a new instance of this class with its walkback and message
     * filled in.
     * 
     * @param detailMessage
     *            the detail message for this exception.
     */
    public PortUnreachableException(String detailMessage) {
        super(detailMessage);
    }
}
