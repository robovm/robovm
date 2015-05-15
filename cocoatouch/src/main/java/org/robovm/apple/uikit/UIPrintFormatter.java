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
 * @since Available in iOS 4.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPrintFormatter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIPrintFormatterPtr extends Ptr<UIPrintFormatter, UIPrintFormatterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIPrintFormatter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIPrintFormatter() {}
    protected UIPrintFormatter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "printPageRenderer")
    public native UIPrintPageRenderer getPrintPageRenderer();
    @Property(selector = "maximumContentHeight")
    public native @MachineSizedFloat double getMaximumContentHeight();
    @Property(selector = "setMaximumContentHeight:")
    public native void setMaximumContentHeight(@MachineSizedFloat double v);
    @Property(selector = "maximumContentWidth")
    public native @MachineSizedFloat double getMaximumContentWidth();
    @Property(selector = "setMaximumContentWidth:")
    public native void setMaximumContentWidth(@MachineSizedFloat double v);
    @Property(selector = "contentInsets")
    public native @ByVal UIEdgeInsets getContentInsets();
    @Property(selector = "setContentInsets:")
    public native void setContentInsets(@ByVal UIEdgeInsets v);
    @Property(selector = "perPageContentInsets")
    public native @ByVal UIEdgeInsets getPerPageContentInsets();
    @Property(selector = "setPerPageContentInsets:")
    public native void setPerPageContentInsets(@ByVal UIEdgeInsets v);
    @Property(selector = "startPage")
    public native @MachineSizedSInt long getStartPage();
    @Property(selector = "setStartPage:")
    public native void setStartPage(@MachineSizedSInt long v);
    @Property(selector = "pageCount")
    public native @MachineSizedSInt long getPageCount();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "removeFromPrintPageRenderer")
    public native void removeFromPrintPageRenderer();
    @Method(selector = "rectForPageAtIndex:")
    public native @ByVal CGRect getRectForPage(@MachineSizedSInt long pageIndex);
    @Method(selector = "drawInRect:forPageAtIndex:")
    public native void draw(@ByVal CGRect rect, @MachineSizedSInt long pageIndex);
    /*</methods>*/
}
