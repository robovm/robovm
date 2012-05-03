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
   Corresponds to PKIStatus structure.
   See RFC 3161 -
   Internet X.509 Public Key Infrastructure
   Time-Stamp Protocol (TSP)
   http://www.ietf.org/rfc/rfc3161.txt)

   PKIStatus ::= INTEGER {
      granted                (0),
      -- when the PKIStatus contains the value zero a TimeStampToken, as
         requested, is present.
      grantedWithMods        (1),
       -- when the PKIStatus contains the value one a TimeStampToken,
         with modifications, is present.
      rejection              (2),
      waiting                (3),
      revocationWarning      (4),
       -- this message contains a warning that a revocation is
       -- imminent
      revocationNotification (5)
       -- notification that a revocation has occurred  }
 */
public enum PKIStatus {
    /**
     * TimeStampToken is present as requested
     */
    GRANTED(0),
    /**
     * TimeStampToken is present with modifications
     */
    GRANTED_WITH_MODS(1),
    /**
     * rejected
     */
    REJECTION(2),
    /**
     * waiting
     */
    WAITING(3),
    /**
     * revocation time comes soon
     */
    REVOCATION_WARNING(4),
    /**
     * revoked
     */
    REVOCATION_NOTIFICATION(5);

    private final int status;
    PKIStatus(int status) {
        this.status = status;
    }

    /**
     * @return int value of the status
     */
    public int getStatus(){
        return status;
    }

    public static PKIStatus getInstance(int status) {
        for (PKIStatus curStatus : values()) {
            if (status == curStatus.status) {
                return curStatus;
            }
        }
        throw new InvalidParameterException("Unknown PKIStatus value");
    }
}

