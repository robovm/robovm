// Copyright 2008 The Android Open Source Project

/**
 * Doubled sub-class, form #1.
 */
class DoubledImplement implements ICommon {
    public DoubledImplement() {
        System.out.println("Ctor: doubled implement, type 1");
    }

    public DoubledImplement getDoubledInstance() {
        return new DoubledImplement();
    }

    public void one() {
        System.out.println("DoubledImplement one");
    }
}
