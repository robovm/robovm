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
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    
    public interface VideoSaveListener {
        void didFinishSaving (String videoPath, NSError error);
    }
    
    private static java.util.concurrent.atomic.AtomicLong id = new java.util.concurrent.atomic.AtomicLong();
    private static final Selector video$didFinishSavingWithError$contextInfo$ = Selector.register("video:didFinishSavingWithError:contextInfo:");
    private static Map<Long, ListenerWrapper> listeners = new HashMap<>();
    private static class ListenerWrapper extends NSObject {
        private final VideoSaveListener listener;
        private ListenerWrapper(VideoSaveListener listener, long contextInfo) {
            this.listener = listener;
            
            synchronized (listeners) {
                listeners.put(contextInfo, this);
            }
        }
        @TypeEncoding("v@:@:^v")
        @Method(selector = "video:didFinishSavingWithError:contextInfo:")
        private void didFinishSaving(String videoPath, NSError error, @Pointer long contextInfo) {
            listener.didFinishSaving(videoPath, error);
            
            synchronized (listeners) {
                listeners.remove(contextInfo);
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
    
    public static void saveToPhotosAlbum(File videoPath, VideoSaveListener listener) {
        if (listener != null) {
            long context = id.getAndIncrement();
            ListenerWrapper l = new ListenerWrapper(listener, context);
            saveToPhotosAlbum(videoPath.getAbsolutePath(), l, video$didFinishSavingWithError$contextInfo$, context);
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
    protected static native void saveToPhotosAlbum(String videoPath, NSObject completionTarget, Selector completionSelector, @Pointer long contextInfo);
    /*</methods>*/
}
