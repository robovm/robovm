// Copyright 2008 The Android Open Source Project

public class Main {
    public static void main(String[] args) {
        Special special = new Special();
        special.callInner();
        System.out.println("done");
    }

    public static class Special {
        Blort mBlort = null;

        Special() {
            System.out.println("new Special()");
        }

        public void callInner() {
            try {
                mBlort.repaint();
                throw new RuntimeException("fail");
            } catch (NullPointerException npe) {}
        }
    }

    private class Blort {
        public void repaint() {
            System.out.println("shouldn't see this");
        }
    }

}
