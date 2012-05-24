project(rt)

add_subdirectory(robovm)
add_subdirectory(android)

add_library(robovm-rt STATIC ${ROBOVM_RT_CORE_OBJECTS} ${ANDROID_OBJECTS})
install(TARGETS robovm-rt DESTINATION ${INSTALL_DIR})

