// Copyright 2007 The Android Open Source Project

/**
 * Make sure private methods don't inherit.
 */
public class Main {
    public static void main(String args[]) {
        PrivatePackage inst1 = new PrivatePackage();
        PrivatePackage inst2 = new PrivatePackageSub();
        PrivatePackageSub inst3 = new PrivatePackageSub();

        System.out.println("PrivatePackage --> " + inst1.getStr());
        System.out.println("PrivatePackage --> " + inst2.getStr());
        System.out.println("PrivatePackage --> " + inst3.getStr());
        System.out.println("PrivatePackageSub --> " + inst3.getStrSub());

        inst1.stretchTest();
    }
}

class PrivatePackage {
    public String getStr() {
        return privGetStr();
    }

    private String privGetStr() {
        return "PrivatePackage!";
    }

    public void stretchTest() {
        PrivatePackage inst = new PrivatePackageSub();
        System.out.println("PrivatePackage --> " + inst.getStr());
        System.out.println("PrivatePackage --> " + inst.privGetStr());
    }
}

class PrivatePackageSub extends PrivatePackage {
    public String getStrSub() {
        return privGetStr();
    }

    private String privGetStr() {
        return "PrivatePackageSub!";
    }
}
