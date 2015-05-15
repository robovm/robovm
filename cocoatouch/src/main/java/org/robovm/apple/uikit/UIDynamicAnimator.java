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
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIDynamicAnimator/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIDynamicAnimatorPtr extends Ptr<UIDynamicAnimator, UIDynamicAnimatorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIDynamicAnimator.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIDynamicAnimator() {}
    protected UIDynamicAnimator(SkipInit skipInit) { super(skipInit); }
    public UIDynamicAnimator(UIView view) { super((SkipInit) null); initObject(init(view)); }
    public UIDynamicAnimator(UICollectionViewLayout layout) { super((SkipInit) null); initObject(init(layout)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "referenceView")
    public native UIView getReferenceView();
    @Property(selector = "behaviors")
    public native NSArray<UIDynamicBehavior> getBehaviors();
    @Property(selector = "isRunning")
    public native boolean isRunning();
    @Property(selector = "delegate")
    public native UIDynamicAnimatorDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIDynamicAnimatorDelegate v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithReferenceView:")
    protected native @Pointer long init(UIView view);
    @Method(selector = "addBehavior:")
    public native void addBehavior(UIDynamicBehavior behavior);
    @Method(selector = "removeBehavior:")
    public native void removeBehavior(UIDynamicBehavior behavior);
    @Method(selector = "removeAllBehaviors")
    public native void removeAllBehaviors();
    @Method(selector = "itemsInRect:")
    public native List<UIDynamicItem> getItemsInRect(@ByVal CGRect rect);
    @Method(selector = "updateItemUsingCurrentState:")
    public native void updateItemUsingCurrentState(UIDynamicItem item);
    @Method(selector = "elapsedTime")
    public native double getElapsedTime();
    @Method(selector = "initWithCollectionViewLayout:")
    protected native @Pointer long init(UICollectionViewLayout layout);
    @Method(selector = "layoutAttributesForCellAtIndexPath:")
    public native UICollectionViewLayoutAttributes getLayoutAttributesForCell(NSIndexPath indexPath);
    @Method(selector = "layoutAttributesForSupplementaryViewOfKind:atIndexPath:")
    public native UICollectionViewLayoutAttributes getLayoutAttributesForSupplementaryView(String kind, NSIndexPath indexPath);
    @Method(selector = "layoutAttributesForDecorationViewOfKind:atIndexPath:")
    public native UICollectionViewLayoutAttributes getLayoutAttributesForDecorationView(String decorationViewKind, NSIndexPath indexPath);
    /*</methods>*/
}
