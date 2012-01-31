#!/bin/bash

BASE=$(cd $(dirname $0); pwd -P)
CLEAN=0

while [ "${1:0:2}" = '--' ]; do
  NAME=${1%%=*}
  VALUE=${1#*=}
  case $NAME in
    '--targets') TARGETS=$VALUE ;;
    '--clean') CLEAN=1 ;;
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
    TARGETS="macosx-i386 macosx-x86_64 ios-i386"
    ;;
  Linux)
    TARGETS="linux-$(uname -m)"
    ;;
  *)
    echo "Unsupported OS: $OS"
    exit 1
    ;;
  esac
fi

mkdir -p "$BASE/target/build"
if [ "$CLEAN" = '1' ]; then
  rm -rf "$BASE/target/build/*"
fi

for T in $TARGETS; do
  OS=${T%%-*}
  ARCH=${T#*-}
  mkdir -p "$BASE/target/build/$T-Debug"
  rm -rf "$BASE/binaries/$OS/$ARCH/Debug"
  bash -c "cd '$BASE/target/build/$T-Debug'; cmake -D CMAKE_BUILD_TYPE=Debug -D OS=$OS -D ARCH=$ARCH '$BASE'; make install"
  mkdir -p "$BASE/target/build/$T-Release"
  rm -rf "$BASE/binaries/$OS/$ARCH/Release"
  bash -c "cd '$BASE/target/build/$T-Release'; cmake -D CMAKE_BUILD_TYPE=Release -D OS=$OS -D ARCH=$ARCH '$BASE'; make install"
done
