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
import org.robovm.rt.annotation.*;
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
 * @since Available in iOS 3.2 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UITextInputStringTokenizer/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements UITextInputTokenizer/*</implements>*/ {

    /*<ptr>*/public static class UITextInputStringTokenizerPtr extends Ptr<UITextInputStringTokenizer, UITextInputStringTokenizerPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UITextInputStringTokenizer.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UITextInputStringTokenizer() {}
    protected UITextInputStringTokenizer(SkipInit skipInit) { super(skipInit); }
    public UITextInputStringTokenizer(UITextInput textInput) { super((SkipInit) null); initObject(init(textInput)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithTextInput:")
    protected native @Pointer long init(UITextInput textInput);
    @Method(selector = "rangeEnclosingPosition:withGranularity:inDirection:")
    public native UITextRange getRangeEnclosingPosition(UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    @Method(selector = "isPosition:atBoundary:inDirection:")
    public native boolean isPositionAtBoundary(UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    @Method(selector = "positionFromPosition:toBoundary:inDirection:")
    public native UITextPosition getPosition(UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    @Method(selector = "isPosition:withinTextUnit:inDirection:")
    public native boolean isPositionWithinTextUnit(UITextPosition position, UITextGranularity granularity, UITextDirection direction);
    /*</methods>*/
}
