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
import org.robovm.apple.coretext.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 5.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIStoryboardSegue/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIStoryboardSeguePtr extends Ptr<UIStoryboardSegue, UIStoryboardSeguePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIStoryboardSegue.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIStoryboardSegue() {}
    protected UIStoryboardSegue(SkipInit skipInit) { super(skipInit); }
    public UIStoryboardSegue(String identifier, UIViewController source, UIViewController destination) { super((SkipInit) null); initObject(init(identifier, source, destination)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "identifier")
    public native String getIdentifier();
    @Property(selector = "sourceViewController")
    public native UIViewController getSourceViewController();
    @Property(selector = "destinationViewController")
    public native UIViewController getDestinationViewController();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithIdentifier:source:destination:")
    protected native @Pointer long init(String identifier, UIViewController source, UIViewController destination);
    @Method(selector = "perform")
    public native void perform();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Method(selector = "segueWithIdentifier:source:destination:performHandler:")
    public static native UIStoryboardSegue create(String identifier, UIViewController source, UIViewController destination, @Block Runnable performHandler);
    /*</methods>*/
}
