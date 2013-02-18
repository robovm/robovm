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
    static class ArrayMemEater {
        static int blowup(char[][] holder, int size) {
            int i = 0;
            try {
                for ( ; i < size; i++)
                    holder[i] = new char[128];
            } catch (OutOfMemoryError oome) {
                return i;
            }

            return size;
        }

        static void confuseCompilerOptimization(char[][] holder) {
        }
    }

    static class InstanceMemEater {
        InstanceMemEater next;
        double d1, d2, d3, d4, d5, d6, d7, d8;

        static InstanceMemEater blowup() {
            InstanceMemEater memEater;
            try {
                memEater = new InstanceMemEater();
            } catch (OutOfMemoryError e) {
                memEater = null;
            }
            return memEater;
        }

        static void confuseCompilerOptimization(InstanceMemEater memEater) {
        }
    }

    static void triggerArrayOOM() {
        int size = 1 * 1024 * 1024;
        char[][] holder = new char[size][];

        int count = ArrayMemEater.blowup(holder, size);
        ArrayMemEater.confuseCompilerOptimization(holder);
        if (count < size) {
            System.out.println("Array allocation failed");
        }
    }

    static void triggerInstanceOOM() {
        InstanceMemEater memEater = InstanceMemEater.blowup();
        InstanceMemEater lastMemEater = memEater;
        do {
            lastMemEater.next = InstanceMemEater.blowup();
            lastMemEater = lastMemEater.next;
        } while (lastMemEater != null);
        memEater.confuseCompilerOptimization(memEater);
        System.out.println("Instance allocation failed");
    }

    public static void main(String[] args) {
        triggerArrayOOM();
        triggerInstanceOOM();
    }
}
