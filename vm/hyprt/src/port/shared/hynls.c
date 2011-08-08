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
 * @brief Native language support
 */
#define CDEV_CURRENT_FUNCTION include_header

#undef CDEV_CURRENT_FUNCTION

#include "hycomp.h"
#include "hyport.h"
#include "portpriv.h"
#include "hythread.h"
#include "hynlshelpers.h"
#include "portnls.h"

#include <stdlib.h>
#include <string.h>

#ifdef ZOS
#include <iconv.h>
#endif

#define CDEV_CURRENT_FUNCTION _prototypes_private
static const char *nlsh_lookup (struct HyPortLibrary *portLibrary,
                                U_32 module_name, U_32 message_num);

static char *build_catalog_name (struct HyPortLibrary *portLibrary,
                                 I_32 usePath, I_32 useDepth);

static void free_catalog (struct HyPortLibrary *portLibrary);

char *read_from_catalog (struct HyPortLibrary *portLibrary, IDATA fd,
                         char *buf, IDATA bufsize);

static HyNLSHashEntry *nls_allocateHashEntry (struct HyPortLibrary
                                              *portLibrary, U_32 module_name,
                                              U_32 message_num,
                                              const char *message,
                                              U_32 sizeOfMessage);
static const char *parse_catalogues (struct HyPortLibrary *portLibrary,
                                  UDATA flags, U_32 module_name,
                                  U_32 message_num,
                                  const char *default_string);
static void nlsh_insert (struct HyPortLibrary *portLibrary,
                         HyNLSHashEntry * entry);

static void open_catalog (struct HyPortLibrary *portLibrary);

void convertModuleName (U_32 module_name, U_8 * module_str);
static U_32 nlsh_hash (U_32 module_name, U_32 message_num);

#undef CDEV_CURRENT_FUNCTION

/* a sample key */
#define HYNLS_EXEMPLAR "XXXX000"

#define BUF_SIZE 1024

#define CDEV_CURRENT_FUNCTION hynls_set_locale
/**
 * Set the language, region, and variant of the locale.
 *
 * @param[in] portLibrary The port library
 * @param[in] lang - the language of the locale (e.g., "en"), 2 characters or less
 * @param[in] region - the region of the locale (e.g., "US"), 2 characters or less
 * @param[in] variant - the variant of the locale (e.g., "boont"), 31 characters or less
 */
void VMCALL
hynls_set_locale (struct HyPortLibrary *portLibrary, const char *lang,
                  const char *region, const char *variant)
{
#ifdef HY_NO_THR
  THREAD_ACCESS_FROM_PORT(portLibrary);
#endif /* HY_NO_THR */

  HyNLSDataCache *nls = &portLibrary->portGlobals->nls_data;
#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - hynls_set_locale\n");
#endif


  hythread_monitor_enter (nls->monitor);

  if (lang && strlen (lang) <= 2)
    strcpy (nls->language, lang);
  if (region && strlen (region) <= 2)
    strcpy (nls->region, region);
  if (variant && strlen (variant) <= 31)
    strcpy (nls->variant, variant);

  hythread_monitor_exit (nls->monitor);

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hynls_get_language
/**
 * Return the string representing the currently set language.
 *
 * @param[in] portLibrary The port library
 *
 * @return language string
 */
const char *VMCALL
hynls_get_language (struct HyPortLibrary *portLibrary)
{

#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - hynls_get_language\n");
#endif


  return portLibrary->portGlobals->nls_data.language;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hynls_get_region
/**
 * Return the string representing the currently set region.
 *
 * @param[in] portLibrary The port library
 *
 * @return region string
 */
const char *VMCALL
hynls_get_region (struct HyPortLibrary *portLibrary)
{

#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - hynls_get_region\n");
#endif


  return portLibrary->portGlobals->nls_data.region;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hynls_get_variant
/**
 * Return the string representing the currently set variant.
 *
 * @param[in] portLibrary The port library
 *
 * @return variant string
 */
const char *VMCALL
hynls_get_variant (struct HyPortLibrary *portLibrary)
{

#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - hynls_get_variant\n");
#endif


  return portLibrary->portGlobals->nls_data.variant;

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hynls_printf
/**
 * Print a formatted NLS message. 
 *
 * @param[in] portLibrary The port library
 * @param[in] flags - to indicate what type of message (e.g., ERROR) and whether a newline is required
 * @param[in] module_name - the module identifier of the NLS message
 * @param[in] message_num - the NLS message number within the module
 * @param[in] ... - arguments used in the NLS message format
 *
 */
void VMCALL
hynls_printf (struct HyPortLibrary *portLibrary, UDATA flags,
              U_32 module_name, U_32 message_num, ...)
{
  va_list args;
#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - hynls_printf\n");
#endif


  va_start (args, message_num);
  portLibrary->nls_vprintf (portLibrary, flags, module_name, message_num,
                            args);
  va_end (args);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hynls_vprintf
/**
 * Print a formatted NLS message. 
 *
 * @param[in] portLibrary The port library
 * @param[in] flags - to indicate what type of message (e.g., ERROR) and whether a newline is required
 * @param[in] module_name - the module identifier of the NLS message
 * @param[in] message_num - the NLS message number within the module
 * @param[in] args - arguments used in the NLS message format
 *
 */
void VMCALL
hynls_vprintf (struct HyPortLibrary *portLibrary, UDATA flags,
               U_32 module_name, U_32 message_num, va_list args)
{
  const char *message;

#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - hynls_vprintf\n");
#endif


  message =
    portLibrary->nls_lookup_message (portLibrary, flags, module_name,
                                     message_num, NULL);

#if defined(NLS_DEBUG)
  portLibrary->tty_printf (portLibrary, "NLS - vprintf - message: %s\n",
                           message);
#endif


  if (flags & HYNLS_STDOUT)
    {
      portLibrary->file_vprintf (portLibrary, HYPORT_TTY_OUT, message, args);
    }
  else
    {
      portLibrary->file_vprintf (portLibrary, HYPORT_TTY_ERR, message, args);
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hynls_lookup_message
/**
 * Return the NLS string for the module name and message number.  If no string is found,
 * or a failure occurs, return the default_string.
 *
 * @param[in] portLibrary The port library
 * @param[in] flags - to indicate what type of message (e.g., ERROR) and whether a newline is required
 * @param[in] module_name - the module identifier of the NLS message
 * @param[in] message_num - the NLS message number within the module
 * @param[in] default_string - a default message, in case no NLS message is found
 *
 * @return NLS String
 */
const char *VMCALL
hynls_lookup_message (struct HyPortLibrary *portLibrary, UDATA flags,
                      U_32 module_name, U_32 message_num,
                      const char *default_string)
{
#ifdef HY_NO_THR
  THREAD_ACCESS_FROM_PORT(portLibrary);
#endif /* HY_NO_THR */
  HyNLSDataCache *nls = &portLibrary->portGlobals->nls_data;
  const char *message;
#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - hynls_lookup_message\n");
#endif


  hythread_monitor_enter (nls->monitor);

  if (!nls->catalogues[0])
    open_catalog (portLibrary);

  message = nlsh_lookup (portLibrary, module_name, message_num);
  if (!message)
    {
      message =
        parse_catalogues (portLibrary, flags, module_name, message_num,
                       default_string);
      if (!message)
        message =
          HYNLS_ERROR_MESSAGE (HYNLS_PORT_NLS_FAILURE, "NLS Failure\n");
    }

  hythread_monitor_exit (nls->monitor);
  return message;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hynls_release_message

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hynls_set_catalog
/**
 * Setup the path to the NLS catalog. 
 *
 * @param[in] portLibrary The port library
 * @param[in] paths - an array of directory paths where the NLS catalog may be found
 * @param[in] nPaths - the number of entries in the @ref paths array
 * @param[in] baseName - the lead name of the catalog file name (i.e., the "java" in java_en_US.properties)
 * @param[in] extension - the extension of the catalog file name (i.e., the "properties in java_en_US.properties)
 */
void VMCALL
hynls_set_catalog (struct HyPortLibrary *portLibrary, const char **paths,
                   const int nPaths, const char *baseName,
                   const char *extension)
{
#ifdef HY_NO_THR
  THREAD_ACCESS_FROM_PORT(portLibrary);
#endif /* HY_NO_THR */

  int i;
  char *p;

  HyNLSDataCache *nls = &portLibrary->portGlobals->nls_data;
#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - hynls_set_catalog\n");
#endif


  hythread_monitor_enter (nls->monitor);

  if (!baseName || !extension)
    goto clean_exit;
  for (i = 0; i < nPaths; i++)
    {
      if (nls->baseCatalogPaths[i])
        portLibrary->mem_free_memory (portLibrary, nls->baseCatalogPaths[i]);
      nls->baseCatalogPaths[i] = NULL;
      if (nls->catalogues[i])
          portLibrary->mem_free_memory (portLibrary, nls->catalogues[i]);
      nls->catalogues[i] = NULL;
    }
  nls->nPaths = 0;
  if (nls->baseCatalogName)
    {
      portLibrary->mem_free_memory (portLibrary, nls->baseCatalogName);
      nls->baseCatalogName = NULL;
    }
  if (nls->baseCatalogExtension)
    {
      portLibrary->mem_free_memory (portLibrary, nls->baseCatalogExtension);
      nls->baseCatalogExtension = NULL;
    }

  for (i = 0; i < nPaths; i++)
    {
      nls->baseCatalogPaths[i] =
        portLibrary->mem_allocate_memory (portLibrary, strlen (paths[i]) + 1);
      if (nls->baseCatalogPaths[i])
        {
          strcpy (nls->baseCatalogPaths[i], paths[i]);
          p = strrchr (nls->baseCatalogPaths[i], DIR_SEPARATOR);
          if (p)
            p[1] = '\0';
          nls->nPaths++;
        }
    }

  nls->baseCatalogName =
    portLibrary->mem_allocate_memory (portLibrary, strlen (baseName) + 1);
  if (nls->baseCatalogName)
    strcpy (nls->baseCatalogName, baseName);

  nls->baseCatalogExtension =
    portLibrary->mem_allocate_memory (portLibrary, strlen (extension) + 1);
  if (nls->baseCatalogExtension)
    strcpy (nls->baseCatalogExtension, extension);

  if (nls->language[0] == 0 && nls->region[0] == 0 && nls->variant[0] == 0)
    {
      nls_determine_locale (portLibrary);
    }

clean_exit:
  hythread_monitor_exit (nls->monitor);

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION build_catalog_name

static char *
build_catalog_name (struct HyPortLibrary *portLibrary, I_32 usePath,
                    I_32 useDepth)
{
  HyNLSDataCache *nls = &portLibrary->portGlobals->nls_data;
  I_32 len = 1;
  char *catalog = NULL;
  char *defaultCatalog = NULL;
  char *endPathPtr = NULL;

  hysysinfo_get_executable_name (portLibrary, NULL, &defaultCatalog);
  endPathPtr = strrchr (defaultCatalog, DIR_SEPARATOR);
  endPathPtr[1] = '\0';
  // defaultCatalog now holds the name of the launcher's home directory

#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - build_catalog_name\n");
#endif


  if (!nls->nPaths)
    {
      portLibrary->nls_set_catalog (portLibrary,
                                    (const char **) &defaultCatalog, 1,
                                    "harmony", "properties");  
      if (!nls->baseCatalogName)
        goto _done;
      if (nls->language[0] == 0 && nls->region[0] == 0
          && nls->variant[0] == 0)
        {
          nls_determine_locale (portLibrary);
        }
    }

  if (useDepth > 0)
    {
      if (nls->language[0] == 0)
        goto _done;
      if (useDepth > 1)
        {
          if (nls->region[0] == 0)
            goto _done;
          if (useDepth > 2)
            {
              if (nls->variant[0] == 0)
                goto _done;
            }
        }
    }

  len += strlen (nls->baseCatalogPaths[usePath]);
  len += strlen (nls->baseCatalogName);
  len += strlen (nls->baseCatalogExtension);
  len += 1;                     /* the . before the extension */
  len += strlen (nls->language) + 1;    /* '_en' */
  len += strlen (nls->region) + 1;
  len += strlen (nls->variant) + 1;
  len += 1;                     /* null terminator */

  catalog = portLibrary->mem_allocate_memory (portLibrary, len);
  if (!catalog)
    goto _done;
  strcpy (catalog, nls->baseCatalogPaths[usePath]);
  strcat (catalog, nls->baseCatalogName);
  if (useDepth > 0)
    {
      strcat (catalog, "_");
      strcat (catalog, nls->language);
      if (useDepth > 1)
        {
          strcat (catalog, "_");
          strcat (catalog, nls->region);
          if (useDepth > 2)
            {
              strcat (catalog, "_");
              strcat (catalog, nls->variant);
            }
        }
    }
  strcat (catalog, ".");
  strcat (catalog, nls->baseCatalogExtension);

_done:
  // Free memory for defaultCatalog -- necessary ??
  if (defaultCatalog)
    {
      portLibrary->mem_free_memory (portLibrary, defaultCatalog);
    }

  return catalog;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION open_catalog

static void
open_catalog (struct HyPortLibrary *portLibrary)
{
  HyNLSDataCache *nls = &portLibrary->portGlobals->nls_data;
  char *catalog = NULL;
  char *add_catalog = NULL;
  I_32 position = 0;
  I_32 fd = -1, d, p, successfully_opened = 0;

#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - open_catalog\n");
  portLibrary->tty_printf (portLibrary, "nPaths = %d\n", (I_32) nls->nPaths);
#endif


  /* try the following way to look for the catalog:
   * The base name will typically be "<path>/java.properties". Append as many locale descriptors to the file name as possible to find a file. 
   * e.g. 
   *      harmony_en_GB.properties
   *      harmony_en.properties
   *      harmony.properties
   */

  for (p = 0; p < (I_32) nls->nPaths; p++)
    {
      for (d = 3; d >= 0; d--)
        {
          if (catalog)
            portLibrary->mem_free_memory (portLibrary, catalog);
          catalog = build_catalog_name (portLibrary, p, d);
          if (!catalog)
            continue;
#if defined(NLS_DEBUG)
          portLibrary->tty_printf (portLibrary,
                                   "NLS - attempting to open: %s\n", catalog);
#endif


          fd = portLibrary->file_open (portLibrary, catalog, HyOpenRead, 0);
          if (fd != -1)
            break;
        }
      if (fd != -1) 
        {
          add_catalog = portLibrary->mem_allocate_memory (portLibrary, strlen(catalog) +1);
          strcpy(add_catalog, catalog);
          portLibrary->portGlobals->nls_data.catalogues[position++] = add_catalog;
          portLibrary->file_close (portLibrary, fd);
#if defined(NLS_DEBUG)
          portLibrary->tty_printf (portLibrary, "NLS - successfully opened %s\n", catalog);
#endif
          successfully_opened = 1;
          fd = -1;
        }
    }

  if (!successfully_opened)
    {
#if defined(NLS_DEBUG)
      portLibrary->tty_printf (portLibrary,
                               "NLS - failed to open the nls catalog\n");
#endif


      return;
    }


  free_catalog(portLibrary);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION parse_catalogues
static const char *
parse_catalogues (struct HyPortLibrary *portLibrary, UDATA flags,
               U_32 module_name, U_32 message_num, const char *default_string)
{

#define MSG_NONE 0
#define MSG_SLASH 1
#define MSG_UNICODE 2
#define MSG_CONTINUE 3
#define MSG_DONE 4
#define MSG_IGNORE 5

  U_8 dataBuf[BUF_SIZE];
  U_8 *charPointer = NULL;
  U_8 *endPointer = NULL;
  int mode = MSG_NONE, count = 0, digit;
  U_32 unicode = 0;
  char nextChar;
  U_32 offset = 0, bufSize = BUF_SIZE, maxOffset = 0;
  I_32 keyLength = -1;
  char *buf, *newBuf;
  BOOLEAN firstChar = TRUE;
  IDATA fd = -1;
  I_32 success_reading = 0;
  HyNLSDataCache *nls = &portLibrary->portGlobals->nls_data;
  HyNLSHashEntry *entry = NULL;
  char convertedModuleEnum[5];
  U_32 catalog_index;
  /* calculate a size which is larger than we could possibly need by putting together all of the prefixes and suffixes */
  char
    prefix[sizeof
           (HYNLS_ERROR_PREFIX "" HYNLS_INFO_PREFIX "" HYNLS_WARNING_PREFIX ""
            HYNLS_COMMON_PREFIX "" HYNLS_EXEMPLAR "" HYNLS_ERROR_SUFFIX ""
            HYNLS_INFO_SUFFIX "" HYNLS_WARNING_SUFFIX " \n")];
  char *searchKey;
  BOOLEAN newline = !(flags & HYNLS_DO_NOT_APPEND_NEWLINE);
  const char *format;

#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - parse_catalog\n");
#endif


  convertModuleName (module_name, (U_8 *) convertedModuleEnum);

  format = "" HYNLS_COMMON_PREFIX "%s%03u %s";

  if (0 == (flags & HYNLS_DO_NOT_PRINT_MESSAGE_TAG))
    {
      if (flags & HYNLS_ERROR)
        {
          format =
            "" HYNLS_ERROR_PREFIX "" HYNLS_COMMON_PREFIX "%s%03u"
            HYNLS_ERROR_SUFFIX " %s";
        }
      else if (flags & HYNLS_WARNING)
        {
          format =
            "" HYNLS_WARNING_PREFIX "" HYNLS_COMMON_PREFIX "%s%03u"
            HYNLS_WARNING_SUFFIX " %s";
        }
      else if (flags & HYNLS_INFO)
        {
          format =
            "" HYNLS_INFO_PREFIX "" HYNLS_COMMON_PREFIX "%s%03u"
            HYNLS_INFO_SUFFIX " %s";
        }
    }
  portLibrary->str_printf (portLibrary, prefix, sizeof (prefix), format,
                           convertedModuleEnum, message_num,
                           newline ? "\n" : "");

  /* make sure the searchKey string starts at HYVM001 instead of (E)HYVM001 */
  searchKey = prefix + (strchr (format, '%') - format);

#if defined(NLS_DEBUG)
  portLibrary->tty_printf (portLibrary,
                           "NLS - parse_catalog - searchKey: %s\n",
                           searchKey);
#endif


  /* we do a lazy caching, populate the cache as we look up messages */

  if (!(buf = portLibrary->mem_allocate_memory (portLibrary, bufSize)))
  {
      goto finished;
  }
  for(catalog_index = 0; catalog_index < nls->nPaths; catalog_index++)
  {
  if (nls->catalogues[catalog_index])
    {
      fd = -1;
      fd = portLibrary->file_open (portLibrary, (char *) nls->catalogues[catalog_index], HyOpenRead, 0);
      offset = 0;
      maxOffset = 0;
      keyLength = -1;
      firstChar = TRUE;
      mode = MSG_NONE;
      count = 0;
      unicode = 0;
    }
  if (fd != -1)
  {
      success_reading = 1;

  while (read_from_catalog (portLibrary, fd, (char *) dataBuf, BUF_SIZE) !=
         NULL)
    {
      charPointer = dataBuf;
      endPointer = charPointer + strlen ((char *) dataBuf);

      while (charPointer < endPointer)
        {
          nextChar = *charPointer++;

          if (offset + 2 >= bufSize)
            {
              bufSize <<= 1;
              if (!
                  (newBuf =
                   portLibrary->mem_allocate_memory (portLibrary, bufSize)))
                goto finished;
              memcpy (newBuf, buf, offset);
              portLibrary->mem_free_memory (portLibrary, buf);
              buf = newBuf;
            }

          if (mode == MSG_UNICODE)
            {
              if (nextChar >= '0' && nextChar <= '9')
                digit = nextChar - '0';
              else if (nextChar >= 'a' && nextChar <= 'f')
                digit = (nextChar - 'a') + 10;
              else if (nextChar >= 'A' && nextChar <= 'F')
                digit = (nextChar - 'A') + 10;
              else
                digit = -1;
              if (digit >= 0)
                {
                  unicode = (unicode << 4) + digit;
                  if (++count < 4)
                    continue;
                }
              mode = MSG_NONE;
              if (unicode >= 0x01 && unicode <= 0x7f)
                {
                  buf[offset++] = unicode;
                }
              else if (unicode == 0 || (unicode >= 0x80 && unicode <= 0x7ff))
                {
                  buf[offset++] = ((unicode >> 6) & 0x1f) | 0xc0;
                  buf[offset++] = (unicode & 0x3f) | 0x80;
                }
              else if (unicode >= 0x800 && unicode <= 0xffff)
                {
                  buf[offset++] = ((unicode >> 12) & 0x0f) | 0xe0;
                  buf[offset++] = ((unicode >> 6) & 0x3f) | 0x80;
                  buf[offset++] = (unicode & 0x3f) | 0x80;
                }
              if (nextChar != '\n')
                continue;
            }

          if (mode == MSG_SLASH)
            {
              mode = MSG_NONE;
              switch (nextChar)
                {
                case '\r':
                  mode = MSG_CONTINUE;  /* Look for a following 'n */
                  continue;
                case '\n':
                  mode = MSG_IGNORE;    /* Ignore whitespace on the next line */
                  continue;
                case 'b':
                  nextChar = '\b';
                  break;
                case 'f':
                  nextChar = '\f';
                  break;
                case 'n':
                  nextChar = '\n';
                  break;
                case 'r':
                  nextChar = '\r';
                  break;
                case 't':
                  nextChar = '\t';
                  break;
                case 'u':
                  mode = MSG_UNICODE;
                  unicode = count = 0;
                  continue;
                }
            }
          else
            {
              switch (nextChar)
                {
                case '#':
                case '!':
                  if (firstChar)
                    {

                      while (1)
                        {
                          if (charPointer >= endPointer)
                            {
                              if (read_from_catalog
                                  (portLibrary, fd, (char *) dataBuf,
                                   BUF_SIZE) != NULL)
                                {
                                  charPointer = dataBuf;
                                  endPointer =
                                    charPointer + strlen ((char *)charPointer);
                                }
                            }
                          if (charPointer >= endPointer)
                            break;
                          nextChar = *charPointer++;
                          if (nextChar == '\r' || nextChar == '\n')
                            break;
                        }
                      continue;
                    }
                  break;
                case '\n':
                  if (mode == MSG_CONTINUE)
                    {           /* Part of a \r\n sequence */
                      mode = MSG_IGNORE;
                      continue;
                    }
                  /* fall into next case */
                case '\r':
                  mode = MSG_NONE;
                  firstChar = TRUE;
                makeStrings:
                  if (keyLength >= 0)
                    {
#if defined(NLS_DEBUG)
                      portLibrary->tty_printf (portLibrary,
                                               "NLS - parse_catalog - keyLength: %d -- buf: %20.20s\n",
                                               keyLength, buf);
#endif


                      if (strncmp
                          (searchKey, buf, sizeof (HYNLS_EXEMPLAR) - 1) == 0)
                        {
#if defined(NLS_DEBUG)
                          portLibrary->tty_printf (portLibrary,
                                                   "NLS - parse_catalog - key match\n");
#endif


                          /* we have the exact message */
                          if (flags & HYNLS_DO_NOT_PRINT_MESSAGE_TAG)
                            {
                              entry =
                                nls_allocateHashEntry (portLibrary,
                                                       module_name,
                                                       message_num,
                                                       buf + keyLength,
                                                       offset - keyLength +
                                                       1);
                              if (entry)
                                {
                                  entry->message[offset - keyLength] = '\0';
                                  if (newline)
                                    strcat (entry->message, "\n");
                                }
                            }
                          else
                            {
                              entry =
                                nls_allocateHashEntry (portLibrary,
                                                       module_name,
                                                       message_num, prefix,
                                                       offset - keyLength +
                                                       strlen (prefix));
                              if (entry)
                                {
                                  /* null terminate and trim the \n if required */
                                  entry->message[strlen (prefix) -
                                                 (newline ? 1 : 0)] = '\0';
                                  strncat (entry->message, buf + keyLength,
                                           offset - keyLength);
                                  if (newline)
                                    strcat (entry->message, "\n");
                                }
                            }
                          goto finished;
                        }
                      keyLength = -1;
                    }
                  if (charPointer >= endPointer)
                    {
                      if (read_from_catalog
                          (portLibrary, fd, (char *) dataBuf,
                           BUF_SIZE) != NULL)
                        {
                          charPointer = dataBuf;
                          endPointer = charPointer + strlen ((char *)charPointer);
                        }
                    }
                  if (charPointer >= endPointer)
                    {
                    finished:
                      if (buf)
                        portLibrary->mem_free_memory (portLibrary, buf);
#if defined(NLS_DEBUG)
                      portLibrary->tty_printf (portLibrary,
                                               "NLS - parse_catalog - inserting message\n");
#endif


                      if (!entry)
                        {
                          char *tmpStr = prefix;
                          if (default_string)
                            tmpStr = (char *) default_string;
                          entry =
                            nls_allocateHashEntry (portLibrary, module_name,
                                                   message_num, tmpStr,
                                                   strlen (tmpStr));
                          if (!entry)
                            {
                              portLibrary->file_close (portLibrary, fd);
                              return default_string;
                            }
                        }
                      nlsh_insert (portLibrary, entry);
                      portLibrary->file_close (portLibrary, fd);
                      return entry->message;
                    }
                  if (offset > maxOffset)
                    maxOffset = offset;
                  offset = 0;
                  continue;
                case '\\':
                  mode = MSG_SLASH;
                  continue;
                case ':':
                case '=':
                  if (keyLength == -1)
                    {           /* if parsing the key */
                      keyLength = offset;
                      continue;
                    }
                  break;
                }
              if ((nextChar >= 0x1c && nextChar <= ' ')
                  || (nextChar >= 9 && nextChar <= 0xd))
                {
                  if (mode == MSG_CONTINUE)
                    mode = MSG_IGNORE;
                  /* if key length == 0 or value length == 0 */
                  if (offset == 0 || offset == (U_32) keyLength
                      || mode == MSG_IGNORE)
                    continue;
                  if (keyLength == -1)
                    {           /* if parsing the key */
                      mode = MSG_DONE;
                      continue;
                    }
                }
              if (mode == MSG_IGNORE || mode == MSG_CONTINUE)
                mode = MSG_NONE;
            }
          firstChar = FALSE;
          if (mode == MSG_DONE)
            {
              keyLength = offset;
              mode = MSG_NONE;
            }
          buf[offset++] = nextChar;
        }
    }
  }
  }
  if (!success_reading)
  {
      /* couldn't open the file, store the searchKey instead */
      char *tmpStr = prefix;
      if (default_string)
      {
          tmpStr = (char *) default_string;
      }
      entry =
          nls_allocateHashEntry (portLibrary, module_name, message_num, tmpStr,
          strlen (tmpStr));
      if (!entry)
      {
          return default_string;
      }
      nlsh_insert (portLibrary, entry);
      return entry->message;
  }
  goto makeStrings;

#undef MSG_NONE
#undef MSG_SLASH
#undef MSG_UNICODE
#undef MSG_CONTINUE
#undef MSG_DONE
#undef MSG_IGNORE

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION free_catalog

static void
free_catalog (struct HyPortLibrary *portLibrary)
{
  HyNLSDataCache *nls = &portLibrary->portGlobals->nls_data;
  U_32 i;
#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - free_catalog\n");
#endif


  for (i = 0; i < HYNLS_NUM_HASH_BUCKETS; i++)
    {
      HyNLSHashEntry *entry = nls->hash_buckets[i];
      if (entry)
        {
          while (entry->next)
            {
              entry = entry->next;
            }
          entry->next = nls->old_hashEntries;
          nls->old_hashEntries = nls->hash_buckets[i];
          nls->hash_buckets[i] = NULL;
        }
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION nlsh_hash
static U_32
nlsh_hash (U_32 module_name, U_32 message_num)
{
  return (module_name ^ message_num);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION nlsh_lookup
static const char *
nlsh_lookup (struct HyPortLibrary *portLibrary, U_32 module_name,
             U_32 message_num)
{
  HyNLSDataCache *nls = &portLibrary->portGlobals->nls_data;
  U_32 hashKey = nlsh_hash (module_name, message_num);
  U_32 index;
  HyNLSHashEntry *entry;

#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - nlsh_lookup\n");
#endif


  index = hashKey % HYNLS_NUM_HASH_BUCKETS;
  entry = nls->hash_buckets[index];

  while (entry)
    {
      if (entry->module_name == module_name
          && entry->message_num == message_num)
        {
          return entry->message;
        }
      entry = entry->next;
    }

  return NULL;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION nlsh_insert
static void
nlsh_insert (struct HyPortLibrary *portLibrary, HyNLSHashEntry * entry)
{
  HyNLSDataCache *nls = &portLibrary->portGlobals->nls_data;
  U_32 hashKey = nlsh_hash (entry->module_name, entry->message_num);
  U_32 index;

#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - nlsh_insert\n");
#endif


  index = hashKey % HYNLS_NUM_HASH_BUCKETS;
  entry->next = nls->hash_buckets[index];
  nls->hash_buckets[index] = entry;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hynls_startup
/**
 * PortLibrary startup.
 * 
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the NLS library operations may be created here.  All resources created here should be destroyed
 * in @ref hynls_shutdown.
 *
 * @param[in] portLibrary The port library
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_NLS
 */
I_32 VMCALL
hynls_startup (struct HyPortLibrary *portLibrary)
{
#ifdef HY_NO_THR
  THREAD_ACCESS_FROM_PORT(portLibrary);
#endif /* HY_NO_THR */
  HyNLSDataCache *nls = &portLibrary->portGlobals->nls_data;

  if (0 !=
      hythread_monitor_init_with_name (&nls->monitor, 0, "NLS hash table"))
    {
      return (I_32) HYPORT_ERROR_STARTUP_NLS;
    }

  nls_determine_locale (portLibrary);

  return (I_32) 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hynls_shutdown
/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by 
 * @ref hynls_startup should be destroyed here.
 *
 * @param[in] portLibrary The port library
 */
void VMCALL
hynls_shutdown (struct HyPortLibrary *portLibrary)
{
#ifdef HY_NO_THR
  THREAD_ACCESS_FROM_PORT(portLibrary);
#endif /* HY_NO_THR */
  HyNLSDataCache *nls = &portLibrary->portGlobals->nls_data;
  HyNLSHashEntry *entry;
  U_32 i;

  for (i = 0; i < HYNLS_NUM_HASH_BUCKETS; i++)
    {
      entry = nls->hash_buckets[i];
      while (entry)
        {
          HyNLSHashEntry *next = entry->next;
          portLibrary->mem_free_memory (portLibrary, entry);
          entry = next;
        }
    }

  /* Free the baseCatalogPaths allocated in hynls_set_catalog */
  for (i = 0; i < nls->nPaths; i++)
    {
      if (nls->baseCatalogPaths[i])
        {
          portLibrary->mem_free_memory (portLibrary,
                                        nls->baseCatalogPaths[i]);
          nls->baseCatalogPaths[i] = NULL;
        }
      if (nls->catalogues[i])
        {
            portLibrary->mem_free_memory (portLibrary, nls->catalogues[i]);
            nls->catalogues[i] = NULL;
        }
    }

  if (nls->baseCatalogExtension)
    {
      portLibrary->mem_free_memory (portLibrary, nls->baseCatalogExtension);
      nls->baseCatalogExtension = NULL;
    }

/* catalog is never free'd without this flag */
  entry = nls->old_hashEntries;
  while (entry)
    {
      HyNLSHashEntry *next = entry->next;
      portLibrary->mem_free_memory (portLibrary, entry);
      entry = next;
    }

  if (nls->baseCatalogName)
    portLibrary->mem_free_memory (portLibrary, nls->baseCatalogName);

  hythread_monitor_destroy (nls->monitor);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION nls_allocateHashEntry
static HyNLSHashEntry *
nls_allocateHashEntry (struct HyPortLibrary *portLibrary, U_32 module_name,
                       U_32 message_num, const char *message,
                       U_32 sizeOfMessage)
{
  HyNLSHashEntry *entry = portLibrary->mem_allocate_memory (portLibrary,
                                                            sizeof
                                                            (HyNLSHashEntry) +
                                                            sizeOfMessage +
                                                            1 -
                                                            sizeof (entry->
                                                                    message));
#if defined(NLS_DEBUG_TRACE)
  portLibrary->tty_printf (portLibrary, "NLS - nls_allocateHashEntry\n");
#endif


  if (!entry)
    return NULL;
  entry->module_name = module_name;
  entry->message_num = message_num;
  entry->next = NULL;
  memcpy (entry->message, message, sizeOfMessage);
  entry->message[sizeOfMessage] = '\0';
#if defined(NLS_DEBUG)
  portLibrary->tty_printf (portLibrary,
                           "NLS - nls_allocateHashEntry - message: %s - sizeOfMessage: %d\n",
                           entry->message, sizeOfMessage);
#endif


  return entry;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION convertModuleName
void
convertModuleName (U_32 module_name, U_8 * module_str)
{
  module_str[0] = (U_8) ((module_name >> 24) & 0xff);
  module_str[1] = (U_8) ((module_name >> 16) & 0xff);
  module_str[2] = (U_8) ((module_name >> 8) & 0xff);
  module_str[3] = (U_8) ((module_name) & 0xff);
  module_str[4] = 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION read_from_catalog

char *
read_from_catalog (struct HyPortLibrary *portLibrary, IDATA fd, char *buf,
                   IDATA bufsize)
{
  char temp[BUF_SIZE];
  IDATA count, nbytes = bufsize;
  char *cursor = buf;
#ifdef ZOS
	iconv_t converter;
	size_t inbytesleft, outbytesleft;
	char* inbuf, *outbuf;
#endif

  if (nbytes <= 0)
    return 0;

  /* discount 1 for the trailing NUL */
  nbytes -= 1;

#ifdef ZOS
/* iconv_open is not an a2e function, so we need to pass it EBCDIC strings */
#pragma convlit(suspend)
	converter = iconv_open("UTF-8", "IBM-1047");
#pragma convlit(resume)
	if ( converter == (iconv_t)-1 ) return NULL;
#endif

  while (nbytes)
    {
      count = BUF_SIZE > nbytes ? nbytes : BUF_SIZE;
      count = portLibrary->file_read (portLibrary, fd, temp, count);

      if (count < 0)
        {
#ifdef ZOS
          iconv_close(converter);
#endif
          /* if we've made it through a successful read, return the buf. */
          if (nbytes + 1 != bufsize)
            return buf;
          return NULL;
        }

#ifdef ZOS
		inbuf = temp;
		inbytesleft = count;
		outbuf = cursor;
		outbytesleft = nbytes;
		if ( (size_t)-1 == iconv(converter, &inbuf, &inbytesleft, &outbuf, &outbytesleft) || inbytesleft == count ) {
			/* conversion failed */
			iconv_close(converter);
			portLibrary->file_seek(portLibrary, fd, -1 * count, HySeekCur);
			return NULL;
		}
		if ( inbytesleft > 0 ) {
			portLibrary->file_seek(portLibrary, fd, inbytesleft - count, HySeekCur);
		}
		nbytes -= count - inbytesleft;
		cursor += count - inbytesleft;
#else 
      memcpy (cursor, temp, count);
      cursor += count;
      nbytes -= count;
#endif /* ZOS */
    }

  *cursor = '\0';

#ifdef ZOS
	if ( converter != (iconv_t)-1 ) iconv_close(converter);
#endif

  return buf;
}

#undef CDEV_CURRENT_FUNCTION

