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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFReadStream/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFReadStreamPtr extends Ptr<CFReadStream, CFReadStreamPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFReadStream.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFReadStream() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFReadStreamGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFReadStreamCreateWithBytesNoCopy")
    public static native CFReadStream createWithBytesNoCopy(CFAllocator alloc, BytePtr bytes, @MachineSizedSInt long length, CFAllocator bytesDeallocator);
    @Bridge(symbol="CFReadStreamCreateWithFile")
    public static native CFReadStream createWithFile(CFAllocator alloc, CFURL fileURL);
    @Bridge(symbol="CFReadStreamGetStatus")
    public native CFStreamStatus getStatus();
    @Bridge(symbol="CFReadStreamCopyError")
    public native CFError copyError();
    @Bridge(symbol="CFReadStreamOpen")
    public native boolean openStream();
    @Bridge(symbol="CFReadStreamClose")
    public native void closeStream();
    @Bridge(symbol="CFReadStreamHasBytesAvailable")
    public native boolean hasBytesAvailable();
    @Bridge(symbol="CFReadStreamRead")
    public native @MachineSizedSInt long read(BytePtr buffer, @MachineSizedSInt long bufferLength);
    @Bridge(symbol="CFReadStreamGetBuffer")
    public native BytePtr getBuffer(@MachineSizedSInt long maxBytesToRead, MachineSizedSIntPtr numBytesRead);
    @Bridge(symbol="CFReadStreamCopyProperty")
    public native CFType copyProperty(CFString propertyName);
    @Bridge(symbol="CFReadStreamSetProperty")
    public native boolean setProperty(CFString propertyName, CFType propertyValue);
    @Bridge(symbol="CFReadStreamSetClient")
    public native boolean setClient(CFStreamEventType streamEvents, FunctionPtr clientCB, CFStreamClientContext clientContext);
    @Bridge(symbol="CFReadStreamScheduleWithRunLoop")
    public native void scheduleWithRunLoop(CFRunLoop runLoop, CFString runLoopMode);
    @Bridge(symbol="CFReadStreamUnscheduleFromRunLoop")
    public native void unscheduleFromRunLoop(CFRunLoop runLoop, CFString runLoopMode);
    @Bridge(symbol="CFReadStreamSetDispatchQueue")
    public native void setDispatchQueue(DispatchQueue q);
    @Bridge(symbol="CFReadStreamCopyDispatchQueue")
    public native DispatchQueue copyDispatchQueue();
    @Bridge(symbol="CFReadStreamGetError")
    public native @ByVal CFStreamError getError();
    /*</methods>*/
}
