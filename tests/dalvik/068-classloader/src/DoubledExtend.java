// Copyright 2008 The Android Open Source Project

/**
 * Doubled sub-class, form #1.
 */
public class DoubledExtend extends Base {
    public DoubledExtend() {
        //System.out.println("Ctor: doubled extend, type 1");
    }

    @Override
    public DoubledExtend getExtended() {
        System.out.println("getExtended 1");
        return new DoubledExtend();
    }

    public String getStr() {
        return "DoubledExtend 1";
    }
}
