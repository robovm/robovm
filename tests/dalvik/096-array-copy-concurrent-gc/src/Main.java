/*
 * Copyright (C) 2011 The Android Open Source Project
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

/**
 * Running concurrent gc and doing some System.arraycopy
 * Several threads is created in order to increase the probability
 * of thread switches at critical points. Without creating several
 * threads the test case usually passed even when there were bugs.
 * Size of array and amount of garbage created is based on experimental
 * numbers and is a tradeoff between time that the test takes when
 * it succeeds and the probability that the test discovers a problem.
 */
public class Main {
    public static void main(String args[]) {
        new ObjectCreatorThread(true).start();
        new ObjectCreatorThread(false).start();
        new ObjectCreatorThread(false).start();
    }

    static class ObjectCreatorThread extends Thread {
        boolean mDoLog;
        public ObjectCreatorThread(boolean doLog) {
            mDoLog = doLog;
        }

        @Override
        public void run() {
            new Main().stressArray(mDoLog);
        }
    }

    Object [] array = new Object[10000];

    void stressArray(boolean doLog) {
        // We want many references in the array
        // We also want elements close to each other to have large
        // diff in address so lets skip every 2:nd address so it is null
        if (doLog) {
            System.out.println("Initializing...");
        }
        for (int i = 0; i < array.length; i+=2) {
            array[i] = new String("Creating some garbage" + i);
        }

        if (doLog) {
            System.out.println("Starting the test");
        }

        for (int j = 0; j < array.length; j++) {
            Object obj = array[array.length - 1];
            System.arraycopy(array, 0, array, 1, array.length - 1);
            array[0] = obj;
            new String("Creating some garbage" + Math.random());
            new String("Creating some garbage" + Math.random());
            new String("Creating some garbage" + Math.random());
            new String("Creating some garbage" + Math.random());
        }

        for (int j = 0; j < array.length; j++) {
            Object obj = array[0];
            System.arraycopy(array, 1, array, 0, array.length - 1);
            array[array.length - 1] = obj;
            new String("Creating some garbage" + Math.random());
            new String("Creating some garbage" + Math.random());
            new String("Creating some garbage" + Math.random());
            new String("Creating some garbage" + Math.random());
        }

        if (doLog) {
            System.out.println("Test OK");
        }
    }
}
