// Copyright 2008 The Android Open Source Project

public class Main {
    int mFoo = 27;

    private void doStuff() {
        System.out.println("mFoo is " + mFoo);
    }

    public static void main(String[] args) {
        Main instance = null;
        try {
            instance.doStuff();
            throw new RuntimeException("fail");
        } catch (NullPointerException npe) { }

        System.out.println("done");
    }
}
