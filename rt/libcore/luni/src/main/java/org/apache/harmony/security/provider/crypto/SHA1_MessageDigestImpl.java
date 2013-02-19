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


package org.apache.harmony.security.provider.crypto;


import java.security.DigestException;
import java.security.MessageDigestSpi;
import java.util.Arrays;


/**
 * This class extends the MessageDigestSpi class implementing all its abstract methods;
 * it overrides the "Object clone()" and "int engineGetDigestLength()" methods. <BR>
 * The class implements the Cloneable interface.
 */


public class SHA1_MessageDigestImpl extends MessageDigestSpi implements Cloneable, SHA1_Data {
    private int[] buffer;        // buffer has the following structure:
                                 // -  0-16 - frame for accumulating a message
                                 // - 17-79 - for SHA1Impl methods
                                 // - 80    - unused
                                 // - 81    - to store length of the message
                                 // - 82-86 - frame for current message digest

    private byte[] oneByte;      // one byte buffer needed to use in engineUpdate(byte)
                                 // having buffer as private field is just optimization

    private long messageLength;   // total length of bytes supplied by user


    /**
     *  The constructor creates needed buffers and sets the engine at initial state
     */
    public SHA1_MessageDigestImpl() {

        // BYTES_OFFSET +6 is minimal length required by methods in SHA1Impl
        buffer  = new int[BYTES_OFFSET +6];

        oneByte = new byte[1];

        engineReset();
    }


    /**
     * The method performs final actions and invokes the "computeHash(int[])" method.
     * In case if there is no enough words in current frame
     * after processing its data, extra frame is prepared and
     * the "computeHash(int[])" method is invoked second time. <BR>
     *
     * After processing, the method resets engine's state
     *
     * @param
     *       digest - byte array
     * @param
     *       offset - offset in digest
     */
    private void processDigest(byte[] digest, int offset) {

        int i, j;         // implementation variables
        int lastWord;     //

        long nBits = messageLength <<3 ;  // length has to be calculated before padding

        engineUpdate( (byte) 0x80 );      // beginning byte in padding

        i = 0;                     // i contains number of beginning word for following loop

        lastWord = (buffer[BYTES_OFFSET] + 3)>>2 ;  // computing of # of full words by shifting
                                                    // # of bytes

        // possible cases:
        //
        // - buffer[BYTES_OFFSET] == 0 - buffer frame is empty,
        //                         padding byte was 64th in previous frame
        //                         current frame should contain only message's length
        //
        // - lastWord < 14 - two last, these are 14 & 15, words in 16 word frame are free;
        //                   no extra frame needed
        // - lastWord = 14 - only one last, namely 15-th, word in frame doesn't contain bytes;
        //                   extra frame is needed
        // - lastWord > 14 - last word in frame is not full;
        //                   extra frame is needed

        if ( buffer[BYTES_OFFSET] != 0 ) {

            if ( lastWord < 15 ) {
                i = lastWord;
            } else {
                if ( lastWord == 15 ) {
                    buffer[15] = 0;       // last word in frame is set to "0"
                }
                SHA1Impl.computeHash(buffer);
                i = 0;
            }
        }
        Arrays.fill(buffer, i, 14, 0);

        buffer[14] = (int)( nBits >>>32 );
        buffer[15] = (int)( nBits & 0xFFFFFFFF );
        SHA1Impl.computeHash(buffer);

        // converting 5-word frame into 20 bytes
        j = offset;
        for ( i = HASH_OFFSET; i < HASH_OFFSET +5; i++ ) {
            int k = buffer[i];
            digest[j  ] = (byte) ( k >>>24 );   // getting first  byte from left
            digest[j+1] = (byte) ( k >>>16 );   // getting second byte from left
            digest[j+2] = (byte) ( k >>> 8 );   // getting third  byte from left
            digest[j+3] = (byte) ( k       );   // getting fourth byte from left
            j += 4;
        }

        engineReset();
    }

    //  methods specified in java.security.MessageDigestSpi

    /**
     * Returns a "deep" copy of this SHA1MDImpl object. <BR>
     *
     * The method overrides "clone()" in class Object. <BR>
     *
     * @return
     *       a clone of this object
     */
    public Object clone() throws CloneNotSupportedException {
        SHA1_MessageDigestImpl cloneObj = (SHA1_MessageDigestImpl) super.clone();
        cloneObj.buffer = buffer.clone();
        cloneObj.oneByte = oneByte.clone();
        return cloneObj;
    }


    /**
     * Computes a message digest value. <BR>
     *
     * The method resets the engine. <BR>
     *
     * The method overrides "engineDigest()" in class MessageDigestSpi. <BR>
     *
     * @return
     *       byte array containing message digest value
     */
    protected byte[] engineDigest() {
        byte[] hash = new byte[DIGEST_LENGTH];
        processDigest(hash, 0);
        return hash;
    }


    /**
     * Computes message digest value.
     * Upon return, the value is stored in "buf" buffer beginning "offset" byte. <BR>
     *
     * The method resets the engine. <BR>
     *
     * The method overrides "engineDigest(byte[],int,int) in class MessageDigestSpi.
     *
     * @param
     *       buf    byte array to store a message digest returned
     * @param
     *       offset a position in the array for first byte of the message digest
     * @param
     *       len    number of bytes within buffer allotted for the message digest;
     *                as this implementation doesn't provide partial digests,
     *                len should be >= 20, DigestException is thrown otherwise
     * @return
     *       the length of the message digest stored in the "buf" buffer;
     *       in this implementation the length=20
     *
     * @throws IllegalArgumentException
     *               if null is passed to the "buf" argument <BR>
     *               if offset + len > buf.length  <BR>
     *               if offset > buf.length or len > buf.length
     *
     * @throws DigestException
     *               if len < 20
     *
     * @throws  ArrayIndexOutOfBoundsException
     *               if offset < 0
     */
    protected int engineDigest(byte[] buf, int offset, int len) throws DigestException {
        if (buf == null) {
            throw new IllegalArgumentException("buf == null");
        }
        if (offset > buf.length || len > buf.length || (len + offset) > buf.length) {
            throw new IllegalArgumentException();
        }
        if (len < DIGEST_LENGTH) {
            throw new DigestException("len < DIGEST_LENGTH");
        }
        if (offset < 0) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }

        processDigest(buf, offset);

        return DIGEST_LENGTH;
    }


    /**
     * Returns a message digest length. <BR>
     *
     * The method overrides "engineGetDigestLength()" in class MessageDigestSpi. <BR>
     *
     * @return
     *        total length of current message digest as an int value
     */
    protected int engineGetDigestLength() {
        return DIGEST_LENGTH;
    }


    /**
     * Resets the engine. <BR>
     *
     * The method overrides "engineReset()" in class MessageDigestSpi. <BR>
     */
    protected void engineReset() {

        messageLength = 0;

        buffer[BYTES_OFFSET] = 0;
        buffer[HASH_OFFSET   ] = H0;
        buffer[HASH_OFFSET +1] = H1;
        buffer[HASH_OFFSET +2] = H2;
        buffer[HASH_OFFSET +3] = H3;
        buffer[HASH_OFFSET +4] = H4;
    }


    /**
     * Supplements a byte to current message. <BR>
     *
     * The method overrides "engineUpdate(byte)" in class MessageDigestSpi. <BR>
     *
     * @param
     *       input byte to add to current message
     */
    protected void engineUpdate(byte input) {

        oneByte[0] = input;
        SHA1Impl.updateHash( buffer, oneByte, 0, 0 );
        messageLength++;
    }


    /**
     * Updates current message. <BR>
     *
     * The method overrides "engineUpdate(byte[],int,int)" in class MessageDigestSpi. <BR>
     *
     * The method silently returns if "len" <= 0.
     *
     * @param
     *       input  a byte array
     * @param
     *       offset a number of first byte in the "input" array to use for updating
     * @param
     *       len    a number of bytes to use
     *
     * @throws NullPointerException
     *                if null is passed to the "buf" argument
     *
     * @throws IllegalArgumentException
     *                if offset > buf.length or len > buf.length or
     *                (len + offset) > buf.length
     * @throws ArrayIndexOutOfBoundsException
     *                offset < 0
     */
    protected void engineUpdate(byte[] input, int offset, int len) {
        if (input == null) {
            throw new IllegalArgumentException("input == null");
        }
        if (len <= 0) {
            return;
        }
        if (offset < 0) {
            throw new ArrayIndexOutOfBoundsException(offset);
        }
        if (offset > input.length || len > input.length || (len + offset) > input.length) {
            throw new IllegalArgumentException();
        }

        SHA1Impl.updateHash(buffer, input, offset, offset + len -1 );
        messageLength += len;
    }

}
