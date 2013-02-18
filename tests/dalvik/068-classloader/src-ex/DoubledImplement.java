// Copyright 2008 The Android Open Source Project

/**
 * Doubled sub-class, form #2.
 */
public class DoubledImplement implements ICommon {
    public DoubledImplement() {
        System.out.println("Ctor: doubled implement, type 2");
    }

    public DoubledImplement getDoubledInstance() {
        return new DoubledImplement();
    }

    public void two() {
        System.out.println("DoubledImplement two");
    }
}
