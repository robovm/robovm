/*
 * Copyright (C) 2013-2015 RoboVM AB
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
package org.robovm.apple.uikit;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coredata.*;
import org.robovm.apple.coreimage.*;
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UICollectionViewFlowLayout/*</name>*/ 
    extends /*<extends>*/UICollectionViewLayout/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UICollectionViewFlowLayoutPtr extends Ptr<UICollectionViewFlowLayout, UICollectionViewFlowLayoutPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UICollectionViewFlowLayout.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UICollectionViewFlowLayout() {}
    protected UICollectionViewFlowLayout(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "minimumLineSpacing")
    public native @MachineSizedFloat double getMinimumLineSpacing();
    @Property(selector = "setMinimumLineSpacing:")
    public native void setMinimumLineSpacing(@MachineSizedFloat double v);
    @Property(selector = "minimumInteritemSpacing")
    public native @MachineSizedFloat double getMinimumInteritemSpacing();
    @Property(selector = "setMinimumInteritemSpacing:")
    public native void setMinimumInteritemSpacing(@MachineSizedFloat double v);
    @Property(selector = "itemSize")
    public native @ByVal CGSize getItemSize();
    @Property(selector = "setItemSize:")
    public native void setItemSize(@ByVal CGSize v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "estimatedItemSize")
    public native @ByVal CGSize getEstimatedItemSize();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setEstimatedItemSize:")
    public native void setEstimatedItemSize(@ByVal CGSize v);
    @Property(selector = "scrollDirection")
    public native UICollectionViewScrollDirection getScrollDirection();
    @Property(selector = "setScrollDirection:")
    public native void setScrollDirection(UICollectionViewScrollDirection v);
    @Property(selector = "headerReferenceSize")
    public native @ByVal CGSize getHeaderReferenceSize();
    @Property(selector = "setHeaderReferenceSize:")
    public native void setHeaderReferenceSize(@ByVal CGSize v);
    @Property(selector = "footerReferenceSize")
    public native @ByVal CGSize getFooterReferenceSize();
    @Property(selector = "setFooterReferenceSize:")
    public native void setFooterReferenceSize(@ByVal CGSize v);
    @Property(selector = "sectionInset")
    public native @ByVal UIEdgeInsets getSectionInset();
    @Property(selector = "setSectionInset:")
    public native void setSectionInset(@ByVal UIEdgeInsets v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
