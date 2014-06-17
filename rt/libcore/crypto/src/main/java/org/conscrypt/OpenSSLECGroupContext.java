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
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidParameterException;
import java.security.spec.ECField;
import java.security.spec.ECFieldF2m;
import java.security.spec.ECFieldFp;
import java.security.spec.ECParameterSpec;
import java.security.spec.ECPoint;
import java.security.spec.EllipticCurve;

public final class OpenSSLECGroupContext {
    private final long groupCtx;

    public OpenSSLECGroupContext(long groupCtx) {
        this.groupCtx = groupCtx;
    }

    public static OpenSSLECGroupContext getCurveByName(String curveName) {
        // Workaround for OpenSSL not supporting SECG names for NIST P-192 and P-256
        // (aka ANSI X9.62 prime192v1 and prime256v1) curve names.
        if ("secp256r1".equals(curveName)) {
            curveName = "prime256v1";
        } else if ("secp192r1".equals(curveName)) {
            curveName = "prime192v1";
        }

        final long ctx = NativeCrypto.EC_GROUP_new_by_curve_name(curveName);
        if (ctx == 0) {
            return null;
        }

        NativeCrypto.EC_GROUP_set_point_conversion_form(ctx,
                NativeCrypto.POINT_CONVERSION_UNCOMPRESSED);
        NativeCrypto.EC_GROUP_set_asn1_flag(ctx, NativeCrypto.OPENSSL_EC_NAMED_CURVE);

        return new OpenSSLECGroupContext(ctx);
    }

    public static OpenSSLECGroupContext getInstance(int type, BigInteger p, BigInteger a,
            BigInteger b, BigInteger x, BigInteger y, BigInteger n, BigInteger h) {
        final long ctx = NativeCrypto.EC_GROUP_new_curve(type, p.toByteArray(), a.toByteArray(),
                b.toByteArray());
        if (ctx == 0) {
            return null;
        }

        NativeCrypto.EC_GROUP_set_point_conversion_form(ctx,
                NativeCrypto.POINT_CONVERSION_UNCOMPRESSED);

        OpenSSLECGroupContext group = new OpenSSLECGroupContext(ctx);

        OpenSSLECPointContext generator = new OpenSSLECPointContext(group,
                NativeCrypto.EC_POINT_new(ctx));

        NativeCrypto.EC_POINT_set_affine_coordinates(ctx, generator.getContext(),
                x.toByteArray(), y.toByteArray());

        NativeCrypto.EC_GROUP_set_generator(ctx, generator.getContext(), n.toByteArray(),
                h.toByteArray());

        return group;
    }

    @Override
    protected void finalize() throws Throwable {
        try {
            if (groupCtx != 0) {
                NativeCrypto.EC_GROUP_clear_free(groupCtx);
            }
        } finally {
            super.finalize();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof OpenSSLECGroupContext)) {
            return false;
        }

        final OpenSSLECGroupContext other = (OpenSSLECGroupContext) o;
        return NativeCrypto.EC_GROUP_cmp(groupCtx, other.groupCtx);
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        return super.hashCode();
    }

    public long getContext() {
        return groupCtx;
    }

    public static OpenSSLECGroupContext getInstance(ECParameterSpec params)
            throws InvalidAlgorithmParameterException {
        final String curveName = params.getCurveName();
        if (curveName != null) {
            return OpenSSLECGroupContext.getCurveByName(curveName);
        }

        final EllipticCurve curve = params.getCurve();
        final ECField field = curve.getField();

        final int type;
        final BigInteger p;
        if (field instanceof ECFieldFp) {
            type = NativeCrypto.EC_CURVE_GFP;
            p = ((ECFieldFp) field).getP();
        } else if (field instanceof ECFieldF2m) {
            type = NativeCrypto.EC_CURVE_GF2M;
            p = ((ECFieldF2m) field).getReductionPolynomial();
        } else {
            throw new InvalidParameterException("unhandled field class "
                    + field.getClass().getName());
        }

        final ECPoint generator = params.getGenerator();
        return OpenSSLECGroupContext.getInstance(type, p, curve.getA(), curve.getB(),
                generator.getAffineX(), generator.getAffineY(), params.getOrder(),
                BigInteger.valueOf(params.getCofactor()));
    }

    public ECParameterSpec getECParameterSpec() {
        final String curveName = NativeCrypto.EC_GROUP_get_curve_name(groupCtx);

        final byte[][] curveParams = NativeCrypto.EC_GROUP_get_curve(groupCtx);
        final BigInteger p = new BigInteger(curveParams[0]);
        final BigInteger a = new BigInteger(curveParams[1]);
        final BigInteger b = new BigInteger(curveParams[2]);

        final ECField field;
        final int type = NativeCrypto.get_EC_GROUP_type(groupCtx);
        if (type == NativeCrypto.EC_CURVE_GFP) {
            field = new ECFieldFp(p);
        } else if (type == NativeCrypto.EC_CURVE_GF2M) {
            field = new ECFieldF2m(p.bitLength() - 1, p);
        } else {
            throw new RuntimeException("unknown curve type " + type);
        }

        final EllipticCurve curve = new EllipticCurve(field, a, b);

        final OpenSSLECPointContext generatorCtx = new OpenSSLECPointContext(this,
                NativeCrypto.EC_GROUP_get_generator(groupCtx));
        final ECPoint generator = generatorCtx.getECPoint();

        final BigInteger order = new BigInteger(NativeCrypto.EC_GROUP_get_order(groupCtx));
        final BigInteger cofactor = new BigInteger(NativeCrypto.EC_GROUP_get_cofactor(groupCtx));

        return new ECParameterSpec(curve, generator, order, cofactor.intValue(), curveName);
    }
}
