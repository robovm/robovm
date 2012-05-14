/*
 * Copyright (C) 2011 The Android Open Source Project
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

#include "JniConstants.h"
#include "toStringArray.h"

#include <string>
#include <vector>

struct VectorCounter {
    const std::vector<std::string>& strings;
    VectorCounter(const std::vector<std::string>& strings) : strings(strings) {}
    size_t operator()() {
        return strings.size();
    }
};
struct VectorGetter {
    const std::vector<std::string>& strings;
    VectorGetter(const std::vector<std::string>& strings) : strings(strings) {}
    const char* operator()(size_t i) {
        return strings[i].c_str();
    }
};

jobjectArray toStringArray(JNIEnv* env, const std::vector<std::string>& strings) {
    VectorCounter counter(strings);
    VectorGetter getter(strings);
    return toStringArray<VectorCounter, VectorGetter>(env, &counter, &getter);
}

struct ArrayCounter {
    const char* const* strings;
    ArrayCounter(const char* const* strings) : strings(strings) {}
    size_t operator()() {
        size_t count = 0;
        while (strings[count] != NULL) {
            ++count;
        }
        return count;
    }
};

struct ArrayGetter {
    const char* const* strings;
    ArrayGetter(const char* const* strings) : strings(strings) {}
    const char* operator()(size_t i) {
        return strings[i];
    }
};

jobjectArray toStringArray(JNIEnv* env, const char* const* strings) {
    ArrayCounter counter(strings);
    ArrayGetter getter(strings);
    return toStringArray(env, &counter, &getter);
}
