#include <nullvm.h>
#include <string.h>

static Method* stringConstructor = NULL;
static InstanceField* stringValueField = NULL;
static InstanceField* stringOffsetField = NULL;
static InstanceField* stringCountField = NULL;


// TODO: Return the same instance for strings of length == 0?

/* 
 * Determines the number of Java chars needed to store the 
 * specified modified UTF-8 string.
 * Copied from Harmony (vm_strings.cpp).
 */
static jint getUnicodeLengthOfUtf8(char* utf8) {
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

static jint getUtf8LengthOfUnicode(jchar* unicode, jint unicodeLength) {
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
static void utf8ToUnicode(jchar* unicode, char* utf8String) {
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

static void unicodeToUtf8(char* utf8String, jchar* unicode, jint unicodeLength) {
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

static Object* newString(Env* env, CharArray* value, jint length) {
    jvalue args[3];
    args[0].i = 0;
    args[1].i = length;
    args[2].l = (jobject) value;
    return nvmNewObjectA(env, java_lang_String, stringConstructor, args);
}

jboolean nvmInitStrings(Env* env) {
    stringConstructor = nvmGetInstanceMethod(env, java_lang_String, "<init>", "(II[C)V");
    if (!stringConstructor) return FALSE;
    stringValueField = nvmGetInstanceField(env, java_lang_String, "value", "[C");
    if (!stringValueField) return FALSE;
    stringOffsetField = nvmGetInstanceField(env, java_lang_String, "offset", "I");
    if (!stringOffsetField) return FALSE;
    stringCountField = nvmGetInstanceField(env, java_lang_String, "count", "I");
    if (!stringCountField) return FALSE;
    return TRUE;
}

Object* nvmNewStringAscii(Env* env, char* s, jint length) {
    length = length = -1 ? strlen(s) : length;
    CharArray* value = nvmNewCharArray(env, length);
    if (!value) return NULL;
    jint i;
    for (i = 0; i < length; i++) {
        value->values[i] = (jchar) (s[i] & 0xff);
    }
    return newString(env, value, length);
}

Object* nvmNewStringUTF(Env* env, char* s, jint length) {
    length = length = -1 ? getUnicodeLengthOfUtf8(s) : length;
    CharArray* value = nvmNewCharArray(env, length);
    if (!value) return NULL;
    utf8ToUnicode(value->values, s);
    return newString(env, value, length);
}

Object* nvmNewString(Env* env, jchar* chars, jint length) {
    CharArray* value = nvmNewCharArray(env, length);
    if (!value) return NULL;
    memcpy(value->values, chars, sizeof(jchar) * length);
    return newString(env, value, length);
}

jint nvmGetStringLength(Env* env, Object* str) {
    return nvmGetIntInstanceFieldValue(env, str, stringCountField);
}

jchar* nvmGetStringChars(Env* env, Object* str) {
    CharArray* value = (CharArray*) nvmGetObjectInstanceFieldValue(env, str, stringValueField);
    jint offset = nvmGetIntInstanceFieldValue(env, str, stringOffsetField);
    jint count = nvmGetIntInstanceFieldValue(env, str, stringCountField);

    jchar* result = nvmAllocateMemory(env, sizeof(jchar) * count);
    if (!result) return NULL;

    memcpy(result, value->values + offset, sizeof(jchar) * count);
    return result;
}

jint nvmGetStringUTFLength(Env* env, Object* str) {
    CharArray* value = (CharArray*) nvmGetObjectInstanceFieldValue(env, str, stringValueField);
    jint offset = nvmGetIntInstanceFieldValue(env, str, stringOffsetField);
    jint count = nvmGetIntInstanceFieldValue(env, str, stringCountField);
    return getUtf8LengthOfUnicode(value->values + offset, count);
}

char* nvmGetStringUTFChars(Env* env, Object* str) {
    CharArray* value = (CharArray*) nvmGetObjectInstanceFieldValue(env, str, stringValueField);
    jint offset = nvmGetIntInstanceFieldValue(env, str, stringOffsetField);
    jint count = nvmGetIntInstanceFieldValue(env, str, stringCountField);

    jint length = getUtf8LengthOfUnicode(value->values + offset, count);

    char* result = nvmAllocateMemory(env, length + 1);
    if (!result) return NULL;

    unicodeToUtf8(result, value->values + offset, count);
    return result;
}

void nvmGetStringRegion(Env* env, Object* str, jint start, jint len, jchar* buf) {
    // TODO: Check bounds
    CharArray* value = (CharArray*) nvmGetObjectInstanceFieldValue(env, str, stringValueField);
    jint offset = nvmGetIntInstanceFieldValue(env, str, stringOffsetField);
    jint count = nvmGetIntInstanceFieldValue(env, str, stringCountField);
    memcpy(buf, value->values + offset + start, len);
}

void nvmGetStringUTFRegion(Env *env, Object* str, jint start, jint len, char* buf) {
    // TODO: Check bounds
    CharArray* value = (CharArray*) nvmGetObjectInstanceFieldValue(env, str, stringValueField);
    jint offset = nvmGetIntInstanceFieldValue(env, str, stringOffsetField);
    jint count = nvmGetIntInstanceFieldValue(env, str, stringCountField);
    unicodeToUtf8(buf, value->values + offset + start, len);
}

