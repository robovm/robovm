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
 * @since Available in iOS 7.0 and later.
 */
/*</javadoc>*/
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
    public UIKeyCommand(NSCoder aDecoder) { super((SkipInit) null); initObject(init(aDecoder)); }
    public UIKeyCommand(String input, UIKeyModifierFlags modifierFlags, Selector action) { super(create(input, modifierFlags, action)); retain(getHandle()); }
    /**
     * @since Available in iOS 9.0 and later.
     */
    public UIKeyCommand(String input, UIKeyModifierFlags modifierFlags, Selector action, String discoverabilityTitle) { super(create(input, modifierFlags, action, discoverabilityTitle)); retain(getHandle()); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "input")
    public native String getInput();
    @Property(selector = "modifierFlags")
    public native UIKeyModifierFlags getModifierFlags();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "discoverabilityTitle")
    public native String getDiscoverabilityTitle();
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Property(selector = "setDiscoverabilityTitle:")
    public native void setDiscoverabilityTitle(String v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIKeyInputUpArrow", optional=true)
    public static native String UpArrow();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIKeyInputDownArrow", optional=true)
    public static native String DownArrow();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIKeyInputLeftArrow", optional=true)
    public static native String LeftArrow();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIKeyInputRightArrow", optional=true)
    public static native String RightArrow();
    /**
     * @since Available in iOS 7.0 and later.
     */
    @GlobalValue(symbol="UIKeyInputEscape", optional=true)
    public static native String Escape();
    
    @Method(selector = "initWithCoder:")
    protected native @Pointer long init(NSCoder aDecoder);
    @Method(selector = "keyCommandWithInput:modifierFlags:action:")
    protected static native @Pointer long create(String input, UIKeyModifierFlags modifierFlags, Selector action);
    /**
     * @since Available in iOS 9.0 and later.
     */
    @Method(selector = "keyCommandWithInput:modifierFlags:action:discoverabilityTitle:")
    protected static native @Pointer long create(String input, UIKeyModifierFlags modifierFlags, Selector action, String discoverabilityTitle);
    /*</methods>*/
}
