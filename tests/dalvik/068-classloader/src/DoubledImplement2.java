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

/**
 * Another doubled sub-class, form #1.
 */
public class DoubledImplement2 implements ICommon2 {
    public DoubledImplement2() {
        System.out.println("Ctor: doubled implement, type 1");
    }

    public DoubledImplement2 getDoubledInstance2() {
        return new DoubledImplement2();
    }

    public void one() {
        System.out.println("DoubledImplement2 one");
    }
}
