project(portlib)

if (WITH_FPIC)
  add_definitions(-fPIC)
endif()

add_subdirectory(common)
add_subdirectory(pool)
if(LINUX)
  add_subdirectory(thread/unix)
  add_subdirectory(port/unix)
endif()

