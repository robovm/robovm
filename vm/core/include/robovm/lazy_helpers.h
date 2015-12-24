/*
 * Copyright (C) 2015 RoboVM AB
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
#ifndef ROBOVM_LAZY_HELPERS_H
#define ROBOVM_LAZY_HELPERS_H

#define LAZY_CLASS(function, name) \
    static inline Class* function(Env* env) { \
        static Class* c = NULL; \
        if (!c) { \
            c = rvmFindClassUsingLoader(env, name, NULL); \
            assert(c != NULL); \
        } \
        return c; \
    }

#define LAZY_INSTANCE_METHOD(function, lazy_class_function, name, desc) \
    static inline Method* function(Env* env) { \
        static Method* m = NULL; \
        if (!m) { \
            m = rvmGetInstanceMethod(env, lazy_class_function(env), name, desc); \
            assert(m != NULL); \
        } \
        return m; \
    }

#define LAZY_INSTANCE_FIELD(function, lazy_class_function, name, desc) \
    static inline InstanceField* function(Env* env) { \
        static InstanceField* f = NULL; \
        if (!f) { \
            f = rvmGetInstanceField(env, lazy_class_function(env), name, desc); \
            assert(f != NULL); \
        } \
        return f; \
    }

#endif
