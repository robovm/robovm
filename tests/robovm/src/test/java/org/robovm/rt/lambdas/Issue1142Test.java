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
package org.robovm.rt.lambdas;

import static org.junit.Assert.*;

import org.junit.Test;

public class Issue1142Test {
    public interface Consumer<T> {
        void accept(T t);
    }

    public interface IntConsumer {
        void accept(int t);
    }

    private static void foo(IntConsumer action) {
        action.accept(10);
    }

    private static void foo(Consumer<? super Integer> action) {
        foo((IntConsumer) action::accept);
    }

    @Test
    public void testIssue1142() {
        final int[] res = {0};
        foo((Integer i) -> res[0] = i);
        assertEquals(10, res[0]);
    }
}
