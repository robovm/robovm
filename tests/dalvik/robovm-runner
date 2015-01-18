#!/bin/bash

SELF=$(basename $0)
BASE=$(cd $(dirname $0)/../..; pwd -P)

SSH_HOST=

if [ -f "$BASE/tests/dalvik/robovm.env" ]; then
  . $BASE/tests/dalvik/robovm.env
fi

if [ "x$SSH_HOST" != 'x' -a "x$SSH_HOST" != 'xlocalhost' ]; then
  TARGET=$(pwd)
  ssh -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null -o LogLevel=quiet $SSH_HOST "rm -rf /tmp/test-*"
  rsync -az --delete -e "ssh -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null -o LogLevel=quiet" $TARGET/ $SSH_HOST:$TARGET/ > /dev/null
  ssh -o StrictHostKeyChecking=no -o UserKnownHostsFile=/dev/null -o LogLevel=quiet $SSH_HOST "$@"
  result=$?
else
  exec "$@"

  result=$?
fi
exit $result
