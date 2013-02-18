// Copyright 2006 The Android Open Source Project

/**
 * Miranda testing.
 */
public class MirandaClass extends MirandaAbstract {

    public MirandaClass() {}

    public boolean inInterface() {
        //System.out.println("    MirandaClass inInterface");
        return true;
    }

    public int inInterface2() {
        //System.out.println("    MirandaClass inInterface2");
        return 27;
    }

    public boolean inAbstract() {
        //System.out.println("    MirandaClass inAbstract");
        return false;
    }
}
