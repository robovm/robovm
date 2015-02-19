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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/WKWebView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class WKWebViewPtr extends Ptr<WKWebView, WKWebViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(WKWebView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public WKWebView() {}
    protected WKWebView(SkipInit skipInit) { super(skipInit); }
    public WKWebView(@ByVal CGRect frame, WKWebViewConfiguration configuration) { super((SkipInit) null); initObject(init(frame, configuration)); }
    public WKWebView(NSCoder coder) { super((SkipInit) null); initObject(init(coder)); }
    /*</constructors>*/
    public WKWebView(CGRect frame) {
        super(frame);
    }
    /*<properties>*/
    @Property(selector = "configuration")
    public native WKWebViewConfiguration getConfiguration();
    @Property(selector = "navigationDelegate")
    public native WKNavigationDelegate getNavigationDelegate();
    @Property(selector = "setNavigationDelegate:", strongRef = true)
    public native void setNavigationDelegate(WKNavigationDelegate v);
    @Property(selector = "UIDelegate")
    public native WKUIDelegate getUIDelegate();
    @Property(selector = "setUIDelegate:", strongRef = true)
    public native void setUIDelegate(WKUIDelegate v);
    @Property(selector = "backForwardList")
    public native WKBackForwardList getBackForwardList();
    @Property(selector = "title")
    public native String getTitle();
    @Property(selector = "URL")
    public native NSURL getURL();
    @Property(selector = "isLoading")
    public native boolean isLoading();
    @Property(selector = "estimatedProgress")
    public native double getEstimatedProgress();
    @Property(selector = "hasOnlySecureContent")
    public native boolean hasOnlySecureContent();
    @Property(selector = "canGoBack")
    public native boolean canGoBack();
    @Property(selector = "canGoForward")
    public native boolean canGoForward();
    @Property(selector = "allowsBackForwardNavigationGestures")
    public native boolean allowsBackForwardNavigationGestures();
    @Property(selector = "setAllowsBackForwardNavigationGestures:")
    public native void setAllowsBackForwardNavigationGestures(boolean v);
    @Property(selector = "scrollView")
    public native UIScrollView getScrollView();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithFrame:configuration:")
    protected native @Pointer long init(@ByVal CGRect frame, WKWebViewConfiguration configuration);
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder coder);
    @Method(selector = "loadRequest:")
    public native WKNavigation loadRequest(NSURLRequest request);
    @Method(selector = "loadHTMLString:baseURL:")
    public native WKNavigation loadHTMLString(String string, NSURL baseURL);
    @Method(selector = "goToBackForwardListItem:")
    public native WKNavigation goToBackForwardListItem(WKBackForwardListItem item);
    @Method(selector = "goBack")
    public native WKNavigation goBack();
    @Method(selector = "goForward")
    public native WKNavigation goForward();
    @Method(selector = "reload")
    public native WKNavigation reload();
    @Method(selector = "reloadFromOrigin")
    public native WKNavigation reloadFromOrigin();
    @Method(selector = "stopLoading")
    public native void stopLoading();
    @Method(selector = "evaluateJavaScript:completionHandler:")
    public native void evaluateJavaScript(String javaScriptString, @Block VoidBlock2<NSObject, NSError> completionHandler);
    /*</methods>*/
}
