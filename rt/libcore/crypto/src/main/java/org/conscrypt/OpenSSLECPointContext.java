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

import java.math.BigInteger;
import java.security.spec.ECPoint;

final class OpenSSLECPointContext {
    private final OpenSSLECGroupContext group;
    private final long pointCtx;

    OpenSSLECPointContext(OpenSSLECGroupContext group, long pointCtx) {
        this.group = group;
        this.pointCtx = pointCtx;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (pointCtx != 0) {
                NativeCrypto.EC_POINT_clear_free(pointCtx);
            }
        } finally {
            super.finalize();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OpenSSLECPointContext)) {
            return false;
        }

        final OpenSSLECPointContext other = (OpenSSLECPointContext) o;
        if (!NativeCrypto.EC_GROUP_cmp(group.getContext(), other.group.getContext())) {
            return false;
        }

        return NativeCrypto.EC_POINT_cmp(group.getContext(), pointCtx, other.pointCtx);
    }

    public ECPoint getECPoint() {
        final byte[][] generatorCoords = NativeCrypto.EC_POINT_get_affine_coordinates(
                group.getContext(), pointCtx);
        final BigInteger x = new BigInteger(generatorCoords[0]);
        final BigInteger y = new BigInteger(generatorCoords[1]);
        return new ECPoint(x, y);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    public long getContext() {
        return pointCtx;
    }

    public static OpenSSLECPointContext getInstance(int curveType, OpenSSLECGroupContext group,
            ECPoint javaPoint) {
        OpenSSLECPointContext point = new OpenSSLECPointContext(group,
                NativeCrypto.EC_POINT_new(group.getContext()));
        NativeCrypto.EC_POINT_set_affine_coordinates(group.getContext(),
                point.getContext(), javaPoint.getAffineX().toByteArray(),
                javaPoint.getAffineY().toByteArray());
        return point;
    }
}
