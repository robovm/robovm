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
package org.robovm.apple.gamekit;

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
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.1 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("GameKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/GKPlayer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {
    
    public static class Notifications {
        public static NSObject observeDidChange(GKPlayer object, final VoidBlock1<GKPlayer> block) {
            return NSNotificationCenter.getDefaultCenter().addObserver(DidChangeNotificationName(), object, NSOperationQueue.getMainQueue(), new VoidBlock1<NSNotification>() {
                @Override
                public void invoke(NSNotification a) {
                    block.invoke((GKPlayer) a.getObject());
                }
            });
        }
    }
    /*<ptr>*/public static class GKPlayerPtr extends Ptr<GKPlayer, GKPlayerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(GKPlayer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public GKPlayer() {}
    protected GKPlayer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "playerID")
    public native String getPlayerID();
    @Property(selector = "isFriend")
    public native boolean isFriend();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "displayName")
    public native String getDisplayName();
    @Property(selector = "alias")
    public native String getAlias();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="GKPlayerDidChangeNotificationName", optional=true)
    public static native NSString DidChangeNotificationName();
    
    @Method(selector = "loadPlayersForIdentifiers:withCompletionHandler:")
    public static native void loadPlayers(NSArray<NSString> identifiers, @Block VoidBlock2<NSArray<GKPlayer>, NSError> completionHandler);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "loadPhotoForSize:withCompletionHandler:")
    public native void loadPhoto(GKPhotoSize size, @Block VoidBlock2<UIImage, NSError> completionHandler);
    /*</methods>*/
}
