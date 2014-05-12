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
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 4.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIPrintPageRenderer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIPrintPageRendererPtr extends Ptr<UIPrintPageRenderer, UIPrintPageRendererPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIPrintPageRenderer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIPrintPageRenderer() {}
    protected UIPrintPageRenderer(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "headerHeight")
    public native @MachineSizedFloat double getHeaderHeight();
    @Property(selector = "setHeaderHeight:")
    public native void setHeaderHeight(@MachineSizedFloat double v);
    @Property(selector = "footerHeight")
    public native @MachineSizedFloat double getFooterHeight();
    @Property(selector = "setFooterHeight:")
    public native void setFooterHeight(@MachineSizedFloat double v);
    @Property(selector = "paperRect")
    public native @ByVal CGRect getPaperRect();
    @Property(selector = "printableRect")
    public native @ByVal CGRect getPrintableRect();
    @Property(selector = "printFormatters")
    public native NSArray<UIPrintFormatter> getPrintFormatters();
    @Property(selector = "setPrintFormatters:")
    public native void setPrintFormatters(NSArray<UIPrintFormatter> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "printFormattersForPageAtIndex:")
    public native NSArray<UIPrintFormatter> getPrintFormatters(@MachineSizedSInt long pageIndex);
    @Method(selector = "addPrintFormatter:startingAtPageAtIndex:")
    public native void addPrintFormatter(UIPrintFormatter formatter, @MachineSizedSInt long pageIndex);
    @Method(selector = "numberOfPages")
    public native @MachineSizedSInt long getNumberOfPages();
    @Method(selector = "prepareForDrawingPages:")
    public native void prepareForDrawingPages(@ByVal NSRange range);
    @Method(selector = "drawPageAtIndex:inRect:")
    public native void drawPage(@MachineSizedSInt long pageIndex, @ByVal CGRect printableRect);
    @Method(selector = "drawPrintFormatter:forPageAtIndex:")
    public native void drawPrintFormatter(UIPrintFormatter printFormatter, @MachineSizedSInt long pageIndex);
    @Method(selector = "drawHeaderForPageAtIndex:inRect:")
    public native void drawHeader(@MachineSizedSInt long pageIndex, @ByVal CGRect headerRect);
    @Method(selector = "drawContentForPageAtIndex:inRect:")
    public native void drawContent(@MachineSizedSInt long pageIndex, @ByVal CGRect contentRect);
    @Method(selector = "drawFooterForPageAtIndex:inRect:")
    public native void drawFooter(@MachineSizedSInt long pageIndex, @ByVal CGRect footerRect);
    /*</methods>*/
}
