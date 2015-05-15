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

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSJSONSerialization/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSJSONSerializationPtr extends Ptr<NSJSONSerialization, NSJSONSerializationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSJSONSerialization.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSJSONSerialization() {}
    protected NSJSONSerialization(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "isValidJSONObject:")
    public static native boolean isValidJSONObject(NSObject obj);
    public static NSData createJSONData(NSObject obj, NSJSONWritingOptions opt) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSData result = createJSONData(obj, opt, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "dataWithJSONObject:options:error:")
    private static native NSData createJSONData(NSObject obj, NSJSONWritingOptions opt, NSError.NSErrorPtr error);
    public static NSObject createJSONObject(NSData data, NSJSONReadingOptions opt) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSObject result = createJSONObject(data, opt, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "JSONObjectWithData:options:error:")
    private static native NSObject createJSONObject(NSData data, NSJSONReadingOptions opt, NSError.NSErrorPtr error);
    public static @MachineSizedSInt long writeJSONObject(NSObject obj, NSOutputStream stream, NSJSONWritingOptions opt) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       long result = writeJSONObject(obj, stream, opt, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "writeJSONObject:toStream:options:error:")
    private static native @MachineSizedSInt long writeJSONObject(NSObject obj, NSOutputStream stream, NSJSONWritingOptions opt, NSError.NSErrorPtr error);
    public static NSObject readJSONObject(NSInputStream stream, NSJSONReadingOptions opt) throws NSErrorException {
       NSError.NSErrorPtr ptr = new NSError.NSErrorPtr();
       NSObject result = readJSONObject(stream, opt, ptr);
       if (ptr.get() != null) { throw new NSErrorException(ptr.get()); }
       return result;
    }
    @Method(selector = "JSONObjectWithStream:options:error:")
    private static native NSObject readJSONObject(NSInputStream stream, NSJSONReadingOptions opt, NSError.NSErrorPtr error);
    /*</methods>*/
}
