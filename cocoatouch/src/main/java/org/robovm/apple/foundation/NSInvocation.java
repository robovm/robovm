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
package org.robovm.apple.foundation;

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
import org.robovm.apple.corefoundation.*;
import org.robovm.apple.uikit.*;
import org.robovm.apple.coreanimation.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSInvocation/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSInvocationPtr extends Ptr<NSInvocation, NSInvocationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSInvocation.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSInvocation() {}
    protected NSInvocation(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "methodSignature")
    public native NSMethodSignature getMethodSignature();
    @Property(selector = "argumentsRetained")
    public native boolean isArgumentsRetained();
    @Property(selector = "target")
    public native NSObject getTarget();
    @Property(selector = "setTarget:", strongRef = true)
    public native void setTarget(NSObject v);
    @Property(selector = "selector")
    public native Selector getSelector();
    @Property(selector = "setSelector:")
    public native void setSelector(Selector v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "retainArguments")
    public native void retainArguments();
    @Method(selector = "getReturnValue:")
    public native void getReturnValue(VoidPtr retLoc);
    @Method(selector = "setReturnValue:")
    public native void setReturnValue(VoidPtr retLoc);
    @Method(selector = "getArgument:atIndex:")
    public native void getArgument(VoidPtr argumentLocation, @MachineSizedSInt long idx);
    @Method(selector = "setArgument:atIndex:")
    public native void setArgument(VoidPtr argumentLocation, @MachineSizedSInt long idx);
    @Method(selector = "invoke")
    public native void invoke();
    @Method(selector = "invokeWithTarget:")
    public native void invoke(NSObject target);
    @Method(selector = "invocationWithMethodSignature:")
    public static native NSInvocation create(NSMethodSignature sig);
    /*</methods>*/
}
