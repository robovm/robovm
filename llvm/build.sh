#!/bin/bash

SELF=$(basename $0)
BASE=$(cd $(dirname $0); pwd -P)
CLEAN=0
WIN=0
WORKERS=1

function usage {
  cat <<EOF
Usage: $SELF [options]
Options:
  --clean                 Cleans the build dir before starting the build.
  --help                  Displays this information and exits.
EOF
  exit $1
}

while [ "${1:0:2}" = '--' ]; do
  NAME=${1%%=*}
  VALUE=${1#*=}
  case $NAME in
    '--windows') WIN=1 ;;
    '--clean') CLEAN=1 ;;
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

if [ "$WIN" = '1' ]; then
  OS="Windows"
else
  OS=$(uname)
fi

case $OS in
Darwin)
  TARGETS="macosx-x86_64"
  ;;
Linux)
  TARGETS="linux-x86_64 linux-x86"
  ;;
Windows)
  TARGETS="windows-x86_64" # windows-x86"
  ;;
*)
  echo "Unsupported OS: $OS"
  exit 1
  ;;
esac

mkdir -p "$BASE/target/build"
if [ "$CLEAN" = '1' ]; then
  for T in $TARGETS; do
    rm -rf "$BASE/target/build/$T"
  done
fi

case $OS in
Darwin)
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
  ;;
Linux)
  CC=$(which gcc)
  CXX=$(which g++)
  ;;
Windows)
  CC=$(which x86_64-w64-mingw32-gcc)
  CXX=$(which x86_64-w64-mingw32-g++)
  WORKERS=1
  ;;
*)
  echo "Unsupported OS: $OS"
  exit 1
  ;;
esac

for T in $TARGETS; do
  OS=${T%%-*}
  ARCH=${T#*-}
  BUILD_TYPE=Release
  mkdir -p "$BASE/target/build/$T"
  rm -rf "$BASE/binaries/$OS/$ARCH"
  bash -c "cd '$BASE/target/build/$T'; cmake -DCMAKE_C_COMPILER=$CC -DCMAKE_CXX_COMPILER=$CXX -DCMAKE_BUILD_TYPE=$BUILD_TYPE -DOS=$OS -DARCH=$ARCH '$BASE'; make -j $WORKERS install/strip"
  R=$?
  if [[ $R != 0 ]]; then
    echo "$T build failed"
    exit $R
  fi
done
