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
import org.robovm.rt.annotation.*;
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
/**
 * @since Available in iOS 3.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIGestureRecognizer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIGestureRecognizerPtr extends Ptr<UIGestureRecognizer, UIGestureRecognizerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIGestureRecognizer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    
    public interface OnGestureListener {
        void onGesture(UIGestureRecognizer gestureRecognizer);
    }
    
    private static final Selector handleGesture = Selector.register("handleGesture:");
    private static class ListenerWrapper extends NSObject {
        private final OnGestureListener listener;
        private ListenerWrapper(OnGestureListener listener) {
            this.listener = listener;
        }
        @Method(selector = "handleGesture:")
        private void handleGesture(UIGestureRecognizer gestureRecognizer) {
            listener.onGesture(gestureRecognizer);
        }
    }
    
    
    @SuppressWarnings("unchecked")
    private List<ListenerWrapper> getListeners(boolean create) {
        synchronized (UIGestureRecognizer.class) {
            List<ListenerWrapper> listeners = 
                    (List<ListenerWrapper>) getAssociatedObject(UIGestureRecognizer.class.getName() + ".listeners");
            if (listeners == null && create) {
                listeners = new LinkedList<ListenerWrapper>();
                setAssociatedObject(UIGestureRecognizer.class.getName() + ".listeners", listeners);
            }
            return listeners;
        }
    }
    public void addListener(OnGestureListener listener) {
        ListenerWrapper wrapper = new ListenerWrapper(listener);
        List<ListenerWrapper> listeners = getListeners(true);
        synchronized (listeners) {
            listeners.add(wrapper);
        }
        addTarget(wrapper, handleGesture);
    }
    
    public void removeListener(OnGestureListener listener) {
        List<ListenerWrapper> listeners = getListeners(false);
        if (listeners == null) {
            return;
        }
        synchronized (listeners) {
            for (Iterator<ListenerWrapper> it = listeners.iterator(); it.hasNext();) {
                ListenerWrapper wrapper = it.next();
                if (wrapper.listener == listener) {
                    removeTarget(wrapper, handleGesture);
                    it.remove();
                    break;
                }
            }
        }        
    }
    
    public UIGestureRecognizer(OnGestureListener listener) { 
        if (listener != null) addListener(listener);
    }
    /*<constructors>*/
    public UIGestureRecognizer() {}
    protected UIGestureRecognizer(SkipInit skipInit) { super(skipInit); }
    public UIGestureRecognizer(NSObject target, Selector action) { super((SkipInit) null); initObject(init(target, action)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "state")
    public native UIGestureRecognizerState getState();
    @Property(selector = "delegate")
    public native UIGestureRecognizerDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIGestureRecognizerDelegate v);
    @Property(selector = "isEnabled")
    public native boolean isEnabled();
    @Property(selector = "setEnabled:")
    public native void setEnabled(boolean v);
    @Property(selector = "view")
    public native UIView getView();
    @Property(selector = "cancelsTouchesInView")
    public native boolean cancelsTouchesInView();
    @Property(selector = "setCancelsTouchesInView:")
    public native void setCancelsTouchesInView(boolean v);
    @Property(selector = "delaysTouchesBegan")
    public native boolean delaysTouchesBegan();
    @Property(selector = "setDelaysTouchesBegan:")
    public native void setDelaysTouchesBegan(boolean v);
    @Property(selector = "delaysTouchesEnded")
    public native boolean delaysTouchesEnded();
    @Property(selector = "setDelaysTouchesEnded:")
    public native void setDelaysTouchesEnded(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithTarget:action:")
    protected native @Pointer long init(NSObject target, Selector action);
    @Method(selector = "addTarget:action:")
    public native void addTarget(NSObject target, Selector action);
    @Method(selector = "removeTarget:action:")
    public native void removeTarget(NSObject target, Selector action);
    @Method(selector = "requireGestureRecognizerToFail:")
    public native void requireGestureRecognizerToFail(UIGestureRecognizer otherGestureRecognizer);
    @Method(selector = "locationInView:")
    public native @ByVal CGPoint getLocationInView(UIView view);
    @Method(selector = "numberOfTouches")
    public native @MachineSizedUInt long getNumberOfTouches();
    @Method(selector = "locationOfTouch:inView:")
    public native @ByVal CGPoint getLocationOfTouch(@MachineSizedUInt long touchIndex, UIView view);
    /*</methods>*/
}
