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

public class Main {
    static public void main(String[] args) {
        try {
            PublicAccess.main();
            System.err.println("ERROR: call 1 not expected to succeed");
        } catch (VerifyError ve) {
            // dalvik
            System.out.println("Got expected failure 1");
        } catch (IllegalAccessError iae) {
            // reference
            System.out.println("Got expected failure 1");
        }

        try {
            CheckInstanceof.main(new Object());
            System.err.println("ERROR: call 2 not expected to succeed");
        } catch (VerifyError ve) {
            // dalvik
            System.out.println("Got expected failure 2");
        } catch (IllegalAccessError iae) {
            // reference
            System.out.println("Got expected failure 2");
        }
    }
}
