/*
 * Copyright (C) 2012 Trillian AB
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
#include <robovm.h>
#include <string.h>
#include "uthash.h"

#define LOG_TAG "core.string"

static Method* stringConstructor = NULL;
static InstanceField* stringValueField = NULL;
static InstanceField* stringOffsetField = NULL;
static InstanceField* stringCountField = NULL;

// TODO: Restrict the number of bytes stored in the cache instead of the number of String objects.
#define MAX_CACHE_SIZE 10000

// TODO: Protect this with lock
typedef struct CacheEntry {
    const char* key; // The string in modified UTF-8
    Object* string;  // The java.lang.String object.
    UT_hash_handle hh;
} CacheEntry;
static CacheEntry* internedStrings = NULL;


// TODO: Return the same instance for strings of length == 0?

/* 
 * Determines the number of Java chars needed to store the 
 * specified modified UTF-8 string.
 * Copied from Harmony (vm_strings.cpp).
 */
static jint getUnicodeLengthOfUtf8(const char* utf8) {
    jint len = 0;
    unsigned char ch;
    unsigned char ch2;
    unsigned char ch3;
    while ((ch = *utf8++)) {
        len++;
        if (ch & 0x80) { // 2 or 3 byte encoding
            if (! (ch & 0x40))
                return -1;
            ch2 = *utf8++;
            if (ch & 0x20) { // 3 byte encoding
                ch3 = *utf8++;
                if ((ch  & 0xf0) != 0xe0  ||  // check first byte high bits
                    (ch2 & 0xc0) != 0x80  ||  // check second byte high bits
                    (ch3 & 0xc0) != 0x80)     // check third byte high bits
                    return -1;
            } else {    // 2 byte encoding
                if ((ch2 & 0xc0) != 0x80)     // check second byte high bits
                    return -1;
            }
        } 
    }
    return len;
}

static jint getUtf8LengthOfUnicode(const jchar* unicode, jint unicodeLength) {
    jint length = 0;
    jint i;
    for (i = 0; i < unicodeLength; i++) {
        jchar ch = unicode[i];
        if (ch == 0) {
            length += 2;
        } else if (ch < 0x80) {
            length += 1;
        } else if (ch < 0x800) {
            length += 2;
        } else {
            length += 3;
        }
    }
    return length;
}

/* 
 * Converts a null terminated string of modified UTF-8 
 * characters into a string of UTF-16 Java chars.
 * Copied from Harmony (vm_strings.cpp).
 */
static void utf8ToUnicode(jchar* unicode, const char* utf8String) {
    const unsigned char* utf8 = (const unsigned char*) utf8String;
    jchar ch;
    while ((ch = (jchar) *utf8++)) {
        if (ch & 0x80) {
//            assert(ch & 0x40);
            if (ch & 0x20) {
                jchar x = ch;
                jchar y = (jchar) *utf8++;
                jchar z = (jchar) *utf8++;
                *unicode++ = (jchar) (((0x0f & x) << 12) + ((0x3f & y) << 6) + ((0x3f & z)));
            } else {
                jchar x = ch;
                jchar y = (jchar) *utf8++;
                *unicode++ = (jchar) (((0x1f & x) << 6) + (0x3f & y));
            }
        } else {
            *unicode++ = ch;
        }
    }
}

static void unicodeToUtf8(char* utf8String, const jchar* unicode, jint unicodeLength) {
    char *s = utf8String;
    jint i;
    for (i = 0; i < unicodeLength; i++) {
        jint ch = unicode[i];
        if (ch == 0) {
            *s++ = (char)0xc0;
            *s++ = (char)0x80;
        } else if (ch < 0x80) {
            *s++ = (char)ch;
        } else if(ch < 0x800) {
            unsigned b5_0 = ch & 0x3f;
            unsigned b10_6 = (ch >> 6) & 0x1f;
            *s++ = (char)(0xc0 | b10_6);
            *s++ = (char)(0x80 | b5_0);
        } else {
            unsigned b5_0 = ch & 0x3f;
            unsigned b11_6 = (ch >> 6) & 0x3f;
            unsigned b15_12 = (ch >> 12) & 0xf;
            *s++ = (char)(0xe0 | b15_12);
            *s++ = (char)(0x80 | b11_6);
            *s++ = (char)(0x80 | b5_0);
        }
    }
    *s = 0;
}

static inline Object* newString(Env* env, CharArray* value, jint offset, jint length) {
    jvalue args[3];
    args[0].i = offset;
    args[1].i = length;
    args[2].l = (jobject) value;
    return rvmNewObjectA(env, java_lang_String, stringConstructor, args);
}

jboolean rvmInitStrings(Env* env) {
    stringConstructor = rvmGetInstanceMethod(env, java_lang_String, "<init>", "(II[C)V");
    if (!stringConstructor) return FALSE;
    stringValueField = rvmGetInstanceField(env, java_lang_String, "value", "[C");
    if (!stringValueField) return FALSE;
    stringOffsetField = rvmGetInstanceField(env, java_lang_String, "offset", "I");
    if (!stringOffsetField) return FALSE;
    stringCountField = rvmGetInstanceField(env, java_lang_String, "count", "I");
    if (!stringCountField) return FALSE;
    return TRUE;
}

Object* rvmNewStringNoCopy(Env* env, CharArray* value, jint offset, jint length) {
    return newString(env, value, offset, length);
}

Object* rvmNewStringAscii(Env* env, const char* s, jint length) {
    length = (length == -1) ? strlen(s) : length;
    CharArray* value = rvmNewCharArray(env, length);
    if (!value) return NULL;
    jint i;
    for (i = 0; i < length; i++) {
        value->values[i] = (jchar) (s[i] & 0xff);
    }
    return newString(env, value, 0, length);
}

Object* rvmNewStringUTF(Env* env, const char* s, jint length) {
    if (!s) return NULL;
    length = (length == -1) ? getUnicodeLengthOfUtf8(s) : length;
    CharArray* value = rvmNewCharArray(env, length);
    if (!value) return NULL;
    utf8ToUnicode(value->values, s);
    return newString(env, value, 0, length);
}

Object* rvmNewString(Env* env, const jchar* chars, jint length) {
    if (!chars) return NULL;
    CharArray* value = rvmNewCharArray(env, length);
    if (!value) return NULL;
    memcpy(value->values, chars, sizeof(jchar) * length);
    return newString(env, value, 0, length);
}

Object* rvmNewInternedStringUTF(Env* env, const char* s, jint length) {
    if (!s) return NULL;
    // Check the cache first.
    CacheEntry* cacheEntry;
    HASH_FIND_STR(internedStrings, s, cacheEntry);
    if (cacheEntry) {
        // Touch the string
        HASH_DELETE(hh, internedStrings, cacheEntry);
        HASH_ADD_KEYPTR(hh, internedStrings, cacheEntry->key, strlen(cacheEntry->key), cacheEntry);
        return cacheEntry->string;
    }

    length = (length == -1) ? getUnicodeLengthOfUtf8(s) : length;
    CharArray* value = rvmNewCharArray(env, length);
    if (!value) return NULL;
    utf8ToUnicode(value->values, s);
    Object* string = newString(env, value, 0, length);
    if (!string) return NULL;

    cacheEntry = rvmAllocateMemory(env, sizeof(CacheEntry));
    if (!cacheEntry) return NULL;
    cacheEntry->key = s;
    cacheEntry->string = string;
    HASH_ADD_KEYPTR(hh, internedStrings, cacheEntry->key, strlen(cacheEntry->key), cacheEntry);
    
    // prune the cache to MAX_CACHE_SIZE
    if (HASH_COUNT(internedStrings) >= MAX_CACHE_SIZE) {
        CacheEntry* tmpEntry;
        HASH_ITER(hh, internedStrings, cacheEntry, tmpEntry) {
            // prune the first entry (loop is based on insertion order so this deletes the oldest item)
            HASH_DELETE(hh, internedStrings, cacheEntry);
            break;
        }
    } 

    return string;
}

Object* rvmInternString(Env* env, Object* str) {
    if (!str) return NULL;
    // Check the cache first.
    char* s = rvmGetStringUTFChars(env, str);
    if (!s) return NULL;
    CacheEntry* cacheEntry;
    HASH_FIND_STR(internedStrings, s, cacheEntry);
    if (cacheEntry) {
        // Touch the string
        HASH_DELETE(hh, internedStrings, cacheEntry);
        HASH_ADD_KEYPTR(hh, internedStrings, cacheEntry->key, strlen(cacheEntry->key), cacheEntry);
        return cacheEntry->string;
    }

    cacheEntry = rvmAllocateMemory(env, sizeof(CacheEntry));
    if (!cacheEntry) return NULL;
    cacheEntry->key = s;
    cacheEntry->string = str;
    HASH_ADD_KEYPTR(hh, internedStrings, cacheEntry->key, strlen(cacheEntry->key), cacheEntry);
    
    // prune the cache to MAX_CACHE_SIZE
    if (HASH_COUNT(internedStrings) >= MAX_CACHE_SIZE) {
        CacheEntry* tmpEntry;
        HASH_ITER(hh, internedStrings, cacheEntry, tmpEntry) {
            // prune the first entry (loop is based on insertion order so this deletes the oldest item)
            HASH_DELETE(hh, internedStrings, cacheEntry);
            break;
        }
    } 

    return str;
}

jint rvmGetStringLength(Env* env, Object* str) {
    return rvmGetIntInstanceFieldValue(env, str, stringCountField);
}

jchar* rvmGetStringChars(Env* env, Object* str) {
    CharArray* value = (CharArray*) rvmGetObjectInstanceFieldValue(env, str, stringValueField);
    jint offset = rvmGetIntInstanceFieldValue(env, str, stringOffsetField);
    jint count = rvmGetIntInstanceFieldValue(env, str, stringCountField);

    jchar* result = rvmAllocateMemoryAtomic(env, sizeof(jchar) * count);
    if (!result) return NULL;

    memcpy(result, value->values + offset, sizeof(jchar) * count);
    return result;
}

jint rvmGetStringUTFLength(Env* env, Object* str) {
    CharArray* value = (CharArray*) rvmGetObjectInstanceFieldValue(env, str, stringValueField);
    jint offset = rvmGetIntInstanceFieldValue(env, str, stringOffsetField);
    jint count = rvmGetIntInstanceFieldValue(env, str, stringCountField);
    return getUtf8LengthOfUnicode(value->values + offset, count);
}

char* rvmGetStringUTFChars(Env* env, Object* str) {
    CharArray* value = (CharArray*) rvmGetObjectInstanceFieldValue(env, str, stringValueField);
    jint offset = rvmGetIntInstanceFieldValue(env, str, stringOffsetField);
    jint count = rvmGetIntInstanceFieldValue(env, str, stringCountField);

    jint length = getUtf8LengthOfUnicode(value->values + offset, count);

    char* result = rvmAllocateMemoryAtomic(env, length + 1);
    if (!result) return NULL;

    unicodeToUtf8(result, value->values + offset, count);
    return result;
}

void rvmGetStringRegion(Env* env, Object* str, jint start, jint len, jchar* buf) {
    // TODO: Check bounds
    CharArray* value = (CharArray*) rvmGetObjectInstanceFieldValue(env, str, stringValueField);
    jint offset = rvmGetIntInstanceFieldValue(env, str, stringOffsetField);
    //jint count = rvmGetIntInstanceFieldValue(env, str, stringCountField);
    memcpy(buf, value->values + offset + start, len);
}

void rvmGetStringUTFRegion(Env *env, Object* str, jint start, jint len, char* buf) {
    // TODO: Check bounds
    CharArray* value = (CharArray*) rvmGetObjectInstanceFieldValue(env, str, stringValueField);
    jint offset = rvmGetIntInstanceFieldValue(env, str, stringOffsetField);
    //jint count = rvmGetIntInstanceFieldValue(env, str, stringCountField);
    unicodeToUtf8(buf, value->values + offset + start, len);
}

