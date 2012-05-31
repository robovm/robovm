project(drlvm-port)

add_subdirectory(thread)
set(DRLVM_PORT_OBJECTS
  ${DRLVM_PORT_THREAD_OBJECTS} 
  PARENT_SCOPE
)
