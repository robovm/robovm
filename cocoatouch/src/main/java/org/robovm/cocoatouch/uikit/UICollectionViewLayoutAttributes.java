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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html">UICollectionViewLayoutAttributes Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UICollectionViewLayoutAttributes /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UICollectionViewLayoutAttributes /*</name>*/.class);
    }

    /*<constructors>*/
    public UICollectionViewLayoutAttributes() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/alpha">@property (nonatomic) CGFloat alpha;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("alpha") public native @Type("CGFloat") float getAlpha();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/alpha">@property (nonatomic) CGFloat alpha;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setAlpha:") public native void setAlpha(@Type("CGFloat") float v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/center">@property (nonatomic) CGPoint center;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("center") public native @Type("CGPoint") CGPoint getCenter();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/center">@property (nonatomic) CGPoint center;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setCenter:") public native void setCenter(@Type("CGPoint") CGPoint v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/frame">@property (nonatomic) CGRect frame;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("frame") public native @Type("CGRect") CGRect getFrame();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/frame">@property (nonatomic) CGRect frame;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setFrame:") public native void setFrame(@Type("CGRect") CGRect v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/hidden">@property (nonatomic, getter=isHidden) BOOL hidden;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("isHidden") public native @Type("BOOL") boolean isHidden();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/hidden">@property (nonatomic, getter=isHidden) BOOL hidden;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setHidden:") public native void setHidden(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/indexPath">@property (nonatomic, retain) NSIndexPath *indexPath;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("indexPath") public native @Type("NSIndexPath *") NSIndexPath getIndexPath();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/indexPath">@property (nonatomic, retain) NSIndexPath *indexPath;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setIndexPath:") public native void setIndexPath(@Type("NSIndexPath *") NSIndexPath v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/representedElementCategory">@property (nonatomic, readonly) UICollectionElementCategory representedElementCategory;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("representedElementCategory") public native @Type("UICollectionElementCategory") UICollectionElementCategory getRepresentedElementCategory();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/representedElementKind">@property (nonatomic, readonly) NSString *representedElementKind;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("representedElementKind") public native @Type("NSString *") String getRepresentedElementKind();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/size">@property (nonatomic) CGSize size;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("size") public native @Type("CGSize") CGSize getSize();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/size">@property (nonatomic) CGSize size;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setSize:") public native void setSize(@Type("CGSize") CGSize v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/transform3D">@property (nonatomic) CATransform3D transform3D;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("transform3D") public native @Type("CATransform3D") CATransform3D getTransform3D();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/transform3D">@property (nonatomic) CATransform3D transform3D;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setTransform3D:") public native void setTransform3D(@Type("CATransform3D") CATransform3D v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/zIndex">@property (nonatomic) NSInteger zIndex;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("zIndex") public native @Type("NSInteger") int getZIndex();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewLayoutAttributes/zIndex">@property (nonatomic) NSInteger zIndex;</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setZIndex:") public native void setZIndex(@Type("NSInteger") int v);
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/clm/UICollectionViewLayoutAttributes/layoutAttributesForCellWithIndexPath:">+ (instancetype)layoutAttributesForCellWithIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("layoutAttributesForCellWithIndexPath:") public native static @Type("instancetype") UICollectionViewLayoutAttributes createForCell(@Type("NSIndexPath *") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/clm/UICollectionViewLayoutAttributes/layoutAttributesForDecorationViewOfKind:withIndexPath:">+ (instancetype)layoutAttributesForDecorationViewOfKind:(NSString *)decorationViewKind withIndexPath:(NSIndexPath*)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("layoutAttributesForDecorationViewOfKind:withIndexPath:") public native static @Type("instancetype") UICollectionViewLayoutAttributes createForDecorationView(@Type("NSString *") String decorationViewKind, @Type("NSIndexPath*") NSIndexPath indexPath);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewLayoutAttributes_class/Reference/Reference.html#//apple_ref/occ/clm/UICollectionViewLayoutAttributes/layoutAttributesForSupplementaryViewOfKind:withIndexPath:">+ (instancetype)layoutAttributesForSupplementaryViewOfKind:(NSString *)elementKind withIndexPath:(NSIndexPath *)indexPath</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("layoutAttributesForSupplementaryViewOfKind:withIndexPath:") public native static @Type("instancetype") UICollectionViewLayoutAttributes createForSupplementaryView(@Type("NSString *") String elementKind, @Type("NSIndexPath *") NSIndexPath indexPath);
    /*</methods>*/

}
