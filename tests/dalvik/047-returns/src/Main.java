// Copyright 2007 The Android Open Source Project

/**
 * Return stuff.
 */
public class Main {
    public static void main(String[] args) {

        System.out.println("pick 1");
        pickOne(1).run();
        System.out.println(((CommonInterface)pickOne(1)).doStuff());

        System.out.println("pick 2");
        pickOne(2).run();
        System.out.println(((CommonInterface)pickOne(2)).doStuff());

        System.out.println("pick 3");
        pickOne(3).run();
    }

    public static Runnable pickOne(int which) {
        Runnable runme;

        if (which == 1)
            runme = new ClassOne();
        else if (which == 2)
            runme = new ClassTwo();
        else if (which == 3)
            runme = new ClassThree();
        else
            runme = null;

        return runme;
    }
}

class ClassOne implements CommonInterface, Runnable {
    public void run() {
        System.out.println("one running");
    }
    public int doStuff() {
        System.out.println("one");
        return 1;
    }
}

class ClassTwo implements CommonInterface, Runnable {
    public void run() {
        System.out.println("two running");
    }
    public int doStuff() {
        System.out.println("two");
        return 2;
    }
}

class ClassThree implements Runnable {
    public void run() {
        System.out.println("three running");
    }
}

interface CommonInterface {
    int doStuff();
}
