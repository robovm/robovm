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
/*</javadoc>*/
/*<annotations>*//*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/WKNavigationDelegateAdapter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements WKNavigationDelegate/*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/
    /*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @NotImplemented("webView:decidePolicyForNavigationAction:decisionHandler:")
    public void decidePolicyForNavigationAction(WKWebView webView, WKNavigationAction navigationAction, @Block VoidBlock1<WKNavigationActionPolicy> decisionHandler) { throw new UnsupportedOperationException(); }
    @NotImplemented("webView:decidePolicyForNavigationResponse:decisionHandler:")
    public void decidePolicyForNavigationResponse(WKWebView webView, WKNavigationResponse navigationResponse, @Block VoidBlock1<WKNavigationResponsePolicy> decisionHandler) { throw new UnsupportedOperationException(); }
    @NotImplemented("webView:didStartProvisionalNavigation:")
    public void didStartProvisionalNavigation(WKWebView webView, WKNavigation navigation) { throw new UnsupportedOperationException(); }
    @NotImplemented("webView:didReceiveServerRedirectForProvisionalNavigation:")
    public void didReceiveServerRedirectForProvisionalNavigation(WKWebView webView, WKNavigation navigation) { throw new UnsupportedOperationException(); }
    @NotImplemented("webView:didFailProvisionalNavigation:withError:")
    public void didFailProvisionalNavigation(WKWebView webView, WKNavigation navigation, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("webView:didCommitNavigation:")
    public void didCommitNavigation(WKWebView webView, WKNavigation navigation) { throw new UnsupportedOperationException(); }
    @NotImplemented("webView:didFinishNavigation:")
    public void didFinishNavigation(WKWebView webView, WKNavigation navigation) { throw new UnsupportedOperationException(); }
    @NotImplemented("webView:didFailNavigation:withError:")
    public void didFailNavigation(WKWebView webView, WKNavigation navigation, NSError error) { throw new UnsupportedOperationException(); }
    @NotImplemented("webView:didReceiveAuthenticationChallenge:completionHandler:")
    public void didReceiveAuthenticationChallenge(WKWebView webView, NSURLAuthenticationChallenge challenge, @Block VoidBlock2<NSURLSessionAuthChallengeDisposition, NSURLCredential> completionHandler) { throw new UnsupportedOperationException(); }
    /*</methods>*/
}
