project(hypool)
add_library(nullvm-hypool STATIC 
  shared/pool.c shared/pool_cap.c 
)
install(TARGETS nullvm-hypool DESTINATION ${INSTALL_DIR})

