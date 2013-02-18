/*
 * Copyright (C) 2010 The Android Open Source Project
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
 * Class that initializes with a pause.
 */
public class SlowInit {

    public static final IntHolder FIELD0 = new IntHolder(0);
    public static final IntHolder FIELD1 = new IntHolder(0);
    public static final IntHolder FIELD2 = new IntHolder(0);
    public static final IntHolder FIELD3 = new IntHolder(0);

    public static void printMsg(String msg) {
        System.out.println(msg);
    }

    static {
        FIELD0.setValue(111);
        FIELD1.setValue(222);
        printMsg("SlowInit static block pre-sleep");
        Main.sleep(4000);
        printMsg("SlowInit static block post-sleep");
        FIELD2.setValue(333);
        FIELD3.setValue(444);
    };
}
