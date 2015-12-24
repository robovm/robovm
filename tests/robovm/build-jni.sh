#!/bin/bash

MACOSX_SDK_VERSION=10.10
IOS_SDK_VERSION=8.4

SELF=$(basename $0)
BASE=$(cd $(dirname $0); pwd -P)

rm -rf $BASE/target/jni
mkdir -p $BASE/target/jni

if [ $(uname) = 'Darwin' ]; then
  if xcrun -f clang &> /dev/null; then
    CC=$(xcrun -f clang)
  else
    CC=$(which clang)
  fi
  if xcrun -f clang++ &> /dev/null; then
    CXX=$(xcrun -f clang++)
  else
    CXX=$(which clang++)
  fi

  XCODE_PATH=$(xcode-select --print-path)

  $CC -arch arm64 -isysroot $XCODE_PATH/Platforms/iPhoneOS.platform/Developer/SDKs/iPhoneOS$IOS_SDK_VERSION.sdk -I$BASE/../../vm/core/include/ -o $BASE/target/jni/StaticJNITest-arm64.o -c $BASE/src/test/native/StaticJNITest.c
  $CC -arch armv7 -isysroot $XCODE_PATH/Platforms/iPhoneOS.platform/Developer/SDKs/iPhoneOS$IOS_SDK_VERSION.sdk -I$BASE/../../vm/core/include/ -o $BASE/target/jni/StaticJNITest-thumbv7.o -c $BASE/src/test/native/StaticJNITest.c
  $CC -arch i386 -isysroot $XCODE_PATH/Platforms/MacOSX.platform/Developer/SDKs/MacOSX$MACOSX_SDK_VERSION.sdk -I$BASE/../../vm/core/include/ -o $BASE/target/jni/StaticJNITest-x86.o -c $BASE/src/test/native/StaticJNITest.c
  $CC -arch x86_64 -isysroot $XCODE_PATH/Platforms/MacOSX.platform/Developer/SDKs/MacOSX$MACOSX_SDK_VERSION.sdk -I$BASE/../../vm/core/include/ -o $BASE/target/jni/StaticJNITest-x86_64.o -c $BASE/src/test/native/StaticJNITest.c
  ar rcs $BASE/target/jni/libStaticJNITest-arm64.a $BASE/target/jni/StaticJNITest-arm64.o
  ar rcs $BASE/target/jni/libStaticJNITest-thumbv7.a $BASE/target/jni/StaticJNITest-thumbv7.o
  ar rcs $BASE/target/jni/libStaticJNITest-x86.a $BASE/target/jni/StaticJNITest-x86.o
  ar rcs $BASE/target/jni/libStaticJNITest-x86_64.a $BASE/target/jni/StaticJNITest-x86_64.o
  lipo $BASE/target/jni/libStaticJNITest-*.a -create -output $BASE/target/jni/libStaticJNITest-ios.a
  lipo $BASE/target/jni/libStaticJNITest-x86.a $BASE/target/jni/libStaticJNITest-x86_64.a -create -output $BASE/target/jni/libStaticJNITest-macosx.a
  cp $BASE/target/jni/libStaticJNITest-ios.a $BASE/src/test/resources/META-INF/robovm/ios/libStaticJNITest.a
  cp $BASE/target/jni/libStaticJNITest-macosx.a $BASE/src/test/resources/META-INF/robovm/macosx/libStaticJNITest.a

  $CC -arch arm64 -isysroot $XCODE_PATH/Platforms/iPhoneOS.platform/Developer/SDKs/iPhoneOS$IOS_SDK_VERSION.sdk -I$BASE/../../vm/core/include/ -shared -o $BASE/target/jni/libDynamicJNITest-arm64.dylib $BASE/src/test/native/DynamicJNITest.c
  $CC -arch armv7 -isysroot $XCODE_PATH/Platforms/iPhoneOS.platform/Developer/SDKs/iPhoneOS$IOS_SDK_VERSION.sdk -I$BASE/../../vm/core/include/ -shared -o $BASE/target/jni/libDynamicJNITest-thumbv7.dylib $BASE/src/test/native/DynamicJNITest.c
  $CC -arch i386 -isysroot $XCODE_PATH/Platforms/MacOSX.platform/Developer/SDKs/MacOSX$MACOSX_SDK_VERSION.sdk -I$BASE/../../vm/core/include/ -shared -o $BASE/target/jni/libDynamicJNITest-x86.dylib $BASE/src/test/native/DynamicJNITest.c
  $CC -arch x86_64 -isysroot $XCODE_PATH/Platforms/MacOSX.platform/Developer/SDKs/MacOSX$MACOSX_SDK_VERSION.sdk -I$BASE/../../vm/core/include/ -shared -o $BASE/target/jni/libDynamicJNITest-x86_64.dylib $BASE/src/test/native/DynamicJNITest.c
  lipo $BASE/target/jni/libDynamicJNITest-*.dylib -create -output $BASE/target/jni/libDynamicJNITest-ios.dylib
  lipo $BASE/target/jni/libDynamicJNITest-x86.dylib $BASE/target/jni/libDynamicJNITest-x86_64.dylib -create -output $BASE/target/jni/libDynamicJNITest-macosx.dylib
  cp $BASE/target/jni/libDynamicJNITest-ios.dylib $BASE/src/test/resources/
  cp $BASE/target/jni/libDynamicJNITest-macosx.dylib $BASE/src/test/resources/

else
  CC=$(which gcc)
  CXX=$(which g++)

  $CC -m32 -fPIC -I$BASE/../../vm/core/include/ -o $BASE/target/jni/StaticJNITest-linux-x86.o -c $BASE/src/test/native/StaticJNITest.c
  $CC -m64 -fPIC -I$BASE/../../vm/core/include/ -o $BASE/target/jni/StaticJNITest-linux-x86_64.o -c $BASE/src/test/native/StaticJNITest.c
  ar rcs $BASE/target/jni/libStaticJNITest-linux-x86.a $BASE/target/jni/StaticJNITest-linux-x86.o
  ar rcs $BASE/target/jni/libStaticJNITest-linux-x86_64.a $BASE/target/jni/StaticJNITest-linux-x86_64.o
  mkdir -p $BASE/src/test/resources/META-INF/robovm/linux/x86
  mkdir -p $BASE/src/test/resources/META-INF/robovm/linux/x86_64
  cp $BASE/target/jni/libStaticJNITest-linux-x86.a $BASE/src/test/resources/META-INF/robovm/linux/x86/libStaticJNITest.a
  cp $BASE/target/jni/libStaticJNITest-linux-x86_64.a $BASE/src/test/resources/META-INF/robovm/linux/x86_64/libStaticJNITest.a

  $CC -m32 -fPIC -I$BASE/../../vm/core/include/ -shared -o $BASE/target/jni/libDynamicJNITest-linux-x86.so $BASE/src/test/native/DynamicJNITest.c
  $CC -m64 -fPIC -I$BASE/../../vm/core/include/ -shared -o $BASE/target/jni/libDynamicJNITest-linux-x86_64.so $BASE/src/test/native/DynamicJNITest.c
  cp $BASE/target/jni/libDynamicJNITest-linux-x86.so $BASE/src/test/resources/
  cp $BASE/target/jni/libDynamicJNITest-linux-x86_64.so $BASE/src/test/resources/

fi
