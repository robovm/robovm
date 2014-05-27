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
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSParagraphStyle/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSParagraphStylePtr extends Ptr<NSParagraphStyle, NSParagraphStylePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSParagraphStyle.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSParagraphStyle() {}
    protected NSParagraphStyle(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "lineSpacing")
    public native @MachineSizedFloat double getLineSpacing();
    @Property(selector = "paragraphSpacing")
    public native @MachineSizedFloat double getParagraphSpacing();
    @Property(selector = "alignment")
    public native NSTextAlignment getAlignment();
    @Property(selector = "headIndent")
    public native @MachineSizedFloat double getHeadIndent();
    @Property(selector = "tailIndent")
    public native @MachineSizedFloat double getTailIndent();
    @Property(selector = "firstLineHeadIndent")
    public native @MachineSizedFloat double getFirstLineHeadIndent();
    @Property(selector = "minimumLineHeight")
    public native @MachineSizedFloat double getMinimumLineHeight();
    @Property(selector = "maximumLineHeight")
    public native @MachineSizedFloat double getMaximumLineHeight();
    @Property(selector = "lineBreakMode")
    public native NSLineBreakMode getLineBreakMode();
    @Property(selector = "baseWritingDirection")
    public native NSWritingDirection getBaseWritingDirection();
    @Property(selector = "lineHeightMultiple")
    public native @MachineSizedFloat double getLineHeightMultiple();
    @Property(selector = "paragraphSpacingBefore")
    public native @MachineSizedFloat double getParagraphSpacingBefore();
    @Property(selector = "hyphenationFactor")
    public native float getHyphenationFactor();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "tabStops")
    public native NSArray<NSTextTab> getTabStops();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @Property(selector = "defaultTabInterval")
    public native @MachineSizedFloat double getDefaultTabInterval();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "defaultParagraphStyle")
    public static native NSParagraphStyle getDefaultParagraphStyle();
    @Method(selector = "defaultWritingDirectionForLanguage:")
    public static native NSWritingDirection getDefaultWritingDirection(String languageName);
    @Method(selector = "encodeWithCoder:")
    public native void encode(NSCoder aCoder);
    /*</methods>*/
}
