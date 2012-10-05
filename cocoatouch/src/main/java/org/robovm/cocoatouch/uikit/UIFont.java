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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html">UIFont Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIFont /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIFont /*</name>*/.class);
    }

    /*<constructors>*/
    public UIFont() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/ascender">@property(nonatomic, readonly) CGFloat ascender</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("ascender") public native @Type("CGFloat") float getAscender();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/capHeight">@property(nonatomic, readonly) CGFloat capHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("capHeight") public native @Type("CGFloat") float getCapHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/descender">@property(nonatomic, readonly) CGFloat descender</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("descender") public native @Type("CGFloat") float getDescender();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/familyName">@property(nonatomic, readonly, retain) NSString *familyName</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("familyName") public native @Type("NSString *") String getFamilyName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/fontName">@property(nonatomic, readonly, retain) NSString *fontName</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("fontName") public native @Type("NSString *") String getFontName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/lineHeight">@property(nonatomic,readonly) CGFloat lineHeight</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("lineHeight") public native @Type("CGFloat") float getLineHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/pointSize">@property(nonatomic, readonly) CGFloat pointSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("pointSize") public native @Type("CGFloat") float getPointSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/xHeight">@property(nonatomic, readonly) CGFloat xHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("xHeight") public native @Type("CGFloat") float getXHeight();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/boldSystemFontOfSize:">+ (UIFont *)boldSystemFontOfSize:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("boldSystemFontOfSize:") public native static @Type("UIFont *") UIFont getBoldSystemFont(@Type("CGFloat") float fontSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/buttonFontSize">+ (CGFloat)buttonFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("buttonFontSize") public native static @Type("CGFloat") float getButtonFontSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/familyNames">+ (NSArray *)familyNames</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("familyNames") public native static @Type("NSArray *") NSArray getFamilyNames();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/fontWithName:size:">+ (UIFont *)fontWithName:(NSString *)fontName size:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("fontWithName:size:") public native static @Type("UIFont *") UIFont getFont(@Type("NSString *") String fontName, @Type("CGFloat") float fontSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/fontNamesForFamilyName:">+ (NSArray *)fontNamesForFamilyName:(NSString *)familyName</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("fontNamesForFamilyName:") public native static @Type("NSArray *") NSArray getFontNamesForFamilyName(@Type("NSString *") String familyName);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/italicSystemFontOfSize:">+ (UIFont *)italicSystemFontOfSize:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("italicSystemFontOfSize:") public native static @Type("UIFont *") UIFont getItalicSystemFont(@Type("CGFloat") float fontSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/labelFontSize">+ (CGFloat)labelFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("labelFontSize") public native static @Type("CGFloat") float getLabelFontSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/smallSystemFontSize">+ (CGFloat)smallSystemFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("smallSystemFontSize") public native static @Type("CGFloat") float getSmallSystemFontSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/systemFontOfSize:">+ (UIFont *)systemFontOfSize:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("systemFontOfSize:") public native static @Type("UIFont *") UIFont getSystemFont(@Type("CGFloat") float fontSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/systemFontSize">+ (CGFloat)systemFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("systemFontSize") public native static @Type("CGFloat") float getSystemFontSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instm/UIFont/fontWithSize:">- (UIFont *)fontWithSize:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("fontWithSize:") public native @Type("UIFont *") UIFont getFontWithSize(@Type("CGFloat") float fontSize);
    /*</methods>*/

}
