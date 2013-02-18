// Copyright 2006 The Android Open Source Project

/**
 * Some stuff for access checks.
 */
public class PublicAccess {
    public static void main() {
        String shouldFail = SemiPrivate.mPrivvy;
        System.out.println("Got " + shouldFail);
    }
}
