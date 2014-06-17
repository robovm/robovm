project(android-libcore-crypto)

add_definitions(-DJNI_JARJAR_PREFIX=com/android/)

include_directories(
  ../../../../../external/openssl/include
  ../../../../luni/src/main/native
)

set(SRC
  org_conscrypt_NativeCrypto.cpp
)

add_library(android-libcore-crypto OBJECT ${SRC})
set(LIBCORE_CRYPTO_OBJECTS $<TARGET_OBJECTS:android-libcore-crypto> PARENT_SCOPE)

