/*
 * Copyright (C) 2012 Trillian AB
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coredata.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html">UIResponder Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIResponder /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIResponder /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIResponder /*</name>*/.class);

    /*<constructors>*/
    protected UIResponder(SkipInit skipInit) { super(skipInit); }
    public UIResponder() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector inputAccessoryView = Selector.register("inputAccessoryView");
    @Bridge private native static UIView objc_getInputAccessoryView(UIResponder __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getInputAccessoryViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instp/UIResponder/inputAccessoryView">@property (readonly, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIView getInputAccessoryView() {
        if (customClass) { return objc_getInputAccessoryViewSuper(getSuper(), inputAccessoryView); } else { return objc_getInputAccessoryView(this, inputAccessoryView); }
    }
    
    private static final Selector inputView = Selector.register("inputView");
    @Bridge private native static UIView objc_getInputView(UIResponder __self__, Selector __cmd__);
    @Bridge private native static UIView objc_getInputViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instp/UIResponder/inputView">@property (readonly, retain) UIView *inputView</a>
     * @since Available in iOS 3.2 and later.
     */
    public UIView getInputView() {
        if (customClass) { return objc_getInputViewSuper(getSuper(), inputView); } else { return objc_getInputView(this, inputView); }
    }
    
    private static final Selector undoManager = Selector.register("undoManager");
    @Bridge private native static NSUndoManager objc_getUndoManager(UIResponder __self__, Selector __cmd__);
    @Bridge private native static NSUndoManager objc_getUndoManagerSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instp/UIResponder/undoManager">@property(readonly) NSUndoManager *undoManager</a>
     * @since Available in iOS 3.0 and later.
     */
    public NSUndoManager getUndoManager() {
        if (customClass) { return objc_getUndoManagerSuper(getSuper(), undoManager); } else { return objc_getUndoManager(this, undoManager); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector becomeFirstResponder = Selector.register("becomeFirstResponder");
    @Bridge private native static boolean objc_becomeFirstResponder(UIResponder __self__, Selector __cmd__);
    @Bridge private native static boolean objc_becomeFirstResponderSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/becomeFirstResponder">- (BOOL)becomeFirstResponder</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean becomeFirstResponder() {
        if (customClass) { return objc_becomeFirstResponderSuper(getSuper(), becomeFirstResponder); } else { return objc_becomeFirstResponder(this, becomeFirstResponder); }
    }
    
    private static final Selector canBecomeFirstResponder = Selector.register("canBecomeFirstResponder");
    @Bridge private native static boolean objc_canBecomeFirstResponder(UIResponder __self__, Selector __cmd__);
    @Bridge private native static boolean objc_canBecomeFirstResponderSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/canBecomeFirstResponder">- (BOOL)canBecomeFirstResponder</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean canBecomeFirstResponder() {
        if (customClass) { return objc_canBecomeFirstResponderSuper(getSuper(), canBecomeFirstResponder); } else { return objc_canBecomeFirstResponder(this, canBecomeFirstResponder); }
    }
    
    private static final Selector canPerformAction$withSender$ = Selector.register("canPerformAction:withSender:");
    @Bridge private native static boolean objc_canPerformAction(UIResponder __self__, Selector __cmd__, Selector action, NSObject sender);
    @Bridge private native static boolean objc_canPerformActionSuper(ObjCSuper __super__, Selector __cmd__, Selector action, NSObject sender);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/canPerformAction:withSender:">- (BOOL)canPerformAction:(SEL)action withSender:(id)sender</a>
     * @since Available in iOS 3.0 and later.
     */
    public boolean canPerformAction(Selector action, NSObject sender) {
        if (customClass) { return objc_canPerformActionSuper(getSuper(), canPerformAction$withSender$, action, sender); } else { return objc_canPerformAction(this, canPerformAction$withSender$, action, sender); }
    }
    
    private static final Selector canResignFirstResponder = Selector.register("canResignFirstResponder");
    @Bridge private native static boolean objc_canResignFirstResponder(UIResponder __self__, Selector __cmd__);
    @Bridge private native static boolean objc_canResignFirstResponderSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/canResignFirstResponder">- (BOOL)canResignFirstResponder</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean canResignFirstResponder() {
        if (customClass) { return objc_canResignFirstResponderSuper(getSuper(), canResignFirstResponder); } else { return objc_canResignFirstResponder(this, canResignFirstResponder); }
    }
    
    private static final Selector nextResponder = Selector.register("nextResponder");
    @Bridge private native static UIResponder objc_getNextResponder(UIResponder __self__, Selector __cmd__);
    @Bridge private native static UIResponder objc_getNextResponderSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/nextResponder">- (UIResponder *)nextResponder</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIResponder getNextResponder() {
        if (customClass) { return objc_getNextResponderSuper(getSuper(), nextResponder); } else { return objc_getNextResponder(this, nextResponder); }
    }
    
    private static final Selector isFirstResponder = Selector.register("isFirstResponder");
    @Bridge private native static boolean objc_isFirstResponder(UIResponder __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isFirstResponderSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/isFirstResponder">- (BOOL)isFirstResponder</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isFirstResponder() {
        if (customClass) { return objc_isFirstResponderSuper(getSuper(), isFirstResponder); } else { return objc_isFirstResponder(this, isFirstResponder); }
    }
    
    private static final Selector motionBegan$withEvent$ = Selector.register("motionBegan:withEvent:");
    @Bridge private native static void objc_motionBegan(UIResponder __self__, Selector __cmd__, UIEventSubtype motion, UIEvent event);
    @Bridge private native static void objc_motionBeganSuper(ObjCSuper __super__, Selector __cmd__, UIEventSubtype motion, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/motionBegan:withEvent:">- (void)motionBegan:(UIEventSubtype)motion withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.0 and later.
     */
    public void motionBegan(UIEventSubtype motion, UIEvent event) {
        if (customClass) { objc_motionBeganSuper(getSuper(), motionBegan$withEvent$, motion, event); } else { objc_motionBegan(this, motionBegan$withEvent$, motion, event); }
    }
    
    private static final Selector motionCancelled$withEvent$ = Selector.register("motionCancelled:withEvent:");
    @Bridge private native static void objc_motionCancelled(UIResponder __self__, Selector __cmd__, UIEventSubtype motion, UIEvent event);
    @Bridge private native static void objc_motionCancelledSuper(ObjCSuper __super__, Selector __cmd__, UIEventSubtype motion, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/motionCancelled:withEvent:">- (void)motionCancelled:(UIEventSubtype)motion withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.0 and later.
     */
    public void motionCancelled(UIEventSubtype motion, UIEvent event) {
        if (customClass) { objc_motionCancelledSuper(getSuper(), motionCancelled$withEvent$, motion, event); } else { objc_motionCancelled(this, motionCancelled$withEvent$, motion, event); }
    }
    
    private static final Selector motionEnded$withEvent$ = Selector.register("motionEnded:withEvent:");
    @Bridge private native static void objc_motionEnded(UIResponder __self__, Selector __cmd__, UIEventSubtype motion, UIEvent event);
    @Bridge private native static void objc_motionEndedSuper(ObjCSuper __super__, Selector __cmd__, UIEventSubtype motion, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/motionEnded:withEvent:">- (void)motionEnded:(UIEventSubtype)motion withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.0 and later.
     */
    public void motionEnded(UIEventSubtype motion, UIEvent event) {
        if (customClass) { objc_motionEndedSuper(getSuper(), motionEnded$withEvent$, motion, event); } else { objc_motionEnded(this, motionEnded$withEvent$, motion, event); }
    }
    
    private static final Selector reloadInputViews = Selector.register("reloadInputViews");
    @Bridge private native static void objc_reloadInputViews(UIResponder __self__, Selector __cmd__);
    @Bridge private native static void objc_reloadInputViewsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/reloadInputViews">- (void)reloadInputViews</a>
     * @since Available in iOS 3.2 and later.
     */
    public void reloadInputViews() {
        if (customClass) { objc_reloadInputViewsSuper(getSuper(), reloadInputViews); } else { objc_reloadInputViews(this, reloadInputViews); }
    }
    
    private static final Selector remoteControlReceivedWithEvent$ = Selector.register("remoteControlReceivedWithEvent:");
    @Bridge private native static void objc_remoteControlReceived(UIResponder __self__, Selector __cmd__, UIEvent event);
    @Bridge private native static void objc_remoteControlReceivedSuper(ObjCSuper __super__, Selector __cmd__, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/remoteControlReceivedWithEvent:">- (void)remoteControlReceivedWithEvent:(UIEvent *)event</a>
     * @since Available in iOS 4.0 and later.
     */
    public void remoteControlReceived(UIEvent event) {
        if (customClass) { objc_remoteControlReceivedSuper(getSuper(), remoteControlReceivedWithEvent$, event); } else { objc_remoteControlReceived(this, remoteControlReceivedWithEvent$, event); }
    }
    
    private static final Selector resignFirstResponder = Selector.register("resignFirstResponder");
    @Bridge private native static boolean objc_resignFirstResponder(UIResponder __self__, Selector __cmd__);
    @Bridge private native static boolean objc_resignFirstResponderSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/resignFirstResponder">- (BOOL)resignFirstResponder</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean resignFirstResponder() {
        if (customClass) { return objc_resignFirstResponderSuper(getSuper(), resignFirstResponder); } else { return objc_resignFirstResponder(this, resignFirstResponder); }
    }
    
    private static final Selector touchesBegan$withEvent$ = Selector.register("touchesBegan:withEvent:");
    @Bridge private native static void objc_touchesBegan(UIResponder __self__, Selector __cmd__, NSSet touches, UIEvent event);
    @Bridge private native static void objc_touchesBeganSuper(ObjCSuper __super__, Selector __cmd__, NSSet touches, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/touchesBegan:withEvent:">- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public void touchesBegan(NSSet touches, UIEvent event) {
        if (customClass) { objc_touchesBeganSuper(getSuper(), touchesBegan$withEvent$, touches, event); } else { objc_touchesBegan(this, touchesBegan$withEvent$, touches, event); }
    }
    
    private static final Selector touchesCancelled$withEvent$ = Selector.register("touchesCancelled:withEvent:");
    @Bridge private native static void objc_touchesCancelled(UIResponder __self__, Selector __cmd__, NSSet touches, UIEvent event);
    @Bridge private native static void objc_touchesCancelledSuper(ObjCSuper __super__, Selector __cmd__, NSSet touches, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/touchesCancelled:withEvent:">- (void)touchesCancelled:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public void touchesCancelled(NSSet touches, UIEvent event) {
        if (customClass) { objc_touchesCancelledSuper(getSuper(), touchesCancelled$withEvent$, touches, event); } else { objc_touchesCancelled(this, touchesCancelled$withEvent$, touches, event); }
    }
    
    private static final Selector touchesEnded$withEvent$ = Selector.register("touchesEnded:withEvent:");
    @Bridge private native static void objc_touchesEnded(UIResponder __self__, Selector __cmd__, NSSet touches, UIEvent event);
    @Bridge private native static void objc_touchesEndedSuper(ObjCSuper __super__, Selector __cmd__, NSSet touches, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/touchesEnded:withEvent:">- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public void touchesEnded(NSSet touches, UIEvent event) {
        if (customClass) { objc_touchesEndedSuper(getSuper(), touchesEnded$withEvent$, touches, event); } else { objc_touchesEnded(this, touchesEnded$withEvent$, touches, event); }
    }
    
    private static final Selector touchesMoved$withEvent$ = Selector.register("touchesMoved:withEvent:");
    @Bridge private native static void objc_touchesMoved(UIResponder __self__, Selector __cmd__, NSSet touches, UIEvent event);
    @Bridge private native static void objc_touchesMovedSuper(ObjCSuper __super__, Selector __cmd__, NSSet touches, UIEvent event);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/touchesMoved:withEvent:">- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    public void touchesMoved(NSSet touches, UIEvent event) {
        if (customClass) { objc_touchesMovedSuper(getSuper(), touchesMoved$withEvent$, touches, event); } else { objc_touchesMoved(this, touchesMoved$withEvent$, touches, event); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("inputAccessoryView") public static UIView getInputAccessoryView(UIResponder __self__, Selector __cmd__) { return __self__.getInputAccessoryView(); }
        @Callback @BindSelector("inputView") public static UIView getInputView(UIResponder __self__, Selector __cmd__) { return __self__.getInputView(); }
        @Callback @BindSelector("undoManager") public static NSUndoManager getUndoManager(UIResponder __self__, Selector __cmd__) { return __self__.getUndoManager(); }
        @Callback @BindSelector("becomeFirstResponder") public static boolean becomeFirstResponder(UIResponder __self__, Selector __cmd__) { return __self__.becomeFirstResponder(); }
        @Callback @BindSelector("canBecomeFirstResponder") public static boolean canBecomeFirstResponder(UIResponder __self__, Selector __cmd__) { return __self__.canBecomeFirstResponder(); }
        @Callback @BindSelector("canPerformAction:withSender:") public static boolean canPerformAction(UIResponder __self__, Selector __cmd__, Selector action, NSObject sender) { return __self__.canPerformAction(action, sender); }
        @Callback @BindSelector("canResignFirstResponder") public static boolean canResignFirstResponder(UIResponder __self__, Selector __cmd__) { return __self__.canResignFirstResponder(); }
        @Callback @BindSelector("nextResponder") public static UIResponder getNextResponder(UIResponder __self__, Selector __cmd__) { return __self__.getNextResponder(); }
        @Callback @BindSelector("isFirstResponder") public static boolean isFirstResponder(UIResponder __self__, Selector __cmd__) { return __self__.isFirstResponder(); }
        @Callback @BindSelector("motionBegan:withEvent:") public static void motionBegan(UIResponder __self__, Selector __cmd__, UIEventSubtype motion, UIEvent event) { __self__.motionBegan(motion, event); }
        @Callback @BindSelector("motionCancelled:withEvent:") public static void motionCancelled(UIResponder __self__, Selector __cmd__, UIEventSubtype motion, UIEvent event) { __self__.motionCancelled(motion, event); }
        @Callback @BindSelector("motionEnded:withEvent:") public static void motionEnded(UIResponder __self__, Selector __cmd__, UIEventSubtype motion, UIEvent event) { __self__.motionEnded(motion, event); }
        @Callback @BindSelector("reloadInputViews") public static void reloadInputViews(UIResponder __self__, Selector __cmd__) { __self__.reloadInputViews(); }
        @Callback @BindSelector("remoteControlReceivedWithEvent:") public static void remoteControlReceived(UIResponder __self__, Selector __cmd__, UIEvent event) { __self__.remoteControlReceived(event); }
        @Callback @BindSelector("resignFirstResponder") public static boolean resignFirstResponder(UIResponder __self__, Selector __cmd__) { return __self__.resignFirstResponder(); }
        @Callback @BindSelector("touchesBegan:withEvent:") public static void touchesBegan(UIResponder __self__, Selector __cmd__, NSSet touches, UIEvent event) { __self__.touchesBegan(touches, event); }
        @Callback @BindSelector("touchesCancelled:withEvent:") public static void touchesCancelled(UIResponder __self__, Selector __cmd__, NSSet touches, UIEvent event) { __self__.touchesCancelled(touches, event); }
        @Callback @BindSelector("touchesEnded:withEvent:") public static void touchesEnded(UIResponder __self__, Selector __cmd__, NSSet touches, UIEvent event) { __self__.touchesEnded(touches, event); }
        @Callback @BindSelector("touchesMoved:withEvent:") public static void touchesMoved(UIResponder __self__, Selector __cmd__, NSSet touches, UIEvent event) { __self__.touchesMoved(touches, event); }
    }
    /*</callbacks>*/

}
