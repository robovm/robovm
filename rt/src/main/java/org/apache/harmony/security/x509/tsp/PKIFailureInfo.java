/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.    
 */

package org.apache.harmony.security.x509.tsp;

import java.security.InvalidParameterException;

/**
   Corresponds to PKIFailureInfo structure.
   See RFC 3161 -
   Internet X.509 Public Key Infrastructure
   Time-Stamp Protocol (TSP)
   http://www.ietf.org/rfc/rfc3161.txt)
    
   PKIFailureInfo ::= BIT STRING {
   badAlg               (0),
     -- unrecognized or unsupported Algorithm Identifier
   badRequest           (2),
     -- transaction not permitted or supported
   badDataFormat        (5),
     -- the data submitted has the wrong format
   timeNotAvailable    (14),
     -- the TSA's time source is not available
   unacceptedPolicy    (15),
     -- the requested TSA policy is not supported by the TSA
   unacceptedExtension (16),
     -- the requested extension is not supported by the TSA
    addInfoNotAvailable (17)
      -- the additional information requested could not be understood
      -- or is not available
    systemFailure       (25)
      -- the request cannot be handled due to system failure  }

    The value of PKIFailureInfo can take only one of the values,
    so it is represented by an integer here.
 */
public enum PKIFailureInfo {
    /**
     *  Unrecognized algorithm ID 
     */
    BAD_ALG(0),
    
    /**
     *  Transaction is not supported 
     */
    BAD_REQUEST(2),
    
    /**
     *  Data format is wrong 
     */
    BAD_DATA_FORMAT(5),
    
    /**
     *  TSA cannot use the time source  
     */
    TIME_NOT_AVAILABLE(14),
    
    /**
     *  The policy is not supported
     */
    UNACCEPTED_POLICY(15),
    
    /**
     *  The extension is not supported
     */
    UNACCEPTED_EXTENSION(16),
    
    /**
     *  The requested additional info is not available
     */
    ADD_INFO_NOT_AVAILABLE(17),
    
    /**
     *  System failure has occured
     */
    SYSTEM_FAILURE(25);

    
    private final int value;

    private static int maxValue;

    PKIFailureInfo(int value) {
        this.value = value;
    }
    
    /**
     * @return int value of the failure
     */
    public int getValue() {
        return value;
    }

    /**
     * @return maximum of values in the enum
     */
    public static int getMaxValue() {
        if (maxValue == 0) {
            for (PKIFailureInfo cur : values())
                if (cur.value > maxValue) {
                    maxValue = cur.value;
                }
        }
        return maxValue;
    }
    
    /**
     * @param value
     * @return
     */
    public static PKIFailureInfo getInstance(int value) {
        for (PKIFailureInfo info : values()){
            if (value == info.value) {
                return info;
            }
        }
        throw new InvalidParameterException("Unknown PKIFailureInfo value");
    }
}

