#!/bin/bash

lipo -create ./binaries/ios/thumbv7/libgc.a binaries/ios/x86_64/libgc.a -output ./binaries/ios/libgc-all.a
echo "created libgc-all.a"

lipo -create ./binaries/ios/thumbv7/librobovm-bclib.a binaries/ios/x86_64/librobovm-bclib.a -output ./binaries/ios/librobovm-bclib-all.a
echo "created librobovm-bclib-all.a"

lipo -create ./binaries/ios/thumbv7/librobovm-core.a binaries/ios/x86_64/librobovm-core.a -output ./binaries/ios/librobovm-core-all.a
echo "created librobovm-core-all.a"

lipo -create ./binaries/ios/thumbv7/librobovm-debug.a binaries/ios/x86_64/librobovm-debug.a -output ./binaries/ios/librobovm-debug-all.a
echo "created librobovm-debug-all.a"

lipo -create ./binaries/ios/thumbv7/librobovm-rt.a binaries/ios/x86_64/librobovm-rt.a -output ./binaries/ios/librobovm-rt-all.a
echo "created librobovm-rt-all.a"

