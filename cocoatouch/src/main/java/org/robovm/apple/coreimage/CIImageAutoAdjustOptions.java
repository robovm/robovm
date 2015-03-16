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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.imageio.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CIImageAutoAdjustOptions.Marshaler.class)
/*<annotations>*/@Library("CoreImage")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CIImageAutoAdjustOptions/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @MarshalsPointer
        public static CIImageAutoAdjustOptions toObject(Class<CIImageAutoAdjustOptions> cls, long handle, long flags) {
            CFDictionary o = (CFDictionary) CFType.Marshaler.toObject(CFDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new CIImageAutoAdjustOptions(o);
        }
        @MarshalsPointer
        public static long toNative(CIImageAutoAdjustOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    private CFDictionary data;
    
    protected CIImageAutoAdjustOptions(CFDictionary data) {
        this.data = data;
    }
    public CIImageAutoAdjustOptions() {
        data = CFMutableDictionary.create();
    }
    /*<bind>*/static { Bro.bind(CIImageAutoAdjustOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFDictionary getDictionary() {
        return data;
    }
    
    
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean appliesEnhancementFilter() {
        if (data.containsKey(EnhanceKey())) {
            CFBoolean val = data.get(EnhanceKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return true;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIImageAutoAdjustOptions setAppliesEnhancementFilter(boolean apply) {
        data.put(EnhanceKey(), CFBoolean.valueOf(apply));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public boolean appliesRedEyeFilter() {
        if (data.containsKey(RedEyeKey())) {
            CFBoolean val = data.get(RedEyeKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return true;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIImageAutoAdjustOptions setAppliesRedEyeFilter(boolean apply) {
        data.put(RedEyeKey(), CFBoolean.valueOf(apply));
        return this;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public List<CIFeature> getFeatures() {
        if (data.containsKey(FeaturesKey())) {
            CFArray val = data.get(FeaturesKey(), CFArray.class);
            return val.toList(CIFeature.class);
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public CIImageAutoAdjustOptions setFeatures(List<CIFeature> features) {
        data.put(FeaturesKey(), CFArray.create(features));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean appliesCropFilter() {
        if (data.containsKey(CropKey())) {
            CFBoolean val = data.get(CropKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CIImageAutoAdjustOptions setAppliesCropFilter(boolean apply) {
        data.put(CropKey(), CFBoolean.valueOf(apply));
        return this;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public boolean appliesAutoLevel() {
        if (data.containsKey(LevelKey())) {
            CFBoolean val = data.get(LevelKey(), CFBoolean.class);
            return val.booleanValue();
        }
        return false;
    }
    /**
     * @since Available in iOS 8.0 and later.
     */
    public CIImageAutoAdjustOptions setAppliesAutoLevel(boolean apply) {
        data.put(LevelKey(), CFBoolean.valueOf(apply));
        return this;
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIImageAutoAdjustEnhance", optional=true)
    protected static native CFString EnhanceKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIImageAutoAdjustRedEye", optional=true)
    protected static native CFString RedEyeKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kCIImageAutoAdjustFeatures", optional=true)
    protected static native CFString FeaturesKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCIImageAutoAdjustCrop", optional=true)
    protected static native CFString CropKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCIImageAutoAdjustLevel", optional=true)
    protected static native CFString LevelKey();
    /*</methods>*/
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
