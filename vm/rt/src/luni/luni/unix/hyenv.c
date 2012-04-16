/* Licensed to the Apache Software Foundation (ASF) under one or more
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
#include <string.h>
#include <vmi.h>
#include "hyenv.h"
#include <stdlib.h>
#if defined(MACOSX)
#include <crt_externs.h>
#define environ (*_NSGetEnviron())
#else
extern char** environ;
#endif

JNIEXPORT jbyteArray JNICALL Java_org_apache_harmony_luni_platform_Environment_getEnvBytes
  (JNIEnv *env, jclass obj){
  jbyteArray byteArray;
  int bufsize=0,i=0,start=0,len=0;
  for(i=0;*(environ+i);i++){
    bufsize+=strlen(environ[i])+1;
  }
  byteArray = (*env)->NewByteArray(env,bufsize);
  for(i=0;*(environ+i);i++){
      len=strlen(environ[i])+1;
      (*env)->SetByteArrayRegion(env,byteArray, start, len, (jbyte *)environ[i]);
      start+=len;
  }
  return byteArray;
}

JNIEXPORT jbyteArray JNICALL Java_org_apache_harmony_luni_platform_Environment_getEnvByName
  (JNIEnv *env, jclass obj, jbyteArray name){
  jsize len = 0;
  jbyteArray byteArray;
  char *envname, *envvalue;
  PORT_ACCESS_FROM_ENV(env);

  len = (*env)->GetArrayLength(env, name);
  envname = (char *)hymem_allocate_memory(len+1);  
  (*env)->GetByteArrayRegion(env, name, 0, len,(jbyte *)envname);
  envname[len] = 0;

  envvalue = getenv(envname);
  hymem_free_memory(envname);
  
  if(NULL == envvalue){
    return NULL;
  }
  
  len = strlen(envvalue);
  byteArray = (*env)->NewByteArray(env,len);
  (*env)->SetByteArrayRegion(env,byteArray, 0, len, (jbyte *)envvalue);
  return byteArray;
}
