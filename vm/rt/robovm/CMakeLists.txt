project(robovm-rt-core)

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
  java_lang_ref_Reference.c 
  java_lang_reflect_AccessibleObject.c 
  java_lang_reflect_Array.c 
  java_lang_reflect_Constructor.c 
  java_lang_reflect_Field.c 
  java_lang_reflect_Method.c 
  java_lang_reflect_Proxy.c 
  java_net_NetworkInterface.c 
  org_robovm_rt_VM.c 
  org_robovm_rt_bro_Dl.c 
  sun_misc_Unsafe.c 
)

if(DARWIN)
  set(SRC ${SRC} darwin.m)
  set_property(SOURCE darwin.m PROPERTY LANGUAGE C)
  set_property(SOURCE darwin.m PROPERTY COMPILE_FLAGS "-x objective-c")
  if(IOS)
    set_property(SOURCE darwin.m PROPERTY COMPILE_FLAGS "-x objective-c -fobjc-abi-version=2")
  endif()
endif()

add_library(robovm-rt-core OBJECT ${SRC})
set(ROBOVM_RT_CORE_OBJECTS $<TARGET_OBJECTS:robovm-rt-core> PARENT_SCOPE)

