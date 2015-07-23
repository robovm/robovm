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

package org.robovm.rt.lambdas.test006;

/**
 * Test that mixed lambda with non lambda expression of the same type.
 */
public class Lambda {

  public class A implements I<Double> {
    @Override
    public int add(Double val1, Double val2) {
      return (int) (val1.doubleValue() + val2.doubleValue());
    }
  }

  public int testAddDouble(double d1, double d2) {
    Double D1 = new Double(d1);
    Double D2 = new Double(d2);
    I<Number> i = (a, b) -> a.intValue() + b.intValue() + 1;
    I<Double> a = new A();
    return i.add(D1, D2) + a.add(D1, D2);
  }
}