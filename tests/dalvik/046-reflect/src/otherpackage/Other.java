/*
 * Copyright (C) 2012 The Android Open Source Project
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

package otherpackage;

public class Other {
    public void publicMethod() {
        System.out.println("public method");
    }
    void packageMethod() {
        System.out.println("package method");
    }

    public static InnerOther getInnerClassInstance() {
        return new InnerOther();
    }

    private static class InnerOther {
        public void innerMethod() {
            System.out.println("inner method");
        }

        public int innerField = 7;
    }
}
