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
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html">UITextSelectionRect Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITextSelectionRect /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITextSelectionRect /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITextSelectionRect /*</name>*/.class);

    /*<constructors>*/
    protected UITextSelectionRect(SkipInit skipInit) { super(skipInit); }
    public UITextSelectionRect() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector containsEnd = Selector.register("containsEnd");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isContainsEnd(UITextSelectionRect __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isContainsEndSuper(ObjCSuper __super__, UITextSelectionRect __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html#//apple_ref/occ/instp/UITextSelectionRect/containsEnd">@property (nonatomic, readonly) BOOL containsEnd</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isContainsEnd() {
        if (customClass) { return objc_isContainsEndSuper(getSuper(), this, containsEnd); } else { return objc_isContainsEnd(this, containsEnd); }
    }
    
    private static final Selector containsStart = Selector.register("containsStart");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isContainsStart(UITextSelectionRect __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isContainsStartSuper(ObjCSuper __super__, UITextSelectionRect __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html#//apple_ref/occ/instp/UITextSelectionRect/containsStart">@property (nonatomic, readonly) BOOL containsStart</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isContainsStart() {
        if (customClass) { return objc_isContainsStartSuper(getSuper(), this, containsStart); } else { return objc_isContainsStart(this, containsStart); }
    }
    
    private static final Selector range = Selector.register("range");
    @Bridge(symbol = "objc_msgSend") private native static UITextRange objc_getRange(UITextSelectionRect __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextRange objc_getRangeSuper(ObjCSuper __super__, UITextSelectionRect __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html#//apple_ref/occ/instp/UITextSelectionRect/range">@property (nonatomic, readonly) UITextRange *range</a>
     */
    public UITextRange getRange() {
        if (customClass) { return objc_getRangeSuper(getSuper(), this, range); } else { return objc_getRange(this, range); }
    }
    
    private static final Selector rect = Selector.register("rect");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getRect(UITextSelectionRect __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getRectSuper(ObjCSuper __super__, UITextSelectionRect __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html#//apple_ref/occ/instp/UITextSelectionRect/rect">@property (nonatomic, readonly) CGRect rect</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGRect getRect() {
        if (customClass) { return objc_getRectSuper(getSuper(), this, rect); } else { return objc_getRect(this, rect); }
    }
    
    private static final Selector isVertical = Selector.register("isVertical");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isVertical(UITextSelectionRect __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isVerticalSuper(ObjCSuper __super__, UITextSelectionRect __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html#//apple_ref/occ/instp/UITextSelectionRect/isVertical">@property (nonatomic, readonly) BOOL isVertical</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isVertical() {
        if (customClass) { return objc_isVerticalSuper(getSuper(), this, isVertical); } else { return objc_isVertical(this, isVertical); }
    }
    
    private static final Selector writingDirection = Selector.register("writingDirection");
    @Bridge(symbol = "objc_msgSend") private native static UITextWritingDirection objc_getWritingDirection(UITextSelectionRect __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UITextWritingDirection objc_getWritingDirectionSuper(ObjCSuper __super__, UITextSelectionRect __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html#//apple_ref/occ/instp/UITextSelectionRect/writingDirection">@property (nonatomic, readonly) UITextWritingDirection writingDirection</a>
     * @since Available in iOS 6.0 and later.
     */
    public UITextWritingDirection getWritingDirection() {
        if (customClass) { return objc_getWritingDirectionSuper(getSuper(), this, writingDirection); } else { return objc_getWritingDirection(this, writingDirection); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
