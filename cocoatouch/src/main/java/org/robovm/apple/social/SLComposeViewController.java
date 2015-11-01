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
package org.robovm.apple.social;

/*<imports>*/
import java.io.*;
import java.nio.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.annotation.*;
import org.robovm.objc.block.*;
import org.robovm.rt.*;
import org.robovm.rt.annotation.*;
import org.robovm.rt.bro.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
import org.robovm.apple.foundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.accounts.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("Social") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/SLComposeViewController/*</name>*/ 
    extends /*<extends>*/UIViewController/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class SLComposeViewControllerPtr extends Ptr<SLComposeViewController, SLComposeViewControllerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(SLComposeViewController.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public SLComposeViewController() {}
    protected SLComposeViewController(SkipInit skipInit) { super(skipInit); }
    public SLComposeViewController(SLServiceType serviceType) { super(create(serviceType)); retain(getHandle()); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "serviceType")
    public native SLServiceType getServiceType();
    @Property(selector = "completionHandler")
    public native @Block VoidBlock1<SLComposeViewControllerResult> getCompletionHandler();
    @Property(selector = "setCompletionHandler:")
    public native void setCompletionHandler(@Block VoidBlock1<SLComposeViewControllerResult> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setInitialText:")
    public native boolean setInitialText(String text);
    @Method(selector = "addImage:")
    public native boolean addImage(UIImage image);
    @Method(selector = "removeAllImages")
    public native boolean removeAllImages();
    @Method(selector = "addURL:")
    public native boolean addURL(NSURL url);
    @Method(selector = "removeAllURLs")
    public native boolean removeAllURLs();
    @Method(selector = "isAvailableForServiceType:")
    public static native boolean isAvailable(SLServiceType serviceType);
    @Method(selector = "composeViewControllerForServiceType:")
    protected static native @Pointer long create(SLServiceType serviceType);
    /*</methods>*/
}
