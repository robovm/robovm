// Copyright 2008 The Android Open Source Project

/*
 * Some basic operations for testing the debugger.
 */
public class Main {
    long mLong = 0x1122334455667788L;

    public Main() {
        double d = 3.1415;
        System.out.println("d is " + d);
    }

    public static void showObject(Object[] foo) {
        int xyz = 27;
        System.out.println("class: " + foo.getClass());

        for (int i = 0; i < foo.length; i++) {
            System.out.println(i + ": "  + foo[i]);
        }
    }

    public static void main(String[] args) {
        int x = 5;
        Main testObj = new Main();

        Object[] array = new Object[5];
        showObject(array);

        String[] niftyStrings = new String[] { "hey", "you", "there" };
        array = niftyStrings;
        showObject(array);
    }
}
