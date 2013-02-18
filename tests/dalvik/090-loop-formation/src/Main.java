/*
 * Copyright (C) 2011 The Android Open Source Project
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

/*
 * Create two versions of loops where the unresolved field is on either the
 * taken or the non-taken path to make sure that the loop detection code bails
 * on unresolved fields.
 */
public class Main {
    static int counter1;
    static int counter2;
    static int counter3;
    static int counter4;
    static int counter5;

    public static void main(String[] args) {
        /* counter1 is not resolved */
        for (int i = 0; i < 32767; i++) {
            if (i < 0) {
                counter1++;
            } else {
                counter2++;
            }
            counter5++;
        }

        /* counter4 is not resolved */
        for (int i = 0; i < 32767; i++) {
            if (i >= 0) {
                counter3++;
            } else {
                counter4++;
            }
            counter5++;
        }

        System.out.println("counter1 is " + counter1);
        System.out.println("counter2 is " + counter2);
        System.out.println("counter3 is " + counter3);
        System.out.println("counter4 is " + counter4);
        System.out.println("counter5 is " + counter5);
    }
}
