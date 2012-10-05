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
 *   @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewController_clas/Reference/Reference.html">UICollectionViewController Class Reference</a>
 *   @since Available in iOS 6.0 and later.
 * </div>
 */
/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UICollectionViewController /*</name>*/ 
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
    @Bridge(symbol = "objc_msgSend") private native static NSObject objc_initWithCollectionViewLayout(UICollectionViewController __self__, Selector __cmd__, UICollectionViewLayout layout);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewController_clas/Reference/Reference.html#//apple_ref/occ/instm/UICollectionViewController/initWithCollectionViewLayout:">- (id)initWithCollectionViewLayout:(UICollectionViewLayout *)layout</a>
     * @since Available in iOS 6.0 and later.
     */
    public UICollectionViewController(UICollectionViewLayout layout) {
        super((SkipInit) null);
        objc_initWithCollectionViewLayout(this, initWithCollectionViewLayout$, layout);
    }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewController_clas/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewController/clearsSelectionOnViewWillAppear">@property (nonatomic) BOOL clearsSelectionOnViewWillAppear</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("clearsSelectionOnViewWillAppear") public native boolean isClearsSelectionOnViewWillAppear();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewController_clas/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewController/clearsSelectionOnViewWillAppear">@property (nonatomic) BOOL clearsSelectionOnViewWillAppear</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setClearsSelectionOnViewWillAppear:") public native void setClearsSelectionOnViewWillAppear(boolean v);
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewController_clas/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewController/collectionView">@property (nonatomic, retain) UICollectionView *collectionView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("collectionView") public native UICollectionView getCollectionView();
    /**
     * @see <a href="http://developer.apple.com/library/ios/documentation/uikit/reference/UIKit_Framework/../UICollectionViewController_clas/Reference/Reference.html#//apple_ref/occ/instp/UICollectionViewController/collectionView">@property (nonatomic, retain) UICollectionView *collectionView</a>
     * @since Available in iOS 6.0 and later.
     */
    @Bind("setCollectionView:") public native void setCollectionView(UICollectionView v);
    /*</properties>*/
    /*<methods>*/
    
    /*</methods>*/

}
