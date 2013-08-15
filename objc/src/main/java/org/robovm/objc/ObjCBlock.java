/*
 * Copyright (C) 2013 Trillian AB
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
package org.robovm.objc;

import java.lang.reflect.Method;
import java.util.IdentityHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.robovm.objc.block.VoidBooleanBlock;
import org.robovm.rt.VM;
import org.robovm.rt.bro.Struct;
import org.robovm.rt.bro.annotation.Callback;
import org.robovm.rt.bro.annotation.Pointer;
import org.robovm.rt.bro.annotation.StructMember;

/**
 * {@link Struct} mapping the {@code Block_literal} struct used internally by
 * the Objective-C runtime. This class is only for internal use. See the
 * source code of the block interfaces in the {@link org.robovm.objc.block}
 * package (e.g. {@link VoidBooleanBlock} for examples on how to marshal
 * Objective-C block types.
 * The <a href="http://clang.llvm.org/docs/Block-ABI-Apple.html">Block ABI
 * documentation</a> has more information on how blocks are implemented in 
 * Objective-C.
 */
public final class ObjCBlock extends Struct<ObjCBlock> {
    @StructMember(0)
    public native @Pointer long isa();
    
    @StructMember(0)
    public native ObjCBlock isa(@Pointer long isa);
    
    @StructMember(1)
    public native int flags();
    
    @StructMember(1)
    public native ObjCBlock flags(int flags);

    @StructMember(2)
    public native int reserved();
    
    @StructMember(2)
    public native ObjCBlock reserved(int reserved);

    @StructMember(3)
    public native @Pointer long invoke();
    
    @StructMember(3)
    public native ObjCBlock invoke(@Pointer long invoke);

    @StructMember(4)
    public native Descriptor descriptor();
    
    @StructMember(4)
    public native ObjCBlock descriptor(Descriptor descriptor);
    
    @StructMember(5)
    public native @Pointer long object_addr();
    
    @StructMember(5)
    public native ObjCBlock object_addr(@Pointer long object_addr);
    
    @StructMember(6)
    public native @Pointer long wrapper_addr();
    
    @StructMember(6)
    public native ObjCBlock wrapper_addr(@Pointer long wrapper_addr);
    
    public Object object() {
        return VM.castAddressToObject(object_addr());
    }

    public ObjCBlock object(Object o) {
        object_addr(VM.getObjectAddress(o));
        return this;
    }

    public Wrapper wrapper() {
        return (Wrapper) VM.castAddressToObject(wrapper_addr());
    }

    public ObjCBlock wrapper(Wrapper o) {
        wrapper_addr(VM.getObjectAddress(o));
        return this;
    }
    
    public static final class Descriptor extends Struct<Descriptor> {
        @StructMember(0)
        public native int reserved();
        
        @StructMember(0)
        public native Descriptor reserved(int reserved);

        @StructMember(1)
        public native int literal_size();
        
        @StructMember(1)
        public native Descriptor literal_size(int literal_size);

        @StructMember(2)
        public native @Pointer long copy_helper();
        
        @StructMember(2)
        public native Descriptor copy_helper(@Pointer long copy_helper);
        
        @StructMember(3)
        public native @Pointer long dispose_helper();
        
        @StructMember(3)
        public native Descriptor dispose_helper(@Pointer long dispose_helper);
    }
    
    public static final class Wrapper {
        private static final Descriptor DESCRIPTOR;
        private static final long NSStackBlock;
        private static final int BLOCK_HAS_COPY_DISPOSE = (1 << 25);
        private static final int BLOCK_HAS_STRET = (1 << 29);
        private static final int BLOCK_HAS_SIGNATURE = (1 << 30);
        
        static {
            try {
                long copyImpl = VM.getCallbackMethodImpl(
                        Wrapper.class.getDeclaredMethod("copy", ObjCBlock.class, ObjCBlock.class));
                long disposeImpl = VM.getCallbackMethodImpl(
                        Wrapper.class.getDeclaredMethod("dispose", ObjCBlock.class));
                DESCRIPTOR = new Descriptor()
                    .literal_size(ObjCBlock.sizeOf())
                    .copy_helper(copyImpl)
                    .dispose_helper(disposeImpl);
            } catch (Exception e) {
                throw new Error(e);
            }
            NSStackBlock = 
                    ObjCRuntime.objc_getClass(VM.getStringUTFChars("__NSStackBlock__"));
            if (NSStackBlock == 0L) {
                throw new Error("Objective-C class __NSStackBlock__ not found");
            }
        }
        
        private final long callbackImpl;
        private final int flags;
        private final IdentityHashMap<Object, AtomicInteger> refCounts = 
                new IdentityHashMap<Object, AtomicInteger>();
        
        public Wrapper(Class<?> callbacks) {
            Method method = null;
            for (Method m : callbacks.getDeclaredMethods()) {
                if (m.getAnnotation(Callback.class) != null) {
                    if (method != null) {
                        throw new IllegalArgumentException("Several @Callback " 
                                + "methods found in class " + callbacks.getName());
                    }
                    method = m;
                }
            }
            if (method == null) {
                throw new IllegalArgumentException("No @Callback method found " 
                            + "in class " + callbacks.getName());
            }
            callbackImpl = VM.getCallbackMethodImpl(method);
            
            int flags = BLOCK_HAS_COPY_DISPOSE | BLOCK_HAS_SIGNATURE;
            if (ObjCRuntime.isStret(method)) {
                flags |= BLOCK_HAS_STRET;
            }
            this.flags = flags;
        }
        
        public Object toObject(long handle) {
            ObjCBlock block = Struct.toStruct(ObjCBlock.class, handle);
            if (block == null) {
                return null;
            }
            return block.object();
        }
        
        public long toNative(Object o) {
            if (o == null) {
                return 0L;
            }
            // Create an Objective-C block struct which looks like it was
            // allocated on the stack. The struct will be GC reachable during
            // the duration of the call to the Objective-C side. The Objective-C
            // side will copy the block if it needs to call it later which will
            // trigger a call to the copy() method below. The GC will reclaim 
            // the struct memory area some time after the call has finished but
            // the Java block instance (e.g. VoidBlock) will be retained on 
            // every copy and only GCed after all copies have been deallocated.
            ObjCBlock block = new ObjCBlock()
                .isa(NSStackBlock)
                .flags(flags)
                .invoke(callbackImpl)
                .descriptor(DESCRIPTOR)
                .object(o)
                .wrapper(this);
            return block.getHandle();
        }
        
        @SuppressWarnings("unused")
        @Callback
        private static void copy(ObjCBlock dst, ObjCBlock src) {
            // Called from the Objective-C side every time the block is copied.
            // Increase the ref count of the block object on the Java side to
            // prevent it from being GCed.
            Object blockObj = src.object();
            Wrapper wrapper = src.wrapper();
            IdentityHashMap<Object, AtomicInteger> refCounts = wrapper.refCounts;
            synchronized (refCounts) {
                AtomicInteger count = refCounts.get(blockObj);
                if (count == null) {
                    count = new AtomicInteger(0);
                    refCounts.put(blockObj, count);
                }
                count.incrementAndGet();
            }
        }
        
        @SuppressWarnings("unused")
        @Callback
        private static void dispose(ObjCBlock block) {
            // Called from the Objective-C side when a previously copied block
            // is released and about to be deallocated. Decrease the ref count
            // of the object on the Java side.
            Object blockObj = block.object();
            Wrapper wrapper = block.wrapper();
            IdentityHashMap<Object, AtomicInteger> refCounts = wrapper.refCounts;
            synchronized (refCounts) {
                if (refCounts.get(blockObj).decrementAndGet() == 0) {
                    refCounts.remove(blockObj);
                }
            }
        }
    }
}
