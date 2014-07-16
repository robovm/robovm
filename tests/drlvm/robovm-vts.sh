#!/bin/bash

export HOME=$(cd ~; pwd)

BASE=$(cd $(dirname $0)/../..; pwd -P)
if [ -f "$BASE/tests/drlvm/robovm-vts.env" ]; then
  . $BASE/tests/drlvm/robovm-vts.env
fi
[ "x$ARCH" == 'x' ] && ARCH=auto
[ "x$OS" == 'x' ] && OS=auto
[ "x$TARGET" == 'x' ] && TARGET=/tmp/robovm-vts.$ARCH
EXTRA_ARGS=
[ "x$DEBUG" == 'x1' ] && EXTRA_ARGS="$EXTRA_ARGS -debug"

export PATH

mkdir -p $HOME/.robovm/vts/

n=0
while [ ${1:0:1} = '-' ]; do
  if [ "$1" = '-cp' ]; then
    shift
    parts=$(echo $1 | tr ':' '\n')
    for p in $parts; do
      if [ -d "$p" ]; then
        JAR=$HOME/.robovm/vts/vts$n.jar
        if [ ! -f "$JAR" ]; then
          jar cf $JAR -C "$p" .
        fi
        CP=$(test "x$CP" = "x" && echo "$JAR" || echo "$CP:$JAR")
        n=$((n + 1))
      elif [ -f "$p" ]; then
        CP=$(test "x$CP" = "x" && echo "$p" || echo "$CP:$p")
      fi
    done
  fi
  shift
done

MAINCLASS=$1
shift

while [ "x$1" != 'x' ]; do
  RUNARGS="$RUNARGS $1"
  shift
done

#echo "RUNARGS=$RUNARGS"
#echo "MAINCLASS=$MAINCLASS"

if [ ! -x $TARGET/vts ]; then
  export ROBOVM_DEV_ROOT=$BASE
  $ROBOVM_DEV_ROOT/bin/robovm \
    -tmp /tmp/robovm-vts.tmp \
    -d $TARGET \
    -arch $ARCH \
    -os $OS \
    -o vts \
    -verbose \
    -use-debug-libs \
    -dynamic-jni \
    $EXTRA_ARGS \
    -cp $CP
fi

LIBPATH=$TARGET
if [ "x$LD_LIBRARY_PATH" != 'x' ]; then
  LIBPATH=$LIBPATH:$LD_LIBRARY_PATH
fi
if [ "x$DYLD_LIBRARY_PATH" != 'x' ]; then
  LIBPATH=$LIBPATH:$DYLD_LIBRARY_PATH
fi

if [ "x$SSH_HOST" != 'x' ]; then
  rsync -a --delete $TARGET/ $SSH_HOST:$TARGET/ > /dev/null
  ssh $SSH_HOST $TARGET/vts -rvm:MainClass=$MAINCLASS $RUNARGS
else
  LD_LIBRARY_PATH=$LIBPATH DYLD_LIBRARY_PATH=$LIBPATH $TARGET/vts -rvm:MainClass=$MAINCLASS $RUNARGS
fi
CODE=$?
exit $CODE
