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

#define CDEV_CURRENT_FUNCTION _comment_
/**
  * @file
  * @ingroup Port
  * @brief TTY output
  *
  * All VM output goes to stderr by default.  These routines provide the helpers for such output.
  */

#undef CDEV_CURRENT_FUNCTION

#include <stdio.h>
#include <stdarg.h>
#include "hyport.h"
#include <unistd.h>

#include <sys/ioctl.h>
#include <sys/types.h>

#define CDEV_CURRENT_FUNCTION _prototypes_private

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_char_ready

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_flush

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_gets

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_read_char

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_output_string

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_output_char

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_printf
/** 
 * Write characters to stderr.
 *
 * @param[in] portLibrary The port library.
 * @param[in] format The format string to be output.
 * @param[in] ... arguments for format.
 *
 * @note Use hyfile_printf for stdout output.
 *
 */
void VMCALL
hytty_printf (struct HyPortLibrary *portLibrary, const char *format, ...)
{
  va_list args;

  va_start (args, format);
  portLibrary->tty_vprintf (portLibrary, format, args);
  va_end (args);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_startup
/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the TTY library operations may be created here.  All resources created here should be destroyed
 * in @ref hytty_shutdown.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_TTY
 * \arg HYPORT_ERROR_STARTUP_TTY_HANDLE
 * \arg HYPORT_ERROR_STARTUP_TTY_CONSOLE
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hytty_startup (struct HyPortLibrary *portLibrary)
{
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_shutdown
/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hytty_startup
 * should be destroyed here.
 *
 * @param[in] portLibrary The port library.
 *
 * @note Most implementations will be empty.
 */
void VMCALL
hytty_shutdown (struct HyPortLibrary *portLibrary)
{
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_get_chars
/** 
 * Read characters from stdin into buffer.
 *
 * @param[in] portLibrary The port library.
 * @param[out] s Buffer.
 * @param[in] length Size of buffer (s).
 *
 * @return The number of characters read, -1 on error.
 */
IDATA VMCALL
hytty_get_chars (struct HyPortLibrary *portLibrary, char *s, UDATA length)
{
/*This was changed to NOT use fgetc, which uses buffering, which throws off the #bytes
 *returned by the FIONREAD ioctl call in available()*/
  return read (STDIN_FILENO, s, length);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_err_printf
/**
 * Output message to stderr.
 *
 * @param[in] portLibrary The port library.
 * @param[in] format The format String.
 * @param[in] ... argument list.
 *
 * @deprecated All output goes to stderr, use hytty_printf()
 *
 */
void VMCALL
hytty_err_printf (struct HyPortLibrary *portLibrary, const char *format, ...)
{
  va_list args;

  va_start (args, format);
  portLibrary->tty_err_vprintf (portLibrary, format, args);
  va_end (args);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_available
/**
 * Determine the number of characters remaining to be read from stdin.
 *
 * @param[in] portLibrary The port library.
 *
 * @return number of characters remaining to be read.
 */
IDATA VMCALL
hytty_available (struct HyPortLibrary *portLibrary)
{
  int rc;
  off_t curr, end;

  IDATA avail = 0;

  /* when redirected from a file */
  curr = lseek (STDIN_FILENO, 0L, SEEK_CUR);    /* don't use tell(), it doesn't exist on all platforms, i.e. linux */
  if (curr != -1)
    {
      end = lseek (STDIN_FILENO, 0L, SEEK_END);
      lseek (STDIN_FILENO, curr, SEEK_SET);
      if (end >= curr)
        {
          return end - curr;
        }
    }

  /* ioctl doesn't work for files on all platforms (i.e. SOLARIS) */

  rc = ioctl (STDIN_FILENO, FIONREAD, &avail);

  /* 64 bit platforms use a 32 bit value, using IDATA fails on big endian */
  /* Pass in IDATA because ioctl() is device dependent, some devices may write 64 bits */
  if (rc != -1)
    {
      return *(I_32 *) & avail;
    }
  return 0;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_vprintf
/**
 * Output message to stderr.
 *
 * @param[in] portLibrary The port library.
 * @param[in] format The format String.
 * @param[in] args Variable argument list.
 *
 * @note Use hyfile_vprintf for stdout output.
 * 
 */
void VMCALL
hytty_vprintf (struct HyPortLibrary *portLibrary, const char *format,
               va_list args)
{
  portLibrary->file_vprintf (portLibrary, HYPORT_TTY_ERR, format, args);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hytty_err_vprintf
/**
 * Output message to stderr.
 *
 * @param[in] portLibrary The port library.
 * @param[in] format The format String.
 * @param[in] args Variable argument list.
 *
 * @deprecated All output goes to stderr, use hytty_vprintf()
 *
 */
void VMCALL
hytty_err_vprintf (struct HyPortLibrary *portLibrary, const char *format,
                   va_list args)
{
  portLibrary->file_vprintf (portLibrary, HYPORT_TTY_ERR, format, args);
}

#undef CDEV_CURRENT_FUNCTION
