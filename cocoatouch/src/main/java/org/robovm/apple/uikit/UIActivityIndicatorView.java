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
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIActivityIndicatorView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class UIActivityIndicatorViewPtr extends Ptr<UIActivityIndicatorView, UIActivityIndicatorViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIActivityIndicatorView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIActivityIndicatorView() {}
    protected UIActivityIndicatorView(SkipInit skipInit) { super(skipInit); }
    public UIActivityIndicatorView(UIActivityIndicatorViewStyle style) { super((SkipInit) null); initObject(init(style)); }
    /*</constructors>*/
    
    public UIActivityIndicatorView(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "activityIndicatorViewStyle")
    public native UIActivityIndicatorViewStyle getActivityIndicatorViewStyle();
    @Property(selector = "setActivityIndicatorViewStyle:")
    public native void setActivityIndicatorViewStyle(UIActivityIndicatorViewStyle v);
    @Property(selector = "hidesWhenStopped")
    public native boolean hidesWhenStopped();
    @Property(selector = "setHidesWhenStopped:")
    public native void setHidesWhenStopped(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "color")
    public native UIColor getColor();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setColor:")
    public native void setColor(UIColor v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithActivityIndicatorStyle:")
    protected native @Pointer long init(UIActivityIndicatorViewStyle style);
    @Method(selector = "startAnimating")
    public native void startAnimating();
    @Method(selector = "stopAnimating")
    public native void stopAnimating();
    @Method(selector = "isAnimating")
    public native boolean isAnimating();
    /*</methods>*/
}
