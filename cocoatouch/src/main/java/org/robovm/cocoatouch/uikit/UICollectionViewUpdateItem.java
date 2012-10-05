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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewUpdateItem_class/Reference/Reference.html">UICollectionViewUpdateItem Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UICollectionViewUpdateItem /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UICollectionViewUpdateItem /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UICollectionViewUpdateItem /*</name>*/.class);

    /*<constructors>*/
    protected UICollectionViewUpdateItem(SkipInit skipInit) { super(skipInit); }
    public UICollectionViewUpdateItem() {}
    
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewUpdateItem_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewUpdateItem/elementKind">@property (nonatomic, readonly) NSString *elementKind</a>
     */
    @Bind("elementKind") public native String getElementKind();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewUpdateItem_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewUpdateItem/indexPathAfterUpdate">@property (nonatomic, readonly) NSIndexPath *indexPathAfterUpdate</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("indexPathAfterUpdate") public native NSIndexPath getIndexPathAfterUpdate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewUpdateItem_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewUpdateItem/indexPathBeforeUpdate">@property (nonatomic, readonly) NSIndexPath *indexPathBeforeUpdate</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("indexPathBeforeUpdate") public native NSIndexPath getIndexPathBeforeUpdate();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewUpdateItem_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewUpdateItem/updateAction">@property (nonatomic, readonly) UICollectionUpdateAction updateAction</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("updateAction") public native UICollectionUpdateAction getUpdateAction();
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
