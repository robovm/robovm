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
import org.robovm.apple.coredata.*;
import org.robovm.apple.coregraphics.*;
import org.robovm.apple.coremedia.*;
import org.robovm.apple.security.*;
import org.robovm.apple.dispatch.*;
/*</imports>*/

/*<javadoc>*/

/*</javadoc>*/
/*<annotations>*/@Library("Foundation") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSNotificationCenter/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class NSNotificationCenterPtr extends Ptr<NSNotificationCenter, NSNotificationCenterPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(NSNotificationCenter.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public NSNotificationCenter() {}
    protected NSNotificationCenter(SkipInit skipInit) { super(skipInit); }
    /*</constructors>*/
    /*<properties>*/
    
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "addObserver:selector:name:object:")
    public native void addObserver(NSObject observer, Selector aSelector, NSString aName, NSObject anObject);
    @Method(selector = "postNotification:")
    public native void postNotification(NSNotification notification);
    @Method(selector = "postNotificationName:object:")
    public native void postNotification(NSString aName, NSObject anObject);
    @Method(selector = "postNotificationName:object:userInfo:")
    public native void postNotification(NSString aName, NSObject anObject, NSDictionary<NSString, ?> aUserInfo);
    @Method(selector = "removeObserver:")
    public native void removeObserver(NSObject observer);
    @Method(selector = "removeObserver:name:object:")
    public native void removeObserver(NSObject observer, NSString aName, NSObject anObject);
    /**
     * @since Available in iOS 4.0 and later.
     */
    @Method(selector = "addObserverForName:object:queue:usingBlock:")
    public native NSObject addObserver(NSString name, NSObject obj, NSOperationQueue queue, @Block VoidBlock1<NSNotification> block);
    @Method(selector = "defaultCenter")
    public static native NSNotificationCenter getDefaultCenter();
    /*</methods>*/
}
