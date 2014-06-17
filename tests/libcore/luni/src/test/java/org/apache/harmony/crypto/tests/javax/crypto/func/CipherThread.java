/*
 * Copyright (C) 2008 The Android Open Source Project
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
package org.apache.harmony.crypto.tests.javax.crypto.func;

import javax.crypto.Cipher;

public abstract class CipherThread implements Runnable {
    private int[] keyLengthAr = null;
    private String[] modeAr = null;
    private String[] paddingModeAr = null;
    private int kCounter = 0;
    private int mCounter = 0;
    private int pCounter = 0;
    private StringBuffer errorSB = null;

    private boolean flagTestResult = false;
    private String data = "    Albert Einstein was a German-born " +
                "theoretical physicist.    ";
    private String algName = null;
    private int keyLength = 0;
    private String mode = null;
    private String paddingMode = null;
    private int fails = 0;

    public abstract void crypt() throws Exception;

    CipherThread(String name, int[] keys, String[] modes, String[] paddings) {
        algName     = name;
        keyLengthAr   = keys;
        modeAr        = modes;
        paddingModeAr = paddings;
        kCounter = 0;
        mCounter = 0;
        pCounter = 0;
    }

    public void checkEncodedData(byte[] original, byte[] encoded)
            throws Exception {
        for(int i = 0; i < original.length; i++) {
            if (original[i] != encoded[i]) {
                throw new Exception("Source and encoded data not match " +
                        getCipherParameters());
            }
        }
    }

    public void checkPaddedEncodedData(byte[] original, byte[] encoded, int offset)
            throws Exception {
        for (int i = 0; i < offset; i++) {
            if (encoded[i] != 0) {
                throw new Exception("Encoded data is not properly padded at offset " + i);
            }
        }
        for (int i = 0; i < original.length; i++) {
            if (original[i] != encoded[i + offset]) {
                throw new Exception("Source and encoded data not match " + getCipherParameters());
            }
        }
    }

    public void launcher() {
        Thread thread = null;

        do {
            keyLength = getNextKey();
            do {
                mode = getNextMode();
                do {
                    paddingMode = getNextPadding();
                    thread = new Thread(this);
                    thread.start();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } while (hasNextPadding());
            } while (hasNextMode());
        } while (hasNextKey());
    }

    public void run() {
        try {
            crypt();
        } catch (Exception e) {
            if(errorSB == null) {
                errorSB = new StringBuffer();
            }
            errorSB.append(e.getMessage());
            errorSB.append("\n");
            errorSB.append(getCipherParameters());
            errorSB.append("\n");
            StackTraceElement[] st = e.getStackTrace();
            for (int i = 0; i < st.length; i++) {
                errorSB.append(st[i].toString());
                errorSB.append("\n");
            }
            fails++;
            return;
        }
        flagTestResult = true;
    }

    public String getAlgName() {
        return algName;
    }

    public int getKeyLength() {
        return keyLength;
    }

    public String getData() {
        return data;
    }

    public String getPadding() {
        return paddingMode;
    }

    public String getMode() {
        return mode;
    }

    public String getCipherParameters() {
        return "Alg name:" + algName + " Key:" + keyLength + " Mode:" + mode +
        " Padding:" + paddingMode;
    }

    public boolean getTestStatus() {
        return flagTestResult;
    }

    public String getAlgorithmName() {
        return algName;
    }

    public boolean hasNextKey() {
        return (kCounter < keyLengthAr.length);
    }

    public boolean hasNextMode() {
        return (mCounter < modeAr.length);
    }

    public boolean hasNextPadding() {
        return (pCounter < paddingModeAr.length);
    }

    public int getNextKey() {
        kCounter = (hasNextKey()) ? kCounter : 0;
        return keyLengthAr[kCounter++];
    }

    public String getNextMode() {
        mCounter = (hasNextMode()) ? mCounter : 0;
        return modeAr[mCounter++];
    }

    public String getNextPadding() {
        pCounter = (hasNextPadding()) ? pCounter : 0;
        return paddingModeAr[pCounter++];
    }

    public long getTotalCasesNumber() {
        return keyLengthAr.length * modeAr.length * paddingModeAr.length;
    }

    public long getTotalFailuresNumber() {
        return fails;
    }

    public String getFailureMessages() {
        return (errorSB == null) ? "" : new String(errorSB);
    }
}
