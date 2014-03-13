/*
 * Copyright (C) 2014 Trillian AB
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
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UICollectionViewLayoutAttributes/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UIDynamicItem/*</implements>*/ {

    /*<ptr>*/public static class UICollectionViewLayoutAttributesPtr extends Ptr<UICollectionViewLayoutAttributes, UICollectionViewLayoutAttributesPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UICollectionViewLayoutAttributes.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UICollectionViewLayoutAttributes() {}
    protected UICollectionViewLayoutAttributes(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "frame")
    public native @ByVal CGRect getFrame();
    @Property(selector = "setFrame:")
    public native void setFrame(@ByVal CGRect v);
    @Property(selector = "center")
    public native @ByVal CGPoint getCenter();
    @Property(selector = "setCenter:")
    public native void setCenter(@ByVal CGPoint v);
    @Property(selector = "size")
    public native @ByVal CGSize getSize();
    @Property(selector = "setSize:")
    public native void setSize(@ByVal CGSize v);
    @Property(selector = "transform3D")
    public native @ByVal CATransform3D getTransform3D();
    @Property(selector = "setTransform3D:")
    public native void setTransform3D(@ByVal CATransform3D v);
    @Property(selector = "bounds")
    public native @ByVal CGRect getBounds();
    @Property(selector = "setBounds:")
    public native void setBounds(@ByVal CGRect v);
    @Property(selector = "transform")
    public native @ByVal CGAffineTransform getTransform();
    @Property(selector = "setTransform:")
    public native void setTransform(@ByVal CGAffineTransform v);
    @Property(selector = "alpha")
    public native @MachineSizedFloat double getAlpha();
    @Property(selector = "setAlpha:")
    public native void setAlpha(@MachineSizedFloat double v);
    @Property(selector = "zIndex")
    public native @MachineSizedSInt long getZIndex();
    @Property(selector = "setZIndex:")
    public native void setZIndex(@MachineSizedSInt long v);
    @Property(selector = "isHidden")
    public native boolean isHidden();
    @Property(selector = "setHidden:")
    public native void setHidden(boolean v);
    @Property(selector = "indexPath")
    public native NSIndexPath getIndexPath();
    @Property(selector = "setIndexPath:")
    public native void setIndexPath(NSIndexPath v);
    @Property(selector = "representedElementCategory")
    public native UICollectionElementCategory getRepresentedElementCategory();
    @Property(selector = "representedElementKind")
    public native String getRepresentedElementKind();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "layoutAttributesForCellWithIndexPath:")
    public static native UICollectionViewLayoutAttributes createForCell(NSIndexPath indexPath);
    @Method(selector = "layoutAttributesForSupplementaryViewOfKind:withIndexPath:")
    public static native UICollectionViewLayoutAttributes createForSupplementaryView(String elementKind, NSIndexPath indexPath);
    @Method(selector = "layoutAttributesForDecorationViewOfKind:withIndexPath:")
    public static native UICollectionViewLayoutAttributes createForDecorationView(String decorationViewKind, NSIndexPath indexPath);
    /*</methods>*/
}
