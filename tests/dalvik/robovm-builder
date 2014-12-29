#!/bin/bash

SELF=$(basename $0)
BASE=$(cd $(dirname $0)/../..; pwd -P)

ARCH=auto
OS=auto

if [ -f "$BASE/tests/dalvik/robovm.env" ]; then
  . $BASE/tests/dalvik/robovm.env
fi

EXTRA_ARGS=
[ "x$DEBUG" == 'x1' ] && EXTRA_ARGS="$EXTRA_ARGS -debug"
[ "x$USE_DEBUG_LIBS" == 'x1' ] && EXTRA_ARGS="$EXTRA_ARGS -use-debug-libs"

TMP=$(pwd).tmp
TARGET=$(pwd)

if [ ! -x $TARGET/test.out ]; then
  if [ "x$ROBOVM_HOME" != 'x' ]; then
    ROBOVM="$ROBOVM_HOME/bin/robovm"
  else
    export ROBOVM_DEV_ROOT=$BASE
    ROBOVM="$ROBOVM_DEV_ROOT/bin/robovm"
  fi
  echo "Build command line: " "$ROBOVM" \
    -arch $ARCH \
    -os $OS \
    -tmp $TMP \
    -d $TARGET \
    -o test.out \
    -verbose \
    $EXTRA_ARGS \
    $@

  "$ROBOVM" \
    -arch $ARCH \
    -os $OS \
    -tmp $TMP \
    -d $TARGET \
    -o test.out \
    -verbose \
    $EXTRA_ARGS \
    "$@"
  result=$?
  if [ $result -ne 0 ]; then
    exit $result
  fi
fi
