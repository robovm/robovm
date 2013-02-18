// Copyright 2007 The Android Open Source Project

import java.net.ServerSocket;
import java.io.IOException;


/**
 * Quick server socket test.
 */
public class Main {
    private static void snooze(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ServerSocket socket;

        try {
            socket = new ServerSocket(7890);
        } catch (IOException ioe) {
            System.out.println("couldn't open socket " + ioe.getMessage());
            return;
        }

        System.out.println("opened!");
        snooze(1);

        try {
            socket.close();
        } catch (IOException ioe) {
            System.out.println("couldn't close socket " + ioe.getMessage());
            return;
        }

        System.out.println("closed!");
        snooze(1);

        try {
            socket = new ServerSocket(7890);
        } catch (IOException ioe) {
            System.out.println("couldn't reopen socket " + ioe.getMessage());
            return;
        }

        System.out.println("reopened!");
        System.out.println("done");
    }
}
