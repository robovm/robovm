/*
 * Copyright (C) 2014 Trillian Mobile AB
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/gpl-2.0.html>.
 */

#include <jni.h>
#include <errno.h>
#include <iconv.h>
#include <stdlib.h>
#include <stdio.h>
#include <wchar.h>
#include <locale.h>
#include <string.h>

#ifndef _Included_javaiconv_IconvProvider
#define _Included_javaiconv_IconvProvider
#ifdef __cplusplus
extern "C" {
#endif

#define CONVERSION_OK 0
#define ILLEGAL_SEQUENCE 1
#define OUTPUT_BUFFER_TOO_SMALL 2
#define INCOMPLETE_SEQUENCE 3
#define ERROR_METHOD_ID 4
#define ERROR_ARRAY_FUNCTION_CALL 5
#define ERROR_DESCRIPTOR 6
#define UNKNOWN_ERROR 7
#define MAX_FILE_DESCRIPTORS 8
#define TOO_MANY_FILES_OPEN 9
#define UNSUPPORTED_CONVERSION 10
#define OUT_OF_MEMORY 11

/**
* throws IllegalStateException
*/
jint throwIllegalStateException( JNIEnv* env, char* message) {
    jclass exceptionClass;
    char *className = "java/lang/IllegalStateException";
    
    exceptionClass = (*env)->FindClass(env, className);
    if (exceptionClass == NULL) {
        return -1;
    }
    
    return (*env)->ThrowNew(env, exceptionClass, message);
}

/**
* Inits content descriptor of encoding / decoding  
**/
JNIEXPORT jlong JNICALL Java_org_robovm_rt_iconv_IconvProvider_initIconv
  (JNIEnv *env, jclass thisObj, jstring fromEncoding, jstring toEncoding) {

    char* to_enc = (char*) (*env)->GetStringUTFChars(env, toEncoding, NULL);
    char* from_enc = (char*) (*env)->GetStringUTFChars(env, fromEncoding, NULL);
      
    iconv_t content_descriptor = iconv_open(to_enc, from_enc);
    
    if (content_descriptor == (iconv_t) -1) {
        char* errorMessage = NULL;
        switch (errno) {
        case EMFILE:
            errorMessage = "MAX_FILE_DESCRIPTORS";
            break;
        case ENFILE:
            errorMessage = "TOO_MANY_FILES_OPEN";
            break;
        case ENOMEM:
            errorMessage = "OUT_OF_MEMORY";
            break;
        case EINVAL:
            errorMessage = "UNSUPPORTED_CONVERSION";
            break;
        default:
            errorMessage = "";
            break;    
        }
        (*env)->ReleaseStringUTFChars(env, toEncoding, to_enc);
        (*env)->ReleaseStringUTFChars(env, fromEncoding, from_enc);
        return throwIllegalStateException(env, errorMessage);
    }

    int enabled = 1;
    iconvctl(content_descriptor, ICONV_SET_DISCARD_ILSEQ, &enabled);

    (*env)->ReleaseStringUTFChars(env, toEncoding, to_enc);
    (*env)->ReleaseStringUTFChars(env, fromEncoding, from_enc);
      
    return (jlong) content_descriptor;     
}

/**
* Frees allocated content descriptor
*/
JNIEXPORT void JNICALL Java_org_robovm_rt_iconv_IconvProvider_releaseIconv
  (JNIEnv *env, jclass thisObj, jlong pointer) {

    iconv_t cd = (iconv_t) pointer;
    iconv_close(cd);

 }
  
 /**
 * Checks for errors after iconv has been called
 */
 int check_for_error(size_t result, const char* message) {
    if (result == (size_t)-1 ) {
        if (errno == EILSEQ) {
            return ILLEGAL_SEQUENCE;
        } else if (errno == E2BIG) {
            return OUTPUT_BUFFER_TOO_SMALL;
        } else if (errno == EINVAL) {
            return INCOMPLETE_SEQUENCE;
        } else {
            return UNKNOWN_ERROR;
        }
    }
    return result;
 }
 
 /**
 * sets positions of buffers
 */
 void set_buffer_positions(JNIEnv* env, int charBufferPosition, int byteBufferPosition, char** pChar, char** pByte ) {
     if (pChar != NULL) {
        *pChar += charBufferPosition*2;
     }
     if (pByte != NULL) {
         *pByte += byteBufferPosition;
     }
 }
 
/**
 * converts data from/to according to content descriptor and handles possible errors
 */
 size_t convert(iconv_t content_descriptor, char* inbuffer, size_t* inlength, char* outbuffer, size_t* outlength, int* error) {
     
    if (content_descriptor == (iconv_t)-1) {
        return ERROR_DESCRIPTOR;
    }

    size_t result = iconv(content_descriptor, &inbuffer, inlength, &outbuffer, outlength);
    (*error) = check_for_error(result, "error converting\n");
    
    return result;
 }
 
 /**
 * Converts data and repositions starting points in buffers
 */
 int reposition_and_convert(JNIEnv *env, jobject iconvresult, iconv_t content_descriptor, 
 size_t in_bytes, size_t out_bytes_left, char* pSrc, char* pDst) { 
      
    int error = 0;
    //USE INTPUT POSITION HERE 
    int position_src = in_bytes; 
    int position_dst = out_bytes_left; 

    convert(content_descriptor, pSrc, &in_bytes, pDst, &out_bytes_left, &error);
    
    //Set positions accordingly;
    position_src -= in_bytes;
    
    //Set positions accordingly;
    position_dst -= out_bytes_left;
    
    static jclass resultClass = NULL;
    
    if (resultClass == NULL) {
        resultClass = (*env)->GetObjectClass(env, iconvresult);
    }
    
    static jfieldID fidSource = NULL;
    if (fidSource == NULL) {
        fidSource = (*env)->GetFieldID(env, resultClass, "bytesWrittenFromSource", "I");
        
        if (NULL == fidSource) {
            return -1; //Exception has been raised
        }
    }
    
    (*env)->SetIntField(env, iconvresult, fidSource, position_src);
        
    static jfieldID fidDestination = NULL;
    if (fidDestination == NULL) {
        fidDestination = (*env)->GetFieldID(env, resultClass, "bytesWrittenToDestination", "I");
        
        if (NULL == fidDestination) {
            return -1; //Exception has been raised
        }
    }

    (*env)->SetIntField(env, iconvresult, fidDestination, position_dst);

    return error;
 }
    
 /**
 * Flushes char buffer if array backed
 */
 JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_flushCharArray
 (JNIEnv *env, jclass thisObj, jlong cd, jcharArray theCharArray,
    jobject iconvResult, jint positionOut, jint limitOut) {
        
    iconv_t content_descriptor = (iconv_t) cd;
        
    //Get sizes pre/post conversion
    size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jchar);
        
    if (out_bytes_left <= 0) {
        return 0;
    }
        
    jchar* pCharArray = (jchar*) (*env)->GetByteArrayElements(env, theCharArray, NULL);
        
    char* pDst = ((char *) pCharArray);
        
    set_buffer_positions(env, 0, positionOut, NULL, &pDst);   
    int error = reposition_and_convert(env, iconvResult, content_descriptor, 0, out_bytes_left, NULL, pDst);
        
    (*env)->ReleaseCharArrayElements(env, theCharArray, pCharArray, 0);
        
    return error;
 }
    
 /**
 *Flushes char buffer if direct
 */
 JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_flushCharDirect
 (JNIEnv *env, jclass thisObj, jlong cd, jobject charbuffer,
    jobject iconvResult, jint positionOut, jint limitOut) {
        
    iconv_t content_descriptor = (iconv_t) cd;
        
    //Get sizes pre/post conversion
    size_t in_bytes = 0;
    size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jchar);
        
    if (out_bytes_left <= 0) {
        return 0;
    }
        
    //fix direct buffer stuff here
    jchar* char_buf_address = (jchar*) (*env)->GetDirectBufferAddress(env, charbuffer);
        
    char* pSrc = NULL;
    char* pDst = ((char *) char_buf_address);
        
    set_buffer_positions(env, 0, positionOut, NULL, &pDst);
    int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);
        
    return error;
 }

 /**
 * Flushes buffer if array backed
 */
 JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_flushByteArray
  (JNIEnv *env, jclass thisObj, jlong cd, jbyteArray theByteArray,
   jobject iconvResult, jint positionOut, jint limitOut) {

    iconv_t content_descriptor = (iconv_t) cd;

    //Get sizes pre/post conversion
    size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jbyte);
      
      if (out_bytes_left <= 0) {
          return 0;
      }
      
      
    jbyte* pByteArray = (*env)->GetByteArrayElements(env, theByteArray, NULL);

    char* pDst = ((char *) pByteArray);

    set_buffer_positions(env, 0, positionOut, NULL, &pDst);

    int error = reposition_and_convert(env, iconvResult, content_descriptor, 0, out_bytes_left, NULL, pDst);
    
    (*env)->ReleaseByteArrayElements(env, theByteArray, pByteArray, 0);

    return error;
  }
  
 /**
 *Flushes buffer if direct
 */
 JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_flushByteDirect
  (JNIEnv *env, jclass thisObj, jlong cd, jobject bytebuffer,
   jobject iconvResult, jint positionOut, jint limitOut) {
   
      iconv_t content_descriptor = (iconv_t) cd;

    //Get sizes pre/post conversion
    size_t in_bytes = 0;
    size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jbyte);
      
      if (out_bytes_left <= 0) {
          return 0;
      }
      
    //fix direct buffer stuff here
    jbyte* byte_buf_address = (jbyte*) (*env)->GetDirectBufferAddress(env, bytebuffer);
      
    char* pSrc = NULL;
    char* pDst = ((char *) byte_buf_address);
    
    set_buffer_positions(env, 0, positionOut, NULL, &pDst);
    int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);
    
    return error;
  }

/**
* Handles the case when both buffers are backed by arrays
*/
JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_encodeNativeArray
  (JNIEnv *env, jclass thisObj, jlong cd, jcharArray theCharArray, jbyteArray theByteArray,
   jobject iconvResult, jint positionIn, jint limitIn, jint positionOut, jint limitOut) {

    iconv_t content_descriptor = (iconv_t) cd;

    //Get sizes pre/post conversion
    size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jchar) ;
    size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jbyte);
      
    jchar* pCharArray = (*env)->GetCharArrayElements(env, theCharArray, NULL);
    jbyte* pByteArray = (*env)->GetByteArrayElements(env, theByteArray, NULL);
      
    char* pSrc = ((char *) pCharArray);
    char* pDst = ((char *) pByteArray);

    set_buffer_positions(env, positionIn, positionOut, &pSrc, &pDst);

    int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);
    
    (*env)->ReleaseCharArrayElements(env, theCharArray, pCharArray, 0);
    (*env)->ReleaseByteArrayElements(env, theByteArray, pByteArray, 0);

    return error;
}

/**
* Handles the case when both buffers are backed by arrays
*/
JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_decodeNativeArray
  (JNIEnv *env, jclass thisObj, jlong cd, jbyteArray theByteArray, jcharArray theCharArray, 
   jobject iconvResult, jint positionIn, jint limitIn, jint positionOut, jint limitOut) {

    iconv_t content_descriptor = (iconv_t) cd;
    
    //Get sizes pre/post conversion
    size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jbyte);
    size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jchar);
      
    jchar* pCharArray = (*env)->GetCharArrayElements(env, theCharArray, NULL);
    jbyte* pByteArray = (*env)->GetByteArrayElements(env, theByteArray, NULL);
      
    char* pSrc = ((char *) pByteArray);
    char* pDst = ((char *) pCharArray);

    set_buffer_positions(env, positionOut, positionIn, &pDst, &pSrc);
    int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);
    
    // @TODO check 0 at the end... maybe a different position is needed if buffer is reused
    (*env)->ReleaseCharArrayElements(env, theCharArray, pCharArray, 0);
    (*env)->ReleaseByteArrayElements(env, theByteArray, pByteArray, 0);
    
    return error;
}
   

/**
* Handles the case when both buffers are direct
*/
JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_encodeNativeBuffer
  (JNIEnv *env, jclass thisObj, jlong cd, jobject charbuffer, jobject bytebuffer, jobject iconvResult,
  jint positionIn, jint limitIn, jint positionOut, jint limitOut) {

    iconv_t content_descriptor = (iconv_t) cd;

    //Get sizes pre/post conversion
    size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jchar);
    size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jbyte);
      
    //fix direct buffer stuff here
    jbyte* char_buf_address = (jbyte*) (*env)->GetDirectBufferAddress(env, charbuffer);
    jbyte* byte_buf_address = (jbyte*) (*env)->GetDirectBufferAddress(env, bytebuffer);
      
    char* pSrc = ((char *) char_buf_address);
    char* pDst = ((char *) byte_buf_address);
    
    set_buffer_positions(env, positionIn, positionOut, &pSrc, &pDst);
    int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);
    
    return error;
}

/**
* Handles the case when both buffers are direct
*/
JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_decodeNativeBuffer
  (JNIEnv *env, jclass thisObj, jlong cd, jobject bytebuffer, jobject charbuffer, jobject iconvResult,
  jint positionIn, jint limitIn, jint positionOut, jint limitOut) {

    iconv_t content_descriptor = (iconv_t) cd;

    //Get sizes pre/post conversion
    size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jbyte) ;
    size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jchar);
      
    //fix direct buffer stuff here
    char* char_buf_address = (char*) (*env)->GetDirectBufferAddress(env, charbuffer);
    char* byte_buf_address = (char*) (*env)->GetDirectBufferAddress(env, bytebuffer);
      
    char* pSrc = ((char *) byte_buf_address);
    char* pDst = ((char *) char_buf_address);
      
    set_buffer_positions(env, positionOut, positionIn, &pDst, &pSrc);
    int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);
    
    return error;
}
 
/**
* Handles the case when input buffer is array backed and output is direct
*/
JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_encodeHybridArrayBuffer
  (JNIEnv *env, jclass thisObj, jlong cd, jcharArray theCharArray, jobject bytebuffer, jobject iconvResult,
   jint positionIn, jint limitIn, jint positionOut, jint limitOut) {

    iconv_t content_descriptor = (iconv_t) cd;

    //Get sizes pre/post conversion
    size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jchar) ;
    size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jbyte);
      
    jchar* pCharArray = (*env)->GetCharArrayElements(env, theCharArray, NULL);
      
    //fix direct buffer stuff here
    char* byte_buf_address = (char*) (*env)->GetDirectBufferAddress(env, bytebuffer);
      
    char* pSrc = (((char *) pCharArray ));
    char* pDst = ((char *) byte_buf_address);
      
    set_buffer_positions(env, positionIn, positionOut, &pSrc, &pDst);
    int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);
    (*env)->ReleaseCharArrayElements(env, theCharArray, pCharArray, 0);    
    return error;
}

/**
* Handles the case when input buffer is array backed and output is direct
*/
JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_decodeHybridArrayBuffer
   (JNIEnv *env, jclass thisObj, jlong cd, jbyteArray theByteArray, jobject charbuffer, jobject iconvResult,
   jint positionIn, jint limitIn, jint positionOut, jint limitOut) {
   
    iconv_t content_descriptor = (iconv_t) cd;

    //Get sizes pre/post conversion
    size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jbyte) ;
    size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jchar);
      
    jbyte* pByteArray = (*env)->GetByteArrayElements(env, theByteArray, NULL);
      
    //fix direct buffer stuff here
    char* char_buf_address = (char*) (*env)->GetDirectBufferAddress(env, charbuffer);
      
    char* pSrc = ((char *) pByteArray );
    char* pDst = ((char *) char_buf_address);
      
    set_buffer_positions(env, positionOut, positionIn, &pDst, &pSrc);
    int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);
    (*env)->ReleaseByteArrayElements(env, theByteArray, pByteArray, 0);
      
    return error;
   
} 

/**
* Handles case when input buffer is direct and output buffer array backed
*/
JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_encodeHybridBufferArray
  (JNIEnv *env, jclass thisObj, jlong cd, jobject charbuffer, jbyteArray theByteArray, jobject iconvResult,
   jint positionIn, jint limitIn, jint positionOut, jint limitOut) {
    
    iconv_t content_descriptor = (iconv_t) cd;
    
    //Get sizes pre/post conversion
    size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jchar) ;
    size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jbyte);
      
    //fix direct buffer stuff here
    jbyte* char_buf_address = (jbyte*) (*env)->GetDirectBufferAddress(env, charbuffer);
    jbyte* pByteArray = (*env)->GetByteArrayElements(env, theByteArray, NULL);
      
    char* pSrc = ((char *) char_buf_address);
    char* pDst = ((char *) pByteArray);

    set_buffer_positions(env, positionIn, positionOut, &pSrc, &pDst);

    int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);
    (*env)->ReleaseByteArrayElements(env, theByteArray, pByteArray, 0);    
    
    return error;
}

/**
* Handles case when input buffer is direct and output buffer array backed
*/
JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_decodeHybridBufferArray
  (JNIEnv *env, jclass thisObj, jlong cd, jobject bytebuffer, jcharArray theCharArray, jobject iconvResult,
   jint positionIn, jint limitIn, jint positionOut, jint limitOut) { 

    iconv_t content_descriptor = (iconv_t) cd;

    //Get sizes pre/post conversion
    size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jbyte) ;
    size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jchar);
      
    //fix direct buffer stuff here
    jbyte* byte_buf_address = (jbyte*) (*env)->GetDirectBufferAddress(env, bytebuffer);
    jchar* pCharArray = (*env)->GetCharArrayElements(env, theCharArray, NULL);
      
    char* pSrc = ((char *) byte_buf_address);
    char* pDst = ((char *) pCharArray);

    set_buffer_positions(env, positionOut, positionIn, &pDst, &pSrc);
    int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);
    (*env)->ReleaseCharArrayElements(env, theCharArray, pCharArray, 0);    
    
    return error;
}

#ifdef __cplusplus
}
#endif
#endif