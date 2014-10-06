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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSPropertyListSerialization/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSPropertyListSerializationPtr extends Ptr<NSPropertyListSerialization, NSPropertyListSerializationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSPropertyListSerialization.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSPropertyListSerialization() {}
    protected NSPropertyListSerialization(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static NSPropertyList getPropertyListFromData(NSData data, @MachineSizedUInt long opt) {
        return getPropertyListFromData(data, opt, null, null);
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static NSPropertyList getPropertyListFromStream(NSInputStream stream, @MachineSizedUInt long opt) {
        return getPropertyListFromStream(stream, opt, null, null);
    }
    public static NSPropertyList getPropertyListFromData(NSData data, NSPropertyListMutabilityOptions opt) {
        return getPropertyListFromData(data, opt, null, null);
    }
    /*<methods>*/
    @Method(selector = "propertyList:isValidForFormat:")
    public static native boolean isPropertyListValidForFormat(NSPropertyList plist, NSPropertyListFormat format);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "dataWithPropertyList:format:options:error:")
    public static native NSData getDataFromPropertyList(NSPropertyList plist, NSPropertyListFormat format, @MachineSizedUInt long opt, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "writePropertyList:toStream:format:options:error:")
    public static native @MachineSizedSInt long writePropertyListToStream(NSPropertyList plist, NSOutputStream stream, NSPropertyListFormat format, @MachineSizedUInt long opt, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "propertyListWithData:options:format:error:")
    protected static native NSPropertyList getPropertyListFromData(NSData data, @MachineSizedUInt long opt, MachineSizedUIntPtr format, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "propertyListWithStream:options:format:error:")
    protected static native NSPropertyList getPropertyListFromStream(NSInputStream stream, @MachineSizedUInt long opt, MachineSizedUIntPtr format, NSError.NSErrorPtr error);
    @Method(selector = "dataFromPropertyList:format:errorDescription:")
    public static native NSData getDataFromPropertyList(NSObject plist, NSPropertyListFormat format, NSString.NSStringPtr errorString);
    @Method(selector = "propertyListFromData:mutabilityOption:format:errorDescription:")
    protected static native NSPropertyList getPropertyListFromData(NSData data, NSPropertyListMutabilityOptions opt, MachineSizedUIntPtr format, NSString.NSStringPtr errorString);
    /*</methods>*/
}
