project(port)

add_definitions(-DHYPORT_LIBRARY_DEFINE)
include_directories(../../include/shared ../../include/unix ../shared .)

add_library(nullvm-hyprt SHARED 
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

set_target_properties(nullvm-hyprt PROPERTIES LINK_FLAGS "-Wl,--version-script=${port_SOURCE_DIR}/symbols.map")
target_link_libraries(nullvm-hyprt hythr hycommon hypool dl pthread)

