/*
 * Copyright (C) 2013-2015 RoboVM AB
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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
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
    public CFAllocatorContext getContext() {
        CFAllocatorContext.CFAllocatorContextPtr ptr = new CFAllocatorContext.CFAllocatorContextPtr();
        getContext(ptr);
        return ptr.get();
    }
    /*<methods>*/
    @GlobalValue(symbol="kCFAllocatorDefault", optional=true)
    public static native CFAllocator getDefaultAllocator();
    @GlobalValue(symbol="kCFAllocatorSystemDefault", optional=true)
    public static native CFAllocator getSystemDefaultAllocator();
    @GlobalValue(symbol="kCFAllocatorMalloc", optional=true)
    public static native CFAllocator getMallocAllocator();
    @GlobalValue(symbol="kCFAllocatorMallocZone", optional=true)
    public static native CFAllocator getMallocZoneAllocator();
    @GlobalValue(symbol="kCFAllocatorNull", optional=true)
    public static native CFAllocator getNullAllocator();
    @GlobalValue(symbol="kCFAllocatorUseContext", optional=true)
    public static native CFAllocator getUseContextAllocator();
    
    @Bridge(symbol="CFAllocatorGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFAllocatorSetDefault", optional=true)
    public static native void setDefault(CFAllocator allocator);
    @Bridge(symbol="CFAllocatorGetDefault", optional=true)
    public static native CFAllocator getDefault();
    @Bridge(symbol="CFAllocatorCreate", optional=true)
    public native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFAllocator create(CFAllocatorContext context);
    @Bridge(symbol="CFAllocatorAllocate", optional=true)
    public native VoidPtr allocate(@MachineSizedSInt long size, @MachineSizedSInt long hint);
    @Bridge(symbol="CFAllocatorReallocate", optional=true)
    public native VoidPtr reallocate(VoidPtr ptr, @MachineSizedSInt long newsize, @MachineSizedSInt long hint);
    @Bridge(symbol="CFAllocatorDeallocate", optional=true)
    public native void deallocate(VoidPtr ptr);
    @Bridge(symbol="CFAllocatorGetPreferredSizeForSize", optional=true)
    public native @MachineSizedSInt long getPreferredSizeForSize(@MachineSizedSInt long size, @MachineSizedSInt long hint);
    @Bridge(symbol="CFAllocatorGetContext", optional=true)
    private native void getContext(CFAllocatorContext.CFAllocatorContextPtr context);
    /*</methods>*/
}
