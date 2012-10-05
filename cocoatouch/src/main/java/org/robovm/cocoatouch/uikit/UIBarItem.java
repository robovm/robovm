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
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEnabled") public native boolean isEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEnabled:") public native void setEnabled(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/image">@property(nonatomic, retain) UIImage *image</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("image") public native UIImage getImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/image">@property(nonatomic, retain) UIImage *image</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setImage:") public native void setImage(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/imageInsets">@property(nonatomic) UIEdgeInsets imageInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("imageInsets") public native UIEdgeInsets getImageInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/imageInsets">@property(nonatomic) UIEdgeInsets imageInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setImageInsets:") public native void setImageInsets(UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/landscapeImagePhone">@property(nonatomic, retain) UIImage *landscapeImagePhone</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("landscapeImagePhone") public native UIImage getLandscapeImagePhone();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/landscapeImagePhone">@property(nonatomic, retain) UIImage *landscapeImagePhone</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setLandscapeImagePhone:") public native void setLandscapeImagePhone(UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/landscapeImagePhoneInsets">@property(nonatomic) UIEdgeInsets landscapeImagePhoneInsets</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("landscapeImagePhoneInsets") public native UIEdgeInsets getLandscapeImagePhoneInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/landscapeImagePhoneInsets">@property(nonatomic) UIEdgeInsets landscapeImagePhoneInsets</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setLandscapeImagePhoneInsets:") public native void setLandscapeImagePhoneInsets(UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/tag">@property(nonatomic) NSInteger tag</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tag") public native int getTag();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/tag">@property(nonatomic) NSInteger tag</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTag:") public native void setTag(int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("title") public native String getTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitle:") public native void setTitle(String v);
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

}
