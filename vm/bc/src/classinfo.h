#ifndef CLASSINFO_H
#define CLASSINFO_H

#include <nullvm.h>

#define CI_FLAGS_BITS 10
#define CI_INTERFACE_COUNT_BITS 6
#define CI_INTERFACE_COUNT_MASK ((1 << CI_INTERFACE_COUNT_BITS) - 1)
#define CI_FIELD_COUNT_BITS 8
#define CI_FIELD_COUNT_MASK ((1 << CI_FIELD_COUNT_BITS) - 1)
#define CI_METHOD_COUNT_BITS 8
#define CI_METHOD_COUNT_MASK ((1 << CI_METHOD_COUNT_BITS) - 1)
#define CI_PUBLIC 0x1
#define CI_FINAL 0x2
#define CI_INTERFACE 0x4
#define CI_ABSTRACT 0x8
#define CI_SYNTHETIC 0x10
#define CI_ANNOTATION 0x20
#define CI_ENUM 0x40
#define CI_ATTRIBUTES 0x80
#define CI_ERROR 0x100
#define CI_INITIALIZED 0x200

#define CI_ERROR_TYPE_NONE 0x0
#define CI_ERROR_TYPE_NO_CLASS_DEF_FOUND 0x1
#define CI_ERROR_TYPE_ILLEGAL_ACCESS 0x2
#define CI_ERROR_TYPE_INCOMPATIBLE_CLASS_CHANGE 0x3

typedef struct {
    Class* clazz;
    jint flags;
    const char* className;
    void* initializer;
    jint classDataSize;
    jint instanceDataSize;
} ClassInfoHeader;

typedef struct {
    Class* clazz;
    jint flags;
    const char* className;
    jint errorType;
    const char* errorMessage;
} ClassInfoError;

typedef struct {
    jboolean (*classCallback)(Env*, const char*, const char*, jint, jint, jint, void*, void*, void*);
    jboolean (*interfaceCallback)(Env*, const char*, void*);
    jboolean (*fieldCallback)(Env*, const char*, const char*, jint, jint, void*, void*);
    jboolean (*methodCallback)(Env*, const char*, const char*, jint, jint, void*, void*, void*, void*);
} ParseClassInfoCallbacks;

void parseClassInfo(Env* env, ClassInfoHeader* header, ParseClassInfoCallbacks* callbacks, void* data);

#endif

