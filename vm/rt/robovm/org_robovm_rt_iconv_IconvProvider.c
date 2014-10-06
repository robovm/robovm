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

/*
 * Class:     javaiconv_IconvProvider
 * Method:    initIconv
 * Signature: (Ljava/lang/String;)J
 */
JNIEXPORT jlong JNICALL Java_org_robovm_rt_iconv_IconvProvider_initIconv
  (JNIEnv *env, jclass thisObj, jstring fromEncoding, jstring toEncoding) {
  	
  	char* to_enc = (char*) (*env)->GetStringUTFChars(env, toEncoding, NULL);
  	char* from_enc = (char*) (*env)->GetStringUTFChars(env, fromEncoding, NULL);
  	
  	iconv_t content_descriptor = iconv_open(to_enc, from_enc);
	
	if (content_descriptor == (iconv_t) -1) {
		switch ((int)content_descriptor) {
		case EMFILE:
			return MAX_FILE_DESCRIPTORS;
		case ENFILE:
			return TOO_MANY_FILES_OPEN;
		case ENOMEM:
			return OUT_OF_MEMORY;
		case EINVAL:
			return UNSUPPORTED_CONVERSION;
		}
		return UNKNOWN_ERROR;
	}

	
	int enabled = 1;
	iconvctl(content_descriptor, ICONV_SET_DISCARD_ILSEQ, &enabled);

  	(*env)->ReleaseStringUTFChars(env, toEncoding, to_enc);
  	(*env)->ReleaseStringUTFChars(env, fromEncoding, from_enc);
  	
  	return (jlong) content_descriptor;
  	
  }

/*
 * Class:     javaiconv_IconvProvider
 * Method:    releaseIconv
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_robovm_rt_iconv_IconvProvider_releaseIconv
  (JNIEnv *env, jclass thisObj, jlong pointer) {

	iconv_t cd = (iconv_t) pointer;
	iconv_close(cd);

 }
  
 //Checks for errors after conv has been called
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
 
 //sets positions of buffers
 void set_buffer_positions(JNIEnv* env, int charBufferPosition, int byteBufferPosition, char** pChar, char** pByte ) {
 	*pChar += charBufferPosition*2;
 	*pByte += byteBufferPosition;
 }
 
//converts from specified encoding to specified encoding
 size_t convert(iconv_t content_descriptor, char* inbuffer, size_t* inlength, char* outbuffer, size_t* outlength, int* error) {

	if (content_descriptor == (iconv_t)-1) {
  		return ERROR_DESCRIPTOR;
  	}

 	size_t result = iconv(content_descriptor, &inbuffer, inlength, &outbuffer, outlength);
	(*error) = check_for_error(result, "error converting\n");
	
 	return result;
 }
 
 int reposition_and_convert(JNIEnv *env, jobject iconvresult, iconv_t content_descriptor, 
 size_t in_bytes, size_t out_bytes_left, char* pSrc, char* pDst) { 
  	
 	int error = 0;
 	//USE INTPUT POSITION HERE 
 	int position_src = in_bytes; //Changed here offset to 0
	int position_dst = out_bytes_left; //Changed here offset to 0

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
	}
	
    if (NULL == fidSource) {
    	return ERROR_METHOD_ID;
    }
    (*env)->SetIntField(env, iconvresult, fidSource, position_src);
        
    static jfieldID fidDestination = NULL;
    if (fidDestination == NULL) {
    	fidDestination = (*env)->GetFieldID(env, resultClass, "bytesWrittenToDestination", "I");
    }
    
    if (NULL == fidDestination) {
    	return ERROR_METHOD_ID;
    }
    
    (*env)->SetIntField(env, iconvresult, fidDestination, position_dst);

	return error;
 }

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
	
	// @TODO check 0 at the end... maybe a different position is needed if buffer is reused
	
	(*env)->ReleaseCharArrayElements(env, theCharArray, pCharArray, 0);
	(*env)->ReleaseByteArrayElements(env, theByteArray, pByteArray, 0);

  	return error;
   }

/*
 * Class:     javaiconv_IconvProvider
 * Method:    decodeNativeArray
 * Signature: (Ljava/lang/String;[B[CLjavaiconv/IconvResult;IIII)I
 */
JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_decodeNativeArray
  (JNIEnv *env, jclass thisObj, jlong cd, jbyteArray theByteArray, jcharArray theCharArray, 
   jobject iconvResult, jint positionIn, jint limitIn, jint positionOut, jint limitOut) {

    iconv_t content_descriptor = (iconv_t) cd;
    
  	//Get sizes pre/post conversion
  	size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jbyte) ;
  	size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jchar); //gahh check this
  	
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
   

/*
 * Class:     javaiconv_IconvProvider
 * Method:    encodeNativeBuffer
 * Signature: (Ljava/lang/String;Ljava/nio/CharBuffer;Ljava/nio/ByteBuffer;Ljavaiconv/IconvResult;IIII)I
 */
JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_encodeNativeBuffer
  (JNIEnv *env, jclass thisObj, jlong cd, jobject charbuffer, jobject bytebuffer, jobject iconvResult,
  jint positionIn, jint limitIn, jint positionOut, jint limitOut) {

	iconv_t content_descriptor = (iconv_t) cd;

  	//Get sizes pre/post conversion
  	size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jchar) ;
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

/*
 * Class:     javaiconv_IconvProvider
 * Method:    decodeNative
 * Signature: (Ljava/lang/String;Ljava/nio/ByteBuffer;Ljava/nio/CharBuffer;Ljavaiconv/IconvResult;IIII)I
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
 
/*
 * Class:     javaiconv_IconvProvider
 * Method:    encodeHybridBuffer
 * Signature: (J[CLjava/nio/ByteBuffer;Ljavaiconv/IconvResult;IIII)I
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

/*
 * Class:     javaiconv_IconvProvider
 * Method:    decodeHybridBuffer
 * Signature: (JLjava/nio/ByteBuffer;[CLjavaiconv/IconvResult;IIII)I
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
  	
  	char* pSrc = ((char *) pByteArray );// + positionIn);
 	char* pDst = ((char *) char_buf_address);
  	
  	set_buffer_positions(env, positionOut, positionIn, &pDst, &pSrc);
	int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);
	(*env)->ReleaseByteArrayElements(env, theByteArray, pByteArray, 0);
  	
  	return error;
   
  } 

/*
 * Class:     javaiconv_IconvProvider
 * Method:    encodeHybridBuffer
 * Signature: (JLjava/nio/CharBuffer;[BLjavaiconv/IconvResult;IIII)I
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

/*
 * Class:     javaiconv_IconvProvider
 * Method:    decodeHybridBuffer
 * Signature: (J[BLjava/nio/CharBuffer;Ljavaiconv/IconvResult;IIII)I
 */
JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_decodeHybridBufferArray
  (JNIEnv *env, jclass thisObj, jlong cd, jobject bytebuffer, jcharArray theCharArray, jobject iconvResult,
   jint positionIn, jint limitIn, jint positionOut, jint limitOut) { 

	iconv_t content_descriptor = (iconv_t) cd;

  	//Get sizes pre/post conversion
  	size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jbyte) ;
  	size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jchar); //gahh check this
  	
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