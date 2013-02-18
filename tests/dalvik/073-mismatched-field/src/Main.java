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

public class Main extends SuperMain implements IMain {
    public static void main(String[] args) {
        Main main = new Main();
        main.doit();
    }

    void doit() {
        try {
            System.out.println("value=" + this.f);
            System.err.println("Succeeded unexpectedly");
        } catch (IncompatibleClassChangeError icce) {
            System.out.println("Got expected failure");
        }
    }
}
