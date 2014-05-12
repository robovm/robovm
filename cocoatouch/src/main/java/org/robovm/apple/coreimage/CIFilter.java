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
package org.robovm.apple.coreimage;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.opengles.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("CoreImage") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIFilter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class CIFilterPtr extends Ptr<CIFilter, CIFilterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CIFilter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CIFilter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "outputImage")
    public native CIImage getOutputImage();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "name")
    public native String getName();
    @Method(selector = "inputKeys")
    public native NSArray<?> getInputKeys();
    @Method(selector = "outputKeys")
    public native NSArray<?> getOutputKeys();
    @Method(selector = "setDefaults")
    public native void setDefaults();
    @Method(selector = "attributes")
    public native NSDictionary<?, ?> getAttributes();
    @Method(selector = "filterWithName:")
    public static native CIFilter getFilter(String name);
    @Method(selector = "filterNamesInCategory:")
    public static native NSArray<NSString> getFilterNames(String category);
    @Method(selector = "filterNamesInCategories:")
    public static native NSArray<NSString> getFilterNames(NSArray<NSString> categories);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "serializedXMPFromFilters:inputImageExtent:")
    public static native NSData serializeToXMP(NSArray<CIFilter> filters, @ByVal CGRect extent);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "filterArrayFromSerializedXMP:inputImageExtent:error:")
    public static native NSArray<CIFilter> deserializeFromXMP(NSData xmpData, @ByVal CGRect extent, NSError.NSErrorPtr outError);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
