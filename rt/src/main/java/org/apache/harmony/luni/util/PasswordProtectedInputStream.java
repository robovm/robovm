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

package org.apache.harmony.luni.util;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * This class implements a password-protected input stream. The algorithm used
 * for protection is a Vigenere (repeated-key) cipher. The encrypted data is the
 * result of <original data> XOR KEYKEYKEY...
 */
public class PasswordProtectedInputStream extends FilterInputStream {

    private byte[] password; // Password to use to decrypt the input bytes

    private int pwdIndex; // Index into the password array.

    /**
     * Constructs a new instance of the receiver.
     * 
     * @param in The actual input stream where to read the bytes from.
     * @param password The password bytes to use to decrypt the input bytes
     */
    public PasswordProtectedInputStream(InputStream in, byte[] password) {
        super(in);
        this.password = password.clone();
    }

    @Override
    public int read() throws IOException {
        int read = in.read();
        if (read >= 0) {
            read ^= password[pwdIndex];
            pwdIndex = (pwdIndex + 1) % password.length;
        }
        return read;
    }

    @Override
    public int read(byte b[], int off, int len) throws IOException {
        int read = in.read(b, off, len);
        if (read > 0) {
            int lastIndex = off + read;
            for (int i = off; i < lastIndex; i++) {
                b[i] ^= password[pwdIndex];
                pwdIndex = (pwdIndex + 1) % password.length;
            }
        }
        return read;
    }

    @Override
    public long skip(long n) throws IOException {
        long skip = super.skip(n);
        pwdIndex += skip;
        return skip;
    }
}
