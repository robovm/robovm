/*
 * Copyright (C) 2012 Trillian AB
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
#ifndef ROBOVM_INIT_H
#define ROBOVM_INIT_H

extern ClassLoader* systemClassLoader;

extern jboolean rvmInitOptions(int argc, char* argv[], Options* options, jboolean ignoreRvmArgs);
extern Env* rvmStartup(Options* options);
extern VM* rvmCreateVM(Options* options);
extern Env* rvmCreateEnv(VM* vm);
extern jboolean rvmRun(Env* env);
extern void rvmShutdown(Env* env, jint code);
extern void rvmAbort(char* format, ...);

extern DynamicLib* rvmOpenDynamicLib(Env* env, const char* file, char** errorMsg);
extern void rvmCloseDynamicLib(Env* env, DynamicLib* lib);
extern jboolean rvmHasDynamicLib(Env* env, DynamicLib* lib, DynamicLib* libs);
extern void rvmAddDynamicLib(Env* env, DynamicLib* lib, DynamicLib** libs);
extern void rvmRemoveDynamicLib(Env* env, DynamicLib* lib, DynamicLib* libs);
extern void* rvmFindDynamicLibSymbol(Env* env, DynamicLib* first, const char* symbol, jboolean searchAll);

#endif

