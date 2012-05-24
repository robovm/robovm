project(port)

add_definitions(-DHYPORT_LIBRARY_DEFINE)
include_directories(../../include/shared ../../include/unix ../shared .)

add_library(robovm-hyprt STATIC 
  ../shared/port_copyright.c ../shared/hyerror.c 
	hyerrorhelpers.c ../shared/hyexit.c hyfile.c hyfiletext.c 
	../shared/hygp.c hymem.c 
	${HY_OS}/hymmap.c ../shared/hynls.c hynlshelpers.c hyosdump.c 
	../shared/hyport.c ../shared/hyportcontrol.c hyportptb.c 
	hysharedhelper.c ${HY_OS}/hysl.c hysock.c 
	../shared/hystr.c ../shared/hystrftime.c ../shared/hystsl.c 
	hysysinfo.c hytime.c ../shared/hytlshelpers.c hytty.c
  hycpu.c hyipcmutex.c hyshmem.c hyshsem.c ${HY_OS}/hyvmem.c
)

install(TARGETS robovm-hyprt DESTINATION ${INSTALL_DIR})

