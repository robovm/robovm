/*
 * Copyright (C) 2014 Trillian AB
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
    @Bridge(symbol="CFMessagePortGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFMessagePortCreateLocal")
    public static native CFMessagePort createLocal(CFAllocator allocator, CFString name, FunctionPtr callout, CFMessagePortContext context, BytePtr shouldFreeInfo);
    @Bridge(symbol="CFMessagePortCreateRemote")
    public static native CFMessagePort createRemote(CFAllocator allocator, CFString name);
    @Bridge(symbol="CFMessagePortIsRemote")
    public native boolean isRemote();
    @Bridge(symbol="CFMessagePortGetName")
    public native CFString getName();
    @Bridge(symbol="CFMessagePortSetName")
    public native boolean setName(CFString newName);
    @Bridge(symbol="CFMessagePortGetContext")
    public native void getContext(CFMessagePortContext context);
    @Bridge(symbol="CFMessagePortInvalidate")
    public native void invalidate();
    @Bridge(symbol="CFMessagePortIsValid")
    public native boolean isValid();
    @Bridge(symbol="CFMessagePortGetInvalidationCallBack")
    public native FunctionPtr getInvalidationCallBack();
    @Bridge(symbol="CFMessagePortSetInvalidationCallBack")
    public native void setInvalidationCallBack(FunctionPtr callout);
    @Bridge(symbol="CFMessagePortSendRequest")
    public native int sendRequest(int msgid, CFData data, double sendTimeout, double rcvTimeout, CFString replyMode, CFData.CFDataPtr returnData);
    @Bridge(symbol="CFMessagePortCreateRunLoopSource")
    public static native CFRunLoopSource createRunLoopSource(CFAllocator allocator, CFMessagePort local, @MachineSizedSInt long order);
    @Bridge(symbol="CFMessagePortSetDispatchQueue")
    public native void setDispatchQueue(DispatchQueue queue);
    /*</methods>*/
}
