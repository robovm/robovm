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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIActivityViewController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIActivityViewControllerPtr extends Ptr<UIActivityViewController, UIActivityViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIActivityViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIActivityViewController() {}
    protected UIActivityViewController(SkipInit skipInit) { super(skipInit); }
    public UIActivityViewController(NSArray<?> activityItems, NSArray<UIActivity> applicationActivities) { super((SkipInit) null); initObject(init(activityItems, applicationActivities)); }
    /*</constructors>*/
    /*<properties>*/
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "completionHandler")
    public native @Block VoidBlock2<String, Boolean> getCompletionHandler();
    /**
     * @since Available in iOS 6.0 and later.
     * @deprecated Deprecated in iOS 8.0.
     */
    @Deprecated
    @Property(selector = "setCompletionHandler:")
    public native void setCompletionHandler(@Block VoidBlock2<String, Boolean> v);
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "completionWithItemsHandler")
    public native @Block VoidBlock4<String, Boolean, NSArray<NSObject>, NSError> getCompletionWithItemsHandler();
    /**
     * @since Available in iOS 8.0 and later.
     */
    @Property(selector = "setCompletionWithItemsHandler:")
    public native void setCompletionWithItemsHandler(@Block VoidBlock4<String, Boolean, NSArray<NSObject>, NSError> v);
    @Property(selector = "excludedActivityTypes")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> getExcludedActivityTypes();
    @Property(selector = "setExcludedActivityTypes:")
    public native void setExcludedActivityTypes(@org.robovm.rt.bro.annotation.Marshaler(NSArray.AsStringListMarshaler.class) List<String> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithActivityItems:applicationActivities:")
    protected native @Pointer long init(NSArray<?> activityItems, NSArray<UIActivity> applicationActivities);
    /*</methods>*/
}
