#!/bin/bash

BASE=$(cd $(dirname $0); pwd -P)
CLEAN=0

while [ "${1:0:2}" = '--' ]; do
  NAME=${1%%=*}
  VALUE=${1#*=}
  case $NAME in
    '--target') TARGETS="$TARGETS $VALUE" ;;
    '--clean') CLEAN=1 ;;
    '--build') BUILDS="$BUILDS $VALUE" ;;
    *)
      echo "Unrecognized option or syntax error in option '$1'"
      exit 1
      ;;
  esac
  shift
done

if [ "x$TARGETS" = 'x' ]; then
  OS=$(uname)
  case $OS in
  Darwin)
    TARGETS="macosx-x86 ios-x86 ios-thumbv7"
    ;;
  Linux)
    TARGETS="linux-x86"
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
  if ! [[ $T =~ (macosx|ios|linux)-(x86|thumbv7) ]] ; then
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
    rm -rf "$BASE/target/build/$T"
  done
fi

CC=$(which gcc)
CXX=$(which g++)
if [ $(uname) = 'Darwin' ]; then
  CC=$(which clang)
  CXX=$(which clang++)
fi

for T in $TARGETS; do
  OS=${T%%-*}
  ARCH=${T#*-}
  for B in $BUILDS; do
    BUILD_TYPE=$B
    mkdir -p "$BASE/target/build/$T-$B"
    rm -rf "$BASE/binaries/$OS/$ARCH/$B"
    bash -c "cd '$BASE/target/build/$T-$B'; cmake -DCMAKE_C_COMPILER=$CC -DCMAKE_CXX_COMPILER=$CXX -DCMAKE_BUILD_TYPE=$BUILD_TYPE -DOS=$OS -DARCH=$ARCH '$BASE'; make install"
  done
done
