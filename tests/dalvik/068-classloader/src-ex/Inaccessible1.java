// Copyright 2008 The Android Open Source Project

/**
 * Non-public class, inaccessible from Main.  Note the constructor is
 * public.
 */
class Inaccessible1 extends SimpleBase {
    public Inaccessible1() {
        System.out.println("--- inaccessible1");
    }
}
