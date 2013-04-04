project(android-external-javasqlite)

include_directories(
  ../sqlite/dist
)

add_definitions(
  -DDONT_USE_JNI_ONLOAD
)

set(SRC
  src/main/native/sqlite_jni.c
)

add_library(android-external-javasqlite OBJECT ${SRC})
set(EXTERNAL_JAVASQLITE_OBJECTS $<TARGET_OBJECTS:android-external-javasqlite> PARENT_SCOPE)
