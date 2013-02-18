// Copyright 2008 The Android Open Source Project

import java.lang.ref.WeakReference;

public class FinalizerTest {
    public static FinalizerTest mNothing = new FinalizerTest("nothing");
    public static FinalizerTest mReborn = mNothing;

    public String mMsg = "default";

    public FinalizerTest(String msg) {
        mMsg = msg;
    }

    public String toString() {
        return mMsg;
    }

    protected void finalize() {
        System.out.println("finalizer executed: " + mMsg);
        mReborn = this;
    }
}
