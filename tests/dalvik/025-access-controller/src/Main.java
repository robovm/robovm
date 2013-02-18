// Copyright 2007 The Android Open Source Project

import java.security.AccessController;

/**
 * Test java.security.AccessController.
 */
public class Main {
    public static void main(String[] args) {
        Privvy priv = new Privvy(38);
        Integer result = AccessController.doPrivileged(priv);
        System.out.println("AccessControllerTest: got " + result);
    }
}
