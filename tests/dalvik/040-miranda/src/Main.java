// Copyright 2006 The Android Open Source Project

/**
 * Miranda testing.
 */
public class Main {
    public static void main(String[] args) {
        MirandaClass mir = new MirandaClass();
        System.out.println("MirandaClass:");
        System.out.println("  inInterface:  " + mir.inInterface());
        System.out.println("  inInterface2: " + mir.inInterface2());
        System.out.println("  inAbstract:   " + mir.inAbstract());

        /* try again through abstract class; results should be identical */
        MirandaAbstract mira = mir;
        System.out.println("MirandaAbstract / MirandaClass:");
        System.out.println("  inInterface:  " + mira.inInterface());
        System.out.println("  inInterface2: " + mira.inInterface2());
        System.out.println("  inAbstract:   " + mira.inAbstract());

        MirandaAbstract mira2 = new MirandaClass2();
        System.out.println("MirandaAbstract / MirandaClass2:");
        System.out.println("  inInterface:  " + mira2.inInterface());
        System.out.println("  inInterface2: " + mira2.inInterface2());
        System.out.println("  inAbstract:   " + mira2.inAbstract());
    }
}
