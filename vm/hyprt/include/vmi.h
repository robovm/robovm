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

/**
 * @file
 * @ingroup VMInterface
 * @brief VM interface specification
 */

#if !defined(vmi_h)
#define vmi_h
#define USING_VMI
#if defined(__cplusplus)
extern "C"
{
#endif
#include "jni.h"
#include "hyport.h"
#include "hyvmls.h"

#ifndef HY_ZIP_API
#include "zipsup.h"
#endif /* ! HY_ZIP_API */
/**
 * @enum vmiError
 * Enumeration of all possible return codes from VM interface functions
 */
  typedef enum
  {
    VMI_ERROR_NONE = 0,                 /**< Success */
    VMI_ERROR_UNKNOWN = 1,              /**< Unknown error */
    VMI_ERROR_UNIMPLEMENTED = 2,        /**< Function has not been implemented */
    VMI_ERROR_UNSUPPORTED_VERSION = 3,  /**< The requested VM interface version is not supported */
    VMI_ERROR_OUT_OF_MEMORY = 4,        /**< Not enough memory was available to complete the request */
    VMI_ERROR_ILLEGAL_ARG = 5,          /**< An attempt to set illegal value 
                                        (e.g. NULL value is not allowed in system properties) */
    VMI_ERROR_READ_ONLY = 6,            /**< An attempt was made to modify a read-only item */
    vmiErrorEnsureWideEnum = 0x1000000  /* ensure 4-byte enum */
  } vmiError;

/**
 * @enum vmiVersion
 * VM interface version identifier
 */
  typedef enum
  {
    VMI_VERSION_UNKNOWN = 0x00000000,     /**< Unknown VMInterface version */
    VMI_VERSION_1_0 = 0x00010000,         /**< VMInterface version 1.0 */
#ifdef HY_ZIP_API
    VMI_VERSION_2_0 = 0x00020000,         /**< VMInterface version 2.0 */
#endif /* HY_ZIP_API */
    vmiVersionEnsureWideEnum = 0x1000000  /* ensure 4-byte enum */
  } vmiVersion;

/**
 * @typedef vmiSystemPropertyIterator
 * Specification of the iterator function to provide to IterateSystemProperties
 *
 * @code void iterator(char* key, char* value, void* userData); @endcode
 */
  typedef void (JNICALL * vmiSystemPropertyIterator) (char *key, char *value,
                                                      void *userData);

#ifdef HY_ZIP_API
  struct HyZipFunctionTable;
#endif /* HY_ZIP_API */
  struct VMInterface_;
  struct VMInterfaceFunctions_;

/**
 * @typedef VMInterface
 * The VM interface structure. Points to the @ref VMInterfaceFunctions_ "VM interface function table". 
 * Implementations will likely choose to store opaque data off this structure.
 */
  typedef const struct VMInterfaceFunctions_ *VMInterface;

/**
 * @struct VMInterfaceFunctions_ 
 * The VM interface function table.
 * 
 * Example usage:
 * @code
 * JavaVM* vm = (*vmi)->GetJavaVM(vmi);
 * @endcode
 */
  struct VMInterfaceFunctions_
  {
    vmiError (JNICALL * CheckVersion) (VMInterface * vmi,
      vmiVersion * version);
    JavaVM *(JNICALL * GetJavaVM) (VMInterface * vmi);
    HyPortLibrary *(JNICALL * GetPortLibrary) (VMInterface * vmi);
    HyVMLSFunctionTable *(JNICALL * GetVMLSFunctions) (VMInterface * vmi);
#ifndef HY_ZIP_API

    HyZipCachePool *(JNICALL * GetZipCachePool) (VMInterface * vmi);
#else /* HY_ZIP_API */
    struct VMIZipFunctionTable *(JNICALL * GetZipFunctions) (VMInterface * vmi);
#endif /* HY_ZIP_API */
    JavaVMInitArgs *(JNICALL * GetInitArgs) (VMInterface * vmi);
    vmiError (JNICALL * GetSystemProperty) (VMInterface * vmi, char *key,
      char **valuePtr);
    vmiError (JNICALL * SetSystemProperty) (VMInterface * vmi, char *key,
      char *value);
    vmiError (JNICALL * CountSystemProperties) (VMInterface * vmi,
      int *countPtr);
    vmiError (JNICALL * IterateSystemProperties) (VMInterface * vmi,
      vmiSystemPropertyIterator
      iterator, void *userData);
  };

  /**
  * Extract the VM interface from a JNIEnv
  */
  VMInterface *JNICALL VMI_GetVMIFromJNIEnv (JNIEnv * env);

  /**
  * Extract the VM interface from a JNI JavaVM
  */
  VMInterface *JNICALL VMI_GetVMIFromJavaVM (JavaVM * vm);

/** 
 *  Convenience macros for acquiring a VMInterface
 */
#define VMI_ACCESS_FROM_ENV(env) VMInterface* privateVMI = VMI_GetVMIFromJNIEnv(env)
#define VMI_ACCESS_FROM_JAVAVM(javaVM) VMInterface* privateVMI = VMI_GetVMIFromJavaVM(javaVM)
#define VMI privateVMI

/**
 * @fn VMInterfaceFunctions_::CheckVersion(VMInterface * vmi, vmiVersion * version)
 * Check the version of the VM interface
 *
 * @code vmiError JNICALL CheckVersion(VMInterface* vmi, vmiVersion* version); @endcode
 *
 * @param[in] vmi  The VM interface pointer
 * @param[in,out] version  Pass in the version to check, or @ref VMI_VERSION_UNKNOWN. Returns the current version.
 *
 * @return a @ref vmiError "VMI error code"
 *
 * @note The CheckVersion function allows a class library to verify that the VM provides the required interface functions.
 * If the version requested is @ref VMI_VERSION_UNKNOWN, then the function will reply with the current version and not return an error.
 * If a specific version is passed, it will be compatibility checked against the current, and @ref VMI_ERROR_UNSUPPORTED_VERSION
 * may be returned.
 */
  vmiError JNICALL CheckVersion (VMInterface * vmi, vmiVersion * version);

/**
 * @fn VMInterfaceFunctions_::GetJavaVM(VMInterface * vmi)
 * Return the JNI JavaVM associated with the VM interface
 *
 * @code JavaVM* JNICALL GetJavaVM(VMInterface* vmi); @endcode
 *
 * @param[in] vmi  The VM interface pointer
 *
 * @return a JavaVM pointer
 */
  JavaVM *JNICALL GetJavaVM (VMInterface * vmi);

/**
 * @fn VMInterfaceFunctions_::GetPortLibrary(VMInterface * vmi)
 * Return a pointer to an initialized HyPortLibrary structure.
 * @code HyPortLibrary* JNICALL GetPortLibrary(VMInterface* vmi); @endcode
 *
 * The @ref hyport.h "port library" is a table of functions that implement useful platform specific
 * capability. For example, file and socket manipulation, memory management, etc.
 * It is the responsibility of the VM to create the port library.
 *
 * @param[in] vmi  The VM interface pointer
 *
 * @return the HyPortLibrary associated with the VMI
 * 
 * @see hyport.c
 */
  HyPortLibrary *JNICALL GetPortLibrary (VMInterface * vmi);

/**
 * @fn VMInterfaceFunctions_::GetVMLSFunctions(VMInterface * vmi)
 * Return a pointer to a HyVMLSFunctionTable. This is a table of functions for allocating,
 * freeing, getting, and setting thread local storage.
 *
 * @code HyVMLSFunctionTable* JNICALL GetVMLSFunctions(VMInterface* vmi); @endcode
 *
 * @param[in] vmi  The VM interface pointer
 *
 * @return the VM local storage function table
 */
  HyVMLSFunctionTable *JNICALL GetVMLSFunctions (VMInterface * vmi);

/**
#ifndef HY_ZIP_API
 * @fn VMInterfaceFunctions_::GetZipCachePool(VMInterface * vmi)
 * Return a pointer to the HyZipCachePool structure used by the VM. It is the
 * responsibility of the vm to allocate the pool using zipCachePool_new().
#else
   * @fn VMInterfaceFunctions_::GetZipFunctions
   * Return a pointer to a HyZipFunctionTable. This is a table of functions for managing zip files.
#endif
 *
#ifndef HY_ZIP_API
 * @code HyZipCachePool* JNICALL GetZipCachePool(VMInterface* vmi); @endcode
#else
   * @code HyZipFunctionTable* JNICALL GetZipFunctions(VMInterface* vmi); @endcode
#endif
 * 
 * @param[in] vmi  The VM interface pointer
 *
#ifndef HY_ZIP_API
 * @return a HyZipCachePool pointer
#else
   * @return a HyZipFunctionTable pointer
#endif
 */
#ifndef HY_ZIP_API
  HyZipCachePool *JNICALL GetZipCachePool (VMInterface * vmi);
#else /* HY_ZIP_API */
  struct HyZipFunctionTable* JNICALL 
  GetZipFunctions(VMInterface* vmi);
#endif /* HY_ZIP_API */

/**
 * @fn VMInterfaceFunctions_::GetInitArgs(VMInterface * vmi)
 * Return a pointer to a JavaVMInitArgs structure as defined by the 1.2 JNI
 * specification. This structure contains the arguments used to invoke the vm.
 *
 * @code JavaVMInitArgs* JNICALL GetInitArgs(VMInterface* vmi); @endcode
 *
 * @param[in] vmi  The VM interface pointer
 *
 * @return the VM invocation arguments
 */
  JavaVMInitArgs *JNICALL GetInitArgs (VMInterface * vmi);


/**
 * Name of the property holding bootclasspath string.
 */
#define BOOTCLASSPATH_PROPERTY "org.apache.harmony.boot.class.path"

 /**
  * @fn VMInterfaceFunctions_::GetSystemProperty(VMInterface * vmi, char *key, char **valuePtr)
  * Retrieve the value of a VM system property. 
  * 
  * @note The returned string is owned by the VM, and should not be freed.
  */
  vmiError JNICALL GetSystemProperty (VMInterface * vmi, char *key,
    char **valuePtr);

/**
 * @fn VMInterfaceFunctions_::SetSystemProperty(VMInterface * vmi, char *key, char *value)
 * Override the value of a VM system property
 *
 * @code vmiError JNICALL SetSystemProperty(VMInterface* vmi, char* key, char* value); @endcode
 * 
 * @param[in] vmi  The VM interface pointer
 * @param[in] key  The system property to override
 * @param[in] value  The value of the system property
 *
 * @return a @ref vmiError "VMI error code"
 *
 * @note Only existing properties can be overridden. New properties cannot be added by this mechanism.
 *
 * @note See  GetSystemProperty() for the list of properties that must be defined
 * by the vm.
 */
  vmiError JNICALL SetSystemProperty (VMInterface * vmi, char *key,
                                      char *value);

/**
 * @fn VMInterfaceFunctions_::CountSystemProperties(VMInterface * vmi, int *countPtr)
 * Return the number of VM system properties
 * 
 * @code vmiError JNICALL CountSystemProperties(VMInterface* vmi, int* countPtr); @endcode
 *
 * @param[in] vmi  The VM interface pointer
 * @param[out] countPtr The location to store the number of system properties
 *
 * @return a @ref vmiError "VMI error code"
 *
 * @note See  GetSystemProperty() for the list of properties that must be defined
 * by the vm.
 */
  vmiError JNICALL CountSystemProperties (VMInterface * vmi, int *countPtr);

/**
 * @fn VMInterfaceFunctions_::IterateSystemProperties(VMInterface * vmi, vmiSystemPropertyIterator iterator, void *userData)
 * Iterate over the VM system properties calling a function.
 *
 * 
 * @param[in] vmi  The VM interface pointer
 * @param[in] iterator  The iterator function to call with each property
 * @param[in] userData  Opaque data to pass to the iterator function
 *
 * @return a @ref vmiError "VMI error code"
 *
 * @note The returned strings are owned by the VM, and should not be freed.
 *
 * @note See  GetSystemProperty() for the list of properties that must be defined
 * by the vm.
 */
  vmiError JNICALL IterateSystemProperties (VMInterface * vmi,
                                            vmiSystemPropertyIterator
                                            iterator, void *userData);

#if defined(__cplusplus)
}
#endif
#endif  /* vmi_h */
