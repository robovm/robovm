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

import java.util.Random;

/**
 * Tests long division by zero for both / and %.
 */
public class ZeroTests {
    public void longDivTest() throws Exception {
      longTest("longDivTest", true);
    }

    public void longModTest() throws Exception {
      longTest("longModTest", false);
    }

    private static void longTest(String name, boolean divide) throws Exception {
      // Warm up JIT.
      for (int i = 0; i < 10000; ++i) {
        try {
          // We won't JIT code that hasn't completed successfully, so rhs can't be 0 here!
          if (divide) {
            longDiv(1, 1);
          } else {
            longMod(1, 1);
          }
        } catch (ArithmeticException expected) {
          throw new AssertionError(name + " threw during warmup");
        }
      }

      // Boom?
      int catchCount = 0;
      for (int i = 0; i < 10000; ++i) {
        try {
          if (divide) {
            longDiv(1, 0);
          } else {
            longMod(1, 0);
          }
          throw new AssertionError(name + " failed to throw: " + i);
        } catch (ArithmeticException expected) {
          ++catchCount;
        }
      }
      if (catchCount != 10000) throw new AssertionError(name + " failed: " + catchCount);

      System.out.println(name + " passes");
    }

    private static long longDiv(long lhs, long rhs) {
      return lhs / rhs;
    }

    private static long longMod(long lhs, long rhs) {
      return lhs % rhs;
    }
}
