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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.mediatoolbox.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.audiounit.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/AVLayerVideoGravity/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVLayerVideoGravity/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVLayerVideoGravity/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVLayerVideoGravity toObject(Class<AVLayerVideoGravity> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVLayerVideoGravity.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVLayerVideoGravity o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVLayerVideoGravity> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVLayerVideoGravity> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVLayerVideoGravity.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVLayerVideoGravity> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVLayerVideoGravity o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVLayerVideoGravity ResizeAspect = new AVLayerVideoGravity("ResizeAspect");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVLayerVideoGravity ResizeAspectFill = new AVLayerVideoGravity("ResizeAspectFill");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final AVLayerVideoGravity Resize = new AVLayerVideoGravity("Resize");
    /*</constants>*/
    
    private static /*<name>*/AVLayerVideoGravity/*</name>*/[] values = new /*<name>*/AVLayerVideoGravity/*</name>*/[] {/*<value_list>*/ResizeAspect, ResizeAspectFill, Resize/*</value_list>*/};
    
    /*<name>*/AVLayerVideoGravity/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVLayerVideoGravity/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVLayerVideoGravity/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVLayerVideoGravity/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVLayerVideoGravityResizeAspect", optional=true)
        public static native NSString ResizeAspect();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVLayerVideoGravityResizeAspectFill", optional=true)
        public static native NSString ResizeAspectFill();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="AVLayerVideoGravityResize", optional=true)
        public static native NSString Resize();
        /*</values>*/
    }
}
