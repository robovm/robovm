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
 * @brief file
 */

#undef CDEV_CURRENT_FUNCTION

/* __STDC_ISO_10646__ indicates that the platform wchar_t encoding is Unicode */
/* but older versions of libc fail to set the flag, even though they are Unicode */
#if defined(__STDC_ISO_10646__) || defined(LINUX) || defined(FREEBSD)
#define HYVM_USE_WCTOMB
#else
#define HYVM_USE_ICONV
#endif

#if defined(HYVM_USE_ICONV)
#include <iconv.h>
#endif

#include <nl_types.h>
#include <langinfo.h>
#include <errno.h>
#include <stdlib.h>
#include <string.h>
#include "hyport.h"

/* Some older platforms (Netwinder) don't declare CODESET */
#if !defined(CODESET)
#define CODESET _NL_CTYPE_CODESET_NAME
#endif

/* a2e overrides nl_langinfo to return ASCII strings. We need the native EBCDIC string */
#if defined(ZOS) && defined (nl_langinfo)
#undef nl_langinfo
#endif

#define CDEV_CURRENT_FUNCTION _prototypes_private
#if (defined(HYVM_USE_ICONV))
static IDATA file_write_using_iconv (struct HyPortLibrary *portLibrary,
                                     IDATA fd, const char *buf, IDATA nbytes);
#endif /* HYVM_USE_ICONV */

#if (defined(HYVM_USE_ICONV))
static char* buf_write_using_iconv (struct HyPortLibrary *portLibrary,
                                     const char *buf, IDATA nbytes);
#endif /* HYVM_USE_ICONV */

#if (defined(HYVM_USE_WCTOMB))
#include "utf8decode.h"
#endif

#if (defined(HYVM_USE_WCTOMB))
static IDATA walkUTF8String (const U_8 * buf, IDATA nbytes);
#endif /* HYVM_USE_WCTOMB */

#if (defined(HYVM_USE_WCTOMB))
static void translateUTF8String (const U_8 * in, U_8 * out, IDATA nbytes);
#endif /* HYVM_USE_WCTOMB */

#if (defined(HYVM_USE_WCTOMB))
static IDATA file_write_using_wctomb (struct HyPortLibrary *portLibrary,
                                      IDATA fd, const char *buf,
                                      IDATA nbytes);
#endif /* HYVM_USE_WCTOMB */

#if (defined(HYVM_USE_WCTOMB))
static char* buf_write_using_wctomb (struct HyPortLibrary *portLibrary,
                                      const char *buf, IDATA nbytes);
#endif /* HYVM_USE_WCTOMB */

#undef CDEV_CURRENT_FUNCTION


#define CDEV_CURRENT_FUNCTION hybuf_write_text
/**
* Output the buffer onto the another buffer as text. The in buffer is a UTF8-encoded array of chars.
* It is converted to the appropriate platform encoding.
*
* @param[in] portLibrary The port library
* @param[in] buf buffer of text to be converted.
* @param[in] nbytes size of buffer of text to be converted.
*
* @return buffer of converted to the appropriate platform encoding text.
*/
char *VMCALL
hybuf_write_text (struct HyPortLibrary * portLibrary,
                  const char *buf, IDATA nbytes)
{
  char* outBuf = NULL;
  IDATA i;
  int requiresTranslation = 0;
#ifdef ZOS
#pragma convlit(suspend)
#endif /* ZOS */
  const char *utf8Encoding = "UTF-8";
#ifdef ZOS
#pragma convlit(resume)
#endif /* ZOS */

#ifdef ZOS
  /* z/OS always needs to translate to EBCDIC */
  requiresTranslation = 1;
#else
  /* we can short circuit if the string is all ASCII */
  for (i = 0; i < nbytes; i++)
  {
      if ((U_8) buf[i] >= 0x80)
      {
          requiresTranslation = 1;
          break;
      }
  }
#endif /* ZOS */

  if (!requiresTranslation
      || strcmp (nl_langinfo (CODESET), utf8Encoding) == 0)
  {
      /* We're in luck! No transformations are necessary */
      outBuf = portLibrary->mem_allocate_memory (portLibrary, nbytes + 1);
      memcpy(outBuf, buf, nbytes);
      outBuf[nbytes] = '\0';
      return outBuf;
  }

#if defined(HYVM_USE_WCTOMB)
  return buf_write_using_wctomb (portLibrary, buf, nbytes);
#else
  return buf_write_using_iconv (portLibrary, buf, nbytes);
#endif

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_write_text
/**
 * Output the buffer onto the stream as text. The buffer is a UTF8-encoded array of chars.
 * It is converted to the appropriate platform encoding.
 *
 * @param[in] portLibrary The port library
 * @param[in] fd the file descriptor.
 * @param[in] buf buffer of text to be output.
 * @param[in] nbytes size of buffer of text to be output.
 *
 * @return 0 on success, negative error code on failure.
 */
IDATA VMCALL
hyfile_write_text (struct HyPortLibrary *portLibrary, IDATA fd,
                   const char *buf, IDATA nbytes)
{
  IDATA result, i;
  int requiresTranslation = 0;

#ifdef ZOS
#pragma convlit(suspend)
#endif
  const char *utf8Encoding = "UTF-8";
#ifdef ZOS
#pragma convlit(resume)
#endif

#ifdef ZOS
  /* z/OS always needs to translate to EBCDIC */
  requiresTranslation = 1;
#else
  /* we can short circuit if the string is all ASCII */
  for (i = 0; i < nbytes; i++)
    {
      if ((U_8) buf[i] >= 0x80)
        {
          requiresTranslation = 1;
          break;
        }
    }
#endif /* ZOS */

  if (!requiresTranslation
      || strcmp (nl_langinfo (CODESET), utf8Encoding) == 0)
    {
      /* We're in luck! No transformations are necessary */
      result =
        portLibrary->file_write (portLibrary, fd, (void *) buf, nbytes);
      return (result == nbytes) ? 0 : result;
    }

#if defined(HYVM_USE_WCTOMB)
  return file_write_using_wctomb (portLibrary, fd, buf, nbytes);
#else
  return file_write_using_iconv (portLibrary, fd, buf, nbytes);
#endif

}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION walkUTF8String
#if (defined(HYVM_USE_WCTOMB))
/** 
 * @internal walk the given UTF8 string, looking for non-ASCII characters.
 * @return  0 if none were found, or, if non-ASCII strings were found,
 * answer the length of the buffer if it were converted to platform
 * encoding
 *
 * @note this relies on the assumption that wide chars are Unicode.
 * If not, the platform will need different support for this
 */
static IDATA
walkUTF8String (const U_8 * buf, IDATA nbytes)
{
  const U_8 *end = buf + nbytes;
  const U_8 *cursor = buf;
  IDATA newLength = 0;
  int hasHighChars = 0;
  int wcresult;
  /* reset the shift state */
  wcresult = wctomb (NULL, 0);
  while (cursor < end)
    {
      if ((*cursor & 0x80) == 0x80)
        {
          char temp[MB_CUR_MAX];
          U_16 unicode;
          U_32 numberU8Consumed =
            decodeUTF8CharN (cursor, &unicode, end - cursor);
          if (numberU8Consumed == 0)
            {
              /* an illegal encoding was encountered! Don't try to decode the string */
              return 0;
            }
          cursor += numberU8Consumed;
          /* calculate the encoded length of this character */
          wcresult = wctomb (temp, (wchar_t) unicode);
          if (wcresult == -1)
            {
              /* an un-encodable char was encountered */
              newLength += 1;
            }
          else
            {
              newLength += wcresult;
            }
          hasHighChars = 1;
        }
      else
        {
          newLength += 1;
          cursor += 1;
        }
    }
  return hasHighChars ? newLength : 0;
}
#endif /* HYVM_USE_WCTOMB */

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION translateUTF8String
#if (defined(HYVM_USE_WCTOMB))
/**
 * @internal assumes that the input has already been validated by walkUTF8String
 */
static void
translateUTF8String (const U_8 * in, U_8 * out, IDATA nbytes)
{
  const U_8 *end = in + nbytes;
  const U_8 *cursor = in;
  /* walk the string again, translating it */
  while (cursor < end)
    {
      U_32 numberU8Consumed;
      if ((*cursor & 0x80) == 0x80)
        {
          U_16 unicode;
          int wcresult;
          numberU8Consumed = decodeUTF8Char (cursor, &unicode);
          cursor += numberU8Consumed;
          wcresult = wctomb ((char *) out, (wchar_t) unicode);
          if (wcresult == -1)
            {
              *out++ = '?';
            }
          else
            {
              out += wcresult;
            }
        }
      else
        {
          *out++ = *cursor++;
        }
    }
}
#endif /* HYVM_USE_WCTOMB */

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION file_write_using_wctomb
#if (defined(HYVM_USE_WCTOMB))
static IDATA
file_write_using_wctomb (struct HyPortLibrary *portLibrary, IDATA fd,
                         const char *buf, IDATA nbytes)
{
  IDATA result;
  IDATA newLength = 0;
  char stackBuf[512];
  char *newBuf = stackBuf;
  newLength = walkUTF8String ((U_8 *) buf, nbytes);
  if (newLength)
    {
      if (newLength > sizeof (stackBuf))
        {
          newBuf = portLibrary->mem_allocate_memory (portLibrary, newLength);
        }
      if (newBuf)
        {
          translateUTF8String ((const U_8*)buf, (U_8 *)newBuf, nbytes);
          buf = newBuf;
          nbytes = newLength;
        }
    }
  result = portLibrary->file_write (portLibrary, fd, (void *) buf, nbytes);
  if (newBuf != stackBuf && newBuf != NULL)
    {
      portLibrary->mem_free_memory (portLibrary, newBuf);
    }
  return (result == nbytes) ? 0 : result;
}
#endif /* HYVM_USE_WCTOMB */

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION buf_write_using_wctomb
#if (defined(HYVM_USE_WCTOMB))
static char*
buf_write_using_wctomb (struct HyPortLibrary *portLibrary, const char *buf,
                         IDATA nbytes)
{
    IDATA newLength = 0;
    char *outBuf = (char*)buf;
    newLength = walkUTF8String ((U_8 *) buf, nbytes);
    if (newLength)
    {
        outBuf = portLibrary->mem_allocate_memory (portLibrary, newLength + 1);
        if (outBuf)
        {
            translateUTF8String ((const U_8 *)buf, (U_8 *) outBuf, nbytes);
            nbytes = newLength;
            outBuf[nbytes] = '\0';
        } else
        {
            outBuf = portLibrary->mem_allocate_memory (portLibrary, nbytes + 1);
            memcpy(outBuf, buf, nbytes);
            outBuf[nbytes] = '\0';
        }
        return outBuf;
    }
    outBuf = portLibrary->mem_allocate_memory (portLibrary, nbytes + 1);
    memcpy(outBuf, buf, nbytes);
    outBuf[nbytes] = '\0';
    return outBuf;
}
#endif /* HYVM_USE_WCTOMB */

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION file_write_using_iconv
#if (defined(HYVM_USE_ICONV))
static IDATA
file_write_using_iconv (struct HyPortLibrary *portLibrary, IDATA fd,
                        const char *buf, IDATA nbytes)
{
  IDATA result, i;
  IDATA newLength = 0;
  char stackBuf[512];
  char *bufStart;
  UDATA outBufLen = sizeof (stackBuf);
  iconv_t converter;
  size_t inbytesleft, outbytesleft;
  char *inbuf, *outbuf;

/* iconv_open is not an a2e function, so we need to pass it honest-to-goodness EBCDIC strings */
#ifdef ZOS
#pragma convlit(suspend)
#endif /* ZOS */
  converter = iconv_open (nl_langinfo (CODESET), "UTF-8");
#ifdef ZOS
#pragma convlit(resume)
#endif /* ZOS */

  if (converter == (iconv_t) - 1)
    {
      /* no converter available for this code set. Just dump the UTF-8 chars */
      result =
        portLibrary->file_write (portLibrary, fd, (void *) buf, nbytes);
      return (result == nbytes) ? 0 : result;
    }
  inbuf = (char *) buf;         /* for some reason this argument isn't const */
  outbuf = bufStart = stackBuf;
  inbytesleft = nbytes;
  outbytesleft = sizeof (stackBuf);
  while ((size_t) - 1 ==
         iconv (converter, &inbuf, &inbytesleft, &outbuf, &outbytesleft))
    {
      if (errno == E2BIG)
        {
          /* grow the buffer by 512 more bytes */
          char *newBuf =
            portLibrary->mem_allocate_memory (portLibrary, outBufLen += 512);
          if (newBuf == NULL)
            {
              break;            /* just output what we've got so far */
            }
          /* copy over the work we've already done */
          memcpy (newBuf, bufStart, outbuf - bufStart);
          /* set up the new buffer, and free the old one */
          outbytesleft = outBufLen - (outbuf - bufStart);
          outbuf = newBuf + (outbuf - bufStart);
          if (bufStart != stackBuf)
            {
              portLibrary->mem_free_memory (portLibrary, bufStart);
            }
          bufStart = newBuf;
        }
      else
        {
          /* failure -- just output the unconverted data */
          iconv_close (converter);
          result =
            portLibrary->file_write (portLibrary, fd, (void *) buf, nbytes);
          return (result == nbytes) ? 0 : result;
        }
    }
  iconv_close (converter);
  result =
    portLibrary->file_write (portLibrary, fd, (void *) bufStart,
                             outbuf - bufStart);
  if (bufStart != stackBuf)
    {
      portLibrary->mem_free_memory (portLibrary, bufStart);
    }
  return (result == nbytes) ? 0 : result;
}
#endif /* HYVM_USE_ICONV */

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION buf_write_using_iconv
#if (defined(HYVM_USE_ICONV))
static char*
buf_write_using_iconv (struct HyPortLibrary *portLibrary,
                        const char *buf, IDATA nbytes)
{
    UDATA outBufLen = 512;
    iconv_t converter;
    size_t inbytesleft, outbytesleft;
    char *inbuf;
    char *outbuf;
    char *bufStart;
    /* iconv_open is not an a2e function, so we need to pass it honest-to-goodness EBCDIC strings */
    converter = iconv_open (nl_langinfo (CODESET), "UTF-8");
    if (converter == (iconv_t) - 1)
    {
        /* no converter available for this code set. Just dump the UTF-8 chars */
        outbuf = portLibrary->mem_allocate_memory (portLibrary, nbytes + 1);
        memcpy(outbuf, buf, nbytes);
        outbuf[nbytes] = '\0';
        return  outbuf;
    }
    bufStart = portLibrary->mem_allocate_memory (portLibrary, outBufLen);
    outbuf = bufStart;
    inbuf = (char *) buf;         /* for some reason this argument isn't const */
    inbytesleft = nbytes;
    outbytesleft = outBufLen;
    while ((size_t) - 1 ==
        iconv (converter, &inbuf, &inbytesleft, &outbuf, &outbytesleft))
    {
        if (errno == E2BIG)
        {
            /* grow the buffer by 512 more bytes */
            char *newBuf =
                portLibrary->mem_allocate_memory (portLibrary, outBufLen += 512);
            if (newBuf == NULL)
            {
                break;            /* just output what we've got so far */
            }
            /* copy over the work we've already done */
            memcpy (newBuf, bufStart, outbuf - bufStart);
            /* set up the new buffer, and free the old one */
            outbytesleft = outBufLen - (outbuf - bufStart);
            outbuf = newBuf + (outbuf - bufStart);
            portLibrary->mem_free_memory (portLibrary, bufStart);
            bufStart = newBuf;
        }
        else
        {
            /* failure -- just output the unconverted data */
            iconv_close (converter);
            outbuf = portLibrary->mem_allocate_memory (portLibrary, nbytes + 1);
            memcpy(outbuf, buf, nbytes);
            outbuf[nbytes] = '\0';
            portLibrary->mem_free_memory (portLibrary, bufStart);
            return  outbuf;
        }
    }
    iconv_close (converter);
    outbuf = portLibrary->mem_allocate_memory (portLibrary, outbuf - bufStart + 1);
    memcpy(outbuf, buf, outbuf - bufStart);
    outbuf[outbuf - bufStart] = '\0';
    portLibrary->mem_free_memory (portLibrary, bufStart);
    return outbuf;
}
#endif /* HYVM_USE_ICONV */

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_read_text
/**
 * Read a line of text from the file into buf.  Text is converted from the platform file encoding to UTF8.
 * This is mostly equivalent to fgets in standard C.
 *
 * @param[in] portLibrary The port library
 * @param[in] fd File descriptor.
 * @param[in,out] buf Buffer for read in text.
 * @param[in] nbytes Size of buffer.
 *
 * @return buf on success, NULL on failure.
 */
char *VMCALL
hyfile_read_text (struct HyPortLibrary *portLibrary, IDATA fd, char *buf,
                  IDATA nbytes)
{
  char temp[64];
  IDATA count, i;
  char *cursor = buf;

  if (nbytes <= 0)
    {
      return 0;
    }

  /* discount 1 for the trailing NUL */
  nbytes -= 1;

  while (nbytes)
    {
      count = sizeof (temp) > nbytes ? nbytes : sizeof (temp);
      count = portLibrary->file_read (portLibrary, fd, temp, count);

      /* ignore translation for now */
      if (count < 0)
        {
          if (cursor == buf)
            {
              return NULL;
            }
          else
            {
              break;
            }
        }

      for (i = 0; i < count; i++)
        {
          char c = temp[i];
          *cursor++ = c;

          if (c == '\n')
            {                   /* EOL */
              portLibrary->file_seek (portLibrary, fd, i - count + 1,
                                      HySeekCur);
              *cursor = '\0';
              return buf;
            }
        }
      nbytes -= count;
    }

  *cursor = '\0';
  return buf;
}

#undef CDEV_CURRENT_FUNCTION
