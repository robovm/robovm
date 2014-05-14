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
package org.robovm.apple.passkit;

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
import org.robovm.apple.uikit.*;
/*</imports>*/

/*<javadoc>*/
/**
 * @since Available in iOS 6.0 and later.
 */
/*</javadoc>*/
/*<annotations>*/@Library("PassKit") @NativeClass/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/PKPass/*</name>*/ 
    extends /*<extends>*/NSObject/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/public static class PKPassPtr extends Ptr<PKPass, PKPassPtr> {}/*</ptr>*/
    /*<bind>*/static { ObjCRuntime.bind(PKPass.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*/
    public PKPass() {}
    protected PKPass(SkipInit skipInit) { super(skipInit); }
    public PKPass(NSData data, NSError.NSErrorPtr error) { super((SkipInit) null); initObject(initWithData$error$(data, error)); }
    /*</constructors>*/
    /*<properties>*/
    @Property(selector = "serialNumber")
    public native String getSerialNumber();
    @Property(selector = "passTypeIdentifier")
    public native String getPassTypeIdentifier();
    @Property(selector = "webServiceURL")
    public native NSURL getWebServiceURL();
    @Property(selector = "authenticationToken")
    public native String getAuthenticationToken();
    @Property(selector = "icon")
    public native UIImage getIcon();
    @Property(selector = "localizedName")
    public native String getLocalizedName();
    @Property(selector = "localizedDescription")
    public native String getLocalizedDescription();
    @Property(selector = "organizationName")
    public native String getOrganizationName();
    @Property(selector = "relevantDate")
    public native NSDate getRelevantDate();
    @Property(selector = "userInfo")
    public native NSDictionary<NSString, ?> getUserInfo();
    @Property(selector = "passURL")
    public native NSURL getPassURL();
    /*</properties>*/
    /*<members>*//*</members>*/
    /*<methods>*/
    @Method(selector = "initWithData:error:")
    protected native @Pointer long initWithData$error$(NSData data, NSError.NSErrorPtr error);
    @Method(selector = "localizedValueForFieldKey:")
    public native NSObject getLocalizedValue(NSString key);
    /*</methods>*/
}
