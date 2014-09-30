#include <jni.h>
#include <errno.h>
#include <iconv.h>
#include <stdlib.h>
#include <stdio.h>
#include <wchar.h>
#include <locale.h>
#include <string.h>
/* Header for class javaiconv_IconvProvider */

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

//typedef struct {
//   iconv_t pointer;
//} IconvDescriptor;

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
	
  	////printf("address: \n", &content_descriptor);
  	(*env)->ReleaseStringUTFChars(env, toEncoding, to_enc);
  	(*env)->ReleaseStringUTFChars(env, fromEncoding, from_enc);
  	//printf("descriptor set:\n");
  	
  	return (jlong) content_descriptor;
  	
  }

/*
 * Class:     javaiconv_IconvProvider
 * Method:    releaseIconv
 * Signature: (J)V
 */
JNIEXPORT void JNICALL Java_org_robovm_rt_iconv_IconvProvider_releaseIconv
  (JNIEnv *env, jclass thisObj, jlong pointer) {
	//IconvDescriptor* p = (IconvDescriptor*) pointer;
	
	iconv_t cd = (iconv_t) pointer;
	//printf("deallocated address: %d\n", &cd);
	iconv_close(cd);
	//free(p);
  }
  
 //Checks for errors after conv has been called
 int check_for_error(size_t result, const char* message) {
   	if (result == (size_t)-1 ) {
 	  	//printf(message);
  		
  		if (errno == EILSEQ) {
  			//printf("EILSEQ");
  			return ILLEGAL_SEQUENCE;
  		} else if (errno == E2BIG) {
  			//printf("E2BIG");
  			return OUTPUT_BUFFER_TOO_SMALL;
  		} else if (errno == EINVAL) {
  			//printf("EINVAL");
  			return INCOMPLETE_SEQUENCE;
  		} else {
  			return UNKNOWN_ERROR;
  		}
  	 }
  	 return result;
 }
 
 //sets position of buffers
 void set_buffer_positions(JNIEnv* env, int charBufferPosition, int byteBufferPosition, char** pChar, char** pByte ) {

 	*pChar += charBufferPosition*2;
 	*pByte += byteBufferPosition;
 
 }
 
//converts from specified encoding to specified encoding
//error messages are printed to stdout, no error handling implemented yet
 size_t convert(iconv_t content_descriptor, char* inbuffer, size_t* inlength, char* outbuffer, size_t* outlength, int* error) {

 	//iconv_t content_descriptor = iconv_open(to, from);
	if (content_descriptor == (iconv_t)-1) {
  		//printf("descriptor error\n");
  		return ERROR_DESCRIPTOR;
  	}
 	
 	//printf("In buffer mbs: %s\n", inbuffer);
 	//setlocale(LC_ALL,"");
 	//w//printf(L"In buffer wcs: %ls", (wchar_t*)inbuffer);
 	
 	//printf("inbytes: %lu \n", (*inlength));
 	//printf("outbytes: %lu \n", (*outlength));
 	
 	//size_t result = iconv(content_descriptor, NULL, NULL, &outbuffer, outlength);
 	size_t result = iconv(content_descriptor, &inbuffer, inlength, &outbuffer, outlength);
	(*error) = check_for_error(result, "error converting\n");
	//iconv_close(content_descriptor);
	
	//printf("Error: %d \n", (*error));
	//printf("in bytes left: %lu\n", (*inlength));
	//printf("out bytes left: %lu\n", (*outlength));
	
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
	//printf("position_src: %d\n", position_src);
	
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

  	//jmethodID methodSourceBytes = (*env)->GetMethodID(env, resultClass, "setBytesWrittenFromSource", "(I)V");
  	//jmethodID methodDestinationBytes = (*env)->GetMethodID(env, resultClass, "setBytesWrittenToDestination", "(I)V");
  	//(*env)->CallVoidMethod(env, iconvresult, methodSourceBytes, position_src);
  	//(*env)->CallVoidMethod(env, iconvresult, methodDestinationBytes, position_dst);
  	
	//printf("position_dst: %d\n", position_dst);
	return error;
 }

JNIEXPORT jint JNICALL Java_org_robovm_rt_iconv_IconvProvider_encodeNativeArray
  (JNIEnv *env, jclass thisObj, jlong cd, jcharArray theCharArray, jbyteArray theByteArray,
   jobject iconvResult, jint positionIn, jint limitIn, jint positionOut, jint limitOut) {

   	//printf("Encode arrays\n");

  	iconv_t content_descriptor = (iconv_t) cd;//((IconvDescriptor*) cd)->pointer;

  	//Get sizes pre/post conversion
  	size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jchar) ;
  	size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jbyte);
  	
  	jchar* pCharArray = (*env)->GetCharArrayElements(env, theCharArray, NULL);
  	jbyte* pByteArray = (*env)->GetByteArrayElements(env, theByteArray, NULL);
  	
  	//printf("encoded array pre copy %s \n", (pByteArray));
  	
  	char* pSrc = ((char *) pCharArray);
 	char* pDst = ((char *) pByteArray);
	
	//printf("pre set buffer pSrc: %p \n", pSrc);
  	//printf("pre set buffer pDst: %p \n", pDst);
  	set_buffer_positions(env, positionIn, positionOut, &pSrc, &pDst);
	//printf("post set buffer pSrc: %p \n", pSrc);
  	//printf("post set buffer pDst: %p \n", pDst);
  	//printf("position in: %d\n", positionIn);
	//printf("position out: %d\n", positionOut);
	
	int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);

	//for(int i = 0; i < (int) limitOut ; i++) {
		//printf("encoded text: %s \n", (*ppDes));
		//printf("encoded array %s \n", (pByteArray));
		//printf("address of pByteArray %p\n", (pByteArray));
		//printf("address of pDst %p\n", pDst);
	//	(*ppDes)++;
	//}
	//printf("\n");
	
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

	//printf("Decode\n");

    iconv_t content_descriptor = (iconv_t) cd;//((IconvDescriptor*) cd)->pointer;
	
  	//Get sizes pre/post conversion
  	size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jbyte) ;
  	size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jchar); //gahh check this
  	
  	jchar* pCharArray = (*env)->GetCharArrayElements(env, theCharArray, NULL);
  	jbyte* pByteArray = (*env)->GetByteArrayElements(env, theByteArray, NULL);
  	
  	char* pSrc = ((char *) pByteArray);
 	char* pDst = ((char *) pCharArray);
  	
  	//printf("to decode: %s\n", pSrc);

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
  
    //printf("Encode Native\n");
	
	iconv_t content_descriptor = (iconv_t) cd;//((IconvDescriptor*) cd)->pointer;

  	//Get sizes pre/post conversion
  	size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jchar) ;
  	size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jbyte);
  	
  		//fix direct buffer stuff here
	jbyte* char_buf_address = (jbyte*) (*env)->GetDirectBufferAddress(env, charbuffer);
	jbyte* byte_buf_address = (jbyte*) (*env)->GetDirectBufferAddress(env, bytebuffer);
  	
  	char* pSrc = ((char *) char_buf_address);
 	char* pDst = ((char *) byte_buf_address);
  	
  	//printf("pSrc: %s\n", pSrc);
  	//printf("pDst: %s\n", pDst);
  	
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
  	//printf("Decode Native\n");
  	
	iconv_t content_descriptor = (iconv_t) cd;

  	//Get sizes pre/post conversion
  	size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jbyte) ;
  	size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jchar); //gahh check this
  	
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
   	//printf("Encode Hybrid\n");

	iconv_t content_descriptor = (iconv_t) cd;//((IconvDescriptor*) cd)->pointer;

  	//Get sizes pre/post conversion
  	size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jchar) ;
  	size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jbyte);
  	
  	jchar* pCharArray = (*env)->GetCharArrayElements(env, theCharArray, NULL);
  	
  	//fix direct buffer stuff here
	char* byte_buf_address = (char*) (*env)->GetDirectBufferAddress(env, bytebuffer);
  	
  	char* pSrc = (((char *) pCharArray ));// + positionIn*2);
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
   
   	iconv_t content_descriptor = (iconv_t) cd;//((IconvDescriptor*) cd)->pointer;

  	//Get sizes pre/post conversion
  	size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jbyte) ;
  	size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jchar); //gahh check this
  	
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
    //printf("Encode Native charbuffer byte[]\n");
	
	iconv_t content_descriptor = (iconv_t) cd;//((IconvDescriptor*) cd)->pointer;
	
  	//Get sizes pre/post conversion
  	size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jchar) ;
  	size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jbyte);
  	
  	//fix direct buffer stuff here
	jbyte* char_buf_address = (jbyte*) (*env)->GetDirectBufferAddress(env, charbuffer);
	jbyte* pByteArray = (*env)->GetByteArrayElements(env, theByteArray, NULL);
  	
  	char* pSrc = ((char *) char_buf_address);
 	char* pDst = ((char *) pByteArray);
  	
  	//printf("pSrc: %s\n", pSrc);
  	//printf("pDst: %s\n", pDst);
  	
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
    //printf("Decode Native\n");
	
	iconv_t content_descriptor = (iconv_t) cd;//((IconvDescriptor*) cd)->pointer;

  	//Get sizes pre/post conversion
  	size_t in_bytes = (size_t) (limitIn - positionIn) * sizeof(jbyte) ;
  	size_t out_bytes_left = (size_t) (limitOut - positionOut) * sizeof(jchar); //gahh check this
  	
  		//fix direct buffer stuff here
	jbyte* byte_buf_address = (jbyte*) (*env)->GetDirectBufferAddress(env, bytebuffer);
	jchar* pCharArray = (*env)->GetCharArrayElements(env, theCharArray, NULL);
  	
  	char* pSrc = ((char *) byte_buf_address);
 	char* pDst = ((char *) pCharArray);// + positionOut*2);
  	
  	//printf("pSrc: %s\n", pSrc);
  	//printf("pDst: %s\n", pDst);
  	
  	set_buffer_positions(env, positionOut, positionIn, &pDst, &pSrc);
	int error = reposition_and_convert(env, iconvResult, content_descriptor, in_bytes, out_bytes_left, pSrc, pDst);
	(*env)->ReleaseCharArrayElements(env, theCharArray, pCharArray, 0);	
	
  	return error;
}

	


#ifdef __cplusplus
}
#endif
#endif