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
 * Native glue for Java class org.apache.harmony.xnet.provider.jsse.NativeCrypto
 */

#define LOG_TAG "NativeCrypto"

#include <algorithm>
#include <fcntl.h>
#include <sys/socket.h>
#include <unistd.h>

#include <jni.h>

#include <openssl/dsa.h>
#include <openssl/engine.h>
#include <openssl/err.h>
#include <openssl/evp.h>
#include <openssl/rand.h>
#include <openssl/rsa.h>
#include <openssl/ssl.h>

#include "AsynchronousSocketCloseMonitor.h"
#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "NetFd.h"
#include "NetworkUtilities.h"
#include "ScopedLocalRef.h"
#include "ScopedPrimitiveArray.h"
#include "ScopedUtfChars.h"
#include "UniquePtr.h"

#undef WITH_JNI_TRACE
#undef WITH_JNI_TRACE_DATA

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

struct X509_NAME_Delete {
    void operator()(X509_NAME* p) const {
        X509_NAME_free(p);
    }
};
typedef UniquePtr<X509_NAME, X509_NAME_Delete> Unique_X509_NAME;

struct sk_SSL_CIPHER_Delete {
    void operator()(STACK_OF(SSL_CIPHER)* p) const {
        sk_SSL_CIPHER_free(p);
    }
};
typedef UniquePtr<STACK_OF(SSL_CIPHER), sk_SSL_CIPHER_Delete> Unique_sk_SSL_CIPHER;

struct sk_X509_Delete {
    void operator()(STACK_OF(X509)* p) const {
        sk_X509_free(p);
    }
};
typedef UniquePtr<STACK_OF(X509), sk_X509_Delete> Unique_sk_X509;

struct sk_X509_NAME_Delete {
    void operator()(STACK_OF(X509_NAME)* p) const {
        sk_X509_NAME_free(p);
    }
};
typedef UniquePtr<STACK_OF(X509_NAME), sk_X509_NAME_Delete> Unique_sk_X509_NAME;

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

/*
 * Checks this thread's OpenSSL error queue and throws a RuntimeException if
 * necessary.
 *
 * @return true if an exception was thrown, false if not.
 */
static bool throwExceptionIfNecessary(JNIEnv* env, const char* location  __attribute__ ((unused))) {
    int error = ERR_get_error();
    int result = false;

    if (error != 0) {
        char message[256];
        ERR_error_string_n(error, message, sizeof(message));
        JNI_TRACE("OpenSSL error in %s %d: %s", location, error, message);
        jniThrowRuntimeException(env, message);
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
static SSL_CTX* to_SSL_CTX(JNIEnv* env, int ssl_ctx_address, bool throwIfNull) {
    SSL_CTX* ssl_ctx = reinterpret_cast<SSL_CTX*>(static_cast<uintptr_t>(ssl_ctx_address));
    if ((ssl_ctx == NULL) && throwIfNull) {
        JNI_TRACE("ssl_ctx == null");
        jniThrowNullPointerException(env, "ssl_ctx == null");
    }
    return ssl_ctx;
}

static SSL* to_SSL(JNIEnv* env, int ssl_address, bool throwIfNull) {
    SSL* ssl = reinterpret_cast<SSL*>(static_cast<uintptr_t>(ssl_address));
    if ((ssl == NULL) && throwIfNull) {
        JNI_TRACE("ssl == null");
        jniThrowNullPointerException(env, "ssl == null");
    }
    return ssl;
}

static SSL_SESSION* to_SSL_SESSION(JNIEnv* env, int ssl_session_address, bool throwIfNull) {
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
static jbyteArray bignumToArray(JNIEnv* env, BIGNUM* source) {
    JNI_TRACE("bignumToArray(%p)", source);

    if (source == NULL) {
        jniThrowNullPointerException(env, NULL);
        return NULL;
    }

    int len = BN_num_bytes(source) + 1;
    jbyteArray javaBytes = env->NewByteArray(len);
    ScopedByteArrayRW bytes(env, javaBytes);
    if (bytes.get() == NULL) {
        JNI_TRACE("bignumToArray(%p) => NULL", source);
        return NULL;
    }

    unsigned char* tmp = reinterpret_cast<unsigned char*>(bytes.get());

    // Set the sign for the Java code.
    if (BN_is_negative(source)) {
        *tmp = 0xFF;
    } else {
        *tmp = 0x00;
    }

    if (BN_bn2bin(source, tmp + 1) <= 0) {
        throwExceptionIfNecessary(env, "bignumToArray");
        return NULL;
    }

    JNI_TRACE("bignumToArray(%p) => %p", source, javaBytes);
    return javaBytes;
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
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_clinit(JNIEnv*, jclass)
{
    SSL_load_error_strings();
    ERR_load_crypto_strings();
    SSL_library_init();
    OpenSSL_add_all_algorithms();
    THREAD_setup();
}

extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_ENGINE_1load_1dynamic(JNIEnv*, jclass) {
    JNI_TRACE("ENGINE_load_dynamic()");

    ENGINE_load_dynamic();
}

extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_ENGINE_1by_1id(JNIEnv* env, jclass, jstring idJava) {
    JNI_TRACE("ENGINE_by_id(%p)", idJava);

    ScopedUtfChars id(env, idJava);
    if (id.c_str() == NULL) {
        jniThrowException(env, "java/lang/IllegalArgumentException", "id == NULL");
        return 0;
    }

    ENGINE* e = ENGINE_by_id(id.c_str());
    if (e == NULL) {
        throwExceptionIfNecessary(env, "ENGINE_by_id");
        return 0;
    }

    JNI_TRACE("ENGINE_by_id(%p) => %p", idJava, e);
    return static_cast<jint>(reinterpret_cast<uintptr_t>(e));
}

extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_ENGINE_1init(JNIEnv* env, jclass, jint engineRef) {
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

extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_ENGINE_1finish(JNIEnv* env, jclass, jint engineRef) {
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

extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_ENGINE_1free(JNIEnv* env, jclass, jint engineRef) {
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

extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_ENGINE_1load_1private_1key(JNIEnv* env, jclass, jint engineRef,
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
    return static_cast<jint>(reinterpret_cast<uintptr_t>(pkey.release()));
}

/**
 * public static native int EVP_PKEY_new_DSA(byte[] p, byte[] q, byte[] g,
 *                                           byte[] pub_key, byte[] priv_key);
 */
extern "C" EVP_PKEY* Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1PKEY_1new_1DSA(JNIEnv* env, jclass,
                                               jbyteArray p, jbyteArray q, jbyteArray g,
                                               jbyteArray pub_key, jbyteArray priv_key) {
    JNI_TRACE("EVP_PKEY_new_DSA(p=%p, q=%p, g=%p, pub_key=%p, priv_key=%p)",
              p, q, g, pub_key, priv_key);

    Unique_DSA dsa(DSA_new());
    if (dsa.get() == NULL) {
        jniThrowRuntimeException(env, "DSA_new failed");
        return NULL;
    }

    if (!arrayToBignum(env, p, &dsa->p)) {
        return NULL;
    }

    if (!arrayToBignum(env, q, &dsa->q)) {
        return NULL;
    }

    if (!arrayToBignum(env, g, &dsa->g)) {
        return NULL;
    }

    if (pub_key != NULL && !arrayToBignum(env, pub_key, &dsa->pub_key)) {
        return NULL;
    }

    if (priv_key != NULL && !arrayToBignum(env, priv_key, &dsa->priv_key)) {
        return NULL;
    }

    if (dsa->p == NULL || dsa->q == NULL || dsa->g == NULL
            || (dsa->pub_key == NULL && dsa->priv_key == NULL)) {
        jniThrowRuntimeException(env, "Unable to convert BigInteger to BIGNUM");
        return NULL;
    }

    Unique_EVP_PKEY pkey(EVP_PKEY_new());
    if (pkey.get() == NULL) {
        jniThrowRuntimeException(env, "EVP_PKEY_new failed");
        return NULL;
    }
    if (EVP_PKEY_assign_DSA(pkey.get(), dsa.get()) != 1) {
        jniThrowRuntimeException(env, "EVP_PKEY_assign_DSA failed");
        return NULL;
    }
    OWNERSHIP_TRANSFERRED(dsa);
    JNI_TRACE("EVP_PKEY_new_DSA(p=%p, q=%p, g=%p, pub_key=%p, priv_key=%p) => %p",
              p, q, g, pub_key, priv_key, pkey.get());
    return pkey.release();
}

/**
 * private static native int EVP_PKEY_new_RSA(byte[] n, byte[] e, byte[] d, byte[] p, byte[] q);
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1PKEY_1new_1RSA(JNIEnv* env, jclass,
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
    return static_cast<jint>(reinterpret_cast<uintptr_t>(pkey.release()));
}

/**
 * private static native int EVP_PKEY_size(int pkey);
 */
extern "C" int Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1PKEY_1type(JNIEnv* env, jclass, jint pkeyRef) {
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
extern "C" int Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1PKEY_1size(JNIEnv* env, jclass, jint pkeyRef) {
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

/**
 * private static native void EVP_PKEY_free(int pkey);
 */
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1PKEY_1free(JNIEnv*, jclass, EVP_PKEY* pkey) {
    JNI_TRACE("EVP_PKEY_free(%p)", pkey);

    if (pkey != NULL) {
        EVP_PKEY_free(pkey);
    }
}

/*
 * static native byte[] i2d_PKCS8_PRIV_KEY_INFO(int, byte[])
 */
extern "C" jbyteArray Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_i2d_1PKCS8_1PRIV_1KEY_1INFO(JNIEnv* env, jclass, jint pkeyRef) {
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

    int len = i2d_PKCS8_PRIV_KEY_INFO(pkcs8.get(), NULL);
    if (len < 0) {
        throwExceptionIfNecessary(env, "i2d_PKCS8_PRIV_KEY_INFO");
        return NULL;
    }

    jbyteArray javaBytes = env->NewByteArray(len);
    ScopedByteArrayRW bytes(env, javaBytes);
    if (bytes.get() == NULL) {
        return NULL;
    }

    unsigned char* tmp = reinterpret_cast<unsigned char*>(bytes.get());
    if (i2d_PKCS8_PRIV_KEY_INFO(pkcs8.get(), &tmp) < 0) {
        throwExceptionIfNecessary(env, "i2d_PKCS8_PRIV_KEY_INFO");
        return NULL;
    }

    JNI_TRACE("pkey=%p i2d_PKCS8_PRIV_KEY_INFO => size=%d", pkey, len);
    return javaBytes;
}

/*
 * static native int d2i_PKCS8_PRIV_KEY_INFO(byte[])
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_d2i_1PKCS8_1PRIV_1KEY_1INFO(JNIEnv* env, jclass, jbyteArray keyJavaBytes) {
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
    return static_cast<jint>(reinterpret_cast<uintptr_t>(pkey.release()));
}

/*
 * static native byte[] i2d_PUBKEY(int)
 */
extern "C" jbyteArray Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_i2d_1PUBKEY(JNIEnv* env, jclass, jint pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("i2d_PUBKEY(%p)", pkey);

    if (pkey == NULL) {
        jniThrowNullPointerException(env, NULL);
        return NULL;
    }

    int len = i2d_PUBKEY(pkey, NULL);
    if (len < 0) {
        throwExceptionIfNecessary(env, "i2d_PUBKEY");
        JNI_TRACE("i2d_PUBKEY(%p) => threw error measuring key", pkey);
        return NULL;
    }

    jbyteArray javaBytes = env->NewByteArray(len);
    ScopedByteArrayRW bytes(env, javaBytes);
    if (bytes.get() == NULL) {
        return NULL;
    }

    unsigned char* tmp = reinterpret_cast<unsigned char*>(bytes.get());
    if (i2d_PUBKEY(pkey, &tmp) < 0) {
        throwExceptionIfNecessary(env, "i2d_PUBKEY");
        JNI_TRACE("i2d_PUBKEY(%p) => threw error converting key", pkey);
        return NULL;
    }

    JNI_TRACE("pkey=%p i2d_PUBKEY => size=%d", pkey, len);
    return javaBytes;
}

/*
 * static native int d2i_PUBKEY(byte[])
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_d2i_1PUBKEY(JNIEnv* env, jclass, jbyteArray javaBytes) {
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

    return static_cast<jint>(reinterpret_cast<uintptr_t>(pkey.release()));
}

/*
 * public static native int RSA_generate_key(int modulusBits, byte[] publicExponent);
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_RSA_1generate_1key_1ex(JNIEnv* env, jclass, jint modulusBits,
        jbyteArray publicExponent) {
    JNI_TRACE("RSA_generate_key_ex(%d, %p)", modulusBits, publicExponent);

    BIGNUM* eRef;
    if (!arrayToBignum(env, publicExponent, &eRef)) {
        return 0;
    }
    Unique_BIGNUM e(eRef);

    Unique_RSA rsa(RSA_new());
    if (rsa.get() == NULL) {
        jniThrowOutOfMemoryError(env, "Unable to allocate RSA key");
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
    return static_cast<jint>(reinterpret_cast<uintptr_t>(pkey.release()));
}

/*
 * public static native byte[][] get_RSA_public_params(int);
 */
extern "C" jobjectArray Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_get_1RSA_1public_1params(JNIEnv* env, jclass, jint pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("get_RSA_public_params(%p)", pkey);

    Unique_RSA rsa(EVP_PKEY_get1_RSA(pkey));
    if (rsa.get() == NULL) {
        jniThrowRuntimeException(env, "get_RSA_public_params failed");
        return 0;
    }

    jobjectArray joa = env->NewObjectArray(2, JniConstants::byteArrayClass, NULL);
    if (joa == NULL) {
        return NULL;
    }

    jbyteArray n = bignumToArray(env, rsa->n);
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 0, n);

    jbyteArray e = bignumToArray(env, rsa->e);
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 1, e);

    return joa;
}

/*
 * public static native byte[][] get_RSA_private_params(int);
 */
extern "C" jobjectArray Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_get_1RSA_1private_1params(JNIEnv* env, jclass, jint pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("get_RSA_public_params(%p)", pkey);

    Unique_RSA rsa(EVP_PKEY_get1_RSA(pkey));
    if (rsa.get() == NULL) {
        jniThrowRuntimeException(env, "get_RSA_public_params failed");
        return 0;
    }

    jobjectArray joa = env->NewObjectArray(8, JniConstants::byteArrayClass, NULL);
    if (joa == NULL) {
        return NULL;
    }

    jbyteArray n = bignumToArray(env, rsa->n);
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 0, n);

    if (rsa->e != NULL) {
        jbyteArray e = bignumToArray(env, rsa->e);
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 1, e);
    }

    if (rsa->d != NULL) {
        jbyteArray d = bignumToArray(env, rsa->d);
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 2, d);
    }

    if (rsa->p != NULL) {
        jbyteArray p = bignumToArray(env, rsa->p);
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 3, p);
    }

    if (rsa->q != NULL) {
        jbyteArray q = bignumToArray(env, rsa->q);
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 4, q);
    }

    if (rsa->dmp1 != NULL) {
        jbyteArray dmp1 = bignumToArray(env, rsa->dmp1);
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 5, dmp1);
    }

    if (rsa->dmq1 != NULL) {
        jbyteArray dmq1 = bignumToArray(env, rsa->dmq1);
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 6, dmq1);
    }

    if (rsa->iqmp != NULL) {
        jbyteArray iqmp = bignumToArray(env, rsa->iqmp);
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
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_DSA_1generate_1key(JNIEnv* env, jclass, jint primeBits,
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
        jniThrowOutOfMemoryError(env, "Unable to allocate DSA key");
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
        return 0;
    }

    if (EVP_PKEY_assign_DSA(pkey.get(), dsa.get()) != 1) {
        JNI_TRACE("DSA_generate_key failed");
        jniThrowRuntimeException(env, "NativeCrypto_DSA_generate_key failed");
        return 0;
    }

    OWNERSHIP_TRANSFERRED(dsa);
    JNI_TRACE("DSA_generate_key(n=%d, e=%p) => %p", primeBits, seedPtr.get(), pkey.get());
    return static_cast<jint>(reinterpret_cast<uintptr_t>(pkey.release()));
}

/*
 * public static native byte[][] get_DSA_params(int);
 */
extern "C" jobjectArray Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_get_1DSA_1params(JNIEnv* env, jclass, jint pkeyRef) {
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("get_DSA_params(%p)", pkey);

    Unique_DSA dsa(EVP_PKEY_get1_DSA(pkey));
    if (dsa.get() == NULL) {
        jniThrowRuntimeException(env, "get_DSA_params failed");
        return 0;
    }

    jobjectArray joa = env->NewObjectArray(5, JniConstants::byteArrayClass, NULL);
    if (joa == NULL) {
        return NULL;
    }

    jbyteArray g = bignumToArray(env, dsa->g);
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 0, g);

    jbyteArray p = bignumToArray(env, dsa->p);
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 1, p);

    jbyteArray q = bignumToArray(env, dsa->q);
    if (env->ExceptionCheck()) {
        return NULL;
    }
    env->SetObjectArrayElement(joa, 2, q);

    if (dsa->pub_key != NULL) {
        jbyteArray pub_key = bignumToArray(env, dsa->pub_key);
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 3, pub_key);
    }

    if (dsa->priv_key != NULL) {
        jbyteArray priv_key = bignumToArray(env, dsa->priv_key);
        if (env->ExceptionCheck()) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, 4, priv_key);
    }

    return joa;
}

/*
 * public static native void EVP_MD_CTX_destroy(int)
 */
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1MD_1CTX_1destroy(JNIEnv*, jclass, EVP_MD_CTX* ctx) {
    JNI_TRACE("NativeCrypto_EVP_MD_CTX_destroy(%p)", ctx);

    if (ctx != NULL) {
        EVP_MD_CTX_destroy(ctx);
    }
}

/*
 * public static native int EVP_MD_CTX_copy(int)
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1MD_1CTX_1copy(JNIEnv* env, jclass, EVP_MD_CTX* ctx) {
    JNI_TRACE("NativeCrypto_EVP_MD_CTX_copy(%p)", ctx);

    if (ctx == NULL) {
        jniThrowNullPointerException(env, NULL);
        return 0;
    }

    EVP_MD_CTX* copy = EVP_MD_CTX_create();
    if (copy == NULL) {
        jniThrowOutOfMemoryError(env, "Unable to allocate copy of EVP_MD_CTX");
        return 0;
    }

    EVP_MD_CTX_init(copy);
    int result = EVP_MD_CTX_copy_ex(copy, ctx);
    if (result == 0) {
        EVP_MD_CTX_destroy(copy);
        jniThrowRuntimeException(env, "Unable to copy EVP_MD_CTX");
        return 0;
    }

    JNI_TRACE("NativeCrypto_EVP_MD_CTX_copy(%p) => %p", ctx, copy);
    return static_cast<jint>(reinterpret_cast<uintptr_t>(copy));
}

/*
 * public static native int EVP_DigestFinal(int, byte[], int)
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1DigestFinal(JNIEnv* env, jclass, jint ctxRef,
                                         jbyteArray hash, jint offset) {
    EVP_MD_CTX* ctx = reinterpret_cast<EVP_MD_CTX*>(ctxRef);
    JNI_TRACE("NativeCrypto_EVP_DigestFinal(%p, %p, %d)", ctx, hash, offset);

    if (ctx == NULL || hash == NULL) {
        jniThrowNullPointerException(env, NULL);
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
extern "C" int Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1DigestInit(JNIEnv* env, jclass, jint evpMdRef) {
    EVP_MD* evp_md = reinterpret_cast<EVP_MD*>(evpMdRef);
    JNI_TRACE("NativeCrypto_EVP_DigestInit(%p)", evp_md);

    if (evp_md == NULL) {
        jniThrowNullPointerException(env, NULL);
        return 0;
    }

    Unique_EVP_MD_CTX ctx(EVP_MD_CTX_create());
    if (ctx.get() == NULL) {
        jniThrowOutOfMemoryError(env, "Unable to allocate EVP_MD_CTX");
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
    return static_cast<jint>(reinterpret_cast<uintptr_t>(ctx.release()));
}

/*
 * public static native int EVP_get_digestbyname(java.lang.String)
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1get_1digestbyname(JNIEnv* env, jclass, jstring algorithm) {
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
    return static_cast<jint>(reinterpret_cast<uintptr_t>(evp_md));
}

/*
 * public static native int EVP_MD_size(int)
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1MD_1size(JNIEnv* env, jclass, EVP_MD* evp_md) {
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
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1MD_1block_1size(JNIEnv* env, jclass, EVP_MD* evp_md) {
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
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1DigestUpdate(JNIEnv* env, jclass, jint ctxRef,
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

/*
 * public static native int EVP_SignInit(java.lang.String)
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1SignInit(JNIEnv* env, jclass, jstring algorithm) {
    JNI_TRACE("NativeCrypto_EVP_SignInit(%p)", algorithm);

    if (algorithm == NULL) {
        jniThrowNullPointerException(env, NULL);
        return 0;
    }

    Unique_EVP_MD_CTX ctx(EVP_MD_CTX_create());
    if (ctx.get() == NULL) {
        jniThrowOutOfMemoryError(env, "Unable to allocate EVP_MD_CTX");
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
        jniThrowRuntimeException(env, "Hash algorithm not found");
        return 0;
    }

    int ok = EVP_SignInit(ctx.get(), digest);
    if (ok == 0) {
        bool exception = throwExceptionIfNecessary(env, "NativeCrypto_EVP_SignInit");
        if (exception) {
            return 0;
        }
    }
    return static_cast<jint>(reinterpret_cast<uintptr_t>(ctx.release()));
}

/*
 * public static native void EVP_SignUpdate(int, byte[], int, int)
 */
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1SignUpdate(JNIEnv* env, jclass, jint ctxRef,
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
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1SignFinal(JNIEnv* env, jclass, jint ctxRef, jbyteArray signature,
        jint offset, jint pkeyRef) {
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
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1VerifyInit(JNIEnv* env, jclass, jstring algorithm) {
    JNI_TRACE("NativeCrypto_EVP_VerifyInit(%p)", algorithm);

    if (algorithm == NULL) {
        jniThrowNullPointerException(env, NULL);
        return 0;
    }

    Unique_EVP_MD_CTX ctx(EVP_MD_CTX_create());
    if (ctx.get() == NULL) {
        jniThrowOutOfMemoryError(env, "Unable to allocate EVP_MD_CTX");
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
    return static_cast<jint>(reinterpret_cast<uintptr_t>(ctx.release()));
}

/*
 * public static native void EVP_VerifyUpdate(int, byte[], int, int)
 */
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1VerifyUpdate(JNIEnv* env, jclass, jint ctxRef,
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
extern "C" int Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1VerifyFinal(JNIEnv* env, jclass, jint ctxRef, jbyteArray buffer,
                                        jint offset, jint length, jint pkeyRef) {
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
    JNI_TRACE("NativeCrypto_EVP_VerifyFinal(%p, %p, %d, %d, %p) => %d",
              ctx, buffer, offset, length, pkey, ok);

    return ok;
}

/*
 * public static native int EVP_get_cipherbyname(java.lang.String)
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1get_1cipherbyname(JNIEnv* env, jclass, jstring algorithm) {
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
        jniThrowRuntimeException(env, "Cipher algorithm not found");
        return 0;
    }

    JNI_TRACE("EVP_get_cipherbyname(%s) => %p", algorithmChars.c_str(), evp_cipher);
    return static_cast<jint>(reinterpret_cast<uintptr_t>(evp_cipher));
}

/*
 * public static native int EVP_CipherInit_ex(int cipherNid, byte[] key, byte[] iv,
 *          boolean encrypting);
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1CipherInit_1ex(JNIEnv* env, jclass, jint cipherRef, jbyteArray keyArray,
        jbyteArray ivArray, jboolean encrypting) {
    const EVP_CIPHER* evp_cipher = reinterpret_cast<const EVP_CIPHER*>(cipherRef);
    JNI_TRACE("EVP_CipherInit_ex(%p, %p, %p, %d)", evp_cipher, keyArray, ivArray, encrypting ? 1 : 0);

    ScopedByteArrayRO keyBytes(env, keyArray);
    if (keyBytes.get() == NULL) {
        return 0;
    }

    // The IV can be null if we're using ECB.
    UniquePtr<unsigned char[]> ivPtr;
    if (ivArray != NULL) {
        ScopedByteArrayRO ivBytes(env, ivArray);
        if (ivBytes.get() == NULL) {
            return 0;
        }

        ivPtr.reset(new unsigned char[ivBytes.size()]);
        memcpy(ivPtr.get(), ivBytes.get(), ivBytes.size());
    }

    Unique_EVP_CIPHER_CTX ctx(EVP_CIPHER_CTX_new());
    if (ctx.get() == NULL) {
        jniThrowOutOfMemoryError(env, "Unable to allocate cipher context");
        JNI_TRACE("ctx=%p EVP_CipherInit_ex => context allocation error", evp_cipher);
        return 0;
    }

    const unsigned char* key = reinterpret_cast<const unsigned char*>(keyBytes.get());
    if (!EVP_CipherInit_ex(ctx.get(), evp_cipher, NULL, key, ivPtr.get(), encrypting ? 1 : 0)) {
        throwExceptionIfNecessary(env, "EVP_CipherInit_ex");
        JNI_TRACE("EVP_CipherInit_ex => error initializing cipher");
        return 0;
    }

    JNI_TRACE("EVP_CipherInit_ex(%p, %p, %p, %d) => %p", evp_cipher, keyArray, ivArray,
            encrypting ? 1 : 0, ctx.get());
    return static_cast<jint>(reinterpret_cast<uintptr_t>(ctx.release()));
}

/*
 *  public static native int EVP_CipherUpdate(int ctx, byte[] out, int outOffset, byte[] in,
 *          int inOffset);
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1CipherUpdate(JNIEnv* env, jclass, jint ctxRef, jbyteArray outArray,
        jint outOffset, jbyteArray inArray, jint inOffset) {
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
    if (size_t(inOffset + EVP_CIPHER_CTX_block_size(ctx)) > inSize) {
        jniThrowException(env, "java/lang/IndexOutOfBoundsException", NULL);
        return 0;
    }

    ScopedByteArrayRW outBytes(env, outArray);
    if (outBytes.get() == NULL) {
        return 0;
    }
    const size_t outSize = outBytes.size();
    if (size_t(outOffset + EVP_CIPHER_CTX_block_size(ctx)) > outSize) {
        jniThrowException(env, "java/lang/IndexOutOfBoundsException", NULL);
        return 0;
    }

    unsigned char* out = reinterpret_cast<unsigned char*>(outBytes.get());
    const unsigned char* in = reinterpret_cast<const unsigned char*>(inBytes.get());

    int outl;
    if (!EVP_CipherUpdate(ctx, out + outOffset, &outl, in+inOffset, inSize - inOffset)) {
        throwExceptionIfNecessary(env, "EVP_CipherInit_ex");
        JNI_TRACE("ctx=%p EVP_CipherUpdate => threw error", ctx);
        return 0;
    }

    JNI_TRACE("EVP_CipherUpdate(%p, %p, %d, %p, %d) => %d", ctx, outArray, outOffset, inArray,
            inOffset, outl);
    return outl;
}

/*
 *  public static native int EVP_CipherFinal(int ctx, byte[] out, int outOffset);
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1CipherFinal_1ex(JNIEnv* env, jclass, jint ctxRef, jbyteArray outArray,
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
        throwExceptionIfNecessary(env, "EVP_CipherInit_ex");
        JNI_TRACE("ctx=%p EVP_CipherUpdate => threw error", ctx);
        return 0;
    }

    JNI_TRACE("EVP_CipherFinal(%p, %p, %d) => %d", ctx, outArray, outOffset, outl);
    return outl;
}

/*
 * public static native void EVP_CIPHER_CTX_cleanup(int ctx);
 */
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_EVP_1CIPHER_1CTX_1cleanup(JNIEnv*, jclass, jint ctxRef) {
    EVP_CIPHER_CTX* ctx = reinterpret_cast<EVP_CIPHER_CTX*>(ctxRef);

    if (ctx != NULL) {
        EVP_CIPHER_CTX_cleanup(ctx);
    }
}

/**
 * public static native void RAND_seed(byte[]);
 */
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_RAND_1seed(JNIEnv* env, jclass, jbyteArray seed) {
    JNI_TRACE("NativeCrypto_RAND_seed seed=%p", seed);
    ScopedByteArrayRO randseed(env, seed);
    if (randseed.get() == NULL) {
        return;
    }
    RAND_seed(randseed.get(), randseed.size());
}

extern "C" int Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_RAND_1load_1file(JNIEnv* env, jclass, jstring filename, jlong max_bytes) {
    JNI_TRACE("NativeCrypto_RAND_load_file filename=%p max_bytes=%lld", filename, max_bytes);
    ScopedUtfChars file(env, filename);
    if (file.c_str() == NULL) {
        return -1;
    }
    int result = RAND_load_file(file.c_str(), max_bytes);
    JNI_TRACE("NativeCrypto_RAND_load_file file=%s => %d", file.c_str(), result);
    return result;
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

    jobjectArray joa = env->NewObjectArray(count, JniConstants::byteArrayClass, NULL);
    if (joa == NULL) {
        return NULL;
    }

    for (int i = 0; i < count; i++) {
        X509* cert = sk_X509_value(chain, i);

        int len = i2d_X509(cert, NULL);
        if (len < 0) {
            return NULL;
        }
        ScopedLocalRef<jbyteArray> byteArray(env, env->NewByteArray(len));
        if (byteArray.get() == NULL) {
            return NULL;
        }
        ScopedByteArrayRW bytes(env, byteArray.get());
        if (bytes.get() == NULL) {
            return NULL;
        }
        unsigned char* p = reinterpret_cast<unsigned char*>(bytes.get());
        int n = i2d_X509(cert, &p);
        if (n < 0) {
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

    jobjectArray joa = env->NewObjectArray(count, JniConstants::byteArrayClass, NULL);
    if (joa == NULL) {
        return NULL;
    }

    for (int i = 0; i < count; i++) {
        X509_NAME* principal = sk_X509_NAME_value(names, i);

        int len = i2d_X509_NAME(principal, NULL);
        if (len < 0) {
            return NULL;
        }
        ScopedLocalRef<jbyteArray> byteArray(env, env->NewByteArray(len));
        if (byteArray.get() == NULL) {
            return NULL;
        }
        ScopedByteArrayRW bytes(env, byteArray.get());
        if (bytes.get() == NULL) {
            return NULL;
        }
        unsigned char* p = reinterpret_cast<unsigned char*>(bytes.get());
        int n = i2d_X509_NAME(principal, &p);
        if (n < 0) {
            return NULL;
        }
        env->SetObjectArrayElement(joa, i, byteArray.get());
    }

    return joa;
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
    Unique_RSA ephemeralRsa;
    Unique_EC_KEY ephemeralEc;

    /**
     * Creates the application data context for the SSL*.
     */
  public:
    static AppData* create() {
        UniquePtr<AppData> appData(new AppData());
        if (pipe(appData.get()->fdsEmergency) == -1) {
            return NULL;
        }
        if (!setBlocking(appData.get()->fdsEmergency[0], false)) {
            return NULL;
        }
        if (MUTEX_SETUP(appData.get()->mutex) == -1) {
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
     */
    bool setCallbackState(JNIEnv* e, jobject shc, jobject fd, jbyteArray npnProtocols) {
        NetFd netFd(e, fd);
        if (netFd.isClosed()) {
            return false;
        }
        env = e;
        sslHandshakeCallbacks = shc;
        fileDescriptor = fd;
        if (npnProtocols != NULL) {
            npnProtocolsArray = npnProtocols;
            npnProtocolsLength = e->GetArrayLength(npnProtocols);
            npnProtocolsData = e->GetByteArrayElements(npnProtocols, NULL);
            if (npnProtocolsData == NULL) {
                return false;
            }
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
 * Call back to ask for a client certificate
 */
static int client_cert_cb(SSL* ssl, X509** x509Out, EVP_PKEY** pkeyOut) {
    JNI_TRACE("ssl=%p client_cert_cb x509Out=%p pkeyOut=%p", ssl, x509Out, pkeyOut);

    AppData* appData = toAppData(ssl);
    JNIEnv* env = appData->env;
    if (env == NULL) {
        ALOGE("AppData->env missing in client_cert_cb");
        JNI_TRACE("ssl=%p client_cert_cb env error => 0", ssl);
        return 0;
    }
    if (env->ExceptionCheck()) {
        JNI_TRACE("ssl=%p client_cert_cb already pending exception => 0", ssl);
        return 0;
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
        return 0;
    }

    // Check for values set from Java
    X509*     certificate = SSL_get_certificate(ssl);
    EVP_PKEY* privatekey  = SSL_get_privatekey(ssl);
    int result;
    if (certificate != NULL && privatekey != NULL) {
        *x509Out = certificate;
        *pkeyOut = privatekey;
        result = 1;
    } else {
        *x509Out = NULL;
        *pkeyOut = NULL;
        result = 0;
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
extern "C" int Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1CTX_1new(JNIEnv* env, jclass) {
    Unique_SSL_CTX sslCtx(SSL_CTX_new(SSLv23_method()));
    if (sslCtx.get() == NULL) {
        jniThrowRuntimeException(env, "SSL_CTX_new");
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
#if defined(SSL_MODE_SMALL_BUFFERS) /* not all SSL versions have this */
    mode |= SSL_MODE_SMALL_BUFFERS;  /* lazily allocate record buffers; usually saves
                                      * 44k over the default */
#endif
    SSL_CTX_set_mode(sslCtx.get(), mode);

    SSL_CTX_set_cert_verify_callback(sslCtx.get(), cert_verify_callback, NULL);
    SSL_CTX_set_info_callback(sslCtx.get(), info_callback);
    SSL_CTX_set_client_cert_cb(sslCtx.get(), client_cert_cb);
    SSL_CTX_set_tmp_rsa_callback(sslCtx.get(), tmp_rsa_callback);
    SSL_CTX_set_tmp_dh_callback(sslCtx.get(), tmp_dh_callback);
    SSL_CTX_set_tmp_ecdh_callback(sslCtx.get(), tmp_ecdh_callback);

    JNI_TRACE("NativeCrypto_SSL_CTX_new => %p", sslCtx.get());
    return (jint) sslCtx.release();
}

/**
 * public static native void SSL_CTX_free(int ssl_ctx)
 */
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1CTX_1free(JNIEnv* env,
        jclass, jint ssl_ctx_address)
{
    SSL_CTX* ssl_ctx = to_SSL_CTX(env, ssl_ctx_address, true);
    JNI_TRACE("ssl_ctx=%p NativeCrypto_SSL_CTX_free", ssl_ctx);
    if (ssl_ctx == NULL) {
        return;
    }
    SSL_CTX_free(ssl_ctx);
}

extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1CTX_1set_1session_1id_1context(JNIEnv* env, jclass,
                                                        jint ssl_ctx_address, jbyteArray sid_ctx)
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
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1new(JNIEnv* env, jclass, jint ssl_ctx_address)
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
    return (jint) ssl.release();
}

extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1use_1OpenSSL_1PrivateKey(JNIEnv* env, jclass, jint ssl_address, jint pkeyRef) {
    SSL* ssl = to_SSL(env, ssl_address, true);
    EVP_PKEY* pkey = reinterpret_cast<EVP_PKEY*>(pkeyRef);
    JNI_TRACE("ssl=%p SSL_use_OpenSSL_PrivateKey privatekey=%p", ssl, pkey);
    if (ssl == NULL) {
        return;
    }

    if (pkey == NULL) {
        return;
    }

    int ret = SSL_use_PrivateKey(ssl, pkey);
    if (ret != 1) {
        ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error setting private key");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p SSL_use_OpenSSL_PrivateKey => error", ssl);
        return;
    }

    JNI_TRACE("ssl=%p SSL_use_OpenSSL_PrivateKey => ok", ssl);
}

extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1use_1PrivateKey(JNIEnv* env, jclass,
                                            jint ssl_address, jbyteArray privatekey)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_use_PrivateKey privatekey=%p", ssl, privatekey);
    if (ssl == NULL) {
        return;
    }

    ScopedByteArrayRO buf(env, privatekey);
    if (buf.get() == NULL) {
        JNI_TRACE("ssl=%p NativeCrypto_SSL_use_PrivateKey => threw exception", ssl);
        return;
    }
    const unsigned char* tmp = reinterpret_cast<const unsigned char*>(buf.get());
    Unique_PKCS8_PRIV_KEY_INFO pkcs8(d2i_PKCS8_PRIV_KEY_INFO(NULL, &tmp, buf.size()));
    if (pkcs8.get() == NULL) {
        ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE,
                                       "Error parsing private key from DER to PKCS8");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_use_PrivateKey => error from DER to PKCS8", ssl);
        return;
    }

    Unique_EVP_PKEY privatekeyevp(EVP_PKCS82PKEY(pkcs8.get()));
    if (privatekeyevp.get() == NULL) {
        ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE,
                                       "Error creating private key from PKCS8");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_use_PrivateKey => error from PKCS8 to key", ssl);
        return;
    }

    JNI_TRACE("ssl=%p NativeCrypto_SSL_use_PrivateKey EVP_PKEY_type=%d",
              ssl, EVP_PKEY_type(privatekeyevp.get()->type));
    int ret = SSL_use_PrivateKey(ssl, privatekeyevp.get());
    if (ret == 1) {
        OWNERSHIP_TRANSFERRED(privatekeyevp);
    } else {
        ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error setting private key");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_use_PrivateKey => error", ssl);
        return;
    }

    JNI_TRACE("ssl=%p NativeCrypto_SSL_use_PrivateKey => ok", ssl);
}

extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1use_1certificate(JNIEnv* env, jclass,
                                             jint ssl_address, jobjectArray certificates)
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

// RoboVM note: clang cannot handle the dynamic array allocated on the stack here. Use a fixed maximum size when compiler is clang.
#ifndef __clang__
    Unique_X509 certificatesX509[length];
#else
    #define MAX_CERTS_LENGTH 64
    if (length > MAX_CERTS_LENGTH) {
        jniThrowExceptionFmt(env, "java/lang/IllegalArgumentException", "certificates.length > %d", MAX_CERTS_LENGTH);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => certificates.length > %d", ssl, MAX_CERTS_LENGTH);
        return;
    }
    Unique_X509 certificatesX509[MAX_CERTS_LENGTH];
#endif
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
        certificatesX509[i].reset(d2i_X509(NULL, &tmp, buf.size()));

        if (certificatesX509[i].get() == NULL) {
            ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
            throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error parsing certificate");
            SSL_clear(ssl);
            JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => certificates parsing error", ssl);
            return;
        }
    }

    int ret = SSL_use_certificate(ssl, certificatesX509[0].get());
    if (ret == 1) {
        OWNERSHIP_TRANSFERRED(certificatesX509[0]);
    } else {
        ALOGE("%s", ERR_error_string(ERR_peek_error(), NULL));
        throwSSLExceptionWithSslErrors(env, ssl, SSL_ERROR_NONE, "Error setting certificate");
        SSL_clear(ssl);
        JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => SSL_use_certificate error", ssl);
        return;
    }

    Unique_sk_X509 chain(sk_X509_new_null());
    if (chain.get() == NULL) {
        jniThrowOutOfMemoryError(env, "Unable to allocate local certificate chain");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_use_certificate => chain allocation error", ssl);
        return;
    }
    for (int i = 1; i < length; i++) {
        if (!sk_X509_push(chain.get(), certificatesX509[i].release())) {
            jniThrowOutOfMemoryError(env, "Unable to push certificate");
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

extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1check_1private_1key(JNIEnv* env, jclass, jint ssl_address)
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

extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1set_1client_1CA_1list(JNIEnv* env, jclass,
                                                jint ssl_address, jobjectArray principals)
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
        jniThrowOutOfMemoryError(env, "Unable to allocate principal stack");
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
            jniThrowOutOfMemoryError(env, "Unable to push principal");
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
extern "C" jlong Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1get_1mode(JNIEnv* env, jclass, jint ssl_address) {
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
extern "C" jlong Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1set_1mode(JNIEnv* env, jclass,
        jint ssl_address, jlong mode) {
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
extern "C" jlong Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1clear_1mode(JNIEnv* env, jclass,
        jint ssl_address, jlong mode) {
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
extern "C" jlong Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1get_1options(JNIEnv* env, jclass,
        jint ssl_address) {
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
extern "C" jlong Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1set_1options(JNIEnv* env, jclass,
        jint ssl_address, jlong options) {
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
extern "C" jlong Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1clear_1options(JNIEnv* env, jclass,
        jint ssl_address, jlong options) {
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
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1set_1cipher_1lists(JNIEnv* env, jclass,
        jint ssl_address, jobjectArray cipherSuites)
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
                    jniThrowOutOfMemoryError(env, "Unable to push cipher");
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
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1set_1verify(JNIEnv* env,
        jclass, jint ssl_address, jint mode)
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
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1set_1session(JNIEnv* env, jclass,
        jint ssl_address, jint ssl_session_address)
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
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1set_1session_1creation_1enabled(JNIEnv* env, jclass,
        jint ssl_address, jboolean creation_enabled)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_set_session_creation_enabled creation_enabled=%d",
              ssl, creation_enabled);
    if (ssl == NULL) {
        return;
    }
    SSL_set_session_creation_enabled(ssl, creation_enabled);
}

extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1set_1tlsext_1host_1name(JNIEnv* env, jclass,
        jint ssl_address, jstring hostname)
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

extern "C" jstring Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1get_1servername(JNIEnv* env, jclass, jint ssl_address) {
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
 * Callback for the client to select a protocol.
 */
static int next_proto_select_callback(SSL* ssl, unsigned char **out, unsigned char *outlen,
        const unsigned char *in, unsigned int inlen, void *)
{
    JNI_TRACE("ssl=%p next_proto_select_callback", ssl);

    // Enable False Start on the client if the server understands NPN
    // http://www.imperialviolet.org/2012/04/11/falsestart.html
    SSL_set_mode(ssl, SSL_MODE_HANDSHAKE_CUTTHROUGH);

    AppData* appData = toAppData(ssl);
    unsigned char* npnProtocols = reinterpret_cast<unsigned char*>(appData->npnProtocolsData);
    size_t npnProtocolsLength = appData->npnProtocolsLength;

    int status = SSL_select_next_proto(out, outlen, in, inlen, npnProtocols, npnProtocolsLength);
    switch (status) {
      case OPENSSL_NPN_NEGOTIATED:
        JNI_TRACE("ssl=%p next_proto_select_callback NPN negotiated", ssl);
        break;
      case OPENSSL_NPN_UNSUPPORTED:
        JNI_TRACE("ssl=%p next_proto_select_callback NPN unsupported", ssl);
        break;
      case OPENSSL_NPN_NO_OVERLAP:
        JNI_TRACE("ssl=%p next_proto_select_callback NPN no overlap", ssl);
        break;
    }
    return SSL_TLSEXT_ERR_OK;
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
    }
    return SSL_TLSEXT_ERR_OK;
}

extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1CTX_1enable_1npn(JNIEnv* env, jclass, jint ssl_ctx_address)
{
    SSL_CTX* ssl_ctx = to_SSL_CTX(env, ssl_ctx_address, true);
    if (ssl_ctx == NULL) {
        return;
    }
    SSL_CTX_set_next_proto_select_cb(ssl_ctx, next_proto_select_callback, NULL); // client
    SSL_CTX_set_next_protos_advertised_cb(ssl_ctx, next_protos_advertised_callback, NULL); // server
}

extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1CTX_1disable_1npn(JNIEnv* env, jclass, jint ssl_ctx_address)
{
    SSL_CTX* ssl_ctx = to_SSL_CTX(env, ssl_ctx_address, true);
    if (ssl_ctx == NULL) {
        return;
    }
    SSL_CTX_set_next_proto_select_cb(ssl_ctx, NULL, NULL); // client
    SSL_CTX_set_next_protos_advertised_cb(ssl_ctx, NULL, NULL); // server
}

extern "C" jbyteArray Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1get_1npn_1negotiated_1protocol(JNIEnv* env, jclass,
        jint ssl_address)
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

/**
 * Perform SSL handshake
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1do_1handshake(JNIEnv* env, jclass, jint ssl_address,
        jobject fdObject, jobject shc, jint timeout_millis, jboolean client_mode,
        jbyteArray npnProtocols)
{
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
    }

    ret = 0;
    while (appData->aliveAndKicking) {
        errno = 0;

        if (!appData->setCallbackState(env, shc, fdObject, npnProtocols)) {
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
    return (jint) ssl_session;
}

/**
 * Perform SSL renegotiation
 */
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1renegotiate(JNIEnv* env, jclass, jint ssl_address)
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
extern "C" jobjectArray Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1get_1certificate(JNIEnv* env, jclass, jint ssl_address)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_certificate", ssl);
    if (ssl == NULL) {
        return NULL;
    }
    X509* certificate = SSL_get_certificate(ssl);
    if (certificate == NULL) {
        JNI_TRACE("ssl=%p NativeCrypto_SSL_get_certificate => NULL", ssl);
        return NULL;
    }

    Unique_sk_X509 chain(sk_X509_new_null());
    if (chain.get() == NULL) {
        jniThrowOutOfMemoryError(env, "Unable to allocate local certificate chain");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_get_certificate => threw exception", ssl);
        return NULL;
    }
    if (!sk_X509_push(chain.get(), certificate)) {
        jniThrowOutOfMemoryError(env, "Unable to push local certificate");
        JNI_TRACE("ssl=%p NativeCrypto_SSL_get_certificate => NULL", ssl);
        return NULL;
    }
    STACK_OF(X509)* cert_chain = SSL_get_certificate_chain(ssl, certificate);
    for (int i=0; i<sk_X509_num(cert_chain); i++) {
        if (!sk_X509_push(chain.get(), sk_X509_value(cert_chain, i))) {
            jniThrowOutOfMemoryError(env, "Unable to push local certificate chain");
            JNI_TRACE("ssl=%p NativeCrypto_SSL_get_certificate => NULL", ssl);
            return NULL;
        }
    }

    jobjectArray objectArray = getCertificateBytes(env, chain.get());
    JNI_TRACE("ssl=%p NativeCrypto_SSL_get_certificate => %p", ssl, objectArray);
    return objectArray;
}

// Fills a byte[][] with the peer certificates in the chain.
extern "C" jobjectArray Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1get_1peer_1cert_1chain(JNIEnv* env, jclass, jint ssl_address)
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
        chain_copy.reset(sk_X509_dup(chain));
        if (chain_copy.get() == NULL) {
            jniThrowOutOfMemoryError(env, "Unable to allocate peer certificate chain");
            JNI_TRACE("ssl=%p NativeCrypto_SSL_get_peer_cert_chain => certificate dup error", ssl);
            return NULL;
        }
        if (!sk_X509_push(chain_copy.get(), x509)) {
            jniThrowOutOfMemoryError(env, "Unable to push server's peer certificate");
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
                   int* sslReturnCode, int* sslErrorCode, int timeout_millis) {
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

        if (!appData->setCallbackState(env, shc, fdObject, NULL)) {
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
            JNI_TRACE("ssl=%p sslRead data: %d:\n%*s", ssl, n, n, buf+i);
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
                int selectResult = sslSelect(env, sslError, fdObject, appData, timeout_millis);
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
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1read(JNIEnv* env, jclass, jint ssl_address, jobject fdObject,
                                  jobject shc, jbyteArray b, jint offset, jint len,
                                  jint timeout_millis)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_read fd=%p shc=%p b=%p offset=%d len=%d timeout_millis=%d",
              ssl, fdObject, shc, b, offset, len, timeout_millis);
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
                      &returnCode, &sslErrorCode, timeout_millis);

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
                    int* sslReturnCode, int* sslErrorCode) {
    JNI_TRACE("ssl=%p sslWrite buf=%p len=%d", ssl, buf, len);

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

    while (appData->aliveAndKicking && len > 0) {
        errno = 0;

        if (MUTEX_LOCK(appData->mutex) == -1) {
            return -1;
        }

        unsigned int bytesMoved = BIO_number_read(bio) + BIO_number_written(bio);

        // ALOGD("Doing SSL_write() with %d bytes to go", len);
        if (!appData->setCallbackState(env, shc, fdObject, NULL)) {
            MUTEX_UNLOCK(appData->mutex);
            return THROWN_EXCEPTION;
        }
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
        JNI_TRACE("ssl=%p sslWrite SSL_write result=%d sslError=%d", ssl, result, sslError);
#ifdef WITH_JNI_TRACE_DATA
        for (int i = 0; i < result; i+= WITH_JNI_TRACE_DATA_CHUNK_SIZE) {
            int n = std::min(result - i, WITH_JNI_TRACE_DATA_CHUNK_SIZE);
            JNI_TRACE("ssl=%p sslWrite data: %d:\n%*s", ssl, n, n, buf+i);
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
                int selectResult = sslSelect(env, sslError, fdObject, appData, 0);
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
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1write(JNIEnv* env, jclass, jint ssl_address, jobject fdObject,
                                   jobject shc, jbyteArray b, jint offset, jint len)
{
    SSL* ssl = to_SSL(env, ssl_address, true);
    JNI_TRACE("ssl=%p NativeCrypto_SSL_write fd=%p shc=%p b=%p offset=%d len=%d",
              ssl, fdObject, shc, b, offset, len);
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
                       len, &returnCode, &sslErrorCode);

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
 * Interrupt any pending IO before closing the socket.
 */
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1interrupt(
        JNIEnv* env, jclass, jint ssl_address) {
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
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1shutdown(JNIEnv* env, jclass, jint ssl_address,
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
        if (!appData->setCallbackState(env, shc, fdObject, NULL)) {
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
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1free(JNIEnv* env, jclass, jint ssl_address)
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
extern "C" jbyteArray Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1SESSION_1session_1id(JNIEnv* env, jclass,
                                                      jint ssl_session_address) {
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
extern "C" jlong Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1SESSION_1get_1time(JNIEnv* env, jclass, jint ssl_session_address) {
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
extern "C" jstring Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1SESSION_1get_1version(JNIEnv* env, jclass, jint ssl_session_address) {
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
extern "C" jstring Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1SESSION_1cipher(JNIEnv* env, jclass, jint ssl_session_address) {
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
 * Gets and returns in a string the compression method negotiated for the SSL session.
 */
extern "C" jstring Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1SESSION_1compress_1meth(JNIEnv* env, jclass,
                                                      jint ssl_ctx_address,
                                                      jint ssl_session_address) {
    SSL_CTX* ssl_ctx = to_SSL_CTX(env, ssl_ctx_address, true);
    SSL_SESSION* ssl_session = to_SSL_SESSION(env, ssl_session_address, true);
    JNI_TRACE("ssl_session=%p NativeCrypto_SSL_SESSION_compress_meth ssl_ctx=%p",
              ssl_session, ssl_ctx);
    if (ssl_ctx == NULL || ssl_session == NULL) {
        return NULL;
    }

    int compress_meth = ssl_session->compress_meth;
    if (compress_meth == 0) {
        const char* name = "NULL";
        JNI_TRACE("ssl_session=%p NativeCrypto_SSL_SESSION_compress_meth => %s", ssl_session, name);
        return env->NewStringUTF(name);
    }

    int num_comp_methods = sk_SSL_COMP_num(ssl_ctx->comp_methods);
    for (int i = 0; i < num_comp_methods; i++) {
        SSL_COMP* comp = sk_SSL_COMP_value(ssl_ctx->comp_methods, i);
        if (comp->id != compress_meth) {
            continue;
        }
        const char* name = ((comp->method && comp->method->type == NID_zlib_compression)
                            ? SN_zlib_compression
                            : (comp->name ? comp->name : "UNKNOWN"));
        JNI_TRACE("ssl_session=%p NativeCrypto_SSL_SESSION_compress_meth => %s", ssl_session, name);
        return env->NewStringUTF(name);
    }
    throwSSLExceptionStr(env, "Unknown compression method");
    return NULL;
}

/**
 * Frees the SSL session.
 */
extern "C" void Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_SSL_1SESSION_1free(JNIEnv* env, jclass, jint ssl_session_address) {
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
extern "C" jbyteArray Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_i2d_1SSL_1SESSION(JNIEnv* env, jclass, jint ssl_session_address) {
    SSL_SESSION* ssl_session = to_SSL_SESSION(env, ssl_session_address, true);
    JNI_TRACE("ssl_session=%p NativeCrypto_i2d_SSL_SESSION", ssl_session);
    if (ssl_session == NULL) {
        return NULL;
    }

    // Compute the size of the DER data
    int size = i2d_SSL_SESSION(ssl_session, NULL);
    if (size == 0) {
        JNI_TRACE("ssl_session=%p NativeCrypto_i2d_SSL_SESSION => NULL", ssl_session);
        return NULL;
    }

    jbyteArray javaBytes = env->NewByteArray(size);
    if (javaBytes != NULL) {
        ScopedByteArrayRW bytes(env, javaBytes);
        if (bytes.get() == NULL) {
            JNI_TRACE("ssl_session=%p NativeCrypto_i2d_SSL_SESSION => threw exception",
                      ssl_session);
            return NULL;
        }
        unsigned char* ucp = reinterpret_cast<unsigned char*>(bytes.get());
        i2d_SSL_SESSION(ssl_session, &ucp);
    }

    JNI_TRACE("ssl_session=%p NativeCrypto_i2d_SSL_SESSION => size=%d", ssl_session, size);
    return javaBytes;
}

/**
 * Deserialize the session.
 */
extern "C" jint Java_org_apache_harmony_xnet_provider_jsse_NativeCrypto_d2i_1SSL_1SESSION(JNIEnv* env, jclass, jbyteArray javaBytes) {
    JNI_TRACE("NativeCrypto_d2i_SSL_SESSION bytes=%p", javaBytes);

    ScopedByteArrayRO bytes(env, javaBytes);
    if (bytes.get() == NULL) {
        JNI_TRACE("NativeCrypto_d2i_SSL_SESSION => threw exception");
        return 0;
    }
    const unsigned char* ucp = reinterpret_cast<const unsigned char*>(bytes.get());
    SSL_SESSION* ssl_session = d2i_SSL_SESSION(NULL, &ucp, bytes.size());

    JNI_TRACE("NativeCrypto_d2i_SSL_SESSION => %p", ssl_session);
    return static_cast<jint>(reinterpret_cast<uintptr_t>(ssl_session));
}

