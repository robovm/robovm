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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizerDelegate_Protocol/Reference/Reference.html">UIGestureRecognizerDelegate Protocol Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
public interface /*<name>*/ UIGestureRecognizerDelegate /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIGestureRecognizerDelegate/gestureRecognizerShouldBegin:">- (BOOL)gestureRecognizerShouldBegin:(UIGestureRecognizer *)gestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    boolean shouldBegin(UIGestureRecognizer gestureRecognizer);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIGestureRecognizerDelegate/gestureRecognizer:shouldReceiveTouch:">- (BOOL)gestureRecognizer:(UIGestureRecognizer *)gestureRecognizer   shouldReceiveTouch:(UITouch *)touch</a>
     * @since Available in iOS 3.2 and later.
     */
    boolean shouldReceiveTouch(UIGestureRecognizer gestureRecognizer, UITouch touch);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIGestureRecognizerDelegate_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UIGestureRecognizerDelegate/gestureRecognizer:shouldRecognizeSimultaneouslyWithGestureRecognizer:">- (BOOL)gestureRecognizer:(UIGestureRecognizer *)gestureRecognizer  shouldRecognizeSimultaneouslyWithGestureRecognizer:(UIGestureRecognizer *)otherGestureRecognizer</a>
     * @since Available in iOS 3.2 and later.
     */
    boolean shouldRecognizeSimultaneously(UIGestureRecognizer gestureRecognizer, UIGestureRecognizer otherGestureRecognizer);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UIGestureRecognizerDelegate {
        @NotImplemented("gestureRecognizerShouldBegin:") public boolean shouldBegin(UIGestureRecognizer gestureRecognizer) { throw new UnsupportedOperationException(); }
        @NotImplemented("gestureRecognizer:shouldReceiveTouch:") public boolean shouldReceiveTouch(UIGestureRecognizer gestureRecognizer, UITouch touch) { throw new UnsupportedOperationException(); }
        @NotImplemented("gestureRecognizer:shouldRecognizeSimultaneouslyWithGestureRecognizer:") public boolean shouldRecognizeSimultaneously(UIGestureRecognizer gestureRecognizer, UIGestureRecognizer otherGestureRecognizer) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("gestureRecognizerShouldBegin:") public static boolean shouldBegin(UIGestureRecognizerDelegate __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer) { return __self__.shouldBegin(gestureRecognizer); }
        @Callback @BindSelector("gestureRecognizer:shouldReceiveTouch:") public static boolean shouldReceiveTouch(UIGestureRecognizerDelegate __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer, UITouch touch) { return __self__.shouldReceiveTouch(gestureRecognizer, touch); }
        @Callback @BindSelector("gestureRecognizer:shouldRecognizeSimultaneouslyWithGestureRecognizer:") public static boolean shouldRecognizeSimultaneously(UIGestureRecognizerDelegate __self__, Selector __cmd__, UIGestureRecognizer gestureRecognizer, UIGestureRecognizer otherGestureRecognizer) { return __self__.shouldRecognizeSimultaneously(gestureRecognizer, otherGestureRecognizer); }
    }
    /*</callbacks>*/

}
