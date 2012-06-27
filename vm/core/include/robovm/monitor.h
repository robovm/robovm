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
#ifndef ROBOVM_MONITOR_H
#define ROBOVM_MONITOR_H

jboolean rvmInitMonitors(Env* env);
Monitor* rvmCreateMonitor(Env* env, Object* obj);
Object* rvmGetMonitorObject(Monitor* mon);
Thread* rvmGetObjectLockHolder(Env* env, Object* obj);
jboolean rvmHoldsLock(Env* env, Thread* thread, Object* obj);
void rvmLockObject(Env* env, Object* obj);
jboolean rvmUnlockObject(Env* env, Object* obj);
void rvmObjectWait(Env* env, Object* obj, jlong msec, jint nsec, jboolean interruptShouldThrow);
void rvmObjectNotify(Env* env, Object* obj);
void rvmObjectNotifyAll(Env* env, Object* obj);
void rvmThreadSleep(Env* env, jlong msec, jint nsec);
void rvmThreadInterrupt(Env* env, Thread* thread);

#endif
