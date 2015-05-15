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
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/
import org.robovm.apple.foundation.NSError.NSErrorPtr;

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
     * @throws NSErrorException
     */
    public static NSPropertyList getPropertyListFromData(NSData data, NSPropertyListMutabilityOptions opt) throws NSErrorException {
        return getPropertyListFromData(data, opt, null);
    }
    /**
     * @since Available in iOS 4.0 and later.
     * @throws NSErrorException
     */
    public static NSPropertyList getPropertyListFromStream(NSInputStream stream, NSPropertyListMutabilityOptions opt) throws NSErrorException {
        return getPropertyListFromStream(stream, opt, null);
    }
    /*<methods>*/
    @Method(selector = "propertyList:isValidForFormat:")
    public static native boolean isPropertyListValidForFormat(NSPropertyList plist, NSPropertyListFormat format);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static NSData getDataFromPropertyList(NSPropertyList plist, NSPropertyListFormat format, @MachineSizedUInt long opt) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSData result = getDataFromPropertyList(plist, format, opt, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "dataWithPropertyList:format:options:error:")
    private static native NSData getDataFromPropertyList(NSPropertyList plist, NSPropertyListFormat format, @MachineSizedUInt long opt, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static @MachineSizedSInt long writePropertyListToStream(NSPropertyList plist, NSOutputStream stream, NSPropertyListFormat format, @MachineSizedUInt long opt) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       long result = writePropertyListToStream(plist, stream, format, opt, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "writePropertyList:toStream:format:options:error:")
    private static native @MachineSizedSInt long writePropertyListToStream(NSPropertyList plist, NSOutputStream stream, NSPropertyListFormat format, @MachineSizedUInt long opt, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    protected static NSPropertyList getPropertyListFromData(NSData data, NSPropertyListMutabilityOptions opt, MachineSizedUIntPtr format) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSPropertyList result = getPropertyListFromData(data, opt, format, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "propertyListWithData:options:format:error:")
    private static native NSPropertyList getPropertyListFromData(NSData data, NSPropertyListMutabilityOptions opt, MachineSizedUIntPtr format, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 4.0 and later.
     */
    protected static NSPropertyList getPropertyListFromStream(NSInputStream stream, NSPropertyListMutabilityOptions opt, MachineSizedUIntPtr format) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSPropertyList result = getPropertyListFromStream(stream, opt, format, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "propertyListWithStream:options:format:error:")
    private static native NSPropertyList getPropertyListFromStream(NSInputStream stream, NSPropertyListMutabilityOptions opt, MachineSizedUIntPtr format, NSError.NSErrorPtr error);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "dataFromPropertyList:format:errorDescription:")
    protected static native NSData getDataFromPropertyList(NSObject plist, NSPropertyListFormat format, NSString.NSStringPtr errorString);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Method(selector = "propertyListFromData:mutabilityOption:format:errorDescription:")
    protected static native NSPropertyList getPropertyListFromData(NSData data, NSPropertyListMutabilityOptions opt, MachineSizedUIntPtr format, NSString.NSStringPtr errorString);
    /*</methods>*/
}
