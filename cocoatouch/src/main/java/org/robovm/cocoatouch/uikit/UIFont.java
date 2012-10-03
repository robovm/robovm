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
public class /*<name>*/ UIFont /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UIFont /*</name>*/.class);
    }

    /*<constructors>*/
    public UIFont() {}
    
    /*</constructors>*/
    /*<properties>*/
    @Bind("ascender") public native @Type("CGFloat") float getAscender();
    @Bind("capHeight") public native @Type("CGFloat") float getCapHeight();
    @Bind("descender") public native @Type("CGFloat") float getDescender();
    @Bind("familyName") public native @Type("NSString *") String getFamilyName();
    @Bind("fontName") public native @Type("NSString *") String getFontName();
    @Bind("lineHeight") public native @Type("CGFloat") float getLineHeight();
    @Bind("pointSize") public native @Type("CGFloat") float getPointSize();
    @Bind("xHeight") public native @Type("CGFloat") float getXHeight();
    /*</properties>*/
    /*<methods>*/
    @Bind("boldSystemFontOfSize:") public native static @Type("UIFont *") UIFont getBoldSystemFont(@Type("CGFloat") float fontSize);
    @Bind("buttonFontSize") public native static @Type("CGFloat") float getButtonFontSize();
    @Bind("familyNames") public native static @Type("NSArray *") NSArray getFamilyNames();
    @Bind("fontWithName:size:") public native static @Type("UIFont *") UIFont getFont(@Type("NSString *") String fontName, @Type("CGFloat") float fontSize);
    @Bind("fontNamesForFamilyName:") public native static @Type("NSArray *") NSArray getFontNamesForFamilyName(@Type("NSString *") String familyName);
    @Bind("italicSystemFontOfSize:") public native static @Type("UIFont *") UIFont getItalicSystemFont(@Type("CGFloat") float fontSize);
    @Bind("labelFontSize") public native static @Type("CGFloat") float getLabelFontSize();
    @Bind("smallSystemFontSize") public native static @Type("CGFloat") float getSmallSystemFontSize();
    @Bind("systemFontOfSize:") public native static @Type("UIFont *") UIFont getSystemFont(@Type("CGFloat") float fontSize);
    @Bind("systemFontSize") public native static @Type("CGFloat") float getSystemFontSize();
    @Bind("fontWithSize:") public native @Type("UIFont *") UIFont getFontWithSize(@Type("CGFloat") float fontSize);
    /*</methods>*/

}
