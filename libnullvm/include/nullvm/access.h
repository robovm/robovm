#ifndef ACCESS_H
#define ACCESS_H

#define ACC_PUBLIC 0x0001
#define ACC_PRIVATE 0x0002
#define ACC_PROTECTED 0x0004
#define ACC_STATIC 0x0008
#define ACC_FINAL 0x0010
#define ACC_SUPER 0x0020
#define ACC_SYNCHRONIZED 0x0020
#define ACC_VOLATILE 0x0040
#define ACC_BRIDGE 0x0040
#define ACC_VARARGS 0x0080
#define ACC_TRANSIENT 0x0080
#define ACC_NATIVE 0x0100
#define ACC_INTERFACE 0x0200
#define ACC_ABSTRACT 0x0400
#define ACC_STRICT 0x0800
#define ACC_SYNTHETIC 0x1000
#define ACC_ANNOTATION 0x2000
#define ACC_ENUM 0x4000

#define IS_PRIVATE(access) (access & ACC_PRIVATE)
#define IS_PROTECTED(access) (access & ACC_PROTECTED)
#define IS_PUBLIC(access) (access & ACC_PUBLIC)
#define IS_PACKAGE_PRIVATE(access) (!IS_PRIVATE(access) && !IS_PROTECTED(access) && !IS_PUBLIC(access))
#define IS_FINAL(access) (access & ACC_FINAL)
#define IS_NATIVE(access) (access & ACC_NATIVE)
#define IS_SYNCHRONIZED(access) (access & ACC_SYNCHRONIZED)
#define IS_ABSTRACT(access) (access & ACC_ABSTRACT)

#endif

