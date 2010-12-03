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
 * @brief shared library
 */

#undef CDEV_CURRENT_FUNCTION

/* 
 *	Standard unix shared libraries
 *	(AIX: 4.2 and higher only)
 */
#include <stdlib.h>
#include <string.h>
#include <errno.h>
#include <dlfcn.h>

/* Start copy from hyfiletext.c */
/* __STDC_ISO_10646__ indicates that the platform wchar_t encoding is Unicode */
/* but older versions of libc fail to set the flag, even though they are Unicode */
#if defined(__STDC_ISO_10646__) || defined(LINUX) || defined(FREEBSD)
#define HYVM_USE_MBTOWC
#else
#define HYVM_USE_ICONV
#include <iconv.h>
#endif

#if defined(HYVM_USE_MBTOWC) || defined(HYVM_USE_ICONV)
#include <nl_types.h>
#include <langinfo.h>
/* Some older platforms (Netwinder) don't declare CODESET */
#if !defined(CODESET)
#define CODESET _NL_CTYPE_CODESET_NAME
#endif
#endif

/* End copy */

#include "hyport.h"
#include "portnls.h"

#define CDEV_CURRENT_FUNCTION _prototypes_private
#if (defined(HYVM_USE_MBTOWC))
static void convertWithMBTOWC (struct HyPortLibrary *portLibrary, const char *error,
                               char *errBuf, UDATA bufLen);
#endif /* HYVM_USE_MBTOWC */

#if (defined(HYVM_USE_ICONV))
static void convertWithIConv (struct HyPortLibrary *portLibrary,
                              const char *error,
                              char *errBuf, UDATA bufLen);
#endif /* HYVM_USE_ICONV */

static void getDLError (struct HyPortLibrary *portLibrary, char *errBuf,
                        UDATA bufLen);

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysl_close_shared_library
/**
 * Close a shared library.
 *
 * @param[in] portLibrary The port library.
 * @param[in] descriptor Shared library handle to close.
 *
 * @return 0 on success, any other value on failure.
 */
UDATA VMCALL
hysl_close_shared_library (struct HyPortLibrary *portLibrary,
                           UDATA descriptor)
{
  return (UDATA) dlclose ((void *)descriptor);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysl_open_shared_library
/** 
 * Opens a shared library .
 *
 * @param[in] portLibrary The port library.
 * @param[in] name path Null-terminated string containing the shared library.
 * @param[out] descriptor Pointer to memory which is filled in with shared-library handle on success.
 * @param[in] decorate Boolean value indicates whether name should be decorated if it contains path information and cannot be found.
 *
 * @return 0 on success, any other value on failure.
 *
 * @note contents of descriptor are undefined on failure.
 */
UDATA VMCALL
hysl_open_shared_library (struct HyPortLibrary * portLibrary, char *name,
                          UDATA * descriptor, BOOLEAN decorate)
{
  void *handle;
  char *openName = name;
  char mangledName[1024];
  char errBuf[512];

  if (decorate)
    {
      char *p = strrchr (name, '/');
      if (p)
        {
          /* the names specifies a path */
          portLibrary->str_printf (portLibrary, mangledName, 1024,
                                   "%.*slib%s" PLATFORM_DLL_EXTENSION,
                                   (UDATA) p + 1 - (UDATA) name, name, p + 1);
        }
      else
        {
          portLibrary->str_printf (portLibrary, mangledName, 1024,
                                   "lib%s" PLATFORM_DLL_EXTENSION, name);
        }
      openName = mangledName;
    }

  handle = dlopen (openName, RTLD_NOW);
  if (handle == NULL)
    {
      getDLError (portLibrary, errBuf, sizeof (errBuf));
      if (portLibrary->file_attr (portLibrary, openName) == HyIsFile)
        {
          return portLibrary->error_set_last_error_with_message (portLibrary,
                                                                 HYPORT_SL_INVALID,
                                                                 errBuf);
        }
      else
        {
          return portLibrary->error_set_last_error_with_message (portLibrary,
                                                                 HYPORT_SL_NOT_FOUND,
                                                                 errBuf);
        }
    }

  *descriptor = (UDATA) handle;
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysl_lookup_name
/**
 * Search for a function named 'name' taking argCount in the shared library 'descriptor'.
 *
 * @param[in] portLibrary The port library.
 * @param[in] descriptor Shared library to search.
 * @param[in] name Function to look up.
 * @param[out] func Pointer to the function.
 * @param[in] argSignature Argument signature.
 *
 * @return 0 on success, any other value on failure.
 *
 * argSignature is a C (ie: NUL-terminated) string with the following possible values for each character:
 *
 *		V	- void
 *		Z	- boolean
 *		B	- byte
 *		C	- char (16 bits)
 *		I	- integer (32 bits)
 *		J	- long (64 bits)
 *		F	- float (32 bits) 
 *		D	- double (64 bits) 
 *		L	- object / pointer (32 or 64, depending on platform)
 *		P	- pointer-width platform data. (in this context an IDATA)
 *
 * Lower case signature characters imply unsigned value.
 * Upper case signature characters imply signed values.
 * If it doesn't make sense to be signed/unsigned (eg: V, L, F, D Z) the character is upper case.
 * 
 * argList[0] is the return type from the function.
 * The argument list is as it appears in english: list is left (1) to right (argCount)
 *
 * @note contents of func are undefined on failure.
 */
UDATA VMCALL
hysl_lookup_name (struct HyPortLibrary * portLibrary, UDATA descriptor,
                  const char *name, UDATA * func, const char *argSignature)
{
  void *address;
  address = dlsym ((void *)descriptor, name);
  if (address == NULL)
    {
      return 1;
    }
  *func = (UDATA) address;
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION getDLError
static void
getDLError (struct HyPortLibrary *portLibrary, char *errBuf, UDATA bufLen)
{
  const char *error;

  if (bufLen == 0)
    {
      return;
    }

  error = dlerror ();
  if (error == NULL || error[0] == '\0')
    {
      /* just in case another thread consumed our error message */
      error = (char *)portLibrary->nls_lookup_message (portLibrary,
                                               HYNLS_ERROR |
                                               HYNLS_DO_NOT_APPEND_NEWLINE,
                                               HYNLS_PORT_SL_UNKOWN_ERROR,
                                               "Unknown error");
      strncpy (errBuf, error, bufLen);
      errBuf[bufLen - 1] = '\0';
      return;
    }

#if defined(HYVM_USE_MBTOWC)
  convertWithMBTOWC (portLibrary, error, errBuf, bufLen);
#elif defined(HYVM_USE_ICONV)
  convertWithIConv (portLibrary, error, errBuf, bufLen);
#else
  strncpy (errBuf, error, bufLen);
  errBuf[bufLen - 1] = '\0';
#endif

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION convertWithIConv
#if (defined(HYVM_USE_ICONV))
static void
convertWithIConv (struct HyPortLibrary *portLibrary, const char *error,
                  char *errBuf, UDATA bufLen)
{
  iconv_t converter;
  size_t inbytesleft, outbytesleft;
  char *inbuf, *outbuf;
  converter = iconv_open ("UTF-8", nl_langinfo (CODESET));
  if (converter == (iconv_t) - 1)
    {
      /* no converter available for this code set. Just dump the platform chars */
      strncpy (errBuf, error, bufLen);
      errBuf[bufLen - 1] = '\0';
      return;
    }
  inbuf = (char *) error;       /* for some reason this argument isn't const */
  outbuf = errBuf;
  inbytesleft = strlen (error);
  outbytesleft = bufLen - 1;
  while ((outbytesleft > 0) && (inbytesleft > 0))
    {
      if ((size_t) - 1 ==
          iconv (converter, &inbuf, &inbytesleft, &outbuf, &outbytesleft))
        {
          if (errno == E2BIG)
            {
              break;
            }
          /* if we couldn't translate this character, copy one byte verbatim */
          *outbuf = *inbuf;
          outbuf++;
          inbuf++;
          inbytesleft--;
          outbytesleft--;
        }
    }
  *outbuf = '\0';
  iconv_close (converter);
}
#endif /* HYVM_USE_ICONV */

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION convertWithMBTOWC
#if (defined(HYVM_USE_MBTOWC))
static void
convertWithMBTOWC (struct HyPortLibrary *portLibrary, const char *error,
                   char *errBuf, UDATA bufLen)
{
  char *out, *end;
  const char* walk;
  wchar_t ch;
  int ret;
  out = errBuf;
  end = &errBuf[bufLen - 1];
  walk = error;
  /* reset the shift state */
  ret = mbtowc (NULL, NULL, 0);
  while (*walk)
    {
      ret = mbtowc (&ch, walk, MB_CUR_MAX);
      if (ret < 0)
        {
          ch = *walk++;
        }
      else if (ret == 0)
        {
          break;
        }
      else
        {
          walk += ret;
        }
      if (ch == '\r')
        continue;
      if (ch == '\n')
        ch = ' ';
      if (ch < 0x80)
        {
          if ((out + 1) > end)
            break;
          *out++ = (char) ch;
        }
      else if (ch < 0x800)
        {
          if ((out + 2) > end)
            break;
          *out++ = (char) (0xc0 | ((ch >> 6) & 0x1f));
          *out++ = (char) (0x80 | (ch & 0x3f));
        }
      else
        {
          if ((out + 3) > end)
            break;
          *out++ = (char) (0xe0 | ((ch >> 12) & 0x0f));
          *out++ = (char) (0x80 | ((ch >> 6) & 0x3f));
          *out++ = (char) (0x80 | (ch & 0x3f));
        }
    }
  *out = '\0';
}
#endif /* HYVM_USE_MBTOWC */

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysl_shutdown
/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hysl_startup
 * should be destroyed here.
 *
 * @param[in] portLibrary The port library.
 *
 * @note Most implementations will be empty.
 */
void VMCALL
hysl_shutdown (struct HyPortLibrary *portLibrary)
{
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hysl_startup
/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the shared library operations may be created here.  All resources created here should be destroyed
 * in @ref hysl_shutdown.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_SL
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hysl_startup (struct HyPortLibrary *portLibrary)
{
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION

#undef CDEV_CURRENT_FUNCTION
