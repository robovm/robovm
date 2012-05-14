/*
 * Copyright (C) 2008 The Android Open Source Project
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

#define LOG_TAG "ExpatParser"

#include "JNIHelp.h"
#include "JniConstants.h"
#include "JniException.h"
#include "LocalArray.h"
#include "ScopedLocalRef.h"
#include "ScopedPrimitiveArray.h"
#include "ScopedStringChars.h"
#include "ScopedUtfChars.h"
#include "UniquePtr.h"
#include "jni.h"
#include "cutils/log.h"
#include "cutils/jstring.h" // for strcpylen8to16

#include <string.h>
#include <expat.h>

#define BUCKET_COUNT 128

/**
 * Wrapper around an interned string.
 */
struct InternedString {
    InternedString() : interned(NULL), bytes(NULL) {
    }

    ~InternedString() {
        delete[] bytes;
    }

    /** The interned string itself. */
    jstring interned;

    /** UTF-8 equivalent of the interned string. */
    const char* bytes;

    /** Hash code of the interned string. */
    int hash;
};

/**
 * Keeps track of strings between start and end events.
 */
class StringStack {
public:
    StringStack() : array(new jstring[DEFAULT_CAPACITY]), capacity(DEFAULT_CAPACITY), size(0) {
    }

    ~StringStack() {
        delete[] array;
    }

    void push(JNIEnv* env, jstring s) {
        if (size == capacity) {
            int newCapacity = capacity * 2;
            jstring* newArray = new jstring[newCapacity];
            if (newArray == NULL) {
                jniThrowOutOfMemoryError(env, NULL);
                return;
            }
            memcpy(newArray, array, capacity * sizeof(jstring));

            array = newArray;
            capacity = newCapacity;
        }

        array[size++] = s;
    }

    jstring pop() {
        return (size == 0) ? NULL : array[--size];
    }

private:
    enum { DEFAULT_CAPACITY = 10 };

    jstring* array;
    int capacity;
    int size;
};

/**
 * Data passed to parser handler method by the parser.
 */
struct ParsingContext {
    ParsingContext(jobject object) : env(NULL), object(object), buffer(NULL), bufferSize(-1) {
        for (int i = 0; i < BUCKET_COUNT; i++) {
            internedStrings[i] = NULL;
        }
    }

    // Warning: 'env' must be valid on entry.
    ~ParsingContext() {
        freeBuffer();

        // Free interned string cache.
        for (int i = 0; i < BUCKET_COUNT; i++) {
            if (internedStrings[i]) {
                InternedString** bucket = internedStrings[i];
                InternedString* current;
                while ((current = *(bucket++)) != NULL) {
                    // Free the interned string reference.
                    env->DeleteGlobalRef(current->interned);

                    // Free the bucket.
                    delete current;
                }

                // Free the buckets.
                delete[] internedStrings[i];
            }
        }
    }

    jcharArray ensureCapacity(int length) {
        if (bufferSize < length) {
            // Free the existing char[].
            freeBuffer();

            // Allocate a new char[].
            jcharArray javaBuffer = env->NewCharArray(length);
            if (javaBuffer == NULL) return NULL;

            // Create a global reference.
            javaBuffer = (jcharArray) env->NewGlobalRef(javaBuffer);
            if (javaBuffer == NULL) return NULL;

            buffer = javaBuffer;
            bufferSize = length;
        }
        return buffer;
    }

private:
    void freeBuffer() {
        if (buffer != NULL) {
            env->DeleteGlobalRef(buffer);
            buffer = NULL;
            bufferSize = -1;
        }
    }

public:
    /**
     * The JNI environment for the current thread. This should only be used
     * to keep a reference to the env for use in Expat callbacks.
     */
    JNIEnv* env;

    /** The Java parser object. */
    jobject object;

    /** Buffer for text events. */
    jcharArray buffer;

private:
    /** The size of our buffer in jchars. */
    int bufferSize;

public:
    /** Current attributes. */
    const char** attributes;

    /** Number of attributes. */
    int attributeCount;

    /** True if namespace support is enabled. */
    bool processNamespaces;

    /** Keep track of names. */
    StringStack stringStack;

    /** Cache of interned strings. */
    InternedString** internedStrings[BUCKET_COUNT];
};

static ParsingContext* toParsingContext(void* data) {
    return reinterpret_cast<ParsingContext*>(data);
}

static ParsingContext* toParsingContext(XML_Parser parser) {
    return reinterpret_cast<ParsingContext*>(XML_GetUserData(parser));
}

static jmethodID commentMethod;
static jmethodID endCdataMethod;
static jmethodID endDtdMethod;
static jmethodID endElementMethod;
static jmethodID endNamespaceMethod;
static jmethodID handleExternalEntityMethod;
static jmethodID internMethod;
static jmethodID notationDeclMethod;
static jmethodID processingInstructionMethod;
static jmethodID startCdataMethod;
static jmethodID startDtdMethod;
static jmethodID startElementMethod;
static jmethodID startNamespaceMethod;
static jmethodID textMethod;
static jmethodID unparsedEntityDeclMethod;
static jstring emptyString;

/**
 * Calculates a hash code for a null-terminated string. This is *not* equivalent
 * to Java's String.hashCode(). This hashes the bytes while String.hashCode()
 * hashes UTF-16 chars.
 *
 * @param s null-terminated string to hash
 * @returns hash code
 */
static int hashString(const char* s) {
    int hash = 0;
    if (s) {
        while (*s) {
            hash = hash * 31 + *s++;
        }
    }
    return hash;
}

/**
 * Creates a new interned string wrapper. Looks up the interned string
 * representing the given UTF-8 bytes.
 *
 * @param bytes null-terminated string to intern
 * @param hash of bytes
 * @returns wrapper of interned Java string
 */
static InternedString* newInternedString(JNIEnv* env, const char* bytes, int hash) {
    // Allocate a new wrapper.
    UniquePtr<InternedString> wrapper(new InternedString);
    if (wrapper.get() == NULL) {
        jniThrowOutOfMemoryError(env, NULL);
        return NULL;
    }

    // Create a copy of the UTF-8 bytes.
    // TODO: sometimes we already know the length. Reuse it if so.
    char* copy = new char[strlen(bytes) + 1];
    if (copy == NULL) {
        jniThrowOutOfMemoryError(env, NULL);
        return NULL;
    }
    strcpy(copy, bytes);
    wrapper->bytes = copy;

    // Save the hash.
    wrapper->hash = hash;

    // To intern a string, we must first create a new string and then call
    // intern() on it. We then keep a global reference to the interned string.
    ScopedLocalRef<jstring> newString(env, env->NewStringUTF(bytes));
    if (env->ExceptionCheck()) {
        return NULL;
    }

    // Call intern().
    ScopedLocalRef<jstring> interned(env,
            reinterpret_cast<jstring>(env->CallObjectMethod(newString.get(), internMethod)));
    if (env->ExceptionCheck()) {
        return NULL;
    }

    // Create a global reference to the interned string.
    wrapper->interned = (jstring) env->NewGlobalRef(interned.get());
    if (env->ExceptionCheck()) {
        return NULL;
    }

    return wrapper.release();
}

/**
 * Allocates a new bucket with one entry.
 *
 * @param entry to store in the bucket
 * @returns a reference to the bucket
 */
static InternedString** newInternedStringBucket(InternedString* entry) {
    InternedString** bucket = new InternedString*[2];
    if (bucket != NULL) {
        bucket[0] = entry;
        bucket[1] = NULL;
    }
    return bucket;
}

/**
 * Expands an interned string bucket and adds the given entry. Frees the
 * provided bucket and returns a new one.
 *
 * @param existingBucket the bucket to replace
 * @param entry to add to the bucket
 * @returns a reference to the newly-allocated bucket containing the given entry
 */
static InternedString** expandInternedStringBucket(
        InternedString** existingBucket, InternedString* entry) {
    // Determine the size of the existing bucket.
    int size = 0;
    while (existingBucket[size]) size++;

    // Allocate the new bucket with enough space for one more entry and
    // a null terminator.
    InternedString** newBucket = new InternedString*[size + 2];
    if (newBucket == NULL) return NULL;

    memcpy(newBucket, existingBucket, size * sizeof(InternedString*));
    newBucket[size] = entry;
    newBucket[size + 1] = NULL;

    return newBucket;
}

/**
 * Returns an interned string for the given UTF-8 string.
 *
 * @param bucket to search for s
 * @param s null-terminated string to find
 * @param hash of s
 * @returns interned Java string equivalent of s or null if not found
 */
static jstring findInternedString(InternedString** bucket, const char* s, int hash) {
    InternedString* current;
    while ((current = *(bucket++)) != NULL) {
        if (current->hash != hash) continue;
        if (!strcmp(s, current->bytes)) return current->interned;
    }
    return NULL;
}

/**
 * Returns an interned string for the given UTF-8 string.
 *
 * @param s null-terminated string to intern
 * @returns interned Java string equivelent of s or NULL if s is null
 */
static jstring internString(JNIEnv* env, ParsingContext* parsingContext, const char* s) {
    if (s == NULL) return NULL;

    int hash = hashString(s);
    int bucketIndex = hash & (BUCKET_COUNT - 1);

    InternedString*** buckets = parsingContext->internedStrings;
    InternedString** bucket = buckets[bucketIndex];
    InternedString* internedString;

    if (bucket) {
        // We have a bucket already. Look for the given string.
        jstring found = findInternedString(bucket, s, hash);
        if (found) {
            // We found it!
            return found;
        }

        // We didn't find it. :(
        // Create a new entry.
        internedString = newInternedString(env, s, hash);
        if (internedString == NULL) return NULL;

        // Expand the bucket.
        bucket = expandInternedStringBucket(bucket, internedString);
        if (bucket == NULL) {
            jniThrowOutOfMemoryError(env, NULL);
            return NULL;
        }

        buckets[bucketIndex] = bucket;

        return internedString->interned;
    } else {
        // We don't even have a bucket yet. Create an entry.
        internedString = newInternedString(env, s, hash);
        if (internedString == NULL) return NULL;

        // Create a new bucket with one entry.
        bucket = newInternedStringBucket(internedString);
        if (bucket == NULL) {
            jniThrowOutOfMemoryError(env, NULL);
            return NULL;
        }

        buckets[bucketIndex] = bucket;

        return internedString->interned;
    }
}

static void jniThrowExpatException(JNIEnv* env, XML_Error error) {
    const char* message = XML_ErrorString(error);
    jniThrowException(env, "org/apache/harmony/xml/ExpatException", message);
}

/**
 * Copies UTF-8 characters into the buffer. Returns the number of Java chars
 * which were buffered.
 *
 * @param characters to copy into the buffer
 * @param length of characters to copy (in bytes)
 * @returns number of UTF-16 characters which were copied
 */
static size_t fillBuffer(ParsingContext* parsingContext, const char* characters, int length) {
    JNIEnv* env = parsingContext->env;

    // Grow buffer if necessary.
    jcharArray buffer = parsingContext->ensureCapacity(length);
    if (buffer == NULL) return -1;

    // Decode UTF-8 characters into our buffer.
    ScopedCharArrayRW nativeBuffer(env, buffer);
    if (nativeBuffer.get() == NULL) {
        return -1;
    }

    size_t utf16length;
    strcpylen8to16(nativeBuffer.get(), characters, length, &utf16length);
    return utf16length;
}

/**
 * Buffers the given text and passes it to the given method.
 *
 * @param method to pass the characters and length to with signature
 *  (char[], int)
 * @param data parsing context
 * @param text to copy into the buffer
 * @param length of text to copy (in bytes)
 */
static void bufferAndInvoke(jmethodID method, void* data, const char* text, size_t length) {
    ParsingContext* parsingContext = toParsingContext(data);
    JNIEnv* env = parsingContext->env;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) return;

    // Buffer the element name.
    size_t utf16length = fillBuffer(parsingContext, text, length);

    // Invoke given method.
    jobject javaParser = parsingContext->object;
    jcharArray buffer = parsingContext->buffer;
    env->CallVoidMethod(javaParser, method, buffer, utf16length);
}

static const char** toAttributes(jint attributePointer) {
    return reinterpret_cast<const char**>(static_cast<uintptr_t>(attributePointer));
}

/**
 * The component parts of an attribute or element name.
 */
class ExpatElementName {
public:
    ExpatElementName(JNIEnv* env, ParsingContext* parsingContext, jint attributePointer, jint index) {
        const char** attributes = toAttributes(attributePointer);
        const char* name = attributes[index * 2];
        init(env, parsingContext, name);
    }

    ExpatElementName(JNIEnv* env, ParsingContext* parsingContext, const char* s) {
        init(env, parsingContext, s);
    }

    ~ExpatElementName() {
        free(mCopy);
    }

    /**
     * Returns the namespace URI, like "http://www.w3.org/1999/xhtml".
     * Possibly empty.
     */
    jstring uri() {
        return internString(mEnv, mParsingContext, mUri);
    }

    /**
     * Returns the element or attribute local name, like "h1". Never empty. When
     * namespace processing is disabled, this may contain a prefix, yielding a
     * local name like "html:h1". In such cases, the qName will always be empty.
     */
    jstring localName() {
        return internString(mEnv, mParsingContext, mLocalName);
    }

    /**
     * Returns the namespace prefix, like "html". Possibly empty.
     */
    jstring qName() {
        if (*mPrefix == 0) {
            return localName();
        }

        // return prefix + ":" + localName
        LocalArray<1024> qName(strlen(mPrefix) + 1 + strlen(mLocalName) + 1);
        snprintf(&qName[0], qName.size(), "%s:%s", mPrefix, mLocalName);
        return internString(mEnv, mParsingContext, &qName[0]);
    }

    /**
     * Returns true if this expat name has the same URI and local name.
     */
    bool matches(const char* uri, const char* localName) {
        return strcmp(uri, mUri) == 0 && strcmp(localName, mLocalName) == 0;
    }

    /**
     * Returns true if this expat name has the same qualified name.
     */
    bool matchesQName(const char* qName) {
        const char* lastColon = strrchr(qName, ':');

        // Compare local names only if either:
        //  - the input qualified name doesn't have a colon (like "h1")
        //  - this element doesn't have a prefix. Such is the case when it
        //    doesn't belong to a namespace, or when this parser's namespace
        //    processing is disabled. In the latter case, this element's local
        //    name may still contain a colon (like "html:h1").
        if (lastColon == NULL || *mPrefix == 0) {
            return strcmp(qName, mLocalName) == 0;
        }

        // Otherwise compare both prefix and local name
        size_t prefixLength = lastColon - qName;
        return strlen(mPrefix) == prefixLength
            && strncmp(qName, mPrefix, prefixLength) == 0
            && strcmp(lastColon + 1, mLocalName) == 0;
    }

private:
    JNIEnv* mEnv;
    ParsingContext* mParsingContext;
    char* mCopy;
    const char* mUri;
    const char* mLocalName;
    const char* mPrefix;

    /**
     * Decodes an Expat-encoded name of one of these three forms:
     *     "uri|localName|prefix" (example: "http://www.w3.org/1999/xhtml|h1|html")
     *     "uri|localName" (example: "http://www.w3.org/1999/xhtml|h1")
     *     "localName" (example: "h1")
     */
    void init(JNIEnv* env, ParsingContext* parsingContext, const char* s) {
        mEnv = env;
        mParsingContext = parsingContext;
        mCopy = strdup(s);

        // split the input into up to 3 parts: a|b|c
        char* context = NULL;
        char* a = strtok_r(mCopy, "|", &context);
        char* b = strtok_r(NULL, "|", &context);
        char* c = strtok_r(NULL, "|", &context);

        if (c != NULL) { // input of the form "uri|localName|prefix"
            mUri = a;
            mLocalName = b;
            mPrefix = c;
        } else if (b != NULL) { // input of the form "uri|localName"
            mUri = a;
            mLocalName = b;
            mPrefix = "";
        } else { // input of the form "localName"
            mLocalName = a;
            mUri = "";
            mPrefix = "";
        }
    }

    // Disallow copy and assignment.
    ExpatElementName(const ExpatElementName&);
    void operator=(const ExpatElementName&);
};

/**
 * Called by Expat at the start of an element. Delegates to the same method
 * on the Java parser.
 *
 * @param data parsing context
 * @param elementName "uri|localName" or "localName" for the current element
 * @param attributes alternating attribute names and values. Like element
 * names, attribute names follow the format "uri|localName" or "localName".
 */
static void startElement(void* data, const char* elementName, const char** attributes) {
    ParsingContext* parsingContext = toParsingContext(data);
    JNIEnv* env = parsingContext->env;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) return;

    // Count the number of attributes.
    int count = 0;
    while (attributes[count * 2]) count++;

    // Make the attributes available for the duration of this call.
    parsingContext->attributes = attributes;
    parsingContext->attributeCount = count;

    jobject javaParser = parsingContext->object;

    ExpatElementName e(env, parsingContext, elementName);
    jstring uri = parsingContext->processNamespaces ? e.uri() : emptyString;
    jstring localName = parsingContext->processNamespaces ? e.localName() : emptyString;
    jstring qName = e.qName();

    parsingContext->stringStack.push(env, qName);
    parsingContext->stringStack.push(env, uri);
    parsingContext->stringStack.push(env, localName);

    env->CallVoidMethod(javaParser, startElementMethod, uri, localName, qName, attributes, count);

    parsingContext->attributes = NULL;
    parsingContext->attributeCount = -1;
}

/**
 * Called by Expat at the end of an element. Delegates to the same method
 * on the Java parser.
 *
 * @param data parsing context
 * @param elementName "uri|localName" or "localName" for the current element;
 *         we assume that this matches the last data on our stack.
 */
static void endElement(void* data, const char* /*elementName*/) {
    ParsingContext* parsingContext = toParsingContext(data);
    JNIEnv* env = parsingContext->env;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) return;

    jobject javaParser = parsingContext->object;

    jstring localName = parsingContext->stringStack.pop();
    jstring uri = parsingContext->stringStack.pop();
    jstring qName = parsingContext->stringStack.pop();

    env->CallVoidMethod(javaParser, endElementMethod, uri, localName, qName);
}

/**
 * Called by Expat when it encounters text. Delegates to the same method
 * on the Java parser. This may be called mutiple times with incremental pieces
 * of the same contiguous block of text.
 *
 * @param data parsing context
 * @param characters buffer containing encountered text
 * @param length number of characters in the buffer
 */
static void text(void* data, const char* characters, int length) {
    bufferAndInvoke(textMethod, data, characters, length);
}

/**
 * Called by Expat when it encounters a comment. Delegates to the same method
 * on the Java parser.

 * @param data parsing context
 * @param comment 0-terminated
 */
static void comment(void* data, const char* comment) {
    bufferAndInvoke(commentMethod, data, comment, strlen(comment));
}

/**
 * Called by Expat at the beginning of a namespace mapping.
 *
 * @param data parsing context
 * @param prefix null-terminated namespace prefix used in the XML
 * @param uri of the namespace
 */
static void startNamespace(void* data, const char* prefix, const char* uri) {
    ParsingContext* parsingContext = toParsingContext(data);
    JNIEnv* env = parsingContext->env;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) return;

    jstring internedPrefix = emptyString;
    if (prefix != NULL) {
        internedPrefix = internString(env, parsingContext, prefix);
        if (env->ExceptionCheck()) return;
    }

    jstring internedUri = emptyString;
    if (uri != NULL) {
        internedUri = internString(env, parsingContext, uri);
        if (env->ExceptionCheck()) return;
    }

    parsingContext->stringStack.push(env, internedPrefix);

    jobject javaParser = parsingContext->object;
    env->CallVoidMethod(javaParser, startNamespaceMethod, internedPrefix, internedUri);
}

/**
 * Called by Expat at the end of a namespace mapping.
 *
 * @param data parsing context
 * @param prefix null-terminated namespace prefix used in the XML;
 *         we assume this is the same as the last prefix on the stack.
 */
static void endNamespace(void* data, const char* /*prefix*/) {
    ParsingContext* parsingContext = toParsingContext(data);
    JNIEnv* env = parsingContext->env;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) return;

    jstring internedPrefix = parsingContext->stringStack.pop();

    jobject javaParser = parsingContext->object;
    env->CallVoidMethod(javaParser, endNamespaceMethod, internedPrefix);
}

/**
 * Called by Expat at the beginning of a CDATA section.
 *
 * @param data parsing context
 */
static void startCdata(void* data) {
    ParsingContext* parsingContext = toParsingContext(data);
    JNIEnv* env = parsingContext->env;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) return;

    jobject javaParser = parsingContext->object;
    env->CallVoidMethod(javaParser, startCdataMethod);
}

/**
 * Called by Expat at the end of a CDATA section.
 *
 * @param data parsing context
 */
static void endCdata(void* data) {
    ParsingContext* parsingContext = toParsingContext(data);
    JNIEnv* env = parsingContext->env;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) return;

    jobject javaParser = parsingContext->object;
    env->CallVoidMethod(javaParser, endCdataMethod);
}

/**
 * Called by Expat at the beginning of a DOCTYPE section.
 * Expat gives us 'hasInternalSubset', but the Java API doesn't expect it, so we don't need it.
 */
static void startDtd(void* data, const char* name,
        const char* systemId, const char* publicId, int /*hasInternalSubset*/) {
    ParsingContext* parsingContext = toParsingContext(data);
    JNIEnv* env = parsingContext->env;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) return;

    jstring javaName = internString(env, parsingContext, name);
    if (env->ExceptionCheck()) return;

    jstring javaPublicId = internString(env, parsingContext, publicId);
    if (env->ExceptionCheck()) return;

    jstring javaSystemId = internString(env, parsingContext, systemId);
    if (env->ExceptionCheck()) return;

    jobject javaParser = parsingContext->object;
    env->CallVoidMethod(javaParser, startDtdMethod, javaName, javaPublicId,
        javaSystemId);
}

/**
 * Called by Expat at the end of a DOCTYPE section.
 *
 * @param data parsing context
 */
static void endDtd(void* data) {
    ParsingContext* parsingContext = toParsingContext(data);
    JNIEnv* env = parsingContext->env;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) return;

    jobject javaParser = parsingContext->object;
    env->CallVoidMethod(javaParser, endDtdMethod);
}

/**
 * Called by Expat when it encounters processing instructions.
 *
 * @param data parsing context
 * @param target of the instruction
 * @param instructionData
 */
static void processingInstruction(void* data, const char* target, const char* instructionData) {
    ParsingContext* parsingContext = toParsingContext(data);
    JNIEnv* env = parsingContext->env;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) return;

    jstring javaTarget = internString(env, parsingContext, target);
    if (env->ExceptionCheck()) return;

    ScopedLocalRef<jstring> javaInstructionData(env, env->NewStringUTF(instructionData));
    if (env->ExceptionCheck()) return;

    jobject javaParser = parsingContext->object;
    env->CallVoidMethod(javaParser, processingInstructionMethod, javaTarget, javaInstructionData.get());
}

/**
 * Creates a new entity parser.
 *
 * @param object the Java ExpatParser instance
 * @param parentParser pointer
 * @param javaEncoding the character encoding name
 * @param javaContext that was provided to handleExternalEntity
 * @returns the pointer to the C Expat entity parser
 */
extern "C" jint Java_org_apache_harmony_xml_ExpatParser_createEntityParser(JNIEnv* env, jobject, jint parentParser, jstring javaContext) {
    ScopedUtfChars context(env, javaContext);
    if (context.c_str() == NULL) {
        return 0;
    }

    XML_Parser parent = (XML_Parser) parentParser;
    XML_Parser entityParser = XML_ExternalEntityParserCreate(parent, context.c_str(), NULL);
    if (entityParser == NULL) {
        jniThrowOutOfMemoryError(env, NULL);
    }

    return (jint) entityParser;
}

/**
 * Handles external entities. We ignore the "base" URI and keep track of it
 * ourselves.
 */
static int handleExternalEntity(XML_Parser parser, const char* context,
        const char*, const char* systemId, const char* publicId) {
    ParsingContext* parsingContext = toParsingContext(parser);
    jobject javaParser = parsingContext->object;
    JNIEnv* env = parsingContext->env;
    jobject object = parsingContext->object;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) {
        return XML_STATUS_ERROR;
    }

    ScopedLocalRef<jstring> javaSystemId(env, env->NewStringUTF(systemId));
    if (env->ExceptionCheck()) {
        return XML_STATUS_ERROR;
    }
    ScopedLocalRef<jstring> javaPublicId(env, env->NewStringUTF(publicId));
    if (env->ExceptionCheck()) {
        return XML_STATUS_ERROR;
    }
    ScopedLocalRef<jstring> javaContext(env, env->NewStringUTF(context));
    if (env->ExceptionCheck()) {
        return XML_STATUS_ERROR;
    }

    // Pass the wrapped parser and both strings to java.
    env->CallVoidMethod(javaParser, handleExternalEntityMethod, javaContext.get(),
            javaPublicId.get(), javaSystemId.get());

    /*
     * Parsing the external entity leaves parsingContext->env and object set to
     * NULL, so we need to restore both.
     *
     * TODO: consider restoring the original env and object instead of setting
     * them to NULL in the append() functions.
     */
    parsingContext->env = env;
    parsingContext->object = object;

    return env->ExceptionCheck() ? XML_STATUS_ERROR : XML_STATUS_OK;
}

/**
 * Expat gives us 'base', but the Java API doesn't expect it, so we don't need it.
 */
static void unparsedEntityDecl(void* data, const char* name, const char* /*base*/, const char* systemId, const char* publicId, const char* notationName) {
    ParsingContext* parsingContext = toParsingContext(data);
    jobject javaParser = parsingContext->object;
    JNIEnv* env = parsingContext->env;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) return;

    ScopedLocalRef<jstring> javaName(env, env->NewStringUTF(name));
    if (env->ExceptionCheck()) return;
    ScopedLocalRef<jstring> javaPublicId(env, env->NewStringUTF(publicId));
    if (env->ExceptionCheck()) return;
    ScopedLocalRef<jstring> javaSystemId(env, env->NewStringUTF(systemId));
    if (env->ExceptionCheck()) return;
    ScopedLocalRef<jstring> javaNotationName(env, env->NewStringUTF(notationName));
    if (env->ExceptionCheck()) return;

    env->CallVoidMethod(javaParser, unparsedEntityDeclMethod, javaName.get(), javaPublicId.get(), javaSystemId.get(), javaNotationName.get());
}

/**
 * Expat gives us 'base', but the Java API doesn't expect it, so we don't need it.
 */
static void notationDecl(void* data, const char* name, const char* /*base*/, const char* systemId, const char* publicId) {
    ParsingContext* parsingContext = toParsingContext(data);
    jobject javaParser = parsingContext->object;
    JNIEnv* env = parsingContext->env;

    // Bail out if a previously called handler threw an exception.
    if (env->ExceptionCheck()) return;

    ScopedLocalRef<jstring> javaName(env, env->NewStringUTF(name));
    if (env->ExceptionCheck()) return;
    ScopedLocalRef<jstring> javaPublicId(env, env->NewStringUTF(publicId));
    if (env->ExceptionCheck()) return;
    ScopedLocalRef<jstring> javaSystemId(env, env->NewStringUTF(systemId));
    if (env->ExceptionCheck()) return;

    env->CallVoidMethod(javaParser, notationDeclMethod, javaName.get(), javaPublicId.get(), javaSystemId.get());
}

/**
 * Creates a new Expat parser. Called from the Java ExpatParser constructor.
 *
 * @param object the Java ExpatParser instance
 * @param javaEncoding the character encoding name
 * @param processNamespaces true if the parser should handle namespaces
 * @returns the pointer to the C Expat parser
 */
extern "C" jint Java_org_apache_harmony_xml_ExpatParser_initialize(JNIEnv* env, jobject object, jstring javaEncoding,
        jboolean processNamespaces) {
    // Allocate parsing context.
    UniquePtr<ParsingContext> context(new ParsingContext(object));
    if (context.get() == NULL) {
        jniThrowOutOfMemoryError(env, NULL);
        return 0;
    }

    context->processNamespaces = (bool) processNamespaces;

    // Create a parser.
    XML_Parser parser;
    ScopedUtfChars encoding(env, javaEncoding);
    if (encoding.c_str() == NULL) {
        return 0;
    }
    if (processNamespaces) {
        // Use '|' to separate URIs from local names.
        parser = XML_ParserCreateNS(encoding.c_str(), '|');
    } else {
        parser = XML_ParserCreate(encoding.c_str());
    }

    if (parser != NULL) {
        if (processNamespaces) {
            XML_SetNamespaceDeclHandler(parser, startNamespace, endNamespace);
            XML_SetReturnNSTriplet(parser, 1);
        }

        XML_SetCdataSectionHandler(parser, startCdata, endCdata);
        XML_SetCharacterDataHandler(parser, text);
        XML_SetCommentHandler(parser, comment);
        XML_SetDoctypeDeclHandler(parser, startDtd, endDtd);
        XML_SetElementHandler(parser, startElement, endElement);
        XML_SetExternalEntityRefHandler(parser, handleExternalEntity);
        XML_SetNotationDeclHandler(parser, notationDecl);
        XML_SetProcessingInstructionHandler(parser, processingInstruction);
        XML_SetUnparsedEntityDeclHandler(parser, unparsedEntityDecl);
        XML_SetUserData(parser, context.release());
    } else {
        jniThrowOutOfMemoryError(env, NULL);
        return 0;
    }

    return (jint) parser;
}

/**
 * Decodes the bytes as characters and parse the characters as XML. This
 * performs character decoding using the charset specified at XML_Parser
 * creation. For Java chars, that charset must be UTF-16 so that a Java char[]
 * can be reinterpreted as a UTF-16 encoded byte[]. appendBytes, appendChars
 * and appendString all call through this method.
 */
static void append(JNIEnv* env, jobject object, jint pointer,
        const char* bytes, size_t byteOffset, size_t byteCount, jboolean isFinal) {
    XML_Parser parser = (XML_Parser) pointer;
    ParsingContext* context = toParsingContext(parser);
    context->env = env;
    context->object = object;
    if (!XML_Parse(parser, bytes + byteOffset, byteCount, isFinal) && !env->ExceptionCheck()) {
        jniThrowExpatException(env, XML_GetErrorCode(parser));
    }
    context->object = NULL;
    context->env = NULL;
}

extern "C" void Java_org_apache_harmony_xml_ExpatParser_appendBytes(JNIEnv* env, jobject object, jint pointer,
        jbyteArray xml, jint byteOffset, jint byteCount) {
    ScopedByteArrayRO byteArray(env, xml);
    if (byteArray.get() == NULL) {
        return;
    }

    const char* bytes = reinterpret_cast<const char*>(byteArray.get());
    append(env, object, pointer, bytes, byteOffset, byteCount, XML_FALSE);
}

extern "C" void Java_org_apache_harmony_xml_ExpatParser_appendChars(JNIEnv* env, jobject object, jint pointer,
        jcharArray xml, jint charOffset, jint charCount) {
    ScopedCharArrayRO charArray(env, xml);
    if (charArray.get() == NULL) {
        return;
    }

    const char* bytes = reinterpret_cast<const char*>(charArray.get());
    size_t byteOffset = 2 * charOffset;
    size_t byteCount = 2 * charCount;
    append(env, object, pointer, bytes, byteOffset, byteCount, XML_FALSE);
}

extern "C" void Java_org_apache_harmony_xml_ExpatParser_appendString(JNIEnv* env, jobject object, jint pointer, jstring javaXml, jboolean isFinal) {
    ScopedStringChars xml(env, javaXml);
    if (xml.get() == NULL) {
        return;
    }
    const char* bytes = reinterpret_cast<const char*>(xml.get());
    size_t byteCount = 2 * xml.size();
    append(env, object, pointer, bytes, 0, byteCount, isFinal);
}

/**
 * Releases parser only.
 *
 * @param object the Java ExpatParser instance
 * @param i pointer to the C expat parser
 */
extern "C" void Java_org_apache_harmony_xml_ExpatParser_releaseParser(JNIEnv*, jobject, jint i) {
    XML_Parser parser = (XML_Parser) i;
    XML_ParserFree(parser);
}

/**
 * Cleans up after the parser. Called at garbage collection time.
 *
 * @param object the Java ExpatParser instance
 * @param i pointer to the C expat parser
 */
extern "C" void Java_org_apache_harmony_xml_ExpatParser_release(JNIEnv* env, jobject, jint i) {
    XML_Parser parser = (XML_Parser) i;

    ParsingContext* context = toParsingContext(parser);
    context->env = env;
    delete context;

    XML_ParserFree(parser);
}

/**
 * Gets the current line.
 *
 * @param object the Java ExpatParser instance
 * @param pointer to the C expat parser
 * @returns current line number
 */
extern "C" int Java_org_apache_harmony_xml_ExpatParser_line(JNIEnv*, jobject, jint pointer) {
    XML_Parser parser = (XML_Parser) pointer;
    return XML_GetCurrentLineNumber(parser);
}

/**
 * Gets the current column.
 *
 * @param object the Java ExpatParser instance
 * @param pointer to the C expat parser
 * @returns current column number
 */
extern "C" int Java_org_apache_harmony_xml_ExpatParser_column(JNIEnv*, jobject, jint pointer) {
    XML_Parser parser = (XML_Parser) pointer;
    return XML_GetCurrentColumnNumber(parser);
}

/**
 * Gets the URI of the attribute at the given index.
 *
 * @param object Java ExpatParser instance
 * @param pointer to the C expat parser
 * @param attributePointer to the attribute array
 * @param index of the attribute
 * @returns interned Java string containing attribute's URI
 */
extern "C" jstring Java_org_apache_harmony_xml_ExpatAttributes_getURI(JNIEnv* env, jobject, jint pointer,
        jint attributePointer, jint index) {
    XML_Parser parser = (XML_Parser) pointer;
    ParsingContext* context = toParsingContext(parser);
    return ExpatElementName(env, context, attributePointer, index).uri();
}

/**
 * Gets the local name of the attribute at the given index.
 *
 * @param object Java ExpatParser instance
 * @param pointer to the C expat parser
 * @param attributePointer to the attribute array
 * @param index of the attribute
 * @returns interned Java string containing attribute's local name
 */
extern "C" jstring Java_org_apache_harmony_xml_ExpatAttributes_getLocalName(JNIEnv* env, jobject, jint pointer,
        jint attributePointer, jint index) {
    XML_Parser parser = (XML_Parser) pointer;
    ParsingContext* context = toParsingContext(parser);
    return ExpatElementName(env, context, attributePointer, index).localName();
}

/**
 * Gets the qualified name of the attribute at the given index.
 *
 * @param object Java ExpatParser instance
 * @param pointer to the C expat parser
 * @param attributePointer to the attribute array
 * @param index of the attribute
 * @returns interned Java string containing attribute's local name
 */
extern "C" jstring Java_org_apache_harmony_xml_ExpatAttributes_getQName(JNIEnv* env, jobject, jint pointer,
        jint attributePointer, jint index) {
    XML_Parser parser = (XML_Parser) pointer;
    ParsingContext* context = toParsingContext(parser);
    return ExpatElementName(env, context, attributePointer, index).qName();
}

/**
 * Gets the value of the attribute at the given index.
 *
 * @param object Java ExpatParser instance
 * @param attributePointer to the attribute array
 * @param index of the attribute
 * @returns Java string containing attribute's value
 */
extern "C" jstring Java_org_apache_harmony_xml_ExpatAttributes_getValueByIndex(JNIEnv* env, jobject,
        jint attributePointer, jint index) {
    const char** attributes = toAttributes(attributePointer);
    const char* value = attributes[(index * 2) + 1];
    return env->NewStringUTF(value);
}

/**
 * Gets the index of the attribute with the given qualified name.
 *
 * @param attributePointer to the attribute array
 * @param qName to look for
 * @returns index of attribute with the given uri and local name or -1 if not
 *  found
 */
extern "C" jint Java_org_apache_harmony_xml_ExpatAttributes_getIndexForQName(JNIEnv* env, jobject,
        jint attributePointer, jstring qName) {
    ScopedUtfChars qNameBytes(env, qName);
    if (qNameBytes.c_str() == NULL) {
        return -1;
    }

    const char** attributes = toAttributes(attributePointer);
    int found = -1;
    for (int index = 0; attributes[index * 2]; ++index) {
        if (ExpatElementName(NULL, NULL, attributePointer, index).matchesQName(qNameBytes.c_str())) {
            found = index;
            break;
        }
    }

    return found;
}

/**
 * Gets the index of the attribute with the given URI and name.
 *
 * @param attributePointer to the attribute array
 * @param uri to look for
 * @param localName to look for
 * @returns index of attribute with the given uri and local name or -1 if not
 *  found
 */
extern "C" jint Java_org_apache_harmony_xml_ExpatAttributes_getIndex(JNIEnv* env, jobject, jint attributePointer,
        jstring uri, jstring localName) {
    ScopedUtfChars uriBytes(env, uri);
    if (uriBytes.c_str() == NULL) {
        return -1;
    }

    ScopedUtfChars localNameBytes(env, localName);
    if (localNameBytes.c_str() == NULL) {
        return -1;
    }

    const char** attributes = toAttributes(attributePointer);
    for (int index = 0; attributes[index * 2]; ++index) {
        if (ExpatElementName(NULL, NULL, attributePointer, index).matches(uriBytes.c_str(),
                localNameBytes.c_str())) {
            return index;
        }
    }
    return -1;
}

/**
 * Gets the value of the attribute with the given qualified name.
 *
 * @param attributePointer to the attribute array
 * @param uri to look for
 * @param localName to look for
 * @returns value of attribute with the given uri and local name or NULL if not
 *  found
 */
extern "C" jstring Java_org_apache_harmony_xml_ExpatAttributes_getValueForQName(JNIEnv* env, jobject clazz,
        jint attributePointer, jstring qName) {
    jint index = Java_org_apache_harmony_xml_ExpatAttributes_getIndexForQName(env, clazz, attributePointer, qName);
    return index == -1 ? NULL
            : Java_org_apache_harmony_xml_ExpatAttributes_getValueByIndex(env, clazz, attributePointer, index);
}

/**
 * Gets the value of the attribute with the given URI and name.
 *
 * @param attributePointer to the attribute array
 * @param uri to look for
 * @param localName to look for
 * @returns value of attribute with the given uri and local name or NULL if not
 *  found
 */
extern "C" jstring Java_org_apache_harmony_xml_ExpatAttributes_getValue(JNIEnv* env, jobject clazz,
        jint attributePointer, jstring uri, jstring localName) {
    jint index = Java_org_apache_harmony_xml_ExpatAttributes_getIndex(env, clazz, attributePointer, uri, localName);
    return index == -1 ? NULL
            : Java_org_apache_harmony_xml_ExpatAttributes_getValueByIndex(env, clazz, attributePointer, index);
}

/**
 * Clones an array of strings. Uses one contiguous block of memory so as to
 * maximize performance.
 *
 * @param address char** to clone
 * @param count number of attributes
 */
extern "C" jint Java_org_apache_harmony_xml_ExpatParser_cloneAttributes(JNIEnv* env, jobject, jint address, jint count) {
    const char** source = reinterpret_cast<const char**>(static_cast<uintptr_t>(address));
    count *= 2;

    // Figure out how big the buffer needs to be.
    int arraySize = (count + 1) * sizeof(char*);
    int totalSize = arraySize;
    int stringLengths[count];
    for (int i = 0; i < count; i++) {
        int length = strlen(source[i]);
        stringLengths[i] = length;
        totalSize += length + 1;
    }

    char* buffer = new char[totalSize];
    if (buffer == NULL) {
        jniThrowOutOfMemoryError(env, NULL);
        return 0;
    }

    // Array is at the beginning of the buffer.
    char** clonedArray = reinterpret_cast<char**>(buffer);
    clonedArray[count] = NULL; // null terminate

    // String data follows immediately after.
    char* destinationString = buffer + arraySize;
    for (int i = 0; i < count; i++) {
        const char* sourceString = source[i];
        int stringLength = stringLengths[i];
        memcpy(destinationString, sourceString, stringLength + 1);
        clonedArray[i] = destinationString;
        destinationString += stringLength + 1;
    }

    return static_cast<jint>(reinterpret_cast<uintptr_t>(buffer));
}

/**
 * Frees cloned attributes.
 */
extern "C" void Java_org_apache_harmony_xml_ExpatAttributes_freeAttributes(JNIEnv*, jobject, jint pointer) {
    delete[] reinterpret_cast<char*>(static_cast<uintptr_t>(pointer));
}

/**
 * Called when we initialize our Java parser class.
 *
 * @param clazz Java ExpatParser class
 */
extern "C" void Java_org_apache_harmony_xml_ExpatParser_staticInitialize(JNIEnv* env, jobject classObject, jstring empty) {
    jclass clazz = reinterpret_cast<jclass>(classObject);
    startElementMethod = env->GetMethodID(clazz, "startElement",
        "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;II)V");
    if (startElementMethod == NULL) return;

    endElementMethod = env->GetMethodID(clazz, "endElement",
        "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
    if (endElementMethod == NULL) return;

    textMethod = env->GetMethodID(clazz, "text", "([CI)V");
    if (textMethod == NULL) return;

    commentMethod = env->GetMethodID(clazz, "comment", "([CI)V");
    if (commentMethod == NULL) return;

    startCdataMethod = env->GetMethodID(clazz, "startCdata", "()V");
    if (startCdataMethod == NULL) return;

    endCdataMethod = env->GetMethodID(clazz, "endCdata", "()V");
    if (endCdataMethod == NULL) return;

    startDtdMethod = env->GetMethodID(clazz, "startDtd",
        "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
    if (startDtdMethod == NULL) return;

    endDtdMethod = env->GetMethodID(clazz, "endDtd", "()V");
    if (endDtdMethod == NULL) return;

    startNamespaceMethod = env->GetMethodID(clazz, "startNamespace",
        "(Ljava/lang/String;Ljava/lang/String;)V");
    if (startNamespaceMethod == NULL) return;

    endNamespaceMethod = env->GetMethodID(clazz, "endNamespace",
        "(Ljava/lang/String;)V");
    if (endNamespaceMethod == NULL) return;

    processingInstructionMethod = env->GetMethodID(clazz,
        "processingInstruction", "(Ljava/lang/String;Ljava/lang/String;)V");
    if (processingInstructionMethod == NULL) return;

    handleExternalEntityMethod = env->GetMethodID(clazz,
        "handleExternalEntity",
        "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
    if (handleExternalEntityMethod == NULL) return;

    notationDeclMethod = env->GetMethodID(clazz, "notationDecl",
            "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
    if (notationDeclMethod == NULL) return;

    unparsedEntityDeclMethod = env->GetMethodID(clazz, "unparsedEntityDecl",
            "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V");
    if (unparsedEntityDeclMethod == NULL) return;

    internMethod = env->GetMethodID(JniConstants::stringClass, "intern", "()Ljava/lang/String;");
    if (internMethod == NULL) return;

    // Reference to "".
    emptyString = (jstring) env->NewGlobalRef(empty);
}

