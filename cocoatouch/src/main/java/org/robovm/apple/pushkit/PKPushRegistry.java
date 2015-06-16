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
package org.robovm.apple.pushkit;

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
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 8.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("PushKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PKPushRegistry/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class PKPushRegistryPtr extends Ptr<PKPushRegistry, PKPushRegistryPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PKPushRegistry.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PKPushRegistry() {}
    protected PKPushRegistry(SkipInit skipInit) { super(skipInit); }
    @WeaklyLinked
    public PKPushRegistry(DispatchQueue queue) { super((SkipInit) null); initObject(init(queue)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "delegate")
    public native PKPushRegistryDelegate getDelegate();
    @Property(selector = "setDelegate:", strongRef = true)
    public native void setDelegate(PKPushRegistryDelegate v);
    @Property(selector = "desiredPushTypes")
    public native @org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class) Set<String> getDesiredPushTypes();
    @Property(selector = "setDesiredPushTypes:")
    public native void setDesiredPushTypes(@org.robovm.rt.bro.annotation.Marshaler(NSSet.AsStringSetMarshaler.class)Set<String> v);
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "pushTokenForType:")
    public native NSData getPushTokenForType(String type);
    @WeaklyLinked
    @Method(selector = "initWithQueue:")
    protected native @Pointer long init(DispatchQueue queue);
    /*</methods>*/
}
