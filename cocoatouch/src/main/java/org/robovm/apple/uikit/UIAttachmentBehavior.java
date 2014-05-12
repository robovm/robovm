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

/*<javadoc>*/
/**
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIAttachmentBehavior/*</name>*/ 
    extends /*<extends>*/UIDynamicBehavior/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIAttachmentBehaviorPtr extends Ptr<UIAttachmentBehavior, UIAttachmentBehaviorPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIAttachmentBehavior.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIAttachmentBehavior() {}
    protected UIAttachmentBehavior(SkipInit skipInit) { super(skipInit); }
    public UIAttachmentBehavior(UIDynamicItem item, @ByVal CGPoint point) { super((SkipInit) null); initObject(initWithItem$attachedToAnchor$(item, point)); }
    public UIAttachmentBehavior(UIDynamicItem item, @ByVal UIOffset offset, @ByVal CGPoint point) { super((SkipInit) null); initObject(initWithItem$offsetFromCenter$attachedToAnchor$(item, offset, point)); }
    public UIAttachmentBehavior(UIDynamicItem item1, UIDynamicItem item2) { super((SkipInit) null); initObject(initWithItem$attachedToItem$(item1, item2)); }
    public UIAttachmentBehavior(UIDynamicItem item1, @ByVal UIOffset offset1, UIDynamicItem item2, @ByVal UIOffset offset2) { super((SkipInit) null); initObject(initWithItem$offsetFromCenter$attachedToItem$offsetFromCenter$(item1, offset1, item2, offset2)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "items")
    public native List<UIDynamicItem> getItems();
    @Property(selector = "attachedBehaviorType")
    public native UIAttachmentBehaviorType getAttachedBehaviorType();
    @Property(selector = "anchorPoint")
    public native @ByVal CGPoint getAnchorPoint();
    @Property(selector = "setAnchorPoint:")
    public native void setAnchorPoint(@ByVal CGPoint v);
    @Property(selector = "length")
    public native @MachineSizedFloat double getLength();
    @Property(selector = "setLength:")
    public native void setLength(@MachineSizedFloat double v);
    @Property(selector = "damping")
    public native @MachineSizedFloat double getDamping();
    @Property(selector = "setDamping:")
    public native void setDamping(@MachineSizedFloat double v);
    @Property(selector = "frequency")
    public native @MachineSizedFloat double getFrequency();
    @Property(selector = "setFrequency:")
    public native void setFrequency(@MachineSizedFloat double v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithItem:attachedToAnchor:")
    protected native @Pointer long initWithItem$attachedToAnchor$(UIDynamicItem item, @ByVal CGPoint point);
    @Method(selector = "initWithItem:offsetFromCenter:attachedToAnchor:")
    protected native @Pointer long initWithItem$offsetFromCenter$attachedToAnchor$(UIDynamicItem item, @ByVal UIOffset offset, @ByVal CGPoint point);
    @Method(selector = "initWithItem:attachedToItem:")
    protected native @Pointer long initWithItem$attachedToItem$(UIDynamicItem item1, UIDynamicItem item2);
    @Method(selector = "initWithItem:offsetFromCenter:attachedToItem:offsetFromCenter:")
    protected native @Pointer long initWithItem$offsetFromCenter$attachedToItem$offsetFromCenter$(UIDynamicItem item1, @ByVal UIOffset offset1, UIDynamicItem item2, @ByVal UIOffset offset2);
    /*</methods>*/
}
