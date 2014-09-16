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
package org.robovm.apple.uikit;

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
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(UIPageViewControllerOptions.Marshaler.class)
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPageViewControllerOptions/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public static class Marshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static UIPageViewControllerOptions toObject(Class<UIPageViewControllerOptions> cls, long handle, long flags) {
            NSDictionary<NSString, NSObject> o = (NSDictionary<NSString, NSObject>) NSObject.Marshaler.toObject(NSDictionary.class, handle, flags);
            if (o == null) {
                return null;
            }
            return new UIPageViewControllerOptions(o);
        }
        @MarshalsPointer
        public static long toNative(UIPageViewControllerOptions o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.data, flags);
        }
    }
    
    /*<ptr>*/
    /*</ptr>*/
    
    private NSDictionary<NSString, NSObject> data;
    
    protected UIPageViewControllerOptions(NSDictionary<NSString, NSObject> data) {
        this.data = data;
    }
    /*<bind>*/static { Bro.bind(UIPageViewControllerOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSObject> getDictionary() {
        return data;
    }
    
    public UIPageViewControllerSpineLocation getSpineLocation() {
        if (data.containsKey(SpineLocationKey())) {
            NSNumber val = (NSNumber)data.get(SpineLocationKey());
            return UIPageViewControllerSpineLocation.valueOf(val.intValue());
        }
        return null;
    }
    public void setSpineLocation(UIPageViewControllerSpineLocation spineLocation) {
        data.put(SpineLocationKey(), NSNumber.valueOf((int)spineLocation.value()));
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public @MachineSizedFloat double getInterPageSpacing() {
        if (data.containsKey(InterPageSpacingKey())) {
            NSNumber val = (NSNumber)data.get(InterPageSpacingKey());
            return val.floatValue();
        }
        return 0;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setInterPageSpacing(@MachineSizedFloat double interPageSpacing) {
        data.put(InterPageSpacingKey(), NSNumber.valueOf(interPageSpacing));
    }
    /*<methods>*/
    @GlobalValue(symbol="UIPageViewControllerOptionSpineLocationKey", optional=true)
    public static native NSString SpineLocationKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="UIPageViewControllerOptionInterPageSpacingKey", optional=true)
    public static native NSString InterPageSpacingKey();
    /*</methods>*/
    
    @Override
    public String toString() {
        if (data != null) return data.toString();
        return super.toString();
    }
}
