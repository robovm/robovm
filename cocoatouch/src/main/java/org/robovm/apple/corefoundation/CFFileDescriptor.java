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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFFileDescriptor/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFFileDescriptorPtr extends Ptr<CFFileDescriptor, CFFileDescriptorPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFFileDescriptor.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFFileDescriptor() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFFileDescriptorGetTypeID")
    public static native @MachineSizedUInt long getClassTypeID();
    @Bridge(symbol="CFFileDescriptorCreate")
    public static native CFFileDescriptor create(CFAllocator allocator, int fd, boolean closeOnInvalidate, FunctionPtr callout, CFFileDescriptorContext context);
    @Bridge(symbol="CFFileDescriptorGetNativeDescriptor")
    public native int getNativeDescriptor();
    @Bridge(symbol="CFFileDescriptorGetContext")
    public native void getContext(CFFileDescriptorContext context);
    @Bridge(symbol="CFFileDescriptorEnableCallBacks")
    public native void enableCallBacks(CFFileDescriptorCallBackType callBackTypes);
    @Bridge(symbol="CFFileDescriptorDisableCallBacks")
    public native void disableCallBacks(CFFileDescriptorCallBackType callBackTypes);
    @Bridge(symbol="CFFileDescriptorInvalidate")
    public native void invalidate();
    @Bridge(symbol="CFFileDescriptorIsValid")
    public native boolean isValid();
    @Bridge(symbol="CFFileDescriptorCreateRunLoopSource")
    public static native CFRunLoopSource createRunLoopSource(CFAllocator allocator, CFFileDescriptor f, @MachineSizedSInt long order);
    /*</methods>*/
}
