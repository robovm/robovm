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

import java.security.GeneralSecurityException;
import java.util.Arrays;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.NullCipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.net.ssl.SSLProtocolException;

/**
 * This class encapsulates the operating environment of the TLS v1
 * (http://www.ietf.org/rfc/rfc2246.txt) Record Protocol and provides
 * relating encryption/decryption functionality.
 * The work functionality is based on the security
 * parameters negotiated during the handshake.
 */
public class ConnectionStateTLS extends ConnectionState {

    // Pre-calculated prf label values:
    // "key expansion".getBytes()
    private static byte[] KEY_EXPANSION_LABEL = {
        (byte) 0x6B, (byte) 0x65, (byte) 0x79, (byte) 0x20, (byte) 0x65,
        (byte) 0x78, (byte) 0x70, (byte) 0x61, (byte) 0x6E, (byte) 0x73,
        (byte) 0x69, (byte) 0x6F, (byte) 0x6E };

    // "client write key".getBytes()
    private static byte[] CLIENT_WRITE_KEY_LABEL = {
        (byte) 0x63, (byte) 0x6C, (byte) 0x69, (byte) 0x65, (byte) 0x6E,
        (byte) 0x74, (byte) 0x20, (byte) 0x77, (byte) 0x72, (byte) 0x69,
        (byte) 0x74, (byte) 0x65, (byte) 0x20, (byte) 0x6B, (byte) 0x65,
        (byte) 0x79 };

    // "server write key".getBytes()
    private static byte[] SERVER_WRITE_KEY_LABEL = {
        (byte) 0x73, (byte) 0x65, (byte) 0x72, (byte) 0x76, (byte) 0x65,
        (byte) 0x72, (byte) 0x20, (byte) 0x77, (byte) 0x72, (byte) 0x69,
        (byte) 0x74, (byte) 0x65, (byte) 0x20, (byte) 0x6B, (byte) 0x65,
        (byte) 0x79 };

    // "IV block".getBytes()
    private static byte[] IV_BLOCK_LABEL = {
        (byte) 0x49, (byte) 0x56, (byte) 0x20, (byte) 0x62, (byte) 0x6C,
        (byte) 0x6F, (byte) 0x63, (byte) 0x6B };

    // MACs to create and check the message integrity info
    private final Mac encMac;
    private final Mac decMac;

    // Once created permanently used array:
    // is used to create the header of the MAC material value:
    // 5 == 1(TLSCompressed.type) + 2(TLSCompressed.version) +
    //      2(TLSCompressed.length)
    private final byte[] mac_material_header = new byte[] {0, 3, 1, 0, 0};

    /**
     * Creates the instance of TLS v1 Connection State. All of the
     * security parameters are provided by session object.
     * @param   session: the sessin object which incapsulates
     * all of the security parameters established by handshake protocol.
     * The key calculation for the state is done according
     * to the TLS v 1.0 Protocol specification.
     * (http://www.ietf.org/rfc/rfc2246.txt)
     */
    protected ConnectionStateTLS(SSLSessionImpl session) {
        try {
            CipherSuite cipherSuite = session.cipherSuite;

            hash_size = cipherSuite.getMACLength();
            boolean is_exportabe =  cipherSuite.isExportable();
            int key_size = (is_exportabe)
                ? cipherSuite.keyMaterial
                : cipherSuite.expandedKeyMaterial;
            int iv_size = cipherSuite.ivSize;
            block_size = cipherSuite.getBlockSize();

            String algName = cipherSuite.getBulkEncryptionAlgorithm();
            String macName = cipherSuite.getHmacName();
            if (logger != null) {
                logger.println("ConnectionStateTLS.create:");
                logger.println("  cipher suite name: "
                                            + cipherSuite.getName());
                logger.println("  encryption alg name: " + algName);
                logger.println("  mac alg name: " + macName);
                logger.println("  hash size: " + hash_size);
                logger.println("  block size: " + block_size);
                logger.println("  IV size:" + iv_size);
                logger.println("  key size: " + key_size);
            }

            byte[] clientRandom = session.clientRandom;
            byte[] serverRandom = session.serverRandom;
            // so we need PRF value of size of
            // 2*hash_size + 2*key_size + 2*iv_size
            byte[] key_block = new byte[2*hash_size + 2*key_size + 2*iv_size];
            byte[] seed = new byte[clientRandom.length + serverRandom.length];
            System.arraycopy(serverRandom, 0, seed, 0, serverRandom.length);
            System.arraycopy(clientRandom, 0, seed, serverRandom.length,
                    clientRandom.length);

            PRF.computePRF(key_block, session.master_secret,
                    KEY_EXPANSION_LABEL, seed);

            byte[] client_mac_secret = new byte[hash_size];
            byte[] server_mac_secret = new byte[hash_size];
            byte[] client_key = new byte[key_size];
            byte[] server_key = new byte[key_size];

            boolean is_client = !session.isServer;

            System.arraycopy(key_block, 0, client_mac_secret, 0, hash_size);
            System.arraycopy(key_block, hash_size,
                    server_mac_secret, 0, hash_size);
            System.arraycopy(key_block, 2*hash_size, client_key, 0, key_size);
            System.arraycopy(key_block, 2*hash_size+key_size,
                    server_key, 0, key_size);

            IvParameterSpec clientIV = null;
            IvParameterSpec serverIV = null;

            if (is_exportabe) {
                System.arraycopy(clientRandom, 0,
                        seed, 0, clientRandom.length);
                System.arraycopy(serverRandom, 0,
                        seed, clientRandom.length, serverRandom.length);
                byte[] final_client_key =
                    new byte[cipherSuite.expandedKeyMaterial];
                byte[] final_server_key =
                    new byte[cipherSuite.expandedKeyMaterial];
                PRF.computePRF(final_client_key, client_key,
                        CLIENT_WRITE_KEY_LABEL, seed);
                PRF.computePRF(final_server_key, server_key,
                        SERVER_WRITE_KEY_LABEL, seed);
                client_key = final_client_key;
                server_key = final_server_key;
                if (block_size != 0) {
                    byte[] iv_block = new byte[2*iv_size];
                    PRF.computePRF(iv_block, null, IV_BLOCK_LABEL, seed);
                    clientIV = new IvParameterSpec(iv_block, 0, iv_size);
                    serverIV = new IvParameterSpec(iv_block, iv_size, iv_size);
                }
            } else if (block_size != 0) {
                clientIV = new IvParameterSpec(key_block,
                        2*(hash_size+key_size), iv_size);
                serverIV = new IvParameterSpec(key_block,
                        2*(hash_size+key_size)+iv_size, iv_size);
            }

            if (logger != null) {
                logger.println("is exportable: "+is_exportabe);
                logger.println("master_secret");
                logger.print(session.master_secret);
                logger.println("client_random");
                logger.print(clientRandom);
                logger.println("server_random");
                logger.print(serverRandom);
                //logger.println("key_block");
                //logger.print(key_block);
                logger.println("client_mac_secret");
                logger.print(client_mac_secret);
                logger.println("server_mac_secret");
                logger.print(server_mac_secret);
                logger.println("client_key");
                logger.print(client_key);
                logger.println("server_key");
                logger.print(server_key);
                if (clientIV == null) {
                    logger.println("no IV.");
                } else {
                    logger.println("client_iv");
                    logger.print(clientIV.getIV());
                    logger.println("server_iv");
                    logger.print(serverIV.getIV());
                }
            }

            if (algName == null) {
                encCipher = new NullCipher();
                decCipher = new NullCipher();
            } else {
                encCipher = Cipher.getInstance(algName);
                decCipher = Cipher.getInstance(algName);
                if (is_client) { // client side
                    encCipher.init(Cipher.ENCRYPT_MODE,
                                   new SecretKeySpec(client_key, algName), clientIV);
                    decCipher.init(Cipher.DECRYPT_MODE,
                                   new SecretKeySpec(server_key, algName), serverIV);
                } else { // server side
                    encCipher.init(Cipher.ENCRYPT_MODE,
                                   new SecretKeySpec(server_key, algName), serverIV);
                    decCipher.init(Cipher.DECRYPT_MODE,
                                   new SecretKeySpec(client_key, algName), clientIV);
                }
            }

            encMac = Mac.getInstance(macName);
            decMac = Mac.getInstance(macName);
            if (is_client) { // client side
                encMac.init(new SecretKeySpec(client_mac_secret, macName));
                decMac.init(new SecretKeySpec(server_mac_secret, macName));
            } else { // server side
                encMac.init(new SecretKeySpec(server_mac_secret, macName));
                decMac.init(new SecretKeySpec(client_mac_secret, macName));
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new AlertException(AlertProtocol.INTERNAL_ERROR,
                    new SSLProtocolException(
                        "Error during computation of security parameters"));
        }
    }

    /**
     * Creates the GenericStreamCipher or GenericBlockCipher
     * data structure for specified data of specified type.
     * @throws AlertException if alert was occurred.
     */
    @Override
    protected byte[] encrypt(byte type, byte[] fragment, int offset, int len) {
        try {
            int content_mac_length = len + hash_size;
            int padding_length = (block_size == 0) ? 0 : getPaddingSize(++content_mac_length);
            byte[] res = new byte[content_mac_length + padding_length];
            System.arraycopy(fragment, offset, res, 0, len);

            mac_material_header[0] = type;
            mac_material_header[3] = (byte) ((0x00FF00 & len) >> 8);
            mac_material_header[4] = (byte) (0x0000FF & len);

            encMac.update(write_seq_num);
            encMac.update(mac_material_header);
            encMac.update(fragment, offset, len);
            encMac.doFinal(res, len);

            //if (logger != null) {
            //    logger.println("MAC Material:");
            //    logger.print(write_seq_num);
            //    logger.print(mac_material_header);
            //    logger.print(fragment, offset, len);
            //}

            if (block_size != 0) {
                // do padding:
                Arrays.fill(res, content_mac_length-1,
                        res.length, (byte) (padding_length));
            }
            if (logger != null) {
                logger.println("SSLRecordProtocol.do_encryption: Generic"
                        + (block_size != 0
                            ? "BlockCipher with padding["+padding_length+"]:"
                            : "StreamCipher:"));
                logger.print(res);
            }
            byte[] rez = new byte[encCipher.getOutputSize(res.length)];
            // We should not call just doFinal because it reinitialize
            // the cipher, but as says rfc 2246:
            // "For stream ciphers that do not use a synchronization
            // vector (such as RC4), the stream cipher state from the end
            // of one record is simply used on the subsequent packet."
            // and for block ciphers:
            // "The IV for subsequent records is the last ciphertext block from
            // the previous record."
            // i.e. we should keep the cipher state.
            encCipher.update(res, 0, res.length, rez);
            incSequenceNumber(write_seq_num);
            return rez;
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
            throw new AlertException(AlertProtocol.INTERNAL_ERROR,
                    new SSLProtocolException("Error during the encryption"));
        }
    }

    /**
     * Retrieves the fragment of the Plaintext structure of
     * the specified type from the provided data representing
     * the Generic[Stream|Block]Cipher structure.
     * @throws AlertException if alert was occurred.
     */
    @Override
    protected byte[] decrypt(byte type, byte[] fragment,
            int offset, int len) {
        // plain data of the Generic[Stream|Block]Cipher structure
        byte[] data = decCipher.update(fragment, offset, len);
        // the 'content' part of the structure
        byte[] content;
        if (block_size != 0) {
            // check padding
            int padding_length = data[data.length-1];
            for (int i=0; i<padding_length; i++) {
                if (data[data.length-2-i] != padding_length) {
                    throw new AlertException(
                            AlertProtocol.DECRYPTION_FAILED,
                            new SSLProtocolException(
                                "Received message has bad padding"));
                }
            }
            content = new byte[data.length - hash_size - padding_length - 1];
        } else {
            content = new byte[data.length - hash_size];
        }

        mac_material_header[0] = type;
        mac_material_header[3] = (byte) ((0x00FF00 & content.length) >> 8);
        mac_material_header[4] = (byte) (0x0000FF & content.length);

        decMac.update(read_seq_num);
        decMac.update(mac_material_header);
        decMac.update(data, 0, content.length); // mac.update(fragment);
        byte[] mac_value = decMac.doFinal();
        if (logger != null) {
            logger.println("Decrypted:");
            logger.print(data);
            //logger.println("MAC Material:");
            //logger.print(read_seq_num);
            //logger.print(mac_material_header);
            //logger.print(data, 0, content.length);
            logger.println("Expected mac value:");
            logger.print(mac_value);
        }
        // checking the mac value
        for (int i=0; i<hash_size; i++) {
            if (mac_value[i] != data[i+content.length]) {
                throw new AlertException(AlertProtocol.BAD_RECORD_MAC,
                        new SSLProtocolException("Bad record MAC"));
            }
        }
        System.arraycopy(data, 0, content, 0, content.length);
        incSequenceNumber(read_seq_num);
        return content;
    }
}

