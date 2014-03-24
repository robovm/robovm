/*
 * Copyright (C) 2014 Trillian Mobile AB
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
package org.robovm.apple.corefoundation;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFAllocator/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFAllocatorPtr extends Ptr<CFAllocator, CFAllocatorPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFAllocator.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFAllocator() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="kCFAllocatorDefault")
    public static native CFAllocator AllocatorDefault();
    @GlobalValue(symbol="kCFAllocatorSystemDefault")
    public static native CFAllocator AllocatorSystemDefault();
    @GlobalValue(symbol="kCFAllocatorMalloc")
    public static native CFAllocator AllocatorMalloc();
    @GlobalValue(symbol="kCFAllocatorMallocZone")
    public static native CFAllocator AllocatorMallocZone();
    @GlobalValue(symbol="kCFAllocatorNull")
    public static native CFAllocator AllocatorNull();
    @GlobalValue(symbol="kCFAllocatorUseContext")
    public static native CFAllocator AllocatorUseContext();
    
    @Bridge(symbol="CFAllocatorGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFAllocatorSetDefault")
    public native void setDefault();
    @Bridge(symbol="CFAllocatorGetDefault")
    public static native CFAllocator getDefault();
    @Bridge(symbol="CFAllocatorCreate")
    public native CFAllocator create(CFAllocatorContext context);
    @Bridge(symbol="CFAllocatorAllocate")
    public native VoidPtr allocate(@MachineSizedSInt long size, @MachineSizedSInt long hint);
    @Bridge(symbol="CFAllocatorReallocate")
    public native VoidPtr reallocate(VoidPtr ptr, @MachineSizedSInt long newsize, @MachineSizedSInt long hint);
    @Bridge(symbol="CFAllocatorDeallocate")
    public native void deallocate(VoidPtr ptr);
    @Bridge(symbol="CFAllocatorGetPreferredSizeForSize")
    public native @MachineSizedSInt long getPreferredSizeForSize(@MachineSizedSInt long size, @MachineSizedSInt long hint);
    @Bridge(symbol="CFAllocatorGetContext")
    public native void getContext(CFAllocatorContext context);
    /*</methods>*/
}
