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
package org.robovm.apple.coreanimation;

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
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.opengles.*;
import org.robovm.apple.metal.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("QuartzCore") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/CADisplayLink/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    public interface OnUpdateListener {
        void onUpdate(CADisplayLink displayLink);
    }
    
    private static final LongMap<ListenerWrapper> listeners = new LongMap<>();
    private static final Selector handleUpdate = Selector.register("handleUpdate");
    private static class ListenerWrapper extends NSObject {
        private final OnUpdateListener listener;
        private ListenerWrapper(OnUpdateListener listener) {
            this.listener = listener;
        }
        @Method(selector = "handleUpdate")
        private void handleUpdate(CADisplayLink displayLink) {
            listener.onUpdate(displayLink);
        }
    }
    
    /*<ptr>*/public static class CADisplayLinkPtr extends Ptr<CADisplayLink, CADisplayLinkPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(CADisplayLink.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    protected CADisplayLink(SkipInit skipInit) { super(skipInit); }
    public CADisplayLink(NSObject target, Selector sel) { super(create(target, sel)); retain(getHandle()); }
    /*</constructors>*/
    public CADisplayLink(OnUpdateListener listener) {
        super(create(listener));
        retain(getHandle());
    }
    
    private static long create(OnUpdateListener listener) {
        if (listener == null) {
            throw new NullPointerException("listener");
        }
        ListenerWrapper l = new ListenerWrapper(listener);
        long result = create(l, handleUpdate);
        CADisplayLink.listeners.put(result, l);
        return result;
    }
    /*<properties>*/
    @Property(selector = "timestamp")
    public native double getTimestamp();
    @Property(selector = "duration")
    public native double getDuration();
    @Property(selector = "isPaused")
    public native boolean isPaused();
    @Property(selector = "setPaused:")
    public native void setPaused(boolean v);
    @Property(selector = "frameInterval")
    public native @MachineSizedSInt long getFrameInterval();
    @Property(selector = "setFrameInterval:")
    public native void setFrameInterval(@MachineSizedSInt long v);
    /*</properties>*/
    /*<members>*//*</members>*/
    public void addToRunLoop(NSRunLoop runloop, NSRunLoopMode mode) {
        addToRunLoop(runloop, mode.value().toString());
    }
    public void removeFromRunLoop(NSRunLoop runloop, NSRunLoopMode mode) {
        removeFromRunLoop(runloop, mode.value().toString());
    }
    
    @Override
    protected void dispose(boolean finalizing) {
        CADisplayLink.listeners.remove(getHandle());
        super.dispose(finalizing);
    }
    /*<methods>*/
    @Method(selector = "addToRunLoop:forMode:")
    public native void addToRunLoop(NSRunLoop runloop, String mode);
    @Method(selector = "removeFromRunLoop:forMode:")
    public native void removeFromRunLoop(NSRunLoop runloop, String mode);
    @Method(selector = "invalidate")
    public native void invalidate();
    @Method(selector = "displayLinkWithTarget:selector:")
    protected static native @Pointer long create(NSObject target, Selector sel);
    /*</methods>*/
}
