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
 * @internal @file
 * @ingroup Port
 * @brief Native language support helpers
 */

#undef CDEV_CURRENT_FUNCTION

#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>

#include <utime.h>

#include <stdlib.h>
#include <string.h>
#include <time.h>
#include <locale.h>

#include <langinfo.h>

#include "hyport.h"
#include "portpriv.h"

#define CDEV_CURRENT_FUNCTION _prototypes_public
void nls_determine_locale (struct HyPortLibrary *portLibrary);

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION _prototypes_private

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION nls_determine_locale
/**
 * @internal
 * Set set locale.
 *
 * @param[in] portLibrary The port library
 */
void
nls_determine_locale (struct HyPortLibrary *portLibrary)
{

  HyNLSDataCache *nls = &portLibrary->portGlobals->nls_data;
  char languageProp[4] = "en\0";
  char countryProp[3] = "US";
  char *lang = NULL;
  int langlen = 0;
#if defined(LINUX)
  IDATA result;
  char langProp[24];
#endif


  IDATA countryStart = 2;

  /* Get the language */

  /* Set locale, returns NULL in case locale data cannot be initialized. This may indicate
   * that the locale is not installed OR not selected & generated (see /etc/locale.gen) */
  setlocale (LC_ALL, "");

#if defined(LINUX)
  lang = setlocale (LC_CTYPE, NULL);
  /* set lang for later usage, we cannot use the return of setlocale(LC_ALL, "") as its not
   * the correct interface to retrieve it (lang/region) */
  /* Use LANG environment variable when locale cannot be obtained */
  if (!lang || !strcmp (lang, "C") || !strcmp (lang, "POSIX"))
    {
      result =
        hysysinfo_get_env (portLibrary, "LANG", langProp, sizeof (langProp));
      if (!result && !strcmp (langProp, "ja"))
        {
          strcat (langProp, "_JP");
          lang = langProp;
        }
    }
#else
  /* Query locale data */
  lang = setlocale (LC_CTYPE, NULL);

#if defined (ZOS)
  if (NULL != lang) {
    /* z/OS sometimes returns the HFS path to a "locale object" so carve it up to make it look like the corresponding locale name */
    char *lastSlash = strrchr(lang, '/');

    if (NULL != lastSlash) {
      lang = lastSlash + 1;
    }
  }
#endif /* defined (ZOS) */

#endif /* LINUX */


  if (lang != NULL && strcmp (lang, "POSIX") && strcmp (lang, "C"))

    if (lang != NULL && (langlen = strlen (lang)) >= 2)
      {
        /* copy the language, stopping at '_' */
        languageProp[0] = lang[0];
        languageProp[1] = lang[1];
        if (lang[2] != '_')
          {
            languageProp[2] = lang[2];
            countryStart++;
          }
      }
  if (!strcmp (languageProp, "jp"))
    languageProp[1] = 'a';
  strncpy (nls->language, languageProp, 3);

  /* Get the region */

  if (langlen >= (3 + countryStart) && lang[countryStart] == '_')
    {
      countryProp[0] = lang[countryStart + 1];
      countryProp[1] = lang[countryStart + 2];
    }
  strncpy (nls->region, countryProp, 2);

}

#undef CDEV_CURRENT_FUNCTION
