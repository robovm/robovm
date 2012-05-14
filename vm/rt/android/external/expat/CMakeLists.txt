project(android-external-expat)

include_directories(. lib)

add_definitions(-DHAVE_EXPAT_CONFIG_H)

set(SRC
  lib/xmlparse.c
  lib/xmlrole.c
  lib/xmltok.c
)

add_library(android-external-expat OBJECT ${SRC})
set(EXTERNAL_EXPAT_OBJECTS $<TARGET_OBJECTS:android-external-expat> PARENT_SCOPE)

