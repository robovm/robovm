/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.rt.bro.ptr;

import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * Points to an unspecified value (<code>void *</code> in C). As in C there's no
 * way to dereference a {@link VoidPtr}. Use {@link #as(Class)} to cast it to
 * another type first.
 */
public final class VoidPtr extends Struct<VoidPtr> {

    /**
     * Pointer to {@link VoidPtr} (<code>void **</code> in C)
     */
    public static class VoidPtrPtr extends org.robovm.rt.bro.ptr.Ptr<VoidPtr, VoidPtrPtr> {}
    
    /**
     * Creates a new {@link VoidPtr} with a value of NULL.
     */
    public VoidPtr() {
    }
    
    /**
     * Hidden.
     */
    @StructMember(0)
    private native @Pointer long get();
    
    /**
     * Hidden.
     */
    @StructMember(0)
    private native void set(@Pointer long value);
}
