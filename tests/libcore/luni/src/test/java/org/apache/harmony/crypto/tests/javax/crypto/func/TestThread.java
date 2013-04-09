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

public abstract class TestThread implements Runnable {
    public String[] algNamesArray = null;
    public int aCounter = 0;
    public String algName = null;
    public StringBuffer errorSB = null;
    public int fails = 0;
    public boolean flagTestResult = false;

    TestThread(String[] names) {
        algNamesArray = names;
        aCounter = 0;
    }

    public abstract void test() throws Exception;

    public void launcher() {
        Thread thread = null;

        do {
            algName = getNextAlgorithmName();
            thread = new Thread(this);
            thread.start();
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (hasAlgorithmName());
    }

    public void run() {
        try {
            test();
        } catch (Exception e) {
            if(errorSB == null) {
                errorSB = new StringBuffer();
            }
            errorSB.append(e.getMessage());
            errorSB.append("\n");
            errorSB.append(getAlgorithmName());
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

    public String getAlgorithmName() {
        return algName;
    }

    public boolean hasAlgorithmName() {
        return (aCounter < algNamesArray.length);
    }

    public String getNextAlgorithmName() {
        aCounter = (hasAlgorithmName()) ? aCounter : 0;
        return algNamesArray[aCounter++];
    }

    public long getTotalFailuresNumber() {
        return fails;
    }

    public String getFailureMessages() {
        return (errorSB == null) ? "" : new String(errorSB);
    }
}
