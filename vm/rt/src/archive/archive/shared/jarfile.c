/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

#include "vmi.h"
#include "iohelp.h"
#include "exceptions.h"
#include "jclglob.h"

#ifndef HY_ZIP_API
#include "zipsup.h"
#else /* HY_ZIP_API */
#include "vmizip.h"
#endif /* HY_ZIP_API */

/* Build a new ZipEntry from the C struct */
jobject
#ifndef HY_ZIP_API
createZipEntry (JNIEnv * env, HyZipFile * zipFile, HyZipEntry * zipEntry)
#else
createZipEntry (JNIEnv * env, VMIZipFile * zipFile, VMIZipEntry * zipEntry)
#endif
{
  PORT_ACCESS_FROM_ENV (env);
#ifdef HY_ZIP_API
  VMI_ACCESS_FROM_ENV(env);
#endif /* HY_ZIP_API */
  jclass javaClass;
  jobject java_ZipEntry, extra, entryName;
  jmethodID mid;
#ifdef HY_ZIP_API
  VMIZipFunctionTable *zipFuncs = (*VMI)->GetZipFunctions(VMI);
#endif /* HY_ZIP_API */

  /* Build a new ZipEntry from the C struct */
  entryName = ((*env)->NewStringUTF (env, (const char*)zipEntry->filename));
  if (((*env)->ExceptionCheck (env)))
    return NULL;

  extra = NULL;
  if (zipEntry->extraFieldLength > 0)
    {
#ifndef HY_ZIP_API
      zip_getZipEntryExtraField (PORTLIB, zipFile, zipEntry, NULL,
#else /* HY_ZIP_API */
      zipFuncs->zip_getZipEntryExtraField (VMI, zipFile, zipEntry, NULL,
#endif /* HY_ZIP_API */
				 zipEntry->extraFieldLength);
      if (zipEntry->extraField == NULL)
	return NULL;
      extra = ((*env)->NewByteArray (env, zipEntry->extraFieldLength));
      if (((*env)->ExceptionCheck (env)))
	return NULL;
      ((*env)->
       SetByteArrayRegion (env, extra, 0, zipEntry->extraFieldLength,
			   (jbyte*)zipEntry->extraField));
      jclmem_free_memory (env, zipEntry->extraField);
      zipEntry->extraField = NULL;
    }

  javaClass = JCL_CACHE_GET (env, CLS_java_util_zip_ZipEntry);
  mid = JCL_CACHE_GET (env, MID_java_util_zip_ZipEntry_init);
  java_ZipEntry = ((*env)->NewObject (env, javaClass, mid, entryName, NULL,
				      extra,
				      (jlong) zipEntry->lastModTime,
				      (jlong) zipEntry->uncompressedSize,
				      (jlong) zipEntry->compressedSize,
				      (jlong) zipEntry->crc32,
				      zipEntry->compressionMethod,
				      (jlong) zipEntry->lastModDate,
				      (jlong) zipEntry->dataPointer));
  return java_ZipEntry;
}

JNIEXPORT jarray JNICALL
Java_java_util_jar_JarFile_getMetaEntriesImpl (JNIEnv * env, jobject recv,
					       jbyteArray zipName)
{

#define RESULT_BUF_SIZE 256

  PORT_ACCESS_FROM_ENV (env);
#ifdef HY_ZIP_API
  VMI_ACCESS_FROM_ENV(env);
#endif /* HY_ZIP_API */

  JCLZipFile *jclZipFile;
#ifdef HY_ZIP_API
  VMIZipFile *zipFile;
  VMIZipEntry zipEntry;
#else
  HyZipFile *zipFile;
  HyZipEntry zipEntry;
#endif
  jobject current;
  jclass javaClass;
  jobject resultArray[RESULT_BUF_SIZE];
  UDATA resultCount = 0, offset, i;
  void *scanPtr;
  char metaInfName[10];		/* 10 == strlen("META-INF/") + 1 */
  jobjectArray result = NULL;
  char *nameBuf, *newNameBuf, *oldNameBuf = NULL;
  char startNameBuf[HyMaxPath];
  UDATA nameBufSize = HyMaxPath;
  IDATA rc;
#ifdef HY_ZIP_API
  VMIZipFunctionTable *zipFuncs = (*VMI)->GetZipFunctions(VMI);
#endif /* HY_ZIP_API */

  nameBuf = (char *) &startNameBuf;

  jclZipFile =
    (JCLZipFile *) (IDATA) (*env)->GetLongField (env, recv,
						 JCL_CACHE_GET (env,
								FID_java_util_zip_ZipFile_descriptor));
  if (jclZipFile == (void *) -1)
    {
      throwNewIllegalStateException (env, "");
      return NULL;
    }
  zipFile = &(jclZipFile->hyZipFile);

  if (zipFile->cache)
    {
#ifndef HY_ZIP_API
      if (zipCache_enumNew (zipFile->cache, "META-INF/", &scanPtr))
#else /* HY_ZIP_API */
      if (zipFuncs->zipCache_enumNew (zipFile->cache, "META-INF/", &scanPtr))
#endif /* HY_ZIP_API */
	return NULL;

      if (0 !=
#ifndef HY_ZIP_API
	  zipCache_enumGetDirName (scanPtr, (char *) &metaInfName,
#else /* HY_ZIP_API */
	  zipFuncs->zipCache_enumGetDirName (scanPtr, (char *) &metaInfName,
#endif /* HY_ZIP_API */
				   sizeof (metaInfName)))
	return NULL;

      for (;;)
	{
#ifndef HY_ZIP_API
	  rc = zipCache_enumElement (scanPtr, nameBuf, nameBufSize, &offset);
#else /* HY_ZIP_API */
	  rc = zipFuncs->zipCache_enumElement (scanPtr, nameBuf, nameBufSize, &offset);
#endif /* HY_ZIP_API */
	  if (rc < 0)
	    {
	      break;		/* we're done, leave the loop */
	    }
	  else if (rc > 0)
	    {
	      /* the buffer wasn't big enough, grow it */
	      newNameBuf = jclmem_allocate_memory (env, rc);
	      nameBufSize = rc;
	      if (oldNameBuf)
		{
		  jclmem_free_memory (env, oldNameBuf);	/* free old before checking result so we clean up on fail */
		  oldNameBuf = NULL;
		}
	      if (!newNameBuf)
		goto cleanup;
	      nameBuf = oldNameBuf = newNameBuf;
	      continue;		/* go to the top of the loop again */
	    }

#ifndef HY_ZIP_API
	  zip_initZipEntry (PORTLIB, &zipEntry);
	  if (zip_getZipEntryFromOffset (PORTLIB, zipFile, &zipEntry, offset))
#else /* HY_ZIP_API */
	  zipFuncs->zip_initZipEntry (VMI, &zipEntry);
	  if (zipFuncs->zip_getZipEntryFromOffset (VMI, zipFile, &zipEntry, offset, 0))
#endif /* HY_ZIP_API */
	    goto cleanup;
	  current = createZipEntry (env, zipFile, &zipEntry);
#ifndef HY_ZIP_API
	  zip_freeZipEntry (PORTLIB, &zipEntry);
#else /* HY_ZIP_API */
	  zipFuncs->zip_freeZipEntry (VMI, &zipEntry);
#endif /* HY_ZIP_API */
	  if (resultCount == RESULT_BUF_SIZE)
	    goto cleanup;	/* fail - should fix. */
	  if (current)
	    resultArray[resultCount++] = current;
	  else
	    goto cleanup;
	}
      javaClass = JCL_CACHE_GET (env, CLS_java_util_zip_ZipEntry);
      result = ((*env)->NewObjectArray (env, resultCount, javaClass, NULL));
      if (((*env)->ExceptionCheck (env)))
	{
	  result = NULL;
	  goto cleanup;
	}
      for (i = 0; i < resultCount; i++)
	{
	  (*env)->SetObjectArrayElement (env, result, i, resultArray[i]);
	}
    cleanup:
#ifndef HY_ZIP_API
      zipCache_enumKill (scanPtr);
#else /* HY_ZIP_API */
      zipFuncs->zipCache_enumKill (scanPtr);
#endif /* HY_ZIP_API */
      if (oldNameBuf)
	jclmem_free_memory (env, oldNameBuf);	/* free old before checking result so we clean up on fail */
      return result;
    }
  return NULL;

#undef RESULT_BUF_SIZE
}
