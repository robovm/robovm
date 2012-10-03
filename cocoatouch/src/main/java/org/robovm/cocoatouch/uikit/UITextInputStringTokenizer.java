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
public class /*<name>*/ UITextInputStringTokenizer /*</name>*/ 
    extends /*<extends>*/ NSObject /*</extends>*/ 
    /*<implements>*/ implements UITextInputTokenizer /*</implements>*/ {

    static {
        ObjCRuntime.bind(/*<name>*/ UITextInputStringTokenizer /*</name>*/.class);
    }

    /*<constructors>*/
    public UITextInputStringTokenizer() {}
    @Bind("initWithTextInput:") public UITextInputStringTokenizer(@Type("UIResponder < UITextInput > *") UITextInput textInput) {}
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<methods>*/
    @Bind("positionFromPosition:toBoundary:inDirection:") public native @Type("UITextPosition *") UITextPosition getPosition(@Type("UITextPosition *") UITextPosition position, @Type("UITextGranularity") UITextGranularity granularity, @Type("UITextDirection") UITextDirection direction);
    @Bind("rangeEnclosingPosition:withGranularity:inDirection:") public native @Type("UITextRange *") UITextRange getRangeEnclosingPosition(@Type("UITextPosition *") UITextPosition position, @Type("UITextGranularity") UITextGranularity granularity, @Type("UITextDirection") UITextDirection direction);
    @Bind("isPosition:atBoundary:inDirection:") public native @Type("BOOL") boolean isPositionAtBoundary(@Type("UITextPosition *") UITextPosition position, @Type("UITextGranularity") UITextGranularity granularity, @Type("UITextDirection") UITextDirection direction);
    @Bind("isPosition:withinTextUnit:inDirection:") public native @Type("BOOL") boolean isPositionWithinTextUnit(@Type("UITextPosition *") UITextPosition position, @Type("UITextGranularity") UITextGranularity granularity, @Type("UITextDirection") UITextDirection direction);
    /*</methods>*/

}
