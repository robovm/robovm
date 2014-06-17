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

package libcore.icu;

/**
 * Exposes icu4c's Transliterator.
 */
public final class Transliterator {
  private long peer;

  /**
   * Creates a new Transliterator for the given id.
   */
  public Transliterator(String id) {
    peer = create(id);
  }

  @Override protected synchronized void finalize() throws Throwable {
    try {
      destroy(peer);
      peer = 0;
    } finally {
      super.finalize();
    }
  }

  /**
   * Returns the ids of all known transliterators.
   */
  public static native String[] getAvailableIDs();

  /**
   * Transliterates the specified string.
   */
  public String transliterate(String s) {
    return transliterate(peer, s);
  }

  private static native long create(String id);
  private static native void destroy(long peer);
  private static native String transliterate(long peer, String s);
}
