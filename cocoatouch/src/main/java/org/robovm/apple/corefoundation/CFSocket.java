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
import org.robovm.apple.foundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFSocket/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFSocketPtr extends Ptr<CFSocket, CFSocketPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFSocket.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFSocket() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFSocketGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFSocketCreate", optional=true)
    public static native CFSocket create(CFAllocator allocator, int protocolFamily, int socketType, int protocol, CFSocketCallBackType callBackTypes, FunctionPtr callout, CFSocketContext context);
    @Bridge(symbol="CFSocketCreateWithNative", optional=true)
    public static native CFSocket createWithNative(CFAllocator allocator, int sock, CFSocketCallBackType callBackTypes, FunctionPtr callout, CFSocketContext context);
    @Bridge(symbol="CFSocketCreateWithSocketSignature", optional=true)
    public static native CFSocket createWithSocketSignature(CFAllocator allocator, CFSocketSignature signature, CFSocketCallBackType callBackTypes, FunctionPtr callout, CFSocketContext context);
    @Bridge(symbol="CFSocketCreateConnectedToSocketSignature", optional=true)
    public static native CFSocket createConnectedToSocketSignature(CFAllocator allocator, CFSocketSignature signature, CFSocketCallBackType callBackTypes, FunctionPtr callout, CFSocketContext context, double timeout);
    @Bridge(symbol="CFSocketSetAddress", optional=true)
    public native CFSocketError setAddress(CFData address);
    @Bridge(symbol="CFSocketConnectToAddress", optional=true)
    public native CFSocketError connectToAddress(CFData address, double timeout);
    @Bridge(symbol="CFSocketInvalidate", optional=true)
    public native void invalidate();
    @Bridge(symbol="CFSocketIsValid", optional=true)
    public native boolean isValid();
    @Bridge(symbol="CFSocketCopyAddress", optional=true)
    public native CFData copyAddress();
    @Bridge(symbol="CFSocketCopyPeerAddress", optional=true)
    public native CFData copyPeerAddress();
    @Bridge(symbol="CFSocketGetContext", optional=true)
    public native void getContext(CFSocketContext context);
    @Bridge(symbol="CFSocketGetNative", optional=true)
    public native int getNative();
    @Bridge(symbol="CFSocketCreateRunLoopSource", optional=true)
    public static native CFRunLoopSource createRunLoopSource(CFAllocator allocator, CFSocket s, @MachineSizedSInt long order);
    @Bridge(symbol="CFSocketGetSocketFlags", optional=true)
    public native CFSocketFlags getSocketFlags();
    @Bridge(symbol="CFSocketSetSocketFlags", optional=true)
    public native void setSocketFlags(CFSocketFlags flags);
    @Bridge(symbol="CFSocketDisableCallBacks", optional=true)
    public native void disableCallBacks(CFSocketCallBackType callBackTypes);
    @Bridge(symbol="CFSocketEnableCallBacks", optional=true)
    public native void enableCallBacks(CFSocketCallBackType callBackTypes);
    @Bridge(symbol="CFSocketSendData", optional=true)
    public native CFSocketError sendData(CFData address, CFData data, double timeout);
    @Bridge(symbol="CFSocketRegisterValue", optional=true)
    public static native CFSocketError registerValue(CFSocketSignature nameServerSignature, double timeout, String name, CFType value);
    @Bridge(symbol="CFSocketCopyRegisteredValue", optional=true)
    public static native CFSocketError copyRegisteredValue(CFSocketSignature nameServerSignature, double timeout, String name, CFType.CFTypePtr value, CFData.CFDataPtr nameServerAddress);
    @Bridge(symbol="CFSocketRegisterSocketSignature", optional=true)
    public static native CFSocketError registerSocketSignature(CFSocketSignature nameServerSignature, double timeout, String name, CFSocketSignature signature);
    @Bridge(symbol="CFSocketCopyRegisteredSocketSignature", optional=true)
    public static native CFSocketError copyRegisteredSocketSignature(CFSocketSignature nameServerSignature, double timeout, String name, CFSocketSignature signature, CFData.CFDataPtr nameServerAddress);
    @Bridge(symbol="CFSocketUnregister", optional=true)
    public static native CFSocketError unregister(CFSocketSignature nameServerSignature, double timeout, String name);
    @Bridge(symbol="CFSocketSetDefaultNameRegistryPortNumber", optional=true)
    public static native void setDefaultNameRegistryPortNumber(short port);
    @Bridge(symbol="CFSocketGetDefaultNameRegistryPortNumber", optional=true)
    public static native short getDefaultNameRegistryPortNumber();
    /*</methods>*/
}
