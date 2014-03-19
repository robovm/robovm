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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UICollisionBehavior/*</name>*/ 
    extends /*<extends>*/UIDynamicBehavior/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UICollisionBehaviorPtr extends Ptr<UICollisionBehavior, UICollisionBehaviorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UICollisionBehavior.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UICollisionBehavior() {}
    protected UICollisionBehavior(SkipInit skipInit) { super(skipInit); }
    public UICollisionBehavior(NSArray<?> items) { super((SkipInit) null); initObject(initWithItems$(items)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "items")
    public native NSArray<?> getItems();
    @Property(selector = "collisionMode")
    public native UICollisionBehaviorMode getCollisionMode();
    @Property(selector = "setCollisionMode:")
    public native void setCollisionMode(UICollisionBehaviorMode v);
    @Property(selector = "translatesReferenceBoundsIntoBoundary")
    public native boolean isTranslatesReferenceBoundsIntoBoundary();
    @Property(selector = "setTranslatesReferenceBoundsIntoBoundary:")
    public native void setTranslatesReferenceBoundsIntoBoundary(boolean v);
    @Property(selector = "boundaryIdentifiers")
    public native NSArray<?> getBoundaryIdentifiers();
    @Property(selector = "collisionDelegate")
    public native UICollisionBehaviorDelegate getCollisionDelegate();
    @Property(selector = "setCollisionDelegate:", strongRef = true)
    public native void setCollisionDelegate(UICollisionBehaviorDelegate v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithItems:")
    protected native @Pointer long initWithItems$(NSArray<?> items);
    @Method(selector = "addItem:")
    public native void addItem$(UIDynamicItem item);
    @Method(selector = "removeItem:")
    public native void removeItem$(UIDynamicItem item);
    @Method(selector = "setTranslatesReferenceBoundsIntoBoundaryWithInsets:")
    public native void setTranslatesReferenceBoundsIntoBoundaryWithInsets$(@ByVal UIEdgeInsets insets);
    @Method(selector = "addBoundaryWithIdentifier:forPath:")
    public native void addBoundaryWithIdentifier$forPath$(NSObject identifier, UIBezierPath bezierPath);
    @Method(selector = "addBoundaryWithIdentifier:fromPoint:toPoint:")
    public native void addBoundaryWithIdentifier$fromPoint$toPoint$(NSObject identifier, @ByVal CGPoint p1, @ByVal CGPoint p2);
    @Method(selector = "boundaryWithIdentifier:")
    public native UIBezierPath boundaryWithIdentifier$(NSObject identifier);
    @Method(selector = "removeBoundaryWithIdentifier:")
    public native void removeBoundaryWithIdentifier$(NSObject identifier);
    @Method(selector = "removeAllBoundaries")
    public native void removeAllBoundaries();
    /*</methods>*/
}
