project(android-libcore)

include_directories(
  include
)

add_subdirectory(crypto/src/main/native)
add_subdirectory(luni/src/main/native)
set(LIBCORE_OBJECTS
  ${LIBCORE_CRYPTO_OBJECTS} 
  ${LIBCORE_LUNI_OBJECTS} 
  PARENT_SCOPE
)

