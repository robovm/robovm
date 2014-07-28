/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.rt;

/**
 * Provides a means for installing custom signal handlers without overriding
 * the signal handlers needed by RoboVM to handle {@link NullPointerException}s
 * and {@link StackOverflowError}s properly. This should be used when using 
 * crash reporting services like TestFlight, Flurry or HockeyApp. The 
 * initialization method for the crash reporting service SDK should be called 
 * inside an  {@link InstallSignalsCallback} using the 
 * {@link #installSignals(InstallSignalsCallback)} method.
 * <p>
 * NOTE! This should be called as early as possible in the app from the main 
 * thread and MUST be called before any other threads have been created.
 */
public class Signals {

    private static boolean called = false;
    
    /**
     * Encapsulates the user code which typically initializes a crash report
     * service SDK or in some other way modifies signal handlers.
     */
    public interface InstallSignalsCallback {
        /**
         * Called by {@link Signals#installSignals(InstallSignalsCallback)}
         * to install the custom signal handlers.
         */
        void install();
    }
    
    /**
     * Saves the current signal handlers essential for RoboVM to work correctly
     * then calls the specified {@link InstallSignalsCallback} and 
     * finally restores the saved signals chaining them to installed ones
     * properly.
     */
    public static void installSignals(InstallSignalsCallback callback) {
        if (callback == null) {
            throw new NullPointerException("callback");
        }
        if (called) {
            throw new IllegalStateException(Signals.class.getSimpleName() 
                    + ".installSignals() called twice");
        }
        long state = saveSignals();
        try {
            callback.install();
        } finally {
            called = true;
            installChainingSignals();
            reinstallSavedSignals(state);
        }
    }

    private native static void installChainingSignals();
    private native static void reinstallSavedSignals(long state);
    private native static long saveSignals();
}
