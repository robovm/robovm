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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 2.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIWebView/*</name>*/ 
    extends /*<extends>*/UIView/*</extends>*/ 
    /*<implements>*/implements NSCoding, UIScrollViewDelegate/*</implements>*/ {

    /*<ptr>*/public static class UIWebViewPtr extends Ptr<UIWebView, UIWebViewPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIWebView.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIWebView() {}
    protected UIWebView(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UIWebView(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "delegate")
    public native UIWebViewDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(UIWebViewDelegate v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "scrollView")
    public native UIScrollView getScrollView();
    @Property(selector = "request")
    public native NSURLRequest getRequest();
    @Property(selector = "canGoBack")
    public native boolean isCanGoBack();
    @Property(selector = "canGoForward")
    public native boolean isCanGoForward();
    @Property(selector = "isLoading")
    public native boolean isLoading();
    @Property(selector = "scalesPageToFit")
    public native boolean isScalesPageToFit();
    @Property(selector = "setScalesPageToFit:")
    public native void setScalesPageToFit(boolean v);
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "detectsPhoneNumbers")
    public native boolean isDetectsPhoneNumbers();
    /**
     * @since Available in iOS 2.0 and later.
     * @deprecated Deprecated in iOS 3.0.
     */
    @Deprecated
    @Property(selector = "setDetectsPhoneNumbers:")
    public native void setDetectsPhoneNumbers(boolean v);
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "dataDetectorTypes")
    public native UIDataDetectorTypes getDataDetectorTypes();
    /**
     * @since Available in iOS 3.0 and later.
     */
    @Property(selector = "setDataDetectorTypes:")
    public native void setDataDetectorTypes(UIDataDetectorTypes v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "allowsInlineMediaPlayback")
    public native boolean isAllowsInlineMediaPlayback();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setAllowsInlineMediaPlayback:")
    public native void setAllowsInlineMediaPlayback(boolean v);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "mediaPlaybackRequiresUserAction")
    public native boolean isMediaPlaybackRequiresUserAction();
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Property(selector = "setMediaPlaybackRequiresUserAction:")
    public native void setMediaPlaybackRequiresUserAction(boolean v);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "mediaPlaybackAllowsAirPlay")
    public native boolean isMediaPlaybackAllowsAirPlay();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Property(selector = "setMediaPlaybackAllowsAirPlay:")
    public native void setMediaPlaybackAllowsAirPlay(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "suppressesIncrementalRendering")
    public native boolean isSuppressesIncrementalRendering();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setSuppressesIncrementalRendering:")
    public native void setSuppressesIncrementalRendering(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "keyboardDisplayRequiresUserAction")
    public native boolean isKeyboardDisplayRequiresUserAction();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setKeyboardDisplayRequiresUserAction:")
    public native void setKeyboardDisplayRequiresUserAction(boolean v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "paginationMode")
    public native UIWebPaginationMode getPaginationMode();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setPaginationMode:")
    public native void setPaginationMode(UIWebPaginationMode v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "paginationBreakingMode")
    public native UIWebPaginationBreakingMode getPaginationBreakingMode();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setPaginationBreakingMode:")
    public native void setPaginationBreakingMode(UIWebPaginationBreakingMode v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "pageLength")
    public native @MachineSizedFloat double getPageLength();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setPageLength:")
    public native void setPageLength(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "gapBetweenPages")
    public native @MachineSizedFloat double getGapBetweenPages();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "setGapBetweenPages:")
    public native void setGapBetweenPages(@MachineSizedFloat double v);
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "pageCount")
    public native @MachineSizedUInt long getPageCount();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "loadRequest:")
    public native void loadRequest(NSURLRequest request);
    @Method(selector = "loadHTMLString:baseURL:")
    public native void loadHTML(String string, NSURL baseURL);
    @Method(selector = "loadData:MIMEType:textEncodingName:baseURL:")
    public native void loadData(NSData data, String MIMEType, String textEncodingName, NSURL baseURL);
    @Method(selector = "reload")
    public native void reload();
    @Method(selector = "stopLoading")
    public native void stopLoading();
    @Method(selector = "goBack")
    public native void goBack();
    @Method(selector = "goForward")
    public native void goForward();
    @Method(selector = "stringByEvaluatingJavaScriptFromString:")
    public native String evaluateJavaScript(String script);
    @Method(selector = "scrollViewDidScroll:")
    public native void didScroll(UIScrollView scrollView);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "scrollViewDidZoom:")
    public native void didZoom(UIScrollView scrollView);
    @Method(selector = "scrollViewWillBeginDragging:")
    public native void willBeginDragging(UIScrollView scrollView);
    /**
     * @since Available in iOS 5.0 and later.
     */
    @Method(selector = "scrollViewWillEndDragging:withVelocity:targetContentOffset:")
    public native void willEndDragging(UIScrollView scrollView, @ByVal CGPoint velocity, CGPoint targetContentOffset);
    @Method(selector = "scrollViewDidEndDragging:willDecelerate:")
    public native void didEndDragging(UIScrollView scrollView, boolean decelerate);
    @Method(selector = "scrollViewWillBeginDecelerating:")
    public native void willBeginDecelerating(UIScrollView scrollView);
    @Method(selector = "scrollViewDidEndDecelerating:")
    public native void didEndDecelerating(UIScrollView scrollView);
    @Method(selector = "scrollViewDidEndScrollingAnimation:")
    public native void didEndScrollingAnimation(UIScrollView scrollView);
    @Method(selector = "viewForZoomingInScrollView:")
    public native UIView getViewForZooming(UIScrollView scrollView);
    /**
     * @since Available in iOS 3.2 and later.
     */
    @Method(selector = "scrollViewWillBeginZooming:withView:")
    public native void willBeginZooming(UIScrollView scrollView, UIView view);
    @Method(selector = "scrollViewDidEndZooming:withView:atScale:")
    public native void didEndZooming(UIScrollView scrollView, UIView view, @MachineSizedFloat double scale);
    @Method(selector = "scrollViewShouldScrollToTop:")
    public native boolean shouldScrollToTop(UIScrollView scrollView);
    @Method(selector = "scrollViewDidScrollToTop:")
    public native void didScrollToTop(UIScrollView scrollView);
    /*</methods>*/
}
