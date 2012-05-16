project(android-libcore-luni)

include_directories(
  ../../../../../external/expat/lib
  ../../../../../external/icu4c/common
  ../../../../../external/icu4c/i18n
  ../../../../../external/openssl/include
  ../../../../../external/zlib
)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -Wno-unused-but-set-variable")

set(SRC
  AsynchronousSocketCloseMonitor.cpp
  JniConstants.cpp
  JniException.cpp
  NetworkUtilities.cpp
  Register.cpp
  cbigint.cpp
  java_io_Console.cpp
  java_io_File.cpp
  java_io_ObjectStreamClass.cpp
  java_lang_Character.cpp
  java_lang_Math.cpp
  java_lang_ProcessManager.cpp
  java_lang_RealToString.cpp
  java_lang_StrictMath.cpp
  java_lang_StringToReal.cpp
  java_lang_System.cpp
  java_math_NativeBN.cpp
  java_nio_ByteOrder.cpp
  java_nio_charset_Charsets.cpp
  java_text_Bidi.cpp
  java_util_regex_Matcher.cpp
  java_util_regex_Pattern.cpp
  java_util_zip_Adler32.cpp
  java_util_zip_CRC32.cpp
  java_util_zip_Deflater.cpp
  java_util_zip_Inflater.cpp
  libcore_icu_ICU.cpp
  libcore_icu_NativeBreakIterator.cpp
  libcore_icu_NativeCollation.cpp
  libcore_icu_NativeConverter.cpp
  libcore_icu_NativeDecimalFormat.cpp
  libcore_icu_NativeIDN.cpp
  libcore_icu_NativeNormalizer.cpp
  libcore_icu_NativePluralRules.cpp
  libcore_icu_TimeZones.cpp
  libcore_io_AsynchronousCloseMonitor.cpp
  libcore_io_Memory.cpp
  libcore_io_OsConstants.cpp
  libcore_io_Posix.cpp
  org_apache_harmony_xml_ExpatParser.cpp
  org_apache_harmony_xnet_provider_jsse_NativeCrypto.cpp
  readlink.cpp
  realpath.cpp
  toStringArray.cpp
  valueOf.cpp
)
if(LINUX)
  set(SRC
    ${SRC}
    libcore_net_RawSocket.cpp
  )
endif()

add_library(android-libcore-luni OBJECT ${SRC})
set(LIBCORE_LUNI_OBJECTS $<TARGET_OBJECTS:android-libcore-luni> PARENT_SCOPE)

