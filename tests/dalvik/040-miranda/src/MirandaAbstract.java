// Copyright 2006 The Android Open Source Project

/**
 * Miranda testing.
 */
public abstract class MirandaAbstract implements MirandaInterface, MirandaInterface2
{
    protected MirandaAbstract() { }

    //public abstract boolean inInterface();
    //public abstract int inInterface2();

    public boolean inAbstract() {
        return true;
    }
}
