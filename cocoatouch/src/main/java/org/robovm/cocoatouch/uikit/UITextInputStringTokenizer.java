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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputStringTokenizer_Class/Reference/Reference.html">UITextInputStringTokenizer Class Reference</a>
 *   @since Available in iOS 3.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UITextInputStringTokenizer /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ implements UITextInputTokenizer /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITextInputStringTokenizer /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITextInputStringTokenizer /*</name>*/.class);

    /*<constructors>*/
    protected UITextInputStringTokenizer(SkipInit skipInit) { super(skipInit); }
    public UITextInputStringTokenizer() {}
    
    private static final Selector initWithTextInput$ = Selector.register("initWithTextInput:");
    @Bridge private native static @Pointer long objc_initWithTextInput(UITextInputStringTokenizer __self__, Selector __cmd__, UITextInput textInput);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputStringTokenizer_Class/Reference/Reference.html#//apple_ref/occ/instm/UITextInputStringTokenizer/initWithTextInput:">- (id)initWithTextInput:(UIResponder &amp;lt; UITextInput &amp;gt; *)textInput</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextInputStringTokenizer(UITextInput textInput) {
        super((SkipInit) null);
        initObject(objc_initWithTextInput(this, initWithTextInput$, textInput));
    }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector positionFromPosition$toBoundary$inDirection$ = Selector.register("positionFromPosition:toBoundary:inDirection:");
    @Bridge private native static UITextPosition objc_getPosition(UITextInputStringTokenizer __self__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    @Bridge private native static UITextPosition objc_getPositionSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputTokenizer/positionFromPosition:toBoundary:inDirection:">- (UITextPosition *)positionFromPosition:(UITextPosition *)position toBoundary:(UITextGranularity)granularity inDirection:(UITextDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextPosition getPosition(UITextPosition position, UITextGranularity granularity, UITextDirection direction) {
        if (customClass) { return objc_getPositionSuper(getSuper(), positionFromPosition$toBoundary$inDirection$, position, granularity, direction); } else { return objc_getPosition(this, positionFromPosition$toBoundary$inDirection$, position, granularity, direction); }
    }
    
    private static final Selector rangeEnclosingPosition$withGranularity$inDirection$ = Selector.register("rangeEnclosingPosition:withGranularity:inDirection:");
    @Bridge private native static UITextRange objc_getRangeEnclosingPosition(UITextInputStringTokenizer __self__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    @Bridge private native static UITextRange objc_getRangeEnclosingPositionSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputTokenizer/rangeEnclosingPosition:withGranularity:inDirection:">- (UITextRange *)rangeEnclosingPosition:(UITextPosition *)position withGranularity:(UITextGranularity)granularity inDirection:(UITextDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public UITextRange getRangeEnclosingPosition(UITextPosition position, UITextGranularity granularity, UITextDirection direction) {
        if (customClass) { return objc_getRangeEnclosingPositionSuper(getSuper(), rangeEnclosingPosition$withGranularity$inDirection$, position, granularity, direction); } else { return objc_getRangeEnclosingPosition(this, rangeEnclosingPosition$withGranularity$inDirection$, position, granularity, direction); }
    }
    
    private static final Selector isPosition$atBoundary$inDirection$ = Selector.register("isPosition:atBoundary:inDirection:");
    @Bridge private native static boolean objc_isPositionAtBoundary(UITextInputStringTokenizer __self__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    @Bridge private native static boolean objc_isPositionAtBoundarySuper(ObjCSuper __super__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputTokenizer/isPosition:atBoundary:inDirection:">- (BOOL)isPosition:(UITextPosition *)position atBoundary:(UITextGranularity)granularity inDirection:(UITextDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isPositionAtBoundary(UITextPosition position, UITextGranularity granularity, UITextDirection direction) {
        if (customClass) { return objc_isPositionAtBoundarySuper(getSuper(), isPosition$atBoundary$inDirection$, position, granularity, direction); } else { return objc_isPositionAtBoundary(this, isPosition$atBoundary$inDirection$, position, granularity, direction); }
    }
    
    private static final Selector isPosition$withinTextUnit$inDirection$ = Selector.register("isPosition:withinTextUnit:inDirection:");
    @Bridge private native static boolean objc_isPositionWithinTextUnit(UITextInputStringTokenizer __self__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    @Bridge private native static boolean objc_isPositionWithinTextUnitSuper(ObjCSuper __super__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextInputTokenizer_Protocol/Reference/Reference.html#//apple_ref/occ/intfm/UITextInputTokenizer/isPosition:withinTextUnit:inDirection:">- (BOOL)isPosition:(UITextPosition *)position withinTextUnit:(UITextGranularity)granularity inDirection:(UITextDirection)direction</a>
     * @since Available in iOS 3.2 and later.
     */
    public boolean isPositionWithinTextUnit(UITextPosition position, UITextGranularity granularity, UITextDirection direction) {
        if (customClass) { return objc_isPositionWithinTextUnitSuper(getSuper(), isPosition$withinTextUnit$inDirection$, position, granularity, direction); } else { return objc_isPositionWithinTextUnit(this, isPosition$withinTextUnit$inDirection$, position, granularity, direction); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("positionFromPosition:toBoundary:inDirection:") public static UITextPosition getPosition(UITextInputStringTokenizer __self__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction) { return __self__.getPosition(position, granularity, direction); }
        @Callback @BindSelector("rangeEnclosingPosition:withGranularity:inDirection:") public static UITextRange getRangeEnclosingPosition(UITextInputStringTokenizer __self__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction) { return __self__.getRangeEnclosingPosition(position, granularity, direction); }
        @Callback @BindSelector("isPosition:atBoundary:inDirection:") public static boolean isPositionAtBoundary(UITextInputStringTokenizer __self__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction) { return __self__.isPositionAtBoundary(position, granularity, direction); }
        @Callback @BindSelector("isPosition:withinTextUnit:inDirection:") public static boolean isPositionWithinTextUnit(UITextInputStringTokenizer __self__, Selector __cmd__, UITextPosition position, UITextGranularity granularity, UITextDirection direction) { return __self__.isPositionWithinTextUnit(position, granularity, direction); }
    }
    /*</callbacks>*/

}
