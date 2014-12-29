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
package org.robovm.apple.webkit;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("WebKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/WKWindowFeatures/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class WKWindowFeaturesPtr extends Ptr<WKWindowFeatures, WKWindowFeaturesPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(WKWindowFeatures.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public WKWindowFeatures() {}
    protected WKWindowFeatures(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    public boolean isMenuBarVisible() {
        return menuBarVisibility().booleanValue();
    }
    public boolean isStatusBarVisible() {
        return statusBarVisibility().booleanValue();
    }
    public boolean isToolbarsVisible() {
        return toolbarsVisibility().booleanValue();
    }
    public boolean allowsResizing() {
        return getAllowsResizing().booleanValue();
    }
    public double getX() {
        return x().doubleValue();
    }
    public double getY() {
        return y().doubleValue();
    }
    public double getWidth() {
        return width().doubleValue();
    }
    public double getHeight() {
        return height().doubleValue();
    }
    /*<properties>*/
    @Property(selector = "menuBarVisibility")
    protected native NSNumber menuBarVisibility();
    @Property(selector = "statusBarVisibility")
    protected native NSNumber statusBarVisibility();
    @Property(selector = "toolbarsVisibility")
    protected native NSNumber toolbarsVisibility();
    @Property(selector = "allowsResizing")
    protected native NSNumber getAllowsResizing();
    @Property(selector = "x")
    protected native NSNumber x();
    @Property(selector = "y")
    protected native NSNumber y();
    @Property(selector = "width")
    protected native NSNumber width();
    @Property(selector = "height")
    protected native NSNumber height();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
