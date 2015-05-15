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
package org.robovm.apple.uikit;

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
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit")/*</annotations>*/
@Marshaler(NSString.AsStringMarshaler.class)
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIVideo/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    
    private static java.util.concurrent.atomic.AtomicLong id = new java.util.concurrent.atomic.AtomicLong();
    private static final Selector didFinishSaving = Selector.register("video:didFinishSavingWithError:contextInfo:");
    private static LongMap<ListenerWrapper> callbacks = new LongMap<>();
    private static class ListenerWrapper extends NSObject {
        private final VoidBlock2<String, NSError> callback;
        private ListenerWrapper(VoidBlock2<String, NSError> callback, long contextInfo) {
            this.callback = callback;
            
            synchronized (callbacks) {
                callbacks.put(contextInfo, this);
            }
        }
        @TypeEncoding("v@:@:^v")
        @Method(selector = "video:didFinishSavingWithError:contextInfo:")
        private void didFinishSaving(String videoPath, NSError error, @Pointer long contextInfo) {
            callback.invoke(videoPath, error);
            
            synchronized (callbacks) {
                callbacks.remove(contextInfo);
            }
        }
    }
    
    /*<bind>*/static { Bro.bind(UIVideo.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/

    public static boolean isCompatibleWithSavedPhotosAlbum(File videoPath) {
        return isCompatibleWithSavedPhotosAlbum(videoPath.getAbsolutePath());
    }
    
    public static void saveToPhotosAlbum(File videoPath, VoidBlock2<String, NSError> callback) {
        if (callback != null) {
            long context = id.getAndIncrement();
            ListenerWrapper l = new ListenerWrapper(callback, context);
            saveToPhotosAlbum(videoPath.getAbsolutePath(), l, didFinishSaving, context);
        } else {
            saveToPhotosAlbum(videoPath.getAbsolutePath(), null, null, 0);
        }
    }
    
    /*<methods>*/
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Bridge(symbol="UIVideoAtPathIsCompatibleWithSavedPhotosAlbum", optional=true)
    protected static native boolean isCompatibleWithSavedPhotosAlbum(String videoPath);
    /**
     * @since Available in iOS 3.1 and later.
     */
    @Bridge(symbol="UISaveVideoAtPathToSavedPhotosAlbum", optional=true)
    public static native void saveToPhotosAlbum(String videoPath, NSObject completionTarget, Selector completionSelector, @Pointer long contextInfo);
    /*</methods>*/
}
