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
 *   @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewController_clas/Reference/Reference.html">UICollectionViewController Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
@NativeClass public class /*<name>*/ UICollectionViewController /*</name>*/ 
    extends /*<extends>*/ UIViewController /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UICollectionViewController /*</name>*/.class);
    }

    private static final ObjCClass objCClass = ObjCClass.getByType(/*<name>*/ UICollectionViewController /*</name>*/.class);

    /*<constructors>*/
    protected UICollectionViewController(SkipInit skipInit) { super(skipInit); }
    public UICollectionViewController() {}
    
    private static final Selector initWithCollectionViewLayout$ = Selector.register("initWithCollectionViewLayout:");
    @Bridge private native static @Pointer long objc_initWithCollectionViewLayout(UICollectionViewController __self__, Selector __cmd__, UICollectionViewLayout layout);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewController_clas/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewController/initWithCollectionViewLayout:">- (id)initWithCollectionViewLayout:(UICollectionViewLayout *)layout</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewController(UICollectionViewLayout layout) {
        super((SkipInit) null);
        initObject(objc_initWithCollectionViewLayout(this, initWithCollectionViewLayout$, layout));
    }
    /*</constructors>*/
    /*<properties>*/
    
    private static final Selector clearsSelectionOnViewWillAppear = Selector.register("clearsSelectionOnViewWillAppear");
    @Bridge private native static boolean objc_isClearsSelectionOnViewWillAppear(UICollectionViewController __self__, Selector __cmd__);
    @Bridge private native static boolean objc_isClearsSelectionOnViewWillAppearSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewController_clas/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewController/clearsSelectionOnViewWillAppear">@property (nonatomic) BOOL clearsSelectionOnViewWillAppear</a>
     * @since Available in iOS 6.0 and later.
     */
    public boolean isClearsSelectionOnViewWillAppear() {
        if (customClass) { return objc_isClearsSelectionOnViewWillAppearSuper(getSuper(), clearsSelectionOnViewWillAppear); } else { return objc_isClearsSelectionOnViewWillAppear(this, clearsSelectionOnViewWillAppear); }
    }
    
    private static final Selector setClearsSelectionOnViewWillAppear$ = Selector.register("setClearsSelectionOnViewWillAppear:");
    @Bridge private native static void objc_setClearsSelectionOnViewWillAppear(UICollectionViewController __self__, Selector __cmd__, boolean clearsSelectionOnViewWillAppear);
    @Bridge private native static void objc_setClearsSelectionOnViewWillAppearSuper(ObjCSuper __super__, Selector __cmd__, boolean clearsSelectionOnViewWillAppear);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewController_clas/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewController/clearsSelectionOnViewWillAppear">@property (nonatomic) BOOL clearsSelectionOnViewWillAppear</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setClearsSelectionOnViewWillAppear(boolean clearsSelectionOnViewWillAppear) {
        if (customClass) { objc_setClearsSelectionOnViewWillAppearSuper(getSuper(), setClearsSelectionOnViewWillAppear$, clearsSelectionOnViewWillAppear); } else { objc_setClearsSelectionOnViewWillAppear(this, setClearsSelectionOnViewWillAppear$, clearsSelectionOnViewWillAppear); }
    }
    
    private static final Selector collectionView = Selector.register("collectionView");
    @Bridge private native static UICollectionView objc_getCollectionView(UICollectionViewController __self__, Selector __cmd__);
    @Bridge private native static UICollectionView objc_getCollectionViewSuper(ObjCSuper __super__, Selector __cmd__);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewController_clas/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewController/collectionView">@property (nonatomic, retain) UICollectionView *collectionView</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionView getCollectionView() {
        if (customClass) { return objc_getCollectionViewSuper(getSuper(), collectionView); } else { return objc_getCollectionView(this, collectionView); }
    }
    
    private static final Selector setCollectionView$ = Selector.register("setCollectionView:");
    @Bridge private native static void objc_setCollectionView(UICollectionViewController __self__, Selector __cmd__, UICollectionView collectionView);
    @Bridge private native static void objc_setCollectionViewSuper(ObjCSuper __super__, Selector __cmd__, UICollectionView collectionView);
    /**
     * @see <a href="https://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewController_clas/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewController/collectionView">@property (nonatomic, retain) UICollectionView *collectionView</a>
     * @since Available in iOS 6.0 and later.
     */
    public void setCollectionView(UICollectionView collectionView) {
        if (customClass) { objc_setCollectionViewSuper(getSuper(), setCollectionView$, collectionView); } else { objc_setCollectionView(this, setCollectionView$, collectionView); }
    }
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/
    /*<callbacks>*/
    static class Callbacks {
        @Callback @BindSelector("clearsSelectionOnViewWillAppear") public static boolean isClearsSelectionOnViewWillAppear(UICollectionViewController __self__, Selector __cmd__) { return __self__.isClearsSelectionOnViewWillAppear(); }
        @Callback @BindSelector("setClearsSelectionOnViewWillAppear:") public static void setClearsSelectionOnViewWillAppear(UICollectionViewController __self__, Selector __cmd__, boolean clearsSelectionOnViewWillAppear) { __self__.setClearsSelectionOnViewWillAppear(clearsSelectionOnViewWillAppear); }
        @Callback @BindSelector("collectionView") public static UICollectionView getCollectionView(UICollectionViewController __self__, Selector __cmd__) { return __self__.getCollectionView(); }
        @Callback @BindSelector("setCollectionView:") public static void setCollectionView(UICollectionViewController __self__, Selector __cmd__, UICollectionView collectionView) { __self__.setCollectionView(collectionView); }
    }
    /*</callbacks>*/

}
