// Copyright 2007 The Android Open Source Project

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;

class Privvy implements PrivilegedAction<Integer> {

    private Integer mValue;

    public Privvy(int val) {
        mValue = new Integer(val + 1);
    }

    public Integer run() {
        return mValue;
    }
}
