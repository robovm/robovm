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

package org.robovm.rt.lambdas.test018;

/**
 * Test target typing with overloading methods.
 */

interface I1 {
  void m();
}

interface I2 {
  String m();
}

interface I3 {
  int m();
}

class C {

  int idMethod;

  void call(I1 i1) {
    idMethod = 1;
    i1.m();
  }

  String call(I2 i2) {
    idMethod = 2;
    return i2.m();
  }

  int call(I3 i3) {
    idMethod = 3;
    return i3.m();
  }

}

public class Lambda {

  public String test1() {
     C c = new C();
     c.call(() -> {int i = 0;});
     return c.idMethod + "-void";
  }

  public String test2() {
    C c = new C();
    String s = c.call(() -> "test");
    return c.idMethod + "-" + s;
 }

 public String test3() {
    C c = new C();
    int r = c.call(() -> "test".length());
    return c.idMethod + "-" + r;
 }

}
