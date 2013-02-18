/*
 * Copyright (C) 2009 The Android Open Source Project
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
    Bitmap mBitmap1, mBitmap2, mBitmap3, mBitmap4;

    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException ie) {
            System.err.println("sleep interrupted");
        }
    }

    public static void main(String args[]) {
        System.out.println("start");

        Main main = new Main();
        main.run();

        sleep(1000);
        System.out.println("done");
    }

    public void run() {
        createBitmaps();

        System.gc();
        sleep(250);

        mBitmap2.drawAt(0, 0);

        System.out.println("nulling 1");
        mBitmap1 = null;
        System.gc();
        sleep(500);

        System.out.println("nulling 2");
        mBitmap2 = null;
        System.gc();
        sleep(500);

        System.out.println("nulling 3");
        mBitmap3 = null;
        System.gc();
        sleep(500);

        System.out.println("nulling 4");
        mBitmap4 = null;
        System.gc();
        sleep(500);

        Bitmap.shutDown();
    }

    /*
     * Create bitmaps.
     *
     * bitmap1 is 10x10 and unique
     * bitmap2 and bitmap3 are 20x20 and share the same storage.
     * bitmap4 is just another reference to bitmap3
     *
     * When we return there should be no local refs lurking on the stack.
     */
    public void createBitmaps() {
        Bitmap.NativeWrapper dataA = Bitmap.allocNativeStorage(10, 10);
        Bitmap.NativeWrapper dataB = Bitmap.allocNativeStorage(20, 20);
        mBitmap1 = new Bitmap("one", 10, 10, dataA);
        mBitmap2 = new Bitmap("two", 20, 20, dataB);
        mBitmap3 = mBitmap4 = new Bitmap("three/four", 20, 20, dataB);
    }
}
