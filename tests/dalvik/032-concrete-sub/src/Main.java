/*
 * Copyright (C) 2007 The Android Open Source Project
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
 * Test insertion of an abstract method in a superclass.
 */
public class Main {
    public static void main(String[] args) {
        ConcreteSub.main();

        try {
            // Dalvik verifier stops here (VerifyError)
            ConcreteSub2 blah = new ConcreteSub2();
            // other VMs fail here (AbstractMethodError)
            blah.doStuff();
            System.err.println("Succeeded unexpectedly");
        } catch (VerifyError ve) {
            System.out.println("Got expected failure");
        } catch (AbstractMethodError ame) {
            System.out.println("Got expected failure");
        }
    }
}
