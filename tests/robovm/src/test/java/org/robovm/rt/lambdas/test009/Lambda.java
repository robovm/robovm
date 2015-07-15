/*
 * Copyright (C) 2015 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the License);
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an AS IS BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.robovm.rt.lambdas.test009;

/**
 * Test using exception with lambda.
 */
public class Lambda {

  public int testAddInt(int v1, int v2) {
    I i = (a, b) -> a + b;
    try {
      return i.add(v1, v2);
    } catch (Test009Exception e) {
      return 1;
    }
  }

  public int testCatchingException() {
    I i = (a, b) -> { throw new Test009Exception(); };
    try {
      return i.add(0, 0);
    } catch (Test009Exception e) {
      return 1;
    }
  }

  public int testThrowingException() throws Test009Exception {
    I i = (a, b) -> { throw new Test009Exception(); };
    return i.add(0, 0);
  }
}