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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFMessagePort/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFMessagePortPtr extends Ptr<CFMessagePort, CFMessagePortPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFMessagePort.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFMessagePort() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFMessagePortGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFMessagePortCreateLocal", optional=true)
    public static native CFMessagePort createLocal(CFAllocator allocator, CFString name, FunctionPtr callout, CFMessagePortContext context, BytePtr shouldFreeInfo);
    @Bridge(symbol="CFMessagePortCreateRemote", optional=true)
    public static native CFMessagePort createRemote(CFAllocator allocator, CFString name);
    @Bridge(symbol="CFMessagePortIsRemote", optional=true)
    public native boolean isRemote();
    @Bridge(symbol="CFMessagePortGetName", optional=true)
    public native CFString getName();
    @Bridge(symbol="CFMessagePortSetName", optional=true)
    public native boolean setName(CFString newName);
    @Bridge(symbol="CFMessagePortGetContext", optional=true)
    public native void getContext(CFMessagePortContext context);
    @Bridge(symbol="CFMessagePortInvalidate", optional=true)
    public native void invalidate();
    @Bridge(symbol="CFMessagePortIsValid", optional=true)
    public native boolean isValid();
    @Bridge(symbol="CFMessagePortGetInvalidationCallBack", optional=true)
    public native FunctionPtr getInvalidationCallBack();
    @Bridge(symbol="CFMessagePortSetInvalidationCallBack", optional=true)
    public native void setInvalidationCallBack(FunctionPtr callout);
    @Bridge(symbol="CFMessagePortSendRequest", optional=true)
    public native int sendRequest(int msgid, CFData data, double sendTimeout, double rcvTimeout, CFString replyMode, CFData.CFDataPtr returnData);
    @Bridge(symbol="CFMessagePortCreateRunLoopSource", optional=true)
    public static native CFRunLoopSource createRunLoopSource(CFAllocator allocator, CFMessagePort local, @MachineSizedSInt long order);
    @Bridge(symbol="CFMessagePortSetDispatchQueue", optional=true)
    public native void setDispatchQueue(DispatchQueue queue);
    /*</methods>*/
}
