/*
 * Copyright (C) 2008 The Android Open Source Project
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

package java.util.concurrent.locks;

import sun.misc.Unsafe;

/**
 * Easy access to {@link Unsafe} for the rest of this package.
 */
/*package*/ final class UnsafeAccess {
    /** non-null; unique instance of {@link Unsafe} */
    /*package*/ static final Unsafe THE_ONE = Unsafe.getUnsafe();

    /**
     * This class is uninstantiable.
     */
    private UnsafeAccess() {
        // This space intentionally left blank.
    }
}
