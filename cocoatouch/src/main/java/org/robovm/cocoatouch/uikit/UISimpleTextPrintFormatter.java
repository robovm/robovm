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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html">UISimpleTextPrintFormatter Class Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UISimpleTextPrintFormatter /*</name>*/ 
    extends /*<extends>*/ UIPrintFormatter /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UISimpleTextPrintFormatter /*</name>*/.class);
    }

    /*<constructors>*/
    public UISimpleTextPrintFormatter() {}
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instm/UISimpleTextPrintFormatter/initWithText:">- (id)initWithText:(NSString *)text</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("initWithText:") public UISimpleTextPrintFormatter(@Type("NSString *") String text) {}
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/color">@property(nonatomic, retain) UIColor *color</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("color") public native @Type("UIColor *") UIColor getColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/color">@property(nonatomic, retain) UIColor *color</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setColor:") public native void setColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("font") public native @Type("UIFont *") UIFont getFont();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setFont:") public native void setFont(@Type("UIFont *") UIFont v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/lineBreakMode">@property(nonatomic) UILineBreakMode lineBreakMode</a>
     */
    @Bind("lineBreakMode") public native @Type("UILineBreakMode") UILineBreakMode getLineBreakMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/lineBreakMode">@property(nonatomic) UILineBreakMode lineBreakMode</a>
     */
    @Bind("setLineBreakMode:") public native void setLineBreakMode(@Type("UILineBreakMode") UILineBreakMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("text") public native @Type("NSString *") String getText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setText:") public native void setText(@Type("NSString *") String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/textAlignment">@property(nonatomic) UITextAlignment textAlignment</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("textAlignment") public native @Type("UITextAlignment") UITextAlignment getTextAlignment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/textAlignment">@property(nonatomic) UITextAlignment textAlignment</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setTextAlignment:") public native void setTextAlignment(@Type("UITextAlignment") UITextAlignment v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
