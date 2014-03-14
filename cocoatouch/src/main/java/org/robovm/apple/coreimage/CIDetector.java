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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("CoreImage") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIDetector/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class CIDetectorPtr extends Ptr<CIDetector, CIDetectorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CIDetector.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public CIDetector() {}
    protected CIDetector(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public CIDetector(String type, CIContext context, NSDictionary<?, ?> options) {
        super(detectorOfType$context$options$(type, context, options));
    }
    
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="CIDetectorTypeFace")
    public static native String TypeFace();
    @GlobalValue(symbol="CIDetectorAccuracy")
    public static native NSString ConfigKeyAccuracy();
    @GlobalValue(symbol="CIDetectorAccuracyLow")
    public static native NSString AccuracyLow();
    @GlobalValue(symbol="CIDetectorAccuracyHigh")
    public static native NSString AccuracyHigh();
    @GlobalValue(symbol="CIDetectorTracking")
    public static native NSString ConfigKeyTracking();
    @GlobalValue(symbol="CIDetectorMinFeatureSize")
    public static native NSString ConfigKeyMinFeatureSize();
    @GlobalValue(symbol="CIDetectorImageOrientation")
    public static native NSString FeatureKeyImageOrientation();
    @GlobalValue(symbol="CIDetectorEyeBlink")
    public static native NSString FeatureKeyEyeBlink();
    @GlobalValue(symbol="CIDetectorSmile")
    public static native NSString FeatureKeySmile();
    
    @Method(selector = "featuresInImage:")
    public native NSArray<CIFeature> findFeatures(CIImage image);
    @Method(selector = "featuresInImage:options:")
    public native NSArray<CIFeature> findFeatures(CIImage image, NSDictionary<?, ?> options);
    @Method(selector = "detectorOfType:context:options:")
    protected static native @Pointer long detectorOfType$context$options$(String type, CIContext context, NSDictionary<?, ?> options);
    /*</methods>*/
}
