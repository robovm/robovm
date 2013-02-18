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
 * Holds an int.
 */
public class IntHolder {
    private int mValue = 0;

    /**
     * Constructs an IntHolder with the specified value.  Throws an
     * exception if the initial value is less than zero.
     */
    public IntHolder(int initialVal) {
        if (initialVal < 0)
            throw new RuntimeException("negative number");

        mValue = initialVal;
    }

    public int getValue() {
        return mValue;
    }
    public void setValue(int val) {
        mValue = val;
    }
}
