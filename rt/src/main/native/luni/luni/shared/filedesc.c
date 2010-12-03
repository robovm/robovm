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
#include "harmonyglob.h"

JNIEXPORT void JNICALL
Java_java_io_FileDescriptor_syncImpl (JNIEnv * env, jobject recv)
{
  /* Cause all unwritten data to be written out to the OS */
  IDATA descriptor;
  I_32 syncfailed = 0;
  PORT_ACCESS_FROM_ENV (env);

  descriptor = (IDATA) getJavaIoFileDescriptorContentsAsAPointer (env, recv);
  if (descriptor == -1)
    {
      syncfailed = 1;
    }
  if (!syncfailed && (descriptor > 2))
    {
      /* Don't attempt to sync stdin, out, or err */
      syncfailed = hyfile_sync (descriptor) != 0;
    }
  if (syncfailed)
    {
      /* Find and throw SyncFailedException */
      throwNewExceptionByName(env, "java/io/SyncFailedException",
                              "Failed to Sync File");
      return;
    }
}

JNIEXPORT void JNICALL
Java_java_io_FileDescriptor_oneTimeInitialization (JNIEnv * env,
                                                   jclass fdClazz)
{
  jfieldID descriptorFID =
    (*env)->GetFieldID (env, fdClazz, "descriptor", "J");
  if (!descriptorFID)
    return;
  HARMONY_CACHE_SET (env, FID_java_io_FileDescriptor_descriptor, descriptorFID);
}
