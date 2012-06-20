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
#include "CuTest.h"

void testTrycatchEnterLeaveOnce(CuTest* tc) {
    Env env = {0};
    TrycatchContext ctx = {0};
    ctx.sel = 1;
    rvmTrycatchEnter(&env, &ctx);
    CuAssertPtrEquals(tc, &ctx, env.trycatchContext);
    CuAssertIntEquals(tc, 1, ctx.sel);
    CuAssertTrue(tc, (void*) testTrycatchEnterLeaveOnce < ctx.pc);
    CuAssertTrue(tc, &&end > ctx.pc);
    CuAssertPtrEquals(tc, (void*) __builtin_frame_address(0), ctx.fp);
    CuAssertPtrEquals(tc, &ctx, rvmTrycatchLeave(&env));
    CuAssertPtrEquals(tc, NULL, env.trycatchContext);
end:
    return;
}


void testTrycatchEnterLeaveMultiple(CuTest* tc) {
    Env env = {0};
    TrycatchContext ctx1 = {0};
    ctx1.sel = 1;
    TrycatchContext ctx2 = {0};
    ctx2.sel = 1;
    TrycatchContext ctx3 = {0};
    ctx3.sel = 1;
    rvmTrycatchEnter(&env, &ctx1);
    CuAssertPtrEquals(tc, &ctx1, env.trycatchContext);
    CuAssertPtrEquals(tc, NULL, ctx1.prev);
    rvmTrycatchEnter(&env, &ctx2);
    CuAssertPtrEquals(tc, &ctx2, env.trycatchContext);
    CuAssertPtrEquals(tc, &ctx1, ctx2.prev);
    rvmTrycatchEnter(&env, &ctx3);
    CuAssertPtrEquals(tc, &ctx3, env.trycatchContext);
    CuAssertPtrEquals(tc, &ctx2, ctx3.prev);
    CuAssertPtrEquals(tc, &ctx3, rvmTrycatchLeave(&env));
    CuAssertPtrEquals(tc, &ctx2, env.trycatchContext);
    CuAssertPtrEquals(tc, &ctx2, rvmTrycatchLeave(&env));
    CuAssertPtrEquals(tc, &ctx1, env.trycatchContext);
    CuAssertPtrEquals(tc, &ctx1, rvmTrycatchLeave(&env));
    CuAssertPtrEquals(tc, NULL, env.trycatchContext);
}


void testTrycatchJumpOnce(CuTest* tc) {
    Env env = {0};
    TrycatchContext ctx = {0};
    ctx.sel = 1;
    jint count = 0;
    jint p1 = -1;
    jint p2 = -1;
    jint p3 = -1;
    jint ret = rvmTrycatchEnter(&env, &ctx);
    if (ret == 0) {
        p1 = ++count;
        rvmTrycatchJump(&ctx);
        p2 = ++count;
    } else if (ret == 1) {
        p3 = ++count;
    }
    rvmTrycatchLeave(&env);
    CuAssertPtrEquals(tc, NULL, env.trycatchContext);
    CuAssertIntEquals(tc, 1, p1);
    CuAssertIntEquals(tc, -1, p2);
    CuAssertIntEquals(tc, 2, p3);
}


void inner(TrycatchContext* ctx) {
    rvmTrycatchJump(ctx);
}
void testTrycatchJumpNested(CuTest* tc) {
    Env env = {0};
    TrycatchContext ctx = {0};
    ctx.sel = 1;
    jint count = 0;
    jint p1 = -1;
    jint p2 = -1;
    jint p3 = -1;
    jint ret = rvmTrycatchEnter(&env, &ctx);
    if (ret == 0) {
        p1 = ++count;
        inner(&ctx);
        p2 = ++count;
    } else if (ret == 1) {
        p3 = ++count;
    }
    rvmTrycatchLeave(&env);
    CuAssertPtrEquals(tc, NULL, env.trycatchContext);
    CuAssertIntEquals(tc, 1, p1);
    CuAssertIntEquals(tc, -1, p2);
    CuAssertIntEquals(tc, 2, p3);
}


int main(int argc, char* argv[]) {
    CuSuite* suite = CuSuiteNew();

    if (argc < 2 || !strcmp(argv[1], "testTrycatchEnterLeaveOnce")) SUITE_ADD_TEST(suite, testTrycatchEnterLeaveOnce);
    if (argc < 2 || !strcmp(argv[1], "testTrycatchEnterLeaveMultiple")) SUITE_ADD_TEST(suite, testTrycatchEnterLeaveMultiple);
    if (argc < 2 || !strcmp(argv[1], "testTrycatchJumpOnce")) SUITE_ADD_TEST(suite, testTrycatchJumpOnce);
    if (argc < 2 || !strcmp(argv[1], "testTrycatchJumpNested")) SUITE_ADD_TEST(suite, testTrycatchJumpNested);

    CuSuiteRun(suite);

    if (argc < 2) {
        CuString *output = CuStringNew();
        CuSuiteSummary(suite, output);
        CuSuiteDetails(suite, output);
        printf("%s\n", output->buffer);
    }

    return suite->failCount;
}

