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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMachPort/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMachPortPtr extends Ptr<CFMachPort, CFMachPortPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMachPort.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFMachPort() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFMachPortGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFMachPortCreate")
    public static native CFMachPort create(CFAllocator allocator, FunctionPtr callout, CFMachPortContext context, BytePtr shouldFreeInfo);
    @Bridge(symbol="CFMachPortCreateWithPort")
    public static native CFMachPort createWithPort(CFAllocator allocator, int portNum, FunctionPtr callout, CFMachPortContext context, BytePtr shouldFreeInfo);
    @Bridge(symbol="CFMachPortGetPort")
    public native int getPort();
    @Bridge(symbol="CFMachPortGetContext")
    public native void getContext(CFMachPortContext context);
    @Bridge(symbol="CFMachPortInvalidate")
    public native void invalidate();
    @Bridge(symbol="CFMachPortIsValid")
    public native boolean isValid();
    @Bridge(symbol="CFMachPortGetInvalidationCallBack")
    public native FunctionPtr getInvalidationCallBack();
    @Bridge(symbol="CFMachPortSetInvalidationCallBack")
    public native void setInvalidationCallBack(FunctionPtr callout);
    @Bridge(symbol="CFMachPortCreateRunLoopSource")
    public static native CFRunLoopSource createRunLoopSource(CFAllocator allocator, CFMachPort port, @MachineSizedSInt long order);
    /*</methods>*/
}
