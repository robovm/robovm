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
import org.robovm.apple.corelocation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("UIKit")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIFontTextStyle/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(UIFontTextStyle.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontTextStyle Headline = new UIFontTextStyle("HeadlineValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontTextStyle Body = new UIFontTextStyle("BodyValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontTextStyle Subheadline = new UIFontTextStyle("SubheadlineValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontTextStyle Footnote = new UIFontTextStyle("FootnoteValue");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontTextStyle Caption1 = new UIFontTextStyle("Caption1Value");
    /**
     * @since Available in iOS 7.0 and later.
     */
    public static UIFontTextStyle Caption2 = new UIFontTextStyle("Caption2Value");
    private static UIFontTextStyle[] values = new UIFontTextStyle[] {Headline, Body, Subheadline, Footnote, Caption1, Caption2};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private UIFontTextStyle(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static UIFontTextStyle valueOf(NSString value) {
        for (UIFontTextStyle v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/UIFontTextStyle/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontTextStyleHeadline", optional=true)
    protected static native NSString HeadlineValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontTextStyleBody", optional=true)
    protected static native NSString BodyValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontTextStyleSubheadline", optional=true)
    protected static native NSString SubheadlineValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontTextStyleFootnote", optional=true)
    protected static native NSString FootnoteValue();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontTextStyleCaption1", optional=true)
    protected static native NSString Caption1Value();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIFontTextStyleCaption2", optional=true)
    protected static native NSString Caption2Value();
    /*</methods>*/
}
