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
package org.robovm.apple.mediaplayer;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 7.1 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("MediaPlayer") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/MPContentItem/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class MPContentItemPtr extends Ptr<MPContentItem, MPContentItemPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(MPContentItem.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public MPContentItem() {}
    protected MPContentItem(SkipInit skipInit) { super(skipInit); }
    public MPContentItem(String identifier) { super((SkipInit) null); initObject(init(identifier)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "identifier")
    public native String getIdentifier();
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "setTitle:")
    public native void setTitle(String v);
    @Property(selector = "subtitle")
    public native String getSubtitle();
    @Property(selector = "setSubtitle:")
    public native void setSubtitle(String v);
    @Property(selector = "artwork")
    public native MPMediaItemArtwork getArtwork();
    @Property(selector = "setArtwork:")
    public native void setArtwork(MPMediaItemArtwork v);
    @Property(selector = "isContainer")
    public native boolean isContainer();
    @Property(selector = "setContainer:")
    public native void setContainer(boolean v);
    @Property(selector = "isPlayable")
    public native boolean isPlayable();
    @Property(selector = "setPlayable:")
    public native void setPlayable(boolean v);
    @Property(selector = "playbackProgress")
    public native float getPlaybackProgress();
    @Property(selector = "setPlaybackProgress:")
    public native void setPlaybackProgress(float v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithIdentifier:")
    protected native @Pointer long init(String identifier);
    /*</methods>*/
}
