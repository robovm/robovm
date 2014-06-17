/*
 * Copyright (C) 2013 The Android Open Source Project
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

import java.io.ByteArrayOutputStream;
import java.math.BigInteger;
import java.security.cert.CRLException;
import java.security.cert.X509CRLEntry;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

public class OpenSSLX509CRLEntry extends X509CRLEntry {
    private final long mContext;

    OpenSSLX509CRLEntry(long ctx) {
        mContext = ctx;
    }

    @Override
    public Set<String> getCriticalExtensionOIDs() {
        String[] critOids =
                NativeCrypto.get_X509_REVOKED_ext_oids(mContext,
                        NativeCrypto.EXTENSION_TYPE_CRITICAL);

        /*
         * This API has a special case that if there are no extensions, we
         * should return null. So if we have no critical extensions, we'll check
         * non-critical extensions.
         */
        if ((critOids.length == 0)
                && (NativeCrypto.get_X509_REVOKED_ext_oids(mContext,
                        NativeCrypto.EXTENSION_TYPE_NON_CRITICAL).length == 0)) {
            return null;
        }

        return new HashSet<String>(Arrays.asList(critOids));
    }

    @Override
    public byte[] getExtensionValue(String oid) {
        return NativeCrypto.X509_REVOKED_get_ext_oid(mContext, oid);
    }

    @Override
    public Set<String> getNonCriticalExtensionOIDs() {
        String[] critOids =
                NativeCrypto.get_X509_REVOKED_ext_oids(mContext,
                        NativeCrypto.EXTENSION_TYPE_NON_CRITICAL);

        /*
         * This API has a special case that if there are no extensions, we
         * should return null. So if we have no non-critical extensions, we'll
         * check critical extensions.
         */
        if ((critOids.length == 0)
                && (NativeCrypto.get_X509_REVOKED_ext_oids(mContext,
                        NativeCrypto.EXTENSION_TYPE_CRITICAL).length == 0)) {
            return null;
        }

        return new HashSet<String>(Arrays.asList(critOids));
    }

    @Override
    public boolean hasUnsupportedCriticalExtension() {
        final String[] criticalOids =
                NativeCrypto.get_X509_REVOKED_ext_oids(mContext,
                        NativeCrypto.EXTENSION_TYPE_CRITICAL);
        for (String oid : criticalOids) {
            final long extensionRef = NativeCrypto.X509_REVOKED_get_ext(mContext, oid);
            if (NativeCrypto.X509_supported_extension(extensionRef) != 1) {
                return true;
            }
        }

        return false;
    }

    @Override
    public byte[] getEncoded() throws CRLException {
        return NativeCrypto.i2d_X509_REVOKED(mContext);
    }

    @Override
    public BigInteger getSerialNumber() {
        return new BigInteger(NativeCrypto.X509_REVOKED_get_serialNumber(mContext));
    }

    @Override
    public Date getRevocationDate() {
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        calendar.set(Calendar.MILLISECOND, 0);
        NativeCrypto.ASN1_TIME_to_Calendar(NativeCrypto.get_X509_REVOKED_revocationDate(mContext),
                calendar);
        return calendar.getTime();
    }

    @Override
    public boolean hasExtensions() {
        return (NativeCrypto.get_X509_REVOKED_ext_oids(mContext,
                NativeCrypto.EXTENSION_TYPE_NON_CRITICAL).length != 0)
                || (NativeCrypto.get_X509_REVOKED_ext_oids(mContext,
                        NativeCrypto.EXTENSION_TYPE_CRITICAL).length != 0);
    }

    @Override
    public String toString() {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        long bioCtx = NativeCrypto.create_BIO_OutputStream(os);
        try {
            NativeCrypto.X509_REVOKED_print(bioCtx, mContext);
            return os.toString();
        } finally {
            NativeCrypto.BIO_free(bioCtx);
        }
    }
}
