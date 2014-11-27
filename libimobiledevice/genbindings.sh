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
rename SWIGTYPE_p_mobile_image_mounter_client_private MobileImageMounterClientRef
rename afc_error_t AfcError
rename idevice_error_t IDeviceError
rename instproxy_error_t InstProxyError
rename lockdownd_error_t LockdowndError
rename mobile_image_mounter_error_t MobileImageMounterError
