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
 * @since Available in iOS 4.1 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVQueuePlayer/*</name>*/ 
    extends /*<extends>*/AVPlayer/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVQueuePlayerPtr extends Ptr<AVQueuePlayer, AVQueuePlayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVQueuePlayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVQueuePlayer() {}
    protected AVQueuePlayer(SkipInit skipInit) { super(skipInit); }
    public AVQueuePlayer(NSArray<AVPlayerItem> items) { super((SkipInit) null); initObject(init(items)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithItems:")
    protected native @Pointer long init(NSArray<AVPlayerItem> items);
    @Method(selector = "items")
    public native NSArray<AVPlayerItem> getItems();
    @Method(selector = "advanceToNextItem")
    public native void advanceToNextItem();
    @Method(selector = "canInsertItem:afterItem:")
    public native boolean canInsertItem(AVPlayerItem item, AVPlayerItem afterItem);
    @Method(selector = "insertItem:afterItem:")
    public native void insertItem(AVPlayerItem item, AVPlayerItem afterItem);
    @Method(selector = "removeItem:")
    public native void removeItem(AVPlayerItem item);
    @Method(selector = "removeAllItems")
    public native void removeAllItems();
    @Method(selector = "queuePlayerWithItems:")
    public static native AVQueuePlayer create(NSArray<AVPlayerItem> items);
    /*</methods>*/
}
