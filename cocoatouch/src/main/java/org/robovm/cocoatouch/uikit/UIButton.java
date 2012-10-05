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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html">UIButton Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIButton /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIButton /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIButton /*</name>*/.class);

    /*<constructors>*/
    protected UIButton(SkipInit skipInit) { super(skipInit); }
    public UIButton() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenDisabled">@property(nonatomic) BOOL adjustsImageWhenDisabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("adjustsImageWhenDisabled") public native boolean isAdjustsImageWhenDisabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenDisabled">@property(nonatomic) BOOL adjustsImageWhenDisabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAdjustsImageWhenDisabled:") public native void setAdjustsImageWhenDisabled(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenHighlighted">@property(nonatomic) BOOL adjustsImageWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("adjustsImageWhenHighlighted") public native boolean isAdjustsImageWhenHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenHighlighted">@property(nonatomic) BOOL adjustsImageWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAdjustsImageWhenHighlighted:") public native void setAdjustsImageWhenHighlighted(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/buttonType">@property(nonatomic, readonly) UIButtonType buttonType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("buttonType") public native UIButtonType getButtonType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/contentEdgeInsets">@property(nonatomic) UIEdgeInsets contentEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentEdgeInsets") public native UIEdgeInsets getContentEdgeInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/contentEdgeInsets">@property(nonatomic) UIEdgeInsets contentEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentEdgeInsets:") public native void setContentEdgeInsets(UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentAttributedTitle">@property(nonatomic,readonly,retain) NSAttributedString *currentAttributedTitle</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("currentAttributedTitle") public native NSAttributedString getCurrentAttributedTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentBackgroundImage">@property(nonatomic, readonly, retain) UIImage *currentBackgroundImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentBackgroundImage") public native UIImage getCurrentBackgroundImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentImage">@property(nonatomic, readonly, retain) UIImage *currentImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentImage") public native UIImage getCurrentImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentTitle">@property(nonatomic, readonly, retain) NSString *currentTitle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentTitle") public native String getCurrentTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentTitleColor">@property(nonatomic, readonly, retain) UIColor *currentTitleColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentTitleColor") public native UIColor getCurrentTitleColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentTitleShadowColor">@property(nonatomic, readonly, retain) UIColor *currentTitleShadowColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentTitleShadowColor") public native UIColor getCurrentTitleShadowColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/imageEdgeInsets">@property(nonatomic) UIEdgeInsets imageEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("imageEdgeInsets") public native UIEdgeInsets getImageEdgeInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/imageEdgeInsets">@property(nonatomic) UIEdgeInsets imageEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setImageEdgeInsets:") public native void setImageEdgeInsets(UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/imageView">@property(nonatomic, readonly, retain) UIImageView *imageView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("imageView") public native UIImageView getImageView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/reversesTitleShadowWhenHighlighted">@property(nonatomic) BOOL reversesTitleShadowWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("reversesTitleShadowWhenHighlighted") public native boolean isReversesTitleShadowWhenHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/reversesTitleShadowWhenHighlighted">@property(nonatomic) BOOL reversesTitleShadowWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setReversesTitleShadowWhenHighlighted:") public native void setReversesTitleShadowWhenHighlighted(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/showsTouchWhenHighlighted">@property(nonatomic) BOOL showsTouchWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsTouchWhenHighlighted") public native boolean isShowsTouchWhenHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/showsTouchWhenHighlighted">@property(nonatomic) BOOL showsTouchWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsTouchWhenHighlighted:") public native void setShowsTouchWhenHighlighted(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("tintColor") public native UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/titleEdgeInsets">@property(nonatomic) UIEdgeInsets titleEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("titleEdgeInsets") public native UIEdgeInsets getTitleEdgeInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/titleEdgeInsets">@property(nonatomic) UIEdgeInsets titleEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitleEdgeInsets:") public native void setTitleEdgeInsets(UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/titleLabel">@property(nonatomic, readonly, retain) UILabel *titleLabel</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("titleLabel") public native UILabel getTitleLabel();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector buttonWithType$ = Selector.register("buttonWithType:");
    @Bridge(symbol = "objc_msgSend") private native static UIButton objc_fromType(ObjCClass __self__, Selector __cmd__, UIButtonType buttonType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/clm/UIButton/buttonWithType:">+ (id)buttonWithType:(UIButtonType)buttonType</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIButton fromType(UIButtonType buttonType) {
        return objc_fromType(objCClass, buttonWithType$, buttonType);
    }
    
    private static final Selector attributedTitleForState$ = Selector.register("attributedTitleForState:");
    @Bridge(symbol = "objc_msgSend") private native static NSAttributedString objc_getAttributedTitle(UIButton __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSAttributedString objc_getAttributedTitleSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/attributedTitleForState:">- (NSAttributedString *)attributedTitleForState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedString getAttributedTitle(UIControlState state) {
        if (customClass) { return objc_getAttributedTitleSuper(getSuper(), this, attributedTitleForState$, state); } else { return objc_getAttributedTitle(this, attributedTitleForState$, state); }
    }
    
    private static final Selector backgroundImageForState$ = Selector.register("backgroundImageForState:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getBackgroundImage(UIButton __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/backgroundImageForState:">- (UIImage *)backgroundImageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getBackgroundImage(UIControlState state) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), this, backgroundImageForState$, state); } else { return objc_getBackgroundImage(this, backgroundImageForState$, state); }
    }
    
    private static final Selector backgroundRectForBounds$ = Selector.register("backgroundRectForBounds:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getBackgroundRect(UIButton __self__, Selector __cmd__, CGRect bounds);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getBackgroundRectSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/backgroundRectForBounds:">- (CGRect)backgroundRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getBackgroundRect(CGRect bounds) {
        if (customClass) { return objc_getBackgroundRectSuper(getSuper(), this, backgroundRectForBounds$, bounds); } else { return objc_getBackgroundRect(this, backgroundRectForBounds$, bounds); }
    }
    
    private static final Selector contentRectForBounds$ = Selector.register("contentRectForBounds:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getContentRect(UIButton __self__, Selector __cmd__, CGRect bounds);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getContentRectSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/contentRectForBounds:">- (CGRect)contentRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getContentRect(CGRect bounds) {
        if (customClass) { return objc_getContentRectSuper(getSuper(), this, contentRectForBounds$, bounds); } else { return objc_getContentRect(this, contentRectForBounds$, bounds); }
    }
    
    private static final Selector imageForState$ = Selector.register("imageForState:");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getImage(UIButton __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getImageSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/imageForState:">- (UIImage *)imageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getImage(UIControlState state) {
        if (customClass) { return objc_getImageSuper(getSuper(), this, imageForState$, state); } else { return objc_getImage(this, imageForState$, state); }
    }
    
    private static final Selector imageRectForContentRect$ = Selector.register("imageRectForContentRect:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getImageRect(UIButton __self__, Selector __cmd__, CGRect contentRect);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getImageRectSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, CGRect contentRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/imageRectForContentRect:">- (CGRect)imageRectForContentRect:(CGRect)contentRect</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getImageRect(CGRect contentRect) {
        if (customClass) { return objc_getImageRectSuper(getSuper(), this, imageRectForContentRect$, contentRect); } else { return objc_getImageRect(this, imageRectForContentRect$, contentRect); }
    }
    
    private static final Selector titleForState$ = Selector.register("titleForState:");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getTitle(UIButton __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getTitleSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/titleForState:">- (NSString *)titleForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getTitle(UIControlState state) {
        if (customClass) { return objc_getTitleSuper(getSuper(), this, titleForState$, state); } else { return objc_getTitle(this, titleForState$, state); }
    }
    
    private static final Selector titleColorForState$ = Selector.register("titleColorForState:");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getTitleColor(UIButton __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getTitleColorSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/titleColorForState:">- (UIColor *)titleColorForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getTitleColor(UIControlState state) {
        if (customClass) { return objc_getTitleColorSuper(getSuper(), this, titleColorForState$, state); } else { return objc_getTitleColor(this, titleColorForState$, state); }
    }
    
    private static final Selector titleRectForContentRect$ = Selector.register("titleRectForContentRect:");
    @Bridge(symbol = "objc_msgSend") private native static CGRect objc_getTitleRect(UIButton __self__, Selector __cmd__, CGRect contentRect);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGRect objc_getTitleRectSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, CGRect contentRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/titleRectForContentRect:">- (CGRect)titleRectForContentRect:(CGRect)contentRect</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getTitleRect(CGRect contentRect) {
        if (customClass) { return objc_getTitleRectSuper(getSuper(), this, titleRectForContentRect$, contentRect); } else { return objc_getTitleRect(this, titleRectForContentRect$, contentRect); }
    }
    
    private static final Selector titleShadowColorForState$ = Selector.register("titleShadowColorForState:");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getTitleShadowColor(UIButton __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getTitleShadowColorSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/titleShadowColorForState:">- (UIColor *)titleShadowColorForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getTitleShadowColor(UIControlState state) {
        if (customClass) { return objc_getTitleShadowColorSuper(getSuper(), this, titleShadowColorForState$, state); } else { return objc_getTitleShadowColor(this, titleShadowColorForState$, state); }
    }
    
    private static final Selector setAttributedTitle$forState$ = Selector.register("setAttributedTitle:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAttributedTitle(UIButton __self__, Selector __cmd__, NSAttributedString title, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAttributedTitleSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, NSAttributedString title, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setAttributedTitle:forState:">- (void)setAttributedTitle:(NSAttributedString *)title forState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setAttributedTitle(NSAttributedString title, UIControlState state) {
        if (customClass) { objc_setAttributedTitleSuper(getSuper(), this, setAttributedTitle$forState$, title, state); } else { objc_setAttributedTitle(this, setAttributedTitle$forState$, title, state); }
    }
    
    private static final Selector setBackgroundImage$forState$ = Selector.register("setBackgroundImage:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setBackgroundImage(UIButton __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setBackgroundImage:forState:">- (void)setBackgroundImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBackgroundImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), this, setBackgroundImage$forState$, image, state); } else { objc_setBackgroundImage(this, setBackgroundImage$forState$, image, state); }
    }
    
    private static final Selector setImage$forState$ = Selector.register("setImage:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setImage(UIButton __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setImageSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setImage:forState:">- (void)setImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setImageSuper(getSuper(), this, setImage$forState$, image, state); } else { objc_setImage(this, setImage$forState$, image, state); }
    }
    
    private static final Selector setTitle$forState$ = Selector.register("setTitle:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitle(UIButton __self__, Selector __cmd__, String title, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitleSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, String title, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setTitle:forState:">- (void)setTitle:(NSString *)title forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitle(String title, UIControlState state) {
        if (customClass) { objc_setTitleSuper(getSuper(), this, setTitle$forState$, title, state); } else { objc_setTitle(this, setTitle$forState$, title, state); }
    }
    
    private static final Selector setTitleColor$forState$ = Selector.register("setTitleColor:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitleColor(UIButton __self__, Selector __cmd__, UIColor color, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitleColorSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIColor color, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setTitleColor:forState:">- (void)setTitleColor:(UIColor *)color forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitleColor(UIColor color, UIControlState state) {
        if (customClass) { objc_setTitleColorSuper(getSuper(), this, setTitleColor$forState$, color, state); } else { objc_setTitleColor(this, setTitleColor$forState$, color, state); }
    }
    
    private static final Selector setTitleShadowColor$forState$ = Selector.register("setTitleShadowColor:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitleShadowColor(UIButton __self__, Selector __cmd__, UIColor color, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitleShadowColorSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIColor color, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setTitleShadowColor:forState:">- (void)setTitleShadowColor:(UIColor *)color forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitleShadowColor(UIColor color, UIControlState state) {
        if (customClass) { objc_setTitleShadowColorSuper(getSuper(), this, setTitleShadowColor$forState$, color, state); } else { objc_setTitleShadowColor(this, setTitleShadowColor$forState$, color, state); }
    }
    /*</methods>*/

}
