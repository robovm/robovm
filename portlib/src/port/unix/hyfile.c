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

#include <stdlib.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <dirent.h>
#include <fcntl.h>
#include <time.h>

#include "hyport.h"
#include "hysock.h"
#include "hystdarg.h"
#include "portnls.h"
#include "ut_hyprt.h"

#ifdef ZOS
#define FD_BIAS 1000
#undef fwrite
#undef fread
#else
#define FD_BIAS 0
#endif /* ZOS */

#define CDEV_CURRENT_FUNCTION _prototypes_private
static I_32 EsTranslateOpenFlags (I_32 flags);
static I_32 findError (I_32 errorCode);

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION EsTranslateOpenFlags
static I_32
EsTranslateOpenFlags (I_32 flags)
{
  I_32 realFlags = 0;

  if (flags & HyOpenAppend)
    {
      realFlags |= O_APPEND;
    }
  if (flags & HyOpenTruncate)
    {
      realFlags |= O_TRUNC;
    }
  if (flags & HyOpenCreate)
    {
      realFlags |= O_CREAT;
    }
  if (flags & HyOpenCreateNew)
    {
      realFlags |= O_EXCL | O_CREAT;
    }
#ifdef O_SYNC
	if (flags & HyOpenSync) {
		realFlags |= O_SYNC;
	}
#endif    
  if (flags & HyOpenRead)
    {
      if (flags & HyOpenWrite)
        {
          return (O_RDWR | realFlags);
        }
      return (O_RDONLY | realFlags);
    }
  if (flags & HyOpenWrite)
    {
      return (O_WRONLY | realFlags);
    }
  return -1;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION findError
/**
 * @internal
 * Determines the proper portable error code to return given a native error code
 *
 * @param[in] errorCode The error code reported by the OS
 *
 * @return	the (negative) portable error code
 */
static I_32
findError (I_32 errorCode)
{
  switch (errorCode)
    {
    case EACCES:
    case EPERM:
      return HYPORT_ERROR_FILE_NOPERMISSION;
    case ENAMETOOLONG:
      return HYPORT_ERROR_FILE_NAMETOOLONG;
    case ENOENT:
      return HYPORT_ERROR_FILE_NOENT;
    case ENOTDIR:
      return HYPORT_ERROR_FILE_NOTDIR;
    case ELOOP:
      return HYPORT_ERROR_FILE_LOOP;

    case EBADF:
      return HYPORT_ERROR_FILE_BADF;
    case EEXIST:
      return HYPORT_ERROR_FILE_EXIST;
    case ENOSPC:
    case EFBIG:
      return HYPORT_ERROR_FILE_DISKFULL;
    default:
      return HYPORT_ERROR_FILE_OPFAILED;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_close
/**
 * Closes a file descriptor.
 *
 * @param[in] portLibrary The port library
 * @param[in] fd The file descriptor.
 *
 * @return 0 on success, -1 on failure.
 * @internal @todo return negative portable return code on failure.
 */
I_32 VMCALL
hyfile_close (struct HyPortLibrary * portLibrary, IDATA fd)
{

#if (FD_BIAS != 0)
    if (fd < FD_BIAS) {
        /* Cannot close STD streams, and no other FD's should exist <FD_BIAS */
	    return -1;
    }
#endif

    return close ((int) (fd - FD_BIAS));
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_open
/**
 * Convert a pathname into a file descriptor.
 *
 * @param[in] portLibrary The port library
 * @param[in] path Name of the file to be opened.
 * @param[in] flags Portable file read/write attributes.
 * @param[in] mode Platform file permissions.
 *
 * @return The file descriptor of the newly opened file, -1 on failure.
 */
IDATA VMCALL
hyfile_open (struct HyPortLibrary *portLibrary, const char *path, I_32 flags,
             I_32 mode)
{
  struct stat buffer;
  I_32 fd;
  I_32 realFlags = EsTranslateOpenFlags (flags);
  I_32 fdflags;

  Trc_PRT_file_open_Entry (path, flags, mode);

  if (realFlags == -1)
    {
      Trc_PRT_file_open_Exit1 (flags);
      portLibrary->error_set_last_error (portLibrary, EINVAL,
                                         findError (EINVAL));
      return -1;
    }

  if ( ( flags&HyOpenRead && !(flags&HyOpenWrite) )  && !stat (path, &buffer))
    {
      if (S_ISDIR (buffer.st_mode))
        {
          portLibrary->error_set_last_error_with_message (portLibrary,
                                                          findError (EEXIST),
                                                          "Is a directory");
          Trc_PRT_file_open_Exit4 ();
          return -1;
        }
    }

  fd = open (path, realFlags, mode);

  if (-1 == fd)
    {
      Trc_PRT_file_open_Exit2 (errno, findError (errno));
      portLibrary->error_set_last_error (portLibrary, errno,
                                         findError (errno));
      return -1;
    }

  /* Tag this descriptor as being non-inheritable */
  fdflags = fcntl (fd, F_GETFD, 0);
  fcntl (fd, F_SETFD, fdflags | FD_CLOEXEC);

  fd += FD_BIAS;
  Trc_PRT_file_open_Exit (fd);
  return (IDATA) fd;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_unlink
/**
 * Remove a file from the file system.
 *
 * @param[in] portLibrary The port library
 * @param[in] path file/path name to remove.
 *
 * @return 0 on success, -1 on failure.
 * @internal @todo return negative portable return code on failure.
 */
I_32 VMCALL
hyfile_unlink (struct HyPortLibrary * portLibrary, const char *path)
{
  return unlink (path);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_write
/**
 * Write to a file.
 *
 * Writes up to nbytes from the provided buffer  to the file referenced by the file descriptor.
 *
 * @param[in] portLibrary The port library
 * @param[in] fd File descriptor to write.
 * @param[in] buf Buffer to be written.
 * @param[in] nbytes Size of buffer.
 *
 * @return Number of bytes written on success,  -1 on failure.
 * @internal @todo return negative portable return code on failure.
 */
IDATA VMCALL
hyfile_write (struct HyPortLibrary * portLibrary, IDATA fd, const void *buf,
              IDATA nbytes)
{
  IDATA rc = 0;

#ifdef ZOS
  if (fd == HYPORT_TTY_OUT) {
    rc = fwrite(buf, sizeof(char), nbytes, stdout);
  } else if (fd == HYPORT_TTY_ERR) {
    rc = fwrite(buf, sizeof(char), nbytes, stderr);
  } else if (fd < FD_BIAS) {
    /* Cannot fsync STDIN, and no other FD's should exist <FD_BIAS */
    return -1;
  } else 
#endif /* ZOS */
  {
  /* write will just do the right thing for HYPORT_TTY_OUT and HYPORT_TTY_ERR */
    rc = write ((int) (fd - FD_BIAS), buf, nbytes);
  }

  if (rc == -1)
    {
      return portLibrary->error_set_last_error (portLibrary, errno,
                                                findError (errno));
    }
  return rc;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_read
/**
 * Read bytes from a file descriptor into a user provided buffer.
 *
 * @param[in] portLibrary The port library
 * @param[in] fd The file descriptor.
 * @param[in,out] buf Buffer to read into.
 * @param[in] nbytes Size of buffer.
 *
 * @return The number of bytes read, or -1 on failure.
 */
IDATA VMCALL
hyfile_read (struct HyPortLibrary * portLibrary, IDATA fd, void *buf,
             IDATA nbytes)
{
  IDATA result;
  if (nbytes == 0)
    {
      return 0;
    }

#ifdef ZOS
  if (fd == HYPORT_TTY_IN) {
    result = fread(buf, sizeof(char), nbytes, stdin);
  }  else	if (fd < FD_BIAS) {
    /* Cannot read from STDOUT/ERR, and no other FD's should exist <FD_BIAS */
    return -1;
  } else
#endif /* ZOS */
  {
    result = read ((int) (fd - FD_BIAS), buf, nbytes);
  }

  if (result == 0)
    {
      return -1;
    }
  else
    {
      return result;
    }
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_seek
/**
 * Repositions the offset of the file descriptor to a given offset as per directive whence.
 *
 * @param[in] portLibrary The port library
 * @param[in] fd The file descriptor.
 * @param[in] offset The offset in the file to position to.
 * @param[in] whence Portable constant describing how to apply the offset.
 *
 * @return The resulting offset on success, -1 on failure.
 * @note whence is one of HySeekSet (seek from beginning of file), 
 * HySeekCur (seek from current file pointer) or HySeekEnd (seek backwards from
 * end of file).
 * @internal @note seek operations return -1 on failure.  Negative offsets
 * can be returned when seeking beyond end of file.
 */
I_64 VMCALL
hyfile_seek (struct HyPortLibrary * portLibrary, IDATA inFD, I_64 offset,
             I_32 whence)
{
  int fd = (int)inFD;
  off_t localOffset = (off_t) offset;

  if ((whence < HySeekSet) || (whence > HySeekEnd))
    {
      return -1;
    }

  /* If file offsets are 32 bit, truncate the seek to that range */
  if (sizeof (off_t) < sizeof (I_64))
    {
      if (offset > 0x7FFFFFFF)
        {
          localOffset = 0x7FFFFFFF;
        }
      else if (offset < -0x7FFFFFFF)
        {
          localOffset = -0x7FFFFFFF;
        }
    }

#if (FD_BIAS != 0)
	if (fd < FD_BIAS) {
		/* Cannot seek on STD streams, and no other FD's should exist <FD_BIAS */
		return -1;
	}
#endif

  return (I_64) lseek ((int) (fd - FD_BIAS), localOffset, whence);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_attr
/**
 * Determine whether path is a file or directory.
 *
 * @param[in] portLibrary The port library
 * @param[in] path file/path name being queried.
 *
 * @return HyIsFile if a file, HyIsDir if a directory, negative portable error code on failure.
 */
I_32 VMCALL
hyfile_attr (struct HyPortLibrary *portLibrary, const char *path)
{
  struct stat buffer;

  /* Neutrino does not handle NULL for stat */

  if (stat (path, &buffer))
    {
      return portLibrary->error_set_last_error (portLibrary, errno,
                                                findError (errno));
    }
  if (S_ISDIR (buffer.st_mode))
    {
      return HyIsDir;
    }
  return HyIsFile;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_findclose
/**
 * Close the handle returned from @ref hyfile_findfirst.
 *
 * @param[in] portLibrary The port library
 * @param[in] findhandle  Handle returned from @ref hyfile_findfirst.
 */
void VMCALL
hyfile_findclose (struct HyPortLibrary *portLibrary, UDATA findhandle)
{
#if defined(_AIX)
  closedir64 ((DIR64 *) findhandle);
#else
  closedir ((DIR *) findhandle);
#endif


}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_findfirst
/**
 * Find the first occurrence of a file identified by path.  Answers a handle
 * to be used in subsequent calls to @ref hyfile_findnext and @ref hyfile_findclose. 
 *
 * @param[in] portLibrary The port library
 * @param[in] path file/path name being queried.
 * @param[out] resultbuf filename and path matching path.
 *
 * @return valid handle on success, -1 on failure.
 */
UDATA VMCALL
hyfile_findfirst (struct HyPortLibrary *portLibrary, const char *path,
                  char *resultbuf)
{
#if defined(_AIX)
  DIR64 *dirp = NULL;
  struct dirent64 *entry;
#else
  DIR *dirp = NULL;
  struct dirent *entry;
#endif


#if defined(_AIX)
  dirp = opendir64 (path);
#else
  dirp = opendir (path);
#endif


  if (dirp == NULL)
    {
      return (UDATA) - 1;
    }
#if defined(_AIX)
  entry = readdir64 (dirp);
#else
  entry = readdir (dirp);
#endif


  if (entry == NULL)
    {
#if defined(_AIX)
      closedir64 (dirp);
#else
      closedir (dirp);
#endif


      return (UDATA) - 1;
    }
  strcpy (resultbuf, entry->d_name);
  return (UDATA) dirp;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_findnext
/**
 * Find the next filename and path matching a given handle.
 *
 * @param[in] portLibrary The port library
 * @param[in] findhandle handle returned from @ref hyfile_findfirst.
 * @param[out] resultbuf next filename and path matching findhandle.
 *
 * @return 0 on success, -1 on failure or if no matching entries.
 * @internal @todo return negative portable return code on failure.
 */
I_32 VMCALL
hyfile_findnext (struct HyPortLibrary * portLibrary, UDATA findhandle,
                 char *resultbuf)
{
#if defined(_AIX)
  struct dirent64 *entry;
#else
  struct dirent *entry;
#endif


#if defined(_AIX)
  entry = readdir64 ((DIR64 *) findhandle);
#else
  entry = readdir ((DIR *) findhandle);
#endif


  if (entry == NULL)
    {
      return -1;
    }
  strcpy (resultbuf, entry->d_name);
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_lastmod
/**
 *  Return the last modification time of the file path in milliseconds.
 *
 * @param[in] portLibrary The port library
 * @param[in] path file/path name being queried.
 *
 * @return last modification time on success, -1 on failure.
 */
I_64 VMCALL
hyfile_lastmod (struct HyPortLibrary * portLibrary, const char *path)
{
  struct stat st;
  tzset ();

  /* Neutrino does not handle NULL for stat */

  if (stat (path, &st))
    {
      return -1;
    }
  return (U_64)st.st_mtime * 1000;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_length
/**
 * Answer the length in bytes of the file.
 *
 * @param[in] portLibrary The port library
 * @param[in] path file/path name being queried.
 *
 * @return Length in bytes of the file on success, negative portable error code on failure
 */
I_64 VMCALL
hyfile_length (struct HyPortLibrary * portLibrary, const char *path)
{
  struct stat st;

  /* Neutrino does not handle NULL for stat */

  if (stat (path, &st))
    {
      return portLibrary->error_set_last_error (portLibrary, errno,
                                                findError (errno));
    }
  return (I_64) st.st_size;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_mkdir
/**
 * Create a directory.
 *
 * @param[in] portLibrary The port library
 * @param[in] path Directory to be created.
 *
 * @return 0 on success, -1 on failure.
 * @note Assumes all components of path up to the last directory already exist. 
 * @internal @todo return negative portable return code on failure.
 */
I_32 VMCALL
hyfile_mkdir (struct HyPortLibrary * portLibrary, const char *path)
{
  if (-1 == mkdir (path, S_IRWXU | S_IRWXG | S_IRWXO))
    {
      portLibrary->error_set_last_error (portLibrary, errno,
                                         findError (errno));
      return -1;
    }

  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_move
/**
 * Move the file pathExist to a new name pathNew.
 *
 * @param[in] portLibrary The port library
 * @param[in] pathExist The existing file name.
 * @param[in] pathNew The new file name.
 *
 * @return 0 on success, -1 on failure.
 * @internal @todo return negative portable return code on failure.
 */
I_32 VMCALL
hyfile_move (struct HyPortLibrary * portLibrary, const char *pathExist,
             const char *pathNew)
{
  return rename (pathExist, pathNew);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_unlinkdir
/**
 * Remove the trailing directory of the path. If the path is a symbolic link to a directory, remove
 * the symbolic link.
 *
 * @param[in] portLibrary The port library
 * @param[in] path directory name being removed.
 *
 * @return 0 on success, -1 on failure.
 * @internal @todo return negative portable return code on failure..
 */
I_32 VMCALL
hyfile_unlinkdir (struct HyPortLibrary * portLibrary, const char *path)
{
  /* Call remove() so symbolic links to directories are removed */

  /* QNX has modified the API for remove and rmdir. */
  /* Remove does not call rmdir automagically like every other Unix. */

  return remove (path);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_sync
/**
 * Synchronize a file's state with the state on disk.
 *
 * @param[in] portLibrary The port library
 * @param[in] fd The file descriptor.
 *
 * @return 0 on success, -1 on failure.
 * @internal @todo return negative portable return code on failure.
 */
I_32 VMCALL
hyfile_sync (struct HyPortLibrary * portLibrary, IDATA inFD)
{
  int fd = (int)inFD;

#ifdef ZOS
	 if (fd == HYPORT_TTY_OUT) {
		return fflush(stdout);
	} else if (fd == HYPORT_TTY_ERR) {
		return fflush(stderr);
	} else if (fd < FD_BIAS) {
		/* Cannot fsync STDIN, and no other FD's should exist <FD_BIAS */
		return -1;
	}
#endif /* ZOS */

  return fsync ((int) (fd - FD_BIAS));
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_error_message
/**
 * Return an error message describing the last OS error that occurred.  The last
 * error returned is not thread safe, it may not be related to the operation that
 * failed for this thread.
 *
 * @param[in] portLibrary The port library
 *
 * @return	error message describing the last OS error, may return NULL.
 *
 * @internal
 * @note  This function gets the last error code from the OS and then returns
 * the corresponding string.  It is here as a helper function for JCL.  Once hyerror
 * is integrated into the port library this function should probably disappear.
 */
const char *VMCALL
hyfile_error_message (struct HyPortLibrary *portLibrary)
{
  I_32 errorCode;

  errorCode =
    portLibrary->error_set_last_error (portLibrary, errno, findError (errno));
  return portLibrary->error_last_error_message (portLibrary);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_shutdown
/**
 * PortLibrary shutdown.
 *
 * This function is called during shutdown of the portLibrary.  Any resources that were created by @ref hyfile_startup
 * should be destroyed here.
 *
 * @param[in] portLibrary The port library
 *
 * @note Most implementations will be empty.
 */
void VMCALL
hyfile_shutdown (struct HyPortLibrary *portLibrary)
{
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_startup
/**
 * PortLibrary startup.
 *
 * This function is called during startup of the portLibrary.  Any resources that are required for
 * the file operations may be created here.  All resources created here should be destroyed
 * in @ref hyfile_shutdown.
 *
 * @param[in] portLibrary The port library
 *
 * @return 0 on success, negative error code on failure.  Error code values returned are
 * \arg HYPORT_ERROR_STARTUP_FILE
 *
 * @note Most implementations will simply return success.
 */
I_32 VMCALL
hyfile_startup (struct HyPortLibrary *portLibrary)
{
  return 0;
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_vprintf
/**
 * Write to a file.
 *
 * Writes formatted output  to the file referenced by the file descriptor.
 *
 * @param[in] portLibrary The port library
 * @param[in] fd File descriptor to write.
 * @param[in] format The format String.
 * @param[in] args Variable argument list.
 *
 */
void VMCALL
hyfile_vprintf (struct HyPortLibrary *portLibrary, IDATA fd,
                const char *format, va_list args)
{

  char outputBuffer[256];

  char *allocatedBuffer;
  U_32 numberWritten;
  va_list copyOfArgs;

  /* Attempt to write output to stack buffer */
  COPY_VA_LIST (copyOfArgs, args);
  numberWritten =
    portLibrary->str_vprintf (portLibrary, outputBuffer,
                              sizeof (outputBuffer), format, copyOfArgs);

  /* str_vprintf always null terminates, returns number characters written excluding the null terminator */
  if (sizeof (outputBuffer) > (numberWritten + 1))
    {
      /* write out the buffer */
      portLibrary->file_write_text (portLibrary, fd, outputBuffer,
                                    numberWritten);
      return;
    }

  /* Either the buffer was too small, or it was the exact size.  Unfortunately can't tell the difference,
   * need to determine the size of the buffer (another call to str_vprintf) then print to the buffer,
   * a third call to str_vprintf
   */
  COPY_VA_LIST (copyOfArgs, args);

  /* What is size of buffer required ? Does not include the \0 */
  numberWritten =
    portLibrary->str_vprintf (portLibrary, NULL, (U_32) (-1), format,
                              copyOfArgs);
  numberWritten += 1;

  allocatedBuffer =
    portLibrary->mem_allocate_memory (portLibrary, numberWritten);
  if (NULL == allocatedBuffer)
    {
      portLibrary->nls_printf (portLibrary, HYNLS_ERROR,
                               HYNLS_PORT_FILE_MEMORY_ALLOCATE_FAILURE);
      return;
    }

  numberWritten =
    portLibrary->str_vprintf (portLibrary, allocatedBuffer, numberWritten,
                              format, args);
  portLibrary->file_write_text (portLibrary, fd, allocatedBuffer,
                                numberWritten);
  portLibrary->mem_free_memory (portLibrary, allocatedBuffer);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_printf
/** 
 * Write to a file.
 *
 * Writes formatted output  to the file referenced by the file descriptor.
 *
 * @param[in] portLibrary The port library
 * @param[in] fd File descriptor to write to
 * @param[in] format The format string to be output.
 * @param[in] ... arguments for format.
 *
 */
void VMCALL
hyfile_printf (struct HyPortLibrary *portLibrary, IDATA fd,
               const char *format, ...)
{
  va_list args;

  va_start (args, format);
  portLibrary->file_vprintf (portLibrary, fd, format, args);
  va_end (args);
}

#undef CDEV_CURRENT_FUNCTION

#define CDEV_CURRENT_FUNCTION hyfile_set_length
/**
 * Set the length of a file to a specified value.
 *
 * @param[in] portLibrary The port library
 * @param[in] fd The file descriptor.
 * @param[in] newLength Length to be set
 *
 * @return 0 on success, negative portable error code on failure
 */
I_32 VMCALL
hyfile_set_length (struct HyPortLibrary *portLibrary, IDATA inFD,
                   I_64 newLength)
{
  int fd = (int)inFD;
  I_32 rc;
  off_t length = (off_t) newLength;

  /* If file offsets are 32 bit, truncate the newLength to that range */
  if (sizeof (off_t) < sizeof (I_64))
    {
      if (newLength > 0x7FFFFFFF)
        {
          length = 0x7FFFFFFF;
        }
      else if (newLength < -0x7FFFFFFF)
        {
          length = -0x7FFFFFFF;
        }
    }

#if (FD_BIAS != 0)
	if (fd < FD_BIAS) {
		/* Cannot ftruncate on STD streams, and no other FD's should exist <FD_BIAS */
		return -1;
	}
#endif

  rc = ftruncate (fd - FD_BIAS, length);
  if (0 != rc)
    {
      rc =
        portLibrary->error_set_last_error (portLibrary, errno,
                                           findError (errno));
    }
  return rc;

}

#undef CDEV_CURRENT_FUNCTION
