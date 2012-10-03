/*
 * Copyright (C) 2012 RoboVM
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
package org.robovm.cocoatouch.uikit;

/*<imports>*/
import org.robovm.cocoatouch.coreanimation.*;
import org.robovm.cocoatouch.coregraphics.*;
import org.robovm.cocoatouch.coreimage.*;
import org.robovm.cocoatouch.foundation.*;
import java.util.*;
import org.robovm.objc.*;
import org.robovm.objc.bind.*;
import org.robovm.objc.block.*;
import org.robovm.rt.bro.annotation.*;
import org.robovm.rt.bro.ptr.*;
/*</imports>*/

/*<library>*/@Library("UIKit")/*</library>*/
public class /*<name>*/ UIWebView /*</name>*/ 
    extends /*<extends>*/ UIView /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIWebView /*</name>*/.class);
    }

    /*<constructors>*/
    public UIWebView() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("allowsInlineMediaPlayback") public native @Type("BOOL") boolean isAllowsInlineMediaPlayback();
    @Bind("setAllowsInlineMediaPlayback:") public native void setAllowsInlineMediaPlayback(@Type("BOOL") boolean v);
    @Bind("canGoBack") public native @Type("BOOL") boolean isCanGoBack();
    @Bind("canGoForward") public native @Type("BOOL") boolean isCanGoForward();
    @Bind("dataDetectorTypes") public native @Type("UIDataDetectorTypes") EnumSet<UIDataDetectorType> getDataDetectorTypes();
    @Bind("setDataDetectorTypes:") public native void setDataDetectorTypes(@Type("UIDataDetectorTypes") EnumSet<UIDataDetectorType> v);
    @Bind("delegate") public native @Type("id<UIWebViewDelegate>") UIWebViewDelegate getDelegate();
    @Bind("setDelegate:") public native void setDelegate(@Type("id<UIWebViewDelegate>") UIWebViewDelegate v);
    @Bind("keyboardDisplayRequiresUserAction") public native @Type("BOOL") boolean isKeyboardDisplayRequiresUserAction();
    @Bind("setKeyboardDisplayRequiresUserAction:") public native void setKeyboardDisplayRequiresUserAction(@Type("BOOL") boolean v);
    @Bind("isLoading") public native @Type("BOOL") boolean isLoading();
    @Bind("mediaPlaybackAllowsAirPlay") public native @Type("BOOL") boolean isMediaPlaybackAllowsAirPlay();
    @Bind("setMediaPlaybackAllowsAirPlay:") public native void setMediaPlaybackAllowsAirPlay(@Type("BOOL") boolean v);
    @Bind("mediaPlaybackRequiresUserAction") public native @Type("BOOL") boolean isMediaPlaybackRequiresUserAction();
    @Bind("setMediaPlaybackRequiresUserAction:") public native void setMediaPlaybackRequiresUserAction(@Type("BOOL") boolean v);
    @Bind("request") public native @Type("NSURLRequest *") NSURLRequest getRequest();
    @Bind("scalesPageToFit") public native @Type("BOOL") boolean isScalesPageToFit();
    @Bind("setScalesPageToFit:") public native void setScalesPageToFit(@Type("BOOL") boolean v);
    @Bind("scrollView") public native @Type("UIScrollView *") UIScrollView getScrollView();
    @Bind("suppressesIncrementalRendering") public native @Type("BOOL") boolean isSuppressesIncrementalRendering();
    @Bind("setSuppressesIncrementalRendering:") public native void setSuppressesIncrementalRendering(@Type("BOOL") boolean v);
    /*</properties>*/
    /*<methods>*/
    @Bind("stringByEvaluatingJavaScriptFromString:") public native @Type("NSString *") String evaluateJavaScript(@Type("NSString *") String script);
    @Bind("goBack") public native @Type("void") void goBack();
    @Bind("goForward") public native @Type("void") void goForward();
    @Bind("loadData:MIMEType:textEncodingName:baseURL:") public native @Type("void") void loadData(@Type("NSData *") NSData data, @Type("NSString *") String MIMEType, @Type("NSString *") String encodingName, @Type("NSURL *") NSURL baseURL);
    @Bind("loadHTMLString:baseURL:") public native @Type("void") void loadHTML(@Type("NSString *") String string, @Type("NSURL *") NSURL baseURL);
    @Bind("loadRequest:") public native @Type("void") void loadRequest(@Type("NSURLRequest *") NSURLRequest request);
    @Bind("reload") public native @Type("void") void reload();
    @Bind("stopLoading") public native @Type("void") void stopLoading();
    /*</methods>*/

}
