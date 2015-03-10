/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.watchkit;

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
import org.robovm.apple.uikit.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.mapkit.*;
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("WatchKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/WKInterfaceGroup/*</name>*/ 
    extends /*<extends>*/WKInterfaceObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class WKInterfaceGroupPtr extends Ptr<WKInterfaceGroup, WKInterfaceGroupPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(WKInterfaceGroup.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public WKInterfaceGroup() {}
    protected WKInterfaceGroup(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "setCornerRadius:")
    public native void setCornerRadius(@MachineSizedFloat double cornerRadius);
    @Method(selector = "setBackgroundColor:")
    public native void setBackgroundColor(UIColor color);
    @Method(selector = "setBackgroundImage:")
    public native void setBackgroundImage(UIImage image);
    @Method(selector = "setBackgroundImageData:")
    public native void setBackgroundImageData(NSData imageData);
    @Method(selector = "setBackgroundImageNamed:")
    public native void setBackgroundImageNamed(String imageName);
    @Method(selector = "startAnimating")
    public native void startAnimating();
    @Method(selector = "startAnimatingWithImagesInRange:duration:repeatCount:")
    public native void startAnimatingWithImages(@ByVal NSRange imageRange, double duration, @MachineSizedSInt long repeatCount);
    @Method(selector = "stopAnimating")
    public native void stopAnimating();
    /*</methods>*/
}
