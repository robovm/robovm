/*
 * Copyright (C) 2012 Trillian Mobile AB
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
#ifndef ROBOVM_ACCESS_H
#define ROBOVM_ACCESS_H

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

#define IS_PUBLIC(access) (access & ACC_PUBLIC)
#define IS_PRIVATE(access) (access & ACC_PRIVATE)
#define IS_PROTECTED(access) (access & ACC_PROTECTED)
#define IS_STATIC(access) (access & ACC_STATIC)
#define IS_FINAL(access) (access & ACC_FINAL)
#define IS_SUPER(access) (access & ACC_SUPER)
#define IS_SYNCHRONIZED(access) (access & ACC_SYNCHRONIZED)
#define IS_VOLATILE(access) (access & ACC_VOLATILE)
#define IS_BRIDGE(access) (access & ACC_BRIDGE)
#define IS_VARARGS(access) (access & ACC_VARARGS)
#define IS_TRANSIENT(access) (access & ACC_TRANSIENT)
#define IS_NATIVE(access) (access & ACC_NATIVE)
#define IS_INTERFACE(access) (access & ACC_INTERFACE)
#define IS_ABSTRACT(access) (access & ACC_ABSTRACT)
#define IS_STRICT(access) (access & ACC_STRICT)
#define IS_SYNTHETIC(access) (access & ACC_SYNTHETIC)
#define IS_ANNOTATION(access) (access & ACC_ANNOTATION)
#define IS_ENUM(access) (access & ACC_ENUM)
#define IS_PACKAGE_PRIVATE(access) (!IS_PRIVATE(access) && !IS_PROTECTED(access) && !IS_PUBLIC(access))

#endif

