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

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("UIKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/UIKeyCommand/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class UIKeyCommandPtr extends Ptr<UIKeyCommand, UIKeyCommandPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(UIKeyCommand.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public UIKeyCommand() {}
    protected UIKeyCommand(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "input")
    public native String getInput();
    @Property(selector = "modifierFlags")
    public native UIKeyModifierFlags getModifierFlags();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @GlobalValue(symbol="UIKeyInputUpArrow")
    public static native String KeyInputUpArrow();
    @GlobalValue(symbol="UIKeyInputDownArrow")
    public static native String KeyInputDownArrow();
    @GlobalValue(symbol="UIKeyInputLeftArrow")
    public static native String KeyInputLeftArrow();
    @GlobalValue(symbol="UIKeyInputRightArrow")
    public static native String KeyInputRightArrow();
    @GlobalValue(symbol="UIKeyInputEscape")
    public static native String KeyInputEscape();
    
    @Method(selector = "keyCommandWithInput:modifierFlags:action:")
    public static native UIKeyCommand create(String input, UIKeyModifierFlags modifierFlags, Selector action);
    /*</methods>*/
}
