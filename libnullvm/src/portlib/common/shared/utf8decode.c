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

#include "hycomp.h"

/**
 * Decode the UTF8 character.
 *
 * Decode the input UTF8 character and stores it into result.
 *
 * @param[in] input The UTF8 character
 * @param[in,out] result buffer for unicode characters
 * @param[in] bytesRemaining number of bytes remaining in input
 *
 * @return The number of UTF8 characters consumed (1,2,3) on success, 0 on failure
 * @note Don't read more than bytesRemaining characters.
 * @note  If morecharacters are required to fully decode the character, return failure
 */
U_32
decodeUTF8CharN (const U_8 * input, U_16 * result, U_32 bytesRemaining)
{
  U_8 c;
  const U_8 *cursor = input;

  if (bytesRemaining < 1)
    {
      return 0;
    }

  c = *cursor++;
  if (c == 0x00)
    {
      /* illegal NUL encoding */

      return 0;

    }
  else if ((c & 0x80) == 0x00)
    {
      /* one byte encoding */

      *result = (U_16) c;
      return 1;

    }
  else if ((c & 0xE0) == 0xC0)
    {
      /* two byte encoding */
      U_16 unicodeC;

      if (bytesRemaining < 2)
        {
          return 0;
        }
      unicodeC = ((U_16) c & 0x1F) << 6;

      c = *cursor++;
      unicodeC += (U_16) c & 0x3F;
      if ((c & 0xC0) != 0x80)
        {
          return 0;
        }

      *result = unicodeC;
      return 2;

    }
  else if ((c & 0xF0) == 0xE0)
    {
      /* three byte encoding */
      U_16 unicodeC;

      if (bytesRemaining < 3)
        {
          return 0;
        }
      unicodeC = ((U_16) c & 0x0F) << 12;

      c = *cursor++;
      unicodeC += ((U_16) c & 0x3F) << 6;
      if ((c & 0xC0) != 0x80)
        {
          return 0;
        }

      c = *cursor++;
      unicodeC += (U_16) c & 0x3F;
      if ((c & 0xC0) != 0x80)
        {
          return 0;
        }

      *result = unicodeC;
      return 3;
    }
  else
    {
      /* illegal encoding (i.e. would decode to a char > 0xFFFF) */

      return 0;
    }
}

/**
 * Decode the UTF8 character.
 *
 * Decode the input UTF8 character and stores it into result.
 *
 * @param[in] input The UTF8 character
 * @param[in,out] result buffer for unicode characters
 *
 * @return The number of UTF8 characters consumed (1,2,3) on success, 0 on failure
 */
U_32
decodeUTF8Char (const U_8 * input, U_16 * result)
{
  /* a UTF8 character can't require more than 3 bytes */
  return decodeUTF8CharN (input, result, 3);
}

