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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ABPersonSocialProfileService/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ABPersonSocialProfileService.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ABPersonSocialProfileService Twitter = new ABPersonSocialProfileService("TwitterValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final ABPersonSocialProfileService SinaWeibo = new ABPersonSocialProfileService("SinaWeiboValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ABPersonSocialProfileService GameCenter = new ABPersonSocialProfileService("GameCenterValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ABPersonSocialProfileService Facebook = new ABPersonSocialProfileService("FacebookValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ABPersonSocialProfileService Myspace = new ABPersonSocialProfileService("MyspaceValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ABPersonSocialProfileService LinkedIn = new ABPersonSocialProfileService("LinkedInValue");
    /**
     * @since Available in iOS 5.0 and later.
     */
    public static final ABPersonSocialProfileService Flickr = new ABPersonSocialProfileService("FlickrValue");
    private static ABPersonSocialProfileService[] values = new ABPersonSocialProfileService[] {Twitter, GameCenter, Facebook, Myspace, LinkedIn, Flickr, SinaWeibo};
    
    private final LazyGlobalValue<CFString> lazyGlobalValue;
    
    private ABPersonSocialProfileService(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public CFString value() {
        return lazyGlobalValue.value();
    }
    public static ABPersonSocialProfileService valueOf(CFString value) {
        for (ABPersonSocialProfileService v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ABPersonSocialProfileService/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceTwitter", optional=true)
    protected static native CFString TwitterValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceSinaWeibo", optional=true)
    protected static native CFString SinaWeiboValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceGameCenter", optional=true)
    protected static native CFString GameCenterValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceFacebook", optional=true)
    protected static native CFString FacebookValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceMyspace", optional=true)
    protected static native CFString MyspaceValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceLinkedIn", optional=true)
    protected static native CFString LinkedInValue();
    /**
     * @since Available in iOS 5.0 and later.
     */
    @GlobalValue(symbol="kABPersonSocialProfileServiceFlickr", optional=true)
    protected static native CFString FlickrValue();
    /*</methods>*/
}
