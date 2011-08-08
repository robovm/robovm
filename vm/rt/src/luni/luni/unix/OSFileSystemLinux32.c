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
 * Linux32 specific natives supporting the file system interface.
 */

#include <sys/uio.h>
#include <fcntl.h>
#include <unistd.h>
#include <errno.h>
#include <sys/stat.h>
#if defined(FREEBSD) || defined(AIX) || defined(ZOS) || defined(MACOSX)
#include <sys/types.h>
#include <sys/socket.h>
#else
#include <sys/sendfile.h>
#endif
#include "vmi.h"
#include "iohelp.h"
#include "nethelp.h"
#include "harmonyglob.h"
#include "hysock.h"
#include "exceptions.h"

#include "IFileSystem.h"
#include "OSFileSystem.h"

#ifdef ZOS
#define FD_BIAS 1000
#else
#define FD_BIAS 0
#endif /* ZOS */

/**
 * Lock the file identified by the given handle.
 * The range and lock type are given.
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_lockImpl
  (JNIEnv * env, jobject thiz, jlong handle, jlong start, jlong length,
   jint typeFlag, jboolean waitFlag)
{
  int rc;
  int waitMode = (waitFlag) ? F_SETLKW : F_SETLK;
  struct flock lock = { 0 };
  jlong lockHandle = handle - FD_BIAS;

  // If start or length overflow the max values we can represent, then max them out.
#if __WORDSIZE==32
#define MAX_INT 0x7fffffffL
  if (start > MAX_INT)
    {
      start = MAX_INT;
    }
  if (length > MAX_INT)
    {
      length = MAX_INT;
    }
#endif

  lock.l_whence = SEEK_SET;
  lock.l_start = start;
  lock.l_len = length;

  if ((typeFlag & org_apache_harmony_luni_platform_IFileSystem_SHARED_LOCK_TYPE) ==
      org_apache_harmony_luni_platform_IFileSystem_SHARED_LOCK_TYPE)
    {
      lock.l_type = F_RDLCK;
    }
  else
    {
      lock.l_type = F_WRLCK;
    }

  do
    {
      rc = fcntl (lockHandle, waitMode, &lock);
    }
  while ((rc < 0) && (errno == EINTR));

  return (rc == -1) ? -1 : 0;
}

/**
 * Unlocks the specified region of the file.
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_unlockImpl
  (JNIEnv * env, jobject thiz, jlong handle, jlong start, jlong length)
{
  int rc;
  struct flock lock = { 0 };
  jlong lockHandle = handle - FD_BIAS;

  // If start or length overflow the max values we can represent, then max them out.
#if __WORDSIZE==32
#define MAX_INT 0x7fffffffL
  if (start > MAX_INT)
    {
      start = MAX_INT;
    }
  if (length > MAX_INT)
    {
      length = MAX_INT;
    }
#endif

  lock.l_whence = SEEK_SET;
  lock.l_start = start;
  lock.l_len = length;
  lock.l_type = F_UNLCK;

  do
    {
      rc = fcntl (lockHandle, F_SETLKW, &lock);
    }
  while ((rc < 0) && (errno == EINTR));

  return (rc == -1) ? -1 : 0;
}

/**
 * Returns the granularity of the starting address for virtual memory allocation.
 * (It's the same as the page size.)
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    getAllocGranularity
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_getAllocGranularity
  (JNIEnv * env, jobject thiz)
{
  static int allocGranularity = 0;
  if(allocGranularity == 0){
    allocGranularity = getpagesize();
  }
  return allocGranularity;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    readvImpl
 * Signature: (J[J[I[I)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_readvImpl
  (JNIEnv *env, jobject thiz, jlong fd, jlongArray jbuffers, jintArray joffsets, jintArray jlengths, jint size){
  PORT_ACCESS_FROM_ENV (env);
  jboolean bufsCopied = JNI_FALSE;
  jboolean offsetsCopied = JNI_FALSE;
  jboolean lengthsCopied = JNI_FALSE;
  jlong *bufs;
  jint *offsets;
  jint *lengths;
  int i = 0;
  long totalRead = 0;
  struct iovec *vectors = (struct iovec *)hymem_allocate_memory(size * sizeof(struct iovec));
  if(vectors == NULL){
    return -1;
  }
  bufs = (*env)->GetLongArrayElements(env, jbuffers, &bufsCopied);
  offsets = (*env)->GetIntArrayElements(env, joffsets, &offsetsCopied);
  lengths = (*env)->GetIntArrayElements(env, jlengths, &lengthsCopied);
  while(i < size){
    vectors[i].iov_base = (void *)((IDATA)(bufs[i]+offsets[i]));
    vectors[i].iov_len = lengths[i];
    i++;
  }
  totalRead = readv(fd - FD_BIAS, vectors, size);
  if(bufsCopied){
    (*env)->ReleaseLongArrayElements(env, jbuffers, bufs, JNI_ABORT);
  }
  if(offsetsCopied){
    (*env)->ReleaseIntArrayElements(env, joffsets, offsets, JNI_ABORT);
  }
  if(lengthsCopied){
    (*env)->ReleaseIntArrayElements(env, jlengths, lengths, JNI_ABORT);
  }
  hymem_free_memory(vectors);
  return totalRead == 0 ? -1 : totalRead;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    writev
 * Signature: (J[Ljava/lang/Object;[I[II)J
 */
JNIEXPORT jlong JNICALL
Java_org_apache_harmony_luni_platform_OSFileSystem_writev
  (JNIEnv *env, jobject thiz, jlong fd, jobjectArray buffers, jintArray offset, jintArray counts, jint length){
  PORT_ACCESS_FROM_ENV (env);
  jobject buffer;
  jobject* toBeReleasedBuffers;
  jint *noffset = NULL;
  jboolean isDirectBuffer = JNI_FALSE;
  ssize_t result = 0;
  jclass byteBufferClass;
  struct iovec* vect;
  int i;

  vect = (struct iovec*) hymem_allocate_memory(sizeof(struct iovec) * length);
  if (vect == NULL) {
    throwNewOutOfMemoryError(env, "");
    return (jlong)0;
  }

  toBeReleasedBuffers =
    (jobject*) hymem_allocate_memory(sizeof(jobject) * length);
  if (toBeReleasedBuffers == NULL) {
    throwNewOutOfMemoryError(env, "");
    goto free_resources;
  }
  memset(toBeReleasedBuffers, 0, sizeof(jobject)*length);

  byteBufferClass = HARMONY_CACHE_GET (env, CLS_java_nio_DirectByteBuffer);
  noffset = (*env)->GetIntArrayElements(env, offset, NULL);
  if (noffset == NULL) {
    throwNewOutOfMemoryError(env, "");
    goto free_resources;
  }

  for (i = 0; i < length; ++i) {
    jint *cts;
    U_8* base;
    buffer = (*env)->GetObjectArrayElement(env, buffers, i);
    isDirectBuffer = (*env)->IsInstanceOf(env, buffer, byteBufferClass);
    if (isDirectBuffer) {
      base =
        (U_8 *)(jbyte *)(IDATA) (*env)->GetDirectBufferAddress(env, buffer);
      if (base == NULL) {
        throwNewOutOfMemoryError(env, "");
        goto free_resources;
      }
      toBeReleasedBuffers[i] = NULL;
    } else {
      base =
        (U_8 *)(jbyte *)(IDATA) (*env)->GetByteArrayElements(env, buffer, NULL);
      if (base == NULL) {
        throwNewOutOfMemoryError(env, "");
        goto free_resources;
      }
      toBeReleasedBuffers[i] = buffer;
    }
    vect[i].iov_base = base + noffset[i];

    cts = (*env)->GetPrimitiveArrayCritical(env, counts, NULL);
    vect[i].iov_len = cts[i];
    (*env)->ReleasePrimitiveArrayCritical(env, counts, cts, JNI_ABORT);
  }

  result = writev(fd - FD_BIAS, vect, length);

  if (0 > result) {
    if (errno != EAGAIN) {
      throwJavaIoIOException(env, "");
    }
    result = 0;
  }

 free_resources:
  
  if (toBeReleasedBuffers != NULL) {
    for (i = 0; i < length; ++i) {
      if (toBeReleasedBuffers[i] != NULL) {
        (*env)->ReleaseByteArrayElements(env, toBeReleasedBuffers[i],
                                         vect[i].iov_base - noffset[i],
                                         JNI_ABORT);
      }
    }
  }

  if (noffset != NULL) {
    (*env)->ReleaseIntArrayElements(env, offset, noffset, JNI_ABORT);
  }

  hymem_free_memory(toBeReleasedBuffers);
  hymem_free_memory(vect);

  return (jlong) result;
}

/*
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    transferImpl
 * Signature: (JLjava/io/FileDescriptor;JJ)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_transferImpl
  (JNIEnv *env, jobject thiz, jlong fd, jobject sd, jlong offset, jlong count)
{
  OSSOCKET socket;
  //TODO IPV6
  hysocket_t hysocketP =
    (hysocket_t)getJavaIoFileDescriptorContentsAsAPointer (env,sd);
  if(hysocketP == NULL)
    return -1;
  socket = hysocketP->sock;
#if defined(AIX) || defined(ZOS)
  {
    struct sf_parms parms;
    int result;
    int positionBack = Java_org_apache_harmony_luni_platform_OSFileSystem_seekImpl(env, thiz, fd, 0, 2);
    parms.file_descriptor = (int)fd - FD_BIAS;
    parms.file_offset = (off64_t)offset;
    parms.file_bytes = count;
    parms.header_data = 0;
    parms.header_length = 0;
    parms.trailer_data = 0;
    parms.trailer_length = 0;
    result = send_file(&socket, &parms, 0);
    if (result == 0) {
      Java_org_apache_harmony_luni_platform_OSFileSystem_seekImpl(env, thiz, fd, (jlong)positionBack, 1);
      return count;
    } else {
      return result;
    }
  }
#else
  /* Value of offset is checked in jint scope (checked in java layer)
   The conversion here is to guarantee no value lost when converting offset to off_t
   */
  off_t off = offset;
#if defined(FREEBSD)
  return sendfile(fd-FD_BIAS, socket, off, (size_t)count, NULL, NULL, 0);
#elif defined(MACOSX)
  return sendfile((int)fd-FD_BIAS, (int)socket, off, (off_t *)&count, NULL, 0);
#else
  return sendfile(socket,(int)fd-FD_BIAS,(off_t *)&off,(size_t)count);
#endif
#endif
}


/*
 * Answers the size of the file pointed to by the file descriptor.
 *
 * Class:     org_apache_harmony_luni_platform_OSFileSystem
 * Method:    sizeImpl
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSFileSystem_sizeImpl
(JNIEnv *env, jobject thiz, jlong fd)
{
  struct stat statbuf;
  if (fstat(fd - FD_BIAS, &statbuf) < 0) {
    return -1;
  }
  return (jlong)statbuf.st_size;
}
