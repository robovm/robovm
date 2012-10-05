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

    /*<constructors>*/
    public UIBarItem() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("isEnabled") public native @Type("BOOL") boolean isEnabled();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/enabled">@property(nonatomic, getter=isEnabled) BOOL enabled</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setEnabled:") public native void setEnabled(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/image">@property(nonatomic, retain) UIImage *image</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("image") public native @Type("UIImage *") UIImage getImage();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/image">@property(nonatomic, retain) UIImage *image</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setImage:") public native void setImage(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/imageInsets">@property(nonatomic) UIEdgeInsets imageInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("imageInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getImageInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/imageInsets">@property(nonatomic) UIEdgeInsets imageInsets</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setImageInsets:") public native void setImageInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/landscapeImagePhone">@property(nonatomic, retain) UIImage *landscapeImagePhone</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("landscapeImagePhone") public native @Type("UIImage *") UIImage getLandscapeImagePhone();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/landscapeImagePhone">@property(nonatomic, retain) UIImage *landscapeImagePhone</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setLandscapeImagePhone:") public native void setLandscapeImagePhone(@Type("UIImage *") UIImage v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/landscapeImagePhoneInsets">@property(nonatomic) UIEdgeInsets landscapeImagePhoneInsets</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("landscapeImagePhoneInsets") public native @Type("UIEdgeInsets") UIEdgeInsets getLandscapeImagePhoneInsets();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/landscapeImagePhoneInsets">@property(nonatomic) UIEdgeInsets landscapeImagePhoneInsets</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setLandscapeImagePhoneInsets:") public native void setLandscapeImagePhoneInsets(@Type("UIEdgeInsets") UIEdgeInsets v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/tag">@property(nonatomic) NSInteger tag</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("tag") public native @Type("NSInteger") int getTag();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/tag">@property(nonatomic) NSInteger tag</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTag:") public native void setTag(@Type("NSInteger") int v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("title") public native @Type("NSString *") String getTitle();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UIBarItem/title">@property(nonatomic, copy) NSString *title</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setTitle:") public native void setTitle(@Type("NSString *") String v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarItem/titleTextAttributesForState:">- (NSDictionary *)titleTextAttributesForState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("titleTextAttributesForState:") public native @Type("NSDictionary *") NSDictionary getTitleTextAttributes(@Type("UIControlState") UIControlState state);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UIBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UIBarItem/setTitleTextAttributes:forState:">- (void)setTitleTextAttributes:(NSDictionary *)attributes forState:(UIControlState)state</a>
     * @since Available in iOS 5.0 and later.
     */
    @Bind("setTitleTextAttributes:forState:") public native @Type("void") void setTitleTextAttributes(@Type("NSDictionary *") NSDictionary attributes, @Type("UIControlState") UIControlState state);
    /*</methods>*/

}
