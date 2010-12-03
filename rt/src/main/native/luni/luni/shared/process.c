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
#include "nethelp.h"
#include "exceptions.h"
#include "procimpl.h"

#include "harmonyglob.h"

/**
  * Create a System Process with the specified
  * environment and arguments 
  */
JNIEXPORT jlongArray JNICALL
Java_org_apache_harmony_luni_internal_process_SystemProcess_createImpl (JNIEnv * env, jclass clazz,
            jobject recv,
            jobjectArray arg1,
            jobjectArray arg2,
            jbyteArray dir)
{
  jlongArray pVals = NULL;
  jlong npVals[4];
  char *envArray[256];
  char *command[256];
  int i, retVal;
  IDATA pHandle, inHandle, outHandle, errHandle;
  int envLength, commandLineLength, len;
  char *workingDir = NULL;
  PORT_ACCESS_FROM_ENV (env);

  /* validate sizes */
  commandLineLength = (*env)->GetArrayLength (env, arg1);
  envLength = (*env)->GetArrayLength (env, arg2);
  if (commandLineLength >= 255)
    {
      throwJavaIoIOException(env, "Too many arguments");
      return NULL;
    }
  if (envLength >= 255)
    {
      throwJavaIoIOException(env, "Too many environment arguments");
      return NULL;
    }

  memset (command, 0, sizeof (command));
  memset (envArray, 0, sizeof (envArray));

  /* Get the command string and arguments */
  /* convert java.lang.String into C char* */
  for (i = commandLineLength; --i >= 0;)
    {
      jbyteArray element = (*env)->GetObjectArrayElement (env, arg1, i);
      len = (*env)->GetArrayLength (env, element);
      command[i] = jclmem_allocate_memory (env, len + 1);
      if (command[i] == NULL)
        {
          throwNewOutOfMemoryError (env, "");
          goto failed;
        }
      (*env)->GetByteArrayRegion (env, element, 0, len, (jbyte *)command[i]);
      command[i][len] = 0;
      (*env)->DeleteLocalRef(env, element);
    }
  if (envLength)
    for (i = 0; i < envLength; i++)
      {
        jbyteArray envString = (*env)->GetObjectArrayElement (env, arg2, i);
        len = (*env)->GetArrayLength (env, envString);
        envArray[i] = jclmem_allocate_memory (env, len + 1);
        if (envArray[i] == NULL)
          {
            throwNewOutOfMemoryError (env, "");
            goto failed;
          }
        (*env)->GetByteArrayRegion (env, envString, 0, len, (jbyte *)envArray[i]);
        envArray[i][len] = 0;
        (*env)->DeleteLocalRef(env, envString);
      }
  /* NULL terminate for UNIX (does work on windows too; in fact, it doesn't care) */
  command[commandLineLength] = NULL;
  envArray[envLength] = NULL;

  if (dir != NULL)
    {
      jsize dirLength = (*env)->GetArrayLength (env, dir);

      workingDir = jclmem_allocate_memory (env, dirLength + 1);
      if (workingDir)
        {
          (*env)->GetByteArrayRegion (env, dir, 0, dirLength,
            (jbyte *) workingDir);
          workingDir[dirLength] = '\0';
        }
    }

  /*
   *  now call execProgram.  Any non-zero return code 
   *  indicates some kind of failure
   */
   
  retVal = execProgram (env, recv,
      command, commandLineLength, envArray, envLength,
      workingDir, &pHandle, &inHandle, &outHandle,
      &errHandle);

  if (workingDir)
    {
      jclmem_free_memory (env, workingDir);
    }

  if (retVal)
    {
        char errMsg[256];

        /* Failed to exec program */
        
        switch(retVal) {
        case 1001 :
            sprintf(errMsg, "Unable to start program : %s", "fork() failed with errno = EOMEM");
            break;
        case 1002 : 
            sprintf(errMsg, "Unable to start program : %s", "fork() failed with errno = EAGAIN");
            break;
        case 1003 : 
            sprintf(errMsg, "Unable to start program : %s", "too many open files");
            break;
        case 1004 : 
            sprintf(errMsg, "Unable to start program : %s", "no such file or directory");
            break;
        default:
            sprintf(errMsg, "Unable to start program : %s", "unknown");
            break;
        }

        throwJavaIoIOException(env, errMsg);
        goto failed;
    }

  pVals = (*env)->NewLongArray (env, 4);

  if (pVals)
    {
      npVals[0] = (jlong) pHandle;
      npVals[1] = (jlong) inHandle;
      npVals[2] = (jlong) outHandle;
      npVals[3] = (jlong) errHandle;
      (*env)->SetLongArrayRegion (env, pVals, 0, 4, (jlong *) (&npVals));
    }

failed:

  for (i = 0; i < envLength; i++)
    {
      if (envArray[i])
        jclmem_free_memory (env, envArray[i]);
    }
  for (i = commandLineLength; --i >= 0;)
    {
      if (command[i])
        jclmem_free_memory (env, command[i]);
    }

  return pVals;
}

/* Kill the receiver */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_internal_process_SystemProcess_destroyImpl (JNIEnv * env, jobject recv)
{
  jlong pHandle;
  pHandle =
    (*env)->GetLongField (env, recv,
        HARMONY_CACHE_GET (env,
           FID_org_apache_harmony_luni_internal_process_SystemProcess_handle));
  termProc ((IDATA) pHandle);
}

/* Close the input stream*/
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_internal_process_ProcessInputStream_closeImpl (JNIEnv * env,
                jobject recv)
{
  new_ioh_close (env, recv,
     HARMONY_CACHE_GET (env,
        FID_org_apache_harmony_luni_internal_process_ProcessInputStream_fd));
}

JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_internal_process_ProcessOutputStream_closeImpl (JNIEnv * env,
                 jobject recv)
{
  new_ioh_close (env, recv,
     HARMONY_CACHE_GET (env,
        FID_org_apache_harmony_luni_internal_process_ProcessOutputStream_fd));
}

/* Read nbytes from the receiver */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_internal_process_ProcessInputStream_readImpl (JNIEnv * env, jobject recv,
               jbyteArray buffer,
               jint offset, jint nbytes,
               jlong handle)
{

  return (jint) ioh_readbytesImpl (env, recv, buffer, offset, nbytes,
           (IDATA) handle);

}

/* Return the number of byes available to be read without blocking */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_internal_process_ProcessInputStream_availableImpl (JNIEnv * env,
              jobject recv)
{
  jlong sHandle;
  int retVal;

  sHandle =
    (*env)->GetLongField (env, recv,
        HARMONY_CACHE_GET (env,
           FID_org_apache_harmony_luni_internal_process_ProcessInputStream_handle));
  retVal = getAvailable ((jint)sHandle);
  if (retVal < 0)
    {
      /* Couldn't read bytes */
      throwJavaIoIOException(env, "Unable to peek on stream");
    }
  return (jint) retVal;
}

/* Write nbytes to the receiver */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_internal_process_ProcessOutputStream_writeImpl (JNIEnv * env,
                 jobject recv,
                 jbyteArray buffer,
                 jint offset, jint nbytes,
                 jlong handle)
{

  ioh_writebytesImpl (env, recv, buffer, offset, nbytes, (IDATA) handle);

}

/* Set the descriptor field od the receiver */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_internal_process_ProcessInputStream_setFDImpl (JNIEnv * env,
                jobject recv,
                jobject arg1, jlong arg2)
{
  setJavaIoFileDescriptorContents (env, arg1, (void *) ((IDATA) arg2));
}

JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_internal_process_ProcessOutputStream_setFDImpl (JNIEnv * env,
                 jobject recv,
                 jobject arg1, jlong arg2)
{
  setJavaIoFileDescriptorContents (env, arg1, (void *) ((IDATA) arg2));
}

/* Wait for the receiver to finish then return the exit value */
JNIEXPORT jint JNICALL
Java_org_apache_harmony_luni_internal_process_SystemProcess_waitForCompletionImpl (JNIEnv * env,
                 jobject recv)
{
  jlong pHandle;
  pHandle =
    (*env)->GetLongField (env, recv,
        HARMONY_CACHE_GET (env,
           FID_org_apache_harmony_luni_internal_process_SystemProcess_handle));
  return (jint) waitForProc ((IDATA) pHandle);
}

JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_internal_process_SystemProcess_oneTimeInitialization (JNIEnv * env,
                 jclass clazz)
{
  jfieldID fid = (*env)->GetFieldID (env, clazz, "handle", "J");
  if (!fid)
    return;
  HARMONY_CACHE_SET (env, FID_org_apache_harmony_luni_internal_process_SystemProcess_handle, fid);
}

JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_internal_process_ProcessOutputStream_oneTimeInitialization (JNIEnv * env,
                 jclass clazz)
{
  jfieldID fid;

  fid = (*env)->GetFieldID (env, clazz, "handle", "J");
  if (!fid)
    return;
  HARMONY_CACHE_SET (env, FID_org_apache_harmony_luni_internal_process_ProcessOutputStream_handle, fid);

  fid = (*env)->GetFieldID (env, clazz, "fd", "Ljava/io/FileDescriptor;");
  if (!fid)
    return;
  HARMONY_CACHE_SET (env, FID_org_apache_harmony_luni_internal_process_ProcessOutputStream_fd, fid);
}

JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_internal_process_ProcessInputStream_oneTimeInitialization (JNIEnv * env,
                jclass clazz)
{
  jfieldID fid;

  fid = (*env)->GetFieldID (env, clazz, "handle", "J");
  if (!fid)
    return;
  HARMONY_CACHE_SET (env, FID_org_apache_harmony_luni_internal_process_ProcessInputStream_handle, fid);

  fid = (*env)->GetFieldID (env, clazz, "fd", "Ljava/io/FileDescriptor;");
  if (!fid)
    return;
  HARMONY_CACHE_SET (env, FID_org_apache_harmony_luni_internal_process_ProcessInputStream_fd, fid);
}

/* Close the handle */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_internal_process_SystemProcess_closeImpl (JNIEnv * env, jobject recv)
{
  jlong pHandle;
  pHandle =
    (*env)->GetLongField (env, recv,
        HARMONY_CACHE_GET (env,
           FID_org_apache_harmony_luni_internal_process_SystemProcess_handle));
  closeProc ((IDATA) pHandle);
}
