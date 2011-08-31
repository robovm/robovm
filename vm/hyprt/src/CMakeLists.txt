project(hyprt)

add_subdirectory(common)
add_subdirectory(pool)
if(LINUX OR DARWIN)
  add_subdirectory(thread/unix)
  add_subdirectory(port/unix)
endif()

