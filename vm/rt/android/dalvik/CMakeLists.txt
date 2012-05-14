project(android-dalvik)

add_subdirectory(vm)
add_subdirectory(libnativehelper)
set(DALVIK_OBJECTS
  ${DALVIK_VM_OBJECTS} 
  ${DALVIK_LIBNATIVEHELPER_OBJECTS} 
  PARENT_SCOPE
)

