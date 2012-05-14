project(rt)

add_subdirectory(nullvm)
add_subdirectory(android)

add_library(nullvm-rt STATIC ${NULLVM_RT_CORE_OBJECTS} ${ANDROID_OBJECTS})
install(TARGETS nullvm-rt DESTINATION ${INSTALL_DIR})

