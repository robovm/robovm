// Copyright 2007 The Android Open Source Project

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * Report death-by-uncaught-exception.
 */
public class ThreadDeathHandler implements Thread.UncaughtExceptionHandler {
    private String mMyMessage;

    public ThreadDeathHandler(String msg) {
        mMyMessage = msg;
    }

    public void uncaughtException(Thread t, Throwable e) {
        System.err.println("Uncaught exception " + mMyMessage + "!");
        e.printStackTrace();
    }
}
