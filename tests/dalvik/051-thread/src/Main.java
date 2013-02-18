/*
 * Copyright (C) 2006 The Android Open Source Project
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

import java.util.ArrayList;

/**
 * Test some basic thread stuff.
 */
public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Initializing System.out...");

        MyThread[] threads = new MyThread[512];
        for (int i = 0; i < 512; i++) {
            threads[i] = new MyThread();
        }

        for (MyThread thread : threads) {
            thread.start();
        }
        for (MyThread thread : threads) {
            thread.join();
        }

        System.out.println("Thread count: " + MyThread.mCount);

        go();
        System.out.println("thread test done");
    }

    public static void go() {
        Thread t = new Thread(null, new ThreadTestSub(), "Thready", 7168);

        t.setDaemon(false);

        System.out.print("Starting thread '" + t.getName() + "'\n");
        t.start();

        try {
            t.join();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        System.out.print("Thread starter returning\n");
    }

    /*
     * Simple thread capacity test.
     */
    static class MyThread extends Thread {
        static int mCount = 0;
        public void run() {
            synchronized (MyThread.class) {
                ++mCount;
            }
        }
    }
}

class ThreadTestSub implements Runnable {
    public void run() {
        System.out.print("@ Thread running\n");

        try {
            Thread.currentThread().setDaemon(true);
            System.out.print("@ FAILED: setDaemon() succeeded\n");
        } catch (IllegalThreadStateException itse) {
            System.out.print("@ Got expected setDaemon exception\n");
        }

        //if (true)
        //    throw new NullPointerException();
        try {
            Thread.sleep(2000);
        }
        catch (InterruptedException ie) {
            System.out.print("@ Interrupted!\n");
        }
        finally {
            System.out.print("@ Thread bailing\n");
        }
    }
}
