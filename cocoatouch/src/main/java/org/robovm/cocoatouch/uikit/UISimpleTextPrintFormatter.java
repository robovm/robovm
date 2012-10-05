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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UISimpleTextPrintFormatter /*</name>*/.class);

    /*<constructors>*/
    protected UISimpleTextPrintFormatter(SkipInit skipInit) { super(skipInit); }
    public UISimpleTextPrintFormatter() {}
    
    private static final Selector initWithText$ = Selector.register("initWithText:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithText(UISimpleTextPrintFormatter __self__, Selector __cmd__, String text);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instm/UISimpleTextPrintFormatter/initWithText:">- (id)initWithText:(NSString *)text</a>
     * @since Available in iOS 4.2 and later.
     */
    public UISimpleTextPrintFormatter(String text) {
        super((SkipInit) null);
        objc_initWithText(this, initWithText$, text);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/color">@property(nonatomic, retain) UIColor *color</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("color") public native UIColor getColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/color">@property(nonatomic, retain) UIColor *color</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setColor:") public native void setColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("font") public native UIFont getFont();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setFont:") public native void setFont(UIFont v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/lineBreakMode">@property(nonatomic) UILineBreakMode lineBreakMode</a>
     */
    @Bind("lineBreakMode") public native UILineBreakMode getLineBreakMode();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/lineBreakMode">@property(nonatomic) UILineBreakMode lineBreakMode</a>
     */
    @Bind("setLineBreakMode:") public native void setLineBreakMode(UILineBreakMode v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("text") public native String getText();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setText:") public native void setText(String v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/textAlignment">@property(nonatomic) UITextAlignment textAlignment</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("textAlignment") public native UITextAlignment getTextAlignment();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/textAlignment">@property(nonatomic) UITextAlignment textAlignment</a>
     * @since Available in iOS 4.2 and later.
     */
    @Bind("setTextAlignment:") public native void setTextAlignment(UITextAlignment v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
