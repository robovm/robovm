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

package org.robovm.rt.lambdas.test013;

/**
 * Lambda specifying parameter types.
 */
interface I {
   int addValues(int v1, float v2, short v3, byte v4);
}

public class Lambda {

  public int addValues() {
    I i = (int v1, float v2, short v3, byte v4) -> {
      return v1 + (int) v2 + v3 + v4;
    };
    return i.addValues(1, 2, (short) 3, (byte) 4);
  }
}
