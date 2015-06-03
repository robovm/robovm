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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CFURLEnumerator/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CFURLEnumeratorPtr extends Ptr<CFURLEnumerator, CFURLEnumeratorPtr> {}/*</ptr>*/
    /*<bind>*/static { Bro.bind(CFURLEnumerator.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CFURLEnumerator() {}
    /*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLEnumeratorGetTypeID", optional=true)
    public static native @MachineSizedUInt long getClassTypeID();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLEnumeratorCreateForDirectoryURL", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURLEnumerator createForDirectoryURL(CFAllocator alloc, CFURL directoryURL, CFURLEnumeratorOptions option, CFArray propertyKeys);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLEnumeratorCreateForMountedVolumes", optional=true)
    public static native @org.robovm.rt.bro.annotation.Marshaler(CFType.NoRetainMarshaler.class) CFURLEnumerator createForMountedVolumes(CFAllocator alloc, CFURLEnumeratorOptions option, CFArray propertyKeys);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public CFURLEnumeratorResult getNextURL(CFURL.CFURLPtr url) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       CFURLEnumeratorResult result = getNextURL(url, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLEnumeratorGetNextURL", optional=true)
    private native CFURLEnumeratorResult getNextURL(CFURL.CFURLPtr url, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLEnumeratorSkipDescendents", optional=true)
    public native void skipDescendents();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFURLEnumeratorGetDescendentLevel", optional=true)
    public native @MachineSizedSInt long getDescendentLevel();
    /*</methods>*/
}
