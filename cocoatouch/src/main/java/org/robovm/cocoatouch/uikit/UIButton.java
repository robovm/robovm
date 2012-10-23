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
    
    private static final Selector adjustsImageWhenDisabled = Selector.register("adjustsImageWhenDisabled");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isAdjustsImageWhenDisabled(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isAdjustsImageWhenDisabledSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenDisabled">@property(nonatomic) BOOL adjustsImageWhenDisabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAdjustsImageWhenDisabled() {
        if (customClass) { return objc_isAdjustsImageWhenDisabledSuper(getSuper(), this, adjustsImageWhenDisabled); } else { return objc_isAdjustsImageWhenDisabled(this, adjustsImageWhenDisabled); }
    }
    
    private static final Selector setAdjustsImageWhenDisabled$ = Selector.register("setAdjustsImageWhenDisabled:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAdjustsImageWhenDisabled(UIButton __self__, Selector __cmd__, boolean adjustsImageWhenDisabled);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAdjustsImageWhenDisabledSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, boolean adjustsImageWhenDisabled);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenDisabled">@property(nonatomic) BOOL adjustsImageWhenDisabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAdjustsImageWhenDisabled(boolean adjustsImageWhenDisabled) {
        if (customClass) { objc_setAdjustsImageWhenDisabledSuper(getSuper(), this, setAdjustsImageWhenDisabled$, adjustsImageWhenDisabled); } else { objc_setAdjustsImageWhenDisabled(this, setAdjustsImageWhenDisabled$, adjustsImageWhenDisabled); }
    }
    
    private static final Selector adjustsImageWhenHighlighted = Selector.register("adjustsImageWhenHighlighted");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isAdjustsImageWhenHighlighted(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isAdjustsImageWhenHighlightedSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenHighlighted">@property(nonatomic) BOOL adjustsImageWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAdjustsImageWhenHighlighted() {
        if (customClass) { return objc_isAdjustsImageWhenHighlightedSuper(getSuper(), this, adjustsImageWhenHighlighted); } else { return objc_isAdjustsImageWhenHighlighted(this, adjustsImageWhenHighlighted); }
    }
    
    private static final Selector setAdjustsImageWhenHighlighted$ = Selector.register("setAdjustsImageWhenHighlighted:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setAdjustsImageWhenHighlighted(UIButton __self__, Selector __cmd__, boolean adjustsImageWhenHighlighted);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setAdjustsImageWhenHighlightedSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, boolean adjustsImageWhenHighlighted);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenHighlighted">@property(nonatomic) BOOL adjustsImageWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAdjustsImageWhenHighlighted(boolean adjustsImageWhenHighlighted) {
        if (customClass) { objc_setAdjustsImageWhenHighlightedSuper(getSuper(), this, setAdjustsImageWhenHighlighted$, adjustsImageWhenHighlighted); } else { objc_setAdjustsImageWhenHighlighted(this, setAdjustsImageWhenHighlighted$, adjustsImageWhenHighlighted); }
    }
    
    private static final Selector buttonType = Selector.register("buttonType");
    @Bridge(symbol = "objc_msgSend") private native static UIButtonType objc_getButtonType(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIButtonType objc_getButtonTypeSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/buttonType">@property(nonatomic, readonly) UIButtonType buttonType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIButtonType getButtonType() {
        if (customClass) { return objc_getButtonTypeSuper(getSuper(), this, buttonType); } else { return objc_getButtonType(this, buttonType); }
    }
    
    private static final Selector contentEdgeInsets = Selector.register("contentEdgeInsets");
    @Bridge(symbol = "objc_msgSend") private native static UIEdgeInsets objc_getContentEdgeInsets(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIEdgeInsets objc_getContentEdgeInsetsSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/contentEdgeInsets">@property(nonatomic) UIEdgeInsets contentEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIEdgeInsets getContentEdgeInsets() {
        if (customClass) { return objc_getContentEdgeInsetsSuper(getSuper(), this, contentEdgeInsets); } else { return objc_getContentEdgeInsets(this, contentEdgeInsets); }
    }
    
    private static final Selector setContentEdgeInsets$ = Selector.register("setContentEdgeInsets:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setContentEdgeInsets(UIButton __self__, Selector __cmd__, UIEdgeInsets contentEdgeInsets);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setContentEdgeInsetsSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIEdgeInsets contentEdgeInsets);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/contentEdgeInsets">@property(nonatomic) UIEdgeInsets contentEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContentEdgeInsets(UIEdgeInsets contentEdgeInsets) {
        if (customClass) { objc_setContentEdgeInsetsSuper(getSuper(), this, setContentEdgeInsets$, contentEdgeInsets); } else { objc_setContentEdgeInsets(this, setContentEdgeInsets$, contentEdgeInsets); }
    }
    
    private static final Selector currentAttributedTitle = Selector.register("currentAttributedTitle");
    @Bridge(symbol = "objc_msgSend") private native static NSAttributedString objc_getCurrentAttributedTitle(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSAttributedString objc_getCurrentAttributedTitleSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentAttributedTitle">@property(nonatomic,readonly,retain) NSAttributedString *currentAttributedTitle</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedString getCurrentAttributedTitle() {
        if (customClass) { return objc_getCurrentAttributedTitleSuper(getSuper(), this, currentAttributedTitle); } else { return objc_getCurrentAttributedTitle(this, currentAttributedTitle); }
    }
    
    private static final Selector currentBackgroundImage = Selector.register("currentBackgroundImage");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getCurrentBackgroundImage(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getCurrentBackgroundImageSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentBackgroundImage">@property(nonatomic, readonly, retain) UIImage *currentBackgroundImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getCurrentBackgroundImage() {
        if (customClass) { return objc_getCurrentBackgroundImageSuper(getSuper(), this, currentBackgroundImage); } else { return objc_getCurrentBackgroundImage(this, currentBackgroundImage); }
    }
    
    private static final Selector currentImage = Selector.register("currentImage");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getCurrentImage(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getCurrentImageSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentImage">@property(nonatomic, readonly, retain) UIImage *currentImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getCurrentImage() {
        if (customClass) { return objc_getCurrentImageSuper(getSuper(), this, currentImage); } else { return objc_getCurrentImage(this, currentImage); }
    }
    
    private static final Selector currentTitle = Selector.register("currentTitle");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getCurrentTitle(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getCurrentTitleSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentTitle">@property(nonatomic, readonly, retain) NSString *currentTitle</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getCurrentTitle() {
        if (customClass) { return objc_getCurrentTitleSuper(getSuper(), this, currentTitle); } else { return objc_getCurrentTitle(this, currentTitle); }
    }
    
    private static final Selector currentTitleColor = Selector.register("currentTitleColor");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getCurrentTitleColor(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getCurrentTitleColorSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentTitleColor">@property(nonatomic, readonly, retain) UIColor *currentTitleColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getCurrentTitleColor() {
        if (customClass) { return objc_getCurrentTitleColorSuper(getSuper(), this, currentTitleColor); } else { return objc_getCurrentTitleColor(this, currentTitleColor); }
    }
    
    private static final Selector currentTitleShadowColor = Selector.register("currentTitleShadowColor");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getCurrentTitleShadowColor(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getCurrentTitleShadowColorSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentTitleShadowColor">@property(nonatomic, readonly, retain) UIColor *currentTitleShadowColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getCurrentTitleShadowColor() {
        if (customClass) { return objc_getCurrentTitleShadowColorSuper(getSuper(), this, currentTitleShadowColor); } else { return objc_getCurrentTitleShadowColor(this, currentTitleShadowColor); }
    }
    
    private static final Selector imageEdgeInsets = Selector.register("imageEdgeInsets");
    @Bridge(symbol = "objc_msgSend") private native static UIEdgeInsets objc_getImageEdgeInsets(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIEdgeInsets objc_getImageEdgeInsetsSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/imageEdgeInsets">@property(nonatomic) UIEdgeInsets imageEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIEdgeInsets getImageEdgeInsets() {
        if (customClass) { return objc_getImageEdgeInsetsSuper(getSuper(), this, imageEdgeInsets); } else { return objc_getImageEdgeInsets(this, imageEdgeInsets); }
    }
    
    private static final Selector setImageEdgeInsets$ = Selector.register("setImageEdgeInsets:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setImageEdgeInsets(UIButton __self__, Selector __cmd__, UIEdgeInsets imageEdgeInsets);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setImageEdgeInsetsSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIEdgeInsets imageEdgeInsets);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/imageEdgeInsets">@property(nonatomic) UIEdgeInsets imageEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setImageEdgeInsets(UIEdgeInsets imageEdgeInsets) {
        if (customClass) { objc_setImageEdgeInsetsSuper(getSuper(), this, setImageEdgeInsets$, imageEdgeInsets); } else { objc_setImageEdgeInsets(this, setImageEdgeInsets$, imageEdgeInsets); }
    }
    
    private static final Selector imageView = Selector.register("imageView");
    @Bridge(symbol = "objc_msgSend") private native static UIImageView objc_getImageView(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImageView objc_getImageViewSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/imageView">@property(nonatomic, readonly, retain) UIImageView *imageView</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIImageView getImageView() {
        if (customClass) { return objc_getImageViewSuper(getSuper(), this, imageView); } else { return objc_getImageView(this, imageView); }
    }
    
    private static final Selector reversesTitleShadowWhenHighlighted = Selector.register("reversesTitleShadowWhenHighlighted");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isReversesTitleShadowWhenHighlighted(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isReversesTitleShadowWhenHighlightedSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/reversesTitleShadowWhenHighlighted">@property(nonatomic) BOOL reversesTitleShadowWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isReversesTitleShadowWhenHighlighted() {
        if (customClass) { return objc_isReversesTitleShadowWhenHighlightedSuper(getSuper(), this, reversesTitleShadowWhenHighlighted); } else { return objc_isReversesTitleShadowWhenHighlighted(this, reversesTitleShadowWhenHighlighted); }
    }
    
    private static final Selector setReversesTitleShadowWhenHighlighted$ = Selector.register("setReversesTitleShadowWhenHighlighted:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setReversesTitleShadowWhenHighlighted(UIButton __self__, Selector __cmd__, boolean reversesTitleShadowWhenHighlighted);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setReversesTitleShadowWhenHighlightedSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, boolean reversesTitleShadowWhenHighlighted);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/reversesTitleShadowWhenHighlighted">@property(nonatomic) BOOL reversesTitleShadowWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setReversesTitleShadowWhenHighlighted(boolean reversesTitleShadowWhenHighlighted) {
        if (customClass) { objc_setReversesTitleShadowWhenHighlightedSuper(getSuper(), this, setReversesTitleShadowWhenHighlighted$, reversesTitleShadowWhenHighlighted); } else { objc_setReversesTitleShadowWhenHighlighted(this, setReversesTitleShadowWhenHighlighted$, reversesTitleShadowWhenHighlighted); }
    }
    
    private static final Selector showsTouchWhenHighlighted = Selector.register("showsTouchWhenHighlighted");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isShowsTouchWhenHighlighted(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isShowsTouchWhenHighlightedSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/showsTouchWhenHighlighted">@property(nonatomic) BOOL showsTouchWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isShowsTouchWhenHighlighted() {
        if (customClass) { return objc_isShowsTouchWhenHighlightedSuper(getSuper(), this, showsTouchWhenHighlighted); } else { return objc_isShowsTouchWhenHighlighted(this, showsTouchWhenHighlighted); }
    }
    
    private static final Selector setShowsTouchWhenHighlighted$ = Selector.register("setShowsTouchWhenHighlighted:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setShowsTouchWhenHighlighted(UIButton __self__, Selector __cmd__, boolean showsTouchWhenHighlighted);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setShowsTouchWhenHighlightedSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, boolean showsTouchWhenHighlighted);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/showsTouchWhenHighlighted">@property(nonatomic) BOOL showsTouchWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShowsTouchWhenHighlighted(boolean showsTouchWhenHighlighted) {
        if (customClass) { objc_setShowsTouchWhenHighlightedSuper(getSuper(), this, setShowsTouchWhenHighlighted$, showsTouchWhenHighlighted); } else { objc_setShowsTouchWhenHighlighted(this, setShowsTouchWhenHighlighted$, showsTouchWhenHighlighted); }
    }
    
    private static final Selector tintColor = Selector.register("tintColor");
    @Bridge(symbol = "objc_msgSend") private native static UIColor objc_getTintColor(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIColor objc_getTintColorSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getTintColor() {
        if (customClass) { return objc_getTintColorSuper(getSuper(), this, tintColor); } else { return objc_getTintColor(this, tintColor); }
    }
    
    private static final Selector setTintColor$ = Selector.register("setTintColor:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTintColor(UIButton __self__, Selector __cmd__, UIColor tintColor);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTintColorSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIColor tintColor);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTintColor(UIColor tintColor) {
        if (customClass) { objc_setTintColorSuper(getSuper(), this, setTintColor$, tintColor); } else { objc_setTintColor(this, setTintColor$, tintColor); }
    }
    
    private static final Selector titleEdgeInsets = Selector.register("titleEdgeInsets");
    @Bridge(symbol = "objc_msgSend") private native static UIEdgeInsets objc_getTitleEdgeInsets(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIEdgeInsets objc_getTitleEdgeInsetsSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/titleEdgeInsets">@property(nonatomic) UIEdgeInsets titleEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIEdgeInsets getTitleEdgeInsets() {
        if (customClass) { return objc_getTitleEdgeInsetsSuper(getSuper(), this, titleEdgeInsets); } else { return objc_getTitleEdgeInsets(this, titleEdgeInsets); }
    }
    
    private static final Selector setTitleEdgeInsets$ = Selector.register("setTitleEdgeInsets:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitleEdgeInsets(UIButton __self__, Selector __cmd__, UIEdgeInsets titleEdgeInsets);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitleEdgeInsetsSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__, UIEdgeInsets titleEdgeInsets);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/titleEdgeInsets">@property(nonatomic) UIEdgeInsets titleEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitleEdgeInsets(UIEdgeInsets titleEdgeInsets) {
        if (customClass) { objc_setTitleEdgeInsetsSuper(getSuper(), this, setTitleEdgeInsets$, titleEdgeInsets); } else { objc_setTitleEdgeInsets(this, setTitleEdgeInsets$, titleEdgeInsets); }
    }
    
    private static final Selector titleLabel = Selector.register("titleLabel");
    @Bridge(symbol = "objc_msgSend") private native static UILabel objc_getTitleLabel(UIButton __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UILabel objc_getTitleLabelSuper(ObjCSuper __super__, UIButton __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/titleLabel">@property(nonatomic, readonly, retain) UILabel *titleLabel</a>
     * @since Available in iOS 3.0 and later.
     */
    public UILabel getTitleLabel() {
        if (customClass) { return objc_getTitleLabelSuper(getSuper(), this, titleLabel); } else { return objc_getTitleLabel(this, titleLabel); }
    }
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
