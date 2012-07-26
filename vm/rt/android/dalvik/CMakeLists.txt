project(android-dalvik)

add_subdirectory(vm)
set(DALVIK_OBJECTS
  ${DALVIK_VM_OBJECTS} 
  PARENT_SCOPE
)

