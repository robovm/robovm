/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVFileType/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(AVFileType.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeQuickTimeMovie", optional=true)
    public static native String QuickTimeMovie();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeMPEG4", optional=true)
    public static native String MPEG4();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAppleM4V", optional=true)
    public static native String AppleM4V();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAppleM4A", optional=true)
    public static native String AppleM4A();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileType3GPP", optional=true)
    public static native String _3GPP();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVFileType3GPP2", optional=true)
    public static native String _3GPP2();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeCoreAudioFormat", optional=true)
    public static native String CoreAudioFormat();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeWAVE", optional=true)
    public static native String WAVE();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAIFF", optional=true)
    public static native String AIFF();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAIFC", optional=true)
    public static native String AIFC();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAMR", optional=true)
    public static native String AMR();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeMPEGLayer3", optional=true)
    public static native String MPEGLayer3();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeSunAU", optional=true)
    public static native String SunAU();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="AVFileTypeAC3", optional=true)
    public static native String AC3();
    /*</methods>*/
}
