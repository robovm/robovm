/*
 * Copyright (C) 2010 The Android Open Source Project
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

#define LOG_TAG "libcore" // We'll be next to "dalvikvm" in the log; make the distinction clear.

#include "JniConstants.h"
#include "ScopedLocalFrame.h"

#include <stdlib.h>

extern int register_java_io_Console(JNIEnv* env);
extern int register_java_io_File(JNIEnv* env);
extern int register_java_io_ObjectStreamClass(JNIEnv* env);
extern int register_java_lang_Character(JNIEnv* env);
extern int register_java_lang_Math(JNIEnv* env);
extern int register_java_lang_ProcessManager(JNIEnv* env);
extern int register_java_lang_RealToString(JNIEnv* env);
extern int register_java_lang_StrictMath(JNIEnv* env);
extern int register_java_lang_StringToReal(JNIEnv* env);
extern int register_java_lang_System(JNIEnv* env);
extern int register_java_math_NativeBN(JNIEnv* env);
extern int register_java_nio_ByteOrder(JNIEnv* env);
extern int register_java_nio_charset_Charsets(JNIEnv* env);
extern int register_java_text_Bidi(JNIEnv* env);
extern int register_java_util_regex_Matcher(JNIEnv* env);
extern int register_java_util_regex_Pattern(JNIEnv* env);
extern int register_java_util_zip_Adler32(JNIEnv* env);
extern int register_java_util_zip_CRC32(JNIEnv* env);
extern int register_java_util_zip_Deflater(JNIEnv* env);
extern int register_java_util_zip_Inflater(JNIEnv* env);
extern int register_libcore_icu_ICU(JNIEnv* env);
extern int register_libcore_icu_NativeBreakIterator(JNIEnv* env);
extern int register_libcore_icu_NativeCollation(JNIEnv* env);
extern int register_libcore_icu_NativeConverter(JNIEnv* env);
extern int register_libcore_icu_NativeDecimalFormat(JNIEnv* env);
extern int register_libcore_icu_NativeIDN(JNIEnv* env);
extern int register_libcore_icu_NativeNormalizer(JNIEnv* env);
extern int register_libcore_icu_NativePluralRules(JNIEnv* env);
extern int register_libcore_icu_TimeZones(JNIEnv* env);
extern int register_libcore_io_AsynchronousCloseMonitor(JNIEnv* env);
extern int register_libcore_io_Memory(JNIEnv* env);
extern int register_libcore_io_OsConstants(JNIEnv* env);
extern int register_libcore_io_Posix(JNIEnv* env);
extern int register_libcore_net_RawSocket(JNIEnv* env);
extern int register_org_apache_harmony_dalvik_NativeTestTarget(JNIEnv* env);
extern int register_org_apache_harmony_xml_ExpatParser(JNIEnv* env);
extern int register_org_apache_harmony_xnet_provider_jsse_NativeCrypto(JNIEnv* env);

// DalvikVM calls this on startup, so we can statically register all our native methods.
extern "C" int registerCoreLibrariesJni(JNIEnv* env) {
    ScopedLocalFrame localFrame(env);

    JniConstants::init(env);

    bool result =
//            register_java_io_Console(env) != -1 &&
//            register_java_io_File(env) != -1 &&
//            register_java_io_ObjectStreamClass(env) != -1 &&
//            register_java_lang_Character(env) != -1 &&
//            register_java_lang_Math(env) != -1 &&
//            register_java_lang_ProcessManager(env) != -1 &&
//            register_java_lang_RealToString(env) != -1 &&
//            register_java_lang_StrictMath(env) != -1 &&
//            register_java_lang_StringToReal(env) != -1 &&
//            register_java_lang_System(env) != -1 &&
//            register_java_math_NativeBN(env) != -1 &&
//            register_java_nio_ByteOrder(env) != -1 &&
//            register_java_nio_charset_Charsets(env) != -1 &&
//            register_java_text_Bidi(env) != -1 &&
//            register_java_util_regex_Matcher(env) != -1 &&
//            register_java_util_regex_Pattern(env) != -1 &&
//            register_java_util_zip_Adler32(env) != -1 &&
//            register_java_util_zip_CRC32(env) != -1 &&
//            register_java_util_zip_Deflater(env) != -1 &&
//            register_java_util_zip_Inflater(env) != -1 &&
            register_libcore_icu_ICU(env) != -1 &&
//            register_libcore_icu_NativeBreakIterator(env) != -1 &&
//            register_libcore_icu_NativeCollation(env) != -1 &&
//            register_libcore_icu_NativeConverter(env) != -1 &&
//            register_libcore_icu_NativeDecimalFormat(env) != -1 &&
//            register_libcore_icu_NativeIDN(env) != -1 &&
//            register_libcore_icu_NativeNormalizer(env) != -1 &&
//            register_libcore_icu_NativePluralRules(env) != -1 &&
//            register_libcore_icu_TimeZones(env) != -1 &&
            register_libcore_io_AsynchronousCloseMonitor(env) != -1 &&
//            register_libcore_io_Memory(env) != -1 &&
//            register_libcore_io_OsConstants(env) != -1 &&
//            register_libcore_io_Posix(env) != -1 &&
//            register_libcore_net_RawSocket(env) != -1 &&
//            register_org_apache_harmony_dalvik_NativeTestTarget(env) != -1 &&
//            register_org_apache_harmony_xml_ExpatParser(env) != -1 &&
//            register_org_apache_harmony_xnet_provider_jsse_NativeCrypto(env) != -1 &&
            true;

    if (!result) {
        LOGE("Failed to initialize the core libraries; aborting...");
        abort();
    }
    return 0;
}
