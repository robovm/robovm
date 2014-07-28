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

import static org.junit.Assert.*;

import org.junit.Test;
import org.robovm.rt.Signals.InstallSignalsCallback;
import org.robovm.rt.bro.Bro;
import org.robovm.rt.bro.annotation.Bridge;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.Library;
import org.robovm.rt.bro.annotation.Pointer;

/**
 * Tests {@link Signals}.
 */
@Library("c")
public class SignalsTest {
    
    static {
        Bro.bind(SignalsTest.class);
    }
    
    private static final int SIGBUS = 10;
    private static final int SIGSEGV = 11;

    private static Object nullObj;
    private static long oldHandlerSigbus;
    private static long oldHandlerSigsegv;

    private void triggerSOE() {
        triggerSOE();
    }
    
    @Bridge native static int sigaction(int sig, @Pointer long act, @Pointer long oact);
    @Bridge native static @Pointer long signal(int sig, @Pointer long func);
    @Bridge native static int raise(int sig);
    
    @Callback static void handler(int signum, @Pointer long info, @Pointer long context) {
        // Dummy
    }
    
    private static long currentHandler(int sig) {
        long h = signal(sig, 0);
        signal(sig, h);
        return h;
    }
    
    @Test
    public void testInstallSignals() throws Exception {
        // This is hard to test properly without generating a fatal signal and
        // kill the process. We just make sure that NPE/SOE works fine after
        // installSignals() has been called and that the handlers aren't the
        // original non-chaining once and aren't handler().
        
        final long handlerPtr = VM.getCallbackMethodImpl(SignalsTest.class.getDeclaredMethod("handler", int.class, long.class, long.class));
        long oldSigactionSigbus = VM.malloc(512);
        long oldSigactionSigsegv = VM.malloc(512);
        try {
            if (sigaction(SIGBUS, 0, oldSigactionSigbus) != 0) {
                fail("sigaction");
            }
            if (sigaction(SIGSEGV, 0, oldSigactionSigsegv) != 0) {
                fail("sigaction");
            }
            
            try {
                Signals.installSignals(new InstallSignalsCallback() {
                    @Override
                    public void install() {
                        if (Bro.IS_DARWIN) {
                            oldHandlerSigbus = signal(SIGBUS, handlerPtr);
                        }
                        oldHandlerSigsegv = signal(SIGSEGV, handlerPtr);
                    }
                });
                // Make sure NPEs and SOEs still work properly
                try {
                    nullObj.hashCode();
                    fail("NullPointerException expected");
                } catch (NullPointerException e) {
                }
                try {
                    triggerSOE();
                    fail("StackOverflowError expected");
                } catch (StackOverflowError e) {
                }
                
                if (Bro.IS_DARWIN) {
                    assertNotEquals(handlerPtr, currentHandler(SIGBUS));
                    assertNotEquals(oldHandlerSigbus, currentHandler(SIGBUS));
                }
                assertNotEquals(handlerPtr, currentHandler(SIGSEGV));
                assertNotEquals(oldHandlerSigsegv, currentHandler(SIGSEGV));
                
                try {
                    Signals.installSignals(new InstallSignalsCallback() {
                        @Override
                        public void install() {
                        }
                    });
                    fail("IllegalStateException expected");
                } catch (IllegalStateException e) {
                }
            } finally {
                sigaction(SIGBUS, oldSigactionSigbus, 0);
                sigaction(SIGSEGV, oldSigactionSigsegv, 0);
            }
            
        } finally {
            VM.free(oldSigactionSigbus);
            VM.free(oldSigactionSigsegv);
        }
    }

}
