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

#include "iohelp.h"
#include "exceptions.h"
#include "jclglob.h"

/**
  * This will convert all separators to the proper platform separator
  * and remove duplicates on non POSIX platforms.
  */
void
ioh_convertToPlatform (char *path)
{
  char *pathIndex;
#if !(DIR_SEPARATOR == '/')
  size_t length = strlen (path);
#endif

  /* Convert all separators to the same type */
  pathIndex = path;
  while (*pathIndex != '\0')
    {
      if ((*pathIndex == '\\' || *pathIndex == '/')
          && (*pathIndex != DIR_SEPARATOR))
        *pathIndex = DIR_SEPARATOR;
      pathIndex++;
    }

#if !(DIR_SEPARATOR == '/')

  /* Remove duplicate initial separators */
  pathIndex = path;
  while ((*pathIndex != '\0') && (*pathIndex == DIR_SEPARATOR))
    {
      pathIndex++;
    }
  if ((pathIndex > path) && ((int)length > (pathIndex - path))
      && (*(pathIndex + 1) == ':'))
    {
      /* For Example '////c:/!*' (! added to avoid gcc warning) */
      size_t newlen = length - (pathIndex - path);
      memmove (path, pathIndex, newlen);
      path[newlen] = '\0';
    }
  else
    {
      if ((pathIndex - path > 3) && ((int)length > (pathIndex - path)))
        {
          /* For Example '////serverName/!*' (! added to avoid gcc warning) */
          size_t newlen = length - (pathIndex - path) + 2;
          memmove (path, pathIndex - 2, newlen);
          path[newlen] = '\0';
        }
    }
  /* This will have to handle extra \'s but currently doesn't */
#endif

}
