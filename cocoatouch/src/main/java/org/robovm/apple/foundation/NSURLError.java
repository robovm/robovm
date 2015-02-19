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
/*<annotations>*/@Library("Foundation")/*</annotations>*/
/*<visibility>*/public/*</visibility>*/ class /*<name>*/NSURLError/*</name>*/ 
    extends /*<extends>*/NSError/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    protected NSURLError(SkipInit skipInit) {
        super(skipInit);
    }
    
    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(NSURLError.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    @Override
    public NSURLErrorCode getErrorCode() {
        return NSURLErrorCode.valueOf(getCode());
    }
    
    /**
     * @since Available in iOS 4.0 and later.
     */
    public NSURL getFailingURL() {
        NSErrorUserInfo userInfo = getUserInfo();
        if (userInfo.contains(NSURLErrorUserInfoKey.FailingURL)) {
            NSURL val = (NSURL)userInfo.get(NSURLErrorUserInfoKey.FailingURL);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public String getFailingURLString() {
        NSErrorUserInfo userInfo = getUserInfo();
        if (userInfo.contains(NSURLErrorUserInfoKey.FailingURLString)) {
            NSString val = (NSString)userInfo.get(NSURLErrorUserInfoKey.FailingURLString);
            return val.toString();
        }
        return null;
    }
    /**
     * @since Available in iOS 4.0 and later.
     */
    public SecTrust getFailingURLPeerTrust() {
        NSErrorUserInfo userInfo = getUserInfo();
        if (userInfo.contains(NSURLErrorUserInfoKey.FailingURLPeerTrust)) {
            SecTrust val = userInfo.get(NSURLErrorUserInfoKey.FailingURLPeerTrust, SecTrust.class);
            return val;
        }
        return null;
    }
    /**
     * @since Available in iOS 5.0 and later.
     */
    @SuppressWarnings("unchecked")
    public List<NSURLProperty> getUnsetProperties() {
        List<NSURLProperty> properties = new ArrayList<>();
        NSErrorUserInfo userInfo = getUserInfo();
        if (userInfo.contains(NSURLErrorUserInfoKey.KeysOfUnsetValues)) {
            NSArray<NSString> val = (NSArray<NSString>)userInfo.get(NSURLErrorUserInfoKey.KeysOfUnsetValues);
            for (NSString s : val) {
                NSURLProperty p = NSURLProperty.valueOf(s);
                if (p != null) properties.add(p);
            }
        }
        return properties;
    }
    /*<methods>*/
    @GlobalValue(symbol="NSURLErrorDomain", optional=true)
    public static native String getClassDomain();
    /*</methods>*/
}
