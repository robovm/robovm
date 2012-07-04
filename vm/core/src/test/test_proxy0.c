/*
 * Copyright (C) 2012 RoboVM
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
#include <robovm.h>
#include <string.h>
#include "../private.h"
#include "CuTest.h"

int main(int argc, char* argv[]);

void (*handler)(CallInfo*);
void _rvmProxyHandler(CallInfo* ci) {
    handler(ci);
}

void* rvmAllocateMemory(Env* env, int size) {
    return calloc(1, size);
}

static void testProxy0ReturnByte_handler(CallInfo* ci) {
    jbyte b = proxy0NextInt(ci);
    proxy0ReturnInt(ci, b << 1);
}
static void testProxy0ReturnByte(CuTest* tc) {
    handler = testProxy0ReturnByte_handler;
    jbyte (*f)(jbyte) = (jbyte (*)(jbyte)) _proxy0;
    jbyte result = f(16);
    CuAssertIntEquals(tc, 32, result);
}


static void testProxy0ReturnInt_handler(CallInfo* ci) {
    jint i = proxy0NextInt(ci);
    proxy0ReturnInt(ci, i >> 8);
}
static void testProxy0ReturnInt(CuTest* tc) {
    handler = testProxy0ReturnInt_handler;
    jint (*f)(jint) = (jint (*)(jint)) _proxy0;
    jint result = f(0xdeadbeef);
    CuAssertIntEquals(tc, 0xffdeadbe, result);
}


static void testProxy0ReturnPtr_handler(CallInfo* ci) {
    void* p = proxy0NextPtr(ci);
    proxy0ReturnPtr(ci, p + sizeof(void*));
}
static void testProxy0ReturnPtr(CuTest* tc) {
    handler = testProxy0ReturnPtr_handler;
    void* (*f)(void*) = (void* (*)(void*)) _proxy0;
    void* result = f(testProxy0ReturnPtr_handler);
    CuAssertPtrEquals(tc, testProxy0ReturnPtr_handler + sizeof(void*), result);
}


static void testProxy0ReturnLong_handler(CallInfo* ci) {
    jlong j = proxy0NextLong(ci);
    proxy0ReturnLong(ci, j << 8);
}
static void testProxy0ReturnLong(CuTest* tc) {
    handler = testProxy0ReturnLong_handler;
    jlong (*f)(jlong) = (jlong (*)(jlong)) _proxy0;
    jlong result = f(0xdeadbeefLL);
    CuAssertTrue(tc, result == 0xdeadbeef00LL);
}


static void testProxy0ReturnFloat_handler(CallInfo* ci) {
    jfloat f = proxy0NextFloat(ci);
    proxy0ReturnFloat(ci, f * f);
}
static void testProxy0ReturnFloat(CuTest* tc) {
    handler = testProxy0ReturnFloat_handler;
    jfloat (*f)(jfloat) = (jfloat (*)(jfloat)) _proxy0;
    jfloat result = f(3.14f);
    CuAssertTrue(tc, result == 3.14f * 3.14f);
}


static void testProxy0ReturnDouble_handler(CallInfo* ci) {
    jdouble d = proxy0NextDouble(ci);
    proxy0ReturnDouble(ci, d * d);
}
static void testProxy0ReturnDouble(CuTest* tc) {
    handler = testProxy0ReturnDouble_handler;
    jdouble (*f)(jdouble) = (jdouble (*)(jdouble)) _proxy0;
    jdouble result = f(-3.14);
    CuAssertTrue(tc, result == -3.14 * -3.14);
}


void testProxy0OneArgOfEach_handler(CallInfo* ci) {
    proxy0ReturnLong(ci, 0);
    void* p = proxy0NextPtr(ci);
    jint i = proxy0NextInt(ci);
    jlong l = proxy0NextLong(ci);
    jfloat f = proxy0NextFloat(ci);
    jdouble d = proxy0NextDouble(ci);
    if (p != testProxy0OneArgOfEach_handler) return;
    if (i != -100) return;
    if (l != 0xfedcba9876543210LL) return;
    if (f != 3.14f) return;
    if (d != -3.14) return;
    proxy0ReturnLong(ci, 0x0123456789abcdefLL);
}
void testProxy0OneArgOfEach(CuTest* tc) {
    handler = testProxy0OneArgOfEach_handler;
    jlong (*f)(void*, jint, jlong, jfloat, jdouble) = (jlong (*)(void*, jint, jlong, jfloat, jdouble)) _proxy0;
    jlong result = f(testProxy0OneArgOfEach_handler, -100, 0xfedcba9876543210LL, 3.14f, -3.14);
    CuAssertTrue(tc, result == 0x0123456789abcdefLL);
}


void testProxy0ManyArgsOfEach_handler(CallInfo* ci) {
    proxy0ReturnInt(ci, 0);

    void* p1 = proxy0NextPtr(ci);
    jint i1 = proxy0NextInt(ci);
    jlong l1 = proxy0NextLong(ci);
    jfloat f1 = proxy0NextFloat(ci);
    jdouble d1 = proxy0NextDouble(ci);
    void* p2 = proxy0NextPtr(ci);
    jint i2 = proxy0NextInt(ci);
    jlong l2 = proxy0NextLong(ci);
    jfloat f2 = proxy0NextFloat(ci);
    jdouble d2 = proxy0NextDouble(ci);
    void* p3 = proxy0NextPtr(ci);
    jint i3 = proxy0NextInt(ci);
    jlong l3 = proxy0NextLong(ci);
    jfloat f3 = proxy0NextFloat(ci);
    jdouble d3 = proxy0NextDouble(ci);
    void* p4 = proxy0NextPtr(ci);
    jint i4 = proxy0NextInt(ci);
    jlong l4 = proxy0NextLong(ci);
    jfloat f4 = proxy0NextFloat(ci);
    jdouble d4 = proxy0NextDouble(ci);
    void* p5 = proxy0NextPtr(ci);
    jint i5 = proxy0NextInt(ci);
    jlong l5 = proxy0NextLong(ci);
    jfloat f5 = proxy0NextFloat(ci);
    jdouble d5 = proxy0NextDouble(ci);
    void* p6 = proxy0NextPtr(ci);
    jint i6 = proxy0NextInt(ci);
    jlong l6 = proxy0NextLong(ci);
    jfloat f6 = proxy0NextFloat(ci);
    jdouble d6 = proxy0NextDouble(ci);
    void* p7 = proxy0NextPtr(ci);
    jint i7 = proxy0NextInt(ci);
    jlong l7 = proxy0NextLong(ci);
    jfloat f7 = proxy0NextFloat(ci);
    jdouble d7 = proxy0NextDouble(ci);
    void* p8 = proxy0NextPtr(ci);
    jint i8 = proxy0NextInt(ci);
    jlong l8 = proxy0NextLong(ci);
    jfloat f8 = proxy0NextFloat(ci);
    jdouble d8 = proxy0NextDouble(ci);

    if (p1 != testProxy0ManyArgsOfEach_handler + 0xcab1) return;
    if (i1 != -100) return;
    if (l1 != 0xfedcba9876543211LL) return;
    if (f1 != 3.11f) return;
    if (d1 != -3.11) return;
    if (p2 != testProxy0ManyArgsOfEach_handler + 0xcab2) return;
    if (i2 != -200) return;
    if (l2 != 0xfedcba9876543212LL) return;
    if (f2 != 3.12f) return;
    if (d2 != -3.12) return;
    if (p3 != testProxy0ManyArgsOfEach_handler + 0xcab3) return;
    if (i3 != -300) return;
    if (l3 != 0xfedcba9876543213LL) return;
    if (f3 != 3.13f) return;
    if (d3 != -3.13) return;
    if (p4 != testProxy0ManyArgsOfEach_handler + 0xcab4) return;
    if (i4 != -400) return;
    if (l4 != 0xfedcba9876543214LL) return;
    if (f4 != 3.14f) return;
    if (d4 != -3.14) return;
    if (p5 != testProxy0ManyArgsOfEach_handler + 0xcab5) return;
    if (i5 != -500) return;
    if (l5 != 0xfedcba9876543215LL) return;
    if (f5 != 3.15f) return;
    if (d5 != -3.15) return;
    if (p6 != testProxy0ManyArgsOfEach_handler + 0xcab6) return;
    if (i6 != -600) return;
    if (l6 != 0xfedcba9876543216LL) return;
    if (f6 != 3.16f) return;
    if (d6 != -3.16) return;
    if (p7 != testProxy0ManyArgsOfEach_handler + 0xcab7) return;
    if (i7 != -700) return;
    if (l7 != 0xfedcba9876543217LL) return;
    if (f7 != 3.17f) return;
    if (d7 != -3.17) return;
    if (p8 != testProxy0ManyArgsOfEach_handler + 0xcab8) return;
    if (i8 != -800) return;
    if (l8 != 0xfedcba9876543218LL) return;
    if (f8 != 3.18f) return;
    if (d8 != -3.18) return;

    proxy0ReturnInt(ci, 1);
}
void testProxy0ManyArgsOfEach(CuTest* tc) {
    handler = testProxy0ManyArgsOfEach_handler;
    jint (*f)(void*, jint, jlong, jfloat, jdouble, 
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble) = (jint (*)(
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble,
              void*, jint, jlong, jfloat, jdouble)) _proxy0;

    jint result = f(
        testProxy0ManyArgsOfEach_handler + 0xcab1, -100, 0xfedcba9876543211LL, 3.11f, -3.11,
        testProxy0ManyArgsOfEach_handler + 0xcab2, -200, 0xfedcba9876543212LL, 3.12f, -3.12,
        testProxy0ManyArgsOfEach_handler + 0xcab3, -300, 0xfedcba9876543213LL, 3.13f, -3.13,
        testProxy0ManyArgsOfEach_handler + 0xcab4, -400, 0xfedcba9876543214LL, 3.14f, -3.14,
        testProxy0ManyArgsOfEach_handler + 0xcab5, -500, 0xfedcba9876543215LL, 3.15f, -3.15,
        testProxy0ManyArgsOfEach_handler + 0xcab6, -600, 0xfedcba9876543216LL, 3.16f, -3.16,
        testProxy0ManyArgsOfEach_handler + 0xcab7, -700, 0xfedcba9876543217LL, 3.17f, -3.17,
        testProxy0ManyArgsOfEach_handler + 0xcab8, -800, 0xfedcba9876543218LL, 3.18f, -3.18
    );
    CuAssertIntEquals(tc, 1, result);
}


int main(int argc, char* argv[]);
void* findFunctionAt(void* pc);
static jboolean unwindCallStack(UnwindContext* ctx, void* d) {
    jint i;
    void** callers = (void**) d;

    void* address = findFunctionAt(unwindGetIP(ctx));
    for (i = 0; i < 10; i++) {
        if (!callers[i]) {
            callers[i] = address;
            break;
        }
    }

    return (i < 9 && address != main) ? TRUE : FALSE;
}
void testProxy0Unwind_handler(CallInfo* ci) {
    void** ptrs = proxy0NextPtr(ci);
    unwindBacktrace(NULL, unwindCallStack, ptrs);
}
void testProxy0Unwind(CuTest* tc) {
    handler = testProxy0Unwind_handler;
    void* callers[10] = {0};
    void (*f)(void*) = (void (*)(void*)) _proxy0;
    f(callers);
    CuAssertPtrEquals(tc, testProxy0Unwind_handler, callers[0]);
    CuAssertPtrEquals(tc, _rvmProxyHandler, callers[1]);
    CuAssertPtrEquals(tc, _proxy0, callers[2]);
    CuAssertPtrEquals(tc, testProxy0Unwind, callers[3]);
    CuAssertPtrEquals(tc, CuTestRun, callers[4]);
    CuAssertPtrEquals(tc, CuSuiteRun, callers[5]);
    CuAssertPtrEquals(tc, main, callers[6]);
    CuAssertPtrEquals(tc, NULL, callers[7]);
}
void* findFunctionAt(void* pc) {
    void* candidates[7] = {0};
    candidates[0] = testProxy0Unwind_handler;
    candidates[1] = _rvmProxyHandler;
    candidates[2] = _proxy0;
    candidates[3] = testProxy0Unwind;
    candidates[4] = CuTestRun;
    candidates[5] = CuSuiteRun;
    candidates[6] = main;

    void* match = NULL;
    jint delta = 0x7fffffff;
    jint i;
    for (i = 0; i < 7; i++) {
        if (candidates[i] < pc && pc - candidates[i] < delta) {
            match = candidates[i];
            delta = pc - candidates[i];
        }
    }
    return (match && delta < 1000) ? match : pc;
}


int main(int argc, char* argv[]) {
    CuSuite* suite = CuSuiteNew();

    if (argc < 2 || !strcmp(argv[1], "testProxy0ReturnByte")) SUITE_ADD_TEST(suite, testProxy0ReturnByte);
    if (argc < 2 || !strcmp(argv[1], "testProxy0ReturnInt")) SUITE_ADD_TEST(suite, testProxy0ReturnInt);
    if (argc < 2 || !strcmp(argv[1], "testProxy0ReturnPtr")) SUITE_ADD_TEST(suite, testProxy0ReturnPtr);
    if (argc < 2 || !strcmp(argv[1], "testProxy0ReturnLong")) SUITE_ADD_TEST(suite, testProxy0ReturnLong);
    if (argc < 2 || !strcmp(argv[1], "testProxy0ReturnFloat")) SUITE_ADD_TEST(suite, testProxy0ReturnFloat);
    if (argc < 2 || !strcmp(argv[1], "testProxy0ReturnDouble")) SUITE_ADD_TEST(suite, testProxy0ReturnDouble);
    if (argc < 2 || !strcmp(argv[1], "testProxy0OneArgOfEach")) SUITE_ADD_TEST(suite, testProxy0OneArgOfEach);
    if (argc < 2 || !strcmp(argv[1], "testProxy0ManyArgsOfEach")) SUITE_ADD_TEST(suite, testProxy0ManyArgsOfEach);
    if (argc < 2 || !strcmp(argv[1], "testProxy0Unwind")) SUITE_ADD_TEST(suite, testProxy0Unwind);

    CuSuiteRun(suite);

    if (argc < 2) {
        CuString *output = CuStringNew();
        CuSuiteSummary(suite, output);
        CuSuiteDetails(suite, output);
        printf("%s\n", output->buffer);
    }

    return suite->failCount;
}

