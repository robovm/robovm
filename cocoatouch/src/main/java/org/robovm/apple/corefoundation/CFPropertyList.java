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
/*<visibility>*/public/*</visibility>*/ abstract class /*<name>*/CFPropertyList/*</name>*/ 
    extends /*<extends>*/CFType/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CFPropertyList.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Bridge(symbol="CFPropertyListCreateFromXMLData", optional=true)
    public static native CFType createFromXMLData(CFAllocator allocator, CFData xmlData, CFPropertyListMutabilityOptions mutabilityOption, CFString.CFStringPtr errorString);
    @Bridge(symbol="CFPropertyListCreateXMLData", optional=true)
    public static native CFData createXMLData(CFAllocator allocator, CFType propertyList);
    @Bridge(symbol="CFPropertyListCreateDeepCopy", optional=true)
    public static native CFType createDeepCopy(CFAllocator allocator, CFType propertyList, CFPropertyListMutabilityOptions mutabilityOption);
    @Bridge(symbol="CFPropertyListIsValid", optional=true)
    public static native boolean isValid(CFType plist, CFPropertyListFormat format);
    @Bridge(symbol="CFPropertyListWriteToStream", optional=true)
    public static native @MachineSizedSInt long writeToStream(CFType propertyList, CFWriteStream stream, CFPropertyListFormat format, CFString.CFStringPtr errorString);
    @Bridge(symbol="CFPropertyListCreateFromStream", optional=true)
    public static native CFType createFromStream(CFAllocator allocator, CFReadStream stream, @MachineSizedSInt long streamLength, CFPropertyListMutabilityOptions mutabilityOption, MachineSizedSIntPtr format, CFString.CFStringPtr errorString);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFPropertyListCreateWithData", optional=true)
    public static native CFType createWithData(CFAllocator allocator, CFData data, CFPropertyListMutabilityOptions options, MachineSizedSIntPtr format, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFPropertyListCreateWithStream", optional=true)
    public static native CFType createWithStream(CFAllocator allocator, CFReadStream stream, @MachineSizedSInt long streamLength, CFPropertyListMutabilityOptions options, MachineSizedSIntPtr format, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFPropertyListWrite", optional=true)
    public static native @MachineSizedSInt long write(CFType propertyList, CFWriteStream stream, CFPropertyListFormat format, CFPropertyListMutabilityOptions options, CFError.CFErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Bridge(symbol="CFPropertyListCreateData", optional=true)
    public static native CFData createData(CFAllocator allocator, CFType propertyList, CFPropertyListFormat format, CFPropertyListMutabilityOptions options, CFError.CFErrorPtr error);
    /*</methods>*/
}
