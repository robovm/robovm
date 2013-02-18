/*
 * Copyright (C) 2009 The Android Open Source Project
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

import java.util.concurrent.*;

/**
 * Test for Jit regressions.
 */
public class Main {
    public static void main(String args[]) throws Exception {
        b2296099Test();
        b2302318Test();
        b2487514Test();
        b5884080Test();
        zeroTest();
    }

    static void b2296099Test() throws Exception {
       int x = -1190771042;
       int dist = 360530809;
       int xl = -1190771042;
       int distl = 360530809;

       for (int i = 0; i < 100000; i++) {
           int b = rotateLeft(x, dist);
           if (b != 1030884493)
               throw new RuntimeException("Unexpected value: " + b
                       + " after " + i + " iterations");
       }
       for (int i = 0; i < 100000; i++) {
           long bl = rotateLeft(xl, distl);
           if (bl != 1030884493)
               throw new RuntimeException("Unexpected value: " + bl
                       + " after " + i + " iterations");
       }
       System.out.println("b2296099 passes");
   }

    static int rotateLeft(int i, int distance) {
        return ((i << distance) | (i >>> (-distance)));
    }

    static void b2302318Test() {
        System.gc();

        SpinThread slow = new SpinThread(Thread.MIN_PRIORITY);
        SpinThread fast1 = new SpinThread(Thread.NORM_PRIORITY);
        SpinThread fast2 = new SpinThread(Thread.MAX_PRIORITY);

        slow.setDaemon(true);
        fast1.setDaemon(true);
        fast2.setDaemon(true);

        fast2.start();
        slow.start();
        fast1.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ie) {/*ignore */}
        System.gc();

        System.out.println("b2302318 passes");
    }

    static void b2487514Test() {
        PriorityBlockingQueue q = new PriorityBlockingQueue(10);
        int catchCount = 0;

        q.offer(new Integer(0));
        /*
         * Warm up the code cache to have toArray() compiled. The key here is
         * to pass a compatible type so that there are no exceptions when
         * executing the method body (ie the APUT_OBJECT bytecode).
         */
        for (int i = 0; i < 1000; i++) {
            Integer[] ints = (Integer[]) q.toArray(new Integer[5]);
        }

        /* Now pass an incompatible type which is guaranteed to throw */
        for (int i = 0; i < 1000; i++) {
            try {
                Object[] obj = q.toArray(new String[5]);
            }
            catch (ArrayStoreException  success) {
                catchCount++;
            }
        }

        if (catchCount == 1000) {
            System.out.println("b2487514 passes");
        }
        else {
            System.out.println("b2487514 fails: catchCount is " + catchCount +
                               " (expecting 1000)");
        }
    }

    static void b5884080Test() {
        int vA = 1;

        int l = 0;
        do {
            int k = 0;
            do
                vA += 1;
            while(++k < 100);
        } while (++l < 1000);
        if (vA == 100001) {
            System.out.println("b5884080 passes");
        }
        else {
            System.out.println("b5884080 fails: vA is " + vA +
                               " (expecting 100001)");
        }
    }

    static void zeroTest() throws Exception {
        ZeroTests zt = new ZeroTests();
        try {
            zt.longDivTest();
        } catch (Throwable th) {
            th.printStackTrace();
        }
        try {
            zt.longModTest();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}

class SpinThread extends Thread {
    int mPriority;

    SpinThread(int prio) {
        super("Spin prio=" + prio);
        mPriority = prio;
    }

    public void run() {
        setPriority(mPriority);
        while (true) {}
    }
}
