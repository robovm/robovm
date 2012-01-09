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
 * Common natives supporting the memory system interface.
 */

#include <string.h>
#if defined(DARWIN)
#include <sys/types.h>
#endif
#include <sys/mman.h>
#include <errno.h>
#include <unistd.h>
#include <string.h>
#include "vmi.h"
#include "OSMemory.h"
#include "IMemorySystem.h"
#include "exceptions.h"
#include "hyport.h"

#ifdef ZOS
#define FD_BIAS 1000
#else
#define FD_BIAS 0
#endif /* ZOS */

/* z/OS mman.h does not define MAP_FAILED - it should always be ((void*)-1) */
#ifndef MAP_FAILED
#define MAP_FAILED      ((void *) -1)
#endif

#define	OS_JNI(func) JNICALL Java_org_apache_harmony_luni_platform_OSMemory_##func

JNIEXPORT jboolean JNICALL Java_org_apache_harmony_luni_platform_OSMemory_isLittleEndianImpl(JNIEnv * env, jclass clazz)
{
  long l = 0x01020304;
  unsigned char* c = (unsigned char*)&l;
  return (*c == 0x04) ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSMemory_getPointerSizeImpl (JNIEnv * env, jclass clazz)
{
   return sizeof(void *);
}

JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSMemory_getAddress
  (JNIEnv * env, jobject thiz, jlong address)
{
  return (jlong) * (long *) ((IDATA) address);
}

JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_setAddress
  (JNIEnv * env, jobject thiz, jlong address, jlong value)
{
  *(long *) ((IDATA) address) = (long) value;
}


int getPageSize()
{
	static int page_size = 0;
	if(page_size==0)
		page_size=getpagesize();
	return page_size;
}

JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSMemory_loadImpl
  (JNIEnv * env, jobject thiz, jlong addr, jlong size){
#if !defined(ZOS)
   if(mlock((void *)((IDATA)addr), size)!=-1)
   {
      if(munlock((void *)((IDATA)addr),size)!=-1)
	      return 0;  /*normally*/
   }
   else{
      	if(errno == EPERM) /*according to linux sys call, only root can mlock memory.*/
      	   return 0;
   }
   return -1;
#else
   return 0;
#endif /* !defined(ZOS) */
  }

JNIEXPORT jboolean JNICALL Java_org_apache_harmony_luni_platform_OSMemory_isLoadedImpl
  (JNIEnv * env, jobject thiz, jlong addr, jlong size){
#if !defined(ZOS)
	  PORT_ACCESS_FROM_ENV (env);
  	  jboolean result = 0;
  	  IDATA m_addr = (IDATA)addr;
	  int page_size = getPageSize();
#if defined(FREEBSD) || defined(DARWIN)
#define HY_VEC_T char
#else
#define HY_VEC_T unsigned char
#endif
	  HY_VEC_T* vec = NULL;
	  int page_count = 0;
	  int align_offset = m_addr%page_size;//addr should align with the boundary of a page.
	  m_addr -= align_offset;
	  size   += align_offset;
	  page_count = (size+page_size-1)/page_size;
	  vec = (HY_VEC_T *) hymem_allocate_memory(page_count*sizeof(char));
	  if(mincore((void *)m_addr, size, vec)==0) //or else there is error about the mincore and return false;
#undef HY_VEC_T
	  {
	  	  int i;
		  for(i=0 ;i<page_count;i++)
			  if(vec[i]!=1)
			     break;
		  if(i==page_count)
			  result = 1;
	  }
	  hymem_free_memory(vec);
      return result;
#else
      return ;
#endif /* !defined(ZOS) */
  }

JNIEXPORT jint JNICALL Java_org_apache_harmony_luni_platform_OSMemory_flushImpl
  (JNIEnv * env, jobject thiz, jlong addr, jlong size){
  return msync((void *)((IDATA)addr), size, MS_SYNC);
}

/*
 * Class:     org_apache_harmony_luni_platform_OSMemory
 * Method:    unmapImpl
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_apache_harmony_luni_platform_OSMemory_unmapImpl
  (JNIEnv * env, jobject thiz, jlong addr, jlong size)
{
    munmap((void *)((IDATA)addr), (size_t)size);
}

/*
 * Class:     org_apache_harmony_luni_platform_OSMemory
 * Method:    mmapImpl
 * Signature: (JJJI)J
 */
JNIEXPORT jlong JNICALL Java_org_apache_harmony_luni_platform_OSMemory_mmapImpl
  (JNIEnv * env, jobject thiz, jlong fd, jlong alignment, jlong size, jint mmode)
{
  PORT_ACCESS_FROM_ENV (env);
  void *mapAddress = NULL;
  int prot, flags;

  // Convert from Java mapping mode to port library mapping mode.
  switch (mmode)
    {
      case org_apache_harmony_luni_platform_IMemorySystem_MMAP_READ_ONLY:
	prot = PROT_READ;
	flags = MAP_SHARED;
        break;
      case org_apache_harmony_luni_platform_IMemorySystem_MMAP_READ_WRITE:
	prot = PROT_READ|PROT_WRITE;
	flags = MAP_SHARED;
        break;
      case org_apache_harmony_luni_platform_IMemorySystem_MMAP_WRITE_COPY:
	prot = PROT_READ|PROT_WRITE;
	flags = MAP_PRIVATE;
        break;
      default:
    throwJavaIoIOException(env, "Map mode not recognised");
        return -1;
    }

  mapAddress = mmap(0, (size_t)(size&0x7fffffff), prot, flags, fd-FD_BIAS, (off_t)(alignment&0x7fffffff));
  if (mapAddress == MAP_FAILED)
    {
      hyerror_set_last_error(errno, HYPORT_ERROR_OPFAILED);
      throwJavaIoIOException(env, hyerror_last_error_message());
      return -1;
    }
  return (jlong) ((IDATA)mapAddress);
}

