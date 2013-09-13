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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html">UIButton Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIButton /*</name>*/ 
    extends /*<extends>*/ UIControl /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIButton /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIButton /*</name>*/.class);

    public UIButton(CGRect aRect) {
        super(aRect);
    }
    /*<constructors>*/
    protected UIButton(SkipInit skipInit) { super(skipInit); }
    public UIButton() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector adjustsImageWhenDisabled = Selector.register("adjustsImageWhenDisabled");
    @Bridge private native static boolean objc_isAdjustsImageWhenDisabled(UIButton __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAdjustsImageWhenDisabledSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenDisabled">@property(nonatomic) BOOL adjustsImageWhenDisabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAdjustsImageWhenDisabled() {
        if (customClass) { return objc_isAdjustsImageWhenDisabledSuper(getSuper(), adjustsImageWhenDisabled); } else { return objc_isAdjustsImageWhenDisabled(this, adjustsImageWhenDisabled); }
    }
    
    private static final Selector setAdjustsImageWhenDisabled$ = Selector.register("setAdjustsImageWhenDisabled:");
    @Bridge private native static void objc_setAdjustsImageWhenDisabled(UIButton __self__, Selector __cmd__, boolean adjustsImageWhenDisabled);
    @Bridge private native static void objc_setAdjustsImageWhenDisabledSuper(ObjCSuper __super__, Selector __cmd__, boolean adjustsImageWhenDisabled);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenDisabled">@property(nonatomic) BOOL adjustsImageWhenDisabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAdjustsImageWhenDisabled(boolean adjustsImageWhenDisabled) {
        if (customClass) { objc_setAdjustsImageWhenDisabledSuper(getSuper(), setAdjustsImageWhenDisabled$, adjustsImageWhenDisabled); } else { objc_setAdjustsImageWhenDisabled(this, setAdjustsImageWhenDisabled$, adjustsImageWhenDisabled); }
    }
    
    private static final Selector adjustsImageWhenHighlighted = Selector.register("adjustsImageWhenHighlighted");
    @Bridge private native static boolean objc_isAdjustsImageWhenHighlighted(UIButton __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isAdjustsImageWhenHighlightedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenHighlighted">@property(nonatomic) BOOL adjustsImageWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isAdjustsImageWhenHighlighted() {
        if (customClass) { return objc_isAdjustsImageWhenHighlightedSuper(getSuper(), adjustsImageWhenHighlighted); } else { return objc_isAdjustsImageWhenHighlighted(this, adjustsImageWhenHighlighted); }
    }
    
    private static final Selector setAdjustsImageWhenHighlighted$ = Selector.register("setAdjustsImageWhenHighlighted:");
    @Bridge private native static void objc_setAdjustsImageWhenHighlighted(UIButton __self__, Selector __cmd__, boolean adjustsImageWhenHighlighted);
    @Bridge private native static void objc_setAdjustsImageWhenHighlightedSuper(ObjCSuper __super__, Selector __cmd__, boolean adjustsImageWhenHighlighted);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenHighlighted">@property(nonatomic) BOOL adjustsImageWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setAdjustsImageWhenHighlighted(boolean adjustsImageWhenHighlighted) {
        if (customClass) { objc_setAdjustsImageWhenHighlightedSuper(getSuper(), setAdjustsImageWhenHighlighted$, adjustsImageWhenHighlighted); } else { objc_setAdjustsImageWhenHighlighted(this, setAdjustsImageWhenHighlighted$, adjustsImageWhenHighlighted); }
    }
    
    private static final Selector buttonType = Selector.register("buttonType");
    @Bridge private native static UIButtonType objc_getButtonType(UIButton __self__, Selector __cmd__);
    @Bridge private native static UIButtonType objc_getButtonTypeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/buttonType">@property(nonatomic, readonly) UIButtonType buttonType</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIButtonType getButtonType() {
        if (customClass) { return objc_getButtonTypeSuper(getSuper(), buttonType); } else { return objc_getButtonType(this, buttonType); }
    }
    
    private static final Selector contentEdgeInsets = Selector.register("contentEdgeInsets");
    @Bridge private native static @ByVal UIEdgeInsets objc_getContentEdgeInsets(UIButton __self__, Selector __cmd__);
    @Bridge private native static @ByVal UIEdgeInsets objc_getContentEdgeInsetsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/contentEdgeInsets">@property(nonatomic) UIEdgeInsets contentEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIEdgeInsets getContentEdgeInsets() {
        if (customClass) { return objc_getContentEdgeInsetsSuper(getSuper(), contentEdgeInsets); } else { return objc_getContentEdgeInsets(this, contentEdgeInsets); }
    }
    
    private static final Selector setContentEdgeInsets$ = Selector.register("setContentEdgeInsets:");
    @Bridge private native static void objc_setContentEdgeInsets(UIButton __self__, Selector __cmd__, @ByVal UIEdgeInsets contentEdgeInsets);
    @Bridge private native static void objc_setContentEdgeInsetsSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIEdgeInsets contentEdgeInsets);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/contentEdgeInsets">@property(nonatomic) UIEdgeInsets contentEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setContentEdgeInsets(UIEdgeInsets contentEdgeInsets) {
        if (customClass) { objc_setContentEdgeInsetsSuper(getSuper(), setContentEdgeInsets$, contentEdgeInsets); } else { objc_setContentEdgeInsets(this, setContentEdgeInsets$, contentEdgeInsets); }
    }
    
    private static final Selector currentAttributedTitle = Selector.register("currentAttributedTitle");
    @Bridge private native static NSAttributedString objc_getCurrentAttributedTitle(UIButton __self__, Selector __cmd__);
    @Bridge private native static NSAttributedString objc_getCurrentAttributedTitleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentAttributedTitle">@property(nonatomic,readonly,retain) NSAttributedString *currentAttributedTitle</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedString getCurrentAttributedTitle() {
        if (customClass) { return objc_getCurrentAttributedTitleSuper(getSuper(), currentAttributedTitle); } else { return objc_getCurrentAttributedTitle(this, currentAttributedTitle); }
    }
    
    private static final Selector currentBackgroundImage = Selector.register("currentBackgroundImage");
    @Bridge private native static UIImage objc_getCurrentBackgroundImage(UIButton __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getCurrentBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentBackgroundImage">@property(nonatomic, readonly, retain) UIImage *currentBackgroundImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getCurrentBackgroundImage() {
        if (customClass) { return objc_getCurrentBackgroundImageSuper(getSuper(), currentBackgroundImage); } else { return objc_getCurrentBackgroundImage(this, currentBackgroundImage); }
    }
    
    private static final Selector currentImage = Selector.register("currentImage");
    @Bridge private native static UIImage objc_getCurrentImage(UIButton __self__, Selector __cmd__);
    @Bridge private native static UIImage objc_getCurrentImageSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentImage">@property(nonatomic, readonly, retain) UIImage *currentImage</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getCurrentImage() {
        if (customClass) { return objc_getCurrentImageSuper(getSuper(), currentImage); } else { return objc_getCurrentImage(this, currentImage); }
    }
    
    private static final Selector currentTitle = Selector.register("currentTitle");
    @Bridge private native static String objc_getCurrentTitle(UIButton __self__, Selector __cmd__);
    @Bridge private native static String objc_getCurrentTitleSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentTitle">@property(nonatomic, readonly, retain) NSString *currentTitle</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getCurrentTitle() {
        if (customClass) { return objc_getCurrentTitleSuper(getSuper(), currentTitle); } else { return objc_getCurrentTitle(this, currentTitle); }
    }
    
    private static final Selector currentTitleColor = Selector.register("currentTitleColor");
    @Bridge private native static UIColor objc_getCurrentTitleColor(UIButton __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getCurrentTitleColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentTitleColor">@property(nonatomic, readonly, retain) UIColor *currentTitleColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getCurrentTitleColor() {
        if (customClass) { return objc_getCurrentTitleColorSuper(getSuper(), currentTitleColor); } else { return objc_getCurrentTitleColor(this, currentTitleColor); }
    }
    
    private static final Selector currentTitleShadowColor = Selector.register("currentTitleShadowColor");
    @Bridge private native static UIColor objc_getCurrentTitleShadowColor(UIButton __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getCurrentTitleShadowColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentTitleShadowColor">@property(nonatomic, readonly, retain) UIColor *currentTitleShadowColor</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getCurrentTitleShadowColor() {
        if (customClass) { return objc_getCurrentTitleShadowColorSuper(getSuper(), currentTitleShadowColor); } else { return objc_getCurrentTitleShadowColor(this, currentTitleShadowColor); }
    }
    
    private static final Selector imageEdgeInsets = Selector.register("imageEdgeInsets");
    @Bridge private native static @ByVal UIEdgeInsets objc_getImageEdgeInsets(UIButton __self__, Selector __cmd__);
    @Bridge private native static @ByVal UIEdgeInsets objc_getImageEdgeInsetsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/imageEdgeInsets">@property(nonatomic) UIEdgeInsets imageEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIEdgeInsets getImageEdgeInsets() {
        if (customClass) { return objc_getImageEdgeInsetsSuper(getSuper(), imageEdgeInsets); } else { return objc_getImageEdgeInsets(this, imageEdgeInsets); }
    }
    
    private static final Selector setImageEdgeInsets$ = Selector.register("setImageEdgeInsets:");
    @Bridge private native static void objc_setImageEdgeInsets(UIButton __self__, Selector __cmd__, @ByVal UIEdgeInsets imageEdgeInsets);
    @Bridge private native static void objc_setImageEdgeInsetsSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIEdgeInsets imageEdgeInsets);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/imageEdgeInsets">@property(nonatomic) UIEdgeInsets imageEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setImageEdgeInsets(UIEdgeInsets imageEdgeInsets) {
        if (customClass) { objc_setImageEdgeInsetsSuper(getSuper(), setImageEdgeInsets$, imageEdgeInsets); } else { objc_setImageEdgeInsets(this, setImageEdgeInsets$, imageEdgeInsets); }
    }
    
    private static final Selector imageView = Selector.register("imageView");
    @Bridge private native static UIImageView objc_getImageView(UIButton __self__, Selector __cmd__);
    @Bridge private native static UIImageView objc_getImageViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/imageView">@property(nonatomic, readonly, retain) UIImageView *imageView</a>
     * @since Available in iOS 3.0 and later.
     */
    public UIImageView getImageView() {
        if (customClass) { return objc_getImageViewSuper(getSuper(), imageView); } else { return objc_getImageView(this, imageView); }
    }
    
    private static final Selector reversesTitleShadowWhenHighlighted = Selector.register("reversesTitleShadowWhenHighlighted");
    @Bridge private native static boolean objc_isReversesTitleShadowWhenHighlighted(UIButton __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isReversesTitleShadowWhenHighlightedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/reversesTitleShadowWhenHighlighted">@property(nonatomic) BOOL reversesTitleShadowWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isReversesTitleShadowWhenHighlighted() {
        if (customClass) { return objc_isReversesTitleShadowWhenHighlightedSuper(getSuper(), reversesTitleShadowWhenHighlighted); } else { return objc_isReversesTitleShadowWhenHighlighted(this, reversesTitleShadowWhenHighlighted); }
    }
    
    private static final Selector setReversesTitleShadowWhenHighlighted$ = Selector.register("setReversesTitleShadowWhenHighlighted:");
    @Bridge private native static void objc_setReversesTitleShadowWhenHighlighted(UIButton __self__, Selector __cmd__, boolean reversesTitleShadowWhenHighlighted);
    @Bridge private native static void objc_setReversesTitleShadowWhenHighlightedSuper(ObjCSuper __super__, Selector __cmd__, boolean reversesTitleShadowWhenHighlighted);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/reversesTitleShadowWhenHighlighted">@property(nonatomic) BOOL reversesTitleShadowWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setReversesTitleShadowWhenHighlighted(boolean reversesTitleShadowWhenHighlighted) {
        if (customClass) { objc_setReversesTitleShadowWhenHighlightedSuper(getSuper(), setReversesTitleShadowWhenHighlighted$, reversesTitleShadowWhenHighlighted); } else { objc_setReversesTitleShadowWhenHighlighted(this, setReversesTitleShadowWhenHighlighted$, reversesTitleShadowWhenHighlighted); }
    }
    
    private static final Selector showsTouchWhenHighlighted = Selector.register("showsTouchWhenHighlighted");
    @Bridge private native static boolean objc_isShowsTouchWhenHighlighted(UIButton __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isShowsTouchWhenHighlightedSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/showsTouchWhenHighlighted">@property(nonatomic) BOOL showsTouchWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isShowsTouchWhenHighlighted() {
        if (customClass) { return objc_isShowsTouchWhenHighlightedSuper(getSuper(), showsTouchWhenHighlighted); } else { return objc_isShowsTouchWhenHighlighted(this, showsTouchWhenHighlighted); }
    }
    
    private static final Selector setShowsTouchWhenHighlighted$ = Selector.register("setShowsTouchWhenHighlighted:");
    @Bridge private native static void objc_setShowsTouchWhenHighlighted(UIButton __self__, Selector __cmd__, boolean showsTouchWhenHighlighted);
    @Bridge private native static void objc_setShowsTouchWhenHighlightedSuper(ObjCSuper __super__, Selector __cmd__, boolean showsTouchWhenHighlighted);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/showsTouchWhenHighlighted">@property(nonatomic) BOOL showsTouchWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setShowsTouchWhenHighlighted(boolean showsTouchWhenHighlighted) {
        if (customClass) { objc_setShowsTouchWhenHighlightedSuper(getSuper(), setShowsTouchWhenHighlighted$, showsTouchWhenHighlighted); } else { objc_setShowsTouchWhenHighlighted(this, setShowsTouchWhenHighlighted$, showsTouchWhenHighlighted); }
    }
    
    private static final Selector tintColor = Selector.register("tintColor");
    @Bridge private native static UIColor objc_getTintColor(UIButton __self__, Selector __cmd__);
    @Bridge private native static UIColor objc_getTintColorSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIColor getTintColor() {
        if (customClass) { return objc_getTintColorSuper(getSuper(), tintColor); } else { return objc_getTintColor(this, tintColor); }
    }
    
    private static final Selector setTintColor$ = Selector.register("setTintColor:");
    @Bridge private native static void objc_setTintColor(UIButton __self__, Selector __cmd__, UIColor tintColor);
    @Bridge private native static void objc_setTintColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor tintColor);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTintColor(UIColor tintColor) {
        if (customClass) { objc_setTintColorSuper(getSuper(), setTintColor$, tintColor); } else { objc_setTintColor(this, setTintColor$, tintColor); }
    }
    
    private static final Selector titleEdgeInsets = Selector.register("titleEdgeInsets");
    @Bridge private native static @ByVal UIEdgeInsets objc_getTitleEdgeInsets(UIButton __self__, Selector __cmd__);
    @Bridge private native static @ByVal UIEdgeInsets objc_getTitleEdgeInsetsSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/titleEdgeInsets">@property(nonatomic) UIEdgeInsets titleEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIEdgeInsets getTitleEdgeInsets() {
        if (customClass) { return objc_getTitleEdgeInsetsSuper(getSuper(), titleEdgeInsets); } else { return objc_getTitleEdgeInsets(this, titleEdgeInsets); }
    }
    
    private static final Selector setTitleEdgeInsets$ = Selector.register("setTitleEdgeInsets:");
    @Bridge private native static void objc_setTitleEdgeInsets(UIButton __self__, Selector __cmd__, @ByVal UIEdgeInsets titleEdgeInsets);
    @Bridge private native static void objc_setTitleEdgeInsetsSuper(ObjCSuper __super__, Selector __cmd__, @ByVal UIEdgeInsets titleEdgeInsets);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/titleEdgeInsets">@property(nonatomic) UIEdgeInsets titleEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitleEdgeInsets(UIEdgeInsets titleEdgeInsets) {
        if (customClass) { objc_setTitleEdgeInsetsSuper(getSuper(), setTitleEdgeInsets$, titleEdgeInsets); } else { objc_setTitleEdgeInsets(this, setTitleEdgeInsets$, titleEdgeInsets); }
    }
    
    private static final Selector titleLabel = Selector.register("titleLabel");
    @Bridge private native static UILabel objc_getTitleLabel(UIButton __self__, Selector __cmd__);
    @Bridge private native static UILabel objc_getTitleLabelSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/titleLabel">@property(nonatomic, readonly, retain) UILabel *titleLabel</a>
     * @since Available in iOS 3.0 and later.
     */
    public UILabel getTitleLabel() {
        if (customClass) { return objc_getTitleLabelSuper(getSuper(), titleLabel); } else { return objc_getTitleLabel(this, titleLabel); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector buttonWithType$ = Selector.register("buttonWithType:");
    @Bridge private native static UIButton objc_fromType(ObjCClass __self__, Selector __cmd__, UIButtonType buttonType);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/clm/UIButton/buttonWithType:">+ (id)buttonWithType:(UIButtonType)buttonType</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIButton fromType(UIButtonType buttonType) {
        return objc_fromType(objCClass, buttonWithType$, buttonType);
    }
    
    private static final Selector attributedTitleForState$ = Selector.register("attributedTitleForState:");
    @Bridge private native static NSAttributedString objc_getAttributedTitle(UIButton __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static NSAttributedString objc_getAttributedTitleSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/attributedTitleForState:">- (NSAttributedString *)attributedTitleForState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSAttributedString getAttributedTitle(UIControlState state) {
        if (customClass) { return objc_getAttributedTitleSuper(getSuper(), attributedTitleForState$, state); } else { return objc_getAttributedTitle(this, attributedTitleForState$, state); }
    }
    
    private static final Selector backgroundImageForState$ = Selector.register("backgroundImageForState:");
    @Bridge private native static UIImage objc_getBackgroundImage(UIButton __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static UIImage objc_getBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/backgroundImageForState:">- (UIImage *)backgroundImageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getBackgroundImage(UIControlState state) {
        if (customClass) { return objc_getBackgroundImageSuper(getSuper(), backgroundImageForState$, state); } else { return objc_getBackgroundImage(this, backgroundImageForState$, state); }
    }
    
    private static final Selector backgroundRectForBounds$ = Selector.register("backgroundRectForBounds:");
    @Bridge private native static @ByVal CGRect objc_getBackgroundRect(UIButton __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static @ByVal CGRect objc_getBackgroundRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/backgroundRectForBounds:">- (CGRect)backgroundRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getBackgroundRect(CGRect bounds) {
        if (customClass) { return objc_getBackgroundRectSuper(getSuper(), backgroundRectForBounds$, bounds); } else { return objc_getBackgroundRect(this, backgroundRectForBounds$, bounds); }
    }
    
    private static final Selector contentRectForBounds$ = Selector.register("contentRectForBounds:");
    @Bridge private native static @ByVal CGRect objc_getContentRect(UIButton __self__, Selector __cmd__, @ByVal CGRect bounds);
    @Bridge private native static @ByVal CGRect objc_getContentRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect bounds);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/contentRectForBounds:">- (CGRect)contentRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getContentRect(CGRect bounds) {
        if (customClass) { return objc_getContentRectSuper(getSuper(), contentRectForBounds$, bounds); } else { return objc_getContentRect(this, contentRectForBounds$, bounds); }
    }
    
    private static final Selector imageForState$ = Selector.register("imageForState:");
    @Bridge private native static UIImage objc_getImage(UIButton __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static UIImage objc_getImageSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/imageForState:">- (UIImage *)imageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getImage(UIControlState state) {
        if (customClass) { return objc_getImageSuper(getSuper(), imageForState$, state); } else { return objc_getImage(this, imageForState$, state); }
    }
    
    private static final Selector imageRectForContentRect$ = Selector.register("imageRectForContentRect:");
    @Bridge private native static @ByVal CGRect objc_getImageRect(UIButton __self__, Selector __cmd__, @ByVal CGRect contentRect);
    @Bridge private native static @ByVal CGRect objc_getImageRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect contentRect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/imageRectForContentRect:">- (CGRect)imageRectForContentRect:(CGRect)contentRect</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getImageRect(CGRect contentRect) {
        if (customClass) { return objc_getImageRectSuper(getSuper(), imageRectForContentRect$, contentRect); } else { return objc_getImageRect(this, imageRectForContentRect$, contentRect); }
    }
    
    private static final Selector titleForState$ = Selector.register("titleForState:");
    @Bridge private native static String objc_getTitle(UIButton __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static String objc_getTitleSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/titleForState:">- (NSString *)titleForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getTitle(UIControlState state) {
        if (customClass) { return objc_getTitleSuper(getSuper(), titleForState$, state); } else { return objc_getTitle(this, titleForState$, state); }
    }
    
    private static final Selector titleColorForState$ = Selector.register("titleColorForState:");
    @Bridge private native static UIColor objc_getTitleColor(UIButton __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static UIColor objc_getTitleColorSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/titleColorForState:">- (UIColor *)titleColorForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getTitleColor(UIControlState state) {
        if (customClass) { return objc_getTitleColorSuper(getSuper(), titleColorForState$, state); } else { return objc_getTitleColor(this, titleColorForState$, state); }
    }
    
    private static final Selector titleRectForContentRect$ = Selector.register("titleRectForContentRect:");
    @Bridge private native static @ByVal CGRect objc_getTitleRect(UIButton __self__, Selector __cmd__, @ByVal CGRect contentRect);
    @Bridge private native static @ByVal CGRect objc_getTitleRectSuper(ObjCSuper __super__, Selector __cmd__, @ByVal CGRect contentRect);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/titleRectForContentRect:">- (CGRect)titleRectForContentRect:(CGRect)contentRect</a>
     * @since Available in iOS 2.0 and later.
     */
    public CGRect getTitleRect(CGRect contentRect) {
        if (customClass) { return objc_getTitleRectSuper(getSuper(), titleRectForContentRect$, contentRect); } else { return objc_getTitleRect(this, titleRectForContentRect$, contentRect); }
    }
    
    private static final Selector titleShadowColorForState$ = Selector.register("titleShadowColorForState:");
    @Bridge private native static UIColor objc_getTitleShadowColor(UIButton __self__, Selector __cmd__, UIControlState state);
    @Bridge private native static UIColor objc_getTitleShadowColorSuper(ObjCSuper __super__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/titleShadowColorForState:">- (UIColor *)titleShadowColorForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIColor getTitleShadowColor(UIControlState state) {
        if (customClass) { return objc_getTitleShadowColorSuper(getSuper(), titleShadowColorForState$, state); } else { return objc_getTitleShadowColor(this, titleShadowColorForState$, state); }
    }
    
    private static final Selector setAttributedTitle$forState$ = Selector.register("setAttributedTitle:forState:");
    @Bridge private native static void objc_setAttributedTitle(UIButton __self__, Selector __cmd__, NSAttributedString title, UIControlState state);
    @Bridge private native static void objc_setAttributedTitleSuper(ObjCSuper __super__, Selector __cmd__, NSAttributedString title, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setAttributedTitle:forState:">- (void)setAttributedTitle:(NSAttributedString *)title forState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setAttributedTitle(NSAttributedString title, UIControlState state) {
        if (customClass) { objc_setAttributedTitleSuper(getSuper(), setAttributedTitle$forState$, title, state); } else { objc_setAttributedTitle(this, setAttributedTitle$forState$, title, state); }
    }
    
    private static final Selector setBackgroundImage$forState$ = Selector.register("setBackgroundImage:forState:");
    @Bridge private native static void objc_setBackgroundImage(UIButton __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge private native static void objc_setBackgroundImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setBackgroundImage:forState:">- (void)setBackgroundImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setBackgroundImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setBackgroundImageSuper(getSuper(), setBackgroundImage$forState$, image, state); } else { objc_setBackgroundImage(this, setBackgroundImage$forState$, image, state); }
    }
    
    private static final Selector setImage$forState$ = Selector.register("setImage:forState:");
    @Bridge private native static void objc_setImage(UIButton __self__, Selector __cmd__, UIImage image, UIControlState state);
    @Bridge private native static void objc_setImageSuper(ObjCSuper __super__, Selector __cmd__, UIImage image, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setImage:forState:">- (void)setImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setImage(UIImage image, UIControlState state) {
        if (customClass) { objc_setImageSuper(getSuper(), setImage$forState$, image, state); } else { objc_setImage(this, setImage$forState$, image, state); }
    }
    
    private static final Selector setTitle$forState$ = Selector.register("setTitle:forState:");
    @Bridge private native static void objc_setTitle(UIButton __self__, Selector __cmd__, String title, UIControlState state);
    @Bridge private native static void objc_setTitleSuper(ObjCSuper __super__, Selector __cmd__, String title, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setTitle:forState:">- (void)setTitle:(NSString *)title forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitle(String title, UIControlState state) {
        if (customClass) { objc_setTitleSuper(getSuper(), setTitle$forState$, title, state); } else { objc_setTitle(this, setTitle$forState$, title, state); }
    }
    
    private static final Selector setTitleColor$forState$ = Selector.register("setTitleColor:forState:");
    @Bridge private native static void objc_setTitleColor(UIButton __self__, Selector __cmd__, UIColor color, UIControlState state);
    @Bridge private native static void objc_setTitleColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor color, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setTitleColor:forState:">- (void)setTitleColor:(UIColor *)color forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitleColor(UIColor color, UIControlState state) {
        if (customClass) { objc_setTitleColorSuper(getSuper(), setTitleColor$forState$, color, state); } else { objc_setTitleColor(this, setTitleColor$forState$, color, state); }
    }
    
    private static final Selector setTitleShadowColor$forState$ = Selector.register("setTitleShadowColor:forState:");
    @Bridge private native static void objc_setTitleShadowColor(UIButton __self__, Selector __cmd__, UIColor color, UIControlState state);
    @Bridge private native static void objc_setTitleShadowColorSuper(ObjCSuper __super__, Selector __cmd__, UIColor color, UIControlState state);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setTitleShadowColor:forState:">- (void)setTitleShadowColor:(UIColor *)color forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitleShadowColor(UIColor color, UIControlState state) {
        if (customClass) { objc_setTitleShadowColorSuper(getSuper(), setTitleShadowColor$forState$, color, state); } else { objc_setTitleShadowColor(this, setTitleShadowColor$forState$, color, state); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("adjustsImageWhenDisabled") public static boolean isAdjustsImageWhenDisabled(UIButton __self__, Selector __cmd__) { return __self__.isAdjustsImageWhenDisabled(); }
        @Callback @BindSelector("setAdjustsImageWhenDisabled:") public static void setAdjustsImageWhenDisabled(UIButton __self__, Selector __cmd__, boolean adjustsImageWhenDisabled) { __self__.setAdjustsImageWhenDisabled(adjustsImageWhenDisabled); }
        @Callback @BindSelector("adjustsImageWhenHighlighted") public static boolean isAdjustsImageWhenHighlighted(UIButton __self__, Selector __cmd__) { return __self__.isAdjustsImageWhenHighlighted(); }
        @Callback @BindSelector("setAdjustsImageWhenHighlighted:") public static void setAdjustsImageWhenHighlighted(UIButton __self__, Selector __cmd__, boolean adjustsImageWhenHighlighted) { __self__.setAdjustsImageWhenHighlighted(adjustsImageWhenHighlighted); }
        @Callback @BindSelector("buttonType") public static UIButtonType getButtonType(UIButton __self__, Selector __cmd__) { return __self__.getButtonType(); }
        @Callback @BindSelector("contentEdgeInsets") public static @ByVal UIEdgeInsets getContentEdgeInsets(UIButton __self__, Selector __cmd__) { return __self__.getContentEdgeInsets(); }
        @Callback @BindSelector("setContentEdgeInsets:") public static void setContentEdgeInsets(UIButton __self__, Selector __cmd__, @ByVal UIEdgeInsets contentEdgeInsets) { __self__.setContentEdgeInsets(contentEdgeInsets); }
        @Callback @BindSelector("currentAttributedTitle") public static NSAttributedString getCurrentAttributedTitle(UIButton __self__, Selector __cmd__) { return __self__.getCurrentAttributedTitle(); }
        @Callback @BindSelector("currentBackgroundImage") public static UIImage getCurrentBackgroundImage(UIButton __self__, Selector __cmd__) { return __self__.getCurrentBackgroundImage(); }
        @Callback @BindSelector("currentImage") public static UIImage getCurrentImage(UIButton __self__, Selector __cmd__) { return __self__.getCurrentImage(); }
        @Callback @BindSelector("currentTitle") public static String getCurrentTitle(UIButton __self__, Selector __cmd__) { return __self__.getCurrentTitle(); }
        @Callback @BindSelector("currentTitleColor") public static UIColor getCurrentTitleColor(UIButton __self__, Selector __cmd__) { return __self__.getCurrentTitleColor(); }
        @Callback @BindSelector("currentTitleShadowColor") public static UIColor getCurrentTitleShadowColor(UIButton __self__, Selector __cmd__) { return __self__.getCurrentTitleShadowColor(); }
        @Callback @BindSelector("imageEdgeInsets") public static @ByVal UIEdgeInsets getImageEdgeInsets(UIButton __self__, Selector __cmd__) { return __self__.getImageEdgeInsets(); }
        @Callback @BindSelector("setImageEdgeInsets:") public static void setImageEdgeInsets(UIButton __self__, Selector __cmd__, @ByVal UIEdgeInsets imageEdgeInsets) { __self__.setImageEdgeInsets(imageEdgeInsets); }
        @Callback @BindSelector("imageView") public static UIImageView getImageView(UIButton __self__, Selector __cmd__) { return __self__.getImageView(); }
        @Callback @BindSelector("reversesTitleShadowWhenHighlighted") public static boolean isReversesTitleShadowWhenHighlighted(UIButton __self__, Selector __cmd__) { return __self__.isReversesTitleShadowWhenHighlighted(); }
        @Callback @BindSelector("setReversesTitleShadowWhenHighlighted:") public static void setReversesTitleShadowWhenHighlighted(UIButton __self__, Selector __cmd__, boolean reversesTitleShadowWhenHighlighted) { __self__.setReversesTitleShadowWhenHighlighted(reversesTitleShadowWhenHighlighted); }
        @Callback @BindSelector("showsTouchWhenHighlighted") public static boolean isShowsTouchWhenHighlighted(UIButton __self__, Selector __cmd__) { return __self__.isShowsTouchWhenHighlighted(); }
        @Callback @BindSelector("setShowsTouchWhenHighlighted:") public static void setShowsTouchWhenHighlighted(UIButton __self__, Selector __cmd__, boolean showsTouchWhenHighlighted) { __self__.setShowsTouchWhenHighlighted(showsTouchWhenHighlighted); }
        @Callback @BindSelector("tintColor") public static UIColor getTintColor(UIButton __self__, Selector __cmd__) { return __self__.getTintColor(); }
        @Callback @BindSelector("setTintColor:") public static void setTintColor(UIButton __self__, Selector __cmd__, UIColor tintColor) { __self__.setTintColor(tintColor); }
        @Callback @BindSelector("titleEdgeInsets") public static @ByVal UIEdgeInsets getTitleEdgeInsets(UIButton __self__, Selector __cmd__) { return __self__.getTitleEdgeInsets(); }
        @Callback @BindSelector("setTitleEdgeInsets:") public static void setTitleEdgeInsets(UIButton __self__, Selector __cmd__, @ByVal UIEdgeInsets titleEdgeInsets) { __self__.setTitleEdgeInsets(titleEdgeInsets); }
        @Callback @BindSelector("titleLabel") public static UILabel getTitleLabel(UIButton __self__, Selector __cmd__) { return __self__.getTitleLabel(); }
        @Callback @BindSelector("attributedTitleForState:") public static NSAttributedString getAttributedTitle(UIButton __self__, Selector __cmd__, UIControlState state) { return __self__.getAttributedTitle(state); }
        @Callback @BindSelector("backgroundImageForState:") public static UIImage getBackgroundImage(UIButton __self__, Selector __cmd__, UIControlState state) { return __self__.getBackgroundImage(state); }
        @Callback @BindSelector("backgroundRectForBounds:") public static @ByVal CGRect getBackgroundRect(UIButton __self__, Selector __cmd__, @ByVal CGRect bounds) { return __self__.getBackgroundRect(bounds); }
        @Callback @BindSelector("contentRectForBounds:") public static @ByVal CGRect getContentRect(UIButton __self__, Selector __cmd__, @ByVal CGRect bounds) { return __self__.getContentRect(bounds); }
        @Callback @BindSelector("imageForState:") public static UIImage getImage(UIButton __self__, Selector __cmd__, UIControlState state) { return __self__.getImage(state); }
        @Callback @BindSelector("imageRectForContentRect:") public static @ByVal CGRect getImageRect(UIButton __self__, Selector __cmd__, @ByVal CGRect contentRect) { return __self__.getImageRect(contentRect); }
        @Callback @BindSelector("titleForState:") public static String getTitle(UIButton __self__, Selector __cmd__, UIControlState state) { return __self__.getTitle(state); }
        @Callback @BindSelector("titleColorForState:") public static UIColor getTitleColor(UIButton __self__, Selector __cmd__, UIControlState state) { return __self__.getTitleColor(state); }
        @Callback @BindSelector("titleRectForContentRect:") public static @ByVal CGRect getTitleRect(UIButton __self__, Selector __cmd__, @ByVal CGRect contentRect) { return __self__.getTitleRect(contentRect); }
        @Callback @BindSelector("titleShadowColorForState:") public static UIColor getTitleShadowColor(UIButton __self__, Selector __cmd__, UIControlState state) { return __self__.getTitleShadowColor(state); }
        @Callback @BindSelector("setAttributedTitle:forState:") public static void setAttributedTitle(UIButton __self__, Selector __cmd__, NSAttributedString title, UIControlState state) { __self__.setAttributedTitle(title, state); }
        @Callback @BindSelector("setBackgroundImage:forState:") public static void setBackgroundImage(UIButton __self__, Selector __cmd__, UIImage image, UIControlState state) { __self__.setBackgroundImage(image, state); }
        @Callback @BindSelector("setImage:forState:") public static void setImage(UIButton __self__, Selector __cmd__, UIImage image, UIControlState state) { __self__.setImage(image, state); }
        @Callback @BindSelector("setTitle:forState:") public static void setTitle(UIButton __self__, Selector __cmd__, String title, UIControlState state) { __self__.setTitle(title, state); }
        @Callback @BindSelector("setTitleColor:forState:") public static void setTitleColor(UIButton __self__, Selector __cmd__, UIColor color, UIControlState state) { __self__.setTitleColor(color, state); }
        @Callback @BindSelector("setTitleShadowColor:forState:") public static void setTitleShadowColor(UIButton __self__, Selector __cmd__, UIColor color, UIControlState state) { __self__.setTitleShadowColor(color, state); }
    }
    /*</callbacks>*/

}
