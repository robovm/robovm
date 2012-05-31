/*
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

#ifndef _VM_TYPES_H_
#define _VM_TYPES_H_

#include "open/platform_types.h"
#include <stddef.h>


/**
 * <code>VM_Data_Type</code>
 */

typedef
enum VM_Data_Type {
    VM_DATA_TYPE_INT8    = 'B',
    VM_DATA_TYPE_UINT8   = 'b',
    VM_DATA_TYPE_INT16   = 'S',
    VM_DATA_TYPE_UINT16  = 's',
    VM_DATA_TYPE_INT32   = 'I',
    VM_DATA_TYPE_UINT32  = 'i',
    VM_DATA_TYPE_INT64   = 'J',
    VM_DATA_TYPE_UINT64  = 'j',
    VM_DATA_TYPE_INTPTR  = 'N',
    VM_DATA_TYPE_UINTPTR = 'n',
    VM_DATA_TYPE_F8      = 'D',
    VM_DATA_TYPE_F4      = 'F',
    VM_DATA_TYPE_BOOLEAN = 'Z',
    VM_DATA_TYPE_CHAR    = 'C',
    VM_DATA_TYPE_CLASS   = 'L',
    VM_DATA_TYPE_ARRAY   = '[',
    VM_DATA_TYPE_VOID    = 'V',
    VM_DATA_TYPE_MP      = 'P',        // managed pointers
    VM_DATA_TYPE_UP      = 'p',        // unmanaged pointers
    VM_DATA_TYPE_VALUE   = 'K',
    //
    VM_DATA_TYPE_STRING  = '$',        // deprecated
    //
    VM_DATA_TYPE_INVALID = '?',
    VM_DATA_TYPE_END     = ')'         // For the iterator
} VM_Data_Type; //VM_Data_Type

typedef
enum WeakReferenceType {
    NOT_REFERENCE = 0,
    WEAK_REFERENCE,
    SOFT_REFERENCE,
    PHANTOM_REFERENCE
} WeakReferenceType;

/**
 * Handles for Various VM Structures.
 *
 * This header file is also used in pure C sources,
 * thus we use struct instead of classes.
 */
typedef struct Class *Class_Handle;
typedef struct VTable *VTable_Handle;
typedef struct Field *Field_Handle;
typedef struct Method *Method_Handle;
typedef struct Method_Signature *Method_Signature_Handle;
typedef struct TypeDesc *Type_Info_Handle;
typedef UDATA Allocation_Handle;
typedef UDATA Runtime_Type_Handle;
typedef void* NativeCodePtr;
typedef struct ClassLoader* Class_Loader_Handle;
typedef struct ManagedObject* ManagedPointer;

/**
 * Fields of these types are not directly accessible from the core VM.
 *  typedef struct ManagedObject Java_java_lang_Class;
 *  typedef ManagedObject Java_java_lang_System;
 */
typedef struct ManagedObject Java_java_lang_Throwable;
typedef struct ManagedObject Java_java_lang_Thread;
typedef struct ManagedObject Java_java_io_FileInputStream;
typedef struct ManagedObject Java_java_lang_String;


/**
 * Used for opaques accesses to managed arrays. This handle points
 * to an array in the managed heap, so handling must be careful to account
 * for the possibility of a moving GC.
 */
typedef void *Vector_Handle;

typedef void *Managed_Object_Handle;

typedef void *GC_Enumeration_Handle;

#endif //!_VM_TYPES_H_
