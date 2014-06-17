/*
 * Copyright (C) 2013 The Android Open Source Project
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

public class Main {
  public static void main(String args[]) throws Exception {
    System.err.println(new C().m());
  }
}

// An arbitrary interface.
interface I { public String m(); }

// This is I-like, but doesn't actually claim to implement I.
abstract class Abstract { protected abstract String m(); }

// This claims to implement I, but the inherited m isn't sufficiently visible.
abstract class AbstractI extends Abstract implements I { }

// This has a concrete m that's sufficiently visible, so all should be good.
class C extends AbstractI { public String m() { return "passed"; }; }
