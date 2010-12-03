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
 * @brief Time formatting utilities.
 */

#include <string.h>
#include "hycomp.h"
#include "hyport.h"

typedef struct HyTimeInfo
{
  U_32 second;
  U_32 minute;
  U_32 hour;
  U_32 day;
  U_32 month;
  U_32 year;
} HyTimeInfo;

static void gettimestruct (I_64 millis, struct HyTimeInfo *tm);

/**
 * Returns the current time in as a formatted string.  Formatted according to the 'format' parameter.
 *
 * @param[in] portLibrary  The port library.
 * @param[in,out] buf A pointer to a character buffer where the resulting time string will be stored.
 * @param[in] bufLen The length of the 'buf' character buffer.
 * @param[in] format The format string, ordinary characters placed in the format string are copied.
 * to buf without conversion.  Conversion specifiers are introduced by a '%' character, and are replaced in buf as follows:.
 * <ul>
 * <li>%b The abbreviated month name in english
 * <li>%d The  day  of the month as a decimal number (range 0 to 31).
 * <li>%H The hour as a decimal number using a 24-hour  clock (range 00 to 23).
 * <li>%m The month as a decimal number (range 01 to 12).
 * <li>%M The minute as a decimal number.
 * <li>%S The second as a decimal number.
 * <li>%Y The year as a decimal number including the century.
 * <li>%% A literal '%' character.
 * <li>all other '%' specifiers will be ignored
 * </ul>
 *
 * @return The number of characters placed in the array buf, not including NULL terminator.
 * If the value equals 'bufLen', it means that the array was too small.
 */
U_32 VMCALL
hystrftime (struct HyPortLibrary *portLibrary, char *buf, U_32 bufLen,
            const char *format)
{
  I_64 millis;
  U_32 index = 0;
  HyTimeInfo tm;
  static const char abbMonthName[12][4] =
    { "JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT",
    "NOV", "DEC"
  };

  if (buf == NULL || bufLen < 1)
    {
      return 0;
    }

  millis = portLibrary->time_current_time_millis (portLibrary);

  gettimestruct (millis, &tm);

  /* format the date/time information according to the format string */
  while (*format && index < bufLen - 1)
    {
      switch (*format)
        {
        case '%':
          format++;
          switch (*format)
            {
            case '\0':
              /* end of the format string */
              break;
            case '%':
              /* literal '%' */
              buf[index] = '%';
              index++;
              format++;
              break;
            case 'Y':
              if (index + 4 >= bufLen)
                {
                  return bufLen;
                }
              index +=
                portLibrary->str_printf (portLibrary, buf + index,
                                         bufLen - index, "%04u", tm.year);
              format++;
              break;
            case 'm':
              if (index + 2 >= bufLen)
                {
                  return bufLen;
                }
              index +=
                portLibrary->str_printf (portLibrary, buf + index,
                                         bufLen - index, "%02u", tm.month);
              format++;
              break;
            case 'b':
              if (index + strlen (abbMonthName[tm.month - 1]) >= bufLen)
                {
                  return bufLen;
                }
              index +=
                portLibrary->str_printf (portLibrary, buf + index,
                                         bufLen - index, "%s",
                                         abbMonthName[tm.month - 1]);
              format++;
              break;
            case 'd':
              if (index + 2 >= bufLen)
                {
                  return bufLen;
                }
              index +=
                portLibrary->str_printf (portLibrary, buf + index,
                                         bufLen - index, "%02u", tm.day);
              format++;
              break;
            case 'H':
              if (index + 2 >= bufLen)
                {
                  return bufLen;
                }
              index +=
                portLibrary->str_printf (portLibrary, buf + index,
                                         bufLen - index, "%02u", tm.hour);
              format++;
              break;
            case 'M':
              if (index + 2 >= bufLen)
                {
                  return bufLen;
                }
              index +=
                portLibrary->str_printf (portLibrary, buf + index,
                                         bufLen - index, "%02u", tm.minute);
              format++;
              break;
            case 'S':
              if (index + 2 >= bufLen)
                {
                  return bufLen;
                }
              index +=
                portLibrary->str_printf (portLibrary, buf + index,
                                         bufLen - index, "%02u", tm.second);
              format++;
              break;
            default:
              /* ignore unsupported format specifiers */
              format++;
              break;
            }
          break;
        default:
          buf[index] = *format++;
          index++;
          break;
        }
    }

  buf[index] = 0;

  return index;
}

static void
gettimestruct (I_64 millis, struct HyTimeInfo *tm)
{
#define HYSFT_NUM_MONTHS         (12)
#define HYSFT_NUM_SECS_IN_MINUTE (60)
#define HYSFT_NUM_SECS_IN_HOUR   (60*HYSFT_NUM_SECS_IN_MINUTE)
#define HYSFT_NUM_SECS_IN_DAY    (24*(I_32)HYSFT_NUM_SECS_IN_HOUR)
#define HYSFT_NUM_SECS_IN_YEAR   (365*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_LEAP_YEAR (366*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_JAN (31*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_FEB (28*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_MAR (31*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_APR (30*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_MAY (31*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_JUN (30*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_JUL (31*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_AUG (31*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_SEP (30*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_OCT (31*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_NOV (30*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_DEC (31*HYSFT_NUM_SECS_IN_DAY)
#define HYSFT_NUM_SECS_IN_LEAP_FEB (29*HYSFT_NUM_SECS_IN_DAY)

  I_64 timeLeft;
  I_32 i;
  I_32 *secondsInMonth;
  I_32 normalSecondsInMonth[12] = {
    HYSFT_NUM_SECS_IN_JAN,
    HYSFT_NUM_SECS_IN_FEB,
    HYSFT_NUM_SECS_IN_MAR,
    HYSFT_NUM_SECS_IN_APR,
    HYSFT_NUM_SECS_IN_MAY,
    HYSFT_NUM_SECS_IN_JUN,
    HYSFT_NUM_SECS_IN_JUL,
    HYSFT_NUM_SECS_IN_AUG,
    HYSFT_NUM_SECS_IN_SEP,
    HYSFT_NUM_SECS_IN_OCT,
    HYSFT_NUM_SECS_IN_NOV,
    HYSFT_NUM_SECS_IN_DEC
  };
  I_32 leapYearSecondsInMonth[12] = {
    HYSFT_NUM_SECS_IN_JAN,
    HYSFT_NUM_SECS_IN_LEAP_FEB,
    HYSFT_NUM_SECS_IN_MAR,
    HYSFT_NUM_SECS_IN_APR,
    HYSFT_NUM_SECS_IN_MAY,
    HYSFT_NUM_SECS_IN_JUN,
    HYSFT_NUM_SECS_IN_JUL,
    HYSFT_NUM_SECS_IN_AUG,
    HYSFT_NUM_SECS_IN_SEP,
    HYSFT_NUM_SECS_IN_OCT,
    HYSFT_NUM_SECS_IN_NOV,
    HYSFT_NUM_SECS_IN_DEC
  };
  BOOLEAN leapYear = FALSE;

  if (!tm)
    return;

  memset (tm, 0, sizeof (struct HyTimeInfo));

  tm->year = 1970;

  /* -- obtain the current time in seconds */
  timeLeft = millis / 1000;
  /* -- determine the year */
  while (timeLeft)
    {
      I_64 numSecondsInAYear = HYSFT_NUM_SECS_IN_YEAR;
      leapYear = FALSE;
      if (tm->year % 4 == 0)
        {
          /* potential leap year */
          if ((tm->year % 100 != 0) || (tm->year % 400 == 0))
            {
              /* we have leap year! */
              leapYear = TRUE;
              numSecondsInAYear = HYSFT_NUM_SECS_IN_LEAP_YEAR;
            }
        }
      if (timeLeft < numSecondsInAYear)
        {
          /* under a year's time left */
          break;
        }
      /* increment the year and take the appropriate number 
         of seconds off the timeLeft */
      tm->year++;
      timeLeft -= numSecondsInAYear;
    }
  /* -- determine the month */
  if (leapYear)
    {
      secondsInMonth = leapYearSecondsInMonth;
    }
  else
    {
      secondsInMonth = normalSecondsInMonth;
    }
  for (i = 0; i < HYSFT_NUM_MONTHS; i++)
    {
      if (timeLeft >= secondsInMonth[i])
        {
          timeLeft -= secondsInMonth[i];
        }
      else
        {
          break;
        }
    }
  tm->month = i + 1;
  /* -- determine the day of the month */
  tm->day = 1;
  while (timeLeft)
    {
      if (timeLeft >= HYSFT_NUM_SECS_IN_DAY)
        {
          timeLeft -= HYSFT_NUM_SECS_IN_DAY;
        }
      else
        {
          break;
        }
      tm->day++;
    }
  /* -- determine the hour of the day */
  tm->hour = 0;
  while (timeLeft)
    {
      if (timeLeft >= HYSFT_NUM_SECS_IN_HOUR)
        {
          timeLeft -= HYSFT_NUM_SECS_IN_HOUR;
        }
      else
        {
          break;
        }
      tm->hour++;
    }
  /* -- determine the minute of the hour */
  tm->minute = 0;
  while (timeLeft)
    {
      if (timeLeft >= HYSFT_NUM_SECS_IN_MINUTE)
        {
          timeLeft -= HYSFT_NUM_SECS_IN_MINUTE;
        }
      else
        {
          break;
        }
      tm->minute++;
    }
  /* -- and the rest is seconds */
  tm->second = (U_32) timeLeft;

#undef HYSFT_NUM_MONTHS
#undef HYSFT_NUM_SECS_IN_MINUTE
#undef HYSFT_NUM_SECS_IN_HOUR
#undef HYSFT_NUM_SECS_IN_DAY
#undef HYSFT_NUM_SECS_IN_YEAR
#undef HYSFT_NUM_SECS_IN_LEAP_YEAR
#undef HYSFT_NUM_SECS_IN_JAN
#undef HYSFT_NUM_SECS_IN_FEB
#undef HYSFT_NUM_SECS_IN_MAR
#undef HYSFT_NUM_SECS_IN_APR
#undef HYSFT_NUM_SECS_IN_MAY
#undef HYSFT_NUM_SECS_IN_JUN
#undef HYSFT_NUM_SECS_IN_JUL
#undef HYSFT_NUM_SECS_IN_AUG
#undef HYSFT_NUM_SECS_IN_SEP
#undef HYSFT_NUM_SECS_IN_OCT
#undef HYSFT_NUM_SECS_IN_NOV
#undef HYSFT_NUM_SECS_IN_DEC
#undef HYSFT_NUM_SECS_IN_LEAP_FEB
}
