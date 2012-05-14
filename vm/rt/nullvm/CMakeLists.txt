project(nullvm-rt-core)

include_directories(../../core/include ${CMAKE_BINARY_DIR}/gc/include)

set(SRC
  reflection_helpers.c 
  java_lang_Class.c 
  java_lang_ClassCache.c 
  java_lang_Object.c 
  java_lang_Thread.c 
  java_lang_Throwable.c 
  java_lang_Runtime.c 
  java_lang_String.c 
  java_lang_System.c 
  java_lang_VMClassLoader.c 
  java_lang_reflect_Array.c 
  java_lang_reflect_Constructor.c 
  java_lang_reflect_Field.c 
  java_lang_reflect_Method.c 
  java_lang_reflect_Proxy.c 
  org_nullvm_rt_VM.c 
  org_nullvm_rt_bro_Dl.c 
  org_nullvm_rt_bro_Bro.c 
  sun_misc_Unsafe.c 
)

add_library(nullvm-rt-core OBJECT ${SRC})
set(NULLVM_RT_CORE_OBJECTS $<TARGET_OBJECTS:nullvm-rt-core> PARENT_SCOPE)

