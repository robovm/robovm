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

/**
* @author Vladimir N. Molotkov, Alexander Y. Kleymenov
*/

package org.apache.harmony.security.x509;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.security.auth.x500.X500Principal;

import org.apache.harmony.security.asn1.ASN1Choice;
import org.apache.harmony.security.asn1.ASN1Implicit;
import org.apache.harmony.security.asn1.ASN1OctetString;
import org.apache.harmony.security.asn1.ASN1Oid;
import org.apache.harmony.security.asn1.ASN1StringType;
import org.apache.harmony.security.asn1.ASN1Type;
import org.apache.harmony.security.asn1.BerInputStream;
import org.apache.harmony.security.asn1.ObjectIdentifier;
import org.apache.harmony.security.internal.nls.Messages;
import org.apache.harmony.security.x501.Name;

/**
 * The class encapsulates the ASN.1 DER encoding/decoding work 
 * with the GeneralName structure which is a part of X.509 certificate
 * (as specified in RFC 3280 -
 *  Internet X.509 Public Key Infrastructure.
 *  Certificate and Certificate Revocation List (CRL) Profile.
 *  http://www.ietf.org/rfc/rfc3280.txt):
 * 
 * <pre>
 * 
 *   GeneralName::= CHOICE {
 *        otherName                       [0]     OtherName,
 *        rfc822Name                      [1]     IA5String,
 *        dNSName                         [2]     IA5String,
 *        x400Address                     [3]     ORAddress,
 *        directoryName                   [4]     Name,
 *        ediPartyName                    [5]     EDIPartyName,
 *        uniformResourceIdentifier       [6]     IA5String,
 *        iPAddress                       [7]     OCTET STRING,
 *        registeredID                    [8]     OBJECT IDENTIFIER
 *   }
 * 
 *   OtherName::= SEQUENCE {
 *        type-id    OBJECT IDENTIFIER,
 *        value      [0] EXPLICIT ANY DEFINED BY type-id 
 *   }
 * 
 *   EDIPartyName::= SEQUENCE {
 *        nameAssigner            [0]     DirectoryString OPTIONAL,
 *        partyName               [1]     DirectoryString 
 *   }
 * 
 *   DirectoryString::= CHOICE {
 *        teletexString             TeletexString   (SIZE (1..MAX)),
 *        printableString           PrintableString (SIZE (1..MAX)),
 *        universalString           UniversalString (SIZE (1..MAX)),
 *        utf8String              UTF8String      (SIZE (1..MAX)),
 *        bmpString               BMPString       (SIZE (1..MAX)) 
 *   }
 *  
 * </pre>
 * 
 * @see org.apache.harmony.security.x509.NameConstraints
 * @see org.apache.harmony.security.x509.GeneralSubtree
 */
public class GeneralName {
    
    /**
     * The values of the tags of fields
     */
    public static final int OTHER_NAME = 0;
    public static final int RFC822_NAME = 1;
    public static final int DNS_NAME = 2;
    public static final int X400_ADDR = 3;
    public static final int DIR_NAME = 4;
    public static final int EDIP_NAME = 5;
    public static final int UR_ID = 6;
    public static final int IP_ADDR = 7;
    public static final int REG_ID = 8;
    
    // ASN1 encoders/decoders for name choices
    private static ASN1Type[] nameASN1 = new ASN1Type[9];
    
    static {
        nameASN1[OTHER_NAME] = OtherName.ASN1;
        nameASN1[RFC822_NAME] = ASN1StringType.IA5STRING;
        nameASN1[DNS_NAME] = ASN1StringType.IA5STRING;
        nameASN1[UR_ID] = ASN1StringType.IA5STRING;
        nameASN1[X400_ADDR] = ORAddress.ASN1;
        nameASN1[DIR_NAME] = Name.ASN1;
        nameASN1[EDIP_NAME] = EDIPartyName.ASN1;
        nameASN1[IP_ADDR] = ASN1OctetString.getInstance();
        nameASN1[REG_ID] = ASN1Oid.getInstance();
    }
    
    // the tag of the name type
    private int tag;
    // the name value (can be String or byte array)
    private Object name;
    // the ASN.1 encoded form of GeneralName
    private byte[] encoding;
    // the ASN.1 encoded form of GeneralName's field 
    private byte[] name_encoding;

    /**
     * Makes the GeneralName object from the tag type and corresponding
     * well established string representation of the name value.
     * The String representation of [7] iPAddress is such as:
     *  For IP v4, as specified in RFC 791, the address must
     *  contain exactly 4 byte component.  For IP v6, as specified in
     *  RFC 1883, the address must contain exactly 16 byte component.
     *  If GeneralName structure is used as a part of Name Constraints
     *  extension, to represent an address range the number of address
     *  component is doubled (to 8 and 32 bytes respectively).
     * Note that the names:
     * [0] otherName, [3] x400Address, [5] ediPartyName
     *   have no the string representation, so exception will be thrown.
     * To make the GeneralName object with such names use another constructor. 
     * @param tag is an integer which value corresponds to the name type. 
     * @param name is a name value corresponding to the tag.
     * <pre>
     */
    public GeneralName(int tag, String name) throws IOException {
        if (name == null) {
            throw new IOException(Messages.getString("security.28")); //$NON-NLS-1$
        }
        this.tag = tag;
        switch (tag) {
            case OTHER_NAME :
            case X400_ADDR :
            case EDIP_NAME :
                throw new IOException( Messages.getString("security.180", tag )); //$NON-NLS-1$ //$NON-NLS-2$
            case DNS_NAME :
                // according to RFC 3280 p.34 the DNS name should be 
                // checked against the
                // RFC 1034 p.10 (3.5. Preferred name syntax):
                checkDNS(name);
                this.name = name;
                break;
            case UR_ID :
                // check the uniformResourceIdentifier for correctness
                // according to RFC 3280 p.34
                checkURI(name);
                this.name = name;
                break;
            case RFC822_NAME :
                this.name = name;
                break;
            case REG_ID:
                this.name = oidStrToInts(name);
                break;
            case DIR_NAME :
                this.name = new Name(name);
                break;
            case IP_ADDR :
                this.name = ipStrToBytes(name);
                break;
            default:
                throw new IOException(Messages.getString("security.181", tag)); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * TODO
     * @param   name:   OtherName
     */
    public GeneralName(OtherName name) {
        this.tag = OTHER_NAME;
        this.name = name;
    }

    /**
     * TODO
     * @param   name:   ORAddress
     */
    public GeneralName(ORAddress name) {
        this.tag = X400_ADDR;
        this.name = name;
    }

    /**
     * TODO
     * @param   name:   Name
     */
    public GeneralName(Name name) {
        this.tag = DIR_NAME;
        this.name = name;
    }

    /**
     * TODO
     * @param   name:   EDIPartyName
     */
    public GeneralName(EDIPartyName name) {
        this.tag = EDIP_NAME;
        this.name = name;
    }
    /**
     * Constructor for type [7] iPAddress. 
     * name is an array of bytes such as:
     *  For IP v4, as specified in RFC 791, the address must
     *  contain exactly 4 byte component.  For IP v6, as specified in
     *  RFC 1883, the address must contain exactly 16 byte component.
     *  If GeneralName structure is used as a part of Name Constraints
     *  extension, to represent an address range the number of address
     *  component is doubled (to 8 and 32 bytes respectively).
     */
    public GeneralName(byte[] name) throws IllegalArgumentException {
        int length = name.length;
        if (length != 4 && length != 8 && length != 16 && length != 32) {
            throw new IllegalArgumentException(
                    Messages.getString("security.182")); //$NON-NLS-1$
        }
        this.tag = IP_ADDR;
        this.name = new byte[name.length];
        System.arraycopy(name, 0, this.name, 0, name.length);
    }

    /**
     * Constructs an object representing the value of GeneralName.
     * @param tag is an integer which value corresponds
     * to the name type (0-8), 
     * @param name is a DER encoded for of the name value
     */
    public GeneralName(int tag, byte[] name) 
                                    throws IOException {
        if (name == null) {
            throw new NullPointerException(Messages.getString("security.28")); //$NON-NLS-1$
        }
        if ((tag < 0) || (tag > 8)) {
            throw new IOException(Messages.getString("security.183", tag)); //$NON-NLS-1$
        }
        this.tag = tag;
        this.name_encoding = new byte[name.length];
        System.arraycopy(name, 0, this.name_encoding, 0, name.length);
        this.name = nameASN1[tag].decode(this.name_encoding);
    }
   
    /**
     * Returns the tag of the name in the structure
     * @return the tag of the name
     */
    public int getTag() {
        return tag;
    }

    /**
     * @return the value of the name. 
     * The class of name object depends on the tag as follows:
     * [0] otherName - OtherName object,
     * [1] rfc822Name - String object,
     * [2] dNSName - String object,
     * [3] x400Address - ORAddress object,
     * [4] directoryName - instance of Name object,
     * [5] ediPartyName - EDIPartyName object,
     * [6] uniformResourceIdentifier - String object,
     * [7] iPAddress - array of bytes such as:
     *  For IP v4, as specified in RFC 791, the address must
     *  contain exactly 4 byte component.  For IP v6, as specified in
     *  RFC 1883, the address must contain exactly 16 byte component.
     *  If GeneralName structure is used as a part of Name Constraints
     *  extension, to represent an address range the number of address
     *  component is doubled (to 8 and 32 bytes respectively).
     * [8] registeredID - String.
     */
    public Object getName() {
        return name;
    }
    
    /**
     * TODO
     * @param   _gname: Object
     * @return
     */
    public boolean equals(Object _gname) {
        if (!(_gname instanceof GeneralName)) {
            return false;
        }
        GeneralName gname = (GeneralName) _gname;
        if (this.tag != gname.tag) {
            return false;
        }
        switch(tag) {
            case RFC822_NAME:
            case DNS_NAME:
            case UR_ID:
                return ((String) name).equalsIgnoreCase(
                        (String) gname.getName());
            case REG_ID:
                return Arrays.equals((int[]) name, (int[]) gname.name);
            case IP_ADDR: 
                // iPAddress [7], check by using ranges.
                return Arrays.equals((byte[]) name, (byte[]) gname.name);
            case DIR_NAME: 
            case X400_ADDR:
            case OTHER_NAME:
            case EDIP_NAME:
                return Arrays.equals(getEncoded(), gname.getEncoded());
            default:
                // should never happen
        }
        //System.out.println(false);
        return false;
    }
    
	public int hashCode() {
		switch(tag) {
	        case RFC822_NAME:
	        case DNS_NAME:
	        case UR_ID:
	        case REG_ID:
	        case IP_ADDR: 
	            return name.hashCode();
	        case DIR_NAME: 
	        case X400_ADDR:
	        case OTHER_NAME:
	        case EDIP_NAME:
	            return getEncoded().hashCode();
	        default:
	            return super.hashCode();
		}
	}
    
    /**
     * Checks if the other general name is acceptable by this object.
     * The name is acceptable if it has the same type name and its
     * name value is equal to name value of this object. Also the name
     * is acceptable if this general name object is a part of name 
     * constraints and the specified name is satisfied the restriction
     * provided by this object (for more detail see section 4.2.1.11
     * of rfc 3280).
     * Note that for X400Address [3] check procedure is unclear so method
     * just checks the equality of encoded forms.
     * For otherName [0], ediPartyName [5], and registeredID [8] 
     * the check procedure if not defined by rfc 3280 and for names of 
     * these types this method also checks only for equality of encoded forms.
     */
    public boolean isAcceptable(GeneralName gname) {
        if (this.tag != gname.getTag()) {
            return false;
        }
        switch (this.tag) {
            case RFC822_NAME:
                // Mail address [1]: 
                // a@b.c - particular address is acceptable by the same address,
                // or by b.c - host name.
                return ((String) gname.getName()).toLowerCase()
                    .endsWith(((String) name).toLowerCase());
            case DNS_NAME:
                // DNS name [2] that can be constructed by simply adding 
                // to the left hand side of the name satisfies the name 
                // constraint: aaa.aa.aa satisfies to aaa.aa.aa, aa.aa, ..
                String dns = (String) name;
                String _dns = (String) gname.getName();
                if (dns.equalsIgnoreCase(_dns)) {
                    return true;
                } else {
                    return _dns.toLowerCase().endsWith("." + dns.toLowerCase()); //$NON-NLS-1$
                }
            case UR_ID:
                // For URIs the constraint ".xyz.com" is satisfied by both 
                // abc.xyz.com and abc.def.xyz.com.  However, the constraint 
                // ".xyz.com" is not satisfied by "xyz.com".  
                // When the constraint does not begin with a period, it
                // specifies a host.
                // Extract the host from URI:
                String uri = (String) name;
                int begin = uri.indexOf("://")+3; //$NON-NLS-1$
                int end = uri.indexOf('/', begin);
                String host = (end == -1) 
                                ? uri.substring(begin)
                                : uri.substring(begin, end);
                uri = (String) gname.getName();
                begin = uri.indexOf("://")+3; //$NON-NLS-1$
                end = uri.indexOf('/', begin);
                String _host = (end == -1) 
                                ? uri.substring(begin)
                                : uri.substring(begin, end);
                if (host.startsWith(".")) { //$NON-NLS-1$
                    return _host.toLowerCase().endsWith(host.toLowerCase());
                } else {
                    return host.equalsIgnoreCase(_host);
                }
            case IP_ADDR: 
                // iPAddress [7], check by using ranges.
                byte[] address = (byte[]) name;
                byte[] _address = (byte[]) gname.getName();
                int length = address.length;
                int _length = _address.length;
                if (length == _length) {
                    return Arrays.equals(address, _address);
                } else if (length == 2*_length) {
                    for (int i=0; i<_address.length; i++) {
                        if ((_address[i] < address[i]) 
                                || (_address[i] > address[i+_length])) {
                            return false;
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            case DIR_NAME: 
                // FIXME: false:
                // directoryName according to 4.1.2.4
                // comparing the encoded forms of the names
                //TODO:
                //Legacy implementations exist where an RFC 822 name 
                //is embedded in the subject distinguished name in an 
                //attribute of type EmailAddress
            case X400_ADDR:
            case OTHER_NAME:
            case EDIP_NAME:
            case REG_ID:
                return Arrays.equals(getEncoded(), gname.getEncoded());
            default:
                // should never happen
        }
        return true;
    }
    
    /**
     * Gets a list representation of this GeneralName object.
     * The first entry of the list is an Integer object representing
     * the type of mane (0-8), and the second entry is a value of the name:
     * string or ASN.1 DER encoded form depending on the type as follows:
     * rfc822Name, dNSName, uniformResourceIdentifier names are returned 
     * as Strings, using the string formats for those types (rfc 3280)
     * IP v4 address names are returned using dotted quad notation. 
     * IP v6 address names are returned in the form "p1:p2:...:p8", 
     * where p1-p8 are hexadecimal values representing the eight 16-bit 
     * pieces of the address. registeredID name are returned as Strings 
     * represented as a series of nonnegative integers separated by periods. 
     * And directory names (distinguished names) are returned in 
     * RFC 2253 string format. 
     * otherName, X400Address, ediPartyName returned as byte arrays 
     * containing the ASN.1 DER encoded form of the name. 
     */
    public List getAsList() {
        ArrayList result = new ArrayList();
        result.add(new Integer(tag));
        switch (tag) {
            case OTHER_NAME:
                result.add(((OtherName) name).getEncoded());
                break;
            case RFC822_NAME:
            case DNS_NAME:
            case UR_ID:
                result.add(name); // String
                break;
            case REG_ID:
                result.add(ObjectIdentifier.toString((int[]) name));
                break;
            case X400_ADDR:
                result.add(((ORAddress) name).getEncoded());
                break;
            case DIR_NAME: // directoryName is returned as a String
                result.add(((Name) name).getName(X500Principal.RFC2253));
                break;
            case EDIP_NAME:
                result.add(((EDIPartyName) name).getEncoded());
                break;
            case IP_ADDR: //iPAddress is returned as a String, not as a byte array
                result.add(ipBytesToStr((byte[]) name));
                break;
            default:
                // should never happen
        }
        return Collections.unmodifiableList(result);
    }

    // 
    // TODO
    // @param   data:   byte[]
    // @return
    // 
    private String getBytesAsString(byte[] data) {
        String result = ""; //$NON-NLS-1$
        for (int i=0; i<data.length; i++) {
            String tail = Integer.toHexString(0x00ff & data[i]);
            if (tail.length() == 1) {
                tail = "0" + tail;  //$NON-NLS-1$
            }
            result += tail + " "; //$NON-NLS-1$
        }
        return result;
    }

    /**
     * TODO
     * @return
     */
    public String toString() {
        String result = ""; //$NON-NLS-1$
        switch (tag) {
            case OTHER_NAME:
                result = "otherName[0]: "  //$NON-NLS-1$
                         + getBytesAsString(getEncoded());
                break;
            case RFC822_NAME:
                result = "rfc822Name[1]: " + name; //$NON-NLS-1$
                break;
            case DNS_NAME:
                result = "dNSName[2]: " + name; //$NON-NLS-1$
                break;
            case UR_ID:
                result = "uniformResourceIdentifier[6]: " + name; //$NON-NLS-1$
                break;
            case REG_ID:
                result = "registeredID[8]: " + ObjectIdentifier.toString((int[]) name); //$NON-NLS-1$
                break;
            case X400_ADDR:
                result = "x400Address[3]: "  //$NON-NLS-1$
                         + getBytesAsString(getEncoded());
                break;
            case DIR_NAME: 
                result = "directoryName[4]: "  //$NON-NLS-1$
                         + ((Name) name).getName(X500Principal.RFC2253);
                break;
            case EDIP_NAME:
                result = "ediPartyName[5]: "  //$NON-NLS-1$
                         + getBytesAsString(getEncoded());
                break;
            case IP_ADDR: 
                result = "iPAddress[7]: " + ipBytesToStr((byte[]) name); //$NON-NLS-1$
                break;
            default:
                // should never happen
        }
        return result;
    }
    
    /**
     * Returns ASN.1 encoded form of this X.509 GeneralName value.
     * @return a byte array containing ASN.1 encode form.
     */
    public byte[] getEncoded() {
        if (encoding == null) {
            encoding = ASN1.encode(this);
        }
        return encoding;
    }

    /**
     * @return the encoded value of the name without the tag associated 
     *         with the name in the GeneralName structure
     * @throws  IOException 
     */
    public byte[] getEncodedName() {
        if (name_encoding == null) {
            name_encoding = nameASN1[tag].encode(name);
        }
        return name_encoding;
    }

    /**
     * Checks the correctness of the string representation of DNS name.
     * The correctness is checked as specified in RFC 1034 p. 10, and modified
     * by RFC 1123 (section 2.1).
     */
    public static void checkDNS(String dns) throws IOException {
        byte[] bytes = dns.toLowerCase().getBytes("UTF-8"); //$NON-NLS-1$
        // indicates if it is a first letter of the label
        boolean first_letter = true;
        for (int i=0; i<bytes.length; i++) {
            byte ch = bytes[i];
            if (first_letter) {
                if ((bytes.length > 2) && (ch == '*') && (bytes[1] == '.')) {
                    first_letter = false;
                    continue;
                }
                if ((ch > 'z' || ch < 'a') && (ch < '0' || ch > '9')) {
                    throw new IOException(Messages.getString("security.184", //$NON-NLS-1$
                            (char)ch, dns));
                }
                first_letter = false;
                continue;
            }
            if (!((ch >= 'a' && ch <= 'z') || (ch >= '0' && ch <= '9')
                    || (ch == '-') || (ch == '.'))) {
                throw new IOException(Messages.getString("security.185", dns)); //$NON-NLS-1$
            }
            if (ch == '.') {
                // check the end of the previous label, it should not
                // be '-' sign
                if (bytes[i-1] == '-') {
                    throw new IOException(
                            Messages.getString("security.186", dns)); //$NON-NLS-1$
                }
                first_letter = true;
            }
        }
    }

    /**
     * Checks the correctness of the string representation of URI name.
     * The correctness is checked as pointed out in RFC 3280 p. 34.
     */
    public static void checkURI(String uri) throws IOException {
        try {
            URI ur = new URI(uri);
            if ((ur.getScheme() == null) 
                    || (ur.getRawSchemeSpecificPart().length() == 0)) {
                throw new IOException(Messages.getString("security.187", uri)); //$NON-NLS-1$
            }
            if (!ur.isAbsolute()) {
                throw new IOException(Messages.getString("security.188", uri)); //$NON-NLS-1$
            }
        } catch (URISyntaxException e) {
            throw (IOException) new IOException(
                    Messages.getString("security.189", uri)).initCause(e);//$NON-NLS-1$
                    
        }
    }

    /**
     * Converts OID into array of bytes.
     */
    public static int[] oidStrToInts(String oid) throws IOException {
        byte[] bytes = oid.getBytes("UTF-8"); //$NON-NLS-1$
        if (bytes[bytes.length-1] == '.') {
            throw new IOException(Messages.getString("security.56", oid)); //$NON-NLS-1$
        }
        int[] result = new int[bytes.length/2+1]; // best case: a.b.c.d.e
        int number = 0; // the number of OID's components
        for (int i=0; i<bytes.length; i++) {
            int value = 0;
            int pos = i;
            while ((i < bytes.length) && (bytes[i] >= '0')
                        && (bytes[i] <= '9')) {
                value = 10 * value + (bytes[i++] - 48);
            }
            if (i == pos) {
                // the number was not read
                throw new IOException(Messages.getString("security.56", oid)); //$NON-NLS-1$
            }
            result[number++] = value;
            if (i >= bytes.length) {
                break;
            }
            if (bytes[i] != '.') {
                throw new IOException(Messages.getString("security.56", oid)); //$NON-NLS-1$
            }
        }
        if (number < 2) {
            throw new IOException(Messages.getString("security.18A", oid));//$NON-NLS-1$
        }
        int[] res = new int[number];
        for (int i=0; i<number; i++) {
            res[i] = result[i];
        }
        return res;
    }

    /**
     * Helper method. Converts the String representation of IP address
     * to the array of bytes. IP addresses are expected in two versions:<br>
     * IPv4 - in dot-decimal notation<br>
     * IPv6 - in colon hexadecimal notation<br>
     * Also method works with the ranges of the addresses represented
     * as 2 addresses separated by '/' character.
     * @param   address :   String representation of IP address
     * @return  byte representation of IP address
     */
    public static byte[] ipStrToBytes(String ip) throws IOException {
        boolean isIPv4 = (ip.indexOf('.') > 0);
        // number of components (should be 4 or 8)
        int num_components = (isIPv4) ? 4 : 16;
        if (ip.indexOf('/') > 0) {
            num_components *= 2; // this is a range of addresses
        }
        // the resulting array
        byte[] result = new byte[num_components];
        byte[] ip_bytes = ip.getBytes("UTF-8"); //$NON-NLS-1$
        // number of address component to be read
        int component = 0;
        // if it is reading the second bound of a range
        boolean reading_second_bound = false;
        if (isIPv4) {
            // IPv4 address is expected in the form of dot-decimal notation:
            //      1.100.2.200
            // or in the range form:
            //      1.100.2.200/1.100.3.300
            int i = 0;
            while (i < ip_bytes.length) {
                int digits = 0;
                // the value of the address component
                int value = 0;
                while ((i < ip_bytes.length) && (ip_bytes[i] >= '0')
                        && (ip_bytes[i] <= '9')) {
                    digits++;
                    if (digits > 3) {
                        throw new IOException(Messages.getString("security.18B", ip)); //$NON-NLS-1$
                    }
                    value = 10 * value + (ip_bytes[i] - 48);
                    i++;
                }
                if (digits == 0) {
                    // ip_bytes[i] is not a number
                    throw new IOException(Messages.getString("security.18C", ip));//$NON-NLS-1$
                }
                result[component] = (byte) value;
                component++;
                if (i >= ip_bytes.length) {
                    // no more bytes
                    break;
                }
                // check the reached delimiter
                if ((ip_bytes[i] != '.' && ip_bytes[i] != '/')) {
                    throw new IOException(Messages.getString("security.18C", ip)); //$NON-NLS-1$
                }
                // check the correctness of the range
                if (ip_bytes[i] == '/') {
                    if (reading_second_bound) {
                        // more than 2 bounds in the range
                        throw new IOException(Messages.getString("security.18C", ip)); //$NON-NLS-1$
                    }
                    if (component != 4) {
                        throw new IOException(Messages.getString("security.18D", ip)); //$NON-NLS-1$
                    }
                    reading_second_bound = true;
                }
                // check the number of the components
                if (component > ((reading_second_bound) ? 7 : 3)) {
                    throw new IOException(Messages.getString("security.18D", ip)); //$NON-NLS-1$
                }
                i++;
            }
            // check the number of read components
            if (component != num_components) {
                throw new IOException(Messages.getString("security.18D", ip)); //$NON-NLS-1$
            }
        } else {
            // IPv6 address is expected in the form of
            // colon hexadecimal notation:
            // 010a:020b:3337:1000:FFFA:ABCD:9999:0000
            // or in a range form:
            // 010a:020b:3337:1000:FFFA:ABCD:9999:0000/010a:020b:3337:1000:FFFA:ABCD:9999:1111
            if (ip_bytes.length != 39 && ip_bytes.length != 79) {
                // incorrect length of the string representation
                throw new IOException(Messages.getString("security.18E", ip)); //$NON-NLS-1$
            }
            int value = 0;
            // indicates the reading of the second half of byte
            boolean second_hex = false;
            // if the delimiter (':' or '/') is expected
            boolean expect_delimiter = false;
            for (int i=0; i<ip_bytes.length; i++) {
                byte bytik = ip_bytes[i];
                if ((bytik >= '0') && (bytik <= '9')) {
                    value = (bytik - 48); // '0':0, '1':1, ... , '9':9
                } else if ((bytik >= 'A') && (bytik <= 'F')) {
                    value = (bytik - 55); // 'A':10, 'B':11, ... , 'F':15
                } else if ((bytik >= 'a') && (bytik <= 'f')) {
                    value = (bytik - 87); // 'a':10, 'b':11, ... , 'f':15
                } else if (second_hex) {
                    // second hex value of a byte is expected but was not read
                    // (it is the situation like: ...ABCD:A:ABCD...)
                    throw new IOException(Messages.getString("security.18E", ip)); //$NON-NLS-1$
                } else if ((bytik == ':') || (bytik == '/')) {
                    if (component % 2 == 1) {
                        // second byte of the component is omitted 
                        // (it is the situation like: ... ABDC:AB:ABCD ...)
                        throw new IOException(Messages.getString("security.18E", ip)); //$NON-NLS-1$
                    }
                    if (bytik == '/') {
                        if (reading_second_bound) {
                            // more than 2 bounds in the range
                            throw new IOException(
                                    Messages.getString("security.18E", ip)); //$NON-NLS-1$
                        }
                        if (component != 16) {
                            // check the number of read components
                            throw new IOException(Messages.getString("security.18F", ip)); //$NON-NLS-1$
                        }
                        reading_second_bound = true;
                    }
                    expect_delimiter = false;
                    continue;
                } else {
                    throw new IOException(Messages.getString("security.18E", ip)); //$NON-NLS-1$
                }
                if (expect_delimiter) { // delimiter is expected but was not read
                    throw new IOException(Messages.getString("security.18E", ip)); //$NON-NLS-1$
                }
                if (!second_hex) {
                    // first half of byte has been read
                    result[component] = (byte) (value << 4);
                    second_hex = true;
                } else {
                    // second half of byte has been read
                    result[component] = (byte)
                        ((result[component] & 0xFF) | value);
                    // delimiter is expected if 2 bytes were read
                    expect_delimiter = (component % 2 == 1);
                    second_hex = false;
                    component++;
                }
            }
            // check the correctness of the read address:
            if (second_hex || (component % 2 == 1)) {
                throw new IOException(Messages.getString("security.18E", ip)); //$NON-NLS-1$
            }
        }
        return result;
    }

    
    /**
     * Helper method. Converts the byte array representation of ip address
     * to the String.
     * @param   ip :   byte array representation of ip address
     *  If the length of byte array 4 then it represents an IP v4 
     *  and the output String will be in the dotted quad form. 
     *  If the length is 16 then it represents an IP v6 
     *  and the output String will be returned in format "p1:p2:...:p8", 
     *  where p1-p8 are hexadecimal values representing the eight 16-bit 
     *  pieces of the address.
     *  If the length is 8 or 32 then it represents an address range (RFC 1519)
     *  and the output String will contain 2 IP address divided by "/"
     * @return  String representation of ip address
     */
    public static String ipBytesToStr(byte[] ip) {
        String result = ""; //$NON-NLS-1$
        if (ip.length < 9) { // IP v4
            for (int i=0; i<ip.length; i++) {
                result += Integer.toString(ip[i] & 0xff);
                if (i != ip.length-1) {
                    result += (i == 3) ? "/": "."; //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
        } else {
            for (int i=0; i<ip.length; i++) {
                result += Integer.toHexString(0x00ff & ip[i]);
                if ((i % 2 != 0) && (i != ip.length-1)) {
                    result += (i == 15) ? "/": ":"; //$NON-NLS-1$ //$NON-NLS-2$
                }
            }
        }
        return result;
    }
 
    public static final ASN1Choice ASN1 = new ASN1Choice(new ASN1Type[] {
           new ASN1Implicit(0, OtherName.ASN1), 
           new ASN1Implicit(1, ASN1StringType.IA5STRING), 
           new ASN1Implicit(2, ASN1StringType.IA5STRING),
           new ASN1Implicit(3, ORAddress.ASN1),
           new ASN1Implicit(4, Name.ASN1),
           new ASN1Implicit(5, EDIPartyName.ASN1),
           new ASN1Implicit(6, ASN1StringType.IA5STRING),
           new ASN1Implicit(7, ASN1OctetString.getInstance()),
           new ASN1Implicit(8, ASN1Oid.getInstance()) }) {

        public Object getObjectToEncode(Object value) {
            return ((GeneralName) value).name;
        }
        
        public int getIndex(java.lang.Object object) {
            return  ((GeneralName) object).tag;
        }

        public Object getDecodedObject(BerInputStream in) throws IOException {
            GeneralName result;
            switch (in.choiceIndex) {
                case OTHER_NAME: // OtherName
                    result = new GeneralName((OtherName) in.content);
                    break;
                case RFC822_NAME: // rfc822Name
                case DNS_NAME: // dNSName
                    result = new GeneralName(in.choiceIndex, (String) in.content);
                    break;
                case X400_ADDR:
                    result = new GeneralName((ORAddress) in.content);
                    break;
                case DIR_NAME: // directoryName (X.500 Name)
                    result = new GeneralName((Name) in.content);
                    break;
                case EDIP_NAME: // ediPartyName
                    result = new GeneralName((EDIPartyName) in.content);
                    break;
                case UR_ID: // uniformResourceIdentifier
                    String uri = (String) in.content;
                    if (uri.indexOf(":") == -1) { //$NON-NLS-1$
                        throw new IOException(
                            Messages.getString("security.190", uri)); //$NON-NLS-1$
                    }
                    result = new GeneralName(in.choiceIndex, uri);
                    break;
                case IP_ADDR: // iPAddress
                    result = new GeneralName((byte[]) in.content);
                    break;
                case REG_ID: // registeredID
                    result = new GeneralName(in.choiceIndex, 
                            ObjectIdentifier.toString((int[]) in.content));
                    break;
                default:
                    throw new IOException(Messages.getString("security.191", in.choiceIndex)); //$NON-NLS-1$
            }
            result.encoding = in.getEncoded();
            return result;
        }
    };
    
    // public static void printAsHex(int perLine,
    //         String prefix,
    //         String delimiter,
    //         byte[] data) {
    //     for (int i=0; i<data.length; i++) {
    //         String tail = Integer.toHexString(0x000000ff & data[i]);
    //         if (tail.length() == 1) {
    //             tail = "0" + tail; 
    //         }
    //         System.out.print(prefix + "0x" + tail + delimiter);
 
    //         if (((i+1)%perLine) == 0) {
    //             System.out.println();
    //         }
    //     }
    //     System.out.println();
    // }

    // public static void main(String[] args) {
    //     System.out.println(">> "+new BigInteger(new byte[] {(byte)23, (byte)255}).toString(2));
    //     System.out.println(ipBytesToStr(new byte[] {(byte)255, (byte)23, (byte)128, (byte)130}));
    //     System.out.println(ipBytesToStr(new byte[] {(byte)255, (byte)23, (byte)128, (byte)130,
    //                                                 (byte)255, (byte)23, (byte)128, (byte)130}));
    //     System.out.println(ipBytesToStr(new byte[] {(byte)255, (byte)23, (byte)128, (byte)130,
    //                                                 (byte)255, (byte)23, (byte)128, (byte)130,
    //                                                 (byte)255, (byte)23, (byte)128, (byte)130,
    //                                                 (byte)255, (byte)23, (byte)128, (byte)130}));
    //     System.out.println(ipBytesToStr(new byte[] {(byte)255, (byte)23, (byte)128, (byte)130,
    //                                                 (byte)255, (byte)23, (byte)128, (byte)130,
    //                                                 (byte)255, (byte)23, (byte)128, (byte)130,
    //                                                 (byte)255, (byte)23, (byte)128, (byte)130,
    //                                                 (byte)255, (byte)23, (byte)128, (byte)130,
    //                                                 (byte)255, (byte)23, (byte)128, (byte)130,
    //                                                 (byte)255, (byte)23, (byte)128, (byte)130,
    //                                                 (byte)255, (byte)23, (byte)128, (byte)130}));
    //     ipStrToBytes("1.2.3.4");
    //     ipStrToBytes("1.2.3.4/4.3.2.1");
    //     printAsHex(8, "", " ", ipStrToBytes("ff17:8082:ff17:8082:ff17:8082:ff17:8082/ff17:8082:ff17:8082:ff17:8082:ff17:8082"));
    // }
}
