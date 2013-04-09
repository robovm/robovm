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

package com.android.org.bouncycastle.jce.provider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;
import junit.framework.TestCase;
import com.android.org.bouncycastle.jce.provider.CertBlacklist;
import com.android.org.bouncycastle.util.encoders.Hex;

public class CertBlacklistTest extends TestCase {

    private File tmpFile;

    private Set<String> DEFAULT_PUBKEYS;
    private Set<String> DEFAULT_SERIALS;

    public CertBlacklistTest() throws IOException {
        tmpFile = File.createTempFile("test", "");
        DEFAULT_PUBKEYS = getDefaultPubkeys();
        DEFAULT_SERIALS = getDefaultSerials();
        tmpFile.delete();
    }

    @Override
    public void setUp() throws Exception {
        super.setUp();
        tmpFile = File.createTempFile("test", "");
    }

    @Override
    public void tearDown() throws Exception {
        try {
            tmpFile.delete();
        } finally {
            super.tearDown();
        }
    }

    private Set<String> getPubkeyBlacklist(String path) throws IOException {
        // set our blacklist path
        CertBlacklist bl = new CertBlacklist(path, CertBlacklist.DEFAULT_SERIAL_BLACKLIST_PATH);
        // call readPubkeyBlacklist
        Set<byte[]> arr = bl.pubkeyBlacklist;
        // convert the results to a hashset of strings
        Set<String> results = new HashSet<String>();
        for (byte[] value: arr) {
            results.add(new String(Hex.encode(value)));
        }
        return results;
    }

    private Set<String> getSerialBlacklist(String path) throws IOException {
        // set our blacklist path
        CertBlacklist bl = new CertBlacklist(CertBlacklist.DEFAULT_PUBKEY_BLACKLIST_PATH, path);
        // call readPubkeyBlacklist
        Set<BigInteger> arr = bl.serialBlacklist;
        // convert the results to a hashset of strings
        Set<String> results = new HashSet<String>();
        for (BigInteger value: arr) {
            results.add(value.toString(16));
        }
        return results;
    }

    private Set<String> getDefaultPubkeys() throws IOException {
        return getPubkeyBlacklist("");
    }

    private Set<String> getDefaultSerials() throws IOException {
        return getSerialBlacklist("");
    }

    private Set<String> getCurrentPubkeyBlacklist() throws IOException {
        return getPubkeyBlacklist(tmpFile.getCanonicalPath());
    }

    private Set<String> getCurrentSerialBlacklist() throws IOException {
        return getSerialBlacklist(tmpFile.getCanonicalPath());
    }

    private void blacklistToFile(String blacklist) throws IOException {
        FileOutputStream out = new FileOutputStream(tmpFile);
        out.write(blacklist.toString().getBytes());
        out.close();
    }

    private void writeBlacklist(HashSet<String> values) throws IOException {
        StringBuilder result = new StringBuilder();
        // join the values into a string
        for (String value : values) {
            if (result.length() != 0) {
                result.append(",");
            }
            result.append(value);
        }
        blacklistToFile(result.toString());
    }

    public void testPubkeyBlacklistLegit() throws IOException {
        // build the blacklist
        HashSet<String> bl = new HashSet<String>();
        bl.add("6ccabd7db47e94a5759901b6a7dfd45d1c091ccc");
        // write the blacklist
        writeBlacklist(bl);
        // add the default pubkeys into the bl
        bl.addAll(DEFAULT_PUBKEYS);
        // do the test
        assertEquals(bl, getCurrentPubkeyBlacklist());
    }

    public void testSerialBlacklistLegit() throws IOException {
        // build the blacklist
        HashSet<String> bl = new HashSet<String>();
        bl.add("22e514121e61c643b1e9b06bd4b9f7d0");
        // write the blacklist
        writeBlacklist(bl);
        // add the default serials into the bl
        bl.addAll(DEFAULT_SERIALS);
        // do the test
        assertEquals(bl, getCurrentSerialBlacklist());
    }

    public void testPubkeyBlacklistMultipleLegit() throws IOException {
        // build the blacklist
        HashSet<String> bl = new HashSet<String>();
        bl.add("6ccabd7db47e94a5759901b6a7dfd45d1c091ccc");
        bl.add("6ccabd7db47e94a5759901b6a7dfd45d1c091ccd");
        // write the blacklist
        writeBlacklist(bl);
        // add the default pubkeys into the bl
        bl.addAll(DEFAULT_PUBKEYS);
        // do the test
        assertEquals(bl, getCurrentPubkeyBlacklist());
    }

    public void testSerialBlacklistMultipleLegit() throws IOException {
        // build the blacklist
        HashSet<String> bl = new HashSet<String>();
        bl.add("22e514121e61c643b1e9b06bd4b9f7d0");
        bl.add("22e514121e61c643b1e9b06bd4b9f7d1");
        // write the blacklist
        writeBlacklist(bl);
        // add the default serials into the bl
        bl.addAll(DEFAULT_SERIALS);
        // do the test
        assertEquals(bl, getCurrentSerialBlacklist());
    }

    public void testPubkeyBlacklistMultipleBad() throws IOException {
        // build the blacklist
        HashSet<String> bl = new HashSet<String>();
        bl.add("6ccabd7db47e94a5759901b6a7dfd45d1c091ccc");
        bl.add("");
        bl.add("6ccabd7db47e94a5759901b6a7dfd45d1c091ccd");
        // write the blacklist
        writeBlacklist(bl);
        // add the default pubkeys into the bl
        bl.addAll(DEFAULT_PUBKEYS);
        // remove the bad one
        bl.remove("");
        // do the test- results should be all but the bad one are handled
        assertEquals(bl, getCurrentPubkeyBlacklist());
    }

    public void testSerialBlacklistMultipleBad() throws IOException {
        // build the blacklist
        HashSet<String> bl = new HashSet<String>();
        bl.add("22e514121e61c643b1e9b06bd4b9f7d0");
        bl.add("");
        bl.add("22e514121e61c643b1e9b06bd4b9f7d1");
        // write the blacklist
        writeBlacklist(bl);
        // add the default serials into the bl
        bl.addAll(DEFAULT_SERIALS);
        // remove the bad one
        bl.remove("");
        // do the test- results should be all but the bad one are handled
        assertEquals(bl, getCurrentSerialBlacklist());
    }

    public void testPubkeyBlacklistDoesntExist() throws IOException {
        assertEquals(DEFAULT_PUBKEYS, getCurrentPubkeyBlacklist());
    }

    public void testSerialBlacklistDoesntExist() throws IOException {
        assertEquals(DEFAULT_SERIALS, getCurrentSerialBlacklist());
    }

    public void testPubkeyBlacklistNotHexValues() throws IOException {
        // build the blacklist
        HashSet<String> bl = new HashSet<String>();
        bl.add("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        // write the blacklist
        writeBlacklist(bl);
        // do the test
        assertEquals(DEFAULT_PUBKEYS, getCurrentPubkeyBlacklist());
    }

    public void testSerialBlacklistNotHexValues() throws IOException {
        // build the blacklist
        HashSet<String> bl = new HashSet<String>();
        bl.add("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
        // write the blacklist
        writeBlacklist(bl);
        // do the test
        assertEquals(DEFAULT_SERIALS, getCurrentSerialBlacklist());
    }

    public void testPubkeyBlacklistIncorrectLength() throws IOException {
        // build the blacklist
        HashSet<String> bl = new HashSet<String>();
        bl.add("6ccabd7db47e94a5759901b6a7dfd45d1c091cc");
        // write the blacklist
        writeBlacklist(bl);
        // do the test
        assertEquals(DEFAULT_PUBKEYS, getCurrentPubkeyBlacklist());
    }

    public void testSerialBlacklistZero() throws IOException {
        // build the blacklist
        HashSet<String> bl = new HashSet<String>();
        bl.add("0");
        // write the blacklist
        writeBlacklist(bl);
        // add the default serials
        bl.addAll(DEFAULT_SERIALS);
        // do the test
        assertEquals(bl, getCurrentSerialBlacklist());
    }

    public void testSerialBlacklistNegative() throws IOException {
        // build the blacklist
        HashSet<String> bl = new HashSet<String>();
        bl.add("-1");
        // write the blacklist
        writeBlacklist(bl);
        // add the default serials
        bl.addAll(DEFAULT_SERIALS);
        // do the test
        assertEquals(bl, getCurrentSerialBlacklist());
    }
}
