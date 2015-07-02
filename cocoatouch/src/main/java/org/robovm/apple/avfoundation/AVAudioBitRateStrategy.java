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
@Marshaler(/*<name>*/AVAudioBitRateStrategy/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVAudioBitRateStrategy/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<NSString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/AVAudioBitRateStrategy/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static AVAudioBitRateStrategy toObject(Class<AVAudioBitRateStrategy> cls, long handle, long flags) {
            NSString o = (NSString) NSObject.Marshaler.toObject(NSString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return AVAudioBitRateStrategy.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(AVAudioBitRateStrategy o, long flags) {
            if (o == null) {
                return 0L;
            }
            return NSObject.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @SuppressWarnings("unchecked")
        @MarshalsPointer
        public static List<AVAudioBitRateStrategy> toObject(Class<? extends NSObject> cls, long handle, long flags) {
            NSArray<NSString> o = (NSArray<NSString>) NSObject.Marshaler.toObject(NSArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<AVAudioBitRateStrategy> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(AVAudioBitRateStrategy.valueOf(o.get(i)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<AVAudioBitRateStrategy> l, long flags) {
            if (l == null) {
                return 0L;
            }
            NSArray<NSString> array = new NSMutableArray<>();
            for (AVAudioBitRateStrategy o : l) {
                array.add(o.value());
            }
            return NSObject.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioBitRateStrategy Constant = new AVAudioBitRateStrategy("Constant");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioBitRateStrategy LongTermAverage = new AVAudioBitRateStrategy("LongTermAverage");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioBitRateStrategy VariableConstrained = new AVAudioBitRateStrategy("VariableConstrained");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static final AVAudioBitRateStrategy Variable = new AVAudioBitRateStrategy("Variable");
    /*</constants>*/
    
    private static /*<name>*/AVAudioBitRateStrategy/*</name>*/[] values = new /*<name>*/AVAudioBitRateStrategy/*</name>*/[] {/*<value_list>*/Constant, LongTermAverage, VariableConstrained, Variable/*</value_list>*/};
    
    /*<name>*/AVAudioBitRateStrategy/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/AVAudioBitRateStrategy/*</name>*/ valueOf(/*<type>*/NSString/*</type>*/ value) {
        for (/*<name>*/AVAudioBitRateStrategy/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/AVAudioBitRateStrategy/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("AVFoundation") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVAudioBitRateStrategy_Constant", optional=true)
        public static native NSString Constant();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVAudioBitRateStrategy_LongTermAverage", optional=true)
        public static native NSString LongTermAverage();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVAudioBitRateStrategy_VariableConstrained", optional=true)
        public static native NSString VariableConstrained();
        /**
         * @since Available in iOS 7.0 and later.
         */
        @GlobalValue(symbol="AVAudioBitRateStrategy_Variable", optional=true)
        public static native NSString Variable();
        /*</values>*/
    }
}
