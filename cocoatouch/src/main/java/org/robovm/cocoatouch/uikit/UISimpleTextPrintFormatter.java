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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html">UISimpleTextPrintFormatter Class Reference</a>
 *   @since Available in iOS 4.2 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UISimpleTextPrintFormatter /*</name>*/ 
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
    @Bridge private native static @Pointer long objc_initWithText(UISimpleTextPrintFormatter __self__, Selector __cmd__, String text);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instm/UISimpleTextPrintFormatter/initWithText:">- (id)initWithText:(NSString *)text</a>
     * @since Available in iOS 4.2 and later.
     */
    public UISimpleTextPrintFormatter(String text) {
        super((SkipInit) null);
        initObject(objc_initWithText(this, initWithText$, text));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector color = Selector.register("color");
    @Bridge private native static UIColor objc_getColor(UISimpleTextPrintFormatter __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/color">@property(nonatomic, retain) UIColor *color</a>
     * @since Available in iOS 4.2 and later.
     */
    public UIColor getColor() {
        if (customClass) { return objc_getColorSuper(getSuper(), color); } else { return objc_getColor(this, color); }
    }
    
    private static final Selector setColor$ = Selector.register("setColor:");
    @Bridge private native static void objc_setColor(UISimpleTextPrintFormatter __self__, Selector __cmd__, UIColor color);
    @Bridge private native static void objc_setColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor color);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/color">@property(nonatomic, retain) UIColor *color</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setColor(UIColor color) {
        if (customClass) { objc_setColorSuper(getSuper(), setColor$, color); } else { objc_setColor(this, setColor$, color); }
    }
    
    private static final Selector font = Selector.register("font");
    @Bridge private native static UIFont objc_getFont(UISimpleTextPrintFormatter __self__, Selector __cmd__);
    @Bridge private native static UIFont objc_getFontSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 4.2 and later.
     */
    public UIFont getFont() {
        if (customClass) { return objc_getFontSuper(getSuper(), font); } else { return objc_getFont(this, font); }
    }
    
    private static final Selector setFont$ = Selector.register("setFont:");
    @Bridge private native static void objc_setFont(UISimpleTextPrintFormatter __self__, Selector __cmd__, UIFont font);
    @Bridge private native static void objc_setFontSuper(ObjCSuper __super__, Selector __cmd__, UIFont font);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/font">@property(nonatomic, retain) UIFont *font</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setFont(UIFont font) {
        if (customClass) { objc_setFontSuper(getSuper(), setFont$, font); } else { objc_setFont(this, setFont$, font); }
    }
    
    private static final Selector lineBreakMode = Selector.register("lineBreakMode");
    @Bridge private native static UILineBreakMode objc_getLineBreakMode(UISimpleTextPrintFormatter __self__, Selector __cmd__);
    @Bridge private native static UILineBreakMode objc_getLineBreakModeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/lineBreakMode">@property(nonatomic) UILineBreakMode lineBreakMode</a>
     */
    public UILineBreakMode getLineBreakMode() {
        if (customClass) { return objc_getLineBreakModeSuper(getSuper(), lineBreakMode); } else { return objc_getLineBreakMode(this, lineBreakMode); }
    }
    
    private static final Selector setLineBreakMode$ = Selector.register("setLineBreakMode:");
    @Bridge private native static void objc_setLineBreakMode(UISimpleTextPrintFormatter __self__, Selector __cmd__, UILineBreakMode lineBreakMode);
    @Bridge private native static void objc_setLineBreakModeSuper(ObjCSuper __super__, Selector __cmd__, UILineBreakMode lineBreakMode);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/lineBreakMode">@property(nonatomic) UILineBreakMode lineBreakMode</a>
     */
    public void setLineBreakMode(UILineBreakMode lineBreakMode) {
        if (customClass) { objc_setLineBreakModeSuper(getSuper(), setLineBreakMode$, lineBreakMode); } else { objc_setLineBreakMode(this, setLineBreakMode$, lineBreakMode); }
    }
    
    private static final Selector text = Selector.register("text");
    @Bridge private native static String objc_getText(UISimpleTextPrintFormatter __self__, Selector __cmd__);
    @Bridge private native static String objc_getTextSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 4.2 and later.
     */
    public String getText() {
        if (customClass) { return objc_getTextSuper(getSuper(), text); } else { return objc_getText(this, text); }
    }
    
    private static final Selector setText$ = Selector.register("setText:");
    @Bridge private native static void objc_setText(UISimpleTextPrintFormatter __self__, Selector __cmd__, String text);
    @Bridge private native static void objc_setTextSuper(ObjCSuper __super__, Selector __cmd__, String text);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/text">@property(nonatomic, copy) NSString *text</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setText(String text) {
        if (customClass) { objc_setTextSuper(getSuper(), setText$, text); } else { objc_setText(this, setText$, text); }
    }
    
    private static final Selector textAlignment = Selector.register("textAlignment");
    @Bridge private native static UITextAlignment objc_getTextAlignment(UISimpleTextPrintFormatter __self__, Selector __cmd__);
    @Bridge private native static UITextAlignment objc_getTextAlignmentSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/textAlignment">@property(nonatomic) UITextAlignment textAlignment</a>
     * @since Available in iOS 4.2 and later.
     */
    public UITextAlignment getTextAlignment() {
        if (customClass) { return objc_getTextAlignmentSuper(getSuper(), textAlignment); } else { return objc_getTextAlignment(this, textAlignment); }
    }
    
    private static final Selector setTextAlignment$ = Selector.register("setTextAlignment:");
    @Bridge private native static void objc_setTextAlignment(UISimpleTextPrintFormatter __self__, Selector __cmd__, UITextAlignment textAlignment);
    @Bridge private native static void objc_setTextAlignmentSuper(ObjCSuper __super__, Selector __cmd__, UITextAlignment textAlignment);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UISimpleTextPrintFormatter_Class/Reference/Reference.html#//apple_ref/occ/instp/UISimpleTextPrintFormatter/textAlignment">@property(nonatomic) UITextAlignment textAlignment</a>
     * @since Available in iOS 4.2 and later.
     */
    public void setTextAlignment(UITextAlignment textAlignment) {
        if (customClass) { objc_setTextAlignmentSuper(getSuper(), setTextAlignment$, textAlignment); } else { objc_setTextAlignment(this, setTextAlignment$, textAlignment); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("color") public static UIColor getColor(UISimpleTextPrintFormatter __self__, Selector __cmd__) { return __self__.getColor(); }
        @Callback @BindSelector("setColor:") public static void setColor(UISimpleTextPrintFormatter __self__, Selector __cmd__, UIColor color) { __self__.setColor(color); }
        @Callback @BindSelector("font") public static UIFont getFont(UISimpleTextPrintFormatter __self__, Selector __cmd__) { return __self__.getFont(); }
        @Callback @BindSelector("setFont:") public static void setFont(UISimpleTextPrintFormatter __self__, Selector __cmd__, UIFont font) { __self__.setFont(font); }
        @Callback @BindSelector("lineBreakMode") public static UILineBreakMode getLineBreakMode(UISimpleTextPrintFormatter __self__, Selector __cmd__) { return __self__.getLineBreakMode(); }
        @Callback @BindSelector("setLineBreakMode:") public static void setLineBreakMode(UISimpleTextPrintFormatter __self__, Selector __cmd__, UILineBreakMode lineBreakMode) { __self__.setLineBreakMode(lineBreakMode); }
        @Callback @BindSelector("text") public static String getText(UISimpleTextPrintFormatter __self__, Selector __cmd__) { return __self__.getText(); }
        @Callback @BindSelector("setText:") public static void setText(UISimpleTextPrintFormatter __self__, Selector __cmd__, String text) { __self__.setText(text); }
        @Callback @BindSelector("textAlignment") public static UITextAlignment getTextAlignment(UISimpleTextPrintFormatter __self__, Selector __cmd__) { return __self__.getTextAlignment(); }
        @Callback @BindSelector("setTextAlignment:") public static void setTextAlignment(UISimpleTextPrintFormatter __self__, Selector __cmd__, UITextAlignment textAlignment) { __self__.setTextAlignment(textAlignment); }
    }
    /*</callbacks>*/

}
