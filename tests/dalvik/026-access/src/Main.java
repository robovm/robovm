// Copyright 2006 The Android Open Source Project

import otherpackage.PublicAccess;

public class Main {
    public static void main(String[] args) {
        System.out.println("access test");

        PublicAccess pa = new PublicAccess();
        pa.main();
    }
}
