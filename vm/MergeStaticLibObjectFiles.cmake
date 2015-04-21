# Copyright (C) 2015 RoboVM AB

# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at

#      http://www.apache.org/licenses/LICENSE-2.0

# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

# Merges the object files in a sttaic lib into a single object file and
# overwrites the static lib with a new static lib only containing this single
# object file. Will not do anything if CMAKE_BUILD_TYPE is 'debug' as it
# destroys the debug info. Only the specified symbols will be externally
# visible in the resulting static lib.

# Usage: merge_static_lib_object_files(lib sym1 sym2 ... symn)

function(merge_static_lib_object_files lib)

  if (NOT CMAKE_BUILD_TYPE STREQUAL "debug")

    set(exported_symbols ${ARGV})
    list(REMOVE_AT exported_symbols 0)

    if (APPLE)

      foreach(sym ${exported_symbols})
        list(APPEND exported_symbols_args "-exported_symbol")
        list(APPEND exported_symbols_args "_${sym}")
      endforeach()

      string(REPLACE ";" ", " exported_symbols_joined "${exported_symbols}")
      string(REPLACE ";" " " exported_symbols_args_joined "${exported_symbols_args}")
      add_custom_command(TARGET ${lib}
        COMMAND echo Merging object files in $<TARGET_FILE:${lib}> with exported symbols: ${exported_symbols_joined}
        COMMAND echo ld -arch ${CARCH} -r ${exported_symbols_args_joined} -all_load $<TARGET_FILE:${lib}> -o ${CMAKE_CURRENT_BINARY_DIR}/merged.o
        COMMAND ld -arch ${CARCH} -r ${exported_symbols_args} -all_load $<TARGET_FILE:${lib}> -o ${CMAKE_CURRENT_BINARY_DIR}/merged.o
        COMMAND rm -f $<TARGET_FILE:${lib}>
        COMMAND ar rcs $<TARGET_FILE:${lib}> ${CMAKE_CURRENT_BINARY_DIR}/merged.o
      )

    else()
      # Linux
      set(EMULATION_MODE elf_i386)
      if(64_BIT)
        set(EMULATION_MODE elf_x86_64)
      endif()
      message(STATUS "Format ${FORMAT}")

      foreach(sym ${exported_symbols})
        list(APPEND exported_symbols_args "-G")
        list(APPEND exported_symbols_args "${sym}")
      endforeach()

      string(REPLACE ";" ", " exported_symbols_joined "${exported_symbols}")
      string(REPLACE ";" " " exported_symbols_args_joined "${exported_symbols_args}")
      add_custom_command(TARGET ${lib}
        COMMAND echo Merging object files in $<TARGET_FILE:${lib}> with exported symbols: ${exported_symbols_joined}
        COMMAND echo ld -m ${EMULATION_MODE} -r --whole-archive $<TARGET_FILE:${lib}> -o ${CMAKE_CURRENT_BINARY_DIR}/tmp.o
        COMMAND ld -m ${EMULATION_MODE} -r --whole-archive $<TARGET_FILE:${lib}> -o ${CMAKE_CURRENT_BINARY_DIR}/tmp.o
        COMMAND echo objcopy -w ${exported_symbols_args} ${CMAKE_CURRENT_BINARY_DIR}/tmp.o ${CMAKE_CURRENT_BINARY_DIR}/merged.o
        COMMAND objcopy -w ${exported_symbols_args} ${CMAKE_CURRENT_BINARY_DIR}/tmp.o ${CMAKE_CURRENT_BINARY_DIR}/merged.o
        COMMAND rm -f $<TARGET_FILE:${lib}>
        COMMAND ar rcs $<TARGET_FILE:${lib}> ${CMAKE_CURRENT_BINARY_DIR}/merged.o
      )

    endif()

  endif()

endfunction()
