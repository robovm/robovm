/*
 * Copyright (C) 2007-2008 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Native glue for Java class org.conscrypt.NativeCrypto
 */

#define TO_STRING1(x) #x
#define TO_STRING(x) TO_STRING1(x)
#ifndef JNI_JARJAR_PREFIX
#define CONSCRYPT_UNBUNDLED
#define JNI_JARJAR_PREFIX
#endif

#define LOG_TAG "NativeCrypto"

#include <algorithm>
#include <arpa/inet.h>
#include <fcntl.h>
#include <sys/socket.h>
#include <unistd.h>
#include <vector>

#include <jni.h>

#include <openssl/asn1t.h>
#include <openssl/dsa.h>
#include <openssl/engine.h>
#include <openssl/err.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/rsa.h>
#include <openssl/ssl.h>
#include <openssl/x509v3.h>

#include "AsynchronousSocketCloseMonitor.h"
#include "cutils/log.h"
#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "NetFd.h"
#include "ScopedLocalRef.h"
#include "ScopedPrimitiveArray.h"
#include "ScopedUtfChars.h"
#include "UniquePtr.h"

#undef WITH_JNI_TRACE
#undef WITH_JNI_TRACE_DATA

/*
 * How to use this for debugging with Wireshark:
 *
 * 1. Pull lines from logcat to a file that looks like (without quotes):
 *     "RSA Session-ID:... Master-Key:..." <CR>
 *     "RSA Session-ID:... Master-Key:..." <CR>
 *     <etc>
 * 2. Start Wireshark
 * 3. Go to Edit -> Preferences -> SSL -> (Pre-)Master-Key log and fill in
 *    the file you put the lines in above.
 * 4. Follow the stream that corresponds to the desired "Session-ID" in
 *    the Server Hello.
 */
#undef WITH_JNI_TRACE_KEYS

#ifdef WITH_JNI_TRACE
#define JNI_TRACE(...) \
        ((void)ALOG(LOG_INFO, LOG_TAG "-jni", __VA_ARGS__));     \
/*
        ((void)printf("I/" LOG_TAG "-jni:"));         \
        ((void)printf(__VA_ARGS__));          \
        ((void)printf("\n"))
*/
#else
#define JNI_TRACE(...) ((void)0)
#endif
// don't overwhelm logcat
#define WITH_JNI_TRACE_DATA_CHUNK_SIZE 512

static JavaVM* gJavaVM;
static jclass openSslOutputStreamClass;

static jclass byteArrayClass;
static jclass calendarClass;
static jclass objectClass;
static jclass objectArrayClass;
static jclass integerClass;
static jclass inputStreamClass;
static jclass outputStreamClass;
static jclass stringClass;

static jmethodID calendar_setMethod;
static jmethodID inputStream_readMethod;
static jmethodID integer_valueOfMethod;
static jmethodID openSslInputStream_readLineMethod;
static jmethodID outputStream_writeMethod;
static jmethodID outputStream_flushMethod;

struct OPENSSL_Delete {
    void operator()(void* p) const {
        OPENSSL_free(p);
    }
};
typedef UniquePtr<unsigned char, OPENSSL_Delete> Unique_OPENSSL_str;

struct BIO_Delete {
    void operator()(BIO* p) const {
        BIO_free(p);
    }
};
typedef UniquePtr<BIO, BIO_Delete> Unique_BIO;

struct BIGNUM_Delete {
    void operator()(BIGNUM* p) const {
        BN_free(p);
    }
};
typedef UniquePtr<BIGNUM, BIGNUM_Delete> Unique_BIGNUM;

struct ASN1_INTEGER_Delete {
    void operator()(ASN1_INTEGER* p) const {
        ASN1_INTEGER_free(p);
    }
};
typedef UniquePtr<ASN1_INTEGER, ASN1_INTEGER_Delete> Unique_ASN1_INTEGER;

struct DH_Delete {
    void operator()(DH* p) const {
        DH_free(p);
    }
};
typedef UniquePtr<DH, DH_Delete> Unique_DH;

struct DSA_Delete {
    void operator()(DSA* p) const {
        DSA_free(p);
    }
};
typedef UniquePtr<DSA, DSA_Delete> Unique_DSA;

struct EC_GROUP_Delete {
    void operator()(EC_GROUP* p) const {
        EC_GROUP_clear_free(p);
    }
};
typedef UniquePtr<EC_GROUP, EC_GROUP_Delete> Unique_EC_GROUP;

struct EC_POINT_Delete {
    void operator()(EC_POINT* p) const {
        EC_POINT_clear_free(p);
    }
};
typedef UniquePtr<EC_POINT, EC_POINT_Delete> Unique_EC_POINT;

struct EC_KEY_Delete {
    void operator()(EC_KEY* p) const {
        EC_KEY_free(p);
    }
};
typedef UniquePtr<EC_KEY, EC_KEY_Delete> Unique_EC_KEY;

struct EVP_MD_CTX_Delete {
    void operator()(EVP_MD_CTX* p) const {
        EVP_MD_CTX_destroy(p);
    }
};
typedef UniquePtr<EVP_MD_CTX, EVP_MD_CTX_Delete> Unique_EVP_MD_CTX;

struct EVP_CIPHER_CTX_Delete {
    void operator()(EVP_CIPHER_CTX* p) const {
        EVP_CIPHER_CTX_cleanup(p);
    }
};
typedef UniquePtr<EVP_CIPHER_CTX, EVP_CIPHER_CTX_Delete> Unique_EVP_CIPHER_CTX;

struct EVP_PKEY_Delete {
    void operator()(EVP_PKEY* p) const {
        EVP_PKEY_free(p);
    }
};
typedef UniquePtr<EVP_PKEY, EVP_PKEY_Delete> Unique_EVP_PKEY;

struct PKCS8_PRIV_KEY_INFO_Delete {
    void operator()(PKCS8_PRIV_KEY_INFO* p) const {
        PKCS8_PRIV_KEY_INFO_free(p);
    }
};
typedef UniquePtr<PKCS8_PRIV_KEY_INFO, PKCS8_PRIV_KEY_INFO_Delete> Unique_PKCS8_PRIV_KEY_INFO;

struct RSA_Delete {
    void operator()(RSA* p) const {
        RSA_free(p);
    }
};
typedef UniquePtr<RSA, RSA_Delete> Unique_RSA;

struct ASN1_BIT_STRING_Delete {
    void operator()(ASN1_BIT_STRING* p) const {
        ASN1_BIT_STRING_free(p);
    }
};
typedef UniquePtr<ASN1_BIT_STRING, ASN1_BIT_STRING_Delete> Unique_ASN1_BIT_STRING;

struct ASN1_OBJECT_Delete {
    void operator()(ASN1_OBJECT* p) const {
        ASN1_OBJECT_free(p);
    }
};
typedef UniquePtr<ASN1_OBJECT, ASN1_OBJECT_Delete> Unique_ASN1_OBJECT;

struct ASN1_GENERALIZEDTIME_Delete {
    void operator()(ASN1_GENERALIZEDTIME* p) const {
        ASN1_GENERALIZEDTIME_free(p);
    }
};
typedef UniquePtr<ASN1_GENERALIZEDTIME, ASN1_GENERALIZEDTIME_Delete> Unique_ASN1_GENERALIZEDTIME;

struct SSL_Delete {
    void operator()(SSL* p) const {
        SSL_free(p);
    }
};
typedef UniquePtr<SSL, SSL_Delete> Unique_SSL;

struct SSL_CTX_Delete {
    void operator()(SSL_CTX* p) const {
        SSL_CTX_free(p);
    }
};
typedef UniquePtr<SSL_CTX, SSL_CTX_Delete> Unique_SSL_CTX;

struct X509_Delete {
    void operator()(X509* p) const {
        X509_free(p);
    }
};
typedef UniquePtr<X509, X509_Delete> Unique_X509;

class X509Chain {
  public:
    X509Chain(size_t n) : x509s_(n) {}

    ~X509Chain() {
        for (const auto& x509 : x509s_) {
            X509_free(x509);
        }
    }

    X509*& operator[](size_t n) {
        return x509s_[n];
    }

    X509* operator[](size_t n) const {
        return x509s_[n];
    }

    X509* release(size_t i) {
        X509* x = x509s_[i];
        x509s_[i] = NULL;
        return x;
    }

  private:
    std::vector<X509*> x509s_;
};

struct X509_NAME_Delete {
    void operator()(X509_NAME* p) const {
        X509_NAME_free(p);
    }
};
typedef UniquePtr<X509_NAME, X509_NAME_Delete> Unique_X509_NAME;

struct PKCS7_Delete {
    void operator()(PKCS7* p) const {
        PKCS7_free(p);
    }
};
typedef UniquePtr<PKCS7, PKCS7_Delete> Unique_PKCS7;

struct sk_SSL_CIPHER_Delete {
    void operator()(STACK_OF(SSL_CIPHER)* p) const {
        // We don't own SSL_CIPHER references, so no need for pop_free
        sk_SSL_CIPHER_free(p);
    }
};
typedef UniquePtr<STACK_OF(SSL_CIPHER), sk_SSL_CIPHER_Delete> Unique_sk_SSL_CIPHER;

struct sk_X509_Delete {
    void operator()(STACK_OF(X509)* p) const {
        sk_X509_pop_free(p, X509_free);
    }
};
typedef UniquePtr<STACK_OF(X509), sk_X509_Delete> Unique_sk_X509;

struct sk_X509_NAME_Delete {
    void operator()(STACK_OF(X509_NAME)* p) const {
        sk_X509_NAME_pop_free(p, X509_NAME_free);
    }
};
typedef UniquePtr<STACK_OF(X509_NAME), sk_X509_NAME_Delete> Unique_sk_X509_NAME;

struct sk_ASN1_OBJECT_Delete {
    void operator()(STACK_OF(ASN1_OBJECT)* p) const {
        sk_ASN1_OBJECT_pop_free(p, ASN1_OBJECT_free);
    }
};
typedef UniquePtr<STACK_OF(ASN1_OBJECT), sk_ASN1_OBJECT_Delete> Unique_sk_ASN1_OBJECT;

struct sk_GENERAL_NAME_Delete {
    void operator()(STACK_OF(GENERAL_NAME)* p) const {
        sk_GENERAL_NAME_pop_free(p, GENERAL_NAME_free);
    }
};
typedef UniquePtr<STACK_OF(GENERAL_NAME), sk_GENERAL_NAME_Delete> Unique_sk_GENERAL_NAME;

/**
 * Many OpenSSL APIs take ownership of an argument on success but don't free the argument
 * on failure. This means we need to tell our scoped pointers when we've transferred ownership,
 * without triggering a warning by not using the result of release().
 */
#define OWNERSHIP_TRANSFERRED(obj) \
    typeof (obj.release()) _dummy __attribute__((unused)) = obj.release()

/**
 * Frees the SSL error state.
 *
 * OpenSSL keeps an "error stack" per thread, and given that this code
 * can be called from arbitrary threads that we don't keep track of,
 * we err on the side of freeing the error state promptly (instead of,
 * say, at thread death).
 */
static void freeOpenSslErrorState(void) {
    ERR_clear_error();
    ERR_remove_state(0);
}

/**
 * Throws a OutOfMemoryError with the given string as a message.
 */
static void jniThrowOutOfMemory(JNIEnv* env, const char* message) {
    jniThrowException(env, "java/lang/OutOfMemoryError", message);
}

/**
 * Throws a BadPaddingException with the given string as a message.
 */
static void throwBadPaddingException(JNIEnv* env, const char* message) {
    JNI_TRACE("throwBadPaddingException %s", message);
    jniThrowException(env, "javax/crypto/BadPaddingException", message);
}

/**
 * Throws a SignatureException with the given string as a message.
 */
static void throwSignatureException(JNIEnv* env, const char* message) {
    JNI_TRACE("throwSignatureException %s", message);
    jniThrowException(env, "java/security/SignatureException", message);
}

/**
 * Throws a InvalidKeyException with the given string as a message.
 */
static void throwInvalidKeyException(JNIEnv* env, const char* message) {
    JNI_TRACE("throwInvalidKeyException %s", message);
    jniThrowException(env, "java/security/InvalidKeyException", message);
}

/**
 * Throws a SignatureException with the given string as a message.
 */
static void throwIllegalBlockSizeException(JNIEnv* env, const char* message) {
    JNI_TRACE("throwIllegalBlockSizeException %s", message);
    jniThrowException(env, "javax/crypto/IllegalBlockSizeException", message);
}

/**
 * Throws a NoSuchAlgorithmException with the given string as a message.
 */
static void throwNoSuchAlgorithmException(JNIEnv* env, const char* message) {
    JNI_TRACE("throwUnknownAlgorithmException %s", message);
    jniThrowException(env, "java/security/NoSuchAlgorithmException", message);
}

static void throwForAsn1Error(JNIEnv* env, int reason, const char *message) {
    switch (reason) {
    case ASN1_R_UNABLE_TO_DECODE_RSA_KEY:
    case ASN1_R_UNABLE_TO_DECODE_RSA_PRIVATE_KEY:
    case ASN1_R_UNKNOWN_PUBLIC_KEY_TYPE:
    case ASN1_R_UNSUPPORTED_PUBLIC_KEY_TYPE:
    case ASN1_R_WRONG_PUBLIC_KEY_TYPE:
        throwInvalidKeyException(env, message);
        break;
    case ASN1_R_UNKNOWN_MESSAGE_DIGEST_ALGORITHM:
        throwNoSuchAlgorithmException(env, message);
        break;
    default:
        jniThrowRuntimeException(env, message);
        break;
    }
}

static void throwForEvpError(JNIEnv* env, int reason, const char *message) {
    switch (reason) {
    case EVP_R_BAD_DECRYPT:
        throwBadPaddingException(env, message);
        break;
    case EVP_R_DATA_NOT_MULTIPLE_OF_BLOCK_LENGTH:
    case EVP_R_WRONG_FINAL_BLOCK_LENGTH:
        throwIllegalBlockSizeException(env, message);
        break;
    case EVP_R_BAD_KEY_LENGTH:
    case EVP_R_BN_DECODE_ERROR:
    case EVP_R_BN_PUBKEY_ERROR:
    case EVP_R_INVALID_KEY_LENGTH:
    case EVP_R_MISSING_PARAMETERS:
    case EVP_R_UNSUPPORTED_KEY_SIZE:
    case EVP_R_UNSUPPORTED_KEYLENGTH:
        throwInvalidKeyException(env, message);
        break;
    case EVP_R_WRONG_PUBLIC_KEY_TYPE:
        throwSignatureException(env, message);
        break;
    case EVP_R_UNSUPPORTED_ALGORITHM:
        throwNoSuchAlgorithmException(env, message);
        break;
    default:
        jniThrowRuntimeException(env, message);
        break;
    }
}

static void throwForRsaError(JNIEnv* env, int reason, const char *message) {
    switch (reason) {
    case RSA_R_BLOCK_TYPE_IS_NOT_01:
    case RSA_R_BLOCK_TYPE_IS_NOT_02:
        throwBadPaddingException(env, message);
        break;
    case RSA_R_ALGORITHM_MISMATCH:
    case RSA_R_BAD_SIGNATURE:
    case RSA_R_DATA_GREATER_THAN_MOD_LEN:
    case RSA_R_DATA_TOO_LARGE_FOR_MODULUS:
    case RSA_R_INVALID_MESSAGE_LENGTH:
    case RSA_R_WRONG_SIGNATURE_LENGTH:
        throwSignatureException(env, message);
        break;
    case RSA_R_UNKNOWN_ALGORITHM_TYPE:
        throwNoSuchAlgorithmException(env, message);
        break;
    case RSA_R_MODULUS_TOO_LARGE:
    case RSA_R_NO_PUBLIC_EXPONENT:
        throwInvalidKeyException(env, message);
        break;
    default:
        jniThrowRuntimeException(env, message);
        break;
    }
}

static void throwForX509Error(JNIEnv* env, int reason, const char *message) {
    switch (reason) {
    case X509_R_UNSUPPORTED_ALGORITHM:
        throwNoSuchAlgorithmException(env, message);
        break;
    default:
        jniThrowRuntimeException(env, message);
        break;
    }
}

/*
 * Checks this thread's OpenSSL error queue and throws a RuntimeException if
 * necessary.
 *
 * @return true if an exception was thrown, false if not.
 */
static bool throwExceptionIfNecessary(JNIEnv* env, const char* location  __attribute__ ((unused))) {
    const char* file;
    int line;
    const char* data;
    int flags;
    unsigned long error = ERR_get_error_line_data(&file, &line, &data, &flags);
    int result = false;

    if (error != 0) {
        char message[256];
        ERR_error_string_n(error, message, sizeof(message));
        int library = ERR_GET_LIB(error);
        int reason = ERR_GET_REASON(error);
        JNI_TRACE("OpenSSL error in %s error=%lx library=%x reason=%x (%s:%d): %s %s",
                  location, error, library, reason, file, line, message,
                  (flags & ERR_TXT_STRING) ? data : "(no data)");
        switch (library) {
        case ERR_LIB_RSA:
            throwForRsaError(env, reason, message);
            break;
        case ERR_LIB_ASN1:
            throwForAsn1Error(env, reason, message);
            break;
        case ERR_LIB_EVP:
            throwForEvpError(env, reason, message);
            break;
        case ERR_LIB_X509:
            throwForX509Error(env, reason, message);
            break;
        case ERR_LIB_DSA:
            throwInvalidKeyException(env, message);
            break;
        default:
            jniThrowRuntimeException(env, message);
            break;
        }
        result = true;
    }

    freeOpenSslErrorState();
    return result;
}

/**
 * Throws an SocketTimeoutException with the given string as a message.
 */
static void throwSocketTimeoutException(JNIEnv* env, const char* message) {
    JNI_TRACE("throwSocketTimeoutException %s", message);
    jniThrowException(env, "java/net/SocketTimeoutException", message);
}

/**
 * Throws a javax.net.ssl.SSLException with the given string as a message.
 */
static void throwSSLExceptionStr(JNIEnv* env, const char* message) {
    JNI_TRACE("throwSSLExceptionStr %s", message);
    jniThrowException(env, "javax/net/ssl/SSLException", message);
}

/**
 * Throws a javax.net.ssl.SSLProcotolException with the given string as a message.
 */
static void throwSSLProtocolExceptionStr(JNIEnv* env, const char* message) {
    JNI_TRACE("throwSSLProtocolExceptionStr %s", message);
    jniThrowException(env, "javax/net/ssl/SSLProtocolException", message);
}

/**
 * Throws an SSLException with a message constructed from the current
 * SSL errors. This will also log the errors.
 *
 * @param env the JNI environment
 * @param ssl the possibly NULL SSL
 * @param sslErrorCode error code returned from SSL_get_error() or
 * SSL_ERROR_NONE to probe with ERR_get_error
 * @param message null-ok; general error message
 */
static void throwSSLExceptionWithSslErrors(
        JNIEnv* env, SSL* ssl, int sslErrorCode, const char* message) {

    if (message == NULL) {
        message = "SSL error";
    }

    // First consult the SSL error code for the general message.
    const char* sslErrorStr = NULL;
    switch (sslErrorCode) {
        case SSL_ERROR_NONE:
            if (ERR_peek_error() == 0) {
                sslErrorStr = "OK";
            } else {
                sslErrorStr = "";
            }
            break;
        case SSL_ERROR_SSL:
            sslErrorStr = "Failure in SSL library, usually a protocol error";
            break;
        case SSL_ERROR_WANT_READ:
            sslErrorStr = "SSL_ERROR_WANT_READ occurred. You should never see this.";
            break;
        case SSL_ERROR_WANT_WRITE:
            sslErrorStr = "SSL_ERROR_WANT_WRITE occurred. You should never see this.";
            break;
        case SSL_ERROR_WANT_X509_LOOKUP:
            sslErrorStr = "SSL_ERROR_WANT_X509_LOOKUP occurred. You should never see this.";
            break;
        case SSL_ERROR_SYSCALL:
            sslErrorStr = "I/O error during system call";
            break;
        case SSL_ERROR_ZERO_RETURN:
            sslErrorStr = "SSL_ERROR_ZERO_RETURN occurred. You should never see this.";
            break;
        case SSL_ERROR_WANT_CONNECT:
            sslErrorStr = "SSL_ERROR_WANT_CONNECT occurred. You should never see this.";
            break;
        case SSL_ERROR_WANT_ACCEPT:
            sslErrorStr = "SSL_ERROR_WANT_ACCEPT occurred. You should never see this.";
            break;
        default:
            sslErrorStr = "Unknown SSL error";
    }

    // Prepend either our explicit message or a default one.
    char* str;
    if (asprintf(&str, "%s: ssl=%p: %s", message, ssl, sslErrorStr) <= 0) {
        // problem with asprintf, just throw argument message, log everything
        throwSSLExceptionStr(env, message);
        ALOGV("%s: ssl=%p: %s", message, ssl, sslErrorStr);
        freeOpenSslErrorState();
        return;
    }

    char* allocStr = str;

    // For protocol errors, SSL might have more information.
    if (sslErrorCode == SSL_ERROR_NONE || sslErrorCode == SSL_ERROR_SSL) {
        // Append each error as an additional line to the message.
        for (;;) {
            char errStr[256];
            const char* file;
            int line;
            const char* data;
            int flags;
            unsigned long err = ERR_get_error_line_data(&file, &line, &data, &flags);
            if (err == 0) {
                break;
            }

            ERR_error_string_n(err, errStr, sizeof(errStr));

            int ret = asprintf(&str, "%s\n%s (%s:%d %p:0x%08x)",
                               (allocStr == NULL) ? "" : allocStr,
                               errStr,
                               file,
                               line,
                               (flags & ERR_TXT_STRING) ? data : "(no data)",
                               flags);

            if (ret < 0) {
                break;
            }

            free(allocStr);
            allocStr = str;
        }
    // For errors during system calls, errno might be our friend.
    } else if (sslErrorCode == SSL_ERROR_SYSCALL) {
        if (asprintf(&str, "%s, %s", allocStr, strerror(errno)) >= 0) {
            free(allocStr);
            allocStr = str;
        }
    // If the error code is invalid, print it.
    } else if (sslErrorCode > SSL_ERROR_WANT_ACCEPT) {
        if (asprintf(&str, ", error code is %d", sslErrorCode) >= 0) {
            free(allocStr);
            allocStr = str;
        }
    }

    if (sslErrorCode == SSL_ERROR_SSL) {
        throwSSLProtocolExceptionStr(env, allocStr);
    } else {
        throwSSLExceptionStr(env, allocStr);
    }

    ALOGV("%s", allocStr);
    free(allocStr);
    freeOpenSslErrorState();
}

/**
 * Helper function that grabs the casts an ssl pointer and then checks for nullness.
 * If this function returns NULL and <code>throwIfNull</code> is
 * passed as <code>true</code>, then this function will call
 * <code>throwSSLExceptionStr</code> before returning, so in this case of
 * NULL, a caller of this function should simply return and allow JNI
 * to do its thing.
 *
 * @param env the JNI environment
 * @param ssl_address; the ssl_address pointer as an integer
 * @param throwIfNull whether to throw if the SSL pointer is NULL
 * @returns the pointer, which may be NULL
 */
static SSL_CTX* to_SSL_CTX(JNIEnv* env, jlong ssl_ctx_address, bool throwIfNull) {
    SSL_CTX* ssl_ctx = reinterpret_cast<SSL_CTX*>(static_cast<uintptr_t>(ssl_ctx_address));
    if ((ssl_ctx == NULL) && throwIfNull) {
        JNI_TRACE("ssl_ctx == null");
        jniThrowNullPointerException(env, "ssl_ctx == null");
    }
    return ssl_ctx;
}

static SSL* to_SSL(JNIEnv* env, jlong ssl_address, bool throwIfNull) {
    SSL* ssl = reinterpret_cast<SSL*>(static_cast<uintptr_t>(ssl_address));
    if ((ssl == NULL) && throwIfNull) {
        JNI_TRACE("ssl == null");
        jniThrowNullPointerException(env, "ssl == null");
    }
    return ssl;
}

static SSL_SESSION* to_SSL_SESSION(JNIEnv* env, jlong ssl_session_address, bool throwIfNull) {
    SSL_SESSION* ssl_session
        = reinterpret_cast<SSL_SESSION*>(static_cast<uintptr_t>(ssl_session_address));
    if ((ssl_session == NULL) && throwIfNull) {
        JNI_TRACE("ssl_session == null");
        jniThrowNullPointerException(env, "ssl_session == null");
    }
    return ssl_session;
}

/**
 * Converts a Java byte[] to an OpenSSL BIGNUM, allocating the BIGNUM on the
 * fly. Returns true on success. If the return value is false, there is a
 * pending exception.
 */
static bool arrayToBignum(JNIEnv* env, jbyteArray source, BIGNUM** dest) {
    JNI_TRACE("arrayToBignum(%p, %p)", source, *dest);

    ScopedByteArrayRO sourceBytes(env, source);
    if (sourceBytes.get() == NULL) {
        JNI_TRACE("arrayToBignum(%p) => NULL", source);
        return false;
    }
    *dest = BN_bin2bn(reinterpret_cast<const unsigned char*>(sourceBytes.get()),
                           sourceBytes.size(),
                           NULL);
    if (*dest == NULL) {
        jniThrowRuntimeException(env, "Conversion to BIGNUM failed");
        JNI_TRACE("arrayToBignum(%p) => threw exception", source);
        return false;
    }

    JNI_TRACE("arrayToBignum(%p) => %p", source, *dest);
    return true;
}

/**
 * Converts an OpenSSL BIGNUM to a Java byte[] array.
 */
static jbyteArray bignumToArray(JNIEnv* env, const BIGNUM* source, const char* sourceName) {
    JNI_TRACE("bignumToArray(%p, %s)", source, sourceName);

    if (source == NULL) {
        jniThrowNullPointerException(env, sourceName);
        return NULL;
    }

    jbyteArray javaBytes = env->NewByteArray(BN_num_bytes(source) + 1);
    ScopedByteArrayRW bytes(env, javaBytes);
    if (bytes.get() == NULL) {
        JNI_TRACE("bignumToArray(%p, %s) => NULL", source, sourceName);
        return NULL;
    }

    unsigned char* tmp = reinterpret_cast<unsigned char*>(bytes.get());

    // Set the sign for the Java code.
    if (BN_is_negative(source)) {
        *tmp = 0xFF;
    } else {
        *tmp = 0x00;
    }

    if (BN_num_bytes(source) > 0 && BN_bn2bin(source, tmp + 1) <= 0) {
        throwExceptionIfNecessary(env, "bignumToArray");
        return NULL;
    }

    JNI_TRACE("bignumToArray(%p, %s) => %p", source, sourceName, javaBytes);
    return javaBytes;
}

/**
 * Converts various OpenSSL ASN.1 types to a jbyteArray with DER-encoded data
 * inside. The "i2d_func" function pointer is a function of the "i2d_<TYPE>"
 * from the OpenSSL ASN.1 API.
 */
template<typename T, int (*i2d_func)(T*, unsigned char**)>
jbyteArray ASN1ToByteArray(JNIEnv* env, T* obj) {
    if (obj == NULL) {
        jniThrowNullPointerException(env, "ASN1 input == null");
        JNI_TRACE("ASN1ToByteArray(%p) => null input", obj);
        return NULL;
    }

    int derLen = i2d_func(obj, NULL);
    if (derLen < 0) {
        throwExceptionIfNecessary(env, "ASN1ToByteArray");
        JNI_TRACE("ASN1ToByteArray(%p) => measurement failed", obj);
        return NULL;
    }

    ScopedLocalRef<jbyteArray> byteArray(env, env->NewByteArray(derLen));
    if (byteArray.get() == NULL) {
        JNI_TRACE("ASN1ToByteArray(%p) => creating byte array failed", obj);
        return NULL;
    }

    ScopedByteArrayRW bytes(env, byteArray.get());
    if (bytes.get() == NULL) {
        JNI_TRACE("ASN1ToByteArray(%p) => using byte array failed", obj);
        return NULL;
    }

    unsigned char* p = reinterpret_cast<unsigned char*>(bytes.get());
    int ret = i2d_func(obj, &p);
    if (ret < 0) {
        throwExceptionIfNecessary(env, "ASN1ToByteArray");
        JNI_TRACE("ASN1ToByteArray(%p) => final conversion failed", obj);
        return NULL;
    }

    JNI_TRACE("ASN1ToByteArray(%p) => success (%d bytes written)", obj, ret);
    return byteArray.release();
}

template<typename T, T* (*d2i_func)(T**, const unsigned char**, long)>
T* ByteArrayToASN1(JNIEnv* env, jbyteArray byteArray) {
    ScopedByteArrayRO bytes(env, byteArray);
    if (bytes.get() == NULL) {
        JNI_TRACE("ByteArrayToASN1(%p) => using byte array failed", byteArray);
        return 0;
    }

    const unsigned char* tmp = reinterpret_cast<const unsigned char*>(bytes.get());
    return d2i_func(NULL, &tmp, bytes.size());
}

/**
 * Converts ASN.1 BIT STRING to a jbooleanArray.
 */
jbooleanArray ASN1BitStringToBooleanArray(JNIEnv* env, ASN1_BIT_STRING* bitStr) {
    int size = bitStr->length * 8;
    if (bitStr->flags & ASN1_STRING_FLAG_BITS_LEFT) {
        size -= bitStr->flags & 0x07;
    }

    ScopedLocalRef<jbooleanArray> bitsRef(env, env->NewBooleanArray(size));
    if (bitsRef.get() == NULL) {
        return NULL;
    }

    ScopedBooleanArrayRW bitsArray(env, bitsRef.get());
    for (int i = 0; i < static_cast<int>(bitsArray.size()); i++) {
        bitsArray[i] = ASN1_BIT_STRING_get_bit(bitStr, i);
    }

    return bitsRef.release();
}

/**
 * To avoid the round-trip to ASN.1 and back in X509_dup, we just up the reference count.
 */
static X509* X509_dup_nocopy(X509* x509) {
    CRYPTO_add(&x509->references, 1, CRYPTO_LOCK_X509);
    return x509;
}

/**
 * BIO for InputStream
 */
class BIO_Stream {
public:
    BIO_Stream(jobject stream) :
            mEof(false) {
        JNIEnv* env = getEnv();
        mStream = env->NewGlobalRef(stream);
    }

    ~BIO_Stream() {
        JNIEnv* env = getEnv();

        env->DeleteGlobalRef(mStream);
    }

    bool isEof() const {
        JNI_TRACE("isEof? %s", mEof ? "yes" : "no");
        return mEof;
    }

    int flush() {
        JNIEnv* env = getEnv();
        if (env == NULL) {
            return -1;
        }

        env->CallVoidMethod(mStream, outputStream_flushMethod);
        if (env->ExceptionCheck()) {
            return -1;
        }

        return 1;
    }

protected:
    jobject getStream() {
        return mStream;
    }

    void setEof(bool eof) {
        mEof = eof;
    }

    JNIEnv* getEnv() {
        JNIEnv* env;

        if (gJavaVM->AttachCurrentThread(&env, NULL) < 0) {
            return NULL;
        }

        return env;
    }

private:
    jobject mStream;
    bool mEof;
};

class BIO_InputStream : public BIO_Stream {
public:
    BIO_InputStream(jobject stream) :
            BIO_Stream(stream) {
    }

    int read(char *buf, int len) {
        return read_internal(buf, len, inputStream_readMethod);
    }

    int gets(char *buf, int len) {
        if (len > PEM_LINE_LENGTH) {
            len = PEM_LINE_LENGTH;
        }

        int read = read_internal(buf, len - 1, openSslInputStream_readLineMethod);
        buf[read] = '\0';
        JNI_TRACE("BIO::gets \"%s\"", buf);
        return read;
    }

private:
    int read_internal(char *buf, int len, jmethodID method) {
        JNIEnv* env = getEnv();
        if (env == NULL) {
            JNI_TRACE("BIO_InputStream::read could not get JNIEnv");
            return -1;
        }

        ScopedLocalRef<jbyteArray> javaBytes(env, env->NewByteArray(len));
        if (javaBytes.get() == NULL) {
            JNI_TRACE("BIO_InputStream::read failed call to NewByteArray");
            return -1;
        }

        jint read = env->CallIntMethod(getStream(), method, javaBytes.get());
        if (env->ExceptionCheck()) {
            JNI_TRACE("BIO_InputStream::read failed call to InputStream#read");
            return -1;
        }

        /* Java uses -1 to indicate EOF condition. */
        if (read == -1) {
            setEof(true);
            read = 0;
        } else if (read > 0) {
            env->GetByteArrayRegion(javaBytes.get(), 0, read, reinterpret_cast<jbyte*>(buf));
        }

        return read;
    }

public:
    /** Length of PEM-encoded line (64) plus CR plus NULL */
    static const int PEM_LINE_LENGTH = 66;
};

class BIO_OutputStream : public BIO_Stream {
public:
    BIO_OutputStream(jobject stream) :
            BIO_Stream(stream) {
    }

    int write(const char *buf, int len) {
        JNIEnv* env = getEnv();
        if (env == NULL) {
            JNI_TRACE("BIO_OutputStream::write => could not get JNIEnv");
            return -1;
        }

        ScopedLocalRef<jbyteArray> javaBytes(env, env->NewByteArray(len));
        if (javaBytes.get() == NULL) {
            JNI_TRACE("BIO_OutputStream::write => failed call to NewByteArray");
            return -1;
        }

        env->SetByteArrayRegion(javaBytes.get(), 0, len, reinterpret_cast<const jbyte*>(buf));

        env->CallVoidMethod(getStream(), outputStream_writeMethod, javaBytes.get());
        if (env->ExceptionCheck()) {
            JNI_TRACE("BIO_OutputStream::write => failed call to OutputStream#write");
            return -1;
        }

        return len;
    }
};

static int bio_stream_create(BIO *b) {
    b->init = 1;
    b->num = 0;
    b->ptr = NULL;
    b->flags = 0;
    return 1;
}

static int bio_stream_destroy(BIO *b) {
    if (b == NULL) {
        return 0;
    }

    if (b->ptr != NULL) {
        delete static_cast<BIO_Stream*>(b->ptr);
        b->ptr = NULL;
    }

    b->init = 0;
    b->flags = 0;
    return 1;
}

static int bio_stream_read(BIO *b, char *buf, int len) {
    BIO_InputStream* stream = static_cast<BIO_InputStream*>(b->ptr);
    return stream->read(buf, len);
}

static int bio_stream_write(BIO *b, const char *buf, int len) {
    BIO_OutputStream* stream = static_cast<BIO_OutputStream*>(b->ptr);
    return stream->write(buf, len);
}

static int bio_stream_puts(BIO *b, const char *buf) {
    BIO_OutputStream* stream = static_cast<BIO_OutputStream*>(b->ptr);
    return stream->write(buf, strlen(buf));
}

static int bio_stream_gets(BIO *b, char *buf, int len) {
    BIO_InputStream* stream = static_cast<BIO_InputStream*>(b->ptr);
    return stream->gets(buf, len);
}

static void bio_stream_assign(BIO *b, BIO_Stream* stream) {
    b->ptr = static_cast<void*>(stream);
}

static long bio_stream_ctrl(BIO *b, int cmd, long, void *) {
    BIO_Stream* stream = static_cast<BIO_Stream*>(b->ptr);

    switch (cmd) {
    case BIO_CTRL_EOF:
        return stream->isEof() ? 1 : 0;
    case BIO_CTRL_FLUSH:
        return stream->flush();
    default:
        return 0;
    }
}

static BIO_METHOD stream_bio_method = {
        ( 100 | 0x0400 ), /* source/sink BIO */
        "InputStream/OutputStream BIO",
        bio_stream_write, /* bio_write */
        bio_stream_read, /* bio_read */
        bio_stream_puts, /* bio_puts */
        bio_stream_gets, /* bio_gets */
        bio_stream_ctrl, /* bio_ctrl */
        bio_stream_create, /* bio_create */
        bio_stream_destroy, /* bio_free */
        NULL, /* no bio_callback_ctrl */
};

/**
 * Copied from libnativehelper NetworkUtilites.cpp
 */
static bool setBlocking(int fd, bool blocking) {
    int flags = fcntl(fd, F_GETFL);
    if (flags == -1) {
        return false;
    }

    if (!blocking) {
        flags |= O_NONBLOCK;
    } else {
        flags &= ~O_NONBLOCK;
    }

    int rc = fcntl(fd, F_SETFL, flags);
    return (rc != -1);
}

/**
 * OpenSSL locking support. Taken from the O'Reilly book by Viega et al., but I
 * suppose there are not many other ways to do this on a Linux system (modulo
 * isomorphism).
 */
#define MUTEX_TYPE pthread_mutex_t
#define MUTEX_SETUP(x) pthread_mutex_init(&(x), NULL)
#define MUTEX_CLEANUP(x) pthread_mutex_destroy(&(x))
#define MUTEX_LOCK(x) pthread_mutex_lock(&(x))
#define MUTEX_UNLOCK(x) pthread_mutex_unlock(&(x))
#define THREAD_ID pthread_self()
#define THROW_SSLEXCEPTION (-2)
#define THROW_SOCKETTIMEOUTEXCEPTION (-3)
#define THROWN_EXCEPTION (-4)

static MUTEX_TYPE* mutex_buf = NULL;

static void locking_function(int mode, int n, const char*, int) {
    if (mode & CRYPTO_LOCK) {
        MUTEX_LOCK(mutex_buf[n]);
    } else {
        MUTEX_UNLOCK(mutex_buf[n]);
    }
}

static unsigned long id_function(void) {
    return ((unsigned long)THREAD_ID);
}

int THREAD_setup(void) {
    mutex_buf = new MUTEX_TYPE[CRYPTO_num_locks()];
    if (!mutex_buf) {
        return 0;
    }

    for (int i = 0; i < CRYPTO_num_locks(); ++i) {
        MUTEX_SETUP(mutex_buf[i]);
    }

    CRYPTO_set_id_callback(id_function);
    CRYPTO_set_locking_callback(locking_function);

    return 1;
}

int THREAD_cleanup(void) {
    if (!mutex_buf) {
        return 0;
    }

    CRYPTO_set_id_callback(NULL);
    CRYPTO_set_locking_callback(NULL);

    for (int i = 0; i < CRYPTO_num_locks( ); i++) {
        MUTEX_CLEANUP(mutex_buf[i]);
    }

    free(mutex_buf);
    mutex_buf = NULL;

    return 1;
}

/**
 * Initialization phase for every OpenSSL job: Loads the Error strings, the
 * crypto algorithms and reset the OpenSSL library
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_clinit(JNIEnv*, jclass)
{
    SSL_load_error_strings();
    ERR_load_crypto_strings();
    SSL_library_init();
    OpenSSL_add_all_algorithms();
    THREAD_setup();
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_ENGINE_1load_1dynamic(JNIEnv*, jclass) {
    JNI_TRACE("ENGINE_load_dynamic()");

    ENGINE_load_dynamic();
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_ENGINE_1by_1id(JNIEnv* env, jclass, jstring idJava) {
    JNI_TRACE("ENGINE_by_id(%p)", idJava);

    ScopedUtfChars id(env, idJava);
    if (id.c_str() == NULL) {
        JNI_TRACE("ENGINE_by_id(%p) => id == null", idJava);
        return 0;
    }
    JNI_TRACE("ENGINE_by_id(\"%s\")", id.c_str());

    ENGINE* e = ENGINE_by_id(id.c_str());
    if (e == NULL) {
        freeOpenSslErrorState();
    }

    JNI_TRACE("ENGINE_by_id(\"%s\") => %p", id.c_str(), e);
    return reinterpret_cast<uintptr_t>(e);
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_ENGINE_1add(JNIEnv* env, jclass, jlong engineRef) {
    ENGINE* e = reinterpret_cast<ENGINE*>(static_cast<uintptr_t>(engineRef));
    JNI_TRACE("ENGINE_add(%p)", e);

    if (e == NULL) {
        jniThrowException(env, "java/lang/IllegalArgumentException", "engineRef == 0");
        return 0;
    }

    int ret = ENGINE_add(e);

    /*
     * We tolerate errors, because the most likely error is that
     * the ENGINE is already in the list.
     */
    freeOpenSslErrorState();

    JNI_TRACE("ENGINE_add(%p) => %d", e, ret);
    return ret;
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_ENGINE_1init(JNIEnv* env, jclass, jlong engineRef) {
    ENGINE* e = reinterpret_cast<ENGINE*>(static_cast<uintptr_t>(engineRef));
    JNI_TRACE("ENGINE_init(%p)", e);

    if (e == NULL) {
        jniThrowException(env, "java/lang/IllegalArgumentException", "engineRef == 0");
        return 0;
    }

    int ret = ENGINE_init(e);
    JNI_TRACE("ENGINE_init(%p) => %d", e, ret);
    return ret;
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_ENGINE_1finish(JNIEnv* env, jclass, jlong engineRef) {
    ENGINE* e = reinterpret_cast<ENGINE*>(static_cast<uintptr_t>(engineRef));
    JNI_TRACE("ENGINE_finish(%p)", e);

    if (e == NULL) {
        jniThrowException(env, "java/lang/IllegalArgumentException", "engineRef == 0");
        return 0;
    }

    int ret = ENGINE_finish(e);
    JNI_TRACE("ENGINE_finish(%p) => %d", e, ret);
    return ret;
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_ENGINE_1free(JNIEnv* env, jclass, jlong engineRef) {
    ENGINE* e = reinterpret_cast<ENGINE*>(static_cast<uintptr_t>(engineRef));
    JNI_TRACE("ENGINE_free(%p)", e);

    if (e == NULL) {
        jniThrowException(env, "java/lang/IllegalArgumentException", "engineRef == 0");
        return 0;
    }

    int ret = ENGINE_free(e);
    JNI_TRACE("ENGINE_free(%p) => %d", e, ret);
    return ret;
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_ENGINE_1load_1private_1key(JNIEnv* env, jclass, jlong engineRef,
        jstring idJava) {
    ENGINE* e = reinterpret_cast<ENGINE*>(static_cast<uintptr_t>(engineRef));
    JNI_TRACE("ENGINE_load_private_key(%p, %p)", e, idJava);

    ScopedUtfChars id(env, idJava);
    if (id.c_str() == NULL) {
        jniThrowException(env, "java/lang/IllegalArgumentException", "id == NULL");
        return 0;
    }

    Unique_EVP_PKEY pkey(ENGINE_load_private_key(e, id.c_str(), NULL, NULL));
    if (pkey.get() == NULL) {
        throwExceptionIfNecessary(env, "ENGINE_load_private_key");
        return 0;
    }

    JNI_TRACE("ENGINE_load_private_key(%p, %p) => %p", e, idJava, pkey.get());
    return reinterpret_cast<uintptr_t>(pkey.release());
}

extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_ENGINE_1get_1id(JNIEnv* env, jclass, jlong engineRef)
{
    ENGINE* e = reinterpret_cast<ENGINE*>(static_cast<uintptr_t>(engineRef));
    JNI_TRACE("ENGINE_get_id(%p)", e);

    if (e == NULL) {
        jniThrowNullPointerException(env, "engine == null");
        JNI_TRACE("ENGINE_get_id(%p) => engine == null", e);
        return NULL;
    }

    const char *id = ENGINE_get_id(e);
    ScopedLocalRef<jstring> idJava(env, env->NewStringUTF(id));

    JNI_TRACE("ENGINE_get_id(%p) => \"%s\"", e, id);
    return idJava.release();
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_ENGINE_1ctrl_1cmd_1string(JNIEnv* env, jclass, jlong engineRef,
        jstring cmdJava, jstring argJava, jint cmd_optional)
{
    ENGINE* e = reinterpret_cast<ENGINE*>(static_cast<uintptr_t>(engineRef));
    JNI_TRACE("ENGINE_ctrl_cmd_string(%p, %p, %p, %d)", e, cmdJava, argJava, cmd_optional);

    if (e == NULL) {
        jniThrowNullPointerException(env, "engine == null");
        JNI_TRACE("ENGINE_ctrl_cmd_string(%p, %p, %p, %d) => engine == null", e, cmdJava, argJava,
                cmd_optional);
        return 0;
    }

    ScopedUtfChars cmdChars(env, cmdJava);
    if (cmdChars.c_str() == NULL) {
        return 0;
    }

    UniquePtr<ScopedUtfChars> arg;
    const char* arg_c_str = NULL;
    if (argJava != NULL) {
        arg.reset(new ScopedUtfChars(env, argJava));
        arg_c_str = arg->c_str();
        if (arg_c_str == NULL) {
            return 0;
        }
    }
    JNI_TRACE("ENGINE_ctrl_cmd_string(%p, \"%s\", \"%s\", %d)", e, cmdChars.c_str(), arg_c_str,
            cmd_optional);

    int ret = ENGINE_ctrl_cmd_string(e, cmdChars.c_str(), arg_c_str, cmd_optional);
    if (ret != 1) {
        throwExceptionIfNecessary(env, "ENGINE_ctrl_cmd_string");
        JNI_TRACE("ENGINE_ctrl_cmd_string(%p, \"%s\", \"%s\", %d) => threw error", e,
                cmdChars.c_str(), arg_c_str, cmd_optional);
        return 0;
    }

    JNI_TRACE("ENGINE_ctrl_cmd_string(%p, \"%s\", \"%s\", %d) => %d", e, cmdChars.c_str(),
            arg_c_str, cmd_optional, ret);
    return ret;
}

/**
 * public static native int EVP_PKEY_new_DSA(byte[] p, byte[] q, byte[] g,
 *                                           byte[] pub_key, byte[] priv_key);
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EVP_1PKEY_1new_1DSA(JNIEnv* env, jclass,
                                               jbyteArray p, jbyteArray q, jbyteArray g,
                                               jbyteArray pub_key, jbyteArray priv_key) {
    JNI_TRACE("EVP_PKEY_new_DSA(p=%p, q=%p, g=%p, pub_key=%p, priv_key=%p)",
              p, q, g, pub_key, priv_key);

    Unique_DSA dsa(DSA_new());
    if (dsa.get() == NULL) {
        jniThrowRuntimeException(env, "DSA_new failed");
        return 0;
    }

    if (!arrayToBignum(env, p, &dsa->p)) {
        return 0;
    }

    if (!arrayToBignum(env, q, &dsa->q)) {
        return 0;
    }

    if (!arrayToBignum(env, g, &dsa->g)) {
        return 0;
    }

    if (pub_key != NULL && !arrayToBignum(env, pub_key, &dsa->pub_key)) {
        return 0;
    }

    if (priv_key != NULL && !arrayToBignum(env, priv_key, &dsa->priv_key)) {
        return 0;
    }

    if (dsa->p == NULL || dsa->q == NULL || dsa->g == NULL
            || (dsa->pub_key == NULL && dsa->priv_key == NULL)) {
        jniThrowRuntimeException(env, "Unable to convert BigInteger to BIGNUM");
        return 0;
    }

    Unique_EVP_PKEY pkey(EVP_PKEY_new());
    if (pkey.get() == NULL) {
        jniThrowRuntimeException(env, "EVP_PKEY_new failed");
        return 0;
    }
    if (EVP_PKEY_assign_DSA(pkey.get(), dsa.get()) != 1) {
        jniThrowRuntimeException(env, "EVP_PKEY_assign_DSA failed");
        return 0;
    }
    OWNERSHIP_TRANSFERRED(dsa);
    JNI_TRACE("EVP_PKEY_new_DSA(p=%p, q=%p, g=%p, pub_key=%p, priv_key=%p) => %p",
              p, q, g, pub_key, priv_key, pkey.get());
    return reinterpret_cast<jlong>(pkey.release());
}

/**
 * private static native int EVP_PKEY_new_RSA(byte[] n, byte[] e, byte[] d, byte[] p, byte[] q);
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EVP_1PKEY_1new_1RSA(JNIEnv* env, jclass,
                                               jbyteArray n, jbyteArray e, jbyteArray d,
                                               jbyteArray p, jbyteArray q,
                                               jbyteArray dmp1, jbyteArray dmq1,
                                               jbyteArray iqmp) {
    JNI_TRACE("EVP_PKEY_new_RSA(n=%p, e=%p, d=%p, p=%p, q=%p, dmp1=%p, dmq1=%p, iqmp=%p)",
            n, e, d, p, q, dmp1, dmq1, iqmp);

    Unique_RSA rsa(RSA_new());
    if (rsa.get() == NULL) {
        jniThrowRuntimeException(env, "RSA_new failed");
        return 0;
    }

    if (e == NULL && d == NULL) {
        jniThrowException(env, "java/lang/IllegalArgumentException", "e == NULL && d == NULL");
        JNI_TRACE("NativeCrypto_EVP_PKEY_new_RSA => e == NULL && d == NULL");
        return 0;
    }

    if (!arrayToBignum(env, n, &rsa->n)) {
        return 0;
    }

    if (e != NULL && !arrayToBignum(env, e, &rsa->e)) {
        return 0;
    }

    if (d != NULL && !arrayToBignum(env, d, &rsa->d)) {
        return 0;
    }

    if (p != NULL && !arrayToBignum(env, p, &rsa->p)) {
        return 0;
    }

    if (q != NULL && !arrayToBignum(env, q, &rsa->q)) {
        return 0;
    }

    if (dmp1 != NULL && !arrayToBignum(env, dmp1, &rsa->dmp1)) {
        return 0;
    }

    if (dmq1 != NULL && !arrayToBignum(env, dmq1, &rsa->dmq1)) {
        return 0;
    }

    if (iqmp != NULL && !arrayToBignum(env, iqmp, &rsa->iqmp)) {
        return 0;
    }

#ifdef WITH_JNI_TRACE
    if (p != NULL && q != NULL) {
        int check = RSA_check_key(rsa.get());
        JNI_TRACE("EVP_PKEY_new_RSA(...) RSA_check_key returns %d", check);
    }
#endif

    if (rsa->n == NULL || (rsa->e == NULL && rsa->d == NULL)) {
        jniThrowRuntimeException(env, "Unable to convert BigInteger to BIGNUM");
        return 0;
    }

    /*
     * If the private exponent is available, there is the potential to do signing
     * operations. If the public exponent is also available, OpenSSL will do RSA
     * blinding. Enable it if possible.
     */
    if (rsa->d != NULL) {
        if (rsa->e != NULL) {
            JNI_TRACE("EVP_PKEY_new_RSA(...) enabling RSA blinding => %p", rsa.get());
            RSA_blinding_on(rsa.get(), NULL);
        } else {
            JNI_TRACE("EVP_PKEY_new_RSA(...) disabling RSA blinding => %p", rsa.get());
            RSA_blinding_off(rsa.get());
        }
    }

    Unique_EVP_PKEY pkey(EVP_PKEY_new());
    if (pkey.get() == NULL) {
        jniThrowRuntimeException(env, "EVP_PKEY_new failed");
        return 0;
    }
    if (EVP_PKEY_assign_RSA(pkey.get(), rsa.get()) != 1) {
        jniThrowRuntimeException(env, "EVP_PKEY_new failed");
        return 0;
    }
    OWNERSHIP_TRANSFERRED(rsa);
    JNI_TRACE("EVP_PKEY_new_RSA(n=%p, e=%p, d=%p, p=%p, q=%p dmp1=%p, dmq1=%p, iqmp=%p) => %p",
            n, e, d, p, q, dmp1, dmq1, iqmp, pkey.get());
    return reinterpret_cast<uintptr_t>(pkey.release());
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EVP_1PKEY_1new_1EC_1KEY(JNIEnv* env, jclass, jlong groupRef,
        jlong pubkeyRef, jbyteArray keyJavaBytes) {
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    const EC_POINT* pubkey = reinterpret_cast<const EC_POINT*>(pubkeyRef);
    JNI_TRACE("EVP_PKEY_new_EC_KEY(%p, %p, %p)", group, pubkey, keyJavaBytes);

    Unique_BIGNUM key(NULL);
    if (keyJavaBytes != NULL) {
        BIGNUM* keyRef;
        if (!arrayToBignum(env, keyJavaBytes, &keyRef)) {
            return 0;
        }
        key.reset(keyRef);
    }

    Unique_EC_KEY eckey(EC_KEY_new());
    if (eckey.get() == NULL) {
        jniThrowRuntimeException(env, "EC_KEY_new failed");
        return 0;
    }

    if (EC_KEY_set_group(eckey.get(), group) != 1) {
        JNI_TRACE("EVP_PKEY_new_EC_KEY(%p, %p, %p) > EC_KEY_set_group failed", group, pubkey,
                keyJavaBytes);
        throwExceptionIfNecessary(env, "EC_KEY_set_group");
        return 0;
    }

    if (pubkey != NULL) {
        if (EC_KEY_set_public_key(eckey.get(), pubkey) != 1) {
            JNI_TRACE("EVP_PKEY_new_EC_KEY(%p, %p, %p) => EC_KEY_set_private_key failed", group,
                    pubkey, keyJavaBytes);
            throwExceptionIfNecessary(env, "EC_KEY_set_public_key");
            return 0;
        }
    }

    if (key.get() != NULL) {
        if (EC_KEY_set_private_key(eckey.get(), key.get()) != 1) {
            JNI_TRACE("EVP_PKEY_new_EC_KEY(%p, %p, %p) => EC_KEY_set_private_key failed", group,
                    pubkey, keyJavaBytes);
            throwExceptionIfNecessary(env, "EC_KEY_set_private_key");
            return 0;
        }
        if (pubkey == NULL) {
            Unique_EC_POINT calcPubkey(EC_POINT_new(group));
            if (!EC_POINT_mul(group, calcPubkey.get(), key.get(), NULL, NULL, NULL)) {
                JNI_TRACE("EVP_PKEY_new_EC_KEY(%p, %p, %p) => can't calulate public key", group,
                        pubkey, keyJavaBytes);
                throwExceptionIfNecessary(env, "EC_KEY_set_private_key");
                return 0;
            }
            EC_KEY_set_public_key(eckey.get(), calcPubkey.get());
        }
    }

    if (!EC_KEY_check_key(eckey.get())) {
        JNI_TRACE("EVP_KEY_new_EC_KEY(%p, %p, %p) => invalid key created", group, pubkey, keyJavaBytes);
        throwExceptionIfNecessary(env, "EC_KEY_check_key");
        return 0;
    }

    Unique_EVP_PKEY pkey(EVP_PKEY_new());
    if (pkey.get() == NULL) {
        JNI_TRACE("EVP_PKEY_new_EC(%p, %p, %p) => threw error", group, pubkey, keyJavaBytes);
        throwExceptionIfNecessary(env, "EVP_PKEY_new failed");
        return 0;
    }
    if (EVP_PKEY_assign_EC_KEY(pkey.get(), eckey.get()) != 1) {
        JNI_TRACE("EVP_PKEY_new_EC(%p, %p, %p) => threw error", group, pubkey, keyJavaBytes);
        jniThrowRuntimeException(env, "EVP_PKEY_assign_EC_KEY failed");
        return 0;
    }
    OWNERSHIP_TRANSFERRED(eckey);

    JNI_TRACE("EVP_PKEY_new_EC_KEY(%p, %p, %p) => %p", group, pubkey, keyJavaBytes, pkey.get());
    return reinterpret_cast<uintptr_t>(pkey.release());
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EVP_1PKEY_1new_1mac_1key(JNIEnv* env, jclass, jint pkeyType,
        jbyteArray keyJavaBytes)
{
    JNI_TRACE("EVP_PKEY_new_mac_key(%d, %p)", pkeyType, keyJavaBytes);

    ScopedByteArrayRO key(env, keyJavaBytes);
    if (key.get() == NULL) {
        return 0;
    }

    const unsigned char* tmp = reinterpret_cast<const unsigned char*>(key.get());
    Unique_EVP_PKEY pkey(EVP_PKEY_new_mac_key(pkeyType, (ENGINE *) NULL, tmp, key.size()));
    if (pkey.get() == NULL) {
        JNI_TRACE("EVP_PKEY_new_mac_key(%d, %p) => threw error", pkeyType, keyJavaBytes);
        throwExceptionIfNecessary(env, "ENGINE_load_private_key");
        return 0;
    }

    JNI_TRACE("EVP_PKEY_new_mac_key(%d, %p) => %p", pkeyType, keyJavaBytes, pkey.get());
    return reinterpret_cast<uintptr_t>(pkey.release());
}

extern "C" int Java_com_android_org_conscrypt_NativeCrypto_EVP_1PKEY_1type(JNIEnv* env, jclass, jlong pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("EVP_PKEY_type(%p)", pkey);

    if (pkey == NULL) {
        jniThrowNullPointerException(env, NULL);
        return -1;
    }

    int result = EVP_PKEY_type(pkey->type);
    JNI_TRACE("EVP_PKEY_type(%p) => %d", pkey, result);
    return result;
}

/**
 * private static native int EVP_PKEY_size(int pkey);
 */
extern "C" int Java_com_android_org_conscrypt_NativeCrypto_EVP_1PKEY_1size(JNIEnv* env, jclass, jlong pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("EVP_PKEY_size(%p)", pkey);

    if (pkey == NULL) {
        jniThrowNullPointerException(env, NULL);
        return -1;
    }

    int result = EVP_PKEY_size(pkey);
    JNI_TRACE("EVP_PKEY_size(%p) => %d", pkey, result);
    return result;
}

extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_EVP_1PKEY_1print_1public(JNIEnv* env, jclass, jlong pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("EVP_PKEY_print_public(%p)", pkey);

    if (pkey == NULL) {
        jniThrowNullPointerException(env, "pkey == null");
        return NULL;
    }

    Unique_BIO buffer(BIO_new(BIO_s_mem()));
    if (buffer.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate BIO");
        return NULL;
    }

    if (EVP_PKEY_print_public(buffer.get(), pkey, 0, (ASN1_PCTX*) NULL) != 1) {
        throwExceptionIfNecessary(env, "EVP_PKEY_print_public");
        return NULL;
    }
    // Null terminate this
    BIO_write(buffer.get(), "\0", 1);

    char *tmp;
    BIO_get_mem_data(buffer.get(), &tmp);
    jstring description = env->NewStringUTF(tmp);

    JNI_TRACE("EVP_PKEY_print_public(%p) => \"%s\"", pkey, tmp);
    return description;
}

extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_EVP_1PKEY_1print_1private(JNIEnv* env, jclass, jlong pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("EVP_PKEY_print_private(%p)", pkey);

    if (pkey == NULL) {
        jniThrowNullPointerException(env, "pkey == null");
        return NULL;
    }

    Unique_BIO buffer(BIO_new(BIO_s_mem()));
    if (buffer.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate BIO");
        return NULL;
    }

    if (EVP_PKEY_print_private(buffer.get(), pkey, 0, (ASN1_PCTX*) NULL) != 1) {
        throwExceptionIfNecessary(env, "EVP_PKEY_print_private");
        return NULL;
    }
    // Null terminate this
    BIO_write(buffer.get(), "\0", 1);

    char *tmp;
    BIO_get_mem_data(buffer.get(), &tmp);
    jstring description = env->NewStringUTF(tmp);

    JNI_TRACE("EVP_PKEY_print_private(%p) => \"%s\"", pkey, tmp);
    return description;
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EVP_1PKEY_1free(JNIEnv*, jclass, jlong pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("EVP_PKEY_free(%p)", pkey);

    if (pkey != NULL) {
        EVP_PKEY_free(pkey);
    }
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_EVP_1PKEY_1cmp(JNIEnv* env, jclass, jlong pkey1Ref, jlong pkey2Ref) {
    EVP_PKEY* pkey1 = reinterpret_cast<EVP_PKEY*>(pkey1Ref);
    EVP_PKEY* pkey2 = reinterpret_cast<EVP_PKEY*>(pkey2Ref);
    JNI_TRACE("EVP_PKEY_cmp(%p, %p)", pkey1, pkey2);

    if (pkey1 == NULL) {
        JNI_TRACE("EVP_PKEY_cmp(%p, %p) => failed pkey1 == NULL", pkey1, pkey2);
        jniThrowNullPointerException(env, "pkey1 == NULL");
        return -1;
    } else if (pkey2 == NULL) {
        JNI_TRACE("EVP_PKEY_cmp(%p, %p) => failed pkey2 == NULL", pkey1, pkey2);
        jniThrowNullPointerException(env, "pkey2 == NULL");
        return -1;
    }

    int result = EVP_PKEY_cmp(pkey1, pkey2);
    JNI_TRACE("EVP_PKEY_cmp(%p, %p) => %d", pkey1, pkey2, result);
    return result;
}

/*
 * static native byte[] i2d_PKCS8_PRIV_KEY_INFO(int, byte[])
 */
extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_i2d_1PKCS8_1PRIV_1KEY_1INFO(JNIEnv* env, jclass, jlong pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("i2d_PKCS8_PRIV_KEY_INFO(%p)", pkey);

    if (pkey == NULL) {
        jniThrowNullPointerException(env, NULL);
        return NULL;
    }

    Unique_PKCS8_PRIV_KEY_INFO pkcs8(EVP_PKEY2PKCS8(pkey));
    if (pkcs8.get() == NULL) {
        throwExceptionIfNecessary(env, "NativeCrypto_i2d_PKCS8_PRIV_KEY_INFO");
        JNI_TRACE("key=%p i2d_PKCS8_PRIV_KEY_INFO => error from key to PKCS8", pkey);
        return NULL;
    }

    return ASN1ToByteArray<PKCS8_PRIV_KEY_INFO, i2d_PKCS8_PRIV_KEY_INFO>(env, pkcs8.get());
}

/*
 * static native int d2i_PKCS8_PRIV_KEY_INFO(byte[])
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_d2i_1PKCS8_1PRIV_1KEY_1INFO(JNIEnv* env, jclass, jbyteArray keyJavaBytes) {
    JNI_TRACE("d2i_PKCS8_PRIV_KEY_INFO(%p)", keyJavaBytes);

    ScopedByteArrayRO bytes(env, keyJavaBytes);
    if (bytes.get() == NULL) {
        JNI_TRACE("bytes=%p d2i_PKCS8_PRIV_KEY_INFO => threw exception", keyJavaBytes);
        return 0;
    }

    const unsigned char* tmp = reinterpret_cast<const unsigned char*>(bytes.get());
    Unique_PKCS8_PRIV_KEY_INFO pkcs8(d2i_PKCS8_PRIV_KEY_INFO(NULL, &tmp, bytes.size()));
    if (pkcs8.get() == NULL) {
        throwExceptionIfNecessary(env, "d2i_PKCS8_PRIV_KEY_INFO");
        JNI_TRACE("ssl=%p d2i_PKCS8_PRIV_KEY_INFO => error from DER to PKCS8", keyJavaBytes);
        return 0;
    }

    Unique_EVP_PKEY pkey(EVP_PKCS82PKEY(pkcs8.get()));
    if (pkey.get() == NULL) {
        throwExceptionIfNecessary(env, "d2i_PKCS8_PRIV_KEY_INFO");
        JNI_TRACE("ssl=%p d2i_PKCS8_PRIV_KEY_INFO => error from PKCS8 to key", keyJavaBytes);
        return 0;
    }

    JNI_TRACE("bytes=%p d2i_PKCS8_PRIV_KEY_INFO => %p", keyJavaBytes, pkey.get());
    return reinterpret_cast<uintptr_t>(pkey.release());
}

/*
 * static native byte[] i2d_PUBKEY(int)
 */
extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_i2d_1PUBKEY(JNIEnv* env, jclass, jlong pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("i2d_PUBKEY(%p)", pkey);
    return ASN1ToByteArray<EVP_PKEY, i2d_PUBKEY>(env, pkey);
}

/*
 * static native int d2i_PUBKEY(byte[])
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_d2i_1PUBKEY(JNIEnv* env, jclass, jbyteArray javaBytes) {
    JNI_TRACE("d2i_PUBKEY(%p)", javaBytes);

    ScopedByteArrayRO bytes(env, javaBytes);
    if (bytes.get() == NULL) {
        JNI_TRACE("d2i_PUBKEY(%p) => threw error", javaBytes);
        return 0;
    }

    const unsigned char* tmp = reinterpret_cast<const unsigned char*>(bytes.get());
    Unique_EVP_PKEY pkey(d2i_PUBKEY(NULL, &tmp, bytes.size()));
    if (pkey.get() == NULL) {
        JNI_TRACE("bytes=%p d2i_PUBKEY => threw exception", javaBytes);
        throwExceptionIfNecessary(env, "d2i_PUBKEY");
        return 0;
    }

    return reinterpret_cast<uintptr_t>(pkey.release());
}

/*
 * public static native int RSA_generate_key(int modulusBits, byte[] publicExponent);
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_RSA_1generate_1key_1ex(JNIEnv* env, jclass, jint modulusBits,
        jbyteArray publicExponent) {
    JNI_TRACE("RSA_generate_key_ex(%d, %p)", modulusBits, publicExponent);

    BIGNUM* eRef;
    if (!arrayToBignum(env, publicExponent, &eRef)) {
        return 0;
    }
    Unique_BIGNUM e(eRef);

    Unique_RSA rsa(RSA_new());
    if (rsa.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate RSA key");
        return 0;
    }

    if (RSA_generate_key_ex(rsa.get(), modulusBits, e.get(), NULL) < 0) {
        throwExceptionIfNecessary(env, "RSA_generate_key_ex");
        return 0;
    }

    Unique_EVP_PKEY pkey(EVP_PKEY_new());
    if (pkey.get() == NULL) {
        jniThrowRuntimeException(env, "RSA_generate_key_ex failed");
        return 0;
    }

    if (EVP_PKEY_assign_RSA(pkey.get(), rsa.get()) != 1) {
        jniThrowRuntimeException(env, "RSA_generate_key_ex failed");
        return 0;
    }

    OWNERSHIP_TRANSFERRED(rsa);
    JNI_TRACE("RSA_generate_key_ex(n=%d, e=%p) => %p", modulusBits, publicExponent, pkey.get());
    return reinterpret_cast<uintptr_t>(pkey.release());
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_RSA_1size(JNIEnv* env, jclass, jlong pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("RSA_size(%p)", pkey);

    Unique_RSA rsa(EVP_PKEY_get1_RSA(pkey));
    if (rsa.get() == NULL) {
        jniThrowRuntimeException(env, "RSA_size failed");
        return 0;
    }

    return static_cast<jint>(RSA_size(rsa.get()));
}

typedef int RSACryptOperation(int flen, const unsigned char* from, unsigned char* to, RSA* rsa,
                              int padding);

static jint RSA_crypt_operation(RSACryptOperation operation,
        const char* caller __attribute__ ((unused)), JNIEnv* env, jint flen,
        jbyteArray fromJavaBytes, jbyteArray toJavaBytes, jlong pkeyRef, jint padding) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("%s(%d, %p, %p, %p)", caller, flen, fromJavaBytes, toJavaBytes, pkey);

    Unique_RSA rsa(EVP_PKEY_get1_RSA(pkey));
    if (rsa.get() == NULL) {
        return -1;
    }

    ScopedByteArrayRO from(env, fromJavaBytes);
    if (from.get() == NULL) {
        return -1;
    }

    ScopedByteArrayRW to(env, toJavaBytes);
    if (to.get() == NULL) {
        return -1;
    }

    int resultSize = operation(static_cast<int>(flen),
            reinterpret_cast<const unsigned char*>(from.get()),
            reinterpret_cast<unsigned char*>(to.get()), rsa.get(), padding);
    if (resultSize == -1) {
        JNI_TRACE("%s => failed", caller);
        throwExceptionIfNecessary(env, "RSA_crypt_operation");
        return -1;
    }

    JNI_TRACE("%s(%d, %p, %p, %p) => %d", caller, flen, fromJavaBytes, toJavaBytes, pkey,
              resultSize);
    return static_cast<jint>(resultSize);
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_RSA_1private_1encrypt(JNIEnv* env, jclass, jint flen,
        jbyteArray fromJavaBytes, jbyteArray toJavaBytes, jlong pkeyRef, jint padding) {
    return RSA_crypt_operation(RSA_private_encrypt, __FUNCTION__,
                               env, flen, fromJavaBytes, toJavaBytes, pkeyRef, padding);
}
extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_RSA_1public_1decrypt(JNIEnv* env, jclass, jint flen,
        jbyteArray fromJavaBytes, jbyteArray toJavaBytes, jlong pkeyRef, jint padding) {
    return RSA_crypt_operation(RSA_public_decrypt, __FUNCTION__,
                               env, flen, fromJavaBytes, toJavaBytes, pkeyRef, padding);
}
extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_RSA_1public_1encrypt(JNIEnv* env, jclass, jint flen,
        jbyteArray fromJavaBytes, jbyteArray toJavaBytes, jlong pkeyRef, jint padding) {
    return RSA_crypt_operation(RSA_public_encrypt, __FUNCTION__,
                               env, flen, fromJavaBytes, toJavaBytes, pkeyRef, padding);
}
extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_RSA_1private_1decrypt(JNIEnv* env, jclass, jint flen,
        jbyteArray fromJavaBytes, jbyteArray toJavaBytes, jlong pkeyRef, jint padding) {
    return RSA_crypt_operation(RSA_private_decrypt, __FUNCTION__,
                               env, flen, fromJavaBytes, toJavaBytes, pkeyRef, padding);
}

/*
 * public static native byte[][] get_RSA_public_params(int);
 */
extern "C" jobjectArray Java_com_android_org_conscrypt_NativeCrypto_get_1RSA_1public_1params(JNIEnv* env, jclass, jlong pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("get_RSA_public_params(%p)", pkey);

    if (pkey == NULL) {
        jniThrowNullPointerException(env, "pkey == null");
        return 0;
    }

    Unique_RSA rsa(EVP_PKEY_get1_RSA(pkey));
    if (rsa.get() == NULL) {
        throwExceptionIfNecessary(env, "get_RSA_public_params failed");
        return 0;
    }

    jobjectArray joa = env->NewObjectArray(2, byteArrayClass, NULL);
    if (joa == NULL) {
        return NULL;
    }

    jbyteArray n = bignumToArray(env, rsa->n, "n");
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 0, n);

    jbyteArray e = bignumToArray(env, rsa->e, "e");
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 1, e);

    return joa;
}

/*
 * public static native byte[][] get_RSA_private_params(int);
 */
extern "C" jobjectArray Java_com_android_org_conscrypt_NativeCrypto_get_1RSA_1private_1params(JNIEnv* env, jclass, jlong pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("get_RSA_public_params(%p)", pkey);

    if (pkey == NULL) {
        jniThrowNullPointerException(env, "pkey == null");
        return 0;
    }

    Unique_RSA rsa(EVP_PKEY_get1_RSA(pkey));
    if (rsa.get() == NULL) {
        throwExceptionIfNecessary(env, "get_RSA_public_params failed");
        return 0;
    }

    jobjectArray joa = env->NewObjectArray(8, byteArrayClass, NULL);
    if (joa == NULL) {
        return NULL;
    }

    jbyteArray n = bignumToArray(env, rsa->n, "n");
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 0, n);

    if (rsa->e != NULL) {
        jbyteArray e = bignumToArray(env, rsa->e, "e");
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 1, e);
    }

    if (rsa->d != NULL) {
        jbyteArray d = bignumToArray(env, rsa->d, "d");
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 2, d);
    }

    if (rsa->p != NULL) {
        jbyteArray p = bignumToArray(env, rsa->p, "p");
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 3, p);
    }

    if (rsa->q != NULL) {
        jbyteArray q = bignumToArray(env, rsa->q, "q");
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 4, q);
    }

    if (rsa->dmp1 != NULL) {
        jbyteArray dmp1 = bignumToArray(env, rsa->dmp1, "dmp1");
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 5, dmp1);
    }

    if (rsa->dmq1 != NULL) {
        jbyteArray dmq1 = bignumToArray(env, rsa->dmq1, "dmq1");
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 6, dmq1);
    }

    if (rsa->iqmp != NULL) {
        jbyteArray iqmp = bignumToArray(env, rsa->iqmp, "iqmp");
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 7, iqmp);
    }

    return joa;
}

/*
 * public static native int DSA_generate_key(int, byte[]);
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_DSA_1generate_1key(JNIEnv* env, jclass, jint primeBits,
        jbyteArray seedJavaBytes, jbyteArray gBytes, jbyteArray pBytes, jbyteArray qBytes) {
    JNI_TRACE("DSA_generate_key(%d, %p, %p, %p, %p)", primeBits, seedJavaBytes,
            gBytes, pBytes, qBytes);

    UniquePtr<unsigned char[]> seedPtr;
    unsigned long seedSize = 0;
    if (seedJavaBytes != NULL) {
        ScopedByteArrayRO seed(env, seedJavaBytes);
        if (seed.get() == NULL) {
            return 0;
        }

        seedSize = seed.size();
        seedPtr.reset(new unsigned char[seedSize]);

        memcpy(seedPtr.get(), seed.get(), seedSize);
    }

    Unique_DSA dsa(DSA_new());
    if (dsa.get() == NULL) {
        JNI_TRACE("DSA_generate_key failed");
        jniThrowOutOfMemory(env, "Unable to allocate DSA key");
        freeOpenSslErrorState();
        return 0;
    }

    if (gBytes != NULL && pBytes != NULL && qBytes != NULL) {
        JNI_TRACE("DSA_generate_key parameters specified");

        if (!arrayToBignum(env, gBytes, &dsa->g)) {
            return 0;
        }

        if (!arrayToBignum(env, pBytes, &dsa->p)) {
            return 0;
        }

        if (!arrayToBignum(env, qBytes, &dsa->q)) {
            return 0;
        }
    } else {
        JNI_TRACE("DSA_generate_key generating parameters");

        if (!DSA_generate_parameters_ex(dsa.get(), primeBits, seedPtr.get(), seedSize, NULL, NULL, NULL)) {
            JNI_TRACE("DSA_generate_key => param generation failed");
            throwExceptionIfNecessary(env, "NativeCrypto_DSA_generate_parameters_ex failed");
            return 0;
        }
    }

    if (!DSA_generate_key(dsa.get())) {
        JNI_TRACE("DSA_generate_key failed");
        throwExceptionIfNecessary(env, "NativeCrypto_DSA_generate_key failed");
        return 0;
    }

    Unique_EVP_PKEY pkey(EVP_PKEY_new());
    if (pkey.get() == NULL) {
        JNI_TRACE("DSA_generate_key failed");
        jniThrowRuntimeException(env, "NativeCrypto_DSA_generate_key failed");
        freeOpenSslErrorState();
        return 0;
    }

    if (EVP_PKEY_assign_DSA(pkey.get(), dsa.get()) != 1) {
        JNI_TRACE("DSA_generate_key failed");
        throwExceptionIfNecessary(env, "NativeCrypto_DSA_generate_key failed");
        return 0;
    }

    OWNERSHIP_TRANSFERRED(dsa);
    JNI_TRACE("DSA_generate_key(n=%d, e=%p) => %p", primeBits, seedPtr.get(), pkey.get());
    return reinterpret_cast<uintptr_t>(pkey.release());
}

/*
 * public static native byte[][] get_DSA_params(int);
 */
extern "C" jobjectArray Java_com_android_org_conscrypt_NativeCrypto_get_1DSA_1params(JNIEnv* env, jclass, jlong pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("get_DSA_params(%p)", pkey);

    Unique_DSA dsa(EVP_PKEY_get1_DSA(pkey));
    if (dsa.get() == NULL) {
        throwExceptionIfNecessary(env, "get_DSA_params failed");
        return 0;
    }

    jobjectArray joa = env->NewObjectArray(5, byteArrayClass, NULL);
    if (joa == NULL) {
        return NULL;
    }

    if (dsa->g != NULL) {
        jbyteArray g = bignumToArray(env, dsa->g, "g");
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 0, g);
    }

    if (dsa->p != NULL) {
        jbyteArray p = bignumToArray(env, dsa->p, "p");
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 1, p);
    }

    if (dsa->q != NULL) {
        jbyteArray q = bignumToArray(env, dsa->q, "q");
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 2, q);
    }

    if (dsa->pub_key != NULL) {
        jbyteArray pub_key = bignumToArray(env, dsa->pub_key, "pub_key");
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 3, pub_key);
    }

    if (dsa->priv_key != NULL) {
        jbyteArray priv_key = bignumToArray(env, dsa->priv_key, "priv_key");
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 4, priv_key);
    }

    return joa;
}

#define EC_CURVE_GFP 1
#define EC_CURVE_GF2M 2

/**
 * Return group type or 0 if unknown group.
 * EC_GROUP_GFP or EC_GROUP_GF2M
 */
static int get_EC_GROUP_type(const EC_GROUP* group)
{
    const EC_METHOD* method = EC_GROUP_method_of(group);
    if (method == EC_GFp_nist_method()
                || method == EC_GFp_mont_method()
                || method == EC_GFp_simple_method()) {
        return EC_CURVE_GFP;
    } else if (method == EC_GF2m_simple_method()) {
        return EC_CURVE_GF2M;
    }

    return 0;
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1new_1by_1curve_1name(JNIEnv* env, jclass, jstring curveNameJava)
{
    JNI_TRACE("EC_GROUP_new_by_curve_name(%p)", curveNameJava);

    ScopedUtfChars curveName(env, curveNameJava);
    if (curveName.c_str() == NULL) {
        return 0;
    }
    JNI_TRACE("EC_GROUP_new_by_curve_name(%s)", curveName.c_str());

    int nid = OBJ_sn2nid(curveName.c_str());
    if (nid == NID_undef) {
        JNI_TRACE("EC_GROUP_new_by_curve_name(%s) => unknown NID name", curveName.c_str());
        return 0;
    }

    EC_GROUP* group = EC_GROUP_new_by_curve_name(nid);
    if (group == NULL) {
        JNI_TRACE("EC_GROUP_new_by_curve_name(%s) => unknown NID %d", curveName.c_str(), nid);
        freeOpenSslErrorState();
        return 0;
    }

    JNI_TRACE("EC_GROUP_new_by_curve_name(%s) => %p", curveName.c_str(), group);
    return reinterpret_cast<uintptr_t>(group);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1set_1asn1_1flag(JNIEnv* env, jclass, jlong groupRef,
        jint flag)
{
    EC_GROUP* group = reinterpret_cast<EC_GROUP*>(groupRef);
    JNI_TRACE("EC_GROUP_set_asn1_flag(%p, %d)", group, flag);

    if (group == NULL) {
        JNI_TRACE("EC_GROUP_set_asn1_flag => group == NULL");
        jniThrowNullPointerException(env, "group == NULL");
        return;
    }

    EC_GROUP_set_asn1_flag(group, flag);
    JNI_TRACE("EC_GROUP_set_asn1_flag(%p, %d) => success", group, flag);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1set_1point_1conversion_1form(JNIEnv* env, jclass,
        jlong groupRef, jint form)
{
    EC_GROUP* group = reinterpret_cast<EC_GROUP*>(groupRef);
    JNI_TRACE("EC_GROUP_set_point_conversion_form(%p, %d)", group, form);

    if (group == NULL) {
        JNI_TRACE("EC_GROUP_set_point_conversion_form => group == NULL");
        jniThrowNullPointerException(env, "group == NULL");
        return;
    }

    EC_GROUP_set_point_conversion_form(group, static_cast<point_conversion_form_t>(form));
    JNI_TRACE("EC_GROUP_set_point_conversion_form(%p, %d) => success", group, form);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1new_1curve(JNIEnv* env, jclass, jint type, jbyteArray pJava,
        jbyteArray aJava, jbyteArray bJava)
{
    JNI_TRACE("EC_GROUP_new_curve(%d, %p, %p, %p)", type, pJava, aJava, bJava);

    BIGNUM* pRef;
    if (!arrayToBignum(env, pJava, &pRef)) {
        return 0;
    }
    Unique_BIGNUM p(pRef);

    BIGNUM* aRef;
    if (!arrayToBignum(env, aJava, &aRef)) {
        return 0;
    }
    Unique_BIGNUM a(aRef);

    BIGNUM* bRef;
    if (!arrayToBignum(env, bJava, &bRef)) {
        return 0;
    }
    Unique_BIGNUM b(bRef);

    EC_GROUP* group;
    switch (type) {
    case EC_CURVE_GFP:
        group = EC_GROUP_new_curve_GFp(p.get(), a.get(), b.get(), (BN_CTX*) NULL);
        break;
    case EC_CURVE_GF2M:
        group = EC_GROUP_new_curve_GF2m(p.get(), a.get(), b.get(), (BN_CTX*) NULL);
        break;
    default:
        jniThrowRuntimeException(env, "invalid group");
        return 0;
    }

    if (group == NULL) {
        throwExceptionIfNecessary(env, "EC_GROUP_new_curve");
    }

    JNI_TRACE("EC_GROUP_new_curve(%d, %p, %p, %p) => %p", type, pJava, aJava, bJava, group);
    return reinterpret_cast<uintptr_t>(group);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1dup(JNIEnv* env, jclass, jlong groupRef) {
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    JNI_TRACE("EC_GROUP_dup(%p)", group);

    if (group == NULL) {
         JNI_TRACE("EC_GROUP_dup => group == NULL");
         jniThrowNullPointerException(env, "group == NULL");
         return 0;
     }

     EC_GROUP* groupDup = EC_GROUP_dup(group);
     JNI_TRACE("EC_GROUP_dup(%p) => %p", group, groupDup);
     return reinterpret_cast<uintptr_t>(groupDup);
}

extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1get_1curve_1name(JNIEnv* env, jclass, jlong groupRef) {
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    JNI_TRACE("EC_GROUP_get_curve_name(%p)", group);

    if (group == NULL) {
        JNI_TRACE("EC_GROUP_get_curve_name => group == NULL");
        jniThrowNullPointerException(env, "group == NULL");
        return 0;
    }

    int nid = EC_GROUP_get_curve_name(group);
    if (nid == NID_undef) {
        JNI_TRACE("EC_GROUP_get_curve_name(%p) => unnamed curve", group);
        return NULL;
    }

    const char* shortName = OBJ_nid2sn(nid);
    JNI_TRACE("EC_GROUP_get_curve_name(%p) => \"%s\"", group, shortName);
    return env->NewStringUTF(shortName);
}

extern "C" jobjectArray Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1get_1curve(JNIEnv* env, jclass, jlong groupRef)
{
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    JNI_TRACE("EC_GROUP_get_curve(%p)", group);

    Unique_BIGNUM p(BN_new());
    Unique_BIGNUM a(BN_new());
    Unique_BIGNUM b(BN_new());

    int ret;
    switch (get_EC_GROUP_type(group)) {
    case EC_CURVE_GFP:
        ret = EC_GROUP_get_curve_GFp(group, p.get(), a.get(), b.get(), (BN_CTX*) NULL);
        break;
    case EC_CURVE_GF2M:
        ret = EC_GROUP_get_curve_GF2m(group, p.get(), a.get(), b.get(), (BN_CTX*)NULL);
        break;
    default:
        jniThrowRuntimeException(env, "invalid group");
        return NULL;
    }
    if (ret != 1) {
        throwExceptionIfNecessary(env, "EC_GROUP_get_curve");
        return NULL;
    }

    jobjectArray joa = env->NewObjectArray(3, byteArrayClass, NULL);
    if (joa == NULL) {
        return NULL;
    }

    jbyteArray pArray = bignumToArray(env, p.get(), "p");
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 0, pArray);

    jbyteArray aArray = bignumToArray(env, a.get(), "a");
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 1, aArray);

    jbyteArray bArray = bignumToArray(env, b.get(), "b");
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 2, bArray);

    JNI_TRACE("EC_GROUP_get_curve(%p) => %p", group, joa);
    return joa;
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1get_1order(JNIEnv* env, jclass, jlong groupRef)
{
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    JNI_TRACE("EC_GROUP_get_order(%p)", group);

    Unique_BIGNUM order(BN_new());
    if (order.get() == NULL) {
        JNI_TRACE("EC_GROUP_get_order(%p) => can't create BN", group);
        jniThrowOutOfMemory(env, "BN_new");
        return NULL;
    }

    if (EC_GROUP_get_order(group, order.get(), NULL) != 1) {
        JNI_TRACE("EC_GROUP_get_order(%p) => threw error", group);
        throwExceptionIfNecessary(env, "EC_GROUP_get_order");
        return NULL;
    }

    jbyteArray orderArray = bignumToArray(env, order.get(), "order");
    if (env->ExceptionCheck()) {
        return NULL;
    }

    JNI_TRACE("EC_GROUP_get_order(%p) => %p", group, orderArray);
    return orderArray;
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1get_1degree(JNIEnv* env, jclass, jlong groupRef)
{
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    JNI_TRACE("EC_GROUP_get_degree(%p)", group);

    jint degree = EC_GROUP_get_degree(group);
    if (degree == 0) {
      JNI_TRACE("EC_GROUP_get_degree(%p) => unsupported", group);
      jniThrowRuntimeException(env, "not supported");
      return 0;
    }

    JNI_TRACE("EC_GROUP_get_degree(%p) => %d", group, degree);
    return degree;
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1get_1cofactor(JNIEnv* env, jclass, jlong groupRef)
{
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    JNI_TRACE("EC_GROUP_get_cofactor(%p)", group);

    Unique_BIGNUM cofactor(BN_new());
    if (cofactor.get() == NULL) {
        JNI_TRACE("EC_GROUP_get_cofactor(%p) => can't create BN", group);
        jniThrowOutOfMemory(env, "BN_new");
        return NULL;
    }

    if (EC_GROUP_get_cofactor(group, cofactor.get(), NULL) != 1) {
        JNI_TRACE("EC_GROUP_get_cofactor(%p) => threw error", group);
        throwExceptionIfNecessary(env, "EC_GROUP_get_cofactor");
        return NULL;
    }

    jbyteArray cofactorArray = bignumToArray(env, cofactor.get(), "cofactor");
    if (env->ExceptionCheck()) {
        return NULL;
    }

    JNI_TRACE("EC_GROUP_get_cofactor(%p) => %p", group, cofactorArray);
    return cofactorArray;
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_get_1EC_1GROUP_1type(JNIEnv* env, jclass, jlong groupRef)
{
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    JNI_TRACE("get_EC_GROUP_type(%p)", group);

    int type = get_EC_GROUP_type(group);
    if (type == 0) {
        JNI_TRACE("get_EC_GROUP_type(%p) => curve type", group);
        jniThrowRuntimeException(env, "unknown curve type");
    } else {
        JNI_TRACE("get_EC_GROUP_type(%p) => %d", group, type);
    }
    return type;
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1clear_1free(JNIEnv* env, jclass, jlong groupRef)
{
    EC_GROUP* group = reinterpret_cast<EC_GROUP*>(groupRef);
    JNI_TRACE("EC_GROUP_clear_free(%p)", group);

    if (group == NULL) {
        JNI_TRACE("EC_GROUP_clear_free => group == NULL");
        jniThrowNullPointerException(env, "group == NULL");
        return;
    }

    EC_GROUP_clear_free(group);
    JNI_TRACE("EC_GROUP_clear_free(%p) => success", group);
}

extern "C" jboolean Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1cmp(JNIEnv* env, jclass, jlong group1Ref, jlong group2Ref)
{
    const EC_GROUP* group1 = reinterpret_cast<const EC_GROUP*>(group1Ref);
    const EC_GROUP* group2 = reinterpret_cast<const EC_GROUP*>(group2Ref);
    JNI_TRACE("EC_GROUP_cmp(%p, %p)", group1, group2);

    if (group1 == NULL || group2 == NULL) {
        JNI_TRACE("EC_GROUP_cmp(%p, %p) => group1 == null || group2 == null", group1, group2);
        jniThrowNullPointerException(env, "group1 == null || group2 == null");
        return false;
    }

    int ret = EC_GROUP_cmp(group1, group2, (BN_CTX*)NULL);

    JNI_TRACE("ECP_GROUP_cmp(%p, %p) => %d", group1, group2, ret);
    return ret == 0;
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1set_1generator(JNIEnv* env, jclass, jlong groupRef, jlong pointRef, jbyteArray njavaBytes, jbyteArray hjavaBytes)
{
    EC_GROUP* group = reinterpret_cast<EC_GROUP*>(groupRef);
    const EC_POINT* point = reinterpret_cast<const EC_POINT*>(pointRef);
    JNI_TRACE("EC_GROUP_set_generator(%p, %p, %p, %p)", group, point, njavaBytes, hjavaBytes);

    if (group == NULL || point == NULL) {
        JNI_TRACE("EC_GROUP_set_generator(%p, %p, %p, %p) => group == null || point == null",
                group, point, njavaBytes, hjavaBytes);
        jniThrowNullPointerException(env, "group == null || point == null");
        return;
    }

    BIGNUM* nRef;
    if (!arrayToBignum(env, njavaBytes, &nRef)) {
        return;
    }
    Unique_BIGNUM n(nRef);

    BIGNUM* hRef;
    if (!arrayToBignum(env, hjavaBytes, &hRef)) {
        return;
    }
    Unique_BIGNUM h(hRef);

    int ret = EC_GROUP_set_generator(group, point, n.get(), h.get());
    if (ret == 0) {
        throwExceptionIfNecessary(env, "EC_GROUP_set_generator");
    }

    JNI_TRACE("EC_GROUP_set_generator(%p, %p, %p, %p) => %d", group, point, njavaBytes, hjavaBytes, ret);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EC_1GROUP_1get_1generator(JNIEnv* env, jclass, jlong groupRef)
{
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    JNI_TRACE("EC_GROUP_get_generator(%p)", group);

    if (group == NULL) {
        JNI_TRACE("EC_POINT_get_generator(%p) => group == null", group);
        jniThrowNullPointerException(env, "group == null");
        return 0;
    }

    const EC_POINT* generator = EC_GROUP_get0_generator(group);

    Unique_EC_POINT dup(EC_POINT_dup(generator, group));
    if (dup.get() == NULL) {
        JNI_TRACE("EC_GROUP_get_generator(%p) => oom error", group);
        jniThrowOutOfMemory(env, "unable to dupe generator");
        return 0;
    }

    JNI_TRACE("EC_GROUP_get_generator(%p) => %p", group, dup.get());
    return reinterpret_cast<uintptr_t>(dup.release());
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EC_1POINT_1new(JNIEnv* env, jclass, jlong groupRef)
{
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    JNI_TRACE("EC_POINT_new(%p)", group);

    if (group == NULL) {
        JNI_TRACE("EC_POINT_new(%p) => group == null", group);
        jniThrowNullPointerException(env, "group == null");
        return 0;
    }

    EC_POINT* point = EC_POINT_new(group);
    if (point == NULL) {
        jniThrowOutOfMemory(env, "Unable create an EC_POINT");
        return 0;
    }

    return reinterpret_cast<uintptr_t>(point);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EC_1POINT_1clear_1free(JNIEnv* env, jclass, jlong groupRef) {
    EC_POINT* group = reinterpret_cast<EC_POINT*>(groupRef);
    JNI_TRACE("EC_POINT_clear_free(%p)", group);

    if (group == NULL) {
        JNI_TRACE("EC_POINT_clear_free => group == NULL");
        jniThrowNullPointerException(env, "group == NULL");
        return;
    }

    EC_POINT_clear_free(group);
    JNI_TRACE("EC_POINT_clear_free(%p) => success", group);
}

extern "C" jboolean Java_com_android_org_conscrypt_NativeCrypto_EC_1POINT_1cmp(JNIEnv* env, jclass, jlong groupRef, jlong point1Ref, jlong point2Ref)
{
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    const EC_POINT* point1 = reinterpret_cast<const EC_POINT*>(point1Ref);
    const EC_POINT* point2 = reinterpret_cast<const EC_POINT*>(point2Ref);
    JNI_TRACE("EC_POINT_cmp(%p, %p, %p)", group, point1, point2);

    if (group == NULL || point1 == NULL || point2 == NULL) {
        JNI_TRACE("EC_POINT_cmp(%p, %p, %p) => group == null || point1 == null || point2 == null",
                group, point1, point2);
        jniThrowNullPointerException(env, "group == null || point1 == null || point2 == null");
        return false;
    }

    int ret = EC_POINT_cmp(group, point1, point2, (BN_CTX*)NULL);

    JNI_TRACE("ECP_GROUP_cmp(%p, %p) => %d", point1, point2, ret);
    return ret == 0;
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EC_1POINT_1set_1affine_1coordinates(JNIEnv* env, jclass,
        jlong groupRef, jlong pointRef, jbyteArray xjavaBytes, jbyteArray yjavaBytes)
{
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    EC_POINT* point = reinterpret_cast<EC_POINT*>(pointRef);
    JNI_TRACE("EC_POINT_set_affine_coordinates(%p, %p, %p, %p)", group, point, xjavaBytes,
            yjavaBytes);

    if (group == NULL || point == NULL) {
        JNI_TRACE("EC_POINT_set_affine_coordinates(%p, %p, %p, %p) => group == null || point == null",
                group, point, xjavaBytes, yjavaBytes);
        jniThrowNullPointerException(env, "group == null || point == null");
        return;
    }

    BIGNUM* xRef;
    if (!arrayToBignum(env, xjavaBytes, &xRef)) {
        return;
    }
    Unique_BIGNUM x(xRef);

    BIGNUM* yRef;
    if (!arrayToBignum(env, yjavaBytes, &yRef)) {
        return;
    }
    Unique_BIGNUM y(yRef);

    int ret;
    switch (get_EC_GROUP_type(group)) {
    case EC_CURVE_GFP:
        ret = EC_POINT_set_affine_coordinates_GFp(group, point, x.get(), y.get(), NULL);
        break;
    case EC_CURVE_GF2M:
        ret = EC_POINT_set_affine_coordinates_GF2m(group, point, x.get(), y.get(), NULL);
        break;
    default:
        jniThrowRuntimeException(env, "invalid curve type");
        return;
    }

    if (ret != 1) {
        throwExceptionIfNecessary(env, "EC_POINT_set_affine_coordinates");
    }

    JNI_TRACE("EC_POINT_set_affine_coordinates(%p, %p, %p, %p) => %d", group, point,
            xjavaBytes, yjavaBytes, ret);
}

extern "C" jobjectArray Java_com_android_org_conscrypt_NativeCrypto_EC_1POINT_1get_1affine_1coordinates(JNIEnv* env, jclass, jlong groupRef,
        jlong pointRef)
{
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    const EC_POINT* point = reinterpret_cast<const EC_POINT*>(pointRef);
    JNI_TRACE("EC_POINT_get_affine_coordinates(%p, %p)", group, point);

    Unique_BIGNUM x(BN_new());
    Unique_BIGNUM y(BN_new());

    int ret;
    switch (get_EC_GROUP_type(group)) {
    case EC_CURVE_GFP:
        ret = EC_POINT_get_affine_coordinates_GFp(group, point, x.get(), y.get(), NULL);
        break;
    case EC_CURVE_GF2M:
        ret = EC_POINT_get_affine_coordinates_GF2m(group, point, x.get(), y.get(), NULL);
        break;
    default:
        jniThrowRuntimeException(env, "invalid curve type");
        return NULL;
    }
    if (ret != 1) {
        JNI_TRACE("EC_POINT_get_affine_coordinates(%p, %p)", group, point);
        throwExceptionIfNecessary(env, "EC_POINT_get_affine_coordinates");
        return NULL;
    }

    jobjectArray joa = env->NewObjectArray(2, byteArrayClass, NULL);
    if (joa == NULL) {
        return NULL;
    }

    jbyteArray xBytes = bignumToArray(env, x.get(), "x");
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 0, xBytes);

    jbyteArray yBytes = bignumToArray(env, y.get(), "y");
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 1, yBytes);

    JNI_TRACE("EC_POINT_get_affine_coordinates(%p, %p) => %p", group, point, joa);
    return joa;
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EC_1KEY_1generate_1key(JNIEnv* env, jclass, jlong groupRef)
{
    const EC_GROUP* group = reinterpret_cast<const EC_GROUP*>(groupRef);
    JNI_TRACE("EC_KEY_generate_key(%p)", group);

    Unique_EC_KEY eckey(EC_KEY_new());
    if (eckey.get() == NULL) {
        JNI_TRACE("EC_KEY_generate_key(%p) => EC_KEY_new() oom", group);
        jniThrowOutOfMemory(env, "Unable to create an EC_KEY");
        return 0;
    }

    if (EC_KEY_set_group(eckey.get(), group) != 1) {
        JNI_TRACE("EC_KEY_generate_key(%p) => EC_KEY_set_group error", group);
        throwExceptionIfNecessary(env, "EC_KEY_set_group");
        return 0;
    }

    if (EC_KEY_generate_key(eckey.get()) != 1) {
        JNI_TRACE("EC_KEY_generate_key(%p) => EC_KEY_generate_key error", group);
        throwExceptionIfNecessary(env, "EC_KEY_set_group");
        return 0;
    }

    Unique_EVP_PKEY pkey(EVP_PKEY_new());
    if (pkey.get() == NULL) {
        JNI_TRACE("EC_KEY_generate_key(%p) => threw error", group);
        throwExceptionIfNecessary(env, "EC_KEY_generate_key");
        return 0;
    }
    if (EVP_PKEY_assign_EC_KEY(pkey.get(), eckey.get()) != 1) {
        jniThrowRuntimeException(env, "EVP_PKEY_assign_EC_KEY failed");
        return 0;
    }
    OWNERSHIP_TRANSFERRED(eckey);

    JNI_TRACE("EC_KEY_generate_key(%p) => %p", group, pkey.get());
    return reinterpret_cast<uintptr_t>(pkey.release());
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EC_1KEY_1get0_1group(JNIEnv* env, jclass, jlong pkeyRef)
{
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("EC_KEY_get0_group(%p)", pkey);

    if (pkey == NULL) {
        jniThrowNullPointerException(env, "pkey == null");
        JNI_TRACE("EC_KEY_get0_group(%p) => pkey == null", pkey);
        return 0;
    }

    if (EVP_PKEY_type(pkey->type) != EVP_PKEY_EC) {
        jniThrowRuntimeException(env, "not EC key");
        JNI_TRACE("EC_KEY_get0_group(%p) => not EC key (type == %d)", pkey,
                EVP_PKEY_type(pkey->type));
        return 0;
    }

    const EC_GROUP* group = EC_KEY_get0_group(pkey->pkey.ec);
    JNI_TRACE("EC_KEY_get0_group(%p) => %p", pkey, group);
    return reinterpret_cast<uintptr_t>(group);
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_EC_1KEY_1get_1private_1key(JNIEnv* env, jclass, jlong pkeyRef)
{
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("EC_KEY_get_private_key(%p)", pkey);

    Unique_EC_KEY eckey(EVP_PKEY_get1_EC_KEY(pkey));
    if (eckey.get() == NULL) {
        throwExceptionIfNecessary(env, "EVP_PKEY_get1_EC_KEY");
        return NULL;
    }

    const BIGNUM *privkey = EC_KEY_get0_private_key(eckey.get());

    jbyteArray privBytes = bignumToArray(env, privkey, "privkey");
    if (env->ExceptionCheck()) {
        JNI_TRACE("EC_KEY_get_private_key(%p) => threw error", pkey);
        return NULL;
    }

    JNI_TRACE("EC_KEY_get_private_key(%p) => %p", pkey, privBytes);
    return privBytes;
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EC_1KEY_1get_1public_1key(JNIEnv* env, jclass, jlong pkeyRef)
{
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("EC_KEY_get_public_key(%p)", pkey);

    Unique_EC_KEY eckey(EVP_PKEY_get1_EC_KEY(pkey));
    if (eckey.get() == NULL) {
        throwExceptionIfNecessary(env, "EVP_PKEY_get1_EC_KEY");
        return 0;
    }

    Unique_EC_POINT dup(EC_POINT_dup(EC_KEY_get0_public_key(eckey.get()),
            EC_KEY_get0_group(eckey.get())));
    if (dup.get() == NULL) {
        JNI_TRACE("EC_KEY_get_public_key(%p) => can't dup public key", pkey);
        jniThrowRuntimeException(env, "EC_POINT_dup");
        return 0;
    }

    JNI_TRACE("EC_KEY_get_public_key(%p) => %p", pkey, dup.get());
    return reinterpret_cast<uintptr_t>(dup.release());
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_ECDH_1compute_1key(JNIEnv* env, jclass,
     jbyteArray outArray, jint outOffset, jlong pubkeyRef, jlong privkeyRef)
{
    EVP_PKEY* pubPkey = reinterpret_cast<EVP_PKEY*>(pubkeyRef);
    EVP_PKEY* privPkey = reinterpret_cast<EVP_PKEY*>(privkeyRef);
    JNI_TRACE("ECDH_compute_key(%p, %d, %p, %p)", outArray, outOffset, pubPkey, privPkey);

    ScopedByteArrayRW out(env, outArray);
    if (out.get() == NULL) {
        JNI_TRACE("ECDH_compute_key(%p, %d, %p, %p) can't get output buffer",
                outArray, outOffset, pubPkey, privPkey);
        return -1;
    }

    if ((outOffset < 0) || ((size_t) outOffset >= out.size())) {
        jniThrowException(env, "java/lang/ArrayIndexOutOfBoundsException", NULL);
        return -1;
    }

    Unique_EC_KEY pubkey(EVP_PKEY_get1_EC_KEY(pubPkey));
    if (pubkey.get() == NULL) {
        JNI_TRACE("ECDH_compute_key(%p) => can't get public key", pubPkey);
        throwExceptionIfNecessary(env, "EVP_PKEY_get1_EC_KEY public");
        return -1;
    }

    const EC_POINT* pubkeyPoint = EC_KEY_get0_public_key(pubkey.get());
    if (pubkeyPoint == NULL) {
        JNI_TRACE("ECDH_compute_key(%p) => can't get public key point", pubPkey);
        throwExceptionIfNecessary(env, "EVP_PKEY_get1_EC_KEY public");
        return -1;
    }

    Unique_EC_KEY privkey(EVP_PKEY_get1_EC_KEY(privPkey));
    if (privkey.get() == NULL) {
        throwExceptionIfNecessary(env, "EVP_PKEY_get1_EC_KEY private");
        return -1;
    }

    int outputLength = ECDH_compute_key(
            &out[outOffset],
            out.size() - outOffset,
            pubkeyPoint,
            privkey.get(),
            NULL // No KDF
            );
    if (outputLength == -1) {
        throwExceptionIfNecessary(env, "ECDH_compute_key");
        return -1;
    }

    return outputLength;
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EVP_1MD_1CTX_1create(JNIEnv* env, jclass) {
    JNI_TRACE("EVP_MD_CTX_create()");

    Unique_EVP_MD_CTX ctx(EVP_MD_CTX_create());
    if (ctx.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable create a EVP_MD_CTX");
        return 0;
    }

    JNI_TRACE("EVP_MD_CTX_create() => %p", ctx.get());
    return reinterpret_cast<uintptr_t>(ctx.release());
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EVP_1MD_1CTX_1init(JNIEnv*, jclass, jlong ctxRef) {
    EVP_MD_CTX* ctx = reinterpret_cast<EVP_MD_CTX*>(ctxRef);
    JNI_TRACE("NativeCrypto_EVP_MD_CTX_init(%p)", ctx);

    if (ctx != NULL) {
        EVP_MD_CTX_init(ctx);
    }
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EVP_1MD_1CTX_1destroy(JNIEnv*, jclass, jlong ctxRef) {
    EVP_MD_CTX* ctx = reinterpret_cast<EVP_MD_CTX*>(ctxRef);
    JNI_TRACE("NativeCrypto_EVP_MD_CTX_destroy(%p)", ctx);

    if (ctx != NULL) {
        EVP_MD_CTX_destroy(ctx);
    }
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EVP_1MD_1CTX_1copy(JNIEnv* env, jclass, jlong ctxRef) {
    EVP_MD_CTX* ctx = reinterpret_cast<EVP_MD_CTX*>(ctxRef);
    JNI_TRACE("NativeCrypto_EVP_MD_CTX_copy(%p)", ctx);

    if (ctx == NULL) {
        jniThrowNullPointerException(env, "ctx == null");
        return 0;
    }

    EVP_MD_CTX* copy = EVP_MD_CTX_create();
    if (copy == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate copy of EVP_MD_CTX");
        return 0;
    }

    EVP_MD_CTX_init(copy);
    int result = EVP_MD_CTX_copy_ex(copy, ctx);
    if (result == 0) {
        EVP_MD_CTX_destroy(copy);
        jniThrowRuntimeException(env, "Unable to copy EVP_MD_CTX");
        freeOpenSslErrorState();
        return 0;
    }

    JNI_TRACE("NativeCrypto_EVP_MD_CTX_copy(%p) => %p", ctx, copy);
    return reinterpret_cast<uintptr_t>(copy);
}

/*
 * public static native int EVP_DigestFinal(int, byte[], int)
 */
extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_EVP_1DigestFinal(JNIEnv* env, jclass, jlong ctxRef,
                                         jbyteArray hash, jint offset) {
    EVP_MD_CTX* ctx = reinterpret_cast<EVP_MD_CTX*>(ctxRef);
    JNI_TRACE("NativeCrypto_EVP_DigestFinal(%p, %p, %d)", ctx, hash, offset);

    if (ctx == NULL || hash == NULL) {
        jniThrowNullPointerException(env, "ctx == null || hash == null");
        return -1;
    }

    ScopedByteArrayRW hashBytes(env, hash);
    if (hashBytes.get() == NULL) {
        return -1;
    }
    unsigned int bytesWritten = -1;
    int ok = EVP_DigestFinal(ctx,
                             reinterpret_cast<unsigned char*>(hashBytes.get() + offset),
                             &bytesWritten);
    if (ok == 0) {
        throwExceptionIfNecessary(env, "NativeCrypto_EVP_DigestFinal");
    }
    EVP_MD_CTX_destroy(ctx);

    JNI_TRACE("NativeCrypto_EVP_DigestFinal(%p, %p, %d) => %d", ctx, hash, offset, bytesWritten);
    return bytesWritten;
}

/*
 * public static native int EVP_DigestInit(int)
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EVP_1DigestInit(JNIEnv* env, jclass, jlong evpMdRef) {
    EVP_MD* evp_md = reinterpret_cast<EVP_MD*>(evpMdRef);
    JNI_TRACE("NativeCrypto_EVP_DigestInit(%p)", evp_md);

    if (evp_md == NULL) {
        jniThrowNullPointerException(env, NULL);
        return 0;
    }

    Unique_EVP_MD_CTX ctx(EVP_MD_CTX_create());
    if (ctx.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate EVP_MD_CTX");
        return 0;
    }
    JNI_TRACE("NativeCrypto_EVP_DigestInit ctx=%p", ctx.get());

    int ok = EVP_DigestInit(ctx.get(), evp_md);
    if (ok == 0) {
        bool exception = throwExceptionIfNecessary(env, "NativeCrypto_EVP_DigestInit");
        if (exception) {
            return 0;
        }
    }
    return reinterpret_cast<uintptr_t>(ctx.release());
}

/*
 * public static native int EVP_get_digestbyname(java.lang.String)
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EVP_1get_1digestbyname(JNIEnv* env, jclass, jstring algorithm) {
    JNI_TRACE("NativeCrypto_EVP_get_digestbyname(%p)", algorithm);

    if (algorithm == NULL) {
        jniThrowNullPointerException(env, NULL);
        return -1;
    }

    ScopedUtfChars algorithmChars(env, algorithm);
    if (algorithmChars.c_str() == NULL) {
        return 0;
    }
    JNI_TRACE("NativeCrypto_EVP_get_digestbyname(%s)", algorithmChars.c_str());

    const EVP_MD* evp_md = EVP_get_digestbyname(algorithmChars.c_str());
    if (evp_md == NULL) {
        jniThrowRuntimeException(env, "Hash algorithm not found");
        return 0;
    }

    JNI_TRACE("NativeCrypto_EVP_get_digestbyname(%s) => %p", algorithmChars.c_str(), evp_md);
    return reinterpret_cast<uintptr_t>(evp_md);
}

/*
 * public static native int EVP_MD_size(int)
 */
extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_EVP_1MD_1size(JNIEnv* env, jclass, jint evpMdRef) {
    EVP_MD* evp_md = reinterpret_cast<EVP_MD*>(evpMdRef);
    JNI_TRACE("NativeCrypto_EVP_MD_size(%p)", evp_md);

    if (evp_md == NULL) {
        jniThrowNullPointerException(env, NULL);
        return -1;
    }

    int result = EVP_MD_size(evp_md);
    JNI_TRACE("NativeCrypto_EVP_MD_size(%p) => %d", evp_md, result);
    return result;
}

/*
 * public static int void EVP_MD_block_size(int)
 */
extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_EVP_1MD_1block_1size(JNIEnv* env, jclass, jlong evpMdRef) {
    EVP_MD* evp_md = reinterpret_cast<EVP_MD*>(evpMdRef);
    JNI_TRACE("NativeCrypto_EVP_MD_block_size(%p)", evp_md);

    if (evp_md == NULL) {
        jniThrowNullPointerException(env, NULL);
        return -1;
    }

    int result = EVP_MD_block_size(evp_md);
    JNI_TRACE("NativeCrypto_EVP_MD_block_size(%p) => %d", evp_md, result);
    return result;
}

/*
 * public static native void EVP_DigestUpdate(int, byte[], int, int)
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EVP_1DigestUpdate(JNIEnv* env, jclass, jlong ctxRef,
                                          jbyteArray buffer, jint offset, jint length) {
    EVP_MD_CTX* ctx = reinterpret_cast<EVP_MD_CTX*>(ctxRef);
    JNI_TRACE("NativeCrypto_EVP_DigestUpdate(%p, %p, %d, %d)", ctx, buffer, offset, length);

    if (offset < 0 || length < 0) {
        jniThrowException(env, "java/lang/IndexOutOfBoundsException", NULL);
        return;
    }

    if (ctx == NULL || buffer == NULL) {
        jniThrowNullPointerException(env, NULL);
        return;
    }

    ScopedByteArrayRO bufferBytes(env, buffer);
    if (bufferBytes.get() == NULL) {
        return;
    }
    int ok = EVP_DigestUpdate(ctx,
                              reinterpret_cast<const unsigned char*>(bufferBytes.get() + offset),
                              length);
    if (ok == 0) {
        throwExceptionIfNecessary(env, "NativeCrypto_EVP_DigestUpdate");
    }
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EVP_1DigestSignInit(JNIEnv* env, jclass, jlong evpMdCtxRef,
        const jlong evpMdRef, jlong pkeyRef) {
    EVP_MD_CTX* mdCtx = reinterpret_cast<EVP_MD_CTX*>(evpMdCtxRef);
    const EVP_MD* md = reinterpret_cast<const EVP_MD*>(evpMdRef);
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("EVP_DigestSignInit(%p, %p, %p)", mdCtx, md, pkey);

    if (mdCtx == NULL) {
         jniThrowNullPointerException(env, "mdCtx == null");
         return;
    }

    if (md == NULL) {
         jniThrowNullPointerException(env, "md == null");
         return;
    }

    if (pkey == NULL) {
         jniThrowNullPointerException(env, "pkey == null");
         return;
    }

    if (EVP_DigestSignInit(mdCtx, (EVP_PKEY_CTX **) NULL, md, (ENGINE *) NULL, pkey) <= 0) {
        JNI_TRACE("ctx=%p EVP_DigestSignInit => threw exception", mdCtx);
        throwExceptionIfNecessary(env, "EVP_DigestSignInit");
        return;
    }

    JNI_TRACE("EVP_DigestSignInit(%p, %p, %p) => success", mdCtx, md, pkey);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EVP_1DigestSignUpdate(JNIEnv* env, jclass, jint evpMdCtxRef,
        jbyteArray inJavaBytes, jint inOffset, jint inLength)
{
    EVP_MD_CTX* mdCtx = reinterpret_cast<EVP_MD_CTX*>(evpMdCtxRef);
    JNI_TRACE("EVP_DigestSignUpdate(%p, %p, %d, %d)", mdCtx, inJavaBytes, inOffset, inLength);

    if (mdCtx == NULL) {
         jniThrowNullPointerException(env, "mdCtx == null");
         return;
    }

    ScopedByteArrayRO inBytes(env, inJavaBytes);
    if (inBytes.get() == NULL) {
        return;
    }

    if (inOffset < 0 || size_t(inOffset) > inBytes.size()) {
        jniThrowException(env, "java/lang/IndexOutOfBoundsException", "inOffset");
        return;
    }

    const ssize_t inEnd = inOffset + inLength;
    if (inEnd < 0 || size_t(inEnd) >= inBytes.size()) {
        jniThrowException(env, "java/lang/IndexOutOfBoundsException", "inLength");
        return;
    }

    const unsigned char *tmp = reinterpret_cast<const unsigned char *>(inBytes.get());
    if (!EVP_DigestSignUpdate(mdCtx, tmp + inOffset, inLength)) {
        JNI_TRACE("ctx=%p EVP_DigestSignUpdate => threw exception", mdCtx);
        throwExceptionIfNecessary(env, "EVP_DigestSignUpdate");
    }

    JNI_TRACE("EVP_DigestSignUpdate(%p, %p, %d, %d) => success", mdCtx, inJavaBytes, inOffset,
            inLength);
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_EVP_1DigestSignFinal(JNIEnv* env, jclass, jlong evpMdCtxRef)
{
    EVP_MD_CTX* mdCtx = reinterpret_cast<EVP_MD_CTX*>(evpMdCtxRef);
    JNI_TRACE("EVP_DigestSignFinal(%p)", mdCtx);

    if (mdCtx == NULL) {
         jniThrowNullPointerException(env, "mdCtx == null");
         return NULL;
    }

    const size_t expectedSize = EVP_MD_CTX_size(mdCtx);
    ScopedLocalRef<jbyteArray> outJavaBytes(env, env->NewByteArray(expectedSize));
    if (outJavaBytes.get() == NULL) {
        return NULL;
    }
    ScopedByteArrayRW outBytes(env, outJavaBytes.get());
    if (outBytes.get() == NULL) {
        return NULL;
    }
    unsigned char *tmp = reinterpret_cast<unsigned char*>(outBytes.get());
    size_t len;
    if (!EVP_DigestSignFinal(mdCtx, tmp, &len)) {
        JNI_TRACE("ctx=%p EVP_DigestSignFinal => threw exception", mdCtx);
        throwExceptionIfNecessary(env, "EVP_DigestSignFinal");
        return 0;
    }

    if (len != expectedSize) {
        jniThrowRuntimeException(env, "hash size unexpected");
        return 0;
    }

    JNI_TRACE("EVP_DigestSignFinal(%p) => %p", mdCtx, outJavaBytes.get());
    return outJavaBytes.release();
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EVP_1SignInit(JNIEnv* env, jclass, jstring algorithm) {
    JNI_TRACE("NativeCrypto_EVP_SignInit(%p)", algorithm);

    if (algorithm == NULL) {
        jniThrowNullPointerException(env, NULL);
        return 0;
    }

    Unique_EVP_MD_CTX ctx(EVP_MD_CTX_create());
    if (ctx.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate EVP_MD_CTX");
        return 0;
    }
    JNI_TRACE("NativeCrypto_EVP_SignInit ctx=%p", ctx.get());

    ScopedUtfChars algorithmChars(env, algorithm);
    if (algorithmChars.c_str() == NULL) {
        return 0;
    }
    JNI_TRACE("NativeCrypto_EVP_SignInit algorithmChars=%s", algorithmChars.c_str());

    const EVP_MD* digest = EVP_get_digestbynid(OBJ_txt2nid(algorithmChars.c_str()));
    if (digest == NULL) {
        JNI_TRACE("NativeCrypto_EVP_SignInit(%s) => hash not found", algorithmChars.c_str());
        throwExceptionIfNecessary(env, "Hash algorithm not found");
        return 0;
    }

    int ok = EVP_SignInit(ctx.get(), digest);
    if (ok == 0) {
        bool exception = throwExceptionIfNecessary(env, "NativeCrypto_EVP_SignInit");
        if (exception) {
            JNI_TRACE("NativeCrypto_EVP_SignInit(%s) => threw exception", algorithmChars.c_str());
            return 0;
        }
    }

    JNI_TRACE("NativeCrypto_EVP_SignInit(%s) => %p", algorithmChars.c_str(), ctx.get());
    return reinterpret_cast<uintptr_t>(ctx.release());
}

/*
 * public static native void EVP_SignUpdate(int, byte[], int, int)
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EVP_1SignUpdate(JNIEnv* env, jclass, jlong ctxRef,
                                          jbyteArray buffer, jint offset, jint length) {
    EVP_MD_CTX* ctx = reinterpret_cast<EVP_MD_CTX*>(ctxRef);
    JNI_TRACE("NativeCrypto_EVP_SignUpdate(%p, %p, %d, %d)", ctx, buffer, offset, length);

    if (ctx == NULL || buffer == NULL) {
        jniThrowNullPointerException(env, NULL);
        return;
    }

    ScopedByteArrayRO bufferBytes(env, buffer);
    if (bufferBytes.get() == NULL) {
        return;
    }
    int ok = EVP_SignUpdate(ctx,
                            reinterpret_cast<const unsigned char*>(bufferBytes.get() + offset),
                            length);
    if (ok == 0) {
        throwExceptionIfNecessary(env, "NativeCrypto_EVP_SignUpdate");
    }
}

/*
 * public static native int EVP_SignFinal(int, byte[], int, int)
 */
extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_EVP_1SignFinal(JNIEnv* env, jclass, jlong ctxRef, jbyteArray signature,
        jint offset, jlong pkeyRef) {
    EVP_MD_CTX* ctx = reinterpret_cast<EVP_MD_CTX*>(ctxRef);
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("NativeCrypto_EVP_SignFinal(%p, %p, %d, %p)", ctx, signature, offset, pkey);

    if (ctx == NULL || pkey == NULL) {
        jniThrowNullPointerException(env, NULL);
        return -1;
    }

    ScopedByteArrayRW signatureBytes(env, signature);
    if (signatureBytes.get() == NULL) {
        return -1;
    }
    unsigned int bytesWritten = -1;
    int ok = EVP_SignFinal(ctx,
                           reinterpret_cast<unsigned char*>(signatureBytes.get() + offset),
                           &bytesWritten,
                           pkey);
    if (ok == 0) {
        throwExceptionIfNecessary(env, "NativeCrypto_EVP_SignFinal");
    }
    JNI_TRACE("NativeCrypto_EVP_SignFinal(%p, %p, %d, %p) => %u",
              ctx, signature, offset, pkey, bytesWritten);

    return bytesWritten;
}

/*
 * public static native int EVP_VerifyInit(java.lang.String)
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EVP_1VerifyInit(JNIEnv* env, jclass, jstring algorithm) {
    JNI_TRACE("NativeCrypto_EVP_VerifyInit(%p)", algorithm);

    if (algorithm == NULL) {
        jniThrowNullPointerException(env, NULL);
        return 0;
    }

    Unique_EVP_MD_CTX ctx(EVP_MD_CTX_create());
    if (ctx.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate EVP_MD_CTX");
        return 0;
    }
    JNI_TRACE("NativeCrypto_EVP_VerifyInit ctx=%p", ctx.get());

    ScopedUtfChars algorithmChars(env, algorithm);
    if (algorithmChars.c_str() == NULL) {
        return 0;
    }
    JNI_TRACE("NativeCrypto_EVP_VerifyInit algorithmChars=%s", algorithmChars.c_str());

    const EVP_MD* digest = EVP_get_digestbynid(OBJ_txt2nid(algorithmChars.c_str()));
    if (digest == NULL) {
        jniThrowRuntimeException(env, "Hash algorithm not found");
        return 0;
    }

    int ok = EVP_VerifyInit(ctx.get(), digest);
    if (ok == 0) {
        bool exception = throwExceptionIfNecessary(env, "NativeCrypto_EVP_VerifyInit");
        if (exception) {
            return 0;
        }
    }
    return reinterpret_cast<uintptr_t>(ctx.release());
}

/*
 * public static native void EVP_VerifyUpdate(int, byte[], int, int)
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EVP_1VerifyUpdate(JNIEnv* env, jclass, jlong ctxRef,
                                          jbyteArray buffer, jint offset, jint length) {
    EVP_MD_CTX* ctx = reinterpret_cast<EVP_MD_CTX*>(ctxRef);
    JNI_TRACE("NativeCrypto_EVP_VerifyUpdate(%p, %p, %d, %d)", ctx, buffer, offset, length);

    if (ctx == NULL || buffer == NULL) {
        jniThrowNullPointerException(env, NULL);
        return;
    }

    ScopedByteArrayRO bufferBytes(env, buffer);
    if (bufferBytes.get() == NULL) {
        return;
    }
    int ok = EVP_VerifyUpdate(ctx,
                              reinterpret_cast<const unsigned char*>(bufferBytes.get() + offset),
                              length);
    if (ok == 0) {
        throwExceptionIfNecessary(env, "NativeCrypto_EVP_VerifyUpdate");
    }
}

/*
 * public static native int EVP_VerifyFinal(int, byte[], int, int, int)
 */
extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_EVP_1VerifyFinal(JNIEnv* env, jclass, jlong ctxRef, jbyteArray buffer,
                                        jint offset, jint length, jlong pkeyRef) {
    EVP_MD_CTX* ctx = reinterpret_cast<EVP_MD_CTX*>(ctxRef);
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("NativeCrypto_EVP_VerifyFinal(%p, %p, %d, %d, %p)",
              ctx, buffer, offset, length, pkey);

    if (ctx == NULL || buffer == NULL || pkey == NULL) {
        jniThrowNullPointerException(env, NULL);
        return -1;
    }

    ScopedByteArrayRO bufferBytes(env, buffer);
    if (bufferBytes.get() == NULL) {
        return -1;
    }
    int ok = EVP_VerifyFinal(ctx,
                             reinterpret_cast<const unsigned char*>(bufferBytes.get() + offset),
                             length,
                             pkey);
    if (ok < 0) {
        throwExceptionIfNecessary(env, "NativeCrypto_EVP_VerifyFinal");
    }

    /*
     * For DSA keys, OpenSSL appears to have a bug where it returns
     * errors for any result != 1. See dsa_ossl.c in dsa_do_verify
     */
    freeOpenSslErrorState();

    JNI_TRACE("NativeCrypto_EVP_VerifyFinal(%p, %p, %d, %d, %p) => %d",
              ctx, buffer, offset, length, pkey, ok);

    return ok;
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EVP_1get_1cipherbyname(JNIEnv* env, jclass, jstring algorithm) {
    JNI_TRACE("EVP_get_cipherbyname(%p)", algorithm);
    if (algorithm == NULL) {
        JNI_TRACE("EVP_get_cipherbyname(%p) => threw exception algorithm == null", algorithm);
        jniThrowNullPointerException(env, NULL);
        return -1;
    }

    ScopedUtfChars algorithmChars(env, algorithm);
    if (algorithmChars.c_str() == NULL) {
        return 0;
    }
    JNI_TRACE("EVP_get_cipherbyname(%p) => algorithm = %s", algorithm, algorithmChars.c_str());

    const EVP_CIPHER* evp_cipher = EVP_get_cipherbyname(algorithmChars.c_str());
    if (evp_cipher == NULL) {
        freeOpenSslErrorState();
    }

    JNI_TRACE("EVP_get_cipherbyname(%s) => %p", algorithmChars.c_str(), evp_cipher);
    return reinterpret_cast<uintptr_t>(evp_cipher);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EVP_1CipherInit_1ex(JNIEnv* env, jclass, jlong ctxRef, jlong evpCipherRef,
        jbyteArray keyArray, jbyteArray ivArray, jboolean encrypting) {
    EVP_CIPHER_CTX* ctx = reinterpret_cast<EVP_CIPHER_CTX*>(ctxRef);
    const EVP_CIPHER* evpCipher = reinterpret_cast<const EVP_CIPHER*>(evpCipherRef);
    JNI_TRACE("EVP_CipherInit_ex(%p, %p, %p, %p, %d)", ctx, evpCipher, keyArray, ivArray,
            encrypting ? 1 : 0);

    if (ctx == NULL) {
        jniThrowNullPointerException(env, "ctx == null");
        JNI_TRACE("EVP_CipherUpdate => ctx == null");
        return;
    }

    // The key can be null if we need to set extra parameters.
    UniquePtr<unsigned char[]> keyPtr;
    if (keyArray != NULL) {
        ScopedByteArrayRO keyBytes(env, keyArray);
        if (keyBytes.get() == NULL) {
            return;
        }

        keyPtr.reset(new unsigned char[keyBytes.size()]);
        memcpy(keyPtr.get(), keyBytes.get(), keyBytes.size());
    }

    // The IV can be null if we're using ECB.
    UniquePtr<unsigned char[]> ivPtr;
    if (ivArray != NULL) {
        ScopedByteArrayRO ivBytes(env, ivArray);
        if (ivBytes.get() == NULL) {
            return;
        }

        ivPtr.reset(new unsigned char[ivBytes.size()]);
        memcpy(ivPtr.get(), ivBytes.get(), ivBytes.size());
    }

    if (!EVP_CipherInit_ex(ctx, evpCipher, NULL, keyPtr.get(), ivPtr.get(), encrypting ? 1 : 0)) {
        throwExceptionIfNecessary(env, "EVP_CipherInit_ex");
        JNI_TRACE("EVP_CipherInit_ex => error initializing cipher");
        return;
    }

    JNI_TRACE("EVP_CipherInit_ex(%p, %p, %p, %p, %d) => success", ctx, evpCipher, keyArray, ivArray,
            encrypting ? 1 : 0);
}

/*
 *  public static native int EVP_CipherUpdate(int ctx, byte[] out, int outOffset, byte[] in,
 *          int inOffset);
 */
extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_EVP_1CipherUpdate(JNIEnv* env, jclass, jlong ctxRef, jbyteArray outArray,
        jint outOffset, jbyteArray inArray, jint inOffset, jint inLength) {
    EVP_CIPHER_CTX* ctx = reinterpret_cast<EVP_CIPHER_CTX*>(ctxRef);
    JNI_TRACE("EVP_CipherUpdate(%p, %p, %d, %p, %d)", ctx, outArray, outOffset, inArray, inOffset);

    if (ctx == NULL) {
        jniThrowNullPointerException(env, "ctx == null");
        JNI_TRACE("ctx=%p EVP_CipherUpdate => ctx == null", ctx);
        return 0;
    }

    ScopedByteArrayRO inBytes(env, inArray);
    if (inBytes.get() == NULL) {
        return 0;
    }
    const size_t inSize = inBytes.size();
    if (size_t(inOffset + inLength) > inSize) {
        jniThrowException(env, "java/lang/IndexOutOfBoundsException",
                "in.length < (inSize + inOffset)");
        return 0;
    }

    ScopedByteArrayRW outBytes(env, outArray);
    if (outBytes.get() == NULL) {
        return 0;
    }
    const size_t outSize = outBytes.size();
    if (size_t(outOffset + inLength) > outSize) {
        jniThrowException(env, "java/lang/IndexOutOfBoundsException",
                "out.length < inSize + outOffset + blockSize - 1");
        return 0;
    }

    JNI_TRACE("ctx=%p EVP_CipherUpdate in=%p in.length=%d inOffset=%d inLength=%d out=%p out.length=%d outOffset=%d",
            ctx, inBytes.get(), inBytes.size(), inOffset, inLength, outBytes.get(), outBytes.size(), outOffset);

    unsigned char* out = reinterpret_cast<unsigned char*>(outBytes.get());
    const unsigned char* in = reinterpret_cast<const unsigned char*>(inBytes.get());

    int outl;
    if (!EVP_CipherUpdate(ctx, out + outOffset, &outl, in + inOffset, inLength)) {
        throwExceptionIfNecessary(env, "EVP_CipherUpdate");
        JNI_TRACE("ctx=%p EVP_CipherUpdate => threw error", ctx);
        return 0;
    }

    JNI_TRACE("EVP_CipherUpdate(%p, %p, %d, %p, %d) => %d", ctx, outArray, outOffset, inArray,
            inOffset, outl);
    return outl;
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_EVP_1CipherFinal_1ex(JNIEnv* env, jclass, jlong ctxRef, jbyteArray outArray,
        jint outOffset) {
    EVP_CIPHER_CTX* ctx = reinterpret_cast<EVP_CIPHER_CTX*>(ctxRef);
    JNI_TRACE("EVP_CipherFinal_ex(%p, %p, %d)", ctx, outArray, outOffset);

    if (ctx == NULL) {
        jniThrowNullPointerException(env, "ctx == null");
        JNI_TRACE("ctx=%p EVP_CipherFinal_ex => ctx == null", ctx);
        return 0;
    }

    ScopedByteArrayRW outBytes(env, outArray);
    if (outBytes.get() == NULL) {
        return 0;
    }

    unsigned char* out = reinterpret_cast<unsigned char*>(outBytes.get());

    int outl;
    if (!EVP_CipherFinal_ex(ctx, out + outOffset, &outl)) {
        throwExceptionIfNecessary(env, "EVP_CipherFinal_ex");
        JNI_TRACE("ctx=%p EVP_CipherFinal_ex => threw error", ctx);
        return 0;
    }

    JNI_TRACE("EVP_CipherFinal(%p, %p, %d) => %d", ctx, outArray, outOffset, outl);
    return outl;
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_EVP_1CIPHER_1iv_1length(JNIEnv* env, jclass, jlong evpCipherRef) {
    const EVP_CIPHER* evpCipher = reinterpret_cast<const EVP_CIPHER*>(evpCipherRef);
    JNI_TRACE("EVP_CIPHER_iv_length(%p)", evpCipher);

    if (evpCipher == NULL) {
        jniThrowNullPointerException(env, "evpCipher == null");
        JNI_TRACE("EVP_CIPHER_iv_length => evpCipher == null");
        return 0;
    }

    const int ivLength = EVP_CIPHER_iv_length(evpCipher);
    JNI_TRACE("EVP_CIPHER_iv_length(%p) => %d", evpCipher, ivLength);
    return ivLength;
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_EVP_1CIPHER_1CTX_1new(JNIEnv* env, jclass) {
    JNI_TRACE("EVP_CIPHER_CTX_new()");

    Unique_EVP_CIPHER_CTX ctx(EVP_CIPHER_CTX_new());
    if (ctx.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate cipher context");
        JNI_TRACE("EVP_CipherInit_ex => context allocation error");
        return 0;
    }

    JNI_TRACE("EVP_CIPHER_CTX_new() => %p", ctx.get());
    return reinterpret_cast<uintptr_t>(ctx.release());
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_EVP_1CIPHER_1CTX_1block_1size(JNIEnv* env, jclass, jlong ctxRef) {
    EVP_CIPHER_CTX* ctx = reinterpret_cast<EVP_CIPHER_CTX*>(ctxRef);
    JNI_TRACE("EVP_CIPHER_CTX_block_size(%p)", ctx);

    if (ctx == NULL) {
        jniThrowNullPointerException(env, "ctx == null");
        JNI_TRACE("ctx=%p EVP_CIPHER_CTX_block_size => ctx == null", ctx);
        return 0;
    }

    int blockSize = EVP_CIPHER_CTX_block_size(ctx);
    JNI_TRACE("EVP_CIPHER_CTX_block_size(%p) => %d", ctx, blockSize);
    return blockSize;
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_get_1EVP_1CIPHER_1CTX_1buf_1len(JNIEnv* env, jclass, jlong ctxRef) {
    EVP_CIPHER_CTX* ctx = reinterpret_cast<EVP_CIPHER_CTX*>(ctxRef);
    JNI_TRACE("get_EVP_CIPHER_CTX_buf_len(%p)", ctx);

    if (ctx == NULL) {
        jniThrowNullPointerException(env, "ctx == null");
        JNI_TRACE("ctx=%p get_EVP_CIPHER_CTX_buf_len => ctx == null", ctx);
        return 0;
    }

    int buf_len = ctx->buf_len;
    JNI_TRACE("get_EVP_CIPHER_CTX_buf_len(%p) => %d", ctx, buf_len);
    return buf_len;
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EVP_1CIPHER_1CTX_1set_1padding(JNIEnv* env, jclass, jlong ctxRef, jboolean enablePaddingBool) {
    EVP_CIPHER_CTX* ctx = reinterpret_cast<EVP_CIPHER_CTX*>(ctxRef);
    jint enablePadding = enablePaddingBool ? 1 : 0;
    JNI_TRACE("EVP_CIPHER_CTX_set_padding(%p, %d)", ctx, enablePadding);

    if (ctx == NULL) {
        jniThrowNullPointerException(env, "ctx == null");
        JNI_TRACE("ctx=%p EVP_CIPHER_CTX_set_padding => ctx == null", ctx);
        return;
    }

    EVP_CIPHER_CTX_set_padding(ctx, enablePadding); // Not void, but always returns 1.
    JNI_TRACE("EVP_CIPHER_CTX_set_padding(%p, %d) => success", ctx, enablePadding);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EVP_1CIPHER_1CTX_1set_1key_1length(JNIEnv* env, jclass, jlong ctxRef,
        jint keySizeBits) {
    EVP_CIPHER_CTX* ctx = reinterpret_cast<EVP_CIPHER_CTX*>(ctxRef);
    JNI_TRACE("EVP_CIPHER_CTX_set_key_length(%p, %d)", ctx, keySizeBits);

    if (ctx == NULL) {
        jniThrowNullPointerException(env, "ctx == null");
        JNI_TRACE("ctx=%p EVP_CIPHER_CTX_set_key_length => ctx == null", ctx);
        return;
    }

    if (!EVP_CIPHER_CTX_set_key_length(ctx, keySizeBits)) {
        throwExceptionIfNecessary(env, "NativeCrypto_EVP_CIPHER_CTX_set_key_length");
        JNI_TRACE("NativeCrypto_EVP_CIPHER_CTX_set_key_length => threw error");
        return;
    }
    JNI_TRACE("EVP_CIPHER_CTX_set_key_length(%p, %d) => success", ctx, keySizeBits);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_EVP_1CIPHER_1CTX_1cleanup(JNIEnv* env, jclass, jlong ctxRef) {
    EVP_CIPHER_CTX* ctx = reinterpret_cast<EVP_CIPHER_CTX*>(ctxRef);
    JNI_TRACE("EVP_CIPHER_CTX_cleanup(%p)", ctx);

    if (ctx != NULL) {
        if (!EVP_CIPHER_CTX_cleanup(ctx)) {
            throwExceptionIfNecessary(env, "EVP_CIPHER_CTX_cleanup");
            JNI_TRACE("EVP_CIPHER_CTX_cleanup => threw error");
            return;
        }
    }
    JNI_TRACE("EVP_CIPHER_CTX_cleanup(%p) => success", ctx);
}

/**
 * public static native void RAND_seed(byte[]);
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_RAND_1seed(JNIEnv* env, jclass, jbyteArray seed) {
    JNI_TRACE("NativeCrypto_RAND_seed seed=%p", seed);
    ScopedByteArrayRO randseed(env, seed);
    if (randseed.get() == NULL) {
        return;
    }
    RAND_seed(randseed.get(), randseed.size());
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_RAND_1load_1file(JNIEnv* env, jclass, jstring filename, jlong max_bytes) {
    JNI_TRACE("NativeCrypto_RAND_load_file filename=%p max_bytes=%lld", filename, max_bytes);
    ScopedUtfChars file(env, filename);
    if (file.c_str() == NULL) {
        return -1;
    }
    int result = RAND_load_file(file.c_str(), max_bytes);
    JNI_TRACE("NativeCrypto_RAND_load_file file=%s => %d", file.c_str(), result);
    return result;
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_RAND_1bytes(JNIEnv* env, jclass, jbyteArray output) {
    JNI_TRACE("NativeCrypto_RAND_bytes(%p)", output);

    ScopedByteArrayRW outputBytes(env, output);
    if (outputBytes.get() == NULL) {
        return;
    }

    unsigned char* tmp = reinterpret_cast<unsigned char*>(outputBytes.get());
    if (RAND_bytes(tmp, outputBytes.size()) <= 0) {
        throwExceptionIfNecessary(env, "NativeCrypto_RAND_bytes");
        JNI_TRACE("tmp=%p NativeCrypto_RAND_bytes => threw error", tmp);
        return;
    }

    JNI_TRACE("NativeCrypto_RAND_bytes(%p) => success", output);
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_OBJ_1txt2nid(JNIEnv* env, jclass, jstring oidStr) {
    JNI_TRACE("OBJ_txt2nid(%p)", oidStr);

    ScopedUtfChars oid(env, oidStr);
    if (oid.c_str() == NULL) {
        return 0;
    }

    int nid = OBJ_txt2nid(oid.c_str());
    JNI_TRACE("OBJ_txt2nid(%s) => %d", oid.c_str(), nid);
    return nid;
}

extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_OBJ_1txt2nid_1longName(JNIEnv* env, jclass, jstring oidStr) {
    JNI_TRACE("OBJ_txt2nid_longName(%p)", oidStr);

    ScopedUtfChars oid(env, oidStr);
    if (oid.c_str() == NULL) {
        return NULL;
    }

    JNI_TRACE("OBJ_txt2nid_longName(%s)", oid.c_str());

    int nid = OBJ_txt2nid(oid.c_str());
    if (nid == NID_undef) {
        JNI_TRACE("OBJ_txt2nid_longName(%s) => NID_undef", oid.c_str());
        freeOpenSslErrorState();
        return NULL;
    }

    const char* longName = OBJ_nid2ln(nid);
    JNI_TRACE("OBJ_txt2nid_longName(%s) => %s", oid.c_str(), longName);
    return env->NewStringUTF(longName);
}

static jstring ASN1_OBJECT_to_OID_string(JNIEnv* env, ASN1_OBJECT* obj) {
    /*
     * The OBJ_obj2txt API doesn't "measure" if you pass in NULL as the buffer.
     * Just make a buffer that's large enough here. The documentation recommends
     * 80 characters.
     */
    char output[128];
    int ret = OBJ_obj2txt(output, sizeof(output), obj, 1);
    if (ret < 0) {
        throwExceptionIfNecessary(env, "ASN1_OBJECT_to_OID_string");
        return NULL;
    } else if (size_t(ret) >= sizeof(output)) {
        jniThrowRuntimeException(env, "ASN1_OBJECT_to_OID_string buffer too small");
        return NULL;
    }

    JNI_TRACE("ASN1_OBJECT_to_OID_string(%p) => %s", obj, output);
    return env->NewStringUTF(output);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_create_1BIO_1InputStream(JNIEnv* env, jclass, jobject streamObj) {
    JNI_TRACE("create_BIO_InputStream(%p)", streamObj);

    if (streamObj == NULL) {
        jniThrowNullPointerException(env, "stream == null");
        return 0;
    }

    Unique_BIO bio(BIO_new(&stream_bio_method));
    if (bio.get() == NULL) {
        return 0;
    }

    bio_stream_assign(bio.get(), new BIO_InputStream(streamObj));

    JNI_TRACE("create_BIO_InputStream(%p) => %p", streamObj, bio.get());
    return static_cast<jlong>(reinterpret_cast<uintptr_t>(bio.release()));
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_create_1BIO_1OutputStream(JNIEnv* env, jclass, jobject streamObj) {
    JNI_TRACE("create_BIO_OutputStream(%p)", streamObj);

    if (streamObj == NULL) {
        jniThrowNullPointerException(env, "stream == null");
        return 0;
    }

    Unique_BIO bio(BIO_new(&stream_bio_method));
    if (bio.get() == NULL) {
        return 0;
    }

    bio_stream_assign(bio.get(), new BIO_OutputStream(streamObj));

    JNI_TRACE("create_BIO_OutputStream(%p) => %p", streamObj, bio.get());
    return static_cast<jlong>(reinterpret_cast<uintptr_t>(bio.release()));
}

extern "C" int Java_com_android_org_conscrypt_NativeCrypto_BIO_1read(JNIEnv* env, jclass, jlong bioRef, jbyteArray outputJavaBytes) {
    BIO* bio = reinterpret_cast<BIO*>(static_cast<uintptr_t>(bioRef));
    JNI_TRACE("BIO_read(%p, %p)", bio, outputJavaBytes);

    if (outputJavaBytes == NULL) {
        jniThrowNullPointerException(env, "output == null");
        JNI_TRACE("BIO_read(%p, %p) => output == null", bio, outputJavaBytes);
        return 0;
    }

    int outputSize = env->GetArrayLength(outputJavaBytes);

    UniquePtr<unsigned char[]> buffer(new unsigned char[outputSize]);
    if (buffer.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate buffer for read");
        return 0;
    }

    int read = BIO_read(bio, buffer.get(), outputSize);
    if (read <= 0) {
        jniThrowException(env, "java/io/IOException", "BIO_read");
        JNI_TRACE("BIO_read(%p, %p) => threw IO exception", bio, outputJavaBytes);
        return 0;
    }

    env->SetByteArrayRegion(outputJavaBytes, 0, read, reinterpret_cast<jbyte*>(buffer.get()));
    JNI_TRACE("BIO_read(%p, %p) => %d", bio, outputJavaBytes, read);
    return read;
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_BIO_1write(JNIEnv* env, jclass, jlong bioRef, jbyteArray inputJavaBytes,
        jint offset, jint length) {
    BIO* bio = reinterpret_cast<BIO*>(static_cast<uintptr_t>(bioRef));
    JNI_TRACE("BIO_write(%p, %p, %d, %d)", bio, inputJavaBytes, offset, length);

    if (inputJavaBytes == NULL) {
        jniThrowNullPointerException(env, "input == null");
        return;
    }

    if (offset < 0 || length < 0) {
        jniThrowException(env, "java/lang/IndexOutOfBoundsException", "offset < 0 || length < 0");
        JNI_TRACE("BIO_write(%p, %p, %d, %d) => IOOB", bio, inputJavaBytes, offset, length);
        return;
    }

    int inputSize = env->GetArrayLength(inputJavaBytes);
    if (inputSize < offset + length) {
        jniThrowException(env, "java/lang/IndexOutOfBoundsException",
                "input.length < offset + length");
        JNI_TRACE("BIO_write(%p, %p, %d, %d) => IOOB", bio, inputJavaBytes, offset, length);
        return;
    }

    UniquePtr<unsigned char[]> buffer(new unsigned char[length]);
    if (buffer.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate buffer for write");
        return;
    }

    env->GetByteArrayRegion(inputJavaBytes, offset, length, reinterpret_cast<jbyte*>(buffer.get()));
    if (BIO_write(bio, buffer.get(), length) != length) {
        freeOpenSslErrorState();
        jniThrowException(env, "java/io/IOException", "BIO_write");
        JNI_TRACE("BIO_write(%p, %p, %d, %d) => IO error", bio, inputJavaBytes, offset, length);
        return;
    }

    JNI_TRACE("BIO_write(%p, %p, %d, %d) => success", bio, inputJavaBytes, offset, length);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_BIO_1free(JNIEnv* env, jclass, jlong bioRef) {
    BIO* bio = reinterpret_cast<BIO*>(static_cast<uintptr_t>(bioRef));
    JNI_TRACE("BIO_free(%p)", bio);

    if (bio == NULL) {
        jniThrowNullPointerException(env, "bio == null");
        return;
    }

    BIO_free(bio);
}

static jstring X509_NAME_to_jstring(JNIEnv* env, X509_NAME* name, unsigned long flags) {
    JNI_TRACE("X509_NAME_to_jstring(%p)", name);

    Unique_BIO buffer(BIO_new(BIO_s_mem()));
    if (buffer.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate BIO");
        JNI_TRACE("X509_NAME_to_jstring(%p) => threw error", name);
        return NULL;
    }

    /* Don't interpret the string. */
    flags &= ~(ASN1_STRFLGS_UTF8_CONVERT | ASN1_STRFLGS_ESC_MSB);

    /* Write in given format and null terminate. */
    X509_NAME_print_ex(buffer.get(), name, 0, flags);
    BIO_write(buffer.get(), "\0", 1);

    char *tmp;
    BIO_get_mem_data(buffer.get(), &tmp);
    JNI_TRACE("X509_NAME_to_jstring(%p) => \"%s\"", name, tmp);
    return env->NewStringUTF(tmp);
}


/**
 * Converts GENERAL_NAME items to the output format expected in
 * X509Certificate#getSubjectAlternativeNames and
 * X509Certificate#getIssuerAlternativeNames return.
 */
static jobject GENERAL_NAME_to_jobject(JNIEnv* env, GENERAL_NAME* gen) {
    switch (gen->type) {
    case GEN_EMAIL:
    case GEN_DNS:
    case GEN_URI: {
        // This must not be a T61String and must not contain NULLs.
        const char* data = reinterpret_cast<const char*>(ASN1_STRING_data(gen->d.ia5));
        ssize_t len = ASN1_STRING_length(gen->d.ia5);
        if ((len == static_cast<ssize_t>(strlen(data)))
                && (ASN1_PRINTABLE_type(ASN1_STRING_data(gen->d.ia5), len) != V_ASN1_T61STRING)) {
            JNI_TRACE("GENERAL_NAME_to_jobject(%p) => Email/DNS/URI \"%s\"", gen, data);
            return env->NewStringUTF(data);
        } else {
            jniThrowException(env, "java/security/cert/CertificateParsingException",
                    "Invalid dNSName encoding");
            JNI_TRACE("GENERAL_NAME_to_jobject(%p) => Email/DNS/URI invalid", gen);
            return NULL;
        }
    }
    case GEN_DIRNAME:
        /* Write in RFC 2253 format */
        return X509_NAME_to_jstring(env, gen->d.directoryName, XN_FLAG_RFC2253);
    case GEN_IPADD: {
        const void *ip = reinterpret_cast<const void *>(gen->d.ip->data);
        if (gen->d.ip->length == 4) {
            // IPv4
            UniquePtr<char[]> buffer(new char[INET_ADDRSTRLEN]);
            if (inet_ntop(AF_INET, ip, buffer.get(), INET_ADDRSTRLEN) != NULL) {
                JNI_TRACE("GENERAL_NAME_to_jobject(%p) => IPv4 %s", gen, buffer.get());
                return env->NewStringUTF(buffer.get());
            } else {
                JNI_TRACE("GENERAL_NAME_to_jobject(%p) => IPv4 failed %s", gen, strerror(errno));
            }
        } else if (gen->d.ip->length == 16) {
            // IPv6
            UniquePtr<char[]> buffer(new char[INET6_ADDRSTRLEN]);
            if (inet_ntop(AF_INET6, ip, buffer.get(), INET6_ADDRSTRLEN) != NULL) {
                JNI_TRACE("GENERAL_NAME_to_jobject(%p) => IPv6 %s", gen, buffer.get());
                return env->NewStringUTF(buffer.get());
            } else {
                JNI_TRACE("GENERAL_NAME_to_jobject(%p) => IPv6 failed %s", gen, strerror(errno));
            }
        }

        /* Invalid IP encodings are pruned out without throwing an exception. */
        return NULL;
    }
    case GEN_RID:
        return ASN1_OBJECT_to_OID_string(env, gen->d.registeredID);
    case GEN_OTHERNAME:
    case GEN_X400:
    default:
        return ASN1ToByteArray<GENERAL_NAME, i2d_GENERAL_NAME>(env, gen);
    }

    return NULL;
}

#define GN_STACK_SUBJECT_ALT_NAME 1
#define GN_STACK_ISSUER_ALT_NAME 2

extern "C" jobjectArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1GENERAL_1NAME_1stack(JNIEnv* env, jclass, jlong x509Ref,
        jint type) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509_GENERAL_NAME_stack(%p, %d)", x509, type);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("get_X509_GENERAL_NAME_stack(%p, %d) => x509 == null", x509, type);
        return NULL;
    }

    X509_check_ca(x509);

    STACK_OF(GENERAL_NAME)* gn_stack;
    Unique_sk_GENERAL_NAME stackHolder;
    if (type == GN_STACK_SUBJECT_ALT_NAME) {
        gn_stack = x509->altname;
    } else if (type == GN_STACK_ISSUER_ALT_NAME) {
        stackHolder.reset(
                static_cast<STACK_OF(GENERAL_NAME)*>(X509_get_ext_d2i(x509, NID_issuer_alt_name,
                        NULL, NULL)));
        gn_stack = stackHolder.get();
    } else {
        JNI_TRACE("get_X509_GENERAL_NAME_stack(%p, %d) => unknown type", x509, type);
        return NULL;
    }

    int count = sk_GENERAL_NAME_num(gn_stack);
    if (count <= 0) {
        JNI_TRACE("get_X509_GENERAL_NAME_stack(%p, %d) => null (no entries)", x509, type);
        return NULL;
    }

    /*
     * Keep track of how many originally so we can ignore any invalid
     * values later.
     */
    const int origCount = count;

    ScopedLocalRef<jobjectArray> joa(env, env->NewObjectArray(count, objectArrayClass, NULL));
    for (int i = 0, j = 0; i < origCount; i++, j++) {
        GENERAL_NAME* gen = sk_GENERAL_NAME_value(gn_stack, i);
        ScopedLocalRef<jobject> val(env, GENERAL_NAME_to_jobject(env, gen));
        if (env->ExceptionCheck()) {
            JNI_TRACE("get_X509_GENERAL_NAME_stack(%p, %d) => threw exception parsing gen name",
                    x509, type);
            return NULL;
        }

        /*
         * If it's NULL, we'll have to skip this, reduce the number of total
         * entries, and fix up the array later.
         */
        if (val.get() == NULL) {
            j--;
            count--;
            continue;
        }

        ScopedLocalRef<jobjectArray> item(env, env->NewObjectArray(2, objectClass, NULL));

        ScopedLocalRef<jobject> type(env, env->CallStaticObjectMethod(integerClass,
                integer_valueOfMethod, gen->type));
        env->SetObjectArrayElement(item.get(), 0, type.get());
        env->SetObjectArrayElement(item.get(), 1, val.get());

        env->SetObjectArrayElement(joa.get(), j, item.get());
    }

    if (count == 0) {
        JNI_TRACE("get_X509_GENERAL_NAME_stack(%p, %d) shrunk from %d to 0; returning NULL",
                x509, type, origCount);
        joa.reset(NULL);
    } else if (origCount != count) {
        JNI_TRACE("get_X509_GENERAL_NAME_stack(%p, %d) shrunk from %d to %d", x509, type,
                origCount, count);

        ScopedLocalRef<jobjectArray> joa_copy(env, env->NewObjectArray(count, objectArrayClass,
                NULL));

        for (int i = 0; i < count; i++) {
            ScopedLocalRef<jobject> item(env, env->GetObjectArrayElement(joa.get(), i));
            env->SetObjectArrayElement(joa_copy.get(), i, item.get());
        }

        joa.reset(joa_copy.release());
    }

    JNI_TRACE("get_X509_GENERAL_NAME_stack(%p, %d) => %d entries", x509, type, count);
    return joa.release();
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_X509_1get_1notBefore(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("X509_get_notBefore(%p)", x509);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("X509_get_notBefore(%p) => x509 == null", x509);
        return 0;
    }

    ASN1_TIME* notBefore = X509_get_notBefore(x509);
    JNI_TRACE("X509_get_notBefore(%p) => %p", x509, notBefore);
    return reinterpret_cast<uintptr_t>(notBefore);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_X509_1get_1notAfter(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("X509_get_notAfter(%p)", x509);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("X509_get_notAfter(%p) => x509 == null", x509);
        return 0;
    }

    ASN1_TIME* notAfter = X509_get_notAfter(x509);
    JNI_TRACE("X509_get_notAfter(%p) => %p", x509, notAfter);
    return reinterpret_cast<uintptr_t>(notAfter);
}

extern "C" long Java_com_android_org_conscrypt_NativeCrypto_X509_1get_1version(JNIEnv*, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("X509_get_version(%p)", x509);

    long version = X509_get_version(x509);
    JNI_TRACE("X509_get_version(%p) => %ld", x509, version);
    return version;
}

template<typename T>
static jbyteArray get_X509Type_serialNumber(JNIEnv* env, T* x509Type, ASN1_INTEGER* (*get_serial_func)(T*)) {
    JNI_TRACE("get_X509Type_serialNumber(%p)", x509Type);

    if (x509Type == NULL) {
        jniThrowNullPointerException(env, "x509Type == null");
        JNI_TRACE("get_X509Type_serialNumber(%p) => x509Type == null", x509Type);
        return NULL;
    }

    ASN1_INTEGER* serialNumber = get_serial_func(x509Type);
    Unique_BIGNUM serialBn(ASN1_INTEGER_to_BN(serialNumber, NULL));
    if (serialBn.get() == NULL) {
        JNI_TRACE("X509_get_serialNumber(%p) => threw exception", x509Type);
        return NULL;
    }

    ScopedLocalRef<jbyteArray> serialArray(env, bignumToArray(env, serialBn.get(), "serialBn"));
    if (env->ExceptionCheck()) {
        JNI_TRACE("X509_get_serialNumber(%p) => threw exception", x509Type);
        return NULL;
    }

    JNI_TRACE("X509_get_serialNumber(%p) => %p", x509Type, serialArray.get());
    return serialArray.release();
}

/* OpenSSL includes set_serialNumber but not get. */
#if !defined(X509_REVOKED_get_serialNumber)
static ASN1_INTEGER* X509_REVOKED_get_serialNumber(X509_REVOKED* x) {
    return x->serialNumber;
}
#endif

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_X509_1get_1serialNumber(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("X509_get_serialNumber(%p)", x509);
    return get_X509Type_serialNumber<X509>(env, x509, X509_get_serialNumber);
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_X509_1REVOKED_1get_1serialNumber(JNIEnv* env, jclass, jlong x509RevokedRef) {
    X509_REVOKED* revoked = reinterpret_cast<X509_REVOKED*>(static_cast<uintptr_t>(x509RevokedRef));
    JNI_TRACE("X509_REVOKED_get_serialNumber(%p)", revoked);
    return get_X509Type_serialNumber<X509_REVOKED>(env, revoked, X509_REVOKED_get_serialNumber);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_X509_1verify(JNIEnv* env, jclass, jlong x509Ref, jlong pkeyRef) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("X509_verify(%p, %p)", x509, pkey);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("X509_verify(%p, %p) => x509 == null", x509, pkey);
        return;
    }

    if (pkey == NULL) {
        jniThrowNullPointerException(env, "pkey == null");
        JNI_TRACE("X509_verify(%p, %p) => pkey == null", x509, pkey);
        return;
    }

    if (X509_verify(x509, pkey) != 1) {
        throwExceptionIfNecessary(env, "X509_verify");
        JNI_TRACE("X509_verify(%p, %p) => verify failure", x509, pkey);
    } else {
        JNI_TRACE("X509_verify(%p, %p) => verify success", x509, pkey);
    }
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1cert_1info_1enc(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509_cert_info_enc(%p)", x509);
    return ASN1ToByteArray<X509_CINF, i2d_X509_CINF>(env, x509->cert_info);
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1ex_1flags(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509_ex_flags(%p)", x509);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("get_X509_ex_flags(%p) => x509 == null", x509);
        return 0;
    }

    X509_check_ca(x509);

    return x509->ex_flags;
}

extern "C" jboolean Java_com_android_org_conscrypt_NativeCrypto_X509_1check_1issued(JNIEnv*, jclass, jlong x509Ref1, jlong x509Ref2) {
    X509* x509_1 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref1));
    X509* x509_2 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref2));
    JNI_TRACE("X509_check_issued(%p, %p)", x509_1, x509_2);

    int ret = X509_check_issued(x509_1, x509_2);
    JNI_TRACE("X509_check_issued(%p, %p) => %d", x509_1, x509_2, ret);
    return ret;
}

static void get_X509_signature(X509 *x509, ASN1_BIT_STRING** signature) {
    *signature = x509->signature;
}

static void get_X509_CRL_signature(X509_CRL *crl, ASN1_BIT_STRING** signature) {
    *signature = crl->signature;
}

template<typename T>
static jbyteArray get_X509Type_signature(JNIEnv* env, T* x509Type, void (*get_signature_func)(T*, ASN1_BIT_STRING**)) {
    JNI_TRACE("get_X509Type_signature(%p)", x509Type);

    if (x509Type == NULL) {
        jniThrowNullPointerException(env, "x509Type == null");
        JNI_TRACE("get_X509Type_signature(%p) => x509Type == null", x509Type);
        return NULL;
    }

    ASN1_BIT_STRING* signature;
    get_signature_func(x509Type, &signature);

    ScopedLocalRef<jbyteArray> signatureArray(env, env->NewByteArray(signature->length));
    if (env->ExceptionCheck()) {
        JNI_TRACE("get_X509Type_signature(%p) => threw exception", x509Type);
        return NULL;
    }

    ScopedByteArrayRW signatureBytes(env, signatureArray.get());
    if (signatureBytes.get() == NULL) {
        JNI_TRACE("get_X509Type_signature(%p) => using byte array failed", x509Type);
        return NULL;
    }

    memcpy(signatureBytes.get(), signature->data, signature->length);

    JNI_TRACE("get_X509Type_signature(%p) => %p (%d bytes)", x509Type, signatureArray.get(),
            signature->length);
    return signatureArray.release();
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1signature(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509_signature(%p)", x509);
    return get_X509Type_signature<X509>(env, x509, get_X509_signature);
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1CRL_1signature(JNIEnv* env, jclass, jlong x509CrlRef) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("get_X509_CRL_signature(%p)", crl);
    return get_X509Type_signature<X509_CRL>(env, crl, get_X509_CRL_signature);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_X509_1CRL_1get0_1by_1cert(JNIEnv* env, jclass, jlong x509crlRef, jlong x509Ref) {
    X509_CRL* x509crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509crlRef));
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("X509_CRL_get0_by_cert(%p, %p)", x509crl, x509);

    if (x509crl == NULL) {
        jniThrowNullPointerException(env, "x509crl == null");
        JNI_TRACE("X509_CRL_get0_by_cert(%p, %p) => x509crl == null", x509crl, x509);
        return 0;
    } else if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("X509_CRL_get0_by_cert(%p, %p) => x509 == null", x509crl, x509);
        return 0;
    }

    X509_REVOKED* revoked = NULL;
    int ret = X509_CRL_get0_by_cert(x509crl, &revoked, x509);
    if (ret == 0) {
        JNI_TRACE("X509_CRL_get0_by_cert(%p, %p) => none", x509crl, x509);
        return 0;
    }

    JNI_TRACE("X509_CRL_get0_by_cert(%p, %p) => %p", x509crl, x509, revoked);
    return reinterpret_cast<uintptr_t>(revoked);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_X509_1CRL_1get0_1by_1serial(JNIEnv* env, jclass, jlong x509crlRef, jbyteArray serialArray) {
    X509_CRL* x509crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509crlRef));
    JNI_TRACE("X509_CRL_get0_by_serial(%p, %p)", x509crl, serialArray);

    if (x509crl == NULL) {
        jniThrowNullPointerException(env, "x509crl == null");
        JNI_TRACE("X509_CRL_get0_by_serial(%p, %p) => crl == null", x509crl, serialArray);
        return 0;
    }

    Unique_BIGNUM serialBn(BN_new());
    if (serialBn.get() == NULL) {
        JNI_TRACE("X509_CRL_get0_by_serial(%p, %p) => BN allocation failed", x509crl, serialArray);
        return 0;
    }

    BIGNUM* serialBare = serialBn.get();
    if (!arrayToBignum(env, serialArray, &serialBare)) {
        if (!env->ExceptionCheck()) {
            jniThrowNullPointerException(env, "serial == null");
        }
        JNI_TRACE("X509_CRL_get0_by_serial(%p, %p) => BN conversion failed", x509crl, serialArray);
        return 0;
    }

    Unique_ASN1_INTEGER serialInteger(BN_to_ASN1_INTEGER(serialBn.get(), NULL));
    if (serialInteger.get() == NULL) {
        JNI_TRACE("X509_CRL_get0_by_serial(%p, %p) => BN conversion failed", x509crl, serialArray);
        return 0;
    }

    X509_REVOKED* revoked = NULL;
    int ret = X509_CRL_get0_by_serial(x509crl, &revoked, serialInteger.get());
    if (ret == 0) {
        JNI_TRACE("X509_CRL_get0_by_serial(%p, %p) => none", x509crl, serialArray);
        return 0;
    }

    JNI_TRACE("X509_CRL_get0_by_cert(%p, %p) => %p", x509crl, serialArray, revoked);
    return reinterpret_cast<uintptr_t>(revoked);
}


/* This appears to be missing from OpenSSL. */
#if !defined(X509_REVOKED_dup)
X509_REVOKED* X509_REVOKED_dup(X509_REVOKED* x) {
    return reinterpret_cast<X509_REVOKED*>(ASN1_item_dup(ASN1_ITEM_rptr(X509_REVOKED), x));
}
#endif

extern "C" jlongArray Java_com_android_org_conscrypt_NativeCrypto_X509_1CRL_1get_1REVOKED(JNIEnv* env, jclass, jlong x509CrlRef) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("X509_CRL_get_REVOKED(%p)", crl);

    if (crl == NULL) {
        jniThrowNullPointerException(env, "crl == null");
        return NULL;
    }

    STACK_OF(X509_REVOKED)* stack = X509_CRL_get_REVOKED(crl);
    if (stack == NULL) {
        JNI_TRACE("X509_CRL_get_REVOKED(%p) => stack is null", crl);
        return NULL;
    }

    size_t size = sk_X509_REVOKED_num(stack);

    ScopedLocalRef<jlongArray> revokedArray(env, env->NewLongArray(size));
    ScopedLongArrayRW revoked(env, revokedArray.get());
    for (size_t i = 0; i < size; i++) {
        X509_REVOKED* item = reinterpret_cast<X509_REVOKED*>(sk_X509_REVOKED_value(stack, i));
        revoked[i] = reinterpret_cast<uintptr_t>(X509_REVOKED_dup(item));
    }

    JNI_TRACE("X509_CRL_get_REVOKED(%p) => %p [size=%d]", stack, revokedArray.get(), size);
    return revokedArray.release();
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_i2d_1X509_1CRL(JNIEnv* env, jclass, jlong x509CrlRef) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("i2d_X509_CRL(%p)", crl);
    return ASN1ToByteArray<X509_CRL, i2d_X509_CRL>(env, crl);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_X509_1CRL_1free(JNIEnv* env, jclass, jlong x509CrlRef) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("X509_CRL_free(%p)", crl);

    if (crl == NULL) {
        jniThrowNullPointerException(env, "crl == null");
        JNI_TRACE("X509_CRL_free(%p) => crl == null", crl);
        return;
    }

    X509_CRL_free(crl);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_X509_1CRL_1print(JNIEnv* env, jclass, jlong bioRef, jlong x509CrlRef) {
    BIO* bio = reinterpret_cast<BIO*>(static_cast<uintptr_t>(bioRef));
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("X509_CRL_print(%p, %p)", bio, crl);

    if (bio == NULL) {
        jniThrowNullPointerException(env, "bio == null");
        JNI_TRACE("X509_CRL_print(%p, %p) => bio == null", bio, crl);
        return;
    }

    if (crl == NULL) {
        jniThrowNullPointerException(env, "crl == null");
        JNI_TRACE("X509_CRL_print(%p, %p) => crl == null", bio, crl);
        return;
    }

    if (!X509_CRL_print(bio, crl)) {
        throwExceptionIfNecessary(env, "X509_CRL_print");
        JNI_TRACE("X509_CRL_print(%p, %p) => threw error", bio, crl);
    } else {
        JNI_TRACE("X509_CRL_print(%p, %p) => success", bio, crl);
    }
}

extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1CRL_1sig_1alg_1oid(JNIEnv* env, jclass, jlong x509CrlRef) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("get_X509_CRL_sig_alg_oid(%p)", crl);

    if (crl == NULL || crl->sig_alg == NULL) {
        jniThrowNullPointerException(env, "crl == NULL || crl->sig_alg == NULL");
        JNI_TRACE("get_X509_CRL_sig_alg_oid(%p) => crl == NULL", crl);
        return NULL;
    }

    return ASN1_OBJECT_to_OID_string(env, crl->sig_alg->algorithm);
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1CRL_1sig_1alg_1parameter(JNIEnv* env, jclass, jlong x509CrlRef) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("get_X509_CRL_sig_alg_parameter(%p)", crl);

    if (crl == NULL) {
        jniThrowNullPointerException(env, "crl == null");
        JNI_TRACE("get_X509_CRL_sig_alg_parameter(%p) => crl == null", crl);
        return NULL;
    }

    if (crl->sig_alg->parameter == NULL) {
        JNI_TRACE("get_X509_CRL_sig_alg_parameter(%p) => null", crl);
        return NULL;
    }

    return ASN1ToByteArray<ASN1_TYPE, i2d_ASN1_TYPE>(env, crl->sig_alg->parameter);
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_X509_1CRL_1get_1issuer_1name(JNIEnv* env, jclass, jlong x509CrlRef) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("X509_CRL_get_issuer_name(%p)", crl);
    return ASN1ToByteArray<X509_NAME, i2d_X509_NAME>(env, X509_CRL_get_issuer(crl));
}

extern "C" long Java_com_android_org_conscrypt_NativeCrypto_X509_1CRL_1get_1version(JNIEnv*, jclass, jlong x509CrlRef) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("X509_CRL_get_version(%p)", crl);

    long version = X509_CRL_get_version(crl);
    JNI_TRACE("X509_CRL_get_version(%p) => %ld", crl, version);
    return version;
}

template<typename T, int (*get_ext_by_OBJ_func)(T*, ASN1_OBJECT*, int),
        X509_EXTENSION* (*get_ext_func)(T*, int)>
static X509_EXTENSION *X509Type_get_ext(JNIEnv* env, T* x509Type, jstring oidString) {
    JNI_TRACE("X509Type_get_ext(%p)", x509Type);

    if (x509Type == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        return NULL;
    }

    ScopedUtfChars oid(env, oidString);
    if (oid.c_str() == NULL) {
        return NULL;
    }

    Unique_ASN1_OBJECT asn1(OBJ_txt2obj(oid.c_str(), 1));
    if (asn1.get() == NULL) {
        JNI_TRACE("X509Type_get_ext(%p, %s) => oid conversion failed", x509Type, oid.c_str());
        freeOpenSslErrorState();
        return NULL;
    }

    int extIndex = get_ext_by_OBJ_func(x509Type, asn1.get(), -1);
    if (extIndex == -1) {
        JNI_TRACE("X509Type_get_ext(%p, %s) => ext not found", x509Type, oid.c_str());
        return NULL;
    }

    X509_EXTENSION* ext = get_ext_func(x509Type, extIndex);
    JNI_TRACE("X509Type_get_ext(%p, %s) => %p", x509Type, oid.c_str(), ext);
    return ext;
}

template<typename T, int (*get_ext_by_OBJ_func)(T*, ASN1_OBJECT*, int),
        X509_EXTENSION* (*get_ext_func)(T*, int)>
static jbyteArray X509Type_get_ext_oid(JNIEnv* env, T* x509Type, jstring oidString) {
    X509_EXTENSION* ext = X509Type_get_ext<T, get_ext_by_OBJ_func, get_ext_func>(env, x509Type,
            oidString);
    if (ext == NULL) {
        JNI_TRACE("X509Type_get_ext_oid(%p, %p) => fetching extension failed", x509Type, oidString);
        return NULL;
    }

    JNI_TRACE("X509Type_get_ext_oid(%p, %p) => %p", x509Type, oidString, ext->value);
    return ASN1ToByteArray<ASN1_OCTET_STRING, i2d_ASN1_OCTET_STRING>(env, ext->value);
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_X509_1CRL_1get_1ext(JNIEnv* env, jclass, jlong x509CrlRef, jstring oid) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("X509_CRL_get_ext(%p, %p)", crl, oid);
    X509_EXTENSION* ext = X509Type_get_ext<X509_CRL, X509_CRL_get_ext_by_OBJ, X509_CRL_get_ext>(
            env, crl, oid);
    JNI_TRACE("X509_CRL_get_ext(%p, %p) => %p", crl, oid, ext);
    return reinterpret_cast<uintptr_t>(ext);
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_X509_1REVOKED_1get_1ext(JNIEnv* env, jclass, jlong x509RevokedRef,
        jstring oid) {
    X509_REVOKED* revoked = reinterpret_cast<X509_REVOKED*>(static_cast<uintptr_t>(x509RevokedRef));
    JNI_TRACE("X509_REVOKED_get_ext(%p, %p)", revoked, oid);
    X509_EXTENSION* ext = X509Type_get_ext<X509_REVOKED, X509_REVOKED_get_ext_by_OBJ,
            X509_REVOKED_get_ext>(env, revoked, oid);
    JNI_TRACE("X509_REVOKED_get_ext(%p, %p) => %p", revoked, oid, ext);
    return reinterpret_cast<uintptr_t>(ext);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_X509_1REVOKED_1dup(JNIEnv* env, jclass, jlong x509RevokedRef) {
    X509_REVOKED* revoked = reinterpret_cast<X509_REVOKED*>(static_cast<uintptr_t>(x509RevokedRef));
    JNI_TRACE("X509_REVOKED_dup(%p)", revoked);

    if (revoked == NULL) {
        jniThrowNullPointerException(env, "revoked == null");
        JNI_TRACE("X509_REVOKED_dup(%p) => revoked == null", revoked);
        return 0;
    }

    X509_REVOKED* dup = X509_REVOKED_dup(revoked);
    JNI_TRACE("X509_REVOKED_dup(%p) => %p", revoked, dup);
    return reinterpret_cast<uintptr_t>(dup);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1REVOKED_1revocationDate(JNIEnv* env, jclass, jlong x509RevokedRef) {
    X509_REVOKED* revoked = reinterpret_cast<X509_REVOKED*>(static_cast<uintptr_t>(x509RevokedRef));
    JNI_TRACE("get_X509_REVOKED_revocationDate(%p)", revoked);

    if (revoked == NULL) {
        jniThrowNullPointerException(env, "revoked == null");
        JNI_TRACE("get_X509_REVOKED_revocationDate(%p) => revoked == null", revoked);
        return 0;
    }

    JNI_TRACE("get_X509_REVOKED_revocationDate(%p) => %p", revoked, revoked->revocationDate);
    return reinterpret_cast<uintptr_t>(revoked->revocationDate);
}

#pragma GCC diagnostic push
#pragma GCC diagnostic ignored "-Wwrite-strings"
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_X509_1REVOKED_1print(JNIEnv* env, jclass, jlong bioRef, jlong x509RevokedRef) {
    BIO* bio = reinterpret_cast<BIO*>(static_cast<uintptr_t>(bioRef));
    X509_REVOKED* revoked = reinterpret_cast<X509_REVOKED*>(static_cast<uintptr_t>(x509RevokedRef));
    JNI_TRACE("X509_REVOKED_print(%p, %p)", bio, revoked);

    if (bio == NULL) {
        jniThrowNullPointerException(env, "bio == null");
        JNI_TRACE("X509_REVOKED_print(%p, %p) => bio == null", bio, revoked);
        return;
    }

    if (revoked == NULL) {
        jniThrowNullPointerException(env, "revoked == null");
        JNI_TRACE("X509_REVOKED_print(%p, %p) => revoked == null", bio, revoked);
        return;
    }

    BIO_printf(bio, "Serial Number: ");
    i2a_ASN1_INTEGER(bio, revoked->serialNumber);
    BIO_printf(bio, "\nRevocation Date: ");
    ASN1_TIME_print(bio, revoked->revocationDate);
    BIO_printf(bio, "\n");
    X509V3_extensions_print(bio, "CRL entry extensions", revoked->extensions, 0, 0);
}
#pragma GCC diagnostic pop

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1CRL_1crl_1enc(JNIEnv* env, jclass, jlong x509CrlRef) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("get_X509_CRL_crl_enc(%p)", crl);
    return ASN1ToByteArray<X509_CRL_INFO, i2d_X509_CRL_INFO>(env, crl->crl);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_X509_1CRL_1verify(JNIEnv* env, jclass, jlong x509CrlRef, jlong pkeyRef) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("X509_CRL_verify(%p, %p)", crl, pkey);

    if (crl == NULL) {
        jniThrowNullPointerException(env, "crl == null");
        JNI_TRACE("X509_CRL_verify(%p, %p) => crl == null", crl, pkey);
        return;
    }

    if (pkey == NULL) {
        jniThrowNullPointerException(env, "pkey == null");
        JNI_TRACE("X509_CRL_verify(%p, %p) => pkey == null", crl, pkey);
        return;
    }

    if (X509_CRL_verify(crl, pkey) != 1) {
        throwExceptionIfNecessary(env, "X509_CRL_verify");
        JNI_TRACE("X509_CRL_verify(%p, %p) => verify failure", crl, pkey);
    } else {
        JNI_TRACE("X509_CRL_verify(%p, %p) => verify success", crl, pkey);
    }
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_X509_1CRL_1get_1lastUpdate(JNIEnv* env, jclass, jlong x509CrlRef) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("X509_CRL_get_lastUpdate(%p)", crl);

    if (crl == NULL) {
        jniThrowNullPointerException(env, "crl == null");
        JNI_TRACE("X509_CRL_get_lastUpdate(%p) => crl == null", crl);
        return 0;
    }

    ASN1_TIME* lastUpdate = X509_CRL_get_lastUpdate(crl);
    JNI_TRACE("X509_CRL_get_lastUpdate(%p) => %p", crl, lastUpdate);
    return reinterpret_cast<uintptr_t>(lastUpdate);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_X509_1CRL_1get_1nextUpdate(JNIEnv* env, jclass, jlong x509CrlRef) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("X509_CRL_get_nextUpdate(%p)", crl);

    if (crl == NULL) {
        jniThrowNullPointerException(env, "crl == null");
        JNI_TRACE("X509_CRL_get_nextUpdate(%p) => crl == null", crl);
        return 0;
    }

    ASN1_TIME* nextUpdate = X509_CRL_get_nextUpdate(crl);
    JNI_TRACE("X509_CRL_get_nextUpdate(%p) => %p", crl, nextUpdate);
    return reinterpret_cast<uintptr_t>(nextUpdate);
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_i2d_1X509_1REVOKED(JNIEnv* env, jclass, jlong x509RevokedRef) {
    X509_REVOKED* x509Revoked =
            reinterpret_cast<X509_REVOKED*>(static_cast<uintptr_t>(x509RevokedRef));
    JNI_TRACE("i2d_X509_REVOKED(%p)", x509Revoked);
    return ASN1ToByteArray<X509_REVOKED, i2d_X509_REVOKED>(env, x509Revoked);
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_X509_1supported_1extension(JNIEnv* env, jclass, jlong x509ExtensionRef) {
    X509_EXTENSION* ext = reinterpret_cast<X509_EXTENSION*>(static_cast<uintptr_t>(x509ExtensionRef));

    if (ext == NULL) {
        jniThrowNullPointerException(env, "ext == NULL");
        return 0;
    }

    return X509_supported_extension(ext);
}

static inline void get_ASN1_TIME_data(char **data, int* output, size_t len) {
    char c = **data;
    **data = '\0';
    *data -= len;
    *output = atoi(*data);
    *(*data + len) = c;
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_ASN1_1TIME_1to_1Calendar(JNIEnv* env, jclass, jlong asn1TimeRef, jobject calendar) {
    ASN1_TIME* asn1Time = reinterpret_cast<ASN1_TIME*>(static_cast<uintptr_t>(asn1TimeRef));
    JNI_TRACE("ASN1_TIME_to_Calendar(%p, %p)", asn1Time, calendar);

    if (asn1Time == NULL) {
        jniThrowNullPointerException(env, "asn1Time == null");
        return;
    }

    Unique_ASN1_GENERALIZEDTIME gen(ASN1_TIME_to_generalizedtime(asn1Time, NULL));
    if (gen.get() == NULL) {
        jniThrowNullPointerException(env, "asn1Time == null");
        return;
    }

    if (gen->length < 14 || gen->data == NULL) {
        jniThrowNullPointerException(env, "gen->length < 14 || gen->data == NULL");
        return;
    }

    int sec, min, hour, mday, mon, year;

    char *p = (char*) &gen->data[14];

    get_ASN1_TIME_data(&p, &sec, 2);
    get_ASN1_TIME_data(&p, &min, 2);
    get_ASN1_TIME_data(&p, &hour, 2);
    get_ASN1_TIME_data(&p, &mday, 2);
    get_ASN1_TIME_data(&p, &mon, 2);
    get_ASN1_TIME_data(&p, &year, 4);

    env->CallVoidMethod(calendar, calendar_setMethod, year, mon - 1, mday, hour, min, sec);
}

extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_OBJ_1txt2nid_1oid(JNIEnv* env, jclass, jstring oidStr) {
    JNI_TRACE("OBJ_txt2nid_oid(%p)", oidStr);

    ScopedUtfChars oid(env, oidStr);
    if (oid.c_str() == NULL) {
        return NULL;
    }

    JNI_TRACE("OBJ_txt2nid_oid(%s)", oid.c_str());

    int nid = OBJ_txt2nid(oid.c_str());
    if (nid == NID_undef) {
        JNI_TRACE("OBJ_txt2nid_oid(%s) => NID_undef", oid.c_str());
        freeOpenSslErrorState();
        return NULL;
    }

    Unique_ASN1_OBJECT obj(OBJ_nid2obj(nid));
    if (obj.get() == NULL) {
        throwExceptionIfNecessary(env, "OBJ_nid2obj");
        return NULL;
    }

    ScopedLocalRef<jstring> ouputStr(env, ASN1_OBJECT_to_OID_string(env, obj.get()));
    JNI_TRACE("OBJ_txt2nid_oid(%s) => %p", oid.c_str(), ouputStr.get());
    return ouputStr.release();
}

extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_X509_1NAME_1print_1ex(JNIEnv* env, jclass, jlong x509NameRef, jlong jflags) {
    X509_NAME* x509name = reinterpret_cast<X509_NAME*>(static_cast<uintptr_t>(x509NameRef));
    unsigned long flags = static_cast<unsigned long>(jflags);
    JNI_TRACE("X509_NAME_print_ex(%p, %ld)", x509name, flags);

    if (x509name == NULL) {
        jniThrowNullPointerException(env, "x509name == null");
        JNI_TRACE("X509_NAME_print_ex(%p, %ld) => x509name == null", x509name, flags);
        return NULL;
    }

    return X509_NAME_to_jstring(env, x509name, flags);
}

template <typename T, T* (*d2i_func)(BIO*, T**)>
static jlong d2i_ASN1Object_to_jlong(JNIEnv* env, jlong bioRef) {
    BIO* bio = reinterpret_cast<BIO*>(static_cast<uintptr_t>(bioRef));
    JNI_TRACE("d2i_ASN1Object_to_jlong(%p)", bio);

    if (bio == NULL) {
        jniThrowNullPointerException(env, "bio == null");
        return 0;
    }

    T* x = d2i_func(bio, NULL);
    if (x == NULL) {
        throwExceptionIfNecessary(env, "d2i_ASN1Object_to_jlong");
        return 0;
    }

    return reinterpret_cast<uintptr_t>(x);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_d2i_1X509_1CRL_1bio(JNIEnv* env, jclass, jlong bioRef) {
    return d2i_ASN1Object_to_jlong<X509_CRL, d2i_X509_CRL_bio>(env, bioRef);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_d2i_1X509_1bio(JNIEnv* env, jclass, jlong bioRef) {
    return d2i_ASN1Object_to_jlong<X509, d2i_X509_bio>(env, bioRef);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_d2i_1X509(JNIEnv* env, jclass, jbyteArray certBytes) {
    X509* x = ByteArrayToASN1<X509, d2i_X509>(env, certBytes);
    return reinterpret_cast<uintptr_t>(x);
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_i2d_1X509(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("i2d_X509(%p)", x509);
    return ASN1ToByteArray<X509, i2d_X509>(env, x509);
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_i2d_1X509_1PUBKEY(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("i2d_X509_PUBKEY(%p)", x509);
    return ASN1ToByteArray<X509_PUBKEY, i2d_X509_PUBKEY>(env, X509_get_X509_PUBKEY(x509));
}


template<typename T, T* (*PEM_read_func)(BIO*, T**, pem_password_cb*, void*)>
static jlong PEM_ASN1Object_to_jlong(JNIEnv* env, jlong bioRef) {
    BIO* bio = reinterpret_cast<BIO*>(static_cast<uintptr_t>(bioRef));
    JNI_TRACE("PEM_ASN1Object_to_jlong(%p)", bio);

    if (bio == NULL) {
        jniThrowNullPointerException(env, "bio == null");
        JNI_TRACE("PEM_ASN1Object_to_jlong(%p) => bio == null", bio);
        return 0;
    }

    T* x = PEM_read_func(bio, NULL, NULL, NULL);
    if (x == NULL) {
        throwExceptionIfNecessary(env, "PEM_ASN1Object_to_jlong");
        // Sometimes the PEM functions fail without pushing an error
        if (!env->ExceptionCheck()) {
            jniThrowRuntimeException(env, "Failure parsing PEM");
        }
        JNI_TRACE("PEM_ASN1Object_to_jlong(%p) => threw exception", bio);
        return 0;
    }

    JNI_TRACE("PEM_ASN1Object_to_jlong(%p) => %p", bio, x);
    return reinterpret_cast<uintptr_t>(x);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_PEM_1read_1bio_1X509(JNIEnv* env, jclass, jlong bioRef) {
    JNI_TRACE("PEM_read_bio_X509(0x%llx)", bioRef);
    return PEM_ASN1Object_to_jlong<X509, PEM_read_bio_X509>(env, bioRef);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_PEM_1read_1bio_1X509_1CRL(JNIEnv* env, jclass, jlong bioRef) {
    JNI_TRACE("PEM_read_bio_X509_CRL(0x%llx)", bioRef);
    return PEM_ASN1Object_to_jlong<X509_CRL, PEM_read_bio_X509_CRL>(env, bioRef);
}

static STACK_OF(X509)* PKCS7_get_certs(PKCS7* pkcs7) {
    if (PKCS7_type_is_signed(pkcs7)) {
        return pkcs7->d.sign->cert;
    } else if (PKCS7_type_is_signedAndEnveloped(pkcs7)) {
        return pkcs7->d.signed_and_enveloped->cert;
    } else {
        JNI_TRACE("PKCS7_get_certs(%p) => unknown PKCS7 type", pkcs7);
        return NULL;
    }
}

static STACK_OF(X509_CRL)* PKCS7_get_CRLs(PKCS7* pkcs7) {
    if (PKCS7_type_is_signed(pkcs7)) {
        return pkcs7->d.sign->crl;
    } else if (PKCS7_type_is_signedAndEnveloped(pkcs7)) {
        return pkcs7->d.signed_and_enveloped->crl;
    } else {
        JNI_TRACE("PKCS7_get_CRLs(%p) => unknown PKCS7 type", pkcs7);
        return NULL;
    }
}

template <typename T, typename T_stack>
static jlongArray PKCS7_to_ItemArray(JNIEnv* env, T_stack* stack, T* (*dup_func)(T*))
{
    if (stack == NULL) {
        return NULL;
    }

    ScopedLocalRef<jlongArray> ref_array(env, NULL);
    size_t size = sk_num(reinterpret_cast<_STACK*>(stack));
    ref_array.reset(env->NewLongArray(size));
    ScopedLongArrayRW items(env, ref_array.get());
    for (size_t i = 0; i < size; i++) {
        T* item = reinterpret_cast<T*>(sk_value(reinterpret_cast<_STACK*>(stack), i));
        items[i] = reinterpret_cast<uintptr_t>(dup_func(item));
    }

    JNI_TRACE("PKCS7_to_ItemArray(%p) => %p [size=%d]", stack, ref_array.get(), size);
    return ref_array.release();
}

#define PKCS7_CERTS 1
#define PKCS7_CRLS 2

extern "C" jlongArray Java_com_android_org_conscrypt_NativeCrypto_PEM_1read_1bio_1PKCS7(JNIEnv* env, jclass, jlong bioRef, jint which) {
    BIO* bio = reinterpret_cast<BIO*>(static_cast<uintptr_t>(bioRef));
    JNI_TRACE("PEM_read_bio_PKCS7_CRLs(%p)", bio);

    if (bio == NULL) {
        jniThrowNullPointerException(env, "bio == null");
        JNI_TRACE("PEM_read_bio_PKCS7_CRLs(%p) => bio == null", bio);
        return 0;
    }

    Unique_PKCS7 pkcs7(PEM_read_bio_PKCS7(bio, NULL, NULL, NULL));
    if (pkcs7.get() == NULL) {
        throwExceptionIfNecessary(env, "PEM_read_bio_PKCS7_CRLs");
        JNI_TRACE("PEM_read_bio_PKCS7_CRLs(%p) => threw exception", bio);
        return 0;
    }

    switch (which) {
    case PKCS7_CERTS:
        return PKCS7_to_ItemArray<X509, STACK_OF(X509)>(env, PKCS7_get_certs(pkcs7.get()), X509_dup);
    case PKCS7_CRLS:
        return PKCS7_to_ItemArray<X509_CRL, STACK_OF(X509_CRL)>(env, PKCS7_get_CRLs(pkcs7.get()),
                X509_CRL_dup);
    default:
        jniThrowRuntimeException(env, "unknown PKCS7 field");
        return NULL;
    }
}

extern "C" jlongArray Java_com_android_org_conscrypt_NativeCrypto_d2i_1PKCS7_1bio(JNIEnv* env, jclass, jlong bioRef, jint which) {
    BIO* bio = reinterpret_cast<BIO*>(static_cast<uintptr_t>(bioRef));
    JNI_TRACE("d2i_PKCS7_bio(%p, %d)", bio, which);

    if (bio == NULL) {
        jniThrowNullPointerException(env, "bio == null");
        JNI_TRACE("d2i_PKCS7_bio(%p, %d) => bio == null", bio, which);
        return 0;
    }

    Unique_PKCS7 pkcs7(d2i_PKCS7_bio(bio, NULL));
    if (pkcs7.get() == NULL) {
        throwExceptionIfNecessary(env, "d2i_PKCS7_bio");
        JNI_TRACE("d2i_PKCS7_bio(%p, %d) => threw exception", bio, which);
        return 0;
    }

    switch (which) {
    case PKCS7_CERTS:
        return PKCS7_to_ItemArray<X509, STACK_OF(X509)>(env, PKCS7_get_certs(pkcs7.get()), X509_dup);
    case PKCS7_CRLS:
        return PKCS7_to_ItemArray<X509_CRL, STACK_OF(X509_CRL)>(env, PKCS7_get_CRLs(pkcs7.get()),
                X509_CRL_dup);
    default:
        jniThrowRuntimeException(env, "unknown PKCS7 field");
        return NULL;
    }
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_i2d_1PKCS7(JNIEnv* env, jclass, jlongArray certsArray) {
    JNI_TRACE("i2d_PKCS7(%p)", certsArray);

    Unique_PKCS7 pkcs7(PKCS7_new());
    if (pkcs7.get() == NULL) {
        jniThrowNullPointerException(env, "pkcs7 == null");
        JNI_TRACE("i2d_PKCS7(%p) => pkcs7 == null", certsArray);
        return NULL;
    }

    if (PKCS7_set_type(pkcs7.get(), NID_pkcs7_signed) != 1) {
        throwExceptionIfNecessary(env, "PKCS7_set_type");
        return NULL;
    }

    ScopedLongArrayRO certs(env, certsArray);
    for (size_t i = 0; i < certs.size(); i++) {
        X509* item = reinterpret_cast<X509*>(certs[i]);
        if (PKCS7_add_certificate(pkcs7.get(), item) != 1) {
            throwExceptionIfNecessary(env, "i2d_PKCS7");
            return NULL;
        }
    }

    JNI_TRACE("i2d_PKCS7(%p) => %d certs", certsArray, certs.size());
    return ASN1ToByteArray<PKCS7, i2d_PKCS7>(env, pkcs7.get());
}

typedef STACK_OF(X509) PKIPATH;

ASN1_ITEM_TEMPLATE(PKIPATH) =
    ASN1_EX_TEMPLATE_TYPE(ASN1_TFLG_SEQUENCE_OF, 0, PkiPath, X509)
ASN1_ITEM_TEMPLATE_END(PKIPATH)

extern "C" jlongArray Java_com_android_org_conscrypt_NativeCrypto_ASN1_1seq_1unpack_1X509_1bio(JNIEnv* env, jclass, jlong bioRef) {
    BIO* bio = reinterpret_cast<BIO*>(static_cast<uintptr_t>(bioRef));
    JNI_TRACE("ASN1_seq_unpack_X509_bio(%p)", bio);

    Unique_sk_X509 path((PKIPATH*) ASN1_item_d2i_bio(ASN1_ITEM_rptr(PKIPATH), bio, NULL));
    if (path.get() == NULL) {
        throwExceptionIfNecessary(env, "ASN1_seq_unpack_X509_bio");
        return NULL;
    }

    size_t size = sk_X509_num(path.get());

    ScopedLocalRef<jlongArray> certArray(env, env->NewLongArray(size));
    ScopedLongArrayRW certs(env, certArray.get());
    for (size_t i = 0; i < size; i++) {
        X509* item = reinterpret_cast<X509*>(sk_X509_shift(path.get()));
        certs[i] = reinterpret_cast<uintptr_t>(item);
    }

    JNI_TRACE("ASN1_seq_unpack_X509_bio(%p) => returns %d items", bio, size);
    return certArray.release();
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_ASN1_1seq_1pack_1X509(JNIEnv* env, jclass, jlongArray certs) {
    JNI_TRACE("ASN1_seq_pack_X509(%p)", certs);
    ScopedLongArrayRO certsArray(env, certs);
    if (certsArray.get() == NULL) {
        JNI_TRACE("ASN1_seq_pack_X509(%p) => failed to get certs array", certs);
        return NULL;
    }

    Unique_sk_X509 certStack(sk_X509_new_null());
    if (certStack.get() == NULL) {
        JNI_TRACE("ASN1_seq_pack_X509(%p) => failed to make cert stack", certs);
        return NULL;
    }

    for (size_t i = 0; i < certsArray.size(); i++) {
        X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(certsArray[i]));
        sk_X509_push(certStack.get(), X509_dup_nocopy(x509));
    }

    int len;
    Unique_OPENSSL_str encoded(ASN1_seq_pack(
                    reinterpret_cast<STACK_OF(OPENSSL_BLOCK)*>(
                            reinterpret_cast<uintptr_t>(certStack.get())),
                    reinterpret_cast<int (*)(void*, unsigned char**)>(i2d_X509), NULL, &len));
    if (encoded.get() == NULL) {
        JNI_TRACE("ASN1_seq_pack_X509(%p) => trouble encoding", certs);
        return NULL;
    }

    ScopedLocalRef<jbyteArray> byteArray(env, env->NewByteArray(len));
    if (byteArray.get() == NULL) {
        JNI_TRACE("ASN1_seq_pack_X509(%p) => creating byte array failed", certs);
        return NULL;
    }

    ScopedByteArrayRW bytes(env, byteArray.get());
    if (bytes.get() == NULL) {
        JNI_TRACE("ASN1_seq_pack_X509(%p) => using byte array failed", certs);
        return NULL;
    }

    unsigned char* p = reinterpret_cast<unsigned char*>(bytes.get());
    memcpy(p, encoded.get(), len);

    return byteArray.release();
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_X509_1free(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("X509_free(%p)", x509);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("X509_free(%p) => x509 == null", x509);
        return;
    }

    X509_free(x509);
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_X509_1cmp(JNIEnv* env, jclass, jlong x509Ref1, jlong x509Ref2) {
    X509* x509_1 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref1));
    X509* x509_2 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref2));
    JNI_TRACE("X509_cmp(%p, %p)", x509_1, x509_2);

    if (x509_1 == NULL) {
        jniThrowNullPointerException(env, "x509_1 == null");
        JNI_TRACE("X509_cmp(%p, %p) => x509_1 == null", x509_1, x509_2);
        return -1;
    }

    if (x509_2 == NULL) {
        jniThrowNullPointerException(env, "x509_2 == null");
        JNI_TRACE("X509_cmp(%p, %p) => x509_2 == null", x509_1, x509_2);
        return -1;
    }

    int ret = X509_cmp(x509_1, x509_2);
    JNI_TRACE("X509_cmp(%p, %p) => %d", x509_1, x509_2, ret);
    return ret;
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1hashCode(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("get_X509_hashCode(%p) => x509 == null", x509);
        return 0;
    }

    // Force caching extensions.
    X509_check_ca(x509);

    jint hashCode = 0L;
    for (int i = 0; i < SHA_DIGEST_LENGTH; i++) {
        hashCode = 31 * hashCode + x509->sha1_hash[i];
    }
    return hashCode;
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_X509_1print_1ex(JNIEnv* env, jclass, jlong bioRef, jlong x509Ref,
        jlong nmflagJava, jlong certflagJava) {
    BIO* bio = reinterpret_cast<BIO*>(static_cast<uintptr_t>(bioRef));
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    long nmflag = static_cast<long>(nmflagJava);
    long certflag = static_cast<long>(certflagJava);
    JNI_TRACE("X509_print_ex(%p, %p, %ld, %ld)", bio, x509, nmflag, certflag);

    if (bio == NULL) {
        jniThrowNullPointerException(env, "bio == null");
        JNI_TRACE("X509_print_ex(%p, %p, %ld, %ld) => bio == null", bio, x509, nmflag, certflag);
        return;
    }

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("X509_print_ex(%p, %p, %ld, %ld) => x509 == null", bio, x509, nmflag, certflag);
        return;
    }

    if (!X509_print_ex(bio, x509, nmflag, certflag)) {
        throwExceptionIfNecessary(env, "X509_print_ex");
        JNI_TRACE("X509_print_ex(%p, %p, %ld, %ld) => threw error", bio, x509, nmflag, certflag);
    } else {
        JNI_TRACE("X509_print_ex(%p, %p, %ld, %ld) => success", bio, x509, nmflag, certflag);
    }
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_X509_1get_1pubkey(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("X509_get_pubkey(%p)", x509);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("X509_get_pubkey(%p) => x509 == null", x509);
        return 0;
    }

    Unique_EVP_PKEY pkey(X509_get_pubkey(x509));
    if (pkey.get() == NULL) {
        throwExceptionIfNecessary(env, "X509_get_pubkey");
        return 0;
    }

    return reinterpret_cast<uintptr_t>(pkey.release());
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_X509_1get_1issuer_1name(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("X509_get_issuer_name(%p)", x509);
    return ASN1ToByteArray<X509_NAME, i2d_X509_NAME>(env, X509_get_issuer_name(x509));
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_X509_1get_1subject_1name(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("X509_get_subject_name(%p)", x509);
    return ASN1ToByteArray<X509_NAME, i2d_X509_NAME>(env, X509_get_subject_name(x509));
}

extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1pubkey_1oid(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509_pubkey_oid(%p)", x509);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("get_X509_pubkey_oid(%p) => x509 == null", x509);
        return NULL;
    }

    X509_PUBKEY* pubkey = X509_get_X509_PUBKEY(x509);
    return ASN1_OBJECT_to_OID_string(env, pubkey->algor->algorithm);
}

extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1sig_1alg_1oid(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509_sig_alg_oid(%p)", x509);

    if (x509 == NULL || x509->sig_alg == NULL) {
        jniThrowNullPointerException(env, "x509 == NULL || x509->sig_alg == NULL");
        JNI_TRACE("get_X509_sig_alg_oid(%p) => x509 == NULL", x509);
        return NULL;
    }

    return ASN1_OBJECT_to_OID_string(env, x509->sig_alg->algorithm);
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1sig_1alg_1parameter(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509_sig_alg_parameter(%p)", x509);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("get_X509_sig_alg_parameter(%p) => x509 == null", x509);
        return NULL;
    }

    if (x509->sig_alg->parameter == NULL) {
        JNI_TRACE("get_X509_sig_alg_parameter(%p) => null", x509);
        return NULL;
    }

    return ASN1ToByteArray<ASN1_TYPE, i2d_ASN1_TYPE>(env, x509->sig_alg->parameter);
}

extern "C" jbooleanArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1issuerUID(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509_issuerUID(%p)", x509);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("get_X509_issuerUID(%p) => x509 == null", x509);
        return NULL;
    }

    if (x509->cert_info->issuerUID == NULL) {
        JNI_TRACE("get_X509_issuerUID(%p) => null", x509);
        return NULL;
    }

    return ASN1BitStringToBooleanArray(env, x509->cert_info->issuerUID);
}
extern "C" jbooleanArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1subjectUID(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509_subjectUID(%p)", x509);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("get_X509_subjectUID(%p) => x509 == null", x509);
        return NULL;
    }

    if (x509->cert_info->subjectUID == NULL) {
        JNI_TRACE("get_X509_subjectUID(%p) => null", x509);
        return NULL;
    }

    return ASN1BitStringToBooleanArray(env, x509->cert_info->subjectUID);
}

extern "C" jbooleanArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1ex_1kusage(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509_ex_kusage(%p)", x509);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("get_X509_ex_kusage(%p) => x509 == null", x509);
        return NULL;
    }

    Unique_ASN1_BIT_STRING bitStr(static_cast<ASN1_BIT_STRING*>(
            X509_get_ext_d2i(x509, NID_key_usage, NULL, NULL)));
    if (bitStr.get() == NULL) {
        JNI_TRACE("get_X509_ex_kusage(%p) => null", x509);
        return NULL;
    }

    return ASN1BitStringToBooleanArray(env, bitStr.get());
}

extern "C" jobjectArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1ex_1xkusage(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509_ex_xkusage(%p)", x509);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("get_X509_ex_xkusage(%p) => x509 == null", x509);
        return NULL;
    }

    Unique_sk_ASN1_OBJECT objArray(static_cast<STACK_OF(ASN1_OBJECT)*>(
            X509_get_ext_d2i(x509, NID_ext_key_usage, NULL, NULL)));
    if (objArray.get() == NULL) {
        JNI_TRACE("get_X509_ex_xkusage(%p) => null", x509);
        return NULL;
    }

    size_t size = sk_ASN1_OBJECT_num(objArray.get());
    ScopedLocalRef<jobjectArray> exKeyUsage(env, env->NewObjectArray(size, stringClass, NULL));
    if (exKeyUsage.get() == NULL) {
        return NULL;
    }

    for (size_t i = 0; i < size; i++) {
        ScopedLocalRef<jstring> oidStr(env, ASN1_OBJECT_to_OID_string(env,
                sk_ASN1_OBJECT_value(objArray.get(), i)));
        env->SetObjectArrayElement(exKeyUsage.get(), i, oidStr.get());
    }

    JNI_TRACE("get_X509_ex_xkusage(%p) => success (%d entries)", x509, size);
    return exKeyUsage.release();
}

extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1ex_1pathlen(JNIEnv* env, jclass, jlong x509Ref) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509_ex_pathlen(%p)", x509);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("get_X509_ex_pathlen(%p) => x509 == null", x509);
        return 0;
    }

    /* Just need to do this to cache the ex_* values. */
    X509_check_ca(x509);

    JNI_TRACE("get_X509_ex_pathlen(%p) => %ld", x509, x509->ex_pathlen);
    return x509->ex_pathlen;
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_X509_1get_1ext_1oid(JNIEnv* env, jclass, jlong x509Ref,
        jstring oidString) {
    X509* x509 = reinterpret_cast<X509*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("X509_get_ext_oid(%p, %p)", x509, oidString);
    return X509Type_get_ext_oid<X509, X509_get_ext_by_OBJ, X509_get_ext>(env, x509, oidString);
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_X509_1CRL_1get_1ext_1oid(JNIEnv* env, jclass, jlong x509CrlRef,
        jstring oidString) {
    X509_CRL* crl = reinterpret_cast<X509_CRL*>(static_cast<uintptr_t>(x509CrlRef));
    JNI_TRACE("X509_CRL_get_ext_oid(%p, %p)", crl, oidString);
    return X509Type_get_ext_oid<X509_CRL, X509_CRL_get_ext_by_OBJ, X509_CRL_get_ext>(env, crl,
            oidString);
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_X509_1REVOKED_1get_1ext_1oid(JNIEnv* env, jclass, jlong x509RevokedRef,
        jstring oidString) {
    X509_REVOKED* revoked = reinterpret_cast<X509_REVOKED*>(static_cast<uintptr_t>(x509RevokedRef));
    JNI_TRACE("X509_REVOKED_get_ext_oid(%p, %p)", revoked, oidString);
    return X509Type_get_ext_oid<X509_REVOKED, X509_REVOKED_get_ext_by_OBJ, X509_REVOKED_get_ext>(
            env, revoked, oidString);
}

template<typename T, int (*get_ext_by_critical_func)(T*, int, int), X509_EXTENSION* (*get_ext_func)(T*, int)>
static jobjectArray get_X509Type_ext_oids(JNIEnv* env, jlong x509Ref, jint critical) {
    T* x509 = reinterpret_cast<T*>(static_cast<uintptr_t>(x509Ref));
    JNI_TRACE("get_X509Type_ext_oids(%p, %d)", x509, critical);

    if (x509 == NULL) {
        jniThrowNullPointerException(env, "x509 == null");
        JNI_TRACE("get_X509Type_ext_oids(%p, %d) => x509 == null", x509, critical);
        return NULL;
    }

    int lastPos = -1;
    int count = 0;
    while ((lastPos = get_ext_by_critical_func(x509, critical, lastPos)) != -1) {
        count++;
    }

    JNI_TRACE("get_X509Type_ext_oids(%p, %d) has %d entries", x509, critical, count);

    ScopedLocalRef<jobjectArray> joa(env, env->NewObjectArray(count, stringClass, NULL));
    if (joa.get() == NULL) {
        JNI_TRACE("get_X509Type_ext_oids(%p, %d) => fail to allocate result array", x509, critical);
        return NULL;
    }

    lastPos = -1;
    count = 0;
    while ((lastPos = get_ext_by_critical_func(x509, critical, lastPos)) != -1) {
        X509_EXTENSION* ext = get_ext_func(x509, lastPos);

        ScopedLocalRef<jstring> extOid(env, ASN1_OBJECT_to_OID_string(env, ext->object));
        if (extOid.get() == NULL) {
            JNI_TRACE("get_X509Type_ext_oids(%p) => couldn't get OID", x509);
            return NULL;
        }

        env->SetObjectArrayElement(joa.get(), count++, extOid.get());
    }

    JNI_TRACE("get_X509Type_ext_oids(%p, %d) => success", x509, critical);
    return joa.release();
}

extern "C" jobjectArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1ext_1oids(JNIEnv* env, jclass, jlong x509Ref,
        jint critical) {
    JNI_TRACE("get_X509_ext_oids(0x%llx, %d)", x509Ref, critical);
    return get_X509Type_ext_oids<X509, X509_get_ext_by_critical, X509_get_ext>(env, x509Ref,
            critical);
}

extern "C" jobjectArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1CRL_1ext_1oids(JNIEnv* env, jclass, jlong x509CrlRef,
        jint critical) {
    JNI_TRACE("get_X509_CRL_ext_oids(0x%llx, %d)", x509CrlRef, critical);
    return get_X509Type_ext_oids<X509_CRL, X509_CRL_get_ext_by_critical, X509_CRL_get_ext>(env,
            x509CrlRef, critical);
}

extern "C" jobjectArray Java_com_android_org_conscrypt_NativeCrypto_get_1X509_1REVOKED_1ext_1oids(JNIEnv* env, jclass, jlong x509RevokedRef,
        jint critical) {
    JNI_TRACE("get_X509_CRL_ext_oids(0x%llx, %d)", x509RevokedRef, critical);
    return get_X509Type_ext_oids<X509_REVOKED, X509_REVOKED_get_ext_by_critical,
            X509_REVOKED_get_ext>(env, x509RevokedRef, critical);
}

#ifdef WITH_JNI_TRACE
/**
 * Based on example logging call back from SSL_CTX_set_info_callback man page
 */
static void info_callback_LOG(const SSL* s __attribute__ ((unused)), int where, int ret)
{
    int w = where & ~SSL_ST_MASK;
    const char* str;
    if (w & SSL_ST_CONNECT) {
        str = "SSL_connect";
    } else if (w & SSL_ST_ACCEPT) {
        str = "SSL_accept";
    } else {
        str = "undefined";
    }

    if (where & SSL_CB_LOOP) {
        JNI_TRACE("ssl=%p %s:%s %s", s, str, SSL_state_string(s), SSL_state_string_long(s));
    } else if (where & SSL_CB_ALERT) {
        str = (where & SSL_CB_READ) ? "read" : "write";
        JNI_TRACE("ssl=%p SSL3 alert %s:%s:%s %s %s",
                  s,
                  str,
                  SSL_alert_type_string(ret),
                  SSL_alert_desc_string(ret),
                  SSL_alert_type_string_long(ret),
                  SSL_alert_desc_string_long(ret));
    } else if (where & SSL_CB_EXIT) {
        if (ret == 0) {
            JNI_TRACE("ssl=%p %s:failed exit in %s %s",
                      s, str, SSL_state_string(s), SSL_state_string_long(s));
        } else if (ret < 0) {
            JNI_TRACE("ssl=%p %s:error exit in %s %s",
                      s, str, SSL_state_string(s), SSL_state_string_long(s));
        } else if (ret == 1) {
            JNI_TRACE("ssl=%p %s:ok exit in %s %s",
                      s, str, SSL_state_string(s), SSL_state_string_long(s));
        } else {
            JNI_TRACE("ssl=%p %s:unknown exit %d in %s %s",
                      s, str, ret, SSL_state_string(s), SSL_state_string_long(s));
        }
    } else if (where & SSL_CB_HANDSHAKE_START) {
        JNI_TRACE("ssl=%p handshake start in %s %s",
                  s, SSL_state_string(s), SSL_state_string_long(s));
    } else if (where & SSL_CB_HANDSHAKE_DONE) {
        JNI_TRACE("ssl=%p handshake done in %s %s",
                  s, SSL_state_string(s), SSL_state_string_long(s));
    } else {
        JNI_TRACE("ssl=%p %s:unknown where %d in %s %s",
                  s, str, where, SSL_state_string(s), SSL_state_string_long(s));
    }
}
#endif

/**
 * Returns an array containing all the X509 certificate's bytes.
 */
static jobjectArray getCertificateBytes(JNIEnv* env, const STACK_OF(X509)* chain)
{
    if (chain == NULL) {
        // Chain can be NULL if the associated cipher doesn't do certs.
        return NULL;
    }

    int count = sk_X509_num(chain);
    if (count <= 0) {
        return NULL;
    }

    jobjectArray joa = env->NewObjectArray(count, byteArrayClass, NULL);
    if (joa == NULL) {
        return NULL;
    }

    for (int i = 0; i < count; i++) {
        X509* cert = sk_X509_value(chain, i);

        ScopedLocalRef<jbyteArray> byteArray(env, ASN1ToByteArray<X509, i2d_X509>(env, cert));
        if (byteArray.get() == NULL) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, i, byteArray.get());
    }

    return joa;
}

/**
 * Returns an array containing all the X500 principal's bytes.
 */
static jobjectArray getPrincipalBytes(JNIEnv* env, const STACK_OF(X509_NAME)* names)
{
    if (names == NULL) {
        return NULL;
    }

    int count = sk_X509_NAME_num(names);
    if (count <= 0) {
        return NULL;
    }

    ScopedLocalRef<jobjectArray> joa(env, env->NewObjectArray(count, byteArrayClass, NULL));
    if (joa.get() == NULL) {
        return NULL;
    }

    for (int i = 0; i < count; i++) {
        X509_NAME* principal = sk_X509_NAME_value(names, i);

        ScopedLocalRef<jbyteArray> byteArray(env, ASN1ToByteArray<X509_NAME, i2d_X509_NAME>(env,
                principal));
        if (byteArray.get() == NULL) {
            return NULL;
        }
        env->SetObjectArrayElement(joa.get(), i, byteArray.get());
    }

    return joa.release();
}

/**
 * Our additional application data needed for getting synchronization right.
 * This maybe warrants a bit of lengthy prose:
 *
 * (1) We use a flag to reflect whether we consider the SSL connection alive.
 * Any read or write attempt loops will be cancelled once this flag becomes 0.
 *
 * (2) We use an int to count the number of threads that are blocked by the
 * underlying socket. This may be at most two (one reader and one writer), since
 * the Java layer ensures that no more threads will enter the native code at the
 * same time.
 *
 * (3) The pipe is used primarily as a means of cancelling a blocking select()
 * when we want to close the connection (aka "emergency button"). It is also
 * necessary for dealing with a possible race condition situation: There might
 * be cases where both threads see an SSL_ERROR_WANT_READ or
 * SSL_ERROR_WANT_WRITE. Both will enter a select() with the proper argument.
 * If one leaves the select() successfully before the other enters it, the
 * "success" event is already consumed and the second thread will be blocked,
 * possibly forever (depending on network conditions).
 *
 * The idea for solving the problem looks like this: Whenever a thread is
 * successful in moving around data on the network, and it knows there is
 * another thread stuck in a select(), it will write a byte to the pipe, waking
 * up the other thread. A thread that returned from select(), on the other hand,
 * knows whether it's been woken up by the pipe. If so, it will consume the
 * byte, and the original state of affairs has been restored.
 *
 * The pipe may seem like a bit of overhead, but it fits in nicely with the
 * other file descriptors of the select(), so there's only one condition to wait
 * for.
 *
 * (4) Finally, a mutex is needed to make sure that at most one thread is in
 * either SSL_read() or SSL_write() at any given time. This is an OpenSSL
 * requirement. We use the same mutex to guard the field for counting the
 * waiting threads.
 *
 * Note: The current implementation assumes that we don't have to deal with
 * problems induced by multiple cores or processors and their respective
 * memory caches. One possible problem is that of inconsistent views on the
 * "aliveAndKicking" field. This could be worked around by also enclosing all
 * accesses to that field inside a lock/unlock sequence of our mutex, but
 * currently this seems a bit like overkill. Marking volatile at the very least.
 *
 * During handshaking, additional fields are used to up-call into
 * Java to perform certificate verification and handshake
 * completion. These are also used in any renegotiation.
 *
 * (5) the JNIEnv so we can invoke the Java callback
 *
 * (6) a NativeCrypto.SSLHandshakeCallbacks instance for callbacks from native to Java
 *
 * (7) a java.io.FileDescriptor wrapper to check for socket close
 *
 * We store the NPN protocols list so we can either send it (from the server) or
 * select a protocol (on the client). We eagerly acquire a pointer to the array
 * data so the callback doesn't need to acquire resources that it cannot
 * release.
 *
 * Because renegotiation can be requested by the peer at any time,
 * care should be taken to maintain an appropriate JNIEnv on any
 * downcall to openssl since it could result in an upcall to Java. The
 * current code does try to cover these cases by conditionally setting
 * the JNIEnv on calls that can read and write to the SSL such as
 * SSL_do_handshake, SSL_read, SSL_write, and SSL_shutdown.
 *
 * Finally, we have two emphemeral keys setup by OpenSSL callbacks:
 *
 * (8) a set of ephemeral RSA keys that is lazily generated if a peer
 * wants to use an exportable RSA cipher suite.
 *
 * (9) a set of ephemeral EC keys that is lazily generated if a peer
 * wants to use an TLS_ECDHE_* cipher suite.
 *
 */
class AppData {
  public:
    volatile int aliveAndKicking;
    int waitingThreads;
    int fdsEmergency[2];
    MUTEX_TYPE mutex;
    JNIEnv* env;
    jobject sslHandshakeCallbacks;
    jobject fileDescriptor;
    jbyteArray npnProtocolsArray;
    jbyte* npnProtocolsData;
    size_t npnProtocolsLength;
    jbyteArray alpnProtocolsArray;
    jbyte* alpnProtocolsData;
    size_t alpnProtocolsLength;
    Unique_RSA ephemeralRsa;
    Unique_EC_KEY ephemeralEc;

    /**
     * Creates the application data context for the SSL*.
     */
  public:
    static AppData* create() {
        UniquePtr<AppData> appData(new AppData());
        if (pipe(appData.get()->fdsEmergency) == -1) {
            ALOGE("AppData::create pipe(2) failed: %s", strerror(errno));
            return NULL;
        }
        if (!setBlocking(appData.get()->fdsEmergency[0], false)) {
            ALOGE("AppData::create fcntl(2) failed: %s", strerror(errno));
            return NULL;
        }
        if (MUTEX_SETUP(appData.get()->mutex) == -1) {
            ALOGE("pthread_mutex_init(3) failed: %s", strerror(errno));
            return NULL;
        }
        return appData.release();
    }

    ~AppData() {
        aliveAndKicking = 0;
        if (fdsEmergency[0] != -1) {
            close(fdsEmergency[0]);
        }
        if (fdsEmergency[1] != -1) {
            close(fdsEmergency[1]);
        }
        clearCallbackState();
        MUTEX_CLEANUP(mutex);
    }

  private:
    AppData() :
            aliveAndKicking(1),
            waitingThreads(0),
            env(NULL),
            sslHandshakeCallbacks(NULL),
            npnProtocolsArray(NULL),
            npnProtocolsData(NULL),
            npnProtocolsLength(-1),
            alpnProtocolsArray(NULL),
            alpnProtocolsData(NULL),
            alpnProtocolsLength(-1),
            ephemeralRsa(NULL),
            ephemeralEc(NULL) {
        fdsEmergency[0] = -1;
        fdsEmergency[1] = -1;
    }

  public:
    /**
     * Used to set the SSL-to-Java callback state before each SSL_*
     * call that may result in a callback. It should be cleared after
     * the operation returns with clearCallbackState.
     *
     * @param env The JNIEnv
     * @param shc The SSLHandshakeCallbacks
     * @param fd The FileDescriptor
     * @param npnProtocols NPN protocols so that they may be advertised (by the
     *                     server) or selected (by the client). Has no effect
     *                     unless NPN is enabled.
     * @param alpnProtocols ALPN protocols so that they may be advertised (by the
     *                     server) or selected (by the client). Passing non-NULL
     *                     enables ALPN.
     */
    bool setCallbackState(JNIEnv* e, jobject shc, jobject fd, jbyteArray npnProtocols,
            jbyteArray alpnProtocols) {
        NetFd netFd(e, fd);
        if (netFd.isClosed()) {
            return false;
        }
        env = e;
        sslHandshakeCallbacks = shc;
        fileDescriptor = fd;
        if (npnProtocols != NULL) {
            npnProtocolsData = e->GetByteArrayElements(npnProtocols, NULL);
            if (npnProtocolsData == NULL) {
                clearCallbackState();
                return false;
            }
            npnProtocolsArray = npnProtocols;
            npnProtocolsLength = e->GetArrayLength(npnProtocols);
        }
        if (alpnProtocols != NULL) {
            alpnProtocolsData = e->GetByteArrayElements(alpnProtocols, NULL);
            if (alpnProtocolsData == NULL) {
                clearCallbackState();
                return false;
            }
            alpnProtocolsArray = alpnProtocols;
            alpnProtocolsLength = e->GetArrayLength(alpnProtocols);
        }
        return true;
    }

    void clearCallbackState() {
        sslHandshakeCallbacks = NULL;
        fileDescriptor = NULL;
        if (npnProtocolsArray != NULL) {
            env->ReleaseByteArrayElements(npnProtocolsArray, npnProtocolsData, JNI_ABORT);
            npnProtocolsArray = NULL;
            npnProtocolsData = NULL;
            npnProtocolsLength = -1;
        }
        if (alpnProtocolsArray != NULL) {
            env->ReleaseByteArrayElements(alpnProtocolsArray, alpnProtocolsData, JNI_ABORT);
            alpnProtocolsArray = NULL;
            alpnProtocolsData = NULL;
            alpnProtocolsLength = -1;
        }
        env = NULL;
    }

};

/**
 * Dark magic helper function that checks, for a given SSL session, whether it
 * can SSL_read() or SSL_write() without blocking. Takes into account any
 * concurrent attempts to close the SSLSocket from the Java side. This is
 * needed to get rid of the hangs that occur when thread #1 closes the SSLSocket
 * while thread #2 is sitting in a blocking read or write. The type argument
 * specifies whether we are waiting for readability or writability. It expects
 * to be passed either SSL_ERROR_WANT_READ or SSL_ERROR_WANT_WRITE, since we
 * only need to wait in case one of these problems occurs.
 *
 * @param env
 * @param type Either SSL_ERROR_WANT_READ or SSL_ERROR_WANT_WRITE
 * @param fdObject The FileDescriptor, since appData->fileDescriptor should be NULL
 * @param appData The application data structure with mutex info etc.
 * @param timeout_millis The timeout value for select call, with the special value
 *                0 meaning no timeout at all (wait indefinitely). Note: This is
 *                the Java semantics of the timeout value, not the usual
 *                select() semantics.
 * @return The result of the inner select() call,
 * THROW_SOCKETEXCEPTION if a SocketException was thrown, -1 on
 * additional errors
 */
static int sslSelect(JNIEnv* env, int type, jobject fdObject, AppData* appData, int timeout_millis) {
    // This loop is an expanded version of the NET_FAILURE_RETRY
    // macro. It cannot simply be used in this case because select
    // cannot be restarted without recreating the fd_sets and timeout
    // structure.
    int result;
    fd_set rfds;
    fd_set wfds;
    do {
        NetFd fd(env, fdObject);
        if (fd.isClosed()) {
            result = THROWN_EXCEPTION;
            break;
        }
        int intFd = fd.get();
        JNI_TRACE("sslSelect type=%s fd=%d appData=%p timeout_millis=%d",
                  (type == SSL_ERROR_WANT_READ) ? "READ" : "WRITE", intFd, appData, timeout_millis);

        FD_ZERO(&rfds);
        FD_ZERO(&wfds);

        if (type == SSL_ERROR_WANT_READ) {
            FD_SET(intFd, &rfds);
        } else {
            FD_SET(intFd, &wfds);
        }

        FD_SET(appData->fdsEmergency[0], &rfds);

        int maxFd = (intFd > appData->fdsEmergency[0]) ? intFd : appData->fdsEmergency[0];

        // Build a struct for the timeout data if we actually want a timeout.
        timeval tv;
        timeval* ptv;
        if (timeout_millis > 0) {
            tv.tv_sec = timeout_millis / 1000;
            tv.tv_usec = (timeout_millis % 1000) * 1000;
            ptv = &tv;
        } else {
            ptv = NULL;
        }

        AsynchronousSocketCloseMonitor monitor(intFd);
        result = select(maxFd + 1, &rfds, &wfds, NULL, ptv);
        JNI_TRACE("sslSelect %s fd=%d appData=%p timeout_millis=%d => %d",
                  (type == SSL_ERROR_WANT_READ) ? "READ" : "WRITE",
                  fd.get(), appData, timeout_millis, result);
        if (result == -1) {
            if (fd.isClosed()) {
                result = THROWN_EXCEPTION;
                break;
            }
            if (errno != EINTR) {
                break;
            }
        }
    } while (result == -1);

    if (MUTEX_LOCK(appData->mutex) == -1) {
        return -1;
    }

    if (result > 0) {
        // We have been woken up by a token in the emergency pipe. We
        // can't be sure the token is still in the pipe at this point
        // because it could have already been read by the thread that
        // originally wrote it if it entered sslSelect and acquired
        // the mutex before we did. Thus we cannot safely read from
        // the pipe in a blocking way (so we make the pipe
        // non-blocking at creation).
        if (FD_ISSET(appData->fdsEmergency[0], &rfds)) {
            char token;
            do {
                read(appData->fdsEmergency[0], &token, 1);
            } while (errno == EINTR);
        }
    }

    // Tell the world that there is now one thread less waiting for the
    // underlying network.
    appData->waitingThreads--;

    MUTEX_UNLOCK(appData->mutex);

    return result;
}

/**
 * Helper function that wakes up a thread blocked in select(), in case there is
 * one. Is being called by sslRead() and sslWrite() as well as by JNI glue
 * before closing the connection.
 *
 * @param data The application data structure with mutex info etc.
 */
static void sslNotify(AppData* appData) {
    // Write a byte to the emergency pipe, so a concurrent select() can return.
    // Note we have to restore the errno of the original system call, since the
    // caller relies on it for generating error messages.
    int errnoBackup = errno;
    char token = '*';
    do {
        errno = 0;
        write(appData->fdsEmergency[1], &token, 1);
    } while (errno == EINTR);
    errno = errnoBackup;
}

static AppData* toAppData(const SSL* ssl) {
    return reinterpret_cast<AppData*>(SSL_get_app_data(ssl));
}

/**
 * Verify the X509 certificate via SSL_CTX_set_cert_verify_callback
 */
static int cert_verify_callback(X509_STORE_CTX* x509_store_ctx, void* arg __attribute__ ((unused)))
{
    /* Get the correct index to the SSLobject stored into X509_STORE_CTX. */
    SSL* ssl = reinterpret_cast<SSL*>(X509_STORE_CTX_get_ex_data(x509_store_ctx,
            SSL_get_ex_data_X509_STORE_CTX_idx()));
    JNI_TRACE("ssl=%p cert_verify_callback x509_store_ctx=%p arg=%p", ssl, x509_store_ctx, arg);

    AppData* appData = toAppData(ssl);
    JNIEnv* env = appData->env;
    if (env == NULL) {
        ALOGE("AppData->env missing in cert_verify_callback");
        JNI_TRACE("ssl=%p cert_verify_callback => 0", ssl);
        return 0;
    }
    jobject sslHandshakeCallbacks = appData->sslHandshakeCallbacks;

    jclass cls = env->GetObjectClass(sslHandshakeCallbacks);
    jmethodID methodID
        = env->GetMethodID(cls, "verifyCertificateChain", "([[BLjava/lang/String;)V");

    jobjectArray objectArray = getCertificateBytes(env, x509_store_ctx->untrusted);

    const char* authMethod = SSL_authentication_method(ssl);
    JNI_TRACE("ssl=%p cert_verify_callback calling verifyCertificateChain authMethod=%s",
              ssl, authMethod);
    jstring authMethodString = env->NewStringUTF(authMethod);
    env->CallVoidMethod(sslHandshakeCallbacks, methodID, objectArray, authMethodString);

    int result = (env->ExceptionCheck()) ? 0 : 1;
    JNI_TRACE("ssl=%p cert_verify_callback => %d", ssl, result);
    return result;
}

/**
 * Call back to watch for handshake to be completed. This is necessary
 * for SSL_MODE_HANDSHAKE_CUTTHROUGH support, since SSL_do_handshake
 * returns before the handshake is completed in this case.
 */
static void info_callback(const SSL* ssl, int where, int ret __attribute__ ((unused))) {
    JNI_TRACE("ssl=%p info_callback where=0x%x ret=%d", ssl, where, ret);
#ifdef WITH_JNI_TRACE
    info_callback_LOG(ssl, where, ret);
#endif
    if (!(where & SSL_CB_HANDSHAKE_DONE)) {
        JNI_TRACE("ssl=%p info_callback ignored", ssl);
        return;
    }

    AppData* appData = toAppData(ssl);
    JNIEnv* env = appData->env;
    if (env == NULL) {
        ALOGE("AppData->env missing in info_callback");
        JNI_TRACE("ssl=%p info_callback env error", ssl);
        return;
    }
    if (env->ExceptionCheck()) {
        JNI_TRACE("ssl=%p info_callback already pending exception", ssl);
        return;
    }

    jobject sslHandshakeCallbacks = appData->sslHandshakeCallbacks;

    jclass cls = env->GetObjectClass(sslHandshakeCallbacks);
    jmethodID methodID = env->GetMethodID(cls, "handshakeCompleted", "()V");

    JNI_TRACE("ssl=%p info_callback calling handshakeCompleted", ssl);
    env->CallVoidMethod(sslHandshakeCallbacks, methodID);

    if (env->ExceptionCheck()) {
        JNI_TRACE("ssl=%p info_callback exception", ssl);
    }
    JNI_TRACE("ssl=%p info_callback completed", ssl);
}

/**
 * Call back to ask for a client certificate. There are three possible exit codes:
 *
 * 1 is success. x509Out and pkeyOut should point to the correct private key and certificate.
 * 0 is unable to find key. x509Out and pkeyOut should be NULL.
 * -1 is error and it doesn't matter what x509Out and pkeyOut are.
 */
static int client_cert_cb(SSL* ssl, X509** x509Out, EVP_PKEY** pkeyOut) {
    JNI_TRACE("ssl=%p client_cert_cb x509Out=%p pkeyOut=%p", ssl, x509Out, pkeyOut);

    /* Clear output of key and certificate in case of early exit due to error. */
    *x509Out = NULL;
    *pkeyOut = NULL;

    AppData* appData = toAppData(ssl);
    JNIEnv* env = appData->env;
    if (env == NULL) {
        ALOGE("AppData->env missing in client_cert_cb");
        JNI_TRACE("ssl=%p client_cert_cb env error => 0", ssl);
        return 0;
    }
    if (env->ExceptionCheck()) {
        JNI_TRACE("ssl=%p client_cert_cb already pending exception => 0", ssl);
        return -1;
    }
    jobject sslHandshakeCallbacks = appData->sslHandshakeCallbacks;

    jclass cls = env->GetObjectClass(sslHandshakeCallbacks);
    jmethodID methodID
        = env->GetMethodID(cls, "clientCertificateRequested", "([B[[B)V");

    // Call Java callback which can use SSL_use_certificate and SSL_use_PrivateKey to set values
    char ssl2_ctype = SSL3_CT_RSA_SIGN;
    const char* ctype = NULL;
    int ctype_num = 0;
    jobjectArray issuers = NULL;
    switch (ssl->version) {
        case SSL2_VERSION:
            ctype = &ssl2_ctype;
            ctype_num = 1;
            break;
        case SSL3_VERSION:
        case TLS1_VERSION:
        case TLS1_1_VERSION:
        case TLS1_2_VERSION:
        case DTLS1_VERSION:
            ctype = ssl->s3->tmp.ctype;
            ctype_num = ssl->s3->tmp.ctype_num;
            issuers = getPrincipalBytes(env, ssl->s3->tmp.ca_names);
            break;
    }
#ifdef WITH_JNI_TRACE
    for (int i = 0; i < ctype_num; i++) {
        JNI_TRACE("ssl=%p clientCertificateRequested keyTypes[%d]=%d", ssl, i, ctype[i]);
    }
#endif

    jbyteArray keyTypes = env->NewByteArray(ctype_num);
    if (keyTypes == NULL) {
        JNI_TRACE("ssl=%p client_cert_cb bytes == null => 0", ssl);
        return 0;
    }
    env->SetByteArrayRegion(keyTypes, 0, ctype_num, reinterpret_cast<const jbyte*>(ctype));

    JNI_TRACE("ssl=%p clientCertificateRequested calling clientCertificateRequested "
              "keyTypes=%p issuers=%p", ssl, keyTypes, issuers);
    env->CallVoidMethod(sslHandshakeCallbacks, methodID, keyTypes, issuers);

    if (env->ExceptionCheck()) {
        JNI_TRACE("ssl=%p client_cert_cb exception => 0", ssl);
        return -1;
    }

    // Check for values set from Java
    X509*     certificate = SSL_get_certificate(ssl);
    EVP_PKEY* privatekey  = SSL_get_privatekey(ssl);
    int result = 0;
    if (certificate != NULL && privatekey != NULL) {
        *x509Out = certificate;
        *pkeyOut = privatekey;
        result = 1;
    } else {
        // Some error conditions return NULL, so make sure it doesn't linger.
        freeOpenSslErrorState();
    }
    JNI_TRACE("ssl=%p client_cert_cb => *x509=%p *pkey=%p %d", ssl, *x509Out, *pkeyOut, result);
    return result;
}

static RSA* rsaGenerateKey(int keylength) {
    Unique_BIGNUM bn(BN_new());
    if (bn.get() == NULL) {
        return NULL;
    }
    int setWordResult = BN_set_word(bn.get(), RSA_F4);
    if (setWordResult != 1) {
        return NULL;
    }
    Unique_RSA rsa(RSA_new());
    if (rsa.get() == NULL) {
        return NULL;
    }
    int generateResult = RSA_generate_key_ex(rsa.get(), keylength, bn.get(), NULL);
    if (generateResult != 1) {
        return NULL;
    }
    return rsa.release();
}

/**
 * Call back to ask for an ephemeral RSA key for SSL_RSA_EXPORT_WITH_RC4_40_MD5 (aka EXP-RC4-MD5)
 */
static RSA* tmp_rsa_callback(SSL* ssl __attribute__ ((unused)),
                             int is_export __attribute__ ((unused)),
                             int keylength) {
    JNI_TRACE("ssl=%p tmp_rsa_callback is_export=%d keylength=%d", ssl, is_export, keylength);

    AppData* appData = toAppData(ssl);
    if (appData->ephemeralRsa.get() == NULL) {
        JNI_TRACE("ssl=%p tmp_rsa_callback generating ephemeral RSA key", ssl);
        appData->ephemeralRsa.reset(rsaGenerateKey(keylength));
    }
    JNI_TRACE("ssl=%p tmp_rsa_callback => %p", ssl, appData->ephemeralRsa.get());
    return appData->ephemeralRsa.get();
}

static DH* dhGenerateParameters(int keylength) {

    /*
     * The SSL_CTX_set_tmp_dh_callback(3SSL) man page discusses two
     * different options for generating DH keys. One is generating the
     * keys using a single set of DH parameters. However, generating
     * DH parameters is slow enough (minutes) that they suggest doing
     * it once at install time. The other is to generate DH keys from
     * DSA parameters. Generating DSA parameters is faster than DH
     * parameters, but to prevent small subgroup attacks, they needed
     * to be regenerated for each set of DH keys. Setting the
     * SSL_OP_SINGLE_DH_USE option make sure OpenSSL will call back
     * for new DH parameters every type it needs to generate DH keys.
     */
#if 0
    // Slow path that takes minutes but could be cached
    Unique_DH dh(DH_new());
    if (!DH_generate_parameters_ex(dh.get(), keylength, 2, NULL)) {
        return NULL;
    }
    return dh.release();
#else
    // Faster path but must have SSL_OP_SINGLE_DH_USE set
    Unique_DSA dsa(DSA_new());
    if (!DSA_generate_parameters_ex(dsa.get(), keylength, NULL, 0, NULL, NULL, NULL)) {
        return NULL;
    }
    DH* dh = DSA_dup_DH(dsa.get());
    return dh;
#endif
}

/**
 * Call back to ask for Diffie-Hellman parameters
 */
static DH* tmp_dh_callback(SSL* ssl __attribute__ ((unused)),
                           int is_export __attribute__ ((unused)),
                           int keylength) {
    JNI_TRACE("ssl=%p tmp_dh_callback is_export=%d keylength=%d", ssl, is_export, keylength);
    DH* tmp_dh = dhGenerateParameters(keylength);
    JNI_TRACE("ssl=%p tmp_dh_callback => %p", ssl, tmp_dh);
    return tmp_dh;
}

static EC_KEY* ecGenerateKey(int keylength __attribute__ ((unused))) {
    // TODO selected curve based on keylength
    Unique_EC_KEY ec(EC_KEY_new_by_curve_name(NID_X9_62_prime256v1));
    if (ec.get() == NULL) {
        return NULL;
    }
    return ec.release();
}

/**
 * Call back to ask for an ephemeral EC key for TLS_ECDHE_* cipher suites
 */
static EC_KEY* tmp_ecdh_callback(SSL* ssl __attribute__ ((unused)),
                                 int is_export __attribute__ ((unused)),
                                 int keylength) {
    JNI_TRACE("ssl=%p tmp_ecdh_callback is_export=%d keylength=%d", ssl, is_export, keylength);
    AppData* appData = toAppData(ssl);
    if (appData->ephemeralEc.get() == NULL) {
        JNI_TRACE("ssl=%p tmp_ecdh_callback generating ephemeral EC key", ssl);
        appData->ephemeralEc.reset(ecGenerateKey(keylength));
    }
    JNI_TRACE("ssl=%p tmp_ecdh_callback => %p", ssl, appData->ephemeralEc.get());
    return appData->ephemeralEc.get();
}

/*
 * public static native int SSL_CTX_new();
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_SSL_1CTX_1new(JNIEnv* env, jclass) {
    Unique_SSL_CTX sslCtx(SSL_CTX_new(SSLv23_method()));
    if (sslCtx.get() == NULL) {
        throwExceptionIfNecessary(env, "SSL_CTX_new");
        return 0;
    }
    SSL_CTX_set_options(sslCtx.get(),
                        SSL_OP_ALL
                        // Note: We explicitly do not allow SSLv2 to be used.
                        | SSL_OP_NO_SSLv2
                        // We also disable session tickets for better compatibility b/2682876
                        | SSL_OP_NO_TICKET
                        // We also disable compression for better compatibility b/2710492 b/2710497
                        | SSL_OP_NO_COMPRESSION
                        // Because dhGenerateParameters uses DSA_generate_parameters_ex
                        | SSL_OP_SINGLE_DH_USE
                        // Because ecGenerateParameters uses a fixed named curve
                        | SSL_OP_SINGLE_ECDH_USE);

    int mode = SSL_CTX_get_mode(sslCtx.get());
    /*
     * Turn on "partial write" mode. This means that SSL_write() will
     * behave like Posix write() and possibly return after only
     * writing a partial buffer. Note: The alternative, perhaps
     * surprisingly, is not that SSL_write() always does full writes
     * but that it will force you to retry write calls having
     * preserved the full state of the original call. (This is icky
     * and undesirable.)
     */
    mode |= SSL_MODE_ENABLE_PARTIAL_WRITE;

    // Reuse empty buffers within the SSL_CTX to save memory
    mode |= SSL_MODE_RELEASE_BUFFERS;

    SSL_CTX_set_mode(sslCtx.get(), mode);

    SSL_CTX_set_cert_verify_callback(sslCtx.get(), cert_verify_callback, NULL);
    SSL_CTX_set_info_callback(sslCtx.get(), info_callback);
    SSL_CTX_set_client_cert_cb(sslCtx.get(), client_cert_cb);
    SSL_CTX_set_tmp_rsa_callback(sslCtx.get(), tmp_rsa_callback);
    SSL_CTX_set_tmp_dh_callback(sslCtx.get(), tmp_dh_callback);
    SSL_CTX_set_tmp_ecdh_callback(sslCtx.get(), tmp_ecdh_callback);

    JNI_TRACE("NativeCrypto_SSL_CTX_new => %p", sslCtx.get());
    return (jlong) sslCtx.release();
}

/**
 * public static native void SSL_CTX_free(int ssl_ctx)
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1CTX_1free(JNIEnv* env,
        jclass, jlong ssl_ctx_address)
{
    SSL_CTX* ssl_ctx = to_SSL_CTX(env, ssl_ctx_address, true);
    JNI_TRACE("ssl_ctx=%p NativeCrypto_SSL_CTX_free", ssl_ctx);
    if (ssl_ctx == NULL) {
        return;
    }
    SSL_CTX_free(ssl_ctx);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1CTX_1set_1session_1id_1context(JNIEnv* env, jclass,
                                                        jlong ssl_ctx_address, jbyteArray sid_ctx)
{
    SSL_CTX* ssl_ctx = to_SSL_CTX(env, ssl_ctx_address, true);
    JNI_TRACE("ssl_ctx=%p NativeCrypto_SSL_CTX_set_session_id_context sid_ctx=%p", ssl_ctx, sid_ctx);
    if (ssl_ctx == NULL) {
        return;
    }

    ScopedByteArrayRO buf(env, sid_ctx);
    if (buf.get() == NULL) {
        JNI_TRACE("ssl_ctx=%p NativeCrypto_SSL_CTX_set_session_id_context => threw exception", ssl_ctx);
        return;
    }

    unsigned int length = buf.size();
    if (length > SSL_MAX_SSL_SESSION_ID_LENGTH) {
        jniThrowException(env, "java/lang/IllegalArgumentException",
                          "length > SSL_MAX_SSL_SESSION_ID_LENGTH");
        JNI_TRACE("NativeCrypto_SSL_CTX_set_session_id_context => length = %d", length);
        return;
    }
    const unsigned char* bytes = reinterpret_cast<const unsigned char*>(buf.get());
    int result = SSL_CTX_set_session_id_context(ssl_ctx, bytes, length);
    if (result == 0) {
        throwExceptionIfNecessary(env, "NativeCrypto_SSL_CTX_set_session_id_context");
        return;
    }
    JNI_TRACE("ssl_ctx=%p NativeCrypto_SSL_CTX_set_session_id_context => ok", ssl_ctx);
}

/**
 * public static native int SSL_new(int ssl_ctx) throws SSLException;
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_SSL_1new(JNIEnv* env, jclass, jlong ssl_ctx_address)
{
    SSL_CTX* ssl_ctx = to_SSL_CTX(env, ssl_ctx_address, true);
    JNI_TRACE("ssl_ctx=%p NativeCrypto_SSL_new", ssl_ctx);
    if (ssl_ctx == NULL) {
        return 0;
    }
    Unique_SSL ssl(SSL_new(ssl_ctx));
    if (ssl.get() == NULL) {
        throwSSLExceptionWithSslErrors(env, NULL, SSL_ERROR_NONE,
                "Unable to create SSL structure");
        JNI_TRACE("ssl_ctx=%p NativeCrypto_SSL_new => NULL", ssl_ctx);
        return 0;
    }

    /* Java code in class OpenSSLSocketImpl does the verification. Meaning of
     * SSL_VERIFY_NONE flag in client mode: if not using an anonymous cipher
     * (by default disabled), the server will send a certificate which will
     * be checked. The result of the certificate verification process can be
     * checked after the TLS/SSL handshake using the SSL_get_verify_result(3)
     * function. The handshake will be continued regardless of the
     * verification result.
     */
    SSL_set_verify(ssl.get(), SSL_VERIFY_NONE, NULL);

    JNI_TRACE("ssl_ctx=%p NativeCrypto_SSL_new => ssl=%p", ssl_ctx, ssl.get());
    return (jlong) ssl.release();
}


extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1enable_1tls_1channel_1id(JNIEnv* env, jclass, jlong ssl_address)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_NativeCrypto_SSL_enable_tls_channel_id", ssl);
    if (ssl == NULL) {
        return;
    }

    long ret = SSL_enable_tls_channel_id(ssl);
    if (ret != 1L) {
        ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error enabling Channel ID");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_enable_tls_channel_id => error", ssl);
        return;
    }
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_SSL_1get_1tls_1channel_1id(JNIEnv* env, jclass, jlong ssl_address)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_NativeCrypto_SSL_get_tls_channel_id", ssl);
    if (ssl == NULL) {
        return NULL;
    }

    // Channel ID is 64 bytes long. Unfortunately, OpenSSL doesn't declare this length
    // as a constant anywhere.
    jbyteArray javaBytes = env->NewByteArray(64);
    ScopedByteArrayRW bytes(env, javaBytes);
    if (bytes.get() == NULL) {
        JNI_TRACE("NativeCrypto_SSL_get_tls_channel_id(%p) => NULL", ssl);
        return NULL;
    }

    unsigned char* tmp = reinterpret_cast<unsigned char*>(bytes.get());
    // Unfortunately, the SSL_get_tls_channel_id method below always returns 64 (upon success)
    // regardless of the number of bytes copied into the output buffer "tmp". Thus, the correctness
    // of this code currently relies on the "tmp" buffer being exactly 64 bytes long.
    long ret = SSL_get_tls_channel_id(ssl, tmp, 64);
    if (ret == 0) {
        // Channel ID either not set or did not verify
        JNI_TRACE("NativeCrypto_SSL_get_tls_channel_id(%p) => not available", ssl);
        return NULL;
    } else if (ret != 64) {
        ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error getting Channel ID");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_get_tls_channel_id => error, returned %ld", ssl, ret);
        return NULL;
    }

    JNI_TRACE("ssl=%p NativeCrypto_NativeCrypto_SSL_get_tls_channel_id() => %p", ssl, javaBytes);
    return javaBytes;
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1set1_1tls_1channel_1id(JNIEnv* env, jclass,
        jlong ssl_address, jlong pkeyRef)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("ssl=%p SSL_set1_tls_channel_id privatekey=%p", ssl, pkey);
    if (ssl == NULL) {
        return;
    }

    if (pkey == NULL) {
        jniThrowNullPointerException(env, "pkey == null");
        JNI_TRACE("ssl=%p SSL_set1_tls_channel_id => pkey == null", ssl);
        return;
    }

    // SSL_set1_tls_channel_id requires ssl->server to be set to 0.
    // Unfortunately, the default value is 1 and it's only changed to 0 just
    // before the handshake starts (see NativeCrypto_SSL_do_handshake).
    ssl->server = 0;
    long ret = SSL_set1_tls_channel_id(ssl, pkey);

    if (ret != 1L) {
        ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
        throwSSLExceptionWithSslErrors(
                env, ssl, SSL_ERROR_NONE, "Error setting private key for Channel ID");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p SSL_set1_tls_channel_id => error", ssl);
        return;
    }
    // SSL_set1_tls_channel_id expects to take ownership of the EVP_PKEY, but
    // we have an external reference from the caller such as an OpenSSLKey,
    // so we manually increment the reference count here.
    CRYPTO_add(&pkey->references,+1,CRYPTO_LOCK_EVP_PKEY);

    JNI_TRACE("ssl=%p SSL_set1_tls_channel_id => ok", ssl);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1use_1PrivateKey(JNIEnv* env, jclass, jlong ssl_address, jlong pkeyRef) {
    SSL* ssl = to_SSL(env, ssl_address, true);
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("ssl=%p SSL_use_PrivateKey privatekey=%p", ssl, pkey);
    if (ssl == NULL) {
        return;
    }

    if (pkey == NULL) {
        jniThrowNullPointerException(env, "pkey == null");
        JNI_TRACE("ssl=%p SSL_use_PrivateKey => pkey == null", ssl);
        return;
    }

    int ret = SSL_use_PrivateKey(ssl, pkey);
    if (ret != 1) {
        ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error setting private key");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p SSL_use_PrivateKey => error", ssl);
        return;
    }
    // SSL_use_PrivateKey expects to take ownership of the EVP_PKEY,
    // but we have an external reference from the caller such as an
    // OpenSSLKey, so we manually increment the reference count here.
    CRYPTO_add(&pkey->references,+1,CRYPTO_LOCK_EVP_PKEY);

    JNI_TRACE("ssl=%p SSL_use_PrivateKey => ok", ssl);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1use_1certificate(JNIEnv* env, jclass,
                                             jlong ssl_address, jobjectArray certificates)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate certificates=%p", ssl, certificates);
    if (ssl == NULL) {
        return;
    }

    if (certificates == NULL) {
        jniThrowNullPointerException(env, "certificates == null");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => certificates == null", ssl);
        return;
    }

    int length = env->GetArrayLength(certificates);
    if (length == 0) {
        jniThrowException(env, "java/lang/IllegalArgumentException", "certificates.length == 0");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => certificates.length == 0", ssl);
        return;
    }

    X509Chain certificatesX509(length);
    for (int i = 0; i < length; i++) {
        ScopedLocalRef<jbyteArray> certificate(env,
                reinterpret_cast<jbyteArray>(env->GetObjectArrayElement(certificates, i)));
        if (certificate.get() == NULL) {
            jniThrowNullPointerException(env, "certificates element == null");
            JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => certificates element null", ssl);
            return;
        }

        ScopedByteArrayRO buf(env, certificate.get());
        if (buf.get() == NULL) {
            JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => threw exception", ssl);
            return;
        }
        const unsigned char* tmp = reinterpret_cast<const unsigned char*>(buf.get());
        certificatesX509[i] = d2i_X509(NULL, &tmp, buf.size());

        if (certificatesX509[i] == NULL) {
            ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
            throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error parsing certificate");
            SSL_clear(ssl);
            JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => certificates parsing error", ssl);
            return;
        }
    }

    int ret = SSL_use_certificate(ssl, certificatesX509[0]);
    if (ret == 1) {
        certificatesX509.release(0);
    } else {
        ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error setting certificate");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => SSL_use_certificate error", ssl);
        return;
    }

    Unique_sk_X509 chain(sk_X509_new_null());
    if (chain.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate local certificate chain");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => chain allocation error", ssl);
        return;
    }
    for (int i = 1; i < length; i++) {
        if (!sk_X509_push(chain.get(), certificatesX509.release(i))) {
            jniThrowOutOfMemory(env, "Unable to push certificate");
            JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => certificate push error", ssl);
            return;
        }
    }
    int chainResult = SSL_use_certificate_chain(ssl, chain.get());
    if (chainResult == 0) {
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error setting certificate chain");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => SSL_use_certificate_chain error",
                  ssl);
        return;
    } else {
        OWNERSHIP_TRANSFERRED(chain);
    }

    JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => ok", ssl);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1check_1private_1key(JNIEnv* env, jclass, jlong ssl_address)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_check_private_key", ssl);
    if (ssl == NULL) {
        return;
    }
    int ret = SSL_check_private_key(ssl);
    if (ret != 1) {
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error checking private key");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_check_private_key => error", ssl);
        return;
    }
    JNI_TRACE("ssl=%p NativeCrypto_SSL_check_private_key => ok", ssl);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1set_1client_1CA_1list(JNIEnv* env, jclass,
                                                jlong ssl_address, jobjectArray principals)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_client_CA_list principals=%p", ssl, principals);
    if (ssl == NULL) {
        return;
    }

    if (principals == NULL) {
        jniThrowNullPointerException(env, "principals == null");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_set_client_CA_list => principals == null", ssl);
        return;
    }

    int length = env->GetArrayLength(principals);
    if (length == 0) {
        jniThrowException(env, "java/lang/IllegalArgumentException", "principals.length == 0");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_set_client_CA_list => principals.length == 0", ssl);
        return;
    }

    Unique_sk_X509_NAME principalsStack(sk_X509_NAME_new_null());
    if (principalsStack.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate principal stack");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_set_client_CA_list => stack allocation error", ssl);
        return;
    }
    for (int i = 0; i < length; i++) {
        ScopedLocalRef<jbyteArray> principal(env,
                reinterpret_cast<jbyteArray>(env->GetObjectArrayElement(principals, i)));
        if (principal.get() == NULL) {
            jniThrowNullPointerException(env, "principals element == null");
            JNI_TRACE("ssl=%p NativeCrypto_SSL_set_client_CA_list => principals element null", ssl);
            return;
        }

        ScopedByteArrayRO buf(env, principal.get());
        if (buf.get() == NULL) {
            JNI_TRACE("ssl=%p NativeCrypto_SSL_set_client_CA_list => threw exception", ssl);
            return;
        }
        const unsigned char* tmp = reinterpret_cast<const unsigned char*>(buf.get());
        Unique_X509_NAME principalX509Name(d2i_X509_NAME(NULL, &tmp, buf.size()));

        if (principalX509Name.get() == NULL) {
            ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
            throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error parsing principal");
            SSL_clear(ssl);
            JNI_TRACE("ssl=%p NativeCrypto_SSL_set_client_CA_list => principals parsing error",
                      ssl);
            return;
        }

        if (!sk_X509_NAME_push(principalsStack.get(), principalX509Name.release())) {
            jniThrowOutOfMemory(env, "Unable to push principal");
            JNI_TRACE("ssl=%p NativeCrypto_SSL_set_client_CA_list => principal push error", ssl);
            return;
        }
    }

    SSL_set_client_CA_list(ssl, principalsStack.release());
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_client_CA_list => ok", ssl);
}

/**
 * public static native long SSL_get_mode(int ssl);
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_SSL_1get_1mode(JNIEnv* env, jclass, jlong ssl_address) {
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_mode", ssl);
    if (ssl == NULL) {
      return 0;
    }
    long mode = SSL_get_mode(ssl);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_mode => 0x%lx", ssl, mode);
    return mode;
}

/**
 * public static native long SSL_set_mode(int ssl, long mode);
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_SSL_1set_1mode(JNIEnv* env, jclass,
        jlong ssl_address, jlong mode) {
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_mode mode=0x%llx", ssl, mode);
    if (ssl == NULL) {
      return 0;
    }
    long result = SSL_set_mode(ssl, mode);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_mode => 0x%lx", ssl, result);
    return result;
}

/**
 * public static native long SSL_clear_mode(int ssl, long mode);
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_SSL_1clear_1mode(JNIEnv* env, jclass,
        jlong ssl_address, jlong mode) {
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_clear_mode mode=0x%llx", ssl, mode);
    if (ssl == NULL) {
      return 0;
    }
    long result = SSL_clear_mode(ssl, mode);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_clear_mode => 0x%lx", ssl, result);
    return result;
}

/**
 * public static native long SSL_get_options(int ssl);
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_SSL_1get_1options(JNIEnv* env, jclass,
        jlong ssl_address) {
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_options", ssl);
    if (ssl == NULL) {
      return 0;
    }
    long options = SSL_get_options(ssl);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_options => 0x%lx", ssl, options);
    return options;
}

/**
 * public static native long SSL_set_options(int ssl, long options);
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_SSL_1set_1options(JNIEnv* env, jclass,
        jlong ssl_address, jlong options) {
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_options options=0x%llx", ssl, options);
    if (ssl == NULL) {
      return 0;
    }
    long result = SSL_set_options(ssl, options);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_options => 0x%lx", ssl, result);
    return result;
}

/**
 * public static native long SSL_clear_options(int ssl, long options);
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_SSL_1clear_1options(JNIEnv* env, jclass,
        jlong ssl_address, jlong options) {
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_clear_options options=0x%llx", ssl, options);
    if (ssl == NULL) {
      return 0;
    }
    long result = SSL_clear_options(ssl, options);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_clear_options => 0x%lx", ssl, result);
    return result;
}

/**
 * Sets the ciphers suites that are enabled in the SSL
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1set_1cipher_1lists(JNIEnv* env, jclass,
        jlong ssl_address, jobjectArray cipherSuites)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_cipher_lists cipherSuites=%p", ssl, cipherSuites);
    if (ssl == NULL) {
        return;
    }
    if (cipherSuites == NULL) {
        jniThrowNullPointerException(env, "cipherSuites == null");
        return;
    }

    Unique_sk_SSL_CIPHER cipherstack(sk_SSL_CIPHER_new_null());
    if (cipherstack.get() == NULL) {
        jniThrowRuntimeException(env, "sk_SSL_CIPHER_new_null failed");
        return;
    }

    const SSL_METHOD* ssl_method = ssl->method;
    int num_ciphers = ssl_method->num_ciphers();

    int length = env->GetArrayLength(cipherSuites);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_cipher_lists length=%d", ssl, length);
    for (int i = 0; i < length; i++) {
        ScopedLocalRef<jstring> cipherSuite(env,
                reinterpret_cast<jstring>(env->GetObjectArrayElement(cipherSuites, i)));
        ScopedUtfChars c(env, cipherSuite.get());
        if (c.c_str() == NULL) {
            return;
        }
        JNI_TRACE("ssl=%p NativeCrypto_SSL_set_cipher_lists cipherSuite=%s", ssl, c.c_str());
        bool found = false;
        for (int j = 0; j < num_ciphers; j++) {
            const SSL_CIPHER* cipher = ssl_method->get_cipher(j);
            if ((strcmp(c.c_str(), cipher->name) == 0)
                    && (strcmp(SSL_CIPHER_get_version(cipher), "SSLv2"))) {
                if (!sk_SSL_CIPHER_push(cipherstack.get(), cipher)) {
                    jniThrowOutOfMemory(env, "Unable to push cipher");
                    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_cipher_lists => cipher push error", ssl);
                    return;
                }
                found = true;
            }
        }
        if (!found) {
            jniThrowException(env, "java/lang/IllegalArgumentException",
                              "Could not find cipher suite.");
            return;
        }
    }

    int rc = SSL_set_cipher_lists(ssl, cipherstack.get());
    if (rc == 0) {
        freeOpenSslErrorState();
        jniThrowException(env, "java/lang/IllegalArgumentException",
                          "Illegal cipher suite strings.");
    } else {
        OWNERSHIP_TRANSFERRED(cipherstack);
    }
}

/**
 * Sets certificate expectations, especially for server to request client auth
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1set_1verify(JNIEnv* env,
        jclass, jlong ssl_address, jint mode)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_verify mode=%x", ssl, mode);
    if (ssl == NULL) {
      return;
    }
    SSL_set_verify(ssl, (int)mode, NULL);
}

/**
 * Sets the ciphers suites that are enabled in the SSL
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1set_1session(JNIEnv* env, jclass,
        jlong ssl_address, jlong ssl_session_address)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    SSL_SESSION* ssl_session = to_SSL_SESSION(env, ssl_session_address, false);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_session ssl_session=%p", ssl, ssl_session);
    if (ssl == NULL) {
        return;
    }

    int ret = SSL_set_session(ssl, ssl_session);
    if (ret != 1) {
        /*
         * Translate the error, and throw if it turns out to be a real
         * problem.
         */
        int sslErrorCode = SSL_get_error(ssl, ret);
        if (sslErrorCode != SSL_ERROR_ZERO_RETURN) {
            throwSSLExceptionWithSslErrors(env, ssl, sslErrorCode, "SSL session set");
            SSL_clear(ssl);
        }
    }
}

/**
 * Sets the ciphers suites that are enabled in the SSL
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1set_1session_1creation_1enabled(JNIEnv* env, jclass,
        jlong ssl_address, jboolean creation_enabled)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_session_creation_enabled creation_enabled=%d",
              ssl, creation_enabled);
    if (ssl == NULL) {
        return;
    }
    SSL_set_session_creation_enabled(ssl, creation_enabled);
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1set_1tlsext_1host_1name(JNIEnv* env, jclass,
        jlong ssl_address, jstring hostname)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_tlsext_host_name hostname=%p",
              ssl, hostname);
    if (ssl == NULL) {
        return;
    }

    ScopedUtfChars hostnameChars(env, hostname);
    if (hostnameChars.c_str() == NULL) {
        return;
    }
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_tlsext_host_name hostnameChars=%s",
              ssl, hostnameChars.c_str());

    int ret = SSL_set_tlsext_host_name(ssl, hostnameChars.c_str());
    if (ret != 1) {
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error setting host name");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_set_tlsext_host_name => error", ssl);
        return;
    }
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_tlsext_host_name => ok", ssl);
}

extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_SSL_1get_1servername(JNIEnv* env, jclass, jlong ssl_address) {
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_servername", ssl);
    if (ssl == NULL) {
        return NULL;
    }
    const char* servername = SSL_get_servername(ssl, TLSEXT_NAMETYPE_host_name);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_servername => %s", ssl, servername);
    return env->NewStringUTF(servername);
}

/**
 * A common selection path for both NPN and ALPN since they're essentially the
 * same protocol. The list of protocols in "primary" is considered the order
 * which should take precedence.
 */
static int proto_select(SSL* ssl __attribute__ ((unused)),
        unsigned char **out, unsigned char *outLength,
        const unsigned char *primary, const unsigned int primaryLength,
        const unsigned char *secondary, const unsigned int secondaryLength) {
    if (primary != NULL) {
        JNI_TRACE("primary=%p, length=%d", primary, primaryLength);

        int status = SSL_select_next_proto(out, outLength, primary, primaryLength, secondary,
                secondaryLength);
        switch (status) {
        case OPENSSL_NPN_NEGOTIATED:
            JNI_TRACE("ssl=%p proto_select NPN/ALPN negotiated", ssl);
            return SSL_TLSEXT_ERR_OK;
            break;
        case OPENSSL_NPN_UNSUPPORTED:
            JNI_TRACE("ssl=%p proto_select NPN/ALPN unsupported", ssl);
            break;
        case OPENSSL_NPN_NO_OVERLAP:
            JNI_TRACE("ssl=%p proto_select NPN/ALPN no overlap", ssl);
            break;
        }
    } else {
        if (out != NULL && outLength != NULL) {
            *out = NULL;
            *outLength = 0;
        }
        JNI_TRACE("protocols=NULL");
    }
    return SSL_TLSEXT_ERR_NOACK;
}

/**
 * Callback for the server to select an ALPN protocol.
 */
static int alpn_select_callback(SSL* ssl, const unsigned char **out, unsigned char *outlen,
        const unsigned char *in, unsigned int inlen, void *) {
    JNI_TRACE("ssl=%p alpn_select_callback", ssl);

    AppData* appData = toAppData(ssl);
    JNI_TRACE("AppData=%p", appData);

    return proto_select(ssl, const_cast<unsigned char **>(out), outlen,
            reinterpret_cast<unsigned char*>(appData->alpnProtocolsData),
            appData->alpnProtocolsLength, in, inlen);
}

/**
 * Callback for the client to select an NPN protocol.
 */
static int next_proto_select_callback(SSL* ssl, unsigned char** out, unsigned char* outlen,
                                      const unsigned char* in, unsigned int inlen, void*)
{
    JNI_TRACE("ssl=%p next_proto_select_callback", ssl);

    AppData* appData = toAppData(ssl);
    JNI_TRACE("AppData=%p", appData);

    // Enable False Start on the client if the server understands NPN
    // http://www.imperialviolet.org/2012/04/11/falsestart.html
    SSL_set_mode(ssl, SSL_MODE_HANDSHAKE_CUTTHROUGH);

    return proto_select(ssl, out, outlen, in, inlen,
            reinterpret_cast<unsigned char*>(appData->npnProtocolsData),
            appData->npnProtocolsLength);
}

/**
 * Callback for the server to advertise available protocols.
 */
static int next_protos_advertised_callback(SSL* ssl,
        const unsigned char **out, unsigned int *outlen, void *)
{
    JNI_TRACE("ssl=%p next_protos_advertised_callback", ssl);
    AppData* appData = toAppData(ssl);
    unsigned char* npnProtocols = reinterpret_cast<unsigned char*>(appData->npnProtocolsData);
    if (npnProtocols != NULL) {
        *out = npnProtocols;
        *outlen = appData->npnProtocolsLength;
        return SSL_TLSEXT_ERR_OK;
    } else {
        *out = NULL;
        *outlen = 0;
        return SSL_TLSEXT_ERR_NOACK;
    }
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1CTX_1enable_1npn(JNIEnv* env, jclass, jlong ssl_ctx_address)
{
    SSL_CTX* ssl_ctx = to_SSL_CTX(env, ssl_ctx_address, true);
    if (ssl_ctx == NULL) {
        return;
    }
    SSL_CTX_set_next_proto_select_cb(ssl_ctx, next_proto_select_callback, NULL); // client
    SSL_CTX_set_next_protos_advertised_cb(ssl_ctx, next_protos_advertised_callback, NULL); // server
}

extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1CTX_1disable_1npn(JNIEnv* env, jclass, jlong ssl_ctx_address)
{
    SSL_CTX* ssl_ctx = to_SSL_CTX(env, ssl_ctx_address, true);
    if (ssl_ctx == NULL) {
        return;
    }
    SSL_CTX_set_next_proto_select_cb(ssl_ctx, NULL, NULL); // client
    SSL_CTX_set_next_protos_advertised_cb(ssl_ctx, NULL, NULL); // server
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_SSL_1get_1npn_1negotiated_1protocol(JNIEnv* env, jclass,
        jlong ssl_address)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_npn_negotiated_protocol", ssl);
    if (ssl == NULL) {
        return NULL;
    }
    const jbyte* npn;
    unsigned npnLength;
    SSL_get0_next_proto_negotiated(ssl, reinterpret_cast<const unsigned char**>(&npn), &npnLength);
    if (npnLength == 0) {
        return NULL;
    }
    jbyteArray result = env->NewByteArray(npnLength);
    if (result != NULL) {
        env->SetByteArrayRegion(result, 0, npnLength, npn);
    }
    return result;
}

extern "C" int Java_com_android_org_conscrypt_NativeCrypto_SSL_1CTX_1set_1alpn_1protos(JNIEnv* env, jclass, jlong ssl_ctx_address,
        jbyteArray protos) {
    SSL_CTX* ssl_ctx = to_SSL_CTX(env, ssl_ctx_address, true);
    if (ssl_ctx == NULL) {
        return 0;
    }

    JNI_TRACE("ssl_ctx=%p SSL_CTX_set_alpn_protos protos=%p", ssl_ctx, protos);

    if (protos == NULL) {
        JNI_TRACE("ssl_ctx=%p SSL_CTX_set_alpn_protos protos=NULL", ssl_ctx);
        return 1;
    }

    ScopedByteArrayRO protosBytes(env, protos);
    if (protosBytes.get() == NULL) {
        JNI_TRACE("ssl_ctx=%p SSL_CTX_set_alpn_protos protos=%p => protosBytes == NULL", ssl_ctx,
                protos);
        return 0;
    }

    const unsigned char *tmp = reinterpret_cast<const unsigned char*>(protosBytes.get());
    int ret = SSL_CTX_set_alpn_protos(ssl_ctx, tmp, protosBytes.size());
    JNI_TRACE("ssl_ctx=%p SSL_CTX_set_alpn_protos protos=%p => ret=%d", ssl_ctx, protos, ret);
    return ret;
}

extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_SSL_1get0_1alpn_1selected(JNIEnv* env, jclass,
        jlong ssl_address)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p SSL_get0_alpn_selected", ssl);
    if (ssl == NULL) {
        return NULL;
    }
    const jbyte* npn;
    unsigned npnLength;
    SSL_get0_alpn_selected(ssl, reinterpret_cast<const unsigned char**>(&npn), &npnLength);
    if (npnLength == 0) {
        return NULL;
    }
    jbyteArray result = env->NewByteArray(npnLength);
    if (result != NULL) {
        env->SetByteArrayRegion(result, 0, npnLength, npn);
    }
    return result;
}

#ifdef WITH_JNI_TRACE_KEYS
static inline char hex_char(unsigned char in)
{
    if (in < 10) {
        return '0' + in;
    } else if (in <= 0xF0) {
        return 'A' + in - 10;
    } else {
        return '?';
    }
}

static void hex_string(char **dest, unsigned char* input, int len)
{
    *dest = (char*) malloc(len * 2 + 1);
    char *output = *dest;
    for (int i = 0; i < len; i++) {
        *output++ = hex_char(input[i] >> 4);
        *output++ = hex_char(input[i] & 0xF);
    }
    *output = '\0';
}

static void debug_print_session_key(SSL_SESSION* session)
{
    char *session_id_str;
    char *master_key_str;
    const char *key_type;
    char *keyline;

    hex_string(&session_id_str, session->session_id, session->session_id_length);
    hex_string(&master_key_str, session->master_key, session->master_key_length);

    X509* peer = SSL_SESSION_get0_peer(session);
    EVP_PKEY* pkey = X509_PUBKEY_get(peer->cert_info->key);
    switch (EVP_PKEY_type(pkey->type)) {
    case EVP_PKEY_RSA:
        key_type = "RSA";
        break;
    case EVP_PKEY_DSA:
        key_type = "DSA";
        break;
    case EVP_PKEY_EC:
        key_type = "EC";
        break;
    default:
        key_type = "Unknown";
        break;
    }

    asprintf(&keyline, "%s Session-ID:%s Master-Key:%s\n", key_type, session_id_str,
            master_key_str);
    JNI_TRACE("ssl_session=%p %s", session, keyline);

    free(session_id_str);
    free(master_key_str);
    free(keyline);
}
#endif /* WITH_JNI_TRACE_KEYS */

/**
 * Perform SSL handshake
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_SSL_1do_1handshake(JNIEnv* env, jclass, jlong ssl_address, jobject fdObject,
        jobject shc, jint timeout_millis, jboolean client_mode, jbyteArray npnProtocols,
        jbyteArray alpnProtocols) {
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake fd=%p shc=%p timeout_millis=%d client_mode=%d npn=%p",
              ssl, fdObject, shc, timeout_millis, client_mode, npnProtocols);
    if (ssl == NULL) {
      return 0;
    }
    if (fdObject == NULL) {
        jniThrowNullPointerException(env, "fd == null");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake fd == null => 0", ssl);
        return 0;
    }
    if (shc == NULL) {
        jniThrowNullPointerException(env, "sslHandshakeCallbacks == null");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake sslHandshakeCallbacks == null => 0", ssl);
        return 0;
    }

    NetFd fd(env, fdObject);
    if (fd.isClosed()) {
        // SocketException thrown by NetFd.isClosed
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake fd.isClosed() => 0", ssl);
        return 0;
    }

    int ret = SSL_set_fd(ssl, fd.get());
    JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake s=%d", ssl, fd.get());

    if (ret != 1) {
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE,
                                       "Error setting the file descriptor");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake SSL_set_fd => 0", ssl);
        return 0;
    }

    /*
     * Make socket non-blocking, so SSL_connect SSL_read() and SSL_write() don't hang
     * forever and we can use select() to find out if the socket is ready.
     */
    if (!setBlocking(fd.get(), false)) {
        throwSSLExceptionStr(env, "Unable to make socket non blocking");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake setBlocking => 0", ssl);
        return 0;
    }

    /*
     * Create our special application data.
     */
    AppData* appData = AppData::create();
    if (appData == NULL) {
        throwSSLExceptionStr(env, "Unable to create application data");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake appData => 0", ssl);
        return 0;
    }

    SSL_set_app_data(ssl, reinterpret_cast<char*>(appData));
    JNI_TRACE("ssl=%p AppData::create => %p", ssl, appData);

    if (client_mode) {
        SSL_set_connect_state(ssl);
    } else {
        SSL_set_accept_state(ssl);
        if (alpnProtocols != NULL) {
            SSL_CTX_set_alpn_select_cb(SSL_get_SSL_CTX(ssl), alpn_select_callback, NULL);
        }
    }

    ret = 0;
    while (appData->aliveAndKicking) {
        errno = 0;

        if (!appData->setCallbackState(env, shc, fdObject, npnProtocols, alpnProtocols)) {
            // SocketException thrown by NetFd.isClosed
            SSL_clear(ssl);
            JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake setCallbackState => 0", ssl);
            return 0;
        }
        ret = SSL_do_handshake(ssl);
        appData->clearCallbackState();
        // cert_verify_callback threw exception
        if (env->ExceptionCheck()) {
            SSL_clear(ssl);
            JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake exception => 0", ssl);
            return 0;
        }
        // success case
        if (ret == 1) {
            break;
        }
        // retry case
        if (errno == EINTR) {
            continue;
        }
        // error case
        int sslError = SSL_get_error(ssl, ret);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake ret=%d errno=%d sslError=%d timeout_millis=%d",
                  ssl, ret, errno, sslError, timeout_millis);

        /*
         * If SSL_do_handshake doesn't succeed due to the socket being
         * either unreadable or unwritable, we use sslSelect to
         * wait for it to become ready. If that doesn't happen
         * before the specified timeout or an error occurs, we
         * cancel the handshake. Otherwise we try the SSL_connect
         * again.
         */
        if (sslError == SSL_ERROR_WANT_READ || sslError == SSL_ERROR_WANT_WRITE) {
            appData->waitingThreads++;
            int selectResult = sslSelect(env, sslError, fdObject, appData, timeout_millis);

            if (selectResult == THROWN_EXCEPTION) {
                // SocketException thrown by NetFd.isClosed
                SSL_clear(ssl);
                JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake sslSelect => 0", ssl);
                return 0;
            }
            if (selectResult == -1) {
                throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_SYSCALL, "handshake error");
                SSL_clear(ssl);
                JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake selectResult == -1 => 0", ssl);
                return 0;
            }
            if (selectResult == 0) {
                throwSocketTimeoutException(env, "SSL handshake timed out");
                SSL_clear(ssl);
                freeOpenSslErrorState();
                JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake selectResult == 0 => 0", ssl);
                return 0;
            }
        } else {
            // ALOGE("Unknown error %d during handshake", error);
            break;
        }
    }

    // clean error. See SSL_do_handshake(3SSL) man page.
    if (ret == 0) {
        /*
         * The other side closed the socket before the handshake could be
         * completed, but everything is within the bounds of the TLS protocol.
         * We still might want to find out the real reason of the failure.
         */
        int sslError = SSL_get_error(ssl, ret);
        if (sslError == SSL_ERROR_NONE || (sslError == SSL_ERROR_SYSCALL && errno == 0)) {
            throwSSLExceptionStr(env, "Connection closed by peer");
        } else {
            throwSSLExceptionWithSslErrors(env, ssl, sslError, "SSL handshake terminated");
        }
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake clean error => 0", ssl);
        return 0;
    }

    // unclean error. See SSL_do_handshake(3SSL) man page.
    if (ret < 0) {
        /*
         * Translate the error and throw exception. We are sure it is an error
         * at this point.
         */
        int sslError = SSL_get_error(ssl, ret);
        throwSSLExceptionWithSslErrors(env, ssl, sslError, "SSL handshake aborted");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake unclean error => 0", ssl);
        return 0;
    }
    SSL_SESSION* ssl_session = SSL_get1_session(ssl);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_do_handshake => ssl_session=%p", ssl, ssl_session);
#ifdef WITH_JNI_TRACE_KEYS
    debug_print_session_key(ssl_session);
#endif
    return (jlong) ssl_session;
}

/**
 * Perform SSL renegotiation
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1renegotiate(JNIEnv* env, jclass, jlong ssl_address)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_renegotiate", ssl);
    if (ssl == NULL) {
        return;
    }
    int result = SSL_renegotiate(ssl);
    if (result != 1) {
        throwSSLExceptionStr(env, "Problem with SSL_renegotiate");
        return;
    }
    // first call asks client to perform renegotiation
    int ret = SSL_do_handshake(ssl);
    if (ret != 1) {
        int sslError = SSL_get_error(ssl, ret);
        throwSSLExceptionWithSslErrors(env, ssl, sslError,
                                       "Problem with SSL_do_handshake after SSL_renegotiate");
        return;
    }
    // if client agrees, set ssl state and perform renegotiation
    ssl->state = SSL_ST_ACCEPT;
    SSL_do_handshake(ssl);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_renegotiate =>", ssl);
}

/**
 * public static native byte[][] SSL_get_certificate(int ssl);
 */
extern "C" jobjectArray Java_com_android_org_conscrypt_NativeCrypto_SSL_1get_1certificate(JNIEnv* env, jclass, jlong ssl_address)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_certificate", ssl);
    if (ssl == NULL) {
        return NULL;
    }
    X509* certificate = SSL_get_certificate(ssl);
    if (certificate == NULL) {
        JNI_TRACE("ssl=%p NativeCrypto_SSL_get_certificate => NULL", ssl);
        // SSL_get_certificate can return NULL during an error as well.
        freeOpenSslErrorState();
        return NULL;
    }

    Unique_sk_X509 chain(sk_X509_new_null());
    if (chain.get() == NULL) {
        jniThrowOutOfMemory(env, "Unable to allocate local certificate chain");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_get_certificate => threw exception", ssl);
        return NULL;
    }
    if (!sk_X509_push(chain.get(), X509_dup_nocopy(certificate))) {
        jniThrowOutOfMemory(env, "Unable to push local certificate");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_get_certificate => NULL", ssl);
        return NULL;
    }
    STACK_OF(X509)* cert_chain = SSL_get_certificate_chain(ssl, certificate);
    for (int i=0; i<sk_X509_num(cert_chain); i++) {
        if (!sk_X509_push(chain.get(), X509_dup_nocopy(sk_X509_value(cert_chain, i)))) {
            jniThrowOutOfMemory(env, "Unable to push local certificate chain");
            JNI_TRACE("ssl=%p NativeCrypto_SSL_get_certificate => NULL", ssl);
            return NULL;
        }
    }

    jobjectArray objectArray = getCertificateBytes(env, chain.get());
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_certificate => %p", ssl, objectArray);
    return objectArray;
}

// Fills a byte[][] with the peer certificates in the chain.
extern "C" jobjectArray Java_com_android_org_conscrypt_NativeCrypto_SSL_1get_1peer_1cert_1chain(JNIEnv* env, jclass, jlong ssl_address)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_peer_cert_chain", ssl);
    if (ssl == NULL) {
        return NULL;
    }
    STACK_OF(X509)* chain = SSL_get_peer_cert_chain(ssl);
    Unique_sk_X509 chain_copy(NULL);
    if (ssl->server) {
        X509* x509 = SSL_get_peer_certificate(ssl);
        if (x509 == NULL) {
            JNI_TRACE("ssl=%p NativeCrypto_SSL_get_peer_cert_chain => NULL", ssl);
            return NULL;
        }
        chain_copy.reset(sk_X509_new_null());
        if (chain_copy.get() == NULL) {
            jniThrowOutOfMemory(env, "Unable to allocate peer certificate chain");
            JNI_TRACE("ssl=%p NativeCrypto_SSL_get_peer_cert_chain => certificate dup error", ssl);
            return NULL;
        }
        size_t chain_size = sk_X509_num(chain);
        for (size_t i = 0; i < chain_size; i++) {
            if (!sk_X509_push(chain_copy.get(), X509_dup_nocopy(sk_X509_value(chain, i)))) {
                jniThrowOutOfMemory(env, "Unable to push server's peer certificate chain");
                JNI_TRACE("ssl=%p NativeCrypto_SSL_get_peer_cert_chain => certificate chain push error", ssl);
                return NULL;
            }
        }
        if (!sk_X509_push(chain_copy.get(), X509_dup_nocopy(x509))) {
            jniThrowOutOfMemory(env, "Unable to push server's peer certificate");
            JNI_TRACE("ssl=%p NativeCrypto_SSL_get_peer_cert_chain => certificate push error", ssl);
            return NULL;
        }
        chain = chain_copy.get();
    }
    jobjectArray objectArray = getCertificateBytes(env, chain);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_peer_cert_chain => %p", ssl, objectArray);
    return objectArray;
}

/**
 * Helper function which does the actual reading. The Java layer guarantees that
 * at most one thread will enter this function at any given time.
 *
 * @param ssl non-null; the SSL context
 * @param buf non-null; buffer to read into
 * @param len length of the buffer, in bytes
 * @param sslReturnCode original SSL return code
 * @param sslErrorCode filled in with the SSL error code in case of error
 * @return number of bytes read on success, -1 if the connection was
 * cleanly shut down, or THROW_SSLEXCEPTION if an exception should be thrown.
 */
static int sslRead(JNIEnv* env, SSL* ssl, jobject fdObject, jobject shc, char* buf, jint len,
                   int* sslReturnCode, int* sslErrorCode, int read_timeout_millis) {
    JNI_TRACE("ssl=%p sslRead buf=%p len=%d", ssl, buf, len);

    if (len == 0) {
        // Don't bother doing anything in this case.
        return 0;
    }

    BIO* bio = SSL_get_rbio(ssl);

    AppData* appData = toAppData(ssl);
    if (appData == NULL) {
        return THROW_SSLEXCEPTION;
    }

    while (appData->aliveAndKicking) {
        errno = 0;

        if (MUTEX_LOCK(appData->mutex) == -1) {
            return -1;
        }

        unsigned int bytesMoved = BIO_number_read(bio) + BIO_number_written(bio);

        if (!appData->setCallbackState(env, shc, fdObject, NULL, NULL)) {
            MUTEX_UNLOCK(appData->mutex);
            return THROWN_EXCEPTION;
        }
        int result = SSL_read(ssl, buf, len);
        appData->clearCallbackState();
        // callbacks can happen if server requests renegotiation
        if (env->ExceptionCheck()) {
            SSL_clear(ssl);
            JNI_TRACE("ssl=%p sslRead => THROWN_EXCEPTION", ssl);
            return THROWN_EXCEPTION;
        }
        int sslError = SSL_ERROR_NONE;
        if (result <= 0) {
            sslError = SSL_get_error(ssl, result);
            freeOpenSslErrorState();
        }
        JNI_TRACE("ssl=%p sslRead SSL_read result=%d sslError=%d", ssl, result, sslError);
#ifdef WITH_JNI_TRACE_DATA
        for (int i = 0; i < result; i+= WITH_JNI_TRACE_DATA_CHUNK_SIZE) {
            int n = std::min(result - i, WITH_JNI_TRACE_DATA_CHUNK_SIZE);
            JNI_TRACE("ssl=%p sslRead data: %d:\n%.*s", ssl, n, n, buf+i);
        }
#endif

        // If we have been successful in moving data around, check whether it
        // might make sense to wake up other blocked threads, so they can give
        // it a try, too.
        if (BIO_number_read(bio) + BIO_number_written(bio) != bytesMoved
                && appData->waitingThreads > 0) {
            sslNotify(appData);
        }

        // If we are blocked by the underlying socket, tell the world that
        // there will be one more waiting thread now.
        if (sslError == SSL_ERROR_WANT_READ || sslError == SSL_ERROR_WANT_WRITE) {
            appData->waitingThreads++;
        }

        MUTEX_UNLOCK(appData->mutex);

        switch (sslError) {
            // Successfully read at least one byte.
            case SSL_ERROR_NONE: {
                return result;
            }

            // Read zero bytes. End of stream reached.
            case SSL_ERROR_ZERO_RETURN: {
                return -1;
            }

            // Need to wait for availability of underlying layer, then retry.
            case SSL_ERROR_WANT_READ:
            case SSL_ERROR_WANT_WRITE: {
                int selectResult = sslSelect(env, sslError, fdObject, appData, read_timeout_millis);
                if (selectResult == THROWN_EXCEPTION) {
                    return THROWN_EXCEPTION;
                }
                if (selectResult == -1) {
                    *sslReturnCode = -1;
                    *sslErrorCode = sslError;
                    return THROW_SSLEXCEPTION;
                }
                if (selectResult == 0) {
                    return THROW_SOCKETTIMEOUTEXCEPTION;
                }

                break;
            }

            // A problem occurred during a system call, but this is not
            // necessarily an error.
            case SSL_ERROR_SYSCALL: {
                // Connection closed without proper shutdown. Tell caller we
                // have reached end-of-stream.
                if (result == 0) {
                    return -1;
                }

                // System call has been interrupted. Simply retry.
                if (errno == EINTR) {
                    break;
                }

                // Note that for all other system call errors we fall through
                // to the default case, which results in an Exception.
            }

            // Everything else is basically an error.
            default: {
                *sslReturnCode = result;
                *sslErrorCode = sslError;
                return THROW_SSLEXCEPTION;
            }
        }
    }

    return -1;
}

/**
 * OpenSSL read function (2): read into buffer at offset n chunks.
 * Returns 1 (success) or value <= 0 (failure).
 */
extern "C" jint Java_com_android_org_conscrypt_NativeCrypto_SSL_1read(JNIEnv* env, jclass, jlong ssl_address, jobject fdObject,
                                  jobject shc, jbyteArray b, jint offset, jint len,
                                  jint read_timeout_millis)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_read fd=%p shc=%p b=%p offset=%d len=%d read_timeout_millis=%d",
              ssl, fdObject, shc, b, offset, len, read_timeout_millis);
    if (ssl == NULL) {
        return 0;
    }
    if (fdObject == NULL) {
        jniThrowNullPointerException(env, "fd == null");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_read => fd == null", ssl);
        return 0;
    }
    if (shc == NULL) {
        jniThrowNullPointerException(env, "sslHandshakeCallbacks == null");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_read => sslHandshakeCallbacks == null", ssl);
        return 0;
    }

    ScopedByteArrayRW bytes(env, b);
    if (bytes.get() == NULL) {
        JNI_TRACE("ssl=%p NativeCrypto_SSL_read => threw exception", ssl);
        return 0;
    }
    int returnCode = 0;
    int sslErrorCode = SSL_ERROR_NONE;;

    int ret = sslRead(env, ssl, fdObject, shc, reinterpret_cast<char*>(bytes.get() + offset), len,
                      &returnCode, &sslErrorCode, read_timeout_millis);

    int result;
    switch (ret) {
        case THROW_SSLEXCEPTION:
            // See sslRead() regarding improper failure to handle normal cases.
            throwSSLExceptionWithSslErrors(env, ssl, sslErrorCode, "Read error");
            result = -1;
            break;
        case THROW_SOCKETTIMEOUTEXCEPTION:
            throwSocketTimeoutException(env, "Read timed out");
            result = -1;
            break;
        case THROWN_EXCEPTION:
            // SocketException thrown by NetFd.isClosed
            // or RuntimeException thrown by callback
            result = -1;
            break;
        default:
            result = ret;
            break;
    }

    JNI_TRACE("ssl=%p NativeCrypto_SSL_read => %d", ssl, result);
    return result;
}

/**
 * Helper function which does the actual writing. The Java layer guarantees that
 * at most one thread will enter this function at any given time.
 *
 * @param ssl non-null; the SSL context
 * @param buf non-null; buffer to write
 * @param len length of the buffer, in bytes
 * @param sslReturnCode original SSL return code
 * @param sslErrorCode filled in with the SSL error code in case of error
 * @return number of bytes read on success, -1 if the connection was
 * cleanly shut down, or THROW_SSLEXCEPTION if an exception should be thrown.
 */
static int sslWrite(JNIEnv* env, SSL* ssl, jobject fdObject, jobject shc, const char* buf, jint len,
                    int* sslReturnCode, int* sslErrorCode, int write_timeout_millis) {
    JNI_TRACE("ssl=%p sslWrite buf=%p len=%d write_timeout_millis=%d",
              ssl, buf, len, write_timeout_millis);

    if (len == 0) {
        // Don't bother doing anything in this case.
        return 0;
    }

    BIO* bio = SSL_get_wbio(ssl);

    AppData* appData = toAppData(ssl);
    if (appData == NULL) {
        return THROW_SSLEXCEPTION;
    }

    int count = len;

    while (appData->aliveAndKicking && ((len > 0) || (ssl->s3->wbuf.left > 0))) {
        errno = 0;

        if (MUTEX_LOCK(appData->mutex) == -1) {
            return -1;
        }

        unsigned int bytesMoved = BIO_number_read(bio) + BIO_number_written(bio);

        if (!appData->setCallbackState(env, shc, fdObject, NULL, NULL)) {
            MUTEX_UNLOCK(appData->mutex);
            return THROWN_EXCEPTION;
        }
        JNI_TRACE("ssl=%p sslWrite SSL_write len=%d left=%d", ssl, len, ssl->s3->wbuf.left);
        int result = SSL_write(ssl, buf, len);
        appData->clearCallbackState();
        // callbacks can happen if server requests renegotiation
        if (env->ExceptionCheck()) {
            SSL_clear(ssl);
            JNI_TRACE("ssl=%p sslWrite exception => THROWN_EXCEPTION", ssl);
            return THROWN_EXCEPTION;
        }
        int sslError = SSL_ERROR_NONE;
        if (result <= 0) {
            sslError = SSL_get_error(ssl, result);
            freeOpenSslErrorState();
        }
        JNI_TRACE("ssl=%p sslWrite SSL_write result=%d sslError=%d left=%d",
                  ssl, result, sslError, ssl->s3->wbuf.left);
#ifdef WITH_JNI_TRACE_DATA
        for (int i = 0; i < result; i+= WITH_JNI_TRACE_DATA_CHUNK_SIZE) {
            int n = std::min(result - i, WITH_JNI_TRACE_DATA_CHUNK_SIZE);
            JNI_TRACE("ssl=%p sslWrite data: %d:\n%.*s", ssl, n, n, buf+i);
        }
#endif

        // If we have been successful in moving data around, check whether it
        // might make sense to wake up other blocked threads, so they can give
        // it a try, too.
        if (BIO_number_read(bio) + BIO_number_written(bio) != bytesMoved
                && appData->waitingThreads > 0) {
            sslNotify(appData);
        }

        // If we are blocked by the underlying socket, tell the world that
        // there will be one more waiting thread now.
        if (sslError == SSL_ERROR_WANT_READ || sslError == SSL_ERROR_WANT_WRITE) {
            appData->waitingThreads++;
        }

        MUTEX_UNLOCK(appData->mutex);

        switch (sslError) {
            // Successfully wrote at least one byte.
            case SSL_ERROR_NONE: {
                buf += result;
                len -= result;
                break;
            }

            // Wrote zero bytes. End of stream reached.
            case SSL_ERROR_ZERO_RETURN: {
                return -1;
            }

            // Need to wait for availability of underlying layer, then retry.
            // The concept of a write timeout doesn't really make sense, and
            // it's also not standard Java behavior, so we wait forever here.
            case SSL_ERROR_WANT_READ:
            case SSL_ERROR_WANT_WRITE: {
                int selectResult = sslSelect(env, sslError, fdObject, appData, write_timeout_millis);
                if (selectResult == THROWN_EXCEPTION) {
                    return THROWN_EXCEPTION;
                }
                if (selectResult == -1) {
                    *sslReturnCode = -1;
                    *sslErrorCode = sslError;
                    return THROW_SSLEXCEPTION;
                }
                if (selectResult == 0) {
                    return THROW_SOCKETTIMEOUTEXCEPTION;
                }

                break;
            }

            // A problem occurred during a system call, but this is not
            // necessarily an error.
            case SSL_ERROR_SYSCALL: {
                // Connection closed without proper shutdown. Tell caller we
                // have reached end-of-stream.
                if (result == 0) {
                    return -1;
                }

                // System call has been interrupted. Simply retry.
                if (errno == EINTR) {
                    break;
                }

                // Note that for all other system call errors we fall through
                // to the default case, which results in an Exception.
            }

            // Everything else is basically an error.
            default: {
                *sslReturnCode = result;
                *sslErrorCode = sslError;
                return THROW_SSLEXCEPTION;
            }
        }
    }
    JNI_TRACE("ssl=%p sslWrite => count=%d", ssl, count);

    return count;
}

/**
 * OpenSSL write function (2): write into buffer at offset n chunks.
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1write(JNIEnv* env, jclass, jlong ssl_address, jobject fdObject,
                                   jobject shc, jbyteArray b, jint offset, jint len, jint write_timeout_millis)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_write fd=%p shc=%p b=%p offset=%d len=%d write_timeout_millis=%d",
              ssl, fdObject, shc, b, offset, len, write_timeout_millis);
    if (ssl == NULL) {
        return;
    }
    if (fdObject == NULL) {
        jniThrowNullPointerException(env, "fd == null");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_write => fd == null", ssl);
        return;
    }
    if (shc == NULL) {
        jniThrowNullPointerException(env, "sslHandshakeCallbacks == null");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_write => sslHandshakeCallbacks == null", ssl);
        return;
    }

    ScopedByteArrayRO bytes(env, b);
    if (bytes.get() == NULL) {
        JNI_TRACE("ssl=%p NativeCrypto_SSL_write => threw exception", ssl);
        return;
    }
    int returnCode = 0;
    int sslErrorCode = SSL_ERROR_NONE;
    int ret = sslWrite(env, ssl, fdObject, shc, reinterpret_cast<const char*>(bytes.get() + offset),
                       len, &returnCode, &sslErrorCode, write_timeout_millis);

    switch (ret) {
        case THROW_SSLEXCEPTION:
            // See sslWrite() regarding improper failure to handle normal cases.
            throwSSLExceptionWithSslErrors(env, ssl, sslErrorCode, "Write error");
            break;
        case THROW_SOCKETTIMEOUTEXCEPTION:
            throwSocketTimeoutException(env, "Write timed out");
            break;
        case THROWN_EXCEPTION:
            // SocketException thrown by NetFd.isClosed
            break;
        default:
            break;
    }
}

/**
 * Interrupt any pending I/O before closing the socket.
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1interrupt(
        JNIEnv* env, jclass, jlong ssl_address) {
    SSL* ssl = to_SSL(env, ssl_address, false);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_interrupt", ssl);
    if (ssl == NULL) {
        return;
    }

    /*
     * Mark the connection as quasi-dead, then send something to the emergency
     * file descriptor, so any blocking select() calls are woken up.
     */
    AppData* appData = toAppData(ssl);
    if (appData != NULL) {
        appData->aliveAndKicking = 0;

        // At most two threads can be waiting.
        sslNotify(appData);
        sslNotify(appData);
    }
}

/**
 * OpenSSL close SSL socket function.
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1shutdown(JNIEnv* env, jclass, jlong ssl_address,
                                      jobject fdObject, jobject shc) {
    SSL* ssl = to_SSL(env, ssl_address, false);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_shutdown fd=%p shc=%p", ssl, fdObject, shc);
    if (ssl == NULL) {
        return;
    }
    if (fdObject == NULL) {
        jniThrowNullPointerException(env, "fd == null");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_shutdown => fd == null", ssl);
        return;
    }
    if (shc == NULL) {
        jniThrowNullPointerException(env, "sslHandshakeCallbacks == null");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_shutdown => sslHandshakeCallbacks == null", ssl);
        return;
    }

    AppData* appData = toAppData(ssl);
    if (appData != NULL) {
        if (!appData->setCallbackState(env, shc, fdObject, NULL, NULL)) {
            // SocketException thrown by NetFd.isClosed
            SSL_clear(ssl);
            freeOpenSslErrorState();
            return;
        }

        /*
         * Try to make socket blocking again. OpenSSL literature recommends this.
         */
        int fd = SSL_get_fd(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_shutdown s=%d", ssl, fd);
        if (fd != -1) {
            setBlocking(fd, true);
        }

        int ret = SSL_shutdown(ssl);
        appData->clearCallbackState();
        // callbacks can happen if server requests renegotiation
        if (env->ExceptionCheck()) {
            SSL_clear(ssl);
            JNI_TRACE("ssl=%p NativeCrypto_SSL_shutdown => exception", ssl);
            return;
        }
        switch (ret) {
            case 0:
                /*
                 * Shutdown was not successful (yet), but there also
                 * is no error. Since we can't know whether the remote
                 * server is actually still there, and we don't want to
                 * get stuck forever in a second SSL_shutdown() call, we
                 * simply return. This is not security a problem as long
                 * as we close the underlying socket, which we actually
                 * do, because that's where we are just coming from.
                 */
                break;
            case 1:
                /*
                 * Shutdown was successful. We can safely return. Hooray!
                 */
                break;
            default:
                /*
                 * Everything else is a real error condition. We should
                 * let the Java layer know about this by throwing an
                 * exception.
                 */
                int sslError = SSL_get_error(ssl, ret);
                throwSSLExceptionWithSslErrors(env, ssl, sslError, "SSL shutdown failed");
                break;
        }
    }

    SSL_clear(ssl);
    freeOpenSslErrorState();
}

/**
 * public static native void SSL_free(int ssl);
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1free(JNIEnv* env, jclass, jlong ssl_address)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_free", ssl);
    if (ssl == NULL) {
        return;
    }

    AppData* appData = toAppData(ssl);
    SSL_set_app_data(ssl, NULL);
    delete appData;
    SSL_free(ssl);
}

/**
 * Gets and returns in a byte array the ID of the actual SSL session.
 */
extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_SSL_1SESSION_1session_1id(JNIEnv* env, jclass,
                                                      jlong ssl_session_address) {
    SSL_SESSION* ssl_session = to_SSL_SESSION(env, ssl_session_address, true);
    JNI_TRACE("ssl_session=%p NativeCrypto_SSL_SESSION_session_id", ssl_session);
    if (ssl_session == NULL) {
        return NULL;
    }
    jbyteArray result = env->NewByteArray(ssl_session->session_id_length);
    if (result != NULL) {
        jbyte* src = reinterpret_cast<jbyte*>(ssl_session->session_id);
        env->SetByteArrayRegion(result, 0, ssl_session->session_id_length, src);
    }
    JNI_TRACE("ssl_session=%p NativeCrypto_SSL_SESSION_session_id => %p session_id_length=%d",
             ssl_session, result, ssl_session->session_id_length);
    return result;
}

/**
 * Gets and returns in a long integer the creation's time of the
 * actual SSL session.
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_SSL_1SESSION_1get_1time(JNIEnv* env, jclass, jlong ssl_session_address) {
    SSL_SESSION* ssl_session = to_SSL_SESSION(env, ssl_session_address, true);
    JNI_TRACE("ssl_session=%p NativeCrypto_SSL_SESSION_get_time", ssl_session);
    if (ssl_session == NULL) {
        return 0;
    }
    // result must be jlong, not long or *1000 will overflow
    jlong result = SSL_SESSION_get_time(ssl_session);
    result *= 1000; // OpenSSL uses seconds, Java uses milliseconds.
    JNI_TRACE("ssl_session=%p NativeCrypto_SSL_SESSION_get_time => %lld", ssl_session, result);
    return result;
}

/**
 * Gets and returns in a string the version of the SSL protocol. If it
 * returns the string "unknown" it means that no connection is established.
 */
extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_SSL_1SESSION_1get_1version(JNIEnv* env, jclass, jlong ssl_session_address) {
    SSL_SESSION* ssl_session = to_SSL_SESSION(env, ssl_session_address, true);
    JNI_TRACE("ssl_session=%p NativeCrypto_SSL_SESSION_get_version", ssl_session);
    if (ssl_session == NULL) {
        return NULL;
    }
    const char* protocol = SSL_SESSION_get_version(ssl_session);
    JNI_TRACE("ssl_session=%p NativeCrypto_SSL_SESSION_get_version => %s", ssl_session, protocol);
    return env->NewStringUTF(protocol);
}

/**
 * Gets and returns in a string the cipher negotiated for the SSL session.
 */
extern "C" jstring Java_com_android_org_conscrypt_NativeCrypto_SSL_1SESSION_1cipher(JNIEnv* env, jclass, jlong ssl_session_address) {
    SSL_SESSION* ssl_session = to_SSL_SESSION(env, ssl_session_address, true);
    JNI_TRACE("ssl_session=%p NativeCrypto_SSL_SESSION_cipher", ssl_session);
    if (ssl_session == NULL) {
        return NULL;
    }
    const SSL_CIPHER* cipher = ssl_session->cipher;
    const char* name = SSL_CIPHER_get_name(cipher);
    JNI_TRACE("ssl_session=%p NativeCrypto_SSL_SESSION_cipher => %s", ssl_session, name);
    return env->NewStringUTF(name);
}

/**
 * Frees the SSL session.
 */
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_SSL_1SESSION_1free(JNIEnv* env, jclass, jlong ssl_session_address) {
    SSL_SESSION* ssl_session = to_SSL_SESSION(env, ssl_session_address, true);
    JNI_TRACE("ssl_session=%p NativeCrypto_SSL_SESSION_free", ssl_session);
    if (ssl_session == NULL) {
        return;
    }
    SSL_SESSION_free(ssl_session);
}


/**
 * Serializes the native state of the session (ID, cipher, and keys but
 * not certificates). Returns a byte[] containing the DER-encoded state.
 * See apache mod_ssl.
 */
extern "C" jbyteArray Java_com_android_org_conscrypt_NativeCrypto_i2d_1SSL_1SESSION(JNIEnv* env, jclass, jlong ssl_session_address) {
    SSL_SESSION* ssl_session = to_SSL_SESSION(env, ssl_session_address, true);
    JNI_TRACE("ssl_session=%p NativeCrypto_i2d_SSL_SESSION", ssl_session);
    if (ssl_session == NULL) {
        return NULL;
    }
    return ASN1ToByteArray<SSL_SESSION, i2d_SSL_SESSION>(env, ssl_session);
}

/**
 * Deserialize the session.
 */
extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_d2i_1SSL_1SESSION(JNIEnv* env, jclass, jbyteArray javaBytes) {
    JNI_TRACE("NativeCrypto_d2i_SSL_SESSION bytes=%p", javaBytes);

    ScopedByteArrayRO bytes(env, javaBytes);
    if (bytes.get() == NULL) {
        JNI_TRACE("NativeCrypto_d2i_SSL_SESSION => threw exception");
        return 0;
    }
    const unsigned char* ucp = reinterpret_cast<const unsigned char*>(bytes.get());
    SSL_SESSION* ssl_session = d2i_SSL_SESSION(NULL, &ucp, bytes.size());

    // Initialize SSL_SESSION cipher field based on cipher_id http://b/7091840
    if (ssl_session != NULL) {
        // based on ssl_get_prev_session
        uint32_t cipher_id_network_order = htonl(ssl_session->cipher_id);
        uint8_t* cipher_id_byte_pointer = reinterpret_cast<uint8_t*>(&cipher_id_network_order);
        if (ssl_session->ssl_version >= SSL3_VERSION_MAJOR) {
            cipher_id_byte_pointer += 2; // skip first two bytes for SSL3+
        } else {
            cipher_id_byte_pointer += 1; // skip first byte for SSL2
        }
        ssl_session->cipher = SSLv23_method()->get_cipher_by_char(cipher_id_byte_pointer);
        JNI_TRACE("NativeCrypto_d2i_SSL_SESSION cipher_id=%lx hton=%x 0=%x 1=%x cipher=%s",
                  ssl_session->cipher_id, cipher_id_network_order,
                  cipher_id_byte_pointer[0], cipher_id_byte_pointer[1],
                  SSL_CIPHER_get_name(ssl_session->cipher));
    } else {
        freeOpenSslErrorState();
    }

    JNI_TRACE("NativeCrypto_d2i_SSL_SESSION => %p", ssl_session);
    return reinterpret_cast<uintptr_t>(ssl_session);
}

extern "C" jlong Java_com_android_org_conscrypt_NativeCrypto_ERR_1peek_1last_1error(JNIEnv*, jclass) {
    return ERR_peek_last_error();
}

// RoboVM note: Not used on RoboVM.
#if 0
#define FILE_DESCRIPTOR "Ljava/io/FileDescriptor;"
#define SSL_CALLBACKS "L" TO_STRING(JNI_JARJAR_PREFIX) "org/conscrypt/NativeCrypto$SSLHandshakeCallbacks;"
static JNINativeMethod sNativeCryptoMethods[] = {
    NATIVE_METHOD(NativeCrypto, clinit, "()V"),
    NATIVE_METHOD(NativeCrypto, ENGINE_load_dynamic, "()V"),
    NATIVE_METHOD(NativeCrypto, ENGINE_by_id, "(Ljava/lang/String;)J"),
    NATIVE_METHOD(NativeCrypto, ENGINE_add, "(J)I"),
    NATIVE_METHOD(NativeCrypto, ENGINE_init, "(J)I"),
    NATIVE_METHOD(NativeCrypto, ENGINE_finish, "(J)I"),
    NATIVE_METHOD(NativeCrypto, ENGINE_free, "(J)I"),
    NATIVE_METHOD(NativeCrypto, ENGINE_load_private_key, "(JLjava/lang/String;)J"),
    NATIVE_METHOD(NativeCrypto, ENGINE_get_id, "(J)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, ENGINE_ctrl_cmd_string, "(JLjava/lang/String;Ljava/lang/String;I)I"),
    NATIVE_METHOD(NativeCrypto, EVP_PKEY_new_DSA, "([B[B[B[B[B)J"),
    NATIVE_METHOD(NativeCrypto, EVP_PKEY_new_RSA, "([B[B[B[B[B[B[B[B)J"),
    NATIVE_METHOD(NativeCrypto, EVP_PKEY_new_EC_KEY, "(JJ[B)J"),
    NATIVE_METHOD(NativeCrypto, EVP_PKEY_new_mac_key, "(I[B)J"),
    NATIVE_METHOD(NativeCrypto, EVP_PKEY_type, "(J)I"),
    NATIVE_METHOD(NativeCrypto, EVP_PKEY_size, "(J)I"),
    NATIVE_METHOD(NativeCrypto, EVP_PKEY_print_public, "(J)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, EVP_PKEY_print_private, "(J)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, EVP_PKEY_free, "(J)V"),
    NATIVE_METHOD(NativeCrypto, EVP_PKEY_cmp, "(JJ)I"),
    NATIVE_METHOD(NativeCrypto, i2d_PKCS8_PRIV_KEY_INFO, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, d2i_PKCS8_PRIV_KEY_INFO, "([B)J"),
    NATIVE_METHOD(NativeCrypto, i2d_PUBKEY, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, d2i_PUBKEY, "([B)J"),
    NATIVE_METHOD(NativeCrypto, RSA_generate_key_ex, "(I[B)J"),
    NATIVE_METHOD(NativeCrypto, RSA_size, "(J)I"),
    NATIVE_METHOD(NativeCrypto, RSA_private_encrypt, "(I[B[BJI)I"),
    NATIVE_METHOD(NativeCrypto, RSA_public_decrypt, "(I[B[BJI)I"),
    NATIVE_METHOD(NativeCrypto, RSA_public_encrypt, "(I[B[BJI)I"),
    NATIVE_METHOD(NativeCrypto, RSA_private_decrypt, "(I[B[BJI)I"),
    NATIVE_METHOD(NativeCrypto, get_RSA_private_params, "(J)[[B"),
    NATIVE_METHOD(NativeCrypto, get_RSA_public_params, "(J)[[B"),
    NATIVE_METHOD(NativeCrypto, DSA_generate_key, "(I[B[B[B[B)J"),
    NATIVE_METHOD(NativeCrypto, get_DSA_params, "(J)[[B"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_new_by_curve_name, "(Ljava/lang/String;)J"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_new_curve, "(I[B[B[B)J"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_dup, "(J)J"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_set_asn1_flag, "(JI)V"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_set_point_conversion_form, "(JI)V"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_get_curve_name, "(J)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_get_curve, "(J)[[B"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_get_order, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_get_degree, "(J)I"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_get_cofactor, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_clear_free, "(J)V"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_cmp, "(JJ)Z"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_get_generator, "(J)J"),
    NATIVE_METHOD(NativeCrypto, EC_GROUP_set_generator, "(JJ[B[B)V"),
    NATIVE_METHOD(NativeCrypto, get_EC_GROUP_type, "(J)I"),
    NATIVE_METHOD(NativeCrypto, EC_POINT_new, "(J)J"),
    NATIVE_METHOD(NativeCrypto, EC_POINT_clear_free, "(J)V"),
    NATIVE_METHOD(NativeCrypto, EC_POINT_cmp, "(JJJ)Z"),
    NATIVE_METHOD(NativeCrypto, EC_POINT_set_affine_coordinates, "(JJ[B[B)V"),
    NATIVE_METHOD(NativeCrypto, EC_POINT_get_affine_coordinates, "(JJ)[[B"),
    NATIVE_METHOD(NativeCrypto, EC_KEY_generate_key, "(J)J"),
    NATIVE_METHOD(NativeCrypto, EC_KEY_get0_group, "(J)J"),
    NATIVE_METHOD(NativeCrypto, EC_KEY_get_private_key, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, EC_KEY_get_public_key, "(J)J"),
    NATIVE_METHOD(NativeCrypto, ECDH_compute_key, "([BIJJ)I"),
    NATIVE_METHOD(NativeCrypto, EVP_MD_CTX_create, "()J"),
    NATIVE_METHOD(NativeCrypto, EVP_MD_CTX_init, "(J)V"),
    NATIVE_METHOD(NativeCrypto, EVP_MD_CTX_destroy, "(J)V"),
    NATIVE_METHOD(NativeCrypto, EVP_MD_CTX_copy, "(J)J"),
    NATIVE_METHOD(NativeCrypto, EVP_DigestFinal, "(J[BI)I"),
    NATIVE_METHOD(NativeCrypto, EVP_DigestInit, "(J)J"),
    NATIVE_METHOD(NativeCrypto, EVP_get_digestbyname, "(Ljava/lang/String;)J"),
    NATIVE_METHOD(NativeCrypto, EVP_MD_block_size, "(J)I"),
    NATIVE_METHOD(NativeCrypto, EVP_MD_size, "(J)I"),
    NATIVE_METHOD(NativeCrypto, EVP_DigestUpdate, "(J[BII)V"),
    NATIVE_METHOD(NativeCrypto, EVP_SignInit, "(Ljava/lang/String;)J"),
    NATIVE_METHOD(NativeCrypto, EVP_SignUpdate, "(J[BII)V"),
    NATIVE_METHOD(NativeCrypto, EVP_SignFinal, "(J[BIJ)I"),
    NATIVE_METHOD(NativeCrypto, EVP_VerifyInit, "(Ljava/lang/String;)J"),
    NATIVE_METHOD(NativeCrypto, EVP_VerifyUpdate, "(J[BII)V"),
    NATIVE_METHOD(NativeCrypto, EVP_VerifyFinal, "(J[BIIJ)I"),
    NATIVE_METHOD(NativeCrypto, EVP_DigestSignInit, "(JJJ)V"),
    NATIVE_METHOD(NativeCrypto, EVP_DigestSignUpdate, "(J[B)V"),
    NATIVE_METHOD(NativeCrypto, EVP_DigestSignFinal, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, EVP_get_cipherbyname, "(Ljava/lang/String;)J"),
    NATIVE_METHOD(NativeCrypto, EVP_CipherInit_ex, "(JJ[B[BZ)V"),
    NATIVE_METHOD(NativeCrypto, EVP_CipherUpdate, "(J[BI[BII)I"),
    NATIVE_METHOD(NativeCrypto, EVP_CipherFinal_ex, "(J[BI)I"),
    NATIVE_METHOD(NativeCrypto, EVP_CIPHER_iv_length, "(J)I"),
    NATIVE_METHOD(NativeCrypto, EVP_CIPHER_CTX_new, "()J"),
    NATIVE_METHOD(NativeCrypto, EVP_CIPHER_CTX_block_size, "(J)I"),
    NATIVE_METHOD(NativeCrypto, get_EVP_CIPHER_CTX_buf_len, "(J)I"),
    NATIVE_METHOD(NativeCrypto, EVP_CIPHER_CTX_set_padding, "(JZ)V"),
    NATIVE_METHOD(NativeCrypto, EVP_CIPHER_CTX_set_key_length, "(JI)V"),
    NATIVE_METHOD(NativeCrypto, EVP_CIPHER_CTX_cleanup, "(J)V"),
    NATIVE_METHOD(NativeCrypto, RAND_seed, "([B)V"),
    NATIVE_METHOD(NativeCrypto, RAND_load_file, "(Ljava/lang/String;J)I"),
    NATIVE_METHOD(NativeCrypto, RAND_bytes, "([B)V"),
    NATIVE_METHOD(NativeCrypto, OBJ_txt2nid, "(Ljava/lang/String;)I"),
    NATIVE_METHOD(NativeCrypto, OBJ_txt2nid_longName, "(Ljava/lang/String;)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, OBJ_txt2nid_oid, "(Ljava/lang/String;)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, create_BIO_InputStream, ("(L" TO_STRING(JNI_JARJAR_PREFIX) "org/conscrypt/OpenSSLBIOInputStream;)J")),
    NATIVE_METHOD(NativeCrypto, create_BIO_OutputStream, "(Ljava/io/OutputStream;)J"),
    NATIVE_METHOD(NativeCrypto, BIO_read, "(J[B)I"),
    NATIVE_METHOD(NativeCrypto, BIO_write, "(J[BII)V"),
    NATIVE_METHOD(NativeCrypto, BIO_free, "(J)V"),
    NATIVE_METHOD(NativeCrypto, X509_NAME_print_ex, "(JJ)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, d2i_X509_bio, "(J)J"),
    NATIVE_METHOD(NativeCrypto, d2i_X509, "([B)J"),
    NATIVE_METHOD(NativeCrypto, i2d_X509, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, i2d_X509_PUBKEY, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, PEM_read_bio_X509, "(J)J"),
    NATIVE_METHOD(NativeCrypto, PEM_read_bio_PKCS7, "(JI)[J"),
    NATIVE_METHOD(NativeCrypto, d2i_PKCS7_bio, "(JI)[J"),
    NATIVE_METHOD(NativeCrypto, i2d_PKCS7, "([J)[B"),
    NATIVE_METHOD(NativeCrypto, ASN1_seq_unpack_X509_bio, "(J)[J"),
    NATIVE_METHOD(NativeCrypto, ASN1_seq_pack_X509, "([J)[B"),
    NATIVE_METHOD(NativeCrypto, X509_free, "(J)V"),
    NATIVE_METHOD(NativeCrypto, X509_cmp, "(JJ)I"),
    NATIVE_METHOD(NativeCrypto, get_X509_hashCode, "(J)I"),
    NATIVE_METHOD(NativeCrypto, X509_print_ex, "(JJJJ)V"),
    NATIVE_METHOD(NativeCrypto, X509_get_pubkey, "(J)J"),
    NATIVE_METHOD(NativeCrypto, X509_get_issuer_name, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, X509_get_subject_name, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, get_X509_pubkey_oid, "(J)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, get_X509_sig_alg_oid, "(J)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, get_X509_sig_alg_parameter, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, get_X509_issuerUID, "(J)[Z"),
    NATIVE_METHOD(NativeCrypto, get_X509_subjectUID, "(J)[Z"),
    NATIVE_METHOD(NativeCrypto, get_X509_ex_kusage, "(J)[Z"),
    NATIVE_METHOD(NativeCrypto, get_X509_ex_xkusage, "(J)[Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, get_X509_ex_pathlen, "(J)I"),
    NATIVE_METHOD(NativeCrypto, X509_get_ext_oid, "(JLjava/lang/String;)[B"),
    NATIVE_METHOD(NativeCrypto, X509_CRL_get_ext_oid, "(JLjava/lang/String;)[B"),
    NATIVE_METHOD(NativeCrypto, get_X509_CRL_crl_enc, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, X509_CRL_verify, "(JJ)V"),
    NATIVE_METHOD(NativeCrypto, X509_CRL_get_lastUpdate, "(J)J"),
    NATIVE_METHOD(NativeCrypto, X509_CRL_get_nextUpdate, "(J)J"),
    NATIVE_METHOD(NativeCrypto, X509_REVOKED_get_ext_oid, "(JLjava/lang/String;)[B"),
    NATIVE_METHOD(NativeCrypto, X509_REVOKED_get_serialNumber, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, X509_REVOKED_print, "(JJ)V"),
    NATIVE_METHOD(NativeCrypto, get_X509_REVOKED_revocationDate, "(J)J"),
    NATIVE_METHOD(NativeCrypto, get_X509_ext_oids, "(JI)[Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, get_X509_CRL_ext_oids, "(JI)[Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, get_X509_REVOKED_ext_oids, "(JI)[Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, get_X509_GENERAL_NAME_stack, "(JI)[[Ljava/lang/Object;"),
    NATIVE_METHOD(NativeCrypto, X509_get_notBefore, "(J)J"),
    NATIVE_METHOD(NativeCrypto, X509_get_notAfter, "(J)J"),
    NATIVE_METHOD(NativeCrypto, X509_get_version, "(J)J"),
    NATIVE_METHOD(NativeCrypto, X509_get_serialNumber, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, X509_verify, "(JJ)V"),
    NATIVE_METHOD(NativeCrypto, get_X509_cert_info_enc, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, get_X509_signature, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, get_X509_CRL_signature, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, get_X509_ex_flags, "(J)I"),
    NATIVE_METHOD(NativeCrypto, X509_check_issued, "(JJ)I"),
    NATIVE_METHOD(NativeCrypto, d2i_X509_CRL_bio, "(J)J"),
    NATIVE_METHOD(NativeCrypto, PEM_read_bio_X509_CRL, "(J)J"),
    NATIVE_METHOD(NativeCrypto, X509_CRL_get0_by_cert, "(JJ)J"),
    NATIVE_METHOD(NativeCrypto, X509_CRL_get0_by_serial, "(J[B)J"),
    NATIVE_METHOD(NativeCrypto, X509_CRL_get_REVOKED, "(J)[J"),
    NATIVE_METHOD(NativeCrypto, i2d_X509_CRL, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, X509_CRL_free, "(J)V"),
    NATIVE_METHOD(NativeCrypto, X509_CRL_print, "(JJ)V"),
    NATIVE_METHOD(NativeCrypto, get_X509_CRL_sig_alg_oid, "(J)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, get_X509_CRL_sig_alg_parameter, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, X509_CRL_get_issuer_name, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, X509_CRL_get_version, "(J)J"),
    NATIVE_METHOD(NativeCrypto, X509_CRL_get_ext, "(JLjava/lang/String;)J"),
    NATIVE_METHOD(NativeCrypto, X509_REVOKED_get_ext, "(JLjava/lang/String;)J"),
    NATIVE_METHOD(NativeCrypto, X509_REVOKED_dup, "(J)J"),
    NATIVE_METHOD(NativeCrypto, i2d_X509_REVOKED, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, X509_supported_extension, "(J)I"),
    NATIVE_METHOD(NativeCrypto, ASN1_TIME_to_Calendar, "(JLjava/util/Calendar;)V"),
    NATIVE_METHOD(NativeCrypto, SSL_CTX_new, "()J"),
    NATIVE_METHOD(NativeCrypto, SSL_CTX_free, "(J)V"),
    NATIVE_METHOD(NativeCrypto, SSL_CTX_set_session_id_context, "(J[B)V"),
    NATIVE_METHOD(NativeCrypto, SSL_new, "(J)J"),
    NATIVE_METHOD(NativeCrypto, SSL_enable_tls_channel_id, "(J)V"),
    NATIVE_METHOD(NativeCrypto, SSL_get_tls_channel_id, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, SSL_set1_tls_channel_id, "(JJ)V"),
    NATIVE_METHOD(NativeCrypto, SSL_use_PrivateKey, "(JJ)V"),
    NATIVE_METHOD(NativeCrypto, SSL_use_certificate, "(J[[B)V"),
    NATIVE_METHOD(NativeCrypto, SSL_check_private_key, "(J)V"),
    NATIVE_METHOD(NativeCrypto, SSL_set_client_CA_list, "(J[[B)V"),
    NATIVE_METHOD(NativeCrypto, SSL_get_mode, "(J)J"),
    NATIVE_METHOD(NativeCrypto, SSL_set_mode, "(JJ)J"),
    NATIVE_METHOD(NativeCrypto, SSL_clear_mode, "(JJ)J"),
    NATIVE_METHOD(NativeCrypto, SSL_get_options, "(J)J"),
    NATIVE_METHOD(NativeCrypto, SSL_set_options, "(JJ)J"),
    NATIVE_METHOD(NativeCrypto, SSL_clear_options, "(JJ)J"),
    NATIVE_METHOD(NativeCrypto, SSL_set_cipher_lists, "(J[Ljava/lang/String;)V"),
    NATIVE_METHOD(NativeCrypto, SSL_set_verify, "(JI)V"),
    NATIVE_METHOD(NativeCrypto, SSL_set_session, "(JJ)V"),
    NATIVE_METHOD(NativeCrypto, SSL_set_session_creation_enabled, "(JZ)V"),
    NATIVE_METHOD(NativeCrypto, SSL_set_tlsext_host_name, "(JLjava/lang/String;)V"),
    NATIVE_METHOD(NativeCrypto, SSL_get_servername, "(J)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, SSL_do_handshake, "(J" FILE_DESCRIPTOR SSL_CALLBACKS "IZ[B[B)I"),
    NATIVE_METHOD(NativeCrypto, SSL_renegotiate, "(J)V"),
    NATIVE_METHOD(NativeCrypto, SSL_get_certificate, "(J)[[B"),
    NATIVE_METHOD(NativeCrypto, SSL_get_peer_cert_chain, "(J)[[B"),
    NATIVE_METHOD(NativeCrypto, SSL_read, "(J" FILE_DESCRIPTOR SSL_CALLBACKS "[BIII)I"),
    NATIVE_METHOD(NativeCrypto, SSL_write, "(J" FILE_DESCRIPTOR SSL_CALLBACKS "[BIII)V"),
    NATIVE_METHOD(NativeCrypto, SSL_interrupt, "(J)V"),
    NATIVE_METHOD(NativeCrypto, SSL_shutdown, "(J" FILE_DESCRIPTOR SSL_CALLBACKS ")V"),
    NATIVE_METHOD(NativeCrypto, SSL_free, "(J)V"),
    NATIVE_METHOD(NativeCrypto, SSL_SESSION_session_id, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, SSL_SESSION_get_time, "(J)J"),
    NATIVE_METHOD(NativeCrypto, SSL_SESSION_get_version, "(J)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, SSL_SESSION_cipher, "(J)Ljava/lang/String;"),
    NATIVE_METHOD(NativeCrypto, SSL_SESSION_free, "(J)V"),
    NATIVE_METHOD(NativeCrypto, i2d_SSL_SESSION, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, d2i_SSL_SESSION, "([B)J"),
    NATIVE_METHOD(NativeCrypto, SSL_CTX_enable_npn, "(J)V"),
    NATIVE_METHOD(NativeCrypto, SSL_CTX_disable_npn, "(J)V"),
    NATIVE_METHOD(NativeCrypto, SSL_get_npn_negotiated_protocol, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, SSL_CTX_set_alpn_protos, "(J[B)I"),
    NATIVE_METHOD(NativeCrypto, SSL_get0_alpn_selected, "(J)[B"),
    NATIVE_METHOD(NativeCrypto, ERR_peek_last_error, "()J"),
};
#endif

static void initialize_conscrypt(JNIEnv* env) {
// RoboVM note: Not used on RoboVM.
#if 0
    jniRegisterNativeMethods(env, TO_STRING(JNI_JARJAR_PREFIX) "org/conscrypt/NativeCrypto",
                             sNativeCryptoMethods, NELEM(sNativeCryptoMethods));
#endif

    ScopedLocalRef<jclass> localClass(env,
            env->FindClass(TO_STRING(JNI_JARJAR_PREFIX) "org/conscrypt/OpenSSLBIOInputStream"));
    openSslOutputStreamClass = reinterpret_cast<jclass>(env->NewGlobalRef(localClass.get()));
    if (openSslOutputStreamClass == NULL) {
        ALOGE("failed to find class OpenSSLBIOInputStream");
        abort();
    }

    calendar_setMethod = env->GetMethodID(calendarClass, "set", "(IIIIII)V");
    inputStream_readMethod = env->GetMethodID(inputStreamClass, "read", "([B)I");
    integer_valueOfMethod = env->GetStaticMethodID(integerClass, "valueOf",
            "(I)Ljava/lang/Integer;");
    openSslInputStream_readLineMethod = env->GetMethodID(openSslOutputStreamClass, "gets",
            "([B)I");
    outputStream_writeMethod = env->GetMethodID(outputStreamClass, "write", "([B)V");
    outputStream_flushMethod = env->GetMethodID(outputStreamClass, "flush", "()V");
}

static jclass findClass(JNIEnv* env, const char* name) {
    ScopedLocalRef<jclass> localClass(env, env->FindClass(name));
    jclass result = reinterpret_cast<jclass>(env->NewGlobalRef(localClass.get()));
    if (result == NULL) {
        ALOGE("failed to find class '%s'", name);
        abort();
    }
    return result;
}

// RoboVM note: JNI_OnLoad() is not suppored with static JNI. The NativeCrypto Java class has been changed to call a native method named onload() instead.
#if 0
// Use JNI_OnLoad for when we're standalone
int JNI_OnLoad(JavaVM *vm, void*) {
    JNI_TRACE("JNI_OnLoad NativeCrypto");
    gJavaVM = vm;

    JNIEnv *env;
    if (vm->GetEnv((void**)&env, JNI_VERSION_1_6) != JNI_OK) {
        ALOGE("Could not get JNIEnv");
        return JNI_ERR;
    }
#endif
extern "C" void Java_com_android_org_conscrypt_NativeCrypto_onload(JNIEnv* env, jclass) {
    env->GetJavaVM(&gJavaVM);
    byteArrayClass = findClass(env, "[B");
    calendarClass = findClass(env, "java/util/Calendar");
    inputStreamClass = findClass(env, "java/io/InputStream");
    integerClass = findClass(env, "java/lang/Integer");
    objectClass = findClass(env, "java/lang/Object");
    objectArrayClass = findClass(env, "[Ljava/lang/Object;");
    outputStreamClass = findClass(env, "java/io/OutputStream");
    stringClass = findClass(env, "java/lang/String");

    initialize_conscrypt(env);
#if 0
    return JNI_VERSION_1_6;
#endif
}
