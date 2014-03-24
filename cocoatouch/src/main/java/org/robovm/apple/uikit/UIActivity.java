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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIActivity/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIActivityPtr extends Ptr<UIActivity, UIActivityPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIActivity.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIActivity() {}
    protected UIActivity(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "activityType")
    public native String getType();
    @Method(selector = "activityTitle")
    public native String getTitle();
    @Method(selector = "activityImage")
    public native UIImage getImage();
    @Method(selector = "canPerformWithActivityItems:")
    public native boolean canPerform(NSArray<?> activityItems);
    @Method(selector = "prepareWithActivityItems:")
    public native void prepare(NSArray<?> activityItems);
    @Method(selector = "activityViewController")
    public native UIViewController getViewController();
    @Method(selector = "performActivity")
    public native void perform();
    @Method(selector = "activityDidFinish:")
    public native void didFinish(boolean completed);
    @Method(selector = "activityCategory")
    public static native UIActivityCategory activityCategory();
    /*</methods>*/
}
