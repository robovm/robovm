#!/bin/bash

BASE=$(cd $(dirname $0); pwd -P)
ls "$BASE"/*.patch | sort | while read p; do
  patch -p1 -t -N < $p
done
