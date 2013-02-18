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

package other;

/**
 * Parts of this class will disappear or change form.
 */
public class Mutant {
    public int disappearingField = 3;
    public static int disappearingStaticField = 4;

    public void disappearingMethod() {
        System.out.println("bye");
    }
    public static void disappearingStaticMethod() {
        System.out.println("kthxbai");
    }

    public int inaccessibleField = 5;
    public static int inaccessibleStaticField = 6;

    public void inaccessibleMethod() {
        System.out.println("no");
    }

    public static void inaccessibleStaticMethod() {
        System.out.println("nay");
    }
}
