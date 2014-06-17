/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class Main {
    private static final int TEST_TIME = 5;

    public static void main(String[] args) {
        System.out.println("Running (" + TEST_TIME + " seconds) ...");
        InfiniteForLoop forLoop = new InfiniteForLoop();
        InfiniteWhileLoop whileLoop = new InfiniteWhileLoop();
        InfiniteDoWhileLoop doWhileLoop = new InfiniteDoWhileLoop();
        MakeGarbage garbage = new MakeGarbage();
        forLoop.start();
        whileLoop.start();
        doWhileLoop.start();
        garbage.start();
        for (int i = 0; i < TEST_TIME; i++) {
          System.gc();
          System.out.println(".");
          sleep(1000);
        }
        forLoop.stopNow();
        whileLoop.stopNow();
        doWhileLoop.stopNow();
        garbage.stopNow();
        System.out.println("Done.");
    }

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
            System.err.println("sleep was interrupted");
        }
    }
}

class InfiniteWhileLoop extends Thread {
  volatile private boolean keepGoing = true;
  public void run() {
    int i = 0;
    while (keepGoing) {
      i++;
    }
  }
  public void stopNow() {
    keepGoing = false;
  }
}

class InfiniteDoWhileLoop extends Thread {
  volatile private boolean keepGoing = true;
  public void run() {
    int i = 0;
    do {
      i++;
    } while (keepGoing);
  }
  public void stopNow() {
    keepGoing = false;
  }
}

class InfiniteForLoop extends Thread {
  int count = 100000;
  volatile private boolean keepGoing = true;
  public void run() {
    int i = 0;
    for (int j = 0; keepGoing; j++) {
      i += j;
    }
  }
  public void stopNow() {
    keepGoing = false;
  }
}


class MakeGarbage extends Thread {
  volatile private boolean keepGoing = true;
  public void run() {
    while (keepGoing) {
      byte[] garbage = new byte[100000];
    }
  }
  public void stopNow() {
    keepGoing = false;
  }
}
