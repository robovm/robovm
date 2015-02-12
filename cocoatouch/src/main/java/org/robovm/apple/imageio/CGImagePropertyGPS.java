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
package org.robovm.apple.imageio;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
@Marshaler(CGImagePropertyGPS.Marshaler.class)
/*<annotations>*/@Library("ImageIO")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CGImagePropertyGPS/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
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

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(CGImagePropertyGPS.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Version = new CGImagePropertyGPS("VersionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS LatitudeRef = new CGImagePropertyGPS("LatitudeRefKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Latitude = new CGImagePropertyGPS("LatitudeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS LongitudeRef = new CGImagePropertyGPS("LongitudeRefKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Longitude = new CGImagePropertyGPS("LongitudeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS AltitudeRef = new CGImagePropertyGPS("AltitudeRefKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Altitude = new CGImagePropertyGPS("AltitudeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS TimeStamp = new CGImagePropertyGPS("TimeStampKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Satellites = new CGImagePropertyGPS("SatellitesKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Status = new CGImagePropertyGPS("StatusKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS MeasureMode = new CGImagePropertyGPS("MeasureModeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DOP = new CGImagePropertyGPS("DOPKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS SpeedRef = new CGImagePropertyGPS("SpeedRefKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Speed = new CGImagePropertyGPS("SpeedKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS TrackRef = new CGImagePropertyGPS("TrackRefKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Track = new CGImagePropertyGPS("TrackKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS ImgDirectionRef = new CGImagePropertyGPS("ImgDirectionRefKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS ImgDirection = new CGImagePropertyGPS("ImgDirectionKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS MapDatum = new CGImagePropertyGPS("MapDatumKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestLatitudeRef = new CGImagePropertyGPS("DestLatitudeRefKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestLatitude = new CGImagePropertyGPS("DestLatitudeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestLongitudeRef = new CGImagePropertyGPS("DestLongitudeRefKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestLongitude = new CGImagePropertyGPS("DestLongitudeKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestBearingRef = new CGImagePropertyGPS("DestBearingRefKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestBearing = new CGImagePropertyGPS("DestBearingKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestDistanceRef = new CGImagePropertyGPS("DestDistanceRefKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DestDistance = new CGImagePropertyGPS("DestDistanceKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS ProcessingMethod = new CGImagePropertyGPS("ProcessingMethodKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS AreaInformation = new CGImagePropertyGPS("AreaInformationKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS DateStamp = new CGImagePropertyGPS("DateStampKey");
    /**
     * @since Available in iOS 4.0 and later.
     */
    public static final CGImagePropertyGPS Differental = new CGImagePropertyGPS("DifferentalKey");
    /**
     * @since Available in iOS 8.0 and later.
     */
    public static final CGImagePropertyGPS HPositioningError = new CGImagePropertyGPS("HPositioningErrorKey");    
    
    private static CGImagePropertyGPS[] values = new CGImagePropertyGPS[] {Version, LatitudeRef, Latitude, LongitudeRef, 
        Longitude, AltitudeRef, Altitude, TimeStamp, Satellites, Status, MeasureMode, DOP, SpeedRef, Speed, TrackRef, Track, 
        ImgDirectionRef, ImgDirection, MapDatum, DestLatitudeRef, DestLatitude, DestLongitudeRef, DestLongitude, DestBearingRef, 
        DestBearing, DestDistanceRef, DestDistance, ProcessingMethod, AreaInformation, DateStamp, Differental, HPositioningError};
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private CGImagePropertyGPS(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    
    public static CGImagePropertyGPS valueOf(CFString value) {
        for (CGImagePropertyGPS v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/CGImagePropertyGPS/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSVersion", optional=true)
    protected static native CFString VersionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSLatitudeRef", optional=true)
    protected static native CFString LatitudeRefKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSLatitude", optional=true)
    protected static native CFString LatitudeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSLongitudeRef", optional=true)
    protected static native CFString LongitudeRefKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSLongitude", optional=true)
    protected static native CFString LongitudeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSAltitudeRef", optional=true)
    protected static native CFString AltitudeRefKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSAltitude", optional=true)
    protected static native CFString AltitudeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSTimeStamp", optional=true)
    protected static native CFString TimeStampKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSSatellites", optional=true)
    protected static native CFString SatellitesKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSStatus", optional=true)
    protected static native CFString StatusKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSMeasureMode", optional=true)
    protected static native CFString MeasureModeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSDOP", optional=true)
    protected static native CFString DOPKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSSpeedRef", optional=true)
    protected static native CFString SpeedRefKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSSpeed", optional=true)
    protected static native CFString SpeedKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSTrackRef", optional=true)
    protected static native CFString TrackRefKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSTrack", optional=true)
    protected static native CFString TrackKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSImgDirectionRef", optional=true)
    protected static native CFString ImgDirectionRefKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSImgDirection", optional=true)
    protected static native CFString ImgDirectionKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSMapDatum", optional=true)
    protected static native CFString MapDatumKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSDestLatitudeRef", optional=true)
    protected static native CFString DestLatitudeRefKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSDestLatitude", optional=true)
    protected static native CFString DestLatitudeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSDestLongitudeRef", optional=true)
    protected static native CFString DestLongitudeRefKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSDestLongitude", optional=true)
    protected static native CFString DestLongitudeKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSDestBearingRef", optional=true)
    protected static native CFString DestBearingRefKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSDestBearing", optional=true)
    protected static native CFString DestBearingKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSDestDistanceRef", optional=true)
    protected static native CFString DestDistanceRefKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSDestDistance", optional=true)
    protected static native CFString DestDistanceKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSProcessingMethod", optional=true)
    protected static native CFString ProcessingMethodKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSAreaInformation", optional=true)
    protected static native CFString AreaInformationKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSDateStamp", optional=true)
    protected static native CFString DateStampKey();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSDifferental", optional=true)
    protected static native CFString DifferentalKey();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @GlobalValue(symbol="kCGImagePropertyGPSHPositioningError", optional=true)
    protected static native CFString HPositioningErrorKey();
    /*</methods>*/
}
