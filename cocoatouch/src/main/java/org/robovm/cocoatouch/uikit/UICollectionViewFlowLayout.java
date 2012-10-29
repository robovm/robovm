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
 * <div class="javadoc">
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html">UICollectionViewFlowLayout Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UICollectionViewFlowLayout /*</name>*/ 
    extends /*<extends>*/ UICollectionViewLayout /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UICollectionViewFlowLayout /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UICollectionViewFlowLayout /*</name>*/.class);

    /*<constructors>*/
    protected UICollectionViewFlowLayout(SkipInit skipInit) { super(skipInit); }
    public UICollectionViewFlowLayout() {}
    
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector footerReferenceSize = Selector.register("footerReferenceSize");
    @Bridge(symbol = "objc_msgSend") private native static CGSize objc_getFooterReferenceSize(UICollectionViewFlowLayout __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGSize objc_getFooterReferenceSizeSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/footerReferenceSize">@property (nonatomic) CGSize footerReferenceSize;</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGSize getFooterReferenceSize() {
        if (customClass) { return objc_getFooterReferenceSizeSuper(getSuper(), this, footerReferenceSize); } else { return objc_getFooterReferenceSize(this, footerReferenceSize); }
    }
    
    private static final Selector setFooterReferenceSize$ = Selector.register("setFooterReferenceSize:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setFooterReferenceSize(UICollectionViewFlowLayout __self__, Selector __cmd__, CGSize footerReferenceSize);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setFooterReferenceSizeSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__, CGSize footerReferenceSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/footerReferenceSize">@property (nonatomic) CGSize footerReferenceSize;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setFooterReferenceSize(CGSize footerReferenceSize) {
        if (customClass) { objc_setFooterReferenceSizeSuper(getSuper(), this, setFooterReferenceSize$, footerReferenceSize); } else { objc_setFooterReferenceSize(this, setFooterReferenceSize$, footerReferenceSize); }
    }
    
    private static final Selector headerReferenceSize = Selector.register("headerReferenceSize");
    @Bridge(symbol = "objc_msgSend") private native static CGSize objc_getHeaderReferenceSize(UICollectionViewFlowLayout __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGSize objc_getHeaderReferenceSizeSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/headerReferenceSize">@property (nonatomic) CGSize headerReferenceSize;</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGSize getHeaderReferenceSize() {
        if (customClass) { return objc_getHeaderReferenceSizeSuper(getSuper(), this, headerReferenceSize); } else { return objc_getHeaderReferenceSize(this, headerReferenceSize); }
    }
    
    private static final Selector setHeaderReferenceSize$ = Selector.register("setHeaderReferenceSize:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setHeaderReferenceSize(UICollectionViewFlowLayout __self__, Selector __cmd__, CGSize headerReferenceSize);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setHeaderReferenceSizeSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__, CGSize headerReferenceSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/headerReferenceSize">@property (nonatomic) CGSize headerReferenceSize;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setHeaderReferenceSize(CGSize headerReferenceSize) {
        if (customClass) { objc_setHeaderReferenceSizeSuper(getSuper(), this, setHeaderReferenceSize$, headerReferenceSize); } else { objc_setHeaderReferenceSize(this, setHeaderReferenceSize$, headerReferenceSize); }
    }
    
    private static final Selector itemSize = Selector.register("itemSize");
    @Bridge(symbol = "objc_msgSend") private native static CGSize objc_getItemSize(UICollectionViewFlowLayout __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static CGSize objc_getItemSizeSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/itemSize">@property (nonatomic) CGSize itemSize;</a>
     * @since Available in iOS 6.0 and later.
     */
    public CGSize getItemSize() {
        if (customClass) { return objc_getItemSizeSuper(getSuper(), this, itemSize); } else { return objc_getItemSize(this, itemSize); }
    }
    
    private static final Selector setItemSize$ = Selector.register("setItemSize:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setItemSize(UICollectionViewFlowLayout __self__, Selector __cmd__, CGSize itemSize);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setItemSizeSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__, CGSize itemSize);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/itemSize">@property (nonatomic) CGSize itemSize;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setItemSize(CGSize itemSize) {
        if (customClass) { objc_setItemSizeSuper(getSuper(), this, setItemSize$, itemSize); } else { objc_setItemSize(this, setItemSize$, itemSize); }
    }
    
    private static final Selector minimumInteritemSpacing = Selector.register("minimumInteritemSpacing");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getMinimumInteritemSpacing(UICollectionViewFlowLayout __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getMinimumInteritemSpacingSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/minimumInteritemSpacing">@property (nonatomic) CGFloat minimumInteritemSpacing;</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getMinimumInteritemSpacing() {
        if (customClass) { return objc_getMinimumInteritemSpacingSuper(getSuper(), this, minimumInteritemSpacing); } else { return objc_getMinimumInteritemSpacing(this, minimumInteritemSpacing); }
    }
    
    private static final Selector setMinimumInteritemSpacing$ = Selector.register("setMinimumInteritemSpacing:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMinimumInteritemSpacing(UICollectionViewFlowLayout __self__, Selector __cmd__, float minimumInteritemSpacing);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMinimumInteritemSpacingSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__, float minimumInteritemSpacing);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/minimumInteritemSpacing">@property (nonatomic) CGFloat minimumInteritemSpacing;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setMinimumInteritemSpacing(float minimumInteritemSpacing) {
        if (customClass) { objc_setMinimumInteritemSpacingSuper(getSuper(), this, setMinimumInteritemSpacing$, minimumInteritemSpacing); } else { objc_setMinimumInteritemSpacing(this, setMinimumInteritemSpacing$, minimumInteritemSpacing); }
    }
    
    private static final Selector minimumLineSpacing = Selector.register("minimumLineSpacing");
    @Bridge(symbol = "objc_msgSend") private native static float objc_getMinimumLineSpacing(UICollectionViewFlowLayout __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static float objc_getMinimumLineSpacingSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/minimumLineSpacing">@property (nonatomic) CGFloat minimumLineSpacing;</a>
     * @since Available in iOS 6.0 and later.
     */
    public float getMinimumLineSpacing() {
        if (customClass) { return objc_getMinimumLineSpacingSuper(getSuper(), this, minimumLineSpacing); } else { return objc_getMinimumLineSpacing(this, minimumLineSpacing); }
    }
    
    private static final Selector setMinimumLineSpacing$ = Selector.register("setMinimumLineSpacing:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setMinimumLineSpacing(UICollectionViewFlowLayout __self__, Selector __cmd__, float minimumLineSpacing);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setMinimumLineSpacingSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__, float minimumLineSpacing);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/minimumLineSpacing">@property (nonatomic) CGFloat minimumLineSpacing;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setMinimumLineSpacing(float minimumLineSpacing) {
        if (customClass) { objc_setMinimumLineSpacingSuper(getSuper(), this, setMinimumLineSpacing$, minimumLineSpacing); } else { objc_setMinimumLineSpacing(this, setMinimumLineSpacing$, minimumLineSpacing); }
    }
    
    private static final Selector scrollDirection = Selector.register("scrollDirection");
    @Bridge(symbol = "objc_msgSend") private native static UICollectionViewScrollDirection objc_getScrollDirection(UICollectionViewFlowLayout __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UICollectionViewScrollDirection objc_getScrollDirectionSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/scrollDirection">@property (nonatomic) UICollectionViewScrollDirection scrollDirection;</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewScrollDirection getScrollDirection() {
        if (customClass) { return objc_getScrollDirectionSuper(getSuper(), this, scrollDirection); } else { return objc_getScrollDirection(this, scrollDirection); }
    }
    
    private static final Selector setScrollDirection$ = Selector.register("setScrollDirection:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setScrollDirection(UICollectionViewFlowLayout __self__, Selector __cmd__, UICollectionViewScrollDirection scrollDirection);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setScrollDirectionSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__, UICollectionViewScrollDirection scrollDirection);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/scrollDirection">@property (nonatomic) UICollectionViewScrollDirection scrollDirection;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setScrollDirection(UICollectionViewScrollDirection scrollDirection) {
        if (customClass) { objc_setScrollDirectionSuper(getSuper(), this, setScrollDirection$, scrollDirection); } else { objc_setScrollDirection(this, setScrollDirection$, scrollDirection); }
    }
    
    private static final Selector sectionInset = Selector.register("sectionInset");
    @Bridge(symbol = "objc_msgSend") private native static UIEdgeInsets objc_getSectionInset(UICollectionViewFlowLayout __self__, Selector __cmd__);
    @Bridge(symbol = "objc_msgSendSuper") private native static UIEdgeInsets objc_getSectionInsetSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/sectionInset">@property (nonatomic) UIEdgeInsets sectionInset;</a>
     * @since Available in iOS 6.0 and later.
     */
    public UIEdgeInsets getSectionInset() {
        if (customClass) { return objc_getSectionInsetSuper(getSuper(), this, sectionInset); } else { return objc_getSectionInset(this, sectionInset); }
    }
    
    private static final Selector setSectionInset$ = Selector.register("setSectionInset:");
    @Bridge(symbol = "objc_msgSend") private native static void objc_setSectionInset(UICollectionViewFlowLayout __self__, Selector __cmd__, UIEdgeInsets sectionInset);
    @Bridge(symbol = "objc_msgSendSuper") private native static void objc_setSectionInsetSuper(ObjCSuper __super__, UICollectionViewFlowLayout __self__, Selector __cmd__, UIEdgeInsets sectionInset);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/sectionInset">@property (nonatomic) UIEdgeInsets sectionInset;</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setSectionInset(UIEdgeInsets sectionInset) {
        if (customClass) { objc_setSectionInsetSuper(getSuper(), this, setSectionInset$, sectionInset); } else { objc_setSectionInset(this, setSectionInset$, sectionInset); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("footerReferenceSize") public static CGSize getFooterReferenceSize(UICollectionViewFlowLayout __self__, Selector __cmd__) { return __self__.getFooterReferenceSize(); }
        @Callback @BindSelector("setFooterReferenceSize:") public static void setFooterReferenceSize(UICollectionViewFlowLayout __self__, Selector __cmd__, CGSize footerReferenceSize) { __self__.setFooterReferenceSize(footerReferenceSize); }
        @Callback @BindSelector("headerReferenceSize") public static CGSize getHeaderReferenceSize(UICollectionViewFlowLayout __self__, Selector __cmd__) { return __self__.getHeaderReferenceSize(); }
        @Callback @BindSelector("setHeaderReferenceSize:") public static void setHeaderReferenceSize(UICollectionViewFlowLayout __self__, Selector __cmd__, CGSize headerReferenceSize) { __self__.setHeaderReferenceSize(headerReferenceSize); }
        @Callback @BindSelector("itemSize") public static CGSize getItemSize(UICollectionViewFlowLayout __self__, Selector __cmd__) { return __self__.getItemSize(); }
        @Callback @BindSelector("setItemSize:") public static void setItemSize(UICollectionViewFlowLayout __self__, Selector __cmd__, CGSize itemSize) { __self__.setItemSize(itemSize); }
        @Callback @BindSelector("minimumInteritemSpacing") public static float getMinimumInteritemSpacing(UICollectionViewFlowLayout __self__, Selector __cmd__) { return __self__.getMinimumInteritemSpacing(); }
        @Callback @BindSelector("setMinimumInteritemSpacing:") public static void setMinimumInteritemSpacing(UICollectionViewFlowLayout __self__, Selector __cmd__, float minimumInteritemSpacing) { __self__.setMinimumInteritemSpacing(minimumInteritemSpacing); }
        @Callback @BindSelector("minimumLineSpacing") public static float getMinimumLineSpacing(UICollectionViewFlowLayout __self__, Selector __cmd__) { return __self__.getMinimumLineSpacing(); }
        @Callback @BindSelector("setMinimumLineSpacing:") public static void setMinimumLineSpacing(UICollectionViewFlowLayout __self__, Selector __cmd__, float minimumLineSpacing) { __self__.setMinimumLineSpacing(minimumLineSpacing); }
        @Callback @BindSelector("scrollDirection") public static UICollectionViewScrollDirection getScrollDirection(UICollectionViewFlowLayout __self__, Selector __cmd__) { return __self__.getScrollDirection(); }
        @Callback @BindSelector("setScrollDirection:") public static void setScrollDirection(UICollectionViewFlowLayout __self__, Selector __cmd__, UICollectionViewScrollDirection scrollDirection) { __self__.setScrollDirection(scrollDirection); }
        @Callback @BindSelector("sectionInset") public static UIEdgeInsets getSectionInset(UICollectionViewFlowLayout __self__, Selector __cmd__) { return __self__.getSectionInset(); }
        @Callback @BindSelector("setSectionInset:") public static void setSectionInset(UICollectionViewFlowLayout __self__, Selector __cmd__, UIEdgeInsets sectionInset) { __self__.setSectionInset(sectionInset); }
    }
    /*</callbacks>*/

}
