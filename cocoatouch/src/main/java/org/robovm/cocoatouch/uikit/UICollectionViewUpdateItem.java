/*
 * Copyright (C) 2012 Trillian AB
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
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/**
 *
 * <div class="javadoc">
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewUpdateItem_class/Reference/Reference.html">UICollectionViewUpdateItem Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UICollectionViewUpdateItem /*</name>*/ 
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
    
    private static final Selector indexPathAfterUpdate = Selector.register("indexPathAfterUpdate");
    @Bridge private native static NSIndexPath objc_getIndexPathAfterUpdate(UICollectionViewUpdateItem __self__, Selector __cmd__);
    @Bridge private native static NSIndexPath objc_getIndexPathAfterUpdateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewUpdateItem_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewUpdateItem/indexPathAfterUpdate">@property (nonatomic, readonly) NSIndexPath *indexPathAfterUpdate</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSIndexPath getIndexPathAfterUpdate() {
        if (customClass) { return objc_getIndexPathAfterUpdateSuper(getSuper(), indexPathAfterUpdate); } else { return objc_getIndexPathAfterUpdate(this, indexPathAfterUpdate); }
    }
    
    private static final Selector indexPathBeforeUpdate = Selector.register("indexPathBeforeUpdate");
    @Bridge private native static NSIndexPath objc_getIndexPathBeforeUpdate(UICollectionViewUpdateItem __self__, Selector __cmd__);
    @Bridge private native static NSIndexPath objc_getIndexPathBeforeUpdateSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewUpdateItem_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewUpdateItem/indexPathBeforeUpdate">@property (nonatomic, readonly) NSIndexPath *indexPathBeforeUpdate</a>
     * @since Available in iOS 6.0 and later.
     */
    public NSIndexPath getIndexPathBeforeUpdate() {
        if (customClass) { return objc_getIndexPathBeforeUpdateSuper(getSuper(), indexPathBeforeUpdate); } else { return objc_getIndexPathBeforeUpdate(this, indexPathBeforeUpdate); }
    }
    
    private static final Selector updateAction = Selector.register("updateAction");
    @Bridge private native static UICollectionUpdateAction objc_getUpdateAction(UICollectionViewUpdateItem __self__, Selector __cmd__);
    @Bridge private native static UICollectionUpdateAction objc_getUpdateActionSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewUpdateItem_class/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewUpdateItem/updateAction">@property (nonatomic, readonly) UICollectionUpdateAction updateAction</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionUpdateAction getUpdateAction() {
        if (customClass) { return objc_getUpdateActionSuper(getSuper(), updateAction); } else { return objc_getUpdateAction(this, updateAction); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("indexPathAfterUpdate") public static NSIndexPath getIndexPathAfterUpdate(UICollectionViewUpdateItem __self__, Selector __cmd__) { return __self__.getIndexPathAfterUpdate(); }
        @Callback @BindSelector("indexPathBeforeUpdate") public static NSIndexPath getIndexPathBeforeUpdate(UICollectionViewUpdateItem __self__, Selector __cmd__) { return __self__.getIndexPathBeforeUpdate(); }
        @Callback @BindSelector("updateAction") public static UICollectionUpdateAction getUpdateAction(UICollectionViewUpdateItem __self__, Selector __cmd__) { return __self__.getUpdateAction(); }
    }
    /*</callbacks>*/

}
