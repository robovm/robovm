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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputStringTokenizer_Class/Reference/Reference.html">UITextInputStringTokenizer Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITextInputStringTokenizer /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ implements UITextInputTokenizer /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITextInputStringTokenizer /*</name>*/.class);
    }

    /*<constructors>*/
    public UITextInputStringTokenizer() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputStringTokenizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UITextInputStringTokenizer/initWithTextInput:">- (id)initWithTextInput:(UIResponder &amp;lt; UITextInput &amp;gt; *)textInput</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("initWithTextInput:") public UITextInputStringTokenizer(@Type("UIResponder < UITextInput > *") UITextInput textInput) {}
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputTokenizer/positionFromPosition:toBoundary:inDirection:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position toBoundary:(UITextGranularity)granularity inDirection:(UITextDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("positionFromPosition:toBoundary:inDirection:") public native @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextPosition *") UITextPosition position, @Type("UITextGranularity") UITextGranularity granularity, @Type("UITextDirection") UITextDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputTokenizer/rangeEnclosingPosition:withGranularity:inDirection:">- (UITextRange *)rangeEnclosingPosition:(UITextPosition *)position withGranularity:(UITextGranularity)granularity inDirection:(UITextDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("rangeEnclosingPosition:withGranularity:inDirection:") public native @Type("UITextRange *") UITextRange getRangeEnclosingPosition(@Type("UITextPosition *") UITextPosition position, @Type("UITextGranularity") UITextGranularity granularity, @Type("UITextDirection") UITextDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputTokenizer/isPosition:atBoundary:inDirection:">- (BOOL)isPosition:(UITextPosition *)position atBoundary:(UITextGranularity)granularity inDirection:(UITextDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("isPosition:atBoundary:inDirection:") public native @Type("BOOL") boolean isPositionAtBoundary(@Type("UITextPosition *") UITextPosition position, @Type("UITextGranularity") UITextGranularity granularity, @Type("UITextDirection") UITextDirection direction);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputTokenizer/isPosition:withinTextUnit:inDirection:">- (BOOL)isPosition:(UITextPosition *)position withinTextUnit:(UITextGranularity)granularity inDirection:(UITextDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    @Bind("isPosition:withinTextUnit:inDirection:") public native @Type("BOOL") boolean isPositionWithinTextUnit(@Type("UITextPosition *") UITextPosition position, @Type("UITextGranularity") UITextGranularity granularity, @Type("UITextDirection") UITextDirection direction);
    /*</methods>*/

}
