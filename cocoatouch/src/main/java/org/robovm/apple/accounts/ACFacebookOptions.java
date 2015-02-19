/*
 * Copyright (C) 2013-2015 Trillian Mobile AB
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
package org.robovm.apple.accounts;

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
/*</imports>*/

/*<javadoc>*/
/*</javadoc>*/
/*<annotations>*/@Library("Accounts")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ACFacebookOptions/*</name>*/ 
    extends /*<extends>*/ACAccountOptions/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ACFacebookOptions.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public String getAppId() {
        if (data.containsKey(AppIdKey())) {
            NSString val = (NSString)data.get(AppIdKey());
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setAppId(String appId) {
        data.put(AppIdKey(), new NSString(appId));
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    @SuppressWarnings("unchecked")
    public List<String> getPermissions() {
        if (data.containsKey(PermissionsKey())) {
            NSArray<NSString> val = (NSArray<NSString>)data.get(PermissionsKey());
            return val.asStringList();
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setPermissions(Collection<String> permissions) {
        NSArray<NSString> p = NSArray.fromStrings(permissions);
        data.put(PermissionsKey(), p);
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setPermissions(String...permissions) {
        NSArray<NSString> p = NSArray.fromStrings(permissions);
        data.put(PermissionsKey(), p);
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public ACFacebookAudience getAudience() {
        if (data.containsKey(AudienceKey())) {
            NSString val = (NSString)data.get(AudienceKey());
            return ACFacebookAudience.valueOf(val);
        }
        return null;
    }
    /**
     * @since Available in iOS 6.0 and later.
     */
    public void setAudience(ACFacebookAudience audience) {
        data.put(AudienceKey(), audience.value());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACFacebookAppIdKey", optional=true)
    protected static native NSString AppIdKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACFacebookPermissionsKey", optional=true)
    protected static native NSString PermissionsKey();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACFacebookAudienceKey", optional=true)
    protected static native NSString AudienceKey();
    /*</methods>*/
}
