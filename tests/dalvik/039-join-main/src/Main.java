// Copyright 2007 The Android Open Source Project

/**
 * Make sure that a sub-thread can join the main thread.
 */
public class Main {
    public static void main(String[] args) {
        Thread t;

        t = new Thread(new JoinMainSub(Thread.currentThread()), "Joiner");
        System.out.print("Starting thread '" + t.getName() + "'\n");
        t.start();

        try { Thread.sleep(1000); }
        catch (InterruptedException ie) {}

        System.out.print("JoinMain starter returning\n");
    }
}

class JoinMainSub implements Runnable {
    private Thread mJoinMe;

    public JoinMainSub(Thread joinMe) {
        mJoinMe = joinMe;
    }

    public void run() {
        System.out.print("@ JoinMainSub running\n");

        try {
            mJoinMe.join();
            System.out.print("@ JoinMainSub successfully joined main\n");
        } catch (InterruptedException ie) {
            System.out.print("@ JoinMainSub interrupted!\n");
        }
        finally {
            System.out.print("@ JoinMainSub bailing\n");
        }
    }
}
