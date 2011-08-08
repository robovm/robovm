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
 * Encode the Unicode character.
 *
 * Encodes the input Unicode character and stores it into result.
 *
 * @param[in] unicode The unicode character
 * @param[in,out] result buffer for UTF8 character
 * @param[in] bytesRemaining available space in result buffer
 *
 * @return Size of encoding (1,2,3) on success, 0 on failure
 *
 * @note Don't write more than bytesRemaining characters
 * @note If result is NULL then bytesRemaining is ignored and the number
 * of characters required to encode the unicode character is returned.
 */
U_32
encodeUTF8CharN (UDATA unicode, U_8 * result, U_32 bytesRemaining)
{
  if (unicode >= 0x01 && unicode <= 0x7f)
    {
      if (result)
        {
          if (bytesRemaining < 1)
            {
              return 0;
            }
          *result = (U_8) unicode;
        }
      return 1;
    }
  else if (unicode == 0 || (unicode >= 0x80 && unicode <= 0x7ff))
    {
      if (result)
        {
          if (bytesRemaining < 2)
            {
              return 0;
            }
          *result++ = (U_8) (((unicode >> 6) & 0x1f) | 0xc0);
          *result = (U_8) ((unicode & 0x3f) | 0x80);
        }
      return 2;
    }
  else if (unicode >= 0x800 && unicode <= 0xffff)
    {
      if (result)
        {
          if (bytesRemaining < 3)
            {
              return 0;
            }
          *result++ = (U_8) (((unicode >> 12) & 0x0f) | 0xe0);
          *result++ = (U_8) (((unicode >> 6) & 0x3f) | 0x80);
          *result = (U_8) ((unicode & 0x3f) | 0x80);
        }
      return 3;
    }
  else
    {
      return 0;
    }
}

/**
 * Encode the Unicode character.
 *
 * Encodes the input Unicode character and stores it into result.
 *
 * @param[in] unicode The unicode character
 * @param[in,out] result buffer for UTF8 character
 *
 * @return Size of encoding (1,2,3) on success, 0 on failure
 *
 * @note If result is NULL then the number of characters required to 
 * encode the character is returned.
 */
U_32
encodeUTF8Char (UDATA unicode, U_8 * result)
{
  return encodeUTF8CharN (unicode, result, 3);
}
