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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html">UIFont Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UIFont /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIFont /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIFont /*</name>*/.class);

    /*<constructors>*/
    protected UIFont(SkipInit skipInit) { super(skipInit); }
    public UIFont() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector ascender = Selector.register("ascender");
    @Bridge private native static float objc_getAscender(UIFont __self__, Selector __cmd__);
    @Bridge private native static float objc_getAscenderSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/ascender">@property(nonatomic, readonly) CGFloat ascender</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getAscender() {
        if (customClass) { return objc_getAscenderSuper(getSuper(), ascender); } else { return objc_getAscender(this, ascender); }
    }
    
    private static final Selector capHeight = Selector.register("capHeight");
    @Bridge private native static float objc_getCapHeight(UIFont __self__, Selector __cmd__);
    @Bridge private native static float objc_getCapHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/capHeight">@property(nonatomic, readonly) CGFloat capHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getCapHeight() {
        if (customClass) { return objc_getCapHeightSuper(getSuper(), capHeight); } else { return objc_getCapHeight(this, capHeight); }
    }
    
    private static final Selector descender = Selector.register("descender");
    @Bridge private native static float objc_getDescender(UIFont __self__, Selector __cmd__);
    @Bridge private native static float objc_getDescenderSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/descender">@property(nonatomic, readonly) CGFloat descender</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getDescender() {
        if (customClass) { return objc_getDescenderSuper(getSuper(), descender); } else { return objc_getDescender(this, descender); }
    }
    
    private static final Selector familyName = Selector.register("familyName");
    @Bridge private native static String objc_getFamilyName(UIFont __self__, Selector __cmd__);
    @Bridge private native static String objc_getFamilyNameSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/familyName">@property(nonatomic, readonly, retain) NSString *familyName</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getFamilyName() {
        if (customClass) { return objc_getFamilyNameSuper(getSuper(), familyName); } else { return objc_getFamilyName(this, familyName); }
    }
    
    private static final Selector fontName = Selector.register("fontName");
    @Bridge private native static String objc_getFontName(UIFont __self__, Selector __cmd__);
    @Bridge private native static String objc_getFontNameSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/fontName">@property(nonatomic, readonly, retain) NSString *fontName</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getFontName() {
        if (customClass) { return objc_getFontNameSuper(getSuper(), fontName); } else { return objc_getFontName(this, fontName); }
    }
    
    private static final Selector lineHeight = Selector.register("lineHeight");
    @Bridge private native static float objc_getLineHeight(UIFont __self__, Selector __cmd__);
    @Bridge private native static float objc_getLineHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/lineHeight">@property(nonatomic,readonly) CGFloat lineHeight</a>
     * @since Available in iOS 4.0 and later.
     */
    public float getLineHeight() {
        if (customClass) { return objc_getLineHeightSuper(getSuper(), lineHeight); } else { return objc_getLineHeight(this, lineHeight); }
    }
    
    private static final Selector pointSize = Selector.register("pointSize");
    @Bridge private native static float objc_getPointSize(UIFont __self__, Selector __cmd__);
    @Bridge private native static float objc_getPointSizeSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/pointSize">@property(nonatomic, readonly) CGFloat pointSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getPointSize() {
        if (customClass) { return objc_getPointSizeSuper(getSuper(), pointSize); } else { return objc_getPointSize(this, pointSize); }
    }
    
    private static final Selector xHeight = Selector.register("xHeight");
    @Bridge private native static float objc_getXHeight(UIFont __self__, Selector __cmd__);
    @Bridge private native static float objc_getXHeightSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/xHeight">@property(nonatomic, readonly) CGFloat xHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    public float getXHeight() {
        if (customClass) { return objc_getXHeightSuper(getSuper(), xHeight); } else { return objc_getXHeight(this, xHeight); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector boldSystemFontOfSize$ = Selector.register("boldSystemFontOfSize:");
    @Bridge private native static UIFont objc_getBoldSystemFont(ObjCClass __self__, Selector __cmd__, float fontSize);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/boldSystemFontOfSize:">+ (UIFont *)boldSystemFontOfSize:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIFont getBoldSystemFont(float fontSize) {
        return objc_getBoldSystemFont(objCClass, boldSystemFontOfSize$, fontSize);
    }
    
    private static final Selector buttonFontSize = Selector.register("buttonFontSize");
    @Bridge private native static float objc_getButtonFontSize(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/buttonFontSize">+ (CGFloat)buttonFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static float getButtonFontSize() {
        return objc_getButtonFontSize(objCClass, buttonFontSize);
    }
    
    private static final Selector familyNames = Selector.register("familyNames");
    @Bridge private native static NSArray objc_getFamilyNames(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/familyNames">+ (NSArray *)familyNames</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSArray getFamilyNames() {
        return objc_getFamilyNames(objCClass, familyNames);
    }
    
    private static final Selector fontWithName$size$ = Selector.register("fontWithName:size:");
    @Bridge private native static UIFont objc_getFont(ObjCClass __self__, Selector __cmd__, String fontName, float fontSize);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/fontWithName:size:">+ (UIFont *)fontWithName:(NSString *)fontName size:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIFont getFont(String fontName, float fontSize) {
        return objc_getFont(objCClass, fontWithName$size$, fontName, fontSize);
    }
    
    private static final Selector fontNamesForFamilyName$ = Selector.register("fontNamesForFamilyName:");
    @Bridge private native static NSArray objc_getFontNamesForFamilyName(ObjCClass __self__, Selector __cmd__, String familyName);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/fontNamesForFamilyName:">+ (NSArray *)fontNamesForFamilyName:(NSString *)familyName</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSArray getFontNamesForFamilyName(String familyName) {
        return objc_getFontNamesForFamilyName(objCClass, fontNamesForFamilyName$, familyName);
    }
    
    private static final Selector italicSystemFontOfSize$ = Selector.register("italicSystemFontOfSize:");
    @Bridge private native static UIFont objc_getItalicSystemFont(ObjCClass __self__, Selector __cmd__, float fontSize);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/italicSystemFontOfSize:">+ (UIFont *)italicSystemFontOfSize:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIFont getItalicSystemFont(float fontSize) {
        return objc_getItalicSystemFont(objCClass, italicSystemFontOfSize$, fontSize);
    }
    
    private static final Selector labelFontSize = Selector.register("labelFontSize");
    @Bridge private native static float objc_getLabelFontSize(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/labelFontSize">+ (CGFloat)labelFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static float getLabelFontSize() {
        return objc_getLabelFontSize(objCClass, labelFontSize);
    }
    
    private static final Selector smallSystemFontSize = Selector.register("smallSystemFontSize");
    @Bridge private native static float objc_getSmallSystemFontSize(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/smallSystemFontSize">+ (CGFloat)smallSystemFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static float getSmallSystemFontSize() {
        return objc_getSmallSystemFontSize(objCClass, smallSystemFontSize);
    }
    
    private static final Selector systemFontOfSize$ = Selector.register("systemFontOfSize:");
    @Bridge private native static UIFont objc_getSystemFont(ObjCClass __self__, Selector __cmd__, float fontSize);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/systemFontOfSize:">+ (UIFont *)systemFontOfSize:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIFont getSystemFont(float fontSize) {
        return objc_getSystemFont(objCClass, systemFontOfSize$, fontSize);
    }
    
    private static final Selector systemFontSize = Selector.register("systemFontSize");
    @Bridge private native static float objc_getSystemFontSize(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/systemFontSize">+ (CGFloat)systemFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static float getSystemFontSize() {
        return objc_getSystemFontSize(objCClass, systemFontSize);
    }
    
    private static final Selector fontWithSize$ = Selector.register("fontWithSize:");
    @Bridge private native static UIFont objc_getFontWithSize(UIFont __self__, Selector __cmd__, float fontSize);
    @Bridge private native static UIFont objc_getFontWithSizeSuper(ObjCSuper __super__, Selector __cmd__, float fontSize);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instm/UIFont/fontWithSize:">- (UIFont *)fontWithSize:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIFont getFontWithSize(float fontSize) {
        if (customClass) { return objc_getFontWithSizeSuper(getSuper(), fontWithSize$, fontSize); } else { return objc_getFontWithSize(this, fontWithSize$, fontSize); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("ascender") public static float getAscender(UIFont __self__, Selector __cmd__) { return __self__.getAscender(); }
        @Callback @BindSelector("capHeight") public static float getCapHeight(UIFont __self__, Selector __cmd__) { return __self__.getCapHeight(); }
        @Callback @BindSelector("descender") public static float getDescender(UIFont __self__, Selector __cmd__) { return __self__.getDescender(); }
        @Callback @BindSelector("familyName") public static String getFamilyName(UIFont __self__, Selector __cmd__) { return __self__.getFamilyName(); }
        @Callback @BindSelector("fontName") public static String getFontName(UIFont __self__, Selector __cmd__) { return __self__.getFontName(); }
        @Callback @BindSelector("lineHeight") public static float getLineHeight(UIFont __self__, Selector __cmd__) { return __self__.getLineHeight(); }
        @Callback @BindSelector("pointSize") public static float getPointSize(UIFont __self__, Selector __cmd__) { return __self__.getPointSize(); }
        @Callback @BindSelector("xHeight") public static float getXHeight(UIFont __self__, Selector __cmd__) { return __self__.getXHeight(); }
        @Callback @BindSelector("fontWithSize:") public static UIFont getFontWithSize(UIFont __self__, Selector __cmd__, float fontSize) { return __self__.getFontWithSize(fontSize); }
    }
    /*</callbacks>*/

}
