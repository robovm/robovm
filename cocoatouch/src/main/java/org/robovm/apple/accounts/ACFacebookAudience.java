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
/*<visibility>*/public/*</visibility>*/ class /*<name>*/ACFacebookAudience/*</name>*/ 
    extends /*<extends>*/CocoaUtility/*</extends>*/ 
    /*<implements>*//*</implements>*/ {

    /*<ptr>*/
    /*</ptr>*/
    /*<bind>*/static { Bro.bind(ACFacebookAudience.class); }/*</bind>*/
    /*<constants>*//*</constants>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final ACFacebookAudience Everyone = new ACFacebookAudience("EveryoneValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final ACFacebookAudience Friends = new ACFacebookAudience("FriendsValue");
    /**
     * @since Available in iOS 6.0 and later.
     */
    public static final ACFacebookAudience OnlyMe = new ACFacebookAudience("OnlyMeValue");
    private static ACFacebookAudience[] values = new ACFacebookAudience[] {Everyone, Friends, OnlyMe};
    
    private final LazyGlobalValue<NSString> lazyGlobalValue;
    
    private ACFacebookAudience(String getterName) {
        lazyGlobalValue = new LazyGlobalValue<>(getClass(), getterName);
    }
    /*<constructors>*//*</constructors>*/
    /*<properties>*//*</properties>*/
    /*<members>*//*</members>*/
    public NSString value() {
        return lazyGlobalValue.value();
    }
    
    public static ACFacebookAudience valueOf(NSString value) {
        if (value == null) throw new NullPointerException("value");
        for (ACFacebookAudience v : values) {
            if (v.value().equals(value)) {
                return v;
            }
        }
        throw new IllegalArgumentException("No constant with value " + value + " found in " 
            + /*<name>*/ACFacebookAudience/*</name>*/.class.getName());
    }
    /*<methods>*/
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACFacebookAudienceEveryone", optional=true)
    protected static native NSString EveryoneValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACFacebookAudienceFriends", optional=true)
    protected static native NSString FriendsValue();
    /**
     * @since Available in iOS 6.0 and later.
     */
    @GlobalValue(symbol="ACFacebookAudienceOnlyMe", optional=true)
    protected static native NSString OnlyMeValue();
    /*</methods>*/
}
