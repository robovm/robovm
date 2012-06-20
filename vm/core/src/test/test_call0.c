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

void* rvmAllocateMemory(Env* env, int size) {
    return calloc(1, size);
}

static jbyte testCall0ReturnByte_target(jbyte b) {
    return b << 1;
}
static void testCall0ReturnByte(CuTest* tc) {
    CallInfo* ci = call0AllocateCallInfo(NULL, testCall0ReturnByte_target, 0, 1, 0, 0, 0);
    CuAssertPtrNotNull(tc, ci);
    call0AddInt(ci, 16);
    jbyte (*f)(CallInfo*) = (jbyte (*)(CallInfo*)) _call0;
    jbyte result = f(ci);
    CuAssertIntEquals(tc, 32, result);
}


static jint testCall0ReturnInt_target(jint i) {
    return i >> 8;
}
static void testCall0ReturnInt(CuTest* tc) {
    CallInfo* ci = call0AllocateCallInfo(NULL, testCall0ReturnInt_target, 0, 1, 0, 0, 0);
    CuAssertPtrNotNull(tc, ci);
    call0AddInt(ci, 0xdeadbeef);
    jint (*f)(CallInfo*) = (jint (*)(CallInfo*)) _call0;
    jint result = f(ci);
    CuAssertIntEquals(tc, 0xffdeadbe, result);
}


static void* testCall0ReturnPtr_target(void* p) {
    return p + sizeof(void*);
}
static void testCall0ReturnPtr(CuTest* tc) {
    CallInfo* ci = call0AllocateCallInfo(NULL, testCall0ReturnPtr_target, 1, 0, 0, 0, 0);
    CuAssertPtrNotNull(tc, ci);
    call0AddPtr(ci, testCall0ReturnPtr_target);
    void* (*f)(CallInfo*) = (void* (*)(CallInfo*)) _call0;
    void* result = f(ci);
    CuAssertPtrEquals(tc, testCall0ReturnPtr_target + sizeof(void*), result);
}


static jlong testCall0ReturnLong_target(jlong j) {
    return j << 8;
}
static void testCall0ReturnLong(CuTest* tc) {
    CallInfo* ci = call0AllocateCallInfo(NULL, testCall0ReturnLong_target, 0, 0, 1, 0, 0);
    CuAssertPtrNotNull(tc, ci);
    call0AddLong(ci, 0xdeadbeef);
    jlong (*f)(CallInfo*) = (jlong (*)(CallInfo*)) _call0;
    jlong result = f(ci);
    CuAssertTrue(tc, result == 0xdeadbeef00LL);
}


static jfloat testCall0ReturnFloat_target(jfloat f) {
    return f * f;
}
static void testCall0ReturnFloat(CuTest* tc) {
    CallInfo* ci = call0AllocateCallInfo(NULL, testCall0ReturnFloat_target, 0, 0, 0, 1, 0);
    CuAssertPtrNotNull(tc, ci);
    call0AddFloat(ci, 3.14f);
    jfloat (*f)(CallInfo*) = (jfloat (*)(CallInfo*)) _call0;
    jfloat result = f(ci);
    CuAssertTrue(tc, result == 3.14f * 3.14f);
}


static jdouble testCall0ReturnDouble_target(jdouble d) {
    return d * d;
}
static void testCall0ReturnDouble(CuTest* tc) {
    CallInfo* ci = call0AllocateCallInfo(NULL, testCall0ReturnDouble_target, 0, 0, 0, 0, 1);
    CuAssertPtrNotNull(tc, ci);
    call0AddDouble(ci, -3.14);
    jdouble (*f)(CallInfo*) = (jdouble (*)(CallInfo*)) _call0;
    jdouble result = f(ci);
    CuAssertTrue(tc, result == -3.14 * -3.14);
}


static jlong testCall0OneArgOfEach_target(void* p, jint i, jlong l, jfloat f, jdouble d) {
    if (p != testCall0OneArgOfEach_target) return 1;
    if (i != -100) return 2;
    if (l != 0xfedcba9876543210LL) return 3;
    if (f != 3.14f) return 4;
    if (d != -3.14) return 5;
    return 0x0123456789abcdefLL;
}
static void testCall0OneArgOfEach(CuTest* tc) {
    CallInfo* ci = call0AllocateCallInfo(NULL, testCall0OneArgOfEach_target, 1, 1, 1, 1, 1);
    CuAssertPtrNotNull(tc, ci);
    call0AddPtr(ci, testCall0OneArgOfEach_target);
    call0AddInt(ci, -100);
    call0AddLong(ci, 0xfedcba9876543210LL);
    call0AddFloat(ci, 3.14f);
    call0AddDouble(ci, -3.14);
    jlong (*f)(CallInfo*) = (jlong (*)(CallInfo*)) _call0;
    jlong result = f(ci);
    CuAssertTrue(tc, result == 0x0123456789abcdefLL);
}


static jint testCall0ManyArgsOfEach_target(
      void* p1, jint i1, jlong l1, jfloat f1, jdouble d1,
      void* p2, jint i2, jlong l2, jfloat f2, jdouble d2,
      void* p3, jint i3, jlong l3, jfloat f3, jdouble d3,
      void* p4, jint i4, jlong l4, jfloat f4, jdouble d4,
      void* p5, jint i5, jlong l5, jfloat f5, jdouble d5,
      void* p6, jint i6, jlong l6, jfloat f6, jdouble d6,
      void* p7, jint i7, jlong l7, jfloat f7, jdouble d7,
      void* p8, jint i8, jlong l8, jfloat f8, jdouble d8) {

    if (p1 != testCall0ManyArgsOfEach_target + 0xcab1) return 0;
    if (i1 != -100) return 0;
    if (l1 != 0xfedcba9876543211LL) return 0;
    if (f1 != 3.11f) return 0;
    if (d1 != -3.11) return 0;
    if (p2 != testCall0ManyArgsOfEach_target + 0xcab2) return 0;
    if (i2 != -200) return 0;
    if (l2 != 0xfedcba9876543212LL) return 0;
    if (f2 != 3.12f) return 0;
    if (d2 != -3.12) return 0;
    if (p3 != testCall0ManyArgsOfEach_target + 0xcab3) return 0;
    if (i3 != -300) return 0;
    if (l3 != 0xfedcba9876543213LL) return 0;
    if (f3 != 3.13f) return 0;
    if (d3 != -3.13) return 0;
    if (p4 != testCall0ManyArgsOfEach_target + 0xcab4) return 0;
    if (i4 != -400) return 0;
    if (l4 != 0xfedcba9876543214LL) return 0;
    if (f4 != 3.14f) return 0;
    if (d4 != -3.14) return 0;
    if (p5 != testCall0ManyArgsOfEach_target + 0xcab5) return 0;
    if (i5 != -500) return 0;
    if (l5 != 0xfedcba9876543215LL) return 0;
    if (f5 != 3.15f) return 0;
    if (d5 != -3.15) return 0;
    if (p6 != testCall0ManyArgsOfEach_target + 0xcab6) return 0;
    if (i6 != -600) return 0;
    if (l6 != 0xfedcba9876543216LL) return 0;
    if (f6 != 3.16f) return 0;
    if (d6 != -3.16) return 0;
    if (p7 != testCall0ManyArgsOfEach_target + 0xcab7) return 0;
    if (i7 != -700) return 0;
    if (l7 != 0xfedcba9876543217LL) return 0;
    if (f7 != 3.17f) return 0;
    if (d7 != -3.17) return 0;
    if (p8 != testCall0ManyArgsOfEach_target + 0xcab8) return 0;
    if (i8 != -800) return 0;
    if (l8 != 0xfedcba9876543218LL) return 0;
    if (f8 != 3.18f) return 0;
    if (d8 != -3.18) return 0;

    return 1;
}
static void testCall0ManyArgsOfEach(CuTest* tc) {
    CallInfo* ci = call0AllocateCallInfo(NULL, testCall0ManyArgsOfEach_target, 8, 8, 8, 8, 8);
    CuAssertPtrNotNull(tc, ci);
    call0AddPtr(ci, testCall0ManyArgsOfEach_target + 0xcab1);
    call0AddInt(ci, -100);
    call0AddLong(ci, 0xfedcba9876543211LL);
    call0AddFloat(ci, 3.11f);
    call0AddDouble(ci, -3.11);
    call0AddPtr(ci, testCall0ManyArgsOfEach_target + 0xcab2);
    call0AddInt(ci, -200);
    call0AddLong(ci, 0xfedcba9876543212LL);
    call0AddFloat(ci, 3.12f);
    call0AddDouble(ci, -3.12);
    call0AddPtr(ci, testCall0ManyArgsOfEach_target + 0xcab3);
    call0AddInt(ci, -300);
    call0AddLong(ci, 0xfedcba9876543213LL);
    call0AddFloat(ci, 3.13f);
    call0AddDouble(ci, -3.13);
    call0AddPtr(ci, testCall0ManyArgsOfEach_target + 0xcab4);
    call0AddInt(ci, -400);
    call0AddLong(ci, 0xfedcba9876543214LL);
    call0AddFloat(ci, 3.14f);
    call0AddDouble(ci, -3.14);
    call0AddPtr(ci, testCall0ManyArgsOfEach_target + 0xcab5);
    call0AddInt(ci, -500);
    call0AddLong(ci, 0xfedcba9876543215LL);
    call0AddFloat(ci, 3.15f);
    call0AddDouble(ci, -3.15);
    call0AddPtr(ci, testCall0ManyArgsOfEach_target + 0xcab6);
    call0AddInt(ci, -600);
    call0AddLong(ci, 0xfedcba9876543216LL);
    call0AddFloat(ci, 3.16f);
    call0AddDouble(ci, -3.16);
    call0AddPtr(ci, testCall0ManyArgsOfEach_target + 0xcab7);
    call0AddInt(ci, -700);
    call0AddLong(ci, 0xfedcba9876543217LL);
    call0AddFloat(ci, 3.17f);
    call0AddDouble(ci, -3.17);
    call0AddPtr(ci, testCall0ManyArgsOfEach_target + 0xcab8);
    call0AddInt(ci, -800);
    call0AddLong(ci, 0xfedcba9876543218LL);
    call0AddFloat(ci, 3.18f);
    call0AddDouble(ci, -3.18);

    jint (*f)(CallInfo*) = (jint (*)(CallInfo*)) _call0;
    jint result = f(ci);
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
void testCall0Unwind_target(void** ptrs) {
    unwindBacktrace(unwindCallStack, ptrs);
}
void testCall0Unwind(CuTest* tc) {
    void* callers[10] = {0};
    CallInfo* ci = call0AllocateCallInfo(NULL, testCall0Unwind_target, 1, 0, 0, 0, 0);
    CuAssertPtrNotNull(tc, ci);
    call0AddPtr(ci, callers);
    void (*f)(CallInfo*) = (void (*)(CallInfo*)) _call0;
    f(ci);
    CuAssertPtrEquals(tc, testCall0Unwind_target, callers[0]);
    CuAssertPtrEquals(tc, _call0, callers[1]);
    CuAssertPtrEquals(tc, testCall0Unwind, callers[2]);
    CuAssertPtrEquals(tc, CuTestRun, callers[3]);
    CuAssertPtrEquals(tc, CuSuiteRun, callers[4]);
    CuAssertPtrEquals(tc, main, callers[5]);
    CuAssertPtrEquals(tc, NULL, callers[6]);
}
void* findFunctionAt(void* pc) {
    void* candidates[6] = {0};
    candidates[0] = testCall0Unwind_target;
    candidates[1] = _call0;
    candidates[2] = testCall0Unwind;
    candidates[3] = CuTestRun;
    candidates[4] = CuSuiteRun;
    candidates[5] = main;

    void* match = NULL;
    jint delta = 0x7fffffff;
    jint i;
    for (i = 0; i < 6; i++) {
        if (candidates[i] < pc && pc - candidates[i] < delta) {
            match = candidates[i];
            delta = pc - candidates[i];
        }
    }
    return (match && delta < 2000) ? match : pc;
}


int main(int argc, char* argv[]) {
    CuSuite* suite = CuSuiteNew();

    if (argc < 2 || !strcmp(argv[1], "testCall0ReturnByte")) SUITE_ADD_TEST(suite, testCall0ReturnByte);
    if (argc < 2 || !strcmp(argv[1], "testCall0ReturnInt")) SUITE_ADD_TEST(suite, testCall0ReturnInt);
    if (argc < 2 || !strcmp(argv[1], "testCall0ReturnPtr")) SUITE_ADD_TEST(suite, testCall0ReturnPtr);
    if (argc < 2 || !strcmp(argv[1], "testCall0ReturnLong")) SUITE_ADD_TEST(suite, testCall0ReturnLong);
    if (argc < 2 || !strcmp(argv[1], "testCall0ReturnFloat")) SUITE_ADD_TEST(suite, testCall0ReturnFloat);
    if (argc < 2 || !strcmp(argv[1], "testCall0ReturnDouble")) SUITE_ADD_TEST(suite, testCall0ReturnDouble);
    if (argc < 2 || !strcmp(argv[1], "testCall0OneArgOfEach")) SUITE_ADD_TEST(suite, testCall0OneArgOfEach);
    if (argc < 2 || !strcmp(argv[1], "testCall0ManyArgsOfEach")) SUITE_ADD_TEST(suite, testCall0ManyArgsOfEach);
    if (argc < 2 || !strcmp(argv[1], "testCall0Unwind")) SUITE_ADD_TEST(suite, testCall0Unwind);

    CuSuiteRun(suite);

    if (argc < 2) {
        CuString *output = CuStringNew();
        CuSuiteSummary(suite, output);
        CuSuiteDetails(suite, output);
        printf("%s\n", output->buffer);
    }

    return suite->failCount;
}

