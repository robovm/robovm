// Copyright 2006 The Android Open Source Project

/**
 * Interface test.
 */
public class ImplBSub extends ImplB implements /*Iface2,*/ Iface2Sub1 {

    public int iFunc1(int ii) {
        return ii+100;
    }
    public int iFunc2(int ii) {
        return ii+200;
    }
}
