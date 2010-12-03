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
/*
 * Common natives supporting the file system interface.
 */

#include "vmi.h"
#include <string.h>
#include "iohelp.h"
#include "exceptions.h"
#include "nethelp.h"
#include "harmonyglob.h"
#include "OSFileSystem.h"
#include "IFileSystem.h"

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    oneTimeInitializationImpl
 * Signature: ()V
 */
JNIEXPORT void JNICALL
Java_org_apache_harmony_luni_platform_OSFileSystem_oneTimeInitializationImpl
  (JNIEnv * env, jclass clazz)
{
  jclass lookupClass;
  jobject globalRef;

  if (HARMONY_CACHE_GET (env, CLS_java_nio_DirectByteBuffer)) {
    /* Cache is already initialized */
    return;
  }
  lookupClass = (*env)->FindClass (env, "java/nio/DirectByteBuffer");
  if (!lookupClass)
    return;
  globalRef = (*env)->NewGlobalRef (env, lookupClass);
  if (!globalRef)
    return;
  HARMONY_CACHE_SET (env, CLS_java_nio_DirectByteBuffer, globalRef);
}

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    readDirectImpl
 * Signature: (JJI)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_readDirectImpl
  (JNIEnv * env, jobject thiz, jlong fd, jlong buf, jint offset, jint nbytes)
{
  PORT_ACCESS_FROM_ENV (env);
  return (jlong) hyfile_read ((IDATA) fd,
                              (void *) ((IDATA)(buf+offset)),
                              (IDATA) nbytes);
}

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    writeDirectImpl
 * Signature: (JJI)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_writeDirectImpl
  (JNIEnv * env, jobject thiz, jlong fd, jlong buf, jint offset, jint nbytes)
{
  PORT_ACCESS_FROM_ENV (env);
  return (jlong) hyfile_write ((IDATA) fd,
                               (const void *) ((IDATA)(buf+offset)),
                               (IDATA) nbytes);
}

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    readImpl
 * Signature: (J[BII)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_readImpl
  (JNIEnv * env, jobject thiz, jlong fd, jbyteArray byteArray, jint offset,
   jint nbytes)
{
  PORT_ACCESS_FROM_ENV (env);
  jboolean isCopy;
  jbyte *bytes = (*env)->GetByteArrayElements (env, byteArray, &isCopy);
  jlong result;

  result =
    (jlong) hyfile_read ((IDATA) fd, (void *) (bytes + offset),
                         (IDATA) nbytes);
  if(result == -1 && hyerror_last_error_number() == HYPORT_ERROR_FILE_LOCKED){
    throwNewExceptionByName(env, "java/io/IOException", netLookupErrorString(env, HYPORT_ERROR_FILE_LOCKED));
  }
  (*env)->ReleaseByteArrayElements (env, byteArray, bytes,
                                    result==0 || result==-1 ? JNI_ABORT : 0);

  return result;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    writeImpl
 * Signature: (J[BII)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_writeImpl
  (JNIEnv * env, jobject thiz, jlong fd, jbyteArray byteArray, jint offset, jint nbytes)
{
  PORT_ACCESS_FROM_ENV (env);
  jboolean isCopy;
  jbyte *bytes = (*env)->GetByteArrayElements (env, byteArray, &isCopy);
  jlong result;

  result =
    (jlong) hyfile_write ((IDATA) fd, (void *) (bytes + offset),
                         (IDATA) nbytes);
  if(result == -1 && hyerror_last_error_number() == HYPORT_ERROR_FILE_LOCKED){
    throwNewExceptionByName(env, "java/io/IOException", netLookupErrorString(env, HYPORT_ERROR_FILE_LOCKED));
  }
   (*env)->ReleaseByteArrayElements (env, byteArray, bytes, JNI_ABORT);

  return result;
}

/**
 * Seeks a file descriptor to a given file position.
 * 
 * @param env pointer to Java environment
 * @param thiz pointer to object receiving the message
 * @param fd handle of file to be seeked
 * @param offset distance of movement in bytes relative to whence arg
 * @param whence enum value indicating from where the offset is relative
 * The valid values are defined in fsconstants.h.
 * @return the new file position from the beginning of the file, in bytes;
 * or -1 if a problem occurs.
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_seekImpl
  (JNIEnv * env, jobject thiz, jlong fd, jlong offset, jint whence)
{
  PORT_ACCESS_FROM_ENV (env);
  I_32 hywhence = 0;            /* The HY PPL equivalent of our whence arg.*/

  /* Convert whence argument */
  switch (whence)
    {
    case org_apache_harmony_luni_platform_IFileSystem_SEEK_SET:
      hywhence = HySeekSet;
      break;
    case org_apache_harmony_luni_platform_IFileSystem_SEEK_CUR:
      hywhence = HySeekCur;
      break;
    case org_apache_harmony_luni_platform_IFileSystem_SEEK_END:
      hywhence = HySeekEnd;
      break;
    default:
      return -1;
    }

  return (jlong) hyfile_seek ((IDATA) fd, (I_64) offset, hywhence);
}

/**
 * Flushes a file state to disk.
 *
 * @param env pointer to Java environment
 * @param thiz pointer to object receiving the message
 * @param fd handle of file to be flushed
 * @param metadata if true also flush metadata, otherwise just flush data is possible.
 * @return zero on success and -1 on failure
 *
 * Method:    fflushImpl
 * Signature: (JZ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_fflushImpl
  (JNIEnv * env, jobject thiz, jlong fd, jboolean metadata)
{
  PORT_ACCESS_FROM_ENV (env);

  return (jint) hyfile_sync ((IDATA) fd);
}

/**
 * Closes the given file handle
 * 
 * @param env pointer to Java environment
 * @param thiz pointer to object receiving the message
 * @param fd handle of file to be closed
 * @return zero on success and -1 on failure
 *
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    closeImpl
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_closeImpl
  (JNIEnv * env, jobject thiz, jlong fd)
{
  PORT_ACCESS_FROM_ENV (env);

  return (jint) hyfile_close ((IDATA) fd);
}


/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    truncateImpl
 * Signature: (JJ)I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_truncateImpl
  (JNIEnv * env, jobject thiz, jlong fd, jlong size)
{
  PORT_ACCESS_FROM_ENV (env);

  return (jint)hyfile_set_length((IDATA)fd, (I_64)size);

}

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    openImpl
 * Signature: ([BI)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_openImpl
  (JNIEnv * env, jobject obj, jbyteArray path, jint jflags){
      PORT_ACCESS_FROM_ENV (env);
      I_32 flags = 0;
      I_32 mode = 0; 
      IDATA portFD;
      jsize length;
      char pathCopy[HyMaxPath];

      switch(jflags){
        case org_apache_harmony_luni_platform_IFileSystem_O_RDONLY:
                flags = HyOpenRead;
                mode = 0;
                break;
        case org_apache_harmony_luni_platform_IFileSystem_O_WRONLY:
                flags = HyOpenCreate | HyOpenWrite | HyOpenTruncate;
                mode = 0666;
                break;
        case org_apache_harmony_luni_platform_IFileSystem_O_RDWR:
                flags = HyOpenRead | HyOpenWrite | HyOpenCreate;
                mode = 0666;
                break;
        case org_apache_harmony_luni_platform_IFileSystem_O_APPEND:
                flags = HyOpenWrite | HyOpenCreate | HyOpenAppend; 
                mode = 0666;
                break;
        case org_apache_harmony_luni_platform_IFileSystem_O_RDWRSYNC:
        		flags = HyOpenRead | HyOpenWrite | HyOpenCreate | HyOpenSync;
        		mode = 0666;
        		break;
      }

      length = (*env)->GetArrayLength (env, path);
      length = length < HyMaxPath - 1 ? length : HyMaxPath - 1;
      ((*env)->GetByteArrayRegion (env, path, 0, length, (jbyte *)pathCopy));
      pathCopy[length] = '\0';
      ioh_convertToPlatform (pathCopy);

      portFD = hyfile_open (pathCopy, flags, mode);
      return (jlong)portFD;
  }

/*
 * Answers the number of remaining chars in the stdin.
 *
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    ttyAvailableImpl
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_ttyAvailableImpl
  (JNIEnv *env, jobject thiz)
{
    PORT_ACCESS_FROM_ENV (env);

    return (jlong)hytty_available();
}

/*
 * Answers the number of remaining chars on the file descriptor.
 *
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    availableImpl
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_availableImpl
(JNIEnv *env, jobject thiz, jlong fd)
{
    jlong currentPosition =
      Java_org_apache_harmony_luni_platform_OSFileSystem_seekImpl(env,
          thiz, fd, 0,
          org_apache_harmony_luni_platform_IFileSystem_SEEK_CUR);

    jlong endPosition =
      Java_org_apache_harmony_luni_platform_OSFileSystem_seekImpl(env,
          thiz, fd, 0,
          org_apache_harmony_luni_platform_IFileSystem_SEEK_END);
    
    Java_org_apache_harmony_luni_platform_OSFileSystem_seekImpl(env,
          thiz, fd, currentPosition,
          org_apache_harmony_luni_platform_IFileSystem_SEEK_SET);
    
    return (jlong) (endPosition - currentPosition);
}

/*
 * Reads the number of bytes from stdin.
 *
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    ttyReadImpl
 * Signature: ([BII)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_ttyReadImpl
  (JNIEnv *env, jobject thiz, jbyteArray byteArray, jint offset, jint nbytes)
{
    PORT_ACCESS_FROM_ENV (env);
    jboolean isCopy;
    jbyte *bytes = (*env)->GetByteArrayElements(env, byteArray, &isCopy);
    jlong result;

    result = (jlong) hytty_get_chars((char *)(bytes + offset), (IDATA) nbytes);
    (*env)->ReleaseByteArrayElements (env, byteArray, bytes, 0);

    return result;
}
