project(rt)

add_subdirectory(robovm)
add_subdirectory(android)

add_library(robovm-rt STATIC ${ROBOVM_RT_CORE_OBJECTS} ${ANDROID_OBJECTS})
set_target_properties(robovm-rt PROPERTIES SUFFIX "${LIB_SUFFIX}")
install(TARGETS robovm-rt DESTINATION ${INSTALL_DIR})
