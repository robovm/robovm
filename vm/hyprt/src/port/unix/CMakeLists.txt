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

if(LINUX)
  set_target_properties(nullvm-hyprt PROPERTIES LINK_FLAGS "-Wl,--version-script=${port_SOURCE_DIR}/symbols.linux" INSTALL_RPATH "$ORIGIN")
  target_link_libraries(nullvm-hyprt hythr hycommon hypool dl pthread gc)
elseif(DARWIN)
  set_target_properties(nullvm-hyprt PROPERTIES LINK_FLAGS "-exported_symbols_list ${port_SOURCE_DIR}/symbols.darwin" INSTALL_NAME_DIR "@loader_path")
  target_link_libraries(nullvm-hyprt hythr hycommon hypool dl pthread iconv gc)
endif()
install(TARGETS nullvm-hyprt DESTINATION ${INSTALL_DIR})
