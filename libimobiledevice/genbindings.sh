#!/bin/bash

BASE=$(cd $(dirname $0); pwd -P)
PACKAGE=org.robovm.libimobiledevice.binding
JAVAOUT="$BASE/src/main/java/${PACKAGE//.//}"
COUT="$BASE/src/main/native"

function rename {
    from=$1
    to=$2
    if [ $(uname) == 'Darwin' ]; then
    	sed -i '' -e "s/[[:<:]]$from[[:>:]]/$to/g" "$JAVAOUT"/*.java
    else
    	sed -i -e "s/\b$from\b/$to/g" "$JAVAOUT"/*.java
    fi
    mv "$JAVAOUT/$from.java" "$JAVAOUT/$to.java" 
}

mkdir -p "$JAVAOUT"
mkdir -p "$COUT"
swig -I"$BASE/src/main/swig/include" \
     -outdir "$JAVAOUT" -o "$COUT"/libimobiledevice_wrap.c \
     -java -package $PACKAGE -fakeversion 2.0.4 \
     "$BASE/src/main/swig/libimobiledevice.i"

rename SWIGTYPE_p_idevice_private IDeviceRef
rename SWIGTYPE_p_idevice_connection_private IDeviceConnectionRef
rename SWIGTYPE_p_lockdownd_client_private LockdowndClientRef
rename SWIGTYPE_p_plist_t PlistRef
rename SWIGTYPE_p_afc_client_private AfcClientRef
rename SWIGTYPE_p_instproxy_client_private InstproxyClientRef
