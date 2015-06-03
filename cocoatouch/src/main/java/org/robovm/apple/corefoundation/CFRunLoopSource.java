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
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreservices.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFRunLoopSource/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFRunLoopSourcePtr extends Ptr<CFRunLoopSource, CFRunLoopSourcePtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFRunLoopSource.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFRunLoopSource() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public static CFRunLoopSource create(@MachineSizedSInt long order, CFRunLoopSourceContext context) {
        return create(null, order, context);
    }

    public CFRunLoopSourceContext getContext() {
        CFRunLoopSourceContext.CFRunLoopSourceContextPtr ptr = new CFRunLoopSourceContext.CFRunLoopSourceContextPtr();
        getContext(ptr);
        return ptr.get();
    }
    /*<methods>*/
    @Bridge(symbol="CFRunLoopSourceGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFRunLoopSourceCreate", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFRunLoopSource create(CFAllocator allocator, @MachineSizedSInt long order, CFRunLoopSourceContext context);
    @Bridge(symbol="CFRunLoopSourceGetOrder", optional=true)
    public native @MachineSizedSInt long getOrder();
    @Bridge(symbol="CFRunLoopSourceInvalidate", optional=true)
    public native void invalidate();
    @Bridge(symbol="CFRunLoopSourceIsValid", optional=true)
    public native boolean isValid();
    @Bridge(symbol="CFRunLoopSourceGetContext", optional=true)
    private native void getContext(CFRunLoopSourceContext.CFRunLoopSourceContextPtr context);
    @Bridge(symbol="CFRunLoopSourceSignal", optional=true)
    public native void signal();
    /*</methods>*/
}
