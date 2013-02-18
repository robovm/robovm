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
 * "Okay" doubled sub-class, form #2.
 */
public class DoubledExtendOkay extends BaseOkay {
    public DoubledExtendOkay() {
        //System.out.println("Ctor: doubled extend okay, type 2");
    }

    /*
    @Override
    public DoubledExtendOkay getExtended() {
        //System.out.println("getExtended 2");
        return new DoubledExtendOkay();
    }
    */

    public String getStr() {
        return "DoubledExtendOkay 2";
    }
}
