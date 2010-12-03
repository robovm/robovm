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

#include "US_ASCII.h"

#include "hycomp.h"

JNIEXPORT jint JNICALL Java_org_apache_harmony_niochar_charset_US_1ASCII_00024Decoder_nDecode
  (JNIEnv *env, jobject obj, jcharArray outArr, jint arrPosition, jint remaining, jlong inAddr, jint absolutePos) 
{ 

   jchar *out = (*env)->GetCharArrayElements(env, outArr, NULL);

   jint position = absolutePos;	
   int i; 
   unsigned char input;
   for(i=0; i < remaining; i++) {
        input = *(jlong2addr(jbyte, inAddr) + position++);
         if( input > 0x007F) { 
             (*env)->ReleaseCharArrayElements(env, outArr, out, 0);
             return (jint)- (position-1 - absolutePos) ;
         }
         out[arrPosition+i] = (jchar)input;
   }
   (*env)->ReleaseCharArrayElements(env, outArr, out, 0);
   return position-absolutePos;

}


JNIEXPORT void JNICALL Java_org_apache_harmony_niochar_charset_US_1ASCII_00024Encoder_nEncode
  (JNIEnv *env, jobject obj, jlong outAddr, jint absolutePos, jcharArray array, jint arrayOffset, jintArray result) {

    jint position = absolutePos;
    int i;
    jchar input1;
    jchar *in = (*env)->GetCharArrayElements(env, array, NULL);
    jint *res = (*env)->GetIntArrayElements(env, result, NULL);
    for(i=0; i<res[0]; i++) {
         jchar input = in[arrayOffset+i];
         if( input > 0x007F) { 
             if (input >= 0xD800 && input <= 0xDFFF) {
                 if(i+1<res[0]) {
                     input1 = in[arrayOffset+i+1];
                     if(input1 >= 0xD800 && input1 <= 0xDFFF) {
                         res[0] = absolutePos - position; res[1] = 2; 
                         (*env)->ReleaseIntArrayElements(env, result, res, 0);
                         (*env)->ReleaseCharArrayElements(env, array, in, 0);
                         return;
                     } 
                 } else {
                     res[0]=absolutePos - position; res[1] = 0;
                     (*env)->ReleaseIntArrayElements(env, result, res, 0);
                     (*env)->ReleaseCharArrayElements(env, array, in, 0);
                     return;
                 }
                 res[0]=absolutePos - position; res[1] = -1;
                 (*env)->ReleaseIntArrayElements(env, result, res, 0);
                 (*env)->ReleaseCharArrayElements(env, array, in, 0);
                 return;
             }
             res[0]=absolutePos - position; res[1] = 1;
             (*env)->ReleaseIntArrayElements(env, result, res, 0);
             (*env)->ReleaseCharArrayElements(env, array, in, 0);
             return ;
         }
         *(jlong2addr(jbyte, outAddr) + position++) = (jbyte)input;
    }
    res[0]=position-absolutePos;
    (*env)->ReleaseIntArrayElements(env, result, res, 0);
    (*env)->ReleaseCharArrayElements(env, array, in, 0);
    return;
}
