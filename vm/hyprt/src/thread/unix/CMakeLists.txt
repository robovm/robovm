project(hythr)

include_directories(../../include/shared ../../include/unix ../shared .)

add_library(hythr STATIC 
  ../shared/thread_copyright.c thrspinlock.c thrhelp.c
	../shared/hythread.c ../shared/hythreadinspect.c linuxonexit.c priority.c 
	rasthrsup.c ../shared/rwmutex.c thrcreate.c thrdsup.c ../shared/thrprof.c
)

