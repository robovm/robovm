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
package org.robovm.apple.addressbook;

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
import org.robovm.apple.corefoundation.*;
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("AddressBook")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPersonSocialProfile/*</name>*/ 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSString> data = new NSMutableDictionary<>();
    private CFString label;
    
    public ABPersonSocialProfile(String label) {
        this.label = new CFString(label);
    }
    
    public ABPersonSocialProfile(ABPropertyLabel label) {
        this.label = label.value();
    }
    
    protected ABPersonSocialProfile(NSDictionary<NSString, NSString> data, CFString label) {
        this.data = data;
        this.label = label;
    }
    /*<bind>*/static { Bro.bind(ABPersonSocialProfile.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSDictionary<NSString, NSString> getDictionary() {
        return data;
    }
    
    public String getLabel() {
        return label.toString();
    }
    
    public CFString getLabel0() {
        return label;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getURL() {
        NSString val = data.get(URLKey());
        if (val != null) return val.toString();
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public void setURL(String url) {
        data.put(URLKey(), new NSString(url));
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public ABPersonSocialProfileService getService() {
        NSString val = data.get(ServiceKey());
        if (val != null) return ABPersonSocialProfileService.valueOf(val);
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public void setService(ABPersonSocialProfileService service) {
        data.put(ServiceKey(), service.value());
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getUsername() {
        NSString val = data.get(UsernameKey());
        if (val != null) return val.toString();
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public void setUsername(String username) {
        data.put(UsernameKey(), new NSString(username));
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public String getUserIdentifier() {
        NSString val = data.get(UserIdentifierKey());
        if (val != null) return val.toString();
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    public void setUserIdentifier(String userIdentifier) {
        data.put(UserIdentifierKey(), new NSString(userIdentifier));
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileURLKey", optional=true)
    protected static native NSString URLKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceKey", optional=true)
    protected static native NSString ServiceKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileUsernameKey", optional=true)
    protected static native NSString UsernameKey();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileUserIdentifierKey", optional=true)
    protected static native NSString UserIdentifierKey();
    /*</methods>*/
}
