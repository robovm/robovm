#!/bin/bash

BASE=$(cd $(dirname $0); pwd -P)
BUILD=$(printf "%08d" `git log --oneline | wc -l`)
BUCKET=binaries.robovm.org
ENDPOINT=http://$BUCKET.s3-website-eu-west-1.amazonaws.com

if which md5sum > /dev/null; then
  # On Linux we use md5sum
  MD5SUM=md5sum
else
  # On OS X we use md5 -q
  MD5SUM='md5 -q'
fi

rm -rf "$BASE/target/upload"
mkdir -p "$BASE/target/upload/vm"
rsync -a --exclude '*-dbg.a' --include '*.a' --include '*/' --exclude '**' "$BASE/target/binaries/" "$BASE/target/upload/vm/"
s3cmd sync --add-header=x-amz-storage-class:REDUCED_REDUNDANCY -r -P -rr --delete-removed --no-preserve \
    "$BASE/target/upload/" "s3://$BUCKET/$BUILD/"

# Copy the binaries to binaries/ while we're at it. Prevents the need for running fetch-binaries.sh.
rsync -a --exclude '*-dbg.a' --include '*.a' --include '*/' --exclude '**' "$BASE/target/binaries/" "$BASE/binaries/"

rm -f "$BASE/binaries.idx"
find "$BASE/target/upload" -type f | while read F; do
  MD5=$($MD5SUM $F | awk '{print $1}')
  F=${F#$BASE/target/upload/vm/}
  echo $F $ENDPOINT/$BUILD/vm/$F $MD5 >> "$BASE/binaries.idx"
done
