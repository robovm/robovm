%module LibIMobileDevice
%{

#include <libimobiledevice/libimobiledevice.h>
#include <libimobiledevice/lockdown.h>
#include <libimobiledevice/afc.h>
#include <libimobiledevice/installation_proxy.h>

%}

%include "enums.swg"
%include "fast_primitive_arrays.i"
//%include "arrays_java.i"

%javaconst(1);
SWIG_JAVABODY_METHODS(protected, protected, SWIGTYPE)

%define OUT_CLASS(TYPE,NAME,CLEANUP...)
    %{
        typedef struct NAME {
            TYPE value;
        } NAME;
    %}
    typedef struct NAME {
    %immutable;
        TYPE value;
    } NAME;
    %extend NAME {
        NAME() {
          return (NAME *) calloc(1,sizeof(TYPE));
        }
        ~NAME() {
          CLEANUP;
          free(self);
        }
    };
    %types(TYPE);
%enddef

%define OUT_ARG(javatype, pattern)
    %typemap(jni) pattern "jlong"
    %typemap(jtype) pattern "long"
    %typemap(jstype) pattern "javatype"
    %typemap(javain) pattern "javatype.getCPtr($javainput)"
%enddef

%define ARRAY_CLASS(TYPE,NAME)
    %{
        typedef struct NAME {
            TYPE value;
        } NAME;
    %}
    typedef struct NAME {
        TYPE value;
    } NAME;
    %extend NAME {
        NAME(int nelements) {
          return (NAME *) calloc(nelements,sizeof(TYPE));
        }
        ~NAME() {
          free(self);
        }
        TYPE get(int index) {
          return self[index].value;
        }
        void set(int index, TYPE value) {
          self[index].value = value;
        }
    };
    %types(TYPE);
%enddef

%define CALLBACK(TYPE)
    %typemap(jni) TYPE "jlong"
    %typemap(jtype) TYPE "long"
    %typemap(jstype) TYPE "long"
    %typemap(javain) TYPE "$javainput"
%enddef
CALLBACK(instproxy_status_cb_t)
CALLBACK(idevice_event_cb_t)

// Map user_data passed to callbacks as int. We will pass in a
// callback id which is mapped to a cllback object instance in Java.
%typemap(jni) void *user_data "jint"
%typemap(jtype) void *user_data "int"
%typemap(jstype) void *user_data "int"
%typemap(javain) void *user_data "$javainput"

ARRAY_CLASS(char *, StringArray)
ARRAY_CLASS(jbyte, ByteArray)

OUT_CLASS(jint, IntOut)
OUT_CLASS(jlong, LongOut)
OUT_CLASS(char *, StringOut)
OUT_CLASS(StringArray *, StringArrayOut)
OUT_CLASS(ByteArray *, ByteArrayOut)
OUT_CLASS(idevice_t, IDeviceRefOut)
OUT_CLASS(idevice_connection_t, IDeviceConnectionRefOut)
OUT_CLASS(lockdownd_client_t, LockdowndClientRefOut)
OUT_CLASS(lockdownd_service_descriptor_t, LockdowndServiceDescriptorStructOut)
OUT_CLASS(afc_client_t, AfcClientRefOut)
OUT_CLASS(plist_t, PlistRefOut)
OUT_CLASS(instproxy_client_t, InstproxyClientRefOut)
OUT_ARG(IntOut, int *)
OUT_ARG(IntOut, uint32_t *)
OUT_ARG(LongOut, uint64_t *)
OUT_ARG(LongOut, int64_t *)
OUT_ARG(StringArrayOut, char ***devices)
OUT_ARG(StringArray, char **devices)
OUT_ARG(StringArrayOut, char ***classes)
OUT_ARG(StringArray, char **classes)
OUT_ARG(StringArrayOut, char ***infos)
OUT_ARG(StringArrayOut, char ***list)
OUT_ARG(StringArrayOut, char ***infolist)
OUT_ARG(StringOut, char **udid)
OUT_ARG(StringOut, char **device_name)
OUT_ARG(StringOut, char **type)
OUT_ARG(StringOut, char **session_id)
OUT_ARG(StringOut, char **value)
OUT_ARG(ByteArrayOut, char **plist_bin)
OUT_ARG(ByteArrayOut, char **plist_xml)
OUT_ARG(IDeviceRefOut, idevice_t *device)
OUT_ARG(IDeviceConnectionRefOut, idevice_connection_t *connection)
OUT_ARG(LockdowndClientRefOut, lockdownd_client_t *client)
OUT_ARG(LockdowndServiceDescriptorStructOut, lockdownd_service_descriptor_t *service)
OUT_ARG(PlistRefOut, plist_t *)
OUT_ARG(AfcClientRefOut, afc_client_t *client)
OUT_ARG(InstproxyClientRefOut, instproxy_client_t *client)

%apply signed char[] {char *data};
%apply signed char[] {char *plist_bin};
%apply signed char[] {char *plist_xml};
%apply jboolean {uint8_t ssl_enabled};

%rename (IDeviceEvent) idevice_event_t;
%rename (IDeviceEventType) idevice_event_type;
%rename (LockdowndPairRecordStruct) lockdownd_pair_record;
%rename (LockdowndServiceDescriptorStruct) lockdownd_service_descriptor;
%rename (AfcLockOperation) afc_lock_op_t;
%rename (AfcFileMode) afc_file_mode_t;
%rename (AfcLinkType) afc_link_type_t;
%rename (sslEnabled) ssl_enabled; 
%rename (connectionType) conn_type; 

typedef short int16_t;
typedef short uint16_t;
typedef int int32_t;
typedef int uint32_t;
typedef jlong int64_t;
typedef jlong uint64_t;

%ignore instproxy_client_options_add;

// Map just enough of the plist.h functions to be able to convert to/from Java plists.
extern plist_t plist_new_dict(void);
extern void plist_free(plist_t plist);
extern void plist_to_bin(plist_t plist, char **plist_bin, uint32_t * length);
extern void plist_to_xml(plist_t plist, char **plist_xml, uint32_t * length);
extern void plist_from_bin(const char *plist_bin, uint32_t length, plist_t * plist);

extern void delete_StringOut_value(StringOut* s);
extern void delete_ByteArrayOut_value(ByteArrayOut* s);
extern void delete_StringArray_values(StringArray* s, int length);
extern void delete_StringArray_values_z(StringArray* s);
extern jlong get_global_instproxy_status_cb(void);
extern jlong get_global_idevice_event_cb(void);
%{
static JavaVM *vm = NULL;
static jclass class_Callbacks = NULL;
static jmethodID meth_callInstproxyCallback = NULL;
static jmethodID meth_callIDeviceEventCallback = NULL;
jint JNI_OnLoad(JavaVM *_vm, void *reserved) {
    vm = _vm;
    return JNI_VERSION_1_2;
}

void delete_StringOut_value(StringOut* s) {
	if (s && s->value) free(s->value);
}
void delete_ByteArrayOut_value(ByteArrayOut* s) {
	if (s && s->value) free(s->value);
}
void delete_StringArray_values(StringArray* s, int length) {
	int i;
	if (s) {
		for (i = 0; i < length; i++) {
	    	if (s[i].value) free(s[i].value);
	    }
	}
}
void delete_StringArray_values_z(StringArray* s) {
    int i;
    if (s) {
        for (i = 0; s[i].value; i++) {
            free(s[i].value);
        }
    }
}
SWIGEXPORT void JNICALL Java_org_robovm_libimobiledevice_binding_LibIMobileDeviceJNI_initNative(JNIEnv *env, jclass cls) {
    class_Callbacks = (*env)->FindClass(env, "org/robovm/libimobiledevice/Callbacks");
    if ((*env)->ExceptionCheck(env)) return;
    meth_callInstproxyCallback = (*env)->GetStaticMethodID(env, class_Callbacks, "callInstproxyCallback", "(Ljava/lang/String;[BI)V");
    if ((*env)->ExceptionCheck(env)) return;
    meth_callIDeviceEventCallback = (*env)->GetStaticMethodID(env, class_Callbacks, "callIDeviceEventCallback", "(ILjava/lang/String;)V");
    if ((*env)->ExceptionCheck(env)) return;
}
static void global_instproxy_status_cb(const char *operation, plist_t status, void *user_data) {
    char *plist_bin = NULL;
    uint32_t length = 0;
    plist_to_bin(status, &plist_bin, &length);

    JNIEnv *env;
    if ((*vm)->AttachCurrentThreadAsDaemon(vm, (void**) &env, NULL) != JNI_OK) {
        fprintf(stderr, "Failed to attach callback thread\n");
        abort();
    }
    jstring jop = (*env)->NewStringUTF(env, operation);
    jbyteArray jstatus = (*env)->NewByteArray(env, length);
    (*env)->SetByteArrayRegion(env, jstatus, 0, length, (const jbyte *) plist_bin);
    free(plist_bin);
    jint jid = *((jint*) &user_data);
    (*env)->CallStaticVoidMethod(env, class_Callbacks, meth_callInstproxyCallback, jop, jstatus, jid);
}
jlong get_global_instproxy_status_cb(void) {
    return (jlong) global_instproxy_status_cb;
}
static void global_idevice_event_cb(const idevice_event_t *event, void *user_data) {
    JNIEnv *env;
    if ((*vm)->AttachCurrentThreadAsDaemon(vm, (void**) &env, NULL) != JNI_OK) {
        fprintf(stderr, "Failed to attach callback thread\n");
        abort();
    }
    jint jeventType = event->event;
    jstring judid = (*env)->NewStringUTF(env, event->udid);
    (*env)->CallStaticVoidMethod(env, class_Callbacks, meth_callIDeviceEventCallback, jeventType, judid);
}
jlong get_global_idevice_event_cb(void) {
    return (jlong) global_idevice_event_cb;
}
%}

%include "libimobiledevice/libimobiledevice.h"
%include "libimobiledevice/lockdown.h"
%include "libimobiledevice/afc.h"
%include "libimobiledevice/installation_proxy.h"

%pragma(java) jniclasscode=%{
  private static native void initNative();
  static {
    org.robovm.libimobiledevice.NativeLibrary.load();
    initNative();
  }
%}

%pragma(java) modulecode=%{
  public static PlistRef toPlistRef(long ptr) {
    return new PlistRef(ptr, false);
  }
%}
