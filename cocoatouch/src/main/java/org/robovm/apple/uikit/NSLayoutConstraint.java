/*
 * Copyright (C) 2014 Trillian Mobile AB
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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSLayoutConstraint/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSLayoutConstraintPtr extends Ptr<NSLayoutConstraint, NSLayoutConstraintPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSLayoutConstraint.class); }/*</bind>*/
    /*<constants>*/
    public static final float PriorityRequired = 1000;
    public static final float PriorityDefaultHigh = 750;
    public static final float PriorityDefaultLow = 250;
    public static final float PriorityFittingSizeLevel = 50;
    /*</constants>*/
    /*<constructors>*/
    public NSLayoutConstraint() {}
    protected NSLayoutConstraint(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "priority")
    public native float getPriority();
    @Property(selector = "setPriority:")
    public native void setPriority(float v);
    @Property(selector = "shouldBeArchived")
    public native boolean isShouldBeArchived();
    @Property(selector = "setShouldBeArchived:")
    public native void setShouldBeArchived(boolean v);
    @Property(selector = "firstItem")
    public native NSObject getFirstItem();
    @Property(selector = "firstAttribute")
    public native NSLayoutAttribute getFirstAttribute();
    @Property(selector = "relation")
    public native NSLayoutRelation getRelation();
    @Property(selector = "secondItem")
    public native NSObject getSecondItem();
    @Property(selector = "secondAttribute")
    public native NSLayoutAttribute getSecondAttribute();
    @Property(selector = "multiplier")
    public native @MachineSizedFloat double getMultiplier();
    @Property(selector = "constant")
    public native @MachineSizedFloat double getConstant();
    @Property(selector = "setConstant:")
    public native void setConstant(@MachineSizedFloat double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "constraintsWithVisualFormat:options:metrics:views:")
    public static native NSArray<NSLayoutConstraint> createFromVisualFormat(String format, NSLayoutFormatOptions opts, NSDictionary<NSString, NSNumber> metrics, NSDictionary<NSString, UIView> views);
    @Method(selector = "constraintWithItem:attribute:relatedBy:toItem:attribute:multiplier:constant:")
    public static native NSObject create(NSObject view1, NSLayoutAttribute attr1, NSLayoutRelation relation, NSObject view2, NSLayoutAttribute attr2, @MachineSizedFloat double multiplier, @MachineSizedFloat double c);
    /*</methods>*/
}
