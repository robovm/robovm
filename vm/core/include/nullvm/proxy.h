#ifndef NULLVM_PROXY_H
#define NULLVM_PROXY_H

typedef void (*ProxyHandler)(Env*, Object*, ProxyMethod*, jvalue*, jvalue*);

extern Class* nvmProxyCreateProxyClass(Env* env, Class* superclass, ClassLoader* classLoader, char* className, jint interfacesCount, Class** interfaces, jint instanceDataSize, ProxyHandler handler);

#endif

