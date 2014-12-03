# RoboVM

Please see [robovm.com](http://www.robovm.com) for downloads, build and
installation instructions and other documentation.

# RoboVM static library

This fork changes the library structure ob robovm and adds an additional bclib library that does not call the "main" method and therefore can be used to be link with for creating a static iOS library.

## Linker flags to be added for the app using the static iOS library
-ObjC -all_load -L$(PATH_TO_ROBOVM_HOME)}/vm/binaries/ios/x86_64 -lrobovm-bclib -lgc -lrobovm-rt -lrobovm-core

## Linked framewoks for the app
- libsqlite3.dylib
- libstdc++.6.dylib
- libc++.dylib

## Main class
You need to add an empty class org.robovm.staticlibrary.RoboVmMain to your java project/classpath and use that dummy for the robovm compiler as main class.

## Starting the RoboVM runtime
Add the rvmlibhelper sources to the xcode project and start the RVM with [RoboVMFramework startRVM:@"-App_Path-"]



