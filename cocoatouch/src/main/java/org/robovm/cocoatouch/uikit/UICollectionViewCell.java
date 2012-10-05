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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html">UICollectionViewCell Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UICollectionViewCell /*</name>*/ 
    extends /*<extends>*/ UICollectionReusableView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UICollectionViewCell /*</name>*/.class);
    }

    /*<constructors>*/
    public UICollectionViewCell() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/backgroundView">@property (nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("backgroundView") public native @Type("UIView *") UIView getBackgroundView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/backgroundView">@property (nonatomic, retain) UIView *backgroundView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setBackgroundView:") public native void setBackgroundView(@Type("UIView *") UIView v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/contentView">@property (nonatomic, readonly) UIView *contentView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("contentView") public native @Type("UIView *") UIView getContentView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/highlighted">@property (nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("isHighlighted") public native @Type("BOOL") boolean isHighlighted();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/highlighted">@property (nonatomic, getter=isHighlighted) BOOL highlighted</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setHighlighted:") public native void setHighlighted(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/selected">@property (nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("isSelected") public native @Type("BOOL") boolean isSelected();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/selected">@property (nonatomic, getter=isSelected) BOOL selected</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setSelected:") public native void setSelected(@Type("BOOL") boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/selectedBackgroundView">@property (nonatomic, retain) UIView *selectedBackgroundView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("selectedBackgroundView") public native @Type("UIView *") UIView getSelectedBackgroundView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewCell_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewCell/selectedBackgroundView">@property (nonatomic, retain) UIView *selectedBackgroundView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setSelectedBackgroundView:") public native void setSelectedBackgroundView(@Type("UIView *") UIView v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
