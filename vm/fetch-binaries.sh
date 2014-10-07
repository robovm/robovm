#!/bin/bash

BASE=$(cd $(dirname $0); pwd -P)

if which md5sum; then
  # On Linux we use md5sum
  MD5SUM=md5sum
else
  # On OS X we use md5 -q
  MD5SUM='md5 -q'
fi

mkdir -p "$BASE/binaries"

# Set modification time of all files in binaries/ to 1970-01-01
find "$BASE/binaries" -type f | while read F; do
  touch -t '197001010000' $F
done

cat "$BASE/binaries.idx" | while read LINE; do
  LOCAL=binaries/$(echo $LINE | awk '{print $1}')
  REMOTE=$(echo $LINE | awk '{print $2}')
  REMOTE_MD5=$(echo $LINE | awk '{print $3}')
  LOCAL_MD5='0'

  if [ -f "$BASE/$LOCAL" ]; then
    LOCAL_MD5=$($MD5SUM "$BASE/$LOCAL" | awk '{print $1}')
  fi

  if [ $REMOTE_MD5 != $LOCAL_MD5 ]; then
    echo "Downloading $REMOTE to $BASE/$LOCAL"
    mkdir -p $(dirname "$BASE/$LOCAL")
    curl "$REMOTE" > "$BASE/$LOCAL"
  else
    echo "$BASE/$LOCAL is up to date"
  fi

  # Make sure the modification time of the file is now to prevent
  # it from being deleted below
  touch "$BASE/$LOCAL"
done

# Delete any file which isn't in the index. Such files will still
# have modification time 1970-01-01 and thus be older than 30 days.
find "$BASE/binaries" -type f -mtime +30 | while read F; do
  rm -f $F
done
