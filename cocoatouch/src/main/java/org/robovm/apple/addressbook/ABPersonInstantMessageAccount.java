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
/*<visibility>*/public/*</visibility>*/ class ABPersonInstantMessageAccount 
    extends /*<extends>*/Object/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    private NSDictionary<NSString, NSString> data = new NSMutableDictionary<>();
    private CFString label;
    
    public ABPersonInstantMessageAccount(String username, ABPersonInstantMessageService service, String label) {
        setUsername(username);
        setService(service);
        this.label = new CFString(label);
    }
    
    public ABPersonInstantMessageAccount(String username, ABPersonInstantMessageService service, ABPropertyLabel label) {
        setUsername(username);
        setService(service);
        this.label = label.value();
    }
    
    protected ABPersonInstantMessageAccount(NSDictionary<NSString, NSString> data, CFString label) {
        this.data = data;
        this.label = label;
    }
    /*<bind>*/static { Bro.bind(ABPersonInstantMessageAccount.class); }/*</bind>*/
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
    public CFString getLabel0 () {
        return label;
    }
    
    public void setUsername(String username) {
        data.put(UsernameKey(), new NSString(username));
    }
    public String getUsername() {
        NSString username = data.get(UsernameKey());
        if (username != null) return username.toString();
        return null;
    }
    public void setService(ABPersonInstantMessageService service) {
        data.put(ServiceKey(), service.value());
    }
    public ABPersonInstantMessageService getService() {
        NSString service = data.get(ServiceKey());
        if (service != null) return ABPersonInstantMessageService.valueOf(service);
        return null;
    }
    /*<methods>*/
    @GlobalValue(symbol="kABPersonInstantMessageServiceKey", optional=true)
    protected static native NSString ServiceKey();
    @GlobalValue(symbol="kABPersonInstantMessageUsernameKey", optional=true)
    protected static native NSString UsernameKey();
    /*</methods>*/
}
