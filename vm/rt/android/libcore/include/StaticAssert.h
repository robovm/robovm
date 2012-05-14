/*
 * Copyright (C) 2010 The Android Open Source Project
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

#ifndef STATIC_ASSERT_H_included
#define STATIC_ASSERT_H_included

/**
 * Similar to C++0x's static_assert. Message argument must be a valid identifier, not a string.
 * Called COMPILE_ASSERT in Google, COMPILE_TIME_ASSERT in other places. This is the usual Google
 * implementation.
 */
#define STATIC_ASSERT(exp, msg) typedef StaticAssert<(bool(exp))> msg[bool(exp) ? 1 : -1]
template <bool> struct StaticAssert {};

#endif  // STATIC_ASSERT_H_included
