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
/*<visibility>*/public final/*</visibility>*/ class /*<name>*/NSCoderExtensions/*</name>*/ 
    extends /*<extends>*/NSExtensions/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSCoderExtensions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    private NSCoderExtensions() {}
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "encodeCMTime:forKey:")
    public static native void encodeCMTime(NSCoder thiz, @ByVal CMTime time, String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "decodeCMTimeForKey:")
    public static native @ByVal CMTime decodeCMTime(NSCoder thiz, String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "encodeCMTimeRange:forKey:")
    public static native void encodeCMTimeRange(NSCoder thiz, @ByVal CMTimeRange timeRange, String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "decodeCMTimeRangeForKey:")
    public static native @ByVal CMTimeRange decodeCMTimeRange(NSCoder thiz, String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "encodeCMTimeMapping:forKey:")
    public static native void encodeCMTimeMapping(NSCoder thiz, @ByVal CMTimeMapping timeMapping, String key);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "decodeCMTimeMappingForKey:")
    public static native @ByVal CMTimeMapping decodeCMTimeMapping(NSCoder thiz, String key);
    /*</methods>*/
}
