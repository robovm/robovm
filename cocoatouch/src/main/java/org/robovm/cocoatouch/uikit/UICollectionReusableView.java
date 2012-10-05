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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionReusableView_class/Reference/Reference.html">UICollectionReusableView Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UICollectionReusableView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UICollectionReusableView /*</name>*/.class);
    }

    /*<constructors>*/
    public UICollectionReusableView() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionReusableView_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionReusableView/reuseIdentifier">@property (nonatomic, readonly, copy) NSString *reuseIdentifier</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("reuseIdentifier") public native @Type("NSString *") String getReuseIdentifier();
    /*</properties>*/
    /*<methods>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionReusableView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionReusableView/applyLayoutAttributes:">- (void)applyLayoutAttributes:(UICollectionViewLayoutAttributes *)layoutAttributes</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("applyLayoutAttributes:") public native @Type("void") void applyLayoutAttributes(@Type("UICollectionViewLayoutAttributes *") UICollectionViewLayoutAttributes layoutAttributes);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionReusableView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionReusableView/didTransitionFromLayout:toLayout:">- (void)didTransitionFromLayout:(UICollectionViewLayout *)oldLayout toLayout:(UICollectionViewLayout *)newLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("didTransitionFromLayout:toLayout:") public native @Type("void") void didTransition(@Type("UICollectionViewLayout *") UICollectionViewLayout oldLayout, @Type("UICollectionViewLayout *") UICollectionViewLayout newLayout);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionReusableView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionReusableView/prepareForReuse">- (void)prepareForReuse</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("prepareForReuse") public native @Type("void") void prepareForReuse();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionReusableView_class/Reference/Reference.html#//apple_ref/occ/instm/UICollectionReusableView/willTransitionFromLayout:toLayout:">- (void)willTransitionFromLayout:(UICollectionViewLayout *)oldLayout toLayout:(UICollectionViewLayout *)newLayout</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("willTransitionFromLayout:toLayout:") public native @Type("void") void willTransition(@Type("UICollectionViewLayout *") UICollectionViewLayout oldLayout, @Type("UICollectionViewLayout *") UICollectionViewLayout newLayout);
    /*</methods>*/

}
