package org.bouncycastle.asn1;

import java.io.IOException;

/**
 * A NULL object.
 */
public class DERNull
    extends ASN1Null
{
    public static final DERNull INSTANCE = new DERNull();

    // BEGIN android-changed
    private static final byte[]  zeroBytes = new byte[0];
    // END android-changed

    // BEGIN android-changed
    protected DERNull()
    // END android-changed
    {
    }

    void encode(
        DEROutputStream  out)
        throws IOException
    {
        out.writeEncoded(NULL, zeroBytes);
    }
}
