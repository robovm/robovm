#!/bin/bash

SELF=$(basename $0)
BASE=$(cd $(dirname $0)/../..; pwd -P)

CP=
ARCH=auto
OS=auto
TIMESTAMP=
SSH_HOST=

if [ -f "$BASE/tests/libcore/$SELF.env" ]; then
  . $BASE/tests/libcore/$SELF.env
fi

# Record -classpath, -arch, -os and -timestamp and remove them from $@
# The rest of the args should be the main class (org.apache.tools.ant.taskdefs.optional.junit.JUnitTestRunner)
# and its args
while [ ${1:0:1} = '-' ]; do
  if [ "$1" = '-cp' -o "$1" = '-classpath' ]; then
    shift
    CP=$1
  elif [ "$1" = '-arch' ]; then
    shift
    ARCH=$1
  elif [ "$1" = '-os' ]; then
    shift
    OS=$1
  elif [ "$1" = '-timestamp' ]; then
    shift
    TIMESTAMP=$1
  elif [ "$1" = '-host' ]; then
    shift
    SSH_HOST=$1
  fi
  shift
done

MAINCLASS=$1
shift

TMP=/tmp/robovm-libcore.tmp.$OS.$ARCH.$TIMESTAMP
TARGET=/tmp/robovm-libcore.$OS.$ARCH.$TIMESTAMP

if [ ! -x $TARGET/test ]; then
  export ROBOVM_DEV_ROOT=$BASE
  $ROBOVM_DEV_ROOT/bin/robovm \
    -tmp $TMP \
    -d $TARGET \
    -arch $ARCH \
    -os $OS \
    -o test \
    -verbose \
    -forcelinkclasses '##.#' \
    -use-debug-libs \
    -cp $CP
  result=$?
  if [ $result -ne 0 ]; then
    exit $result
  fi
  mkdir -p $TARGET/work
fi

if [ "x$SSH_HOST" != 'x' -a "x$SSH_HOST" != 'xlocalhost' ]; then
  rsync -a --delete $TARGET/ $SSH_HOST:$TARGET/
  echo ssh $SSH_HOST ROBOVM_TESTS_INCLUDE_PATTERN="'$ROBOVM_TESTS_INCLUDE_PATTERN'" ROBOVM_TESTS_EXCLUDE_PATTERN="'$ROBOVM_TESTS_EXCLUDE_PATTERN'" ROBOVM_TESTS_RUN_EXCLUDED="'$ROBOVM_TESTS_RUN_EXCLUDED'" $TARGET/test -rvm:mx256M -rvm:MainClass=$MAINCLASS $@
  ssh $SSH_HOST ROBOVM_TESTS_INCLUDE_PATTERN="'$ROBOVM_TESTS_INCLUDE_PATTERN'" ROBOVM_TESTS_EXCLUDE_PATTERN="'$ROBOVM_TESTS_EXCLUDE_PATTERN'" ROBOVM_TESTS_RUN_EXCLUDED="'$ROBOVM_TESTS_RUN_EXCLUDED'" $TARGET/test -rvm:mx256M -rvm:MainClass=$MAINCLASS $@
  result=$?
  rsync -a $SSH_HOST:$TARGET/work/ $TARGET/work/
else
  $TARGET/test -rvm:mx256M -rvm:MainClass=$MAINCLASS $@
  result=$?
fi
exit $result
