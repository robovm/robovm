#!/bin/bash

SELF=$(basename $0)
BASE=$(cd $(dirname $0); pwd -P)
CLEAN=0

function usage {
  cat <<EOF
Usage: $SELF [options]
Options:
  --build=[release|debug] Specifies the build type. If not set both release
                          and debug versions of the libraries will be built.
  --target=...            Specifies the target(s) to build for. Supported 
                          targets are macosx-x86_64, macosx-x86, ios-x86_64,
                          ios-x86, ios-thumbv7, ios-arm64, linux-x86_64, 
                          linux-x86. Enclose multiple targets in quotes and 
                          separate with spaces or specify --target multiple
                          times. If not set the current host OS determines the
                          targets. macosx-x86_64, macosx-x86, ios-x86_64,
                          ios-x86, ios-thumbv7 and ios-arm64 on MacOSX and
                          linux-x86_64 and linux-x86 on Linux.
  --clean                 Cleans the build dir before starting the build.
  --help                  Displays this information and exits.
EOF
  exit $1
}

while [ "${1:0:2}" = '--' ]; do
  NAME=${1%%=*}
  VALUE=${1#*=}
  case $NAME in
    '--target') TARGETS="$TARGETS $VALUE" ;;
    '--clean') CLEAN=1 ;;
    '--build') BUILDS="$BUILDS $VALUE" ;;
    '--help')
      usage 0
      ;;
    *)
      echo "Unrecognized option or syntax error in option '$1'"
      usage 1
      ;;
  esac
  shift
done

if [ "x$TARGETS" = 'x' ]; then
  OS=$(uname)
  case $OS in
  Darwin)
    TARGETS="macosx-x86_64 macosx-x86 ios-x86_64 ios-x86 ios-thumbv7 ios-arm64"
    ;;
  Linux)
    TARGETS="linux-x86_64 linux-x86"
    ;;
  *)
    echo "Unsupported OS: $OS"
    exit 1
    ;;
  esac
fi
if [ "x$BUILDS" = 'x' ]; then
  BUILDS="debug release"
fi

# Validate targets
for T in $TARGETS; do
  if ! [[ $T =~ (macosx-(x86_64|x86))|(ios-(x86_64|x86|thumbv7|arm64))|(linux-(x86_64|x86)) ]] ; then
    echo "Unsupported target: $T"
    exit 1
  fi
done

# Validate build types
for B in $BUILDS; do
  if ! [[ $B =~ (debug|release) ]] ; then
    echo "Unsupported build type: $B"
    exit 1
  fi
done

mkdir -p "$BASE/target/build"
if [ "$CLEAN" = '1' ]; then
  for T in $TARGETS; do
    for B in $BUILDS; do
      rm -rf "$BASE/target/build/$T-$B"
    done
  done
fi

if [ $(uname) = 'Darwin' ]; then
  if xcrun -f clang &> /dev/null; then
    CC=$(xcrun -f clang)
  else
    CC=$(which clang)
  fi
  if xcrun -f clang++ &> /dev/null; then
    CXX=$(xcrun -f clang++)
  else
    CXX=$(which clang++)
  fi
else
  CC=$(which gcc)
  CXX=$(which g++)
fi

for T in $TARGETS; do
  OS=${T%%-*}
  ARCH=${T#*-}
  for B in $BUILDS; do
    BUILD_TYPE=$B
    mkdir -p "$BASE/target/build/$T-$B"
    rm -rf "$BASE/binaries/$OS/$ARCH/$B"
    bash -c "cd '$BASE/target/build/$T-$B'; cmake -DCMAKE_C_COMPILER=$CC -DCMAKE_CXX_COMPILER=$CXX -DCMAKE_BUILD_TYPE=$BUILD_TYPE -DOS=$OS -DARCH=$ARCH '$BASE'; make install"
    R=$?
    if [[ $R != 0 ]]; then
      echo "$T-$B build failed"
      exit $R
    fi
  done
done
