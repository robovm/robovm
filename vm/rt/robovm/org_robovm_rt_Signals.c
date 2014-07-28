/*
 * Copyright (C) 2014 Trillian Mobile AB
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

void Java_org_robovm_rt_Signals_installChainingSignals(Env* env, Class* c) {
    rvmInstallChainingSignals(env);
}

void Java_org_robovm_rt_Signals_reinstallSavedSignals(Env* env, Class* c, jlong state) {
    rvmReinstallSavedSignals(env, LONG_TO_PTR(state));
}

jlong Java_org_robovm_rt_Signals_saveSignals(Env* env, Class* c) {
    return PTR_TO_LONG(rvmSaveSignals(env));
}
