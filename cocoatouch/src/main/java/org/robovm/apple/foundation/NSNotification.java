/*
 * Copyright (C) 2014 Trillian AB
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
import org.robovm.apple.security.*;
/*</imports>*/

/**
 *
 * <div class="javadoc"></div>
 */
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSNotification/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*/implements NSCoding/*</implements>*/ {

    /*<ptr>*/public static class NSNotificationPtr extends Ptr<NSNotification, NSNotificationPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSNotification.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSNotification() {}
    protected NSNotification(SkipInit skipInit) { super(skipInit); }
    public NSNotification(String name, NSNotification object, NSDictionary<?, ?> userInfo) { super((SkipInit) null); initObject(initWithName$object$userInfo$(name, object, userInfo)); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "name")
    public native String name();
    @Method(selector = "object")
    public native NSObject object();
    @Method(selector = "userInfo")
    public native NSDictionary<?, ?> userInfo();
    @Method(selector = "initWithName:object:userInfo:")
    protected native @Pointer long initWithName$object$userInfo$(String name, NSNotification object, NSDictionary<?, ?> userInfo);
    @Method(selector = "notificationWithName:object:")
    public static native NSNotification notificationWithName$object$(String aName, NSObject anObject);
    @Method(selector = "notificationWithName:object:userInfo:")
    public static native NSNotification notificationWithName$object$userInfo$(String aName, NSObject anObject, NSDictionary<?, ?> aUserInfo);
    @Method(selector = "encodeWithCoder:")
    public native void encodeWithCoder$(NSCoder aCoder);
    /*</methods>*/
}
