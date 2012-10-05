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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html#//apple_ref/occ/instp/UITextSelectionRect/containsEnd">@property (nonatomic, readonly) BOOL containsEnd</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("containsEnd") public native boolean isContainsEnd();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html#//apple_ref/occ/instp/UITextSelectionRect/containsStart">@property (nonatomic, readonly) BOOL containsStart</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("containsStart") public native boolean isContainsStart();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html#//apple_ref/occ/instp/UITextSelectionRect/range">@property (nonatomic, readonly) UITextRange *range</a>
     */
    @Bind("range") public native UITextRange getRange();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html#//apple_ref/occ/instp/UITextSelectionRect/rect">@property (nonatomic, readonly) CGRect rect</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("rect") public native CGRect getRect();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html#//apple_ref/occ/instp/UITextSelectionRect/isVertical">@property (nonatomic, readonly) BOOL isVertical</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("isVertical") public native boolean isVertical();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITextSelectionRect_class/Reference/Reference.html#//apple_ref/occ/instp/UITextSelectionRect/writingDirection">@property (nonatomic, readonly) UITextWritingDirection writingDirection</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("writingDirection") public native UITextWritingDirection getWritingDirection();
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
