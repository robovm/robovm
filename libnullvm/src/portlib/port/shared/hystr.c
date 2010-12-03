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
 * @brief String utilities.
 */
#include <stdarg.h>
#include <string.h>

/* for sprintf, which is used for printing floats */

#include <stdio.h>

#include "hycomp.h"
#include "hyport.h"
#include "utf8encode.h"

#define HYFTYPE_U64 1
#define HYFTYPE_U32 2
#define HYFTYPE_DBL 3
#define HYFTYPE_PTR 4
#define HYFTYPE_IMMEDIATE 5

#define HYFFLAG_DASH 0x01
#define HYFFLAG_HASH 0x02
#define HYFFLAG_ZERO 0x04
#define HYFFLAG_SPACE 0x08
#define HYFFLAG_PLUS 0x10

#define HYFSPEC_LL 0x20
#define HYFSPEC_L 0x40

#define HYF_NO_VALUE ((U_64)-1)

typedef union
{
  U_64 u64;
  double dbl;
  void *ptr;
} HyFormatValue;

typedef struct
{
  U_8 tag;
  U_8 index;
  U_8 widthIndex;
  U_8 precisionIndex;
  const char *type;
} HyFormatSpecifier;

#define HYF_MAX_SPECS 16
#define HYF_MAX_ARGS (HYF_MAX_SPECS * 3)

typedef struct
{
  const char *formatString;
  HyFormatValue value[HYF_MAX_ARGS];
  U_8 valueType[HYF_MAX_ARGS];
  HyFormatSpecifier spec[HYF_MAX_SPECS];
  U_8 valueCount;
  U_8 immediateCount;
  U_8 specCount;
} HyFormatData;

static const char digits_dec[] = "0123456789";
static const char digits_hex_lower[] = "0123456789abcdef";
static const char digits_hex_upper[] = "0123456789ABCDEF";

static const char *parseTagChar (const char *format, HyFormatData * result);
static void readValues (struct HyPortLibrary *portLibrary,
                        HyFormatData * result, va_list args);
static int parseFormatString (struct HyPortLibrary *portLibrary,
                              HyFormatData * result);
static I_32 writeDoubleToBuffer (char *buf, U_32 bufLen, U_64 width,
                                 U_64 precision, double value, U_8 type,
                                 U_8 tag);
static const char *parseModifier (const char *format, HyFormatData * result);
static const char *parseType (const char *format, HyFormatData * result);
static const char *parseWidth (const char *format, HyFormatData * result);
static U_32 writeFormattedString (struct HyPortLibrary *portLibrary,
                                  HyFormatData * data, char *result,
                                  U_32 length);
static I_32 writeSpec (HyFormatData * data, HyFormatSpecifier * spec,
                       char *result, U_32 length);
static const char *parseIndex (const char *format, U_8 * result);
static I_32 writeStringToBuffer (char *buf, U_32 bufLen, U_64 width,
                                 U_64 precision, const char *value, U_8 tag);
static const char *parsePrecision (const char *format, HyFormatData * result);
static I_32 writeIntToBuffer (char *buf, U_32 bufLen, U_64 width,
                              U_64 precision, U_64 value, U_8 tag,
                              int isSigned, const char *digits);
static I_32 writeUnicodeStringToBuffer (char *buf, U_32 bufLen, U_64 width,
                                        U_64 precision, const U_16 * value,
                                        U_8 tag);

/**
 * Write characters to a string as specified by format.
 *
 * @param[in] portLibrary The port library.
 * @param[in, out] buf The string buffer to be written.
 * @param[in] bufLen The size of the string buffer to be written.
 * @param[in] format The format of the string.
 * @param[in] ... Arguments for the format string.
 *
 * @return The number of characters printed not including the NUL terminator.
 *
 * @note When buf is NULL, the size of the buffer required to print to the string, including
 * the NUL terminator is returned.
 *
 */
U_32 VMCALL
hystr_printf (struct HyPortLibrary *portLibrary, char *buf, U_32 bufLen,
              const char *format, ...)
{
  U_32 rc;
  va_list args;
  va_start (args, format);
  rc = portLibrary->str_vprintf (portLibrary, buf, bufLen, format, args);
  va_end (args);
  return rc;
}

/**
 * Write characters to a string as specified by format.
 *
 * @param[in] portLibrary The port library.
 * @param[in, out] buf The string buffer to be written.
 * @param[in] bufLen The size of the string buffer to be written.
 * @param[in] format The format of the string.
 * @param[in] args Arguments for the format string.
 *
 * @return The number of characters printed not including the NUL terminator.
 *
 * @note When buf is NULL, the size of the buffer required to print to the string, including
 * the NUL terminator is returned.
 *
 */
U_32 VMCALL
hystr_vprintf (struct HyPortLibrary * portLibrary, char *buf, U_32 bufLen,
               const char *format, va_list args)
{
  HyFormatData formatData;
  memset (&formatData, 0, sizeof (formatData));

  formatData.formatString = format;
  parseFormatString (portLibrary, &formatData);
  readValues (portLibrary, &formatData, args);
  return writeFormattedString (portLibrary, &formatData, buf, bufLen);
}

static int
parseFormatString (struct HyPortLibrary *portLibrary, HyFormatData * result)
{
  const char *format = result->formatString;

  while (*format)
    {
      switch (*format)
        {
        case '%':
          format++;
          switch (*format)
            {
            case '%':
              /* literal '%' */
              format++;
              break;
            default:
              format =
                parseIndex (format, &result->spec[result->specCount].index);
              format = parseTagChar (format, result);
              format = parseWidth (format, result);
              format = parsePrecision (format, result);
              format = parseModifier (format, result);
              format = parseType (format, result);
              if (format == NULL)
                {
                  return -1;
                }
              result->specCount++;
            }
          break;
        default:
          format++;
        }
    }

  return 0;
}
static const char *
parseTagChar (const char *format, HyFormatData * result)
{

  switch (*format)
    {
    case '0':
      result->spec[result->specCount].tag |= HYFFLAG_ZERO;
      format++;
      break;
    case ' ':
      result->spec[result->specCount].tag |= HYFFLAG_SPACE;
      format++;
      break;
    case '-':
      result->spec[result->specCount].tag |= HYFFLAG_DASH;
      format++;
      break;
    case '+':
      result->spec[result->specCount].tag |= HYFFLAG_PLUS;
      format++;
      break;
    case '#':
      result->spec[result->specCount].tag |= HYFFLAG_HASH;
      format++;
      break;
    }

  return format;
}
static const char *
parseWidth (const char *format, HyFormatData * result)
{
  U_8 index;

  if (*format == '*')
    {
      format =
        parseIndex (format + 1, &result->spec[result->specCount].widthIndex);
      index = result->spec[result->specCount].widthIndex;
      if (index == 0xFF)
        {
          index = result->valueCount;
          result->spec[result->specCount].widthIndex = index;
        }
      result->valueCount++;
      result->valueType[index] = HYFTYPE_U32;
      result->value[index].u64 = HYF_NO_VALUE;

      return format;
    }
  else
    {
      U_32 width = 0;
      int anyDigits = 0;
      for (;;)
        {
          switch (*format)
            {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
              anyDigits = 1;
              width = width * 10 + (*format - '0');
              format += 1;
              break;
            default:
              index = HYF_MAX_ARGS - ++result->immediateCount;
              result->spec[result->specCount].widthIndex = index;
              result->valueType[index] = HYFTYPE_IMMEDIATE;
              if (anyDigits)
                {
                  result->value[index].u64 = width;
                }
              else
                {
                  result->value[index].u64 = HYF_NO_VALUE;
                }
              return format;
            }
        }
    }
}
static const char *
parsePrecision (const char *format, HyFormatData * result)
{
  U_8 index;

  if (*format == '.')
    {
      format += 1;
    }
  else
    {
      index = HYF_MAX_ARGS - ++result->immediateCount;
      result->spec[result->specCount].precisionIndex = index;
      result->valueType[index] = HYFTYPE_IMMEDIATE;
      result->value[index].u64 = HYF_NO_VALUE;

      return format;
    }

  if (*format == '*')
    {
      format =
        parseIndex (format + 1,
                    &result->spec[result->specCount].precisionIndex);
      index = result->spec[result->specCount].precisionIndex;
      if (index == 0xFF)
        {
          index = result->valueCount;
          result->spec[result->specCount].precisionIndex = index;
        }
      result->valueCount++;
      result->valueType[index] = HYFTYPE_U32;
      result->value[index].u64 = HYF_NO_VALUE;

      return format;
    }
  else
    {
      U_32 precision = 0;
      int anyDigits = 0;
      for (;;)
        {
          switch (*format)
            {
            case '0':
            case '1':
            case '2':
            case '3':
            case '4':
            case '5':
            case '6':
            case '7':
            case '8':
            case '9':
              anyDigits = 1;
              precision = precision * 10 + (*format - '0');
              format += 1;
              break;
            default:
              index = HYF_MAX_ARGS - ++result->immediateCount;
              result->spec[result->specCount].precisionIndex = index;
              result->valueType[index] = HYFTYPE_IMMEDIATE;
              if (anyDigits)
                {
                  result->value[index].u64 = precision;
                }
              else
                {
                  result->value[index].u64 = HYF_NO_VALUE;
                }
              return format;
            }
        }
    }
}
static const char *
parseModifier (const char *format, HyFormatData * result)
{

  switch (*format)
    {
    case 'z':
      format++;

      break;
    case 'l':
      format++;
      if (*format == 'l')
        {
          format++;
          result->spec[result->specCount].tag |= HYFSPEC_LL;
        }
      else
        {
          result->spec[result->specCount].tag |= HYFSPEC_L;
        }
      break;
    }

  return format;
}
static const char *
parseType (const char *format, HyFormatData * result)
{
  const char *type = format++;
  U_8 tag = result->spec[result->specCount].tag;
  U_8 index = result->spec[result->specCount].index;

  if (index == 0xFF)
    {
      index = result->valueCount;
      result->spec[result->specCount].index = index;
    }
  result->valueCount++;

  result->spec[result->specCount].type = type;

  switch (*type)
    {
      /* integers */
    case 'c':
      result->valueType[index] = HYFTYPE_U32;
      break;
    case 'i':
    case 'd':
    case 'u':
    case 'x':
    case 'X':
      result->valueType[index] = tag & HYFSPEC_LL ? HYFTYPE_U64 : HYFTYPE_U32;
      break;

      /* pointers */
    case 'p':
    case 's':
      result->valueType[index] = HYFTYPE_PTR;
      break;

      /* floats */
    case 'f':
    case 'e':
    case 'E':
    case 'F':
    case 'g':
    case 'G':
      result->valueType[index] = HYFTYPE_DBL;
      break;

    default:
      return NULL;
    }

  return format;
}
static void
readValues (struct HyPortLibrary *portLibrary, HyFormatData * result,
            va_list args)
{
  U_8 index;

  for (index = 0; index < result->valueCount; index++)
    {
      switch (result->valueType[index])
        {
        case HYFTYPE_U64:
          result->value[index].u64 = va_arg (args, U_64);
          break;
        case HYFTYPE_U32:
          result->value[index].u64 = va_arg (args, U_32);
          break;
        case HYFTYPE_DBL:
          result->value[index].dbl = va_arg (args, double);
          break;
        case HYFTYPE_PTR:
          result->value[index].ptr = va_arg (args, void *);
          break;
        case HYFTYPE_IMMEDIATE:
          /* shouldn't be encountered -- these should all be at the end of the value array */
          break;
        }
    }
}

static I_32
writeSpec (HyFormatData * data, HyFormatSpecifier * spec, char *result,
           U_32 length)
{
  HyFormatValue *value = &data->value[spec->index];
  U_64 width = data->value[spec->widthIndex].u64;
  U_64 precision = data->value[spec->precisionIndex].u64;
  I_32 index = 0;

  switch (*spec->type)
    {
    case 'i':
    case 'd':
      index =
        writeIntToBuffer (result, length, width, precision, value->u64,
                          spec->tag, 1, digits_dec);
      break;
    case 'u':
      index =
        writeIntToBuffer (result, length, width, precision, value->u64,
                          spec->tag, 0, digits_dec);
      break;
    case 'x':
      index =
        writeIntToBuffer (result, length, width, precision, value->u64,
                          spec->tag, 0, digits_hex_lower);
      break;
    case 'X':
      index =
        writeIntToBuffer (result, length, width, precision, value->u64,
                          spec->tag, 0, digits_hex_upper);
      break;

    case 'p':
      index =
        writeIntToBuffer (result, length, sizeof (UDATA) * 2,
                          sizeof (UDATA) * 2, (UDATA) value->ptr, 0, 0,
                          digits_hex_upper);
      break;

    case 'c':
      if (spec->tag & HYFSPEC_L)
        {
          char asUTF8[4];
          U_32 numberWritten =
            encodeUTF8Char ((UDATA) value->u64, (U_8 *) asUTF8);

          /* what if width/precision is less than size of asUTF8? [truncate?] */
          asUTF8[numberWritten] = '\0';
          index =
            writeStringToBuffer (result, length, width, precision, asUTF8,
                                 spec->tag);
        }
      else
        {
          index =
            writeStringToBuffer (result, length, width, precision, " ",
                                 spec->tag);
	  if (index <= (I_32)length)
            {
              if (result)
                {
                  result[index - 1] = (char) value->u64;
                }
            }
        }
      break;

    case 's':
      if (value->ptr)
        {
          if (spec->tag & HYFSPEC_L)
            {
              index =
                writeUnicodeStringToBuffer (result, length, width, precision,
                                            (const U_16 *) value->ptr,
                                            spec->tag);
            }
          else
            {
              index =
                writeStringToBuffer (result, length, width, precision,
                                     (const char *) value->ptr, spec->tag);
            }
        }
      else
        {
          index =
            writeStringToBuffer (result, length, width, precision, "<NULL>",
                                 spec->tag);
        }
      break;

      /* floats */
    case 'f':
    case 'e':
    case 'E':
    case 'F':
    case 'g':
    case 'G':
      index =
        writeDoubleToBuffer (result, length, width, precision, value->dbl,
                             *spec->type, spec->tag);
      break;
    }

  return index;
}

static I_32
writeIntToBuffer (char *buf, U_32 bufLen, U_64 width, U_64 precision,
                  U_64 value, U_8 tag, int isSigned, const char *digits)
{
  U_32 index = 0;
  U_32 length = 0;
  I_32 rightSpace = 0;
  U_64 temp;
  int base = strlen (digits);
  U_32 actualPrecision = 0;
  char signChar = 0;

  if (isSigned)
    {
      I_64 signedValue;
      if (tag & HYFSPEC_LL)
        {
          signedValue = (I_64) value;
        }
      else
        {
          signedValue = (I_32) value;
        }

      if (signedValue < 0)
        {
          signChar = '-';
          value = (U_64) (signedValue * -1);
        }
      else if (signedValue >= 0 && (tag & HYFFLAG_PLUS))
        {
          signChar = '+';
        }
    }

  /* find the end of the number */
  temp = value;
  do
    {
      length++;
      temp /= base;
    }
  while (temp);

  if (precision != HYF_NO_VALUE)
    {
      actualPrecision = (I_32) precision;
      if (actualPrecision > length)
        {
          length = actualPrecision;
        }
    }

  /* Account for "-" Must be after setting actualPrecision, before calculation of rightSpace */
  if (signChar)
    {
      length++;
    }

  if (width != HYF_NO_VALUE && (I_32) width > length)
    {
      if (tag & HYFFLAG_DASH)
        {
          rightSpace = (I_32) width - length;
        }
      length = (I_32) width;
    }

  if (tag & HYFFLAG_ZERO)
    {
      actualPrecision = length - (signChar ? 1 : 0);
    }

  /* now write the number out backwards */
  for (; rightSpace > 0; rightSpace--)
    {
      length -= 1;
      if (bufLen > length)
        {
          if (buf)
            {
              buf[length] = ' ';
            }
          index += 1;
        }
    }

  /* write out the digits */
  temp = value;
  do
    {
      length -= 1;
      actualPrecision -= 1;
      if (bufLen > length)
        {
          if (buf)
            {
              buf[length] = digits[(int) (temp % base)];
            }
          index += 1;
        }
      temp /= base;
    }
  while (temp);

  /* zero extend to the left according the requested precision */
  while (length > 0)
    {
      length -= 1;
      actualPrecision -= 1;
      if (bufLen > length)
        {
          if (buf)
            {
              if ((I_32)actualPrecision >= 0)
                {
                  buf[length] = '0';
                }
              else
                {
                  if (signChar)
                    {
                      buf[length] = signChar;
                      /* only print the sign char once */
                      signChar = 0;
                    }
                  else
                    {
                      buf[length] = ' ';
                    }
                }
            }
          index += 1;
        }
    }

  return index;
}

static I_32
writeStringToBuffer (char *buf, U_32 bufLen, U_64 width, U_64 precision,
                     const char *value, U_8 tag)
{
  size_t leftPadding = 0;
  size_t rightPadding = 0;

  if (precision == HYF_NO_VALUE)
    {
      precision = strlen (value);
    }
  else
    {
      I_32 n;
      /* detect if the string is shorter than precision */
      for (n = 0; n < precision; n++)
        {
          if (value[n] == 0)
            {
              precision = n;
              break;
            }
        }
    }

  if (width == HYF_NO_VALUE)
    {
      width = precision;
    }

  if (width > precision)
    {
      if (tag & HYFFLAG_DASH)
        {
          rightPadding = (size_t) (width - precision);
        }
      else
        {
          leftPadding = (size_t) (width - precision);
        }
    }

  if (leftPadding > bufLen)
    {
      leftPadding = bufLen;
    }
  if (buf)
    {
      memset (buf, ' ', leftPadding);
      buf += leftPadding;
    }
  bufLen -= leftPadding;

  if (precision > bufLen)
    {
      precision = bufLen;
    }
  if (buf)
    {
      memcpy (buf, value, (size_t) precision);
      buf += (size_t) precision;
    }
  bufLen -= (size_t) precision;

  if (rightPadding > bufLen)
    {
      rightPadding = bufLen;
    }
  if (buf)
    {
      memset (buf, ' ', rightPadding);
      buf += rightPadding;
    }

  return (I_32) (leftPadding + (size_t) precision + rightPadding);
}

/*
 * To determine size of buffer required for format string pass in a NULL buffer and the maximum
 * size willing to create.  For example for no restrictions result=NULL, length=(U_32)(-1), to restrict
 * the buffer to 2k, result=NULL, length=2048
 *
 * Value returned does not include space required for the null terminator
 */
static U_32
writeFormattedString (struct HyPortLibrary *portLibrary, HyFormatData * data,
                      char *result, U_32 length)
{
  const char *format = data->formatString;
  U_8 specIndex = 0;
  U_32 index = 0;

  if (NULL == result)
    {
      length = (U_32) - 1;
    }
  else if (0 == length)
    {
      /* empty buffer */
      return 0;
    }

  while (*format && index < length - 1)
    {
      switch (*format)
        {
        case '%':
          format++;
          switch (*format)
            {
            case '%':
              /* literal '%' */
              if (result)
                {
                  result[index] = '%';
                }
              index++;
              format++;
              break;
            default:
              if (result)
                {
                  index +=
                    writeSpec (data, &data->spec[specIndex], result + index,
                               length - index);
                }
              else
                {
                  index +=
                    writeSpec (data, &data->spec[specIndex], result, length);
                }

              format = data->spec[specIndex].type + 1;
              specIndex += 1;
              break;
            }
          break;
        default:
          if (result)
            {
              result[index] = *format;
            }
          format++;
          index++;
        }
    }

  /* writeSpec can return value > 1, so index does not increment sequentially */
  if (index > length - 1)
    {
      index = length - 1;
    }

  if (result)
    {
      result[index] = 0;
    }

  if (NULL == result)
    {
      return index + 1;         /* For the NUL terminator */
    }

  return index;
}

static I_32
writeDoubleToBuffer (char *buf, U_32 bufLen, U_64 width, U_64 precision,
                     double value, U_8 type, U_8 tag)
{
  char format[sizeof ("%+4294967295.4294967295f")];
  char *formatCursor = format;
  char *lastFormat = format + sizeof (format);
  char tempBuf[510];            /* 509 is maximum size of a converted double */

  *formatCursor++ = '%';

  if (tag & HYFFLAG_DASH)
    {
      *formatCursor++ = '-';
    }
  else if (tag & HYFFLAG_PLUS)
    {
      *formatCursor++ = '+';
    }
  else if (tag & HYFFLAG_SPACE)
    {
      *formatCursor++ = ' ';
    }
  else if (tag & HYFFLAG_ZERO)
    {
      *formatCursor++ = '0';
    }
  else if (tag & HYFFLAG_HASH)
    {
      *formatCursor++ = '#';
    }

  if (width != HYF_NO_VALUE)
    {
      formatCursor +=
        writeIntToBuffer (formatCursor, lastFormat - formatCursor,
                          HYF_NO_VALUE, HYF_NO_VALUE, width, 'u', 0,
                          digits_dec);
    }
  if (precision != HYF_NO_VALUE)
    {
      *formatCursor++ = '.';
      formatCursor +=
        writeIntToBuffer (formatCursor, lastFormat - formatCursor,
                          HYF_NO_VALUE, HYF_NO_VALUE, precision, 'u', 0,
                          digits_dec);
    }

  *formatCursor++ = type;
  *formatCursor++ = '\0';

  sprintf (tempBuf, format, value);

  if (buf)
    {
      strncpy (buf, tempBuf, bufLen);
      buf[bufLen - 1] = '\0';
      return strlen (buf);
    }

  return strlen (tempBuf);
}

/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hystr_startup
 * should be destroyed here.
 *
 * @param[in] portLibrary The port library.
 *
 * @note Most implementations will be empty.
 */
void VMCALL
hystr_shutdown (struct HyPortLibrary *portLibrary)
{
  /* empty */
}

/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the string operations may be created here.  All resources created here should be destroyed
 * in @ref hystr_shutdown.
 *
 * @param[in] portLibrary The port library.
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_STR
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hystr_startup (struct HyPortLibrary *portLibrary)
{
  return 0;
}

static I_32
writeUnicodeStringToBuffer (char *buf, U_32 bufLen, U_64 width,
                            U_64 precision, const U_16 * value, U_8 tag)
{
  U_32 numberOfUnicodeChar = 0;
  U_32 numberOfUTF8Char = 0;
  U_32 encodingLength;
  size_t leftPadding = 0;
  size_t rightPadding = 0;
  const U_16 *currentU16;

  if (precision == HYF_NO_VALUE)
    {
      currentU16 = value;
      precision = 0;
      while (*currentU16++ != 0)
        {
          precision++;
        }
    }
  else
    {
      I_32 n;
      /* detect if the string is shorter than precision */
      for (n = 0; n < precision; n++)
        {
          if (value[n] == 0)
            {
              precision = n;
              break;
            }
        }
    }

  currentU16 = value;
  while (numberOfUnicodeChar < precision)
    {

      encodingLength = encodeUTF8Char ((UDATA) * currentU16++, NULL);
      if (numberOfUTF8Char + encodingLength > bufLen)
        {
          break;
        }

      /* If the character fits then increment counts */
      numberOfUnicodeChar++;
      numberOfUTF8Char += encodingLength;
    }

  if (width == HYF_NO_VALUE)
    {
      width = numberOfUTF8Char;
    }

  if (width > numberOfUTF8Char)
    {
      if (tag & HYFFLAG_DASH)
        {
          rightPadding = (size_t) (width - numberOfUTF8Char);
        }
      else
        {
          leftPadding = (size_t) (width - numberOfUTF8Char);
        }
    }

  if (leftPadding > bufLen)
    {
      leftPadding = bufLen;
    }
  if (buf)
    {
      memset (buf, ' ', leftPadding);
      buf += leftPadding;
    }
  bufLen -= leftPadding;

  /* The space required for the UTF8 chars is guaranteed to be there */
  if (buf)
    {
      currentU16 = value;
      while (numberOfUnicodeChar-- > 0)
        {
          buf += encodeUTF8Char ((UDATA) * currentU16++, (U_8 *)buf);
        }
    }
  bufLen -= numberOfUTF8Char;

  if (rightPadding > bufLen)
    {
      rightPadding = bufLen;
    }
  if (buf)
    {
      memset (buf, ' ', rightPadding);
      buf += rightPadding;
    }

  return (I_32) (leftPadding + numberOfUTF8Char + rightPadding);
}
static const char *
parseIndex (const char *format, U_8 * result)
{
  const char *start = format;
  U_8 index = 0;

  for (;;)
    {
      switch (*format)
        {
        case '0':
        case '1':
        case '2':
        case '3':
        case '4':
        case '5':
        case '6':
        case '7':
        case '8':
        case '9':
          index = index * 10 + (*format - '0');
          format += 1;
          break;
        case '$':
          if (index > 0)
            {
              *result = index - 1;
              return format + 1;
            }
          /* fall through */
        default:
          *result = 0xFF;
          return start;
        }
    }
}
