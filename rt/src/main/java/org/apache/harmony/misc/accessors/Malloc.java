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

package org.apache.harmony.misc.accessors;

/**
 * Utility class which is used to host the native memory access functions
 */
class Malloc {
    static native long malloc(long size);

    static native long realloc(long addr, long size);
    
    static native void free(long addr);
    
    static native long memcpy(long dst, long src, long size);

    static native int memcmp(long addr, long addr1, long size);

    static native long memset(long addr, int fill, long size);

    static native long memmove(long dst, long src, long size);

    static native long strncpy(long dst, long src, long size);
    
    static native int getPointerSize();

    static native int getCLongSize();
}
