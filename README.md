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

## Calling Java objects from Objc/C++

```cpp

// C++ Header

class ClientCpp
{
public:
	ClientCpp(); // wrapper class for JavaClient
	~ClientCpp();
    
    void* getHandle(); // Will be casted to real objc object

	// Wrapper for method implemented in Java
    const char * clientMethod(const char * url, const char * payload, unsigned long timeout);
    
    
private:
    struct ClientCppImpl;
    ClientCppImpl *impl;
};



// C++ Implementation


struct ClientCpp::ClientCppImpl
{
    Env* rvmEnv_;
    Object *javaClient_; // The reference to the "java" object
    jlong handle_; // If robovm creates a bound objc object we can get the handle
};


static Class *clientClass = NULL;

ClientCpp::ClientCpp() : impl(new ClientCppImpl())
{
    printf(">> ClientCpp()");
    
    initRvmEnv(&impl->rvmEnv_);

    if (clientClass == NULL) {
        clientClass = findRvmClazz(&impl->rvmEnv_, "com/my/package/JavaClient");
    }
    
    Method *constructor = rvmGetMethod(impl->rvmEnv_, clientClass, "<init>", "()V");

    impl->javaClient_ = rvmNewObject(impl->rvmEnv_, clientClass, constructor);
    impl->handle_ = getRvmHandle(&impl->rvmEnv_, impl->javaClient_, clientClass);

    printf("<< ClientCpp()");
}

ClientCpp::~ClientCpp()
{
    std::cout << ">> ~ClientCpp()";
    impl->handle_ = NULL;
    impl->javaClient_ = NULL;
    impl->rvmEnv_ = NULL;
    delete impl;
    impl = NULL;
    clientClass = NULL;
}

void* ClientCpp::getHandle()
{
    return (void *)impl->handle_;
}

const char * ClientCpp::clientMethod(const char * url, const char * payload, unsigned long timeout)
{
    printf("ClientCpp(): clientMethod: (%s), (%s), (%ld)", url, payload, timeout);
    
    Method *clientMethod = rvmGetMethod(impl->rvmEnv_, clientClass, "clientMethod", "(Ljava/lang/String;Ljava/lang/String;J)Ljava/lang/String;");
    Object *urlJava = rvmNewStringUTF(impl->rvmEnv_, url, -1);
    Object * payloadJava = rvmNewStringUTF(impl->rvmEnv_, payload, -1);
    jlong timeoutJava = timeout;
    Object * result = rvmCallObjectInstanceMethod(impl->rvmEnv_, impl->javaClient_, clientMethod, urlJava, payloadJava, timeoutJava);
    
    printf("ClientCpp() :  clientMethod called\n");
    
    return rvmGetStringUTFChars(impl->rvmEnv_, result);
}

```
