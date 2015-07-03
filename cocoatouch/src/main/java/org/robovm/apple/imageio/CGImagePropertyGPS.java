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
package org.robovm.apple.imageio;

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
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
@Marshaler(/*<name>*/CGImagePropertyGPS/*</name>*/.Marshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyGPS/*</name>*/ 
    extends /*<extends>*/GlobalValueEnumeration<CFString>/*</extends>*/
    /*<implements>*//*</implements>*/ {

    static { Bro.bind(/*<name>*/CGImagePropertyGPS/*</name>*/.class); }

    /*<marshalers>*/
    public static class Marshaler {
        @MarshalsPointer
        public static CGImagePropertyGPS toObject(Class<CGImagePropertyGPS> cls, long handle, long flags) {
            CFString o = (CFString) CFType.Marshaler.toObject(CFString.class, handle, flags);
            if (o == null) {
                return null;
            }
            return CGImagePropertyGPS.valueOf(o);
        }
        @MarshalsPointer
        public static long toNative(CGImagePropertyGPS o, long flags) {
            if (o == null) {
                return 0L;
            }
            return CFType.Marshaler.toNative(o.value(), flags);
        }
    }
    public static class AsListMarshaler {
        @MarshalsPointer
        public static List<CGImagePropertyGPS> toObject(Class<? extends CFType> cls, long handle, long flags) {
            CFArray o = (CFArray) CFType.Marshaler.toObject(CFArray.class, handle, flags);
            if (o == null) {
                return null;
            }
            List<CGImagePropertyGPS> list = new ArrayList<>();
            for (int i = 0; i < o.size(); i++) {
                list.add(CGImagePropertyGPS.valueOf(o.get(i, CFString.class)));
            }
            return list;
        }
        @MarshalsPointer
        public static long toNative(List<CGImagePropertyGPS> l, long flags) {
            if (l == null) {
                return 0L;
            }
            CFArray array = CFMutableArray.create();
            for (CGImagePropertyGPS o : l) {
                array.add(o.value());
            }
            return CFType.Marshaler.toNative(array, flags);
        }
    }
    /*</marshalers>*/

    /*<constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Version = new CGImagePropertyGPS("Version");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS LatitudeRef = new CGImagePropertyGPS("LatitudeRef");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Latitude = new CGImagePropertyGPS("Latitude");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS LongitudeRef = new CGImagePropertyGPS("LongitudeRef");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Longitude = new CGImagePropertyGPS("Longitude");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS AltitudeRef = new CGImagePropertyGPS("AltitudeRef");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Altitude = new CGImagePropertyGPS("Altitude");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS TimeStamp = new CGImagePropertyGPS("TimeStamp");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Satellites = new CGImagePropertyGPS("Satellites");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Status = new CGImagePropertyGPS("Status");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS MeasureMode = new CGImagePropertyGPS("MeasureMode");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DOP = new CGImagePropertyGPS("DOP");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS SpeedRef = new CGImagePropertyGPS("SpeedRef");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Speed = new CGImagePropertyGPS("Speed");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS TrackRef = new CGImagePropertyGPS("TrackRef");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Track = new CGImagePropertyGPS("Track");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS ImgDirectionRef = new CGImagePropertyGPS("ImgDirectionRef");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS ImgDirection = new CGImagePropertyGPS("ImgDirection");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS MapDatum = new CGImagePropertyGPS("MapDatum");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestLatitudeRef = new CGImagePropertyGPS("DestLatitudeRef");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestLatitude = new CGImagePropertyGPS("DestLatitude");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestLongitudeRef = new CGImagePropertyGPS("DestLongitudeRef");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestLongitude = new CGImagePropertyGPS("DestLongitude");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestBearingRef = new CGImagePropertyGPS("DestBearingRef");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestBearing = new CGImagePropertyGPS("DestBearing");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestDistanceRef = new CGImagePropertyGPS("DestDistanceRef");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestDistance = new CGImagePropertyGPS("DestDistance");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS ProcessingMethod = new CGImagePropertyGPS("ProcessingMethod");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS AreaInformation = new CGImagePropertyGPS("AreaInformation");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DateStamp = new CGImagePropertyGPS("DateStamp");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Differental = new CGImagePropertyGPS("Differental");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CGImagePropertyGPS HPositioningError = new CGImagePropertyGPS("HPositioningError");
    /*</constants>*/
    
    private static /*<name>*/CGImagePropertyGPS/*</name>*/[] values = new /*<name>*/CGImagePropertyGPS/*</name>*/[] {/*<value_list>*/Version, LatitudeRef, Latitude, LongitudeRef, Longitude, AltitudeRef, Altitude, TimeStamp, Satellites, Status, MeasureMode, DOP, SpeedRef, Speed, TrackRef, Track, ImgDirectionRef, ImgDirection, MapDatum, DestLatitudeRef, DestLatitude, DestLongitudeRef, DestLongitude, DestBearingRef, DestBearing, DestDistanceRef, DestDistance, ProcessingMethod, AreaInformation, DateStamp, Differental, HPositioningError/*</value_list>*/};
    
    /*<name>*/CGImagePropertyGPS/*</name>*/ (String getterName) {
        super(Values.class, getterName);
    }
    
    public static /*<name>*/CGImagePropertyGPS/*</name>*/ valueOf(/*<type>*/CFString/*</type>*/ value) {
        for (/*<name>*/CGImagePropertyGPS/*</name>*/ v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyGPS/*</name>*/.class.getName());
    }
    
    /*<methods>*//*</methods>*/
    
    /*<annotations>*/@Library("ImageIO") @StronglyLinked/*</annotations>*/
    public static class Values {
    	static { Bro.bind(Values.class); }

        /*<values>*/
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSVersion", optional=true)
        public static native CFString Version();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSLatitudeRef", optional=true)
        public static native CFString LatitudeRef();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSLatitude", optional=true)
        public static native CFString Latitude();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSLongitudeRef", optional=true)
        public static native CFString LongitudeRef();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSLongitude", optional=true)
        public static native CFString Longitude();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSAltitudeRef", optional=true)
        public static native CFString AltitudeRef();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSAltitude", optional=true)
        public static native CFString Altitude();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSTimeStamp", optional=true)
        public static native CFString TimeStamp();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSSatellites", optional=true)
        public static native CFString Satellites();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSStatus", optional=true)
        public static native CFString Status();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSMeasureMode", optional=true)
        public static native CFString MeasureMode();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSDOP", optional=true)
        public static native CFString DOP();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSSpeedRef", optional=true)
        public static native CFString SpeedRef();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSSpeed", optional=true)
        public static native CFString Speed();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSTrackRef", optional=true)
        public static native CFString TrackRef();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSTrack", optional=true)
        public static native CFString Track();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSImgDirectionRef", optional=true)
        public static native CFString ImgDirectionRef();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSImgDirection", optional=true)
        public static native CFString ImgDirection();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSMapDatum", optional=true)
        public static native CFString MapDatum();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSDestLatitudeRef", optional=true)
        public static native CFString DestLatitudeRef();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSDestLatitude", optional=true)
        public static native CFString DestLatitude();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSDestLongitudeRef", optional=true)
        public static native CFString DestLongitudeRef();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSDestLongitude", optional=true)
        public static native CFString DestLongitude();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSDestBearingRef", optional=true)
        public static native CFString DestBearingRef();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSDestBearing", optional=true)
        public static native CFString DestBearing();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSDestDistanceRef", optional=true)
        public static native CFString DestDistanceRef();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSDestDistance", optional=true)
        public static native CFString DestDistance();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSProcessingMethod", optional=true)
        public static native CFString ProcessingMethod();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSAreaInformation", optional=true)
        public static native CFString AreaInformation();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSDateStamp", optional=true)
        public static native CFString DateStamp();
        /**
         * @since Available in iOS 4.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSDifferental", optional=true)
        public static native CFString Differental();
        /**
         * @since Available in iOS 8.0 and later.
         */
        @GlobalValue(symbol="kCGImagePropertyGPSHPositioningError", optional=true)
        public static native CFString HPositioningError();
        /*</values>*/
    }
}
