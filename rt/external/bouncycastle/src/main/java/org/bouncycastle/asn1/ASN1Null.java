package org.bouncycastle.asn1;

import java.io.IOException;

/**
 * A NULL object.
 */
public abstract class ASN1Null
    extends ASN1Object
{
    // BEGIN android-changed
    /*package*/ ASN1Null()
    {
    }
    // END android-changed

    public int hashCode()
    {
        return -1;
    }

    boolean asn1Equals(
        DERObject o)
    {
        if (!(o instanceof ASN1Null))
        {
            return false;
        }
        
        return true;
    }

    abstract void encode(DEROutputStream out)
        throws IOException;

    public String toString()
    {
         return "NULL";
    }
}
