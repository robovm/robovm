/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package libcore.java.lang.ref;

/**
 * Triggers finalizers to run. Note that live-precise bugs may interfere with
 * analysis of what is reachable. Unreachable references in local frames may not
 * be finalized even after a call to {@link #induceFinalization}. To work
 * around, create finalizable objects in helper methods. http://b/4191345
 */
public final class FinalizationTester {
    private FinalizationTester() {}

    public static void induceFinalization() {
        System.gc();
        enqueueReferences();
        System.runFinalization();
    }

    public static void enqueueReferences() {
        /*
         * Hack. We don't have a programmatic way to wait for the reference queue
         * daemon to move references to the appropriate queues.
         */
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            throw new AssertionError();
        }
    }
}
