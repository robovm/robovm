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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html">UITextInputTokenizer Protocol Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
public interface /*<name>*/ UITextInputTokenizer /*</name>*/ /*<implements>*/ extends NSObjectProtocol /*</implements>*/ {

    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputTokenizer/positionFromPosition:toBoundary:inDirection:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position toBoundary:(UITextGranularity)granularity inDirection:(UITextDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextPosition getPosition(UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputTokenizer/rangeEnclosingPosition:withGranularity:inDirection:">- (UITextRange *)rangeEnclosingPosition:(UITextPosition *)position withGranularity:(UITextGranularity)granularity inDirection:(UITextDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    UITextRange getRangeEnclosingPosition(UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputTokenizer/isPosition:atBoundary:inDirection:">- (BOOL)isPosition:(UITextPosition *)position atBoundary:(UITextGranularity)granularity inDirection:(UITextDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    boolean isPositionAtBoundary(UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputTokenizer/isPosition:withinTextUnit:inDirection:">- (BOOL)isPosition:(UITextPosition *)position withinTextUnit:(UITextGranularity)granularity inDirection:(UITextDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    boolean isPositionWithinTextUnit(UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    /*</methods>*/
    /*<adapter>*/
    public static class Adapter extends NSObject implements UITextInputTokenizer {
        @NotImplemented("positionFromPosition:toBoundary:inDirection:") public UITextPosition getPosition(UITextPosition position, UITextGranularity granularity, UITextDirection direction) { throw new UnsupportedOperationException(); }
        @NotImplemented("rangeEnclosingPosition:withGranularity:inDirection:") public UITextRange getRangeEnclosingPosition(UITextPosition position, UITextGranularity granularity, UITextDirection direction) { throw new UnsupportedOperationException(); }
        @NotImplemented("isPosition:atBoundary:inDirection:") public boolean isPositionAtBoundary(UITextPosition position, UITextGranularity granularity, UITextDirection direction) { throw new UnsupportedOperationException(); }
        @NotImplemented("isPosition:withinTextUnit:inDirection:") public boolean isPositionWithinTextUnit(UITextPosition position, UITextGranularity granularity, UITextDirection direction) { throw new UnsupportedOperationException(); }
    }
    /*</adapter>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("positionFromPosition:toBoundary:inDirection:") public static UITextPosition getPosition(UITextInputTokenizer __self__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction) { return __self__.getPosition(position, granularity, direction); }
        @Callback @BindSelector("rangeEnclosingPosition:withGranularity:inDirection:") public static UITextRange getRangeEnclosingPosition(UITextInputTokenizer __self__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction) { return __self__.getRangeEnclosingPosition(position, granularity, direction); }
        @Callback @BindSelector("isPosition:atBoundary:inDirection:") public static boolean isPositionAtBoundary(UITextInputTokenizer __self__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction) { return __self__.isPositionAtBoundary(position, granularity, direction); }
        @Callback @BindSelector("isPosition:withinTextUnit:inDirection:") public static boolean isPositionWithinTextUnit(UITextInputTokenizer __self__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction) { return __self__.isPositionWithinTextUnit(position, granularity, direction); }
    }
    /*</callbacks>*/

}
