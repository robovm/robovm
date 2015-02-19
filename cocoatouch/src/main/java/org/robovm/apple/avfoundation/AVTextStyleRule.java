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
package org.robovm.apple.avfoundation;

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
import org.robovm.apple.dispatch.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coreaudio.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.corevideo.*;
import org.robovm.apple.audiotoolbox.*;
import org.robovm.apple.mediatoolbox.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("AVFoundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/AVTextStyleRule/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class AVTextStyleRulePtr extends Ptr<AVTextStyleRule, AVTextStyleRulePtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(AVTextStyleRule.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public AVTextStyleRule() {}
    protected AVTextStyleRule(SkipInit skipInit) { super(skipInit); }
    public AVTextStyleRule(CMTextMarkupAttributes textMarkupAttributes) { super((SkipInit) null); initObject(init(textMarkupAttributes)); }
    public AVTextStyleRule(CMTextMarkupAttributes textMarkupAttributes, String textSelector) { super((SkipInit) null); initObject(init(textMarkupAttributes, textSelector)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "textMarkupAttributes")
    public native CMTextMarkupAttributes getTextMarkupAttributes();
    @Property(selector = "textSelector")
    public native String getTextSelector();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithTextMarkupAttributes:")
    protected native @Pointer long init(CMTextMarkupAttributes textMarkupAttributes);
    @Method(selector = "initWithTextMarkupAttributes:textSelector:")
    protected native @Pointer long init(CMTextMarkupAttributes textMarkupAttributes, String textSelector);
    @Method(selector = "propertyListForTextStyleRules:")
    public static native NSPropertyList createPropertyList(NSArray<AVTextStyleRule> textStyleRules);
    @Method(selector = "textStyleRulesFromPropertyList:")
    public static native NSArray<AVTextStyleRule> createTextStyleRulesArray(NSPropertyList plist);
    @Method(selector = "textStyleRuleWithTextMarkupAttributes:")
    public static native AVTextStyleRule create(CMTextMarkupAttributes textMarkupAttributes);
    @Method(selector = "textStyleRuleWithTextMarkupAttributes:textSelector:")
    public static native AVTextStyleRule create(CMTextMarkupAttributes textMarkupAttributes, String textSelector);
    /*</methods>*/
}
