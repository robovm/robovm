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

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("CoreFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFWriteStream/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFWriteStreamPtr extends Ptr<CFWriteStream, CFWriteStreamPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFWriteStream.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFWriteStream() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFWriteStreamGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFWriteStreamCreateWithBuffer", optional=true)
    public static native CFWriteStream createWithBuffer(CFAllocator alloc, BytePtr buffer, @MachineSizedSInt long bufferCapacity);
    @Bridge(symbol="CFWriteStreamCreateWithAllocatedBuffers", optional=true)
    public static native CFWriteStream createWithAllocatedBuffers(CFAllocator alloc, CFAllocator bufferAllocator);
    @Bridge(symbol="CFWriteStreamCreateWithFile", optional=true)
    public static native CFWriteStream createWithFile(CFAllocator alloc, CFURL fileURL);
    @Bridge(symbol="CFWriteStreamGetStatus", optional=true)
    public native CFStreamStatus getStatus();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFWriteStreamCopyError", optional=true)
    public native CFError copyError();
    @Bridge(symbol="CFWriteStreamOpen", optional=true)
    public native boolean openStream();
    @Bridge(symbol="CFWriteStreamClose", optional=true)
    public native void closeStream();
    @Bridge(symbol="CFWriteStreamCanAcceptBytes", optional=true)
    public native boolean canAcceptBytes();
    @Bridge(symbol="CFWriteStreamWrite", optional=true)
    public native @MachineSizedSInt long write(BytePtr buffer, @MachineSizedSInt long bufferLength);
    @Bridge(symbol="CFWriteStreamCopyProperty", optional=true)
    public native CFType copyProperty(CFString propertyName);
    @Bridge(symbol="CFWriteStreamSetProperty", optional=true)
    public native boolean setProperty(CFString propertyName, CFType propertyValue);
    @Bridge(symbol="CFWriteStreamSetClient", optional=true)
    public native boolean setClient(CFStreamEventType streamEvents, FunctionPtr clientCB, CFStreamClientContext clientContext);
    @Bridge(symbol="CFWriteStreamScheduleWithRunLoop", optional=true)
    public native void scheduleWithRunLoop(CFRunLoop runLoop, CFString runLoopMode);
    @Bridge(symbol="CFWriteStreamUnscheduleFromRunLoop", optional=true)
    public native void unscheduleFromRunLoop(CFRunLoop runLoop, CFString runLoopMode);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CFWriteStreamSetDispatchQueue", optional=true)
    public native void setDispatchQueue(DispatchQueue q);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Bridge(symbol="CFWriteStreamCopyDispatchQueue", optional=true)
    public native DispatchQueue copyDispatchQueue();
    @Bridge(symbol="CFWriteStreamGetError", optional=true)
    public native @ByVal CFStreamError getError();
    /*</methods>*/
}
