project(hypool)
add_library(robovm-hypool STATIC 
  shared/pool.c shared/pool_cap.c 
)
install(TARGETS robovm-hypool DESTINATION ${INSTALL_DIR})

