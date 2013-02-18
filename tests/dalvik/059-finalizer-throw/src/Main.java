// Copyright 2008 The Android Open Source Project

import java.util.Timer;
import java.util.TimerTask;

/*
 * Throw an exception from a finalizer and make sure it's harmless.  Under
 * Dalvik this may also generate a warning in the log file.
 */
public class Main {
    static Object waiter = new Object();
    static volatile boolean didFinal = false;

    static void createAndForget() {
        Main main = new Main();
    }

    public static void main(String[] args) {
        createAndForget();

        System.gc();
        System.runFinalization();

        new Timer(true).schedule(new TimerTask() {
                public void run() {
                    System.out.println("Timed out, exiting");
                    System.exit(1);
                }
            }, 30000);

        while (!didFinal) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                System.err.println(ie);
            }
        }

        /* give it a chance to cause mayhem */
        try {
            Thread.sleep(750);
        } catch (InterruptedException ie) {
            System.err.println(ie);
        }

        System.out.println("done");
    }

    protected void finalize() throws Throwable {
        System.out.println("In finalizer");

        didFinal = true;

        throw new InterruptedException("whee");
    }
}
