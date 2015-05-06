/*
 * Copyright (C) 2015 RoboVM AB
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

import java.util.concurrent.Callable;

import org.junit.Test;

/**
 * Tests for lambdas.
 */
public class LambdasTest {

    @Test
    public void testSimple() throws Exception {
        assertEquals("foo", runCallable(() -> "foo"));
    }

    @Test
    public void testAccessPrivateInstanceMethod() throws Exception {
        assertEquals("foo", runCallable(() -> privateInstanceMethod(() -> "foo")));
    }

    @Test
    public void testAccessPrivateInstanceMethodReference() throws Exception {
        assertEquals("foo", runCallable(this::privateInstanceMethod));
    }

    @Test
    public void testAccessPrivateClassMethod() throws Exception {
        assertEquals("foo", runCallable(() -> privateClassMethod(() -> "foo")));
    }

    @Test
    public void testAccessPrivateClassMethodReference() throws Exception {
        assertEquals("foo", runCallable(LambdasTest::privateClassMethod));
    }

    @Test
    public void testAccessVarInSurroundingScope() throws Exception {
        StringBuilder sb = new StringBuilder();
        runRunnable(() -> sb.append("foo"));
        runRunnable(() -> sb.append("bar"));
        assertEquals("foobar", sb.toString());
    }

    private void runRunnable(Runnable r) throws Exception {
        r.run();
    }

    private <T> T runCallable(Callable<T> callable) throws Exception {
        return callable.call();
    }

    private <T> T privateInstanceMethod(Callable<T> callable) throws Exception {
        return callable.call();
    }

    private static <T> T privateClassMethod(Callable<T> callable) throws Exception {
        return callable.call();
    }

    private String privateInstanceMethod() throws Exception {
        return "foo";
    }

    private static String privateClassMethod() throws Exception {
        return "foo";
    }
}
