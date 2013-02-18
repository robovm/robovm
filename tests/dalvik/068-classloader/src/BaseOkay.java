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

/**
 * Common base class.
 */
public class BaseOkay implements IDoubledExtendOkay {
    public BaseOkay() {}

    public DoubledExtendOkay getExtended() {
        return new DoubledExtendOkay();
    }

    public static String doStuff(DoubledExtendOkay dt) {
        return dt.getStr();
    }
}

/**
 * Interface that declares the not-overridden method.  This exists to ensure
 * that the existence of an interface doesn't trip the check.
 */
interface IDoubledExtendOkay {
    public DoubledExtendOkay getExtended();
}
