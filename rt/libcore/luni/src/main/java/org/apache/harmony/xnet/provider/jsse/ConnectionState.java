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

package org.apache.harmony.xnet.provider.jsse;

import javax.crypto.Cipher;

/**
 * This abstract class is a base for Record Protocol operating environmet
 * of different SSL protocol versions.
 */
public abstract class ConnectionState {

    /**
     * The cipher used for encode operations
     */
    protected Cipher encCipher;

    /**
     * The cipher used for decode operations
     */
    protected Cipher decCipher;

    /**
     * The block size, or zero if not a block cipher
     */
    protected int block_size;

    /**
     * The size of MAC used under this connection state
     */
    protected int hash_size;

    /**
     * Write sequence number which is incremented after each
     * encrypt call
     */
    protected final byte[] write_seq_num = {0, 0, 0, 0, 0, 0, 0, 0};

    /**
     * Read sequence number which is incremented after each
     * decrypt call
     */
    protected final byte[] read_seq_num = {0, 0, 0, 0, 0, 0, 0, 0};

    protected Logger.Stream logger = Logger.getStream("conn_state");

    /**
     * Returns the minimal possible size of the
     * Generic[Stream|Generic]Cipher structure under this
     * connection state.
     */
    protected int getMinFragmentSize() {
        // block ciphers return value with padding included
        return encCipher.getOutputSize(1+hash_size); // 1 byte for data
    }

    /**
     * Returns the size of the Generic[Stream|Generic]Cipher structure
     * corresponding to the content data of specified size.
     */
    protected int getFragmentSize(int content_size) {
        return encCipher.getOutputSize(content_size+hash_size);
    }

    /**
     * Returns the minimal upper bound of the content size enclosed
     * into the Generic[Stream|Generic]Cipher structure of specified size.
     * For stream ciphers the returned value will be exact value.
     */
    protected int getContentSize(int generic_cipher_size) {
        //it does not take the padding of block ciphered structures
        //into account (so returned value can be greater than actual)
        return decCipher.getOutputSize(generic_cipher_size)-hash_size;
    }

    /**
     * Returns the number of bytes of padding required to round the
     * content up to the required block size. Assumes power of two
     * block size.
     */
    protected int getPaddingSize(int content_size) {
        int mask = block_size - 1;
        return (block_size - (content_size & mask));
    }

    /**
     * Creates the GenericStreamCipher or GenericBlockCipher
     * data structure for specified data of specified type.
     * @param type - the ContentType of the provided data
     * @param fragment - the byte array containing the
     * data to be encrypted under the current connection state.
     */
    protected byte[] encrypt(byte type, byte[] fragment) {
        return encrypt(type, fragment, 0, fragment.length);
    }

    /**
     * Creates the GenericStreamCipher or GenericBlockCipher
     * data structure for specified data of specified type.
     * @param type - the ContentType of the provided data
     * @param fragment - the byte array containing the
     * data to be encrypted under the current connection state.
     * @param offset - the offset from which the data begins with.
     * @param len - the length of the data.
     */
    protected abstract byte[] encrypt
        (byte type, byte[] fragment, int offset, int len);

    /**
     * Retrieves the fragment of the Plaintext structure of
     * the specified type from the provided data.
     * @param type - the ContentType of the data to be decrypted.
     * @param fragment - the byte array containing the
     * data to be encrypted under the current connection state.
     */
    protected byte[] decrypt(byte type, byte[] fragment) {
        return decrypt(type, fragment, 0, fragment.length);
    }

    /**
     * Retrieves the fragment of the Plaintext structure of
     * the specified type from the provided data.
     * @param type - the ContentType of the data to be decrypted.
     * @param fragment - the byte array containing the
     * data to be encrypted under the current connection state.
     * @param offset - the offset from which the data begins with.
     * @param len - the length of the data.
     */
    protected abstract byte[] decrypt
        (byte type, byte[] fragment, int offset, int len);

    /**
     * Increments the sequence number.
     */
    protected static void incSequenceNumber(byte[] seq_num) {
        int octet = 7;
        while (octet >= 0) {
            seq_num[octet] ++;
            if (seq_num[octet] == 0) {
                // characteristic overflow, so
                // carrying a number in adding
                octet --;
            } else {
                return;
            }
        }
    }

    /**
     * Shutdownes the protocol. It will be impossiblke to use the instance
     * after the calling of this method.
     */
    protected void shutdown() {
        encCipher = null;
        decCipher = null;
        for (int i=0; i<write_seq_num.length; i++) {
            write_seq_num[i] = 0;
            read_seq_num[i] = 0;
        }
    }
}

