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
 * Test java.lang.reflect.Proxy
 */
public class Main {
    public static void main(String[] args) {
        BasicTest.main(null);
        Clash.main(null);
        Clash2.main(null);
        Clash3.main(null);
        Clash4.main(null);
        WrappedThrow.main(null);
    }
}
