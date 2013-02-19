package org.bouncycastle.asn1;

import java.io.IOException;

public class DERBoolean
    extends ASN1Object
{
    // BEGIN android-changed
    private final byte  value;
    // END android-changed

    public static final DERBoolean FALSE = new DERBoolean(false);
    public static final DERBoolean TRUE  = new DERBoolean(true);

    /**
     * return a boolean from the passed in object.
     *
     * @exception IllegalArgumentException if the object cannot be converted.
     */
    public static DERBoolean getInstance(
        Object  obj)
    {
        if (obj == null || obj instanceof DERBoolean)
        {
            return (DERBoolean)obj;
        }

        throw new IllegalArgumentException("illegal object in getInstance: " + obj.getClass().getName());
    }

    /**
     * return a DERBoolean from the passed in boolean.
     */
    public static DERBoolean getInstance(
        boolean  value)
    {
        return (value ? TRUE : FALSE);
    }

    // BEGIN android-added
    /**
     * return a DERBoolean from the passed in array.
     */
    public static DERBoolean getInstance(
        byte[] octets)
    {
        return (octets[0] != 0) ? TRUE : FALSE;
    }
    // END android-added

    /**
     * return a Boolean from a tagged object.
     *
     * @param obj the tagged object holding the object we want
     * @param explicit true if the object is meant to be explicitly
     *              tagged false otherwise.
     * @exception IllegalArgumentException if the tagged object cannot
     *               be converted.
     */
    public static DERBoolean getInstance(
        ASN1TaggedObject obj,
        boolean          explicit)
    {
        DERObject o = obj.getObject();

        if (explicit || o instanceof DERBoolean)
        {
            return getInstance(o);
        }
        else
        {
            // BEGIN android-changed
            return getInstance(((ASN1OctetString)o).getOctets());
            // END android-changed
        }
    }
    
    // BEGIN android-removed
    // public DERBoolean(
    //     byte[]       value)
    // {
    //     if (value.length != 1)
    //     {
    //         throw new IllegalArgumentException("byte value should have 1 byte in it");
    //     }
    //
    //     this.value = value[0];
    // }
    // END android-removed

    // BEGIN android-changed
    protected DERBoolean(
        boolean     value)
    // END android-changed
    {
        this.value = (value) ? (byte)0xff : (byte)0;
    }

    public boolean isTrue()
    {
        return (value != 0);
    }

    void encode(
        DEROutputStream out)
        throws IOException
    {
        byte[]  bytes = new byte[1];

        bytes[0] = value;

        out.writeEncoded(BOOLEAN, bytes);
    }
    
    protected boolean asn1Equals(
        DERObject  o)
    {
        if ((o == null) || !(o instanceof DERBoolean))
        {
            return false;
        }

        return (value == ((DERBoolean)o).value);
    }
    
    public int hashCode()
    {
        return value;
    }


    public String toString()
    {
      return (value != 0) ? "TRUE" : "FALSE";
    }
}
