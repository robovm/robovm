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
public class /*<name>*/ UIPrintPageRenderer /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIPrintPageRenderer /*</name>*/.class);
    }

    /*<constructors>*/
    public UIPrintPageRenderer() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("footerHeight") public native @Type("CGFloat") float getFooterHeight();
    @Bind("setFooterHeight:") public native void setFooterHeight(@Type("CGFloat") float v);
    @Bind("headerHeight") public native @Type("CGFloat") float getHeaderHeight();
    @Bind("setHeaderHeight:") public native void setHeaderHeight(@Type("CGFloat") float v);
    @Bind("paperRect") public native @Type("CGRect") CGRect getPaperRect();
    @Bind("printFormatters") public native @Type("NSArray *") NSArray getPrintFormatters();
    @Bind("setPrintFormatters:") public native void setPrintFormatters(@Type("NSArray *") NSArray v);
    @Bind("printableRect") public native @Type("CGRect") CGRect getPrintableRect();
    /*</properties>*/
    /*<methods>*/
    @Bind("addPrintFormatter:startingAtPageAtIndex:") public native @Type("void") void addPrintFormatter(@Type("UIPrintFormatter *") UIPrintFormatter formatter, @Type("NSInteger") int pageIndex);
    @Bind("drawContentForPageAtIndex:inRect:") public native @Type("void") void drawContent(@Type("NSInteger") int index, @Type("CGRect") CGRect contentRect);
    @Bind("drawFooterForPageAtIndex:inRect:") public native @Type("void") void drawFooter(@Type("NSInteger") int index, @Type("CGRect") CGRect footerRect);
    @Bind("drawHeaderForPageAtIndex:inRect:") public native @Type("void") void drawHeader(@Type("NSInteger") int index, @Type("CGRect") CGRect headerRect);
    @Bind("drawPageAtIndex:inRect:") public native @Type("void") void drawPage(@Type("NSInteger") int index, @Type("CGRect") CGRect pageRect);
    @Bind("drawPrintFormatter:forPageAtIndex:") public native @Type("void") void drawPrintFormatter(@Type("UIPrintFormatter *") UIPrintFormatter printFormatter, @Type("NSInteger") int index);
    @Bind("numberOfPages") public native @Type("NSInteger") int getNumberOfPages();
    @Bind("printFormattersForPageAtIndex:") public native @Type("NSArray *") NSArray getPrintFormatters(@Type("NSInteger") int pageIndex);
    @Bind("prepareForDrawingPages:") public native @Type("void") void prepareForDrawingPages(@Type("NSRange") NSRange range);
    /*</methods>*/

}
