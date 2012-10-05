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

    /*<constructors>*/
    public UICollectionViewFlowLayout() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/footerReferenceSize">@property (nonatomic) CGSize footerReferenceSize;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("footerReferenceSize") public native @Type("CGSize") CGSize getFooterReferenceSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/footerReferenceSize">@property (nonatomic) CGSize footerReferenceSize;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setFooterReferenceSize:") public native void setFooterReferenceSize(@Type("CGSize") CGSize v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/headerReferenceSize">@property (nonatomic) CGSize headerReferenceSize;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("headerReferenceSize") public native @Type("CGSize") CGSize getHeaderReferenceSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/headerReferenceSize">@property (nonatomic) CGSize headerReferenceSize;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setHeaderReferenceSize:") public native void setHeaderReferenceSize(@Type("CGSize") CGSize v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/itemSize">@property (nonatomic) CGSize itemSize;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("itemSize") public native @Type("CGSize") CGSize getItemSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/itemSize">@property (nonatomic) CGSize itemSize;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setItemSize:") public native void setItemSize(@Type("CGSize") CGSize v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/minimumInteritemSpacing">@property (nonatomic) CGFloat minimumInteritemSpacing;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("minimumInteritemSpacing") public native @Type("CGFloat") float getMinimumInteritemSpacing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/minimumInteritemSpacing">@property (nonatomic) CGFloat minimumInteritemSpacing;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setMinimumInteritemSpacing:") public native void setMinimumInteritemSpacing(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/minimumLineSpacing">@property (nonatomic) CGFloat minimumLineSpacing;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("minimumLineSpacing") public native @Type("CGFloat") float getMinimumLineSpacing();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/minimumLineSpacing">@property (nonatomic) CGFloat minimumLineSpacing;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setMinimumLineSpacing:") public native void setMinimumLineSpacing(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/scrollDirection">@property (nonatomic) UICollectionViewScrollDirection scrollDirection;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("scrollDirection") public native @Type("UICollectionViewScrollDirection") UICollectionViewScrollDirection getScrollDirection();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/scrollDirection">@property (nonatomic) UICollectionViewScrollDirection scrollDirection;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setScrollDirection:") public native void setScrollDirection(@Type("UICollectionViewScrollDirection") UICollectionViewScrollDirection v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/sectionInset">@property (nonatomic) UIEdgeInsets sectionInset;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("sectionInset") public native @Type("UIEdgeInsets") UIEdgeInsets getSectionInset();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewFlowLayout_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewFlowLayout/sectionInset">@property (nonatomic) UIEdgeInsets sectionInset;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setSectionInset:") public native void setSectionInset(@Type("UIEdgeInsets") UIEdgeInsets v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
