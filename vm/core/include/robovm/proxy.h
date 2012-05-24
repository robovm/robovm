#ifndef ROBOVM_PROXY_H
#define ROBOVM_PROXY_H

typedef void (*ProxyHandler)(Env*, Object*, ProxyMethod*, jvalue*, jvalue*);

extern Class* rvmProxyCreateProxyClass(Env* env, Class* superclass, ClassLoader* classLoader, char* className, jint interfacesCount, Class** interfaces, 
	jint instanceDataSize, jint instanceDataOffset, ProxyHandler handler);

#endif

