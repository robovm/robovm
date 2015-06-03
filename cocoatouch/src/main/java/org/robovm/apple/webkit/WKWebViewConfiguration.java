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
package org.robovm.apple.webkit;

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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("WebKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/WKWebViewConfiguration/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class WKWebViewConfigurationPtr extends Ptr<WKWebViewConfiguration, WKWebViewConfigurationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(WKWebViewConfiguration.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public WKWebViewConfiguration() {}
    protected WKWebViewConfiguration(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "processPool")
    public native WKProcessPool getProcessPool();
    @Property(selector = "setProcessPool:")
    public native void setProcessPool(WKProcessPool v);
    @Property(selector = "preferences")
    public native WKPreferences getPreferences();
    @Property(selector = "setPreferences:")
    public native void setPreferences(WKPreferences v);
    @Property(selector = "userContentController")
    public native WKUserContentController getUserContentController();
    @Property(selector = "setUserContentController:")
    public native void setUserContentController(WKUserContentController v);
    @Property(selector = "suppressesIncrementalRendering")
    public native boolean suppressesIncrementalRendering();
    @Property(selector = "setSuppressesIncrementalRendering:")
    public native void setSuppressesIncrementalRendering(boolean v);
    @Property(selector = "allowsInlineMediaPlayback")
    public native boolean allowsInlineMediaPlayback();
    @Property(selector = "setAllowsInlineMediaPlayback:")
    public native void setAllowsInlineMediaPlayback(boolean v);
    @Property(selector = "mediaPlaybackRequiresUserAction")
    public native boolean mediaPlaybackRequiresUserAction();
    @Property(selector = "setMediaPlaybackRequiresUserAction:")
    public native void setMediaPlaybackRequiresUserAction(boolean v);
    @Property(selector = "mediaPlaybackAllowsAirPlay")
    public native boolean mediaPlaybackAllowsAirPlay();
    @Property(selector = "setMediaPlaybackAllowsAirPlay:")
    public native void setMediaPlaybackAllowsAirPlay(boolean v);
    @Property(selector = "selectionGranularity")
    public native WKSelectionGranularity getSelectionGranularity();
    @Property(selector = "setSelectionGranularity:")
    public native void setSelectionGranularity(WKSelectionGranularity v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    
    /*</methods>*/
}
