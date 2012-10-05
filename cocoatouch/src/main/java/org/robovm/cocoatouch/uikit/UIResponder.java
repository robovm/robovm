/*
 * Copyright (C) 2012 RoboVM
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
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html">UIResponder Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIResponder /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIResponder /*</name>*/.class);
    }

    /*<constructors>*/
    public UIResponder() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instp/UIResponder/inputAccessoryView">@property (readonly, retain) UIView *inputAccessoryView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("inputAccessoryView") public native @Type("UIView *") UIView getInputAccessoryView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instp/UIResponder/inputView">@property (readonly, retain) UIView *inputView</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("inputView") public native @Type("UIView *") UIView getInputView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instp/UIResponder/undoManager">@property(readonly) NSUndoManager *undoManager</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("undoManager") public native @Type("NSUndoManager *") NSUndoManager getUndoManager();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/becomeFirstResponder">- (BOOL)becomeFirstResponder</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("becomeFirstResponder") public native @Type("BOOL") boolean becomeFirstResponder();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/canBecomeFirstResponder">- (BOOL)canBecomeFirstResponder</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("canBecomeFirstResponder") public native @Type("BOOL") boolean canBecomeFirstResponder();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/canPerformAction:withSender:">- (BOOL)canPerformAction:(SEL)action withSender:(id)sender</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("canPerformAction:withSender:") public native @Type("BOOL") boolean canPerformAction(@Type("SEL") Selector action, @Type("id") NSObject sender);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/canResignFirstResponder">- (BOOL)canResignFirstResponder</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("canResignFirstResponder") public native @Type("BOOL") boolean canResignFirstResponder();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/nextResponder">- (UIResponder *)nextResponder</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("nextResponder") public native @Type("UIResponder *") UIResponder getNextResponder();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/isFirstResponder">- (BOOL)isFirstResponder</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isFirstResponder") public native @Type("BOOL") boolean isFirstResponder();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/motionBegan:withEvent:">- (void)motionBegan:(UIEventSubtype)motion withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("motionBegan:withEvent:") public native @Type("void") void motionBegan(@Type("UIEventSubtype") UIEventSubtype motion, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/motionCancelled:withEvent:">- (void)motionCancelled:(UIEventSubtype)motion withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("motionCancelled:withEvent:") public native @Type("void") void motionCancelled(@Type("UIEventSubtype") UIEventSubtype motion, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/motionEnded:withEvent:">- (void)motionEnded:(UIEventSubtype)motion withEvent:(UIEvent *)event</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("motionEnded:withEvent:") public native @Type("void") void motionEnded(@Type("UIEventSubtype") UIEventSubtype motion, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/reloadInputViews">- (void)reloadInputViews</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("reloadInputViews") public native @Type("void") void reloadInputViews();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/remoteControlReceivedWithEvent:">- (void)remoteControlReceivedWithEvent:(UIEvent *)event</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("remoteControlReceivedWithEvent:") public native @Type("void") void remoteControlReceived(@Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/resignFirstResponder">- (BOOL)resignFirstResponder</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("resignFirstResponder") public native @Type("BOOL") boolean resignFirstResponder();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/touchesBegan:withEvent:">- (void)touchesBegan:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("touchesBegan:withEvent:") public native @Type("void") void touchesBegan(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/touchesCancelled:withEvent:">- (void)touchesCancelled:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("touchesCancelled:withEvent:") public native @Type("void") void touchesCancelled(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/touchesEnded:withEvent:">- (void)touchesEnded:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("touchesEnded:withEvent:") public native @Type("void") void touchesEnded(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIResponder_Class/Reference/Reference.html#//apple_ref/occ/instm/UIResponder/touchesMoved:withEvent:">- (void)touchesMoved:(NSSet *)touches withEvent:(UIEvent *)event</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("touchesMoved:withEvent:") public native @Type("void") void touchesMoved(@Type("NSSet *") NSSet touches, @Type("UIEvent *") UIEvent event);
    /*</methods>*/

}
