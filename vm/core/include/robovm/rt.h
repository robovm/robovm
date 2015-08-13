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
#ifndef ROBOVM_RT_H
#define ROBOVM_RT_H

/*
 * The core VM native code should not make any assupmtions on the existence of
 * non-standard classes, methods or fields in the runtime library. This file
 * defines an interface which is used by the core VM native code whenever it
 * would otherwise have needed to rely on non-standard properties of the
 * runtime library. It is the responsibility of the runtime library native
 * code to provide an implementation of this interface.
 *
 * All functions/global variables must be prefixed with 'rvmRT'.
 */

/**
 * Returns the name of the runtime library.
 */
extern const char* rvmRTGetName(void);

/**
 * Initializes the runtime library. Returns TRUE on success.
 */
extern jboolean rvmRTInit(Env* env);

#endif
