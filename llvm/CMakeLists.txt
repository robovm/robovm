cmake_minimum_required(VERSION 2.8)
project(robovm-llvm)
include(ExternalProject)

find_package(JNI REQUIRED)

if(OS STREQUAL "linux")
  set(LINUX YES)
elseif(OS STREQUAL "macosx")
  set(MACOSX YES)
endif()

if (ARCH STREQUAL "x86")
  set(X86 YES)
elseif (ARCH STREQUAL "x86_64")
  set(X86_64 YES)
endif()

set(C_CXX_FLAGS "${C_CXX_FLAGS} -fvisibility=hidden -fdata-sections -ffunction-sections")
if(MACOSX AND X86)
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -arch i386 -mmacosx-version-min=10.6")
  set(LD_FLAGS "${LD_FLAGS} -arch i386 -mmacosx-version-min=10.6")
elseif(MACOSX AND X86_64)
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -arch x86_64 -mmacosx-version-min=10.6")
  set(LD_FLAGS "${LD_FLAGS} -arch x86_64 -mmacosx-version-min=10.6")
elseif(LINUX AND X86)
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -m32")
  set(LD_FLAGS "${LD_FLAGS} -m64")
elseif(LINUX AND X86_64)
  set(C_CXX_FLAGS "${C_CXX_FLAGS} -m64")
  set(LD_FLAGS "${LD_FLAGS} -m64")
endif()

set(LLVM_ARCHS "X86^^ARM")
set(LLVM_C_FLAGS "${C_CXX_FLAGS} -fvisibility=hidden -fdata-sections -ffunction-sections")
set(LLVM_CXX_FLAGS "${C_CXX_FLAGS} -fvisibility=hidden -fdata-sections -ffunction-sections")
ExternalProject_Add(extllvm
  URL http://llvm.org/releases/3.3/llvm-3.3.src.tar.gz
  LIST_SEPARATOR ^^
  CONFIGURE_COMMAND cmake -DCMAKE_BUILD_TYPE=Release -DBUILD_SHARED_LIBS=OFF "-DCMAKE_INSTALL_PREFIX=${CMAKE_BINARY_DIR}/llvm" "-DLLVM_TARGETS_TO_BUILD=${LLVM_ARCHS}" "-DCMAKE_C_FLAGS=${LLVM_C_FLAGS}" "-DCMAKE_CXX_FLAGS=${LLVM_CXX_FLAGS}" ../extllvm/
)

set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} ${C_CXX_FLAGS}")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} ${C_CXX_FLAGS} -fno-rtti")
set(CMAKE_INSTALL_DO_STRIP YES)

add_definitions(-D__STDC_LIMIT_MACROS -D__STDC_CONSTANT_MACROS)
include_directories(${JAVA_INCLUDE_PATH} ${JAVA_INCLUDE_PATH2} ${CMAKE_BINARY_DIR}/llvm/include)

# This was generated using 'llvm-config --libs core bitreader bitwriter irreader ipo arm x86 OUTPUT_VARIABLE LLVM_LIBS'
set(LLVM_LIBS
  -lLLVMX86Disassembler -lLLVMX86AsmParser -lLLVMX86CodeGen -lLLVMX86Desc -lLLVMX86Info 
  -lLLVMX86AsmPrinter -lLLVMX86Utils -lLLVMARMDisassembler -lLLVMARMCodeGen -lLLVMSelectionDAG 
  -lLLVMAsmPrinter -lLLVMCodeGen -lLLVMARMAsmParser -lLLVMMCParser -lLLVMARMDesc -lLLVMARMInfo 
  -lLLVMARMAsmPrinter -lLLVMipo -lLLVMObjCARCOpts -lLLVMVectorize -lLLVMScalarOpts -lLLVMInstCombine 
  -lLLVMTransformUtils -lLLVMipa -lLLVMAnalysis -lLLVMTarget -lLLVMMC -lLLVMObject -lLLVMIRReader 
  -lLLVMAsmParser -lLLVMBitWriter -lLLVMBitReader -lLLVMCore -lLLVMSupport
)
# This was generated using 'llvm-config --ldflags'
if(LINUX)
  set(LLVM_LDFLAGS
    -L"${CMAKE_BINARY_DIR}/llvm/lib"
    -lrt -ldl -lpthread -lz
  )
elseif(MACOSX)
  set(LLVM_LDFLAGS
    -L"${CMAKE_BINARY_DIR}/llvm/lib"
    -lpthread -lz
  )
endif()

set(SRC
  src/main/native/LLVM_wrap.c
  src/main/native/LLVMExtra.cpp
)

add_library(robovm-llvm SHARED ${SRC})
add_dependencies(robovm-llvm extllvm)
target_link_libraries(robovm-llvm ${LLVM_LIBS} ${LLVM_LDFLAGS})
set_target_properties(robovm-llvm PROPERTIES LINKER_LANGUAGE CXX)
if(LINUX)
  set_target_properties(robovm-llvm PROPERTIES LINK_FLAGS "-Wl,--gc-sections -Wl,--version-script=${CMAKE_SOURCE_DIR}/src/main/native/exports_linux.sym")
elseif(MACOSX)
  set_target_properties(robovm-llvm PROPERTIES LINK_FLAGS "-Wl,-dead_strip -Wl,-exported_symbols_list -Wl,${CMAKE_SOURCE_DIR}/src/main/native/exports_macosx.sym")
endif()
set(INSTALL_DIR ${CMAKE_SOURCE_DIR}/src/main/resources/org/robovm/llvm/binding/${OS}/${ARCH})
install(TARGETS robovm-llvm DESTINATION ${INSTALL_DIR})
