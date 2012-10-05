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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html">UITabBarItem Class Reference</a>
 *   @since Available in iOS 2.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UITabBarItem /*</name>*/ 
    extends /*<extends>*/ UIBarItem /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITabBarItem /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UITabBarItem /*</name>*/.class);

    /*<constructors>*/
    protected UITabBarItem(SkipInit skipInit) { super(skipInit); }
    public UITabBarItem() {}
    
    private static final Selector initWithTabBarSystemItem$tag$ = Selector.register("initWithTabBarSystemItem:tag:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithTabBarSystemItem(UITabBarItem __self__, Selector __cmd__, UITabBarSystemItem systemItem, int tag);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/initWithTabBarSystemItem:tag:">- (id)initWithTabBarSystemItem:(UITabBarSystemItem)systemItem tag:(NSInteger)tag</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITabBarItem(UITabBarSystemItem systemItem, int tag) {
        super((SkipInit) null);
        objc_initWithTabBarSystemItem(this, initWithTabBarSystemItem$tag$, systemItem, tag);
    }
    
    private static final Selector initWithTitle$image$tag$ = Selector.register("initWithTitle:image:tag:");
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithTitle(UITabBarItem __self__, Selector __cmd__, String title, UIImage image, int tag);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/initWithTitle:image:tag:">- (id)initWithTitle:(NSString *)title image:(UIImage *)image tag:(NSInteger)tag</a>
     * @since Available in iOS 2.0 and later.
     */
    public UITabBarItem(String title, UIImage image, int tag) {
        super((SkipInit) null);
        objc_initWithTitle(this, initWithTitle$image$tag$, title, image, tag);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarItem/badgeValue">@property(nonatomic, copy) NSString *badgeValue</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("badgeValue") public native String getBadgeValue();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instp/UITabBarItem/badgeValue">@property(nonatomic, copy) NSString *badgeValue</a>
     * @since Available in iOS 2.0 and later.
     */
    @Bind("setBadgeValue:") public native void setBadgeValue(String v);
    /*</properties>*/
    /*<methods>*/
    
    private static final Selector finishedSelectedImage = Selector.register("finishedSelectedImage");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getFinishedSelectedImage(UITabBarItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getFinishedSelectedImageSuper(ObjCSuper __super__, UITabBarItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/finishedSelectedImage">- (UIImage *)finishedSelectedImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getFinishedSelectedImage() {
        if (customClass) { return objc_getFinishedSelectedImageSuper(getSuper(), this, finishedSelectedImage); } else { return objc_getFinishedSelectedImage(this, finishedSelectedImage); }
    }
    
    private static final Selector finishedUnselectedImage = Selector.register("finishedUnselectedImage");
    @Bridge(symbol = "objc_msgSend") private native static UIImage objc_getFinishedUnselectedImage(UITabBarItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIImage objc_getFinishedUnselectedImageSuper(ObjCSuper __super__, UITabBarItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/finishedUnselectedImage">- (UIImage *)finishedUnselectedImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIImage getFinishedUnselectedImage() {
        if (customClass) { return objc_getFinishedUnselectedImageSuper(getSuper(), this, finishedUnselectedImage); } else { return objc_getFinishedUnselectedImage(this, finishedUnselectedImage); }
    }
    
    private static final Selector titlePositionAdjustment = Selector.register("titlePositionAdjustment");
    @Bridge(symbol = "objc_msgSend") private native static UIOffset objc_getTitlePositionAdjustment(UITabBarItem __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIOffset objc_getTitlePositionAdjustmentSuper(ObjCSuper __super__, UITabBarItem __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/titlePositionAdjustment">- (UIOffset)titlePositionAdjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    public UIOffset getTitlePositionAdjustment() {
        if (customClass) { return objc_getTitlePositionAdjustmentSuper(getSuper(), this, titlePositionAdjustment); } else { return objc_getTitlePositionAdjustment(this, titlePositionAdjustment); }
    }
    
    private static final Selector setFinishedSelectedImage$withFinishedUnselectedImage$ = Selector.register("setFinishedSelectedImage:withFinishedUnselectedImage:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setFinishedImages(UITabBarItem __self__, Selector __cmd__, UIImage selectedImage, UIImage unselectedImage);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setFinishedImagesSuper(ObjCSuper __super__, UITabBarItem __self__, Selector __cmd__, UIImage selectedImage, UIImage unselectedImage);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/setFinishedSelectedImage:withFinishedUnselectedImage:">- (void)setFinishedSelectedImage:(UIImage *)selectedImage withFinishedUnselectedImage:(UIImage *)unselectedImage</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setFinishedImages(UIImage selectedImage, UIImage unselectedImage) {
        if (customClass) { objc_setFinishedImagesSuper(getSuper(), this, setFinishedSelectedImage$withFinishedUnselectedImage$, selectedImage, unselectedImage); } else { objc_setFinishedImages(this, setFinishedSelectedImage$withFinishedUnselectedImage$, selectedImage, unselectedImage); }
    }
    
    private static final Selector setTitlePositionAdjustment$ = Selector.register("setTitlePositionAdjustment:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setTitlePositionAdjustment(UITabBarItem __self__, Selector __cmd__, UIOffset adjustment);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setTitlePositionAdjustmentSuper(ObjCSuper __super__, UITabBarItem __self__, Selector __cmd__, UIOffset adjustment);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UITabBarItem_Class/Reference/Reference.html#//apple_ref/occ/instm/UITabBarItem/setTitlePositionAdjustment:">- (void)setTitlePositionAdjustment:(UIOffset)adjustment</a>
     * @since Available in iOS 5.0 and later.
     */
    public void setTitlePositionAdjustment(UIOffset adjustment) {
        if (customClass) { objc_setTitlePositionAdjustmentSuper(getSuper(), this, setTitlePositionAdjustment$, adjustment); } else { objc_setTitlePositionAdjustment(this, setTitlePositionAdjustment$, adjustment); }
    }
    /*</methods>*/

}
