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

    /*<constructors>*/
    public UIButton() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenDisabled">@property(nonatomic) BOOL adjustsImageWhenDisabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("adjustsImageWhenDisabled") public native @Type("BOOL") boolean isAdjustsImageWhenDisabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenDisabled">@property(nonatomic) BOOL adjustsImageWhenDisabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAdjustsImageWhenDisabled:") public native void setAdjustsImageWhenDisabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenHighlighted">@property(nonatomic) BOOL adjustsImageWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("adjustsImageWhenHighlighted") public native @Type("BOOL") boolean isAdjustsImageWhenHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/adjustsImageWhenHighlighted">@property(nonatomic) BOOL adjustsImageWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setAdjustsImageWhenHighlighted:") public native void setAdjustsImageWhenHighlighted(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/buttonType">@property(nonatomic, readonly) UIButtonType buttonType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("buttonType") public native @Type("UIButtonType") UIButtonType getButtonType();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/contentEdgeInsets">@property(nonatomic) UIEdgeInsets contentEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentEdgeInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getContentEdgeInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/contentEdgeInsets">@property(nonatomic) UIEdgeInsets contentEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setContentEdgeInsets:") public native void setContentEdgeInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentAttributedTitle">@property(nonatomic,readonly,retain) NSAttributedString *currentAttributedTitle</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("currentAttributedTitle") public native @Type("NSAttributedString *") NSAttributedString getCurrentAttributedTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentBackgroundImage">@property(nonatomic, readonly, retain) UIImage *currentBackgroundImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentBackgroundImage") public native @Type("UIImage *") UIImage getCurrentBackgroundImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentImage">@property(nonatomic, readonly, retain) UIImage *currentImage</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentImage") public native @Type("UIImage *") UIImage getCurrentImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentTitle">@property(nonatomic, readonly, retain) NSString *currentTitle</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentTitle") public native @Type("NSString *") String getCurrentTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentTitleColor">@property(nonatomic, readonly, retain) UIColor *currentTitleColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentTitleColor") public native @Type("UIColor *") UIColor getCurrentTitleColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/currentTitleShadowColor">@property(nonatomic, readonly, retain) UIColor *currentTitleShadowColor</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("currentTitleShadowColor") public native @Type("UIColor *") UIColor getCurrentTitleShadowColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/imageEdgeInsets">@property(nonatomic) UIEdgeInsets imageEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("imageEdgeInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getImageEdgeInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/imageEdgeInsets">@property(nonatomic) UIEdgeInsets imageEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setImageEdgeInsets:") public native void setImageEdgeInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/imageView">@property(nonatomic, readonly, retain) UIImageView *imageView</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("imageView") public native @Type("UIImageView *") UIImageView getImageView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/reversesTitleShadowWhenHighlighted">@property(nonatomic) BOOL reversesTitleShadowWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("reversesTitleShadowWhenHighlighted") public native @Type("BOOL") boolean isReversesTitleShadowWhenHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/reversesTitleShadowWhenHighlighted">@property(nonatomic) BOOL reversesTitleShadowWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setReversesTitleShadowWhenHighlighted:") public native void setReversesTitleShadowWhenHighlighted(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/showsTouchWhenHighlighted">@property(nonatomic) BOOL showsTouchWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("showsTouchWhenHighlighted") public native @Type("BOOL") boolean isShowsTouchWhenHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/showsTouchWhenHighlighted">@property(nonatomic) BOOL showsTouchWhenHighlighted</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setShowsTouchWhenHighlighted:") public native void setShowsTouchWhenHighlighted(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("tintColor") public native @Type("UIColor *") UIColor getTintColor();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/tintColor">@property(nonatomic, retain) UIColor *tintColor</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTintColor:") public native void setTintColor(@Type("UIColor *") UIColor v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/titleEdgeInsets">@property(nonatomic) UIEdgeInsets titleEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("titleEdgeInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getTitleEdgeInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/titleEdgeInsets">@property(nonatomic) UIEdgeInsets titleEdgeInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitleEdgeInsets:") public native void setTitleEdgeInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instp/UIButton/titleLabel">@property(nonatomic, readonly, retain) UILabel *titleLabel</a>
     * @since Available in iOS 3.0 and later.
     */
    @Bind("titleLabel") public native @Type("UILabel *") UILabel getTitleLabel();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/clm/UIButton/buttonWithType:">+ (id)buttonWithType:(UIButtonType)buttonType</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("buttonWithType:") public native static @Type("id") UIButton fromType(@Type("UIButtonType") UIButtonType buttonType);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/attributedTitleForState:">- (NSAttributedString *)attributedTitleForState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("attributedTitleForState:") public native @Type("NSAttributedString *") NSAttributedString getAttributedTitle(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/backgroundImageForState:">- (UIImage *)backgroundImageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("backgroundImageForState:") public native @Type("UIImage *") UIImage getBackgroundImage(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/backgroundRectForBounds:">- (CGRect)backgroundRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("backgroundRectForBounds:") public native @Type("CGRect") CGRect getBackgroundRect(@Type("CGRect") CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/contentRectForBounds:">- (CGRect)contentRectForBounds:(CGRect)bounds</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("contentRectForBounds:") public native @Type("CGRect") CGRect getContentRect(@Type("CGRect") CGRect bounds);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/imageForState:">- (UIImage *)imageForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("imageForState:") public native @Type("UIImage *") UIImage getImage(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/imageRectForContentRect:">- (CGRect)imageRectForContentRect:(CGRect)contentRect</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("imageRectForContentRect:") public native @Type("CGRect") CGRect getImageRect(@Type("CGRect") CGRect contentRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/titleForState:">- (NSString *)titleForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("titleForState:") public native @Type("NSString *") String getTitle(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/titleColorForState:">- (UIColor *)titleColorForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("titleColorForState:") public native @Type("UIColor *") UIColor getTitleColor(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/titleRectForContentRect:">- (CGRect)titleRectForContentRect:(CGRect)contentRect</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("titleRectForContentRect:") public native @Type("CGRect") CGRect getTitleRect(@Type("CGRect") CGRect contentRect);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/titleShadowColorForState:">- (UIColor *)titleShadowColorForState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("titleShadowColorForState:") public native @Type("UIColor *") UIColor getTitleShadowColor(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setAttributedTitle:forState:">- (void)setAttributedTitle:(NSAttributedString *)title forState:(UIControlState)state</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAttributedTitle:forState:") public native @Type("void") void setAttributedTitle(@Type("NSAttributedString *") NSAttributedString title, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setBackgroundImage:forState:">- (void)setBackgroundImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBackgroundImage:forState:") public native @Type("void") void setBackgroundImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setImage:forState:">- (void)setImage:(UIImage *)image forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setImage:forState:") public native @Type("void") void setImage(@Type("UIImage *") UIImage image, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setTitle:forState:">- (void)setTitle:(NSString *)title forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitle:forState:") public native @Type("void") void setTitle(@Type("NSString *") String title, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setTitleColor:forState:">- (void)setTitleColor:(UIColor *)color forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitleColor:forState:") public native @Type("void") void setTitleColor(@Type("UIColor *") UIColor color, @Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIButton_Class/UIButton/UIButton.html#//apple_ref/occ/instm/UIButton/setTitleShadowColor:forState:">- (void)setTitleShadowColor:(UIColor *)color forState:(UIControlState)state</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitleShadowColor:forState:") public native @Type("void") void setTitleShadowColor(@Type("UIColor *") UIColor color, @Type("UIControlState") UIControlState state);
    /*</methods>*/

}
