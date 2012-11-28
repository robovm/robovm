#!/bin/bash

BASE=$(cd $(dirname $0); pwd -P)

rsync -av --exclude '*-dbg.a' --include '*.a' --include '*/' --exclude '**' "$BASE/target/binaries/" "$BASE/binaries/"
