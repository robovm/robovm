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

#include <string.h>
#include <ctype.h>
#include "iohelp.h"
#include "exceptions.h"
#include "harmonyglob.h"
#include "helpers.h"

void
throwPathTooLongIOException(JNIEnv *env, jsize length)
{
  char errorString[100];
  sprintf(errorString, "Path length of %d characters exceeds maximum supported length of %d", length, HyMaxPath-1);
  throwJavaIoIOException(env, errorString);
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_deleteFileImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  result = hyfile_unlink (pathCopy);
  return result == 0;
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_deleteDirImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  result = hyfile_unlinkdir (pathCopy);
  return result == 0;
}

JNIEXPORT jobject JNICALL
Java_java_io_File_listImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  struct dirEntry
  {
    char pathEntry[HyMaxPath];
    struct dirEntry *next;
  } *dirList, *currentEntry;

  PORT_ACCESS_FROM_ENV (env);
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath+1]; // allow room to add trailing /
  char filename[HyMaxPath];
  I_32 result = 0, index;
  I_32 numEntries = 0;
  UDATA findhandle;
  jarray answer = NULL;
  jclass javaClass = NULL;

  dirList = NULL;
  currentEntry = NULL;

  if (length >= HyMaxPath) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  if (length >= 1 && pathCopy[length - 1] != '\\'
      && pathCopy[length - 1] != '/')
    {
      pathCopy[length] = DIR_SEPARATOR;
      length++;
    }
  pathCopy[length] = '\0';
  findhandle = hyfile_findfirst (pathCopy, filename);
  if (findhandle == (UDATA) - 1)
    return NULL;

  while (result > -1)
    {
      if (strcmp (".", filename) != 0 && (strcmp ("..", filename) != 0))
        {
          if (numEntries > 0)
            {
              currentEntry->next =
                (struct dirEntry *) hymem_allocate_memory (sizeof (struct
                                                                    dirEntry));
              currentEntry = currentEntry->next;
            }
          else
            {
              dirList =
                (struct dirEntry *) hymem_allocate_memory (sizeof (struct
                                                                    dirEntry));
              currentEntry = dirList;
            }
          if (currentEntry == NULL)
            {
              hyfile_findclose (findhandle);
              throwNewOutOfMemoryError (env, "");
              goto cleanup;
            }
          strcpy (currentEntry->pathEntry, filename);
          numEntries++;
        }
      result = hyfile_findnext (findhandle, filename);
    }
  hyfile_findclose (findhandle);

  if (numEntries == 0)
    return NULL;

  javaClass = HARMONY_CACHE_GET (env, CLS_array_of_byte);
  answer =
    (*env)->NewObjectArray (env, numEntries, javaClass, NULL);
cleanup:
  for (index = 0; index < numEntries; index++)
    {
      jbyteArray entrypath;
      jsize entrylen = strlen (dirList->pathEntry);
      currentEntry = dirList;
      if (answer)
        {
          entrypath = (*env)->NewByteArray (env, entrylen);
          (*env)->SetByteArrayRegion (env, entrypath, 0, entrylen,
                                      (jbyte *) dirList->pathEntry);
          (*env)->SetObjectArrayElement (env, answer, index, entrypath);
          (*env)->DeleteLocalRef (env, entrypath);
        }
      dirList = dirList->next;
      hymem_free_memory (currentEntry);
    }
  return answer;
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_isDirectoryImpl (JNIEnv * env, jobject recv,
                                   jbyteArray path)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  result = hyfile_attr (pathCopy);
  return result == HyIsDir;
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_existsImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  char pathCopy[HyMaxPath];
  jsize length = (*env)->GetArrayLength (env, path);
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  result = hyfile_attr (pathCopy);
  return result >= 0;
}

JNIEXPORT jlong JNICALL
Java_java_io_File_getTotalSpaceImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  char pathCopy[HyMaxPath];
  jsize length = (*env)->GetArrayLength (env, path);
  if(!Java_java_io_File_existsImpl(env, recv, path)) {
	return 0l;
  }
  
  length = length < HyMaxPath - 1 ? length : HyMaxPath - 1;
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  ioh_convertToPlatform (pathCopy);
  return getPlatformTotal(env, pathCopy);
}

JNIEXPORT jlong JNICALL
Java_java_io_File_getFreeSpaceImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  char pathCopy[HyMaxPath];
  jsize length = (*env)->GetArrayLength (env, path);
  if(!Java_java_io_File_existsImpl(env, recv, path)) {
	return 0l;
  }
  
  length = length < HyMaxPath - 1 ? length : HyMaxPath - 1;
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  ioh_convertToPlatform (pathCopy);
  return getPlatformFreeTotal(env, pathCopy);
}

JNIEXPORT jlong JNICALL
Java_java_io_File_getUsableSpaceImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  char pathCopy[HyMaxPath];
  jsize length = (*env)->GetArrayLength (env, path);
  if(!Java_java_io_File_existsImpl(env, recv, path)) {
	return 0l;
  }
  
  length = length < HyMaxPath - 1 ? length : HyMaxPath - 1;
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  ioh_convertToPlatform (pathCopy);
  return getPlatformUsableTotal(env, pathCopy);
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_isFileImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  result = hyfile_attr (pathCopy);
  return result == HyIsFile;
}

JNIEXPORT jlong JNICALL
Java_java_io_File_lastModifiedImpl (JNIEnv * env, jobject recv,
                                    jbyteArray path)
{
  PORT_ACCESS_FROM_ENV (env);
  I_64 result;
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  result = hyfile_lastmod (pathCopy);
  return result;
}

JNIEXPORT jlong JNICALL
Java_java_io_File_lengthImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  PORT_ACCESS_FROM_ENV (env);
  I_64 result;
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];

  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  result = hyfile_length (pathCopy);
  if (result < 0)
    {
      return 0L;
    }
  return result;
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_mkdirImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  result = hyfile_mkdir (pathCopy);
  return result == 0;
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_renameToImpl (JNIEnv * env, jobject recv,
                                jbyteArray pathExist, jbyteArray pathNew)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 result;
  jsize length;
  char pathExistCopy[HyMaxPath], pathNewCopy[HyMaxPath];
  char errorString[100];
  length = (*env)->GetArrayLength (env, pathExist);
  if (length > HyMaxPath-1) {
    sprintf(errorString, "Old path length of %d characters exceeds maximum supported length of %d", length, HyMaxPath-1);
    throwJavaIoIOException(env, errorString);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, pathExist, 0, length, (jbyte *)pathExistCopy));
  pathExistCopy[length] = '\0';
  length = (*env)->GetArrayLength (env, pathNew);
  if (length > HyMaxPath-1) {
    sprintf(errorString, "New path length of %d characters exceeds maximum supported length of %d", length, HyMaxPath-1);
    throwJavaIoIOException(env, errorString);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, pathNew, 0, length, (jbyte *)pathNewCopy));
  pathNewCopy[length] = '\0';
  result = hyfile_move (pathExistCopy, pathNewCopy);
  return result == 0;
}

JNIEXPORT jobject JNICALL
Java_java_io_File_getCanonImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  /* This needs work.  Currently is does no more or less than VAJ-20 ST implementation
   * but really should figure out '..', '.', and really resolve references.
   */
  jbyteArray answer;
  jsize answerlen;
  char pathCopy[HyMaxPath];
  U_32 length = (U_32) (*env)->GetArrayLength (env, path);
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  (*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy);
  pathCopy[length] = '\0';
#if defined(WIN32)
  platformCanonicalPath (pathCopy);
#endif

  answerlen = strlen (pathCopy);
  answer = (*env)->NewByteArray (env, answerlen);
  (*env)->SetByteArrayRegion (env, answer, 0, answerlen, (jbyte *) pathCopy);
  return answer;
}

JNIEXPORT jint JNICALL
Java_java_io_File_newFileImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  PORT_ACCESS_FROM_ENV (env);
  IDATA portFD;
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';

  /* Now create the file and close it */
  portFD = hyfile_open (pathCopy,
                        HyOpenCreateNew | HyOpenWrite | HyOpenTruncate,
                        0666);
  
  if (portFD == -1) {
    if (hyerror_last_error_number() == HYPORT_ERROR_FILE_EXIST) {
      return 1;
    }
    return 2;
  }
  hyfile_close (portFD);
  return 0;
}

JNIEXPORT jobject JNICALL
Java_java_io_File_rootsImpl (JNIEnv * env, jclass clazz)
{
  char rootStrings[HyMaxPath], *rootCopy;
  I_32 numRoots;
  I_32 index = 0;
  jarray answer;

   /**
	 * It is the responsibility of #getPlatformRoots to return a char array
	 * with volume names separated by null with a trailing extra null, so for
	 * Unix it should be '\<null><null>' .
	 */
  numRoots = getPlatformRoots (rootStrings);
  if (numRoots == 0)
    return NULL;
  rootCopy = rootStrings;

  answer =
    (*env)->NewObjectArray (env, numRoots,
                            HARMONY_CACHE_GET (env, CLS_array_of_byte), NULL);
  if (!answer)
    {
      return NULL;
    }
  while (TRUE)
    {
      jbyteArray rootname;
      jsize entrylen = strlen (rootCopy);
      /* Have we hit the second null? */
      if (entrylen == 0)
        break;
      rootname = (*env)->NewByteArray (env, entrylen);
      (*env)->SetByteArrayRegion (env, rootname, 0, entrylen,
                                  (jbyte *) rootCopy);
      (*env)->SetObjectArrayElement (env, answer, index++, rootname);
      (*env)->DeleteLocalRef (env, rootname);
      rootCopy = rootCopy + entrylen + 1;
    }
  return answer;
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_isHiddenImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  I_32 result;
  char pathCopy[HyMaxPath];
  jsize length = (*env)->GetArrayLength (env, path);
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  result = getPlatformIsHidden (env, pathCopy);
  return result;
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_setLastModifiedImpl (JNIEnv * env, jobject recv,
                                       jbyteArray path, jlong time)
{
  I_32 result;
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';

  result = setPlatformLastModified (env, pathCopy, (I_64) time);

  return result;
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_setReadOnlyImpl (JNIEnv * env, jobject recv,
                                   jbyteArray path)
{
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  return setPlatformReadOnly (env, pathCopy);
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_setReadableImpl (JNIEnv * env, jobject recv,
                                   jbyteArray path, jboolean readable, jboolean ownerOnly)
{
#if (defined(WIN32))
   return readable;
#else
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];
  length = length < HyMaxPath - 1 ? length : HyMaxPath - 1;
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  ioh_convertToPlatform (pathCopy);
  return setPlatformReadable (env, pathCopy, readable, ownerOnly);
#endif
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_setWritableImpl (JNIEnv * env, jobject recv,
                                   jbyteArray path, jboolean writable, jboolean ownerOnly)
{
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];
  length = length < HyMaxPath - 1 ? length : HyMaxPath - 1;
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  ioh_convertToPlatform (pathCopy);
  return setPlatformWritable (env, pathCopy, writable, ownerOnly);
}

JNIEXPORT void JNICALL
Java_java_io_File_oneTimeInitialization (JNIEnv * env, jclass clazz)
{
  jclass arrayClass = (*env)->FindClass (env, "[B");
  if (arrayClass)
    {
      jobject globalRef = (*env)->NewGlobalRef (env, arrayClass);
      if (globalRef)
        HARMONY_CACHE_SET (env, CLS_array_of_byte, globalRef);
    }
  return;
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_isReadOnlyImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  I_32 result;
  char pathCopy[HyMaxPath];
  jsize length = (*env)->GetArrayLength (env, path);
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  result = getPlatformIsReadOnly (env, pathCopy);
  return result;
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_isWriteOnlyImpl (JNIEnv * env, jobject recv,
                                   jbyteArray path)
{
  I_32 result;
  char pathCopy[HyMaxPath];
  jsize length = (*env)->GetArrayLength (env, path);
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  result = getPlatformIsWriteOnly (env, pathCopy);
  return result;
}

JNIEXPORT jobject JNICALL
Java_java_io_File_getLinkImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
  jbyteArray answer;
  jsize answerlen;
  char pathCopy[HyMaxPath];
  U_32 length = (U_32) (*env)->GetArrayLength (env, path);
  if (length > HyMaxPath-1) {
    throwPathTooLongIOException(env, length);
    return 0;
  }
  (*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy);
  pathCopy[length] = '\0';
  if (platformReadLink (pathCopy))
    {
      answerlen = strlen (pathCopy);
      answer = (*env)->NewByteArray (env, answerlen);
      (*env)->SetByteArrayRegion (env, answer, 0, answerlen,
                                  (jbyte *) pathCopy);
    }
  else
    {
      answer = path;
    }
  return answer;
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_isCaseSensitiveImpl (JNIEnv * env, jclass clazz)
{
/* Assume all other platforms ARE case sensitive and add to this list when they prove otherwise */
#if (defined(WIN32))
  return FALSE;
#else
  return TRUE;
#endif
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_setExecutableImpl (JNIEnv * env, jobject recv,
                                   jbyteArray path, jboolean executable, jboolean ownerOnly)
{

#if (defined(WIN32))
  return executable;
#else
  jsize length = (*env)->GetArrayLength (env, path);
  char pathCopy[HyMaxPath];
  length = length < HyMaxPath - 1 ? length : HyMaxPath - 1;
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  ioh_convertToPlatform (pathCopy);
  return setPlatformExecutable (env, pathCopy, executable, ownerOnly);

#endif
  
}

JNIEXPORT jboolean JNICALL
Java_java_io_File_isExecutableImpl (JNIEnv * env, jobject recv, jbyteArray path)
{
#if (defined(WIN32))
  return TRUE;
#else
  I_32 result;
  char pathCopy[HyMaxPath];
  jsize length = (*env)->GetArrayLength (env, path);
  length = length < HyMaxPath - 1 ? length : HyMaxPath - 1;
  ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
  pathCopy[length] = '\0';
  ioh_convertToPlatform (pathCopy);
  result = getPlatformIsExecutable (env, pathCopy);
  return result;
#endif  
} 

