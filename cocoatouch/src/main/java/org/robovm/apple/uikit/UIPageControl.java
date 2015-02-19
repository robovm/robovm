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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPageControl/*</name>*/ 
    extends /*<extends>*/UIControl/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIPageControlPtr extends Ptr<UIPageControl, UIPageControlPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIPageControl.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIPageControl() {}
    protected UIPageControl(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    
    public UIPageControl(CGRect frame) {
        super(frame);
    }
    
    /*<properties>*/
    @Property(selector = "numberOfPages")
    public native @MachineSizedSInt long getNumberOfPages();
    @Property(selector = "setNumberOfPages:")
    public native void setNumberOfPages(@MachineSizedSInt long v);
    @Property(selector = "currentPage")
    public native @MachineSizedSInt long getCurrentPage();
    @Property(selector = "setCurrentPage:")
    public native void setCurrentPage(@MachineSizedSInt long v);
    @Property(selector = "hidesForSinglePage")
    public native boolean hidesForSinglePage();
    @Property(selector = "setHidesForSinglePage:")
    public native void setHidesForSinglePage(boolean v);
    @Property(selector = "defersCurrentPageDisplay")
    public native boolean defersCurrentPageDisplay();
    @Property(selector = "setDefersCurrentPageDisplay:")
    public native void setDefersCurrentPageDisplay(boolean v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "pageIndicatorTintColor")
    public native UIColor getPageIndicatorTintColor();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setPageIndicatorTintColor:")
    public native void setPageIndicatorTintColor(UIColor v);
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "currentPageIndicatorTintColor")
    public native UIColor getCurrentPageIndicatorTintColor();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @Property(selector = "setCurrentPageIndicatorTintColor:")
    public native void setCurrentPageIndicatorTintColor(UIColor v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "updateCurrentPageDisplay")
    public native void updateCurrentPageDisplay();
    @Method(selector = "sizeForNumberOfPages:")
    public native @ByVal CGSize getSizeForNumberOfPages(@MachineSizedSInt long pageCount);
    /*</methods>*/
}
