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
/**
 * @since Available in iOS 4.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVMutableMetadataItem/*</name>*/ 
    extends /*<extends>*/AVMetadataItem/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVMutableMetadataItemPtr extends Ptr<AVMutableMetadataItem, AVMutableMetadataItemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVMutableMetadataItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVMutableMetadataItem() {}
    protected AVMutableMetadataItem(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "identifier")
    public native AVMetadataIdentifier getIdentifier();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setIdentifier:")
    public native void setIdentifier(AVMetadataIdentifier v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "extendedLanguageTag")
    public native String getExtendedLanguageTag();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setExtendedLanguageTag:")
    public native void setExtendedLanguageTag(String v);
    @Property(selector = "locale")
    public native NSLocale getLocale();
    @Property(selector = "setLocale:")
    public native void setLocale(NSLocale v);
    @Property(selector = "time")
    public native @ByVal CMTime getTime();
    @Property(selector = "setTime:")
    public native void setTime(@ByVal CMTime v);
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Property(selector = "duration")
    public native @ByVal CMTime getDuration();
    /**
     * @since Available in iOS 4.2 and later.
     */
    @Property(selector = "setDuration:")
    public native void setDuration(@ByVal CMTime v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "dataType")
    public native String getDataType();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setDataType:")
    public native void setDataType(String v);
    @Property(selector = "value")
    public native NSObject getValue();
    @Property(selector = "setValue:")
    public native void setValue(NSObject v);
    @Property(selector = "extraAttributes")
    public native NSDictionary<NSString, ?> getExtraAttributes();
    @Property(selector = "setExtraAttributes:")
    public native void setExtraAttributes(NSDictionary<NSString, ?> v);
    @Property(selector = "keySpace")
    public native String getKeySpace();
    @Property(selector = "setKeySpace:")
    public native void setKeySpace(String v);
    @Property(selector = "key")
    public native NSString getKey();
    @Property(selector = "setKey:")
    public native void setKey(NSString v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "metadataItem")
    public static native AVMutableMetadataItem create();
    /*</methods>*/
}
