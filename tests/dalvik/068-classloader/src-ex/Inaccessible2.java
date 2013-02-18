// Copyright 2008 The Android Open Source Project

/**
 * Public class that can't access its base.
 */
public class Inaccessible2 extends InaccessibleBase {
    public Inaccessible2() {
        System.out.println("--- inaccessible2");
    }
}
