/*
 * Copyright (C) 2008 The Android Open Source Project
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

package tests.support;

import SQLite.Function;
import SQLite.FunctionContext;


public class MockFunction implements SQLite.Function{
    private StringBuffer acc = new StringBuffer();
    public static boolean getAggValueCalled = false;
    public static boolean functionCalled = false;
    public static boolean stepCalled = false;
    public static boolean lastStepCalled = false;

    public String getAggValue() {
        getAggValueCalled = true;
        return acc.toString();
    }

    public void function(FunctionContext fc, String args[]) {
        functionCalled = true;
        if (args.length > 0) {
            fc.set_result(args[0].toLowerCase());
        }
    }

    public void step(FunctionContext fc, String args[]) {
        stepCalled = true;
        for (int i = 0; i < args.length; i++) {
            acc.append(args[i]);
            acc.append(" ");
        }
    }

    public void last_step(FunctionContext fc) {
        lastStepCalled = true;
        fc.set_result(acc.toString());
    }
}