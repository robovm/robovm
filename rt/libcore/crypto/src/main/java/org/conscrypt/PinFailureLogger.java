/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.conscrypt;

import java.security.cert.CertificateEncodingException;
import java.security.cert.X509Certificate;
import java.util.List;
import libcore.io.Base64;
import libcore.io.DropBox;

public class PinFailureLogger {

    private static final long LOG_INTERVAL_NANOS = 1000 * 1000 * 1000 * 60 * 60;

    private static long lastLoggedNanos = 0;

    public static synchronized void log(String cn, boolean chainContainsUserCert,
                                        boolean pinIsEnforcing,
                                        List<X509Certificate> chain) {
        // if we've logged recently, don't do it again
        if (!timeToLog()) {
            return;
        }
        // otherwise, log the event
        writeToLog(cn, chainContainsUserCert, pinIsEnforcing, chain);
        // update the last logged time
        lastLoggedNanos = System.nanoTime();
    }

    protected static synchronized void writeToLog(String cn, boolean chainContainsUserCert,
                                                  boolean pinIsEnforcing,
                                                  List<X509Certificate> chain) {
        StringBuilder sb = new StringBuilder();
        sb.append(cn);
        sb.append("|");
        sb.append(chainContainsUserCert);
        sb.append("|");
        sb.append(pinIsEnforcing);
        sb.append("|");
        for (X509Certificate cert : chain) {
            try {
                sb.append(Base64.encode(cert.getEncoded()));
            } catch (CertificateEncodingException e) {
                sb.append("Error: could not encode certificate");
            }
            sb.append("|");
        }
        DropBox.addText("exp_det_cert_pin_failure", sb.toString());
    }

    protected static boolean timeToLog() {
        long currentTimeNanos = System.nanoTime();
        return ((currentTimeNanos - lastLoggedNanos) > LOG_INTERVAL_NANOS);
    }
}

