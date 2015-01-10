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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/WKPreferences/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class WKPreferencesPtr extends Ptr<WKPreferences, WKPreferencesPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(WKPreferences.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public WKPreferences() {}
    protected WKPreferences(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "minimumFontSize")
    public native @MachineSizedFloat double getMinimumFontSize();
    @Property(selector = "setMinimumFontSize:")
    public native void setMinimumFontSize(@MachineSizedFloat double v);
    @Property(selector = "javaScriptEnabled")
    public native boolean isJavaScriptEnabled();
    @Property(selector = "setJavaScriptEnabled:")
    public native void setJavaScriptEnabled(boolean v);
    @Property(selector = "javaScriptCanOpenWindowsAutomatically")
    public native boolean javaScriptCanOpenWindowsAutomatically();
    @Property(selector = "setJavaScriptCanOpenWindowsAutomatically:")
    public native void setJavaScriptCanOpenWindowsAutomatically(boolean v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
