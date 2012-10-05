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

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIFont /*</name>*/.class);

    /*<constructors>*/
    protected UIFont(SkipInit skipInit) { super(skipInit); }
    public UIFont() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/ascender">@property(nonatomic, readonly) CGFloat ascender</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("ascender") public native float getAscender();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/capHeight">@property(nonatomic, readonly) CGFloat capHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("capHeight") public native float getCapHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/descender">@property(nonatomic, readonly) CGFloat descender</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("descender") public native float getDescender();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/familyName">@property(nonatomic, readonly, retain) NSString *familyName</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("familyName") public native String getFamilyName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/fontName">@property(nonatomic, readonly, retain) NSString *fontName</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("fontName") public native String getFontName();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/lineHeight">@property(nonatomic,readonly) CGFloat lineHeight</a>
     * @since Available in iOS 4.0 and later.
     */
    @Bind("lineHeight") public native float getLineHeight();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/pointSize">@property(nonatomic, readonly) CGFloat pointSize</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("pointSize") public native float getPointSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instp/UIFont/xHeight">@property(nonatomic, readonly) CGFloat xHeight</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("xHeight") public native float getXHeight();
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector boldSystemFontOfSize$ = Selector.register("boldSystemFontOfSize:");
    @Bridge(symbol = "objc_msgSend") private native static UIFont objc_getBoldSystemFont(ObjCClass __self__, Selector __cmd__, float fontSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/boldSystemFontOfSize:">+ (UIFont *)boldSystemFontOfSize:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIFont getBoldSystemFont(float fontSize) {
        return objc_getBoldSystemFont(objCClass, boldSystemFontOfSize$, fontSize);
    }
    
    private static final Selector buttonFontSize = Selector.register("buttonFontSize");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getButtonFontSize(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/buttonFontSize">+ (CGFloat)buttonFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static float getButtonFontSize() {
        return objc_getButtonFontSize(objCClass, buttonFontSize);
    }
    
    private static final Selector familyNames = Selector.register("familyNames");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getFamilyNames(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/familyNames">+ (NSArray *)familyNames</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSArray getFamilyNames() {
        return objc_getFamilyNames(objCClass, familyNames);
    }
    
    private static final Selector fontWithName$size$ = Selector.register("fontWithName:size:");
    @Bridge(symbol = "objc_msgSend") private native static UIFont objc_getFont(ObjCClass __self__, Selector __cmd__, String fontName, float fontSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/fontWithName:size:">+ (UIFont *)fontWithName:(NSString *)fontName size:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIFont getFont(String fontName, float fontSize) {
        return objc_getFont(objCClass, fontWithName$size$, fontName, fontSize);
    }
    
    private static final Selector fontNamesForFamilyName$ = Selector.register("fontNamesForFamilyName:");
    @Bridge(symbol = "objc_msgSend") private native static NSArray objc_getFontNamesForFamilyName(ObjCClass __self__, Selector __cmd__, String familyName);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/fontNamesForFamilyName:">+ (NSArray *)fontNamesForFamilyName:(NSString *)familyName</a>
     * @since Available in iOS 2.0 and later.
     */
    public static NSArray getFontNamesForFamilyName(String familyName) {
        return objc_getFontNamesForFamilyName(objCClass, fontNamesForFamilyName$, familyName);
    }
    
    private static final Selector italicSystemFontOfSize$ = Selector.register("italicSystemFontOfSize:");
    @Bridge(symbol = "objc_msgSend") private native static UIFont objc_getItalicSystemFont(ObjCClass __self__, Selector __cmd__, float fontSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/italicSystemFontOfSize:">+ (UIFont *)italicSystemFontOfSize:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIFont getItalicSystemFont(float fontSize) {
        return objc_getItalicSystemFont(objCClass, italicSystemFontOfSize$, fontSize);
    }
    
    private static final Selector labelFontSize = Selector.register("labelFontSize");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getLabelFontSize(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/labelFontSize">+ (CGFloat)labelFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static float getLabelFontSize() {
        return objc_getLabelFontSize(objCClass, labelFontSize);
    }
    
    private static final Selector smallSystemFontSize = Selector.register("smallSystemFontSize");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getSmallSystemFontSize(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/smallSystemFontSize">+ (CGFloat)smallSystemFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static float getSmallSystemFontSize() {
        return objc_getSmallSystemFontSize(objCClass, smallSystemFontSize);
    }
    
    private static final Selector systemFontOfSize$ = Selector.register("systemFontOfSize:");
    @Bridge(symbol = "objc_msgSend") private native static UIFont objc_getSystemFont(ObjCClass __self__, Selector __cmd__, float fontSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/systemFontOfSize:">+ (UIFont *)systemFontOfSize:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static UIFont getSystemFont(float fontSize) {
        return objc_getSystemFont(objCClass, systemFontOfSize$, fontSize);
    }
    
    private static final Selector systemFontSize = Selector.register("systemFontSize");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getSystemFontSize(ObjCClass __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/clm/UIFont/systemFontSize">+ (CGFloat)systemFontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public static float getSystemFontSize() {
        return objc_getSystemFontSize(objCClass, systemFontSize);
    }
    
    private static final Selector fontWithSize$ = Selector.register("fontWithSize:");
    @Bridge(symbol = "objc_msgSend") private native static UIFont objc_getFontWithSize(UIFont __self__, Selector __cmd__, float fontSize);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIFont objc_getFontWithSizeSuper(ObjCSuper __super__, UIFont __self__, Selector __cmd__, float fontSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIFont_Class/Reference/Reference.html#//apple_ref/occ/instm/UIFont/fontWithSize:">- (UIFont *)fontWithSize:(CGFloat)fontSize</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIFont getFontWithSize(float fontSize) {
        if (customClass) { return objc_getFontWithSizeSuper(getSuper(), this, fontWithSize$, fontSize); } else { return objc_getFontWithSize(this, fontWithSize$, fontSize); }
    }
    /*</methods>*/

}
