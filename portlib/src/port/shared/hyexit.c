/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * @file
 * @ingroup Port
 * @brief Process shutdown.
 */
#include <stdlib.h>

#include "hyport.h"

/**
 * Block until the portlibary has been exited and return the error code.
 *
 * @param[in] portLibrary The port library
 *
 * @return exit code.
 * @note Most implementations will be empty.
 */
I_32 VMCALL
hyexit_get_exit_code (struct HyPortLibrary *portLibrary)
{
  return 0;
}

/**
 * Terminate a process.
 *
 * Perform any shutdown which is absolutely necessary before terminating the process,
 * and terminate the process.  Unblock any callers to @ref hyexit_get_exit_code
 *
 * @param[in] portLibrary The port library
 * @param[in] exitCode The exit code to be used to terminate the process.
 */
void VMCALL
hyexit_shutdown_and_exit (struct HyPortLibrary *portLibrary, I_32 exitCode)
{
  exit ((int) exitCode);
}

/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hyexit_startup
 * should be destroyed here.
 *
 * @param[in] portLibrary The port library
 *
 * @note Most implementations will be empty.
 */
void VMCALL
hyexit_shutdown (struct HyPortLibrary *portLibrary)
{
}

/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the exit operations may be created here.  All resources created here should be destroyed
 * in @ref hyexit_shutdown.
 *
 * @param[in] portLibrary The port library
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_EXIT
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hyexit_startup (struct HyPortLibrary *portLibrary)
{
  return 0;
}
