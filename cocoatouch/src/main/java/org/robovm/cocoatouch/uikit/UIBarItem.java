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
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 *
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html">UIBarItem Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIBarItem /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ implements UIAppearance /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIBarItem /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UIBarItem /*</name>*/.class);

    /*<constructors>*/
    protected UIBarItem(SkipInit skipInit) { super(skipInit); }
    public UIBarItem() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector isEnabled = Selector.register("isEnabled");
    @Bridge(symbol = "objc_msgSend") private native static boolean objc_isEnabled(UIBarItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static boolean objc_isEnabledSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public boolean isEnabled() {
        if (customClass) { return objc_isEnabledSuper(getSuper(), this, isEnabled); } else { return objc_isEnabled(this, isEnabled); }
    }
    
    private static final Selector setEnabled$ = Selector.register("setEnabled:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setEnabled(UIBarItem __self__, Selector __cmd__, boolean enabled);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setEnabledSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__, boolean enabled);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setEnabled(boolean enabled) {
        if (customClass) { objc_setEnabledSuper(getSuper(), this, setEnabled$, enabled); } else { objc_setEnabled(this, setEnabled$, enabled); }
    }
    
    private static final Selector image = Selector.register("image");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getImage(UIBarItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getImageSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/image">@property(nonatomic, retain) UIImage *image</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIImage getImage() {
        if (customClass) { return objc_getImageSuper(getSuper(), this, image); } else { return objc_getImage(this, image); }
    }
    
    private static final Selector setImage$ = Selector.register("setImage:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setImage(UIBarItem __self__, Selector __cmd__, UIImage image);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setImageSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__, UIImage image);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/image">@property(nonatomic, retain) UIImage *image</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setImage(UIImage image) {
        if (customClass) { objc_setImageSuper(getSuper(), this, setImage$, image); } else { objc_setImage(this, setImage$, image); }
    }
    
    private static final Selector imageInsets = Selector.register("imageInsets");
    @Bridge(symbol = "objc_msgSend") private native static UIEdgeInsets objc_getImageInsets(UIBarItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIEdgeInsets objc_getImageInsetsSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/imageInsets">@property(nonatomic) UIEdgeInsets imageInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public UIEdgeInsets getImageInsets() {
        if (customClass) { return objc_getImageInsetsSuper(getSuper(), this, imageInsets); } else { return objc_getImageInsets(this, imageInsets); }
    }
    
    private static final Selector setImageInsets$ = Selector.register("setImageInsets:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setImageInsets(UIBarItem __self__, Selector __cmd__, UIEdgeInsets imageInsets);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setImageInsetsSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__, UIEdgeInsets imageInsets);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/imageInsets">@property(nonatomic) UIEdgeInsets imageInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setImageInsets(UIEdgeInsets imageInsets) {
        if (customClass) { objc_setImageInsetsSuper(getSuper(), this, setImageInsets$, imageInsets); } else { objc_setImageInsets(this, setImageInsets$, imageInsets); }
    }
    
    private static final Selector landscapeImagePhone = Selector.register("landscapeImagePhone");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getLandscapeImagePhone(UIBarItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getLandscapeImagePhoneSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/landscapeImagePhone">@property(nonatomic, retain) UIImage *landscapeImagePhone</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getLandscapeImagePhone() {
        if (customClass) { return objc_getLandscapeImagePhoneSuper(getSuper(), this, landscapeImagePhone); } else { return objc_getLandscapeImagePhone(this, landscapeImagePhone); }
    }
    
    private static final Selector setLandscapeImagePhone$ = Selector.register("setLandscapeImagePhone:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setLandscapeImagePhone(UIBarItem __self__, Selector __cmd__, UIImage landscapeImagePhone);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setLandscapeImagePhoneSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__, UIImage landscapeImagePhone);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/landscapeImagePhone">@property(nonatomic, retain) UIImage *landscapeImagePhone</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setLandscapeImagePhone(UIImage landscapeImagePhone) {
        if (customClass) { objc_setLandscapeImagePhoneSuper(getSuper(), this, setLandscapeImagePhone$, landscapeImagePhone); } else { objc_setLandscapeImagePhone(this, setLandscapeImagePhone$, landscapeImagePhone); }
    }
    
    private static final Selector landscapeImagePhoneInsets = Selector.register("landscapeImagePhoneInsets");
    @Bridge(symbol = "objc_msgSend") private native static UIEdgeInsets objc_getLandscapeImagePhoneInsets(UIBarItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIEdgeInsets objc_getLandscapeImagePhoneInsetsSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/landscapeImagePhoneInsets">@property(nonatomic) UIEdgeInsets landscapeImagePhoneInsets</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIEdgeInsets getLandscapeImagePhoneInsets() {
        if (customClass) { return objc_getLandscapeImagePhoneInsetsSuper(getSuper(), this, landscapeImagePhoneInsets); } else { return objc_getLandscapeImagePhoneInsets(this, landscapeImagePhoneInsets); }
    }
    
    private static final Selector setLandscapeImagePhoneInsets$ = Selector.register("setLandscapeImagePhoneInsets:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setLandscapeImagePhoneInsets(UIBarItem __self__, Selector __cmd__, UIEdgeInsets landscapeImagePhoneInsets);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setLandscapeImagePhoneInsetsSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__, UIEdgeInsets landscapeImagePhoneInsets);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/landscapeImagePhoneInsets">@property(nonatomic) UIEdgeInsets landscapeImagePhoneInsets</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setLandscapeImagePhoneInsets(UIEdgeInsets landscapeImagePhoneInsets) {
        if (customClass) { objc_setLandscapeImagePhoneInsetsSuper(getSuper(), this, setLandscapeImagePhoneInsets$, landscapeImagePhoneInsets); } else { objc_setLandscapeImagePhoneInsets(this, setLandscapeImagePhoneInsets$, landscapeImagePhoneInsets); }
    }
    
    private static final Selector tag = Selector.register("tag");
    @Bridge(symbol = "objc_msgSend") private native static int objc_getTag(UIBarItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static int objc_getTagSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/tag">@property(nonatomic) NSInteger tag</a>
     * @since Available in iOS 2.0 and later.
     */
    public int getTag() {
        if (customClass) { return objc_getTagSuper(getSuper(), this, tag); } else { return objc_getTag(this, tag); }
    }
    
    private static final Selector setTag$ = Selector.register("setTag:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTag(UIBarItem __self__, Selector __cmd__, int tag);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTagSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__, int tag);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/tag">@property(nonatomic) NSInteger tag</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTag(int tag) {
        if (customClass) { objc_setTagSuper(getSuper(), this, setTag$, tag); } else { objc_setTag(this, setTag$, tag); }
    }
    
    private static final Selector title = Selector.register("title");
    @Bridge(symbol = "objc_msgSend") private native static String objc_getTitle(UIBarItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static String objc_getTitleSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    public String getTitle() {
        if (customClass) { return objc_getTitleSuper(getSuper(), this, title); } else { return objc_getTitle(this, title); }
    }
    
    private static final Selector setTitle$ = Selector.register("setTitle:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitle(UIBarItem __self__, Selector __cmd__, String title);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitleSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__, String title);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    public void setTitle(String title) {
        if (customClass) { objc_setTitleSuper(getSuper(), this, setTitle$, title); } else { objc_setTitle(this, setTitle$, title); }
    }
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector titleTextAttributesForState$ = Selector.register("titleTextAttributesForState:");
    @Bridge(symbol = "objc_msgSend") private native static NSDictionary objc_getTitleTextAttributes(UIBarItem __self__, Selector __cmd__, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static NSDictionary objc_getTitleTextAttributesSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarItem/titleTextAttributesForState:">- (NSDictionary *)titleTextAttributesForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public NSDictionary getTitleTextAttributes(UIControlState state) {
        if (customClass) { return objc_getTitleTextAttributesSuper(getSuper(), this, titleTextAttributesForState$, state); } else { return objc_getTitleTextAttributes(this, titleTextAttributesForState$, state); }
    }
    
    private static final Selector setTitleTextAttributes$forState$ = Selector.register("setTitleTextAttributes:forState:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitleTextAttributes(UIBarItem __self__, Selector __cmd__, NSDictionary attributes, UIControlState state);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitleTextAttributesSuper(ObjCSuper __super__, UIBarItem __self__, Selector __cmd__, NSDictionary attributes, UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarItem/setTitleTextAttributes:forState:">- (void)setTitleTextAttributes:(NSDictionary *)attributes forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTitleTextAttributes(NSDictionary attributes, UIControlState state) {
        if (customClass) { objc_setTitleTextAttributesSuper(getSuper(), this, setTitleTextAttributes$forState$, attributes, state); } else { objc_setTitleTextAttributes(this, setTitleTextAttributes$forState$, attributes, state); }
    }
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("titleTextAttributesForState:") public static NSDictionary getTitleTextAttributes(UIBarItem __self__, Selector __cmd__, UIControlState state) { return __self__.getTitleTextAttributes(state); }
        @Callback @BindSelector("setTitleTextAttributes:forState:") public static void setTitleTextAttributes(UIBarItem __self__, Selector __cmd__, NSDictionary attributes, UIControlState state) { __self__.setTitleTextAttributes(attributes, state); }
    }
    /*</callbacks>*/

}
