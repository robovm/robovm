/*
 * Copyright (C) 2007 The Android Open Source Project
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

/**
 * Test switch() blocks
 */
public class Main {
    public static void main(String args[]) {
        int a = 1;

        switch (a) {
            case -1: System.out.print("neg one\n"); break;
            case 0: System.out.print("zero\n"); break;
            case 1: System.out.print("CORRECT (one)\n"); break;
            case 2: System.out.print("two\n"); break;
            case 3: System.out.print("three\n"); break;
            case 4: System.out.print("four\n"); break;
            default: System.out.print("???\n"); break;
        }
        switch (a) {
            case 3: System.out.print("three\n"); break;
            case 4: System.out.print("four\n"); break;
            default: System.out.print("CORRECT (not found)\n"); break;
        }

        a = 0x12345678;

        switch (a) {
            case 0x12345678: System.out.print("CORRECT (large)\n"); break;
            case 0x12345679: System.out.print("large+1\n"); break;
            default: System.out.print("nuts\n"); break;
        }
        switch (a) {
            case 0x12345678: System.out.print("CORRECT (large2)\n"); break;
            case 0x12345700: System.out.print("large+many\n"); break;
            default: System.out.print("nuts\n"); break;
        }
        switch (a) {
            case 57: System.out.print("fifty-seven!\n"); break;
            case -6: System.out.print("neg six!\n"); break;
            case 0x12345678: System.out.print("CORRECT (large3)\n"); break;
            case 22: System.out.print("twenty-two!\n"); break;
            case 3: System.out.print("three!\n"); break;
            default: System.out.print("huh?\n"); break;
        }
        switch (a) {
            case -6: System.out.print("neg six!\n"); break;
            case 3: System.out.print("three!\n"); break;
            default: System.out.print("CORRECT (not found)\n"); break;
        }

        a = -5;
        switch (a) {
            case 12: System.out.print("twelve\n"); break;
            case -5: System.out.print("CORRECT (not found)\n"); break;
            case 0: System.out.print("zero\n"); break;
            default: System.out.print("wah?\n"); break;
        }

        switch (a) {
            default: System.out.print("CORRECT (default only)\n"); break;
        }

        a = -10;
        switch (a) {
            case -10: System.out.print("CORRECT big sparse / first\n"); break;
            case -5: System.out.print("neg five\n"); break;
            case 0: System.out.print("zero\n"); break;
            case 5: System.out.print("five\n"); break;
            case 10: System.out.print("ten\n"); break;
            case 15: System.out.print("fifteen\n"); break;
            case 20: System.out.print("twenty\n"); break;
            case 50: System.out.print("fifty\n"); break;
            case 100: System.out.print("hundred\n"); break;
            default: System.out.print("blah!\n"); break;
        }

        a = 100;
        switch (a) {
            case -10: System.out.print("neg ten\n"); break;
            case -5: System.out.print("neg five\n"); break;
            case 0: System.out.print("zero\n"); break;
            case 5: System.out.print("five\n"); break;
            case 10: System.out.print("ten\n"); break;
            case 15: System.out.print("fifteen\n"); break;
            case 20: System.out.print("twenty\n"); break;
            case 50: System.out.print("fifty\n"); break;
            case 100: System.out.print("CORRECT big sparse / last\n"); break;
            default: System.out.print("blah!\n"); break;
        }
    }
}
