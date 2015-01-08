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
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorCreate", optional=true)
    public static native CFFileDescriptor create(CFAllocator allocator, int fd, boolean closeOnInvalidate, FunctionPtr callout, CFFileDescriptorContext context);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorGetNativeDescriptor", optional=true)
    public native int getNativeDescriptor();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorGetContext", optional=true)
    public native void getContext(CFFileDescriptorContext context);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorEnableCallBacks", optional=true)
    public native void enableCallBacks(CFFileDescriptorCallBackType callBackTypes);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorDisableCallBacks", optional=true)
    public native void disableCallBacks(CFFileDescriptorCallBackType callBackTypes);
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorInvalidate", optional=true)
    public native void invalidate();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorIsValid", optional=true)
    public native boolean isValid();
    /**
     * @since Available in iOS 2.0 and later.
     */
    @Bridge(symbol="CFFileDescriptorCreateRunLoopSource", optional=true)
    public static native CFRunLoopSource createRunLoopSource(CFAllocator allocator, CFFileDescriptor f, @MachineSizedSInt long order);
    /*</methods>*/
}
